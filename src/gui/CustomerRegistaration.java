/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import static gui.SplashWindow.logger;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.MySQL2;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

public class CustomerRegistaration extends javax.swing.JPanel {

    private JPopupMenu popup;
    HashMap<String, String> CustomerGenderMap = new HashMap<>();
    HashMap<String, String> CustomerLocationMap = new HashMap<>();

    public CustomerRegistaration() {
        initComponents();
        loadGender();
        loadLocaton();
        loadCustomer("mobile", "ASC", jTextField5.getText(), jTextField5.getText(), jTextField5.getText(), jTextField5.getText());
        setupPopupMenu();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.setDefaultRenderer(Object.class, renderer);
    }

    private void loadGender() {
        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `gender`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {

                vector.add(resultSet.getString("type"));
                CustomerGenderMap.put(resultSet.getString("type"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(comboBoxModel);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Customer Registration in loadGender", e);
        }
    }

    private void loadLocaton() {
        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `customer_location_type`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {

                vector.add(resultSet.getString("type"));
                CustomerLocationMap.put(resultSet.getString("type"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(comboBoxModel);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Customer Registration in loadLocaton", e);
        }
    }

    private void loadCustomer(String Column, String orderby, String Mobile, String Gender, String nic, String Location) {
        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `customer` INNER JOIN `gender` ON `customer`.`gender_id` = `gender`.`id`"
                    + "INNER JOIN `customer_location_type` ON `customer`.`customer_location_type_id` = `customer_location_type`.`id`"
                    + "WHERE `mobile` LIKE '" + Mobile + "%' OR `gender`.`type` LIKE '" + Gender + "%' OR `nic` LIKE '" + nic + "%' OR "
                    + "`customer_location_type`.`type` LIKE '" + Location + "%' ORDER BY `" + Column + "`" + orderby + "");

            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
            defaultTableModel.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("name"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("nic"));
                vector.add(resultSet.getString("gender.type"));
                vector.add(resultSet.getString("customer_location_type.type"));
                vector.add(resultSet.getString("point"));

                defaultTableModel.addRow(vector);

            }

        } catch (Exception e) {
//            e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Customer Registration in loadCustomer", e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setMaximumSize(new java.awt.Dimension(1150, 610));
        setMinimumSize(new java.awt.Dimension(1150, 610));
        setPreferredSize(new java.awt.Dimension(1150, 610));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("NAME");

        jTextField1.setBackground(new java.awt.Color(242, 242, 242));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("MOBILE");

        jTextField3.setBackground(new java.awt.Color(242, 242, 242));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("NIC");

        jTextField4.setBackground(new java.awt.Color(242, 242, 242));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("GENDER");

        jComboBox1.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("LOCATION");
        jLabel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 102), 2, true));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jComboBox2.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("ADD TO CUSTOMER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("UPDATE TO CUSTOMER");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/reset.png"))); // NOI18N
        jButton3.setText("CANCEL ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTextField5.setBackground(new java.awt.Color(242, 242, 242));
        jTextField5.setToolTipText("search for customers based on their mobile number, NIC, gender, and location.");
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("SORT BY :");

        jComboBox3.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT", "POINTS ASCENDING", "POINTS DESENDING" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("SEARCH");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, 0, 143, Short.MAX_VALUE)
                .addGap(110, 110, 110)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NAME", "MOBILE", "NIC", "GENDER", "LOCATION", "POINTS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/icons8-budget-40.png"))); // NOI18N
        jLabel14.setText("CUSTOMER MANAGEMENT  ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String name = jTextField1.getText();
        String mobile = jTextField3.getText();
        String nic = jTextField4.getText();
        String gender = String.valueOf(jComboBox1.getSelectedItem());
        String location = String.valueOf(jComboBox2.getSelectedItem());

        if (name.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please Enter Customer First Name", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Enter Customer First Name.");
        } else if (mobile.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please Enter Customer Mobile", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Enter Customer Mobile Number");
        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
//            JOptionPane.showMessageDialog(this, "Please Enter Customer Valid Mobile", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Please Enter Customer Valid Mobile Number");
        } else if (nic.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please Enter Customer National Identify Number", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Enter Customer National Identify Number");
        } else if (nic.length() >= 45) {
//            JOptionPane.showMessageDialog(this, "This NIC Number Is Invalid", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "This NIC Number Is Invalid");
        } else if (gender.equals("Select")) {
//            JOptionPane.showMessageDialog(this, "Please Select Customer gender", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Select Customer gender");
        } else if (location.equals("Select")) {
//            JOptionPane.showMessageDialog(this, "Please Select Customer location", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Select Customer location");
        } else {

            try {
                ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `customer` WHERE `mobile` = '" + mobile + "' AND `nic` = '" + nic + "'");

                if (resultSet.next()) {
//                    JOptionPane.showMessageDialog(this, "This Customer Is Already Added", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "This Customer Is Already Added");
                } else {
                    MySQL2.executeIUD("INSERT INTO `customer` (`mobile`,`name`,`nic`,`gender_id`,`customer_location_type_id`,`point`)"
                            + "VALUES('" + mobile + "','" + name + "','" + nic + "','" + CustomerGenderMap.get(gender) + "','" + CustomerLocationMap.get(location) + "','0')");
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Customer Added Successfully!");
                    loadCustomer("mobile", "ASC", jTextField5.getText(), jTextField5.getText(), jTextField5.getText(), jTextField5.getText());
                    reset();
                }

            } catch (Exception e) {
//                e.printStackTrace();
                logger.log(Level.WARNING, "Exception In Customer Registration in add customer", e);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        try {
            int row = jTable1.getSelectedRow();
            if (row == -1) {
                return;
            }

            // Right click event
            if (SwingUtilities.isRightMouseButton(evt)) {
                jTable1.setRowSelectionInterval(
                        jTable1.rowAtPoint(evt.getPoint()),
                        jTable1.rowAtPoint(evt.getPoint())
                );
                popup.show(evt.getComponent(), evt.getX(), evt.getY());
                return;
            }

            // Left click - set form fields
            jButton1.setEnabled(false);
            jTextField4.setEditable(false);

            String name = String.valueOf(jTable1.getValueAt(row, 0));
            jTextField1.setText(name);
            jTextField1.grabFocus();

            String mobile = String.valueOf(jTable1.getValueAt(row, 1));
            jTextField3.setText(mobile);
            jTextField3.setEditable(false);

            String nic = String.valueOf(jTable1.getValueAt(row, 2));
            jTextField4.setText(nic);

            String gender = String.valueOf(jTable1.getValueAt(row, 3));
            jComboBox1.setSelectedItem(gender);

            String location = String.valueOf(jTable1.getValueAt(row, 4));
            jComboBox2.setSelectedItem(location);

        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception In Customer Registration in jTable1MouseClicked", ex);
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Error processing mouse click: " + ex.getMessage());
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String name = jTextField1.getText();
        String mobile = jTextField3.getText();
        String nic = jTextField4.getText();
        String gender = String.valueOf(jComboBox1.getSelectedItem());
        String location = String.valueOf(jComboBox2.getSelectedItem());

        if (name.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please Enter Customer first name", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Enter Customer first name");
        } else if (mobile.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please Enter Customer Mobile", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Enter Customer Mobile");
        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
//            JOptionPane.showMessageDialog(this, "Please Enter Customer Valid Mobile", "Warning", JOptionPane.WARNING_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Please Enter Customer Valid Mobile");
        } else if (nic.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please Enter Customer National Identify Number", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Enter Customer National Identify Number");
        } else if (nic.length() >= 45) {
//            JOptionPane.showMessageDialog(this, "Please Customer Valid National Identify Number", "Inforamtion", JOptionPane.WARNING_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "This NIC Number Is Invalid");
        } else if (gender.equals("Select")) {
//            JOptionPane.showMessageDialog(this, "Please Select Customer gender", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Select Customer gender");
        } else if (location.equals("Select")) {
//            JOptionPane.showMessageDialog(this, "Please Select Customer location", "Inforamtion", JOptionPane.INFORMATION_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Select Customer location");
        } else {

            try {

                ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `customer` WHERE `nic` = '" + nic + "'");

                boolean canUpdate = false;

                if (resultSet.next()) {
                    if (resultSet.getString("mobile").equals(mobile)) {
                        canUpdate = true;
                    } else {

//                        JOptionPane.showMessageDialog(this, "mobile Number already added", "Warning", JOptionPane.WARNING_MESSAGE);
                        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "mobile Number already added");
                    }

                } else {

                    canUpdate = true;
                }

                if (canUpdate) {
                    MySQL2.executeIUD("UPDATE `customer` SET `name` = '" + name + "' , `gender_id`='" + CustomerGenderMap.get(gender) + "' ,`customer_location_type_id` = '" + CustomerLocationMap.get(location) + "'"
                            + "WHERE `mobile` = '" + mobile + "'");
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Customer Updated Successfully!");
                    loadCustomer("mobile", "ASC", jTextField5.getText(), jTextField5.getText(), jTextField5.getText(), jTextField5.getText());
//                    loadCustomers("first_name", "ASC", jTextField1.getText());
                    reset();
                }

            } catch (Exception e) {
//                e.printStackTrace();
                logger.log(Level.WARNING, "Exception In Customer Registration in update button", e);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked

        Frame CustomerRegistaration = null;

        CustomerLocation customerLocation = new CustomerLocation(CustomerRegistaration, true);
        customerLocation.setVisible(true);

        refreshLocationComboBox();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        try {
            int sort = jComboBox3.getSelectedIndex();

            if (sort == 0) {
                loadCustomer("mobile", "ASC", jTextField5.getText(), jTextField5.getText(), jTextField5.getText(), jTextField5.getText());
            } else if (sort == 1) {
                loadCustomer("point", "ASC", jTextField5.getText(), jTextField5.getText(), jTextField5.getText(), jTextField5.getText());
            } else if (sort == 2) {
                loadCustomer("point", "DESC", jTextField5.getText(), jTextField5.getText(), jTextField5.getText(), jTextField5.getText());
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Customer Registration in jTextField5KeyReleased", e);
        }
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        try {
            int sort = jComboBox3.getSelectedIndex();

            if (sort == 0) {
                loadCustomer("mobile", "ASC", jTextField5.getText(), jTextField5.getText(), jTextField5.getText(), jTextField5.getText());
            } else if (sort == 1) {
                loadCustomer("point", "ASC", jTextField5.getText(), jTextField5.getText(), jTextField5.getText(), jTextField5.getText());
            } else if (sort == 2) {
                loadCustomer("point", "DESC", jTextField5.getText(), jTextField5.getText(), jTextField5.getText(), jTextField5.getText());
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Customer Registration in jComboBox3ItemStateChanged", e);

        }
    }//GEN-LAST:event_jComboBox3ItemStateChanged
    private JFrame tipFrame = null;  // Class level variable
    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // Show tooltip when mouse enters the JLabel
        String tooltipText = "• To add or edit an customers's details, click the \"Add/Edit Customers\" button.\n\n"
                + "• To add an customers's Location, click on the \"Location\" label (highlighted with a red border).\n\n"
                + "• To update an customer's details, click on the respective customer's row in the table. "
                + "The customers's details will automatically populate the fields for editing.";
        showTooltip(tooltipText);
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // Hide tooltip when mouse exits the JLabel
        hideTooltip();
    }//GEN-LAST:event_jLabel2MouseExited
    private void showTooltip(String text) {
        if (tipFrame == null) {
            // Create a new tooltip frame
            tipFrame = new JFrame();
            tipFrame.setUndecorated(true); // No window decorations

            // Create a text area to display the content
            JTextArea textArea = new JTextArea(text);
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setBackground(new Color(255, 255, 220)); // Light yellow background
            textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Border for better visibility
            textArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font style and size

            // Add text area to a scroll pane for long text
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(300, 200)); // Fixed width and height for long text

            // Add scroll pane to the frame
            tipFrame.add(scrollPane);
            tipFrame.pack();

            // Position tooltip near the JLabel
            Point labelLocation = jLabel2.getLocationOnScreen();
            tipFrame.setLocation(labelLocation.x + jLabel2.getWidth() + 10, labelLocation.y);

            // Make the frame visible
            tipFrame.setVisible(true);
        }
    }

    private void hideTooltip() {
        // Dispose the tooltip frame if it exists
        if (tipFrame != null) {
            tipFrame.dispose();
            tipFrame = null;
        }
    }

    private void setupPopupMenu() {
        popup = new JPopupMenu();
        JMenuItem viewProducts = new JMenuItem("View Buy Products");
        viewProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = jTable1.getSelectedRow();
                if (row != -1) {
                    String customerMobile = String.valueOf(jTable1.getValueAt(row, 1));
                    showProductsDialog(customerMobile);
                }
            }
        });
        popup.add(viewProducts);
    }

    private void showProductsDialog(String customerMobile) {
        try {
            // Get the parent window for the dialog
            Window parentWindow = SwingUtilities.getWindowAncestor(this);
            JDialog productsDialog = new JDialog((Frame) parentWindow, "Purchased Products - Customer Mobile: " + customerMobile, true);
            productsDialog.setSize(1000, 600);
            productsDialog.setLocationRelativeTo(parentWindow);

            String[] columns = {"Invoice Date", "Invoice ID", "Product ID", "Product Name", "Brand Name", "Model Name", "Category Name", "Manufactured Date", "Expiration Date",
                "Unit Price", "Quantity", "Discount", "Total"};

            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            JTable productsTable = new JTable(model);

            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.CENTER);
            productsTable.setDefaultRenderer(Object.class, renderer);

            // Set column widths
            productsTable.getColumnModel().getColumn(0).setPreferredWidth(120); // Invoice Date
            productsTable.getColumnModel().getColumn(2).setPreferredWidth(150); // Product Name wider
            productsTable.getColumnModel().getColumn(10).setPreferredWidth(120); // Manufactured Date wider
            productsTable.getColumnModel().getColumn(11).setPreferredWidth(120); // Expiration Date wider
            productsTable.getColumnModel().getColumn(12).setPreferredWidth(120); // Invoice Date wider

            JScrollPane scrollPane = new JScrollPane(productsTable);

            // Load data from database
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try (
                    PreparedStatement pstmt = MySQL2.getConnection().prepareStatement(
                            "SELECT invoice.id, product.product_id, product.name AS product_name, brand.name, model.name, "
                            + "category.Category, invoice.discount, invoice_item.qty, invoice.paid_amount AS total, "
                            + "stock.sell_price, stock.mfd, stock.exp, invoice.date AS invoice_date "
                            + "FROM invoice "
                            + "INNER JOIN invoice_item ON invoice_item.invoice_id = invoice.id "
                            + "INNER JOIN stock ON stock.stock_id = invoice_item.stock_stock_id "
                            + "INNER JOIN product ON product.product_id = stock.product_product_id "
                            + "INNER JOIN brand ON brand.id = product.brand_id "
                            + "INNER JOIN model ON model.id = product.model_id "
                            + "INNER JOIN category ON category.id = product.Category_id "
                            + "WHERE invoice.customer_mobile = ? "
                            + "ORDER BY invoice.date DESC")) {

                pstmt.setString(1, customerMobile);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Object[] row = {
                            dateFormat.format(rs.getDate("invoice_date")),
                            rs.getString("invoice.id"),
                            rs.getString("product.product_id"),
                            rs.getString("product_name"),
                            rs.getString("brand.name"),
                            rs.getString("model.name"),
                            rs.getString("category.Category"),
                            dateFormat.format(rs.getDate("mfd")),
                            dateFormat.format(rs.getDate("exp")),
                            String.format("%.2f", rs.getDouble("sell_price")),
                            rs.getInt("invoice_item.qty"),
                            String.format("%.2f", rs.getDouble("invoice.discount")),
                            String.format("%.2f", rs.getDouble("total")),};
                        model.addRow(row);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    logger.log(Level.WARNING, "Exception In Customer Registration in showProductsDialog", ex);
                    JOptionPane.showMessageDialog(this, "Error loading purchased products data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                logger.log(Level.WARNING, "Exception In Customer Registration in showProductsDialog", ex);
                JOptionPane.showMessageDialog(this, "Error preparing or executing SQL statement: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }

            // Add close button
            JPanel buttonPanel = new JPanel();
//            JButton closeButton = new JButton("Close");
//            closeButton.addActionListener(e -> productsDialog.dispose());
//            buttonPanel.add(closeButton);

            productsDialog.setLayout(new BorderLayout());
            productsDialog.add(scrollPane, BorderLayout.CENTER);
            productsDialog.add(buttonPanel, BorderLayout.SOUTH);

            productsDialog.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Exception In Customer Registration in showProductsDialog", ex);
            JOptionPane.showMessageDialog(this, "Error showing products: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void reset() {
        jTextField1.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jTextField5.setText("");
        jTable1.clearSelection();
        jTextField1.grabFocus();
        jTextField3.setEditable(true);
        loadCustomer("mobile", "ASC", jTextField5.getText(), jTextField5.getText(), jTextField5.getText(), jTextField5.getText());
        jButton1.setEnabled(true);
        jTextField4.setEditable(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables

    private void refreshLocationComboBox() {
        jComboBox2.removeAllItems();  // Clear existing items
        try {
            ResultSet rs = MySQL2.executeSearch("SELECT * FROM `customer_location_type`");

            // Add each position to the JComboBox
            while (rs.next()) {
                jComboBox2.addItem(rs.getString("type"));
            }
        } catch (Exception e) {
//        e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Employee Management in refreshPositionComboBox", e);
        }
    }

}
