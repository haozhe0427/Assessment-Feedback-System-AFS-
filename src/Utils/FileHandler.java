package Utils;

import models.assesment;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    // 1. Point to your friend's specific file location
    private static final String LECTURER_FILE = "src/Text File/LecturerAccount.txt";
    private static final String ASSESMENT_FILE = "assesments.txt";
    private static final String RESULTS_FILE = "results.txt";

    // --- NEW: SMART LOAD PROFILE ---
    // This reads the file and looks for the Lecturer ID you want (e.g., "LC000001")
    // Returns: [ID, Name, Email, Dept]
    public static String[] loadProfile(String targetID) {
        File file = new File(LECTURER_FILE);
        if (!file.exists()) return new String[]{"", "", "", ""};

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split by semicolon because your friend used ";"
                String[] parts = line.split(";");

                // Cleanup spaces (trim) and check if this is the right ID
                if (parts.length >= 5 && parts[0].trim().equals(targetID)) {
                    // Map the data:
                    // File: ID(0) ; Name(1) ; Gender(2) ; Age(3) ; School(4) ; Status(5)
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String dept = parts[4].trim(); // School maps to Dept

                    // Return formatted for your GUI (Email is blank as file doesn't have it)
                    return new String[]{id, name, "", dept};
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{"", "", "", ""};
    }

    // --- NEW: SMART SAVE PROFILE ---
    // Updates ONLY the Name and School for the given ID. Preserves Gender/Age.
    public static void saveProfile(String id, String newName, String newEmail, String newDept) throws IOException {
        File file = new File(LECTURER_FILE);
        List<String> lines = new ArrayList<>();
        boolean found = false;

        // 1. Read ALL lines into memory
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length >= 5 && parts[0].trim().equals(id)) {
                        // FOUND IT! Update this line but keep old gender/age/status
                        // Reconstruct: ID ; Name ; Gender ; Age ; School ; Status
                        String updatedLine = id + " ; " +
                                newName + " ; " +
                                parts[2].trim() + " ; " + // Keep Gender
                                parts[3].trim() + " ; " + // Keep Age
                                newDept + " ; " +
                                (parts.length > 5 ? parts[5].trim() : "NULL"); // Keep Status
                        lines.add(updatedLine);
                        found = true;
                    } else {
                        // Not the right user, just keep the line as is
                        lines.add(line);
                    }
                }
            }
        }

        // 2. Write everything back
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
        }
    }

    // --- KEEP YOUR EXISTING ASSESMENT CODE BELOW THIS LINE ---

    public static void saveAssesment(assesment assesment) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ASSESMENT_FILE, true))) {
            writer.write(assesment.toFileString());
            writer.newLine();
        }
    }

    public static List<assesment> loadAssesments() {
        List<assesment> list = new ArrayList<>();
        File file = new File(ASSESMENT_FILE);
        if (!file.exists()) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    list.add(new assesment(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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

