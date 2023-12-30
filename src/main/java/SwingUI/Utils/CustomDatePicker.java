package SwingUI.Utils;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class CustomDatePicker extends JPanel {
    private final UtilDateModel model;

    public CustomDatePicker() {
        model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new JFormattedTextField.AbstractFormatter() {
            @Override
            public Object stringToValue(String text) throws ParseException {
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                return format.parse(text);
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if (value == null) return "";

                Calendar cal = (Calendar) value;
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                return format.format(cal.getTime());
            }
        });

        model.setSelected(true);
        datePicker.setVisible(true);
        add(datePicker);
    }

    public Date getDate() {
        return model.getValue();
    }

    public void setDate(int year, int month, int day) {
        model.setDate(year, month - 1, day);
    }

    public void setDate(Date date) {
        if (date == null) return;
        model.setValue(date);
    }
}
