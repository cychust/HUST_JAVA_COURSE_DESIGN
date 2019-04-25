package cn.cychust.data.tksxx.source;

import cn.cychust.comm.Executor;
import cn.cychust.data.tksxx.T_KSXX;
import cn.cychust.data.tksxx.source.local.KsxxDao;
import javafx.application.Platform;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 11:10
 **/
public class TKSXXDataSource implements TKSXXRepository {

    private static TKSXXDataSource INSTANCE;

    private ExecutorService executor;

    private TKSXXDataSource() {
        executor = Executor.getINSTANCE().getExecutor();

    }

    public static TKSXXDataSource getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (TKSXXDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TKSXXDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void findAllByPYZS(String pyzs, LoadTksxxsCallback callback) {
        Runnable runnable = () -> {
            Optional optional = KsxxDao.findAllByPYZS(pyzs);
            if (optional.isPresent())
                Platform.runLater(() -> {
                    callback.onTasksLoaded(((List<T_KSXX>) optional.get()));
                });
            else Platform.runLater(() -> {
                callback.onDataNotAvailable();
            });
        };
        executor.execute(runnable);
    }

    @Override
    public void getOne(String id, GetTksxxCallback callback) {

    }

    @Override
    public void getAll(LoadTksxxsCallback callback) {
        Runnable runnable = () -> {
            Optional list = KsxxDao.findAll();
            if (list.isPresent()) {
                Platform.runLater(() -> {
                    callback.onTasksLoaded((List<T_KSXX>) list.get());
                });
            } else
                Platform.runLater(() -> {
                    callback.onDataNotAvailable();
                });
        };
        executor.execute(runnable);
    }

    @Override
    public void createTable() {

    }
}
