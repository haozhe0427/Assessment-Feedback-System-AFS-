import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LoginGUI extends JFrame {

    JLabel loginLabel = new JLabel("Login");
    JLabel userIDLabel = new JLabel("User ID:");
    JLabel passwordLabel = new JLabel("Password:");
    public static JTextField userIDField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JCheckBox showPasswordBox = new JCheckBox();
    JButton loginButton = new JButton("Login");

    String userIDField_message = "Enter Your User ID Here";
    String passwordField_message = "Please Enter Password";

    LoginGUI() {
        // <================== JLabel ==================>
        // <========= 1) LoginLabel =========>
        loginLabel.setForeground(Color.black);
        loginLabel.setBounds(0,20,395,100);
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(loginLabel);


        // <========= 2) userIDLabel =========>
        userIDLabel.setForeground(Color.black);
        userIDLabel.setBounds(20,150,150,30);
        userIDLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(userIDLabel);


        // <========= 3) passwordLabel =========>
        passwordLabel.setForeground(Color.black);
        passwordLabel.setBounds(20, 300, 150, 30);
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD,18));
        this.add(passwordLabel);



        // <================== JTextField ==================>
        // <========= 1) userIDField =========>
        userIDField.setEditable(false);
        userIDField.setText(userIDField_message);
        userIDField.setForeground(Color.gray);
        userIDField.setBounds(20,190,365,30);
        userIDField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        userIDField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userIDField.setEditable(true);
                if (userIDField.getText().equals(userIDField_message)) {
                    userIDField.setText("");
                    userIDField.setFont(new Font("Segoe UI", Font.BOLD, 15));
                    userIDField.setForeground(Color.black);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (userIDField.getText().isEmpty()) {
                    userIDField.setEditable(false);
                    userIDField.setText(userIDField_message);
                    userIDField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                    userIDField.setForeground(Color.gray);
                }
            }
        });
        this.add(userIDField);



        // <================== JPasswordField ==================>
        // <========= 1) passwordField =========>
        passwordField.setEditable(false);
        passwordField.setEchoChar((char) 0);
        passwordField.setText(passwordField_message);
        passwordField.setForeground(Color.gray);
        passwordField.setBounds(20, 340, 365,30);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN,15));
        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                passwordField.setEditable(true);
                passwordField.setEchoChar('•');
                if (new String(passwordField.getPassword()).equals(passwordField_message)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.black);
                    passwordField.setFont(new Font("Segoe UI", Font.BOLD,15));
                    if (showPasswordBox.isSelected()) {
                        passwordField.setEchoChar((char) 0);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setEditable(false);
                    passwordField.setEchoChar((char) 0);
                    passwordField.setText(passwordField_message);
                    passwordField.setFont(new Font("Segoe UI", Font.PLAIN,15));
                    passwordField.setForeground(Color.gray);
                }
            }
        });
        this.add(passwordField);



        // <================== JCheckBox ==================>
        // <========= 1) ShowPasswordBox =========>
        showPasswordBox.setForeground(Color.black);
        showPasswordBox.setBounds(20,370,300,30);
        showPasswordBox.setFont(new Font("Segoe UI", Font.BOLD, 14));
        showPasswordBox.setFocusable(false);
        showPasswordBox.setText("Show Password");
        showPasswordBox.setBackground(new Color(255,250,250));
        showPasswordBox.addActionListener(_ -> {
            if (showPasswordBox.isSelected()) {
                if (new String(passwordField.getPassword()).equals(passwordField_message)) {
                    passwordField.setText(passwordField_message);
                } else {
                    passwordField.setEchoChar((char) 0);
                }
            }
            if (!showPasswordBox.isSelected()) {
                if (new String(passwordField.getPassword()).equals(passwordField_message)) {
                    passwordField.setText(passwordField_message);
                } else {
                    passwordField.setEchoChar('•');
                }
            }
        });
        this.add(showPasswordBox);



        // <================== JButton ==================>
        // <========= 1) LoginButton =========>
        loginButton.setBounds(100, 450, 200,30);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD,20));
        loginButton.setFocusable(false);
        loginButton.addActionListener(_ -> {
            String userID = userIDField.getText();
            String password = new String(passwordField.getPassword());

            if (userID.equals(userIDField_message) || password.equals(passwordField_message)) {
                JOptionPane.showMessageDialog(null,
                        "Please enter userID and password",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (BufferedReader reader1 = new BufferedReader(new FileReader(PicturesAndTextFile.Login));
            BufferedReader reader2 = new BufferedReader(new FileReader(PicturesAndTextFile.AdminAccount))) {

                String line1, line2;
                while ((line1 = reader1.readLine()) != null) {
//                    String[] loginInfo = line1.split(" ; ");
//                    String storedUserID = loginInfo[0];
//                    String storedPassword = loginInfo[1];
//                    String userRole = loginInfo[3];
//
//                    if (storedUserID.equalsIgnoreCase(userID) && storedPassword.equals(password)) {
//                        if (userRole.equals("Student")) {
//                            dispose();
//                            new AssignLecturerGUI_Admin();
//                        } else {
//                            JOptionPane.showMessageDialog(null,
//                                    "Invalid account. Please try again",
//                                    "Error",JOptionPane.ERROR_MESSAGE);
//                        }
//                        return;
//                    }
                    while ((line2 = reader2.readLine()) != null) {
                        String[] adminInfo = line2.split(" ; ");
                        String adminID = adminInfo[0];
                        String adminPassword = adminInfo[1];
                        String isAdmin = adminInfo[3];

                        if (adminID.equalsIgnoreCase(userID) && adminPassword.equals(password)) {
                            if (isAdmin.equals("Admin")) {
                                dispose();
                                new DashboardGUI_Admin();
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid account. Please try again",
                                        "Error",JOptionPane.ERROR_MESSAGE);
                            }
                            return;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null,
                        "Invalid userID / password. Please try again",
                        "Warning",JOptionPane.WARNING_MESSAGE);

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Account list is not found",
                        "Warning",JOptionPane.WARNING_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Something went wrong. Please contact technician team for support",
                        "Error",JOptionPane.WARNING_MESSAGE);
            }
        });
        this.add(loginButton);



        // <========= GUI FRAME =========>
        this.setIconImage(PicturesAndTextFile.imageIcon.getImage());
        this.setTitle("Assessment Feedback System (Login)");
        this.getContentPane().setBackground(new Color(255, 250, 250));
        this.setSize(420,600);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}