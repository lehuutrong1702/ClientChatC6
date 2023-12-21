package Controller.User;

import SwingUI.User.HomePanel.MessagePanel;
import SwingUI.User.MainPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardControl<T> implements MouseListener {
    MainPanel mainPanel;
    T obj;
    public CardControl(MainPanel mainPanel, T obj) {
        this.mainPanel = mainPanel;
        this.obj = obj;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        mainPanel.replace(new MessagePanel<>(obj));
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
