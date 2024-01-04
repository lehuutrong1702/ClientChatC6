package Controller;

import SwingUI.SignIn.SignInFrame;
import SwingUI.SignUp.SignUpFrame;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

public class SignUpControl implements ActionListener {
    private final SignUpFrame SignUpFrame;

    public SignUpControl(SignUpFrame SignUpFrame) {
        this.SignUpFrame = SignUpFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SignUpFrame.getbSignUp()) {
            String username = SignUpFrame.getTfUsername().getText();
            String fullname =  SignUpFrame.getTfFullname().getText();
            Date dob = SignUpFrame.getDatePicker().getDate();
            String email = SignUpFrame.getTfEmail().getText();
            boolean gender = SignUpFrame.getGender().getSelection().getActionCommand().equalsIgnoreCase("male");

            User u = new User();
            u.setEmail(email);
            u.setBirthDay(dob);
            u.setFullName(fullname);
            u.setUserName(username);
            u.setGender(!gender);
            u.setRole("USER");
            u.setActive(true);

            try {
                User added = UserService.getInstance().adduser(u);
                if (added == null) {
                    JOptionPane.showMessageDialog(null, "Username existed");
                } else {
                    try {
                        SignUpFrame.dispose();
                        new SignInFrame();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            try {
                SignUpFrame.dispose();
                new SignInFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
