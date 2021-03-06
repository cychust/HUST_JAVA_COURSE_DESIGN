package cn.cychust.data.tbrxx.source.local;

import cn.cychust.comm.Executor;
import cn.cychust.data.tbrxx.T_BRXX;
import cn.cychust.data.tbrxx.source.TBRXXRepository;
import cn.cychust.data.tbrxx.source.local.dao.UserDao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;


/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-15 16:52
 **/
public class BrxxLocalDataSource implements TBRXXRepository {
    private static BrxxLocalDataSource INSTANCE;

    private ExecutorService executor;

    private BrxxLocalDataSource() {
        this.executor = Executor.getINSTANCE().getExecutor();
    }

    public static BrxxLocalDataSource getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (BrxxLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BrxxLocalDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getOne(String brbh, GetTbrxxCallback callback) {
        Runnable runnable = () -> {
            final Optional user = UserDao.findOne(brbh, "*");
            if (user.isPresent())
                callback.onTasksLoaded(((T_BRXX) user.get()));
            else
                callback.onDataNotAvailable();
        };
        executor.execute(runnable);
    }

    @Override
    public void getOne(String userId, String password, GetTbrxxCallback callback) {
        Runnable runnable = () -> {
            final Optional user = UserDao.findOne(userId, password);
            if (user.isPresent())
                callback.onTasksLoaded(((T_BRXX) user.get()));
            else
                callback.onDataNotAvailable();
        };
        executor.execute(runnable);
    }

    @Override
    public void getAll(LoadTbrxxsCallback callback) {

    }

    @Override
    public void saveOne(T_BRXX newOne) {
        Runnable runnable = () -> {
            UserDao.addOne(newOne);
        };
        executor.execute(runnable);
    }

    @Override
    public void updateOne(T_BRXX newOne) {
        Runnable runnable = () -> {
            UserDao.updateById(newOne);
        };
        executor.execute(runnable);
    }

    @Override
    public void saveAll(List<T_BRXX> brxxList) {

    }

    @Override
    public void deleteOne(String id) {

    }

    @Override
    public void createTable() {
        Runnable runnable = () -> {
            UserDao.createTable();
        };
        executor.execute(runnable);
    }

    //    @Override
//    public void login(final String userId, final String password, Callback callback) {
//        //
////        Observable<Boolean> observable = Observable.create(new ObservableOnSubscribe<Boolean>() {
////            public void subscribe(ObservableEmitter<Boolean> observableEmitter) throws Exception {
////                if (Repository.isEmpty()) observableEmitter.onNext(false);
////                if (Repository.containsKey(userId) && Repository.get(userId).equals(password))
////                    observableEmitter.onNext(true);
////                else observableEmitter.onNext(false);
////                observableEmitter.onComplete();
////            }
////        });
//        UserDao.findOne(userId, password);
//
//    }
}
