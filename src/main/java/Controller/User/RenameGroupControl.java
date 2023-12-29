package Controller.User;

import SwingUI.User.Component.ListComponent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.GroupChatService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenameGroupControl implements ActionListener {
    private final GroupChat groupChat;

    public RenameGroupControl(GroupChat groupChat) {
        this.groupChat = groupChat;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        JFrame frame = new JFrame();
        String name = JOptionPane.showInputDialog(frame, "Enter new group's name:");
        if(name == null || name.isEmpty()) return;
        try {
            GroupChatService.getInstance().renameGroup(groupChat.getId(), name);
            // change ui here
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
