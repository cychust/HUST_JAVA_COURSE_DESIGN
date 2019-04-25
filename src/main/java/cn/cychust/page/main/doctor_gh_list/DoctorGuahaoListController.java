package cn.cychust.page.main.doctor_gh_list;

import cn.cychust.State;
import cn.cychust.data.tghxx_item.Database;
import cn.cychust.data.tghxx_item.GHXX_Item;
import cn.cychust.data.tghxx_item.GHXX_TreeItem;
import cn.cychust.data.tbrxx.source.TBRXXDataSource;
import cn.cychust.data.tghxx_item.Repository;
import cn.cychust.page.login.LoginController;
import cn.cychust.page.main.doctor.DoctorController;
import cn.cychust.page.main.doctor_shouru.DoctorShouRuController;
import cn.cychust.page.main.patient.PatientController;
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
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-23 14:11
 **/
@ViewController(value = "/fxml/main_doctor_gh_list.fxml")
public class DoctorGuahaoListController {

    private static final Logger LOGGER = Logger.getLogger(DoctorGuahaoListController.class);
    @FXML
    private JFXTreeTableView tv_list;

    @FXML
    private JFXButton btn_logout;

    @FXML
    private JFXButton btn_back;
    @FXML
    private Label lb_title;


    @ActionHandler
    private FlowActionHandler actionHandler;

    ObservableList<GHXX_TreeItem> ghxxes_ti = FXCollections.observableArrayList();

    TBRXXDataSource mTBRXXDataSource = new TBRXXDataSource();

    @PostConstruct
    public void init() throws Exception {
        lb_title.setText("全部医生的收入列表");

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
        btn_back.setGraphic(new ImageView(new Image("/images/back2.png")));
        btn_back.setPrefHeight(20);
        btn_back.setPrefWidth(20);
        btn_back.setOnMouseClicked(event -> {
            try {
                navigateToBack();
            } catch (VetoException | FlowException exception) {
                exception.printStackTrace();
            }
        });
        ghxxes_ti.remove(0, ghxxes_ti.size());
        Database.getINSTANCE().getAllByYS(State.getT_ksys().getYSBH(), new Repository.LoadTghxxItemCallback() {
            @Override
            public void onTasksLoaded(List<GHXX_Item> list) {
                for (GHXX_Item ghxx : list
                        ) {
                    ghxxes_ti.add(new GHXX_TreeItem(ghxx.getGHBH(), ghxx.getBRMC(), ghxx.getRQSJ().toString(), ghxx.getHZLB()));
                    LOGGER.info(ghxx.getGHBH());
                }

            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        JFXTreeTableColumn<GHXX_TreeItem, String> ghColumn = new JFXTreeTableColumn<>("挂号编号");
        JFXTreeTableColumn<GHXX_TreeItem, String> brmcColumn = new JFXTreeTableColumn<>("病人名称");
        JFXTreeTableColumn<GHXX_TreeItem, String> ghrqColumn = new JFXTreeTableColumn<>("挂号日期时间");
        JFXTreeTableColumn<GHXX_TreeItem, String> hzlbColumn = new JFXTreeTableColumn<>("号种类别");

        ghColumn.setPrefWidth(150);
        brmcColumn.setPrefWidth(150);
        ghrqColumn.setPrefWidth(200);
        hzlbColumn.setPrefWidth(150);


        ghColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<GHXX_TreeItem, String> param) -> {
            if (ghColumn.validateValue(param)) {
                return param.getValue().getValue().GHBH;
            } else {
                return ghColumn.getComputedValue(param);
            }
        });

        brmcColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<GHXX_TreeItem, String> param) -> {
            if (ghColumn.validateValue(param)) {
                return param.getValue().getValue().GHMC;
            } else {
                return ghColumn.getComputedValue(param);
            }
        });
        ghrqColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<GHXX_TreeItem, String> param) -> {
            if (ghColumn.validateValue(param)) {
                return param.getValue().getValue().GHRQ;
            } else {
                return ghColumn.getComputedValue(param);
            }
        });
        hzlbColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<GHXX_TreeItem, String> param) -> {
            if (ghColumn.validateValue(param)) {
                return param.getValue().getValue().HZLB;
            } else {
                return ghColumn.getComputedValue(param);
            }
        });


        ghColumn.setCellFactory((TreeTableColumn<GHXX_TreeItem, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));
        ghColumn.setEditable(false);

        brmcColumn.setCellFactory((TreeTableColumn<GHXX_TreeItem, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));
        brmcColumn.setEditable(false);
        ghrqColumn.setCellFactory((TreeTableColumn<GHXX_TreeItem, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));

        ghrqColumn.setEditable(false);
        hzlbColumn.setCellFactory((TreeTableColumn<GHXX_TreeItem, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));
        hzlbColumn.setEditable(false);

        final TreeItem<GHXX_TreeItem> root = new RecursiveTreeItem<>(ghxxes_ti, RecursiveTreeObject::getChildren);


        tv_list.setRoot(root);
        tv_list.getColumns().addAll(ghColumn, brmcColumn, ghrqColumn, hzlbColumn);


        tv_list.setShowRoot(false);
        tv_list.setEditable(true);

    }

    private void navigateToLogin() throws VetoException, FlowException {
        actionHandler.navigate(LoginController.class);
    }

    private void navigateToBack() throws VetoException, FlowException {
        actionHandler.navigate(DoctorController.class);
    }

}
