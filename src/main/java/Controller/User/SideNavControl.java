package Controller.User;

import SwingUI.User.Component.SideNav;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideNavControl implements ActionListener {
    private final SideNav nav;
    private final Color bg;

    public SideNavControl(SideNav nav) {
        this.nav = nav;
        bg = nav.getGroups().getBackground();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button == nav.getGroups()) {
            nav.getFriends().setBackground(bg);
            nav.getGroups().setBackground(Color.gray);
            nav.getSidePanel().getList().getList(false);
        } else {
            nav.getGroups().setBackground(bg);
            nav.getFriends().setBackground(Color.gray);
            nav.getSidePanel().getList().getList(true);
        }
    }
}
