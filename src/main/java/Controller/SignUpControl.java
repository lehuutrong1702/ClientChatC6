package Controller;

import SwingUI.SignIn.SignInFrame;
import SwingUI.SignUp.SignUpFrame;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatsystem.model.User;
import com.teamc6.chatsystem.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SignUpControl implements ActionListener {
    private UserService userService;
    private SignUpFrame SignUpFrame;

    public SignUpControl(SignUpFrame SignUpFrame) {
        userService = UserService.getInstance();
        this.SignUpFrame = SignUpFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SignUpFrame.getbSignUp()) {
            //do sth here
        }
        else {
            try {
                SignUpFrame.dispose();
                SignInFrame signInFrame = new SignInFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
