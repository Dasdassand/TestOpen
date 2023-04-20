module com.example.testopen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires static lombok;
    requires io.netty.all;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens com.example.testopen.entety to com.fasterxml.jackson.databind, lombok;
    opens com.example.testopen to javafx.fxml;
    exports com.example.testopen;
    exports com.example.testopen.entety;
    exports com.example.testopen.controllers;
    opens com.example.testopen.controllers to javafx.fxml;



}