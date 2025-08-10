package gui;

import static gui.SplashWindow.logger;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.logging.Level;
import model.MySQL2;
import raven.toast.Notifications;

public class Update_SalaryData extends javax.swing.JFrame {

    public Update_SalaryData() {
        initComponents();
        loadEmployeeTypes();
        setupEventHandlers();
    }

    private void loadEmployeeTypes() {
        try (Connection connection = MySQL2.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee_type ORDER BY type");
            ResultSet resultSet = statement.executeQuery();

            jComboBox1.removeAllItems();
            jComboBox1.addItem("Select Employee Type");

            while (resultSet.next()) {
                String employeeType = resultSet.getString("type");
                jComboBox1.addItem(employeeType);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception In Update Salary Data in loadEmployeeTypes", e);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Error loading employee types: " + e.getMessage());
        }
    }

    private void setupEventHandlers() {

        jComboBox1.addActionListener(e -> loadExistingSalaryData());

        jButton2.addActionListener(e -> updateSalaryData());
    }

    private void loadExistingSalaryData() {
        if (jComboBox1.getSelectedIndex() <= 0) {
            clearFields();
            return;
        }

        String selectedEmployeeType = jComboBox1.getSelectedItem().toString();

        try (Connection connection = MySQL2.getConnection()) {

            PreparedStatement employeeTypeStmt = connection.prepareStatement("SELECT id FROM employee_type WHERE type = ?");
            employeeTypeStmt.setString(1, selectedEmployeeType);
            ResultSet employeeTypeResult = employeeTypeStmt.executeQuery();

            if (employeeTypeResult.next()) {
                int employeeTypeId = employeeTypeResult.getInt("id");

                PreparedStatement basicSalaryStmt = connection.prepareStatement("SELECT price FROM besic_salary WHERE employee_type_id = ?");
                basicSalaryStmt.setInt(1, employeeTypeId);
                ResultSet basicSalaryResult = basicSalaryStmt.executeQuery();

                if (basicSalaryResult.next()) {
                    DecimalFormat df = new DecimalFormat("#,##0.00");
                    jTextField1.setText(df.format(basicSalaryResult.getDouble("price")));
                } else {
                    jTextField1.setText("");
                }

                PreparedStatement otRateStmt = connection.prepareStatement("SELECT amount FROM overtime WHERE overtime_id = 1");
                ResultSet otRateResult = otRateStmt.executeQuery();

                if (otRateResult.next()) {
                    DecimalFormat df = new DecimalFormat("#,##0.00");
                    jTextField2.setText(df.format(otRateResult.getDouble("amount")));
                } else {
                    jTextField2.setText("");
                }

                PreparedStatement allowanceStmt = connection.prepareStatement("SELECT allowance_type, amount FROM allowance");
                ResultSet allowanceResult = allowanceStmt.executeQuery();

                while (allowanceResult.next()) {
                    String allowanceType = allowanceResult.getString("allowance_type");
                    double amount = allowanceResult.getDouble("amount");
                    DecimalFormat df = new DecimalFormat("#,##0.00");

                    if ("Transport".equals(allowanceType)) {
                        jTextField3.setText(df.format(amount));
                    } else if ("Food".equals(allowanceType)) {
                        jTextField4.setText(df.format(amount));
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception In Update Salary Data in loadExistingSalaryData", e);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Error loading salary data: " + e.getMessage());
        }
    }

    private void clearFields() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
    }

    private boolean validateInputs() {
        if (jComboBox1.getSelectedIndex() <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Please select an employee type!");
            return false;
        }

        if (jTextField1.getText().trim().isEmpty()
                && jTextField2.getText().trim().isEmpty()
                && jTextField3.getText().trim().isEmpty()
                && jTextField4.getText().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Please fill at least one salary field!");
            return false;
        }

        if (!jTextField1.getText().trim().isEmpty() && !isValidNumber(jTextField1.getText().trim().replace(",", ""))) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Please enter a valid basic salary!");
            jTextField1.requestFocus();
            return false;
        }

        if (!jTextField2.getText().trim().isEmpty() && !isValidNumber(jTextField2.getText().trim().replace(",", ""))) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Please enter a valid OT rate!");
            jTextField2.requestFocus();
            return false;
        }

        if (!jTextField3.getText().trim().isEmpty() && !isValidNumber(jTextField3.getText().trim().replace(",", ""))) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Please enter a valid transport allowance!");
            jTextField3.requestFocus();
            return false;
        }

        if (!jTextField4.getText().trim().isEmpty() && !isValidNumber(jTextField4.getText().trim().replace(",", ""))) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Please enter a valid food allowance!");
            jTextField4.requestFocus();
            return false;
        }

        return true;
    }

    private boolean isValidNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void updateSalaryData() {
        if (!validateInputs()) {
            return;
        }

        String selectedEmployeeType = jComboBox1.getSelectedItem().toString();
        Connection connection = null;

        try {
            connection = MySQL2.getConnection();

            if (connection == null || connection.isClosed()) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Unable to establish database connection!");
                return;
            }

            connection.setAutoCommit(false);

            PreparedStatement employeeTypeStmt = connection.prepareStatement("SELECT id FROM employee_type WHERE type = ?");
            employeeTypeStmt.setString(1, selectedEmployeeType);
            ResultSet employeeTypeResult = employeeTypeStmt.executeQuery();

            if (!employeeTypeResult.next()) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Employee type not found in database!");
                return;
            }

            int employeeTypeId = employeeTypeResult.getInt("id");

            if (!jTextField1.getText().trim().isEmpty()) {
                double basicSalary = Double.parseDouble(jTextField1.getText().trim().replace(",", ""));

                PreparedStatement checkStmt = connection.prepareStatement("SELECT id FROM besic_salary WHERE employee_type_id = ?");
                checkStmt.setInt(1, employeeTypeId);
                ResultSet checkResult = checkStmt.executeQuery();

                if (checkResult.next()) {
                    PreparedStatement updateStmt = connection.prepareStatement("UPDATE besic_salary SET price = ? WHERE employee_type_id = ?");
                    updateStmt.setDouble(1, basicSalary);
                    updateStmt.setInt(2, employeeTypeId);
                    updateStmt.executeUpdate();
                    updateStmt.close();
                } else {
                    PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO besic_salary (price, employee_type_id) VALUES (?, ?)");
                    insertStmt.setDouble(1, basicSalary);
                    insertStmt.setInt(2, employeeTypeId);
                    insertStmt.executeUpdate();
                    insertStmt.close();
                }
                checkStmt.close();
                checkResult.close();
            }

            if (!jTextField2.getText().trim().isEmpty()) {
                double otRate = Double.parseDouble(jTextField2.getText().trim().replace(",", ""));

                PreparedStatement otStmt = connection.prepareStatement("UPDATE overtime SET amount = ? WHERE overtime_id = 1");
                otStmt.setDouble(1, otRate);
                otStmt.executeUpdate();
                otStmt.close();
            }

            if (!jTextField3.getText().trim().isEmpty()) {
                double transportAllowance = Double.parseDouble(jTextField3.getText().trim().replace(",", ""));

                PreparedStatement transportStmt = connection.prepareStatement("UPDATE allowance SET amount = ? WHERE allowance_type = 'Transport'");
                transportStmt.setDouble(1, transportAllowance);
                transportStmt.executeUpdate();
                transportStmt.close();
            }

            if (!jTextField4.getText().trim().isEmpty()) {
                double foodAllowance = Double.parseDouble(jTextField4.getText().trim().replace(",", ""));

                PreparedStatement foodStmt = connection.prepareStatement("UPDATE allowance SET amount = ? WHERE allowance_type = 'Food'");
                foodStmt.setDouble(1, foodAllowance);
                foodStmt.executeUpdate();
                foodStmt.close();
            }

            employeeTypeStmt.close();
            employeeTypeResult.close();

            connection.commit();
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Salary data updated successfully!");

            loadExistingSalaryData();

        } catch (SQLException e) {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                logger.log(Level.WARNING, "Exception In Update Salary Data ", e);
            }
            logger.log(Level.WARNING, "Exception In Update Salary Data", e);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Database SQL Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Please enter valid numeric values!");
        } catch (Exception e) {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                logger.log(Level.WARNING, "Exception In Update Salary Data", e);
            }
            logger.log(Level.WARNING, "Exception In Update Salary Data ", e);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Error updating salary data: " + e.getMessage());
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Exception In Update Salary Data", e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("UPDATE SALARY DATA");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Basic Salary :");

        jTextField1.setBackground(new java.awt.Color(242, 242, 242));
        jTextField1.setToolTipText("ENTER COMPANY ID");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("OT Rate :");

        jTextField2.setBackground(new java.awt.Color(242, 242, 242));
        jTextField2.setToolTipText("ENTER COMPANY ID");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Transport Allowance :");

        jTextField3.setBackground(new java.awt.Color(242, 242, 242));
        jTextField3.setToolTipText("ENTER COMPANY ID");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Food Allowance :");

        jTextField4.setBackground(new java.awt.Color(242, 242, 242));
        jTextField4.setToolTipText("ENTER COMPANY ID");

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("UPDATE ETC..");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField2)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4)))
                        .addGap(5, 5, 5)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
