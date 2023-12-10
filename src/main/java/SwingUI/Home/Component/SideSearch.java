package SwingUI.Home.Component;

import Controller.SideSearchControl;
import SwingUI.Home.SidePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SideSearch extends JPanel {
    private final JTextField tfSearch;
    private final JButton bSearch;
    private final SidePanel sidePanel;

    public JTextField getTfSearch() {
        return tfSearch;
    }

    public JButton getbSearch() {
        return bSearch;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }

    public SideSearch(SidePanel sidePanel) {
        this.sidePanel = sidePanel;
        setLayout(new BorderLayout());
        tfSearch = new JTextField("Search");
        tfSearch.setPreferredSize(new Dimension(150, 35));
        bSearch = new JButton("🔍");
        bSearch.setPreferredSize(new Dimension(50, 35));
        tfSearch.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                if(tfSearch.getText().isEmpty()) {
                    tfSearch.setText("Search");
                    tfSearch.setForeground(Color.gray);
                }
            }

            public void focusGained(FocusEvent e) {
                if(tfSearch.getText().equals("Search")) {
                    tfSearch.setText("");
                    tfSearch.setForeground(Color.black);
                }
            }
        });

        bSearch.addActionListener(new SideSearchControl(this));

        add(tfSearch, BorderLayout.CENTER);
        add(bSearch, BorderLayout.EAST);
    }
}
