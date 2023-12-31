package SwingUI.User.Component;

import Controller.User.CardControl;
import Controller.User.MemberCardControl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.GroupChatService;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class MemberCard extends JPanel {
    JLabel status;
    JPopupMenu popupMenu;
    public MemberCard(User u, MemberList memberList) {
        setLayout(new BorderLayout());
        Border border = new MatteBorder(0, 0, 1, 0, Color.GRAY);
        Border margin = new EmptyBorder(10, 5, 10, 0);
        setBorder(new CompoundBorder(border, margin));
        add(new JLabel("\uD83D\uDC64"), BorderLayout.WEST);

        this.status = new JLabel();
        add(this.status, BorderLayout.SOUTH);
        add(new JLabel(u.getUserName()), BorderLayout.CENTER);
        ChangeState(u.isActive());
        popupMenu = new JPopupMenu();
        try {
            if(GroupChatService.getInstance().listAdmin(memberList.getGroup().getId()).contains(Account.getInstance().getSelf())){
                addMouseListener(new MemberCardControl(popupMenu, memberList, u));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void ChangeState(boolean isOnline){
        if(isOnline) {
            status.setText("Online");
            setBackground(new Color(190, 255, 152));
        }else{
            status.setText("Offline");
            setBackground(new Color(190, 50, 50));
        }
    }
}
