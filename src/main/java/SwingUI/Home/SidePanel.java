package SwingUI.Home;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    public SidePanel() {
        setBackground(new Color(66, 245, 114));
        setPreferredSize(new Dimension(200, 650));
        setLayout(new BorderLayout());

        JPanel chatNavigator = new JPanel();
        chatNavigator.setPreferredSize(new Dimension(200, 50));
        chatNavigator.setBackground(Color.yellow);
        chatNavigator.setLayout(new FlowLayout());
        JPanel friends = new JPanel();
        friends.setSize(100, 50);
        friends.add(new JLabel("friends"));
        JPanel groups = new JPanel();
        groups.setSize(100, 50);

        chatNavigator.add(friends);
        chatNavigator.add(groups);

        add(chatNavigator, BorderLayout.NORTH);

    }
}
