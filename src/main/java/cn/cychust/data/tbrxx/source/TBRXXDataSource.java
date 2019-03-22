package cn.cychust.data.tbrxx.source;

import cn.cychust.comm.Executor;
import cn.cychust.data.tbrxx.T_BRXX;
import cn.cychust.data.tbrxx.source.local.BrxxLocalDataSource;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-15 16:42
 **/
public class TBRXXDataSource implements TBRXXRepository {

    private static TBRXXDataSource INSTANCE = null;

    private BrxxLocalDataSource brxxLocalDataSource;

    private TBRXXDataSource() {
        this.brxxLocalDataSource = BrxxLocalDataSource.getINSTANCE(Executor.getINSTANCE().getExecutor());
    }

    public static TBRXXDataSource getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new TBRXXDataSource();
        }
        return INSTANCE;
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
