package Controller;

import SwingUI.Home.HomeFrame;
import SwingUI.SignIn.SignInFrame;
import SwingUI.SignUp.SignUpFrame;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatsystem.model.User;
import com.teamc6.chatsystem.properties.Account;
import com.teamc6.chatsystem.service.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SignInControl implements ActionListener {
    private final UserService userService;
    private final SignInFrame signInFrame;

    public SignInControl(SignInFrame signInFrame) {
        userService = UserService.getInstance();
        this.signInFrame = signInFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInFrame.getbSignIn()) {
            String username = signInFrame.getTfUsername().getText();
            String password = signInFrame.getPfPassword().getText();
            System.out.println(username);
            System.out.println(password);
            try {
                User u = UserService.getInstance().findByUserName(username);
                System.out.println(u);
                if(u != null && BCrypt.checkpw(password, u.getPassword())){
                    Account.getInstance().setId(u.getUserId());
                    Account.getInstance().setUserName(username);
                    Account.getInstance().setPassWord(password);
                    signInFrame.dispose();
                    new HomeFrame();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Account not found");
                }
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
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
