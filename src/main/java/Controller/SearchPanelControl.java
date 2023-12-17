package Controller;

import SwingUI.Home.Component.SearchCard;
import SwingUI.Home.HomePanel.SearchPanel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatsystem.model.GroupChat;
import com.teamc6.chatsystem.model.Page;
import com.teamc6.chatsystem.model.User;
import com.teamc6.chatsystem.properties.Account;
import com.teamc6.chatsystem.service.GroupChatService;
import com.teamc6.chatsystem.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPanelControl implements ActionListener {
    private final JComboBox filterContext;
    private final JComboBox filterCondition;
    private final JPanel resultPanel;
    private String text;
    private UserService userService;
    public SearchPanelControl(JComboBox filterContext, JComboBox filterCondition, JPanel resultPanel, String text) {
        this.filterContext = filterContext;
        this.filterCondition = filterCondition;
        this.resultPanel = resultPanel;
        this.text = text;
        this.userService = UserService.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resultPanel.removeAll();

        String context = (String) this.filterContext.getSelectedItem();
        String condition = (String) this.filterCondition.getSelectedItem();
        try {
            System.out.println(text);
            switch (context){
                case "People":{
                    Page<User> page = userService.filterUser(text, 0, 5);
                    java.util.List<User> userList = page.getContent();
                    for(User u : userList){
                        resultPanel.add(new SearchCard<>(u));
                    }
                }break;
                case "Groups":{

                    Page<GroupChat> page = userService.filterGroupsByName(Account.getInstance().getId(),text,0, 5);
                    java.util.List<GroupChat> groupChatList = page.getContent();
                    for(GroupChat u : groupChatList){
                        resultPanel.add(new SearchCard<>(u));
                    }
                }break;
            }

        } catch (JsonProcessingException err) {
            throw new RuntimeException(err);
        }
    }
}
