package gui;

import static gui.SplashWindow.logger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import model.MySQL2;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import raven.toast.Notifications;

public class Employee_Management extends javax.swing.JPanel {

    private static HashMap<String, String> LoadGenderMap = new HashMap<>();
    private static HashMap<String, String> LoadPositionMap = new HashMap<>();
    private static HashMap<String, String> LoadStatuMap = new HashMap<>();
    private JFrame imageFrame;  // Class level variable
    private JFrame currentImageFrame; // Class-level variable to track the currently open image frame

    public Employee_Management() {
        initComponents();
        loadEmployee("employee_id", "ASC", jTextField7.getText());
        loadGender();
        loadPosition();
        Reset();
        loadStatus();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.setDefaultRenderer(Object.class, renderer);
        setupRightClickMenu();

    }

    private void setupRightClickMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        // "View Address" menu item
        JMenuItem viewAddressItem = new JMenuItem("View Address");
        JMenuItem cityAddItem = new JMenuItem("City Add");

        // Action listener for "View Address"
        viewAddressItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row's data when right-click menu option is selected
                int row = jTable1.getSelectedRow();
                if (row != -1) {
                    String userId = String.valueOf(jTable1.getValueAt(row, 0)); // Assuming user ID is in the 1st column
                    Frame Employee_Management = null;
                    JDialog addressView = new AddressView(Employee_Management, true, userId);
                    addressView.setVisible(true);
                }
            }
        });

        // Add "View Address" item to popup menu
        popupMenu.add(viewAddressItem);

        // Action listener for "City Add"
        cityAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open cityView JFrame
                CityView cityFrame = new CityView(); // Assuming cityView is your JFrame class name
                cityFrame.setVisible(true); // Show the JFrame
            }
        });

        // Add "City Add" item to popup menu
        popupMenu.add(cityAddItem);

        // Add mouse listener to display popup menu on right-click
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int row = jTable1.rowAtPoint(e.getPoint());
                    jTable1.setRowSelectionInterval(row, row);  // Select the row
                    popupMenu.show(e.getComponent(), e.getX(), e.getY()); // Show the menu
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int row = jTable1.rowAtPoint(e.getPoint());
                    jTable1.setRowSelectionInterval(row, row);  // Select the row
                    popupMenu.show(e.getComponent(), e.getX(), e.getY()); // Show the menu
                }
            }
        });
    }

    private void loadPosition() {
        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `employee_type`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("type"));
                LoadPositionMap.put(resultSet.getString("type"), resultSet.getString("id"));

            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);

        } catch (Exception e) {

            logger.log(Level.WARNING, "Exception In Employee Management in loadPosition", e);
        }
    }

    private void loadStatus() {
        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `status`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                LoadStatuMap.put(resultSet.getString("status"), resultSet.getString("id"));

            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox4.setModel(model);

        } catch (Exception e) {

            logger.log(Level.WARNING, "Exception In Employee Management in loadStatus", e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField7 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1150, 610));
        setMinimumSize(new java.awt.Dimension(1150, 610));
        setPreferredSize(new java.awt.Dimension(1150, 610));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("USER ID");

        jTextField1.setBackground(new java.awt.Color(242, 242, 242));
        jTextField1.setToolTipText("ENTER COMPANY ID");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("FIRST NAME");

        jTextField2.setBackground(new java.awt.Color(242, 242, 242));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("LAST NAME");

        jTextField3.setBackground(new java.awt.Color(242, 242, 242));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("NIC");

        jTextField4.setBackground(new java.awt.Color(242, 242, 242));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("MOBILE");

        jTextField5.setBackground(new java.awt.Color(242, 242, 242));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("GENDER");

        jComboBox1.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("POSITION");
        jLabel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 2, true));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jComboBox2.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("CREATE ACCOUNT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("UPDATE ACCOUNT");
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

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("PASSWORD");

        jTextField6.setBackground(new java.awt.Color(242, 242, 242));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Image");

        jTextField8.setBackground(new java.awt.Color(242, 242, 242));

        jButton4.setBackground(new java.awt.Color(0, 153, 153));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/show eye.png"))); // NOI18N
        jButton4.setText("+");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField5)
                            .addComponent(jTextField4)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jTextField6)))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.setMaximumSize(new java.awt.Dimension(1197, 633));
        jPanel2.setMinimumSize(new java.awt.Dimension(1197, 633));
        jPanel2.setPreferredSize(new java.awt.Dimension(1197, 633));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "USER ID", "FIRST NAME", "LAST NAME", "NIC", "MOBILE", "PASSWORD", "GENDER", "POSITION", "REGISTERD DATE", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTable1MouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(5, 5, 5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(5, 5, 5))
        );

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("SORT BY:");

        jComboBox3.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT", "MOBILE ASCENDING", "MOBILE DESENDING", "NIC ASCENDING", "NIC DESENDING", "REGISTERD DATE ASCENDING", "REGISTERD DESCENDING" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jTextField7.setBackground(new java.awt.Color(242, 242, 242));
        jTextField7.setToolTipText("Search Employee In All Column");
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("CHANGE STATUS :");

        jComboBox4.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("SEARCH :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(14, 14, 14)
                .addComponent(jComboBox3, 0, 1, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7)
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(jLabel12)
                .addGap(10, 10, 10)
                .addComponent(jComboBox4, 0, 265, Short.MAX_VALUE)
                .addGap(246, 246, 246))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/icons8-employee-32.png"))); // NOI18N
        jLabel14.setText("EMPLOYEE MANAGEMENT  ");

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
            .addGroup(jPanel4Layout.createSequentialGroup()
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 827, Short.MAX_VALUE))))
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
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Get input values
        String userId = jTextField1.getText().trim();
        String fname = jTextField2.getText().trim();
        String lname = jTextField3.getText().trim();
        String nic = jTextField4.getText().trim();
        String mobile = jTextField5.getText().trim();
        String password = jTextField6.getText().trim();
        String gender = String.valueOf(jComboBox1.getSelectedItem());
        String position = String.valueOf(jComboBox2.getSelectedItem());
        String imageName = jTextField8.getText().trim();  // Image file name

        jComboBox4.setEnabled(false);

        // Validate inputs
        if (userId.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please enter Employee's Company Id");
        } else if (fname.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Enter Employee's First Name");
        } else if (lname.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Enter Employee's Last Name");
        } else if (nic.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Enter Employee's National Id Number");
        } else if (mobile.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Enter Employee's Mobile Number");
        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Invalid Mobile Number! Please Enter Valid Mobile Number");
        } else if (password.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Enter Employee's Password");
        } else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$")) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Enter Strong Password");
        } else if (gender.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Select Employee's Gender");
        } else if (position.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Select Employee's Position");
        } else {
            // Validate image file extension
            String[] validExtensions = {".png", ".jpg", ".jpeg", ".webp", ".svg"};
            boolean isValidFile = false;

            for (String ext : validExtensions) {
                if (imageName.toLowerCase().endsWith(ext)) {
                    isValidFile = true;
                    break;
                }
            }

            if (!isValidFile) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Invalid image file. Accepted formats are .png, .jpg, .jpeg, .webp");
            } else {
                try {
                    // Define destination directory for the image
                    File sourceFile = new File(imageName);
                    File destDir = new File("src/EmployeeImages/");
                    if (!destDir.exists()) {
                        destDir.mkdirs();  // Create directory if it does not exist
                    }

                    // Copy the image file to the destination
                    File destFile = new File(destDir, sourceFile.getName());
                    Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    // Save relative path to database
                    String relativePath = "EmployeeImages/" + sourceFile.getName();

                    // Check if employee already exists
                    ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `employee` WHERE `employee_id` = '" + userId + "'");

                    if (resultSet.next()) {
                        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Employee already has an account");
                    } else {
                        // Insert new employee data into database
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                        MySQL2.executeIUD("INSERT INTO `employee` (`employee_id`, `password`, `f_name`, `l_name`, `nic`, `mobile`, `registerd_date`, `gender_id`, `employee_type_id`, `Status_id`, `images`) "
                                + "VALUES ('" + userId + "', '" + password + "', '" + fname + "', '" + lname + "', '" + nic + "', '" + mobile + "', '" + sdf.format(date) + "', '"
                                + LoadGenderMap.get(gender) + "', '" + LoadPositionMap.get(position) + "', '1', '" + relativePath + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Account created successfully");
                        loadEmployee("employee_id", "ASC", jTextField7.getText());  // Reload employee data
                        Reset();  // Reset the form fields
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Employee_Management.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Exception in Employee Management in create button", e);
                }
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Get basic information from form fields
        String userId = jTextField1.getText().trim();
        String fname = jTextField2.getText().trim();
        String lname = jTextField3.getText().trim();
        String nic = jTextField4.getText().trim();
        String mobile = jTextField5.getText().trim();
        String password = jTextField6.getText().trim();
        String gender = String.valueOf(jComboBox1.getSelectedItem());
        String position = String.valueOf(jComboBox2.getSelectedItem());
        String status = String.valueOf(jComboBox4.getSelectedItem());
        String imageName = jTextField8.getText().trim();

        // Validate input values
        if (userId.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Please enter Employee ID");
        } else if (fname.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Please enter employee's first name");
        } else if (lname.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Please enter employee's last name");
        } else if (nic.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Please enter employee's NIC number");
        } else if (mobile.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Please enter employee's mobile number");
        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Invalid mobile number! Please enter a valid number");
        } else if (password.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Please enter a strong password");
        } else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$")) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Password must be 8-10 characters with at least one letter, one number, and one special character");
        } else if (gender.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Please select employee's gender");
        } else if (position.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Please select employee's position");
        } else if (status.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Please select employee's status");
        } else {
            try {
                boolean update = false;
                String relativePath = null;

                // Handle image if provided
                if (!imageName.isEmpty()) {
                    // Validate file extension
                    String[] validExtensions = {".png", ".jpg", ".jpeg", ".webp", ".svg"};
                    boolean isValidFile = false;

                    for (String ext : validExtensions) {
                        if (imageName.toLowerCase().endsWith(ext)) {
                            isValidFile = true;
                            break;
                        }
                    }

                    if (!isValidFile) {
                        Notifications.getInstance().show(Notifications.Type.INFO,
                                Notifications.Location.TOP_CENTER,
                                "Invalid image file. Accepted formats: .png, .jpg, .jpeg, .webp, .svg");
                        return;
                    }

                    // Get project root and setup paths
                    Path projectRoot = Paths.get("").toAbsolutePath();
                    Path employeeImagesPath = projectRoot.resolve("src/EmployeeImages");

                    // Determine source path
                    Path sourcePath;
                    if (Paths.get(imageName).isAbsolute()) {
                        // If absolute path is provided
                        sourcePath = Paths.get(imageName);
                    } else {
                        // First try EmployeeImages folder
                        sourcePath = employeeImagesPath.resolve(Paths.get(imageName).getFileName());

                        // If not found, try as relative path from project root
                        if (!Files.exists(sourcePath)) {
                            sourcePath = projectRoot.resolve(imageName);
                        }
                    }

                    // Verify source file exists
                    if (!Files.exists(sourcePath)) {
                        Notifications.getInstance().show(Notifications.Type.ERROR,
                                Notifications.Location.TOP_CENTER,
                                "Image file not found at: " + sourcePath);
                        return;
                    }

                    // Create destination directory if needed
                    Files.createDirectories(employeeImagesPath);

                    // Copy file to EmployeeImages directory
                    String fileName = sourcePath.getFileName().toString();
                    Path destPath = employeeImagesPath.resolve(fileName);
                    Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);

                    // Set relative path for database
                    relativePath = "EmployeeImages/" + fileName;
                }

                // Check for existing employee with same NIC or mobile
                ResultSet resultSet = MySQL2.executeSearch(
                        "SELECT * FROM `employee` WHERE `mobile` = '" + mobile + "' AND `nic` = '" + nic + "'");

                if (resultSet.next()) {
                    if (!resultSet.getString("employee_id").equals(userId)) {
                        Notifications.getInstance().show(Notifications.Type.INFO,
                                Notifications.Location.TOP_CENTER,
                                "This mobile number or NIC is already in use.");
                    } else {
                        update = true;
                    }
                } else {
                    update = true;
                }

                // Update employee record if validation passes
                if (update) {
                    StringBuilder updateQuery = new StringBuilder();
                    updateQuery.append("UPDATE `employee` SET ")
                            .append("`f_name` = '").append(fname).append("',")
                            .append("`l_name` = '").append(lname).append("',")
                            .append("`nic` = '").append(nic).append("',")
                            .append("`mobile` = '").append(mobile).append("',")
                            .append("`password` = '").append(password).append("',")
                            .append("`gender_id` = '").append(LoadGenderMap.get(gender)).append("',")
                            .append("`employee_type_id` = '").append(LoadPositionMap.get(position)).append("',")
                            .append("`Status_id` = '").append(LoadStatuMap.get(status)).append("'");

                    // Add image path only if new image was uploaded
                    if (relativePath != null) {
                        updateQuery.append(",`images` = '").append(relativePath).append("'");
                    }

                    updateQuery.append(" WHERE `employee_id` = '").append(userId).append("'");

                    MySQL2.executeIUD(updateQuery.toString());

                    loadEmployee("employee_id", "ASC", jTextField7.getText());
                    Reset();

                    Notifications.getInstance().show(Notifications.Type.SUCCESS,
                            Notifications.Location.TOP_CENTER,
                            "Employee information updated successfully");
                }
            } catch (NoSuchFileException e) {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_CENTER,
                        "Image file not found: " + imageName);
                logger.log(Level.WARNING, "Employee_Management jButton2ActionPerformed Image file not found", e);
            } catch (IOException e) {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_CENTER,
                        "Error handling image file: " + e.getMessage());
                logger.log(Level.WARNING, "Employee_Management jButton2ActionPerformed Error handling image file", e);
            } catch (SQLException e) {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_CENTER,
                        "Database error: " + e.getMessage());
                logger.log(Level.WARNING, "Employee_Management jButton2ActionPerformed SQL error in Employee Management", e);
            } catch (Exception e) {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_CENTER,
                        "Unexpected error: " + e.getMessage());
                logger.log(Level.WARNING, "Employee_Management jButton2ActionPerformed Exception in Employee Management during update", e);
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed


    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int row = jTable1.getSelectedRow();
        if (row != -1) {
            displayEmployeeDetails(row);

            if (evt.getClickCount() == 2) {
                openAddressDialog(row);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked
    private void displayEmployeeDetails(int row) {
        jButton1.setEnabled(false);
        jTextField4.setEditable(false);

        // Populate text fields and combo boxes
        jTextField1.setText(String.valueOf(jTable1.getValueAt(row, 0)));
        jTextField1.setEditable(false);
        jTextField2.setText(String.valueOf(jTable1.getValueAt(row, 1)));
        jTextField3.setText(String.valueOf(jTable1.getValueAt(row, 2)));
        jTextField4.setText(String.valueOf(jTable1.getValueAt(row, 3)));
        jTextField5.setText(String.valueOf(jTable1.getValueAt(row, 4)));
        jTextField6.setText(String.valueOf(jTable1.getValueAt(row, 5)));
        jComboBox1.setSelectedItem(String.valueOf(jTable1.getValueAt(row, 6)));
        jComboBox2.setSelectedItem(String.valueOf(jTable1.getValueAt(row, 7)));
        jComboBox4.setSelectedItem(String.valueOf(jTable1.getValueAt(row, 9)));
        try {
            ResultSet rs = MySQL2.executeSearch("SELECT images FROM `employee` WHERE `employee_id` = '" + jTextField1.getText() + "'");

            if (rs.next()) {
                String image_path = String.valueOf(rs.getString("images"));
                jTextField8.setText(image_path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Employee_Management displayEmployeeDetails", e);
        }
        jComboBox4.setEnabled(true);
    }

    private void openAddressDialog(int row) {
        String userId = String.valueOf(jTable1.getValueAt(row, 0));
        JDialog addressView = new AddressView((Frame) SwingUtilities.getWindowAncestor(jTable1), true, userId);
        addressView.setVisible(true);
    }
    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        String orderColumn = "mobile";  // Default column
        String orderType = "ASC";       // Default order type

        int sort = jComboBox3.getSelectedIndex();

        switch (sort) {
            case 0: // Sort by mobile ASC
                orderColumn = "employee_id";
                orderType = "ASC";
                break;
            case 1: // Sort by mobile ASC
                orderColumn = "mobile";
                orderType = "ASC";
                break;
            case 2: // Sort by mobile DESC
                orderColumn = "mobile";
                orderType = "DESC";
                break;
            case 3: // Sort by NIC ASC
                orderColumn = "nic";
                orderType = "ASC";
                break;
            case 4: // Sort by NIC DESC
                orderColumn = "nic";
                orderType = "DESC";
                break;
            case 5: // Sort by registered_date ASC
                orderColumn = "registerd_date";
                orderType = "ASC";
                break;
            case 6: // Sort by registered_date DESC
                orderColumn = "registerd_date";
                orderType = "DESC";
                break;
        }

        loadEmployee(orderColumn, orderType, jTextField7.getText());
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased

        String text = jTextField7.getText();
        loadEmployee("employee_id", "ASC", text);
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        Frame Employee_Management = null;

        Position position = new Position(Employee_Management, true);
        position.setVisible(true);

        refreshPositionComboBox();
    }//GEN-LAST:event_jLabel9MouseClicked
    private JFrame tipFrame = null;  // Class level variable

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
// Show tooltip when mouse enters the JLabel
        // Show tooltip when mouse enters the JLabel
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

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // Hide tooltip when mouse exits the JLabel
        hideTooltip();

    }//GEN-LAST:event_jLabel2MouseExited

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Open a JFileChooser with a dark theme
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this); // 'this' refers to the parent JFrame or component

        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file's path
            String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            // Set the file path in jTextField1
            jTextField8.setText(selectedFilePath);

            // Notify the user
//            JOptionPane.showMessageDialog(this, "Selected File: " + selectedFilePath, "File Selected", JOptionPane.INFORMATION_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER,
                    "Selected File: " + selectedFilePath);
        } else {
            // Notify the user about cancellation
//            JOptionPane.showMessageDialog(this, "No file selected.", "File Selection Cancelled", JOptionPane.WARNING_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "No file selected.");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        int row = jTable1.rowAtPoint(evt.getPoint());
        if (row != -1) {
            showEmployeeImage(row);
        }
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseExited
        if (imageFrame != null) {
            imageFrame.dispose();
            imageFrame = null;
        }
    }//GEN-LAST:event_jTable1MouseExited

    private void showEmployeeImage(int row) {
        String employeeId = String.valueOf(jTable1.getValueAt(row, 0));

        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT images FROM employee WHERE employee_id = '" + employeeId + "'");
            if (resultSet.next()) {
                String imagePath = resultSet.getString("images");

                if (imagePath != null && !imagePath.isEmpty()) {
                    File imageFile = new File(System.getProperty("user.dir") + File.separator + "build"
                            + File.separator + "classes" + File.separator + imagePath);

                    if (imageFile.exists()) {
                        BufferedImage img = ImageIO.read(imageFile);
                        if (img != null) {
                            // Scale the image
                            Image scaledImage = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                            ImageIcon icon = new ImageIcon(scaledImage);

                            // Show the image in a popup
                            showImagePopup(icon);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "Employee_Management showEmployeeImage", ex);
        }
    }

    private void showImagePopup(ImageIcon icon) {
        if (imageFrame != null) {
            imageFrame.dispose();
        }

        imageFrame = new JFrame();
        imageFrame.setUndecorated(true);
        JLabel imageLabel = new JLabel(icon);
        imageFrame.add(imageLabel);
        imageFrame.pack();

        // Position frame near mouse pointer
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        imageFrame.setLocation(mouseLocation.x + 20, mouseLocation.y + 20);

        imageFrame.setVisible(true);
    }

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
            scrollPane.setPreferredSize(new Dimension(350, 400)); // Fixed width and height for long text

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables

    private void loadEmployee(String column, String orderby, String searchText) {

        try {
            String query = "SELECT * FROM employee "
                    + "INNER JOIN employee_type ON employee.employee_type_id = employee_type.id "
                    + "INNER JOIN gender ON employee.gender_id = gender.id "
                    + "INNER JOIN status ON employee.status_id = status.id "
                    + "WHERE employee.nic LIKE ? OR employee.mobile LIKE ? OR employee.employee_id LIKE ? "
                    + "OR employee.f_name LIKE ? OR employee.l_name LIKE ? OR employee.password LIKE ? "
                    + "OR gender.type LIKE ? OR employee_type.type LIKE ? "
                    + "OR status.status LIKE ? "
                    + "ORDER BY " + column + " " + orderby;

            PreparedStatement preparedStatement = MySQL2.getConnection().prepareStatement(query);
            String searchPattern = "%" + searchText + "%";

            // Bind the same search pattern to all fields
            preparedStatement.setString(1, searchPattern);
            preparedStatement.setString(2, searchPattern);
            preparedStatement.setString(3, searchPattern);
            preparedStatement.setString(4, searchPattern);
            preparedStatement.setString(5, searchPattern);
            preparedStatement.setString(6, searchPattern);
            preparedStatement.setString(7, searchPattern);
            preparedStatement.setString(8, searchPattern);
            preparedStatement.setString(9, searchPattern);

            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
            defaultTableModel.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("employee_id"));
                row.add(resultSet.getString("employee.f_name"));
                row.add(resultSet.getString("employee.l_name"));
                row.add(resultSet.getString("employee.nic"));
                row.add(resultSet.getString("employee.mobile"));
                row.add(resultSet.getString("employee.password"));
                row.add(resultSet.getString("gender.type"));
                row.add(resultSet.getString("employee_type.type"));
                row.add(resultSet.getString("employee.registerd_date"));
                row.add(resultSet.getString("status.status"));

                defaultTableModel.addRow(row);
            }

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Exception in Employee Management while loading employees", e);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception in Employee Management while loading employees", ex);
        }
    }

    private void loadGender() {
        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `gender`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("type"));
                LoadGenderMap.put(resultSet.getString("type"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);
        } catch (Exception e) {

//            e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Employee Management in loadGender", e);

        }
    }

    private void Reset() {

        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jTextField1.grabFocus();
        jTextField1.setEditable(true);
        jTable1.clearSelection();
        jTextField7.setText("");
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setEnabled(false);
        loadEmployee("employee_id", "ASC", jTextField7.getText());
        jButton1.setEnabled(true);
        jTextField4.setEditable(true);
        jTextField8.setText("");
    }

    private void refreshPositionComboBox() {
        jComboBox2.removeAllItems();  // Clear existing items
        try {
            ResultSet rs = MySQL2.executeSearch("SELECT * FROM `employee_type`");

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
