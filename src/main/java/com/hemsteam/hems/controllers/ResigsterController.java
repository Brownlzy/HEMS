package com.hemsteam.hems.controllers;

import com.hemsteam.hems.handlers.DataBaseHelper;
import com.hemsteam.hems.HEMS;
import com.hemsteam.hems.handlers.Account;
import com.hemsteam.hems.utils.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResigsterController implements Initializable {

    private HEMS hems;
    @FXML
    protected Label labelInfo;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;
    @FXML
    private PasswordField passWordRepeat;
    @FXML
    private ImageView Image1;

    public ResigsterController() {
    }

    @FXML
    protected void onBackButtonClick() {
        Log.d(this.getClass(), "返回按钮被点击");
        hems.gotoLogin();
    }

    @FXML
    protected void onResigsterButtonClick() {
        Log.d(this.getClass(), "注册按钮被点击");
        //hems.gotoMain();
        int returnId = Account.getInstance().resigster(userName.getText(), passWord.getText(), passWordRepeat.getText());
        switch (returnId) {
            case Account.RESIGSTER_SUCCESSFUL:
                Log.i(this.getClass(), "注册成功！用户名：" + userName.getText());
                labelInfo.setText("注册成功，请返回登录！");
                break;
            case Account.RESIGSTER_USERNAME_INVALID:
                Log.i(this.getClass(), "注册失败，reId=" + returnId);
                labelInfo.setText("输入的用户名无效！");
                break;
            case Account.RESIGSTER_PASSWORD_INVALID:
                Log.i(this.getClass(), "注册失败，reId=" + returnId);
                labelInfo.setText("输入的密码无效！");
                break;
            case Account.RESIGSTER_USERNAME_EXISTS:
                Log.i(this.getClass(), "注册失败，reId=" + returnId);
                labelInfo.setText("输入的用户名已存在！");
                break;
            case Account.RESIGSTER_PASSWORD_UNANIMOUS:
                Log.i(this.getClass(), "注册失败，reId=" + returnId);
                labelInfo.setText("两次输入的密码不一致！");
                break;
        }
    }
    @FXML
    public void setApp(HEMS hems) {
        this.hems = hems;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/main/resources/draw/cover.png");
        String string = file.toURI().toString();
        Image image = new Image(string);
        Image1.setImage(image);
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
