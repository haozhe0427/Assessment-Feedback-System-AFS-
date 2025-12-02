import javax.swing.*;
import java.awt.*;

public class ManageAccountGUI_Admin extends JFrame {

    JPanel topPanel = new JPanel();
    JButton exitButton = new JButton("Exit");
    JLabel manageAccountLabel = new JLabel("Manage Account", JLabel.CENTER);

    ManageAccountGUI_Admin () {
        // <========= TOP PANEL =========>
        topPanel.setBackground(new Color(153, 255, 153));
        topPanel.setLayout(null);
        topPanel.setBounds(0, 0, 820, 150);
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
        manageAccountLabel.setBounds(0,75,820,50);
        manageAccountLabel.setFont(new Font("Impact", Font.PLAIN, 50));
        topPanel.add(manageAccountLabel);



        // <========= GUI FRAME =========>
        this.setIconImage(PicturesAndTextFile.imageIcon.getImage());
        this.setTitle("Manage Account");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(820,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
