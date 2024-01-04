package SwingUI.Admin;

import SwingUI.Admin.Component.ViewPanel;
import SwingUI.Utils.CustomDatePicker;
import SwingUI.Utils.CustomFocusListener;
import SwingUI.Utils.DateAndString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.Page;
import com.teamc6.chatSystem.model.ReportSpam;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.service.ReportSpamService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpamPanel extends JPanel {
    JPanel filterPanel;
    ViewPanel userList;

    public SpamPanel() {
        setSize(950, 650);
        setLayout(new BorderLayout());

        filterPanel = new JPanel();
        initFilterPanel();

        add(filterPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Time", "Username", "Full name", "Banned"};
        List<Object[]> data = new ArrayList<>();
        Page<ReportSpam> spams;
        try {
            spams = ReportSpamService.getInstance().getAll();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        for (var spam: spams.getContent()) {
            User reported = spam.getReportUser();
            Object[] row = {
                    spam.getId(),
                    DateAndString.DatetoString(spam.getTimeReport(), "dd/MM/yyyy hh:mm:ss"),
                    reported.getUserName(),
                    reported.getFullName(),
                    !reported.isActive()
            };
            data.add(row);
        }

        userList = new ViewPanel(columnNames, data, true, 3);
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
        String[] filters = {"Any", "Name", "Date"};
        JComboBox<String> filtersOption = new JComboBox<>(filters);
        filtersOption.addActionListener(e -> {
            resetFilterPanel();
            userList.removeAllFilters();
            if (filtersOption.getSelectedIndex() == 1) {
                JTextField name = new JTextField("Enter name");
                name.addFocusListener(new CustomFocusListener(name, "Enter name"));
                JButton bFilter = new JButton("Filter");
                bFilter.addActionListener(e1 -> {
                    if (name.getText().equals("Enter name") || name.getText().isEmpty())
                        userList.removeAllFilters();
                    else
                        userList.filterText(name.getText(), 3);
                });
                filterPanel.add(name, 2);
                filterPanel.add(bFilter, 3);
            } else if (filtersOption.getSelectedIndex() == 2) {
                CustomDatePicker startPicker = new CustomDatePicker();
                CustomDatePicker endPicker = new CustomDatePicker();
                JButton bFilter = new JButton("Filter");
                bFilter.addActionListener(e1 -> {
                    userList.filterDate(startPicker.getDate(), endPicker.getDate(), 1);
                });

                filterPanel.add(new JLabel("From"), 2);
                filterPanel.add(startPicker, 3);
                filterPanel.add(new JLabel("to"), 4);
                filterPanel.add(endPicker, 5);
                filterPanel.add(bFilter, 6);
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
                sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
                userList.setSortKeys(sortKeys, 3);
            } else if (sortsOptions.getSelectedIndex() == 2) {
                List<RowSorter.SortKey> sortKeys = new ArrayList<>(6);
                sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
                userList.setSortKeys(sortKeys, 1);
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
