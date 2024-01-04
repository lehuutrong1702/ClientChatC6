package SwingUI.User.Component;

import Controller.User.GroupCreationControl;
import SwingUI.User.SidePanel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.model.Page;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.GroupChatService;
import com.teamc6.chatSystem.service.UserService;
import com.teamc6.chatSystem.socket.SocketClient;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class MemberList extends JPanel {
    public SocketClient getSocketClient() {
        return socketClient;
    }

    private SocketClient socketClient;
    private GridBagConstraints main_gbc;
    private GridBagConstraints comp_gbc;
    JPanel mainList;

    public GroupChat getGroup() {
        return group;
    }

    GroupChat group;

    public MemberList(GroupChat g, SocketClient socketClient) {
        this.socketClient = socketClient;
        this.group = g;
        setLayout(new BorderLayout());
        mainList = new JPanel(new GridBagLayout());
        main_gbc = new GridBagConstraints();
        mainList.setPreferredSize(new Dimension(150, 550));
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

    public void getList() {
        try {
            mainList.removeAll();
            mainList.add(new JPanel(), main_gbc);

            Set<User> list = GroupChatService.getInstance().listMember(group.getId());
            System.out.println(list);
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

    public void addCard(User member) {
        MemberCard newCard = new MemberCard(member, this);
        mainList.add(newCard, comp_gbc, 0);
    }
}