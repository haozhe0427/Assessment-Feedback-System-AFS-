import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// <================== DASHBOARD GUI (PROGRAMME LEADER) ==================>
public class DashboardGUI_ProgrammeLeader extends JFrame implements ActionListener {

    JButton logOutButton = new JButton("Log Out");


    DashboardGUI_ProgrammeLeader () {
        // <========= LOG OUT BUTTON =========>
        logOutButton.setBounds(25,15,125,40);
        logOutButton.setFont(new Font("Segoe UI", Font.BOLD,20));
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginGUI();
            }
        });
        this.add(logOutButton);



        // <========= GUI FRAME =========>
        this.setTitle("Assessment Feedback System (AFS)");
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