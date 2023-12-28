package SwingUI.User.Component;

import com.teamc6.chatSystem.model.Message;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TextSearchFrame extends JFrame {
    public TextSearchFrame(List<Message> messages){
        Object[] headers = {"Room", "User", "Message", "Create at"};
        DefaultTableModel model = new DefaultTableModel(headers, 0);
        if(messages != null) {
            for (Message m : messages) {
                Object[] row = {m.getGroupChat().getGroupName(), m.getUserName(), m.getMessage(), m.getCreationDateTime()};
                model.addRow(row);
            }
        }
        JTable messagesTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(messagesTable);
        add(scrollPane);
        setSize(500,200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
