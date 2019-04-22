package cn.cychust.data.tghxx.source;

import cn.cychust.data.tghxx.T_GHXX;
import cn.cychust.data.tksys.T_KSYS;
import cn.cychust.data.tksys.source.TKSYSRepository;

import java.util.List;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 18:25
 **/
public interface TGHXXRepository {
    void getOne(String userId, String password, GetTghxxCallback callback);

    void getAll(LoadTghxxsCallback callback);

    void saveOne(T_GHXX newOne);

    void updateOne(T_GHXX newOne);

    void saveAll(List<T_GHXX> ghxxList);

    void deleteOne(String id);

    void createTable();

    interface LoadTghxxsCallback {
        void onTasksLoaded();

        void onDataNotAvailable();
    }

    interface GetTghxxCallback {
        void onTasksLoaded(T_GHXX t_ghxx);

        void onDataNotAvailable();
    }
}
