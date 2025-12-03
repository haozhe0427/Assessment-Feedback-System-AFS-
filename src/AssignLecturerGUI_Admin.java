import javax.swing.*;
import java.awt.*;

public class AssignLecturerGUI_Admin extends JFrame{

    JPanel topPanel = new JPanel();
    JButton exitButton = new JButton("Exit");
    JLabel assignLecturerLabel = new JLabel("Assign Lecturer", JLabel.CENTER);
    JLabel lecturerLabel = new JLabel("Lecturer:");
    JTextField lecturerField = new JTextField();
    JTable lecturersTable = new JTable();
    JLabel assignToLabel = new JLabel("Assign to:");
    JComboBox academicLeaders_cb;
    JButton assignButton = new JButton("Assign");
    JButton updateButton = new JButton("Update");
    JButton deleteButton = new JButton("Delete");

    AssignLecturerGUI_Admin () {
        // <========= TOP PANEL =========>
        topPanel.setBackground(new Color(153,255,153));
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,820,150);
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
        assignLecturerLabel.setBounds(0,75,820,50);
        assignLecturerLabel.setFont(new Font("Impact", Font.PLAIN, 50));
        topPanel.add(assignLecturerLabel);



        // <========= "LECTURER" LABEL =========>
        lecturerLabel.setBounds(30, 170, 100, 30);
        lecturerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        this.add(lecturerLabel);



        // <========= LECTURER FIELD =========>
        lecturerField.setBounds(30,205,300,30);
        lecturerField.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(lecturerField);



        // <========= LECTURERS TABLE =========>
        lecturersTable.setBounds(30, 270, 450,270);
        lecturersTable.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(lecturersTable);



        // <========= "ASSIGN TO" LABEL =========>
        assignToLabel.setBounds(520, 170, 100, 30);
        assignToLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        this.add(assignToLabel);



        // <========= ACADEMIC LEADERS COMBO BOX =========>
        String[] academicLeaders = new String[10];
        academicLeaders_cb = new JComboBox<>(academicLeaders);
        academicLeaders_cb.setBounds(520,205,250,30);
        academicLeaders_cb.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(academicLeaders_cb);



        // <========= ASSIGN BUTTON =========>
        assignButton.setBounds(520,270, 250,70);
        assignButton.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        assignButton.setFocusable(false);
        this.add(assignButton);



        // <========= UPDATE BUTTON =========>
        updateButton.setBounds(520,370, 250,70);
        updateButton.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        updateButton.setFocusable(false);
        this.add(updateButton);



        // <========= DELETE BUTTON =========>
        deleteButton.setBounds(520,470, 250,70);
        deleteButton.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        deleteButton.setFocusable(false);
        this.add(deleteButton);



        // <========= GUI FRAME =========>
        this.setIconImage(PicturesAndTextFile.imageIcon.getImage());
        this.setTitle("Assign Lecturer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(820,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
