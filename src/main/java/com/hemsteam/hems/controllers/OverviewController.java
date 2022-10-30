package com.hemsteam.hems.controllers;

import com.hemsteam.hems.handlers.Account;
import com.hemsteam.hems.handlers.DataBaseHelper;
import com.hemsteam.hems.utils.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;


public class OverviewController implements Initializable {
    private static final String TAG = "OverviewController";


    @FXML
    protected Label total;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            HashMap<String, Double> ty =
                    DataBaseHelper.getInstance().SUMMARY_Query(
                            "ID='" + Account.getUser() + "' and MONTH=" +
                                    DataBaseHelper.getYearMonth(Account.getYear(), Account.getMonth()));
            total.setText(String.valueOf(DataBaseHelper.getInstance().getTypeMoneyMap(Account.getUser(), Account.getYear(), Account.getMonth()).get("AllType")));
            Log.d(this.getClass(), "数据读出");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
