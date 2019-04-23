package cn.cychust.data.tghxx_item;

import javafx.beans.property.StringProperty;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-23 20:08
 **/
public class GHXX_Item {

    private String GHBH;
    private String BRMC;
    private String RQSJ;
    private String HZLB;

    public GHXX_Item() {
    }

    public String getGHBH() {
        return GHBH;
    }

    public void setGHBH(String GHBH) {
        this.GHBH = GHBH;
    }

    public String getBRMC() {
        return BRMC;
    }

    public void setBRMC(String GHMC) {
        this.BRMC = GHMC;
    }

    public String getRQSJ() {
        return RQSJ;
    }

    public void setRQSJ(String GHRQ) {
        this.RQSJ = GHRQ;
    }

    public String getHZLB() {
        return HZLB;
    }

    public void setHZLB(String HZLB) {
        this.HZLB = HZLB;
    }
}
