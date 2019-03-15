package cn.cychust.comm;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-15 23:10
 **/
public abstract class BaseObserver<T> implements Observer<T> {


    public BaseObserver() {

    }
    public void onSubscribe(Disposable disposable) {

    }
    public void onError(Throwable throwable) {
        onMyError(throwable);
    }

    public void onNext(T t) {

    }

    public void onComplete() {

    }
    public abstract void onMyError(Throwable throwable);
}
