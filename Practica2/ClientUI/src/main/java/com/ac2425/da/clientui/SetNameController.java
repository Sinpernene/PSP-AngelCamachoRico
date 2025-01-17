package com.ac2425.da.clientui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class SetNameController
{
    @FXML
    private TextField usernameField;

    @FXML
    private void submitName()
    {
        String username = usernameField.getText().trim();
        if (!username.isEmpty())
        {
            SocketConnection.getOut().println("/username " + username);
            UserData.setUsername(username);
            Scene scene = usernameField.getScene();
            try
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("select_group-view.fxml"));
                scene.setRoot(loader.load());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


}
