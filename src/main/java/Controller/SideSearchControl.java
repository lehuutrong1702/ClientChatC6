package Controller;

import SwingUI.Home.Component.SideNav;
import SwingUI.Home.Component.SideSearch;

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
