package Controller.User;

import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.service.GroupChatService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearControl implements ActionListener {
    JTextArea textArea;
    GroupChat groupChat;

    public ClearControl(JTextArea textArea, GroupChat groupChat) {
        this.textArea = textArea;
        this.groupChat = groupChat;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        GroupChatService.getInstance().clearMessages(groupChat.getId());
        textArea.setText(null);
    }
}
