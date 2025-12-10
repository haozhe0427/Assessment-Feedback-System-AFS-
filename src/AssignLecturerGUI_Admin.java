import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class AssignLecturerGUI_Admin extends JFrame{

    JPanel topPanel = new JPanel();
    JLabel assignLecturerLabel = new JLabel("Assign Lecturer", JLabel.CENTER);
    JLabel lecturerLabel = new JLabel("Lecturer:");
    JLabel assignToLabel = new JLabel("Assign to:");
    JTextField lecturerField = new JTextField();
    JButton exitButton = new JButton("Exit");
    JButton assignButton = new JButton("Assign");
    JButton deleteButton = new JButton("Delete");
    DefaultTableModel tableModel;
    JTable lecturersTable;
    String[] academicLeaders = new String[15];
    JComboBox<String> academicLeaders_cb = new JComboBox<>(academicLeaders);

    AssignLecturerGUI_Admin () {
        // <================== JPanel ==================>
        // <========= 1) topPanel =========>
        topPanel.setBackground(new Color(153,255,153));
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,1200,150);
        this.add(topPanel);



        // <================== TextField ==================>
        // <========= 1) assignLecturerLabel =========>
        assignLecturerLabel.setBounds(0,75,1200,60);
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



        // <================== JTextField ==================>
        // <========= 1) lecturerField =========>
        lecturerField.setBounds(30,205,400,30);
        lecturerField.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(lecturerField);



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


        // <========= 2) assignButton =========>
        assignButton.setBounds(910,370, 250,70);
        assignButton.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        assignButton.setFocusable(false);
        assignButton.addActionListener(_ -> {
            if (lecturerField.getText().isEmpty() || academicLeaders_cb.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null,
                        "Please select lecturer and academic leader",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String selectedLecturer = lecturerField.getText();
            String selectedAcademicLeader = academicLeaders_cb.getSelectedItem().toString();

            StringBuilder updatedLecturers = new StringBuilder();
            boolean isUpdated = false;
            boolean isDuplicate = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(PicturesAndTextFile.AssignLecturer))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] LecturerInfo = line.split(" ; ");
                    String lecturerName = LecturerInfo[1];
                    String assignStatus = LecturerInfo[3];

                    if (selectedLecturer.equals(lecturerName)) {
                        if (selectedAcademicLeader.equals(assignStatus)) {
                            isDuplicate = true;
                        } else {
                            LecturerInfo[3] = selectedAcademicLeader;
                            isUpdated = true;
                        }
                    }
                    updatedLecturers.append(String.join(" ; ", LecturerInfo)).append("\n");
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


            // REWRITE THE FILE
            try (FileWriter writer = new FileWriter(PicturesAndTextFile.AssignLecturer)) {
                writer.write(updatedLecturers.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error writing to file",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }


            if (isDuplicate) {
                JOptionPane.showMessageDialog(null,
                        "Lecturer is already assigned to this Academic Leader",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (isUpdated) {
                JOptionPane.showMessageDialog(null,
                        "Lecturer successfully assigned/updated",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                tableModel.setRowCount(0);
                displayLecturers();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Lecturer not found",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        this.add(assignButton);


        // <========= 3) deleteButton =========>
        deleteButton.setBounds(910,470, 250,70);
        deleteButton.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(_ -> {
            if (lecturerField.getText().isEmpty() || academicLeaders_cb.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null,
                        "Please select lecturer and academic leader",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String selectedLecturer = lecturerField.getText();

            StringBuilder updatedLecturers = new StringBuilder();
            boolean isUpdated = false;
            boolean isDuplicate = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(PicturesAndTextFile.AssignLecturer))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] LecturerInfo = line.split(" ; ");
                    String lecturerName = LecturerInfo[1];
                    String assignStatus = LecturerInfo[3];

                    if (selectedLecturer.equals(lecturerName)) {
                        if (assignStatus.equals("NULL")) {
                            isDuplicate = true;
                        } else {
                            LecturerInfo[3] = "NULL";
                            isUpdated = true;
                        }
                    }
                    updatedLecturers.append(String.join(" ; ", LecturerInfo)).append("\n");
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


            // REWRITE THE FILE
            try (FileWriter writer = new FileWriter(PicturesAndTextFile.AssignLecturer)) {
                writer.write(updatedLecturers.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error writing to file",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }


            if (isDuplicate) {
                JOptionPane.showMessageDialog(null,
                        "Lecturer haven't assign to any academic leader yet",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (isUpdated) {
                JOptionPane.showMessageDialog(null,
                        "Lecturer successfully removed",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                tableModel.setRowCount(0);
                displayLecturers();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Lecturer not found",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        this.add(deleteButton);



        // <================== JComboBox ==================>
        // <========= 1) academicLeaders_cb =========>
        academicLeaders_cb.setBounds(910,205,250,30);
        academicLeaders_cb.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        displayAcademicLeadersName();
        this.add(academicLeaders_cb);



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
            }
        });



        // <========= GUI FRAME =========>
        this.setIconImage(PicturesAndTextFile.imageIcon.getImage());
        this.setTitle("Assign Lecturer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1200,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }



    public void displayLecturers () { // display every lecturer's info
        try (BufferedReader reader = new BufferedReader(new FileReader(PicturesAndTextFile.AssignLecturer))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] lecturersInfo = line.split(" ; ");
                tableModel.addRow(lecturersInfo);
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
        try (BufferedReader reader = new BufferedReader(new FileReader(PicturesAndTextFile.AcademicLeadersAccount))) {
            String line;
            int i = 0;

            while ((line = reader.readLine()) != null) {
                String[] academicLeadersInfo = line.split(" ; ");

                if (academicLeadersInfo.length >= 5) {
                    academicLeaders[i] = academicLeadersInfo[3];
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