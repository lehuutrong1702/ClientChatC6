package Controller;

import SwingUI.SignIn.SignInFrame;
import SwingUI.SignUp.SignUpFrame;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.model.UserActiveSession;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.UserActiveSessionService;
import com.teamc6.chatSystem.service.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SignInControl implements ActionListener {
    private final SignInFrame signInFrame;

    public SignInControl(SignInFrame signInFrame) {
        this.signInFrame = signInFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInFrame.getbSignIn()) {
            String username = signInFrame.getTfUsername().getText();
            String password = signInFrame.getPfPassword().getText();
            try {
                User u = UserService.getInstance().findByUserName(username);

                if (u != null && BCrypt.checkpw(password, u.getPassword())) {
                    if (!u.isActive()) {
                        JOptionPane.showMessageDialog(null, "Account has been banned!");
                        return;
                    }

                    Account curUser = Account.getInstance();
                    curUser.setId(u.getUserId());
                    curUser.setUserName(username);
                    curUser.setPassWord(password);
                    UserActiveSession activeSession = UserActiveSessionService.getInstance().createSession(u.getUserId());
                    curUser.setSessionID(activeSession.getId());

                    curUser.setSelf(u);
                    signInFrame.dispose();
                    if (u.getRole().equals("USER"))
                        new SwingUI.User.HomeFrame();
                    else
                        new SwingUI.Admin.HomeFrame();
                } else {
                    JOptionPane.showMessageDialog(null, "Account not found");
                }
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            signInFrame.dispose();
            try {
                new SignUpFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
