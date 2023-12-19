package SwingUI.User;

import SwingUI.User.Component.NavSearch;
import SwingUI.User.Component.UserControl;

import javax.swing.*;
import java.awt.*;

public class Navbar extends JPanel {
    public Navbar(MainPanel mainPanel) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(750, 50));

        NavSearch navSearch = new NavSearch(mainPanel);
        UserControl userControl = new UserControl(mainPanel);

        add(navSearch, BorderLayout.CENTER);
        add(userControl, BorderLayout.EAST);
    }
}
