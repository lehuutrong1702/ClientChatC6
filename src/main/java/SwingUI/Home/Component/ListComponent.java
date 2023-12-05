package SwingUI.Home.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatsystem.model.User;
import com.teamc6.chatsystem.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class ListComponent<T> extends JPanel {
    JPanel mainList;

    public ListComponent() {
        setLayout(new BorderLayout());

        mainList = new JPanel(new GridBagLayout());
//        GridBagConstraints main_gbc = new GridBagConstraints();
//        main_gbc.gridwidth = GridBagConstraints.REMAINDER;
//        main_gbc.weightx = 1;
//        main_gbc.weighty = 1;
//        mainList.add(new JPanel(), main_gbc);
        getFriends();
        add(new JScrollPane(mainList));
    }

    public void getFriends() {
        try {
            Set<User> list = UserService.getInstance().getListFriend();
            System.out.println(list);
            GridBagConstraints comp_gbc = new GridBagConstraints();
            comp_gbc.gridwidth = GridBagConstraints.REMAINDER;
            comp_gbc.weightx = 1;
            comp_gbc.fill = GridBagConstraints.HORIZONTAL;

            if (list != null) {
                for (var item : list) {
                    //card component
                    Card<User> newCard = new Card<>(item);
                    mainList.add(newCard, comp_gbc, 0);
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
