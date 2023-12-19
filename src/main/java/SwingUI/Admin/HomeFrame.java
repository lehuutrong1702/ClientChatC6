package SwingUI.Admin;

import Controller.AdminHomeFrameControl;

import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {
    public HomeFrame() {
        setTitle("Chat System C6 Management");
        setLayout(null);
        setSize(950, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        String[] actions = {
                "Manage users", "View sessions", "Manage group chat",
                "Manage spams", "View new users", "New user chart",
                "View friends", "View active users", "Active chart"
        };
        AdminHomeFrameControl control = new AdminHomeFrameControl(this, actions);


        JLabel lbMain = new JLabel("Chat system management");
        lbMain.setHorizontalAlignment(SwingConstants.CENTER);
        lbMain.setFont(new Font("Arial", Font.BOLD, 35));
        lbMain.setBounds(0, 120, 950, 50);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBounds(0, 50, 950, 550);
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
                mainPanel.add(button, gbc);
            }
        }

        add(lbMain);
        add(mainPanel);
//        JButton users = new JButton("Manage users");
//        JButton sessions = new JButton("View sessions");
//        JButton groups = new JButton("Manage group chat");
//        JButton spams = new JButton("Manage spams");
//        JButton newUsers = new JButton("View new users");
//        JButton newUserChart = new JButton("New user chart");
//        JButton userAndFriends = new JButton("View users' friends");
//        JButton activeUser = new JButton("View active users");
//        JButton activeUserChart = new JButton("View active users chart");

        requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomeFrame();
            }
        });
    }
}
