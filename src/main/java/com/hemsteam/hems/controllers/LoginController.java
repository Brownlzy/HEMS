package com.hemsteam.hems.controllers;

import com.hemsteam.hems.HEMS;
import com.hemsteam.hems.utils.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private static final String TAG="LoginController";
    private HEMS hems;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;
    @FXML
    protected void onLoginButtonClick() {
        Log.d(this.getClass(),"登录按钮被点击");
        if("123456".equalsIgnoreCase(userName.getText())
                && "123456".equalsIgnoreCase(passWord.getText())) {
            Log.d(this.getClass(),"登录成功！");
            hems.gotoMain();
        } else {
            Log.d(this.getClass(),"登录失败");
        }
    }

    @FXML
    protected void onClearButtonClick(){
        Log.d(this.getClass(),"清除按钮被点击");
        userName.clear();
        passWord.clear();
    }

    public void setApp(HEMS hems) {
        this.hems = hems;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}