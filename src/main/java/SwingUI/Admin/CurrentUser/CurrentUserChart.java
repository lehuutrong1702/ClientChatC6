package SwingUI.Admin.CurrentUser;

import SwingUI.Admin.HomeFrame;
import SwingUI.Admin.HomePanel;
import SwingUI.Utils.ChartUtils;
import SwingUI.Utils.CustomFocusListener;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CurrentUserChart extends JPanel {
    private ChartPanel chartPanel;

    public CurrentUserChart() {
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

        chartPanel = new ChartPanel(ChartUtils.createChart("Active users", "Month", "Quantity", null));
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
        for (int i = 1; i < 13; i++) {
            Object[] row = { (int) Math.floor(i * Math.random()), i};
            data.add(row);
        }
        chartPanel.setChart(ChartUtils.createChart("Active users", "Month", "Quantity", data));
        chartPanel.revalidate(); chartPanel.repaint();
    }
}
