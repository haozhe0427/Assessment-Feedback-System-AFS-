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
    // Format : UserID ; Password ; Name ; Gender ; UserRole ; Areas
    //
    // *** UserID contains Academic Leaders, Lecturer, And Student ONLY
    // ------------------------------------------------------------------------------------------------------


    // 2) AdminAccount.txt
    public static String AdminAccount = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\AdminAccount.txt";
    // ------------------------------------------------------------------------------------------------------
    // Use in: - LoginGUI.java (Make Credential validations)
    //         - Name.java (Get User's Name then print beside "Welcome Back, ..." after login is successful)
    //
     // Format: UserID(Admin) ; Password ; Name ; UserRole
    // ------------------------------------------------------------------------------------------------------


    // 3) AcademicLeadersAccount.txt
    public static String AcademicLeadersAccount = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\AcademicLeadersAccount.txt";
    // ------------------------------------------------------------------------------------------------------
    // Use in: - AssignLecturerGUI_Admin.java (To get academic leader's name ONLY then put inside JComboBox)
    //         - ManageAccountGUI_Admin.java (To display inside JTable)
    //
    // Format: UserID(Academic Leaders) ; Password ; Email ; Name ; Gender ; UserRole ; Areas
    // ------------------------------------------------------------------------------------------------------


    // 4) LecturerAccount.txt
     public static String LecturerAccount = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\LecturerAccount.txt";
    // -----------------------------------------------------------------------------------
    // Use in: - ManageAccountGUI_Admin.java (To display inside JTable)
    //
    // Format: UserID(Lecturer) ; Password ; Email ; Name ; Gender ; UserRole ; Areas
    // -----------------------------------------------------------------------------------


    // 5) StudentAccount.txt
    public static String StudentAccount = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\StudentAccount.txt";
    // ---------------------------------------------------------------------------------
    // Use in: - ManageAccountGUI_Admin.java (To display inside JTable)
    //
    // Format: UserID(Student) ; Password ; Email ; Name ; Gender ; UserRole ; Areas
    // ---------------------------------------------------------------------------------


    // 6) GradingSystem.txt
    public static String GradingSystem = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\GradingSystem.txt";
    // ------------------------------------------------------------------------------------
    // Use in: - GradingSystemGUI_Admin.java (To display inside JTable / Update the info)
    //
    // Format: Marks ; Grade ; GPA ; Status
    // ------------------------------------------------------------------------------------


    // 7) AssignLecturer.txt
    public static String AssignLecturer = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\AssignLecturer.txt";
    // ------------------------------------------------------------------------------------------------------------------
    // Use in: - AssignLecturerGUI_Admin.java (To assign / update / delete one of the lecturers to any academic leader)
    //
    // Format: UserID(Lecturer) ; Name ; Areas ; AssignedTo
    // ------------------------------------------------------------------------------------------------------------------


    // 8) Modules.txt
    public static String Modules = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\Modules.txt";




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