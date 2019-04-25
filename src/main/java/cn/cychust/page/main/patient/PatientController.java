package cn.cychust.page.main.patient;

import cn.cychust.State;
import cn.cychust.base.BasePresenterImpl;
import cn.cychust.page.login.LoginController;
import cn.cychust.page.main.guahao.GuaHaoController;
import cn.cychust.page.main.info.PatientInfoController;
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
 * @create: 2019-04-22 12:30
 **/
@ViewController(value = "/fxml/main_patient.fxml")
public class PatientController implements PatientContract.View {


    private PatientContract.Presenter mPresenter;
    @FXML
    private JFXButton btn_logout;

    @FXML
    private JFXButton btn_gh;

    @FXML
    private JFXButton btn_info;


    @FXML
    private Label lb_title;

    @ActionHandler
    private FlowActionHandler actionHandler;

    @PostConstruct
    public void init() throws Exception {
        setPresenter(new PatientPresenter(this));

        lb_title.setText("Welcome " + State.getT_brxx().getBRMC());
        btn_logout.setGraphic(new ImageView(new Image("/images/logout.png")));
        btn_logout.setMaxHeight(40);
        btn_logout.setMaxWidth(40);
        btn_logout.setOnMouseClicked(e -> {
            try {
                mPresenter.logout();
                navigateToLoginView();
            } catch (VetoException | FlowException exception) {
                exception.printStackTrace();
            }
        });

        btn_gh.setOnMouseClicked(event -> {
            try {
                navigateToGuahaoView();
            } catch (VetoException | FlowException e) {
                e.printStackTrace();
            }
        });
        btn_info.setOnMouseClicked(event -> {
            try {
                navigateToInfoView();
            } catch (VetoException | FlowException e) {
                e.printStackTrace();
            }
        });
    }

    private void navigateToLoginView() throws VetoException, FlowException {
        actionHandler.navigate(LoginController.class);
    }

    private void navigateToGuahaoView() throws VetoException, FlowException {
        actionHandler.navigate(GuaHaoController.class);
    }

    private void navigateToInfoView() throws VetoException, FlowException {
        actionHandler.navigate(PatientInfoController.class);
    }

    @Override
    public void setPresenter(PatientContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
