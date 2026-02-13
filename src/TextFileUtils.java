import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFileUtils {
    // --- METHOD 1: UPDATE USER (For Edit Profile) ---
    public static boolean updateUserInFile(User updatedUser) {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(Resources.Account))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    lines.add(line); // Preserve empty lines
                    continue;
                }

                String[] data = line.split(" ; ");
                if (data.length > 0 && data[0].equals(updatedUser.getId())) {
                    // This is the user to update. Reconstruct the line using the new data.
                    // Format: ID ; Password ; Email ; Name ; Gender ; Role ; Dept ; Specialization
                    String newLine = updatedUser.getId() + " ; " +
                            updatedUser.getPassword() + " ; " +
                            updatedUser.getEmail() + " ; " +
                            updatedUser.getName() + " ; " +
                            updatedUser.getGender() + " ; " +
                            updatedUser.getRole() + " ; " +
                            updatedUser.getDepartment() + " ; " +
                            (updatedUser.getSpecialization() != null ? updatedUser.getSpecialization() : "NULL") + " ; ";

                    lines.add(newLine);
                    found = true;
                } else {
                    lines.add(line); // Keep other users unchanged
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Write everything back to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Resources.Account))) {
            for (String s : lines) {
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return found;
    }
    // --- INNER CLASS: MODULE (No need for separate file) ---
    public static class Module {
        String code, name, a1, a2, a3, loc, lec, day, time;

        public Module(String code, String name, String a1, String a2, String a3, String loc, String lec, String day, String time) {
            this.code = code;
            this.name = name;
            this.a1 = a1;
            this.a2 = a2;
            this.a3 = a3;
            this.loc = loc;
            this.lec = lec;
            this.day = day;
            this.time = time;
        }

        // Helper to format for file
        public String toFileString() {
            return code + " ; " + name + " ; " + a1 + " ; " + a2 + " ; " +
                    a3 + " ; " + loc + " ; " + lec + " ; " + day + " ; " + time + " ; ";
        }
    }

    // ================= NEW MODULE METHODS =================

    public static List<Module> getAllModules() {
        List<Module> modules = new ArrayList<>();
        File file = new File(Resources.Modules);
        if (!file.exists()) return modules;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(" ; ");
                if (data.length >= 2) {
                    modules.add(new Module(
                            data[0].trim(), data[1].trim(),
                            (data.length > 2) ? data[2].trim() : "NULL",
                            (data.length > 3) ? data[3].trim() : "NULL",
                            (data.length > 4) ? data[4].trim() : "NULL",
                            (data.length > 5) ? data[5].trim() : "NULL",
                            (data.length > 6) ? data[6].trim() : "NULL",
                            (data.length > 7 ) ? data[7].trim() : "NULL",
                            (data.length > 8) ? data[8].trim() :  "NULL"
                    ));
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        return modules;
    }

    public static boolean saveModules(List<Module> modules) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Resources.Modules))) {
            for (Module m : modules) {
                bw.write(m.toFileString());
                bw.newLine();
            }
            return true;
        } catch (IOException e) { return false; }
    }

    public static boolean deleteModule(String code) {
        List<Module> all = getAllModules();
        if (all.removeIf(m -> m.code.equals(code))) {
            return saveModules(all);
        }
        return false;
    }

}