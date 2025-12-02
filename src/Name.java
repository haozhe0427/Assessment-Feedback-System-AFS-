import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Name {
    public static String getAdminName () {
        String userID = LoginGUI.userIDField.getText();
        String userName = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(PicturesAndTextFile.Login))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ; ");

                if (data.length >= 4) {
                    String storedID = data[0];

                    if (storedID.equalsIgnoreCase(userID)) {
                        userName = data[2];
                    }
                }
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Account list is not found",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Something went wrong. Please contact technician team for support",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
        return userName;
    }
}
