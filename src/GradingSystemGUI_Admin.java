import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class GradingSystemGUI_Admin extends JFrame {

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
    JButton updateButton = new JButton("Update");

    // JTextField
    JTextField marksField = new JTextField();
    JTextField gradeField = new JTextField();
    JTextField gpaField = new JTextField();
    JTextField statusField = new JTextField();

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

        // <========= 2) updateButton =========>
        updateButton.setBounds(720, 511, 220, 50);
        updateButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        updateButton.setFocusable(false);
        updateButton.addActionListener(_ -> {
            String selectedMarks = marksField.getText();
            String selectedGrade = gradeField.getText();
            String selectedGPA = gpaField.getText();
            String selectedStatus = statusField.getText();

            if (selectedMarks.isEmpty() ||
                    selectedGrade.isEmpty() ||
                    selectedGPA.isEmpty() ||
                    selectedStatus.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Please select row to edit",
                        "Warning", JOptionPane.WARNING_MESSAGE);
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
            try (BufferedReader reader = new BufferedReader(new FileReader(PicturesAndTextFile.GradingSystem))) {
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
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Grading System is not found",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Something went wrong. Please contact technician team for support",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }


            try (FileWriter writer = new FileWriter(PicturesAndTextFile.GradingSystem)) {
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
            gradeField.setText("");
            gpaField.setText("");
            statusField.setText("");

            marksField.setEditable(false);
            gradeField.setEditable(false);
            gpaField.setEditable(false);
            statusField.setEditable(false);
        });
        this.add(updateButton);



        // <================== JTextField ==================>
        // <========= 1) marksField =========>
        marksField.setBounds(790, 227, 150, 26);
        marksField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        marksField.setEditable(false);
        this.add(marksField);

        // <========= 2) gradeField =========>
        gradeField.setBounds(790, 277, 150, 26);
        gradeField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        gradeField.setEditable(false);
        this.add(gradeField);

        // <========= 3) gpaField =========>
        gpaField.setBounds(790, 327, 150, 26);
        gpaField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        gpaField.setEditable(false);
        this.add(gpaField);

        // <=========4) statusField =========>
        statusField.setBounds(790, 377, 150, 26);
        statusField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        statusField.setEditable(false);
        this.add(statusField);



        // <================== JTable & DefaultTableModel ==================>
        // <========= 1) gradeTable =========>
        String[] columnNames = {"Marks", "Grade", "GPA", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        gradeTable = new JTable(tableModel);

        gradeTable.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        gradeTable.setRowHeight(30);
        gradeTable.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(gradeTable);
        scrollPane.setBounds(30,180,670,383);
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
                gradeField.setText(grade);
                gpaField.setText(gpa);
                statusField.setText(status);

                marksField.setEditable(true);
                gradeField.setEditable(true);
                gpaField.setEditable(true);
                statusField.setEditable(true);
            }
        });



        // <========= GUI FRAME =========>
        this.setIconImage(PicturesAndTextFile.imageIcon.getImage());
        this.setTitle("Assessment Feedback System (Admin)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(985,630);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }



    public void displayGrade () { // display grade's info
        try (BufferedReader reader = new BufferedReader(new FileReader(PicturesAndTextFile.GradingSystem))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String [] GradeInfo = line.split(" ; ");
                tableModel.addRow(GradeInfo);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Grading System is not found",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Something went wrong. Please contact technician team for support",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}