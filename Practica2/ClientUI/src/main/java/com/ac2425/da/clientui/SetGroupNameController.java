package com.ac2425.da.clientui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

public class SetGroupNameController
{
    @FXML
    private TextField groupnameField;

    public void submitGroupName()
    {
        String groupname = groupnameField.getText().trim();
        UserData.setGroupname(groupname);
        SocketConnection.getOut().println("/join " + groupname);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("client-view.fxml"));
        try
        {
            groupnameField.getScene().setRoot(loader.load());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
