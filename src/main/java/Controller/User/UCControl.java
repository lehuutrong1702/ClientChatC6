package Controller.User;

import SwingUI.User.Component.UserControl;
import SwingUI.User.HomePanel.InfoPanel;
import SwingUI.User.MainPanel;
import SwingUI.SignIn.SignInFrame;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.UserActiveSessionService;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UCControl implements ActionListener {

    private final UserControl userControl;

    public UCControl(UserControl userControl) {
        this.userControl = userControl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<String> userControlList = userControl.getUserControlList();
        MainPanel mainPanel = userControl.getMainPanel();
        if (userControlList.getSelectedIndex() == 0) {
            //                User u = UserService.getInstance().findByUserName(Account.getInstance().getUserName());
            mainPanel.replace(new InfoPanel());
        } else {
            mainPanel.getHomeFrame().dispose();
            try {
                UserActiveSessionService.getInstance().endSession(Account.getInstance().getSessionID());
                new SignInFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
