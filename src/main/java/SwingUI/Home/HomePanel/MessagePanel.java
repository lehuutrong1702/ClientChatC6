package SwingUI.Home.HomePanel;

import com.teamc6.chatsystem.model.GroupChat;
import com.teamc6.chatsystem.model.User;

import javax.swing.*;
import java.awt.*;

public class MessagePanel<T> extends JPanel {
    MessageUI messageUI;
    public MessagePanel(T item) {
        messageUI = new MessageUI<>(item);
        add(messageUI.mainPanel());
    }

}
