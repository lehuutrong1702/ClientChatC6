package SwingUI.Admin.Component;

import SwingUI.Utils.CustomTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class ViewPanel extends JPanel {
    private JTable table;
    private CustomTableModel model;
    private TableRowSorter<TableModel> sorter;
    public ViewPanel(String[] columnNames, List<Object[]> data, boolean editable, int col) {
        setOpaque(true);

        model = new CustomTableModel(columnNames, data, editable, col);
        table = new JTable(model);
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(900, 500));

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    public JTable getTable() {
        return table;
    }

    public CustomTableModel getModel() {
        return model;
    }

    public void setSortKeys(List<RowSorter.SortKey> sortKeys) {
        if (model.getRowCount() == 0)
            return;
        sorter.setSortKeys(sortKeys);
    }

    public void removeAllSortKeys() {
        if (model.getRowCount() == 0)
            return;
        sorter.setSortKeys(null);
    }

    public void filterText(String text, int col) {
        if (model.getRowCount() == 0)
            return;
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, col));
    }

    public void filterBoolean(boolean value, int col) {
        if (model.getRowCount() == 0)
            return;
        RowFilter<Object,Object> booleanFilter = new RowFilter<Object,Object>() {
            public boolean include(Entry<? extends Object, ? extends Object> entry) {
                return entry.getValue(col).equals(value);
            }
        };
        sorter.setRowFilter(booleanFilter);
    }

    public void removeAllFilters() {
        if (model.getRowCount() == 0)
            return;
        sorter.setRowFilter(null);
    }
}
