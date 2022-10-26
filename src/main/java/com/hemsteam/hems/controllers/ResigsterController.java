package com.hemsteam.hems.controllers;

import com.hemsteam.hems.HEMS;
import com.hemsteam.hems.utils.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ResigsterController implements Initializable {

    private HEMS hems;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;
    @FXML
    private PasswordField passWordRepeat;

    public ResigsterController() {
    }

    @FXML
    protected void onClearButtonClick(){
        Log.d(this.getClass(),"清除按钮被点击");
        userName.clear();
        passWord.clear();
        passWordRepeat.clear();
    }
    @FXML
    protected void onResigsterButtonClick(){
        Log.d(this.getClass(),"注册按钮被点击");
        hems.gotoMain();
    }
    @FXML
    public void setApp(HEMS hems) {
        this.hems = hems;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * 返回 用户名
     * @return String
     */
    public String getUsername(){
        return userName.getText();
    }

    /**
     * 返回 密码
     * @return String
     */
    public String getPassword(){
        return passWord.getText();
    }

    /**
     * 返回二次输入密码
     * @return String
     */
    public String getPasswordrepeat(){
        return passWordRepeat.getText();
    }
}
