import javax.swing.*;

public class Resources {

    // STORE PICTURES AND TEXT FILES ONLY


    // 1) Account.txt
    public static String Login = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\Account.txt";
    // -------------------------------------------------------------------------------------------------------------------------------
    // Use in: - LoginGUI.java (Make credential validations)
    //         - ManageAccountGUI_Admin.java (To display inside JTable)
    //         - Name.java (Get User's Name then print beside "Welcome Back, ..." after login is successful)
    //
    // FORMAT :
    // a) Student:
    //    [UserID ; Password ; Email ; Name ; Gender ; UserRole ; Areas ; Course]
    //    Example:
    //    (AFS00003 ; 12345 ; AFS00003@mail.apu.edu.my ; Nyan Lin Thet ; M ; Student ; School of Computing ; SE)
    //
    //
    // b) Lecturer:
    //    [UserID ; Password ; Email ; Name ; Gender ; UserRole ; Areas ; AssignedTo]
    //    Example:
    //    (AFS00002 ; 12345 ; AFS00002@mail.apu.edu.my ; Joshua Koroh Pudin ; M ; Lecturer ; School of Computing ; NULL)
    //
    //
    // c) Academic Leaders:
    //    [UserID ; Password ; Email ; Name ; Gender ; UserRole ; Areas ; <None>]
    //    Example:
    //    (AFS00001 ; 12345 ; AFS00001@mail.apu.edu.my ; Lwin Phone Thit ; M ; Academic Leaders ; School of Computing ; NULL)
    // --------------------------------------------------------------------------------------------------------------------------------


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


    // 4) DeletedAccount.txt
    public static String DeletedAccounts = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\DeletedAccount.txt";
    // ----------------------------------------------------------------------------------------------------
    // Use in: - To store deleted account after delete button (in ManageAccountGUI_Admin) is clicked
    //
    // FORMAT: *** Same as Account.txt
    // ----------------------------------------------------------------------------------------------------


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