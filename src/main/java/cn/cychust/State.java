package cn.cychust;

import cn.cychust.data.tbrxx.T_BRXX;
import cn.cychust.data.tksys.T_KSYS;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 14:45
 **/
public class State {
    private boolean isLogin;
    private Identity identity;

    private static T_KSYS sT_ksys = null;
    private static T_BRXX sT_brxx = null;

    private static State INSTANCE;

    private State() {
        isLogin = false;
        identity = null;
    }

    public static State getINSTANCE() {
        INSTANCE = new State();
        return INSTANCE;
    }

    public void login(Identity identity, T_KSYS t_ksys, T_BRXX t_brxx) {
        this.identity = identity;
        isLogin = true;
        switch (identity) {
            case DOCTOR:
                sT_ksys = new T_KSYS();
                sT_ksys.setDLRQ(t_ksys.getDLRQ());
                sT_ksys.setSFZJ(t_ksys.isSFZJ());
                sT_ksys.setDLKL(t_ksys.getDLKL());
                sT_ksys.setPYZS(t_ksys.getPYZS());
                sT_ksys.setYSMC(t_ksys.getYSMC());
                sT_ksys.setKSBH(t_ksys.getKSBH());
                sT_ksys.setYSBH(t_ksys.getYSBH());
                break;
            case PATIENT:
                sT_brxx = new T_BRXX();
                sT_brxx.setBRBH(t_brxx.getBRBH());
                sT_brxx.setBRMC(t_brxx.getBRMC());
                sT_brxx.setDLKL(t_brxx.getDLKL());
                sT_brxx.setDLRQ(t_brxx.getDLRQ());
                sT_brxx.setYCJE(t_brxx.getYCJE());
                break;
            default:
                break;
        }
    }

    public void logout() {
        isLogin = false;
        identity = null;
        sT_brxx = null;
        sT_ksys = null;
    }

    public static T_KSYS getT_ksys() {
        return sT_ksys;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public static T_BRXX getT_brxx() {
        return sT_brxx;
    }
}
