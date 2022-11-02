module com.hemsteam.hems {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.jetbrains.annotations;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.hemsteam.hems to javafx.fxml;
    exports com.hemsteam.hems;
    exports com.hemsteam.hems.controllers;
    opens com.hemsteam.hems.controllers to javafx.fxml;
    opens com.hemsteam.hems.datamodels to javafx.base;
}