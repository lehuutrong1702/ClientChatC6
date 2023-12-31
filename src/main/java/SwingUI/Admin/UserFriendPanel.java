package SwingUI.Admin;

import SwingUI.Admin.Component.ViewPanel;
import SwingUI.Utils.CustomFocusListener;
import SwingUI.Utils.DateAndString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.Page;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserFriendPanel extends JPanel {
    JPanel filterPanel;
    ViewPanel userList;

    public UserFriendPanel() {
        setSize(950, 650);
        setLayout(new BorderLayout());

        filterPanel = new JPanel();
        initFilterPanel();

        add(filterPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Username", "Full name", "Created time", "Friends", "Friends of friends"};
        List<Object[]> data = new ArrayList<>();
        Page<User> users;
        try {
            users = UserService.getInstance().getAll();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        for (var user : users.getContent()) {
            if (user.getRole().equalsIgnoreCase("admin"))
                continue;

            int friendCount = 0;
            int fofCount = 0;
            try {
                Set<User> friends = UserService.getInstance().getListFriend(user.getUserId());
                friendCount = friends.size();

                for (var friend : friends) {
                    Set<User> friendsOfFriends = UserService.getInstance().getListFriend(friend.getUserId());
                    fofCount += friendsOfFriends.size();
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            Object[] row = {
                    user.getUserId(),
                    user.getUserName(),
                    user.getFullName(),
                    DateAndString.DatetoString(user.getTimeRegister(), "dd/MM/yyyy hh:mm:ss"),
                    friendCount,
                    fofCount
            };
            data.add(row);
        }
        userList = new ViewPanel(columnNames, data, false, 10);
        JPanel actions = new JPanel();

        JButton bReturn = new JButton("Return");
        bReturn.addActionListener(e -> {
            Component component = (Component) e.getSource();
            HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

            homeFrame.replace(new HomePanel(homeFrame));
        });

        actions.add(bReturn);

        add(userList, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);
    }

    private void initFilterPanel() {
        JLabel filterByLabel = new JLabel("Filter by");
        String[] filters = {"Any", "Name", "Total friend"};
        JComboBox<String> filtersOption = new JComboBox<>(filters);
        filtersOption.addActionListener(e -> {
            resetFilterPanel();
            if (filtersOption.getSelectedIndex() == 1) {
                JTextField name = new JTextField("Enter name");
                name.addFocusListener(new CustomFocusListener(name, "Enter name"));
                JButton bFilter = new JButton("Filter");
                bFilter.addActionListener(e1 -> {
                    if (name.getText().equals("Enter name") || name.getText().isEmpty())
                        userList.removeAllFilters();
                    else
                        userList.filterText(name.getText(), 2);
                });
                filterPanel.add(name, 2);
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
                                4
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
        String[] sorts = {"Any", "Name", "Date"};
        JComboBox<String> sortsOptions = new JComboBox<>(sorts);
        sortsOptions.addActionListener(e -> {
            userList.removeAllSortKeys();
            if (sortsOptions.getSelectedIndex() == 1) {
                List<RowSorter.SortKey> sortKeys = new ArrayList<>(6);
                sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
                userList.setSortKeys(sortKeys, 2);
            } else if (sortsOptions.getSelectedIndex() == 2) {
                List<RowSorter.SortKey> sortKeys = new ArrayList<>(6);
                sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
                userList.setSortKeys(sortKeys, 3);
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
