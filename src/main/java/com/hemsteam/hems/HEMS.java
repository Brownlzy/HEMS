package com.hemsteam.hems;


import com.hemsteam.hems.controllers.LoginController;
import com.hemsteam.hems.controllers.MainController;
import com.hemsteam.hems.controllers.ResigsterController;
import com.hemsteam.hems.utils.Log;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;


public class HEMS extends Application {
    private Stage stage;


    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.setTitle("");
        //gotoLogin();
        gotoMain();
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
            MainController main = (MainController) replaceMainSceneContent(innerFxml, 1200, 800);
            main.setApp(this);

//            final FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(innerFxml));
//            fxmlLoader.setRoot(this);
//            fxmlLoader.setController(this);

            stage.setTitle("家庭支出管理系统");
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d(this.getClass(), "主页面跳转异常！");
        }
    }

    public void gotoMain() {
        gotoMain("overview.fxml");
    }

    public void gotoResigster() {
        try {
            ResigsterController resigster = (ResigsterController) replaceSceneContent("resigster.fxml", 300, 200);
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

    private Initializable replaceMainSceneContent(String fxml, int width, int height) throws Exception {
        FXMLLoader outerLoader = new FXMLLoader(getClass().getResource("main.fxml"));

        Scene scene = new Scene(outerLoader.load(), width, height);

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