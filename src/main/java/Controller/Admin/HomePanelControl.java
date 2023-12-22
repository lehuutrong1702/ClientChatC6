package Controller.Admin;

import SwingUI.Admin.CurrentUser.ChoosePanel;
import SwingUI.Admin.CurrentUser.CurrentUserChart;
import SwingUI.Admin.GroupChatManage.GroupChatManagePanel;
import SwingUI.Admin.*;
import SwingUI.Admin.NewUser.NewUserChart;
import SwingUI.Admin.NewUser.NewUserPanel;
import SwingUI.Admin.UserManage.UserManagePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanelControl implements ActionListener {
    HomeFrame homeFrame;
    String[] actions;

    public HomePanelControl(HomeFrame homeFrame, String[] actions) {
        this.homeFrame = homeFrame;
        this.actions = actions;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().equals(actions[0])) {
            homeFrame.replace(new UserManagePanel());
        } else if (button.getText().equals(actions[1])) {
            homeFrame.replace(new SessionPanel());
        } else if (button.getText().equals(actions[2])) {
            homeFrame.replace(new GroupChatManagePanel());
        } else if (button.getText().equals(actions[3])) {
            homeFrame.replace(new SpamPanel());
        } else if (button.getText().equals(actions[4])) {
            homeFrame.replace(new NewUserPanel());
        } else if (button.getText().equals(actions[5])) {
            homeFrame.replace(new NewUserChart());
        } else if (button.getText().equals(actions[6])) {
            homeFrame.replace(new UserFriendPanel());
        } else if (button.getText().equals(actions[7])) {
            homeFrame.replace(new ChoosePanel());
        } else if (button.getText().equals(actions[8])) {
            homeFrame.replace(new CurrentUserChart());
        } else {

        }
    }
}
