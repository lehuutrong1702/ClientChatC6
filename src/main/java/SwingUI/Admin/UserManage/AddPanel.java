package SwingUI.Admin.UserManage;

import Controller.Admin.UserMange.AddUserControl;
import SwingUI.Admin.HomeFrame;
import SwingUI.Utils.CustomDatePicker;
import SwingUI.Utils.CustomFocusListener;
import SwingUI.Utils.RoundedCornerBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPanel extends JPanel {
    private final JButton bAdd;
    private final JButton bReturn;
    private final JTextField tfUsername;
    private final JTextField tfPassword;
    private final JTextField tfFullname;
    private final JTextField tfEmail;

    private final CustomDatePicker datePicker;
    private final ButtonGroup gender;
    private final JRadioButton male;
    private final JRadioButton female;

    public AddPanel() {
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

        bAdd = new JButton("Add") {
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

        bReturn = new JButton("Return") {
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

    public JButton getbAdd() {
        return bAdd;
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

    public JTextField getTfUsername() {
        return tfUsername;
    }

    public void addEventListeners() {
        tfUsername.addFocusListener(new CustomFocusListener(tfUsername, "Enter username"));
        tfPassword.addFocusListener(new CustomFocusListener(tfPassword, "Enter password"));
        tfFullname.addFocusListener(new CustomFocusListener(tfFullname, "Enter full name"));
        tfEmail.addFocusListener(new CustomFocusListener(tfEmail, "Enter email"));

        bAdd.addActionListener(new AddUserControl());

        bReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                HomeFrame homeFrame = (HomeFrame) SwingUtilities.getRoot(component);

                homeFrame.replace(new UserManagePanel());
            }
        });
    }

    public void init() {
        tfUsername.setPreferredSize(new Dimension(250, 35));
        tfPassword.setPreferredSize(new Dimension(250, 35));
        tfFullname.setPreferredSize(new Dimension(250, 35));
        tfEmail.setPreferredSize(new Dimension(250, 35));
        datePicker.setPreferredSize(new Dimension(250, 35));

        bAdd.setPreferredSize(new Dimension(250, 35));
        bAdd.setBackground(new Color(66, 245, 114));
        bAdd.setFocusPainted(false);
        bReturn.setPreferredSize(new Dimension(250, 35));
        bReturn.setBackground(new Color(66, 245, 114));
        bReturn.setFocusPainted(false);

        tfUsername.setText("Enter username");
        tfUsername.setForeground(Color.gray);
        tfPassword.setText("Enter password");
        tfPassword.setForeground(Color.gray);
        tfFullname.setText("Enter full name");
        tfFullname.setForeground(Color.gray);
        tfEmail.setText("Enter email");
        tfEmail.setForeground(Color.gray);

        male.setOpaque(false);
        female.setOpaque(false);

        setLayout(new GridBagLayout());

        Insets textInsets = new Insets(10, 10, 5, 10);
        Insets maleInsets = new Insets(5, -150, -10, 0);
        Insets buttonInsets = new Insets(10, 10, 0, 10);

        GridBagConstraints input = new GridBagConstraints();
        input.anchor = GridBagConstraints.CENTER;

        input.insets = textInsets;
        input.gridy = 1;
        add(tfUsername, input);

        input.insets = textInsets;
        input.gridy = 2;
        add(tfPassword, input);

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

        input.insets = buttonInsets;
        input.anchor = GridBagConstraints.WEST;
        input.gridx = 0;
        input.gridy = 8;
        add(bAdd, input);

        input.insets = buttonInsets;
        input.anchor = GridBagConstraints.WEST;
        input.gridx = 0;
        input.gridy = 9;
        add(bReturn, input);

        addEventListeners();
    }
}