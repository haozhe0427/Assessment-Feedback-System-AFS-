import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class ManageClassesGUI_Admin extends JFrame {

    // JButton
    JButton exitButton = new JButton("Exit");
    JButton searchButton = new JButton("Search");
    JButton refreshButton = new JButton("Refresh");
    JButton updateButton = new JButton("Update");
    JButton deleteButton = new JButton("Delete");

    // JComboBox
    JComboBox<String> block_cb;
    JComboBox<String> level_cb;
    JComboBox<String> roomNumber_cb;

    // JLabel
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

    // JPanel
    JPanel topPanel = new JPanel();

    // JTextField
    JTextField modules_ID_NameField = new JTextField();
    JTextField moduleIDField = new JTextField();
    JTextField moduleNameField = new JTextField();
    JTextField lecturerField = new JTextField();

    // DefaultTableModel & JTable
    DefaultTableModel tableModel;
    JTable modulesTable;

    ManageClassesGUI_Admin () {
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
                    if (selectedBlock.equals("x") ||
                            selectedLevel.equals("x") ||
                            selectedRoomNumber.equals("x")) {
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