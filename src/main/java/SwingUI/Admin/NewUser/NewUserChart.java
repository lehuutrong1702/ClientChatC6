package SwingUI.Admin.NewUser;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static SwingUI.Utils.CustomChart.createChart;

public class NewUserChart extends JPanel {
    public NewUserChart() {
        setSize(950, 650);
        List<Object[]> data = new ArrayList<>();
        Object[] obj = {1, 2};
        data.add(obj);
        ChartPanel chartPanel = new ChartPanel(createChart("New users", "Month", "Quantity", data));
        chartPanel.setPreferredSize(new Dimension(950, 550));
        add(chartPanel);

        JPanel options = new JPanel();
        options.add(new JButton("Return"));
        add(options);
    }
}
