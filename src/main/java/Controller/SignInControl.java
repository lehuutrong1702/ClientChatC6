package Controller;

import SwingUI.Home.HomeFrame;
import SwingUI.SignIn.SignInFrame;
import SwingUI.SignUp.SignUpFrame;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatsystem.model.User;
import com.teamc6.chatsystem.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SignInControl implements ActionListener {
    private UserService userService;
    private SignInFrame signInFrame;

    public SignInControl(SignInFrame signInFrame) {
        userService = UserService.getInstance();
        this.signInFrame = signInFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInFrame.getbSignIn()) {
            //do sth here
            signInFrame.dispose();
            new HomeFrame();
        }
        else {
            signInFrame.dispose();
            try {
                new SignUpFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
