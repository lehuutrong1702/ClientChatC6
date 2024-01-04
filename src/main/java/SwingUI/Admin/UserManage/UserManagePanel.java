package SwingUI.Admin.UserManage;

import Controller.Admin.UserMange.UserManageControl;
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

public class UserManagePanel extends JPanel {
    private final JPanel filterPanel;
    private final ViewPanel userList;

    private final List<Object[]> data = new ArrayList<>();

    public UserManagePanel() {
        setSize(950, 650);
        setLayout(new BorderLayout());

        filterPanel = new JPanel();
        initFilterPanel();

        add(filterPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Username", "Full Name",
                "Birthdate", "Gender", "Email", "Online"};

        Page<User> listUser;
        try {
            listUser = UserService.getInstance().getAll();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        for (var user : listUser.getContent()) {
            if (user.getRole().equalsIgnoreCase("admin"))
                continue;

            boolean online;

            try {
                online = UserService.getInstance().isOnline(user.getUserId());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            Object[] row = {
                    user.getUserId(),
                    user.getUserName(),
                    user.getFullName(),
                    DateAndString.DatetoString(user.getBirthDay(), "dd/MM/yyyy"),
                    user.isGender() ? "Female" : "Male",
                    user.getEmail() != null ? user.getEmail() : "",
                    online
            };
            data.add(row);
        }

        userList = new ViewPanel(columnNames, data, true, 1);

        JPanel actions = new JPanel();

        UserManageControl control = new UserManageControl();
        JButton bAdd = new JButton("Add");
        bAdd.addActionListener(control);

        JButton bDel = new JButton("Delete");
        bDel.addActionListener(control);

        JButton bSnaFnd = new JButton("Sessions and friends");
        bSnaFnd.addActionListener(control);

        JButton bChgPwd = new JButton("Change password");
        bChgPwd.addActionListener(control);

        JButton bReturn = new JButton("Return");
        bReturn.addActionListener(control);

        actions.add(bAdd);
        actions.add(bDel);
        actions.add(bChgPwd);
        actions.add(bSnaFnd);
        actions.add(bReturn);

        add(userList, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);
    }

    public List<Object[]> getData() {
        return data;
    }

    public ViewPanel getUserList() {
        return userList;
    }

    public int getSelectedRow() {
        return userList.getTable().getSelectedRow();
    }

    public String getSelectedValueAtCol(int col) {
        JTable table = userList.getTable();
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1)
            return null;
        return table.getModel().getValueAt(selectedRow, col).toString();
    }

    private void initFilterPanel() {
        JLabel filterByLabel = new JLabel("Filter by");
        String[] filters = {"Any", "Name", "Username", "Status"};
        JComboBox<String> filtersOption = new JComboBox<>(filters);
        filtersOption.addActionListener(e -> {
            resetFilterPanel();
            userList.removeAllFilters();
            var selectedItem = filtersOption.getSelectedItem();
            if (filtersOption.getSelectedIndex() == 1) {
                JTextField name = new JTextField("Input name");
                name.addFocusListener(new CustomFocusListener(name, "Input name"));

                JButton bNameFilter = new JButton("Filter");
                bNameFilter.addActionListener(e1 -> {
                    if (name.getText().equals("Input name") || name.getText().isEmpty())
                        userList.removeAllFilters();
                    else
                        userList.filterText(name.getText(), 2);
                });

                filterPanel.add(name, 2);
                filterPanel.add(bNameFilter, 3);
            } else if (filtersOption.getSelectedIndex() == 2) {
                JTextField username = new JTextField("Input username");
                username.addFocusListener(new CustomFocusListener(username, "Input username"));
                filterPanel.add(username, 2);
                JButton bUsername = new JButton("Filter");
                bUsername.addActionListener(e12 -> {
                    if (username.getText().equals("Input username") || username.getText().isEmpty())
                        userList.removeAllFilters();
                    else
                        userList.filterText(username.getText(), 1);
                });
                filterPanel.add(bUsername, 3);
            } else if (filtersOption.getItemAt(3) == selectedItem) {
                String[] status = {"Any", "Active", "Offline"};
                JComboBox<String> statusFilter = new JComboBox<>(status);
                statusFilter.addActionListener(e13 -> {
                    userList.removeAllFilters();
                    if (statusFilter.getSelectedIndex() == 1) {
                        userList.filterBoolean(true, 6);
                    } else if (statusFilter.getSelectedIndex() == 2) {
                        userList.filterBoolean(false, 6);
                    }
                });
                filterPanel.add(statusFilter, 2);
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
