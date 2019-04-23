package cn.cychust.page.main.doctor;

import cn.cychust.State;
import cn.cychust.page.login.LoginController;
import cn.cychust.page.main.doctor_gh_list.DoctorGuahaoListController;
import cn.cychust.page.main.doctor_shouru.DoctorShouRuController;
import com.jfoenix.controls.JFXButton;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.annotation.PostConstruct;


/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-21 20:48
 **/
@ViewController(value = "/fxml/main_doctor.fxml")
public final class DoctorController implements DoctorContract.View {
    @FXML
    private JFXButton btn_logout;

    @FXML
    private JFXButton btn_gh_list;

    @FXML
    private JFXButton btn_shouru;

    private DoctorContract.Presenter mPresenter;

    @FXML
    private Label lb_title;

    @ActionHandler
    private FlowActionHandler actionHandler;

    @PostConstruct
    public void init() throws Exception {

        setPresenter(new DoctorPresenter(this));

        lb_title.setText("Welcome " + State.getT_ksys().getYSMC());

        btn_logout.setGraphic(new ImageView(new Image("/images/logout.png")));
        btn_logout.setMaxHeight(40);
        btn_logout.setMaxWidth(40);
        btn_logout.setOnMouseClicked(e -> {
            try {
                mPresenter.logout();
                navigateToLogin();
            } catch (VetoException | FlowException exception) {
                exception.printStackTrace();
            }
        });
        btn_logout.setTooltip(new Tooltip("退出"));
        btn_gh_list.setTooltip(new Tooltip("挂号界面"));
        btn_gh_list.setOnMouseClicked(event -> {
            try {
                navigateToGuaHaoList();
            } catch (VetoException | FlowException exception) {
                exception.printStackTrace();
            }
        });
        btn_shouru.setOnMouseClicked(event -> {
            try {
                navigateToShouRuList();
            } catch (VetoException | FlowException exception) {
                exception.printStackTrace();
            }
        });
    }

    private void navigateToLogin() throws VetoException, FlowException {
        actionHandler.navigate(LoginController.class);
    }


    private void navigateToGuaHaoList() throws VetoException, FlowException {
        actionHandler.navigate(DoctorGuahaoListController.class);
    }

    private void navigateToShouRuList() throws VetoException, FlowException {
        actionHandler.navigate(DoctorShouRuController.class);
    }


    @Override
    public void setPresenter(DoctorContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
