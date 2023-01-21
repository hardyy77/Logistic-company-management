package com.example.zarzadzanie_firma_logistyczna;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WarehouseController implements Initializable {

    @FXML
    private TableView<WarehouseDataModel> warehouseTable;
    @FXML
    private TableColumn<WarehouseDataModel,  Integer> itemColumn;
    @FXML
    private TableColumn<WarehouseDataModel, String> avaibleColumn;
    @FXML
    private TableColumn<WarehouseDataModel, String> rackColumn;
    @FXML
    private TableColumn<WarehouseDataModel, String> shelfColumn;
    @FXML
    private TableColumn<WarehouseDataModel, String> typeColumn;
    @FXML
    private TableColumn<WarehouseDataModel, String> warningColumn;
    @FXML
    private TableColumn<WarehouseDataModel, String> weightColumn;

    @FXML
    private Button backButton;

    @FXML
    private TextField searchField;

    ObservableList<WarehouseDataModel> WarehouseDataModelObservableList = FXCollections.observableArrayList();

    @FXML
    private void handleBackButton() throws Exception {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("menu.fxml")));
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = DatabaseConnection.getConnection();

        WarehouseDataModelObservableList.clear();

        String query = "SELECT warehouse.item_ID, warehouse.dostepnosc, warehouse.regal, warehouse.polka, types_of_package.typ, types_of_package.uwagi, types_of_package.waga\n" +
                "FROM warehouse\n" +
                "INNER JOIN types_of_package ON warehouse.Package_ID = types_of_package.Package_ID;";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            while (queryOutput.next()){
                Integer queryItem_ID = queryOutput.getInt("Item_ID");
                String queryDostepnosc = queryOutput.getString("Dostepnosc");
                String queryRegal = queryOutput.getString("Regal");
                String queryPolka = queryOutput.getString("Polka");
                String queryTyp = queryOutput.getString("Typ");
                String queryUwagi = queryOutput.getString("Uwagi");
                String queryWaga = queryOutput.getString("Waga");

                WarehouseDataModelObservableList.add(new WarehouseDataModel(queryItem_ID, queryDostepnosc, queryRegal, queryPolka, queryTyp, queryUwagi, queryWaga));
            }
            warehouseTable.setItems(WarehouseDataModelObservableList);

            itemColumn.setCellValueFactory(new PropertyValueFactory<>("Item_ID"));
            avaibleColumn.setCellValueFactory(new PropertyValueFactory<>("Dostepnosc"));
            rackColumn.setCellValueFactory(new PropertyValueFactory<>("Regal"));
            shelfColumn.setCellValueFactory(new PropertyValueFactory<>("Polka"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("Typ"));
            warningColumn.setCellValueFactory(new PropertyValueFactory<>("Uwagi"));
            weightColumn.setCellValueFactory(new PropertyValueFactory<>("Waga"));



        } catch (Exception e) {
            Logger.getLogger(OrdersDataModel.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        FilteredList<WarehouseDataModel> filteredData = new FilteredList<>(WarehouseDataModelObservableList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(type -> {
                //jeżeli pole jest puste wyświetlaj wszystko.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                //porównuje nazwę każdego klienta z tekstem w polu.
                String lowerCaseFilter = newValue.toLowerCase();
                if (type.getTyp().toLowerCase().contains(lowerCaseFilter)) {
                    return true; //znalazło pasujący elemnt.
                }
                return false; //nie znalazło pasującego elementu.
            });
        });
        SortedList<WarehouseDataModel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(warehouseTable.comparatorProperty());
        warehouseTable.setItems(sortedData);

    }

}


