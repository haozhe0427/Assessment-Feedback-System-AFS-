import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Objects;

// ADMIN DASHBOARD
public class AdminDashboard extends JFrame {
    JButton logOutButton = new JButton("Log Out");
    JButton manageAccountButton = new JButton("Manage Account");
    JButton assignLecturersButton = new JButton("Assign Lecturer");
    JButton gradingSystemButton = new JButton("Grading System");
    JButton manageClassesButton = new JButton("Manage Classes");
    JLabel AFSLabel = new JLabel("Assessment Feedback System", JLabel.CENTER);
    JLabel welcomeLabel = new JLabel();
    JPanel topPanel = new JPanel();

    public AdminDashboard() {
        // <================== JButton ==================>
        // <========= 1) logOutButton =========>
        logOutButton.setBounds(25,15,125,40);
        logOutButton.setFont(new Font("Comic Sans MS", Font.BOLD,20));
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(_ -> {
            dispose();
            new LoginGUI();
        });
        topPanel.add(logOutButton);


        // <========= 2) manageAccountButton =========>
        Resources.manageAccountIcon = new ImageIcon(Resources.manageAccountIcon.getImage().
                getScaledInstance(40,40, Image.SCALE_SMOOTH));

        manageAccountButton.setBounds(130,240,250,100);
        manageAccountButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        manageAccountButton.setHorizontalTextPosition(JButton.CENTER);
        manageAccountButton.setVerticalTextPosition(JButton.NORTH);
        manageAccountButton.setFocusable(false);
        manageAccountButton.setIcon(Resources.manageAccountIcon);
        manageAccountButton.addActionListener(_ -> {
            dispose();
            new ManageAccountGUI_Admin();
        });
        this.add(manageAccountButton);


        // <========= 3) assignLecturersButton =========>
        Resources.lecturerIcon = new ImageIcon(Resources.lecturerIcon.getImage().
                getScaledInstance(40,40, Image.SCALE_SMOOTH));

        assignLecturersButton.setBounds(420,240,250,100);
        assignLecturersButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        assignLecturersButton.setHorizontalTextPosition(JButton.CENTER);
        assignLecturersButton.setVerticalTextPosition(JButton.NORTH);
        assignLecturersButton.setFocusable(false);
        assignLecturersButton.setIcon(Resources.lecturerIcon);
        assignLecturersButton.addActionListener(_ -> {
            dispose();
            new AssignLecturerGUI_Admin();
        });
        this.add(assignLecturersButton);


        // <========= 4) gradingSystemButton =========>
        Resources.gradingSystemIcon = new ImageIcon(Resources.gradingSystemIcon.getImage().
                getScaledInstance(40,40, Image.SCALE_SMOOTH));

        gradingSystemButton.setBounds(130,390,250,100);
        gradingSystemButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        gradingSystemButton.setHorizontalTextPosition(JButton.CENTER);
        gradingSystemButton.setVerticalTextPosition(JButton.NORTH);
        gradingSystemButton.setFocusable(false);
        gradingSystemButton.setIcon(Resources.gradingSystemIcon);
        gradingSystemButton.addActionListener(_ -> {
            dispose();
            new GradingSystemGUI_Admin();
        });
        this.add(gradingSystemButton);


        // <========= 5) manageClassesButton =========>
        Resources.classesIcon = new ImageIcon(Resources.classesIcon.getImage().
                getScaledInstance(40, 40, Image.SCALE_SMOOTH));

        manageClassesButton.setBounds(420,390,250,100);
        manageClassesButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        manageClassesButton.setHorizontalTextPosition(JButton.CENTER);
        manageClassesButton.setVerticalTextPosition(JButton.NORTH);
        manageClassesButton.setFocusable(false);
        manageClassesButton.setIcon(Resources.classesIcon);
        manageClassesButton.addActionListener(_ -> {
            dispose();
            new ManageClassesGUI_Admin();
        });
        this.add(manageClassesButton);



        // <================== JLabel ==================>
        // <========= 1) AFSLabel =========>
        AFSLabel.setBounds(0,75,820,50);
        AFSLabel.setFont(new Font("Impact", Font.PLAIN, 50));
        topPanel.add(AFSLabel);


        // <========= 2) welcomeLabel =========>
        welcomeLabel.setText("Welcome back, " + Name.getName());
        welcomeLabel.setBounds(0,140,820,35);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        topPanel.add(welcomeLabel);



        // <================== JPanel ==================>
        // <========= 1) topPanel =========>
        topPanel.setBackground(new Color(153,255,153));
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,820,190);
        this.add(topPanel);



        // <========= GUI FRAME =========>
        this.setIconImage(Resources.imageIcon.getImage());
        this.setTitle("Assessment Feedback System (Admin)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(255,250,250));
        this.setLayout(null);
        this.setSize(820,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}




// ASSIGN LECTURER
class AssignLecturerGUI_Admin extends JFrame {
    DefaultTableModel tableModel;
    JButton exitButton = new JButton("Exit");
    JButton assignButton = new JButton("Assign");
    JButton deleteButton = new JButton("Delete");
    String[] academicLeaders = new String[15];
    JComboBox<String> academicLeaders_cb = new JComboBox<>(academicLeaders);
    JLabel assignLecturerLabel = new JLabel("Assign Lecturer", JLabel.CENTER);
    JLabel lecturerLabel = new JLabel("Lecturer:");
    JLabel assignToLabel = new JLabel("Assign to:");
    JPanel topPanel = new JPanel();
    JTable lecturersTable;
    JTextField lecturerField = new JTextField();

    AssignLecturerGUI_Admin () {
        // <================== JButton ==================>
        // <========= 1) exitButton =========>
        exitButton.setBounds(25, 15, 125, 40);
        exitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        exitButton.setFocusable(false);
        exitButton.addActionListener(_ -> {
            dispose();
            new AdminDashboard();
        });
        topPanel.add(exitButton);


        // <========= 2) assignButton =========>
        assignButton.setBounds(910, 370, 250, 70);
        assignButton.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        assignButton.setFocusable(false);
        assignButton.addActionListener(_ -> {
            String selectedLecturer = lecturerField.getText();
            String selectedAcademicLeader = (String) academicLeaders_cb.getSelectedItem();
            StringBuilder updatedLecturers = new StringBuilder();

            if (selectedLecturer.isEmpty() || selectedAcademicLeader == null) {
                JOptionPane.showMessageDialog(null,
                        "Please select lecturer and academic leader",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Account))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] lecturerInfo = line.split(" ; ");
                    String lecturerName = lecturerInfo[3];
                    String assignStatus = lecturerInfo[7];

                    if (lecturerName.equals(selectedLecturer)) {
                        if (!assignStatus.equals(selectedAcademicLeader)) {
                            lecturerInfo[7] = selectedAcademicLeader;

                            JOptionPane.showMessageDialog(null,
                                    "Lecturer successfully assigned / updated to this academic leader",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Lecturer has already assigned to this academic leader",
                                    "Warning", JOptionPane.WARNING_MESSAGE);

                            return;
                        }
                    }
                    updatedLecturers.append(String.join(" ; ", lecturerInfo)).append("\n");
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Lecturer Account list is not found",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Something went wrong. Please contact technician team for support",
                        "Error", JOptionPane.WARNING_MESSAGE);
            }

            try (FileWriter writer = new FileWriter(Resources.Account)) {
                writer.write(updatedLecturers.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error writing to file",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            tableModel.setRowCount(0);
            displayLecturers();
        });
        this.add(assignButton);


        // <========= 3) deleteButton =========>
        deleteButton.setBounds(910, 470, 250, 70);
        deleteButton.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(_ -> {
            String selectedLecturer = lecturerField.getText();
            String selectedAcademicLeader = (String) academicLeaders_cb.getSelectedItem();
            StringBuilder updatedLecturers = new StringBuilder();

            if (selectedLecturer.isEmpty() || selectedAcademicLeader == null) {
                JOptionPane.showMessageDialog(null,
                        "Please select lecturer and academic leader",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Account))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] lecturerInfo = line.split(" ; ");
                    String lecturerName = lecturerInfo[3];
                    String assignStatus = lecturerInfo[7];

                    if (lecturerName.equals(selectedLecturer)) {
                        if (!assignStatus.equals("NULL")) {
                            lecturerInfo[7] = "NULL";

                            JOptionPane.showMessageDialog(null,
                                    "Academic leader successfully removed",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Lecturer haven't assigned to any academic leaders yet",
                                    "Warning", JOptionPane.WARNING_MESSAGE);

                            return;
                        }
                    }
                    updatedLecturers.append(String.join(" ; ", lecturerInfo)).append("\n");
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Lecturer Account list is not found",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Something went wrong. Please contact technician team for support",
                        "Error", JOptionPane.WARNING_MESSAGE);
            }

            try (FileWriter writer = new FileWriter(Resources.Account)) {
                writer.write(updatedLecturers.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error writing to file",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            tableModel.setRowCount(0);
            displayLecturers();
        });
        this.add(deleteButton);


        // <================== JComboBox ==================>
        // <========= 1) academicLeaders_cb =========>
        academicLeaders_cb.setBounds(910, 205, 250, 30);
        academicLeaders_cb.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        displayAcademicLeadersName();
        this.add(academicLeaders_cb);



        // <================== TextField ==================>
        // <========= 1) assignLecturerLabel =========>
        assignLecturerLabel.setBounds(0, 75, 1200, 60);
        assignLecturerLabel.setFont(new Font("Impact", Font.PLAIN, 60));
        topPanel.add(assignLecturerLabel);


        // <========= 2) lecturerLabel =========>
        lecturerLabel.setBounds(30, 170, 100, 30);
        lecturerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        this.add(lecturerLabel);


        // <========= 3) assignToLabel =========>
        assignToLabel.setBounds(910, 170, 100, 30);
        assignToLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        this.add(assignToLabel);



        // <================== JPanel ==================>
        // <========= 1) topPanel =========>
        topPanel.setBackground(new Color(153, 255, 153));
        topPanel.setLayout(null);
        topPanel.setBounds(0, 0, 1200, 150);
        this.add(topPanel);



        // <================== JTable & DefaultTableModel ==================>
        // <========= 1) lecturerTable & tableModel =========>
        String[] columnNames = {"Lecturer ID", "Name", "Areas", "Assigned to"};
        tableModel = new DefaultTableModel(columnNames, 0);
        lecturersTable = new JTable(tableModel);

        lecturersTable.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        lecturersTable.setRowHeight(20);
        lecturersTable.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(lecturersTable);
        scrollPane.setBounds(30, 270, 850, 270);
        displayLecturers();
        this.add(scrollPane);

        lecturersTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = lecturersTable.getSelectedRow();
                String name = tableModel.getValueAt(selectedRow, 1).toString();
                lecturerField.setText(name);

                String academicLeader = tableModel.getValueAt(selectedRow, 3).toString();
                if (!academicLeader.equals("NULL")) {
                    academicLeaders_cb.setSelectedItem(academicLeader);
                }
            }
        });



        // <================== JTextField ==================>
        // <========= 1) lecturerField =========>
        lecturerField.setBounds(30, 205, 400, 30);
        lecturerField.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(lecturerField);



        // <========= GUI FRAME =========>
        this.setIconImage(Resources.imageIcon.getImage());
        this.setTitle("Assign Lecturer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1200, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }



    public void displayLecturers () { // display every lecturer's info
        try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Account))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] accountInfo = line.split(" ; ");
                String userID = accountInfo[0];
                String userRole = accountInfo[5];

                if (userID.startsWith("AFS") && userRole.equals("Lecturer")) {
                    String[] lecturerInfo = {accountInfo[0], accountInfo[3], accountInfo[6], accountInfo[7]};
                    tableModel.addRow(lecturerInfo);
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Lecturer Account list is not found",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Something went wrong. Please contact technician team for support",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }



    public void displayAcademicLeadersName () { // display every academic leader's name ONLY
        try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Account))) {
            String line;
            int i = 0;

            while ((line = reader.readLine()) != null) {
                String[] AccountInfo = line.split(" ; ");
                String userRole = AccountInfo[5];

                if (userRole.equals("Academic Leaders")) {
                    academicLeaders[i] = AccountInfo[3];
                    i++;
                }
            }
            academicLeaders_cb.setModel(new DefaultComboBoxModel<>(academicLeaders));

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Lecturer Account list is not found",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Something went wrong. Please contact technician team for support",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}




// GRADING SYSTEM
class GradingSystemGUI_Admin extends JFrame {

    DefaultTableModel tableModel;
    JButton exitButton = new JButton("Exit");
    JButton clearButton = new JButton("Clear");
    JButton updateButton = new JButton("Update");
    JComboBox<String> status_cb;
    JComboBox<String> grade_cb;
    JLabel gradingSystemLabel = new JLabel("Grading System", JLabel.CENTER);
    JLabel marksLabel = new JLabel("Marks:");
    JLabel gradeLabel = new JLabel("Grade:");
    JLabel gpaLabel = new JLabel("GPA:");
    JLabel statusLabel = new JLabel("Status:");
    JPanel topPanel = new JPanel();
    JTable gradeTable;
    JTextField marksField = new JTextField();
    JTextField gpaField = new JTextField();

    GradingSystemGUI_Admin () {
        // <================== JButton ==================>
        // <========= 1) exitButton =========>
        exitButton.setBounds(25,15,125,40);
        exitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        exitButton.setFocusable(false);
        exitButton.addActionListener(_ -> {
            dispose();
            new AdminDashboard();
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
                    selectedGrade == null ||
                    selectedGPA.isEmpty() ||
                    selectedStatus == null) {
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
                        "Invalid format. Please try again",
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



        // <================== JComboBox ==================>
        // <========= 1) status_cb =========>
        String[] Status = {"Distinction", "Credit", "Pass", "Fail(Marginal)", "Fail", ""};
        status_cb = new JComboBox<>(Status);
        status_cb.setBounds(790, 377, 150, 26);
        status_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        status_cb.setSelectedIndex(5);
        status_cb.setEnabled(false);
        this.add(status_cb);


        // <========= 2) grade_cb =========>
        String[] grade = {"A+", "A", "B+", "B", "C+", "C", "C-", "D", "F+", "F", "F-", ""};
        grade_cb = new JComboBox<>(grade);
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



        // <================== JPanel ==================>
        // <========= 1) topPanel =========>
        topPanel.setBackground(new Color(153,255,153));
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,985,150);
        this.add(topPanel);



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




// MANAGE ACCOUNT
class ManageAccountGUI_Admin extends JFrame {

    DefaultTableModel tableModel;
    JButton exitButton = new JButton("Exit");
    JButton clearButton1 = new JButton("Clear");
    JButton searchButton = new JButton("Search");
    JButton updateButton = new JButton("Update");
    JButton deleteButton = new JButton("Delete");
    JButton createButton = new JButton("Create");
    JButton createAccountButton = new JButton("Create Account");
    JButton clearButton2 = new JButton("Clear");
    JComboBox<String> selectUserRole_cb;
    JComboBox<String> UserRole_cb;
    JComboBox<String> selectAreas_cb;
    JComboBox<String> selectCourse_cb;
    JLabel manageAccountLabel = new JLabel("Manage Account", JLabel.CENTER);
    JLabel id_OR_NameLabel = new JLabel("Enter UserID / Name:");
    JLabel selectUserRoleLabel = new JLabel("Select user role:");
    JLabel accountInfoLabel = new JLabel("Account info:");
    JLabel userIDLabel = new JLabel("UserID:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel nameLabel = new JLabel("Name:");
    JLabel genderLabel = new JLabel("Gender:");
    JLabel userRoleLabel = new JLabel("User Role:");
    JLabel areasLabel = new JLabel("Areas:");
    JLabel otherAccountInfoLabel = new JLabel("Other account info:");
    JLabel emailLabel = new JLabel("Email:");
    JLabel courseLabel = new JLabel("Course (student only):");
    JPanel topPanel = new JPanel();
    JRadioButton male_rb = new JRadioButton("M");
    JRadioButton female_rb = new JRadioButton("F");
    JTable accountTable;
    JTextField id_OR_NameField = new JTextField();
    JTextField userIDField = new JTextField();
    JTextField passwordField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField emailField = new JTextField();

    ManageAccountGUI_Admin () {
        // <================== JButton ==================>
        // <========= 1) exitButton =========>
        exitButton.setBounds(25,15,125,40);
        exitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        exitButton.setFocusable(false);
        exitButton.addActionListener(_ -> {
            dispose();
            new AdminDashboard();
        });
        topPanel.add(exitButton);


        // <========= 2) clearButton1 =========>
        clearButton1.setBounds(30, 290, 100, 40);
        clearButton1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        clearButton1.setFocusable(false);
        clearButton1.addActionListener(_ -> {
            id_OR_NameField.setText("");
            tableModel.setRowCount(0);
            displayAllAccount();
        });
        this.add(clearButton1);


        // <========= 3) searchButton =========>
        searchButton.setBounds(510, 290, 100, 40);
        searchButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        searchButton.setFocusable(false);
        searchButton.addActionListener(_ -> {
            String selectedID_Name = id_OR_NameField.getText();
            String selectedRole = (String) selectUserRole_cb.getSelectedItem();
            tableModel.setRowCount(0);

            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Account))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] accountInfo = line.split(" ; ");
                    String userID = accountInfo[0];
                    String password = accountInfo[1];
                    String name = accountInfo[3];
                    String gender = accountInfo[4];
                    String userRole = accountInfo[5];
                    String areas = accountInfo[6];

                    if (selectedID_Name.isEmpty()) {
                        if (Objects.equals(selectedRole, userRole)) {
                            tableModel.addRow(new Object[]{
                                    userID, password, name, gender, userRole, areas
                            });
                        }
                    } else {
                        if (Objects.equals(selectedRole, userRole) &&
                                (((selectedID_Name.equals(userID))||(selectedID_Name.equals(name))))) {
                            tableModel.addRow(new Object[]{
                                    userID, password, name, gender, userRole, areas
                            });
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Something went wrong. Please contact technician team for support",
                        "Error", JOptionPane.WARNING_MESSAGE);
            }

        });
        this.add(searchButton);


        // <========= 4) updateButton =========>
        updateButton.setBounds(800, 709, 100, 40);
        updateButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        updateButton.setFocusable(false);
        updateButton.addActionListener(_ -> {
            String selectedUserID = userIDField.getText();
            String selectedUserRole = (String) UserRole_cb.getSelectedItem();
            String selectedAreas = (String) selectAreas_cb.getSelectedItem();
            String selectedCourses = (String) selectCourse_cb.getSelectedItem();
            StringBuilder updatedAccount = new StringBuilder();

            if (selectedUserID.isEmpty() || selectedUserRole == null || selectedAreas == null) {
                JOptionPane.showMessageDialog(null,
                        "Please select any account to update",
                        "Error", JOptionPane.ERROR_MESSAGE);

                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Account))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userInfo = line.split(" ; ");
                    String userID = userInfo[0];
                    String userRole = userInfo[5];
                    String userArea = userInfo[6];
                    String studentCourse = userInfo[7];

                    if (userID.equals(selectedUserID) && userRole.equals("Student")) {
                        if (!userRole.equals(selectedUserRole) ||
                                !userArea.equals(selectedAreas) ||
                                !studentCourse.equals(selectedCourses)) {
                            userInfo[5] = selectedUserRole;
                            userInfo[6] = selectedAreas;
                            userInfo[7] = selectedCourses;

                            JOptionPane.showMessageDialog(null,
                                    "Account successfully updated",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Account update unsuccessful",
                                    "Warning", JOptionPane.WARNING_MESSAGE);

                            return;
                        }
                    }
                    if (userID.equals(selectedUserID) &&
                            ((userRole.equals("Academic Leaders")) || userRole.equals("Lecturer"))) {
                        if (!userRole.equals(selectedUserRole) ||
                                !userArea.equals(selectedAreas)) {
                            userInfo[5] = selectedUserRole;
                            userInfo[6] = selectedAreas;

                            JOptionPane.showMessageDialog(null,
                                    "Account successfully updated",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Account update unsuccessful",
                                    "Warning", JOptionPane.WARNING_MESSAGE);

                            return;
                        }
                    }
                    updatedAccount.append(String.join(" ; ", userInfo)).append("\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Something went wrong. Please contact technician team for support",
                        "Error", JOptionPane.WARNING_MESSAGE);
            }

            try (FileWriter writer = new FileWriter(Resources.Account)) {
                writer.write(updatedAccount.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error writing to file",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            createButton.setEnabled(true);
            tableModel.setRowCount(0);
            displayAllAccount();
        });
        this.add(updateButton);


        // <========= 5) deleteButton =========>
        deleteButton.setBounds(925, 709, 100, 40);
        deleteButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(_ -> {
            String selectedUserID = userIDField.getText();
            StringBuilder remainingAccounts = new StringBuilder();
            StringBuilder deletedAccounts = new StringBuilder();

            if (selectedUserID.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Please select an account to delete",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete this account?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;

            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Account))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userInfo = line.split(" ; ");
                    String userID = userInfo[0];

                    if (!userID.equals(selectedUserID)) {
                        remainingAccounts.append(line).append("\n");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Account deleted successfully",
                                "Success", JOptionPane.INFORMATION_MESSAGE);

                        deletedAccounts.append(String.join(" ; ", userInfo)).append("\n");
                    }
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error reading account file",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (FileWriter writer = new FileWriter(Resources.Account)) {
                writer.write(remainingAccounts.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error writing account file",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (FileWriter writer = new FileWriter(Resources.DeletedAccounts)) {
                writer.write(deletedAccounts.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error writing account file",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            id_OR_NameField.setEditable(true);
            selectUserRole_cb.setEnabled(true);
            userIDField.setText("");
            passwordField.setText("");
            nameField.setText("");
            male_rb.setSelected(false);
            female_rb.setSelected(false);
            UserRole_cb.setSelectedIndex(3);
            UserRole_cb.setEnabled(false);
            selectAreas_cb.setSelectedIndex(4);
            selectAreas_cb.setEnabled(false);
            emailField.setText("");
            selectCourse_cb.setSelectedIndex(6);
            selectCourse_cb.setEnabled(false);
            createButton.setEnabled(true);
            tableModel.setRowCount(0);
            displayAllAccount();
        });

        this.add(deleteButton);


        // <========= 6) createButton =========>
        createButton.setBounds(1050, 709, 100, 40);
        createButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        createButton.setFocusable(false);
        createButton.addActionListener(_ -> {
            String userID = userIDField.getText();
            String password = passwordField.getText();
            String name = nameField.getText();
            String gender = male_rb.isSelected() ? "M" : "F";
            String userRole = (String) UserRole_cb.getSelectedItem();
            String area = (String) selectAreas_cb.getSelectedItem();
            String email = emailField.getText();
            String course = (String) selectCourse_cb.getSelectedItem();
            StringBuilder existedAccounts = new StringBuilder();
            StringBuilder updatedAccount = new StringBuilder();
            String newAccount = "";

            switch (userRole) {
                case "Academic Leaders" , "Lecturer" -> {
                    if (userID.isEmpty() || !(male_rb.isSelected() || female_rb.isSelected()) || area == null) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter every information",
                                "Error", JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                }
                case "Student" -> {
                    if (userID.isEmpty() || !(male_rb.isSelected() || female_rb.isSelected()) || area == null || course == null) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter every information",
                                "Error", JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                }
                case null, default -> {}
            }

            if (userID.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Please click the Create Account button",
                        "Error", JOptionPane.ERROR_MESSAGE);

                return;
            }

            if (name.matches(".*\\d.*")) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please enter user name without numbers",
                        "Error", JOptionPane.ERROR_MESSAGE);

                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Account))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userInfo = line.split(" ; ");
                    String storedName = userInfo[3];
                    String storedUserRole = userInfo[5];


                    switch (storedUserRole) {
                        case "Academic Leaders" , "Lecturer" -> {
                            existedAccounts.append(line).append("\n");
                            if (name.equals(storedName)) {
                                JOptionPane.showMessageDialog(null,
                                        "The user's name has existed",
                                        "Warning", JOptionPane.WARNING_MESSAGE);

                                return;
                            } else {
                                if (Objects.equals(userRole, "Lecturer") || Objects.equals(userRole, "Academic Leaders")) {
                                    newAccount = userID + " ; " +
                                            password + " ; " +
                                            email + " ; " +
                                            name + " ; " +
                                            gender + " ; " +
                                            userRole + " ; " +
                                            area + " ; NULL";
                                }
                            }
                        }
                        case "Student" -> {
                            existedAccounts.append(line).append("\n");
                            if (name.equals(storedName)) {
                                JOptionPane.showMessageDialog(null,
                                        "The user's name has existed",
                                        "Warning", JOptionPane.WARNING_MESSAGE);

                                return;
                            } else {
                                if (Objects.equals(userRole, "Student")) {
                                    newAccount = userID + " ; " +
                                            password + " ; " +
                                            email + " ; " +
                                            name + " ; " +
                                            gender + " ; " +
                                            userRole + " ; " +
                                            area + " ; " +
                                            course;
                                }
                            }
                        }
                    }
                }
                JOptionPane.showMessageDialog(null,
                        "Account successfully added",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                updatedAccount.append(newAccount).append("\n");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error reading account file",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (FileWriter writer = new FileWriter(Resources.Account)) {
                writer.write(existedAccounts.toString());
                writer.write(updatedAccount.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error writing account file",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            userIDField.setText("");
            passwordField.setText("");
            nameField.setText("");
            nameField.setEditable(false);
            male_rb.setSelected(false);
            male_rb.setEnabled(false);
            female_rb.setSelected(false);
            female_rb.setEnabled(false);
            UserRole_cb.setSelectedIndex(3);
            UserRole_cb.setEnabled(false);
            selectAreas_cb.setSelectedIndex(4);
            selectAreas_cb.setEnabled(false);
            emailField.setText("");
            selectCourse_cb.setSelectedIndex(6);
            selectCourse_cb.setEnabled(false);
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);
            tableModel.setRowCount(0);
            displayAllAccount();
        });
        this.add(createButton);


        // <========= 7) createAccountButton =========>
        createAccountButton.setBounds(627, 290, 150, 40);
        createAccountButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        createAccountButton.setFocusable(false);
        createAccountButton.addActionListener(_ -> {
            tableModel.setRowCount(0);

            id_OR_NameField.setEditable(false);
            selectUserRole_cb.setEnabled(false);

            nameField.setText("");
            nameField.setEditable(true);

            male_rb.setSelected(false);
            male_rb.setEnabled(true);

            female_rb.setSelected(false);
            female_rb.setEnabled(true);

            UserRole_cb.setSelectedIndex(0);
            UserRole_cb.setEnabled(true);

            selectAreas_cb.setSelectedIndex(0);
            selectAreas_cb.setEnabled(true);

            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
            createButton.setEnabled(true);

            String id = "AFS" + idNumberGenerator("AFS");
            userIDField.setText(id);
            passwordField.setText(id + "@password");
            emailField.setText(id + "@gmail.apu.edu.my");
        });
        this.add(createAccountButton);


        // <========= 8) clearButton2 =========>
        clearButton2.setBounds(800, 655, 350, 40);
        clearButton2.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        clearButton2.setFocusable(false);
        clearButton2.addActionListener(_ -> {
            id_OR_NameField.setEditable(true);
            selectUserRole_cb.setEnabled(true);

            userIDField.setText("");
            userIDField.setEditable(false);

            passwordField.setText("");
            passwordField.setEditable(false);

            nameField.setText("");
            nameField.setEditable(false);

            male_rb.setSelected(false);
            male_rb.setEnabled(false);

            female_rb.setSelected(false);
            female_rb.setEnabled(false);

            UserRole_cb.setSelectedIndex(3);
            UserRole_cb.setEnabled(false);

            selectAreas_cb.setSelectedIndex(4);
            selectAreas_cb.setEnabled(false);

            emailField.setText("");
            emailField.setEditable(false);

            selectCourse_cb.setSelectedIndex(6);
            selectCourse_cb.setEnabled(false);

            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);
            createButton.setEnabled(true);

            tableModel.setRowCount(0);
            displayAllAccount();
        });
        this.add(clearButton2);



        // <================== JComboBox ==================>
        // <========= 1) selectUserRole_cb =========>
        String[] selectUserRole = {"Academic Leaders", "Lecturer", "Student"};
        selectUserRole_cb = new JComboBox<>(selectUserRole);
        selectUserRole_cb.setBounds(210, 220, 250, 30);
        selectUserRole_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(selectUserRole_cb);


        // <========= 2) UserRole_cb =========>
        String[] UserRole = {"Academic Leaders", "Lecturer", "Student", ""};
        UserRole_cb = new JComboBox<>(UserRole);
        UserRole_cb.setBounds(900, 360, 250, 30);
        UserRole_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        UserRole_cb.setEnabled(false);
        UserRole_cb.setSelectedIndex(3);
        UserRole_cb.addActionListener(_ -> {
            String userRole = (String) UserRole_cb.getSelectedItem();

            switch (userRole) {
                case "Academic Leaders", "Lecturer" -> {
                    selectCourse_cb.setSelectedIndex(6);
                    selectCourse_cb.setEnabled(false);
                }
                case "Student" -> selectCourse_cb.setEnabled(true);
                case null, default -> {}
            }
        });
        this.add(UserRole_cb);


        // <========= 3) selectAreas_cb =========>
        String[] selectAreas = {"School of Computing", "School of Technology", "School of Game Development", "School of Digital Marketing", ""};
        selectAreas_cb = new JComboBox<>(selectAreas);
        selectAreas_cb.setBounds(900, 395, 250, 30);
        selectAreas_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        selectAreas_cb.setEnabled(false);
        selectAreas_cb.setSelectedIndex(4);
        this.add(selectAreas_cb);


        // <========= 4) selectCourse_cb =========>
        String[] selectCourse = {"SE", "CS", "IT", "CYS", "CC", "AI", ""};
        selectCourse_cb  = new JComboBox<>(selectCourse);
        selectCourse_cb.setBounds(1000, 535, 150, 30);
        selectCourse_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        selectCourse_cb.setEnabled(false);
        selectCourse_cb.setSelectedIndex(6);
        this.add(selectCourse_cb);



        // <================== JLabel ==================>
        // <========= 1) manageAccountLabel =========>
        manageAccountLabel.setBounds(0,75,1200,60);
        manageAccountLabel.setFont(new Font("Impact", Font.PLAIN, 60));
        topPanel.add(manageAccountLabel);


        // <========= 2) id_OR_NameLabel =========>
        id_OR_NameLabel.setBounds(30, 185, 300, 30);
        id_OR_NameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(id_OR_NameLabel);


        // <========= 3) selectUserRoleLabel =========>
        selectUserRoleLabel.setBounds(30, 220, 300, 30);
        selectUserRoleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(selectUserRoleLabel);


        // <========= 4) accountInfoLabel =========>
        accountInfoLabel.setBounds(800,185, 300,30);
        accountInfoLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(accountInfoLabel);


        // <========= 5) userIDLabel =========>
        userIDLabel.setBounds(800, 220, 300, 30);
        userIDLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(userIDLabel);


        // <========= 6) passwordLabel =========>
        passwordLabel.setBounds(800, 255, 300, 30);
        passwordLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(passwordLabel);


        // <========= 7) nameLabel =========>
        nameLabel.setBounds(800, 290, 300, 30);
        nameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(nameLabel);


        // <========= 8) genderLabel =========>
        genderLabel.setBounds(800, 325, 300, 30);
        genderLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(genderLabel);


        // <========= 9) userRoleLabel =========>
        userRoleLabel.setBounds(800, 360, 300, 30);
        userRoleLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(userRoleLabel);


        // <========= 10) areasLabel =========>
        areasLabel.setBounds(800, 395, 300, 30);
        areasLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(areasLabel);


        // <========= 11) otherAccountInfoLabel =========>
        otherAccountInfoLabel.setBounds(800, 465, 300, 30);
        otherAccountInfoLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(otherAccountInfoLabel);


        // <========= 12) emailLabel =========>
        emailLabel.setBounds(800, 500, 300, 30);
        emailLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(emailLabel);


        // <========= 13) courseLabel =========>
        courseLabel.setBounds(800, 535, 300, 30);
        courseLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(courseLabel);



        // <================== JPanel ==================>
        // <========= topPanel =========>
        topPanel.setBackground(new Color(153, 255, 153));
        topPanel.setLayout(null);
        topPanel.setBounds(0, 0, 1200, 150);
        this.add(topPanel);



        // <================== JRadioButton ==================>
        // <========= 1) male_rb =========>
        male_rb.setBounds(900, 325, 50 ,30);
        male_rb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        male_rb.setEnabled(false);
        male_rb.setFocusable(false);
        this.add(male_rb);


        // <========= 2) female_rb =========>
        female_rb.setBounds(950, 325, 50, 30);
        female_rb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        female_rb.setEnabled(false);
        female_rb.setFocusable(false);
        this.add(female_rb);

        ButtonGroup male_female_rb = new ButtonGroup();
        male_female_rb.add(male_rb);
        male_female_rb.add(female_rb);



        // <=============== DefaultTableModel & JTable ==================>
        // <========= 1) accountTable =========>
        String[] columnNames = {"UserID", "Password", "Name", "Gender", "UserRole", "Areas"};
        tableModel = new DefaultTableModel(columnNames, 0);
        accountTable = new JTable(tableModel);

        accountTable.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        accountTable.setRowHeight(30);
        accountTable.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(accountTable);
        scrollPane.setBounds(30, 350, 750, 400);
        displayAllAccount();
        this.add(scrollPane);

        accountTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = accountTable.getSelectedRow();

                String userID = tableModel.getValueAt(selectedRow, 0).toString();
                userIDField.setText(userID);

                String password = tableModel.getValueAt(selectedRow, 1).toString();
                passwordField.setText(password);

                String name = tableModel.getValueAt(selectedRow, 2).toString();
                nameField.setText(name);

                String gender = tableModel.getValueAt(selectedRow, 3).toString();
                if (gender.equals("M")) {
                    female_rb.setSelected(false);
                    male_rb.setSelected(true);
                }
                if (gender.equals("F")) {
                    female_rb.setSelected(true);
                    male_rb.setSelected(false);
                }

                String userRole = tableModel.getValueAt(selectedRow, 4).toString();
                switch (userRole) {
                    case "Academic Leaders" -> {
                        UserRole_cb.setSelectedIndex(0);
                        selectCourse_cb.setEnabled(false);
                        selectCourse_cb.setSelectedIndex(6);
                    }
                    case "Lecturer" -> {
                        UserRole_cb.setSelectedIndex(1);
                        selectCourse_cb.setEnabled(false);
                        selectCourse_cb.setSelectedIndex(6);
                    }
                    case "Student" -> {
                        UserRole_cb.setSelectedIndex(2);
                        selectCourse_cb.setEnabled(true);
                        getStudentCourse();
                    }
                }
                UserRole_cb.setEnabled(false);

                String areas = tableModel.getValueAt(selectedRow, 5).toString();
                switch (areas) {
                    case "School of Computing" -> selectAreas_cb.setSelectedIndex(0);
                    case "School of Technology" -> selectAreas_cb.setSelectedIndex(1);
                    case "School of Game Development" -> selectAreas_cb.setSelectedIndex(2);
                    case "School of Digital Marketing" -> selectAreas_cb.setSelectedIndex(3);
                }
                selectAreas_cb.setEnabled(true);
                createButton.setEnabled(false);
                getUserEmail();
            }
        });



        // <================== JTextField ==================>
        // <========= 1) id_OR_NameField =========>
        id_OR_NameField.setBounds(210, 185, 400, 30);
        id_OR_NameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(id_OR_NameField);


        // <========= 2) userIDField =========>
        userIDField.setBounds(900, 220, 250, 30);
        userIDField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        userIDField.setEditable(false);
        this.add(userIDField);


        // <========= 3) passwordField =========>
        passwordField.setBounds(900, 255, 250, 30);
        passwordField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        passwordField.setEditable(false);
        this.add(passwordField);


        // <========= 4) nameField =========>
        nameField.setBounds(900, 290, 250, 30);
        nameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        nameField.setEditable(false);
        this.add(nameField);


        // <========= 2) emailField =========>
        emailField.setBounds(900, 500, 250, 30);
        emailField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        emailField.setEditable(false);
        this.add(emailField);



        // <========= GUI FRAME =========>
        this.setIconImage(Resources.imageIcon.getImage());
        this.setTitle("Manage Account");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1200,800);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }



    public void displayAllAccount () {
        try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Account))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] accountInfo = line.split(" ; ");
                String[] categorizedAccount = {accountInfo[0], accountInfo[1],
                        accountInfo[3], accountInfo[4],
                        accountInfo[5], accountInfo[6]};
                tableModel.addRow(categorizedAccount);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Something went wrong. Please contact technician team for support",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }



    public void getUserEmail () {
        String selectedUserID = userIDField.getText();

        try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Account))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] accountInfo = line.split(" ; ");
                String userID = accountInfo[0];

                if (userID.equals(selectedUserID)) {
                    String userEmail = accountInfo[2];
                    emailField.setText(userEmail);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Something went wrong. Please contact technician team for support",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }



    public void getStudentCourse () {
        String selectedStudentID = userIDField.getText();

        try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Account))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] studentInfo = line.split(" ; ");
                String studentID = studentInfo[0];

                if (studentID.equals(selectedStudentID)) {
                    String course = studentInfo[7];
                    switch (course) {
                        case "SE" -> selectCourse_cb.setSelectedIndex(0);
                        case "CS" -> selectCourse_cb.setSelectedIndex(1);
                        case "IT" -> selectCourse_cb.setSelectedIndex(2);
                        case "CYS" -> selectCourse_cb.setSelectedIndex(3);
                        case "CC" -> selectCourse_cb.setSelectedIndex(4);
                        case "AI" -> selectCourse_cb.setSelectedIndex(5);
                    }
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Something went wrong. Please contact technician team for support",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }



    public String idNumberGenerator (String prefix) {
        int maxNumber = 0;
        try (BufferedReader reader1 = new BufferedReader(new FileReader(Resources.Account));
             BufferedReader reader2 = new BufferedReader(new FileReader(Resources.DeletedAccounts))) {

            String line1, line2;
            while ((line1 = reader1.readLine()) != null) {
                String[] userInfo = line1.split(" ; ");
                String userID = userInfo[0];

                if (userID.startsWith(prefix)) {
                    String[] ID_split_Number = userID.split(prefix);
                    int userNumber = Integer.parseInt(ID_split_Number[1]);
                    if (userNumber > maxNumber) {
                        maxNumber = userNumber;
                    }
                }

                while ((line2 = reader2.readLine()) != null) {
                    String[] deletedInfo = line2.split(" ; ");
                    String deletedID = deletedInfo[0];

                    if (userID.startsWith(prefix)) {
                        String[] ID_split_Number = deletedID.split(prefix);
                        int userNumber = Integer.parseInt(ID_split_Number[1]);
                        if (userNumber > maxNumber) {
                            maxNumber = userNumber;
                        }
                    }
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Something went wrong. Please contact technician team for support",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
        return String.format("%05d", maxNumber + 1);
    }
}




// MANAGE CLASSES
class ManageClassesGUI_Admin extends JFrame {

    DefaultTableModel tableModel;
    JButton exitButton = new JButton("Exit");
    JButton searchButton = new JButton("Search");
    JButton refreshButton = new JButton("Refresh");
    JButton updateButton = new JButton("Update");
    JButton deleteButton = new JButton("Delete");
    JComboBox<String> block_cb;
    JComboBox<String> level_cb;
    JComboBox<String> roomNumber_cb;
    JLabel manageAccountLabel = new JLabel("Manage Classes", JLabel.CENTER);
    JLabel modules_ID_NameLabel = new JLabel("Please enter module ID / module Name:");
    JLabel moduleInfoLabel = new JLabel("Module Info:");
    JLabel moduleIDLabel = new JLabel("Module ID:");
    JLabel moduleNameLabel = new JLabel("Module's Name:");
    JLabel lecturerLabel = new JLabel("Lecturer:");
    JLabel blockLabel = new JLabel("Block:");
    JLabel levelLabel = new JLabel("Level:");
    JLabel classLabel = new JLabel("Class:");
    JLabel roomNumberLabel = new JLabel("Room Number:");
    JPanel topPanel = new JPanel();
    JTable modulesTable;
    JTextField modules_ID_NameField = new JTextField();
    JTextField moduleIDField = new JTextField();
    JTextField moduleNameField = new JTextField();
    JTextField lecturerField = new JTextField();

    ManageClassesGUI_Admin () {
        // <================== JButton ==================>
        // <========= 1) exitButton =========>
        exitButton.setBounds(25,15,125,40);
        exitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        exitButton.setFocusable(false);
        exitButton.addActionListener(_ -> {
            dispose();
            new AdminDashboard();
        });
        topPanel.add(exitButton);


        // <========= 2) searchButton =========>
        searchButton.setBounds(670, 162, 100, 30);
        searchButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        searchButton.setFocusable(false);
        searchButton.addActionListener(_ -> {
            String searchModuleID = modules_ID_NameField.getText();
            String searchModuleName = modules_ID_NameField.getText();
            tableModel.setRowCount(0);

            if (modules_ID_NameField.getText().isEmpty()) {
                tableModel.setRowCount(0);
                displayModules();
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Modules))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] moduleInfo = line.split(" ; ");
                    String currentModuleID = moduleInfo[0];
                    String currentModuleName = moduleInfo[1];

                    if (searchModuleID.equals(currentModuleID)) {
                        String[] result = {currentModuleID, moduleInfo[1], moduleInfo[5], moduleInfo[6]};
                        tableModel.addRow(result);
                    }
                    if (searchModuleName.equals(currentModuleName)) {
                        String[] result = {moduleInfo[0], currentModuleName, moduleInfo[5], moduleInfo[6]};
                        tableModel.addRow(result);
                    }
                }

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Modules list is not found",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Something went wrong. Please contact technician team for support",
                        "Error", JOptionPane.WARNING_MESSAGE);
            }
        });
        this.add(searchButton);


        // <========= 3) refreshButton =========>
        refreshButton.setBounds(30, 210, 100, 30);
        refreshButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        refreshButton.setFocusable(false);
        refreshButton.addActionListener(_ -> {
            tableModel.setRowCount(0);
            displayModules();
        });
        this.add(refreshButton);


        // <========= 4) deleteButton =========>
        deleteButton.setBounds(790, 589, 100, 40);
        deleteButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(_ -> {
            String selectedModuleID = moduleIDField.getText();
            String selectedModuleName = moduleNameField.getText();
            String selectedLecturer = lecturerField.getText();
            StringBuilder updatedClass = new StringBuilder();

            if (selectedModuleID.isEmpty() || selectedModuleName.isEmpty() || selectedLecturer.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Please select any module info",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Modules))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] moduleInfo = line.split(" ; ");
                    String currentModuleID = moduleInfo[0];
                    String currentModuleName = moduleInfo[1];
                    String currentLecturer = moduleInfo[6];
                    String classroom = moduleInfo[5];

                    if (currentModuleID.equals(selectedModuleID) &&
                            currentModuleName.equals(selectedModuleName) &&
                            currentLecturer.equals(selectedLecturer)) {
                        if (classroom.equals("x-x-x")) {
                            JOptionPane.showMessageDialog(null,
                                    "Module haven't assign to any class yet",
                                    "Warning", JOptionPane.WARNING_MESSAGE);
                            return;
                        } else {
                            moduleInfo[5] = "x-x-x";
                            JOptionPane.showMessageDialog(null,
                                    "Class successfully removed",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    updatedClass.append(String.join(" ; ", moduleInfo)).append("\n");
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Modules list is not found",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Something went wrong. Please contact technician team for support",
                        "Error", JOptionPane.WARNING_MESSAGE);
            }

            try (FileWriter writer = new FileWriter(Resources.Modules)) {
                writer.write(updatedClass.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error writing to file",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            moduleIDField.setText("");
            moduleNameField.setText("");
            lecturerField.setText("");
            block_cb.setSelectedIndex(5);
            level_cb.setSelectedIndex(5);
            roomNumber_cb.setSelectedIndex(10);
            tableModel.setRowCount(0);
            displayModules();
        });
        this.add(deleteButton);


        // <========= 5) updateButton =========>
        updateButton.setBounds(1110, 589, 100, 40);
        updateButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        updateButton.setFocusable(false);
        updateButton.addActionListener(_ -> {
            String selectedModuleID = moduleIDField.getText();
            String selectedModuleName = moduleNameField.getText();
            String selectedLecturer = lecturerField.getText();

            String selectedBlock = (String) block_cb.getSelectedItem();
            String selectedLevel = (String) level_cb.getSelectedItem();
            String selectedRoomNumber = (String) roomNumber_cb.getSelectedItem();

            StringBuilder updatedClass = new StringBuilder();

            if (selectedModuleID.isEmpty() || selectedModuleName.isEmpty() || selectedLecturer.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Please select any module info",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }



            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Modules))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] moduleInfo = line.split(" ; ");
                    String storedModuleID = moduleInfo[0];
                    String classroom = moduleInfo[5];

                    String[] classroomInfo = classroom.split("-");
                    String currentBlock = classroomInfo[0];
                    String currentLevel = classroomInfo[1];
                    String currentRoomNumber = classroomInfo[2];

                    if (currentBlock.equals(selectedBlock) &&
                            currentLevel.equals(selectedLevel) &&
                            currentRoomNumber.equals(selectedRoomNumber)) {
                        JOptionPane.showMessageDialog(null,
                                "Update unsuccessful",
                                "Warning", JOptionPane.WARNING_MESSAGE);

                        return;
                    }
                    if (Objects.equals(selectedBlock, "x") ||
                            Objects.equals(selectedLevel, "x") ||
                            Objects.equals(selectedRoomNumber, "x")) {
                        JOptionPane.showMessageDialog(null,
                                "Please select the valid classroom",
                                "Warning", JOptionPane.WARNING_MESSAGE);

                        return;
                    }
                    if (storedModuleID.equals(selectedModuleID)) {
                        classroomInfo[0] = selectedBlock;
                        classroomInfo[1] = selectedLevel;
                        classroomInfo[2] = selectedRoomNumber;
                        String newClassroom = classroomInfo[0] + "-" + classroomInfo[1] + "-" + classroomInfo[2];

                        moduleInfo[5] = newClassroom;

                        JOptionPane.showMessageDialog(null,
                                "Class successfully updated / assigned",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    updatedClass.append(String.join(" ; ", moduleInfo)).append("\n");
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Modules list is not found",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Something went wrong. Please contact technician team for support",
                        "Error", JOptionPane.WARNING_MESSAGE);
            }

            try (FileWriter writer = new FileWriter(Resources.Modules)) {
                writer.write(updatedClass.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error writing to file",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

            moduleIDField.setText("");
            moduleNameField.setText("");
            lecturerField.setText("");
            block_cb.setSelectedIndex(5);
            level_cb.setSelectedIndex(5);
            roomNumber_cb.setSelectedIndex(10);
            tableModel.setRowCount(0);
            displayModules();
        });
        this.add(updateButton);



        // <================== JComboBox ==================>
        // <========= 1) block_cb =========>
        String[] blocks = {"A", "B", "C", "D", "E", "x"};
        block_cb = new JComboBox<>(blocks);
        block_cb.setBounds(790, 455, 50, 30);
        block_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        block_cb.setSelectedIndex(5);
        block_cb.setEnabled(false);
        this.add(block_cb);


        // <========= 2) level_cb =========>
        String[] levels = {"1", "2", "3", "4", "5", "x"};
        level_cb = new JComboBox<>(levels);
        level_cb.setBounds(910, 455, 50, 30);
        level_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        level_cb.setSelectedIndex(5);
        level_cb.setEnabled(false);
        this.add(level_cb);


        // <========= 3) roomNumber_cb =========>
        String[] roomNumber = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "x"};
        roomNumber_cb = new JComboBox<>(roomNumber);
        roomNumber_cb.setBounds(1050, 455, 160, 30);
        roomNumber_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        roomNumber_cb.setSelectedIndex(10);
        roomNumber_cb.setEnabled(false);
        this.add(roomNumber_cb);



        // <================== JLabel ==================>
        // <========= 1) manageAccountLabel =========>
        manageAccountLabel.setBounds(0,75,1250,60);
        manageAccountLabel.setFont(new Font("Impact", Font.PLAIN, 60));
        topPanel.add(manageAccountLabel);


        // <========= 2) modules_ID_NameLabel =========>
        modules_ID_NameLabel.setBounds(30, 160, 300, 30);
        modules_ID_NameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(modules_ID_NameLabel);


        // <========= 3) moduleInfoLabel =========>
        moduleInfoLabel.setBounds(790, 210, 300, 30);
        moduleInfoLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(moduleInfoLabel);


        // <========= 4) moduleIDLabel =========>
        moduleIDLabel.setBounds(790, 245, 300, 30);
        moduleIDLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(moduleIDLabel);


        // <========= 5) moduleNameLabel =========>
        moduleNameLabel.setBounds(790, 280, 300, 30);
        moduleNameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(moduleNameLabel);


        // <========= 6) lecturerLabel =========>
        lecturerLabel.setBounds(790, 315, 300, 30);
        lecturerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(lecturerLabel);


        // <========= 7) classLabel =========>
        classLabel.setBounds(790, 385, 300, 30);
        classLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(classLabel);


        // <========= 8) blockLabel =========>
        blockLabel.setBounds(790, 420, 300, 30);
        blockLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(blockLabel);


        // <========= 9) levelLabel =========>
        levelLabel.setBounds(910, 420, 300, 30);
        levelLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(levelLabel);


        // <========= 10) roomNumberLabel =========>
        roomNumberLabel.setBounds(1050, 420, 300, 30);
        roomNumberLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(roomNumberLabel);



        // <================== JPanel ==================>
        // <========= 1) topPanel =========>
        topPanel.setBackground(new Color(153,255,153));
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,1250,150);
        this.add(topPanel);



        // <================== DefaultTableModel & JTable ==================>
        // <========= 1) modulesTable =========>
        String[] ColumnNames = {"ModuleID", "Module's Name", "Room", "Lecturer"};
        tableModel = new DefaultTableModel(ColumnNames, 0);
        modulesTable = new JTable(tableModel);

        modulesTable.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        modulesTable.setRowHeight(20);
        modulesTable.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(modulesTable);
        scrollPane.setBounds(30, 250, 750, 380);
        displayModules();
        this.add(scrollPane);

        modulesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = modulesTable.getSelectedRow();
                block_cb.setEnabled(true);
                level_cb.setEnabled(true);
                roomNumber_cb.setEnabled(true);

                String selectedModuleID = tableModel.getValueAt(selectedRow, 0).toString();
                moduleIDField.setText(selectedModuleID);

                String selectedModuleName = tableModel.getValueAt(selectedRow, 1).toString();
                moduleNameField.setText(selectedModuleName);

                String selectedLecturer = tableModel.getValueAt(selectedRow, 3).toString();
                lecturerField.setText(selectedLecturer);

                String classroom = tableModel.getValueAt(selectedRow, 2).toString();
                if (classroom.equals("x-x-x")) {
                    block_cb.setSelectedIndex(5);
                    level_cb.setSelectedIndex(5);
                    roomNumber_cb.setSelectedIndex(10);
                    return;
                }
                String[] classroomInfo = classroom.split("-");
                String currentBlock = classroomInfo[0];
                String currentLevel = classroomInfo[1];
                String currentRoomNumber = classroomInfo[2];

                switch (currentBlock) {
                    case "A" -> block_cb.setSelectedIndex(0);
                    case "B" -> block_cb.setSelectedIndex(1);
                    case "C" -> block_cb.setSelectedIndex(2);
                    case "D" -> block_cb.setSelectedIndex(3);
                    case "E" -> block_cb.setSelectedIndex(4);
                }
                switch (currentLevel) {
                    case "1" -> level_cb.setSelectedIndex(0);
                    case "2" -> level_cb.setSelectedIndex(1);
                    case "3" -> level_cb.setSelectedIndex(2);
                    case "4" -> level_cb.setSelectedIndex(3);
                    case "5" -> level_cb.setSelectedIndex(4);
                }
                switch (currentRoomNumber) {
                    case "1" -> roomNumber_cb.setSelectedIndex(0);
                    case "2" -> roomNumber_cb.setSelectedIndex(1);
                    case "3" -> roomNumber_cb.setSelectedIndex(2);
                    case "4" -> roomNumber_cb.setSelectedIndex(3);
                    case "5" -> roomNumber_cb.setSelectedIndex(4);
                    case "6" -> roomNumber_cb.setSelectedIndex(5);
                    case "7" -> roomNumber_cb.setSelectedIndex(6);
                    case "8" -> roomNumber_cb.setSelectedIndex(7);
                    case "9" -> roomNumber_cb.setSelectedIndex(8);
                    case "10" -> roomNumber_cb.setSelectedIndex(9);
                }
            }
        });



        // <================== JTextField ==================>
        // <========= 1) modules_ID_NameField =========>
        modules_ID_NameField.setBounds(345, 162, 300, 30);
        modules_ID_NameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(modules_ID_NameField);


        // <========= 2) moduleIDField =========>
        moduleIDField.setBounds(910, 245, 305, 30);
        moduleIDField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        moduleIDField.setEditable(false);
        this.add(moduleIDField);


        // <========= 3) moduleNameField =========>
        moduleNameField.setBounds(910, 280, 305, 30);
        moduleNameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        moduleNameField.setEditable(false);
        this.add(moduleNameField);


        // <========= 2) lecturerField =========>
        lecturerField.setBounds(910, 315, 305, 30);
        lecturerField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        lecturerField.setEditable(false);
        this.add(lecturerField);



        // <========= GUI FRAME =========>
        this.setIconImage(Resources.imageIcon.getImage());
        this.setTitle("Manage Classes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1250,700);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }



    public void displayModules () {
        try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Modules))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] modulesInfo = line.split(" ; ");
                String[] categorized_Info = {modulesInfo[0], modulesInfo[1], modulesInfo[5], modulesInfo[6]};

                tableModel.addRow(categorized_Info);
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Modules list is not found",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Something went wrong. Please contact technician team for support",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}