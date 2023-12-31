package SwingUI.Admin.NewUser;

import SwingUI.Admin.ChoosePanel;
import SwingUI.Admin.Component.ViewPanel;
import SwingUI.Admin.HomeFrame;
import SwingUI.Admin.HomePanel;
import SwingUI.Utils.CustomDatePicker;
import SwingUI.Utils.CustomFocusListener;
import SwingUI.Utils.DateAndString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewUserPanel extends JPanel {
    JPanel sortPanel;
    ViewPanel userList;


    public NewUserPanel(Date first, Date last) {
        setSize(950, 650);
        setLayout(new BorderLayout());

        sortPanel = new JPanel();
        initSortPanel();

        add(sortPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Username", "Full name", "Created time"};
        List<Object[]> data = new ArrayList<>();
        List<User> users;
        try {
            users = UserService.getInstance().getByTime(first, last);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        for (var user: users) {
            if (user.getRole().equalsIgnoreCase("admin"))
                continue;
            Object[] row = {
                    user.getUserId(),
                    user.getUserName(),
                    user.getFullName(),
                    DateAndString.DatetoString(user.getTimeRegister(), "dd/MM/yyyy hh:mm:ss")
            };
            data.add(row);
        }
        userList = new ViewPanel(columnNames, data, false, 10);
        JPanel actions = new JPanel();

        JButton bReturn = new JButton("Return");
        bReturn.addActionListener(e -> {
            Component component = (Component) e.getSource();
            HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

            homeFrame.replace(new ChoosePanel(2));
        });

        actions.add(bReturn);

        add(userList, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);
    }

    private void initSortPanel() {
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

        JTextField name = new JTextField("Enter name");
        name.addFocusListener(new CustomFocusListener(name, "Enter name"));
        JButton bFilter = new JButton("Filter");
        bFilter.addActionListener(e -> {
            if (name.getText().equals("Enter name") || name.getText().isEmpty())
                userList.removeAllFilters();
            else
                userList.filterText(name.getText(), 2);
        });

        sortPanel.add(sortByLabel);
        sortPanel.add(sortsOptions);
        sortPanel.add(name);
        sortPanel.add(bFilter);
    }
}
