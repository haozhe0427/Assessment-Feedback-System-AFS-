import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// <================== DASHBOARD GUI (ADMIN) ==================>
public class DashboardGUI_Admin extends JFrame implements ActionListener{

    JPanel topPanel = new JPanel();
    JButton logOutButton = new JButton("Log Out");
    JLabel AFSLabel = new JLabel("Assessment Feedback System");
    JLabel welcomeLabel = new JLabel("Welcome back, Ding Hao Zhe");
    JButton manageAccountButton = new JButton("Manage Account");
    JButton assignLecturersButton = new JButton("Assign Lecturer");
    JButton gradingSystemButton = new JButton("Grading System");
    JButton manageClassesButton = new JButton("Manage Classes");

    ImageIcon imageIcon = new ImageIcon("C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Pictures\\Icon.png");
    ImageIcon manageAccountIcon = new ImageIcon("C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Pictures\\skills.png");
    ImageIcon lecturerIcon = new ImageIcon("C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Pictures\\video-lecture.png");
    ImageIcon gradingSystemIcon = new ImageIcon("C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Pictures\\market-research.png");
    ImageIcon classesIcon = new ImageIcon("C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Pictures\\classroom.png");


    DashboardGUI_Admin() {
        // <========= TOP PANEL =========>
        topPanel.setBackground(new Color(153,255,153));
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,820,200);
        this.add(topPanel);



        // <========= LOG OUT BUTTON =========>
        logOutButton.setBounds(25,15,125,40);
        logOutButton.setFont(new Font("Comic Sans MS", Font.BOLD,20));
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(_ -> {
            dispose();
            new LoginGUI();
        });
        topPanel.add(logOutButton);



        // <========= AFS LABEL =========>
        AFSLabel.setBounds(0,75,820,50);
        AFSLabel.setHorizontalAlignment(JLabel.CENTER);
        AFSLabel.setFont(new Font("Impact", Font.PLAIN, 50));
        topPanel.add(AFSLabel);



        // <========= GREETING LABEL =========>
        welcomeLabel.setBounds(0,150,820,40);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        topPanel.add(welcomeLabel);



        // <========= MANAGE ACCOUNT BUTTON =========>
        manageAccountIcon = new ImageIcon(manageAccountIcon.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH));
        manageAccountButton.setBounds(130,240,250,100);
        manageAccountButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        manageAccountButton.setHorizontalTextPosition(JButton.CENTER);
        manageAccountButton.setVerticalTextPosition(JButton.NORTH);
        manageAccountButton.setFocusable(false);
        manageAccountButton.setIcon(manageAccountIcon);
        this.add(manageAccountButton);



        // <========= ASSIGN LECTURER BUTTON =========>
        lecturerIcon = new ImageIcon(lecturerIcon.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH));
        assignLecturersButton.setBounds(420,240,250,100);
        assignLecturersButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        assignLecturersButton.setHorizontalTextPosition(JButton.CENTER);
        assignLecturersButton.setVerticalTextPosition(JButton.NORTH);
        assignLecturersButton.setFocusable(false);
        assignLecturersButton.setIcon(lecturerIcon);
        this.add(assignLecturersButton);



        // <========= GRADING SYSTEM BUTTON =========>
        gradingSystemIcon = new ImageIcon(gradingSystemIcon.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH));
        gradingSystemButton.setBounds(130,390,250,100);
        gradingSystemButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        gradingSystemButton.setHorizontalTextPosition(JButton.CENTER);
        gradingSystemButton.setVerticalTextPosition(JButton.NORTH);
        gradingSystemButton.setFocusable(false);
        gradingSystemButton.setIcon(gradingSystemIcon);
        this.add(gradingSystemButton);



        // <========= MANAGE CLASSES BUTTON =========>
        classesIcon = new ImageIcon(classesIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        manageClassesButton.setBounds(420,390,250,100);
        manageClassesButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        manageClassesButton.setHorizontalTextPosition(JButton.CENTER);
        manageClassesButton.setVerticalTextPosition(JButton.NORTH);
        manageClassesButton.setFocusable(false);
        manageClassesButton.setIcon(classesIcon);
        this.add(manageClassesButton);



        // <========= GUI FRAME =========>
        this.setIconImage(imageIcon.getImage());
        this.setTitle("Assessment Feedback System (Admin)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(820,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }



    @Override
    public void actionPerformed(ActionEvent e) {}
}
