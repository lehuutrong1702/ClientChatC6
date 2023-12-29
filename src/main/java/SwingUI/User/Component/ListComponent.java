package SwingUI.User.Component;

import Controller.User.GroupCreationControl;
import SwingUI.User.SidePanel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.model.Page;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class ListComponent extends JPanel {
    private GridBagConstraints main_gbc;
    private GridBagConstraints comp_gbc;
    JPanel mainList;
    SidePanel sidePanel;

    public SidePanel getSidePanel() {
        return sidePanel;
    }

    public ListComponent(SidePanel sidePanel) {
        this.sidePanel = sidePanel;
        setLayout(new BorderLayout());

        mainList = new JPanel(new GridBagLayout());
        main_gbc = new GridBagConstraints();
        main_gbc.gridwidth = GridBagConstraints.REMAINDER;
        main_gbc.weightx = 1;
        main_gbc.weighty = 1;
        mainList.add(new JPanel(), main_gbc);

        comp_gbc = new GridBagConstraints();
        comp_gbc.gridwidth = GridBagConstraints.REMAINDER;
        comp_gbc.weightx = 1;
        comp_gbc.fill = GridBagConstraints.HORIZONTAL;
        //getList(true);

        add(new JScrollPane(mainList));
    }

    public void getList(boolean friends) {
        try {
            mainList.removeAll();
            mainList.add(new JPanel(), main_gbc);

            Set list = null;
            if (friends)
                list = UserService.getInstance().getListFriend(Account.getInstance().getId());
            else {
                list = UserService.getInstance().getListGroup();
                var newGroup = new JButton("New group");
                newGroup.addActionListener(new GroupCreationControl(this));
                mainList.add(newGroup, comp_gbc, 0);
            }
            if (list != null) {
                for (var item : list) {
                    //card component
                    addCard(item);
                }
            }

            mainList.revalidate();
            mainList.repaint();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCard(Object item){
        Card newCard = new Card<>(item, this);
        mainList.add(newCard, comp_gbc, (item instanceof GroupChat ? 1 : 0));
    }

//    public void getList(boolean friends, String name) {
//        try {
//            for (var i = 0; i < mainList.getComponentCount() - 1; i++) {
//                mainList.remove(i);
//            }
//
//            Page list = null;
//            if (friends)
//                list = UserService.getInstance().filterFriendByName(Account.getInstance().getId(), name, 1, 20);
//            else
//                list = UserService.getInstance().filterGroupsByName(Account.getInstance().getId(), name, 1, 20);
//
//
//            if (list != null) {
//                GridBagConstraints comp_gbc = new GridBagConstraints();
//                comp_gbc.gridwidth = GridBagConstraints.REMAINDER;
//                comp_gbc.weightx = 1;
//                comp_gbc.fill = GridBagConstraints.HORIZONTAL;
//
//                for (var item : list.getContent()) {
//                    //card component
//                    Card newCard = new Card<>(item, this);
//                    mainList.add(newCard, comp_gbc, 0);
//                }
//            }
//
//            mainList.revalidate();
//            mainList.repaint();
//
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
