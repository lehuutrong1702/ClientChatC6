package SwingUI.Home.Component;

import SwingUI.Utils.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;

public class NavSearch extends JPanel {
    private final JButton bSearch;
    private final JTextField tfSearch;


    public JButton getbSearch() {
        return bSearch;
    }

    public JTextField gettfSearch() {
        return tfSearch;
    }


    public NavSearch()  {
        tfSearch = new JTextField() {
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setPaint(getBackground());
                    g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                            0, 0, getWidth() - 1, getHeight() - 1));
                    g2.dispose();
                }
                super.paintComponent(g);
            }
            public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setBorder(new RoundedCornerBorder());
            }
        };

        bSearch = new JButton("Search") {
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setPaint(getBackground());
                    g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                            0, 0, getWidth() - 1, getHeight() - 1));
                    g2.dispose();
                }
                super.paintComponent(g);
            }
            public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setBorder(new RoundedCornerBorder());
            }
        };

        init();
    }

    public void addEventListeners() {
        //submit button action listener
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
    }

    public void init() {
        setLayout(new GridBagLayout());
        setBackground(new Color(72,72,72));

        bSearch.setPreferredSize(new Dimension(70,40));
        bSearch.setBackground(new Color(66, 245, 114));
        bSearch.setFocusPainted(false);

        tfSearch.setText("Search");
        tfSearch.setPreferredSize(new Dimension(300,40));
        tfSearch.setForeground(Color.gray);


        Insets bSearchInsets = new Insets(0, 10, 0, 0);

        GridBagConstraints input = new GridBagConstraints();

        input.anchor = GridBagConstraints.CENTER;
        input.gridx = 0;
        input.gridy = 0;
        add(tfSearch,input);

        input.insets = bSearchInsets;
        input.anchor = GridBagConstraints.WEST;
        input.gridx = 1;
        input.gridy = 0;
        add(bSearch,input);


        addEventListeners();
    }
}