import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// <================== LOGIN GUI ==================>
public class LoginGUI extends JFrame implements ActionListener {

    JLabel loginLabel = new JLabel("Login");
    JLabel userIDLabel = new JLabel("User ID:");
    public static JTextField userIDField = new JTextField();
    JLabel passwordLabel = new JLabel("Password:");
    JPasswordField passwordField = new JPasswordField();
    JCheckBox showPasswordBox = new JCheckBox();
    JButton loginButton = new JButton("Login");

    String userIDField_message = "Enter Your User ID Here";
    String passwordField_message = "Please Enter Password";

    LoginGUI() {
        // <================== COMPONENTS (START) ==================>
        // <========= LOGIN LABEL =========>
        loginLabel.setForeground(Color.white);
        loginLabel.setBounds(0,20,395,100);
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(loginLabel);



        // <========= USER ID LABEL =========>
        userIDLabel.setForeground(Color.white);
        userIDLabel.setBounds(20,150,150,30);
        userIDLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(userIDLabel);



        // <========= USER ID FIELD =========>
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



        // <========= PASSWORD LABEL =========>
        passwordLabel.setForeground(Color.white);
        passwordLabel.setBounds(20, 300, 150, 30);
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD,18));
        this.add(passwordLabel);



        // <========= PASSWORD FIELD =========>
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



        // <========= SHOW PASSWORD CHECKBOX =========>
        showPasswordBox.setForeground(Color.white);
        showPasswordBox.setBounds(20,370,300,30);
        showPasswordBox.setFont(new Font("Segoe UI", Font.BOLD, 14));
        showPasswordBox.setFocusable(false);
        showPasswordBox.setText("Show Password");
        showPasswordBox.setBackground(new Color(46, 26, 71));
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



        // <========= LOGIN BUTTON =========>
        loginButton.setBounds(100, 450, 200,30);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD,20));
        loginButton.setFocusable(false);
        loginButton.addActionListener(_ -> {
            String userID = userIDField.getText();
            String password = new String(passwordField.getPassword());

            try (BufferedReader reader = new BufferedReader(new FileReader(PicturesAndTextFile.accountFile))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(" ; ");

                    if (data.length >= 8) {
                        String storedID = data[0];
                        String storedPassword = data[1];
                        String userRole = data[6];

                        if (storedID.equalsIgnoreCase(userID) && storedPassword.equals(password)) {
                            if (userRole.equals("Admin")) {
                                dispose();
                                new DashboardGUI_Admin();
                                return;
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid account. Please try again",
                                        "Error",JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    }
                }
                JOptionPane.showMessageDialog(null,
                        "Invalid userID / password. Please try again",
                        "Warning",JOptionPane.WARNING_MESSAGE);

            } catch (FileNotFoundException fileNotFoundException) {
                JOptionPane.showMessageDialog(null,
                        "Account list is not found",
                        "Warning",JOptionPane.WARNING_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null,
                        "Something went wrong. Please contact technician team for support",
                        "Error",JOptionPane.WARNING_MESSAGE);
            }
        });
        this.add(loginButton);
        // <================== COMPONENTS (END) ==================>



        // <========= GUI FRAME (START) =========>
        this.setIconImage(PicturesAndTextFile.imageIcon.getImage());
        this.setTitle("Assessment Feedback System (Login)");
        this.getContentPane().setBackground(new Color(46, 26, 71));
        this.setSize(420,600);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        // <========= GUI FRAME (END) =========>
    }



    @Override
    public void actionPerformed(ActionEvent e) {}
}