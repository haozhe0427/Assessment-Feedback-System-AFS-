package AcademicLeader;
import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {
        User currentUser = Session.getCurrentUser();
        if (currentUser == null) {
            // Safety: if accessed without login, return to login
            new LoginFrame().setVisible(true);
            this.dispose();
            return;
        }


        setTitle("Dashboard - " + currentUser.getRole());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel lblWelcome = new JLabel("Welcome, " + currentUser.getName() + " (" + currentUser.getId() + ")", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(lblWelcome, BorderLayout.NORTH);

        // Main Menu Buttons Panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 2, 20, 20));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnEditProfile = new JButton("Edit Personal Profile");
        JButton btnManageModules = new JButton("Manage Modules (CRUD & Assign)");
        JButton btnAnalyzeReports = new JButton("Analyze Reports");
        JButton btnLogout = new JButton("Logout");

        // Add buttons
        menuPanel.add(btnEditProfile);
        menuPanel.add(btnManageModules);
        menuPanel.add(btnAnalyzeReports);
        menuPanel.add(btnLogout);

        add(menuPanel, BorderLayout.CENTER);

        // --- Action Listeners (Placeholders for next steps) ---

        btnEditProfile.addActionListener(e -> {
            new EditProfileFrame().setVisible(true);
        });

        // Inside DashboardFrame.java
        btnManageModules.addActionListener(e -> {
            new ModulesGUI().setVisible(true); // Changed from ModuleManagementFrame
        });

        // Inside DashboardFrame.java
        btnAnalyzeReports.addActionListener(e -> {
            new ReportsGUI().setVisible(true);
        });

        btnLogout.addActionListener(e -> {
            Session.logout();
            new LoginFrame().setVisible(true);
            this.dispose();
        });
    }
}
