package com.hemsteam.hems;

import com.hemsteam.hems.controllers.LoginController;
import com.hemsteam.hems.controllers.MainController;
import com.hemsteam.hems.utils.Log;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class HEMS extends Application {
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.setTitle("登录界面");
        gotoLogin();
        stage.show();
    }

    /**
     * 跳转到登录界面
     */
    public void gotoLogin(){
        try{
            LoginController login = (LoginController) replaceSceneContent("login.fxml",300,150);
            login.setApp(this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Log.d(this.getClass(),"登录页面跳转异常！");
        }
    }


    /**
     * 跳转到主界面
     */
    public void gotoMain(){
        try{
            MainController main = (MainController) replaceSceneContent("main.fxml",1200,800);
            main.setApp(this);
        }
        catch (Exception ex) {
            Log.d(this.getClass(),"主页面跳转异常！");
        }
    }

    private Initializable replaceSceneContent(String fxml,int width,int height) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        InputStream in = HEMS.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(HEMS.class.getResource(fxml));
        try {
            BorderPane page = loader.load(in);
            Scene scene = new Scene(page,width,height);
            stage.setScene(scene);
            stage.sizeToScene();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(this.getClass(),"页面加载异常！");
        } finally {
            in.close();
        }
        return (Initializable)loader.getController();
    }
    public static void main(String[] args) {
        launch();
    }
}