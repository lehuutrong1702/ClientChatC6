package SwingUI.Admin;

import SwingUI.Admin.CurrentUser.CurrentUserPanel;
import SwingUI.Admin.NewUser.NewUserPanel;
import SwingUI.Utils.CustomDatePicker;

import javax.swing.*;
import java.awt.*;

public class ChoosePanel extends JPanel {
    private CustomDatePicker first = new CustomDatePicker();
    private CustomDatePicker last = new CustomDatePicker();
    public ChoosePanel(int choice) {
        setLayout(new GridBagLayout());

        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("From"));
        filterPanel.add(first);
        filterPanel.add(new JLabel("to"));
        filterPanel.add(last);

        JPanel options = getOptionPanel(choice);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        add(filterPanel, gbc);
        gbc.gridy = 1;
        add(options, gbc);
    }

    private JPanel getOptionPanel(int choice) {
        JPanel options = new JPanel();
        JButton bView = new JButton();
        if (choice == 1)
            bView.setText("View current users");
        else
            bView.setText("View new users");
        bView.addActionListener(e -> {
            Component component = (Component) e.getSource();
            HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

            if (choice == 1)
                homeFrame.replace(new CurrentUserPanel(first.getDate(), last.getDate()));
            else
                homeFrame.replace(new NewUserPanel(first.getDate(), last.getDate()));

        });
        JButton bReturn = new JButton("Return");
        bReturn.addActionListener(e -> {
            Component component = (Component) e.getSource();
            HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

            homeFrame.replace(new HomePanel(homeFrame));
        });
        options.add(bView);
        options.add(bReturn);
        return options;
    }
}
