module com.example.zarzadzanie_firma_logistyczna {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.zarzadzanie_firma_logistyczna to javafx.fxml;
    exports com.example.zarzadzanie_firma_logistyczna;
}