import javax.swing.*;

public class Resources {
    // STORE PICTURES AND TEXT FILES ONLY
    // 1) Account.txt
    public static final String Account = "D:\\intelij save\\src\\Text File\\Account.txt";
    // -------------------------------------------------------------------------------------------------------------------------------
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
    public static final String AdminAccount = "D:\\intelij save\\src\\Text File\\AdminAccount.txt";
    // ------------------------------------------------------------------------------------------------------
    // Format: UserID(Admin) ; Password ; Name ; UserRole
    // example: AD000001 ; 12345 ; Ding Hao Zhe ; Admin
    // ------------------------------------------------------------------------------------------------------


    // 3) GradingSystem.txt
    public static final String GradingSystem = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\GradingSystem.txt";
    // ------------------------------------------------------------------------------------
    // Format: Marks ; Grade ; GPA ; Status
    // example: 80-100 ; A+ ; 4.00 ; Distinction
    // ------------------------------------------------------------------------------------


    // 3) Modules.txt
    public static final String Modules = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\Modules.txt";
    // -----------------------------------------------------------------------------------------------------------------------------------
    // Format: ModuleID ; ModuleName ; Assessment 1 ; Assessment 2 ; Assessment 3 ; Classroom ; Lecturer's name ; Day ; Time
    // example : MD0001 ; Object-Oriented Development ; NULL ; NULL ; NULL ; A-1-4 ; Joshua Koroh Pudin ; Monday ; 10:30 - 12:30
    // -----------------------------------------------------------------------------------------------------------------------------------


    // 4) DeletedAccount.txt
    public static final String DeletedAccounts = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\DeletedAccount.txt";
    // ----------------------------------------------------------------------------------------------------
    // FORMAT: *** Same as Account.txt
    // ----------------------------------------------------------------------------------------------------


    // 4) ClassStudentList.txt
    public static final String ClassStudentList = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\ClassStudentList.txt";
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // FORMAT : ModuleID ; ModuleName ; Assessment 1 ; Assessment 2 ; Assessment 3 ; Location ; Account ID (Student ID) ; GPA ; Grade ; Feedback ; Assessment 1 feedback ; Assessment 2 feedback ; Assessment 3 feedback
    // example:
    // MD0002 ; Programming For Data Analysis ; Assignment (55%) ; Final Exam (45%) ; NULL ; A-1-4 ; AFS00003 ; 3.3 ; B+ ; Dear Lecturer, thank you for the guidance on PFDA this semester. ; Good ; Very good ; You did not complete essay but you did great in other parts
    //
    // ** Feedback: Student -> Lecturer
    // ** Assessment 1 feedback - Assessment 3 feedback: Lecturer -> Student
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------






    public static ImageIcon manageAccountIcon = new ImageIcon("C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Pictures\\skills.png");
    public static ImageIcon lecturerIcon = new ImageIcon("C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Pictures\\video-lecture.png");
    public static ImageIcon gradingSystemIcon = new ImageIcon("C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Pictures\\market-research.png");
    public static ImageIcon classesIcon = new ImageIcon("C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Pictures\\classroom.png");
}