package SwingUI.User.Component;

import Controller.User.UCControl;
import SwingUI.User.MainPanel;
import com.teamc6.chatSystem.properties.Account;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class UserControl extends JPanel {
    private final JComboBox<String> userControlList;
    private final MainPanel mainPanel;

    public JComboBox<String> getUserControlList() {
        return userControlList;
    }

    public UserControl(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setBackground(new Color(72, 72, 72));
        setPreferredSize(new Dimension(100, 50));
        setLayout(new GridBagLayout());

        userControlList = initJComboBox();
        userControlList.addActionListener(new UCControl(this));

        add(userControlList);
    }

    private JComboBox<String> initJComboBox() {
        DefaultComboBoxModel<String> action = new DefaultComboBoxModel<>();
        action.addElement("\uD83D\uDC64 " + Account.getInstance().getUserName());
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
        return userControlList;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }
}
