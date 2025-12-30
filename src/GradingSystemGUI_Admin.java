import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class GradingSystemGUI_Admin extends JFrame {

    // JComboBox
    String[] Status = {"Distinction", "Credit", "Pass", "Fail(Marginal)", "Fail", ""};
    JComboBox<String> status_cb = new JComboBox<>(Status);
    String[] grade = {"A+", "A", "B+", "B", "C+", "C", "C-", "D", "F+", "F", "F-", ""};
    JComboBox<String> grade_cb = new JComboBox<>(grade);

    // JPanel
    JPanel topPanel = new JPanel();

    // JLabel
    JLabel gradingSystemLabel = new JLabel("Grading System", JLabel.CENTER);
    JLabel marksLabel = new JLabel("Marks:");
    JLabel gradeLabel = new JLabel("Grade:");
    JLabel gpaLabel = new JLabel("GPA:");
    JLabel statusLabel = new JLabel("Status:");

    // JButton
    JButton exitButton = new JButton("Exit");
    JButton clearButton = new JButton("Clear");
    JButton updateButton = new JButton("Update");

    // JTextField
    JTextField marksField = new JTextField();
    JTextField gpaField = new JTextField();

    // DefaultTableModel & JTable
    DefaultTableModel tableModel;
    JTable gradeTable;

    GradingSystemGUI_Admin () {
        // <================== JPanel ==================>
        // <========= 1) topPanel =========>
        topPanel.setBackground(new Color(153,255,153));
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,985,150);
        this.add(topPanel);



        // <================== JComboBox ==================>
        // <========= 1) status_cb =========>
        status_cb.setBounds(790, 377, 150, 26);
        status_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        status_cb.setSelectedIndex(5);
        status_cb.setEnabled(false);
        this.add(status_cb);


        // <========= 2) grade_cb =========>
        grade_cb.setBounds(790, 277, 150, 26);
        grade_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        grade_cb.setSelectedIndex(11);
        grade_cb.setEnabled(false);
        this.add(grade_cb);



        // <================== JLabel ==================>
        // <========= 1) gradingSystemLabel =========>
        gradingSystemLabel.setBounds(0,75,985,60);
        gradingSystemLabel.setFont(new Font("Impact", Font.PLAIN, 60));
        topPanel.add(gradingSystemLabel);

        // <========= 2) marksLabel =========>
        marksLabel.setBounds(720, 230, 100,16);
        marksLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        this.add(marksLabel);

        // <========= 3) gradeLabel =========>
        gradeLabel.setBounds(720, 280, 100,16);
        gradeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        this.add(gradeLabel);

        // <========= 4) gpaLabel =========>
        gpaLabel.setBounds(720, 330, 100,16);
        gpaLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        this.add(gpaLabel);

        // <========= 5) statusLabel =========>
        statusLabel.setBounds(720, 380, 100,16);
        statusLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        this.add(statusLabel);



        // <================== JButton ==================>
        // <========= 1) exitButton =========>
        exitButton.setBounds(25,15,125,40);
        exitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        exitButton.setFocusable(false);
        exitButton.addActionListener(_ -> {
            dispose();
            new DashboardGUI_Admin();
        });
        topPanel.add(exitButton);


        // <========= 2) clearButton =========>
        clearButton.setBounds(720, 501, 100, 30);
        clearButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        clearButton.setFocusable(false);
        clearButton.addActionListener(_ -> {
           marksField.setText("");
           grade_cb.setSelectedIndex(11);
           gpaField.setText("");
           status_cb.setSelectedIndex(5);

           marksField.setEditable(false);
           grade_cb.setEnabled(false);
           gpaField.setEditable(false);
           status_cb.setEnabled(false);
        });
        this.add(clearButton);


        // <========= 3) updateButton =========>
        updateButton.setBounds(842, 501, 100, 30);
        updateButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        updateButton.setFocusable(false);
        updateButton.addActionListener(_ -> {
            String selectedMarks = marksField.getText();
            String selectedGrade = (String) grade_cb.getSelectedItem();
            String selectedGPA = gpaField.getText();
            String selectedStatus = (String) status_cb.getSelectedItem();

            if (selectedMarks.isEmpty() ||
                    selectedGrade.isEmpty() ||
                    selectedGPA.isEmpty() ||
                    selectedStatus.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Invalid Grade Information",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double GPA = Double.parseDouble(selectedGPA);
                if (selectedMarks.contains("-")) {
                    String[] separatedMarks = marksField.getText().split("-"); // e.g (Split 80-100 into ["80", "100"])
                    int num_1 = Integer.parseInt(separatedMarks[0]);
                    int num_2 = Integer.parseInt(separatedMarks[1]);

                    if ((num_1 < 0 || num_1 > 100) ||
                            (num_2 < 0 || num_2 > 100) ||
                            (GPA < 0 || GPA > 4) || selectedGPA.length() != 4) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter a valid mark / GPA format (e.g mark: 80-100 / GPA: 4.00)",
                                "Error", JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Please enter a valid mark / GPA format (e.g mark: 80-100 / GPA: 4.00)",
                            "Error", JOptionPane.ERROR_MESSAGE);

                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Invalid format",
                        "Error", JOptionPane.ERROR_MESSAGE);

                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure want to update this grading system ?",
                    "confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            StringBuilder updatedGrades = new StringBuilder();
            int selectedRow = gradeTable.getSelectedRow();
            int currentRow = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.GradingSystem))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] GradeInfo = line.split(" ; ");

                    if (currentRow == selectedRow) {
                        if (GradeInfo[0].equals(selectedMarks) &&
                                GradeInfo[1].equals(selectedGrade) &&
                                GradeInfo[2].equals(selectedGPA) &&
                                GradeInfo[3].equals(selectedStatus)) {
                            JOptionPane.showMessageDialog(null,
                                    "Update unsuccessful",
                                    "Warning", JOptionPane.WARNING_MESSAGE);
                            return;
                        } else {
                            GradeInfo[0] = selectedMarks;
                            GradeInfo[1] = selectedGrade;
                            GradeInfo[2] = selectedGPA;
                            GradeInfo[3] = selectedStatus;
                        }
                    }

                    updatedGrades.append(String.join(" ; ", GradeInfo)).append("\n");
                    currentRow++;
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Something went wrong. Please contact technician team for support",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }


            try (FileWriter writer = new FileWriter(Resources.GradingSystem)) {
                writer.write(updatedGrades.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error writing to file",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(null,
                    "Update successfully",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            tableModel.setRowCount(0);
            displayGrade();

            marksField.setText("");
            grade_cb.setSelectedIndex(11);
            gpaField.setText("");
            status_cb.setSelectedIndex(5);

            marksField.setEditable(false);
            grade_cb.setEnabled(false);
            gpaField.setEditable(false);
            status_cb.setEnabled(false);
        });
        this.add(updateButton);



        // <================== JTextField ==================>
        // <========= 1) marksField =========>
        marksField.setBounds(790, 227, 150, 26);
        marksField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        marksField.setEditable(false);
        this.add(marksField);


        // <========= 2) gpaField =========>
        gpaField.setBounds(790, 327, 150, 26);
        gpaField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        gpaField.setEditable(false);
        this.add(gpaField);





        // <================== JTable & DefaultTableModel ==================>
        // <========= 1) gradeTable =========>
        String[] columnNames = {"Marks", "Grade", "GPA", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        gradeTable = new JTable(tableModel);

        gradeTable.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        gradeTable.setRowHeight(30);
        gradeTable.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(gradeTable);
        scrollPane.setBounds(30,180,670,353);
        displayGrade();
        this.add(scrollPane);

        gradeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = gradeTable.getSelectedRow();

                String marks = tableModel.getValueAt(selectedRow, 0).toString();
                String grade = tableModel.getValueAt(selectedRow, 1).toString();
                String gpa = tableModel.getValueAt(selectedRow, 2).toString();
                String status = tableModel.getValueAt(selectedRow, 3).toString();

                marksField.setText(marks);
                grade_cb.setSelectedItem(grade);
                gpaField.setText(gpa);
                status_cb.setSelectedItem(status);

                marksField.setEditable(true);
                grade_cb.setEnabled(true);
                gpaField.setEditable(true);
                status_cb.setEnabled(true);
            }
        });



        // <========= GUI FRAME =========>
        this.setIconImage(Resources.imageIcon.getImage());
        this.setTitle("Grading System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(985,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }



    public void displayGrade () { // display grade's info
        try (BufferedReader reader = new BufferedReader(new FileReader(Resources.GradingSystem))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String [] GradeInfo = line.split(" ; ");
                tableModel.addRow(GradeInfo);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Something went wrong. Please contact technician team for support",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}