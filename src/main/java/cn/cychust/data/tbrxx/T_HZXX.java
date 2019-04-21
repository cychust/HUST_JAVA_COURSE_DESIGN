package cn.cychust.data.tbrxx;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description: 号种信息表
 * @author: Yichao Chen
 * @create: 2019-04-21 23:16
 **/
public class T_HZXX {
    private String HZBH;
    private String HZMC;
    private String PYZS;
    private String KSBH;
    private boolean SFZJ;
    private int GHRS;
    private float GHFY;

    public T_HZXX(String HZBH, String HZMC, String PYZS, String KSBH, boolean SFZJ, int GHRS, float GHFY) {
        this.HZBH = HZBH;
        this.HZMC = HZMC;
        this.PYZS = PYZS;
        this.KSBH = KSBH;
        this.SFZJ = SFZJ;
        this.GHRS = GHRS;
        this.GHFY = GHFY;
    }

    public String getHZBH() {
        return HZBH;
    }

    public void setHZBH(String HZBH) {
        this.HZBH = HZBH;
    }

    public String getHZMC() {
        return HZMC;
    }

    public void setHZMC(String HZMC) {
        this.HZMC = HZMC;
    }

    public String getPYZS() {
        return PYZS;
    }

    public void setPYZS(String PYZS) {
        this.PYZS = PYZS;
    }

    public String getKSBH() {
        return KSBH;
    }

    public void setKSBH(String KSBH) {
        this.KSBH = KSBH;
    }

    public boolean isSFZJ() {
        return SFZJ;
    }

    public void setSFZJ(boolean SFZJ) {
        this.SFZJ = SFZJ;
    }

    public int getGHRS() {
        return GHRS;
    }

    public void setGHRS(int GHRS) {
        this.GHRS = GHRS;
    }

    public float getGHFY() {
        return GHFY;
    }

    public void setGHFY(float GHFY) {
        this.GHFY = GHFY;
    }
}
