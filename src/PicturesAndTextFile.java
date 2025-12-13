import javax.swing.*;

public class PicturesAndTextFile {

    // STORE PICTURES AND TEXT FILES ONLY


    // 1) Login.txt
    public static String Login = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\Login.txt";
    // ------------------------------------------------------------------------------------------------------
    // Use in: - LoginGUI.java (Make credential validations)
    //         - ManageAccountGUI_Admin.java (To display inside JTable)
    //         - Name.java (Get User's Name then print beside "Welcome Back, ..." after login is successful)
    //
    //
    //
    //
    // ------------------------------------------------------------------------------------------------------


    // 2) AdminAccount.txt
    public static String AdminAccount = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\AdminAccount.txt";
    // ------------------------------------------------------------------------------------------------------
    // Use in: - LoginGUI.java (Make Credential validations)
    //         - Name.java (Get User's Name then print beside "Welcome Back, ..." after login is successful)
    //
     // Format: UserID(Admin) ; Password ; Name ; UserRole
    // ------------------------------------------------------------------------------------------------------


    // 3) GradingSystem.txt
    public static String GradingSystem = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\GradingSystem.txt";
    // ------------------------------------------------------------------------------------
    // Use in: - GradingSystemGUI_Admin.java (To display inside JTable / Update the info)
    //
    // Format: Marks ; Grade ; GPA ; Status
    // ------------------------------------------------------------------------------------


    // 3) Modules.txt
    public static String Modules = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\Modules.txt";
    // ---------------------------------------------------------------------------------------------------------------------
    // Use in: - ManageClassesGUI_Admin.java (To display inside JTable / update & delete the class)
    //
    // Format: ModuleID ; ModuleName ; Assessment_1 ; Assessment_2 ; Assessment_3 ; Classroom ; Lecturer ; No.of students
    // ---------------------------------------------------------------------------------------------------------------------



    // Icon.png
    public static ImageIcon imageIcon = new ImageIcon("C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Pictures\\Icon.png");
    // -----------------------------------------------
    // Use in: - For all GUI Frame
    // -----------------------------------------------


    // skills.png, video.lecture.png, market-research.png, classroom.png
    public static ImageIcon manageAccountIcon = new ImageIcon("C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Pictures\\skills.png");
    public static ImageIcon lecturerIcon = new ImageIcon("C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Pictures\\video-lecture.png");
    public static ImageIcon gradingSystemIcon = new ImageIcon("C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Pictures\\market-research.png");
    public static ImageIcon classesIcon = new ImageIcon("C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Pictures\\classroom.png");
    // -----------------------------------------------
    // Use in: - DashboardGUI_Admin.java
    // -----------------------------------------------
}