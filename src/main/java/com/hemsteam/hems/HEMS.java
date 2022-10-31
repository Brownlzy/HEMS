package com.hemsteam.hems;


import com.hemsteam.hems.controllers.LoginController;
import com.hemsteam.hems.controllers.MainController;
import com.hemsteam.hems.controllers.ResigsterController;
import com.hemsteam.hems.handlers.Account;
import com.hemsteam.hems.utils.Log;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;


public class HEMS extends Application {
    private Stage stage;
    boolean max = false;


    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.setTitle("");
        gotoLogin();
        //gotoMain();
        stage.show();
    }

    /**
     * 跳转到登录界面
     */
    public void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("login.fxml", 680, 353);
            login.setApp(this);
            stage.setTitle("登录");
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d(this.getClass(), "登录页面跳转异常！");
        }
    }


    /**
     * 跳转到主界面
     */
    public void gotoMain(String innerFxml) {
        try {
            double height=800;
            double width=1200;
            //监听最大化按钮是否被点击
            stage.maximizedProperty().addListener((ov, t, t1) -> {
                Log.d(getClass(),"最大化被点击");
                max=t1;
            });
            if(!innerFxml.equals("default")) {
                if (max) {
                    height = Screen.getPrimary().getBounds().getHeight();
                    width = Screen.getPrimary().getBounds().getWidth();
                } else {
                    height = stage.getScene().getHeight();
                    width = stage.getScene().getWidth();
                }
            }else {
                innerFxml="overview.fxml";
            }

            MainController main = (MainController) replaceMainSceneContent(innerFxml, width, height);
            main.setApp(this);
            stage.setTitle("家庭支出管理系统");
            Account.setPage(innerFxml);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d(this.getClass(), "主页面跳转异常！");
        }
    }

    public void gotoMain() {
        gotoMain("default");
    }

    public void gotoResigster() {
        try {
            ResigsterController resigster = (ResigsterController) replaceSceneContent("resigster.fxml", 680, 353);
            resigster.setApp(this);
            stage.setTitle("注册");
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d(this.getClass(), "注册页面跳转异常！");
        }
    }

    private Initializable replaceSceneContent(String fxml, int width, int height) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        InputStream in = HEMS.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(HEMS.class.getResource(fxml));
        try {
            BorderPane page = loader.load(in);
            Scene scene = new Scene(page, width, height);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(this.getClass(), "页面加载异常！");
        } finally {
            in.close();
        }
        return (Initializable) loader.getController();
    }

    private Initializable replaceMainSceneContent(String fxml, double width, double height) throws Exception {
        FXMLLoader outerLoader = new FXMLLoader(getClass().getResource("main.fxml"));

        Scene scene = new Scene(outerLoader.load(),width,height);

        URL inner = getClass().getResource(fxml);
        // URL inner = getClass().getResource("inner2.fxml");

        FXMLLoader innerLoader = new FXMLLoader(inner);

        // get insertion point from outer fxml
        innerLoader.setRoot(outerLoader.getNamespace().get("insertionPoint"));

        innerLoader.load();

        try {
            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(this.getClass(), "页面加载异常！");
        }
        return (Initializable) outerLoader.getController();
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        launch();

    }
}