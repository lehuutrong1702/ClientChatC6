package SwingUI.Home.HomePanel;

import SwingUI.Home.Component.SearchCard;
import com.teamc6.chatsystem.model.User;

import javax.swing.*;
import java.awt.*;

public class SearchPanel {
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JPanel MainPanel;
    private JPanel FilterPanel;
    private JLabel lResult;
    private JPanel ResultPanel;
    private JScrollPane ScrollPane;
    private JPanel panelSearch;

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public SearchPanel(String text) {
        this.MainPanel.setPreferredSize(new Dimension(750, 600));

        lResult.setFont(new Font(lResult.getFont().toString(), Font.BOLD, 30));
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
        User u = new User();
        u.setFullName("Minh");
        u.setUserName("minh3107");
        this.ResultPanel.add(new SearchCard<>(u));
    }
}
