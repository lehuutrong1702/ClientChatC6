package SwingUI.Home;

import SwingUI.Home.HomePanel.MessagePanel;
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

        //User u = new User(); u.setFullName("Phan Van Nguyen");
        GroupChat g = new GroupChat();
        g.setGroupName("Doi hoa simp");
        add(new MessagePanel<>(g), BorderLayout.CENTER);
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
