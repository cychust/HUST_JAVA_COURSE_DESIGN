package cn.cychust.controller;

import cn.cychust.comm.BaseObserver;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import io.datafx.controller.ViewController;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javax.annotation.PostConstruct;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-16 00:02
 **/
@ViewController(value = "/fxml/main.fxml")
public final class LoginController {
    @FXML
    private JFXRadioButton btn;

    @FXML
    private JFXButton btn2;

    @PostConstruct
    public void init() throws Exception {
        //init


        Observable<ActionEvent> bttnEvent=JavaFxObservable.eventsOf(btn,ActionEvent.ACTION);
        bttnEvent.subscribe(new BaseObserver<ActionEvent>() {
            @Override
            public void onMyError(Throwable throwable) {

            }

            @Override
            public void onNext(ActionEvent actionEvent) {
                btn.setText("aaaaa");
            }
        });
    }
}
