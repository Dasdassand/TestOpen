package com.example.testmaker.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import com.example.testmaker.data.TemporaryMemory;
import com.example.testmaker.entety.Quest;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class TestCreatedController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField AnswerFour;
    short count = 1;
    @FXML
    private TextField AnswerOne;

    @FXML
    private TextField AnswerThree;

    @FXML
    private TextField AnswerTwo;

    @FXML
    private RadioButton Four;

    @FXML
    private Button Next;

    @FXML
    private RadioButton One;

    @FXML
    private TextField Quest;

    @FXML
    private RadioButton Three;

    @FXML
    private RadioButton Two;

    @FXML
    void initialize() {
        assert AnswerFour != null : "fx:id=\"AnswerFour\" was not injected: check your FXML file 'Untitled'.";
        assert AnswerOne != null : "fx:id=\"AnswerOne\" was not injected: check your FXML file 'Untitled'.";
        assert AnswerThree != null : "fx:id=\"AnswerThree\" was not injected: check your FXML file 'Untitled'.";
        assert AnswerTwo != null : "fx:id=\"AnswerTwo\" was not injected: check your FXML file 'Untitled'.";
        assert Four != null : "fx:id=\"Four\" was not injected: check your FXML file 'Untitled'.";
        assert Next != null : "fx:id=\"Next\" was not injected: check your FXML file 'Untitled'.";
        assert One != null : "fx:id=\"One\" was not injected: check your FXML file 'Untitled'.";
        assert Quest != null : "fx:id=\"Quest\" was not injected: check your FXML file 'Untitled'.";
        assert Three != null : "fx:id=\"Three\" was not injected: check your FXML file 'Untitled'.";
        assert Two != null : "fx:id=\"Two\" was not injected: check your FXML file 'Untitled'.";
        setNull();
        TemporaryMemory.countQuest = TemporaryMemory.test.getCountQuest();
        if (TemporaryMemory.countQuest - 1 == 0) {
            Next.setText("Перейти к просмотру");
        }
        Next.setOnAction(actionEvent -> {
            if (Quest.getText().equals("") || AnswerOne.getText().equals("") || AnswerTwo.getText().equals("") ||
                    AnswerThree.getText().equals("") || AnswerFour.getText().equals(""))
                OtherController.generateAlert("Введены не все значения", Alert.AlertType.WARNING);
            else if (!(One.isSelected() || Two.isSelected() || Three.isSelected() || Four.isSelected()))
                OtherController.generateAlert("Не выбран не один вариант ответа", Alert.AlertType.WARNING);
            else if (One.isSelected() && Two.isSelected() && Three.isSelected() && Four.isSelected())
                OtherController.generateAlert("Вы пометили все варианты ответа, как верыне", Alert.AlertType.WARNING);
            else {
                TemporaryMemory.countQuest--;
                TemporaryMemory.test.getQuests().add(new Quest(count, Quest.getText(),
                        Arrays.asList(AnswerOne.getText(),
                                AnswerTwo.getText(),
                                AnswerThree.getText(),
                                AnswerFour.getText()), getAnswerIndex()));
                count++;
                setNull();
            }
            if (TemporaryMemory.countQuest - 1 == 0 && TemporaryMemory.test.getCountQuest() > 1) {
                Next.setText("Перейти к сохранению");
                OtherController.generateAlert("Остался один вопрос", Alert.AlertType.INFORMATION);
            }
            if (TemporaryMemory.countQuest == 0) {
                AtomicReference<File> dir = new AtomicReference<>();
                DirectoryChooser directoryChooser = new DirectoryChooser();
                configuringDirectoryChooser(directoryChooser);
                dir.set(directoryChooser.showDialog(new Stage()));
                System.out.println(dir.get().getAbsolutePath());

                try {
                    TemporaryMemory.path = dir.get().getAbsolutePath();
                    OtherController.readQuest();

                } catch (IOException e) {

                    throw new RuntimeException(e);
                }

                Stage stage = (Stage) Next.getScene().getWindow();
                stage.close();
                System.out.println(TemporaryMemory.test.toString());
            }

        });
    }


    private List<Integer> getAnswerIndex() {
        List<Integer> tmp = new ArrayList<>();
        if (One.isSelected()) {
            tmp.add(0);
        }
        if (Two.isSelected()) {
            tmp.add(1);
        }
        if (Three.isSelected()) {
            tmp.add(2);
        }
        if (Four.isSelected()) {
            tmp.add(3);
        }
        return tmp;
    }

    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        // Set title for DirectoryChooser
        directoryChooser.setTitle("Select Some Directories");

        // Set Initial Directory
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    private void setNull() {
        One.setSelected(false);
        Two.setSelected(false);
        Three.setSelected(false);
        Four.setSelected(false);
        Quest.setText("");
        AnswerOne.setText("");
        AnswerTwo.setText("");
        AnswerThree.setText("");
        AnswerFour.setText("");
    }
}
