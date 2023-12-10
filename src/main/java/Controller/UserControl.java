package Controller;

import SwingUI.Home.HomePanel.InfoPanel;
import SwingUI.Home.MainPanel;
import SwingUI.SignIn.SignInFrame;
import org.example.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserControl implements ActionListener {

    private final SwingUI.Home.Component.UserControl userControl;

    public UserControl(SwingUI.Home.Component.UserControl userControl) {
        this.userControl = userControl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<String> userControlList = userControl.getUserControlList();
        MainPanel mainPanel = userControl.getMainPanel();
        var selectedItem =  userControlList.getSelectedItem();
        if (userControlList.getItemAt(0) == selectedItem) {
            mainPanel.replace(new InfoPanel());
        } else {
            mainPanel.getHomeFrame().dispose();
            try {
                new SignInFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
