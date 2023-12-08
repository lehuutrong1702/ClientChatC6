package SwingUI.Home;

import SwingUI.Home.Component.NavSearch;
import SwingUI.Home.Component.UserControl;
import SwingUI.Home.MainPanel;
import SwingUI.Utils.RoundedCornerBorder;
import org.example.Main;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
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
