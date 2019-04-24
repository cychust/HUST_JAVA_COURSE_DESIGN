package cn.cychust.data.thzxx.source;

import cn.cychust.data.tghxx_item.Repository;
import cn.cychust.data.thzxx.T_HZXX;
import cn.cychust.data.tksxx.source.TKSXXRepository;

import java.util.List;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 21:30
 **/
public interface THZXXRepository {

    void getOne(String id, GetThzxxCallback callback);

//    void getAll(LoadThzxxsCallback callback);

    void getHzmcByKsbhAndSfzj(String ksbh, boolean sfzj, LoadThzxxsCallback callback);

//    void createTable();

    interface LoadThzxxsCallback {
        void onTasksLoaded(List<T_HZXX> list);

        void onDataNotAvailable();
    }

    interface GetThzxxCallback {
        void onTasksLoaded(T_HZXX t_ksxx);

        void onDataNotAvailable();
    }
}
