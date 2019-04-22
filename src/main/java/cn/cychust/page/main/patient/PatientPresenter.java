package cn.cychust.page.main.patient;

import cn.cychust.State;
import cn.cychust.base.BasePresenterImpl;
import org.apache.log4j.Logger;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 15:37
 **/
public class PatientPresenter extends BasePresenterImpl implements PatientContract.Presenter {


    private final static Logger LOGGER = Logger.getLogger(PatientPresenter.class);
    private PatientContract.View mView;

    public PatientPresenter(PatientContract.View view) {
        mView = view;
    }

    @Override
    public void logout() {
        State.getINSTANCE().logout();
    }
}
