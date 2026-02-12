import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    // Lecturer account file
    private static final String ACCOUNT_FILE = "src/Text File/Account.txt";

    // Assessment file
    private static final String ASSESSMENT_FILE = "src/Text File/Assessments.txt";

    // Results file
    private static final String RESULTS_FILE = "src/Text File/ClassStudentList.txt";

    // Modules file (read-only, for JTable)
    private static final String MODULES_FILE = "src/Text File/Modules.txt";

    // ------------------- Save profile -------------------
    public static void saveProfile(String id, String newName, String newEmail,
                                   String newPassword, String newDept, String newLeader) throws IOException {

        File file = new File(ACCOUNT_FILE);
        List<String> lines = new ArrayList<>();

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" ; ");

                    if (parts.length >= 8 && parts[0].trim().equals(id.trim())) {
                        // Keep original gender and role
                        String gender = parts[4].trim();
                        String role = parts[5].trim();

                        String updatedLine =
                                id.trim() + " ; " +
                                        newPassword.trim() + " ; " +
                                        newEmail.trim() + " ; " +
                                        newName.trim() + " ; " +
                                        gender + " ; " +
                                        role + " ; " +
                                        newDept.trim() + " ; " +
                                        newLeader.trim();

                        lines.add(updatedLine);
                    } else {
                        lines.add(line);
                    }
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
        }
    }

    // ------------------- Save assessment -------------------
    public static void saveAssessment(assessment assessment) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ASSESSMENT_FILE, true))) {
            writer.write(assessment.toFileString());
            writer.newLine();
        }
    }

    // ------------------- Load assessments -------------------
    public static List<assessment> loadAssessments() {
        List<assessment> list = new ArrayList<>();
        File file = new File(ASSESSMENT_FILE);
        if (!file.exists()) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    list.add(new assessment(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Something went wrong. Please contact technician team for support",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
        return list;
    }

    // ------------------- Save result -------------------
    public static void saveResult(String assessmentId, String studentId, int marks, String feedback) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESULTS_FILE, true))) {
            writer.write(assessmentId + "," + studentId + "," + marks + "," + feedback);
            writer.newLine();
        }
    }

    // ------------------- Get report -------------------
    public static ArrayList<String> getReport(String assessmentId) {
        ArrayList<String> reportLines = new ArrayList<>();
        File file = new File(RESULTS_FILE);
        if (!file.exists()) return reportLines;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[0].equals(assessmentId)) {
                    reportLines.add("Student: " + parts[1] + " | Marks: " + parts[2] + " | Feedback: " + parts[3]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reportLines;
    }

    // ------------------- Load Modules (for JTable) -------------------
    public static List<String[]> loadModules() {
        List<String[]> modules = new ArrayList<>();
        File file = new File(MODULES_FILE);
        if (!file.exists()) return modules;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ; ");
                if (parts.length >= 9) {
                    modules.add(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modules;
    }
}


