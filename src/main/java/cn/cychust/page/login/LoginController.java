package cn.cychust.page.login;

import cn.cychust.data.tbrxx.source.TBRXXDataSource;
import cn.cychust.data.tksys.source.TKSYSDataSource;
import cn.cychust.page.main.doctor.DoctorController;
import cn.cychust.page.main.patient.PatientController;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-16 00:02
 **/
@ViewController(value = "/fxml/login.fxml")
public final class LoginController implements LoginContract.View {

    private final static Logger LOGGER = Logger.getLogger(LoginController.class);
    @FXML
    private AnchorPane container;

    private LoginContract.Presenter mPresenter;
    @FXML
    private JFXButton btn_start;

    @FXML
    private JFXProgressBar prgs_login;

    @FXML
    private Label lb_progress;

    @FXML
    private JFXTextField tf_username;

    @FXML
    private JFXPasswordField tf_password;

    @FXML
    private JFXRadioButton rb_duzhe;

    @FXML
    private JFXRadioButton rb_gzry;
    private boolean isBR;

//    private boolean isRememberPassword;


    @FXML
    private JFXCheckBox cb_rememberInfo;//是否记住密码

    @ActionHandler
    private FlowActionHandler actionHandler;

    @PostConstruct
    public void init() throws Exception {

        setPresenter(new LoginPresenter(this, new TBRXXDataSource(), TKSYSDataSource.getINSTANCE()));
        btn_start.setOnMouseClicked(e -> {
            if (tf_username.getText() == null || tf_username.getText().length() == 0) {
                tf_username.validate();
                return;
            }
            if (tf_password.getText() == null || tf_password.getText().length() == 0) {
                tf_password.validate();
                return;
            }
            mPresenter.login(tf_username.getText(), tf_password.getText(), isBR);
        });

        tf_password.setText("123");
        tf_username.setText("000001");
        tf_username.setTooltip(new Tooltip("用户名"));
        tf_password.setTooltip(new Tooltip("密码"));
        btn_start.setTooltip(new Tooltip("登录"));

        rb_duzhe.setSelected(true);
        rb_duzhe.setTooltip(new Tooltip("病人身份"));
        isBR = true;
        //选择读者身份
        rb_duzhe.setOnMouseClicked(a -> {
            isBR = true;
        });

        //选择工作人员
        rb_gzry.setOnMouseClicked(a -> {
            isBR = false;
        });

        rb_gzry.setTooltip(new Tooltip("医生身份"));

        initializeFields();
    }


    public void initializeFields() {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input required");
        validator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(FontAwesomeIcon.WARNING)
                .size("1em")
                .styleClass("error")
                .build());
        tf_username.getValidators().add(validator);
        tf_username.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                tf_username.validate();
            }
        });

        validator = new RequiredFieldValidator();
        validator.setMessage("Input required");
        validator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(FontAwesomeIcon.WARNING)
                .size("1em")
                .styleClass("error")
                .build());
        tf_password.getValidators().add(validator);
        tf_password.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                tf_password.validate();
            }
        });
    }

    @Override
    public void loginSuccess() {
        prgs_login.setSecondaryProgress(1.0);
        if (cb_rememberInfo.isFocused()) {

        }
        lb_progress.setText("100.0%");

        try {
            if (isBR)
                navigateToPatientHome();
            else
                navigateToDoctorHome();
        } catch (VetoException | FlowException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loginFailed() {
        prgs_login.setSecondaryProgress(0.0);
        lb_progress.setText("0.0%");
        JFXSnackbar snackbar = new JFXSnackbar(container);
//        snackbar.setStyle("-fx-text-fill: BLUE");
        snackbar.setStyle("-fx-background-color: #000000;-fx-text-fill: #ffffff");
        snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(new JFXSnackbarLayout("失败"), Duration.millis(3000), null));
    }

    @Override
    public void logining() {
        prgs_login.setSecondaryProgress(0.5);
        lb_progress.setText("50.0%");
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }


    private void navigateToPatientHome() throws VetoException, FlowException {
        actionHandler.navigate(PatientController.class);
    }

    private void navigateToDoctorHome() throws VetoException, FlowException {
        actionHandler.navigate(DoctorController.class);
    }

}
