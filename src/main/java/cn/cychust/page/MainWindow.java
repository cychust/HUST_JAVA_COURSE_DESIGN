package cn.cychust.page;

import cn.cychust.comm.Executor;
import cn.cychust.mysql.DatabaseManager;
import cn.cychust.page.login.LoginController;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyphLoader;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-15 16:20
 **/
public class MainWindow extends Application {
    private static final Logger LOGGER = Logger.getLogger(MainWindow.class);

    @FXMLViewFlowContext
    private ViewFlowContext flowContext;


    @Override
    public void start(Stage primaryStage) throws Exception {
        initStage(primaryStage);
    }

    private void initStage(Stage primaryStage) throws Exception {
        new Thread(() -> {
            try {
                SVGGlyphLoader.loadGlyphsFont(MainWindow.class.getResourceAsStream("/fonts/icomoon.svg"),
                        "icomoon.svg");
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            }
        }).start();
        Flow flow = new Flow(LoginController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flowContext = new ViewFlowContext();
        flowContext.register("Stage", primaryStage);
//        flow.createHandler(flowContext).start(container);
        JFXDecorator decorator = new JFXDecorator(primaryStage, container.getView(), false, true, true);
        Scene scene = new Scene(decorator, 750, 500);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(400);
//        primaryStage.setTitle("Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
        flow.createHandler(flowContext).start(container);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
        DatabaseManager.getINSTANCE().getConnection().close();
        Executor.getINSTANCE().getExecutor().shutdown();

    }
}
