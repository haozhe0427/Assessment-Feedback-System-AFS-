import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ACADEMIC LEADER DASHBOARD
public class AcademicLeaderDashboard extends JFrame {

    public AcademicLeaderDashboard() {

        setTitle("Dashboard - " + Name.getName());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel lblWelcome = new JLabel("Welcome, " + Name.getName(), SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(lblWelcome, BorderLayout.NORTH);

        // Main Menu Buttons Panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 2, 20, 20));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnEditProfile = new JButton("Edit Personal Profile");
        JButton btnManageModules = new JButton("Manage Modules (CRUD & Assign)");
        JButton btnAnalyzeReports = new JButton("Analyze Reports");
        JButton btnLogout = new JButton("Logout");

        // Add buttons
        menuPanel.add(btnEditProfile);
        menuPanel.add(btnManageModules);
        menuPanel.add(btnAnalyzeReports);
        menuPanel.add(btnLogout);

        add(menuPanel, BorderLayout.CENTER);

        // --- Action Listeners (Placeholders for next steps) ---

        btnEditProfile.addActionListener(_ -> {
            dispose();
            EditProfileGUI_AcademicLeader editProfileGUI_academicLeader = new EditProfileGUI_AcademicLeader();
            editProfileGUI_academicLeader.setVisible(true);
            editProfileGUI_academicLeader.setLocationRelativeTo(null);
        });

        // Inside DashboardFrame.java
        btnManageModules.addActionListener(_ -> {
            dispose();
            CreateModulesGUI_AcademicLeader createModulesGUI_academicLeader = new CreateModulesGUI_AcademicLeader();
            createModulesGUI_academicLeader.setVisible(true);
            createModulesGUI_academicLeader.setLocationRelativeTo(null);
        });

        // Inside DashboardFrame.java
        btnAnalyzeReports.addActionListener(_ -> {
            dispose();
            GenerateReportGUI_AcademicLeader generateReportGUI_academicLeader = new GenerateReportGUI_AcademicLeader();
            generateReportGUI_academicLeader.setVisible(true);
            generateReportGUI_academicLeader.setLocationRelativeTo(null);
        });

        btnLogout.addActionListener(_ -> {
            Session.logout();
            LoginGUI loginGUI = new LoginGUI();
            this.dispose();
            loginGUI.setVisible(true);
            loginGUI.setLocationRelativeTo(null);
        });
    }
}




// EDIT PROFILE (ACADEMIC LEADER)
class EditProfileGUI_AcademicLeader extends JFrame {
    private final JTextField txtName;
    private final JTextField txtEmail;
    private final JPasswordField txtPassword;
    private final User currentUser;

    public EditProfileGUI_AcademicLeader() {
        // Get the currently logged-in user
        currentUser = Session.getCurrentUser();

        setTitle("Edit Personal Profile");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only close this window, not the app
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10)); // Grid layout for neat rows

        // --- ID (Read Only) ---
        add(new JLabel("User ID (Cannot change):"));
        JTextField txtID = new JTextField(currentUser.getId());
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
        JTextField txtGender = new JTextField(currentUser.getGender());
        add(txtGender);

        // --- Role (Read Only) ---
        add(new JLabel("Role:"));
        JTextField txtRole = new JTextField(currentUser.getRole());
        txtRole.setEditable(false);
        add(txtRole);

        // --- Buttons ---
        JButton btnUpdate = new JButton("Update Profile");
        JButton btnCancel = new JButton("Cancel");
        add(btnUpdate);
        add(btnCancel);

        // --- Button Actions ---

        // UPDATE BUTTON
        btnUpdate.addActionListener(_ -> {
            // 1. Get data from text fields
            String newName = txtName.getText().trim();
            String newPass = new String(txtPassword.getPassword()).trim();
            String newEmail = txtEmail.getText().trim();


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
        });

        // CANCEL BUTTON
        btnCancel.addActionListener(_ -> {
            dispose();
            AcademicLeaderDashboard academicLeaderDashboard = new AcademicLeaderDashboard();
            academicLeaderDashboard.setVisible(true);
            academicLeaderDashboard.setLocationRelativeTo(null);
        });
    }
}




// CREATE MODULES
class CreateModulesGUI_AcademicLeader extends JFrame {
    private final JTable table;
    private final DefaultTableModel tableModel;

    public CreateModulesGUI_AcademicLeader() {
        setTitle("Manage Modules & Assign Lecturers");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- Top Panel (Buttons) ---
        JPanel topPanel = new JPanel();
        JButton btnAdd = new JButton("Add Module");
        JButton btnEdit = new JButton("Edit / Assign Lecturer");
        JButton btnDelete = new JButton("Delete");
        JButton btnExit = new JButton("Exit");

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);
        topPanel.add(btnExit);
        add(topPanel, BorderLayout.NORTH);

        // --- Table Setup ---
        String[] columns = {"Code", "Name", "Assmnt 1", "Assmnt 2", "Assmnt 3", "Location", "Lecturer"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        refreshTable(); // Load initial data
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- ACTIONS ---

        // 1. DELETE BUTTON
        btnDelete.addActionListener(_ -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a module to delete.");
                return;
            }
            String code = (String) tableModel.getValueAt(row, 0);
            if (JOptionPane.showConfirmDialog(this, "Delete " + code + "?") == JOptionPane.YES_OPTION) {
                TextFileUtils.deleteModule(code);
                refreshTable();
            }
        });
        btnExit.addActionListener(_ -> {
            this.dispose(); // Closes this window, returning to Dashboard
            AcademicLeaderDashboard academicLeaderDashboard = new AcademicLeaderDashboard();
            academicLeaderDashboard.setVisible(true);
            academicLeaderDashboard.setLocationRelativeTo(null);
        });

        // 2. ADD BUTTON
        btnAdd.addActionListener(_ -> showModuleDialog(null));

        // 3. EDIT BUTTON
        btnEdit.addActionListener(_ -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                // Gather data from selected row
                TextFileUtils.Module m = new TextFileUtils.Module(
                        (String)table.getValueAt(row, 0), (String)table.getValueAt(row, 1),
                        (String)table.getValueAt(row, 2), (String)table.getValueAt(row, 3),
                        (String)table.getValueAt(row, 4), (String)table.getValueAt(row, 5),
                        (String)table.getValueAt(row, 6)
                );
                showModuleDialog(m);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a module to edit.");
            }
        });
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (TextFileUtils.Module m : TextFileUtils.getAllModules()) {
            tableModel.addRow(new Object[]{m.code, m.name, m.a1, m.a2, m.a3, m.loc, m.lec});
        }
    }

    private void showModuleDialog(TextFileUtils.Module existing) {
        JDialog d = new JDialog(this, (existing == null ? "Add Module" : "Edit Module"), true);
        d.setLayout(new GridLayout(9, 2, 5, 5)); // Added gaps for better look
        d.setSize(400, 500);
        d.setLocationRelativeTo(this);

        // Fields
        JTextField tCode = new JTextField(existing != null ? existing.code : "");
        if(existing != null) tCode.setEditable(false); // Code is unique ID, cannot change

        JTextField tName = new JTextField(existing != null ? existing.name : "");
        JTextField tA1 = new JTextField(existing != null ? existing.a1 : "Assignment (55%)");
        JTextField tA2 = new JTextField(existing != null ? existing.a2 : "Final Exam (45%)");
        JTextField tA3 = new JTextField(existing != null ? existing.a3 : "NULL");

        // Location Field (Target for error handling)
        JTextField tLoc = new JTextField(existing != null ? existing.loc : "");

        JTextField tLec = new JTextField(existing != null ? existing.lec : "NULL");
        JButton btnSave = new JButton("Save");

        // Add to Dialog
        d.add(new JLabel("Code:")); d.add(tCode);
        d.add(new JLabel("Name:")); d.add(tName);
        d.add(new JLabel("Ass 1:")); d.add(tA1);
        d.add(new JLabel("Ass 2:")); d.add(tA2);
        d.add(new JLabel("Ass 3:")); d.add(tA3);
        d.add(new JLabel("Location (e.g. A-1-4):")); d.add(tLoc);
        d.add(new JLabel("Lecturer:")); d.add(tLec);
        d.add(btnSave);

        // SAVE ACTION WITH ERROR HANDLING
        btnSave.addActionListener(_ -> {
            // --- 1. Basic Empty Checks ---
            if (tCode.getText().trim().isEmpty() || tName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(d, "Module Code and Name are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String val1 = tA1.getText().trim();
            String val2 = tA2.getText().trim();
            String percentRegex = ".+\\(\\d+%\\)$";

            if (!val1.equalsIgnoreCase("NULL") && !val1.matches(percentRegex)) {
                JOptionPane.showMessageDialog(d,
                        "Invalid Assessment 1 Format.\nMust look like: 'Assignment (50%)'",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!val2.equalsIgnoreCase("NULL") && !val2.matches(percentRegex)) {
                JOptionPane.showMessageDialog(d,
                        "Invalid Assessment 2 Format.\nMust look like: 'Final Exam (50%)'",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            // --- 2. LOCATION ERROR HANDLING (New Feature) ---
            String locInput = tLoc.getText().trim();

            // Check A: Is it empty?
            if (locInput.isEmpty()) {
                JOptionPane.showMessageDialog(d, "Location cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check B: Does it match the format 'Block-Level-Room' (e.g., A-1-4)?
            // Regex explanation: ^[A-Z] (One uppercase letter) - \\d+ (number) - \\d+ (number)$
            if (!locInput.matches("^[A-Z]-\\d+-\\d+$")) {
                JOptionPane.showMessageDialog(d,
                        "Invalid Location Format.\nCorrect format: Block-Floor-Room (e.g., A-1-4, B-6-6)",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // --- 3. Duplicate Code Check (Only when Adding new) ---
            List<TextFileUtils.Module> list = TextFileUtils.getAllModules();
            if (existing == null) {
                for (TextFileUtils.Module m : list) {
                    if (m.code.equals(tCode.getText().trim())) {
                        JOptionPane.showMessageDialog(d, "Module Code already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            // --- 4. Save Logic ---
            TextFileUtils.Module newMod = new TextFileUtils.Module(
                    tCode.getText().trim(), tName.getText().trim(), tA1.getText().trim(), tA2.getText().trim(),
                    tA3.getText().trim(), locInput, tLec.getText().trim()
            );

            if (existing == null) { // Add
                list.add(newMod);
            } else { // Edit
                for (int i=0; i<list.size(); i++) {
                    if (list.get(i).code.equals(existing.code)) {
                        list.set(i, newMod);
                        break;
                    }
                }
            }

            boolean saved = TextFileUtils.saveModules(list);
            if (saved) {
                refreshTable();
                d.dispose();
                JOptionPane.showMessageDialog(this, "Module Saved Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Error writing to file.", "System Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        d.setVisible(true);
    }
}




// GENERATE REPORT
class GenerateReportGUI_AcademicLeader extends JFrame {

    public GenerateReportGUI_AcademicLeader() {
        setTitle("Analyzed Reports");
        setSize(800, 700);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use a ScrollPane in case the window is too small
        JScrollPane scrollPane = new JScrollPane(new ReportPanel());
        add(scrollPane);
        JPanel bottomPanel = new JPanel();
        JButton btnExit = new JButton("Exit");

        // Make the button look nice
        btnExit.setPreferredSize(new Dimension(100, 30));

        bottomPanel.add(btnExit);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- EXIT ACTION ---
        btnExit.addActionListener(e -> {
            this.dispose(); // Close report, show Dashboard
        });
    }

    // --- INNER CLASS: The Panel that draws the charts ---
    private class ReportPanel extends JPanel {

        // Data for the charts
        private Map<String, Integer> lecturerData;
        private Map<String, Integer> locationData;

        // Colors for slices (Gold, Blue, Cyan, Green, Dark Blue, Orange, Red)
        private final Color[] colors = {
                new Color(220, 180, 50),  // Gold/Yellow
                new Color(50, 120, 220),  // Blue
                new Color(40, 180, 180),  // Teal/Cyan
                new Color(100, 200, 100), // Green
                new Color(30, 60, 120),   // Dark Blue
                new Color(220, 100, 50),  // Orange
                new Color(200, 50, 50)    // Red
        };

        public ReportPanel() {
            setPreferredSize(new Dimension(750, 800)); // Ensure scrolling works
            calculateData();
        }

        private void calculateData() {
            List<TextFileUtils.Module> modules = TextFileUtils.getAllModules();
            lecturerData = new HashMap<>();
            locationData = new HashMap<>();

            for (TextFileUtils.Module m : modules) {
                // 1. Count by Lecturer
                String lec = m.lec.trim();
                if (lec.equalsIgnoreCase("NULL") || lec.isEmpty()) lec = "Unassigned";
                lecturerData.put(lec, lecturerData.getOrDefault(lec, 0) + 1);

                // 2. Count by Location Block (First letter: A, B, E)
                String loc = m.loc.trim();
                String block = "Unknown";
                if (!loc.equalsIgnoreCase("NULL") && !loc.isEmpty()) {
                    // Extract just the block (e.g. "A" from "A-1-4")
                    if (loc.contains("-")) {
                        block = "Block " + loc.split("-")[0];
                    } else {
                        block = loc;
                    }
                }
                locationData.put(block, locationData.getOrDefault(block, 0) + 1);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // --- CHART 1: Modules by Lecturer (Top) ---
            drawPieChart(g2, "Modules Assigned by Lecturer", lecturerData, 50);

            // --- CHART 2: Modules by Location (Bottom) ---
            drawPieChart(g2, "Modules by Location (Block)", locationData, 400);
        }

        private void drawPieChart(Graphics2D g, String title, Map<String, Integer> data, int yPosition) {
            // 1. Draw Title
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.setColor(Color.BLACK);
            FontMetrics fm = g.getFontMetrics();
            int titleWidth = fm.stringWidth(title);
            g.drawString(title, (getWidth() - titleWidth) / 2, yPosition + 30);

            // Calculate Total
            int total = 0;
            for (int count : data.values()) total += count;

            // Chart Coordinates
            int chartX = 150;
            int chartY = yPosition + 60;
            int diameter = 250;

            // Legend Coordinates
            int legendX = 450;
            int legendY = yPosition + 80;

            // 2. Draw Slices
            int startAngle = 90;
            int colorIndex = 0;

            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                String label = entry.getKey();
                int value = entry.getValue();

                // Calculate Angle size
                int arcAngle = (int) Math.round((double) value / total * 360);

                g.setColor(colors[colorIndex % colors.length]);
                g.fillArc(chartX, chartY, diameter, diameter, startAngle, arcAngle);


                // Draw Legend for this slice
                g.fillRect(legendX, legendY, 15, 15); // Color box
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.PLAIN, 12));

                // Calculate percentage
                int percent = (int) Math.round((double) value / total * 100);
                String legendText = label + " (" + percent + "%)";
                g.drawString(legendText, legendX + 25, legendY + 12);

                // Update positions for next slice
                startAngle += arcAngle;
                legendY += 30; // Move legend down
                colorIndex++;
            }

            g.setColor(Color.WHITE);
            int holeSize = diameter / 3;
            int holeX = chartX + (diameter - holeSize) / 2;
            int holeY = chartY + (diameter - holeSize) / 2;
            g.fillOval(holeX, holeY, holeSize, holeSize);
        }
    }
}