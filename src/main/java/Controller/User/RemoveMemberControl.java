package Controller.User;

import SwingUI.User.Component.MemberList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.service.GroupChatService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveMemberControl implements ActionListener {
    private final GroupChat groupChat;
    private final MemberList memberList;
    private final User member;

    public RemoveMemberControl(MemberList memberList, User member) {
        this.groupChat = memberList.getGroup();
        this.memberList = memberList;
        this.member = member;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Clicked");
        JFrame frame = new JFrame();
        int confirmation = JOptionPane.showConfirmDialog(frame, "You attempt to remove " + member.getUserName() + " from the chat!");
        if(confirmation == 0) {
            try {
                GroupChatService.getInstance().removeMember(groupChat.getId(), member.getUserId());
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
            memberList.getList();
        }
    }
}
