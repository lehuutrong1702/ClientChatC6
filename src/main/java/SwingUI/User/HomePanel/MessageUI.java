package SwingUI.User.HomePanel;

import Controller.User.LoadMessageControl;
import Controller.User.MessageUIControl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.Connection;
import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.model.Message;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.GroupChatService;
import com.teamc6.chatSystem.service.UserService;
import com.teamc6.chatSystem.socket.SocketClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class MessageUI<T> {
    private SocketClient socketClient;
    private GroupChat groupChat;
    private JPanel UiPanel;
    private JTextArea textArea;
    private JTextField message;
    private JButton bSend;
    private JButton bSearch;
    private JButton button2;
    private JLabel name;
    private JPanel utilPanel;
    private JPanel messagePanel;
    private JTextField searchField;
    private JScrollPane scrollPane;

    public MessageUI(T item) {
        GroupChatService.getInstance().setPage(0);
        UiPanel.setPreferredSize(new Dimension(730, 550));
        message.setPreferredSize(new Dimension(500, 30));
        searchField.setPreferredSize(new Dimension(500, 30));
        utilPanel.setPreferredSize(new Dimension(150, 550));
        textArea.setLineWrap(true);

        if (item instanceof User u) {
            name.setText(u.getFullName());
            try {
                groupChat = UserService.getInstance().getPrivateGroupChat(u.getUserId());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            utilPanel.add(new JButton("Delete chat"));
        } else if (item instanceof GroupChat g) {
            name.setText(g.getGroupName());

            GridBagConstraints comp_gbc = new GridBagConstraints();
            comp_gbc.gridwidth = GridBagConstraints.REMAINDER;
            comp_gbc.insets = new Insets(2, 0, 2, 0);
            comp_gbc.weightx = 1;
            comp_gbc.fill = GridBagConstraints.HORIZONTAL;
            utilPanel.add(new JTextField("Enter name"), comp_gbc);
            utilPanel.add(new JButton("Rename"), comp_gbc);
            utilPanel.add(new JButton("Add"),comp_gbc);
            utilPanel.add(new JButton("Remove"),comp_gbc);
            utilPanel.add(new JButton("Assign admin"),comp_gbc);
        }

        if(groupChat != null) {
            try {

                Connection connection = GroupChatService.getInstance().getConnectionByID(groupChat.getId());
                Socket socket = new Socket(connection.getIpv4(), connection.getPort());
                socketClient = new SocketClient(socket, Account.getInstance().getUserName(), textArea);
                List<Message> messages = GroupChatService.getInstance().getOldMessages(groupChat.getId());
                for(Message m : messages){
                    textArea.insert('\n'+ m.getUserName()+ ": "+ m.getMessage(), 0);
                }
                socketClient.listenForMessage();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        scrollPane.getVerticalScrollBar().addAdjustmentListener(new LoadMessageControl(scrollPane.getVerticalScrollBar(), textArea, groupChat));
        bSend.addActionListener(new MessageUIControl(this));


        GridBagConstraints main_gbc = new GridBagConstraints();
        main_gbc.gridwidth = GridBagConstraints.REMAINDER;
        main_gbc.weightx = 1;
        main_gbc.weighty = 1;
        utilPanel.add(new JPanel(), main_gbc);
    }

    public JPanel getUiPanel() {
        return UiPanel;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JTextField getMessage() {
        return message;
    }

    public JButton getbSend() {
        return bSend;
    }

    public JButton getButton1() {
        return bSearch;
    }

    public JButton getButton2() {
        return button2;
    }

    public JLabel getName() {
        return name;
    }

    public JPanel getUtilPanel() {
        return utilPanel;
    }

    public SocketClient getSocketClient() {
        return socketClient;
    }
}