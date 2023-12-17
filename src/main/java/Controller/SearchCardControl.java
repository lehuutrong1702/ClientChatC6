package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatsystem.service.UserService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SearchCardControl implements MouseListener {
    long userId;
    public SearchCardControl(long userId) {
        this.userId = userId;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            UserService.getInstance().addFriend(this.userId);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
