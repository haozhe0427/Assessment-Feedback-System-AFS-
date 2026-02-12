import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

// LOGIN
public class LoginGUI extends JFrame {
    JButton loginButton = new JButton("Login");
    JCheckBox showPasswordBox = new JCheckBox();
    JLabel loginLabel = new JLabel("Login");
    JLabel userIDLabel = new JLabel("User ID:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel forgotPasswordLabel = new JLabel("Forgot Password?", JLabel.CENTER);
    JPasswordField passwordField = new JPasswordField();
    public static JTextField userIDField = new JTextField();

    String userIDField_message = "Enter Your User ID Here";
    String passwordField_message = "Please Enter Password";
    public static String userID_OR_name;

    LoginGUI() {
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

            try (BufferedReader reader1 = new BufferedReader(new FileReader(Resources.Account));
                 BufferedReader reader2 = new BufferedReader(new FileReader(Resources.AdminAccount))) {

                String line1, line2;
                while ((line1 = reader1.readLine()) != null) {
                    String[] loginInfo = line1.split(" ; ");
                    String storedUserID = loginInfo[0];
                    String storedPassword = loginInfo[1];
                    String userRole = loginInfo[5];

                    if (storedUserID.equalsIgnoreCase(userID) && storedPassword.equals(password)) {
                        switch (userRole) {
                            case "Academic Leaders" -> {
                                User user = new User(
                                        loginInfo[0].trim(),
                                        loginInfo[1].trim(),
                                        loginInfo[2].trim(),
                                        loginInfo[3].trim(),
                                        loginInfo[4].trim(),
                                        loginInfo[5].trim()
                                );

                                Session.login(user);

                                dispose();
                                AcademicLeaderDashboard dashboardFrame = new AcademicLeaderDashboard();
                                dashboardFrame.setVisible(true);
                                dashboardFrame.setLocationRelativeTo(null);
                            }
                            case "Lecturer" -> {
                                dispose();
                                LecturerDashboard lecturerDashboard = new LecturerDashboard();
                                lecturerDashboard.setVisible(true);
                                lecturerDashboard.setLocationRelativeTo(null);
                            }
                            case "Student" -> {
                                String studentID = loginInfo[0].trim();
                                String studentPassword = loginInfo[1].trim();
                                String studentEmail = loginInfo[2].trim();
                                String studentName = loginInfo[3].trim();

                                StudentSession.login(
                                        studentID,
                                        studentName,
                                        studentEmail,
                                        studentPassword
                                );

                                dispose();
                                StudentDashboard  studentDashboard = new StudentDashboard();
                                studentDashboard.setVisible(true);
                                studentDashboard.setLocationRelativeTo(null);
                            }
                        }
                        return;
                    }
                    while ((line2 = reader2.readLine()) != null) {
                        String[] adminInfo = line2.split(" ; ");
                        String adminID = adminInfo[0];
                        String adminPassword = adminInfo[1];
                        String isAdmin = adminInfo[3];

                        if (adminID.equalsIgnoreCase(userID) && adminPassword.equals(password)) {
                            if (isAdmin.equals("Admin")) {
                                dispose();
                                new AdminDashboard();
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
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Something went wrong. Please contact technician team for support",
                        "Error",JOptionPane.WARNING_MESSAGE);
            }
        });
        this.add(loginButton);



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

        // <========= 4) forgotPasswordLabel =========>
        forgotPasswordLabel.setForeground(Color.blue);
        forgotPasswordLabel.setBounds(-10,480,420,30);
        forgotPasswordLabel.setFont(new Font("Segoe UI", Font.BOLD,13));
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userID_OR_name = JOptionPane.showInputDialog(null,
                                                                    "Please Enter Your User ID / Name here",
                                                                    "User Verification",  JOptionPane.INFORMATION_MESSAGE);

                if (userID_OR_name == null) {
                    return;
                }
                if (userID_OR_name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter your User ID or Name",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Account))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] userInfo = line.split(" ; ");
                        String userID = userInfo[0];
                        String name = userInfo[3];

                        if (userID.equals(userID_OR_name) || name.equals(userID_OR_name)) {
                            dispose();
                            new SetNewPasswordGUI();
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null,
                            "Invalid account. Please try again",
                            "Warning",JOptionPane.WARNING_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Something went wrong. Please contact technician team for support",
                            "Error",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        this.add(forgotPasswordLabel);



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



        // <========= GUI FRAME =========>
        this.setIconImage(Resources.imageIcon.getImage());
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




// SET NEW PASSWORD
class SetNewPasswordGUI extends JFrame {
    JButton backButton = new JButton("Back");
    JButton createNewPasswordButton = new JButton("Create");
    JCheckBox showPassword_cb = new JCheckBox("Show Password");
    JLabel newPasswordLabel = new JLabel("New password:");
    JLabel confirmNewPasswordLabel = new JLabel("Confirm New password:");
    JPasswordField newPasswordField = new JPasswordField();
    JPasswordField confirmNewPasswordField = new JPasswordField();

    SetNewPasswordGUI() {
        // <================== JButton ==================>
        // <========= 1) backButton =========>
        backButton.setBounds(20, 20, 100, 30);
        backButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        backButton.setFocusable(false);
        backButton.addActionListener(_ -> {
            dispose();
            new LoginGUI();
        });
        this.add(backButton);


        // <========= 2) createNewPasswordButton =========>
        createNewPasswordButton.setBounds(100, 450, 200, 30);
        createNewPasswordButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        createNewPasswordButton.setFocusable(false);
        createNewPasswordButton.addActionListener(_ -> {
            String newPassword = new String(newPasswordField.getPassword());
            String confirmNewPassword = new String(confirmNewPasswordField.getPassword());
            StringBuilder updatedPassword = new StringBuilder();

            if (newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Please enter every field",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Account))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userInfo = line.split(" ; ");
                    String userID = userInfo[0];
                    String name = userInfo[3];
                    String password = userInfo[1];

                    if (userID.equals(LoginGUI.userID_OR_name) || name.equals(LoginGUI.userID_OR_name)) {
                        if (!newPassword.equals(confirmNewPassword)) {
                            JOptionPane.showMessageDialog(null,
                                    "Invalid Password. Please try again",
                                    "Error",JOptionPane.WARNING_MESSAGE);

                            return;
                        } else {
                            userInfo[1] = confirmNewPassword;

                            JOptionPane.showMessageDialog(null,
                                    "Password successfully updated",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);

                            dispose();
                            new LoginGUI();
                        }
                    }
                    updatedPassword.append(String.join(" ; ", userInfo)).append("\n");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Something went wrong. Please contact technician team for support",
                        "Error",JOptionPane.WARNING_MESSAGE);
            }

            try (FileWriter writer = new FileWriter(Resources.Account)) {
                writer.write(updatedPassword.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error writing to file",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        this.add(createNewPasswordButton);



        // <================== JCheckBox ==================>
        // <========= 1) showPassword_cb =========>
        showPassword_cb.setForeground(Color.black);
        showPassword_cb.setBounds(20, 370, 300, 30);
        showPassword_cb.setFont(new Font("Segoe UI", Font.BOLD, 14));
        showPassword_cb.setFocusable(false);
        showPassword_cb.setBackground(new Color(255, 250, 250));
        showPassword_cb.addActionListener(_ -> {
            if (showPassword_cb.isSelected()) {
                newPasswordField.setEchoChar((char) 0);
                confirmNewPasswordField.setEchoChar((char) 0);
            }
            if (!showPassword_cb.isSelected()) {
                newPasswordField.setEchoChar('•');
                confirmNewPasswordField.setEchoChar('•');
            }
        });
        this.add(showPassword_cb);



        // <================== JLabel ==================>
        // <========= 1) newPasswordLabel =========>
        newPasswordLabel.setForeground(Color.black);
        newPasswordLabel.setBounds(20, 150, 150, 30);
        newPasswordLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(newPasswordLabel);


        // <========= 2) confirmNewPasswordLabel =========>
        confirmNewPasswordLabel.setForeground(Color.black);
        confirmNewPasswordLabel.setBounds(20, 300, 300, 30);
        confirmNewPasswordLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(confirmNewPasswordLabel);



        // <================== JPasswordField ==================>
        // <========= 1) newPasswordField =========>
        newPasswordField.setBounds(20, 190, 365, 30);
        newPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        this.add(newPasswordField);


        // <========= 2) confirmNewPasswordField =========>
        confirmNewPasswordField.setBounds(20, 340, 365, 30);
        confirmNewPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        this.add(confirmNewPasswordField);



        // <========= Frame GUI =========>
        this.setIconImage(Resources.imageIcon.getImage());
        this.setTitle("Set New Password");
        this.getContentPane().setBackground(new Color(255, 250, 250));
        this.setSize(420,600);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}