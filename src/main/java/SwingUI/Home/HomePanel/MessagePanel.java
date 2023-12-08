package SwingUI.Home.HomePanel;

import javax.swing.*;

public class MessagePanel extends JPanel {
    MessageUI messageUI = new MessageUI();
    public MessagePanel() {
        add(messageUI.mainPanel());
    }
}
