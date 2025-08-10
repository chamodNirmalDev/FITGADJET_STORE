/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.*;
import model.AutoOutTimeManager;
import java.awt.event.WindowListener;

/**
 *
 * @author Chamod
 */
public class SplashWindow extends javax.swing.JFrame {

    public static Logger logger = Logger.getLogger("FITGADJETSTORE");

    private Timer statusCheckTimer;
    private Timer smoothProgressTimer;
    private boolean isDatabaseConnected = false;
    private boolean areLibrariesLoaded = false;
    private int targetProgress = 0;
    private int currentProgress = 0;

    /**
     * Creates new form SplashWindow
     */
    public SplashWindow() {
        initComponents();
        setWindowIcon();
        initSmoothProgress();

        try {
//            logger.addHandler(new FileHandler("FITGADJETSTORE.log ", true));
            FileHandler fileHandler = new FileHandler("FITGADJETSTORE.log", true);
            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "SplashWindow", e);
        }
        initializeAutoOutTime();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                // Other closing tasks...

                // Make sure to update missing OUT TIMEs
                AutoOutTimeManager.shutdownHook();

                // Continue with normal closing
                System.exit(0);
            }
        });

    }

    private void initializeAutoOutTime() {
        // Start the background monitoring of OUT TIME
        AutoOutTimeManager.startAutoOutTimeMonitor();

        // Register shutdown hook to ensure OUT TIME is set when app closes
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            AutoOutTimeManager.shutdownHook();
        }));
    }

    private void initSmoothProgress() {
        smoothProgressTimer = new Timer(50, e -> {
            if (currentProgress < targetProgress) {
                currentProgress = Math.min(currentProgress + 1, targetProgress);
                progressBar.setValue(currentProgress);
            }
        });
    }

    public void showSplash() {
        setVisible(true);
        startConnectionProcess();
    }

    private void startConnectionProcess() {
        progressBar.setValue(0);
        jLabel3.setText("Initializing...");
        smoothProgressTimer.start();

        Thread initThread = new Thread(() -> {
            try {
                // Initial delay
                Thread.sleep(1500);

                // Step 1: Loading libraries (0-30%)
                updateProgress("Loading libraries...", 0);
                Thread.sleep(1000);
                updateProgress("Loading system components...", 10);
                Thread.sleep(1000);
                updateProgress("Initializing modules...", 20);
                loadLibraries();
                areLibrariesLoaded = true;
                updateProgress("Libraries loaded successfully", 30);
                Thread.sleep(800);

                // Step 2: Database Connection (30-70%)
                updateProgress("Preparing database connection...", 30);
                Thread.sleep(1500);
                updateProgress("Connecting to database server...", 30);
                Thread.sleep(1000);
                updateProgress("Verifying database credentials...", 50);
                Thread.sleep(800);
                updateProgress("Establishing secure connection...", 60);
                connectToDatabase();
                isDatabaseConnected = true;
                updateProgress("Database connection established", 70);
                Thread.sleep(1500);

                // Step 3: Final Initialization (70-100%)
                updateProgress("Loading user interface...", 80);
                Thread.sleep(800);
                updateProgress("Preparing application resources...", 80);
                Thread.sleep(1500);
                updateProgress("Finalizing startup sequence...", 90);
                Thread.sleep(5000);
                updateProgress("Ready!", 100);
                Thread.sleep(500);

                // Launch main window
                SwingUtilities.invokeLater(() -> {
                    smoothProgressTimer.stop();
                    dispose();
                    Employee_SignIn em = new Employee_SignIn();
                    em.setVisible(true);
                });

            } catch (Exception e) {
                handleInitializationError(e);
                logger.log(Level.WARNING, "SplashWindow startConnectionProcess Method", e);
            }
        });

        initThread.start();
    }

    private void updateProgress(String message, int progress) {
        SwingUtilities.invokeLater(() -> {
            jLabel3.setText(message);
            targetProgress = progress;
        });
    }

    private void loadLibraries() throws Exception {
        // Add your library loading logic here
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Add other library loads as needed
        Thread.sleep(2000); // Extended loading time
    }

    private void connectToDatabase() throws Exception {
        // Replace these with your actual database credentials
        String url = "jdbc:mysql://localhost:3306/fitgadjet_store_sad_db";
        String username = "root";
        String password = "C@2003chamod@";

        Connection conn = DriverManager.getConnection(url, username, password);
        if (conn != null) {
            conn.close();
        } else {
            throw new Exception("Failed to establish database connection");
        }
        Thread.sleep(1500); // Extended connection time
    }

    private void handleInitializationError(Exception e) {
        SwingUtilities.invokeLater(() -> {
            smoothProgressTimer.stop();
            jLabel3.setText("Error: " + e.getMessage());
            progressBar.setValue(0);
            System.err.println("Initialization error: " + e.getMessage());
            e.printStackTrace();
            logger.log(Level.WARNING, "SplashWindow handleInitializationError Method", e);
        });
    }

    private void setWindowIcon() {
        try {
            java.net.URL iconURL = getClass().getResource("/resosurcs/now LOGO 48.png");
            if (iconURL != null) {
                ImageIcon icon = new ImageIcon(iconURL);
                setIconImage(icon.getImage());
            } else {
                System.err.println("Icon not found: /resosurcs/now LOGO 48.png");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Error loading icon: " + e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(575, 416));
        setMinimumSize(new java.awt.Dimension(575, 416));
        setUndecorated(true);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Loading...");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        progressBar.setBackground(new java.awt.Color(153, 255, 204));
        progressBar.setMaximumSize(new java.awt.Dimension(140, 4));
        progressBar.setPreferredSize(new java.awt.Dimension(140, 4));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 48)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/new LOGO 100.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 0, 0, new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("SimSun-ExtB", 1, 36)); // NOI18N
        jLabel2.setText("FITGADJET");

        jLabel4.setFont(new java.awt.Font("SimSun-ExtB", 1, 36)); // NOI18N
        jLabel4.setText("STORE");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();

        SwingUtilities.invokeLater(() -> {
            SplashWindow splash = new SplashWindow();
            splash.showSplash();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables
}
