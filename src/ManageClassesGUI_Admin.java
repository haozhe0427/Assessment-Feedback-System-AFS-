import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ManageClassesGUI_Admin extends JFrame {

    // JButton
    JButton exitButton = new JButton("Exit");
    JButton searchButton = new JButton("Search");
    JButton refreshButton = new JButton("Refresh");
    JButton updateButton = new JButton("Update");
    JButton deleteButton = new JButton("Delete");
    JButton createButton = new JButton("Create");

    // JComboBox
    String[] classroom = {};
    JComboBox<String> classroom_cb = new JComboBox<>(classroom);

    // JLabel
    JLabel manageAccountLabel = new JLabel("Manage Classes", JLabel.CENTER);
    JLabel modules_ID_NameLabel = new JLabel("Please enter module ID / module Name:");
    JLabel moduleInfoLabel = new JLabel("Module Info:");
    JLabel moduleIDLabel = new JLabel("Module ID:");
    JLabel moduleNameLabel = new JLabel("Module's Name:");
    JLabel lecturerLabel = new JLabel("Lecturer:");
    JLabel classLabel = new JLabel("Class:");
    JLabel blockLabel = new JLabel("Block:");
    JLabel roomLabel = new JLabel("Room:");

    // JPanel
    JPanel topPanel = new JPanel();

    // JRadioButton
    JRadioButton A_rb = new JRadioButton("A");
    JRadioButton B_rb = new JRadioButton("B");
    JRadioButton C_rb = new JRadioButton("C");
    JRadioButton D_rb = new JRadioButton("D");
    JRadioButton E_rb = new JRadioButton("E");

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

            try (BufferedReader reader = new BufferedReader(new FileReader(PicturesAndTextFile.Modules))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] moduleInfo = line.split(" ; ");
                    String storedModuleID = moduleInfo[0];
                    String storedModuleName = moduleInfo[1];

                    if (searchModuleID.equals(storedModuleID)) {
                        String[] result = {storedModuleID, moduleInfo[1], moduleInfo[5], moduleInfo[6]};
                        tableModel.addRow(result);
                    }
                    if (searchModuleName.equals(storedModuleName)) {
                        String[] result = {moduleInfo[0], storedModuleName, moduleInfo[5], moduleInfo[6]};
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

        // <========= 4) refreshButton =========>
        refreshButton.setBounds(30, 195, 100, 30);
        refreshButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        refreshButton.setFocusable(false);
        refreshButton.addActionListener(_ -> {
            tableModel.setRowCount(0);
            displayModules();
        });
        this.add(refreshButton);

        // <========= 3) updateButton =========>
        updateButton.setBounds(790, 589, 100, 40);
        updateButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        updateButton.setFocusable(false);
        this.add(updateButton);

        // <========= 4) deleteButton =========>
        deleteButton.setBounds(915, 589, 100, 40);
        deleteButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        deleteButton.setFocusable(false);
        this.add(deleteButton);

        // <========= 5) createButton =========>
        createButton.setBounds(1040, 589, 100, 40);
        createButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        createButton.setFocusable(false);
        this.add(createButton);



        // <================== JComboBox ==================>
        // <========= 1) classroom_cb =========>
        classroom_cb.setBounds(1000, 455, 190, 30);
        classroom_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(classroom_cb);



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

        // <========= 9) roomLabel =========>
        roomLabel.setBounds(1000, 420, 300, 30);
        roomLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(roomLabel);



        // <================== JPanel ==================>
        // <========= 1) topPanel =========>
        topPanel.setBackground(new Color(153,255,153));
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,1250,150);
        this.add(topPanel);



        // <================== JRadioButton ==================>
        // <========= 1) A_rb =========>
        A_rb.setBounds(790, 455, 50, 30);
        A_rb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        A_rb.setFocusable(false);
        this.add(A_rb);

        // <========= 1) B_rb =========>
        B_rb.setBounds(790, 480, 50, 30);
        B_rb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        B_rb.setFocusable(false);
        this.add(B_rb);

        // <========= 1) C_rb =========>
        C_rb.setBounds(790, 505, 50, 30);
        C_rb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        C_rb.setFocusable(false);
        this.add(C_rb);

        // <========= 1) D_rb =========>
        D_rb.setBounds(850, 455, 50, 30);
        D_rb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        D_rb.setFocusable(false);
        this.add(D_rb);

        // <========= 1) E_rb =========>
        E_rb.setBounds(850, 480, 50, 30);
        E_rb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        E_rb.setFocusable(false);
        this.add(E_rb);



        // <================== JTextField ==================>
        // <========= 1) modules_ID_NameField =========>
        modules_ID_NameField.setBounds(345, 162, 300, 30);
        modules_ID_NameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(modules_ID_NameField);

        // <========= 2) moduleIDField =========>
        moduleIDField.setBounds(910, 245, 305, 30);
        moduleIDField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(moduleIDField);

        // <========= 3) moduleNameField =========>
        moduleNameField.setBounds(910, 280, 305, 30);
        moduleNameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(moduleNameField);

        // <========= 2) lecturerField =========>
        lecturerField.setBounds(910, 315, 305, 30);
        lecturerField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
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

                String selectedModuleID = tableModel.getValueAt(selectedRow, 0).toString();
                moduleIDField.setText(selectedModuleID);

                String selectedModuleName = tableModel.getValueAt(selectedRow, 1).toString();
                moduleNameField.setText(selectedModuleName);

                String selectedLecturer = tableModel.getValueAt(selectedRow, 3).toString();
                lecturerField.setText(selectedLecturer);

                String Room = tableModel.getValueAt(selectedRow, 2).toString();
            }
        });



        // <========= GUI FRAME =========>
        this.setIconImage(PicturesAndTextFile.imageIcon.getImage());
        this.setTitle("Assessment Feedback System (Admin)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1250,700);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }



    public void displayModules () {
        try (BufferedReader reader = new BufferedReader(new FileReader(PicturesAndTextFile.Modules))) {

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