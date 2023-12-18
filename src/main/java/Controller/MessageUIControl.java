package Controller;

import SwingUI.Home.HomePanel.MessageUI;
import com.teamc6.chatSystem.socket.SocketClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;

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
