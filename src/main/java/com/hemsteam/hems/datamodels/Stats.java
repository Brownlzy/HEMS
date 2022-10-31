package com.hemsteam.hems.datamodels;

import com.hemsteam.hems.handlers.Account;
import com.hemsteam.hems.handlers.DataBaseHelper;
import javafx.collections.ObservableList;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Stats {




    public String type;

    double money;

    public String percent;

    public String getType() {return type;}

    public void setType(String type) {
        this.type = type;
    }

    public String getPercent() {return percent;}


    public void setPercent(){this.percent=percent;}

    public double getMoney(){return money;}

    public void setMoney(double money){this.money=money;}





    public Stats(String type,double money ,String percent) {

        this.type = type;
        this.money=money;
        this.percent=percent;



    }


}
