package cn.cychust.data.tghxx.source;

import cn.cychust.comm.Executor;
import cn.cychust.data.tghxx.T_GHXX;
import cn.cychust.data.tghxx.source.dao.GhxxDao;
import cn.cychust.data.tksys.source.TKSYSDataSource;
import javafx.application.Platform;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 18:24
 **/
public class TGHXXDataSource implements TGHXXRepository {

    private ExecutorService executor;

    private static TGHXXDataSource INSTANCE;

    private TGHXXDataSource() {
        executor = Executor.getINSTANCE().getExecutor();
    }

    public static TGHXXDataSource getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new TGHXXDataSource();
        }
        return INSTANCE;
    }



    @Override
    public void findAllByYSBH(String ysbh, LoadTghxxsCallback callback) {
        Runnable runnable = () -> {
            Optional optional = GhxxDao.findAllByYSBH(ysbh);
            if (optional.isPresent())
                Platform.runLater(() -> {
                    callback.onTasksLoaded((List<T_GHXX>) optional.get());
                });
            else
                Platform.runLater(() -> {
                    callback.onDataNotAvailable();
                });
        };
        executor.execute(runnable);
    }

    @Override
    public void getOne(String userId, String password, GetTghxxCallback callback) {

    }

    @Override
    public void getAll(LoadTghxxsCallback callback) {

    }

    @Override
    public void saveOne(T_GHXX newOne) {
        Runnable runnable = () -> {
            GhxxDao.saveOne(newOne);
        };
        executor.execute(runnable);
    }

    @Override
    public void updateOne(T_GHXX newOne) {

    }

    @Override
    public void saveAll(List<T_GHXX> ghxxList) {

    }

    @Override
    public void deleteOne(String id) {

    }

    @Override
    public void createTable() {
        Runnable runnable = () -> {
            GhxxDao.createTable();
        };
        executor.execute(runnable);
    }
}
