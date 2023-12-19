package Controller;

import SwingUI.User.Component.SideNav;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideNavControl implements ActionListener {
    private final SideNav nav;

    public SideNavControl(SideNav nav) {
        this.nav = nav;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (nav.getSelected() != nav.getFriends()) {
            nav.setIsSelected(nav.getFriends());
            nav.getGroups().setBackground(nav.getFriends().getBackground());
            nav.getFriends().setBackground(Color.gray);
            nav.getSidePanel().getList().getList(true);
        } else {
            nav.setIsSelected(nav.getGroups());
            nav.getFriends().setBackground(nav.getGroups().getBackground());
            nav.getGroups().setBackground(Color.gray);
            nav.getSidePanel().getList().getList(false);
        }
    }
}
