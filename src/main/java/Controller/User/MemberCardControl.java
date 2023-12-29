package Controller.User;

import SwingUI.User.Component.MemberList;
import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.model.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MemberCardControl implements MouseListener {
    private final JPopupMenu menu;

    public MemberCardControl(JPopupMenu menu, MemberList memberList, User member) {
        this.menu = menu;
        JMenuItem remove = new JMenuItem("Kick " + member.getUserName());
        JMenuItem assignAdmin = new JMenuItem("Set " + member.getUserName()+" as admin");
        remove.addActionListener(new RemoveMemberControl(memberList, member));
        assignAdmin.addActionListener(new AssignAdminControl(memberList, member));
        this.menu.add(remove);
        this.menu.add(assignAdmin);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("click");
        menu.show(e.getComponent(), e.getX(), e.getY());
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
