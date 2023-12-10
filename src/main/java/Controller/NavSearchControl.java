package Controller;

import SwingUI.Home.Component.NavSearch;
import SwingUI.Home.HomePanel.SearchPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavSearchControl implements ActionListener {
    private final NavSearch navSearch;

    public NavSearchControl(NavSearch navSearch) {
        this.navSearch = navSearch;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SearchPanel search = new SearchPanel(navSearch.gettfSearch().getText());
        navSearch.getMainPanel().replace(search.getMainPanel());
    }
}
