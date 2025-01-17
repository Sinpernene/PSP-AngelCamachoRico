package com.ac2425.da.clientui;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectGroupController implements Initializable
{
    @FXML
    ListView<String> groupListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        updateGroupList();
    }

    private void updateGroupList() {
        Task<String> task = new Task<>() {
            @Override
            protected String call() throws Exception {
                return SocketConnection.getIn().readLine(); // Leer línea completa
            }
        };

        task.setOnSucceeded(event -> {
            String groupList = task.getValue();
            if (groupList != null && !groupList.isEmpty()) {
                System.out.println("Grupos recibidos: " + groupList);
                String[] groups = groupList.replace(";", "").split(",");
                groupListView.getItems().clear();
                for (String group : groups) {
                    groupListView.getItems().add(group.trim());
                }
            } else {
                System.out.println("No se recibieron grupos.");
            }
        });

        task.setOnFailed(event -> {
            Throwable exception = task.getException();
            exception.printStackTrace();
        });

        var out = SocketConnection.getOut();
        System.out.println("Solicitando grupos al servidor...");
        out.println("/groups");
        out.flush();

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void createGroup()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("set_groupname-view.fxml"));
        Scene scene = groupListView.getScene();
        try
        {
            scene.setRoot(loader.load());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
