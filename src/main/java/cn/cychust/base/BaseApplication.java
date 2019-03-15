package cn.cychust.base;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-15 16:28
 **/
public abstract class BaseApplication extends Application {

    Stage primaryStage;

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initStage(primaryStage);
    }

    public void hide() {
        this.primaryStage.hide();
    }

    public void close() {
        this.primaryStage.close();
    }

    public abstract void initStage(Stage primaryStage) throws Exception;

}
