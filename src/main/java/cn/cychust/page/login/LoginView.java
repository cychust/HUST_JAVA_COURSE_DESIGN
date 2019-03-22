package cn.cychust.page.login;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyphLoader;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-15 16:22
 **/
public class LoginView extends Application {

    @FXMLViewFlowContext
    private ViewFlowContext flowContext;


    @Override
    public void start(Stage primaryStage) throws Exception {
        initStage(primaryStage);
    }

    private void initStage(Stage primaryStage) throws Exception{
        new Thread(() -> {
            try {
                SVGGlyphLoader.loadGlyphsFont(LoginView.class.getResourceAsStream("/fonts/icomoon.svg"),
                        "icomoon.svg");
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            }
        }).start();
        Flow flow=new Flow(LoginController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flowContext = new ViewFlowContext();
        flowContext.register("Stage", primaryStage);
        flow.createHandler(flowContext).start(container);
        JFXDecorator decorator = new JFXDecorator(primaryStage, container.getView(),false, true, true);
        Scene scene = new Scene(decorator, 750, 500);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(400);
//        primaryStage.setTitle("Demo");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args){
        launch(args);
    }
}
