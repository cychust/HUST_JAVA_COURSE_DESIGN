package cn.cychust.data.tksxx.source;

import cn.cychust.comm.Executor;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 11:10
 **/
public class TKSXXDataSource implements TKSXXRepository {

    private static TKSXXDataSource INSTANCE;

    private Executor executor;

    private TKSXXDataSource() {
        executor = Executor.getINSTANCE();

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
    public void getOne(String id, GetTksxxCallback callback) {

    }

    @Override
    public void getAll(LoadTksxxsCallback callback) {

    }

    @Override
    public void createTable() {

    }
}
