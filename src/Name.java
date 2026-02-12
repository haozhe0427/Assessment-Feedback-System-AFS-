import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Name {
    public static String getName () {
        String userID = LoginGUI.userIDField.getText();
        String userName = null;

        try (BufferedReader reader1 = new BufferedReader(new FileReader(Resources.Account));
             BufferedReader reader2 = new BufferedReader(new FileReader(Resources.AdminAccount))) {

            String line1, line2;
            while ((line1 = reader1.readLine()) != null) {
                String[] loginInfo = line1.split(" ; ");
                String storedID = loginInfo[0];

                if (storedID.equalsIgnoreCase(userID)) {
                    userName = loginInfo[3];
                }
                while ((line2 = reader2.readLine()) != null) {
                    String[] adminInfo = line2.split(" ; ");
                    String adminID = adminInfo[0];

                    if (adminID.equalsIgnoreCase(userID)) {
                        userName = adminInfo[2];
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
