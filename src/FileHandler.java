import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String LECTURER_FILE = "src/Text File/LecturerAccount.txt";
    private static final String ASSESMENT_FILE = "assesments.txt";
    private static final String RESULTS_FILE = "results.txt";


    public static String[] loadProfile(String targetID) {
        File file = new File(LECTURER_FILE);
        if (!file.exists()) return new String[]{"", "", "", ""};

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(";");


                if (parts.length >= 5 && parts[0].trim().equals(targetID)) {

                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String dept = parts[4].trim(); // School maps to Dept


                    return new String[]{id, name, "", dept};
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{"", "", "", ""};
    }


    public static void saveProfile(String id, String newName, String newEmail, String newDept) throws IOException {
        File file = new File(LECTURER_FILE);
        List<String> lines = new ArrayList<>();
        boolean found = false;


        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length >= 5 && parts[0].trim().equals(id)) {

                        String updatedLine = id + " ; " +
                                newName + " ; " +
                                parts[2].trim() + " ; " + // Keep Gender
                                parts[3].trim() + " ; " + // Keep Age
                                newDept + " ; " +
                                (parts.length > 5 ? parts[5].trim() : "NULL");
                        lines.add(updatedLine);
                        found = true;
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



    public static void saveAssesment(assessment assesment) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ASSESMENT_FILE, true))) {
            writer.write(assesment.toFileString());
            writer.newLine();
        }
    }

    public static List<assessment> loadAssesments() {
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

