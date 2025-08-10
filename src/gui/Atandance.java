package gui;

import static gui.SplashWindow.logger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.sql.ResultSet;
import java.util.logging.Level;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.MySQL2;
import raven.toast.Notifications;

public class Atandance extends javax.swing.JPanel {

    private JFrame tipFrame = null;

    public Atandance() {
        initComponents();
        checkEmployeeAccess();
        jPanel1.add(new MarkAttendance());
        jPanel2.add(new ViewAttendance());
        jPanel3.add(new AttendanceChart());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/icons8-employee-32.png"))); // NOI18N
        jLabel14.setText("EMPLOYEE ATTANDANCE ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("GUIDLINE");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(353, 353, 353)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Mark Attendance", jPanel1);

        jPanel2.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("View Attendance", jPanel2);

        jPanel3.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Chart", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited

        hideTooltip();
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered

        String tooltipText = "• To add or edit an employee's details, click the \"Add/Edit Employee\" button.\n\n"
                + "• To add an employee's position, click on the \"Position\" label (highlighted with a red border).\n\n"
                + "• To change an employee's status, click on the desired employee's row. This action enables the status combo box. "
                + "Select the new status and click the \"Update Account\" button to save the changes.\n\n"
                + "• To update an employee's details, click on the respective employee's row in the table. "
                + "The employee's details will automatically populate the fields for editing.\n\n"
                + "• To view, add, edit, or delete an employee's address, double-click the employee's row or right-click and select the \" +\n"
                + "\"View Address\" menu option from the context menu.\n\n"
                + "• The Employee can be searched by All Column.";

        showTooltip(tooltipText);
    }//GEN-LAST:event_jLabel2MouseEntered
    private void showTooltip(String text) {
        if (tipFrame == null) {
            tipFrame = new JFrame();
            tipFrame.setUndecorated(true);

            JTextArea textArea = new JTextArea(text);
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setBackground(new Color(255, 255, 220));
            textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            textArea.setFont(new Font("Arial", Font.PLAIN, 14));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(350, 400));

            tipFrame.add(scrollPane);
            tipFrame.pack();

            Point labelLocation = jLabel2.getLocationOnScreen();
            tipFrame.setLocation(labelLocation.x + jLabel2.getWidth() + 10, labelLocation.y);

            tipFrame.setVisible(true);
        }
    }

    private void hideTooltip() {
        if (tipFrame != null) {
            tipFrame.dispose();
            tipFrame = null;
        }
    }

    private void checkEmployeeAccess() {
        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT `employee_type_id` FROM `employee` WHERE `employee_id` = '" + Employee_SignIn.getUserId() + "'");

            if (resultSet.next()) {
                int employeeTypeId = resultSet.getInt("employee_type_id");

                if (employeeTypeId == 2 || employeeTypeId == 6) {
                    jTabbedPane1.setEnabledAt(0, true);
                    jTabbedPane1.setEnabledAt(1, true);
                    jTabbedPane1.setEnabledAt(2, true);
                } else {
                    // Normal employees
                    jTabbedPane1.setEnabledAt(0, true);
                    jTabbedPane1.setEnabledAt(1, false);
                    jTabbedPane1.setEnabledAt(2, false);
                }
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Attendance in checkEmployeeAccess method", e);
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    "Error checking employee access. Please contact administrator.");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
