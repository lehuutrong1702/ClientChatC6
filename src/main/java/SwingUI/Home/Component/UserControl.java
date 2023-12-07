package SwingUI.Home.Component;

import SwingUI.Home.MainPanel;
import SwingUI.SignIn.SignInFrame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserControl extends JPanel {
    public UserControl(MainPanel mainPanel) {
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

        userControlList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var selectedItem =  userControlList.getSelectedItem();
                if (userControlList.getItemAt(0) == selectedItem) {
                    mainPanel.replace(new InfoPanel());
                } else {
                    mainPanel.getHomeFrame().dispose();
                    try {
                        new SignInFrame();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        add(userControlList);
    }
}
