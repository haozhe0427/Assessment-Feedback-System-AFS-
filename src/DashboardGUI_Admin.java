import javax.swing.*;
import java.awt.*;

public class DashboardGUI_Admin extends JFrame {

    JPanel topPanel = new JPanel();
    JLabel AFSLabel = new JLabel("Assessment Feedback System", JLabel.CENTER);
    JLabel welcomeLabel = new JLabel();
    JButton logOutButton = new JButton("Log Out");
    JButton manageAccountButton = new JButton("Manage Account");
    JButton assignLecturersButton = new JButton("Assign Lecturer");
    JButton gradingSystemButton = new JButton("Grading System");
    JButton manageClassesButton = new JButton("Manage Classes");

    DashboardGUI_Admin() {
        // <================== JPanel ==================>
        // <========= 1) topPanel =========>
        topPanel.setBackground(new Color(153,255,153));
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,820,190);
        this.add(topPanel);



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