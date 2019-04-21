package cn.cychust.page.login;

import cn.cychust.comm.Executor;
import cn.cychust.data.tbrxx.source.TBRXXDataSource;
import cn.cychust.page.main.MainController;
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


    @ActionHandler
    private FlowActionHandler actionHandler;

    @PostConstruct
    public void init() throws Exception {

        setPresenter(new LoginPresenter(this, new TBRXXDataSource(Executor.getINSTANCE().getExecutor())));
        btn_start.setOnMouseClicked(e -> {
            if (tf_username.getText() == null || tf_username.getText().length() == 0) {
                tf_username.validate();
                return;
            }
            if (tf_password.getText() == null || tf_password.getText().length() == 0) {
                tf_password.validate();
                return;
            }
            mPresenter.login(tf_username.getText(), tf_password.getText());
        });

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
        lb_progress.setText("100.0%");
        try {
            navigateToHome();
        } catch (VetoException | FlowException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loginFailed() {
        prgs_login.setSecondaryProgress(0.0);
        lb_progress.setText("0.0%");
        JFXSnackbar snackbar = new JFXSnackbar(container);
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


    private void navigateToHome() throws VetoException, FlowException {
        actionHandler.navigate(MainController.class);
    }
}
