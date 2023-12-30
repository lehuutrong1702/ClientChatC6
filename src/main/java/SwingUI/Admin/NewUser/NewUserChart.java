package SwingUI.Admin.NewUser;

import SwingUI.Admin.HomeFrame;
import SwingUI.Admin.HomePanel;
import SwingUI.Utils.ChartUtils;
import SwingUI.Utils.CustomFocusListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.model.UserActiveSession;
import com.teamc6.chatSystem.service.UserActiveSessionService;
import com.teamc6.chatSystem.service.UserService;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NewUserChart extends JPanel {
    private ChartPanel chartPanel;

    public NewUserChart() {
        setSize(950, 650);
        setLayout(new BorderLayout());

        JPanel chooseYear = new JPanel();
        JTextField year = new JTextField("Input year");
        year.addFocusListener(new CustomFocusListener(year, "Input year"));
        chooseYear.add(year);

        JButton bGet = new JButton("Get chart");
        bGet.addActionListener(e -> {
            if (year.getText().equals("Input year") || year.getText().isEmpty() || year.getText().length() != 4)
                return;

            int parsedYear = Integer.parseInt(year.getText());
            getChart(parsedYear);
        });
        chooseYear.add(bGet);
        add(chooseYear, BorderLayout.NORTH);

        chartPanel = new ChartPanel(ChartUtils.createChart("New users", "Month", "Quantity", null));
        chartPanel.setPreferredSize(new Dimension(920, 500));
        add(chartPanel, BorderLayout.CENTER);

        JPanel options = new JPanel();
        JButton bReturn = new JButton("Return");
        bReturn.addActionListener(e -> {
            Component component = (Component) e.getSource();
            HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

            homeFrame.replace(new HomePanel(homeFrame));
        });
        options.add(bReturn);
        add(options, BorderLayout.SOUTH);
    }

    private void getChart(int year) {
        List<Object[]> data = new ArrayList<>();
        List<User> newUsers;
        try {
            newUsers = UserService.getInstance().getByYear(year);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        int[] months = {0,0,0,0,0,0,0,0,0,0,0,0};
        for (var user: newUsers) {
            months[user.getTimeRegister().getMonth()]++;
        }
        for (int i = 0; i < 12; i++) {
            Object[] row = {months[i], i + 1};
            data.add(row);
        }
        chartPanel.setChart(ChartUtils.createChart("New users", "Month", "Quantity", data));
        chartPanel.revalidate(); chartPanel.repaint();
    }
}
