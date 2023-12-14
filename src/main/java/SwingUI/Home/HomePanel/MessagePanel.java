package SwingUI.Home.HomePanel;

import SwingUI.Home.MainPanel;
import com.teamc6.chatsystem.model.Connection;
import com.teamc6.chatsystem.model.GroupChat;
import com.teamc6.chatsystem.model.User;
import com.teamc6.chatsystem.socket.SocketClient;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;

public class MessagePanel<T> extends JPanel {
    MessageUI messageUI;
    public MessagePanel(T item) {
        messageUI = new MessageUI<>(item);
        add(messageUI.getUiPanel());
    }

}
