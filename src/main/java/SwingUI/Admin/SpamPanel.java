package SwingUI.Admin;

import SwingUI.Admin.Component.ViewPanel;
import SwingUI.Utils.CustomDatePicker;

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

public class SpamPanel extends JPanel {
    JPanel filterPanel;
    ViewPanel userList;

    public SpamPanel() {
        setSize(950, 650);
        setLayout(new BorderLayout());

        filterPanel = new JPanel();
        initFilterPanel();

        add(filterPanel, BorderLayout.NORTH);

        Date date = new Date(2003 - 1900, Calendar.NOVEMBER, 10);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);

        String[] columnNames = {"Time", "Username", "Banned"};
        List<Object[]> data = new ArrayList<>();
        Object[] row = {strDate, "Minh", false};
        data.add(row);
        userList = new ViewPanel(columnNames, data, true);
        JPanel actions = new JPanel();

        JButton bReturn = new JButton("Return");
        bReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

                homeFrame.replace(new HomePanel(homeFrame));
            }
        });

        actions.add(bReturn);

        add(userList, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);
    }

    private void initFilterPanel() {
        JLabel filterByLabel = new JLabel("Filter by");
        String[] filters = {"Any", "Name", "Date"};
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
                    filterPanel.add(new JLabel("From"), 2);
                    filterPanel.add(new CustomDatePicker(), 3);
                    filterPanel.add(new JLabel("to"), 4);
                    filterPanel.add(new CustomDatePicker(), 5);
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
