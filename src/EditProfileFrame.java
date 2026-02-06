import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProfileFrame extends JFrame {
    private JTextField txtID, txtName, txtEmail, txtGender;
    private JPasswordField txtPassword;
    private JButton btnUpdate, btnCancel;
    private User currentUser;

    public EditProfileFrame() {
        // Get the currently logged-in user
        currentUser = Session.getCurrentUser();

        setTitle("Edit Personal Profile");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only close this window, not the app
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10)); // Grid layout for neat rows

        // --- ID (Read Only) ---
        add(new JLabel("User ID (Cannot change):"));
        txtID = new JTextField(currentUser.getId());
        txtID.setEditable(false); // ID is the primary key, shouldn't change
        add(txtID);

        // --- Name ---
        add(new JLabel("Full Name:"));
        txtName = new JTextField(currentUser.getName());
        add(txtName);

        // --- Password ---
        add(new JLabel("Password:"));
        txtPassword = new JPasswordField(currentUser.getPassword());
        add(txtPassword);

        // --- Email ---
        add(new JLabel("Email:"));
        txtEmail = new JTextField(currentUser.getEmail());
        add(txtEmail);

        // --- Gender ---
        add(new JLabel("Gender (M/F):"));
        txtGender = new JTextField(currentUser.getGender());
        add(txtGender);

        // --- Role (Read Only) ---
        add(new JLabel("Role:"));
        JTextField txtRole = new JTextField(currentUser.getRole());
        txtRole.setEditable(false);
        add(txtRole);

        // --- Buttons ---
        btnUpdate = new JButton("Update Profile");
        btnCancel = new JButton("Cancel");
        add(btnUpdate);
        add(btnCancel);

        // --- Button Actions ---

        // UPDATE BUTTON
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Get data from text fields
                String newName = txtName.getText().trim();
                String newPass = new String(txtPassword.getPassword()).trim();
                String newEmail = txtEmail.getText().trim();
                String newGender = txtGender.getText().trim();

                // 2. Simple Validation
                if(newName.isEmpty() || newPass.isEmpty() || newEmail.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Name, Password, and Email cannot be empty.");
                    return;
                }

                // 3. Update the User Object (in memory)
                currentUser.setName(newName);
                currentUser.setPassword(newPass);
                currentUser.setEmail(newEmail);
                // 4. Update the File
                boolean success = TextFileUtils.updateUserInFile(currentUser);

                if (success) {
                    // 5. Update Session and UI
                    Session.setCurrentUser(currentUser);
                    JOptionPane.showMessageDialog(null, "Profile Updated Successfully!");
                    dispose(); // Close the edit window
                } else {
                    JOptionPane.showMessageDialog(null, "Error updating file. Please check permissions.");
                }
            }
        });

        // CANCEL BUTTON
        btnCancel.addActionListener(e -> dispose());
    }


}