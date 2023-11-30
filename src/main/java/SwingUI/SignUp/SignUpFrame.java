package SwingUI.SignUp;

import SwingUI.Utils.RoundedCornerBorder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.regex.Pattern;

public class SignUpFrame extends JFrame {
    JButton loginButton;
    JTextField email;
    JPasswordField password;
    JLabel usernameError;
    JLabel passwordError;


    public SignUpFrame() throws IOException {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        email = new JTextField() {
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

        loginButton = new JButton("Sign Up") {
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
        usernameError = new JLabel();
        passwordError = new JLabel();

        setContentPane(new JPanel() {
            BufferedImage bufferedImage = ImageIO.read(SignUpFrame.class.getClassLoader().getResourceAsStream("background.jpg"));
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
                String data = "Username: " + email.getText();
                data += "\n" + "Password: " + password.getText();
                JOptionPane.showMessageDialog(null, data);
            }
        });

        //email validation listener
        email.getDocument().addDocumentListener(new DocumentListener() {

            public void removeUpdate(DocumentEvent e) {
                if(email.getText().length() > 0 && !email.getText().equals("Enter your email")) {
                    if(validateMail(email.getText())) {
                        usernameError.setForeground(new Color(50, 168, 58));
                        usernameError.setText("Email is valid");
                    }
                    else {
                        usernameError.setForeground(Color.RED);
                        usernameError.setText("Email is not valid");
                    }
                }
                else {
                    usernameError.setText("");
                }
            }

            public void insertUpdate(DocumentEvent e) {
                if(email.getText().length() > 0 && !email.getText().equals("Enter your email")) {
                    if(validateMail(email.getText())) {
                        usernameError.setForeground(new Color(50, 168, 58));
                        usernameError.setText("Email is valid");
                    }
                    else {
                        usernameError.setForeground(Color.RED);
                        usernameError.setText("Email is not valid");
                    }
                }
                else {
                    usernameError.setText("");
                }
            }

            public void changedUpdate(DocumentEvent e) {
                if(email.getText().length() > 0  && !email.getText().equals("Enter your email")) {
                    if(validateMail(email.getText())) {
                        usernameError.setForeground(new Color(50, 168, 58));
                        usernameError.setText("Email is valid");
                    }
                    else {
                        usernameError.setForeground(Color.RED);
                        usernameError.setText("Email is not valid");
                    }
                }
                else {
                    usernameError.setText("");
                }
            }
        });

        //password validation listener
        password.getDocument().addDocumentListener(new DocumentListener() {

            public void removeUpdate(DocumentEvent e) {
                if(password.getText().length() > 0  && !password.getText().equals("Enter your password")) {
                    if(validatePassword(password.getText())) {
                        passwordError.setForeground(new Color(50, 168, 58));
                        passwordError.setText("Password is valid");
                    }
                }
                else {
                    passwordError.setText("");
                }
            }

            public void insertUpdate(DocumentEvent e) {
                if(password.getText().length() > 0  && !password.getText().equals("Enter your password")) {
                    if(validatePassword(password.getText())) {
                        passwordError.setForeground(new Color(50, 168, 58));
                        passwordError.setText("Password is valid");
                    }
                }
                else {
                    passwordError.setText("");
                }
            }

            public void changedUpdate(DocumentEvent e) {
                if(password.getText().length() > 0  && !password.getText().equals("Enter your password")) {
                    if(validatePassword(password.getText())) {
                        passwordError.setForeground(new Color(50, 168, 58));
                        passwordError.setText("Password is valid");
                    }
                }
                else {
                    passwordError.setText("");
                }
            }
        });

        email.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                if(email.getText().equals("")) {
                    email.setText("Enter your email");
                    email.setForeground(Color.gray);
                }
            }

            public void focusGained(FocusEvent e) {
                if(email.getText().equals("Enter your email")) {
                    email.setText("");
                    email.setForeground(Color.black);
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

    private boolean validateMail(String mail) {
        String regExp = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regExp);
        return pattern.matcher(mail).matches();
    }

    private boolean validatePassword(String text) {
        passwordError.setForeground(Color.RED);
        if(text.length() < 8) {
            passwordError.setText("Password must be of length 8");
            return false;
        }
        else if(!text.matches(".*[a-zA-Z]+.*")) {
            passwordError.setText("Password must contain alphabates");
            return false;
        }
        else if(!text.matches(".*\\d.*")) {
            passwordError.setText("Password must contain digits");
            return false;
        }

        else
            return true;
    }

    public void init() {
        email.setPreferredSize(new Dimension(250,35));
        password.setPreferredSize(new Dimension(250,35));
        loginButton.setPreferredSize(new Dimension(250,35));
        loginButton.setBackground(new Color(66, 245, 114));
        loginButton.setFocusPainted(false);

        email.setText("Enter your email");
        email.setForeground(Color.gray);
        password.setText("Enter your password");
        password.setForeground(Color.gray);
        password.setEchoChar((char)0);

        usernameError.setFont(new Font("SansSerif", Font.BOLD, 11));
        usernameError.setForeground(Color.RED);

        passwordError.setFont(new Font("SansSerif", Font.BOLD, 11));
        passwordError.setForeground(Color.RED);

        setLayout(new GridBagLayout());

        Insets textInsets = new Insets(10, 10, 5, 10);
        Insets buttonInsets = new Insets(20, 10, 10, 10);
        Insets errorInsets = new Insets(0,20,0,0);

        GridBagConstraints input = new GridBagConstraints();
        input.anchor = GridBagConstraints.CENTER;
        input.insets = textInsets;
        input.gridy = 1;
        add(email,input);

        input.gridy = 2;
        input.insets = errorInsets;
        input.anchor = GridBagConstraints.WEST;
        add(usernameError,input);

        input.gridy = 3;
        input.insets = textInsets;
        input.anchor = GridBagConstraints.CENTER;
        add(password,input);

        input.gridy = 4;
        input.insets = errorInsets;
        input.anchor = GridBagConstraints.WEST;
        add(passwordError,input);

        input.insets = buttonInsets;
        input.anchor = GridBagConstraints.WEST;
        input.gridx = 0;
        input.gridy = 5;
        add(loginButton,input);

        setSize(950,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        requestFocus();
        addEventListeners();
    }

    public static void main(String args[]) {
        try {
            new SignUpFrame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}