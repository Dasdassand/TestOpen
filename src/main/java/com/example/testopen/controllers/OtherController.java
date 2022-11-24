package com.example.testmaker.controller;

import com.example.testmaker.HelloApplication;
import com.example.testmaker.data.TemporaryMemory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;


public class OtherController {
    public static void generateAlert(String alertMessage, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }

    public static void openWindow(String stageName, String formName, String imageName, Button button) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Objects.requireNonNull(HelloApplication.class.getResource(formName)));
            Scene scene = new Scene(fxmlLoader.load(), -1, -1);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream(imageName))));
            stage.setTitle(stageName);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    public static boolean readQuest() throws IOException {
        File file = new File(TemporaryMemory.path, TemporaryMemory.filename + ".txt");
        if (!file.createNewFile()) {
            generateAlert("Файл с таким именем существует - невозможно создать новый", Alert.AlertType.ERROR);
            generateNameEditForm();
            return false;
        } else {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(generateTextQuest());
            fileWriter.flush();
            readAnswer();
            fileWriter.close();
            return true;
        }

    }

    private static void readAnswer() throws IOException {
        File file = new File(TemporaryMemory.path, TemporaryMemory.answerFilename + ".txt");
        file.createNewFile();
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(generateTextAnswer());
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private static void generateNameEditForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Objects.requireNonNull(HelloApplication.class.getResource("NameFileForm.fxml")));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Создание нового имени");
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("title.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static String generateTextQuest() {
        return TemporaryMemory.test.toString();
    }
    public static String generateTextAnswer(){
        return TemporaryMemory.test.answerToString();
    }
    public static File readFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enter file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return fileChooser.showOpenDialog(new Stage());
    }
}
