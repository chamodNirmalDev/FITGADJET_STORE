/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import static gui.SplashWindow.logger;
import java.util.logging.Level;
import model.MySQL2;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import raven.toast.Notifications;

/**
 *
 * @author Chamod
 */
public class MarkAttendance extends javax.swing.JPanel {

    /**
     * Creates new form MarkAttendance
     */
    public MarkAttendance() {
        initComponents();
        setField();
        checkAttendanceAndUpdateButtons();
        if (jTextField1.getText().isEmpty()) {
            jButton2.setEnabled(false);
        } else {
            jButton2.setEnabled(true);
        }

    }
    
    

    public void setField() {
        String userId = jTextField1.getText().trim(); // Trim removes leading/trailing spaces

        if (userId.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "User ID cannot be empty!");
            return;
        }

        String query = "SELECT * FROM `employee` "
                + "INNER JOIN `employee_type` ON `employee_type`.`id` = `employee`.`employee_type_id` "
                + "WHERE `employee_id` = ?";

        try (PreparedStatement ps = MySQL2.getConnection().prepareStatement(query)) {
            ps.setString(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    jLabel5.setText(rs.getString("f_name") + " " + rs.getString("l_name"));
                    jLabel11.setText(rs.getString("type"));
                    jLabel12.setText(rs.getString("mobile"));
                    jLabel13.setText(rs.getString("nic"));
                    jLabel14.setText(rs.getString("registerd_date"));
                } else {
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Employee not found");
                }
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception In Mark Attendance in setField", e);
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Error retrieving employee data!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1148, 507));
        setPreferredSize(new java.awt.Dimension(1148, 507));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/icons8-employee-32.png"))); // NOI18N
        jLabel1.setText("Mark Employee Attendance");

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("User Id :");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("IN TIME MARK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 153, 153));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("OUT TIME MARK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Employee' s Details :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Name");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Job Position :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("NIC :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Mobile :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Joined Date :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Name :");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Job Position ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Mobile ");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("NIC ");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Joined Date ");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/reset.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 584, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        setField();
        checkAttendanceAndUpdateButtons();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        setField();
        checkAttendanceAndUpdateButtons();
        if (jTextField1.getText().trim().isEmpty()) {
            jLabel5.setText("Name");
            jLabel11.setText("Job Position ");
            jLabel12.setText("Mobile ");
            jLabel13.setText("NIC ");
            jLabel14.setText("Joined Date ");
            // Reset buttons to their initial state
            jButton1.setEnabled(true);  // Enable IN TIME button
            jButton2.setEnabled(false); // Disable OUT TIME button
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void checkAttendanceAndUpdateButtons() {
        try {
            java.time.LocalDate today = java.time.LocalDate.now(); // Today's date
            String employeeId = jTextField1.getText(); // Employee ID from the text field

            // Query to check if IN TIME is already marked for the given employee on today's date
            String checkQuery = "SELECT * FROM `employee_attandance` WHERE `employee_employee_id` = ? AND `Date` = ?";

            try (PreparedStatement checkPs = MySQL2.getConnection().prepareStatement(checkQuery)) {
                checkPs.setString(1, employeeId);
                checkPs.setDate(2, java.sql.Date.valueOf(today));
                ResultSet rs = checkPs.executeQuery();

                // If IN TIME is already marked, disable IN TIME button and enable OUT TIME button
                if (rs.next()) {
                    // Check if IN TIME exists, and OUT TIME is NULL, then disable IN TIME and enable OUT TIME
                    if (rs.getTime("OutTime") == null) {
                        jButton1.setEnabled(false);  // Disable IN TIME button
                        jButton2.setEnabled(true);   // Enable OUT TIME button
                    } else {
                        // If both IN and OUT are already marked, disable both buttons
                        jButton1.setEnabled(false);
                        jButton2.setEnabled(false);
                    }
                } else {
                    // If no record is found, enable the IN TIME button
                    jButton1.setEnabled(true);  // Enable IN TIME button
                    jButton2.setEnabled(false); // Disable OUT TIME button
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Mark Attendance in checkAttendanceAndUpdateButtons", e);
            raven.toast.Notifications.getInstance().show(
                    raven.toast.Notifications.Type.ERROR,
                    raven.toast.Notifications.Location.TOP_CENTER,
                    "An error occurred while checking attendance!"
            );
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        java.time.LocalDate today = java.time.LocalDate.now(); // Today's date
        String employeeId = jTextField1.getText(); // Employee ID from the text field

        if (employeeId.isEmpty()) {
            raven.toast.Notifications.getInstance().show(
                    raven.toast.Notifications.Type.INFO,
                    raven.toast.Notifications.Location.TOP_CENTER,
                    "Please Type Employee's User ID"
            );
        } else {

            String checkQuery = "SELECT * FROM `employee_attandance` WHERE `employee_employee_id` = ? AND `Date` = ?";
            String checkStatusQuery = "SELECT `Status_id` FROM `employee` WHERE `employee_id` = ?";
            String insertQuery = "INSERT INTO `employee_attandance` (`mark`, `employee_employee_id`, `Date`, `InTime`) VALUES (?, ?, ?, ?)";

            try (Connection conn = MySQL2.getConnection()) {
                // Check if IN TIME is already marked
                try (PreparedStatement checkPs = conn.prepareStatement(checkQuery)) {
                    checkPs.setString(1, employeeId);
                    checkPs.setDate(2, java.sql.Date.valueOf(today));
                    try (ResultSet rs = checkPs.executeQuery()) {
                        if (rs.next()) {
                            // IN TIME already marked
                            jButton1.setEnabled(false);
                            jButton2.setEnabled(true);
                            return;
                        }
                    }
                }

                // Check employee status
                try (PreparedStatement statusPs = conn.prepareStatement(checkStatusQuery)) {
                    statusPs.setString(1, employeeId);
                    try (ResultSet rs = statusPs.executeQuery()) {
                        if (rs.next()) {
                            String statusId = rs.getString("Status_id");
                            if ("2".equals(statusId)) {
                                raven.toast.Notifications.getInstance().show(
                                        raven.toast.Notifications.Type.INFO,
                                        raven.toast.Notifications.Location.TOP_CENTER,
                                        "This Employee Is an Inactive User."
                                );
                                return;
                            }
                        }
                    }
                }

                // Mark IN TIME
                try (PreparedStatement insertPs = conn.prepareStatement(insertQuery)) {
                    insertPs.setInt(1, 1); // Mark as "IN" (1)
                    insertPs.setString(2, employeeId);
                    insertPs.setDate(3, java.sql.Date.valueOf(today));
                    insertPs.setTime(4, java.sql.Time.valueOf(java.time.LocalTime.now()));

                    int rowsAffected = insertPs.executeUpdate();
                    if (rowsAffected > 0) {
                        raven.toast.Notifications.getInstance().show(
                                raven.toast.Notifications.Type.SUCCESS,
                                raven.toast.Notifications.Location.TOP_CENTER,
                                "IN TIME successfully marked!"
                        );
                        reset();
                        jButton1.setEnabled(false);
//                        jButton2.setEnabled(true);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.log(Level.WARNING, "Exception In Mark Attendance in jButton1ActionPerformed", e);
                raven.toast.Notifications.getInstance().show(
                        raven.toast.Notifications.Type.ERROR,
                        raven.toast.Notifications.Location.TOP_CENTER,
                        "An error occurred while marking IN TIME: " + e.getMessage()
                );
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            java.time.LocalDate today = java.time.LocalDate.now(); // Today's date
            String employeeId = jTextField1.getText(); // Employee ID from the text field

            // Query to check if IN TIME is already marked for the given employee on today's date
            String checkQuery = "SELECT * FROM `employee_attandance` WHERE `employee_employee_id` = ? AND `Date` = ? AND `mark` = 1 AND `OutTime` IS NULL";

            try (PreparedStatement checkPs = MySQL2.getConnection().prepareStatement(checkQuery)) {
                checkPs.setString(1, employeeId);
                checkPs.setDate(2, java.sql.Date.valueOf(today));
                ResultSet rs = checkPs.executeQuery();

                // If IN TIME is marked and OUT TIME is NULL, proceed with OUT TIME update
                if (rs.next()) {
                    String query = "UPDATE `employee_attandance` SET `OutTime` = ? WHERE `employee_employee_id` = ? AND `Date` = ?";

                    try (PreparedStatement ps = MySQL2.getConnection().prepareStatement(query)) {

                        java.time.LocalTime currentTime = java.time.LocalTime.now();
                        java.time.LocalTime outTime = currentTime;
                        if (currentTime.isAfter(java.time.LocalTime.of(17, 0))) {
                            outTime = java.time.LocalTime.of(17, 0); // 5:00 PM
                        }
                        ps.setTime(1, java.sql.Time.valueOf(outTime));
                        ps.setString(2, employeeId); // Employee ID
                        ps.setDate(3, java.sql.Date.valueOf(today)); // Today's date

                        int rowsAffected = ps.executeUpdate();

                        if (rowsAffected > 0) {
                            raven.toast.Notifications.getInstance().show(
                                    raven.toast.Notifications.Type.SUCCESS,
                                    raven.toast.Notifications.Location.TOP_CENTER,
                                    "OUT TIME successfully marked!"
                            );
                            reset();

                            // Disable OUT TIME button after marking OUT TIME
                            jButton2.setEnabled(false); // Disable OUT TIME button
                        }
                    }
                } else {
                    // Show notification if no IN TIME was found or OUT TIME is already set
                    raven.toast.Notifications.getInstance().show(
                            raven.toast.Notifications.Type.ERROR,
                            raven.toast.Notifications.Location.TOP_CENTER,
                            "IN TIME is not marked or OUT TIME is already set!"
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Mark Attendance in jButton2ActionPerformed", e);
            raven.toast.Notifications.getInstance().show(
                    raven.toast.Notifications.Type.ERROR,
                    raven.toast.Notifications.Location.TOP_CENTER,
                    "An error occurred while marking OUT TIME!"
            );
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void setDefaultOutTimeForAll() {
        try {
            java.time.LocalDate today = java.time.LocalDate.now();
            java.time.LocalTime defaultOutTime = java.time.LocalTime.of(17, 0); // 5:00 PM

            String query = "UPDATE `employee_attandance` SET `OutTime` = ? "
                    + "WHERE `Date` = ? AND `OutTime` IS NULL";

            try (Connection conn = MySQL2.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setTime(1, java.sql.Time.valueOf(defaultOutTime));
                ps.setDate(2, java.sql.Date.valueOf(today));

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    logger.log(Level.INFO, "Set default OUT TIME (5:00 PM) for {0} employees", rowsAffected);
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error setting default OUT TIME", e);
        }
    }

    public static void setupAutomaticDefaultOutTime() {
        // Create a scheduled executor service
        java.util.concurrent.ScheduledExecutorService scheduler
                = java.util.concurrent.Executors.newScheduledThreadPool(1);

        // Create a runnable task
        Runnable task = () -> {
            // Get current time
            java.time.LocalTime now = java.time.LocalTime.now();

            // If current time is after work hours (e.g., after 6:00 PM), set default OUT TIME
            if (now.isAfter(java.time.LocalTime.of(18, 0))) {
                setDefaultOutTimeForAll();
            }
        };

        // Schedule the task to run every hour
        scheduler.scheduleAtFixedRate(task, 0, 1, java.util.concurrent.TimeUnit.HOURS);
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTextField1.setText("");
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jLabel5.setText("Name");
        jLabel11.setText("Job Position ");
        jLabel12.setText("Mobile ");
        jLabel13.setText("NIC ");
        jLabel14.setText("Joined Date ");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void reset() {
        jTextField1.setText("");
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jLabel5.setText("Name");
        jLabel11.setText("Job Position ");
        jLabel12.setText("Mobile ");
        jLabel13.setText("NIC ");
        jLabel14.setText("Joined Date ");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
