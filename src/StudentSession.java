// For Student
public class StudentSession {
    private static String studentId;
    private static String studentName;
    private static String studentEmail;
    private static String studentPassword;

    public static void login(String id, String name, String email, String password) {
        studentId = id;
        studentName = name;
        studentEmail = email;
        studentPassword = password;
    }

    public static String getStudentId(){
        return studentId;
    }

    public static String getStudentName(){
        return studentName;
    }

    public static String getStudentEmail(){
        return studentEmail;
    }

    public static String getStudentPassword(){
        return studentPassword;
    }
}
