package SwingUI.User.Component;

import SwingUI.User.SidePanel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.Page;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class ListComponent extends JPanel {
    JPanel mainList;
    SidePanel sidePanel;

    public SidePanel getSidePanel() {
        return sidePanel;
    }

    public ListComponent(SidePanel sidePanel) {
        this.sidePanel = sidePanel;
        setLayout(new BorderLayout());

        mainList = new JPanel(new GridBagLayout());
        GridBagConstraints main_gbc = new GridBagConstraints();
        main_gbc.gridwidth = GridBagConstraints.REMAINDER;
        main_gbc.weightx = 1;
        main_gbc.weighty = 1;
        mainList.add(new JPanel(), main_gbc);
        //getList(true);

        add(new JScrollPane(mainList));
    }

    public void getList(boolean friends) {
        try {
            for (var i = 0; i < mainList.getComponentCount() - 1; i++) {
                mainList.remove(i);
            }

            Set list;
            if (friends)
                list = UserService.getInstance().getListFriend(Account.getInstance().getId());
            else
                list = UserService.getInstance().getListGroup();

            if (list != null) {
                GridBagConstraints comp_gbc = new GridBagConstraints();
                comp_gbc.gridwidth = GridBagConstraints.REMAINDER;
                comp_gbc.weightx = 1;
                comp_gbc.fill = GridBagConstraints.HORIZONTAL;

                for (var item : list) {
                    //card component
                    Card newCard = new Card<>(item, this);
                    mainList.add(newCard, comp_gbc, 0);
                }
            }

            mainList.revalidate();
            mainList.repaint();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void getList(boolean friends, String name) {
        try {
            for (var i = 0; i < mainList.getComponentCount() - 1; i++) {
                mainList.remove(i);
            }

            Page list = null;
            if (friends)
                list = UserService.getInstance().filterFriendByName(Account.getInstance().getId(), name, 1, 20);
            else
                list = UserService.getInstance().filterGroupsByName(Account.getInstance().getId(), name, 1, 20);


            if (list != null) {
                GridBagConstraints comp_gbc = new GridBagConstraints();
                comp_gbc.gridwidth = GridBagConstraints.REMAINDER;
                comp_gbc.weightx = 1;
                comp_gbc.fill = GridBagConstraints.HORIZONTAL;

                for (var item : list.getContent()) {
                    //card component
                    Card newCard = new Card<>(item, this);
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
