import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String LECTURER_FILE = "C:\\Users\\User\\Java\\Projects\\AssessmentFeedbackSystem\\src\\Text File\\Modules.txt";
    private static final String ASSESMENT_FILE = "assesments.txt";
    private static final String RESULTS_FILE = "results.txt";

    public static void saveProfile(String id, String newName, String newEmail,
                                   String newPassword, String newDept, String newLeader) throws IOException {

        File file = new File(LECTURER_FILE);
        List<String> lines = new ArrayList<>();

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" ; ");

                    if (parts.length >= 8 && parts[0].trim().equals(id.trim())) {

                        String updatedLine =
                                id.trim() + " ; " +
                                        newPassword.trim() + " ; " +   // update password
                                        newEmail.trim() + " ; " +
                                        newName.trim() + " ; " +
                                        parts[4].trim() + " ; " +
                                        parts[5].trim() + " ; " +
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


    public static void saveAssessment(assessment assessment) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ASSESMENT_FILE, true))) {
            writer.write(assessment.toFileString());
            writer.newLine();
        }
    }

    public static List<assessment> loadAssessments() {
        List<assessment> list = new ArrayList<>();
        File file = new File(ASSESMENT_FILE);
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
                    "Error",JOptionPane.WARNING_MESSAGE);
        }
        return list;
    }

    public static void saveResult(String assesmentId, String studentId, int marks, String feedback) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESULTS_FILE, true))) {
            writer.write(assesmentId + "," + studentId + "," + marks + "," + feedback);
            writer.newLine();
        }
    }

    public static java.util.ArrayList<String> getReport(String assessmentId) {
        java.util.ArrayList<String> reportLines = new java.util.ArrayList<>();
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
}

