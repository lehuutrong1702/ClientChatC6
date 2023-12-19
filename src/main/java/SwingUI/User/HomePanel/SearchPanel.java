package SwingUI.User.HomePanel;

import Controller.SearchPanelControl;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel{
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
        this.comboBox1.addActionListener(new SearchPanelControl(this.comboBox1, this.comboBox2, this.ResultPanel, text));

        DefaultComboBoxModel<String> what = new DefaultComboBoxModel<>();
        what.addElement("Any");
        what.addElement("Name");
        what.addElement("Created time");
        this.comboBox2.setModel(what);
        this.comboBox2.addActionListener(new SearchPanelControl(this.comboBox1, this.comboBox2, this.ResultPanel, text));

        this.ResultPanel.setLayout(new GridLayout(10, 3));

    }
}
