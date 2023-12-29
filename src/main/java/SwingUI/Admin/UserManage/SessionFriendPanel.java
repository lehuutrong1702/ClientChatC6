package SwingUI.Admin.UserManage;

import SwingUI.Admin.Component.ViewPanel;
import SwingUI.Admin.HomeFrame;
import SwingUI.Utils.DateAndString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.model.UserActiveSession;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SessionFriendPanel {
    private JButton returnButton;
    private JPanel sessionPanel;
    private JPanel friendPanel;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SessionFriendPanel(Long id) {
        ViewPanel friendList = getFriendList(id);
        friendList.getTable().setPreferredScrollableViewportSize(new Dimension(400, 500));
        friendPanel.setLayout(new FlowLayout());
        friendPanel.add(friendList);

        ViewPanel sessionList = getSessionList(id);
        sessionList.getTable().setPreferredScrollableViewportSize(new Dimension(400, 500));
        sessionPanel.setLayout(new FlowLayout());
        sessionPanel.add(sessionList);

        returnButton.addActionListener(e -> {
            Component component = (Component) e.getSource();
            HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

            homeFrame.replace(new UserManagePanel());
        });
    }

    public ViewPanel getSessionList(Long id) {
        String[] sHeader = {"Sign in", "Sign out"};
        List<Object[]> sessions = new ArrayList<>();
        Set<UserActiveSession> activeSessions = null;
        try {
            activeSessions = UserService.getInstance().getActiveSession(id);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        for (var session : activeSessions) {
            Object[] row = {
                    DateAndString.DatetoString(session.getTimeActive(), "dd/MM/yyyy hh:mm:ss"),
                    DateAndString.DatetoString(session.getTimeLogout(), "dd/MM/yyyy hh:mm:ss")
            };
            sessions.add(row);
        }

        return new ViewPanel(sHeader, sessions, false, 10);
    }

    public ViewPanel getFriendList(Long id) {
        String[] frHeaders = {"Username", "Full name"};
        List<Object[]> friends = new ArrayList<>();
        Set<User> friendData = null;
        try {
            friendData = UserService.getInstance().getListFriend(id);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        for (var admin : friendData) {
            Object[] row = {
                    admin.getUserName(),
                    admin.getFullName()
            };
            friends.add(row);
        }

        return new ViewPanel(frHeaders, friends, false, 10);
    }
}
