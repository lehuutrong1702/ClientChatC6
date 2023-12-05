package SwingUI.Home;

import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {
    public HomeFrame() {
        setTitle("Chat System C6");
        setLayout(new BorderLayout());
        SidePanel sidePanel = new SidePanel();
        JPanel centerPanel = new JPanel();

        add(sidePanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
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
