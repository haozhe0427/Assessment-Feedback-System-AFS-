import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentCheckResult extends JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(StudentCheckResult.class.getName());

    // Components
    private JScrollPane jScrollPane5;
    private JTable resultTable;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel studentIdLabel;
    private JLabel studentNameLabel;
    private JButton exitBtn;

    public StudentCheckResult() {
        initComponents();
        loadData();
    }

    private void initComponents() {
        // Initialize components
        jScrollPane5 = new JScrollPane();
        resultTable = new JTable();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        studentIdLabel = new JLabel();
        studentNameLabel = new JLabel();
        exitBtn = new JButton();

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
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setText("View Assessment Result");

        jLabel2.setText("Student ID:");
        jLabel3.setText("Student Name:");

        // Placeholder text before data loads
        studentIdLabel.setText("...");
        studentNameLabel.setText("...");

        // Configure Exit Button
        exitBtn.setText("Exit");
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

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

        java.awt.EventQueue.invokeLater(() -> new StudentCheckResult().setVisible(true));
    }
}
