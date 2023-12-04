package SwingUI.Home.Component;

import SwingUI.Home.SidePanel;

import javax.swing.*;
import java.awt.*;

public class Navigator extends JPanel {
    private JButton isSelected;
    private JButton friends;
    private final JButton groups;
    public Navigator() {
        setPreferredSize(new Dimension(200, 50));
        setBackground(Color.yellow);
        setLayout(new GridLayout(1, 2));

        friends = new JButton(new ImageIcon((SidePanel.class.getClassLoader().getResource("user.png"))));
        friends.setBackground(Color.gray);

        groups = new JButton(new ImageIcon((SidePanel.class.getClassLoader().getResource("people.png"))));

        add(friends);
        add(groups);
    }
}
