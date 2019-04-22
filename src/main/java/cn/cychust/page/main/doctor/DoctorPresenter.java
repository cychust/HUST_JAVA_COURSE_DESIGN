package cn.cychust.page.main.doctor;

import cn.cychust.State;
import cn.cychust.base.BasePresenterImpl;
import org.apache.log4j.Logger;


/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 15:07
 **/
public class DoctorPresenter extends BasePresenterImpl implements DoctorContract.Presenter {

//    private static final String TAG = "DoctorPresenter.class";

    private static final Logger LOGGER = Logger.getLogger(DoctorPresenter.class);

    private DoctorContract.View mView;

    public DoctorPresenter(DoctorContract.View view) {
        mView = view;
//        mView.setPresenter(this);
    }

    @Override
    public void logout() {
        LOGGER.info("aaaa");
        State.getINSTANCE().logout();
        LOGGER.debug(State.getINSTANCE().isLogin());
    }

}
