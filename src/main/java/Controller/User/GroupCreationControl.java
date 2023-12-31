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

public class GroupCreationControl implements ActionListener {
    private final ListComponent list;
    public GroupCreationControl(ListComponent list){
        this.list = list;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        JFrame frame = new JFrame();
        String name = JOptionPane.showInputDialog(frame, "Enter the group's name:");
        if(name == null || name.isEmpty()) return;
        try {
            GroupChat group = new GroupChat();
            group.setGroupName(name);
            group = GroupChatService.getInstance().createGroupChat(group);
            GroupChatService.getInstance().addMemberGroupChat(group.getId(), Account.getInstance().getId());
            GroupChatService.getInstance().addAdminGroupChat(group.getId(), Account.getInstance().getId());
            list.addCard(group);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
