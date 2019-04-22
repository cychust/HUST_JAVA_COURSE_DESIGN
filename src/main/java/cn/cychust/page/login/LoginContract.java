package cn.cychust.page.login;

import cn.cychust.base.BasePresenter;
import cn.cychust.base.BaseView;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-15 16:22
 **/
public class LoginContract {
    public interface View extends BaseView<Presenter> {
        void loginSuccess();

        void loginFailed();

        void logining();
    }

    public interface Presenter extends BasePresenter {
        void login(String user, String pass,boolean isDuZhe);

        void register();
    }
}
