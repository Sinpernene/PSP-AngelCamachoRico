package com.ac2425.da.clientui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            SocketConnection.initialize(new Socket("localhost", 12345));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("set_name-view.fxml"));
            Scene scene = new Scene(loader.load());

            primaryStage.setTitle("Set your name");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e)
        {
            System.err.println("Unable to connect to the server. Please make sure the server is running and try again.");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}