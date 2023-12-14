package SwingUI.SignUp;

import Controller.SignUpControl;
import SwingUI.Utils.CustomDatePicker;
import SwingUI.Utils.CustomFocusListener;
import SwingUI.Utils.RoundedCornerBorder;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class SignUpFrame extends JFrame {
    private final JLabel lbMain;
    private final JButton bSignIn;
    private final JButton bSignUp;
    private final JTextField tfUsername;
    private final JTextField tfFullname;
    private final JTextField tfEmail;

    private final CustomDatePicker datePicker;
    private final ButtonGroup gender;
    private final JRadioButton male;
    private final JRadioButton female;
    private final SignUpControl signUpControl;

    public SignUpFrame() throws IOException {
        setTitle("Sign Up");
        signUpControl = new SignUpControl(this);
        lbMain = new JLabel("SIGN UP");

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

        tfFullname = new JTextField() {
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

        datePicker = new CustomDatePicker();
        male = new JRadioButton("Male");
        male.setActionCommand("Male");
        female = new JRadioButton("Female");
        female.setActionCommand("Female");
        gender = new ButtonGroup();
        gender.add(male);
        gender.add(female);
        male.setSelected(true);

        bSignIn = new JButton("Return to sign in") {
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

        bSignUp = new JButton("Sign up") {
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
            final BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(SignUpFrame.class.getClassLoader().getResourceAsStream("background.jpg")));

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bufferedImage, 0, 0, this);
            }
        });

        init();
    }

    public JLabel getLbMain() {
        return lbMain;
    }

    public JButton getbSignIn() {
        return bSignIn;
    }

    public JTextField getTfFullname() {
        return tfFullname;
    }

    public JTextField getTfEmail() {
        return tfEmail;
    }

    public CustomDatePicker getDatePicker() {
        return datePicker;
    }

    public ButtonGroup getGender() {
        return gender;
    }

    public JRadioButton getMale() {
        return male;
    }

    public JRadioButton getFemale() {
        return female;
    }

    public SignUpControl getSignUpControl() {
        return signUpControl;
    }

    public JButton getbSignUp() {
        return bSignUp;
    }

    public JTextField getTfUsername() {
        return tfUsername;
    }


    public void addEventListeners() {
        //submit button action listener
        bSignUp.addActionListener(signUpControl);
        bSignIn.addActionListener(signUpControl);

        tfUsername.addFocusListener(new CustomFocusListener(tfUsername, "Enter your username"));
        tfFullname.addFocusListener(new CustomFocusListener(tfFullname, "Enter your full name"));
        tfEmail.addFocusListener(new CustomFocusListener(tfEmail, "Enter your email"));
    }

    public void init() {
        lbMain.setFont(new Font("Arial", Font.BOLD, 60));
        lbMain.setForeground(Color.white);

        tfUsername.setPreferredSize(new Dimension(250, 35));
        tfFullname.setPreferredSize(new Dimension(250, 35));
        tfEmail.setPreferredSize(new Dimension(250, 35));
        datePicker.setPreferredSize(new Dimension(250, 35));

        bSignIn.setPreferredSize(new Dimension(250, 35));
        bSignIn.setBackground(new Color(66, 245, 114));
        bSignIn.setFocusPainted(false);

        bSignUp.setPreferredSize(new Dimension(250, 35));
        bSignUp.setBackground(new Color(66, 245, 114));
        bSignUp.setFocusPainted(false);

        tfUsername.setText("Enter your username");
        tfUsername.setForeground(Color.gray);
        tfFullname.setText("Enter your full name");
        tfFullname.setForeground(Color.gray);
        tfEmail.setText("Enter your email");
        tfEmail.setForeground(Color.gray);

        male.setOpaque(false);
        female.setOpaque(false);

        setLayout(new GridBagLayout());

        Insets labelInsets = new Insets(-150, 10, 0, 10);
        Insets textInsets = new Insets(10, 10, 5, 10);
        Insets maleInsets = new Insets(5, -150, -10, 0);
        Insets bSignInInsets = new Insets(20, 10, 10, 10);
        Insets bSignUpInsets = new Insets(0, 10, 0, 10);

        GridBagConstraints input = new GridBagConstraints();
        input.anchor = GridBagConstraints.CENTER;
        input.insets = labelInsets;
        input.gridy = 0;
        add(lbMain, input);

        input.insets = textInsets;
        input.gridy = 1;
        add(tfUsername, input);

        input.insets = textInsets;
        input.gridy = 3;
        add(tfFullname, input);

        JLabel date = new JLabel("Birth date");
        input.gridy = 4;
        add(date, input);

        input.insets = textInsets;
        input.gridy = 5;
        add(datePicker, input);

        input.insets = textInsets;
        input.gridy = 6;
        add(tfEmail, input);

        input.insets = maleInsets;
        input.gridy = 7;
        add(male, input);

        input.insets = maleInsets;
        input.gridy = 7;
        input.gridx = 1;
        add(female, input);

        input.insets = bSignInInsets;
        input.anchor = GridBagConstraints.WEST;
        input.gridx = 0;
        input.gridy = 9;
        add(bSignUp, input);

        input.insets = bSignUpInsets;
        input.anchor = GridBagConstraints.WEST;
        input.gridx = 0;
        input.gridy = 10;
        add(bSignIn, input);

        setSize(950, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        requestFocus();
        addEventListeners();
    }
}