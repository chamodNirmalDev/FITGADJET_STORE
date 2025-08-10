/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import static gui.SplashWindow.logger;
import java.sql.ResultSet;
import java.util.logging.Level;
import javax.swing.SwingUtilities;
import model.MySQL2;
import raven.toast.Notifications;

/**
 *
 * @author Chamod
 */
public class OverView extends javax.swing.JPanel {

    /**
     * Creates new form OverView
     */
    public OverView() {
        initComponents();
        checkEmployeeAccess();
        jPanel1.add(new NewJPanel());
        jPanel2.add(new NewJPanel1());
        jPanel3.add(new Salary());

    }

    private void checkEmployeeAccess() {
        try {
            // Fetch the logged-in user's employee_type_id
            ResultSet resultSet = MySQL2.executeSearch("SELECT `employee_type_id` FROM `employee` WHERE `employee_id` = '" + Employee_SignIn.getUserId() + "'");

            if (resultSet.next()) {
                int employeeTypeId = resultSet.getInt("employee_type_id");

                if (employeeTypeId == 2) {//Admin 
                    jTabbedPane1.setEnabledAt(0, true);
                    jTabbedPane1.setEnabledAt(1, true);
                    jTabbedPane1.setEnabledAt(2, true);
                } else if (employeeTypeId == 6) {//finance manager
                    jTabbedPane1.setEnabledAt(0, true);
                    jTabbedPane1.setEnabledAt(1, false);
                    jTabbedPane1.setEnabledAt(2, false);
                    SwingUtilities.invokeLater(() -> {
                        jTabbedPane1.setSelectedIndex(000);
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Unexpected error during Reports in checkEmployeeAccess", e);
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    "Error checking employee access. Please contact administrator.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(1150, 610));
        setMinimumSize(new java.awt.Dimension(1150, 610));
        setPreferredSize(new java.awt.Dimension(1150, 610));

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 255));
        jTabbedPane1.setForeground(new java.awt.Color(204, 0, 0));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel3.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("salary", jPanel3);

        jPanel1.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("1", jPanel1);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("2", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
