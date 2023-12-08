package SwingUI.Home.HomePanel;

import SwingUI.Home.MainPanel;

import javax.swing.*;

public class SearchPanel extends JPanel {
    SearchUi searchUi = new SearchUi();
    public void search(String pattern) {

    }
    public SearchPanel() {
        add(this.searchUi.getMainPanel());
    }
}
