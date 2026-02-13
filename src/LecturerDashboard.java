import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LecturerDashboard extends JFrame {

    private final Lecturer lecturer;
    private JLabel title;

    public LecturerDashboard(Lecturer Lecturer) {
        this.lecturer = Lecturer;

        setTitle("AFS - Lecturer Dashboard");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);

        title = new JLabel("Welcome, " + lecturer.getName(), SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setBounds(0, 30, 800, 60);
        add(title);

        JButton btnProfile = new JButton("Update Profile");
        JButton btnDesign = new JButton("Design Assessment");
        JButton btnGrade = new JButton("Key-in Marks");
        JButton btnReport = new JButton("View Feedback");
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBackground(Color.PINK);

        btnProfile.setBounds(200, 160, 400, 50);
        btnDesign.setBounds(200, 220, 400, 50);
        btnGrade.setBounds(200, 280, 400, 50);
        btnReport.setBounds(200, 340, 400, 50);
        btnLogout.setBounds(200, 400, 400, 50);

        add(btnProfile);
        add(btnDesign);
        add(btnGrade);
        add(btnReport);
        add(btnLogout);

        // --- Navigation ---
        btnProfile.addActionListener(e -> new ProfileWindow());
        btnDesign.addActionListener(e -> new DesignAssessmentWindow());
        btnGrade.addActionListener(e -> { dispose(); new GradingWindow(); });
        btnReport.addActionListener(e -> { dispose(); new ReceiveFeedbackWindow(); });
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new LoginGUI();
            }
        });

        setVisible(true);
    }

    // ------------------- 1. Profile Window -------------------
    private class ProfileWindow extends JFrame {
        public ProfileWindow() {
            setTitle("Update Profile");
            setSize(400, 450);
            setLocationRelativeTo(null);
            setLayout(null);

            String[] labels = {"Lecturer ID:", "Name:", "Email:", "Password:", "School:", "Academic Leader:"};
            JTextField[] fields = new JTextField[6];
            for (int i = 0; i < 6; i++) {
                JLabel lbl = new JLabel(labels[i]);
                lbl.setBounds(20, 40 + (i * 40), 120, 25);
                add(lbl);

                fields[i] = new JTextField();
                fields[i].setBounds(150, 40 + (i * 40), 200, 25);
                add(fields[i]);
            }

            fields[0].setEditable(false);
            fields[2].setEditable(false);
            fields[4].setEditable(false);
            fields[5].setEditable(false);

            try {
                if (lecturer == null) throw new Exception("Lecturer data is missing!");

                String lecturerId = lecturer.getLecturerID();
                String[] profile = getLecturerProfile(lecturerId);

                if (profile != null && profile.length >= 8) {
                    fields[0].setText(profile[0]); // ID
                    fields[3].setText(profile[1]); // Password
                    fields[2].setText(profile[2]); // Email
                    fields[1].setText(profile[3]); // Name
                    fields[4].setText(profile[6]); // School
                    fields[5].setText(profile[7]); // Academic Leader
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error loading profile: " + ex.getMessage());
            }

            JButton btnUpdate = new JButton("Update Profile");
            btnUpdate.setBounds(150, 320, 150, 30);
            add(btnUpdate);

            btnUpdate.addActionListener(e -> {
                try {
                    String id = fields[0].getText();
                    String name = fields[1].getText();
                    String pass = fields[3].getText();

                    if (name.isEmpty() || pass.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Name and Password cannot be empty!");
                        return;
                    }

                    // NOW THIS CALL WILL WORK:
                    saveProfile(id, name, fields[2].getText(), pass, fields[4].getText(), fields[5].getText());

                    lecturer.setName(name);
                    title.setText("Welcome, " + lecturer.getName());
                    JOptionPane.showMessageDialog(this, "Profile Updated Successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Save Error: " + ex.getMessage());
                }
            });

            setVisible(true);
        }

        // --- ADDED THIS METHOD ---
        public void saveProfile(String id, String newName, String newEmail, String newPassword, String newDept, String newLeader) throws IOException {
            File file = new File(Resources.Account);
            List<String> lines = new ArrayList<>();
            boolean found = false;

            // Read and modify in memory
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\s*;\\s*");
                    if (parts.length >= 8 && parts[0].trim().equalsIgnoreCase(id.trim())) {
                        // Re-format the line: ID;Pass;Email;Name;Gender;Role;Dept;Leader
                        String updatedLine = id.trim() + " ; " + newPassword.trim() + " ; " +
                                newEmail.trim() + " ; " + newName.trim() + " ; " +
                                parts[4].trim() + " ; " + parts[5].trim() + " ; " +
                                newDept.trim() + " ; " + newLeader.trim();
                        lines.add(updatedLine);
                        found = true;
                    } else {
                        lines.add(line);
                    }
                }
            }

            if (!found) throw new IOException("User ID not found in file.");

            // Write memory back to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String l : lines) {
                    writer.write(l);
                    writer.newLine();
                }
            }
        }

        public String[] getLecturerProfile(String lecturerId) throws IOException {
            File file = new File(Resources.Account);
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\s*;\\s*");
                    if (parts.length >= 8 && parts[0].trim().equals(lecturerId.trim())) return parts;
                }
            }
            return null;
        }
    }

    // ------------------- 2. Design Assessment Window -------------------
    public class DesignAssessmentWindow extends JFrame {
        private List<String[]> modulesList = new ArrayList<>();
        private DefaultTableModel tableModel;
        // Moved text fields to class level so both the listener and save button can see them
        private JTextField txtID, txtName, txtA1, txtA2, txtA3;

        public DesignAssessmentWindow() {
            setTitle("Design Assessment");
            setSize(900, 500);
            setLocationRelativeTo(null);
            setLayout(null);

            JLabel lblTitle = new JLabel("Design Assessment", SwingConstants.CENTER);
            lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
            lblTitle.setBounds(0, 10, 900, 50);
            add(lblTitle);

            txtID = new JTextField(); txtName = new JTextField();
            txtA1 = new JTextField(); txtA2 = new JTextField(); txtA3 = new JTextField();

            // Row mapping
            addComp("Module ID:", txtID, 80);
            addComp("Module Name:", txtName, 120);
            addComp("Assmt 1:", txtA1, 160);
            addComp("Assmt 2:", txtA2, 200);
            addComp("Assmt 3:", txtA3, 240);

            JButton btnBack = new JButton("Back"); btnBack.setBounds(20, 300, 120, 30); add(btnBack);
            JButton btnSave = new JButton("Save Assessment"); btnSave.setBounds(150, 300, 200, 30); add(btnSave);

            tableModel = new DefaultTableModel(new String[]{"ID", "Name", "A1", "A2", "A3"}, 0) {
                @Override public boolean isCellEditable(int r, int c) { return false; }
            };
            JTable table = new JTable(tableModel);
            JScrollPane scroll = new JScrollPane(table);
            scroll.setBounds(380, 80, 500, 350);
            add(scroll);

            loadModulesData();

            // --- CLICK TABLE TO DISPLAY IN TEXT BOXES ---
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        // Pull data from the table model and put into text fields
                        txtID.setText(tableModel.getValueAt(row, 0).toString());
                        txtName.setText(tableModel.getValueAt(row, 1).toString());
                        txtA1.setText(tableModel.getValueAt(row, 2).toString());
                        txtA2.setText(tableModel.getValueAt(row, 3).toString());
                        txtA3.setText(tableModel.getValueAt(row, 4).toString());

                        // Optional: Disable ID editing so they don't change the primary key
                        txtID.setEditable(false);
                        txtID.setBackground(new Color(240, 240, 240));
                    }
                }
            });

            btnBack.addActionListener(e -> dispose());

            btnSave.addActionListener(e -> {
                saveModuleData();
                loadModulesData(); // Refresh table after saving
                clearFields();
            });

            setVisible(true);
        }

        private void addComp(String l, JTextField f, int y) {
            JLabel lbl = new JLabel(l); lbl.setBounds(20, y, 120, 25); add(lbl);
            f.setBounds(150, y, 200, 25); add(f);
        }

        private void clearFields() {
            txtID.setText(""); txtName.setText(""); txtA1.setText("");
            txtA2.setText(""); txtA3.setText("");
            txtID.setEditable(true);
            txtID.setBackground(Color.WHITE);
        }

        private void loadModulesData() {
            tableModel.setRowCount(0);
            modulesList.clear();

            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Modules))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;

                    String[] parts = line.split(" ; ");

                    if (parts.length >= 9) {   // 9 fields
                        modulesList.add(parts);

                        // Only show first 5 columns in table
                        tableModel.addRow(new Object[]{
                                parts[0], // ID
                                parts[1], // Name
                                parts[2], // A1
                                parts[3], // A2
                                parts[4]  // A3
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void saveModuleData() {

            String id = txtID.getText().trim();
            String name = txtName.getText().trim();
            String a1 = txtA1.getText().trim();
            String a2 = txtA2.getText().trim();
            String a3 = txtA3.getText().trim();

            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Module ID and Name are mandatory.");
                return;
            }

            if (a1.isEmpty()) a1 = "null";
            if (a2.isEmpty()) a2 = "null";
            if (a3.isEmpty()) a3 = "null";

            List<String> lines = new ArrayList<>();
            boolean updated = false;

            try {
                File file = new File(Resources.Modules);

                if (file.exists()) {
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;

                        while ((line = br.readLine()) != null) {
                            String[] p = line.split(" ; ");
                            if (p.length >= 9 && p[0].equalsIgnoreCase(id)) {
                                // KEEP room, lecturer, day, time
                                line = p[0] + " ; " +
                                        p[1] + " ; " +
                                        a1 + " ; " +
                                        a2 + " ; " +
                                        a3 + " ; " +
                                        p[5] + " ; " +
                                        p[6] + " ; " +
                                        p[7] + " ; " +
                                        p[8];

                                updated = true;
                            }
                            lines.add(line);
                        }
                    }
                }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    for (String l : lines) {
                        bw.write(l);
                        bw.newLine();
                    }
                }
                JOptionPane.showMessageDialog(this,
                        updated ? "Module Updated!" : "Module Not Found!");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Critical File Error: " + ex.getMessage());
            }
        }
    }


    // ------------------- 3. Grading Window (KEY-IN MARKS) -------------------
    public class GradingWindow extends JFrame {
        private JComboBox<String> cmbModule;
        private JTable table;
        private DefaultTableModel model;
        private JTextField txtStudentId, txtGPA, txtFeedback1, txtFeedback2, txtFeedback3;

        public GradingWindow() {
            setTitle("Key-in Marks");
            setSize(1100, 600);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout(10, 10));

            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            JButton btnBack = new JButton("Back");
            JLabel lblHeader = new JLabel("Grading Assessment", SwingConstants.CENTER);
            lblHeader.setFont(new Font("Arial", Font.BOLD, 22));

            JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
            filterPanel.add(new JLabel("Filter by Module:"));
            cmbModule = new JComboBox<>();
            fillModuleDropdown();
            filterPanel.add(cmbModule);

            topPanel.add(btnBack, BorderLayout.WEST);
            topPanel.add(lblHeader, BorderLayout.NORTH);
            topPanel.add(filterPanel, BorderLayout.SOUTH);
            add(topPanel, BorderLayout.NORTH);

            JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
            centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

            model = new DefaultTableModel(new String[]{"Module ID", "Module Name", "Assessment 1", "Assessment 2", "Assessment 3", "Account ID"}, 0) {
                @Override public boolean isCellEditable(int r, int c) { return false; }
            };
            table = new JTable(model);
            centerPanel.add(new JScrollPane(table));

            JPanel formPanel = new JPanel(new GridBagLayout());
            GridBagConstraints g = new GridBagConstraints();
            g.insets = new Insets(4, 5, 4, 5); g.fill = GridBagConstraints.HORIZONTAL;

            addFormRow(formPanel, "Account ID (Student):", txtStudentId = new JTextField(), g, 0);
            txtStudentId.setEditable(false);
            txtStudentId.setBackground(new Color(245, 245, 245));

            addFormRow(formPanel, "Total Mark (0-100):", txtGPA = new JTextField(), g, 1);

            g.gridwidth = 2;
            addLabel(formPanel, "Feedback 1:", g, 2);
            g.gridy = 3; formPanel.add(txtFeedback1 = new JTextField(), g);
            addLabel(formPanel, "Feedback 2:", g, 4);
            g.gridy = 5; formPanel.add(txtFeedback2 = new JTextField(), g);
            addLabel(formPanel, "Feedback 3:", g, 6);
            g.gridy = 7; formPanel.add(txtFeedback3 = new JTextField(), g);

            JButton btnSubmit = new JButton("Submit Grade");
            btnSubmit.setFont(new Font("Arial", Font.BOLD, 14));
            g.gridy = 8; g.insets = new Insets(20, 5, 5, 5);
            formPanel.add(btnSubmit, g);

            centerPanel.add(formPanel);
            add(centerPanel, BorderLayout.CENTER);

            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        txtStudentId.setText(model.getValueAt(selectedRow, 5).toString());
                    }
                }
            });

            cmbModule.addActionListener(e -> {
                if (cmbModule.getSelectedItem() != null) {
                    loadModulesToTable(cmbModule.getSelectedItem().toString());
                    txtStudentId.setText("");
                }
            });

            if (cmbModule.getSelectedItem() != null) {
                loadModulesToTable(cmbModule.getSelectedItem().toString());
            }

            btnBack.addActionListener(e -> { dispose(); new LecturerDashboard(lecturer); });
            btnSubmit.addActionListener(e -> saveMarksAndFeedback());

            setVisible(true);
        }

        private void addFormRow(JPanel p, String l, JTextField f, GridBagConstraints g, int y) {
            g.gridx = 0; g.gridy = y; g.gridwidth = 1; g.weightx = 0; p.add(new JLabel(l), g);
            g.gridx = 1; g.weightx = 1.0; p.add(f, g);
        }

        private void addLabel(JPanel p, String t, GridBagConstraints g, int y) {
            g.gridx = 0; g.gridy = y; g.insets = new Insets(12, 5, 0, 5); p.add(new JLabel(t), g);
            g.insets = new Insets(4, 5, 4, 5);
        }

        private void fillModuleDropdown() {
            cmbModule.removeAllItems();
            try (BufferedReader br = new BufferedReader(new FileReader(Resources.Modules))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] p = line.split("\\s*;\\s*");
                    if (p.length >= 2) cmbModule.addItem(p[1].trim());
                }
            } catch (Exception e) { e.printStackTrace(); }
        }

        private void loadModulesToTable(String selectedModuleName) {
            model.setRowCount(0);
            String targetModuleID = "";
            try (BufferedReader br = new BufferedReader(new FileReader(Resources.Modules))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] p = line.split("\\s*;\\s*");
                    if (p.length >= 2 && p[1].trim().equalsIgnoreCase(selectedModuleName.trim())) {
                        targetModuleID = p[0].trim();
                        break;
                    }
                }
            } catch (Exception e) { e.printStackTrace(); }

            try (BufferedReader br = new BufferedReader(new FileReader(Resources.ClassStudentList))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;
                    String[] p = line.split("\\s*;\\s*");
                    if (p.length >= 7 && p[0].trim().equalsIgnoreCase(targetModuleID)) {
                        model.addRow(new Object[]{p[0].trim(), p[1].trim(), p[2].trim(), p[3].trim(), p[4].trim(), p[6].trim()});
                    }
                }
            } catch (Exception e) { e.printStackTrace(); }
        }

        private void saveMarksAndFeedback() {
            String selectedName = (cmbModule.getSelectedItem() != null) ? cmbModule.getSelectedItem().toString().trim() : "";
            String studentId = txtStudentId.getText().trim();
            String markInput = txtGPA.getText().trim();

            // ERROR: Empty Selection
            if (selectedName.isEmpty() || studentId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: Please select a student from the table first.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // ERROR: Number Parsing & Range Validation
            double mark;
            try {
                mark = Double.parseDouble(markInput);
                if (mark < 0 || mark > 100) {
                    JOptionPane.showMessageDialog(this, "Error: Marks must be between 0 and 100.", "Range Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error: Please enter a valid numeric mark (e.g., 85.5).", "Format Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calculation Logic
            String grade, gpa, status;
            if (mark >= 80) { grade = "A+"; gpa = "4.00"; status = "Distinction"; }
            else if (mark >= 75) { grade = "A";  gpa = "3.70"; status = "Distinction"; }
            else if (mark >= 70) { grade = "B+"; gpa = "3.50"; status = "Credit"; }
            else if (mark >= 65) { grade = "B";  gpa = "3.00"; status = "Credit"; }
            else if (mark >= 60) { grade = "C+"; gpa = "2.70"; status = "Pass"; }
            else if (mark >= 55) { grade = "C";  gpa = "2.30"; status = "Pass"; }
            else if (mark >= 50) { grade = "C-"; gpa = "2.00"; status = "Pass"; }
            else if (mark >= 40) { grade = "D";  gpa = "1.70"; status = "Fail(Marginal)"; }
            else if (mark >= 30) { grade = "F+"; gpa = "1.30"; status = "Fail"; }
            else if (mark >= 20) { grade = "F";  gpa = "1.00"; status = "Fail"; }
            else { grade = "F-"; gpa = "0.00"; status = "Fail"; }

            // Identify Target Module ID
            String targetModuleID = "";
            File modFile = new File(Resources.Modules);
            if (!modFile.exists()) {
                JOptionPane.showMessageDialog(this, "Critical Error: Modules file not found!", "System Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(modFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] p = line.split("\\s*;\\s*");
                    if (p.length >= 2 && p[1].trim().equalsIgnoreCase(selectedName)) {
                        targetModuleID = p[0].trim();
                        break;
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading module data: " + e.getMessage());
                return;
            }

            // Update File Logic
            List<String> lines = new ArrayList<>();
            File studentFile = new File(Resources.ClassStudentList);

            try (BufferedReader br = new BufferedReader(new FileReader(studentFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;
                    String[] p = line.split("\\s*;\\s*");

                    // Error check for row length before accessing indices
                    if (p.length >= 7 && p[0].trim().equalsIgnoreCase(targetModuleID) && p[6].trim().equalsIgnoreCase(studentId)) {
                        String[] np = new String[13];
                        System.arraycopy(p, 0, np, 0, Math.min(p.length, 7));

                        np[7] = grade;
                        np[8] = gpa;
                        np[9] = (p.length > 9) ? p[9] : "null";
                        np[10] = txtFeedback1.getText().trim().isEmpty() ? "null" : txtFeedback1.getText().trim();
                        np[11] = txtFeedback2.getText().trim().isEmpty() ? "null" : txtFeedback2.getText().trim();
                        np[12] = txtFeedback3.getText().trim().isEmpty() ? "null" : txtFeedback3.getText().trim();

                        line = String.join(" ; ", np);
                    }
                    lines.add(line);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error accessing student list: " + e.getMessage());
                return;
            }

            // Write back to file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(studentFile))) {
                for (String l : lines) {
                    bw.write(l);
                    bw.newLine();
                }
                JOptionPane.showMessageDialog(this, "Success: Student Grade Processed.\nGrade: " + grade + "\nStatus: " + status);
                loadModulesToTable(selectedName);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Write Error: Could not save data. Check file permissions.", "Disk Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // ------------------- 4. Receive Feedback Window -------------------
    private class ReceiveFeedbackWindow extends JFrame {
        private JComboBox<String> cmbModule;
        private JTextArea txtFeedbackDisplay;

        public ReceiveFeedbackWindow() {
            setTitle("Feedback from Students");
            setSize(600, 500);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout(10, 10));

            // TOP PANEL: Header and Filter
            JPanel topPanel = new JPanel(new GridLayout(2, 1));

            JButton btnBack = new JButton("Back");
            btnBack.setPreferredSize(new Dimension(80, 25));
            JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            navPanel.add(btnBack);

            JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            filterPanel.add(new JLabel("Select Module:"));
            cmbModule = new JComboBox<>();
            fillModuleDropdown();
            filterPanel.add(cmbModule);

            topPanel.add(navPanel);
            topPanel.add(filterPanel);
            add(topPanel, BorderLayout.NORTH);

            // CENTER PANEL: Feedback display
            txtFeedbackDisplay = new JTextArea();
            txtFeedbackDisplay.setEditable(false);
            txtFeedbackDisplay.setFont(new Font("Monospaced", Font.PLAIN, 13));
            txtFeedbackDisplay.setLineWrap(true);
            txtFeedbackDisplay.setWrapStyleWord(true);

            JScrollPane scrollPane = new JScrollPane(txtFeedbackDisplay);
            scrollPane.setBorder(BorderFactory.createTitledBorder("Student Comments & Feedback"));
            add(scrollPane, BorderLayout.CENTER);

            // --- LISTENERS ---

            // FIX: Pass the 'lecturer' object back to the dashboard
            btnBack.addActionListener(e -> {
                dispose();
                new LecturerDashboard(lecturer);
            });

            cmbModule.addActionListener(e -> {
                if (cmbModule.getSelectedItem() != null) {
                    displayFeedback(cmbModule.getSelectedItem().toString());
                }
            });

            // Load initial data
            if (cmbModule.getItemCount() > 0) {
                displayFeedback(cmbModule.getSelectedItem().toString());
            }

            setVisible(true);
        }

        private void fillModuleDropdown() {
            cmbModule.removeAllItems();
            // FIX: Use Resources.Modules instead of MODULES_FILE
            try (BufferedReader br = new BufferedReader(new FileReader(Resources.Modules))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] p = line.split("\\s*;\\s*");
                    if (p.length >= 2) cmbModule.addItem(p[1].trim());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void displayFeedback(String selectedModuleName) {
            txtFeedbackDisplay.setText("");
            StringBuilder sb = new StringBuilder();
            sb.append("FEEDBACK REPORT: ").append(selectedModuleName).append("\n");
            sb.append("Generated on: ").append(new java.util.Date()).append("\n");
            sb.append("============================================================\n\n");

            try (BufferedReader br = new BufferedReader(new FileReader(Resources.ClassStudentList))) {
                String line;
                boolean foundAny = false;

                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;

                    // Split by semicolon with surrounding whitespace handling
                    String[] p = line.split("\\s*;\\s*");

                    // Check if Module Name matches (Index 1) and line has enough columns
                    if (p.length > 9 && p[1].trim().equalsIgnoreCase(selectedModuleName)) {
                        foundAny = true;
                        String studentId = p[6].trim();
                        String studentFeedback = p[9].trim(); // Your specific index for student feedback

                        sb.append("Student ID: ").append(studentId).append("\n");

                        // Check if feedback is actually present or just a "null" placeholder
                        if (studentFeedback.equalsIgnoreCase("null") || studentFeedback.isEmpty()) {
                            sb.append(" > Status: No feedback provided yet.\n");
                        } else {
                            sb.append(" > Feedback: \"").append(studentFeedback).append("\"\n");
                        }

                        sb.append("------------------------------------------------------------\n");
                    }
                }

                if (!foundAny) {
                    sb.append("No student records or enrollment found for this module.");
                }

            } catch (Exception e) {
                sb.append("Error accessing data: ").append(e.getMessage());
            }

            txtFeedbackDisplay.setText(sb.toString());
            txtFeedbackDisplay.setCaretPosition(0);
        }
    }
}