import javax.swing.*;
import java.awt.*;

public class ManageAccountGUI_Admin extends JFrame {

    JPanel topPanel = new JPanel();
    JButton exitButton = new JButton("Exit");
    JLabel manageAccountLabel = new JLabel("Manage Account", JLabel.CENTER);
    JLabel id_OR_NameLabel = new JLabel("Enter UserID / Name:");
    JTextField id_OR_NameField = new JTextField();
    JLabel userRoleLabel = new JLabel("Select user role:");
    String[] userRole = {"Admin", "Academic Leaders", "Lecturer", "Student"};
    JComboBox<String> userRole_cb = new JComboBox<>(userRole);
    JButton clearButton = new JButton("Clear");
    JButton searchButton = new JButton("Search");

    ManageAccountGUI_Admin () {
        // <========= TOP PANEL =========>
        topPanel.setBackground(new Color(153, 255, 153));
        topPanel.setLayout(null);
        topPanel.setBounds(0, 0, 1200, 150);
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



        // <========= MANAGE ACCOUNT LABEL =========>
        manageAccountLabel.setBounds(0,75,1200,60);
        manageAccountLabel.setFont(new Font("Impact", Font.PLAIN, 60));
        topPanel.add(manageAccountLabel);



        // <========= ID OR NAME LABEL & FIELD =========>
        id_OR_NameLabel.setBounds(30, 170, 250, 30);
        id_OR_NameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(id_OR_NameLabel);

        id_OR_NameField.setBounds(30, 205, 250, 30);
        id_OR_NameField.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(id_OR_NameField);



        // <========= USER ROLE LABEL & COMBO BOX =========>
        userRoleLabel.setBounds(350, 170, 150, 30);
        userRoleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(userRoleLabel);

        userRole_cb.setBounds(350, 205, 250, 30);
        userRole_cb.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        this.add(userRole_cb);



        clearButton.setBounds(350, 275, 100, 40);
        clearButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        clearButton.setFocusable(false);
        this.add(clearButton);



        searchButton.setBounds(500, 275, 100, 40);
        searchButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        searchButton.setFocusable(false);
        this.add(searchButton);


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
}
