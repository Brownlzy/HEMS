package com.hemsteam.hems.datamodels;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

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

    public String id;
    public Date date;
    public String time;
    public String type;
    public String position;
    public double moneyD;
    public String inTime;

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean delete;

//    private boolean delete ;
    public String getMoney() {
        return money;
    }


//    public void setDelete(boolean newValue){
//        this.delete=newValue;
//    }
//    public boolean getDelete(){
//        return this.delete;
//    }


    public void setMoney(String money) {
        try {
            moneyD = Double.parseDouble(money);
            this.money = money;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String money;
    public String tip;

    public Details(String id, Date date, String type, String position, double money, String tip, String inTime) {
        this.id = id;
        this.date = date;
        this.time = String.format("%tF%n", this.date);
        this.type = type;
        this.position = position;
        this.moneyD = money;
        this.money = String.valueOf(moneyD);
        this.tip = tip;
        this.inTime = inTime;
    }

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

    @Override
    public String toString() {
        return id + "," + String.format("%tF", date) + "," + type + "," + position + "," + moneyD + "," + tip;
    }

    public static String toFormatString() {
        return "用户,时间,类型,位置,金额,备注";
    }
}
