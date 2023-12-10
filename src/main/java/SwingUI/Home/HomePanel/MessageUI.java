package SwingUI.Home.HomePanel;

import com.teamc6.chatsystem.model.GroupChat;
import com.teamc6.chatsystem.model.User;

import javax.swing.*;
import java.awt.*;

public class MessageUI<T> {
    private JPanel mainPanel;
    private JTextField message;
    private JButton sendButton;
    private JComboBox option;
    private JLabel name;
    private JTextArea textArea1;

    public JPanel mainPanel() {
        return mainPanel;
    }

    public MessageUI(T item) {
        if (item instanceof User u) {
            name.setText(u.getFullName());

            DefaultComboBoxModel<String> userOptionModel = new DefaultComboBoxModel<>();
            userOptionModel.addElement("Search");
            userOptionModel.addElement("Delete chat");
            this.option.setModel(userOptionModel);

        } else if (item instanceof GroupChat g) {
            name.setText(g.getGroupName());

            DefaultComboBoxModel<String> groupOptionModel = new DefaultComboBoxModel<>();
            groupOptionModel.addElement("Rename");
            groupOptionModel.addElement("User control");

            this.option.setModel(groupOptionModel);

        }


        mainPanel.setPreferredSize(new Dimension(730, 550));
        message.setPreferredSize(new Dimension(150, 30));
    }
}
