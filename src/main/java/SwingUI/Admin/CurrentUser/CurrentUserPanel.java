package SwingUI.Admin.CurrentUser;

import SwingUI.Admin.ChoosePanel;
import SwingUI.Admin.Component.ViewPanel;
import SwingUI.Admin.HomeFrame;
import SwingUI.Admin.HomePanel;

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

public class CurrentUserPanel extends JPanel {
    JPanel filterPanel;
    ViewPanel userList;

    public CurrentUserPanel() {
        setSize(950, 650);
        setLayout(new BorderLayout());

        filterPanel = new JPanel();
        initFilterPanel();

        add(filterPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Username", "Open app", "Chat to people", "Chat to groups"};
        List<Object[]> data = new ArrayList<>();
        Object[] row = {1, "Mary", 10, 2, 5};
        data.add(row);
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

    private String getSelectedValueAtCol(int col) {
        JTable table = userList.getTable();
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1)
            return null;
        return table.getModel().getValueAt(selectedRow, col).toString();
    }

    private void initFilterPanel() {
        JLabel filterByLabel = new JLabel("Filter by");
        String[] filters = {"Any", "Name", "Activities"};
        JComboBox<String> filtersOption = new JComboBox<>(filters);
        filtersOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFilterPanel();
                var selectedItem = filtersOption.getSelectedItem();
                if (filtersOption.getItemAt(1) == selectedItem) {
                    filterPanel.add(new JTextField("Input name"), 2);
                    filterPanel.add(new JButton("Filter"), 3);
                } else if (filtersOption.getItemAt(2) == selectedItem) {
                    String[] compare = {"Equal", "Less than", "Greater than"};
                    JComboBox<String> compareOption = new JComboBox<>(compare);
                    filterPanel.add(compareOption, 2);
                    filterPanel.add(new JTextField("Input number"), 3);
                    filterPanel.add(new JButton("Filter"), 4);
                }

                filterPanel.revalidate();
                filterPanel.repaint();
            }
        });

        JLabel sortByLabel = new JLabel("Sort by");
        String[] sorts = {"Name", "Date"};
        JComboBox<String> sortsOptions = new JComboBox<>(sorts);
        sortsOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var selectedItem = sortsOptions.getSelectedItem();
                if (sortsOptions.getItemAt(0) == selectedItem) {

                } else {

                }
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
