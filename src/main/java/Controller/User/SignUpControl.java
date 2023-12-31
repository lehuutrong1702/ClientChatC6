package Controller.User;

import SwingUI.SignIn.SignInFrame;
import SwingUI.SignUp.SignUpFrame;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.service.UserService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

public class SignUpControl implements ActionListener {
    private final UserService userService;
    private final SignUpFrame SignUpFrame;

    public SignUpControl(SignUpFrame SignUpFrame) {
        userService = UserService.getInstance();
        this.SignUpFrame = SignUpFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SignUpFrame.getbSignUp()) {
            String username = SignUpFrame.getTfUsername().getText();
            String fullname =  SignUpFrame.getTfFullname().getText();
            Date dob = SignUpFrame.getDatePicker().getDate();
            String email = SignUpFrame.getTfEmail().getText();
            boolean gender = SignUpFrame.getGender().getSelection().getActionCommand().equals("Male");

            User u = new User();
            u.setEmail(email);
            u.setBirthDay(dob);
            u.setFullName(fullname);
            u.setUserName(username);
            u.setGender(gender);
            u.setRole("USER");

            try {
                UserService.getInstance().adduser(u);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

            System.out.println(u);
        } else {
            SignUpFrame.dispose();
        }

        try {
            SignUpFrame.dispose();
            SignInFrame signInFrame = new SignInFrame();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
