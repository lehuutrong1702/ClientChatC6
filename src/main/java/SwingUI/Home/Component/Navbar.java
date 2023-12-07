package SwingUI.Home.Component;

import SwingUI.Utils.RoundedCornerBorder;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class Navbar extends JPanel {
    public Navbar() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(750, 50));

        NavSearch navSearch = new NavSearch();

        UserControl userControl = new UserControl();

        add(navSearch, BorderLayout.CENTER);
        add(userControl, BorderLayout.EAST);
    }
}
