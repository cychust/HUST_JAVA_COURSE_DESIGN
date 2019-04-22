package cn.cychust.page.login;

import cn.cychust.base.BasePresenterImpl;
import cn.cychust.data.tbrxx.T_BRXX;
import cn.cychust.data.tbrxx.source.TBRXXDataSource;
import cn.cychust.data.tbrxx.source.TBRXXRepository;
import cn.cychust.data.tksys.T_KSYS;
import cn.cychust.data.tksys.source.TKSYSDataSource;
import cn.cychust.data.tksys.source.TKSYSRepository;

/**
 * @program: hospital-manager-system
 * @description: login presenter
 * @author: Yichao Chen
 * @create: 2019-03-15 16:21
 **/
public class LoginPresenter extends BasePresenterImpl implements LoginContract.Presenter {
    private LoginContract.View mView;
    private TBRXXDataSource mTBRXXDataSource;

    private TKSYSDataSource mTksysDataSource;

    public LoginPresenter(LoginContract.View view, TBRXXDataSource tbrxxDataSource, TKSYSDataSource tKSYSDataSource) {
        mView = view;
        mTBRXXDataSource = tbrxxDataSource;
        mTksysDataSource = tKSYSDataSource;
        mView.setPresenter(this);
    }

    public void login(String user, String pass, boolean isBR) {
        mView.logining();
        if (isBR)
            mTBRXXDataSource.getOne(user, pass, new TBRXXRepository.GetTbrxxCallback() {
                @Override
                public void onTasksLoaded(T_BRXX t_brxx) {
                    mView.loginSuccess();
                }

                @Override
                public void onDataNotAvailable() {
                    mView.loginFailed();
                }
            });
        else
            mTksysDataSource.getOne(user, pass, new TKSYSRepository.GetTbrxxCallback() {
                @Override
                public void onTasksLoaded(T_KSYS t_brxx) {
                    mView.loginSuccess();
                }

                @Override
                public void onDataNotAvailable() {
                    mView.loginFailed();
                }
            });
    }

    public void register() {
        //todo register 界面
        mView.loginSuccess();
    }

}
