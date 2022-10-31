package com.hemsteam.hems.controllers;

import com.hemsteam.hems.HEMS;
import com.hemsteam.hems.handlers.Account;
import com.hemsteam.hems.utils.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private HEMS hems;

    @FXML
    private Button overview;
    @FXML
    private Button query;

    @FXML
    private Button help;

    @FXML
    private Button exit;

    @FXML
    private BorderPane insertionPoint;

    @FXML
    private Button stats;

    @FXML
    private Button details;

    @FXML
    private Button user;

    @FXML
    void onOverviewClick(ActionEvent event) {
        hems.gotoMain("overview.fxml");
    }

    @FXML
    void onUserClick(ActionEvent event) {
        hems.gotoMain("information.fxml");
    }

    @FXML
    void onHelpClick(ActionEvent event) {
        hems.gotoMain("help.fxml");
    }

    @FXML
    void onExitClick(ActionEvent event) {
        //Account.quit();
        hems.gotoLogin();
    }

    @FXML
    void onStatsClick(ActionEvent event) {
        hems.gotoMain("stats.fxml");

    }

    @FXML
    void onDetailsClick(ActionEvent event) {
        hems.gotoMain("details.fxml");
    }


    @FXML
    protected void onExitButtonClick() {
        Log.d(this.getClass(), "退出系统按钮被点击");
        hems.gotoLogin();
        Log.d(this.getClass(), "退出成功");
    }

    public void setApp(HEMS hems) {
        this.hems = hems;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        year.setText(String.valueOf(Account.getYear()));
        month.setText(String.valueOf(Account.getMonth()));
    }

    @FXML
    protected TextField year;
    @FXML
    protected TextField month;

    @FXML
    protected void onQueryClick() {
        Account.setYearMonth(Integer.parseInt(year.getText()),
                Integer.parseInt(month.getText()));
        hems.gotoMain(Account.getPage());
    }
}
