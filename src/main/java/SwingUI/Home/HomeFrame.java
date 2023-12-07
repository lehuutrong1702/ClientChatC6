package SwingUI.Home;

import org.example.Main;

import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {
    public HomeFrame() {
        setTitle("Chat System C6");
        setLayout(new BorderLayout());
        SidePanel sidePanel = new SidePanel();
        MainPanel mainPanel = new MainPanel();

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
        new HomeFrame();
    }
}
