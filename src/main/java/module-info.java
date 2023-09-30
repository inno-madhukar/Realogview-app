module com.example.dmmguivisible {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires org.apache.pdfbox;

    requires activation;
    requires org.apache.poi.poi;
    requires com.fazecast.jSerialComm;
    requires java.sql;
    requires java.mail;
    requires javafx.swing;
//    requires java.prefs;

    opens com.example.dmmguivisible to javafx.fxml;
    exports com.example.dmmguivisible;
}