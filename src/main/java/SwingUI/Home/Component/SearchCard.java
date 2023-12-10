package SwingUI.Home.Component;

import com.teamc6.chatsystem.model.GroupChat;
import com.teamc6.chatsystem.model.User;

import javax.swing.*;

public class SearchCard<T> extends JPanel {
    private JPanel mainPanel;
    private JButton bAdd;
    private JButton bDel;
    private JLabel name;
    private JLabel other;

    public SearchCard(T item) {
        if (item instanceof User u) {
            name.setText(u.getFullName());
            other.setText(u.getUserName());
        } else if (item instanceof GroupChat g) {
            name.setText(g.getGroupName());
        }
        add(mainPanel);
    }
}
