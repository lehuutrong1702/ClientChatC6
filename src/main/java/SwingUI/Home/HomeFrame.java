package SwingUI.Home;

import org.example.Main;

import javax.swing.*;
import java.awt.*;

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

        mainPanel = new MainPanel();
        sidePanel = new SidePanel(this);

        add(sidePanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
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
