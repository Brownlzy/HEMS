package com.hemsteam.hems.controllers;

import com.hemsteam.hems.HEMS;
import com.hemsteam.hems.datamodels.Details;
import com.hemsteam.hems.handlers.Account;
import com.hemsteam.hems.handlers.DataBaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.TableColumn;


import java.net.URL;
import java.util.*;

public class StatsController implements Initializable {
    private static final String TAG = "StatsController";
    @FXML
    private CategoryAxis xAxis = new CategoryAxis();//设置x轴
    @FXML
    private NumberAxis yAxis = new NumberAxis();//设置y轴
    @FXML
    private BarChart<String,Number> barchart =
            new BarChart<>(xAxis, yAxis);//条状图
    @FXML
    private TableColumn typeColumn;
    @FXML
    private TableColumn percentColumn;
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        xAxis.setLabel("type");
        yAxis.setLabel("money");
        HashMap<String,Double>originData;
        originData=DataBaseHelper.getInstance().getTypeMoneyMap(Account.getUser(), Account.getYear(), Account.getMonth());
        XYChart.Series series = new XYChart.Series();
        series.setName("金额");
        for (String key:
                originData.keySet()) {
            if(!key.equals("AllType"))
                series.getData().add(new XYChart.Data(key, originData.get(key)));
        }
        barchart.getData().addAll(series);

    }
}






