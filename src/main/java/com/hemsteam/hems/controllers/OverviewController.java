package com.hemsteam.hems.controllers;

import com.hemsteam.hems.datamodels.Details;
import com.hemsteam.hems.handlers.Account;
import com.hemsteam.hems.handlers.DataBaseHelper;
import com.hemsteam.hems.utils.Log;
import com.hemsteam.hems.utils.Percent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;


public class OverviewController implements Initializable {
    private static final String TAG = "OverviewController";


    @FXML
    protected Label total;

    @FXML
    private PieChart pieChart;//饼状图

    private List<Details> list = new ArrayList<Details>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            String tag;//显示类型以及百分比
            double sum = 0;
            total.setText(String.valueOf(DataBaseHelper.getInstance().getTypeMoneyMap(Account.getUser(), Account.getYear(), Account.getMonth()).get("AllType")));
            Log.d(this.getClass(), "数据读出");
            HashMap<String,Double>optimizeData=getOptimizeData();
        for (String key:
                optimizeData.keySet()) {
            sum=sum+optimizeData.get(key);
        }
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (String key:
                optimizeData.keySet()) {
            tag=key+Percent.getPercent(
                    optimizeData.get(key),
                    sum);
            Log.d(getClass(),tag);
            pieChartData.add(new PieChart.Data(tag,optimizeData.get(key)));
        }
            pieChart.setData(pieChartData);
    }
    /**
     * 筛选出大于5%的消费类型值
     * @return HashMap
     */
    public HashMap<String ,Double> getOptimizeData(){
        HashMap<String,Double>originData;
        HashMap<String,Double>optimizeData=new HashMap<>();
        originData=DataBaseHelper.getInstance().getTypeMoneyMap(Account.getUser(), Account.getYear(), Account.getMonth());
        double other=originData.get("AllType");
        for (String key:
             originData.keySet()) {
            if(!key.equals("AllType")&&originData.get(key)>(originData.get("AllType")*0.05)){
                optimizeData.put(key,originData.get(key));
                other=other-originData.get(key);
            }
        }
        if(optimizeData.containsKey("其它")) {
            optimizeData.put("其它", optimizeData.get("其它") + other);
        }
        else
            optimizeData.put("其它",other);
        return optimizeData;
    }
}
