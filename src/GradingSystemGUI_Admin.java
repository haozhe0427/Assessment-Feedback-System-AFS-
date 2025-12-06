import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GradingSystemGUI_Admin extends JFrame {

    JPanel topPanel = new JPanel();
    JButton exitButton = new JButton("Exit");
    JLabel gradingSystemLabel = new JLabel("Grading System", JLabel.CENTER);
    DefaultTableModel tableModel;
    JTable gradeTable;
    JLabel marksLabel = new JLabel("Marks:");
    JTextField marksField = new JTextField();
    JLabel gradeLabel = new JLabel("Grade:");
    JTextField gradeField = new JTextField();
    JLabel gpaLabel = new JLabel("GPA:");
    JTextField gpaField = new JTextField();
    JLabel statusLabel = new JLabel("Status:");
    JTextField statusField = new JTextField();
    JButton updateButton = new JButton("Update");
    JButton deleteButton = new JButton("Delete");

    GradingSystemGUI_Admin () {
        // <========= TOP PANEL =========>
        topPanel.setBackground(new Color(153,255,153));
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,985,150);
        this.add(topPanel);



        // <========= EXIT BUTTON =========>
        exitButton.setBounds(25,15,125,40);
        exitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        exitButton.setFocusable(false);
        exitButton.addActionListener(_ -> {
            dispose();
            new DashboardGUI_Admin();
        });
        topPanel.add(exitButton);



        // <========= GRADING SYSTEM LABEL =========>
        gradingSystemLabel.setBounds(0,75,985,60);
        gradingSystemLabel.setFont(new Font("Impact", Font.PLAIN, 60));
        topPanel.add(gradingSystemLabel);



        // <========= GRADE TABLE =========>
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
            }
        });


        // <========= MARKS LABEL & FIELD =========>
        marksLabel.setBounds(720, 230, 100,16);
        marksLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        this.add(marksLabel);

        marksField.setBounds(790, 227, 150, 26);
        marksField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        this.add(marksField);



        // <========= GRADE LABEL & FIELD =========>
        gradeLabel.setBounds(720, 280, 100,16);
        gradeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        this.add(gradeLabel);

        gradeField.setBounds(790, 277, 150, 26);
        gradeField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        this.add(gradeField);



        // <========= GPA LABEL =========>
        gpaLabel.setBounds(720, 330, 100,16);
        gpaLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        this.add(gpaLabel);

        gpaField.setBounds(790, 327, 150, 26);
        gpaField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        this.add(gpaField);



        // <=========STATUS LABEL =========>
        statusLabel.setBounds(720, 380, 100,16);
        statusLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        this.add(statusLabel);

        statusField.setBounds(790, 377, 150, 26);
        statusField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        this.add(statusField);



        updateButton.setBounds(720, 440, 220, 50);
        updateButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        updateButton.setFocusable(false);
        this.add(updateButton);



        deleteButton.setBounds(720, 511, 220, 50);
        deleteButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        deleteButton.setFocusable(false);
        this.add(deleteButton);



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



    public void displayGrade () {
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
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}