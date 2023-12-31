package SwingUI.Admin;

import SwingUI.Admin.Component.ViewPanel;
import SwingUI.Utils.DateAndString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.model.UserActiveSession;
import com.teamc6.chatSystem.service.UserActiveSessionService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionPanel extends JPanel {
    ViewPanel sessionList;

    public SessionPanel() {
        setSize(950, 650);
        setLayout(new BorderLayout());

        String[] columnNames = {"Time", "Username", "Full Name"};
        List<UserActiveSession> activeSessions;
        try {
            activeSessions = UserActiveSessionService.getInstance().getAll();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        List<Object[]> data = new ArrayList<>();
        for (var session: activeSessions) {
            User u = session.getSessionUser();
            if (u.getRole().equalsIgnoreCase("admin"))
                continue;

            Object[] row = {
                    DateAndString.DatetoString(session.getTimeActive(), "dd/MM/yyyy hh:mm:ss"),
                    u.getUserName(),
                    u.getFullName()
            };
            data.add(row);
        }
        sessionList = new ViewPanel(columnNames, data, false, 10);
        sessionList.getTable().setPreferredScrollableViewportSize(new Dimension(900, 550));

        JPanel actions = new JPanel();
        JButton bReturn = new JButton("Return");
        bReturn.addActionListener(e -> {
            Component component = (Component) e.getSource();
            HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

            homeFrame.replace(new HomePanel(homeFrame));
        });
        actions.add(bReturn);

        add(sessionList, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);
    }
}
