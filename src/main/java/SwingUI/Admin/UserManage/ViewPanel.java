package SwingUI.Admin.UserManage;

import SwingUI.Utils.CustomTableModel;

import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {
    private JTable table;

    public ViewPanel() {
        setOpaque(true);
        table = new JTable(new CustomTableModel());
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
