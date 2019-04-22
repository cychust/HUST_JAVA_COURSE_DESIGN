package cn.cychust.data.tksys.source;

import cn.cychust.comm.Executor;
import cn.cychust.data.tksxx.source.TKSXXRepository;
import cn.cychust.data.tksxx.source.local.KsxxDao;
import cn.cychust.data.tksys.T_KSYS;
import cn.cychust.data.tksys.source.local.KsysDao;
import javafx.application.Platform;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 11:27
 **/
public class TKSYSDataSource implements TKSYSRepository {

    private static TKSYSDataSource INSTANCE;

    private ExecutorService executor;

    private TKSYSDataSource() {
        executor = Executor.getINSTANCE().getExecutor();
    }

    public static TKSYSDataSource getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (TKSYSDataSource.class) {
                if (INSTANCE == null)
                    INSTANCE = new TKSYSDataSource();
            }
        }
        return INSTANCE;
    }

    @Override
    public void getOne(String userId, String password, GetTksysCallback callback) {
        Runnable runnable = () -> {
            Optional t_ksys = KsysDao.findOne(userId, password);
            if (t_ksys.isPresent()) {
                Platform.runLater(() ->
                        callback.onTasksLoaded((T_KSYS) t_ksys.get())
                );
            } else
                Platform.runLater(() ->
                        callback.onDataNotAvailable()
                );
        };
        executor.execute(runnable);
    }


    @Override
    public void getOneByYSBH(String userId, GetTksysCallback callback) {
        Runnable runnable = () -> {
            Optional t_ksys = KsysDao.findOne(userId);
            if (t_ksys.isPresent()) {
                Platform.runLater(() ->
                        callback.onTasksLoaded((T_KSYS) t_ksys.get())
                );
            } else
                Platform.runLater(() ->
                        callback.onDataNotAvailable()
                );
        };
        executor.execute(runnable);
    }

    @Override
    public void getKsysesByKSBH(String id, LoadTksysesCallback callback) {
        Runnable runnable = () -> {
            Optional optional = KsysDao.findAllByKSBH(id);
            if (optional.isPresent())
                Platform.runLater(() -> {
                    callback.onTasksLoaded((List<T_KSYS>) optional.get());
                });
            else
                Platform.runLater(() -> {
                    callback.onDataNotAvailable();
                });
        };
        executor.execute(runnable);
    }

    @Override
    public void getAll(LoadTksysesCallback callback) {

    }

    @Override
    public void saveOne(T_KSYS newOne) {
        Runnable runnable = () -> {
            KsysDao.saveOne(newOne);
        };
        executor.execute(runnable);
    }

    @Override
    public void updateOne(T_KSYS newOne) {

    }

    @Override
    public void saveAll(List<T_KSYS> brxxList) {

    }

    @Override
    public void deleteOne(String id) {

    }

    @Override
    public void createTable() {
        Runnable runnable = () -> {
            KsysDao.createTable();
        };
        executor.execute(runnable);
    }
}
