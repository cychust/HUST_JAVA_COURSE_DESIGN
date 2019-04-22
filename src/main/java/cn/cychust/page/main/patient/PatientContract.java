package cn.cychust.page.main.patient;

import cn.cychust.base.BasePresenter;
import cn.cychust.base.BaseView;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 15:36
 **/
public class PatientContract {
    public interface View extends BaseView<Presenter> {

    }

    public interface Presenter extends BasePresenter {
        void logout();
    }
}
