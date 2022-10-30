package com.hemsteam.hems.handlers;

import com.hemsteam.hems.datamodels.Details;
import com.hemsteam.hems.utils.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DataBaseHelper {

    //类路径
    public static String Class_Name = "org.sqlite.JDBC";
    //数据库路径
    public static String DB_URL = "jdbc:sqlite:HEMS.sqlite";
    //连接变量
    private static Connection conn;
    //创建声明
    static Statement stmt;

    private static DataBaseHelper dataBaseHelper;
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    private DataBaseHelper(){
        //TODO: init
        //创建数据库
        CreateDB();
        //创建ID表
        try {
            CreateTable_ID();
            Log.i(this.getClass(),"Build Success");
        } catch (SQLException e) {
            Log.e(this.getClass(),"CREATE ID ERROR");
            throw new RuntimeException();
        }

        //创建Data表
        try {
            CreateTable_Data();
            Log.i(this.getClass(),"Build Success");
        } catch (SQLException e) {
            Log.e(this.getClass(),"CREATE Data ERROR");
            throw new RuntimeException();
        }

        //创建SUMMARY表
        try {
            CreateTable_SUMMARY();
            Log.i(this.getClass(),"Build Success");
        } catch (SQLException e) {
            Log.e(this.getClass(),"CREATE Data ERROR");
            throw new RuntimeException();
        }

        //插入初始账户
        try {
            ID_Insert("admin","44a096ad3826989684abd961f3c8f6cee31f9e80d2a93cbbc01e91a1d493cee0");
            SUMMARY_Insert("admin",calendar.get(Calendar.MONTH)+1,"0");
        } catch (SQLException e) {

        }


    }

    public static DataBaseHelper getInstance() {
        if (dataBaseHelper == null)
            dataBaseHelper = new DataBaseHelper();
        return dataBaseHelper;
    }

    @Nullable
    public String getPasswordHashByUsername(String userName) {
//        if (/*用户不存在*/false) {
//            return null;
//        } else {
//            return new String("44a096ad3826989684abd961f3c8f6cee31f9e80d2a93cbbc01e91a1d493cee0");//用户密码的哈希
//        }

        try {
            Log.d(this.getClass(),userName);
            Log.d(this.getClass(),ID_Query("ID='"+userName+"'"));
            return ID_Query("ID='"+userName+"'");
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean resigsterNewUser(String username, String passwordHash) {
//        if (/*用户存在*/false) {
//            return false;
//        } else {
//            //保存新用户
//            return true;
//        }
        try {
            ID_Insert(username,passwordHash);
            SUMMARY_Insert(username,calendar.get(Calendar.MONTH)+1,"0");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * 功能：连接数据库，如果没有则创建
     */
    public void CreateDB() {
        try {
            Class.forName(Class_Name);
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Opened database successfully");
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *功能：创建ID表
     */
    public void CreateTable_ID() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS ID"
                + "(ID vchar(16) PRIMARY KEY,"
                + "PASSWORD vchar(16))";
        stmt.executeUpdate(sql);
    }

    /**
     *功能：创建Data表
     */
    public void CreateTable_Data() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Data"
                + "(ID vchar(16),"
                + "TYPE vchar(10),"
                + "YEAR int(4),"
                + "MONTH int(2),"
                + "DAY int(2),"
                + "POSITION vchar(20),"
                + "MONEY vchar(20),"
                + "TIP vchar(100))";
        stmt.executeUpdate(sql);
    }

    /**
     *功能：创建SUMMARY表
     */
    public void CreateTable_SUMMARY() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS SUMMARY"
                + "(ID vchar(16) PRIMARY KEY,"
                + "MONTH int(16),"
                + "SUM vchar(16))";
        stmt.executeUpdate(sql);
    }



    /**
     * 功能：ID表的插入
     * @param id
     * @param pswd
     * @throws SQLException
     */
    public static void ID_Insert(String id,String pswd) throws SQLException {
        if (conn != null) {
            String sql = "INSERT INTO ID VALUES(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,pswd);;
            pstmt.executeUpdate();
        }
    }

    /**
     * 功能：Data表的插入
     * @param id
     * @param type
     * @param year
     * @param month
     * @param day
     * @param position
     * @param money
     * @throws SQLException
     */
    public static void Data_Insert(String id,String type,int year,int month,int day,String position,String money,String tip) throws SQLException {
        if (conn != null) {
            String sql = "INSERT INTO Data VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,type);
            pstmt.setInt(3,year);
            pstmt.setInt(4,month);
            pstmt.setInt(5,day);
            pstmt.setString(6,position);
            pstmt.setString(7,money);
            pstmt.setString(8,tip);
            pstmt.executeUpdate();
        }
    }

    /**
     * 功能：SUMMARY表的插入
     * @param id
     * @param month
     * @param sum
     * @throws SQLException
     */
    public static void SUMMARY_Insert(String id,int month,String sum) throws SQLException {
        if (conn != null) {
            String sql = "INSERT INTO SUMMARY VALUES(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setInt(2,month);
            pstmt.setString(3,sum);
            pstmt.executeUpdate();
        }
    }


    /**
     * 功能：删除满足condition条件的ID表里的值
     * @param condition
     * @throws SQLException
     */
    public static void ID_Delete(String condition) throws SQLException {
        if (conn != null) {
            String sql = "DELETE FROM ID WHERE " + condition;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }
    }

    /**
     * 功能：删除满足condition条件的Data表里的值
     * @param condition
     * @throws SQLException
     */
    public static void Data_Delete(String condition) throws SQLException {
        if (conn != null) {
            String sql = "DELETE FROM Data WHERE " + condition;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }
    }

    /**
     * 功能：删除满足condition条件的SUMMARY表里的值
     * @param condition
     * @throws SQLException
     */
    public static void SUMMARY_Delete(String condition) throws SQLException {
        if (conn != null) {
            String sql = "DELETE FROM SUMMARY WHERE " + condition;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }
    }


    /**
     * 功能：ID内容更新,使满足condition的数据，修改value值
     * @param condition
     * @param value
     * @throws SQLException
     */
    public static void ID_Update(String condition,String value) throws SQLException {
        if (conn != null) {
            String sql = "UPDATE ID SET " + value + " WHERE " + condition;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }
    }

    /**
     * 功能：Data内容更新,使满足condition的数据，修改value值
     * @param condition
     * @param value
     * @throws SQLException
     */
    public static void Data_Update(String condition,String value) throws SQLException {
        if (conn != null) {
            String sql = "UPDATE Data SET " + value + " WHERE " + condition;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }
    }

    /**
     * 功能：SUMMARY内容更新,使满足condition的数据，修改value值
     * @param condition
     * @param value
     * @throws SQLException
     */
    public static void SUMMARY_Update(String condition,String value) throws SQLException {
        if (conn != null) {
            String sql = "UPDATE SUMMARY SET " + value + " WHERE " + condition;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }
    }

    /**
     * 查找满足conditon条件的ID表的内容
     * @param condition
     * @throws SQLException
     */
    public static String ID_Query(String condition) throws SQLException {
        if (conn != null) {
            String sql = "SELECT * FROM ID WHERE " + condition;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
//                System.out.print("id：" + rs.getString(1) + " ");
//                System.out.print("password：" + rs.getString(2) + " ");
                return rs.getString(2);
            }
        }
        return null;
    }


    /**
     * 查找Data表的内容
     *
     * @param condition
     * @throws SQLException
     */
    public static void Data_Query(String condition, List<Details> data) throws SQLException {
        if (conn != null) {
            String sql = "SELECT * FROM Data WHERE " + condition;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                data.add(
                        new Details(rs.getString(1),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getInt(5),
                                rs.getString(2),
                                rs.getString(6),
                                Double.parseDouble(rs.getString(7)),
                                rs.getString(8)
                        )
                );
//                System.out.print("id：" + rs.getString(1) + " ");
//                System.out.print("type：" + rs.getString(2) + " ");
//                System.out.print("year：" + rs.getInt(3) + " ");
//                System.out.print("month：" + rs.getInt(4) + " ");
//                System.out.print("day：" + rs.getInt(5) + " ");
//                System.out.print("position：" + rs.getString(6) + " ");
//                System.out.println("money：" + rs.getString(7) + " ");
//                System.out.println("tip:" + rs.getString(8));
            }
        }
    }

    /**
     * 查找满足conditon条件的SUMMARY表的内容
     * @param condition
     * @throws SQLException
     */
    public static String SUMMARY_Query(String condition) throws SQLException {
        if (conn != null) {
            String sql = "SELECT * FROM SUMMARY WHERE " + condition;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                return rs.getString(3);
            }
        }
        return null;
    }

    public boolean changePassword(String user, String passwordHash) {
        //TODO
        return true;
    }

    public ObservableList<Details> getDetails() {
        ObservableList<Details> result = FXCollections.observableArrayList();
        try {
            Data_Query("ID='" + Account.getUser() + "'", result);
        } catch (SQLException e) {
            return FXCollections.observableArrayList();
        }
        return result;
    }

    public ObservableList<Details> getDetailsByType(String type) {
        ObservableList<Details> result = FXCollections.observableArrayList();
        //TODO: 向result中add

        return result;
    }

    public ObservableList<Details> getDetailsByYear(int year) {
        ObservableList<Details> result = FXCollections.observableArrayList();
        //TODO: 向result中add
        return result;
    }

    public ObservableList<Details> getDetailsByMonth(int year, int month) {
        ObservableList<Details> result = FXCollections.observableArrayList();
        //TODO: 向result中add
        return result;
    }

    public ObservableList<Details> getDetailsByDay(int year, int month, int day) {
        ObservableList<Details> result = FXCollections.observableArrayList();
        //TODO: 向result中add
        return result;
    }
}
