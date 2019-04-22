package cn.cychust.page.main.doctor;

import cn.cychust.page.login.LoginController;
import com.jfoenix.controls.JFXButton;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
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

    private DoctorContract.Presenter mPresenter;

    @ActionHandler
    private FlowActionHandler actionHandler;

    @PostConstruct
    public void init() throws Exception {

        setPresenter(new DoctorPresenter(this));

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
    }

    private void navigateToLogin() throws VetoException, FlowException {
        actionHandler.navigate(LoginController.class);
    }

    @Override
    public void setPresenter(DoctorContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
