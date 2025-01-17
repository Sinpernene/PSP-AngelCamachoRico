package com.ac2425.da.clientui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.PrintWriter;

public class ClientController
{
    @FXML
    private TextArea messageArea;
    @FXML
    private TextField messageInput;
    @FXML
    private Button sendButton;

    private PrintWriter out = SocketConnection.getOut();

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

    public void displayMessage(String message) {
        messageArea.appendText(message + "\n");
    }
}
