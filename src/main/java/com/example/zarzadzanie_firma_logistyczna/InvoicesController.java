package com.example.zarzadzanie_firma_logistyczna;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class InvoicesController implements Initializable {

    @FXML
    private TableView<InvoicesDataModel> invoicesTable;
    @FXML
    private TableColumn<InvoicesDataModel,  Integer> invoiceColumn;
    @FXML
    private TableColumn<InvoicesDataModel, String> customerColumn;
    @FXML
    private TableColumn<InvoicesDataModel, String> dateColumn;
    @FXML
    private TableColumn<InvoicesDataModel, String> isColumn;
    @FXML
    private TableColumn<InvoicesDataModel, String> priceColumn;

    @FXML
    private Button backButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;


    ObservableList<InvoicesDataModel> InvoicesDataModelObservableList = FXCollections.observableArrayList();

    @FXML
    private void handleBackButton() throws Exception {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("menu.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void handleAddButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-invoice.fxml"));
            Parent root = loader.load();

            AddInvoiceController controller = loader.getController();
            controller.setConnection(DatabaseConnection.getConnection());

            Stage stage = new Stage();
            stage.setTitle("Dodaj nową fakturę");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        refreshTable();
    }

    @FXML
    private void handleDeleteButton() {

        InvoicesDataModel selectedItem = invoicesTable.getSelectionModel().getSelectedItem();
        int selectedId = selectedItem.getInvoice_ID();

        String deleteQuery = "DELETE FROM invoices WHERE Invoice_ID = " + selectedId;

        Connection connectDB = DatabaseConnection.getConnection();
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteQuery);

            refreshTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        refreshTable();
    }

    private void refreshTable() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = DatabaseConnection.getConnection();

        InvoicesDataModelObservableList.clear();

        String query = "SELECT invoice_id, customer_ID, data, czy_oplacona, kwota\n" +
                "FROM invoices";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                Integer queryInvoice_id = queryOutput.getInt("Invoice_ID");
                String queryNazwa = queryOutput.getString("Customer_ID");
                String queryData = queryOutput.getString("Data");
                String queryCzy_oplacona = queryOutput.getString("Czy_oplacona");
                String queryKwota = queryOutput.getString("kwota");

                InvoicesDataModelObservableList.add(new InvoicesDataModel(queryInvoice_id, queryNazwa, queryData, queryCzy_oplacona, queryKwota));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = DatabaseConnection.getConnection();

        InvoicesDataModelObservableList.clear();

        String query = "SELECT invoice_id, Customer_ID, data, czy_oplacona, kwota FROM invoices";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                Integer queryInvoice_id = queryOutput.getInt("Invoice_ID");
                String queryNazwa = queryOutput.getString("Customer_ID");
                String queryData = queryOutput.getString("data");
                String queryCzy_oplacona = queryOutput.getString("Czy_oplacona");
                String queryKwota = queryOutput.getString("kwota");

                InvoicesDataModelObservableList.add(new InvoicesDataModel(queryInvoice_id, queryNazwa, queryData, queryCzy_oplacona, queryKwota));
            }
            invoicesTable.setItems(InvoicesDataModelObservableList);

            invoiceColumn.setCellValueFactory(new PropertyValueFactory<>("Invoice_ID"));
            customerColumn.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
            isColumn.setCellValueFactory(new PropertyValueFactory<>("czy_oplacona"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("Kwota"));

        } catch (Exception e) {
            Logger.getLogger(OrdersDataModel.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
}


