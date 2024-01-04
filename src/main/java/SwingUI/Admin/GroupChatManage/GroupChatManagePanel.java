package SwingUI.Admin.GroupChatManage;

import Controller.Admin.GroupChatManage.GroupChatMangeControl;
import SwingUI.Admin.Component.ViewPanel;
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
            if (group.getTimeCreate() == null)
                continue;

            Object[] row = {
                    group.getId(),
                    group.getGroupName(),
                    DateAndString.DatetoString(group.getTimeCreate(), "dd/MM/yyyy hh:mm:ss")
            };
            data.add(row);
        }


        groupList = new ViewPanel(cols, data, false, 10);
        JPanel actions = new JPanel();
        GroupChatMangeControl control = new GroupChatMangeControl();
        JButton bInfo = new JButton("View group info");
        bInfo.addActionListener(control);

        JButton bReturn = new JButton("Return");
        bReturn.addActionListener(control);

        actions.add(bInfo);
        actions.add(bReturn);

        add(groupList, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);
    }

    public String getSelectedValueAtCol(int col) {
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
                groupList.setSortKeys(sortKeys, 1);
            } else if (sortsOptions.getSelectedIndex() == 2) {
                List<RowSorter.SortKey> sortKeys = new ArrayList<>(6);
                sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
                groupList.setSortKeys(sortKeys, 2);
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

