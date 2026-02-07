import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerClasses extends JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(registerClasses.class.getName());

    // Components
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane3;
    private JTable currentClassTable;
    private JTable newClassTable;
    private JButton exitBtn;
    private JButton enrollBtn;

    public registerClasses() {
        initComponents();
        loadDataIntoTable();
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        exitBtn = new JButton();
        jLabel2 = new JLabel();
        jScrollPane1 = new JScrollPane();
        currentClassTable = new JTable();
        jLabel3 = new JLabel();
        jScrollPane3 = new JScrollPane();
        newClassTable = new JTable();
        enrollBtn = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setText("Class Registration");

        exitBtn.setText("Exit");
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel2.setText("Classes you have enrolled");

        // Setup Current Class Table
        currentClassTable.setModel(new DefaultTableModel(
                new Object [][] {},
                new String [] { "Title 1", "Title 2", "Title 3", "Title 4" }
        ));
        currentClassTable.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(currentClassTable);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel3.setText("New classes");

        // Setup New Class Table
        newClassTable.setModel(new DefaultTableModel(
                new Object [][] {},
                new String [] { "Title 1", "Title 2", "Title 3", "Title 4" }
        ));
        jScrollPane3.setViewportView(newClassTable);

        enrollBtn.setText("Enroll");
        enrollBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                enrollBtnActionPerformed(evt);
            }
        });

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

    public static void main(String args[]) {
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

        java.awt.EventQueue.invokeLater(() -> new registerClasses().setVisible(true));
    }
}
