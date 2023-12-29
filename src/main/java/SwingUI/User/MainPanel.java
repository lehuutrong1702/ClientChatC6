package SwingUI.User;

import SwingUI.User.HomePanel.MessageUI;
import com.teamc6.chatSystem.model.GroupChat;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    HomeFrame homeFrame;

    public MainPanel(HomeFrame homeFrame) {
        this.homeFrame = homeFrame;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(750, 650));

        Navbar navbar = new Navbar(this);
        add(navbar, BorderLayout.NORTH);
    }

    public HomeFrame getHomeFrame() {
        return homeFrame;
    }

    public void replace(JPanel panel) {
        BorderLayout layout = (BorderLayout) this.getLayout();
        if (layout.getLayoutComponent(BorderLayout.CENTER) != null)
            remove(layout.getLayoutComponent(BorderLayout.CENTER));

        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
