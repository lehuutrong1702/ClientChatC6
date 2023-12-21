package SwingUI.Admin.UserManage;

import SwingUI.Admin.HomeFrame;
import SwingUI.Admin.HomePanel;
import SwingUI.SignIn.SignInFrame;
import SwingUI.User.HomePanel.InfoPanel;
import SwingUI.User.MainPanel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserManagePanel extends JPanel {
    JPanel filterPanel;
    ViewPanel userList;
    public UserManagePanel() {
        setSize(950, 650);
        setLayout(new BorderLayout());

        filterPanel = new JPanel();
        refreshFilterPanel();

        add(filterPanel, BorderLayout.NORTH);

        userList = new ViewPanel();
        JPanel actions = new JPanel();

        JButton bAdd = new JButton("Add");
        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

                homeFrame.replace(new AddPanel());
            }
        });
        JButton bSnaFnd = new JButton("Sessions and friends");
        bSnaFnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = getSelectedValueAtCol(0);
                if (value == null)
                    return;
                Component component = (Component) e.getSource();
                HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);


                SessionFriendPanel panel = new SessionFriendPanel(Long.parseLong(value));
                homeFrame.replace(panel.getMainPanel());
            }
        });
        JButton bChgPwd = new JButton("Change password");
        bChgPwd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userList.getTable().getSelectedRow() == -1)
                    return;

                //lay username hoac id, neu muon lay id thi columnindex=0
                String value = getSelectedValueAtCol(1);
                String newPassword = JOptionPane.showInputDialog("Enter new password for " + value);

                if (newPassword != null) {
                    //update password
                }
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

        actions.add(bAdd);
        actions.add(bChgPwd);
        actions.add(bSnaFnd);
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
        filterPanel.removeAll();

        JLabel filterByLabel = new JLabel("Filter by");
        String[] filters = {"Any","Name","Username","Status"};
        JComboBox<String> filtersOption = new JComboBox<>(filters);
        filtersOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshFilterPanel();
                var selectedItem = filtersOption.getSelectedItem();
                if (filtersOption.getItemAt(1) == selectedItem) {
                    filterPanel.add(new JTextField("Input name"), 2);
                    filterPanel.add(new JButton("Filter"), 3);
                    filterPanel.revalidate(); filterPanel.repaint();
                } else if (filtersOption.getItemAt(2) == selectedItem) {
                    filterPanel.add(new JTextField("Input username"), 2);
                    filterPanel.add(new JButton("Filter"), 3);
                    filterPanel.revalidate(); filterPanel.repaint();
                } else if (filtersOption.getItemAt(3) == selectedItem) {
                    String[] status = {"Active","Offline"};
                    JComboBox<String> statusFilter = new JComboBox<>(status);
                    filterPanel.add(statusFilter, 2);
                    filterPanel.revalidate(); filterPanel.repaint();
                }
            }
        });

        JLabel sortByLabel = new JLabel("Sort by");
        String[] sorts = {"Name","Date"};
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
}
