package cn.cychust.comm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-22 22:59
 **/
public class Executor {
    private static Executor INSTANCE = null;

    private ExecutorService executor;

    private Executor() {
        executor = Executors.newSingleThreadExecutor();
    }

    public static Executor getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (Executor.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Executor();
                }
            }
        }
        return INSTANCE;
    }

    public ExecutorService getExecutor() {
        return executor;
    }
}
