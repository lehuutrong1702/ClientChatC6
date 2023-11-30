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
import java.util.Objects;

public class SignInFrame extends JFrame {
    private JLabel mainLabel;
    private JButton bSignIn;
    private JButton bSignUp;
    private JTextField tfUsername;
    private JPasswordField pfPassword;

    public SignInFrame() throws IOException {
        setTitle("Sign In");
        mainLabel = new JLabel("SIGN IN");

        tfUsername = new JTextField() {
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

        pfPassword = new JPasswordField() {
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

        bSignIn = new JButton("Sign in") {
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

        bSignUp = new JButton("Create new account") {
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
            BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(SignInFrame.class.getClassLoader().getResourceAsStream("background.jpg")));
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bufferedImage,0,0,this);
            }
        });

        init();
    }

    public void addEventListeners() {
        //submit button action listener
        bSignIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        bSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        tfUsername.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                if(tfUsername.getText().isEmpty()) {
                    tfUsername.setText("Enter your username");
                    tfUsername.setForeground(Color.gray);
                }
            }

            public void focusGained(FocusEvent e) {
                if(tfUsername.getText().equals("Enter your username")) {
                    tfUsername.setText("");
                    tfUsername.setForeground(Color.black);
                }
            }
        });

        pfPassword.addFocusListener(new FocusListener() {

            public void focusLost(FocusEvent e) {
                if(pfPassword.getText().equals("")) {
                    pfPassword.setText("Enter your password");
                    pfPassword.setForeground(Color.gray);
                    pfPassword.setEchoChar((char)0);
                }
            }

            public void focusGained(FocusEvent e) {
                if(pfPassword.getText().equals("Enter your password")) {
                    pfPassword.setText("");
                    pfPassword.setEchoChar('*');
                    pfPassword.setForeground(Color.black);
                }
            }
        });
    }

    public void init() {
        mainLabel.setFont(new Font("Arial", Font.BOLD, 60));
        mainLabel.setForeground(Color.white);

        tfUsername.setPreferredSize(new Dimension(250,35));
        pfPassword.setPreferredSize(new Dimension(250,35));

        bSignIn.setPreferredSize(new Dimension(250,35));
        bSignIn.setBackground(new Color(66, 245, 114));
        bSignIn.setFocusPainted(false);

        bSignUp.setPreferredSize(new Dimension(250,35));
        bSignUp.setBackground(new Color(66, 245, 114));
        bSignUp.setFocusPainted(false);

        tfUsername.setText("Enter your username");
        tfUsername.setForeground(Color.gray);
        pfPassword.setText("Enter your password");
        pfPassword.setForeground(Color.gray);
        pfPassword.setEchoChar((char)0);

        setLayout(new GridBagLayout());

        Insets labelInsets = new Insets(-200, 10, 0, 10);
        Insets textInsets = new Insets(10, 10, 5, 10);
        Insets bSignInInsets = new Insets(20, 10, 10, 10);
        Insets bSignUpInsets = new Insets(0, 10, 0, 10);

        GridBagConstraints input = new GridBagConstraints();
        input.anchor = GridBagConstraints.CENTER;
        input.insets = labelInsets;
        input.gridy = 0;
        add(mainLabel,input);

        input.anchor = GridBagConstraints.CENTER;
        input.insets = textInsets;
        input.gridy = 3;
        add(tfUsername,input);

        input.insets = textInsets;
        input.anchor = GridBagConstraints.CENTER;
        input.gridy = 5;
        add(pfPassword,input);

        input.insets = bSignInInsets;
        input.anchor = GridBagConstraints.WEST;
        input.gridx = 0;
        input.gridy = 7;
        add(bSignIn,input);

        input.insets = bSignUpInsets;
        input.anchor = GridBagConstraints.WEST;
        input.gridx = 0;
        input.gridy = 8;
        add(bSignUp,input);

        setSize(950,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        requestFocus();
        addEventListeners();
    }
}