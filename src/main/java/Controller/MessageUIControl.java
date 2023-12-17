package Controller;

import SwingUI.Home.HomePanel.MessageUI;
import SwingUI.Home.MainPanel;
import com.teamc6.chatsystem.socket.SocketClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageUIControl implements ActionListener {
    private MessageUI messageUI;
    private SocketClient socketClient;

    public MessageUIControl(MessageUI UI) {
        messageUI = UI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        messageUI.getSocketClient().sendMessage(messageUI.getMessage().getText());
    }
}
