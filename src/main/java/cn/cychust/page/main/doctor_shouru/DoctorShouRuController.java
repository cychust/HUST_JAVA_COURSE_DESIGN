package cn.cychust.page.main.doctor_shouru;

import cn.cychust.State;
import cn.cychust.data.tbrxx.source.TBRXXDataSource;
import cn.cychust.data.tghxx_item.Database;
import cn.cychust.data.tghxx_item.GHXX_Item;
import cn.cychust.data.tghxx_item.GHXX_TreeItem;
import cn.cychust.data.tghxx_item.Repository;
import cn.cychust.data.tsrxx_item.SRXX_Item;
import cn.cychust.data.tsrxx_item.SRXX_TreeItem;
import cn.cychust.data.tsrxx_item.ShouruDataBase;
import cn.cychust.data.tsrxx_item.ShouruRepository;
import cn.cychust.page.login.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-23 20:40
 **/
@ViewController(value = "/fxml/main_doctor_shouru.fxml")
public class DoctorShouRuController {
    @FXML
    private JFXTreeTableView tv_list;

    @FXML
    private JFXButton btn_logout;
    @FXML
    private Label lb_title;

    @ActionHandler
    private FlowActionHandler actionHandler;

    ObservableList<SRXX_TreeItem> ghxxes_ti = FXCollections.observableArrayList();

    TBRXXDataSource mTBRXXDataSource = new TBRXXDataSource();


    @PostConstruct
    public void init() throws Exception {
        lb_title.setText(State.getT_ksys().getYSMC() + "的收入列表");

        btn_logout.setGraphic(new ImageView(new Image("/images/logout.png")));
        btn_logout.setMaxHeight(40);
        btn_logout.setMaxWidth(40);
        btn_logout.setOnMouseClicked(e -> {
            try {
                State.getINSTANCE().logout();
                navigateToLogin();
            } catch (VetoException | FlowException exception) {
                exception.printStackTrace();
            }
        });

        ShouruDataBase.getINSTANCE().getAllByYS(State.getT_ksys().getYSBH(), new ShouruRepository.LoadTsrxxItemCallback() {
            @Override
            public void onTasksLoaded(List<SRXX_Item> list) {
                for (SRXX_Item item : list) {
                    ghxxes_ti.add(new SRXX_TreeItem(item.getKSMC(), item.getYSBH(), item.getYSMC(),
                            item.getHZLB(), item.getGHRC(), item.getSRHJ()));
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });


        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", "111111", 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", "111111", 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", "111111", 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", "111111", 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", "111111", 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", "111111", 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", "111111", 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", "111111", 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", "111111", 1, 20f));


        JFXTreeTableColumn<SRXX_TreeItem, String> ksmcColumn = new JFXTreeTableColumn<>("科室名称");
        JFXTreeTableColumn<SRXX_TreeItem, String> ysbhColumn = new JFXTreeTableColumn<>("医生编号");
        JFXTreeTableColumn<SRXX_TreeItem, String> ysmcColumn = new JFXTreeTableColumn<>("医生名称");
        JFXTreeTableColumn<SRXX_TreeItem, String> hzlbColumn = new JFXTreeTableColumn<>("号种类别");
        JFXTreeTableColumn<SRXX_TreeItem, String> ghrcColumn = new JFXTreeTableColumn<>("挂号人次");
        JFXTreeTableColumn<SRXX_TreeItem, String> srzjColumn = new JFXTreeTableColumn<>("收入总计");

        ksmcColumn.setPrefWidth(100);
        ysbhColumn.setPrefWidth(100);
        ysmcColumn.setPrefWidth(100);
        hzlbColumn.setPrefWidth(100);
        ghrcColumn.setPrefWidth(100);
        srzjColumn.setPrefWidth(100);

        ksmcColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<SRXX_TreeItem, String> param) -> {
            if (ksmcColumn.validateValue(param)) {
                return param.getValue().getValue().KSMC;
            } else {
                return ksmcColumn.getComputedValue(param);
            }
        });

        ysbhColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<SRXX_TreeItem, String> param) -> {
            if (ysbhColumn.validateValue(param)) {
                return param.getValue().getValue().YSBH;
            } else {
                return ysbhColumn.getComputedValue(param);
            }
        });

        ysmcColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<SRXX_TreeItem, String> param) -> {
            if (ysmcColumn.validateValue(param)) {
                return param.getValue().getValue().YSMC;
            } else {
                return ysmcColumn.getComputedValue(param);
            }
        });

        hzlbColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<SRXX_TreeItem, String> param) -> {
            if (hzlbColumn.validateValue(param)) {
                return param.getValue().getValue().HZLB;
            } else {
                return hzlbColumn.getComputedValue(param);
            }
        });

        ghrcColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<SRXX_TreeItem, String> param) -> {
            if (ghrcColumn.validateValue(param)) {
                return param.getValue().getValue().GHRC;
            } else {
                return ghrcColumn.getComputedValue(param);
            }
        });
        srzjColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<SRXX_TreeItem, String> param) -> {
            if (srzjColumn.validateValue(param)) {
                return param.getValue().getValue().SRHJ;
            } else {
                return srzjColumn.getComputedValue(param);
            }
        });


//        ghColumn.setCellFactory((TreeTableColumn<GHXX_TreeItem, String> param) -> new GenericEditableTreeTableCell<>(
//                new TextFieldEditorBuilder()));
//        ghColumn.setEditable(false);
//
//        brmcColumn.setCellFactory((TreeTableColumn<GHXX_TreeItem, String> param) -> new GenericEditableTreeTableCell<>(
//                new TextFieldEditorBuilder()));
//        brmcColumn.setEditable(false);
//        ghrqColumn.setCellFactory((TreeTableColumn<GHXX_TreeItem, String> param) -> new GenericEditableTreeTableCell<>(
//                new TextFieldEditorBuilder()));
//
//        ghrqColumn.setEditable(false);
//        hzlbColumn.setCellFactory((TreeTableColumn<GHXX_TreeItem, String> param) -> new GenericEditableTreeTableCell<>(
//                new TextFieldEditorBuilder()));
//        hzlbColumn.setEditable(false);

        final TreeItem<SRXX_TreeItem> root = new RecursiveTreeItem<>(ghxxes_ti, RecursiveTreeObject::getChildren);


        tv_list.setRoot(root);
        tv_list.getColumns().addAll(ksmcColumn, ysbhColumn, ysmcColumn, hzlbColumn, ghrcColumn, srzjColumn);


        tv_list.setShowRoot(false);
        tv_list.setEditable(true);

    }

    private void navigateToLogin() throws VetoException, FlowException {
        actionHandler.navigate(LoginController.class);
    }

}
