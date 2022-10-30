package com.hemsteam.hems.datamodels;

import java.util.Calendar;
import java.util.Date;

public class Details {
    public String id;
    public Date date;
    public String type;
    public String position;
    public double money;
    public String tip;

    public Details(String id, Date date, String type, String position, double money, String tip) {
        this.id = id;
        this.date = date;
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
        this.type = type;
        this.position = position;
        this.money = money;
        this.tip = tip;
    }

    @Override
    public String toString() {
        return id + "\t" + String.format("%tF%n", date) + "\t" + type + "\t" + position + "\t" + money + "\t" + tip;
    }

    public String toFormatString() {
        return "用户\t时间\t类型\t位置\t金额\t备注";
    }
}
