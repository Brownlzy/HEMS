package com.hemsteam.hems.datamodels;

import java.util.Calendar;
import java.util.Date;

public class Details {
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
        this.time = time;
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String id;
    public Date date;
    public String time;
    public String type;
    public String position;
    public double money;
    public String tip;

    public Details(String id, Date date, String type, String position, double money, String tip) {
        this.id = id;
        this.date = date;
        this.time = String.format("%tF%n", this.date);
        this.type = type;
        this.position = position;
        this.money = money;
        this.tip = tip;
    }

    public Details(String id, int year, int month, int day, String type, String position, double money, String tip) {
        this.id = id;
        Calendar c1 = Calendar.getInstance();
        c1.set(year, month - 1, day);
        this.date = c1.getTime();
        time = String.format("%tF%n", this.date);
        this.type = type;
        this.position = position;
        this.money = money;
        this.tip = tip;
    }

    @Override
    public String toString() {
        return id + "," + String.format("%tF%n", date) + "," + type + "," + position + "," + money + "," + tip;
    }

    public static String toFormatString() {
        return "用户,时间,类型,位置,金额,备注";
    }
}
