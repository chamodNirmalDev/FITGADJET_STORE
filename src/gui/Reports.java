package gui;

import static gui.SplashWindow.logger;
import java.sql.ResultSet;
import java.util.logging.Level;
import javax.swing.SwingUtilities;
import model.MySQL2;
import raven.toast.Notifications;

public class Reports extends javax.swing.JPanel {

    public Reports() {
        initComponents();
        checkEmployeeAccess();
        eReport.add(new EmployeeRports());
        cReport.add(new customerReport());
        Preports.add(new ProductRepots());
        stockReport.add(new StockReports());
        jPanel2.add(new SupplierReports());
        jPanel3.add(new incomeReport());
        jPanel4.add(new SalaryReport());

    }

    private void checkEmployeeAccess() {
        try {
            // Fetch the logged-in user's employee_type_id
            ResultSet resultSet = MySQL2.executeSearch("SELECT `employee_type_id` FROM `employee` WHERE `employee_id` = '" + Employee_SignIn.getUserId() + "'");

            if (resultSet.next()) {
                int employeeTypeId = resultSet.getInt("employee_type_id");

                if (employeeTypeId == 2 || employeeTypeId == 6) {//Admin finance manager
                    jTabbedPane1.setEnabledAt(0, true);
                    jTabbedPane1.setEnabledAt(1, true);
                    jTabbedPane1.setEnabledAt(2, true);
                    jTabbedPane1.setEnabledAt(3, true);
                    jTabbedPane1.setEnabledAt(4, true);
                    jTabbedPane1.setEnabledAt(5, true);
                    jTabbedPane1.setEnabledAt(6, true);
                } else if (employeeTypeId == 3) {//stock manager
                    jTabbedPane1.setEnabledAt(0, false);
                    jTabbedPane1.setEnabledAt(1, false);
                    jTabbedPane1.setEnabledAt(2, true);
                    jTabbedPane1.setEnabledAt(3, true);
                    jTabbedPane1.setEnabledAt(4, true);
                    jTabbedPane1.setEnabledAt(5, false);
                    jTabbedPane1.setEnabledAt(6, false);
                    SwingUtilities.invokeLater(() -> {
                        jTabbedPane1.setSelectedIndex(2);
                    });
                } else if (employeeTypeId == 4) {//cashiyer
                    jTabbedPane1.setEnabledAt(0, false);
                    jTabbedPane1.setEnabledAt(1, true);
                    jTabbedPane1.setEnabledAt(2, false);
                    jTabbedPane1.setEnabledAt(3, false);
                    jTabbedPane1.setEnabledAt(4, false);
                    jTabbedPane1.setEnabledAt(5, false);
                    jTabbedPane1.setEnabledAt(6, false);
                    SwingUtilities.invokeLater(() -> {
                        jTabbedPane1.setSelectedIndex(1);
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

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        eReport = new javax.swing.JPanel();
        cReport = new javax.swing.JPanel();
        Preports = new javax.swing.JPanel();
        stockReport = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(1150, 610));
        setPreferredSize(new java.awt.Dimension(1150, 610));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        eReport.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Employee Reports", eReport);

        cReport.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Customer Report", cReport);

        Preports.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Product Reports", Preports);

        stockReport.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Stock Reports", stockReport);

        jPanel2.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Supplier Reports", jPanel2);

        jPanel3.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Income Report", jPanel3);

        jPanel4.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Salary Report", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Preports;
    private javax.swing.JPanel cReport;
    private javax.swing.JPanel eReport;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel stockReport;
    // End of variables declaration//GEN-END:variables
}
