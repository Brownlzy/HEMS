package com.hemsteam.hems.handlers;

import org.jetbrains.annotations.Nullable;

import java.sql.*;

public class DataBaseHelper {

    //类路径
    public static String Class_Name = "org.sqlite.JDBC";
    //数据库路径
    public static String DB_URL = "jdbc:sqlite:D:\\IDEA\\IDEA Project\\HEMS\\DataBase\\HEMS.sqlite";
    //连接变量
    private static Connection conn;
    //创建声明
    Statement stmt;

    private static DataBaseHelper dataBaseHelper;

    private DataBaseHelper() {
        //TODO: init
    }

    public static DataBaseHelper getInstance() {
        if (dataBaseHelper == null)
            dataBaseHelper = new DataBaseHelper();
        return dataBaseHelper;
    }

    @Nullable
    public String getPasswordHashByUsername(String userName) {
        if (/*用户不存在*/false) {
            return null;
        } else {
            return new String("44a096ad3826989684abd961f3c8f6cee31f9e80d2a93cbbc01e91a1d493cee0");//用户密码的哈希
        }
    }

    public boolean resigsterNewUser(String username, String passwordHash) {
        if (/*用户存在*/false) {
            return false;
        } else {
            //保存新用户
            return true;
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
                + "(ID vchar(16) PRIMARY KEY,"
                + "TYPE vchar(10),"
                + "YEAR int(4),"
                + "MONTH int(2),"
                + "DAY int(2),"
                + "POSITION vchar(20),"
                + "MONEY vchar(20))";
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
            pstmt.setString(2,pswd);
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
    public static void Data_Insert(String id,String type,int year,int month,int day,String position,String money) throws SQLException {
        if (conn != null) {
            String sql = "INSERT INTO Data VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,type);
            pstmt.setInt(3,year);
            pstmt.setInt(4,month);
            pstmt.setInt(5,day);
            pstmt.setString(6,position);
            pstmt.setString(7,money);
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
     * 打印Data表的内容
     * @param condition
     * @throws SQLException
     */
    public static void query(String condition) throws SQLException {
        if (conn != null) {
            String sql = "SELECT * FROM Data WHERE " + condition;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.print("id：" + rs.getString(1) + " ");
                System.out.print("type：" + rs.getString(2) + " ");
                System.out.print("year：" + rs.getInt(3) + " ");
                System.out.print("month：" + rs.getInt(4) + " ");
                System.out.print("day：" + rs.getInt(5) + " ");
                System.out.print("position：" + rs.getString(6) + " ");
                System.out.println("money：" + rs.getString(7));
            }
        }
    }

    /**
     * 打印满足conditon条件的ID表的内容
     * @param condition
     * @throws SQLException
     */
    public static void Data_Query(String condition) throws SQLException {
        if (conn != null) {
            String sql = "SELECT * FROM ID WHERE " + condition;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.print("id：" + rs.getString(1) + " ");
                System.out.print("password：" + rs.getString(2) + " ");
            }
        }
    }


}
