import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {
    LoginGUI() {
        // <========= LOGIN LABEL =========>
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(0,0,410,100);
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 30));
        this.add(loginLabel);


        // <========= USERNAME LABEL =========>
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20,150,150,30);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        this.add(usernameLabel);


        JTextField usernameField = new JTextField();
        usernameField.setBounds(20,190,365,30);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 18));
        this.add(usernameField);


        // <========= LOGIN GUI FRAME =========>
        JFrame frame = new JFrame();
        this.setTitle("Login");
        this.setSize(420,600);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}