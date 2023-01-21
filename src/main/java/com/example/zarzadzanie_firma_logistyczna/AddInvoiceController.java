package com.example.zarzadzanie_firma_logistyczna;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//klasa pobiera dane z pól tekstowych i wstawia je do tabeli w bazie danych
public class AddInvoiceController {

    @FXML
    private TextField customerField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField isField;
    @FXML
    private TextField priceField;
    @FXML
    private Button addButton;

    @FXML
    private void handleAddButton() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO invoices (Customer_ID, Data, Czy_oplacona, Kwota) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, customerField.getText());
            statement.setString(2, dateField.getText());
            statement.setString(3, isField.getText());
            statement.setString(4, priceField.getText());
            statement.executeUpdate();
            statement.close();
            conn.close();

            //wyświetla komunikat o poprawnym dodaniu faktury

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sukces!");
            alert.setHeaderText("Faktura została dodana poprawnie");
            alert.showAndWait();

        } catch (SQLException ex) {

            //wyświetla komunikat o błędzie

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Wystąpił błąd podczas dodawania faktury");
            alert.showAndWait();
            ex.printStackTrace();

        }
    }

    //setConnection ustawia połączenie z bazą danych

    public void setConnection(Connection connection) {
    }
}


