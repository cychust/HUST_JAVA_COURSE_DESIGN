package cn.cychust.data.tghxx_item;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-23 17:15
 **/
public class GHXX_TreeItem extends RecursiveTreeObject<GHXX_TreeItem> {

    public final StringProperty GHBH;
    public final StringProperty GHMC;
    public final StringProperty GHRQ;
    public final StringProperty HZLB;

    public GHXX_TreeItem(String GHBH, String GHMC, String GHRQ, String HZLB) {
        this.GHBH = new SimpleStringProperty(GHBH);
        this.GHMC = new SimpleStringProperty(GHMC);
        this.GHRQ = new SimpleStringProperty(GHRQ);
        this.HZLB = new SimpleStringProperty(HZLB);
    }
}
