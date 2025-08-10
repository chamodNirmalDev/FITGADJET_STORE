package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BackupRestore extends javax.swing.JFrame {

    private JButton btnBackup, btnRestore, btnBrowse;
    private JTextField txtBackupPath, txtRestoreFile;
    private JTextArea txtLog;
    private JProgressBar progressBar;
    // Database connection details
    private final String DB_NAME = "fitgadjet_store_sad_db";
    private final String DB_USER = "root";
    private final String DB_PASS = "C@2003chamod@";
    private final String MYSQL_PATH = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\";

    public BackupRestore() {
        initComponents();
        setupGUI();
        setupActions();
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 746, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 486, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setupGUI() {
        setTitle("Database Backup & Restore - Simple Version");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top Panel - Backup Section
        JPanel topPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Backup Location"));

        txtBackupPath = new JTextField(System.getProperty("user.home") + "\\Desktop\\");
        btnBrowse = new JButton("Browse Folder");
        btnBackup = new JButton("CREATE BACKUP");
        btnBackup.setBackground(Color.GREEN);
        btnBackup.setForeground(Color.WHITE);

        topPanel.add(new JLabel("Backup will be saved to:"));
        topPanel.add(txtBackupPath);
        topPanel.add(btnBrowse);
        topPanel.add(btnBackup);

        // Middle Panel - Restore Section
        JPanel middlePanel = new JPanel(new GridLayout(3, 1, 5, 5));
        middlePanel.setBorder(BorderFactory.createTitledBorder("Restore from File"));

        txtRestoreFile = new JTextField();
        JButton btnBrowseFile = new JButton("Browse SQL File");
        btnRestore = new JButton("RESTORE DATABASE");
        btnRestore.setBackground(Color.RED);
        btnRestore.setForeground(Color.WHITE);

        middlePanel.add(txtRestoreFile);
        middlePanel.add(btnBrowseFile);
        middlePanel.add(btnRestore);

        // Bottom Panel - Log and Progress
        JPanel bottomPanel = new JPanel(new BorderLayout());
        txtLog = new JTextArea(10, 50);
        txtLog.setEditable(false);
        txtLog.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollPane = new JScrollPane(txtLog);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setString("Ready");

        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(progressBar, BorderLayout.SOUTH);

        // Add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Browse file action
        btnBrowseFile.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("SQL Files", "sql"));
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                txtRestoreFile.setText(fc.getSelectedFile().getAbsolutePath());
            }
        });
    }

    private void setupActions() {
        // Browse folder for backup
        btnBrowse.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                txtBackupPath.setText(fc.getSelectedFile().getAbsolutePath() + "\\");
            }
        });

        // Backup button
        btnBackup.addActionListener(e -> doBackup());

        // Restore button  
        btnRestore.addActionListener(e -> doRestore());
    }

    private void doBackup() {
        String backupPath = txtBackupPath.getText().trim();
        if (backupPath.isEmpty()) {
            showError("Please select backup location!");
            return;
        }

        // Disable buttons during process
        setButtonsEnabled(false);
        progressBar.setIndeterminate(true);
        progressBar.setString("Creating backup...");

        // Create backup in background thread
        new Thread(() -> {
            try {
                // Create filename with timestamp
                String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                String fileName = "backup_" + timestamp + ".sql";
                String fullPath = backupPath + fileName;

                log("Starting backup...");
                log("File: " + fileName);

                // Build mysqldump command
                String command = String.format(
                        "\"%smysqldump.exe\" -u %s -p%s --routines --triggers %s",
                        MYSQL_PATH, DB_USER, DB_PASS, DB_NAME
                );

                // Execute command
                Process process = Runtime.getRuntime().exec(command);

                // Save output to file
                try (FileWriter writer = new FileWriter(fullPath); BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line + "\n");
                    }
                }

                // Wait for process to finish
                int exitCode = process.waitFor();

                SwingUtilities.invokeLater(() -> {
                    if (exitCode == 0) {
                        File backupFile = new File(fullPath);
                        if (backupFile.exists() && backupFile.length() > 0) {
                            log("✓ Backup completed successfully!");
                            log("Size: " + (backupFile.length() / 1024) + " KB");
                            progressBar.setValue(100);
                            progressBar.setString("Backup completed!");
                            showSuccess("Backup created successfully!\n" + fileName);
                        } else {
                            log("✗ Backup file not created!");
                            progressBar.setString("Backup failed!");
                        }
                    } else {
                        log("✗ Backup failed! Exit code: " + exitCode);
                        progressBar.setString("Backup failed!");
                    }

                    progressBar.setIndeterminate(false);
                    setButtonsEnabled(true);
                });

            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    log("Error: " + ex.getMessage());
                    progressBar.setString("Error occurred!");
                    progressBar.setIndeterminate(false);
                    setButtonsEnabled(true);
                });
            }
        }).start();
    }

    private void doRestore() {
        String restoreFile = txtRestoreFile.getText().trim();
        if (restoreFile.isEmpty()) {
            showError("Please select a backup file!");
            return;
        }

        File file = new File(restoreFile);
        if (!file.exists()) {
            showError("Selected file does not exist!");
            return;
        }

        // Confirm restore
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure? This will replace all current data!",
                "Confirm Restore", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Disable buttons during process
        setButtonsEnabled(false);
        progressBar.setIndeterminate(true);
        progressBar.setString("Restoring database...");

        // Restore in background thread
        new Thread(() -> {
            try {
                log("Starting restore...");
                log("File: " + file.getName());

                // Build mysql command
                String command = String.format(
                        "\"%smysql.exe\" -u %s -p%s %s",
                        MYSQL_PATH, DB_USER, DB_PASS, DB_NAME
                );

                // Execute command
                Process process = Runtime.getRuntime().exec(command);

                // Send file content to mysql
                try (FileReader reader = new FileReader(file); PrintWriter writer = new PrintWriter(process.getOutputStream())) {

                    char[] buffer = new char[8192];
                    int bytesRead;
                    while ((bytesRead = reader.read(buffer)) != -1) {
                        writer.write(buffer, 0, bytesRead);
                    }
                }

                // Wait for process to finish
                int exitCode = process.waitFor();

                SwingUtilities.invokeLater(() -> {
                    if (exitCode == 0) {
                        log("✓ Restore completed successfully!");
                        progressBar.setValue(100);
                        progressBar.setString("Restore completed!");
                        showSuccess("Database restored successfully!");
                    } else {
                        log("✗ Restore failed! Exit code: " + exitCode);
                        progressBar.setString("Restore failed!");
                    }

                    progressBar.setIndeterminate(false);
                    setButtonsEnabled(true);
                });

            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    log("Error: " + ex.getMessage());
                    progressBar.setString("Error occurred!");
                    progressBar.setIndeterminate(false);
                    setButtonsEnabled(true);
                });
            }
        }).start();
    }

    private void log(String message) {
        SwingUtilities.invokeLater(() -> {
            String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
            txtLog.append(time + " - " + message + "\n");
            txtLog.setCaretPosition(txtLog.getDocument().getLength());
        });
    }

    private void setButtonsEnabled(boolean enabled) {
        btnBackup.setEnabled(enabled);
        btnRestore.setEnabled(enabled);
        btnBrowse.setEnabled(enabled);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
