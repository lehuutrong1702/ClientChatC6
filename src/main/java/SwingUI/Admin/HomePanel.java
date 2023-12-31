package SwingUI.Admin;

import Controller.Admin.HomePanelControl;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    public HomePanel(HomeFrame homeFrame) {
        String[] actions = {
                "Manage users", "View sessions", "Manage group chat",
                "Manage spams", "View new users", "New user chart",
                "View friends", "View active users", "Active chart"
        };
        HomePanelControl control = new HomePanelControl(homeFrame, actions);

        setLayout(null);
        setSize(950, 650);

        JLabel lbMain = new JLabel("Chat system management");
        lbMain.setHorizontalAlignment(SwingConstants.CENTER);
        lbMain.setFont(new Font("Arial", Font.BOLD, 35));
        lbMain.setBounds(0, 120, 950, 50);

        JPanel optionPanel = new JPanel(new GridBagLayout());
        optionPanel.setBounds(0, 50, 950, 550);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gbc.gridx = j;
                gbc.gridy = i;
                JButton button = new JButton(actions[i*3 + j]);
                button.setPreferredSize(new Dimension(150, 50));
                button.addActionListener(control);
                optionPanel.add(button, gbc);
            }
        }

        add(lbMain);
        add(optionPanel);
    }
}
