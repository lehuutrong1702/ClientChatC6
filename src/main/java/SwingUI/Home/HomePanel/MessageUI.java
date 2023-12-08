package SwingUI.Home.HomePanel;

import javax.swing.*;
import java.awt.*;

public class MessageUI {
    private JPanel mainPanel;
    private JTextField message;
    private JButton sendButton;
    private JComboBox comboBox1;

    public JPanel mainPanel() {
        return mainPanel;
    }

    public MessageUI() {
        mainPanel.setPreferredSize(new Dimension(730, 550));
        message.setPreferredSize(new Dimension(150, 30));
    }
}
