package com.example.zarzadzanie_firma_logistyczna;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//główna klasa uruchamiająca aplikacje

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //tworzy instancje FXMLLoader aby załadować plik fxml
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //tworzy nową scenę z pliku fxml
        Scene scene = new Scene(fxmlLoader.load(), 1080, 540);
        //ustawia tytuł okna
        stage.setTitle("Managment Application");
        //ustawia scenę
        stage.setScene(scene);
        //pokazuje okno
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}