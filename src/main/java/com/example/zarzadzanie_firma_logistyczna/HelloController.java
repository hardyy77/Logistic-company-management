package com.example.zarzadzanie_firma_logistyczna;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

//w przyszłości dodać funkcję logowania

public class HelloController {
    @FXML
    private Button button;

    @FXML
    private Label connectionStatusLabel;

    @FXML
    private void initialize() throws SQLException {

        // kod do połączenia z bazą danych

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if (connectDB.isValid(5)) {
            connectionStatusLabel.setText("Połączenie z bazą danych: Aktywne");
        } else {
            connectionStatusLabel.setText("Połączenie z bazą danych: Nieaktywne");
        }
    }

    @FXML
    private void handleButtonAction() throws Exception {
        Stage stage = (Stage) button.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("menu.fxml")));
        stage.setScene(scene);
    }
}