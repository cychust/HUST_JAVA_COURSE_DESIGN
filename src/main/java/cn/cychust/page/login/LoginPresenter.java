package cn.cychust.page.login;

import cn.cychust.base.BasePresenterImpl;
import cn.cychust.comm.BaseObserver;
import cn.cychust.data.Repository;

/**
 * @program: hospital-manager-system
 * @description: login presenter
 * @author: Yichao Chen
 * @create: 2019-03-15 16:21
 **/
public class LoginPresenter extends BasePresenterImpl implements LoginContract.Presenter {
    private LoginContract.View mView;
    private Repository mRepository;

    public LoginPresenter(LoginContract.View view, Repository repository) {
        mView = view;
        mRepository = repository;
        mView.setPresenter(this);
    }

    public void login(String user, String pass) {

        DatabaseHelper.findAll();

        mRepository.login(user, pass).subscribe(new BaseObserver<Boolean>() {
            @Override
            public void onMyError(Throwable throwable) {
                //todo log
            }

            @Override
            public void onNext(Boolean o) {
                if (o) {
                    //开启新界面
                } else {
                    //log
                }
            }
        });
    }

    public void register() {
        //todo register 界面
        DatabaseHelper.findAll();
        mView.loginSuccess();
    }

}
