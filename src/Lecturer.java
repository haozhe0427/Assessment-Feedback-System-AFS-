public class Lecturer {
    private String lecturerID;
    private String Name;
    private String Email;
    private String Password;
    private String School;
    private String AcademicLeader;

    Lecturer (String lecturerID, String Name, String Email, String Password, String School, String AcademicLeader) {
        this.lecturerID = lecturerID;
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
        this.School = School;
        this.AcademicLeader = AcademicLeader;
    }

    public String getLecturerID() {
        return lecturerID;
    }
    public void setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        this.Name = name;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        this.Password = password;
    }
    public String getSchool() {
        return School;
    }
    public void setSchool(String school) {
        this.School = school;
    }
    public String getAcademicLeader() {
        return AcademicLeader;
    }
    public void setAcademicLeader(String academicLeader) {
        this.AcademicLeader = academicLeader;
    }
}