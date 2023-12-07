package SwingUI.Home;

import SwingUI.Home.Component.MessagePanel;
import SwingUI.Home.Component.Navbar;
import org.example.Main;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(750, 650));

        Navbar navbar = new Navbar();
        MessagePanel messagePanel = new MessagePanel();

        add(navbar, BorderLayout.NORTH);
        add(messagePanel, BorderLayout.CENTER);
    }
}
