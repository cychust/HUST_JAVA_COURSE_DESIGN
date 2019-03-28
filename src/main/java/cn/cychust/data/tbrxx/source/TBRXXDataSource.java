package cn.cychust.data.tbrxx.source;

import cn.cychust.comm.Executor;
import cn.cychust.data.tbrxx.T_BRXX;
import cn.cychust.data.tbrxx.source.local.BrxxLocalDataSource;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-15 16:42
 **/
public class TBRXXDataSource implements TBRXXRepository {


    private BrxxLocalDataSource brxxLocalDataSource;

    public TBRXXDataSource(ExecutorService executorService) {
        this.brxxLocalDataSource = BrxxLocalDataSource.getINSTANCE(executorService);
    }


    @Override
    public void getOne(String userId, String password, GetTbrxxCallback callback) {
        brxxLocalDataSource.getOne(userId, password, new GetTbrxxCallback() {
            @Override
            public void onTasksLoaded(T_BRXX t_brxx) {
                callback.onTasksLoaded(t_brxx);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getAll(LoadTbrxxsCallback callback) {

    }

    @Override
    public void saveOne(T_BRXX newOne) {
        brxxLocalDataSource.saveOne(newOne);
    }

    @Override
    public void updateOne(T_BRXX newOne) {

    }

    @Override
    public void saveAll(List<T_BRXX> brxxList) {

    }

    @Override
    public void deleteOne(String id) {

    }

    @Override
    public void createTable() {
        brxxLocalDataSource.createTable();
    }

}
