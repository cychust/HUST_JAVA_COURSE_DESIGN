package cn.cychust.data.tsrxx_item;

import cn.cychust.comm.Executor;
import cn.cychust.data.tghxx_item.Dao;
import cn.cychust.data.tghxx_item.Database;
import cn.cychust.data.tghxx_item.GHXX_Item;
import cn.cychust.data.tghxx_item.Repository;

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
    public void getAllByYS(String id, LoadTsrxxItemCallback callback) {
        Runnable runnable = () -> {

        };
        mExecutorService.execute(runnable);
    }
}
