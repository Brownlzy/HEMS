package com.hemsteam.hems.controllers;

import com.hemsteam.hems.handlers.Account;
import com.hemsteam.hems.handlers.DataBaseHelper;
import com.hemsteam.hems.utils.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class OverviewController implements Initializable {
    private static final String TAG = "OverviewController";


    @FXML
    protected Label total;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            total.setText(DataBaseHelper.getInstance().SUMMARY_Query("ID='"+ Account.getUser()+"'"));
            Log.d(this.getClass(),"数据读出");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
