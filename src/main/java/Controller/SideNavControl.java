package Controller;

import SwingUI.Home.Component.Navigator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideNavControl implements ActionListener {
    private final Navigator nav;

    public SideNavControl(Navigator nav) {
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
