package SwingUI.Admin.GroupChatManage;

import SwingUI.Admin.HomeFrame;
import SwingUI.Admin.UserManage.UserManagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberPanel {
    private JButton returnButton;
    private JPanel sessionList;
    private JPanel friendList;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public MemberPanel(Long id) {
        System.out.println(id);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

                homeFrame.replace(new UserManagePanel());
            }
        });
    }
}
