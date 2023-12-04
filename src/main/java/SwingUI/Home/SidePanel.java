package SwingUI.Home;

import SwingUI.Home.Component.Navigator;
import SwingUI.Home.Component.Search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SidePanel extends JPanel {
    public SidePanel() {
        setPreferredSize(new Dimension(200, 650));
        setLayout(new BorderLayout());

        Navigator chatNavigator = new Navigator();

        JPanel listPanel = new JPanel();
        listPanel.setBackground(Color.green);
        listPanel.setLayout(new BorderLayout());

        Search search = new Search();
        listPanel.add(search, BorderLayout.NORTH);

        add(chatNavigator, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
    }
}
