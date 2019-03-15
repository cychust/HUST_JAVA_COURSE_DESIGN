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
    private static HashMap<String, String> repository;

    LoginClient() {
        repository = new HashMap<String, String>();
    }

    public Observable create(final String userId, final String password) {
        //
        Observable<Boolean> observable = Observable.create(new ObservableOnSubscribe<Boolean>() {
            public void subscribe(ObservableEmitter<Boolean> observableEmitter) throws Exception {
                if (repository.containsKey(userId)) {
                    observableEmitter.onNext(false);
                } else {
                    repository.put(userId, password);
                    observableEmitter.onNext(true);
                }
                observableEmitter.onComplete();
            }
        });
        return observable;
    }

    public Observable login(final String userId, final String password) {
        //
        Observable<Boolean> observable = Observable.create(new ObservableOnSubscribe<Boolean>() {
            public void subscribe(ObservableEmitter<Boolean> observableEmitter) throws Exception {
                if (repository.isEmpty()) observableEmitter.onNext(false);
                if (repository.containsKey(userId) && repository.get(userId).equals(password))
                    observableEmitter.onNext(true);
                else observableEmitter.onNext(false);
                observableEmitter.onComplete();
            }
        });
        return observable;
    }
}
