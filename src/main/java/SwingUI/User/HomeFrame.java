package SwingUI.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.UserActiveSessionService;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class HomeFrame extends JFrame {
    private SidePanel sidePanel;
    private MainPanel mainPanel;

    public SidePanel getSidePanel() {
        return sidePanel;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public HomeFrame() {
        setTitle("Chat System C6");
        setLayout(new BorderLayout());

        mainPanel = new MainPanel(this);
        sidePanel = new SidePanel(this);

        add(sidePanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                try {
                    UserActiveSessionService.getInstance().endSession(Account.getInstance().getSessionID());
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
                e.getWindow().dispose();
            }
        });

        init();
    }

    public void init() {
        setSize(950,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        requestFocus();
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
