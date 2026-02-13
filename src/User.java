// For Academic Leader
public class User {
    private String id;
    private String password;
    private String email;
    private String name;
    private String gender;
    private String role;
    private String department;
    private String specialization; // For the last column (e.g., 'SE' or 'CS')

    public User(String id, String password, String email, String name, String gender, String role) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.role = role;
    }

    // Getters
    public String getId() { return id; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getRole() { return role; }



    // Setters (useful for Edit Profile later)
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDepartment() {
        return department;
    }

    public String getSpecialization() {
        return specialization;
    }
}