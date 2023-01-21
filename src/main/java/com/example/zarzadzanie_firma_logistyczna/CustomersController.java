package com.example.zarzadzanie_firma_logistyczna;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomersController implements Initializable {

    @FXML
    private TableView<CustomersDataModel> customersTable;
    @FXML
    private TableColumn<CustomersDataModel,  Integer> customerColumn;
    @FXML
    private TableColumn<CustomersDataModel, String> nameColumn;
    @FXML
    private TableColumn<CustomersDataModel, String> cityColumn;
    @FXML
    private TableColumn<CustomersDataModel, String> adressColumn;
    @FXML
    private TableColumn<CustomersDataModel, String> emailColumn;
    @FXML
    private TableColumn<CustomersDataModel, String> nrColumn;
    @FXML
    private TableColumn<CustomersDataModel, String> nipColumn;

    @FXML
    private Button backButton;
    @FXML
    private Button adddButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField searchField;

    ObservableList<CustomersDataModel> CustomersDataModelObservableList = FXCollections.observableArrayList();

    @FXML
    private void handleBackButton() throws Exception {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("menu.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void handleAddButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-customer.fxml"));
            Parent root = loader.load();

            AddCustomerController controller = loader.getController();
            controller.setConnection(DatabaseConnection.getConnection());

            Stage stage = new Stage();
            stage.setTitle("Dodaj nowego klienta");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteButton() {

        //pobiera zaznaczony element z tabeli

        CustomersDataModel selectedItem = customersTable.getSelectionModel().getSelectedItem();

        //pobiera id wybranego elementu

        int selectedId = selectedItem.getCustomer_ID();

        //tworzy zapytanie

        String deleteQuery = "DELETE FROM customers WHERE Customer_ID = " + selectedId;

        //tworzy połączenie i wykonuje zapytanie

        Connection connectDB = DatabaseConnection.getConnection();
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteQuery);

            //odświeża tabele

            refreshTable();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        refreshTable();
    }

    private void refreshTable() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = DatabaseConnection.getConnection();

        CustomersDataModelObservableList.clear();

        String query = "SELECT Customer_ID, Nazwa, Miasto, Adres, Email, Nr_tel, NIP FROM customers";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                Integer queryCustomer_ID = queryOutput.getInt("Customer_ID");
                String queryNazwa = queryOutput.getString("nazwa");
                String queryMiasto = queryOutput.getString("miasto");
                String queryAdres = queryOutput.getString("adres");
                String queryEmail = queryOutput.getString("email");
                String queryNr_tel = queryOutput.getString("nr_tel");
                String queryNip = queryOutput.getString("nip");

                CustomersDataModelObservableList.add(new CustomersDataModel(queryCustomer_ID, queryNazwa, queryMiasto, queryAdres, queryEmail, queryNr_tel, queryNip

                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = DatabaseConnection.getConnection();

        String query = "SELECT Customer_ID, Nazwa, Miasto, Adres, Email, Nr_tel, NIP FROM customers";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                Integer queryCustomer_ID = queryOutput.getInt("Customer_ID");
                String queryNazwa = queryOutput.getString("nazwa");
                String queryMiasto = queryOutput.getString("miasto");
                String queryAdres = queryOutput.getString("adres");
                String queryEmail = queryOutput.getString("email");
                String queryNr_tel = queryOutput.getString("nr_tel");
                String queryNip = queryOutput.getString("nip");

                CustomersDataModelObservableList.add(new CustomersDataModel(queryCustomer_ID, queryNazwa, queryMiasto, queryAdres, queryEmail, queryNr_tel, queryNip));
            }
            customersTable.setItems(CustomersDataModelObservableList);

            customerColumn.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
            cityColumn.setCellValueFactory(new PropertyValueFactory<>("miasto"));
            adressColumn.setCellValueFactory(new PropertyValueFactory<>("adres"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            nrColumn.setCellValueFactory(new PropertyValueFactory<>("nr_tel"));
            nipColumn.setCellValueFactory(new PropertyValueFactory<>("nip"));

        } catch (Exception e) {
            Logger.getLogger(OrdersDataModel.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        FilteredList<CustomersDataModel> filteredData = new FilteredList<>(CustomersDataModelObservableList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(customer -> {
                //jeżeli pole jest puste wyświetla wszystko.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                //porównuje nazwę każdego klienta z tesktem w polu.
                String lowerCaseFilter = newValue.toLowerCase();
                if (customer.getNazwa().toLowerCase().contains(lowerCaseFilter)) {
                    return true; //jeżeli znalazło pasujący elemnt.
                }
                return false; //jeżeli nie znalazło pasującego elementu.
            });
        });
        SortedList<CustomersDataModel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(customersTable.comparatorProperty());
        customersTable.setItems(sortedData);
    }
}


