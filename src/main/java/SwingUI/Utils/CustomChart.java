package SwingUI.Utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomChart extends ChartPanel {
    public CustomChart(String title, String categoryAxisLabel, String valueAxisLabel, List<Object[]> data) {
        super(createChart(title, categoryAxisLabel, valueAxisLabel, data));
    }

    public static JFreeChart createChart(String title, String categoryAxisLabel, String valueAxisLabel, List<Object[]> data) {
        return ChartFactory.createBarChart(
                "New users",
                "Month", "Numbers",
                createDataset(data), PlotOrientation.VERTICAL, false, false, false);
    }

    private static CategoryDataset createDataset(List<Object[]> data) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (data != null) {
            for (var item : data) {
                dataset.addValue((Integer) item[0], "Numbers", item[1].toString());
            }
        }

        return dataset;
    }

    public static void main(String[] args) {
        List<Object[]> data = new ArrayList<>();
        Object[] obj = {1, 2};
        data.add(obj);
        ChartPanel chartPanel = new ChartPanel(createChart("New users", "Month", "Quantity", data));
        chartPanel.setPreferredSize(new Dimension(560, 367));
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}