package cn.cychust.page.main.guahao;

import cn.cychust.State;
import cn.cychust.data.tghxx.T_GHXX;
import cn.cychust.data.tghxx.source.TGHXXDataSource;
import cn.cychust.data.tghxx.source.TGHXXRepository;
import cn.cychust.data.thzxx.T_HZXX;
import cn.cychust.data.thzxx.source.THZXXDatasource;
import cn.cychust.data.thzxx.source.THZXXRepository;
import cn.cychust.data.tksxx.T_KSXX;
import cn.cychust.data.tksxx.source.TKSXXDataSource;
import cn.cychust.data.tksxx.source.TKSXXRepository;
import cn.cychust.data.tksys.T_KSYS;
import cn.cychust.data.tksys.source.TKSYSDataSource;
import cn.cychust.data.tksys.source.TKSYSRepository;
import cn.cychust.page.login.LoginController;
import cn.cychust.page.main.patient.PatientController;
import cn.cychust.util.DialogUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 17:08
 **/
@ViewController(value = "/fxml/main_gh.fxml")
public class GuaHaoController {


    private final static Logger LOGGER = Logger.getLogger(GuaHaoController.class);
    @FXML
    private JFXComboBox<String> cb_ksmc;
    @FXML
    private JFXComboBox<String> cb_hzlb;
    @FXML
    private JFXComboBox<String> cb_ysxm;
    @FXML
    private JFXComboBox<String> cb_hzmc;

    @FXML
    private JFXTextField tf_to_pay;
    @FXML
    private JFXTextField tf_real_pay;
    @FXML
    private JFXTextField tf_ghhm;

    @FXML
    private JFXButton btn_confirm;

    @FXML
    private JFXButton btn_clear;

    @FXML
    private JFXTextField tf_zl;

    @FXML
    private JFXButton btn_logout;

    @ActionHandler
    private FlowActionHandler actionHandler;


    @FXML
    private JFXButton btn_back;
//    private List<String> ksmcList = null;

    private ObservableList<String> mValue = null;

    private ObservableList<String> ysxmList = FXCollections.observableArrayList();

    private ObservableList<String> hzlbList = FXCollections.observableArrayList();

    private ObservableList<String> hzmcList = FXCollections.observableArrayList();

    @PostConstruct
    public void init() throws Exception {
        TKSXXDataSource.getINSTANCE().getAll(new TKSXXRepository.LoadTksxxsCallback() {
            @Override
            public void onTasksLoaded(List<T_KSXX> list) {
//                ksmcList = new ArrayList<>();
                mValue = FXCollections.observableArrayList();
                for (T_KSXX ksxx : list) {
                    mValue.add(ksxx.getKSBH() + "  " + ksxx.getKSMC());
                }
                cb_ksmc.setItems(mValue);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        tf_to_pay.setDisable(true);
        tf_zl.setDisable(true);
        tf_ghhm.setDisable(true);


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
        cb_ysxm.setItems(ysxmList);
        cb_hzlb.setItems(hzlbList);
        cb_hzmc.setItems(hzmcList);
        cb_ksmc.valueProperty().addListener((observable, oldValue, newValue) -> {
            LOGGER.info("aaa");
            LOGGER.info(cb_ksmc.getEditor().getText());
            LOGGER.info(cb_ksmc.getAccessibleText());
            ysxmList.remove(0, ysxmList.size());
            if (newValue == null)
                return;
            String ksbh = newValue.split(" ")[0].trim();
            TKSYSDataSource.getINSTANCE().getKsysesByKSBH(ksbh, new TKSYSRepository.LoadTksysesCallback() {
                @Override
                public void onTasksLoaded(List<T_KSYS> list) {
                    for (T_KSYS tKsys : list) {
                        ysxmList.add(tKsys.getYSBH() + " " + tKsys.getYSMC());
                    }
                }

                @Override
                public void onDataNotAvailable() {
                    ysxmList = FXCollections.observableArrayList();
                }
            });

        });

        cb_ysxm.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (ysxmList.size() > 0)
                hzlbList.remove(0, hzlbList.size());
            if (newValue == null)
                return;
            String ysbh = newValue.split(" ")[0].trim();
            TKSYSDataSource.getINSTANCE().getOneByYSBH(ysbh, new TKSYSRepository.GetTksysCallback() {
                @Override
                public void onTasksLoaded(T_KSYS t_brxx) {
                    if (t_brxx.isSFZJ()) {
                        hzlbList.add("普通号");
                        hzlbList.add("专家号");
                    } else {
                        hzlbList.add("普通号");
                    }
                }

                @Override
                public void onDataNotAvailable() {
                    hzlbList.remove(0, hzlbList.size());
                    hzlbList.add("普通号");
                }

            });

        });

        cb_hzlb.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (hzmcList.size() > 0)
                hzmcList.remove(0, hzmcList.size());
            if (cb_ksmc.getEditor().getText() != null && cb_ksmc.getEditor().getText().length() != 0) {
                String ksbh = cb_ksmc.getEditor().getText().split(" ")[0].trim();
                if (newValue == null)
                    return;
                boolean sfzj = newValue.equals("专家号");
                THZXXDatasource.getINSTANCE().getHzmcByKsbhAndSfzj(ksbh, sfzj, new THZXXRepository.LoadThzxxsCallback() {
                    @Override
                    public void onTasksLoaded(List<T_HZXX> list) {
                        LOGGER.info("bbbb");
                        LOGGER.info(list.size());
                        for (T_HZXX item : list) {
                            hzmcList.add(item.getHZBH() + " " + item.getHZMC());
                        }
                    }

                    @Override
                    public void onDataNotAvailable() {
                        hzmcList.remove(0, hzlbList.size());
                    }
                });
            }

        });

        tf_real_pay.setDisable(true);
        cb_hzmc.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null)
                return;
            String hzbh = newValue.split(" ")[0].trim();
            THZXXDatasource.getINSTANCE().getOne(hzbh, new THZXXRepository.GetThzxxCallback() {
                @Override
                public void onTasksLoaded(T_HZXX t_hzxx) {
                    LOGGER.info("hahahah");
                    tf_to_pay.setText(String.valueOf(t_hzxx.getGHFY()));
                    if (State.getT_brxx().getYCJE() < t_hzxx.getGHFY()) {
                        tf_real_pay.setDisable(false);
                        tf_real_pay.setText("0");
                    } else {
                        tf_zl.setText(String.valueOf(State.getT_brxx().getYCJE() - t_hzxx.getGHFY()));
                    }
                }

                @Override
                public void onDataNotAvailable() {

                }
            });
        });


        btn_confirm.setOnMouseClicked(event -> {
            if (tf_zl.getText().length() == 0)
                return;
            if (tf_zl.getText().charAt(0) == '-') {
//                SnackbarUtil.show("余额不足，请充足够的值");
                new DialogUtil.Builder().title("警告").content("余额不足，请充足够的值").root(btn_confirm).show();
//                DialogUtil.show(btn_confirm);
                return;
            }
            State.getT_brxx().setYCJE(Float.valueOf(tf_zl.getText()));    //更新病人登录状态
//            TBRXXDataSource source = new TBRXXDataSource();
//            source.updateOne(State.getT_brxx());                         //更新病人数据库状态
            T_GHXX newOne = new T_GHXX();
            newOne.setGHBH("");                                         //无用
            newOne.setHZBH(cb_hzmc.getEditor().getText().split(" ")[0].trim());
            newOne.setYSBH(cb_ysxm.getEditor().getText().split(" ")[0].trim());
            newOne.setBRBH(State.getT_brxx().getBRBH());
            newOne.setGHRC(1);                                           //无用
            newOne.setTHBZ(false);                                        //退号标志
            newOne.setGHFY(Float.valueOf(tf_to_pay.getText()));
            newOne.setRQSJ(new Timestamp(System.currentTimeMillis()));
            TGHXXDataSource.getINSTANCE().saveOne(newOne, new Timestamp(getZeroHour()), Float.valueOf(tf_zl.getText()),
                    new TGHXXRepository.GetTghxxCallback() {
                        @Override
                        public void onTasksLoaded(T_GHXX t_ghxx) {
                            DialogUtil.Builder builder = new DialogUtil.Builder();
                            builder.title("成功").content(dialogContent(t_ghxx)).root(btn_confirm)
                                    .show(() -> {
                                        doClear();
                                    });
                        }

                        @Override
                        public void onDataNotAvailable() {
                            DialogUtil.Builder builder = new DialogUtil.Builder();
                            builder.title("错误").content("挂号失败").root(btn_confirm).show();
                        }
                    });                //添加
            DialogUtil.Builder builder = new DialogUtil.Builder();
            builder.content("").title("挂号成功").root(btn_confirm).show();

        });
        tf_real_pay.setText(String.valueOf(State.getT_brxx().getYCJE()));


        tf_real_pay.textProperty().addListener((observable, oldValue, newValue) -> {
            Float result = subtract(newValue, tf_to_pay.getText()) + State.getT_brxx().getYCJE();
            tf_zl.setText(String.valueOf(result));
        });

        btn_clear.setOnMouseClicked(event -> {
            doClear();
        });

    }

    private void navigateToLogin() throws VetoException, FlowException {
        actionHandler.navigate(LoginController.class);
    }

    private void navigateToBack() throws VetoException, FlowException {
        actionHandler.navigate(PatientController.class);
    }

    private float subtract(String first, String second) {
        if (first != null && first.length() > 0)
            return Float.valueOf(first) - Float.valueOf(second);
        else
            return 0 - Float.valueOf(second);
    }

    private long getZeroHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    private String dialogContent(T_GHXX t_ghxx) {
        StringBuilder builder = new StringBuilder();
        builder.append("挂号编号    ： " + t_ghxx.getGHBH());
        builder.append("\n实际挂号费用 :  " + t_ghxx.getGHFY());
        builder.append("\n挂号时间    ： " + t_ghxx.getRQSJ());
        return builder.toString();
    }

    private void doClear() {
        cb_hzmc.getEditor().setText("");
        cb_hzlb.getEditor().setText("");
        cb_ksmc.getEditor().setText("");
        cb_ysxm.getEditor().setText("");
        tf_real_pay.setText("");
        tf_real_pay.setDisable(true);
        tf_zl.setText("");
        tf_to_pay.setText("");
        tf_ghhm.setText("");
    }
}
