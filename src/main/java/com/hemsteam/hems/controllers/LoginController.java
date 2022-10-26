package com.hemsteam.hems.controllers;

import com.hemsteam.hems.HEMS;
import com.hemsteam.hems.handlers.Account;
import com.hemsteam.hems.utils.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private static final String TAG = "LoginController";
    private HEMS hems;
    @FXML
    protected Label labelInfo;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;

    @FXML
    protected void onLoginButtonClick() {
        Log.d(this.getClass(), "登录按钮被点击");
        int returnId = Account.getInstance().login(userName.getText(), passWord.getText());
        switch (returnId) {
            case Account.LOGIN_SUCCESSFUL:
                Log.i(this.getClass(), "登录成功！用户名：" + userName.getText());
                hems.gotoMain();
                break;
            case Account.LOGIN_USERNAME_INVALID:
                Log.i(this.getClass(), "登录失败，reId=" + returnId);
                labelInfo.setText("输入的用户名无效！");
                break;
            case Account.LOGIN_PASSWORD_INVALID:
                Log.i(this.getClass(), "登录失败，reId=" + returnId);
                labelInfo.setText("输入的密码无效！");
                break;
            case Account.LOGIN_USERNAME_NOT_EXISTS:
                Log.i(this.getClass(), "登录失败，reId=" + returnId);
                labelInfo.setText("输入的用户不存在！");
                break;
            case Account.LOGIN_PASSWORD_ERROR:
                Log.i(this.getClass(), "登录失败，reId=" + returnId);
                labelInfo.setText("用户名或密码错误！");
                break;
        }
    }

    @FXML
    protected void onClearButtonClick(){
        Log.d(this.getClass(),"清除按钮被点击");
        userName.clear();
        passWord.clear();
    }

    @FXML
    protected void onResigsterClick(){
        Log.d(this.getClass(),"注册按钮被点击");
        hems.gotoResigster();
    }


    public void setApp(HEMS hems) {
        this.hems = hems;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}