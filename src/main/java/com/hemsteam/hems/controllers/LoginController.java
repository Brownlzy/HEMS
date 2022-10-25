package com.hemsteam.hems.controllers;

import com.hemsteam.hems.utils.Log;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    private static final String TAG="LoginController";
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;

    @FXML
    protected void onLoginButtonClick() {
        Log.d(TAG,"登录按钮被点击");
    }

    @FXML
    protected void onClearButtonClick(){
        Log.d(TAG,"清除按钮被点击");
        userName.clear();
        passWord.clear();
    }
}