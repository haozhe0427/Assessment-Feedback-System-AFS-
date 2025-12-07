import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManageAccountGUI_Admin extends JFrame {

    JPanel topPanel = new JPanel();
    JLabel manageAccountLabel = new JLabel("Manage Account", JLabel.CENTER);
    JLabel id_OR_NameLabel = new JLabel("Enter UserID / Name:");
    JLabel userRoleLabel = new JLabel("Select user role:");
    JTextField id_OR_NameField = new JTextField();
    String[] userRole = {"Academic Leaders", "Lecturer", "Student"};
    JComboBox<String> userRole_cb = new JComboBox<>(userRole);
    DefaultTableModel tableModel;
    JTable accountTable;
    JButton exitButton = new JButton("Exit");
    JButton clearButton = new JButton("Clear");
    JButton searchButton = new JButton("Search");

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
        id_OR_NameLabel.setBounds(30, 170, 300, 30);
        id_OR_NameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        this.add(id_OR_NameLabel);


        // <========= 3) userRoleLabel =========>
        userRoleLabel.setBounds(527, 170, 300, 30);
        userRoleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        this.add(userRoleLabel);



        // <================== JTextField ==================>
        // <========= 1) id_OR_NameField =========>
        id_OR_NameField.setBounds(30, 205, 400, 30);
        id_OR_NameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(id_OR_NameField);



        // <================== JComboBox ==================>
        // <========= 1) userRole_cb =========>
        userRole_cb.setBounds(527, 205, 250, 30);
        userRole_cb.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.add(userRole_cb);



        String[] columnNames = {"UserID", "Password", "Name", "UserRole", "Areas", "Course"};
        tableModel = new DefaultTableModel(columnNames, 0);
        accountTable = new JTable(tableModel);

        accountTable.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        accountTable.setRowHeight(20);
        accountTable.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(accountTable);
        scrollPane.setBounds(30, 350, 750, 400);
        this.add(scrollPane);



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


        // <========= 2) clearButton =========>
        clearButton.setBounds(330, 275, 100, 40);
        clearButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        clearButton.setFocusable(false);
        this.add(clearButton);


        // <========= 3) searchButton =========>
        searchButton.setBounds(527, 275, 100, 40);
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