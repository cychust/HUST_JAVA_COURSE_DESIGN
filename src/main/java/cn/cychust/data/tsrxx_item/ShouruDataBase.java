package cn.cychust.data.tsrxx_item;

import cn.cychust.comm.Executor;
import javafx.application.Platform;
import jdk.internal.dynalink.linker.LinkerServices;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-23 21:13
 **/
public class ShouruDataBase implements ShouruRepository {
    private static ShouruDataBase INSTANCE;
    private ExecutorService mExecutorService;

    private ShouruDataBase() {
        mExecutorService = Executor.getINSTANCE().getExecutor();
    }

    public static ShouruDataBase getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new ShouruDataBase();
        return INSTANCE;
    }

    @Override
    public void getAllBetween(Timestamp start, Timestamp end, LoadTsrxxItemCallback callback) {
        Runnable runnable = () -> {
            Optional optional = Dao.findAllBetween(start, end);
            if (optional.isPresent()) {
                Platform.runLater(() -> {
                    callback.onTasksLoaded((List) optional.get());
                });
            } else
                Platform.runLater(() -> {
                    callback.onDataNotAvailable();
                });

        };
        mExecutorService.execute(runnable);
    }
}
