import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class studentLogin extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(studentLogin.class.getName());

    public studentLogin() {
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        userIDTF = new javax.swing.JTextField();
        passwordPF = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        chkShowPassword = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setText("Login");

        jLabel2.setText("User ID:");

        jLabel3.setText("Password:");

        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        chkShowPassword.setText("  Show password");
        chkShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(205, 205, 205)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(118, 118, 118)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(loginBtn)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(userIDTF)
                                                                        .addComponent(passwordPF, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
                                                        .addComponent(chkShowPassword))))
                                .addContainerGap(197, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel1)
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(userIDTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(passwordPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkShowPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(loginBtn)
                                .addContainerGap(165, Short.MAX_VALUE))
        );

        pack();
    }

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String userId = userIDTF.getText();
        char[] passChars = passwordPF.getPassword();
        String password = new String(passChars);

        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(Resources.Login)
            );

            String line;
            boolean userFound = false;

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(";");

                String fileUserId = columns[0].trim();
                String filePassword = columns[1].trim();
                String fileEmail = columns[2].trim();
                String fileStudentName = columns[3].trim();

                if (fileUserId.equals(userId)) {
                    userFound = true;

                    if (filePassword.equals(password)) {
                        StudentSession.login(fileUserId, fileStudentName, fileEmail, filePassword);
                        StudentDashboard dashboard = new StudentDashboard();
                        dashboard.setVisible(true);

                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this,"Incorrect password");
                    }
                    break;
                }
            }

            if (!userFound) {
                JOptionPane.showMessageDialog(this, "User not found");
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chkShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {
        if (chkShowPassword.isSelected()) {
            passwordPF.setEchoChar((char) 0);
        } else {
            passwordPF.setEchoChar('*');
        }
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

        java.awt.EventQueue.invokeLater(() -> new studentLogin().setVisible(true));
    }

    private javax.swing.JCheckBox chkShowPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPasswordField passwordPF;
    private javax.swing.JTextField userIDTF;
}
