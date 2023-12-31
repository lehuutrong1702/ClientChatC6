package SwingUI.Admin;

import SwingUI.Admin.UserManage.UserManagePanel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.UserActiveSession;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.UserActiveSessionService;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomeFrame extends JFrame {
    public HomeFrame() {
        setTitle("Chat System C6 Management");
        setSize(950, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        add(new HomePanel(this));

        requestFocus();
    }

    public void replace(JPanel panel) {
        getContentPane().remove(0);
        add(panel);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomeFrame();
            }
        });
    }
}
