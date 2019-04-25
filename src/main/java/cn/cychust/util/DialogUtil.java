package cn.cychust.util;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-25 12:08
 **/
public class DialogUtil {

    private static JFXAlert alert;

    public static class Builder {
        ConfirmCallback mConfirmCallback;
        String title;
        String content;
        JFXButton button;

        public Builder() {
            title = "Dialog";
            content = "Non-Content";
            mConfirmCallback = null;
            mBuilder = this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder root(JFXButton button) {
            this.button = button;
            return this;
        }


        public void show() {
            if (alert != null)
                alert.hide();
            showDialog(button);
        }

        public void show(ConfirmCallback callback) {
            this.mConfirmCallback = callback;
            show();
        }

    }

    private static Builder mBuilder;

    public static void showDialog(JFXButton alertButton) {
        alert = new JFXAlert((Stage) alertButton.getScene().getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label(mBuilder.title));
        layout.setBody(new Label(mBuilder.content));
        JFXButton closeButton = new JFXButton("确定");
        closeButton.getStyleClass().add("dialog-accept");
        closeButton.setOnAction(event -> {
            alert.hideWithAnimation();
            if (mBuilder.mConfirmCallback != null) {
                mBuilder.mConfirmCallback.doSomething();
                mBuilder.mConfirmCallback = null;                    //防止
            }
        });
        layout.setActions(closeButton);
        alert.setContent(layout);
        alert.show();
    }

    public static void hide() {
        alert.hideWithAnimation();
    }

    public interface ConfirmCallback {
        void doSomething();
    }
}
