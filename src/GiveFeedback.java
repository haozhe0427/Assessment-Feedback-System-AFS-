import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GiveFeedback extends JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GiveFeedback.class.getName());

    // Components
    private JTable classTable;
    private JTextArea feedbackTextArea;
    private JButton exitBtn;
    private JButton submitBtn;
    private JLabel jLabel1, jLabel2, jLabel3;
    private JScrollPane jScrollPane1, jScrollPane2;

    public GiveFeedback() {
        initComponents();
        loadData();
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        exitBtn = new JButton();
        jScrollPane1 = new JScrollPane();
        classTable = new JTable();
        jLabel2 = new JLabel();
        jScrollPane2 = new JScrollPane();
        feedbackTextArea = new JTextArea();
        jLabel3 = new JLabel();
        submitBtn = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setText("Give Feedback To the Lecturer");

        exitBtn.setText("Exit");
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

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
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

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
        new StudentDashboard().setVisible(true);
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
        java.awt.EventQueue.invokeLater(() -> new GiveFeedback().setVisible(true));
    }
}