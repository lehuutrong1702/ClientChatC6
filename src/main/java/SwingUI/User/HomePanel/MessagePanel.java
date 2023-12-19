package SwingUI.User.HomePanel;

import javax.swing.*;

public class MessagePanel<T> extends JPanel {
    MessageUI messageUI;
    public MessagePanel(T item) {
        messageUI = new MessageUI<>(item);
        add(messageUI.getUiPanel());
    }

}
