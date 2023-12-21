package Controller.User;

import SwingUI.User.Component.SideNav;
import SwingUI.User.Component.SideSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideSearchControl implements ActionListener {
    SideSearch search;

    public SideSearchControl(SideSearch search) {
        this.search = search;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = search.getTfSearch().getText();
        SideNav sideNav = search.getSidePanel().getChatNavigator();
        search.getSidePanel().getList().getList(sideNav.getSelected() == sideNav.getFriends(), name);

    }

}
