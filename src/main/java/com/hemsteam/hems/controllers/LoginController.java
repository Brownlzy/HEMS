package com.hemsteam.hems.controllers;

import com.hemsteam.hems.utils.Log;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {
    private static final String TAG="LoginController";
    @FXML
    protected void onLoginButtonClick() {
        Log.d(TAG,"登录按钮被点击");
    }

    @FXML
    protected void onClearButtonClick(){
        Log.d(TAG,"清除按钮被点击");
    }
}