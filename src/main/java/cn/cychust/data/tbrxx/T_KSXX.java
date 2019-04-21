package cn.cychust.data.tbrxx;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-21 23:10
 **/
public class T_KSXX {
    private String KSBH;
    private String KSMC;
    private String PYZC;

    public T_KSXX(String KSBH, String KSMC, String PYZC) {
        this.KSBH = KSBH;
        this.KSMC = KSMC;
        this.PYZC = PYZC;
    }

    public T_KSXX() {
    }

    public String getKSBH() {
        return KSBH;
    }

    public void setKSBH(String KSBH) {
        this.KSBH = KSBH;
    }

    public String getKSMC() {
        return KSMC;
    }

    public void setKSMC(String KSMC) {
        this.KSMC = KSMC;
    }

    public String getPYZC() {
        return PYZC;
    }

    public void setPYZC(String PYZC) {
        this.PYZC = PYZC;
    }
}
