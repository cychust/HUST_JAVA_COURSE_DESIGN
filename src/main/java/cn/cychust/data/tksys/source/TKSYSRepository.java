package cn.cychust.data.tksys.source;

import cn.cychust.data.tksys.T_KSYS;

import java.util.List;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 11:28
 **/
public interface TKSYSRepository {
    void getOne(String userId, String password, GetTksysCallback callback);

    void getOneByYSBH(String userId, GetTksysCallback callback);

    void getAll(LoadTksysesCallback callback);

    void saveOne(T_KSYS newOne);

    void updateOne(T_KSYS newOne);

    void saveAll(List<T_KSYS> brxxList);

    void deleteOne(String id);

    void createTable();

    void getKsysesByKSBH(String id, LoadTksysesCallback callback);

    interface LoadTksysesCallback {
        void onTasksLoaded(List<T_KSYS> list);

        void onDataNotAvailable();
    }

    interface GetTksysCallback {
        void onTasksLoaded(T_KSYS t_brxx);

        void onDataNotAvailable();
    }

}
