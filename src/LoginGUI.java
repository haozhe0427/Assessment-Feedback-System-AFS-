import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// <================== LOGIN GUI ==================>
public class LoginGUI extends JFrame implements ActionListener {

    JLabel loginLabel;
    JLabel userIDLabel;
    JTextField userIDField;
    JLabel passwordLabel;
    JPasswordField passwordField;
    JCheckBox showPasswordBox;
    JButton loginButton;
    ImageIcon imageIcon;

    LoginGUI() {
        // <========= LOGIN LABEL =========>
        loginLabel = new JLabel("Login");
        loginLabel.setForeground(Color.white);
        loginLabel.setBounds(0,20,395,100);
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(loginLabel);



        // <========= USER ID LABEL =========>
        userIDLabel = new JLabel("User ID:");
        userIDLabel.setForeground(Color.white);
        userIDLabel.setBounds(20,150,150,30);
        userIDLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        this.add(userIDLabel);



        // <========= USER ID FIELD =========>
        userIDField = new JTextField();
        userIDField.setEditable(false);
        userIDField.setText("Enter Your User ID Here");
        userIDField.setForeground(Color.DARK_GRAY);
        userIDField.setBounds(20,190,365,30);
        userIDField.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        userIDField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userIDField.setEditable(true);
                if (userIDField.getText().equals("Enter Your User ID Here")) {
                    userIDField.setText("");
                    userIDField.setFont(new Font("Segoe UI", Font.BOLD, 15));
                    userIDField.setForeground(Color.black);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (userIDField.getText().isEmpty()) {
                    userIDField.setEditable(false);
                    userIDField.setText("Enter Your User ID Here");
                    userIDField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                    userIDField.setForeground(Color.DARK_GRAY);
                }
            }
        });
        this.add(userIDField);



        // <========= PASSWORD LABEL =========>
        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.white);
        passwordLabel.setBounds(20, 300, 150, 30);
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD,18));
        this.add(passwordLabel);



        // <========= PASSWORD FIELD =========>
        passwordField = new JPasswordField();
        passwordField.setEditable(false);
        passwordField.setEchoChar((char) 0);
        passwordField.setText("Please Enter Password");
        passwordField.setForeground(Color.DARK_GRAY);
        passwordField.setBounds(20, 340, 365,30);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN,15));

        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                passwordField.setEditable(true);
                if (passwordField.getText().equals("Please Enter Password")) {
                    passwordField.setEchoChar('•');
                    passwordField.setText("");
                    passwordField.setForeground(Color.black);
                    passwordField.setFont(new Font("Segoe UI", Font.BOLD,15));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (passwordField.getText().isEmpty()) {
                    passwordField.setEditable(false);
                    passwordField.setEchoChar((char) 0);
                    passwordField.setText("Please Enter Password");
                    passwordField.setFont(new Font("Segoe UI", Font.PLAIN,15));
                    passwordField.setForeground(Color.DARK_GRAY);
                }
            }
        });
        this.add(passwordField);



        // <========= SHOW PASSWORD CHECKBOX =========>
        showPasswordBox = new JCheckBox();
        showPasswordBox.setForeground(Color.white);
        showPasswordBox.setBounds(20,370,300,30);
        showPasswordBox.setFont(new Font("Segoe UI", Font.BOLD, 14));
        showPasswordBox.setFocusable(false);
        showPasswordBox.setText("Show Password");
        showPasswordBox.setBackground(new Color(46, 26, 71));

        showPasswordBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordBox.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                }
                if (!showPasswordBox.isSelected()) {
                    passwordField.setEchoChar('•');
                }
            }
        });
        this.add(showPasswordBox);



        // <========= LOGIN BUTTON =========>
        loginButton = new JButton("Login");
        loginButton.setBounds(100, 450, 200,30);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD,20));
        loginButton.setFocusable(false);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DashboardGUI_Admin();
            }
        });
        this.add(loginButton);



        // <========= GUI FRAME =========>
        imageIcon = new ImageIcon("C:\\Users\\User\\OneDrive\\Pictures\\Screenshots\\Icon.png");
        this.setIconImage(imageIcon.getImage());
        this.setTitle("Login");
        this.getContentPane().setBackground(new Color(46, 26, 71));
        this.setSize(420,600);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {}
}