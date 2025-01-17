package com.ac2425.da.clientui;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable
{
    @FXML
    private TextArea messageArea;
    @FXML
    private TextField messageInput;

    private PrintWriter out = SocketConnection.getOut();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception
            {
                while (!isCancelled())
                {
                    String message = SocketConnection.getIn().readLine();
                    if (message != null && !message.isEmpty())
                        updateMessage(message);
                }
                return null;
            }
        };

        task.messageProperty().addListener((obs, oldMessage, newMessage) -> {
            if (newMessage != null)
                displayMessage(newMessage);
        });

        task.setOnFailed(event -> {
            Throwable exception = task.getException();
            exception.printStackTrace();
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void sendMessage()
    {
        String message = messageInput.getText().trim();
        if (!message.isEmpty() && out != null)
        {
            out.println("/group " + UserData.getGroupname() + " " + message);
            messageArea.appendText("Tú: " + message + "\n");
            messageInput.clear();
        }
    }

    @FXML
    private void leaveGroup()
    {
        out.println("/leave " + UserData.getGroupname());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("select_group-view.fxml"));
        try
        {
            messageArea.getScene().setRoot(loader.load());
            SelectGroupController controller = loader.getController();
            controller.ReinitializeGroupList();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void displayMessage(String message) {
        messageArea.appendText(message + "\n");
    }
}
