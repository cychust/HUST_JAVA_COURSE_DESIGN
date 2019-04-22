package cn.cychust.page.main.doctor;

import cn.cychust.base.BasePresenter;
import cn.cychust.base.BaseView;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 15:04
 **/
public class DoctorContract {
    public interface View extends BaseView<Presenter> {

    }

    public interface Presenter extends BasePresenter {
        void logout();
    }
}
