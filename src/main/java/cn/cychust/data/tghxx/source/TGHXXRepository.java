package cn.cychust.data.tghxx.source;

import cn.cychust.data.tghxx.T_GHXX;

import java.sql.Timestamp;
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

    void saveOne(T_GHXX newOne, Timestamp start, float ycje,GetTghxxCallback callback);

    void updateOne(T_GHXX newOne);

    void saveAll(List<T_GHXX> ghxxList);

    void deleteOne(String id);

    void createTable();

    void findAllByYSBH(String ysbh, LoadTghxxsCallback callback);

    interface LoadTghxxsCallback {
        void onTasksLoaded(List<T_GHXX> ghxxes);

        void onDataNotAvailable();
    }

    interface GetTghxxCallback {
        void onTasksLoaded(T_GHXX t_ghxx);

        void onDataNotAvailable();
    }
}
