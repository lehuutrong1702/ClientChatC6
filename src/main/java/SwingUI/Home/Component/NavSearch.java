package SwingUI.Home.Component;

import SwingUI.Utils.RoundedCornerBorder;

import javax.swing.*;
import java.awt.*;

public class NavSearch extends JPanel {
    public NavSearch() {
        setBackground(new Color(72, 72, 72));
        JTextField tfSearch = new JTextField() {
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
        tfSearch.setPreferredSize(new Dimension(300, 40));

        JButton bSearch = new JButton("Search");


        add(tfSearch);
        add(bSearch);
    }
}
