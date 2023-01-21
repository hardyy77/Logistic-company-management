package com.example.zarzadzanie_firma_logistyczna;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//klasa jest używana do obsługi dodawania klienta
public class AddCustomerController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField nipField;
    @FXML
    private Button addButton;

    //metoda pobiera dane z pól tekstowych i wstawia je do tabeli w bazie danych

    @FXML
    private void handleAddButton() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO Customers (nazwa, miasto, adres, email, nr_tel, nip) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nameField.getText());
            statement.setString(2, cityField.getText());
            statement.setString(3, addressField.getText());
            statement.setString(4, emailField.getText());
            statement.setString(5, phoneField.getText());
            statement.setString(6, nipField.getText());
            statement.executeUpdate();
            statement.close();
            conn.close();

            //pokazuje komunikat o poprawnym dodaniu klienta

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sukces!");
            alert.setHeaderText("Klient został dodany poprawnie");
            alert.showAndWait();

        } catch (SQLException ex) {

            //pokazuje komunikat o błędzie przy dodawaniu

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("wystąpił problem podczas dodawania klienta");
            alert.showAndWait();
            ex.printStackTrace();

        }
    }

    //setConnection ustawia połączenie z bazą danych

    public void setConnection(Connection connection) {
    }
}


