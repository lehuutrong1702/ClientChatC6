import SwingUI.SignIn.SignInFrame;
import com.teamc6.chatsystem.properties.Account;
import com.teamc6.chatsystem.service.UserService;

import javax.swing.*;
import java.io.IOException;

public class ChatApp {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {

                    new SignInFrame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
