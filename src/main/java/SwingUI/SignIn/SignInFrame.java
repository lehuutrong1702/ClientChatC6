package SwingUI.SignIn;

import SwingUI.Utils.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SignInFrame extends JFrame {
    private JLabel mainLabel;
    private JButton loginButton;
    private JTextField username;
    private JPasswordField password;

    public SignInFrame() throws IOException {
        setTitle("Sign In");
        mainLabel = new JLabel("SIGN IN");
        username = new JTextField() {
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
        password = new JPasswordField() {
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

        loginButton = new JButton("Sign In") {
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

        setContentPane(new JPanel() {
            BufferedImage bufferedImage = ImageIO.read(SignInFrame.class.getClassLoader().getResourceAsStream("background.jpg"));
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bufferedImage,0,0,this);
            }
        });
        init();
    }

    public void addEventListeners() {
        //submit button action listener
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String data = "Username: " + username.getText();
                data += "\n" + "Password: " + password.getText();
                JOptionPane.showMessageDialog(null, data);
            }
        });

        username.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                if(username.getText().equals("")) {
                    username.setText("Enter your username");
                    username.setForeground(Color.gray);
                }
            }

            public void focusGained(FocusEvent e) {
                if(username.getText().equals("Enter your username")) {
                    username.setText("");
                    username.setForeground(Color.black);
                }
            }
        });

        password.addFocusListener(new FocusListener() {

            public void focusLost(FocusEvent e) {
                if(password.getText().equals("")) {
                    password.setText("Enter your password");
                    password.setForeground(Color.gray);
                    password.setEchoChar((char)0);
                }
            }

            public void focusGained(FocusEvent e) {
                if(password.getText().equals("Enter your password")) {
                    password.setText("");
                    password.setEchoChar('*');
                    password.setForeground(Color.black);
                }
            }
        });
    }

    public void init() {
        mainLabel.setFont(new Font("Arial", Font.BOLD, 60));
        mainLabel.setForeground(Color.white);

        username.setPreferredSize(new Dimension(250,35));
        password.setPreferredSize(new Dimension(250,35));

        loginButton.setPreferredSize(new Dimension(250,35));
        loginButton.setBackground(new Color(66, 245, 114));
        loginButton.setFocusPainted(false);

        username.setText("Enter your username");
        username.setForeground(Color.gray);
        password.setText("Enter your password");
        password.setForeground(Color.gray);
        password.setEchoChar((char)0);

        setLayout(new GridBagLayout());

        Insets textInsets = new Insets(10, 10, 5, 10);
        Insets buttonInsets = new Insets(20, 10, 10, 10);
        Insets labelInsets = new Insets(-200, 10, 0, 10);

        GridBagConstraints input = new GridBagConstraints();
        input.anchor = GridBagConstraints.CENTER;
        input.insets = labelInsets;
        input.gridy = 0;
        add(mainLabel,input);

        input.anchor = GridBagConstraints.CENTER;
        input.insets = textInsets;
        input.gridy = 3;
        add(username,input);

        input.insets = textInsets;
        input.anchor = GridBagConstraints.CENTER;
        input.gridy = 5;
        add(password,input);

        input.insets = buttonInsets;
        input.anchor = GridBagConstraints.WEST;
        input.gridx = 0;
        input.gridy = 7;
        add(loginButton,input);

        setSize(950,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        requestFocus();
        addEventListeners();
    }
}