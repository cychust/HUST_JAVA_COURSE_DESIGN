package cn.cychust.data.tbrxx;

import java.sql.Date;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-22 16:33
 **/
public class T_BRXX {

    private String BRBH;    //病人编号
    private String BRMC;    //病人名称
    private String DLKL;    //登录口令
    private float YCJE;    //病人预存金额
    private Date DLRQ;      //最后一次登录信息

    public T_BRXX(String BRBH, String BRMC, String DLKL, float YCJE, Date DLRQ) {
        this.BRBH = BRBH;
        this.BRMC = BRMC;
        this.DLKL = DLKL;
        this.YCJE = YCJE;
        this.DLRQ = DLRQ;
    }

    public T_BRXX() {
    }

    public String getBRBH() {
        return BRBH;
    }

    public void setBRBH(String BRBH) {
        this.BRBH = BRBH;
    }

    public String getBRMC() {
        return BRMC;
    }

    public void setBRMC(String BRMC) {
        this.BRMC = BRMC;
    }

    public String getDLKL() {
        return DLKL;
    }

    public void setDLKL(String DLKL) {
        this.DLKL = DLKL;
    }


    public float getYCJE() {
        return YCJE;
    }

    public void setYCJE(float YCJE) {
        this.YCJE = YCJE;
    }

    public Date getDLRQ() {
        return DLRQ;
    }

    public void setDLRQ(Date DLRQ) {
        this.DLRQ = DLRQ;
    }

    @Override
    public String toString() {
        return "[BRBH: " + BRBH + " ]" + "[BRMC: " + BRMC + " ]" + "[DLKL: " + DLKL + " ]";
    }
}
