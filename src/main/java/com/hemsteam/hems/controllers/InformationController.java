package com.hemsteam.hems.controllers;

import com.hemsteam.hems.HEMS;
import com.hemsteam.hems.handlers.Account;
import com.hemsteam.hems.handlers.DataBaseHelper;
import com.hemsteam.hems.utils.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InformationController implements Initializable {
    private HEMS hems;



    @FXML
    private TextArea username;







    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            username.setText(Account.getUser());
            Log.d(this.getClass(),"用户名读出");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
