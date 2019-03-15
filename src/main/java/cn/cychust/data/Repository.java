package cn.cychust.data;

import cn.cychust.data.source.local.LoginClient;
import io.reactivex.Observable;


/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-15 16:42
 **/
public class Repository implements RepositorySource {

    private static Repository INSTANCE=null;

    private LoginClient loginClient;

    private Repository(){}

    public static Repository getINSTANCE(){
        if (INSTANCE==null){
            INSTANCE=new Repository();
        }
        return INSTANCE;
    }

    public Observable create(String userId, String password) {
        return loginClient.create(userId,password);
    }

    public Observable login(String userId, String password) {
        return loginClient.login(userId,password);
    }
}
