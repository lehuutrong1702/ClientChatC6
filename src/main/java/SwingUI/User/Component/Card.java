package SwingUI.User.Component;

import Controller.User.CardControl;
import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.model.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Card<T> extends JPanel {
    JLabel status;
    public Card(T item, ListComponent listComponent) {
        setLayout(new BorderLayout());
        Border border = new MatteBorder(0, 0, 1, 0, Color.GRAY);
        Border margin = new EmptyBorder(10, 5, 10, 0);
        setBorder(new CompoundBorder(border, margin));
        add(new JLabel("\uD83D\uDC64"), BorderLayout.WEST);

        if (item instanceof User u) {
            this.status = new JLabel();
            add(this.status, BorderLayout.SOUTH);
            add(new JLabel(u.getFullName()), BorderLayout.CENTER);
            ChangeState(u.isActive());
        } else if (item instanceof GroupChat g) {
            add(new JLabel(g.getGroupName()), BorderLayout.CENTER);
        }

        addMouseListener(new CardControl<>(
                listComponent.getSidePanel().getHomeFrame().getMainPanel(),
                item
        ));
//        addPropertyChangeListener(e -> {
//            ChangeState((boolean)e.getNewValue());
//        });
    }

    private void ChangeState(boolean isOnline){
        if(isOnline) {
            status.setText("Online");
            setBackground(new Color(190, 255, 152));
        }else{
            status.setText("Offline");
            setBackground(new Color(190, 50, 50));
        }
    }
}
