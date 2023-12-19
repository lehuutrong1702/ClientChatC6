package Controller;

import SwingUI.Admin.HomeFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminHomeFrameControl implements ActionListener {
    HomeFrame homeFrame;
    String[] actions;
    public AdminHomeFrameControl(HomeFrame homeFrame, String[] actions) {
        this.homeFrame = homeFrame;
        this.actions = actions;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().equals(actions[0])) {

        } else if (button.getText().equals(actions[0])) {

        } else if (button.getText().equals(actions[1])) {

        } else if (button.getText().equals(actions[2])) {

        } else if (button.getText().equals(actions[3])) {

        } else if (button.getText().equals(actions[4])) {

        } else if (button.getText().equals(actions[5])) {

        } else if (button.getText().equals(actions[6])) {

        } else if (button.getText().equals(actions[7])) {

        } else {

        }
    }
}
