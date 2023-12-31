package SwingUI.Admin.CurrentUser;

import SwingUI.Admin.HomeFrame;
import SwingUI.Admin.HomePanel;
import SwingUI.Utils.CustomDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoosePanel extends JPanel {
    public ChoosePanel() {
        setLayout(new GridBagLayout());

        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("From"));
        filterPanel.add(new CustomDatePicker());
        filterPanel.add(new JLabel("to"));
        filterPanel.add(new CustomDatePicker());

        JPanel options = getOptionPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        add(filterPanel, gbc);
        gbc.gridy = 1;
        add(options, gbc);
    }

    private static JPanel getOptionPanel() {
        JPanel options = new JPanel();
        JButton bView = new JButton("View current users");
        bView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

                homeFrame.replace(new CurrentUserPanel());
            }
        });
        JButton bReturn = new JButton("Return");
        bReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

                homeFrame.replace(new HomePanel(homeFrame));
            }
        });
        options.add(bView);
        options.add(bReturn);
        return options;
    }
}
