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
    private BarChart barchart;

    @FXML
    private TableColumn typeColumn;

    @FXML
    private TableColumn percentColumn;











    @FXML
    void onInquireClick(ActionEvent event) {


    }


    private CategoryAxis xAxis;

    private ObservableList<String> typeNames = FXCollections.observableArrayList();


    public void initialize(URL location, ResourceBundle resources) {




    }


}






