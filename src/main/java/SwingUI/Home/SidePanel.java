package SwingUI.Home;

import SwingUI.Home.Component.ListComponent;
import SwingUI.Home.Component.Navigator;
import SwingUI.Home.Component.Search;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatsystem.model.User;
import com.teamc6.chatsystem.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class SidePanel extends JPanel {
    Navigator chatNavigator;
    Search search;
    ListComponent list;

    public Navigator getChatNavigator() {
        return chatNavigator;
    }

    public Search getSearch() {
        return search;
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

        search = new Search();

        list = new ListComponent();
        listPanel.add(search, BorderLayout.NORTH);
        listPanel.add(list, BorderLayout.CENTER);

        add(chatNavigator, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
    }
}
