import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SetNewPasswordGUI extends JFrame {

    // JButton
    JButton backButton = new JButton("Back");
    JButton createNewPasswordButton = new JButton("Create");

    // JCheckBox
    JCheckBox showPassword_cb = new JCheckBox("Show Password");

    // JLabel
    JLabel newPasswordLabel = new JLabel("New password:");
    JLabel confirmNewPasswordLabel = new JLabel("Confirm New password:");

    // JPasswordField
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

            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Login))) {

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

            try (FileWriter writer = new FileWriter(Resources.Login)) {
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
