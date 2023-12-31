package Controller.Admin.UserMange;

import SwingUI.Admin.UserManage.AddPanel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AddUserControl implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        AddPanel addPanel = (AddPanel) ((Component)e.getSource()).getParent();
        String username = addPanel.getTfUsername().getText();
        String fullname =  addPanel.getTfFullname().getText();
        Date dob = addPanel.getDatePicker().getDate();
        String email = addPanel.getTfEmail().getText();
        boolean gender = addPanel.getGender().getSelection().getActionCommand().equals("Male");

        User u = new User();
        u.setEmail(email);
        u.setBirthDay(dob);
        u.setFullName(fullname);
        u.setUserName(username);
        u.setGender(gender);
        u.setTimeRegister(new Date());
        u.setRole("USER");

        try {
            User created = UserService.getInstance().adduser(u);
            if (created != null)
                JOptionPane.showMessageDialog(null, "New user added");
            else
                JOptionPane.showMessageDialog(null, "Add user failed");
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

    }
}
