package SwingUI.Home.Component;

import SwingUI.Home.SidePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Search extends JPanel {
    public Search() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JTextField tfSearch = new JTextField("Search");
        //tfSearch.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        JButton bSearch = new JButton(new ImageIcon((SidePanel.class.getClassLoader().getResource("searchIcon.png"))));
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
        add(tfSearch);
        add(bSearch);
    }
}
