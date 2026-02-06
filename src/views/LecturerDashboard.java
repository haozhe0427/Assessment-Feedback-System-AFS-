package views;
import models.assesment;
import Utils.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LecturerDashboard extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContainer;

    public LecturerDashboard() {
        // 1. Setup the main window
        setTitle("AFS - Lecturer Dashboard");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 2. Setup the Card Layout (The "Stack" of screens)
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // 3. Create the screens and add them to the stack
        mainContainer.add(createMainMenu(), "MENU");
        mainContainer.add(createProfilePanel(), "PROFILE");
        mainContainer.add(createDesignAssesmentPanel(), "DESIGN");
        mainContainer.add(createGradingPanel(), "GRADING");
        mainContainer.add(createReportPanel(), "REPORT");

        // 4. Show the Menu first
        add(mainContainer);
        cardLayout.show(mainContainer, "MENU");
    }

    // --- SCREEN 1: THE MAIN MENU ---
    private JPanel createMainMenu() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10)); // Changed from 5 to 6 rows
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JLabel title = new JLabel("Welcome, Lecturer", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JButton btnProfile = new JButton("Update Profile");
        JButton btnDesign = new JButton("Design Assessment");
        JButton btnGrade = new JButton("Key-in Marks");
        JButton btnReport = new JButton("View Reports");

        // --- NEW LOGOUT BUTTON ---
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBackground(Color.PINK); // Optional: Make it look different

        // Navigation Actions
        btnProfile.addActionListener(e -> cardLayout.show(mainContainer, "PROFILE"));
        btnDesign.addActionListener(e -> cardLayout.show(mainContainer, "DESIGN"));
        btnGrade.addActionListener(e -> refreshAndShowGrading());
        btnReport.addActionListener(e -> refreshAndShowReport());

        // Logout Action: Closes this window
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); // Destroys the window.
                // Later, when you merge, you can add: new LoginScreen().setVisible(true);
            }
        });

        panel.add(title);
        panel.add(btnProfile);
        panel.add(btnDesign);
        panel.add(btnGrade);
        panel.add(btnReport);
        panel.add(btnLogout); // Add the button to the screen

        return panel;
    }

    // --- SCREEN 2: PROFILE ---
    private JPanel createProfilePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Form Panel
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JTextField txtId = new JTextField();
        JTextField txtName = new JTextField();
        JTextField txtEmail = new JTextField(); // Disabled (not in your file)
        txtEmail.setEditable(false);
        JTextField txtDept = new JTextField();
        JButton btnUpdate = new JButton("Update Profile");

        // Hardcoded ID for now until login is connected
        String myID = "LC000001";
        String[] data = FileHandler.loadProfile(myID);
        txtId.setText(data[0]);
        txtName.setText(data[1]);
        txtEmail.setText("N/A"); // Your file doesn't have email
        txtDept.setText(data[3]);

        // Make ID read-only so they can't break the link
        txtId.setEditable(false);

        form.add(new JLabel("Lecturer ID:")); form.add(txtId);
        form.add(new JLabel("Name:"));        form.add(txtName);
        form.add(new JLabel("Email:"));       form.add(txtEmail);
        form.add(new JLabel("School:"));      form.add(txtDept);
        form.add(new JLabel(""));             form.add(btnUpdate);

        btnUpdate.addActionListener(e -> {
            try {
                FileHandler.saveProfile(txtId.getText(), txtName.getText(), "", txtDept.getText());
                JOptionPane.showMessageDialog(this, "Profile Updated!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving profile.");
            }
        });

        panel.add(new JLabel("Update Profile", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(form, BorderLayout.CENTER);
        panel.add(createBackButton(), BorderLayout.SOUTH); // Add Back Button

        return panel;
    }

    // --- SCREEN 3: DESIGN ASSESSMENT ---
    private JPanel createDesignAssesmentPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JTextField txtId = new JTextField();
        JTextField txtSubject = new JTextField();
        JTextField txtTitle = new JTextField();
        JTextField txtMarks = new JTextField();
        JButton btnSave = new JButton("Save Assessment");

        form.add(new JLabel("Assessment ID:")); form.add(txtId);
        form.add(new JLabel("Subject Code:"));  form.add(txtSubject);
        form.add(new JLabel("Title:"));         form.add(txtTitle);
        form.add(new JLabel("Max Marks:"));     form.add(txtMarks);
        form.add(new JLabel(""));               form.add(btnSave);

        btnSave.addActionListener(e -> {
            try {
                if(txtId.getText().isEmpty() || txtMarks.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.");
                    return;
                }
                int marks = Integer.parseInt(txtMarks.getText());
                assesment newAssmt = new assesment(txtId.getText(), txtSubject.getText(), txtTitle.getText(), marks);
                FileHandler.saveAssesment(newAssmt);
                JOptionPane.showMessageDialog(this, "Assessment Saved!");
                txtId.setText(""); txtSubject.setText(""); txtTitle.setText(""); txtMarks.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving.");
            }
        });

        panel.add(new JLabel("Design Assessment", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(form, BorderLayout.CENTER);
        panel.add(createBackButton(), BorderLayout.SOUTH);

        return panel;
    }

    // --- SCREEN 4: GRADING (Dynamic Dropdown) ---
    // We create this as a field so we can refresh it later
    private JComboBox<assesment> gradingDropdown;

    private JPanel createGradingPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        gradingDropdown = new JComboBox<>(); // Empty at first
        JTextField txtStudentId = new JTextField();
        JTextField txtMarks = new JTextField();
        JTextField txtFeedback = new JTextField();
        JButton btnSubmit = new JButton("Submit Grade");

        form.add(new JLabel("Select Assessment:")); form.add(gradingDropdown);
        form.add(new JLabel("Student ID:"));        form.add(txtStudentId);
        form.add(new JLabel("Marks:"));             form.add(txtMarks);
        form.add(new JLabel("Feedback:"));          form.add(txtFeedback);
        form.add(new JLabel(""));                   form.add(btnSubmit);

        btnSubmit.addActionListener(e -> {
            try {
                assesment selected = (assesment) gradingDropdown.getSelectedItem();
                if (selected == null) return;
                FileHandler.saveResult(selected.getId(), txtStudentId.getText(), Integer.parseInt(txtMarks.getText()), txtFeedback.getText());
                JOptionPane.showMessageDialog(this, "Grade Saved!");
                txtStudentId.setText(""); txtMarks.setText(""); txtFeedback.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving grade.");
            }
        });

        panel.add(new JLabel("Key-in Marks", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(form, BorderLayout.CENTER);
        panel.add(createBackButton(), BorderLayout.SOUTH);
        return panel;
    }

    // --- SCREEN 5: REPORTS (Dynamic Dropdown) ---
    private JComboBox<assesment> reportDropdown;
    private JTextArea txtDisplay;

    private JPanel createReportPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();

        reportDropdown = new JComboBox<>();
        JButton btnGenerate = new JButton("Generate Report");

        topPanel.add(new JLabel("Assessment:"));
        topPanel.add(reportDropdown);
        topPanel.add(btnGenerate);

        txtDisplay = new JTextArea();
        txtDisplay.setEditable(false);

        btnGenerate.addActionListener(e -> {
            txtDisplay.setText("");
            assesment selected = (assesment) reportDropdown.getSelectedItem();
            if (selected != null) {
                java.util.List<String> results = FileHandler.getReport(selected.getId());
                txtDisplay.append("REPORT FOR: " + selected.getTitle() + "\n\n");
                for (String line : results) txtDisplay.append(line + "\n----------------\n");
            }
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(txtDisplay), BorderLayout.CENTER);
        panel.add(createBackButton(), BorderLayout.SOUTH);
        return panel;
    }

    // --- HELPER: The "Back" Button ---
    private JButton createBackButton() {
        JButton btnBack = new JButton("<< Back to Menu");
        btnBack.addActionListener(e -> cardLayout.show(mainContainer, "MENU"));
        return btnBack;
    }

    // --- HELPER: Refresh Dropdowns ---
    // This ensures that if you add a new Assessment, it appears in the list immediately
    private void refreshAndShowGrading() {
        gradingDropdown.removeAllItems();
        for (assesment a : FileHandler.loadAssesments()) gradingDropdown.addItem(a);
        cardLayout.show(mainContainer, "GRADING");
    }

    private void refreshAndShowReport() {
        reportDropdown.removeAllItems();
        for (assesment a : FileHandler.loadAssesments()) reportDropdown.addItem(a);
        cardLayout.show(mainContainer, "REPORT");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LecturerDashboard().setVisible(true));
    }
}