package SwingUI.Admin.GroupChatManage;

import SwingUI.Admin.Component.ViewPanel;
import SwingUI.Admin.HomeFrame;
import SwingUI.Admin.HomePanel;
import SwingUI.Utils.CustomFocusListener;
import SwingUI.Utils.DateAndString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.model.Page;
import com.teamc6.chatSystem.service.GroupChatService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupChatManagePanel extends JPanel {
    JPanel filterPanel;
    ViewPanel groupList;

    public GroupChatManagePanel() {
        setSize(950, 650);
        setLayout(new BorderLayout());

        filterPanel = new JPanel();
        initFilterPanel();

        add(filterPanel, BorderLayout.NORTH);

        String[] cols = {"ID", "Name", "Created date"};
        List<Object[]> data = new ArrayList<>();
        Page<GroupChat> listGroupChat;
        try {
            listGroupChat = GroupChatService.getInstance().getAll();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        for (var group : listGroupChat.getContent()) {
            Object[] row = {
                    group.getId(),
                    group.getGroupName(),
                    DateAndString.DatetoString(group.getTimeCreate())
            };
            data.add(row);
        }

        groupList = new ViewPanel(cols, data, false, 10);
        JPanel actions = new JPanel();

        JButton bInfo = new JButton("View group info");
        bInfo.addActionListener(e -> {
            String value = getSelectedValueAtCol(0);
            if (value == null)
                return;

            Component component = (Component) e.getSource();
            HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

            MemberPanel memberPanel = new MemberPanel(Long.parseLong(value));
            homeFrame.replace(memberPanel.getMainPanel());
        });

        JButton bReturn = new JButton("Return");
        bReturn.addActionListener(e -> {
            Component component = (Component) e.getSource();
            HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

            homeFrame.replace(new HomePanel(homeFrame));
        });

        actions.add(bInfo);
        actions.add(bReturn);

        add(groupList, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);
    }

    private String getSelectedValueAtCol(int col) {
        JTable table = groupList.getTable();
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1)
            return null;
        return table.getModel().getValueAt(selectedRow, col).toString();
    }

    private void initFilterPanel() {
        JLabel sortByLabel = new JLabel("Sort by");
        String[] sorts = {"Any", "Name", "Date"};
        JComboBox<String> sortsOptions = new JComboBox<>(sorts);
        sortsOptions.addActionListener(e -> {
            groupList.removeAllSortKeys();
            if (sortsOptions.getSelectedIndex() == 1) {
                List<RowSorter.SortKey> sortKeys = new ArrayList<>(6);
                sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
                groupList.setSortKeys(sortKeys);
            } else if (sortsOptions.getSelectedIndex() == 2) {
                List<RowSorter.SortKey> sortKeys = new ArrayList<>(6);
                sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
                groupList.setSortKeys(sortKeys);
            }
        });

        JTextField name = new JTextField("Enter name");
        name.addFocusListener(new CustomFocusListener(name, "Enter name"));
        JButton bFilter = new JButton("Filter");
        bFilter.addActionListener(e -> {
            if (name.getText().equals("Enter name") || name.getText().isEmpty())
                groupList.removeAllFilters();
            else
                groupList.filterText(name.getText(), 1);
        });

        filterPanel.add(sortByLabel);
        filterPanel.add(sortsOptions);
        filterPanel.add(name);
        filterPanel.add(bFilter);
    }
}

