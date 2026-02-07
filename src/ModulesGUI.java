import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ModulesGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public ModulesGUI() {
        setTitle("Manage Modules & Assign Lecturers");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- Top Panel (Buttons) ---
        JPanel topPanel = new JPanel();
        JButton btnAdd = new JButton("Add Module");
        JButton btnEdit = new JButton("Edit / Assign Lecturer");
        JButton btnDelete = new JButton("Delete");
        JButton btnExit = new JButton("Exit");

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);
        topPanel.add(btnExit);
        add(topPanel, BorderLayout.NORTH);

        // --- Table Setup ---
        String[] columns = {"Code", "Name", "Assmnt 1", "Assmnt 2", "Assmnt 3", "Location", "Lecturer"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        refreshTable(); // Load initial data
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- ACTIONS ---

        // 1. DELETE BUTTON
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a module to delete.");
                return;
            }
            String code = (String) tableModel.getValueAt(row, 0);
            if (JOptionPane.showConfirmDialog(this, "Delete " + code + "?") == JOptionPane.YES_OPTION) {
                TextFileUtils.deleteModule(code);
                refreshTable();
            }
        });
        btnExit.addActionListener(e -> {
            this.dispose(); // Closes this window, returning to Dashboard
        });

        // 2. ADD BUTTON
        btnAdd.addActionListener(e -> showModuleDialog(null));

        // 3. EDIT BUTTON
        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                // Gather data from selected row
                TextFileUtils.Module m = new TextFileUtils.Module(
                        (String)table.getValueAt(row, 0), (String)table.getValueAt(row, 1),
                        (String)table.getValueAt(row, 2), (String)table.getValueAt(row, 3),
                        (String)table.getValueAt(row, 4), (String)table.getValueAt(row, 5),
                        (String)table.getValueAt(row, 6)
                );
                showModuleDialog(m);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a module to edit.");
            }
        });
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (TextFileUtils.Module m : TextFileUtils.getAllModules()) {
            tableModel.addRow(new Object[]{m.code, m.name, m.a1, m.a2, m.a3, m.loc, m.lec});
        }
    }

    private void showModuleDialog(TextFileUtils.Module existing) {
        JDialog d = new JDialog(this, (existing == null ? "Add Module" : "Edit Module"), true);
        d.setLayout(new GridLayout(9, 2, 5, 5)); // Added gaps for better look
        d.setSize(400, 500);
        d.setLocationRelativeTo(this);

        // Fields
        JTextField tCode = new JTextField(existing != null ? existing.code : "");
        if(existing != null) tCode.setEditable(false); // Code is unique ID, cannot change

        JTextField tName = new JTextField(existing != null ? existing.name : "");
        JTextField tA1 = new JTextField(existing != null ? existing.a1 : "Assignment (55%)");
        JTextField tA2 = new JTextField(existing != null ? existing.a2 : "Final Exam (45%)");
        JTextField tA3 = new JTextField(existing != null ? existing.a3 : "NULL");

        // Location Field (Target for error handling)
        JTextField tLoc = new JTextField(existing != null ? existing.loc : "");

        JTextField tLec = new JTextField(existing != null ? existing.lec : "NULL");
        JButton btnSave = new JButton("Save");

        // Add to Dialog
        d.add(new JLabel("Code:")); d.add(tCode);
        d.add(new JLabel("Name:")); d.add(tName);
        d.add(new JLabel("Ass 1:")); d.add(tA1);
        d.add(new JLabel("Ass 2:")); d.add(tA2);
        d.add(new JLabel("Ass 3:")); d.add(tA3);
        d.add(new JLabel("Location (e.g. A-1-4):")); d.add(tLoc);
        d.add(new JLabel("Lecturer:")); d.add(tLec);
        d.add(btnSave);

        // SAVE ACTION WITH ERROR HANDLING
        btnSave.addActionListener(ev -> {
            // --- 1. Basic Empty Checks ---
            if (tCode.getText().trim().isEmpty() || tName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(d, "Module Code and Name are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String val1 = tA1.getText().trim();
            String val2 = tA2.getText().trim();
            String percentRegex = ".+\\(\\d+%\\)$";

            if (!val1.equalsIgnoreCase("NULL") && !val1.matches(percentRegex)) {
                JOptionPane.showMessageDialog(d,
                        "Invalid Assessment 1 Format.\nMust look like: 'Assignment (50%)'",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!val2.equalsIgnoreCase("NULL") && !val2.matches(percentRegex)) {
                JOptionPane.showMessageDialog(d,
                        "Invalid Assessment 2 Format.\nMust look like: 'Final Exam (50%)'",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            // --- 2. LOCATION ERROR HANDLING (New Feature) ---
            String locInput = tLoc.getText().trim();

            // Check A: Is it empty?
            if (locInput.isEmpty()) {
                JOptionPane.showMessageDialog(d, "Location cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check B: Does it match the format 'Block-Level-Room' (e.g., A-1-4)?
            // Regex explanation: ^[A-Z] (One uppercase letter) - \\d+ (number) - \\d+ (number)$
            if (!locInput.matches("^[A-Z]-\\d+-\\d+$")) {
                JOptionPane.showMessageDialog(d,
                        "Invalid Location Format.\nCorrect format: Block-Floor-Room (e.g., A-1-4, B-6-6)",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // --- 3. Duplicate Code Check (Only when Adding new) ---
            List<TextFileUtils.Module> list = TextFileUtils.getAllModules();
            if (existing == null) {
                for (TextFileUtils.Module m : list) {
                    if (m.code.equals(tCode.getText().trim())) {
                        JOptionPane.showMessageDialog(d, "Module Code already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            // --- 4. Save Logic ---
            TextFileUtils.Module newMod = new TextFileUtils.Module(
                    tCode.getText().trim(), tName.getText().trim(), tA1.getText().trim(), tA2.getText().trim(),
                    tA3.getText().trim(), locInput, tLec.getText().trim()
            );

            if (existing == null) { // Add
                list.add(newMod);
            } else { // Edit
                for (int i=0; i<list.size(); i++) {
                    if (list.get(i).code.equals(existing.code)) {
                        list.set(i, newMod);
                        break;
                    }
                }
            }

            boolean saved = TextFileUtils.saveModules(list);
            if (saved) {
                refreshTable();
                d.dispose();
                JOptionPane.showMessageDialog(this, "Module Saved Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Error writing to file.", "System Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        d.setVisible(true);
    }
}
