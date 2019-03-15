package cn.cychust.controller;

import com.jfoenix.controls.JFXButton;
import io.datafx.controller.ViewController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import javax.annotation.PostConstruct;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-16 00:02
 **/
@ViewController(value = "/fxml/main.fxml",title = "Login Windows")
public final class LoginController {
    @FXML
    private JFXButton btn;

    @PostConstruct
    public void init() throws Exception{
        //init
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                btn.setText("vvvvvvvvv");
            }
        });
    }
}
