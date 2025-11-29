import javax.swing.*;

public class AssignLecturerGUI_Admin extends JFrame{

    ImageIcon imageIcon = new ImageIcon("C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Pictures\\Icon.png");

    AssignLecturerGUI_Admin () {
        this.setIconImage(imageIcon.getImage());
        this.setTitle("Assessment Feedback System (Admin)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(820,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
