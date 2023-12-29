package Controller.User;

import com.teamc6.chatSystem.service.UserService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpamControl implements ActionListener {
    public SpamControl(long userId) {
        this.userId = userId;
    }
    long userId;
    @Override
    public void actionPerformed(ActionEvent e) {
        UserService.getInstance().reportSpam(userId);
    }
}
