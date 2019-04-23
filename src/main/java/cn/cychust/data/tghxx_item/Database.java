package cn.cychust.data.tghxx_item;

import cn.cychust.comm.Executor;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-23 20:09
 **/
public class Database implements Repository {

    private static Database INSTANCE;
    private ExecutorService mExecutorService;

    private Database() {
        mExecutorService = Executor.getINSTANCE().getExecutor();
    }

    public static Database getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new Database();
        return INSTANCE;
    }

    @Override
    public void getAllByYS(String id, LoadTghxxItemCallback callback) {
        Runnable runnable = () -> {
            Optional optional = Dao.findAllByYSBH(id);
            if (optional.isPresent())
                callback.onTasksLoaded((List<GHXX_Item>) optional.get());
            else
                callback.onDataNotAvailable();
        };
        mExecutorService.execute(runnable);
    }
}
