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
        JButton btnReport = new JButton("View Reports");
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

            // Labels and text fields
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

            // Make some fields non-editable
            fields[0].setEditable(false); // Lecturer ID
            fields[2].setEditable(false); // Email
            fields[4].setEditable(false); // School
            fields[5].setEditable(false); // Academic Leader

            // Load profile from Account.txt
            try {
                String lecturerId = lecturer.getLecturerID();
                String[] profile = getLecturerProfile(lecturerId);

                if (profile != null) {
                    fields[0].setText(profile[0]); // ID
                    fields[3].setText(profile[1]); // Password
                    fields[2].setText(profile[2]); // Email
                    fields[1].setText(profile[3]); // Name
                    fields[4].setText(profile[6]); // School
                    fields[5].setText(profile[7]); // Academic Leader
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading profile data");
            }

            // Update button
            JButton btnUpdate = new JButton("Update Profile");
            btnUpdate.setBounds(150, 320, 150, 30);
            add(btnUpdate);

            btnUpdate.addActionListener(e -> {
                try {
                    saveProfile(
                            fields[0].getText(), // ID
                            fields[1].getText(), // Name
                            fields[2].getText(), // Email
                            fields[3].getText(), // Password
                            fields[4].getText(), // School
                            fields[5].getText()  // Academic Leader
                    );

                    lecturer.setName(fields[1].getText());
                    title.setText("Welcome, " + lecturer.getName());
                    title.setFont(new Font("Arial", Font.BOLD, 40));
                    title.setBounds(0, 30, 800, 60);
                    JOptionPane.showMessageDialog(this, "Profile Updated!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving profile: " + ex.getMessage());
                }
            });

            setVisible(true);
        }

        public void saveProfile(String id, String newName, String newEmail,
                                       String newPassword, String newDept, String newLeader) throws IOException {

            File file = new File(Resources.Account);
            List<String> lines = new ArrayList<>();

            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(" ; ");

                        if (parts.length >= 8 && parts[0].trim().equals(id.trim())) {
                            // Keep original gender and role
                            String gender = parts[4].trim();
                            String role = parts[5].trim();

                            String updatedLine =
                                    id.trim() + " ; " +
                                            newPassword.trim() + " ; " +
                                            newEmail.trim() + " ; " +
                                            newName.trim() + " ; " +
                                            gender + " ; " +
                                            role + " ; " +
                                            newDept.trim() + " ; " +
                                            newLeader.trim();

                            lines.add(updatedLine);
                        } else {
                            lines.add(line);
                        }
                    }
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String l : lines) {
                    writer.write(l);
                    writer.newLine();
                }
            }
        }

        public String[] getLecturerProfile(String lecturerId) throws IOException {
            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Account))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" ; ");
                    if (parts.length >= 8 && parts[0].equals(lecturerId)) {
                        return parts;
                    }
                }
            }
            return null;
        }
    }

    // ------------------- 2. Design Assessment Window -------------------
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
                    String[] parts = line.split("\\s*;\\s*");
                    if (parts.length >= 5) {
                        modulesList.add(parts);
                        tableModel.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3], parts[4]});
                    }
                }
            } catch (Exception e) { e.printStackTrace(); }
        }

        private void saveModuleData() {
            String id = txtID.getText().trim();
            String name = txtName.getText().trim();
            String a1 = txtA1.getText().trim();
            String a2 = txtA2.getText().trim();
            String a3 = txtA3.getText().trim();

            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID and Name are required!");
                return;
            }

            boolean updated = false;
            List<String> lines = new ArrayList<>();

            try {
                // If the file exists, check for existing ID to update
                File file = new File(Resources.Modules);
                if (file.exists()) {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] p = line.split("\\s*;\\s*");
                        if (p[0].equalsIgnoreCase(id)) {
                            // Replace with new data
                            line = id + " ; " + name + " ; " + a1 + " ; " + a2 + " ; " + a3 + " ; " + lecturer.getName();
                            updated = true;
                        }
                        lines.add(line);
                    }
                    br.close();
                }

                if (!updated) {
                    // It's a new module, add it to the end
                    lines.add(id + " ; " + name + " ; " + a1 + " ; " + a2 + " ; " + a3 + " ; " + lecturer.getName());
                }

                BufferedWriter bw = new BufferedWriter(new FileWriter(Resources.Modules));
                for (String l : lines) { bw.write(l); bw.newLine(); }
                bw.close();

                JOptionPane.showMessageDialog(this, updated ? "Module Updated!" : "New Module Added!");

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // ------------------- 3. Grading Window (KEY-IN MARKS) -------------------
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
            String selectedName = cmbModule.getSelectedItem().toString().trim();
            String studentId = txtStudentId.getText().trim();
            String markInput = txtGPA.getText().trim();

            if (studentId.isEmpty() || markInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a student and enter a mark.");
                return;
            }

            // Conversion Logic Based on your System
            double mark;
            try { mark = Double.parseDouble(markInput); }
            catch (Exception e) { JOptionPane.showMessageDialog(this, "Invalid mark!"); return; }

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

            String targetModuleID = "";
            try (BufferedReader br = new BufferedReader(new FileReader(Resources.Modules))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] p = line.split("\\s*;\\s*");
                    if (p.length >= 2 && p[1].trim().equalsIgnoreCase(selectedName)) {
                        targetModuleID = p[0].trim();
                        break;
                    }
                }
            } catch (Exception e) { e.printStackTrace(); }

            List<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(Resources.ClassStudentList))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;
                    String[] p = line.split("\\s*;\\s*");
                    if (p.length >= 7 && p[0].trim().equalsIgnoreCase(targetModuleID) && p[6].trim().equalsIgnoreCase(studentId)) {
                        // Create 14 columns (Index 0 to 13) to include Status
                        String[] np = new String[14];
                        java.util.Arrays.fill(np, "null");
                        System.arraycopy(p, 0, np, 0, Math.min(p.length, 14));

                        np[7] = markInput;   // Mark
                        np[8] = grade;       // Grade
                        np[9] = gpa;         // GPA Points
                        np[10] = txtFeedback1.getText().trim();
                        np[11] = txtFeedback2.getText().trim();
                        np[12] = txtFeedback3.getText().trim();
                        np[13] = status;     // Achievement Status

                        line = String.join(" ; ", np);
                    }
                    lines.add(line);
                }
            } catch (Exception e) { e.printStackTrace(); }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(Resources.ClassStudentList))) {
                for (String l : lines) { bw.write(l); bw.newLine(); }
                JOptionPane.showMessageDialog(this, "Grading Successful!\nGrade: " + grade + "\nGPA: " + gpa + "\nStatus: " + status);
                loadModulesToTable(selectedName);
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    // ------------------- 4. Receive Feedback Window -------------------
    private class ReceiveFeedbackWindow extends JFrame {
        public ReceiveFeedbackWindow() {
            setTitle("Reports");
            setSize(400, 300);
            setLocationRelativeTo(null);
            setLayout(new FlowLayout());
            JButton b = new JButton("Back");
            b.addActionListener(e -> { dispose(); new LecturerDashboard(lecturer); });
            add(new JLabel("Report Viewer Module Active"));
            add(b);
            setVisible(true);
        }
    }
}