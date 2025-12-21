import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class ManageAccountGUI_Admin extends JFrame {

    // JButton
    JButton exitButton = new JButton("Exit");
    JButton clearButton1 = new JButton("Clear");
    JButton searchButton = new JButton("Search");
    JButton updateButton = new JButton("Update");
    JButton deleteButton = new JButton("Delete");
    JButton createButton = new JButton("Create");
    JButton createAccountButton = new JButton("Create Account");
    JButton clearButton2 = new JButton("Clear");

    // JComboBox
    JComboBox<String> selectUserRole_cb;
    JComboBox<String> UserRole_cb;
    JComboBox<String> selectAreas_cb;
    JComboBox<String> selectCourse_cb;

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

    // JPanel
    JPanel topPanel = new JPanel();

    // JRadioButton
    JRadioButton male_rb = new JRadioButton("M");
    JRadioButton female_rb = new JRadioButton("F");

    // DefaultTableModel & JTable
    DefaultTableModel tableModel;
    JTable accountTable;

    // JTextField
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

            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Login))) {
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
                        if (selectedRole.equals(userRole)) {
                            tableModel.addRow(new Object[]{
                                    userID, password, name, gender, userRole, areas
                            });
                        }
                    } else {
                        if (selectedRole.equals(userRole) &&
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

            if (selectedUserID.isEmpty() || selectedUserRole.isEmpty() || selectedAreas.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Please select any account",
                        "Error", JOptionPane.ERROR_MESSAGE);

                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Login))) {

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

            try (FileWriter writer = new FileWriter(Resources.Login)) {
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

            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Login))) {

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

            try (FileWriter writer = new FileWriter(Resources.Login)) {
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
                    if (userID.isEmpty() || userRole.isEmpty() ||
                            !(male_rb.isSelected() || female_rb.isSelected()) || area.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter every information",
                                "Error", JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                }
                case "Student" -> {
                    if (userID.isEmpty() || userRole.isEmpty() ||
                            !(male_rb.isSelected() || female_rb.isSelected()) ||
                            area.isEmpty() || course.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter every information",
                                "Error", JOptionPane.ERROR_MESSAGE);

                        return;
                    }
                }
            }


            try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Login))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userInfo = line.split(" ; ");
                    String storedUserID = userInfo[0];
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
                                if (userRole.equals("Lecturer") || userRole.equals("Academic Leaders")) {
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
                                if (userRole.equals("Student")) {
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

            try (FileWriter writer = new FileWriter(Resources.Login)) {
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

            String userRole = (String) UserRole_cb.getSelectedItem();
            String id = "AFS" + idNumberGenerator("AFS");
            userIDField.setText(id);
            passwordField.setText(id + "@password");
            emailField.setText(id + "@email.com");
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
        try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Login))) {
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

        try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Login))) {

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

        try (BufferedReader reader = new BufferedReader(new FileReader(Resources.Login))) {

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
        try (BufferedReader reader1 = new BufferedReader(new FileReader(Resources.Login));
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