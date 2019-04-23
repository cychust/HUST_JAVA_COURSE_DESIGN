package cn.cychust.data.tghxx;

import java.sql.Timestamp;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description: 挂号信息表
 * @author: Yichao Chen
 * @create: 2019-04-21 23:19
 **/
public class T_GHXX {
    private String GHBH;
    private String HZBH;
    private String YSBH;
    private String BRBH;
    private int GHRC;
    private boolean THBZ;
    private float GHFY;
    private Timestamp RQSJ;

    public T_GHXX() {

    }

    public T_GHXX(String GHBH, String HZBH, String YSBH, String BRBH, int GHRC, boolean THBZ, float GHFY, Timestamp RQSJ) {
        this.GHBH = GHBH;
        this.HZBH = HZBH;
        this.YSBH = YSBH;
        this.BRBH = BRBH;
        this.GHRC = GHRC;
        this.THBZ = THBZ;
        this.GHFY = GHFY;
        this.RQSJ = RQSJ;
    }

    public String getGHBH() {
        return GHBH;
    }

    public void setGHBH(String GHBH) {
        this.GHBH = GHBH;
    }

    public String getHZBH() {
        return HZBH;
    }

    public void setHZBH(String HZBH) {
        this.HZBH = HZBH;
    }

    public String getYSBH() {
        return YSBH;
    }

    public void setYSBH(String YSBH) {
        this.YSBH = YSBH;
    }

    public String getBRBH() {
        return BRBH;
    }

    public void setBRBH(String BRBH) {
        this.BRBH = BRBH;
    }

    public int getGHRC() {
        return GHRC;
    }

    public void setGHRC(int GHRC) {
        this.GHRC = GHRC;
    }

    public boolean isTHBZ() {
        return THBZ;
    }

    public void setTHBZ(boolean THBZ) {
        this.THBZ = THBZ;
    }

    public float getGHFY() {
        return GHFY;
    }

    public void setGHFY(float GHFY) {
        this.GHFY = GHFY;
    }

    public Timestamp getRQSJ() {
        return RQSJ;
    }

    public void setRQSJ(Timestamp RQSJ) {
        this.RQSJ = RQSJ;
    }
}
