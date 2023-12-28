package SwingUI.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.UserService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;

public class CustomTableModel extends AbstractTableModel {
    private boolean editable;
    private String[] columnNames;
    private List<Object[]> data;
    private int _COL;

    public CustomTableModel(String[] columnNames, List<Object[]> data, boolean editable, int col) {
        this.columnNames = columnNames;
        this.data = data;
        this.editable = editable;
        this._COL = col;
    }

    public void addRow(Object[] obj) {
        data.add(obj);
    }
    public void removeRow(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data.get(row)[col];
    }

    /*
     * JTable uses this method to determine the default renderer/ editor for
     * each cell. If we didn't implement this method, then the last column
     * would contain text ("true"/"false"), rather than a checkbox.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (!editable)
            return false;

        if (_COL == 1) {
            return (col > _COL) && (col < 6);
        }

        return col > _COL;
    }

    /*
     * Don't need to implement this method unless your table's data can
     * change.
     */
    public void setValueAt(Object value, int row, int col) {
        boolean DEBUG = true;

        if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col
                    + " to " + value + " (an instance of "
                    + value.getClass() + ")");
        }

        if (value.equals(data.get(row)[col]))
            return;

        if (_COL == 1) {
            Long id = (Long) data.get(row)[0];
            System.out.println(id);
            System.out.println(Account.getInstance().getUserName());
            System.out.println(Account.getInstance().getPassWord());
            User u;
            try {
                u = UserService.getInstance().findById(id);
                if (col == 2) {
                    if (((String) value).isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Full name must not be null");
                        return;
                    }
                    String fullName = (String) value;
                    u.setFullName(fullName);
                } else if (col == 3) {
                    Date dob = DateAndString.StringtoDate((String) value, "dd/MM/yyyy");
                    if (dob == null) {
                        JOptionPane.showMessageDialog(null, "Date must be dd/MM/yyy");
                        return;
                    }

                    u.setBirthDay(dob);
                } else if (col == 4) {
                    if (((String) value).equalsIgnoreCase("male") || ((String) value).equalsIgnoreCase("female")) {
                        boolean gender = ((String) value).equalsIgnoreCase("female");
                        u.setGender(gender);
                    } else {
                        JOptionPane.showMessageDialog(null, "Gender must be male or female");
                        return;
                    }
                } else {
                    String email = (String) value;
                    u.setEmail(email);
                }
                var res = UserService.getInstance().updateUser(u, id);
                if (res != null) {
                    JOptionPane.showMessageDialog(null, "User updated");
                    data.get(row)[col] = value;
                    fireTableCellUpdated(row, col);
                } else
                    JOptionPane.showMessageDialog(null, "Update failed");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else if (_COL == 2) {
            // xu ly ban user
        }

        if (DEBUG) {
            System.out.println("New value of data:");
            printDebugData();
        }
    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + data.get(i)[j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
