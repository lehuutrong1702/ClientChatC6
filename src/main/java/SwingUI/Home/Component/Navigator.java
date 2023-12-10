package SwingUI.Home.Component;

import Controller.SideNavControl;
import SwingUI.Home.SidePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Navigator extends JPanel {
    private final JButton groups;
    private JButton isSelected;
    private final JButton friends;
    private SidePanel sidePanel;

    public SidePanel getSidePanel() {
        return sidePanel;
    }

    public void setIsSelected(JButton isSelected) {
        this.isSelected = isSelected;
    }

    public JButton getGroups() {
        return groups;
    }

    public JButton getSelected() {
        return isSelected;
    }

    public JButton getFriends() {
        return friends;
    }

    public Navigator(SidePanel sidePanel) {
        this.sidePanel = sidePanel;
        setPreferredSize(new Dimension(200, 50));
        setBackground(Color.yellow);
        setLayout(new GridLayout(1, 2));

        friends = new JButton(new ImageIcon((SidePanel.class.getClassLoader().getResource("user.png"))));
        groups = new JButton(new ImageIcon((SidePanel.class.getClassLoader().getResource("people.png"))));

        friends.setBackground(Color.gray);
        isSelected = friends;

        SideNavControl sideNavControl = new SideNavControl(this);
        friends.addActionListener(sideNavControl);
        groups.addActionListener(sideNavControl);

        add(friends);
        add(groups);
    }
}
