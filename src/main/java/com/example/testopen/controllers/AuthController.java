package com.example.testmaker.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.testmaker.data.TemporaryMemory;
import com.example.testmaker.security.tmpSecurity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        assert But != null : "fx:id=\"But\" was not injected: check your FXML file 'Untitled'.";
        assert Login != null : "fx:id=\"Login\" was not injected: check your FXML file 'Untitled'.";
        assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'Untitled'.";
        Login.setText("");
        Password.setText("");
        But.setOnAction(actionEvent -> {
            if (Login.getText().equals("") || Password.getText().equals(""))
                OtherController.generateAlert("Не введены данные", Alert.AlertType.CONFIRMATION);
            else {
                TemporaryMemory.user.setLogin(Login.getText());
                TemporaryMemory.user.setPassword(Password.getText());
                if (tmpSecurity.checkData(TemporaryMemory.user))
                    OtherController.openWindow("Панель админестратора","AdminForm.fxml","title.png",But);
                 else {
                    OtherController.generateAlert("Введены не верные данные", Alert.AlertType.WARNING);
                    Login.setText("");
                    Password.setText("");
                }
            }
        });

    }


}
