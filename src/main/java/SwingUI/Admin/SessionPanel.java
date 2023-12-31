package SwingUI.Admin;

import SwingUI.Admin.Component.ViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SessionPanel extends JPanel {
    ViewPanel sessionList;
    public SessionPanel() {
        setSize(950, 650);
        setLayout(new BorderLayout());

        Date date = new Date(2003-1900, Calendar.NOVEMBER, 10);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String strTime = dateFormat.format(date);

        String[] columnNames = {"Time", "Username", "Full Name"};
        List<Object[]> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Object[] row = {strTime, "A", "B"};
            data.add(row);
        }
        sessionList = new ViewPanel(columnNames, data, false);
        sessionList.getTable().setPreferredScrollableViewportSize(new Dimension(900, 550));

        JPanel actions = new JPanel();
        JButton bReturn = new JButton("Return");
        bReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

                homeFrame.replace(new HomePanel(homeFrame));
            }
        });
        actions.add(bReturn);

        add(sessionList, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);
    }
}
