package SwingUI.User.HomePanel;

import Controller.User.*;
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

    public GroupChat getGroupChat() {
        return groupChat;
    }

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

    public JTextField getSearchField() {
        return searchField;
    }

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
            setUpPrivateUI(u);
        } else if (item instanceof GroupChat g) {
            setUpGroupUI(g);
        }

        if(groupChat != null) {
            setUpChatConnection();
            bSend.addActionListener(new MessageUIControl(this));
        }
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new LoadMessageControl(scrollPane.getVerticalScrollBar(), textArea, groupChat));
        bSearch.addActionListener(new TextSearchControl(this));

        GridBagConstraints main_gbc = new GridBagConstraints();
        main_gbc.gridwidth = GridBagConstraints.REMAINDER;
        main_gbc.weightx = 1;
        main_gbc.weighty = 1;
        utilPanel.add(new JPanel(), main_gbc);
    }
    private void setUpPrivateUI(User u){
        GridBagConstraints comp_gbc = new GridBagConstraints();
        comp_gbc.gridwidth = GridBagConstraints.REMAINDER;
        comp_gbc.insets = new Insets(2, 0, 2, 0);
        comp_gbc.weightx = 1;
        comp_gbc.fill = GridBagConstraints.HORIZONTAL;

        name.setText(u.getFullName());
        try {
            System.out.println(u.getUserId());
            List<User> blockers = UserService.getInstance().blocking().getContent();

            if(blockers.contains(u)){
                textArea.append("YOU HAVE BLOCKED " + u.getUserName() + "!");
                groupChat = null;
            }else {
                groupChat = UserService.getInstance().getPrivateGroupChat(u.getUserId());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        var clearChatBtn = new JButton("Clear chat");
        var blockBtn = new JButton("Block this user");
        var spamReportBtn = new JButton("Spam report");
        clearChatBtn.addActionListener(new ClearControl(textArea, groupChat));
        blockBtn.addActionListener(new BlockControl(u.getUserId()));
        spamReportBtn.addActionListener(new SpamControl(u.getUserId()));

        utilPanel.add(clearChatBtn, comp_gbc);
        utilPanel.add(spamReportBtn, comp_gbc);
        utilPanel.add(blockBtn, comp_gbc);
    }

    private void setUpGroupUI(GroupChat g){
        GridBagConstraints comp_gbc = new GridBagConstraints();
        comp_gbc.gridwidth = GridBagConstraints.REMAINDER;
        comp_gbc.insets = new Insets(2, 0, 2, 0);
        comp_gbc.weightx = 1;
        comp_gbc.fill = GridBagConstraints.HORIZONTAL;

        name.setText(g.getGroupName());

        utilPanel.add(new JTextField("Enter name"), comp_gbc);
        utilPanel.add(new JButton("Rename"), comp_gbc);
        utilPanel.add(new JButton("Add"),comp_gbc);
        utilPanel.add(new JButton("Remove"),comp_gbc);
        utilPanel.add(new JButton("Assign admin"),comp_gbc);
    }

    private void setUpChatConnection(){
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