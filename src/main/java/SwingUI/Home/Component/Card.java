package SwingUI.Home.Component;

import com.teamc6.chatsystem.model.GroupChat;
import com.teamc6.chatsystem.model.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Card<T> extends JPanel {
    public Card(T item) {
        setLayout(new BorderLayout());
        setBackground(new Color(190, 255, 152));
        Border border = new MatteBorder(0, 0, 1, 0, Color.GRAY);
        Border margin = new EmptyBorder(10, 5, 10, 0);
        setBorder(new CompoundBorder(border, margin));

        if (item instanceof User) {
            System.out.println(((User) item).getFullName());
            add(new JLabel(((User) item).getFullName()), BorderLayout.CENTER);
        } else if (item instanceof GroupChat g) {
            add(new JLabel(g.getGroupName()), BorderLayout.CENTER);
        }

        add(new JLabel("Online"), BorderLayout.SOUTH);
        add(new JLabel("\uD83D\uDC64"), BorderLayout.WEST);
    }
}
