package cn.cychust.data.tbrxx.source;


import cn.cychust.data.tbrxx.T_BRXX;
import io.reactivex.annotations.NonNull;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-15 16:42
 **/
public interface TBRXXRepository {

    void getOne(String userId, String password, GetTbrxxCallback callback);

    void getAll(LoadTbrxxsCallback callback);

    void saveOne(T_BRXX newOne);

    void updateOne(T_BRXX newOne);

    void saveAll(List<T_BRXX> brxxList);

    void deleteOne(String id);

    void getOne(String brbh,GetTbrxxCallback callback);

    void createTable();

    interface LoadTbrxxsCallback {
        void onTasksLoaded();

        void onDataNotAvailable();
    }

    interface GetTbrxxCallback {
        void onTasksLoaded(T_BRXX t_brxx);

        void onDataNotAvailable();
    }


}
