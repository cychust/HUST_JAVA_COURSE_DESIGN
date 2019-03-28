package cn.cychust.page.login;

import cn.cychust.comm.Executor;
import cn.cychust.data.tbrxx.source.TBRXXDataSource;
import com.jfoenix.controls.JFXButton;
import io.datafx.controller.ViewController;
import javafx.fxml.FXML;

import javax.annotation.PostConstruct;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-16 00:02
 **/
@ViewController(value = "/fxml/login.fxml")
public final class LoginController implements LoginContract.View {

    private LoginContract.Presenter mPresenter;
    @FXML
    private JFXButton btn_start;

    @PostConstruct
    public void init() throws Exception {

        setPresenter(new LoginPresenter(this, new TBRXXDataSource(Executor.getINSTANCE().getExecutor())));
        btn_start.setOnMouseClicked(e -> {
            mPresenter.register();
        });
    }

    @Override
    public void loginSuccess() {
        btn_start.setText("test");
    }

    @Override
    public void loginFailed() {

    }

    @Override
    public void logining() {

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
