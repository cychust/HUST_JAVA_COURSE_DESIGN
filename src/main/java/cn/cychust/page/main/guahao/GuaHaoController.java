package cn.cychust.page.main.guahao;

import cn.cychust.State;
import cn.cychust.data.tksxx.T_KSXX;
import cn.cychust.data.tksxx.source.TKSXXDataSource;
import cn.cychust.data.tksxx.source.TKSXXRepository;
import cn.cychust.data.tksys.T_KSYS;
import cn.cychust.data.tksys.source.TKSYSDataSource;
import cn.cychust.data.tksys.source.TKSYSRepository;
import cn.cychust.page.login.LoginController;
import cn.cychust.page.main.patient.PatientController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
        cb_ksmc.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                LOGGER.info("aaa");
                LOGGER.info(cb_ksmc.getEditor().getText());
                LOGGER.info(cb_ksmc.getAccessibleText());
                ysxmList.remove(0, ysxmList.size());
                if (cb_ksmc.getEditor().getText() != null && cb_ksmc.getEditor().getText().length() != 0) {
                    String ksbh = cb_ksmc.getEditor().getText().split(" ")[0].trim();
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
                }
            }
        });
        cb_ysxm.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (ysxmList.size() > 0)
                    hzlbList.remove(0, hzlbList.size());
                if (cb_ysxm.getEditor().getText() != null && cb_ysxm.getEditor().getText().length() != 0) {
                    String ysbh = cb_ysxm.getEditor().getText().split(" ")[0].trim();
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
                }
            }
        });

    }

    private void navigateToLogin() throws VetoException, FlowException {
        actionHandler.navigate(LoginController.class);
    }

    private void navigateToBack() throws VetoException, FlowException {
        actionHandler.navigate(PatientController.class);
    }

}
