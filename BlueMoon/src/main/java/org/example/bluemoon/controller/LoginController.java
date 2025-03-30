package org.example.bluemoon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.bluemoon.models.User;
import org.example.bluemoon.services.LoginService;
import org.example.bluemoon.util.SceneUtil;

public class LoginController {
    private final LoginService loginService = new LoginService();
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label text_empty;
    @FXML
    private Label pass_empty;
    @FXML
    private Label login_failed;
    @FXML
    private Label forgot_password;
    @FXML
    private Button login_button;

    @FXML
    private void initialize() {
        text_empty.setVisible(false);
        pass_empty.setVisible(false);
        login_failed.setVisible(false);
        forgot_password.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Quên mật khẩu");
            alert.setHeaderText("Quên thì thôi, nhớ thì đăng nhập");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/org/example/bluemoon/style/alert-style.css").toExternalForm());
            alert.setGraphic(null);

            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        });
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        // Vô hiệu hóa nút và đổi con trỏ sang trạng thái chờ
        login_button.setDisable(true);
        login_button.setCursor(Cursor.WAIT);

        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // Hiển thị cảnh báo nếu trống
        boolean emptyUsername = username.isEmpty();
        boolean emptyPassword = password.isEmpty();

        text_empty.setVisible(emptyUsername);
        pass_empty.setVisible(emptyPassword);
        login_failed.setVisible(false);

        // Nếu thiếu thông tin, bật lại nút và dừng xử lý
        if (emptyUsername || emptyPassword) {
            login_button.setDisable(false);
            login_button.setCursor(Cursor.DEFAULT);
            return;
        }

        // Gọi service để kiểm tra đăng nhập
        User user = loginService.login(username, password);

        if (user != null) {
            Stage stage = (Stage) login_button.getScene().getWindow();
            SceneUtil.changeScene(stage, "/org/example/bluemoon/views/dashboard.fxml");
            stage.centerOnScreen();
        } else {
            login_failed.setVisible(true);
            login_button.setDisable(false);
            login_button.setCursor(Cursor.DEFAULT);
        }
    }


}