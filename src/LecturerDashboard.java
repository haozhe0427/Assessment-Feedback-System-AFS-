import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LecturerDashboard extends JFrame {

    private static final String MODULES_FILE = "D:/intelij save/src/Text File/Modules.txt";
    private static final String LECTURER_NAME = "Joshua Koroh Pudin";

    public LecturerDashboard() {
        setTitle("AFS - Lecturer Dashboard");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        int frameWidth = getWidth();
        JLabel title = new JLabel("Welcome, Joshua Koroh Pudin", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setBounds(0, 30, frameWidth, 60); // full width of frame
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

        // Button actions
        btnProfile.addActionListener(e -> new ProfileWindow());
        btnDesign.addActionListener(e -> new DesignAssessmentWindow());
        btnGrade.addActionListener(e -> {
            dispose();
            new GradingWindow();
        });
        btnReport.addActionListener(e -> {
            dispose();
            new ReceiveFeedbackWindow();
        });
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new LoginGUI();
            }
        });

        setVisible(true);
    }

    // ------------------- Profile Window -------------------
    private static class ProfileWindow extends JFrame {
        public ProfileWindow() {
            setTitle("Update Profile");
            setSize(400, 400);
            setLocationRelativeTo(null);
            setLayout(null);

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

            txtId.setEditable(false);
            txtEmail.setEditable(false);
            txtDept.setEditable(false);
            txtLeader.setEditable(false);

            btnBack.addActionListener(e -> dispose());
            btnUpdate.addActionListener(e -> {
                try {
                    FileHandler.saveProfile(
                            txtId.getText(),
                            txtName.getText(),
                            txtEmail.getText(),
                            txtPassword.getText(),
                            txtDept.getText(),
                            txtLeader.getText()
                    );
                    JOptionPane.showMessageDialog(this, "Profile Updated!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving profile: " + ex.getMessage());
                }
            });

            setVisible(true);
        }
    }

    // ------------------- Design Assessment Window -------------------
    public class DesignAssessmentWindow extends JFrame {

        private List<String[]> modulesList = new ArrayList<>();

        public DesignAssessmentWindow() {
            setTitle("Design Assessment");
            setSize(900, 500);
            setLocationRelativeTo(null);
            setLayout(null);

            JLabel lblTitle = new JLabel("Design Assessment", SwingConstants.CENTER);
            lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
            lblTitle.setBounds(0, 10, 900, 50);
            add(lblTitle);

            // Input fields
            JLabel lblModuleId = new JLabel("Module ID:");
            lblModuleId.setBounds(20, 80, 120, 25);
            add(lblModuleId);
            JTextField txtModuleId = new JTextField();
            txtModuleId.setBounds(150, 80, 200, 25);
            add(txtModuleId);

            JLabel lblModuleName = new JLabel("Module Name:");
            lblModuleName.setBounds(20, 120, 120, 25);
            add(lblModuleName);
            JTextField txtModuleName = new JTextField();
            txtModuleName.setBounds(150, 120, 200, 25);
            add(txtModuleName);

            JLabel lblAssmt1 = new JLabel("Assessment 1:");
            lblAssmt1.setBounds(20, 160, 120, 25);
            add(lblAssmt1);
            JTextField txtAssmt1 = new JTextField();
            txtAssmt1.setBounds(150, 160, 200, 25);
            add(txtAssmt1);

            JLabel lblAssmt2 = new JLabel("Assessment 2:");
            lblAssmt2.setBounds(20, 200, 120, 25);
            add(lblAssmt2);
            JTextField txtAssmt2 = new JTextField();
            txtAssmt2.setBounds(150, 200, 200, 25);
            add(txtAssmt2);

            JLabel lblAssmt3 = new JLabel("Assessment 3:");
            lblAssmt3.setBounds(20, 240, 120, 25);
            add(lblAssmt3);
            JTextField txtAssmt3 = new JTextField();
            txtAssmt3.setBounds(150, 240, 200, 25);
            add(txtAssmt3);

            // Buttons
            JButton btnBack = new JButton("Back");
            btnBack.setBounds(20, 300, 120, 30);
            add(btnBack);

            JButton btnSave = new JButton("Save Assessment");
            btnSave.setBounds(150, 300, 200, 30);
            add(btnSave);

            // Table
            String[] columns = {"Module ID", "Module Name", "Assessment 1", "Assessment 2", "Assessment 3"};
            DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
            JTable table = new JTable(tableModel);
            JScrollPane scroll = new JScrollPane(table);
            scroll.setBounds(380, 80, 500, 350);
            add(scroll);

            // Load modules
            loadModulesToTable(tableModel);

            // Table click to edit
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = table.getSelectedRow();
                    if (row >= 0) {
                        txtModuleId.setText((String)table.getValueAt(row, 0));
                        txtModuleName.setText((String)table.getValueAt(row, 1));
                        txtAssmt1.setText((String)table.getValueAt(row, 2));
                        txtAssmt2.setText((String)table.getValueAt(row, 3));
                        txtAssmt3.setText((String)table.getValueAt(row, 4));
                    }
                }
            });

            // Button actions
            btnBack.addActionListener(e -> {
                dispose();
                new LecturerDashboard();
            });

            btnSave.addActionListener(e -> {
                String id = txtModuleId.getText().trim();
                String name = txtModuleName.getText().trim();
                String a1 = txtAssmt1.getText().trim();
                String a2 = txtAssmt2.getText().trim();
                String a3 = txtAssmt3.getText().trim();

                if (id.isEmpty() || name.isEmpty() || a1.isEmpty() || a2.isEmpty() || a3.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean found = false;
                for (int i = 0; i < modulesList.size(); i++) {
                    if (modulesList.get(i)[0].equals(id)) {
                        modulesList.set(i, new String[]{id, name, a1, a2, a3, LECTURER_NAME});
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    modulesList.add(new String[]{id, name, a1, a2, a3, LECTURER_NAME});
                }

                // Write all modules to file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(MODULES_FILE))) {
                    for (String[] parts : modulesList) {
                        writer.write(String.join(" ; ", parts));
                        writer.newLine();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving module: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                loadModulesToTable((DefaultTableModel)table.getModel());

                txtModuleId.setText("");
                txtModuleName.setText("");
                txtAssmt1.setText("");
                txtAssmt2.setText("");
                txtAssmt3.setText("");

                JOptionPane.showMessageDialog(this, found ? "Module updated!" : "Module added!");
            });

            setVisible(true);
        }

        private void loadModulesToTable(DefaultTableModel tableModel) {
            File file = new File(MODULES_FILE);
            modulesList.clear();
            tableModel.setRowCount(0);

            if (!file.exists()) return;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" ; ");
                    if (parts.length >= 5) {
                        modulesList.add(parts);
                        tableModel.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3], parts[4]});
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // ------------------- Grading Window -------------------
    private static class GradingWindow extends JFrame {
        public GradingWindow() {
            setTitle("Key-in Marks");
            setSize(900, 500);
            setLocationRelativeTo(null);
            setLayout(null);

            JLabel lblTitle = new JLabel("Grading Assessment", SwingConstants.CENTER);
            lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
            lblTitle.setBounds(0, 10, 900, 50);
            add(lblTitle);

            JButton btnBack = new JButton("Back");
            btnBack.setBounds(20, 25, 100, 30);
            btnBack.addActionListener(e -> new LecturerDashboard());
            add(btnBack);

            setVisible(true);
        }
    }

    // ------------------- Receive Feedback Window -------------------
    private static class ReceiveFeedbackWindow extends JFrame {
        public ReceiveFeedbackWindow() {
            setTitle("View Reports");
            setSize(700, 600);
            setLocationRelativeTo(null);
            setLayout(null);

            JButton btnBack = new JButton("Back");
            btnBack.setBounds(20, 25, 100, 30);
            btnBack.addActionListener(e -> new LecturerDashboard());
            add(btnBack);

            setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LecturerDashboard::new);
    }
}
