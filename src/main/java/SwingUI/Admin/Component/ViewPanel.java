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
        table.setPreferredScrollableViewportSize(new Dimension(900, 500));
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        removeAllSortKeys();
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

    public void setSortKeys(List<RowSorter.SortKey> sortKeys, int col) {
        if (model.getRowCount() == 0)
            return;
        sorter.setSortable(col, true);
        sorter.setSortKeys(sortKeys);
    }

    public void removeAllSortKeys() {
        if (model.getRowCount() == 0)
            return;
        for (int i = 0; i < model.getColumnCount(); i++) {
            sorter.setSortable(i, false);
        }
        sorter.setSortKeys(null);
    }

    public void filterText(String text, int col) {
        if (model.getRowCount() == 0)
            return;
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, col));
    }

    public void filterNumber(int type, int num, int col) {
        if (model.getRowCount() == 0)
            return;
        if (type == 0)
            sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.BEFORE, num, col));
        else if (type == 1)
            sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, num, col));
        else
            sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.BEFORE, num, col));
    }

    public void filterBoolean(boolean value, int col) {
        if (model.getRowCount() == 0)
            return;
        RowFilter<Object, Object> booleanFilter = new RowFilter<Object, Object>() {
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
