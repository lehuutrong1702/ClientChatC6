package Controller.Admin.UserMange;

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

public class UserManageControl implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(button);
        UserManagePanel userManagePanel = (UserManagePanel) button.getParent().getParent();
        String value;
        int row = userManagePanel.getSelectedRow();
        switch (button.getText()) {
            case "Add":
                homeFrame.replace(new AddPanel());
                break;
            case "Delete":
                if (row == -1)
                    return;

                //lay username hoac id, neu muon lay id thi columnindex=0
                value = userManagePanel.getSelectedValueAtCol(0);
                try {
                    Boolean success = UserService.getInstance().deleteUser(Long.valueOf(value));
                    if (success) {
                        userManagePanel.getUserList().getModel().removeRow(row);
                        JOptionPane.showMessageDialog(null, "Delete successfully");
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Delete failed");
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Sessions and friends":
                value = userManagePanel.getSelectedValueAtCol(0);
                if (value == null)
                    return;

                SessionFriendPanel panel = new SessionFriendPanel(Long.parseLong(value));
                homeFrame.replace(panel.getMainPanel());
                break;
            case "Change password":
                if (row == -1)
                    return;

                //lay username hoac id, neu muon lay id thi columnindex=0
                value = userManagePanel.getSelectedValueAtCol(1);
                String newPassword = JOptionPane.showInputDialog("Enter new password for " + value);

                if (newPassword != null) {
                    try {
                        User u = UserService.getInstance().findById(Long.parseLong(value));
                        u.setPassword(newPassword);
                        User updated = UserService.getInstance().updateUser(u, Long.valueOf(value));
                        if (updated != null)
                            JOptionPane.showMessageDialog(null, "Update successfully");
                        else
                            JOptionPane.showMessageDialog(null, "Update failed");
                    } catch (JsonProcessingException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case "Return":
                homeFrame.replace(new HomePanel(homeFrame));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + button.getText());
        }
    }
}
