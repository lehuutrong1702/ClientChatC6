import SwingUI.SignIn.SignInFrame;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatsystem.model.GroupChat;
import com.teamc6.chatsystem.model.User;
import com.teamc6.chatsystem.properties.Account;
import com.teamc6.chatsystem.service.GroupChatService;
import com.teamc6.chatsystem.service.UserService;

import javax.swing.*;
import java.io.IOException;

public class ChatApp {
    public static void main(String[] args) throws JsonProcessingException {
        Account.getInstance().setId(20);
        Account.getInstance().setUserName("Khiem123");
        Account.getInstance().setPassWord("123");
        //User user = UserService.getInstance().findById(Account.getInstance().getId());
        //user.setFullName("PhanLyBaoHanh");
        //User user = new User(Account.getInstance().getId(), "PhanLyBaoHanh", null, true, "nguyen123@gmail.com", null, Account.getInstance().getUserName(), Account.getInstance().getPassWord(), "USER", true);
        System.out.println(GroupChatService.getInstance().searchGroupChatById(1).getGroupName());
        //System.out.println(UserService.getInstance().getListGroup());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {

                    new SignInFrame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
