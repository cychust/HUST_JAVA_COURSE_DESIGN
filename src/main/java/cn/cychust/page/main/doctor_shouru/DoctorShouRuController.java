package cn.cychust.page.main.doctor_shouru;

import cn.cychust.State;
import cn.cychust.data.tbrxx.source.TBRXXDataSource;
import cn.cychust.data.tsrxx_item.SRXX_Item;
import cn.cychust.data.tsrxx_item.SRXX_TreeItem;
import cn.cychust.data.tsrxx_item.ShouruDataBase;
import cn.cychust.data.tsrxx_item.ShouruRepository;
import cn.cychust.page.login.LoginController;
import cn.cychust.page.main.doctor.DoctorController;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import javafx.beans.property.StringProperty;
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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-23 20:40
 **/
@ViewController(value = "/fxml/main_doctor_shouru.fxml")
public class DoctorShouRuController {


    private final static Logger LOGGER = Logger.getLogger(DoctorShouRuController.class);

    @FXML
    private JFXTreeTableView tv_list;

    @FXML
    private JFXButton btn_logout;

    @FXML
    private JFXButton btn_back;
    @FXML
    private Label lb_title;

    @FXML
    private JFXDatePicker dp_start;

    @FXML
    private JFXComboBox<Integer> cb_start_hour;
    @FXML
    private JFXComboBox<Integer> cb_start_minute;

    @FXML
    private JFXButton btn_confirm;

    @FXML
    private JFXDatePicker dp_end;

    @FXML
    private JFXComboBox<Integer> cb_end_hour;
    @FXML
    private JFXComboBox<Integer> cb_end_minute;

    @ActionHandler
    private FlowActionHandler actionHandler;

    ObservableList<SRXX_TreeItem> ghxxes_ti = FXCollections.observableArrayList();

    TBRXXDataSource mTBRXXDataSource = new TBRXXDataSource();


    ObservableList<Integer> hourInts = FXCollections.observableArrayList();

    ObservableList<Integer> minuteInts = FXCollections.observableArrayList();

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

        for (int i = 0; i < 24; i++) {
            hourInts.add(i);
        }
        for (int i = 0; i < 60; i++) {
            minuteInts.add(i);
        }
        dp_start.setValue(LocalDate.now());
        dp_end.setValue(LocalDate.now());

        cb_start_hour.setItems(hourInts);
        cb_start_minute.setItems(minuteInts);
        cb_end_hour.setItems(hourInts);
        cb_end_minute.setItems(minuteInts);
        cb_start_hour.setValue(0);
        cb_start_minute.setValue(0);
        cb_end_hour.setValue(23);
        cb_end_minute.setValue(59);


        btn_confirm.setOnMouseClicked(event -> {
            LOGGER.info(cb_start_hour.getEditor().getText());
            LOGGER.info(cb_end_hour.getEditor().getText());
            getSpace(new Callback() {
                @Override
                public void getStartAndEndTime(Timestamp start, Timestamp end) {
                    ShouruDataBase.getINSTANCE().getAllBetween(start, end, new ShouruRepository.LoadTsrxxItemCallback() {
                        @Override
                        public void onTasksLoaded(List<SRXX_Item> list) {
                            for (SRXX_Item item : list) {
                                ghxxes_ti.add(new SRXX_TreeItem(
                                        item.getKSMC(),
                                        item.getYSBH(),
                                        item.getYSMC(),
                                        item.isSFZJ(),
                                        item.getGHRC(),
                                        item.getSRHJ()
                                ));
                            }
                        }

                        @Override
                        public void onDataNotAvailable() {
                            ghxxes_ti.remove(0, ghxxes_ti.size());
                        }
                    });
                }

                @Override
                public void error(ParseException e) {
                    LOGGER.error("时间解析失败");
                }
            });
        });

        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));
        ghxxes_ti.add(new SRXX_TreeItem("111", "1111", "1111", true, 1, 20f));

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

    private void navigateToBack() throws VetoException, FlowException {
        actionHandler.navigate(DoctorController.class);
    }

    private void getSpace(Callback callback) {
        Timestamp start;
        Timestamp end;
        LocalDate startDate = dp_start.getValue();
        LocalDate endDate = dp_end.getValue();
        String startTime;
        String endTime;
        startTime = parseSingleTime(cb_start_hour) + ":" + parseSingleTime(cb_start_minute);
        endTime = parseSingleTime(cb_end_hour) + ":" + parseSingleTime(cb_end_minute);

        LOGGER.info(startDate.toString());
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date startDateTime = df.parse(startDate.toString() + " " + startTime);
            Date endDateTime = df.parse(endDate.toString() + " " + endTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDateTime);
            start = new Timestamp(cal.getTimeInMillis());
            cal.setTime(endDateTime);
            end = new Timestamp(cal.getTimeInMillis());

            callback.getStartAndEndTime(start, end);
        } catch (ParseException e) {
            callback.error(e);
        }
    }

    static interface Callback {
        void getStartAndEndTime(Timestamp start, Timestamp end);

        void error(ParseException e);
    }


    private String parseSingleTime(JFXComboBox cb) {
        if (Integer.parseInt(cb.getEditor().getText()) < 10)
            return "0" + cb.getEditor().getText();
        return cb.getEditor().getText();
    }


}
