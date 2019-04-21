package cn.cychust.data.tbrxx;

import java.sql.Timestamp;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description: 科室医生表
 * @author: Yichao Chen
 * @create: 2019-04-21 23:12
 **/
public class T_KSYS {
    private String YSBH;
    private String KSBH;
    private String YSMC;
    private String PYZS;
    private String DLKL;
    private boolean SFZJ;
    private Timestamp DLRQ;

    public T_KSYS(String YSBH, String KSBH, String YSMC, String PYZS, String DLKL, boolean SFZJ, Timestamp DLRQ) {
        this.YSBH = YSBH;
        this.KSBH = KSBH;
        this.YSMC = YSMC;
        this.PYZS = PYZS;
        this.DLKL = DLKL;
        this.SFZJ = SFZJ;
        this.DLRQ = DLRQ;
    }

    public String getYSBH() {
        return YSBH;
    }

    public void setYSBH(String YSBH) {
        this.YSBH = YSBH;
    }

    public String getKSBH() {
        return KSBH;
    }

    public void setKSBH(String KSBH) {
        this.KSBH = KSBH;
    }

    public String getYSMC() {
        return YSMC;
    }

    public void setYSMC(String YSMC) {
        this.YSMC = YSMC;
    }

    public String getPYZS() {
        return PYZS;
    }

    public void setPYZS(String PYZS) {
        this.PYZS = PYZS;
    }

    public String getDLKL() {
        return DLKL;
    }

    public void setDLKL(String DLKL) {
        this.DLKL = DLKL;
    }

    public boolean isSFZJ() {
        return SFZJ;
    }

    public void setSFZJ(boolean SFZJ) {
        this.SFZJ = SFZJ;
    }


    public Timestamp getDLRQ() {
        return DLRQ;
    }

    public void setDLRQ(Timestamp DLRQ) {
        this.DLRQ = DLRQ;
    }
}
