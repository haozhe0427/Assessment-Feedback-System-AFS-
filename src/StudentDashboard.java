import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// STUDENT DASHBOARD
public class StudentDashboard extends JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(StudentDashboard.class.getName());

    public StudentDashboard() {
        initComponents();
        // To remove the focus box around "Edit Profile"
        jButton1.setFocusPainted(false);
        jButton2.setFocusPainted(false);
        jButton3.setFocusPainted(false);
        jButton4.setFocusPainted(false);
    }

    private void initComponents() {

        JLabel jLabel1 = new JLabel(String.valueOf(JFrame.CENTER_ALIGNMENT));
        JLabel jLabel2 = new JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setText("Assessment Feedback System");

        jLabel2.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18));
        jLabel2.setText("Hi, " + StudentSession.getStudentName() + "! Welcome to your dashboard.");
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton1.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18));
        jButton1.setText("Edit Profile");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18));
        jButton2.setText("Register for classes");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18));
        jButton3.setText("Check Result");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton4.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18));
        jButton4.setText("Provide Comments");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(131, 131, 131)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(145, 145, 145)
                                                .addComponent(jLabel2)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        EditProfileGUI_Student editProfileGUI_student = new EditProfileGUI_Student();
        editProfileGUI_student.setVisible(true);
        this.dispose();
        editProfileGUI_student.setLocationRelativeTo(null);
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        RegisterClassesGUI_Student registerClassesGUI_student = new RegisterClassesGUI_Student();
        registerClassesGUI_student.setVisible(true);
        this.dispose();
        registerClassesGUI_student.setLocationRelativeTo(null);
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        CheckResultGUI_Student resultGUI = new CheckResultGUI_Student();
        resultGUI.setVisible(true);
        this.dispose();
        resultGUI.setLocationRelativeTo(null);
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        GiveFeedbackGUI_Student feedbackGUI = new GiveFeedbackGUI_Student();
        feedbackGUI.setVisible(true);
        this.dispose();
        feedbackGUI.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new StudentDashboard().setVisible(true));
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
}




// EDIT PROFILE (STUDENT)
class EditProfileGUI_Student extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EditProfileGUI_Student.class.getName());

    public EditProfileGUI_Student() {
        initComponents();
        fillTextFields();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        nameTF = new javax.swing.JTextField();
        emailTF = new javax.swing.JTextField();
        passwordPF = new javax.swing.JPasswordField();
        chkShowPassword = new javax.swing.JCheckBox();
        JButton updateBtn = new JButton();
        JButton exitBtn = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setText("Profile");

        jLabel2.setText("Name: ");

        jLabel3.setText("Email:");

        jLabel4.setText("Password:");

        chkShowPassword.setText(" Show password");
        chkShowPassword.addActionListener(this::chkShowPasswordActionPerformed);

        updateBtn.setText("Update");
        updateBtn.addActionListener(this::updateBtnActionPerformed);

        exitBtn.setText("Exit");
        exitBtn.addActionListener(this::exitBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(exitBtn)
                                                .addGap(146, 146, 146)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(165, 165, 165)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(chkShowPassword)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(passwordPF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                                                        .addComponent(emailTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(nameTF, javax.swing.GroupLayout.Alignment.TRAILING)))
                                                        .addComponent(updateBtn))))
                                .addContainerGap(241, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkShowPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(updateBtn)
                                .addContainerGap(180, Short.MAX_VALUE))
        );

        pack();
    }

    private void fillTextFields(){
        nameTF.setText(StudentSession.getStudentName());
        emailTF.setText(StudentSession.getStudentEmail());
        passwordPF.setText(StudentSession.getStudentPassword());
    }

    private void chkShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {
        if (chkShowPassword.isSelected()) {
            passwordPF.setEchoChar((char) 0);
        } else {
            passwordPF.setEchoChar('*');
        }
    }

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String studentName = nameTF.getText().trim();
        String studentEmail = emailTF.getText().trim();
        char[] passChars = passwordPF.getPassword();
        String studentPassword = new String(passChars).trim();

        if(studentName.isEmpty() || studentEmail.isEmpty() || studentPassword.isEmpty()){
            JOptionPane.showMessageDialog(
                    this,
                    "All fields are required",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        List<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(Resources.Login))){

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < lines.size(); i++) {
            String[] columns = lines.get(i).split(";");

            String userId = columns[0].trim();

            if (userId.equals(StudentSession.getStudentId())) {
                columns[1] = " " + studentPassword + " ";
                columns[2] = " " + studentEmail + " ";
                columns[3] = " " + studentName + " ";

                String updatedLine = String.join(";", columns);
                lines.set(i, updatedLine);
                break;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Resources.Login))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            StudentSession.login(StudentSession.getStudentId(), studentName, studentEmail, studentPassword);
            fillTextFields();
            JOptionPane.showMessageDialog(
                    this,
                    "Successfully updated"
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {
        StudentDashboard dashboard = new StudentDashboard();
        dashboard.setVisible(true);
        this.dispose();
        dashboard.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new EditProfileGUI_Student().setVisible(true));
    }

    private javax.swing.JCheckBox chkShowPassword;
    private javax.swing.JTextField emailTF;
    private javax.swing.JTextField nameTF;
    private javax.swing.JPasswordField passwordPF;
}




class RegisterClassesGUI_Student extends JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegisterClassesGUI_Student.class.getName());

    private JTable currentClassTable;
    private JTable newClassTable;

    public RegisterClassesGUI_Student() {
        initComponents();
        loadDataIntoTable();
    }

    private void initComponents() {
        // Components
        JLabel jLabel1 = new JLabel();
        JButton exitBtn = new JButton();
        JLabel jLabel2 = new JLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        currentClassTable = new JTable();
        JLabel jLabel3 = new JLabel();
        JScrollPane jScrollPane3 = new JScrollPane();
        newClassTable = new JTable();
        JButton enrollBtn = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setText("Class Registration");

        exitBtn.setText("Exit");
        exitBtn.addActionListener(this::exitBtnActionPerformed);

        jLabel2.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLabel2.setText("Classes you have enrolled");

        // Setup Current Class Table
        currentClassTable.setModel(new DefaultTableModel(
                new Object [][] {},
                new String [] { "Title 1", "Title 2", "Title 3", "Title 4" }
        ));
        currentClassTable.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(currentClassTable);

        jLabel3.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLabel3.setText("New classes");

        // Setup New Class Table
        newClassTable.setModel(new DefaultTableModel(
                new Object [][] {},
                new String [] { "Title 1", "Title 2", "Title 3", "Title 4" }
        ));
        jScrollPane3.setViewportView(newClassTable);

        enrollBtn.setText("Enroll");
        enrollBtn.addActionListener(this::enrollBtnActionPerformed);

        // Layout (Standard Swing GroupLayout)
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(exitBtn)
                                                                .addGap(132, 132, 132)
                                                                .addComponent(jLabel1))
                                                        .addComponent(enrollBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(exitBtn)
                                        .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enrollBtn)
                                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null); // Center on screen
    }

    private void exitBtnActionPerformed(ActionEvent evt) {
        StudentDashboard dashboard = new StudentDashboard();
        dashboard.setVisible(true);
        this.dispose();
        dashboard.setLocationRelativeTo(null);
    }

    private void enrollBtnActionPerformed(ActionEvent evt) {
        int selectedRow = newClassTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a class to enroll!");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) newClassTable.getModel();
        // Extract data from the selected row
        String moduleID = model.getValueAt(selectedRow, 0).toString();
        String courseName = model.getValueAt(selectedRow, 1).toString();
        String venue = model.getValueAt(selectedRow, 3).toString();
        String assignment = model.getValueAt(selectedRow, 4).toString();
        String finalExam = model.getValueAt(selectedRow, 5).toString();

        String currentStudentID = StudentSession.getStudentId();

        // Construct string to save (Matching your file format)
        String newRecord = moduleID + ";" + courseName + ";" + assignment + ";" +
                finalExam + ";null;" + venue + ";" + currentStudentID + ";null;null;null;null;null;null";

        boolean success = false;
        try (FileWriter fw = new FileWriter(Resources.ClassStudentList, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.newLine();
            bw.write(newRecord);
            bw.flush();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error writing to file.");
        }

        if (success) {
            JOptionPane.showMessageDialog(this, "Enrollment Successful!");
            loadDataIntoTable(); // Refresh UI
            // Force UI update
            currentClassTable.revalidate();
            currentClassTable.repaint();
            newClassTable.revalidate();
            newClassTable.repaint();
        }
    }

    private void loadDataIntoTable() {
        List<String> enrolledClasses = new ArrayList<>();

        // ---------------------------------------------------
        // 1. SETUP FIRST TABLE (Current Enrolled Classes)
        // ---------------------------------------------------
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Course Name", "Venue", "Assignment", "Final Exam"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        File studentFile = new File(Resources.ClassStudentList);
        if (studentFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(studentFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty() || line.split(";").length < 7) {
                        continue;
                    }

                    String[] allRowData = line.split(";");
                    String studentId = allRowData[6].trim();

                    if (studentId.equals(StudentSession.getStudentId())) {
                        enrolledClasses.add(allRowData[0].trim());
                        String[] rowData = {allRowData[1], allRowData[5], allRowData[2], allRowData[3]};
                        model.addRow(rowData);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        currentClassTable.setModel(model);
        currentClassTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        currentClassTable.getColumnModel().getColumn(0).setPreferredWidth(300);
        currentClassTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        currentClassTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        currentClassTable.getColumnModel().getColumn(3).setPreferredWidth(150);


        // ---------------------------------------------------
        // 2. SETUP SECOND TABLE (New Classes Available)
        // ---------------------------------------------------
        DefaultTableModel model_2 = new DefaultTableModel(
                new String[]{"ID", "Course Name", "Lecturer", "Venue", "Assignment", "Final Exam"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        File moduleFile = new File(Resources.Modules);
        if (moduleFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(moduleFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty() || line.split(";").length < 7) {
                        continue;
                    }

                    String[] allRowData = line.split(";");
                    String moduleId = allRowData[0].trim();

                    // Only add if not already enrolled
                    if (!enrolledClasses.contains(moduleId)) {
                        String[] rowData = {
                                allRowData[0], // ID
                                allRowData[1], // Name
                                allRowData[6], // Lecturer
                                allRowData[5], // Venue
                                allRowData[2], // Assignment
                                allRowData[3]  // Exam
                        };
                        model_2.addRow(rowData);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        newClassTable.setModel(model_2);
        newClassTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        newClassTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        newClassTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        newClassTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        newClassTable.getColumnModel().getColumn(3).setPreferredWidth(60);
        newClassTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        newClassTable.getColumnModel().getColumn(5).setPreferredWidth(150);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new RegisterClassesGUI_Student().setVisible(true));
    }
}




// CHECK RESULT
class CheckResultGUI_Student extends JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CheckResultGUI_Student.class.getName());

    private JTable resultTable;
    private JLabel studentIdLabel;
    private JLabel studentNameLabel;

    public CheckResultGUI_Student() {
        initComponents();
        loadData();
    }

    private void initComponents() {
        // Initialize components
        // Components
        JScrollPane jScrollPane5 = new JScrollPane();
        resultTable = new JTable();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        studentIdLabel = new JLabel();
        studentNameLabel = new JLabel();
        JButton exitBtn = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Configure Table
        resultTable.setModel(new DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane5.setViewportView(resultTable);

        // Configure Labels
        jLabel1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setText("View Assessment Result");

        jLabel2.setText("Student ID:");
        jLabel3.setText("Student Name:");

        // Placeholder text before data loads
        studentIdLabel.setText("...");
        studentNameLabel.setText("...");

        // Configure Exit Button
        exitBtn.setText("Exit");
        exitBtn.addActionListener(this::exitBtnActionPerformed);

        // Layout (GroupLayout copied from NetBeans, cleaned up)
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane5, GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(exitBtn)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(29, 29, 29)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                                                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(studentIdLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(studentNameLabel, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(107, 107, 107)
                                                                .addComponent(jLabel1)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(studentIdLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(studentNameLabel)))
                                        .addComponent(exitBtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane5, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null); // Center the frame on screen
    }

    private void loadData(){
        // Ensure StudentSession is available
        studentIdLabel.setText(StudentSession.getStudentId());
        studentNameLabel.setText(StudentSession.getStudentName());

        DefaultTableModel model = new DefaultTableModel(
                new String[]{
                        "Module", "GPA", "Grade",
                        "Assessment1 Feedback", "Assessment2 Feedback", "Assessment3 Feedback"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        File file = new File(Resources.ClassStudentList);
        if (!file.exists()) {
            System.out.println("File not found at: " + file.getAbsolutePath());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming format: ID;Name;...;StudentId;GPA;Grade;...
                String[] allRowData = line.split(";");

                // Basic index check to prevent crashes if file format is wrong
                if (allRowData.length > 12) {
                    if(allRowData[6].trim().equals(StudentSession.getStudentId())) {
                        String[] rowData = {
                                allRowData[1], allRowData[7], allRowData[8],
                                allRowData[10], allRowData[11], allRowData[12]
                        };
                        model.addRow(rowData);
                    }
                }
            }

            resultTable.setModel(model);
            resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            resultTable.getColumnModel().getColumn(0).setPreferredWidth(250);
            resultTable.getColumnModel().getColumn(1).setPreferredWidth(60);
            resultTable.getColumnModel().getColumn(2).setPreferredWidth(60);
            resultTable.getColumnModel().getColumn(3).setPreferredWidth(500);
            resultTable.getColumnModel().getColumn(4).setPreferredWidth(500);
            resultTable.getColumnModel().getColumn(5).setPreferredWidth(500);

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void exitBtnActionPerformed(ActionEvent evt) {
        // Return to dashboard
        StudentDashboard dashboard = new StudentDashboard();
        dashboard.setVisible(true);
        this.dispose();
        dashboard.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new CheckResultGUI_Student().setVisible(true));
    }
}




// GIVE FEEDBACK
class GiveFeedbackGUI_Student extends JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GiveFeedbackGUI_Student.class.getName());

    // Components
    private JTable classTable;
    private JTextArea feedbackTextArea;

    public GiveFeedbackGUI_Student() {
        initComponents();
        loadData();
    }

    private void initComponents() {
        JLabel jLabel1 = new JLabel();
        JButton exitBtn = new JButton();
        JScrollPane jScrollPane1 = new JScrollPane();
        classTable = new JTable();
        JLabel jLabel2 = new JLabel();
        JScrollPane jScrollPane2 = new JScrollPane();
        feedbackTextArea = new JTextArea();
        JLabel jLabel3 = new JLabel();
        JButton submitBtn = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setText("Give Feedback To the Lecturer");

        exitBtn.setText("Exit");
        exitBtn.addActionListener(this::exitBtnActionPerformed);

        // Setup Table
        classTable.setModel(new DefaultTableModel(
                new Object [][] {},
                new String [] { "Module ID", "Module Name" }
        ));
        jScrollPane1.setViewportView(classTable);

        jLabel2.setText("Select Class:");
        jLabel3.setText("Your Feedback:");

        feedbackTextArea.setColumns(20);
        feedbackTextArea.setRows(5);
        jScrollPane2.setViewportView(feedbackTextArea);

        submitBtn.setText("Submit");
        submitBtn.addActionListener(this::submitBtnActionPerformed);

        // Layout (Standard GroupLayout)
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(117, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(85, 85, 85))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addComponent(jScrollPane2)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(exitBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(submitBtn)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel2))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(exitBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(submitBtn)
                                .addGap(21, 21, 21))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void loadData() {
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Module ID", "Module Name"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try (BufferedReader br = new BufferedReader(new FileReader(Resources.ClassStudentList))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] allRowData = line.split(";");
                if (allRowData.length > 6 && allRowData[6].trim().equals(StudentSession.getStudentId())) {
                    model.addRow(new String[]{allRowData[0].trim(), allRowData[1].trim()});
                }
            }
            classTable.setModel(model);
            classTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            classTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            classTable.getColumnModel().getColumn(1).setPreferredWidth(400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void submitBtnActionPerformed(ActionEvent evt) {
        int selectedRow = classTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a class to give feedback!");
            return;
        }

        String feedbackText = feedbackTextArea.getText().trim();
        if (feedbackText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Comment cannot be empty");
            return;
        }

        String moduleID = classTable.getValueAt(selectedRow, 0).toString().trim();
        List<String> lines = new ArrayList<>();

        // 1. Read all lines into memory
        try (BufferedReader br = new BufferedReader(new FileReader(Resources.ClassStudentList))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. Modify the specific line
        boolean updated = false;
        for (int i = 0; i < lines.size(); i++) {
            String[] columns = lines.get(i).split(";");
            if (columns.length > 6) {
                String moduleId = columns[0].trim();
                String studentId = columns[6].trim();

                if (studentId.equals(StudentSession.getStudentId()) && moduleId.equals(moduleID)) {
                    // Safety check to ensure index 9 exists
                    if (columns.length <= 9) {
                        columns = Arrays.copyOf(columns, 13);
                    }
                    columns[9] = " " + feedbackText.replace(";", ",") + " "; // Sanitize semi-colons
                    lines.set(i, String.join(";", columns));
                    updated = true;
                    break;
                }
            }
        }

        // 3. Write back to file
        if (updated) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(Resources.ClassStudentList))) {
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
                JOptionPane.showMessageDialog(this, "Successfully submitted your feedback");
                feedbackTextArea.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void exitBtnActionPerformed(ActionEvent evt) {
        StudentDashboard studentDashboard = new StudentDashboard();
        studentDashboard.setVisible(true);
        this.dispose();
        studentDashboard.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new GiveFeedbackGUI_Student().setVisible(true));
    }
}