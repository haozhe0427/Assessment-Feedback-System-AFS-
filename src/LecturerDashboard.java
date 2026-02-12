import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LecturerDashboard extends JFrame {

    public LecturerDashboard() {
        setTitle("AFS - Lecturer Dashboard");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Title
        JLabel title = new JLabel("Welcome, " + Name.getName(), SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setBounds(0, 30, 800, 60);
        add(title);

        // Buttons
        JButton btnProfile = new JButton("Update Profile");
        JButton btnDesign = new JButton("Design Assessment");
        JButton btnGrade = new JButton("Key-in Marks");
        JButton btnReport = new JButton("View Reports");
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBackground(Color.PINK);

        // Set bounds for buttons
        btnProfile.setBounds(200, 160, 400, 50);
        btnDesign.setBounds(200, 220, 400, 50);
        btnGrade.setBounds(200, 280, 400, 50);
        btnReport.setBounds(200, 340, 400, 50);
        btnLogout.setBounds(200, 400, 400, 50);

        // Add action listeners
        btnProfile.addActionListener(_ -> {
            dispose();
            new ProfileWindow();
        });
        btnDesign.addActionListener(_ -> {
            dispose();
            new DesignAssessmentWindow();
        });
        btnGrade.addActionListener(_ -> {
            dispose();
            new GradingWindow();
        });
        btnReport.addActionListener(_ -> {
            dispose();
            new ReceiveFeedbackWindow();
        });
        btnLogout.addActionListener(_ -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new LoginGUI();
            }
        });

        // Add buttons to JFrame
        add(btnProfile);
        add(btnDesign);
        add(btnGrade);
        add(btnReport);
        add(btnLogout);

        setVisible(true);
    }

    // ------------------- Profile Window -------------------
    private static class ProfileWindow extends JFrame {
        public ProfileWindow() {
            setTitle("Update Profile");
            setSize(400, 400);
            setLocationRelativeTo(null);
            setLayout(null); // Remove layout manager

            // Labels and text fields
            JLabel lblId = new JLabel("Lecturer ID:");
            lblId.setBounds(20, 40, 120, 25);
            add(lblId);
            JTextField txtId = new JTextField();
            txtId.setBounds(150, 40, 200, 25);
            add(txtId);

            JLabel lblName = new JLabel("Name:");
            lblName.setBounds(20, 80, 120, 25);
            add(lblName);
            JTextField txtName = new JTextField();
            txtName.setBounds(150, 80, 200, 25);
            add(txtName);

            JLabel lblEmail = new JLabel("Email:");
            lblEmail.setBounds(20, 120, 120, 25);
            add(lblEmail);
            JTextField txtEmail = new JTextField();
            txtEmail.setBounds(150, 120, 200, 25);
            add(txtEmail);

            JLabel lblPassword = new JLabel("Password:");
            lblPassword.setBounds(20, 160, 120, 25);
            add(lblPassword);
            JTextField txtPassword = new JTextField();
            txtPassword.setBounds(150, 160, 200, 25);
            add(txtPassword);

            JLabel lblDept = new JLabel("School:");
            lblDept.setBounds(20, 200, 120, 25);
            add(lblDept);
            JTextField txtDept = new JTextField();
            txtDept.setBounds(150, 200, 200, 25);
            add(txtDept);

            JLabel lblLeader = new JLabel("Academic Leader:");
            lblLeader.setBounds(20, 240, 120, 25);
            add(lblLeader);
            JTextField txtLeader = new JTextField();
            txtLeader.setBounds(150, 240, 200, 25);
            add(txtLeader);

            JButton btnBack = new JButton("Back");
            btnBack.setBounds(20, 10, 100, 25);
            add(btnBack);

            JButton btnUpdate = new JButton("Update Profile");
            btnUpdate.setBounds(150, 300, 150, 30);
            add(btnUpdate);

            // Load profile data
            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Account))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] Account = line.split(" ; ");
                    String name = Account[3];

                    if (name.equals(Name.getName())) {
                        txtId.setText(Account[0]);
                        txtName.setText(Name.getName());
                        txtEmail.setText(Account[2]);
                        txtPassword.setText(Account[1]);
                        txtDept.setText(Account[6]);
                        txtLeader.setText(Account[7]);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Something went wrong. Please contact technician team for support",
                        "Error",JOptionPane.WARNING_MESSAGE);
            }


            // Make certain fields read-only
            txtId.setEditable(false);
            txtEmail.setEditable(false);
            txtDept.setEditable(false);
            txtLeader.setEditable(false);

            btnBack.addActionListener(_ -> {
                dispose();
                new LecturerDashboard();
            });

            // Update button action
            btnUpdate.addActionListener(_ -> {
                try {
                    FileHandler.saveProfile(
                            txtId.getText(),
                            txtName.getText(),
                            txtEmail.getText(),
                            txtPassword.getText(),
                            txtDept.getText(),
                            txtLeader.getText()
                    );
                    JOptionPane.showMessageDialog(this, "Profile Updated Successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving profile: " + ex.getMessage());
                }
            });

            setVisible(true);
        }
    }

    // ------------------- Design Assessment Window -------------------
    private static class DesignAssessmentWindow extends JFrame {
        public DesignAssessmentWindow() {
            setTitle("Design Assessment");
            setSize(900, 500);
            setLocationRelativeTo(null);
            setLayout(null); // Remove layout manager

            JLabel lblTitle = new JLabel("Design Assessment", SwingConstants.CENTER);
            lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
            lblTitle.setBounds(0, 10, 900, 50);
            add(lblTitle);

            // Form fields
            JLabel lblId = new JLabel("Module ID:");
            lblId.setBounds(20, 100, 100, 25);
            add(lblId);
            JTextField txtId = new JTextField();
            txtId.setBounds(120, 100, 200, 25);
            add(txtId);

            JLabel lblSubject = new JLabel("Subject Code:");
            lblSubject.setBounds(20, 150, 100, 25);
            add(lblSubject);
            JTextField txtSubject = new JTextField();
            txtSubject.setBounds(120, 150, 200, 25);
            add(txtSubject);

            JLabel lblTitleField = new JLabel("Title:");
            lblTitleField.setBounds(20, 200, 100, 25);
            add(lblTitleField);
            JTextField txtTitle = new JTextField();
            txtTitle.setBounds(120, 200, 200, 25);
            add(txtTitle);

            JLabel lblGPA = new JLabel("Max Marks:");
            lblGPA.setBounds(20, 250, 100, 25);
            add(lblGPA);
            JTextField txtMarks = new JTextField();
            txtMarks.setBounds(120, 250, 200, 25);
            add(txtMarks);

            JButton btnBack = new JButton("Back");
            btnBack.setBounds(20, 50, 100, 25);
            btnBack.addActionListener(_ -> {
                dispose();
                new LecturerDashboard();
            });
            add(btnBack);

            JButton btnSave = new JButton("Save Assessment");
            btnSave.setBounds(20, 360, 300, 40);
            add(btnSave);

            // Table
            String[] columns = {"Module ID", "Subject Code", "Title", "Max Marks"};
            DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
            JTable table = new JTable(tableModel);
            JScrollPane tableScroll = new JScrollPane(table);
            tableScroll.setBounds(350, 100, 500, 300);
            add(tableScroll);

            // Load existing assessments
            for (assessment a : FileHandler.loadAssessments()) {
                tableModel.addRow(new Object[]{a.getId(), a.getSubject(), a.getTitle(), a.getMaxMarks()});
            }

            // Save button action
            btnSave.addActionListener(e -> {
                try {
                    if (txtId.getText().isEmpty() || txtMarks.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please fill all fields.");
                        return;
                    }
                    int marks = Integer.parseInt(txtMarks.getText());
                    assessment newAssmt = new assessment(txtId.getText(), txtSubject.getText(), txtTitle.getText(), marks);
                    FileHandler.saveAssessment(newAssmt);
                    tableModel.addRow(new Object[]{newAssmt.getId(), newAssmt.getSubject(), newAssmt.getTitle(), newAssmt.getMaxMarks()});
                    txtId.setText(""); txtSubject.setText(""); txtTitle.setText(""); txtMarks.setText("");
                    JOptionPane.showMessageDialog(this, "Assessment Saved!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving.");
                }
            });

            setVisible(true);
        }
    }

    // ------------------- Grading Window -------------------
    private static class GradingWindow extends JFrame {
        private JComboBox<assessment> gradingDropdown;

        public GradingWindow() {
            setTitle("Key-in Marks");
            setSize(900, 500);
            setLocationRelativeTo(null);
            setLayout(null);

            JLabel lblTitle = new JLabel("Grading Assessment");
            lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
            lblTitle.setBounds(0, 10, 900, 50);
            lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblTitle);

            JLabel lblSelect = new JLabel("Select Assessment:");
            lblSelect.setBounds(20, 75, 150, 25);
            add(lblSelect);

            gradingDropdown = new JComboBox<>();
            gradingDropdown.setBounds(150, 75, 300, 25);
            add(gradingDropdown);

            for (assessment a : FileHandler.loadAssessments()) gradingDropdown.addItem(a);

            // Table
            String[] columns = {"Module ID", "Subject Code", "Title", "Max Marks"};
            DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
            JTable table = new JTable(tableModel);
            JScrollPane tableScroll = new JScrollPane(table);
            tableScroll.setBounds(20, 125, 500, 317);
            add(tableScroll);

            JLabel lblStudentId = new JLabel("Student ID:");
            lblStudentId.setBounds(550, 75, 100, 25);
            add(lblStudentId);
            JTextField txtStudentId = new JTextField();
            txtStudentId.setBounds(630, 75, 200, 25);
            add(txtStudentId);

            JLabel lblGPA = new JLabel("GPA:");
            lblGPA.setBounds(550, 120, 100, 25);
            add(lblGPA);
            JTextField txtGPA = new JTextField();
            txtGPA.setBounds(630, 120, 200, 25);
            add(txtGPA);

            JLabel lblFeedback_Assessment1 = new JLabel("Feedback (Assessment 1):");
            lblFeedback_Assessment1.setBounds(550, 170, 250, 25);
            add(lblFeedback_Assessment1);
            JTextField txtFeedback_Assessment1 = new JTextField();
            txtFeedback_Assessment1.setBounds(550, 200, 280, 25);
            add(txtFeedback_Assessment1);

            JLabel lblFeedback_Assessment2 = new JLabel("Feedback (Assessment 2):");
            lblFeedback_Assessment2.setBounds(550, 245, 250, 25);
            add(lblFeedback_Assessment2);
            JTextField txtFeedback_Assessment2 = new JTextField();
            txtFeedback_Assessment2.setBounds(550, 275, 280, 25);
            add(txtFeedback_Assessment2);

            JLabel lblFeedback_Assessment3 = new JLabel("Feedback (Assessment 3):");
            lblFeedback_Assessment3.setBounds(550, 320, 250, 25);
            add(lblFeedback_Assessment3);
            JTextField txtFeedback_Assessment3 = new JTextField();
            txtFeedback_Assessment3.setBounds(550, 350, 280, 25);
            add(txtFeedback_Assessment3);

            JButton btnSubmit = new JButton("Submit Grade");
            btnSubmit.setBounds(550, 400, 280, 40);
            add(btnSubmit);

            JButton btnBack = new JButton("Back");
            btnBack.setBounds(20, 25, 100, 30);
            btnBack.addActionListener(e -> {
                dispose();
                new LecturerDashboard();
            });
            add(btnBack);

//            btnSubmit.addActionListener(_ -> {
//                try {
//                    assessment selected = (assessment) gradingDropdown.getSelectedItem();
//                    if (selected == null) return;
//                    FileHandler.saveResult(selected.getId(), txtStudentId.getText(), Integer.parseInt(txtGPA.getText()), txtFeedback.getText());
//                    JOptionPane.showMessageDialog(this, "Grade Saved!");
//                    txtStudentId.setText(""); txtGPA.setText(""); txtFeedback.setText("");
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(this, "Error saving grade.");
//                }
//            });

            setVisible(true);
        }
    }

    // ------------------- Receive Feedback Window -------------------
    private static class ReceiveFeedbackWindow extends JFrame {
        private JComboBox<assessment> reportDropdown;
        private JTextArea txtDisplay;

        public ReceiveFeedbackWindow() {
            setTitle("View Reports");
            setSize(700, 600);
            setLocationRelativeTo(null);
            setLayout(null);

            JLabel lblModules = new JLabel("Modules:");
            lblModules.setBounds(20, 80, 100, 25);
            add(lblModules);

            reportDropdown = new JComboBox<>();
            reportDropdown.setBounds(100, 80, 250, 25);
            add(reportDropdown);

            for (assessment a : FileHandler.loadAssessments()) reportDropdown.addItem(a);

            JButton btnBack = new JButton("Back");
            btnBack.setBounds(20, 25, 100, 30);
            btnBack.addActionListener(_ -> {
               dispose();
               new LecturerDashboard();
            });
            add(btnBack);

            JButton btnSearch = new JButton("Search");
            btnSearch.setBounds(568, 80, 100, 30);
            add(btnSearch);

            // ** REMOVE btn_Generate
            JButton btnGenerate = new JButton("Generate Report");
            btnGenerate.setBounds(330, 20, 140, 25);
            add(btnGenerate);

            txtDisplay = new JTextArea();
            txtDisplay.setEditable(false);
            JScrollPane scroll = new JScrollPane(txtDisplay);
            scroll.setBounds(20, 120, 650, 280);
            add(scroll);

            btnGenerate.addActionListener(e -> {
                txtDisplay.setText("");
                assessment selected = (assessment) reportDropdown.getSelectedItem();
                if (selected != null) {
                    for (String line : FileHandler.getReport(selected.getId())) {
                        txtDisplay.append(line + "\n----------------\n");
                    }
                }
            });

            setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LecturerDashboard::new);
    }
}
