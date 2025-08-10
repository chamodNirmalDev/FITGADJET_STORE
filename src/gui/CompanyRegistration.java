/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import static gui.SplashWindow.logger;
import java.sql.PreparedStatement;
import model.MySQL2;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CompanyRegistration extends javax.swing.JFrame {

    HashMap<String, String> LoadLocationMap = new HashMap<>();
    HashMap<String, String> LoadTypeMap = new HashMap<>();

    private static Supplier sr;

    public static void setSupplier(Supplier sr) {
        CompanyRegistration.sr = sr;
    }

    public CompanyRegistration() {

        initComponents();
        loadCompanies("id", "ASC", jTextField3.getText());
        loadLocation();
        loadTypes();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.setDefaultRenderer(Object.class, renderer);

    }

    private void loadCompanies(String column, String orderby, String text) {
        try {
            // Base query with JOINs
            String query = "SELECT company.id AS company_id, company.name, company.Hotline_number, "
                    + "company_location.location, company_type.type "
                    + "FROM company "
                    + "INNER JOIN company_location ON company.company_location_id = company_location.id "
                    + "INNER JOIN company_type ON company.company_type_id = company_type.id";

            // Dynamic filtering for multiple columns
            if (text != null && !text.isEmpty()) {
                query += " WHERE company.name LIKE ? OR company.Hotline_number LIKE ? OR company.id LIKE ?";
            }

            // Add ordering condition
            if (orderby != null && !orderby.isEmpty() && column != null && !column.isEmpty()) {
                query += " ORDER BY company." + column + " " + orderby;
            }

            PreparedStatement statement = MySQL2.getConnection().prepareStatement(query);

            // Set parameters for the `LIKE` conditions
            if (text != null && !text.isEmpty()) {
                String searchPattern = "%" + text + "%";
                statement.setString(1, searchPattern); // For `name`
                statement.setString(2, searchPattern); // For `Hotline_number`
                statement.setString(3, searchPattern); // For `id`
            }

            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            // Populate the table with data
            while (resultSet.next()) {
                Vector<Object> vector = new Vector<>();
                vector.add(resultSet.getString("company_id")); // Qualified alias for `id`
                vector.add(resultSet.getString("name"));
                vector.add(resultSet.getString("Hotline_number"));
                vector.add(resultSet.getString("location"));
                vector.add(resultSet.getString("type"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            // Log exception without printing stack trace
            logger.log(Level.WARNING, "Exception In Company Registration in loadCompanies", e);
        }
    }

    private void loadLocation() {

        try {

            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `company_location`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("location"));
                LoadLocationMap.put(resultSet.getString("location"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Company Registration in loadLocation", e);
        }
    }

    private void loadTypes() {

        try {

            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `company_type`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("type"));
                LoadTypeMap.put(resultSet.getString("type"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Company Registration in loadType", e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Company Name");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Company Registration");
        setAlwaysOnTop(true);
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Company Name");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Hotline");

        jTextField1.setBackground(new java.awt.Color(242, 242, 242));
        jTextField1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTextField1.setToolTipText("Company Name Here");

        jTextField2.setBackground(new java.awt.Color(242, 242, 242));
        jTextField2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 0, 51));
        jButton2.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Company Name", "Hotline", "Location", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton3.setBackground(new java.awt.Color(242, 242, 242));
        jButton3.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/reset.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Location");

        jComboBox1.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Type");

        jComboBox2.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField3.setBackground(new java.awt.Color(242, 242, 242));
        jTextField3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTextField3.setToolTipText("SEARCH BAR");
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Search By:");

        jButton4.setBackground(new java.awt.Color(255, 153, 153));
        jButton4.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jButton4.setText("Remove");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jButton3)
                                .addGap(15, 15, 15)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String name = jTextField1.getText();
        String hotline = jTextField2.getText();
        String location = String.valueOf(jComboBox1.getSelectedItem());
        String type = String.valueOf(jComboBox2.getSelectedItem());

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter company name", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (hotline.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter company hotline", "Information", JOptionPane.INFORMATION_MESSAGE);

        } else if (!hotline.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid hotline number", "Information", JOptionPane.INFORMATION_MESSAGE);

        } else if (location.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Selcet Company Location ", "Information", JOptionPane.INFORMATION_MESSAGE);

        } else if (type.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Selcet Company Type ", "Information", JOptionPane.INFORMATION_MESSAGE);

        } else {

            try {

                ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `company` WHERE `name`= '" + name + "' OR `Hotline_number` = '" + hotline + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Company name or hotine already used", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    MySQL2.executeIUD("INSERT INTO `company`(`name`,`Hotline_number`,`company_type_id`,`company_location_id`) "
                            + "VALUES('" + name + "','" + hotline + "','" + LoadTypeMap.get(type) + "','" + LoadLocationMap.get(location) + "')");
                    loadCompanies("id", "ASC", jTextField3.getText());
                    reset();
                }
            } catch (Exception e) {
//                e.printStackTrace();
                logger.log(Level.WARNING, "Exception In Company Registration in add button", e);
            }

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int row = jTable1.getSelectedRow();

        jTextField1.setText(String.valueOf(jTable1.getValueAt(row, 1)));
        jTextField2.setText(String.valueOf(jTable1.getValueAt(row, 2)));
        jComboBox1.setSelectedItem(String.valueOf(jTable1.getValueAt(row, 3)));
        jComboBox2.setSelectedItem(String.valueOf(jTable1.getValueAt(row, 4)));

        jButton1.setEnabled(false);

        if (evt.getClickCount() == 2) {

            if (sr != null) {
                sr.getjTextField5().setText(String.valueOf(jTable1.getValueAt(row, 1)));
                this.dispose();
                sr.mobileGrabFocus();
                sr.setCompanyId(String.valueOf(jTable1.getValueAt(row, 0)));
            } else {
                JOptionPane.showMessageDialog(this, "sr object is null", "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int row = jTable1.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {

            String name = jTextField1.getText();
            String hotline = jTextField2.getText();
            String location = String.valueOf(jComboBox1.getSelectedItem());
            String type = String.valueOf(jComboBox2.getSelectedItem());

            String selectedId = String.valueOf(jTable1.getValueAt(row, 0));

            String selectedName = String.valueOf(jTable1.getValueAt(row, 1));
            String selectedHotline = String.valueOf(jTable1.getValueAt(row, 2));
            String selectedlocation = String.valueOf(jTable1.getValueAt(row, 3));
            String selectedType = String.valueOf(jTable1.getValueAt(row, 4));

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter company name", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else if (hotline.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter company hotline", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else if (!hotline.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$")) {
                JOptionPane.showMessageDialog(this, "Please enter valid hotline number", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (location.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select Location", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else if (type.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select Type", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else if (selectedName.equals(name) && selectedHotline.equals(hotline) && selectedlocation.equals(location) && selectedType.equals(type)) {
                JOptionPane.showMessageDialog(this, "Please change name or hotline number to update", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {

                try {

                    ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `company` WHERE (`name`= '" + name + "' OR `Hotline_number` = '" + hotline + "') AND `id` != '" + selectedId + "'");

                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(this, "Company name or hotine already used", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {

                        MySQL2.executeIUD("UPDATE `company` SET `name` = '" + name + "' , `Hotline_number` = '" + hotline + "',"
                                + "`company_type_id` = '" + LoadTypeMap.get(type) + "',`company_location_id` = '" + LoadLocationMap.get(location) + "' WHERE `id` = '" + selectedId + "'");
                        loadCompanies("id", "ASC", jTextField3.getText());
                        reset();
                    }
                } catch (Exception e) {
//                    e.printStackTrace();
                    logger.log(Level.WARNING, "Exception In Company Registration in update button", e);
                }

            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String company = jTextField1.getText();
        String hotline = jTextField2.getText();
        String location = String.valueOf(jComboBox1.getSelectedItem());
        String type = String.valueOf(jComboBox2.getSelectedItem());
//        String company = jTextField5.getText();

        if (company.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Company Name", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (hotline.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Hotline Number", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (location.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Location", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (type.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Type", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                // Check if the category is linked with any products
                ResultSet resultSet = MySQL2.executeSearch("SELECT COUNT(*) AS count FROM `supplier` WHERE `company_id` = (SELECT id FROM `company` WHERE `name` = '" + company + "')");

                if (resultSet.next() && resultSet.getInt("count") > 0) {
                    JOptionPane.showMessageDialog(this, "Cannot delete Company because it is associated with Supplier.", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Safe to delete
                    MySQL2.executeIUD("DELETE FROM `company` WHERE `name` = '" + company + "'");
                    loadCompanies("id", "ASC", jTextField3.getText());
                    reset();
                }

            } catch (Exception e) {
//                ex.printStackTrace();
                logger.log(Level.WARNING, "Exception In Company Registration in remove button", e);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        loadCompanies("id", "ASC", jTextField3.getText());
    }//GEN-LAST:event_jTextField3KeyReleased

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTextField1.setText("");
        jTextField2.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jTextField1.grabFocus();
        jButton1.setEnabled(true);
        jTable1.clearSelection();
        loadCompanies("id", "ASC", jTextField3.getText());
    }
}
