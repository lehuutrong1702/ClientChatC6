package Controller;

import SwingUI.Home.HomePanel.MessageUI;
import SwingUI.Home.MainPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageUIControl implements ActionListener {
    private MessageUI messageUI;

    public MessageUIControl(MessageUI UI) {
        messageUI = UI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
