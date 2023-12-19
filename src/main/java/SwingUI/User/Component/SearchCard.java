package SwingUI.User.Component;

import Controller.SearchCardControl;
import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.model.User;

import javax.swing.*;

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
            bAdd.addMouseListener(new SearchCardControl(u.getUserId()));

//            bDel.addMouseListener(new SearchCardControl());
        } else if (item instanceof GroupChat g) {
            name.setText(g.getGroupName());
        }
        add(mainPanel);
    }


}
