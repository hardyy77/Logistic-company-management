package com.example.zarzadzanie_firma_logistyczna;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Button warehouseButton;
    @FXML
    private Button ordersButton;
    @FXML
    private Button customersButton;
    @FXML
    private Button invoicesButton;
    @FXML
    private Button companyButton;
    @FXML
    private Button exitButton;

    @FXML
    private void handleWarehouseButton() throws Exception {
        Stage stage = (Stage) warehouseButton.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Warehouse.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void handleOrdersButton() throws Exception {
        Stage stage = (Stage) ordersButton.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Orders.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void handleCustomersButton() throws Exception {
        Stage stage = (Stage) customersButton.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Customers.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void handleInvoicesButton() throws Exception {
        Stage stage = (Stage) invoicesButton.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Invoices.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void handleCompanyButton() throws Exception {
        Stage stage = (Stage) companyButton.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Company.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void handleExitButton() {
        Platform.exit();
    }

}