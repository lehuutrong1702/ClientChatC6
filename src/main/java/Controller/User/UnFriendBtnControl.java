package Controller.User;

import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.service.GroupChatService;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnFriendBtnControl implements ActionListener {
    long userId;

    public UnFriendBtnControl(long userId) {
        this.userId = userId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UserService.getInstance().unFriend(userId);

    }
}
