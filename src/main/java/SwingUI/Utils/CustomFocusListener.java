package SwingUI.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CustomFocusListener implements FocusListener {
    private final String string;
    private final JTextField tf;
    public CustomFocusListener(JTextField tf, String str) {
        string = str;
        this.tf  = tf;
    }

    public void focusLost(FocusEvent e) {
        if (tf.getText().isEmpty()) {
            tf.setText(string);
            tf.setForeground(Color.gray);
        }
    }

    public void focusGained(FocusEvent e) {
        if (tf.getText().equals(string)) {
            tf.setText("");
            tf.setForeground(Color.black);
        }
    }
}
