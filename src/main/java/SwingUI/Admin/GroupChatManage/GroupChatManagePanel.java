package SwingUI.Admin.GroupChatManage;

import SwingUI.Admin.Component.ViewPanel;
import SwingUI.Admin.HomeFrame;
import SwingUI.Admin.HomePanel;
import SwingUI.Admin.UserManage.AddPanel;
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

public class GroupChatManagePanel extends JPanel {
    JPanel filterPanel;
    ViewPanel userList;

    public GroupChatManagePanel() {
        setSize(950, 650);
        setLayout(new BorderLayout());

        filterPanel = new JPanel();
        refreshFilterPanel();

        add(filterPanel, BorderLayout.NORTH);

        Date date = new Date(2003 - 1900, Calendar.NOVEMBER, 10);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        String[] cols = {"ID", "Name", "Created date"};
        List<Object[]> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Object[] row = {i, "2", strDate};
            data.add(row);
        }

        userList = new ViewPanel(cols, data, false);
        JPanel actions = new JPanel();

        JButton bInfo = new JButton("View group info");
        bInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = getSelectedValueAtCol(0);
                if (value == null)
                    return;

                Component component = (Component) e.getSource();
                HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

                MemberPanel memberPanel = new MemberPanel(Long.parseLong(value));
                homeFrame.replace(memberPanel.getMainPanel());
            }
        });

        JButton bReturn = new JButton("Return");
        bReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

                homeFrame.replace(new HomePanel(homeFrame));
            }
        });

        actions.add(bInfo);
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
    
    private void refreshFilterPanel() {
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

        filterPanel.add(sortByLabel);
        filterPanel.add(sortsOptions);
        filterPanel.add(name);
        filterPanel.add(bFilter);
    }
}

