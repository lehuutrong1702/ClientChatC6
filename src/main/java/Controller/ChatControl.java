package Controller;

import SwingUI.Home.HomePanel.MessageUI;
import com.teamc6.chatsystem.model.Connection;

public class ChatControl implements Runnable{
    Connection connection;
    MessageUI ui;
    public ChatControl(Connection cn, MessageUI ui) {
        this.connection = cn;
        this.ui = ui;
    }
    @Override
    public void run() {

    }
}
