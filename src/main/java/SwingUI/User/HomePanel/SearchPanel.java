package SwingUI.User.HomePanel;

import Controller.User.SearchPanelControl;
import SwingUI.User.Component.SearchCard;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.Page;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {
    private JPanel MainPanel;
    private JLabel lResult;
    private JPanel ResultPanel;
    private JScrollPane ScrollPane;
    private JPanel panelSearch;

    public SearchPanel(String text) {
        this.MainPanel.setPreferredSize(new Dimension(750, 600));

        lResult.setFont(new Font(lResult.getFont().toString(), Font.BOLD, 30));
        this.ResultPanel.setLayout(new GridLayout(10, 3));

        Page<User> page = null;
        try {
            page = UserService.getInstance().filterUser(text, 0, 5);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        java.util.List<User> userList = page.getContent();
        for (User u : userList) {
            if (u.getRole().equalsIgnoreCase("admin"))
                continue;

            if (u.getUserId() != Account.getInstance().getId()) {
                ResultPanel.add(new SearchCard<>(u));
            }
        }
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }
}
