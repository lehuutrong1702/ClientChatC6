package Controller.Admin.GroupChatManage;

import SwingUI.Admin.GroupChatManage.GroupChatManagePanel;
import SwingUI.Admin.GroupChatManage.MemberPanel;
import SwingUI.Admin.HomeFrame;
import SwingUI.Admin.HomePanel;
import SwingUI.Admin.UserManage.AddPanel;
import SwingUI.Admin.UserManage.SessionFriendPanel;
import SwingUI.Admin.UserManage.UserManagePanel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupChatMangeControl implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(button);
        GroupChatManagePanel groupChatManagePanel = (GroupChatManagePanel) button.getParent().getParent();
        switch (button.getText()) {
            case "View group info":
                String value = groupChatManagePanel.getSelectedValueAtCol(0);
                MemberPanel memberPanel = new MemberPanel(Long.parseLong(value));
                homeFrame.replace(memberPanel.getMainPanel());
                break;
            case "Return":
                homeFrame.replace(new HomePanel(homeFrame));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + button.getText());
        }
    }
}
