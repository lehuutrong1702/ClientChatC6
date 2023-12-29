package SwingUI.User;

import SwingUI.User.Component.ListComponent;
import SwingUI.User.Component.SideNav;
import SwingUI.User.Component.SideSearch;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    SideNav chatSideNav;
    SideSearch sideSearch;
    ListComponent list;
    HomeFrame homeFrame;

    public HomeFrame getHomeFrame() {
        return homeFrame;
    }

    public SideNav getChatNavigator() {
        return chatSideNav;
    }

    public SideSearch getSearch() {
        return sideSearch;
    }

    public ListComponent getList() {
        return list;
    }

    public SidePanel(HomeFrame homeFrame) {
        this.homeFrame = homeFrame;
        setPreferredSize(new Dimension(200, 650));
        setLayout(new BorderLayout());
        chatSideNav = new SideNav(this);

        JPanel listPanel = new JPanel();
        listPanel.setBackground(Color.green);
        listPanel.setLayout(new BorderLayout());

        sideSearch = new SideSearch(this);
        list = new ListComponent(this);

        listPanel.add(sideSearch, BorderLayout.NORTH);
        listPanel.add(list, BorderLayout.CENTER);

        add(chatSideNav, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
    }
}
