package SwingUI.Admin;

import SwingUI.Admin.UserManage.UserManagePanel;

import javax.swing.*;

public class HomeFrame extends JFrame {
    public HomeFrame() {
        setTitle("Chat System C6 Management");
        setSize(950, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        add(new HomePanel(this));

        requestFocus();
    }

    public void replace(JPanel panel) {
        getContentPane().remove(0);
        add(panel);
        revalidate();
        repaint();
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
