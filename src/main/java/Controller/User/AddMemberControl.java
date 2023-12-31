package Controller.User;

import SwingUI.User.Component.ListComponent;
import SwingUI.User.Component.MemberList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.GroupChatService;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMemberControl implements ActionListener {
    private final GroupChat groupChat;
    private final MemberList memberList;

    public AddMemberControl(GroupChat groupChat, MemberList memberList) {
        this.groupChat = groupChat;
        this.memberList = memberList;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        JFrame frame = new JFrame();
        String name = JOptionPane.showInputDialog(frame, "Enter the username that you want to invite:");
        if(name == null || name.isEmpty()) return;
        try {
            var u = UserService.getInstance().findByUserName(name);
            if(u != null){
                int confirmation = JOptionPane.showConfirmDialog(frame, "You attempt to invite " + u.getUserName() + " into the chat!");
                if(confirmation == 0) {
                    GroupChatService.getInstance().addMemberGroupChat(groupChat.getId(), u.getUserId());
                    memberList.getList();
                }
            }
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
