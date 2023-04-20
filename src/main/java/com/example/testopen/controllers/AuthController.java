package com.example.testopen.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import com.example.testopen.client.Network;
import com.example.testopen.db.TemporaryMemory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static com.example.testopen.db.TemporaryMemory.button;
import static com.example.testopen.db.TemporaryMemory.network;

public class AuthController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button But;

    @FXML
    private TextField Login;

    @FXML
    private TextField Password;

    @FXML
    void initialize() {
        network = new Network();
        assert But != null : "fx:id=\"But\" was not injected: check your FXML file 'Untitled'.";
        assert Login != null : "fx:id=\"Login\" was not injected: check your FXML file 'Untitled'.";
        assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'Untitled'.";
        Login.setText("");
        Password.setText("");
        TemporaryMemory.button = But;
        But.setOnAction(actionEvent -> {
            if (Login.getText().equals("") || Password.getText().equals(""))
                OtherController.generateAlert("Не введены данные", Alert.AlertType.CONFIRMATION);
            else {
                network.sendMessage("name " + Login.getText() + " " + "password " + Password.getText());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (!TemporaryMemory.flag) {
                    OtherController.generateAlert("Введены не верные данные", Alert.AlertType.WARNING);
                    Login.setText("");
                    Password.setText("");
                    Login.requestFocus();
                    Password.requestFocus();
                }
            }
        });

    }

    public  void open() {
        OtherController.openWindow("Тест", "OpenTestWindow.fxml", "title.png", But);
    }

}
