package com.example.zarzadzanie_firma_logistyczna;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdersController implements Initializable {

    @FXML
    private TableView<OrdersDataModel> ordersTable;
    @FXML
    private TableColumn<OrdersDataModel, String> dateColumn;
    @FXML
    private TableColumn<OrdersDataModel, String> customerColumn;
    @FXML
    private TableColumn<OrdersDataModel, String> packageColumn;
    @FXML
    private TableColumn<OrdersDataModel, String> statusColumn;
    @FXML
    private TableColumn<OrdersDataModel, String> driverColumn;
    @FXML
    private TableColumn<OrdersDataModel, String> truckColumn;

    @FXML
    private Button backButton;

    ObservableList<OrdersDataModel> OrdersDataModelObservableList = FXCollections.observableArrayList();

    @FXML
    private void handleBackButton() throws Exception {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("menu.fxml")));
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "SELECT Data_realizacji, customers.Nazwa, types_of_package.typ, delivery_status.Status, drivers.Nazwisko, trucks.Marka FROM orders JOIN customers ON orders.customer_ID = customers.Customer_ID JOIN types_of_package ON orders.Package_type_ID = types_of_package.Package_ID JOIN delivery_status ON orders.Delivery_status_ID = delivery_status.Status_ID JOIN drivers ON orders.driver_id = drivers.Driver_ID JOIN trucks ON orders.truck_id = trucks.Truck_ID;";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                String queryData_realizacji = queryOutput.getString("Data_realizacji");
                String queryKlient = queryOutput.getString("nazwa");
                String queryLadunek = queryOutput.getString("Typ");
                String queryStatus = queryOutput.getString("Status");
                String queryKierowca = queryOutput.getString("Nazwisko");
                String queryCiezarowka = queryOutput.getString("Marka");

                OrdersDataModelObservableList.add(new OrdersDataModel(queryData_realizacji, queryKlient, queryLadunek, queryStatus, queryKierowca, queryCiezarowka));
            }

            dateColumn.setCellValueFactory(new PropertyValueFactory<>("Data_realizacji"));
            customerColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
            packageColumn.setCellValueFactory(new PropertyValueFactory<>("typ"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
            driverColumn.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            truckColumn.setCellValueFactory(new PropertyValueFactory<>("marka"));

            ordersTable.setItems(OrdersDataModelObservableList);

        } catch (Exception e) {
            Logger.getLogger(OrdersDataModel.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
}


