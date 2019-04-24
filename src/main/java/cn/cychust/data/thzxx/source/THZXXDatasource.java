package cn.cychust.data.thzxx.source;

import cn.cychust.comm.Executor;
import cn.cychust.data.thzxx.T_HZXX;
import cn.cychust.data.thzxx.source.dao.HzxxDao;
import cn.cychust.data.tksxx.source.TKSXXDataSource;
import javafx.application.Platform;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 21:30
 **/
public class THZXXDatasource implements THZXXRepository {
    private static THZXXDatasource INSTANCE;

    private ExecutorService executor;

    private THZXXDatasource() {
        executor = Executor.getINSTANCE().getExecutor();
    }

    public static THZXXDatasource getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (THZXXDatasource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new THZXXDatasource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getHzmcByKsbhAndSfzj(String ksbh, boolean sfzj, LoadThzxxsCallback callback) {
        Runnable runnable = () -> {
            Optional optional = HzxxDao.findAllByHZBH(ksbh, sfzj);
            if (optional.isPresent()) {
                Platform.runLater(() -> {
                    callback.onTasksLoaded((List) optional.get());
                });
            } else {
                Platform.runLater(() -> {
                    callback.onDataNotAvailable();
                });
            }
        };
        executor.execute(runnable);
    }

    @Override
    public void getOne(String id, GetThzxxCallback callback) {
        Runnable runnable = () -> {
            Optional optional = HzxxDao.findOneById(id);
            if (optional.isPresent())
                Platform.runLater(() -> {
                    callback.onTasksLoaded((T_HZXX) optional.get());
                });
            else
                Platform.runLater(() -> {
                    callback.onDataNotAvailable();
                });
        };
        executor.execute(runnable);
    }
}
