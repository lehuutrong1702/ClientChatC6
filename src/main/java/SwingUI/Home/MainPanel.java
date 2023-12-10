package SwingUI.Home;

import SwingUI.Home.HomePanel.MessagePanel;
import SwingUI.Home.HomePanel.SearchPanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    HomeFrame homeFrame;

    public MainPanel() {
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
