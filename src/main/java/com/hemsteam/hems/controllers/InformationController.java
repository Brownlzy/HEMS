package com.hemsteam.hems.controllers;

import com.hemsteam.hems.HEMS;
import com.hemsteam.hems.handlers.Account;
import com.hemsteam.hems.utils.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

public class InformationController implements Initializable {
    private HEMS hems;

    @FXML
    private PasswordField newPasswd;

    @FXML
    private PasswordField rePasswd;

    @FXML
    private Label userName;

    @FXML
    private PasswordField oldPasswd;

    @FXML
    private Label info;

    @FXML
    void onChangePasswd() {
        oldPasswd.setPromptText("请输入原密码");
        newPasswd.setPromptText("请输入新密码");
        rePasswd.setPromptText("请重新输入密码");
        switch (
                Account.getInstance().changPassword(oldPasswd.getText(), newPasswd.getText(), rePasswd.getText())
        ) {
            case Account.LOGIN_PASSWORD_ERROR:
                oldPasswd.clear();
                oldPasswd.setPromptText("原密码输入错误");
                break;
            case Account.RESIGSTER_PASSWORD_INVALID:
                newPasswd.clear();
                newPasswd.setPromptText("新密码密码不和规范（最少4位）");
                break;
            case Account.RESIGSTER_PASSWORD_UNANIMOUS:
                rePasswd.clear();
                rePasswd.setPromptText("两次输入密码不匹配");
                break;
            case Account.RESIGSTER_SUCCESSFUL:
                oldPasswd.clear();
                newPasswd.clear();
                rePasswd.clear();
                info.setText("修改成功");
                break;
            case Account.RESIGSTER_DATABASE_FAILED:
                info.setText("修改失败");
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userName.setText(Account.getUser());
            Log.d(this.getClass(), "用户名读出");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
