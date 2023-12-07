package SwingUI.Home.Component;

import SwingUI.Utils.RoundedCornerBorder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatsystem.model.User;
import com.teamc6.chatsystem.properties.Account;
import com.teamc6.chatsystem.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class InfoPanel extends JPanel {
    private final JLabel lbMain;
    private final JButton bUpdate;
    private final JTextField tfName;
    private final JTextField tfPassword;
    private final JTextField tfBirthday;
    private final JTextField tfEmail;
    private User u;

    public InfoPanel() {
        lbMain = new JLabel("User info");
        tfName = new JTextField() {
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setPaint(getBackground());
                    g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                            0, 0, getWidth() - 1, getHeight() - 1));
                    g2.dispose();
                }
                super.paintComponent(g);
            }

            public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setBorder(new RoundedCornerBorder());
            }
        };

        tfPassword = new JTextField() {
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setPaint(getBackground());
                    g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                            0, 0, getWidth() - 1, getHeight() - 1));
                    g2.dispose();
                }
                super.paintComponent(g);
            }

            public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setBorder(new RoundedCornerBorder());
            }
        };

        tfBirthday = new JTextField() {
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setPaint(getBackground());
                    g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                            0, 0, getWidth() - 1, getHeight() - 1));
                    g2.dispose();
                }
                super.paintComponent(g);
            }

            public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setBorder(new RoundedCornerBorder());
            }
        };

        tfEmail = new JTextField() {
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setPaint(getBackground());
                    g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                            0, 0, getWidth() - 1, getHeight() - 1));
                    g2.dispose();
                }
                super.paintComponent(g);
            }

            public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setBorder(new RoundedCornerBorder());
            }
        };

        bUpdate = new JButton("Update") {
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setPaint(getBackground());
                    g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                            0, 0, getWidth() - 1, getHeight() - 1));
                    g2.dispose();
                }
                super.paintComponent(g);
            }

            public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setBorder(new RoundedCornerBorder());
            }
        };

        init();


    }

    public void getInfo() throws JsonProcessingException {
        u = UserService.getInstance().findById(Account.getInstance().getId());
    }

    public JButton getbUpdate() {
        return bUpdate;
    }

    public JTextField gettfName() {
        return tfName;
    }


    public void addEventListeners() {
        //submit button action listener
        tfName.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                if (tfName.getText().isEmpty()) {
                    tfName.setText("Search");
                    tfName.setForeground(Color.gray);
                }
            }

            public void focusGained(FocusEvent e) {
                if (tfName.getText().equals("Search")) {
                    tfName.setText("");
                    tfName.setForeground(Color.black);
                }
            }
        });
    }

    public void init() {
        setLayout(new GridBagLayout());

        lbMain.setFont(new Font("Arial", Font.BOLD, 60));
        lbMain.setForeground(Color.BLACK);

        bUpdate.setPreferredSize(new Dimension(100, 40));
        bUpdate.setBackground(new Color(66, 245, 114));
        bUpdate.setFocusPainted(false);

        tfName.setText("Full name");
        tfName.setPreferredSize(new Dimension(300, 40));
        tfName.setForeground(Color.gray);

        tfPassword.setText("Password");
        tfPassword.setPreferredSize(new Dimension(300, 40));
        tfPassword.setForeground(Color.gray);

        tfBirthday.setText("Birthday");
        tfBirthday.setPreferredSize(new Dimension(300, 40));
        tfBirthday.setForeground(Color.gray);

        tfEmail.setText("Email");
        tfEmail.setPreferredSize(new Dimension(300, 40));
        tfEmail.setForeground(Color.gray);


        Insets bUpdateInsets = new Insets(10, 110, 0, 0);
        Insets textInsets = new Insets(2, 10, 5, 10);
        Insets labelInsets = new Insets(-150, 10, 0, 10);

        GridBagConstraints input = new GridBagConstraints();
        input.anchor = GridBagConstraints.CENTER;
        input.insets = labelInsets;
        input.gridy = 0;
        add(lbMain,input);

        input.gridx = 0;
        input.gridy = 1;
        input.insets = textInsets;
        JLabel name = new JLabel("Full name");
        add(name, input);

        input.gridy = 2;
        input.insets = textInsets;
        add(tfName, input);

        input.gridy = 3;
        JLabel password = new JLabel("Password");
        add(password, input);

        input.gridy = 4;
        input.insets = textInsets;
        add(tfPassword, input);

        input.gridy = 5;
        JLabel birthday = new JLabel("Birthday");
        add(birthday, input);

        input.gridy = 6;
        input.insets = textInsets;
        add(tfBirthday, input);

        input.gridy = 7;
        JLabel email = new JLabel("Email");
        add(email, input);

        input.gridy = 8;
        input.insets = textInsets;
        add(tfEmail, input);

        input.insets = bUpdateInsets;
        input.anchor = GridBagConstraints.WEST;
        input.gridy = 9;
        add(bUpdate, input);

        addEventListeners();
    }
}
