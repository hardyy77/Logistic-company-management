package com.example.zarzadzanie_firma_logistyczna;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompanyController implements Initializable {

    @FXML
    private TableView<FeeDataModel> feeTable;
    @FXML
    private TableColumn<FeeDataModel, Integer> feeColumn;
    @FXML
    private TableColumn<FeeDataModel, Integer> electricColumn;
    @FXML
    private TableColumn<FeeDataModel, Integer> internetColumn;
    @FXML
    private TableColumn<FeeDataModel, Integer> insuranceColumn;
    @FXML
    private TableColumn<FeeDataModel, Integer> salaryColumn;
    @FXML
    private TableColumn<FeeDataModel, Integer> softwareColumn;
    @FXML
    private TableColumn<FeeDataModel, Integer> trainingsColumn;
    @FXML
    private TableColumn<FeeDataModel, Integer> marketingColumn;
    @FXML
    private TableColumn<FeeDataModel, Integer> fuelColumn;
    @FXML
    private TableColumn<FeeDataModel, Integer> dataColumn;

    @FXML
    private TableView<TrackDataModel> trackTable;
    @FXML
    private TableColumn<TrackDataModel, Integer> trackColumn;
    @FXML
    private TableColumn<TrackDataModel, String> orderColumn;
    @FXML
    private TableColumn<TrackDataModel, String> dateColumn;
    @FXML
    private TableColumn<TrackDataModel, String> lenghtColumn;
    @FXML
    private TableColumn<TrackDataModel, String> weatherColumn;
    @FXML
    private TableColumn<TrackDataModel, String> asphaltColumn;
    @FXML
    private TableColumn<TrackDataModel, String> startColumn;
    @FXML
    private TableColumn<TrackDataModel, String> endColumn;

    @FXML
    private TextField dateField;
    @FXML
    private TextField orderField;
    @FXML
    private TextField lenghtField;
    @FXML
    private TextField weatherField;
    @FXML
    private TextField asphaltField;
    @FXML
    private TextField startField;
    @FXML
    private TextField endField;

    @FXML
    private Button backButton;

    @FXML
    private Button addButton;

    ObservableList<FeeDataModel> FeeDataModelObservableList = FXCollections.observableArrayList();
    ObservableList<TrackDataModel> TrackDataModelObservableList = FXCollections.observableArrayList();

    @FXML
    private void handleBackButton() throws Exception {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("menu.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void handleAddButton() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO track_details (Data_rozpoczecia, Order_ID, Dlugosc, Przewidywana_pogoda, sredni_stan_asfaltu, Punkt_startowy, Punkt_docelowy) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, dateField.getText());
            statement.setString(2, orderField.getText());
            statement.setString(3, lenghtField.getText());
            statement.setString(4, weatherField.getText());
            statement.setString(5, asphaltField.getText());
            statement.setString(6, startField.getText());
            statement.setString(7, endField.getText());
            statement.executeUpdate();
            statement.close();
            conn.close();

            //komunikat o poprawnym dodaniu trasy

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sukces!");
            alert.setHeaderText("Trasa została dodana poprawnie");
            alert.showAndWait();

        } catch (SQLException ex) {

            //informacja o błędzie

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Wystąpił problem przy dodawaniu trasy");
            alert.showAndWait();
            ex.printStackTrace();

        }
        refreshTable();
    }

    private void refreshTable() {

        //tworzy połączenie z bazą danych

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = DatabaseConnection.getConnection();

        //czyści dabe z tabeli

        TrackDataModelObservableList.clear();

        //pobiera dane z tabeli track_details

        String query = "SELECT Track_ID, Data_rozpoczecia, Order_ID, Dlugosc, Przewidywana_pogoda, sredni_stan_asfaltu, Punkt_startowy, Punkt_docelowy\n" +
                "FROM track_details";
        try {

            //tworzy wyrażenie i wykonuje zapytanie

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            //iteruje po każdym rekordzie z bazy danych

            while (queryOutput.next()){

                //pobiera dane z kolumn tabeli

                Integer queryTrack_ID = queryOutput.getInt("Track_ID");
                String queryData = queryOutput.getString("Data_rozpoczecia");
                String queryOrder_ID = queryOutput.getString("Order_ID");
                String queryDlugosc = queryOutput.getString("Dlugosc");
                String queryPrzewidywana_pogoda = queryOutput.getString("Przewidywana_pogoda");
                String querySredni_stan_asfaltu = queryOutput.getString("sredni_stan_asfaltu");
                String queryPunkt_startowy = queryOutput.getString("Punkt_startowy");
                String queryPunkt_docelowy = queryOutput.getString("Punkt_docelowy");

                //dodaje pobrane dane do listy

                TrackDataModelObservableList.add(new TrackDataModel(queryTrack_ID, queryData, queryOrder_ID, queryDlugosc, queryPrzewidywana_pogoda, querySredni_stan_asfaltu, queryPunkt_startowy, queryPunkt_docelowy));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void FeeData() {

        //połączenie z bazą

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = DatabaseConnection.getConnection();

        //pobiera dane z tabeli

        String query = "SELECT fee_ID, Prad, Internet, Ubezpieczenia, Pensje, Oprogramowanie, Szkolenia, Marketing, Paliwo, data\n" +
                "FROM company_fees";
        try {

            //tworzy wyrażenie i wykonuje zapytanie

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);

            //iteruje po każdym rekordzie z bazy danych

            while (queryOutput.next()) {

                //pobiera dane z kolumn tabeli

                Integer queryFee_ID = queryOutput.getInt("Fee_ID");
                String queryPrad = queryOutput.getString("Prad");
                String queryInternet = queryOutput.getString("Internet");
                String queryUbezpieczenia = queryOutput.getString("Ubezpieczenia");
                String queryPensje = queryOutput.getString("Pensje");
                String queryOprogramowanie = queryOutput.getString("Oprogramowanie");
                String querySzkolenia = queryOutput.getString("Szkolenia");
                String queryMarketing = queryOutput.getString("Marketing");
                String queryPaliwo = queryOutput.getString("Paliwo");
                String queryData = queryOutput.getString("Data");

                //dodaje pobrane dane do listy

                FeeDataModelObservableList.add(new FeeDataModel(queryFee_ID, queryPrad, queryInternet, queryUbezpieczenia, queryPensje, queryOprogramowanie, querySzkolenia, queryMarketing, queryPaliwo, queryData));
            }

            //ustawia dane dla tabeli z listy

            feeTable.setItems(FeeDataModelObservableList);
            feeColumn.setCellValueFactory(new PropertyValueFactory<>("Fee_ID"));
            electricColumn.setCellValueFactory(new PropertyValueFactory<>("Prad"));
            internetColumn.setCellValueFactory(new PropertyValueFactory<>("Internet"));
            insuranceColumn.setCellValueFactory(new PropertyValueFactory<>("Ubezpieczenia"));
            salaryColumn.setCellValueFactory(new PropertyValueFactory<>("Pensje"));
            softwareColumn.setCellValueFactory(new PropertyValueFactory<>("Oprogramowanie"));
            trainingsColumn.setCellValueFactory(new PropertyValueFactory<>("Szkolenia"));
            marketingColumn.setCellValueFactory(new PropertyValueFactory<>("Marketing"));
            fuelColumn.setCellValueFactory(new PropertyValueFactory<>("Paliwo"));
            dataColumn.setCellValueFactory(new PropertyValueFactory<>("Data"));

        } catch (Exception e) {
            Logger.getLogger(OrdersDataModel.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void TrackData() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = DatabaseConnection.getConnection();

        TrackDataModelObservableList.clear();

        String queryTrack = "SELECT Track_ID, data_rozpoczecia, Order_ID, Dlugosc, Przewidywana_pogoda, sredni_stan_asfaltu, Punkt_startowy, Punkt_docelowy\n" +
                "FROM track_details";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(queryTrack);

            while (queryOutput.next()) {
                Integer queryTrack_ID = queryOutput.getInt("Track_ID");
                String queryData = queryOutput.getString("Data_rozpoczecia");
                String queryOrder_ID = queryOutput.getString("Order_ID");
                String queryDlugosc = queryOutput.getString("Dlugosc");
                String queryPrzewidywana_pogoda = queryOutput.getString("Przewidywana_pogoda");
                String querySredni_stan_asfaltu = queryOutput.getString("sredni_stan_asfaltu");
                String queryPunkt_startowy = queryOutput.getString("Punkt_startowy");
                String queryPunkt_docelowy = queryOutput.getString("Punkt_docelowy");

                TrackDataModelObservableList.add(new TrackDataModel(queryTrack_ID, queryData, queryOrder_ID, queryDlugosc, queryPrzewidywana_pogoda, querySredni_stan_asfaltu, queryPunkt_startowy, queryPunkt_docelowy));
            }

            trackTable.setItems(TrackDataModelObservableList);
            trackColumn.setCellValueFactory(new PropertyValueFactory<>("Track_ID"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("Data_rozpoczecia"));
            orderColumn.setCellValueFactory(new PropertyValueFactory<>("Order_ID"));
            lenghtColumn.setCellValueFactory(new PropertyValueFactory<>("Dlugosc"));
            weatherColumn.setCellValueFactory(new PropertyValueFactory<>("Przewidywana_pogoda"));
            asphaltColumn.setCellValueFactory(new PropertyValueFactory<>("sredni_stan_asfaltu"));
            startColumn.setCellValueFactory(new PropertyValueFactory<>("Punkt_startowy"));
            endColumn.setCellValueFactory(new PropertyValueFactory<>("Punkt_docelowy"));

        }
        catch(Exception e){
            Logger.getLogger(TrackDataModel.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            FeeData();
            TrackData();
    }
}


