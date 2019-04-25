package cn.cychust.page.main.info;

import cn.cychust.State;
import cn.cychust.data.tbrxx.T_BRXX;
import cn.cychust.page.login.LoginController;
import cn.cychust.page.main.doctor.DoctorController;
import cn.cychust.page.main.patient.PatientController;
import com.jfoenix.controls.JFXButton;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.annotation.PostConstruct;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-23 19:50
 **/
@ViewController(value = "/fxml/main_info.fxml")
public class PatientInfoController {
    @FXML
    private Label lb_brbh;
    @FXML
    private Label lb_brmc;
    @FXML
    private Label lb_dlkl;
    @FXML
    private Label lb_ycje;
    @FXML
    private Label lb_dlrq;
    @FXML
    private JFXButton btn_logout;

    @FXML
    private JFXButton btn_back;

    @ActionHandler
    private FlowActionHandler actionHandler;

    @PostConstruct
    public void init() throws Exception {
        T_BRXX item = State.getT_brxx();
        lb_brbh.setText(item.getBRBH());
        lb_brmc.setText(item.getBRMC());
        lb_dlkl.setText(item.getDLKL());
        lb_dlrq.setText(item.getDLRQ().toString());
        lb_ycje.setText(String.valueOf(item.getYCJE()).split("\\.")[0]);

        btn_logout.setGraphic(new ImageView(new Image("/images/logout.png")));
        btn_logout.setMaxHeight(40);
        btn_logout.setMaxWidth(40);
        btn_logout.setOnMouseClicked(e -> {
            try {
                State.getINSTANCE().logout();
                navigateToLogin();
            } catch (VetoException | FlowException exception) {
                exception.printStackTrace();
            }
        });
        btn_back.setGraphic(new ImageView(new Image("/images/back2.png")));
        btn_back.setPrefHeight(20);
        btn_back.setPrefWidth(20);
        btn_back.setOnMouseClicked(event -> {
            try {
                navigateToBack();
            } catch (VetoException | FlowException exception) {
                exception.printStackTrace();
            }
        });
    }

    private void navigateToLogin() throws VetoException, FlowException {
        actionHandler.navigate(LoginController.class);
    }

    private void navigateToBack() throws VetoException, FlowException {
        actionHandler.navigate(PatientController.class);
    }
}
