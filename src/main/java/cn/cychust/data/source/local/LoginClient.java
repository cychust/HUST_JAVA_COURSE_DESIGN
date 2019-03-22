package cn.cychust.data.source.local;

import cn.cychust.data.RepositorySource;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import java.util.HashMap;


/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-15 16:52
 **/
public class LoginClient implements RepositorySource {
    //
    //database repository; single instance
    LoginClient() {

    }

    public void create(final String userId, final String password, final Callback callback) {
        //

    }

    public void login(final String userId, final String password, final Callback callback) {
        //
//        Observable<Boolean> observable = Observable.create(new ObservableOnSubscribe<Boolean>() {
//            public void subscribe(ObservableEmitter<Boolean> observableEmitter) throws Exception {
//                if (repository.isEmpty()) observableEmitter.onNext(false);
//                if (repository.containsKey(userId) && repository.get(userId).equals(password))
//                    observableEmitter.onNext(true);
//                else observableEmitter.onNext(false);
//                observableEmitter.onComplete();
//            }
//        });
        if (LoginDao.login(userId, password)) {
            callback.onSuccuss();
        } else {
            callback.onFailed();
        }
    }
}
