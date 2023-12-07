package SwingUI.Home;

import SwingUI.Home.Component.ListComponent;
import SwingUI.Home.Component.Navigator;
import SwingUI.Home.Component.SideSearch;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    Navigator chatNavigator;
    SideSearch sideSearch;
    ListComponent list;

    public Navigator getChatNavigator() {
        return chatNavigator;
    }

    public SideSearch getSearch() {
        return sideSearch;
    }

    public ListComponent getList() {
        return list;
    }

    public SidePanel() {
        setPreferredSize(new Dimension(200, 650));
        setLayout(new BorderLayout());

        chatNavigator = new Navigator(this);

        JPanel listPanel = new JPanel();
        listPanel.setBackground(Color.green);
        listPanel.setLayout(new BorderLayout());

        sideSearch = new SideSearch(this);

        list = new ListComponent();
        listPanel.add(sideSearch, BorderLayout.NORTH);
        listPanel.add(list, BorderLayout.CENTER);

        add(chatNavigator, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
    }
}
