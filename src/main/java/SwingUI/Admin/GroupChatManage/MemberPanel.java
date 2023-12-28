package SwingUI.Admin.GroupChatManage;

import SwingUI.Admin.Component.ViewPanel;
import SwingUI.Admin.HomeFrame;
import SwingUI.Admin.UserManage.UserManagePanel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.service.GroupChatService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MemberPanel {
    private JButton returnButton;
    private JPanel memberPanel;
    private JPanel adminPanel;
    private JPanel mainPanel;

    public MemberPanel(Long id) {
        ViewPanel memberList = getMemberList(id);
        memberList.getTable().setPreferredScrollableViewportSize(new Dimension(400, 500));
        memberPanel.setLayout(new FlowLayout());
        memberPanel.add(memberList);

        ViewPanel adminList = getAdminList(id);
        adminList.getTable().setPreferredScrollableViewportSize(new Dimension(400, 500));
        adminPanel.setLayout(new FlowLayout());
        adminPanel.add(adminList);

        returnButton.addActionListener(e -> {
            Component component = (Component) e.getSource();
            HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

            homeFrame.replace(new GroupChatManagePanel());
        });
    }

    public ViewPanel getMemberList(Long id) {
        String[] mbHeaders = {"Username", "Full name"};
        List<Object[]> members = new ArrayList<>();
        Set<User> memberData = null;
        try {
            memberData = GroupChatService.getInstance().listMember(id);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        for (var member : memberData) {
            Object[] row = {
                    member.getUserName(),
                    member.getFullName()
            };
            members.add(row);
        }

        return new ViewPanel(mbHeaders, members, false, 10);
    }

    public ViewPanel getAdminList(Long id) {
        String[] admHeaders = {"Username", "Full name"};
        List<Object[]> admins = new ArrayList<>();
        Set<User> adminData = null;
        try {
            adminData = GroupChatService.getInstance().listAdmin(id);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        for (var admin : adminData) {
            Object[] row = {
                    admin.getUserName(),
                    admin.getFullName()
            };
            admins.add(row);
        }

        return new ViewPanel(admHeaders, admins, false, 10);
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
