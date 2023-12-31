package SwingUI.Admin.CurrentUser;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static SwingUI.Utils.CustomChart.createChart;

public class CurrentUserChart extends JPanel {
    public CurrentUserChart() {
        setSize(950, 650);
        setLayout(new BorderLayout());

        JPanel chooseYear = new JPanel();
        chooseYear.add(new JTextField("Input year"));
        chooseYear.add(new JButton("Get chart"));
        add(chooseYear, BorderLayout.NORTH);

        List<Object[]> data = new ArrayList<>();
        Object[] obj = {1, 2};
        data.add(obj);
        ChartPanel chartPanel = new ChartPanel(createChart("Active users", "Month", "Quantity", data));
        chartPanel.setPreferredSize(new Dimension(950, 550));
        add(chartPanel, BorderLayout.CENTER);

        JPanel options = new JPanel();
        options.add(new JButton("Return"));
        add(options, BorderLayout.SOUTH);
    }
}
