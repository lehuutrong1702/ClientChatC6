package Controller.User;

import SwingUI.User.Component.TextSearchFrame;
import SwingUI.User.HomePanel.MessageUI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.Message;
import com.teamc6.chatSystem.service.GroupChatService;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TextSearchControl implements ActionListener {
    private MessageUI messageUI;

    public TextSearchControl(MessageUI messageUI) {
        this.messageUI = messageUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Long groupID = messageUI.getGroupChat().getId();
        String context = messageUI.getSearchField().getText();
        List<Message> messages = null;
        try {
            messages = GroupChatService.getInstance().searchInChat(groupID, context);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
        var textSearch = new TextSearchFrame(messages);
    }
}
