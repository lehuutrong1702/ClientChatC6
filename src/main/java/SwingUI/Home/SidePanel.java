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
    public SidePanel() {
        setPreferredSize(new Dimension(200, 650));
        setLayout(new BorderLayout());

        Navigator chatNavigator = new Navigator();

        JPanel listPanel = new JPanel();
        listPanel.setBackground(Color.green);
        listPanel.setLayout(new BorderLayout());

        Search search = new Search();

        ListComponent list = new ListComponent();
        listPanel.add(search, BorderLayout.NORTH);
        listPanel.add(list, BorderLayout.CENTER);

        add(chatNavigator, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
    }
}
