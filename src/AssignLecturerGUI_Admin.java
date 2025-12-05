import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AssignLecturerGUI_Admin extends JFrame{

    JPanel topPanel = new JPanel();
    JButton exitButton = new JButton("Exit");
    JLabel assignLecturerLabel = new JLabel("Assign Lecturer", JLabel.CENTER);
    JLabel lecturerLabel = new JLabel("Lecturer:");
    JTextField lecturerField = new JTextField();
    DefaultTableModel tableModel;
    JTable lecturersTable;
    JLabel assignToLabel = new JLabel("Assign to:");
    String[] academicLeaders = new String[15];
    JComboBox academicLeaders_cb = new JComboBox<>(academicLeaders);
    JButton assignButton = new JButton("Assign");
    JButton updateButton = new JButton("Update");
    JButton deleteButton = new JButton("Delete");

    AssignLecturerGUI_Admin () {
        // <========= TOP PANEL =========>
        topPanel.setBackground(new Color(153,255,153));
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,1200,150);
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



        // <========= "ASSIGN LECTURER" LABEL =========>
        assignLecturerLabel.setBounds(0,75,1200,60);
        assignLecturerLabel.setFont(new Font("Impact", Font.PLAIN, 60));
        topPanel.add(assignLecturerLabel);



        // <========= "LECTURER" LABEL =========>
        lecturerLabel.setBounds(30, 170, 100, 30);
        lecturerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        this.add(lecturerLabel);



        // <========= LECTURER FIELD =========>
        lecturerField.setBounds(30,205,400,30);
        lecturerField.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(lecturerField);



        // <========= LECTURERS TABLE =========>
        String[] columnNames = {"Lecturer ID", "Name", "Gender", "Age", "Areas", "Assigned to"};
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



        // <========= "ASSIGN TO" LABEL =========>
        assignToLabel.setBounds(910, 170, 100, 30);
        assignToLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        this.add(assignToLabel);



        // <========= ACADEMIC LEADERS COMBO BOX =========>
        academicLeaders_cb.setBounds(910,205,250,30);
        academicLeaders_cb.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        displayAcademicLeadersName();
        this.add(academicLeaders_cb);




        // <========= ASSIGN BUTTON =========>
        assignButton.setBounds(910,270, 250,70);
        assignButton.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        assignButton.setFocusable(false);
        this.add(assignButton);



        // <========= UPDATE BUTTON =========>
        updateButton.setBounds(910,370, 250,70);
        updateButton.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        updateButton.setFocusable(false);
        this.add(updateButton);



        // <========= DELETE BUTTON =========>
        deleteButton.setBounds(910,470, 250,70);
        deleteButton.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        deleteButton.setFocusable(false);
        this.add(deleteButton);



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



    public void displayLecturers () {
        try (BufferedReader reader = new BufferedReader(new FileReader(PicturesAndTextFile.LecturerAccount))) {
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



    public void displayAcademicLeadersName () {
        try (BufferedReader reader = new BufferedReader(new FileReader(PicturesAndTextFile.AcademicLeadersAccount))) {
            String line;
            int i = 0;

            while ((line = reader.readLine()) != null) {
                String[] academicLeadersInfo = line.split(" ; ");

                if (academicLeadersInfo.length >= 5) {
                    academicLeaders[i] = academicLeadersInfo[1];
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