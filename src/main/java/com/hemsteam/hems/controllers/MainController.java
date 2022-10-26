package com.hemsteam.hems.controllers;

import com.hemsteam.hems.HEMS;
import com.hemsteam.hems.utils.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private HEMS hems;

    @FXML
    protected void onExitButtonClick(){
        Log.d(this.getClass(),"退出系统按钮被点击");
        hems.gotoLogin();
        Log.d(this.getClass(),"退出成功");
    }

    public void setApp(HEMS hems) {
        this.hems = hems;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
