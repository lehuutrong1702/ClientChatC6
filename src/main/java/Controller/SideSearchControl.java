package Controller;

import SwingUI.Home.Component.Navigator;
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
        Navigator navigator = search.getSidePanel().getChatNavigator();
        search.getSidePanel().getList().getList(navigator.getSelected() == navigator.getFriends(), name);
    }

}
