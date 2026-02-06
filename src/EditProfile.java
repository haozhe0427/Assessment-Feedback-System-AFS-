import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class EditProfile extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EditProfile.class.getName());

    public EditProfile() {
        initComponents();
        fillTextFields();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nameTF = new javax.swing.JTextField();
        emailTF = new javax.swing.JTextField();
        passwordPF = new javax.swing.JPasswordField();
        chkShowPassword = new javax.swing.JCheckBox();
        updateBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setText("Profile");

        jLabel2.setText("Name: ");

        jLabel3.setText("Email:");

        jLabel4.setText("Password:");

        chkShowPassword.setText(" Show password");
        chkShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowPasswordActionPerformed(evt);
            }
        });

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(exitBtn)
                                                .addGap(146, 146, 146)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(165, 165, 165)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(chkShowPassword)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(passwordPF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                                                        .addComponent(emailTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(nameTF, javax.swing.GroupLayout.Alignment.TRAILING)))
                                                        .addComponent(updateBtn))))
                                .addContainerGap(241, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkShowPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(updateBtn)
                                .addContainerGap(180, Short.MAX_VALUE))
        );

        pack();
    }

    private void fillTextFields(){
        nameTF.setText(StudentSession.getStudentName());
        emailTF.setText(StudentSession.getStudentEmail());
        passwordPF.setText(StudentSession.getStudentPassword());
    }

    private void chkShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {
        if (chkShowPassword.isSelected()) {
            passwordPF.setEchoChar((char) 0);
        } else {
            passwordPF.setEchoChar('*');
        }
    }

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String studentName = nameTF.getText().trim();
        String studentEmail = emailTF.getText().trim();
        char[] passChars = passwordPF.getPassword();
        String studentPassword = new String(passChars).trim();

        if(studentName.isEmpty() || studentEmail.isEmpty() || studentPassword.isEmpty()){
            JOptionPane.showMessageDialog(
                    this,
                    "All fields are required",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        List<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(Resources.Login))){

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < lines.size(); i++) {
            String[] columns = lines.get(i).split(";");

            String userId = columns[0].trim();

            if (userId.equals(StudentSession.getStudentId())) {
                columns[1] = " " + studentPassword + " ";
                columns[2] = " " + studentEmail + " ";
                columns[3] = " " + studentName + " ";

                String updatedLine = String.join(";", columns);
                lines.set(i, updatedLine);
                break;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Resources.Login))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            StudentSession.login(StudentSession.getStudentId(), studentName, studentEmail, studentPassword);
            fillTextFields();
            JOptionPane.showMessageDialog(
                    this,
                    "Successfully updated"
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {
        StudentDashboard dashboard = new StudentDashboard();
        dashboard.setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new EditProfile().setVisible(true));
    }

    private javax.swing.JCheckBox chkShowPassword;
    private javax.swing.JTextField emailTF;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField nameTF;
    private javax.swing.JPasswordField passwordPF;
    private javax.swing.JButton updateBtn;
}
