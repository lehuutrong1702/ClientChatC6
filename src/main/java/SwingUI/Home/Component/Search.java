package SwingUI.Home.Component;

import SwingUI.Home.SidePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Search extends JPanel {
    public Search() {
        setLayout(new BorderLayout());
        JTextField tfSearch = new JTextField("Search");
        tfSearch.setPreferredSize(new Dimension(150, 35));
        JButton bSearch = new JButton("🔍");
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
        add(tfSearch, BorderLayout.CENTER);
        add(bSearch, BorderLayout.EAST);
    }
}
