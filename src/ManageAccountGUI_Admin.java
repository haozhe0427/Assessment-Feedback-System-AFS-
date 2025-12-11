import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class ManageAccountGUI_Admin extends JFrame {

    // JPanel
    JPanel topPanel = new JPanel();

    // JLabel
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

    // JTextField
    JTextField id_OR_NameField = new JTextField();
    JTextField userIDField = new JTextField();
    JTextField passwordField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField emailField = new JTextField();

    // JRadioButton
    JRadioButton male_rb = new JRadioButton("M");
    JRadioButton female_rb = new JRadioButton("F");

    // JComboBox
    String[] selectUserRole = {"Academic Leaders", "Lecturer", "Student"};
    JComboBox<String> selectUserRole_cb = new JComboBox<>(selectUserRole);
    String[] createUserRole = {"Academic Leaders", "Lecturer", "Student", ""};
    JComboBox<String> createUserRole_cb = new JComboBox<>(createUserRole);
    String[] selectAreas = {"School of Computing", "School of Technology", "School of Game Development", "School of Digital Marketing", ""};
    JComboBox<String> selectAreas_cb = new JComboBox<>(selectAreas);
    String[] selectCourse = {"SE", "CS", "IT", "CYS", "CC", "AI", ""};
    JComboBox<String> selectCourse_cb = new JComboBox<>(selectCourse);

    // DefaultTableModel & JTable
    DefaultTableModel tableModel;
    JTable accountTable;

    // JButton
    JButton exitButton = new JButton("Exit");
    JButton clearButton1 = new JButton("Clear");
    JButton searchButton = new JButton("Search");
    JButton updateButton = new JButton("Update");
    JButton deleteButton = new JButton("Delete");
    JButton createButton = new JButton("Create");
    JButton createAccountButton = new JButton("Create Account");
    JButton clearButton2 = new JButton("Clear");

    String selectedUserID = userIDField.getText();
    String userRole = (String) createUserRole_cb.getSelectedItem();
    String selectedUserAreas = (String) selectAreas_cb.getSelectedItem();

    ManageAccountGUI_Admin () {
        // <================== JPanel ==================>
        // <========= topPanel =========>
        topPanel.setBackground(new Color(153, 255, 153));
        topPanel.setLayout(null);
        topPanel.setBounds(0, 0, 1200, 150);
        this.add(topPanel);



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



        // <================== JComboBox ==================>
        // <========= 1) selectUserRole_cb =========>
        selectUserRole_cb.setBounds(210, 220, 250, 30);
        selectUserRole_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(selectUserRole_cb);

        // <========= 2) createUserRole_cb =========>
        createUserRole_cb.setBounds(900, 360, 250, 30);
        createUserRole_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        createUserRole_cb.setEnabled(false);
        this.add(createUserRole_cb);

        // <========= 3) selectAreas_cb =========>
        selectAreas_cb.setBounds(900, 395, 250, 30);
        selectAreas_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        selectAreas_cb.setEnabled(false);
        this.add(selectAreas_cb);

        // <========= 4) selectCourse_cb =========>
        selectCourse_cb.setBounds(1000, 535, 150, 30);
        selectCourse_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        selectCourse_cb.setEnabled(false);
        this.add(selectCourse_cb);



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
                    male_rb.setSelected(true);
                } else {
                    female_rb.setSelected(true);
                }

                String userRole = tableModel.getValueAt(selectedRow, 4).toString();
                switch (userRole) {
                    case "Academic Leaders" -> {
                        createUserRole_cb.setSelectedIndex(0);
                        selectCourse_cb.setEnabled(false);
                        selectCourse_cb.setSelectedIndex(6);
                    }
                    case "Lecturer" -> {
                        createUserRole_cb.setSelectedIndex(1);
                        selectCourse_cb.setEnabled(false);
                        selectCourse_cb.setSelectedIndex(6);
                    }
                    case "Student" -> {
                        createUserRole_cb.setSelectedIndex(2);
                        selectCourse_cb.setEnabled(true);
                        selectCourse_cb.setSelectedIndex(0);
                    }
                }
                createUserRole_cb.setEnabled(true);

                String areas = tableModel.getValueAt(selectedRow, 5).toString();
                switch (areas) {
                    case "School of Computing" -> selectAreas_cb.setSelectedIndex(0);
                    case "School of Technology" -> selectAreas_cb.setSelectedIndex(1);
                    case "School of Game Development" -> selectAreas_cb.setSelectedIndex(2);
                    default -> selectAreas_cb.setSelectedIndex(3);
                }
                selectAreas_cb.setEnabled(true);

                getUserEmail();
            }
        });



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

        // <========= 2) clearButton1 =========>
        clearButton1.setBounds(30, 290, 100, 40);
        clearButton1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        clearButton1.setFocusable(false);
        clearButton1.addActionListener(_ -> {
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
            String selectedRole = selectUserRole_cb.getSelectedItem().toString();
            tableModel.setRowCount(0);

            try (BufferedReader reader = new BufferedReader(new FileReader(PicturesAndTextFile.Login))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] accountInfo = line.split(" ; ");
                    String userID = accountInfo[0];
                    String password = accountInfo[1];
                    String name = accountInfo[2];
                    String gender = accountInfo[3];
                    String userRole = accountInfo[4];
                    String areas = accountInfo[5];
                    
                    if (selectedID_Name.isEmpty()) {
                        if (selectedRole.equals(userRole)) {
                            tableModel.addRow(new Object[]{
                                    userID, password, name, gender, userRole, areas
                            });
                            return;
                        }
                    } else {
                        if (selectedRole.equals(userRole) &&
                                (((selectedID_Name.equals(userID))||(selectedID_Name.equals(name))))) {
                            tableModel.addRow(new Object[]{
                                    userID, password, name, gender, userRole, areas
                            });
                            return;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null,
                        "Account doesn't exist",
                        "Warning", JOptionPane.WARNING_MESSAGE);

                id_OR_NameField.setText("");
                displayAllAccount();

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Account list is not found",
                        "Warning", JOptionPane.WARNING_MESSAGE);
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
        this.add(updateButton);

        // <========= 5) deleteButton =========>
        deleteButton.setBounds(925, 709, 100, 40);
        deleteButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        deleteButton.setFocusable(false);
        this.add(deleteButton);

        // <========= 6) createButton =========>
        createButton.setBounds(1050, 709, 100, 40);
        createButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        createButton.setFocusable(false);
        this.add(createButton);

        // <========= 7) createAccountButton =========>
        createAccountButton.setBounds(627, 290, 150, 40);
        createAccountButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        createAccountButton.setFocusable(false);
        this.add(createAccountButton);

        // <========= 8) clearButton2 =========>
        clearButton2.setBounds(800, 655, 350, 40);
        clearButton2.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        clearButton2.setFocusable(false);
        this.add(clearButton2);



        // <========= GUI FRAME =========>
        this.setIconImage(PicturesAndTextFile.imageIcon.getImage());
        this.setTitle("Manage Account");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1200,800);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }



    public void displayAllAccount () {
        try (BufferedReader reader = new BufferedReader(new FileReader(PicturesAndTextFile.Login))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] accountInfo = line.split(" ; ");
                tableModel.addRow(accountInfo);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Account list is not found",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Something went wrong. Please contact technician team for support",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }



    public void getUserEmail () {
        String selectedUserID = userIDField.getText();

        try (BufferedReader reader1 = new BufferedReader(new FileReader(PicturesAndTextFile.AcademicLeadersAccount));
        BufferedReader reader2 = new BufferedReader(new FileReader(PicturesAndTextFile.LecturerAccount));
        BufferedReader reader3 = new BufferedReader(new FileReader(PicturesAndTextFile.StudentAccount))) {

            String line1, line2, line3;
            while ((line1 = reader1.readLine()) != null) {
                String[] academicLeadersInfo = line1.split(" ; ");
                String academicLeadersID = academicLeadersInfo[0];

                if (selectedUserID.equals(academicLeadersID)) {
                    String academicLeadersEmail = academicLeadersInfo[2];
                    emailField.setText(academicLeadersEmail);
                    return;
                }

                while ((line2 = reader2.readLine()) != null) {
                    String[] lecturerInfo = line2.split(" ; ");
                    String lecturerID = lecturerInfo[0];

                    if (selectedUserID.equals(lecturerID)) {
                        String lecturerEmail = lecturerInfo[2];
                        emailField.setText(lecturerEmail);
                        return;
                    }

                    while ((line3 = reader3.readLine()) != null) {
                        String[] studentInfo = line3.split(" ; ");
                        String studentId = studentInfo[0];

                        if (selectedUserID.equals(studentId)) {
                            String studentEmail = studentInfo[2];
                            emailField.setText(studentEmail);
                            return;
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Account list is not found",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Something went wrong. Please contact technician team for support",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}