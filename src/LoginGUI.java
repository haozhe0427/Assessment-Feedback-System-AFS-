import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// <================== LOGIN GUI ==================>
public class LoginGUI extends JFrame implements ActionListener {
    LoginGUI() {
        // <========= LOGIN LABEL =========>
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(0,0,410,100);
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 30));
        this.add(loginLabel);


        // <========= USER ID LABEL =========>
        JLabel userIDLabel = new JLabel("User ID:");
        userIDLabel.setBounds(20,150,150,30);
        userIDLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        this.add(userIDLabel);


        // <========= USER ID FIELD =========>
        JTextField userIDField = new JTextField();
        userIDField.setBounds(20,190,365,30);
        userIDField.setFont(new Font("Dialog", Font.PLAIN, 18));
        this.add(userIDField);


        // <========= PASSWORD LABEL =========>
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 300, 150, 30);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN,18));
        this.add(passwordLabel);


        // <========= PASSWORD FIELD =========>
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20, 340, 365,30);
        passwordField.setFont(new Font("Dialog", Font.PLAIN,18));
        this.add(passwordField);


        // <========= LOGIN BUTTON =========>
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 450, 200,30);
        loginButton.setFont(new Font("Dialog", Font.BOLD,20));
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
        JFrame frame = new JFrame();
        this.setTitle("Login");
        this.setSize(420,600);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}