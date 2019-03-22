package cn.cychust.data;


import io.reactivex.Observable;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-15 16:42
 **/
public interface RepositorySource {

    public void create(String userId, String password, final Callback callback);

    public void login(String userId, String password, final Callback callback);

    interface Callback {
        void onSuccuss();

        void onFailed();
    }
}
