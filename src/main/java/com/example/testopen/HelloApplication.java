package com.example.testopen;

import com.example.testopen.db.TemporaryMemory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AuthForm.fxml"));
        fxmlLoader.setController(TemporaryMemory.controller);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Аунтефекация");
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("title.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        TemporaryMemory.network.close();
    }
}