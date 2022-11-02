package com.hemsteam.hems.datamodels;

import java.util.Calendar;
import java.util.Date;

/**
 * 保存一笔消费记录的数据结构
 *
 * @author Brownlzy
 * @version 1.0
 */
public class Details {
    public String id;   //消费关联的用户名
    public Date date;   //该笔消费的时间
    public String time; //该笔消费的时间（字符串形式）
    public String type; //该笔消费的类型
    public String position; //该笔消费的地点
    public double moneyD;   //该笔消费的金额（double型）
    public String money;    //该笔消费的金额（String型）
    public String inTime;   //该笔消费记录创建的时间戳，用于避免两笔消费信息完全一致时导致无法区分的问题
    public boolean delete;  //该笔消费在明细页是否被选中的标记
    public String tip;  //该笔消费的备注

    /**
     * 构造一笔消费记录（用于明细表新建）
     * @author Brownlzy
     * @param id 关联的用户
     * @param date 消费时间
     * @param type 消费类型
     * @param position 消费地点
     * @param money 消费金额（double）
     * @param tip 备注
     */
    public Details(String id, Date date, String type, String position, double money, String tip) {
        this.id = id;
        this.date = date;
        this.time = String.format("%tF%n", this.date);
        this.type = type;
        this.position = position;
        this.moneyD = money;
        this.money = String.valueOf(moneyD);
        this.tip = tip;
        this.inTime = String.valueOf(new Date().getTime());
    }

    /**
     * 构造一笔消费记录（用于从数据库读取的数据）
     * @author Brownlzy
     * @param id 关联的用户
     * @param year 年
     * @param month 月
     * @param day 日
     * @param type 消费类型
     * @param position 消费地点
     * @param money 消费金额（double）
     * @param tip 备注
     * @param inTime 消费记录创建的时间戳
     */
    public Details(String id, int year, int month, int day, String type, String position, double money, String tip, String inTime) {
        this.id = id;
        Calendar c1 = Calendar.getInstance();
        c1.set(year, month - 1, day);
        this.date = c1.getTime();
        time = String.format("%tF%n", this.date);
        this.type = type;
        this.position = position;
        this.moneyD = money;
        this.money = String.valueOf(moneyD);
        this.tip = tip;
        this.inTime = inTime;
    }

    /**
     * 输出toString方法的数据构成
     * @author Brownlzy
     * @return java.lang.String
     */
    public static String toFormatString() {
        return "用户,时间,类型,位置,金额,备注";
    }

    /**
     * 重写toString方法，便于导出消费记录
     * @author Brownlzy
     * @return java.lang.String
     */
    @Override
    public String toString() {
        return id + "," + String.format("%tF", date) + "," + type + "," + position + "," + moneyD + "," + tip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        try {
            date = new Date(Integer.parseInt(time.split("-")[0]) - 1900,
                    Integer.parseInt(time.split("-")[1]) - 1,
                    Integer.parseInt(time.split("-")[2])
            );
            this.time = String.format("%tF%n", this.date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getMoneyD() {
        return moneyD;
    }

    public void setMoneyD(double money) {
        this.moneyD = money;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    //    private boolean delete ;
    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        try {
            moneyD = Double.parseDouble(money);
            this.money = money;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
