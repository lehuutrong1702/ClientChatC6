package SwingUI.Utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;

public class ChartUtils {
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
                dataset.addValue((int) item[0], "Numbers", item[1].toString());
            }
        }

        return dataset;
    }
}