package com.hemsteam.hems.controllers;

import com.hemsteam.hems.HEMS;
import com.hemsteam.hems.datamodels.Details;
import com.hemsteam.hems.datamodels.Stats;
import com.hemsteam.hems.handlers.Account;
import com.hemsteam.hems.handlers.DataBaseHelper;
import com.hemsteam.hems.utils.Percent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class StatsController implements Initializable {
    private static final String TAG = "StatsController";
    @FXML
    public Label date;
    @FXML
    private CategoryAxis xAxis = new CategoryAxis();//设置x轴
    @FXML
    private NumberAxis yAxis = new NumberAxis();//设置y轴
    @FXML
    private BarChart<String, Number> barchart =
            new BarChart<>(xAxis, yAxis);//条状图

   

    @FXML
    TableView<Stats> table;


    @FXML
    TableColumn typeColumn;
    @FXML
    TableColumn percentColumn;

    @FXML
    private TableColumn moneyColumn;




    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        xAxis.setLabel("种类");
        yAxis.setLabel("金额");

        date.setText(String.valueOf(Account.getYear())+"年"+String.valueOf(Account.getMonth())+"月");
        HashMap<String, Double> originData;
        HashMap<String,Double>optimizeData;
        originData = DataBaseHelper.getInstance().getTypeMoneyMap(Account.getUser(), Account.getYear(), Account.getMonth());
        optimizeData=originData.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        XYChart.Series series = new XYChart.Series();
        series.setName("金额");
        for (String key :
                optimizeData.keySet()) {
            if (!key.equals("AllType"))
                series.getData().add(new XYChart.Data(key, optimizeData.get(key)));
        }
        barchart.getData().addAll(series);



        if (!originData.containsKey("AllType")&&originData.size()==0) {
        }
        else {
            ObservableList<Stats> data =
                    FXCollections.observableArrayList();
            double sum = originData.get("AllType");


            for (String key :
                    originData.keySet()) {
                if (!key.equals("AllType"))
                    data.add(new Stats(key, originData.get(key), Percent.getPercent(
                            originData.get(key),
                            sum)));
            }

            table.setEditable(true);
            typeColumn.setCellValueFactory(
                    new PropertyValueFactory<>("type"));
            moneyColumn.setCellValueFactory(
                    new PropertyValueFactory<>("money"));
            percentColumn.setCellValueFactory(
                    new PropertyValueFactory<>("percent"));

            table.getColumns().addAll(typeColumn, moneyColumn, percentColumn);

            table.setItems(data);
        }




    }
}













