/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import static gui.SplashWindow.logger;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import model.MySQL2;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author USER
 */
public class SalaryReport extends javax.swing.JPanel {

    private boolean isInitializing = false;

    public SalaryReport() {
        isInitializing = true;
        initComponents();
        setupDateChangeListeners();
        loadSalaryPayments();
        isInitializing = false;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();

        jButton2.setBackground(new java.awt.Color(0, 153, 153));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("PRINT REPORT");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE", "ID", "EMPLOYEE NAME", "POSITION", "WORK DAYS", "OVERTIME", "OVERTIME AMOUNT", "TRANSPORT ALLOWANCES", "FOOD ALLOWANCE", "EPF (8%)", "ETF (3%)", "SALARY (RS.)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/reset.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMonthChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(416, 416, 416)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jMonthChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private double parseOvertimeValue(String overtimeValue) {
        if (overtimeValue == null || overtimeValue.trim().isEmpty()) {
            return 0.0;
        }

        // If it's already a numeric value, parse it directly
        try {
            return Double.parseDouble(overtimeValue);
        } catch (NumberFormatException e) {
            // If it's in format like "0 Hours 0 Minutes", parse it
            try {
                String[] parts = overtimeValue.toLowerCase().split("\\s+");
                double totalHours = 0.0;

                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].contains("hour")) {
                        if (i > 0) {
                            totalHours += Double.parseDouble(parts[i - 1]);
                        }
                    } else if (parts[i].contains("minute")) {
                        if (i > 0) {
                            totalHours += Double.parseDouble(parts[i - 1]) / 60.0;
                        }
                    }
                }

                return totalHours;
            } catch (Exception ex) {
                logger.log(Level.WARNING, "Could not parse overtime value: " + overtimeValue, ex);
                return 0.0;
            }
        }
    }

    private void loadSalaryPayments() {
        try {
            // Load all salary payments without date filtering
            String query = "SELECT * "
                    + "FROM salary_payment sp "
                    + "INNER JOIN employee e ON sp.employee_id = e.employee_id "
                    + "INNER JOIN employee_type et ON e.employee_type_id = et.id "
                    + "INNER JOIN besic_salary bs ON bs.employee_type_id = et.id "
                    + "ORDER BY sp.payment_period DESC, sp.employee_id ASC";

            ResultSet rs = MySQL2.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(rs.getString("payment_period"));
                vector.add(rs.getString("employee_id"));
                vector.add(rs.getString("f_name") + " " + rs.getString("l_name"));
                vector.add(rs.getString("type"));
                vector.add(rs.getString("work_days"));

                // Get overtime as string first, then parse it
                String overtimeStr = rs.getString("overtime");
                vector.add(overtimeStr);

                // Parse overtime hours safely
                double overtimeHours = parseOvertimeValue(overtimeStr);
                double basicSalary = rs.getDouble("price");
                double overtimeRate = (basicSalary / 30 / 8) * 1.5; // Assuming 30 days, 8 hours per day, 1.5x rate
                double overtimeAmount = overtimeHours * overtimeRate;
                vector.add(String.format("%.2f", overtimeAmount));

                // Get allowances
                double transportAllowance = getAllowanceAmount("Transport");
                double foodAllowance = getAllowanceAmount("Food");

                vector.add(String.format("%.2f", transportAllowance));
                vector.add(String.format("%.2f", foodAllowance));

                // Calculate gross salary
                double grossSalary = basicSalary + overtimeAmount + transportAllowance + foodAllowance;

                // Calculate deductions
                double epf = grossSalary * 0.08; // 8%
                double etf = grossSalary * 0.03; // 3%

                vector.add(String.format("%.2f", epf));
                vector.add(String.format("%.2f", etf));

                // Net salary
                double netSalary = grossSalary - epf - etf;
                vector.add(String.format("%.2f", netSalary));

                model.addRow(vector);
            }

        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception in SalaryReport loadSalaryPayments", e);
            e.printStackTrace(); // Add this for debugging
        }
    }

    private void filterSalaryPayments() {
        try {
            // Get selected year and month for filtering
            int selectedYear = jYearChooser1.getYear();
            int selectedMonth = jMonthChooser1.getMonth() + 1;
            String monthName = getMonthName(selectedMonth);
            String paymentPeriod = monthName + " " + selectedYear;

            String query = "SELECT * "
                    + "FROM salary_payment sp "
                    + "INNER JOIN employee e ON sp.employee_id = e.employee_id "
                    + "INNER JOIN employee_type et ON e.employee_type_id = et.id "
                    + "INNER JOIN besic_salary bs ON bs.employee_type_id = et.id "
                    + "WHERE sp.payment_period = '" + paymentPeriod + "' "
                    + "ORDER BY sp.employee_id ASC";

            ResultSet rs = MySQL2.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(rs.getString("payment_period"));
                vector.add(rs.getString("employee_id"));
                vector.add(rs.getString("f_name") + " " + rs.getString("l_name"));
                vector.add(rs.getString("type"));
                vector.add(rs.getString("work_days"));

                // Get overtime as string first, then parse it
                String overtimeStr = rs.getString("overtime");
                vector.add(overtimeStr);

                // Parse overtime hours safely
                double overtimeHours = parseOvertimeValue(overtimeStr);
                double basicSalary = rs.getDouble("price");
                double overtimeRate = (basicSalary / 30 / 8) * 1.5; // Assuming 30 days, 8 hours per day, 1.5x rate
                double overtimeAmount = overtimeHours * overtimeRate;
                vector.add(String.format("%.2f", overtimeAmount));

                // Get allowances
                double transportAllowance = getAllowanceAmount("Transport");
                double foodAllowance = getAllowanceAmount("Food");

                vector.add(String.format("%.2f", transportAllowance));
                vector.add(String.format("%.2f", foodAllowance));

                // Calculate gross salary
                double grossSalary = basicSalary + overtimeAmount + transportAllowance + foodAllowance;

                // Calculate deductions
                double epf = grossSalary * 0.08; // 8%
                double etf = grossSalary * 0.03; // 3%

                vector.add(String.format("%.2f", epf));
                vector.add(String.format("%.2f", etf));

                // Net salary
                double netSalary = grossSalary - epf - etf;
                vector.add(String.format("%.2f", netSalary));

                model.addRow(vector);
            }

        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception in SalaryReport filterSalaryPayments", e);
            e.printStackTrace();
        }
    }

    private double getAllowanceAmount(String allowanceType) {
        try {

            String safeQuery = "SELECT amount FROM allowance "
                    + "WHERE allowance_type = '" + allowanceType + "'";

            ResultSet rs = MySQL2.executeSearch(safeQuery);
            if (rs.next()) {
                return rs.getDouble("amount");
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception in getAllowanceAmount", e);
        }
        return 0.0;
    }

// Helper method to convert month number to month name
    private String getMonthName(int month) {
        String[] monthNames = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };

        if (month >= 1 && month <= 12) {
            return monthNames[month - 1];
        }
        return "January"; // Default fallback
    }

// Add these event listeners in your initComponents() method or after it
    private void setupDateChangeListeners() {
        // Add PropertyChangeListener for year chooser
        jYearChooser1.addPropertyChangeListener("year", new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if (!isInitializing) {
                    filterSalaryPayments();
                }
            }
        });

        // Add PropertyChangeListener for month chooser
        jMonthChooser1.addPropertyChangeListener("month", new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if (!isInitializing) {
                    filterSalaryPayments();
                }
            }
        });
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            InputStream s = getClass().getClassLoader().getResourceAsStream("Report/SalaryR.jasper");

            HashMap<String, Object> parameters = new HashMap<>();
            // Get year and month from choosers and create dates
            Calendar cal = Calendar.getInstance();

            // Check and set Parameter4 (start of month)
            try {
                int year = jYearChooser1.getYear();
                int month = jMonthChooser1.getMonth(); // 0-based (January = 0)

                if (year > 0 && month >= 0) {
                    cal.set(year, month, 1); // First day of the month
                    parameters.put("Parameter1", new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
                } else {
                    parameters.put("Parameter1", "2024-10-01");  // Default date
                }
            } catch (Exception ex) {
                parameters.put("Parameter1", "2024-10-01");  // Default date
            }

            // Check and set Parameter5 (end of month)
            try {
                int year = jYearChooser1.getYear();
                int month = jMonthChooser1.getMonth(); // 0-based (January = 0)

                if (year > 0 && month >= 0) {
                    cal.set(year, month, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // Last day of the month
                    parameters.put("Parameter2", new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
                } else {
                    parameters.put("Parameter2", "2024-10-31");  // Default date
                }
            } catch (Exception ex) {
                parameters.put("Parameter2", "2024-10-31");  // Default date
            }

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(s, parameters, dataSource);

            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            //            e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Product Report in print button", e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        isInitializing = true;

        jYearChooser1.setYear(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR));
        jMonthChooser1.setMonth(java.util.Calendar.getInstance().get(java.util.Calendar.MONTH));

        isInitializing = false;
        // Reload all data
        loadSalaryPayments();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables
}
