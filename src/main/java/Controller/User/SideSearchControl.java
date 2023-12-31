package Controller.User;

import SwingUI.User.Component.SideSearch;
import SwingUI.User.Component.TextSearchFrame;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.Message;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.GroupChatService;
import com.teamc6.chatSystem.service.UserService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SideSearchControl implements ActionListener {
    SideSearch search;

    public SideSearchControl(SideSearch search) {
        this.search = search;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String context = search.getTfSearch().getText();
        List<Message> messages = new ArrayList<>();
        try {
            Set<User> friendList = UserService.getInstance().getListFriend(Account.getInstance().getId());
            for(var f : friendList){
                long groupID = UserService.getInstance().getPrivateGroupChat(f.getUserId()).getId();
                messages.addAll(GroupChatService.getInstance().searchInChat(groupID, context));
            }
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

        var textSearch = new TextSearchFrame(messages);
    }

}
