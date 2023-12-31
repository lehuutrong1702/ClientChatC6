package Controller.User;

import SwingUI.User.HomePanel.InfoPanel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class InfoPanelControl implements ActionListener {
    private final InfoPanel infoPanel;

    public InfoPanelControl(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = infoPanel.getTfName().getText();
        String password = infoPanel.getTfPassword().getText();
        Date dob = infoPanel.getDatePicker().getDate();
        String email = infoPanel.getTfEmail().getText();

        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name and password must not be empty");
        }
        else{
            try {
                User u = UserService.getInstance().findById(Account.getInstance().getId());
                u.setFullName(name);
                u.setPassword(password);
                u.setBirthDay(dob);
                u.setEmail(email);
                User updatedUser = UserService.getInstance().updateUser(u);
                Account.getInstance().setPassWord(password);

                if (updatedUser == null)
                    JOptionPane.showMessageDialog(null, "Name and password must not be empty");
                else
                    JOptionPane.showMessageDialog(null, "Update success");
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
