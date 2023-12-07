package SwingUI.Home.Component;

import SwingUI.Home.SidePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Navigator extends JPanel {
    private final JButton groups;
    private JButton isSelected;
    private JButton friends;

    public JButton getGroups() {
        return groups;
    }

    public JButton getIsSelected() {
        return isSelected;
    }

    public JButton getFriends() {
        return friends;
    }

    public Navigator(SidePanel sidePanel) {
        setPreferredSize(new Dimension(200, 50));
        setBackground(Color.yellow);
        setLayout(new GridLayout(1, 2));

        friends = new JButton(new ImageIcon((SidePanel.class.getClassLoader().getResource("user.png"))));
        groups = new JButton(new ImageIcon((SidePanel.class.getClassLoader().getResource("people.png"))));

        friends.setBackground(Color.gray);
        isSelected = friends;

        friends.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected != friends) {
                    isSelected = friends;
                    groups.setBackground(friends.getBackground());
                    friends.setBackground(Color.gray);
                    sidePanel.getList().getList(true);
                }
            }
        });
        groups.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected != groups) {
                    isSelected = groups;
                    friends.setBackground(groups.getBackground());
                    groups.setBackground(Color.gray);
                    sidePanel.getList().getList(false);
                }
            }
        });

        add(friends);
        add(groups);
    }
}
