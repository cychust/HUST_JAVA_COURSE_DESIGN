package cn.cychust.data;


import io.reactivex.Observable;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-15 16:42
 **/
public interface RepositorySource {

    public Observable create(String userId, String password);
    public Observable login(String userId,String password);

    interface Callback{
        void onSuccuss();
        void onFailed();
    }
}
