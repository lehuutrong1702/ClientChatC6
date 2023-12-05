package SwingUI.Home.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatsystem.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class ListComponent extends JPanel {
    JPanel mainList;

    public ListComponent() {
        setLayout(new BorderLayout());

        mainList = new JPanel(new GridBagLayout());
        GridBagConstraints main_gbc = new GridBagConstraints();
        main_gbc.gridwidth = GridBagConstraints.REMAINDER;
        main_gbc.weightx = 1;
        main_gbc.weighty = 1;
        mainList.add(new JPanel(), main_gbc);
        getList(true);

        add(new JScrollPane(mainList));
    }

    public void getList(boolean friends) {
        try {
            for (var i = 0; i < mainList.getComponentCount() - 1; i++) {
                mainList.remove(i);
            }

            Set list;
            if (friends)
                list = UserService.getInstance().getListFriend();
            else
                list = UserService.getInstance().getListGroup();

            if (list != null) {
                GridBagConstraints comp_gbc = new GridBagConstraints();
                comp_gbc.gridwidth = GridBagConstraints.REMAINDER;
                comp_gbc.weightx = 1;
                comp_gbc.fill = GridBagConstraints.HORIZONTAL;

                for (var item : list) {
                    //card component
                    Card newCard = new Card<>(item);
                    mainList.add(newCard, comp_gbc, 0);
                }
            }

            mainList.revalidate();
            mainList.repaint();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
