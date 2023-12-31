package SwingUI.Admin.NewUser;

import SwingUI.Admin.Component.ViewPanel;
import SwingUI.Admin.HomeFrame;
import SwingUI.Admin.HomePanel;
import SwingUI.Utils.CustomDatePicker;
import SwingUI.Utils.CustomFocusListener;

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

    public NewUserPanel() {
        setSize(950, 650);
        setLayout(new BorderLayout());

        sortPanel = new JPanel();
        initSortPanel();

        add(sortPanel, BorderLayout.NORTH);

        Date date = new Date(2003 - 1900, Calendar.NOVEMBER, 10);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String strTime = dateFormat.format(date);

        String[] columnNames = {"ID", "Username", "Created time"};
        List<Object[]> data = new ArrayList<>();
        Object[] row = {1, "Minh", strTime};
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

    private void initSortPanel() {
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

        JTextField name = new JTextField("Enter name");
        name.addFocusListener(new CustomFocusListener(name, "Enter name"));
        JButton bFilter = new JButton("Filter");

        sortPanel.add(sortByLabel);
        sortPanel.add(sortsOptions);
        sortPanel.add(name);
        sortPanel.add(bFilter);
    }
}
