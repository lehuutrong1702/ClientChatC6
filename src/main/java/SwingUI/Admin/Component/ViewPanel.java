package SwingUI.Admin.Component;

import SwingUI.Utils.CustomTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewPanel extends JPanel {
    private JTable table;

    public ViewPanel(String[] columnNames, List<Object[]> data, boolean editable) {
        setOpaque(true);

        CustomTableModel model = new CustomTableModel(columnNames, data, editable);
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(900, 500));

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    public JTable getTable() {
        return table;
    }
}
