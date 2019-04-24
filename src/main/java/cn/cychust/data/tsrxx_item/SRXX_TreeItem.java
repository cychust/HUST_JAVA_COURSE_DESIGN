package cn.cychust.data.tsrxx_item;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-23 21:14
 **/
public class SRXX_TreeItem extends RecursiveTreeObject<SRXX_TreeItem> {
    public final StringProperty KSMC;
    public final StringProperty YSBH;
    public final StringProperty YSMC;
    public final StringProperty HZLB;
    public final StringProperty GHRC;
    public final StringProperty SRHJ;

    public SRXX_TreeItem(String KSMC, String YSBH, String YSMC, Boolean SFZJ, Integer GHRC, Float SRHJ) {
        this.KSMC = new SimpleStringProperty(KSMC);
        this.YSBH = new SimpleStringProperty(YSBH);
        this.YSMC = new SimpleStringProperty(YSMC);
        if (SFZJ)
            this.HZLB = new SimpleStringProperty("专家号");
        else
            this.HZLB = new SimpleStringProperty("普通号");
        this.GHRC = new SimpleStringProperty(GHRC.toString());
        this.SRHJ = new SimpleStringProperty(SRHJ.toString());
    }
}
