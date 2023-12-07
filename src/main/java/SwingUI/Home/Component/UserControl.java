package SwingUI.Home.Component;

import SwingUI.Utils.RoundedCornerBorder;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class UserControl extends JPanel {
    public UserControl() {
        setBackground(new Color(72, 72, 72));
        setPreferredSize(new Dimension(100, 50));
        setLayout(new GridBagLayout());

        DefaultComboBoxModel<String> action = new DefaultComboBoxModel<>();
        action.addElement("\uD83D\uDC64 Username");
        action.addElement("Log out");
        JComboBox<String> userControlList = new JComboBox<String>(action) {
            @Override public void updateUI() {
                super.updateUI();
                UIManager.put("ComboBox.squareButton", Boolean.FALSE);
                setUI(new BasicComboBoxUI() {
                    @Override protected JButton createArrowButton() {
                        JButton b = new JButton();
                        b.setBorder(BorderFactory.createEmptyBorder());
                        b.setVisible(false);
                        return b;
                    }
                });
                setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
        };

        add(userControlList);
    }
}
