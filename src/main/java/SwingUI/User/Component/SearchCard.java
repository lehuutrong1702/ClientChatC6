package SwingUI.User.Component;

import Controller.User.BlockControl;
import Controller.User.SearchCardControl;
import Controller.User.UnFriendBtnControl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.model.Relationship;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.util.Objects;

public class SearchCard<T> extends JPanel {
    private JPanel mainPanel;
    private JButton bAdd;
    private JButton bDel;
    private JLabel nameLabel;
    private JLabel name;
    private JLabel Label;
    private JLabel other;

    public SearchCard(T item) {
        if (item instanceof User u) {
            name.setText(u.getFullName());
            other.setText(u.getUserName());
            try {
                Relationship relationship = UserService.getInstance().getRelationShip(u.getUserId());
                System.out.println(relationship.toString());
                if(Objects.equals(relationship.getName(), "friend")){
                    bAdd.setText("You are friends!");
                    bDel.setText("UnFriend");
                    bDel.addActionListener(new UnFriendBtnControl(u.getUserId()));
                }else {
                    bAdd.addMouseListener(new SearchCardControl(u.getUserId()));
                    bDel.addActionListener(new BlockControl(u.getUserId()));
                }
            } catch (JsonProcessingException e) {
                bAdd.addMouseListener(new SearchCardControl(u.getUserId()));
            }
        } else if (item instanceof GroupChat g) {
            name.setText(g.getGroupName());
        }
        add(mainPanel);
    }


}
