import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {
        setTitle("Academic Management System - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center screen
        setLayout(new GridLayout(4, 1, 10, 10));

        // UI Components
        JPanel panelTitle = new JPanel();
        panelTitle.add(new JLabel("Welcome to APU System"));

        JPanel panelUser = new JPanel();
        panelUser.add(new JLabel("UserID: "));
        txtUsername = new JTextField(15);
        panelUser.add(txtUsername);

        JPanel panelPass = new JPanel();
        panelPass.add(new JLabel("Password: "));
        txtPassword = new JPasswordField(15);
        panelPass.add(txtPassword);

        JPanel panelBtn = new JPanel();
        btnLogin = new JButton("Login");
        panelBtn.add(btnLogin);

        // Add to Frame
        add(panelTitle);
        add(panelUser);
        add(panelPass);
        add(panelBtn);

        // Login Action
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        String id = txtUsername.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();

        if (id.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate using TextFileUtils
        User user = TextFileUtils.validateLogin(id, pass);

        if (user != null) {
            // Set Session
            Session.setCurrentUser(user);


//            [cite_start]// Assuming Academic Leader based on prompt context [cite: 8]
            JOptionPane.showMessageDialog(this, "Login Successful! Welcome " + user.getName());

            // Open Dashboard and close Login
            new DashboardFrame().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid UserID or Password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
