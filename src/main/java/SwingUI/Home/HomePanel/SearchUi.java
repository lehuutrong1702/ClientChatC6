package SwingUI.Home.HomePanel;

import SwingUI.Home.Component.SearchCard;
import org.example.Main;

import javax.swing.*;
import java.awt.*;

public class SearchUi {
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JPanel MainPanel;
    private JPanel FilterPanel;
    private JLabel lResult;
    private JPanel ResultPanel;

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public SearchUi() {
        this.lResult.setFont(new Font(this.lResult.getFont().toString(), Font.BOLD, 30));
        DefaultComboBoxModel<String> who = new DefaultComboBoxModel<>();
        who.addElement("People");
        who.addElement("Groups");
        this.comboBox1.setModel(who);

        DefaultComboBoxModel<String> what = new DefaultComboBoxModel<>();
        what.addElement("Any");
        what.addElement("Name");
        what.addElement("Created time");
        this.comboBox2.setModel(what);

        this.ResultPanel.setLayout(new GridLayout(10, 3));
        this.ResultPanel.add(new SearchCard());
    }
}
