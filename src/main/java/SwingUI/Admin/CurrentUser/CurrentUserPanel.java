package SwingUI.Admin.CurrentUser;

import SwingUI.Admin.ChoosePanel;
import SwingUI.Admin.Component.ViewPanel;
import SwingUI.Admin.HomeFrame;
import SwingUI.Utils.CustomFocusListener;
import SwingUI.Utils.DateAndString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.model.UserActiveSession;
import com.teamc6.chatSystem.service.UserActiveSessionService;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrentUserPanel extends JPanel {
    JPanel filterPanel;
    ViewPanel userList;

    public CurrentUserPanel(Date first, Date last) {
        setSize(950, 650);
        setLayout(new BorderLayout());

        filterPanel = new JPanel();
        initFilterPanel();

        add(filterPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Username", "Created time", "Open app", "Chat to people", "Chat to groups", "Total activities"};
        List<Object[]> data = new ArrayList<>();
        List<UserActiveSession> sessions;
        try {
            sessions = UserActiveSessionService.getInstance().getByTime(first, last);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Map<User, Long> curUsers = sessions.stream()
                .collect(Collectors.groupingBy(UserActiveSession::getSessionUser, Collectors.counting()));

        for (var user: curUsers.keySet()) {
            if (user.getRole().equalsIgnoreCase("admin"))
                continue;
            List<Integer> counts;
            try {
                counts = UserService.getInstance().getChatByTimeSend(first, last, user.getUserName());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            Object[] row = {
                    user.getUserId(),
                    user.getUserName(),
                    DateAndString.DatetoString(user.getTimeRegister(), "dd/MM/yyyy hh:mm:ss"),
                    curUsers.get(user),
                    counts.get(0),
                    counts.get(1),
                    curUsers.get(user) +  counts.get(0) + counts.get(1)
            };
            data.add(row);
        }
        userList = new ViewPanel(columnNames, data, false, 10);
        JPanel actions = new JPanel();

        JButton bReturn = new JButton("Return");
        bReturn.addActionListener(e -> {
            Component component = (Component) e.getSource();
            HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

            homeFrame.replace(new ChoosePanel(1));
        });

        actions.add(bReturn);

        add(userList, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);
    }

    private void initFilterPanel() {
        JLabel filterByLabel = new JLabel("Filter by");
        String[] filters = {"Any", "Username", "Activities"};
        JComboBox<String> filtersOption = new JComboBox<>(filters);
        filtersOption.addActionListener(e -> {
            resetFilterPanel();
            if (filtersOption.getSelectedIndex() == 1) {
                JTextField username = new JTextField("Enter username");
                username.addFocusListener(new CustomFocusListener(username, "Enter username"));
                JButton bFilter = new JButton("Filter");
                bFilter.addActionListener(e1 -> {
                    if (username.getText().equals("Enter name") || username.getText().isEmpty())
                        userList.removeAllFilters();
                    else
                        userList.filterText(username.getText(), 1);
                });
                filterPanel.add(username, 2);
                filterPanel.add(bFilter, 3);
            } else if (filtersOption.getSelectedIndex() == 2) {
                String[] compare = {"Less than", "Equal", "Greater than"};
                JComboBox<String> compareOption = new JComboBox<>(compare);
                JTextField number = new JTextField("Enter number");
                number.addFocusListener(new CustomFocusListener(number, "Enter number"));
                JButton bFilter = new JButton("Filter");
                bFilter.addActionListener(e2 -> {
                    if (number.getText().equals("Enter number") || number.getText().isEmpty())
                        userList.removeAllFilters();
                    else {
                        userList.filterNumber(
                                compareOption.getSelectedIndex(),
                                Integer.parseInt(number.getText()),
                                6
                        );
                    }
                });
                filterPanel.add(compareOption, 2);
                filterPanel.add(number, 3);
                filterPanel.add(bFilter, 4);
            }

            filterPanel.revalidate();
            filterPanel.repaint();
        });

        JLabel sortByLabel = new JLabel("Sort by");
        String[] sorts = {"Any", "Username", "Date"};
        JComboBox<String> sortsOptions = new JComboBox<>(sorts);
        sortsOptions.addActionListener(e -> {
            userList.removeAllSortKeys();
            if (sortsOptions.getSelectedIndex() == 1) {
                List<RowSorter.SortKey> sortKeys = new ArrayList<>(6);
                sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
                userList.setSortKeys(sortKeys, 1);
            } else if (sortsOptions.getSelectedIndex() == 2) {
                List<RowSorter.SortKey> sortKeys = new ArrayList<>(6);
                sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
                userList.setSortKeys(sortKeys, 2);
            }
        });

        filterPanel.add(filterByLabel);
        filterPanel.add(filtersOption);
        filterPanel.add(sortByLabel);
        filterPanel.add(sortsOptions);
    }

    private void resetFilterPanel() {
        int panelItemSize = filterPanel.getComponentCount();
        for (int i = 0; i < panelItemSize - 4; i++) {
            filterPanel.remove(2);
        }
    }
}
