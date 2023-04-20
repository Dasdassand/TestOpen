package com.example.testopen.db;

import com.example.testopen.client.Network;
import com.example.testopen.controllers.AuthController;
import com.example.testopen.entety.Answers;
import com.example.testopen.entety.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Button;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class TemporaryMemory {
    public static String testS;
    public static Button button;
    public static boolean flag = false;
    public static boolean testFlag = false;
    public static Test test;
    public static Network network;
    public static List<Answers> answers = new ArrayList<>();
    public static AuthController controller = new AuthController();
    public static void makeTest() throws IOException {
        try {
            test =  new ObjectMapper().readValue(testS,Test.class);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}