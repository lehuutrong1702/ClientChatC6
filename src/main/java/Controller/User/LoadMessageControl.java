package Controller.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.model.Message;
import com.teamc6.chatSystem.service.GroupChatService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;

public class LoadMessageControl implements AdjustmentListener {
    GroupChat groupChat;
    JScrollBar scrollBar;
    JTextArea textArea;

    public LoadMessageControl(JScrollBar scrollBar, JTextArea textArea, GroupChat groupChat) {
        this.scrollBar = scrollBar;
        this.textArea = textArea;
        this.groupChat = groupChat;
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        if(e.getValueIsAdjusting() && e.getValue() == scrollBar.getMinimum()){
            List<Message> messages = null;
            try {
                messages = GroupChatService.getInstance().getOldMessages(groupChat.getId());
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
            for(Message m : messages){
                textArea.insert('\n'+ m.getUserName()+ ": "+ m.getMessage(), 0);
            }
        }
    }
}
