package cn.cychust.data.tksxx.source;

import cn.cychust.data.tksxx.T_KSXX;

import java.util.List;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 11:11
 **/
public interface TKSXXRepository {
    void getOne(String id, GetTksxxCallback callback);

    void getAll(LoadTksxxsCallback callback);

    void createTable();

    interface LoadTksxxsCallback {
        void onTasksLoaded(List<T_KSXX> list);

        void onDataNotAvailable();
    }

    interface GetTksxxCallback {
        void onTasksLoaded(T_KSXX t_ksxx);

        void onDataNotAvailable();
    }

}
