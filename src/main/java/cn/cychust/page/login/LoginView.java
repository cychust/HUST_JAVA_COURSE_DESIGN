package cn.cychust.page.login;

import cn.cychust.base.BaseApplication;
import cn.cychust.controller.LoginController;
import cn.cychust.controller.MainController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-15 16:22
 **/
public class LoginView extends Application {

    @FXMLViewFlowContext
    private ViewFlowContext flowContext;
    public void start(Stage primaryStage) throws Exception {
        initStage(primaryStage);
    }

    private void initStage(Stage primaryStage) throws Exception{
        Flow flow=new Flow(LoginController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flowContext = new ViewFlowContext();
        flowContext.register("Stage", primaryStage);
        flow.createHandler(flowContext).start(container);
//        flow.startInStage(primaryStage);
//
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
