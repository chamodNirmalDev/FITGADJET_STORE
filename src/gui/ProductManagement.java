/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import static gui.SplashWindow.logger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import model.MySQL2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

public class ProductManagement extends javax.swing.JPanel {

    HashMap<String, Integer> LoadCategoryMap = new HashMap<>();
    HashMap<String, Integer> LoadBrandMap = new HashMap<>();
    HashMap<String, Integer> LoadModelMap = new HashMap<>();

    private JFrame imageFrame = null;
    private JFrame currentImageFrame;

    public ProductManagement() {
        initComponents();
        loadCategory();
        loadModel();
        loadBrand();
        loadProduct("product_id", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.setDefaultRenderer(Object.class, renderer);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        jLabel8.setText("jLabel8");

        jRadioButton1.setText("jRadioButton1");

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1150, 610));
        setMinimumSize(new java.awt.Dimension(1150, 610));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/icons8-product-40.png"))); // NOI18N
        jLabel1.setText("PRODUCT MANAGEMENT  ");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("GUIDLINE");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 0, 0))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("PRODUCT ID");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("CATEGORY");
        jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 2, true));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("MODEL");
        jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 2, true));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("NAME");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("BRAND");
        jLabel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 2, true));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jTextField1.setBackground(new java.awt.Color(242, 242, 242));

        jTextField2.setBackground(new java.awt.Color(242, 242, 242));

        jComboBox1.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("ADD TO PRODUCT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 51, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("UPDATE TO PRODUCT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/reset.png"))); // NOI18N
        jButton3.setText("CLEAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("IMAGE");

        jTextField4.setBackground(new java.awt.Color(242, 242, 242));

        jButton5.setBackground(new java.awt.Color(0, 153, 153));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setText("Choose");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))))
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Id", "Category", "Brand", "Model", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("SORT BY :");

        jComboBox4.setBackground(new java.awt.Color(242, 242, 242));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PRODUCT ID ASENDING", "PRODUCT ID DESENDING", "PRODUCT NAME ASCENDING", "CATEGORY ASENDING", "BRAND ASENDING", "MODEL ASENDING" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        jTextField3.setBackground(new java.awt.Color(242, 242, 242));
        jTextField3.setToolTipText("Search By Product Id , Name , Category , Model , Brand");
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("SEARCH :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel7)
                .addGap(12, 12, 12)
                .addComponent(jComboBox4, 0, 202, Short.MAX_VALUE)
                .addGap(111, 111, 111)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addGap(60, 60, 60))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadCategory() {
        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `category`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("Category"));
                LoadCategoryMap.put(resultSet.getString("Category"), resultSet.getInt("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Product Management in loadCategory", e);
        }
    }

    private void loadModel() {
        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `model`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                LoadModelMap.put(resultSet.getString("name"), resultSet.getInt("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Product Management in loadModel", e);
        }
    }

    private void loadBrand() {
        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `brand`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                LoadBrandMap.put(resultSet.getString("name"), resultSet.getInt("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Product Management in loadBrand", e);
        }
    }

    private void loadProduct(String coloumn, String orderby, String id, String Category, String Brand, String Model, String Name) {
        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `product` INNER JOIN `category` ON `product`.`Category_id` = `category`.`id`"
                    + "INNER JOIN `brand` ON `product`.`brand_id` = `brand`.`id`"
                    + "INNER JOIN `model` ON `product`.`model_id` = `model`.`id` "
                    + "WHERE `product_id` LIKE '" + id + "%' OR `product`.`name` LIKE '" + Name + "%' "
                            + "OR `category`.`Category` LIKE '" + Category + "%' OR `brand`.`name` LIKE '" + Brand + "%'"
                    + "OR `model`.`name` LIKE '" + Model + "%' ORDER BY `" + coloumn + "`" + orderby + "");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("product_id"));
                vector.add(resultSet.getString("category.Category"));
                vector.add(resultSet.getString("brand.name"));
                vector.add(resultSet.getString("model.name"));
                vector.add(resultSet.getString("product.name"));
                model.addRow(vector);

            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Product Management in loadProduct", e);
        }

    }

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

        Frame ProductManagement = null;

        CategorysView categorysView = new CategorysView(ProductManagement, true);
        categorysView.setVisible(true);

        refreshCategoryComboBox();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        Frame ProductManagement = null;

        ModelView modelView = new ModelView(ProductManagement, true);
        modelView.setVisible(true);

        refreshModelComboBox();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

        Frame ProductManagement = null;

        BrandView brandView = new BrandView(ProductManagement, true);
        brandView.setVisible(true);

        refreshBrandComboBox();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String productid = jTextField1.getText();
        String category = String.valueOf(jComboBox1.getSelectedItem());
        String brand = String.valueOf(jComboBox3.getSelectedItem());
        String model = String.valueOf(jComboBox2.getSelectedItem());
        String productname = jTextField2.getText();
        String imageName = jTextField4.getText();

        if (productid.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Enter Product Id");
        } else if (!productid.matches("\\d+")) { // Ensures productid contains only digits
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Product ID must be numeric");
        } else if (category.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Select Category");
        } else if (brand.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Select Brand");
        } else if (model.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Select Model");
        } else if (productname.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Enter Product Name");
        } else {
            // Validate HashMap lookups before proceeding
            Integer categoryId = LoadCategoryMap.get(category);
            Integer brandId = LoadBrandMap.get(brand);
            Integer modelId = LoadModelMap.get(model);

            if (categoryId == null) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Invalid category selected");
                return;
            }
            if (brandId == null) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Invalid brand selected");
                return;
            }
            if (modelId == null) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Invalid model selected");
                return;
            }

            // Validate the file extension
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
                    // Define the destination directory
                    File sourceFile = new File(imageName);
                    File destDir = new File("src/productImages/");
                    if (!destDir.exists()) {
                        destDir.mkdirs(); // Create the directory if it does not exist
                    }

                    // Copy the image file to the destination directory
                    File destFile = new File(destDir, sourceFile.getName());
                    Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    // Save the relative path to the database
                    String relativePath = "productImages/" + sourceFile.getName();

                    // Use the validated IDs in the SQL query
                    ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `product` WHERE `product_id` = '" + productid + "' "
                            + "OR (`name` = '" + productname + "' AND `brand_id` = '" + brandId + "' AND `model_id` = '" + modelId + "' AND `Category_id` = '" + categoryId + "')");

                    if (resultSet.next()) {
                        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Product already added");
                    } else {
                        MySQL2.executeIUD("INSERT INTO `product`(`product_id`, `name`, `brand_id`, `model_id`, `Category_id`, `image`) "
                                + "VALUES('" + productid + "','" + productname + "','" + brandId + "','" + modelId + "','" + categoryId + "','" + relativePath + "')");
                        loadProduct("product_id", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());

                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "New product added Successfully!");
                        reset();
                    }
                } catch (FileAlreadyExistsException e) {
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Image file already exists in the destination folder.");
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Exception in Product Management in Add Button", e);
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int row = jTable1.getSelectedRow();

        if (row != -1) {
            String productId = String.valueOf(jTable1.getValueAt(row, 0));

            // Rest of your code remains the same
            jTextField1.setText(productId);
            jComboBox1.grabFocus();
            jTextField1.setEditable(false);

            String category = String.valueOf(jTable1.getValueAt(row, 1));
            jComboBox1.setSelectedItem(category);

            String brand = String.valueOf(jTable1.getValueAt(row, 2));
            jComboBox3.setSelectedItem(brand);

            String model = String.valueOf(jTable1.getValueAt(row, 3));
            jComboBox2.setSelectedItem(model);

            String productname = String.valueOf(jTable1.getValueAt(row, 4));
            jTextField2.setText(productname);
            try {
                ResultSet rs = MySQL2.executeSearch("SELECT image FROM `product` WHERE `product_id` = '" + jTextField1.getText() + "'");

                if (rs.next()) {
                    String image_path = String.valueOf(rs.getString("image"));
                    jTextField4.setText(image_path);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.log(Level.WARNING, "Exception In Product Management in jTable1MouseClicked", e);
            }

            jButton1.setEnabled(false);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Get basic information from form fields
        String productId = jTextField1.getText().trim();
        String productName = jTextField2.getText().trim();
        String category = String.valueOf(jComboBox1.getSelectedItem());
        String brand = String.valueOf(jComboBox3.getSelectedItem());
        String model = String.valueOf(jComboBox2.getSelectedItem());
        String imageName = jTextField4.getText().trim();

        // Validate input values
        if (productId.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Please enter Product ID");
            return;
        } else if (!productId.matches("\\d+")) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Product ID must be numeric");
            return;
        } else if (productName.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Please enter product name");
            return;
        } else if (category.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Please select product category");
            return;
        } else if (brand.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Please select product brand");
            return;
        } else if (model.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER, "Please select product model");
            return;
        }

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
                Path productImagesPath = projectRoot.resolve("src/productImages");

                // Determine source path
                Path sourcePath;
                if (Paths.get(imageName).isAbsolute()) {
                    sourcePath = Paths.get(imageName);
                } else {
                    sourcePath = productImagesPath.resolve(Paths.get(imageName).getFileName());

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
                Files.createDirectories(productImagesPath);

                // Copy file to ProductImages directory
                String fileName = sourcePath.getFileName().toString();
                Path destPath = productImagesPath.resolve(fileName);
                Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);

                // Set relative path for database
                relativePath = "productImages/" + fileName;
            }

            // Check if product exists
            ResultSet resultSet = MySQL2.executeSearch(
                    "SELECT * FROM `product` WHERE `product_id` = '" + productId + "'");

            if (resultSet.next()) {
                update = true;
            } else {
                Notifications.getInstance().show(Notifications.Type.INFO,
                        Notifications.Location.TOP_CENTER,
                        "Product not found with ID: " + productId);
                return;
            }

            // Update product if validation passes
            if (update) {
                StringBuilder updateQuery = new StringBuilder();
                updateQuery.append("UPDATE `product` SET ")
                        .append("`name` = '").append(productName).append("',")
                        .append("`category_id` = '").append(LoadCategoryMap.get(category)).append("',")
                        .append("`brand_id` = '").append(LoadBrandMap.get(brand)).append("',")
                        .append("`model_id` = '").append(LoadModelMap.get(model)).append("'");

                if (relativePath != null) {
                    updateQuery.append(",`image` = '").append(relativePath).append("'");
                }

                updateQuery.append(" WHERE `product_id` = '").append(productId).append("'");

                MySQL2.executeIUD(updateQuery.toString());
                loadProduct("product_id", "ASC", jTextField3.getText(), jTextField3.getText(),
                        jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
                reset();

                Notifications.getInstance().show(Notifications.Type.SUCCESS,
                        Notifications.Location.TOP_CENTER,
                        "Product updated successfully");
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "Exception in Product Management during update", ex);
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER,
                    "Database error occurred while updating product");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Unexpected error during product update", ex);
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER,
                    "An unexpected error occurred: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged

        int sort = jComboBox4.getSelectedIndex();

        if (sort == 0) {
            loadProduct("product_id", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        } else if (sort == 1) {
            loadProduct("product_id", "DESC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        } else if (sort == 2) {
            loadProduct("product`.`name", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        } else if (sort == 3) {
            loadProduct("category`.`Category", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        } else if (sort == 4) {
            loadProduct("brand`.`name", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        } else if (sort == 5) {
            loadProduct("model`.`name", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
        }

    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased

        loadProduct("product_id", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this); // 'this' refers to the parent JFrame or component

        if (result == JFileChooser.APPROVE_OPTION) {
            String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            jTextField4.setText(selectedFilePath);

            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER,
                    "Selected File: " + selectedFilePath);
        } else {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_CENTER,
                    "File Selection Cancelled");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        int row = jTable1.rowAtPoint(evt.getPoint());
        if (row != -1) {
            showProductImage(row);
        }
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseExited
        if (imageFrame != null) {
            imageFrame.dispose();
            imageFrame = null;
        }
    }//GEN-LAST:event_jTable1MouseExited

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered

        String tooltipText = "• To add or edit an Product's details, click the \"Add/Update Product\" button.\n\n"
                + "• To add an Product's Category, click on the \"CATEGORY\" label (highlighted with a red border).\n\n"
                + "• To add an Product's Model, click on the \"MODEL\" label (highlighted with a red border).\n\n"
                + "• To add an Product's Brand, click on the \"BRAND\" label (highlighted with a red border).\n\n"
                + "• To update an Product's details, click on the respective Product's row in the table. "
                + "The Product's details will automatically populate the fields for editing.\n\n"
                + "• The product can be searched by name, category, model, brand, or product ID.";

        showTooltip(tooltipText);
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited

        hideTooltip();
    }//GEN-LAST:event_jLabel11MouseExited
    private JFrame tipFrame = null;

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
            scrollPane.setPreferredSize(new Dimension(350, 350)); // Fixed width and height for long text

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

    private void showProductImage(int row) {
        String productId = String.valueOf(jTable1.getValueAt(row, 0));

        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT image FROM product WHERE product_id = '" + productId + "'");
            if (resultSet.next()) {
                String imagePath = resultSet.getString("image");

                if (imagePath != null && !imagePath.isEmpty()) {
                    File imageFile = new File(System.getProperty("user.dir") + File.separator + "build"
                            + File.separator + "classes" + File.separator + imagePath);

                    if (imageFile.exists()) {
                        BufferedImage img = ImageIO.read(imageFile);
                        if (img != null) {
                            Image scaledImage = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                            ImageIcon icon = new ImageIcon(scaledImage);

                            // Use existing frame or create new one
                            if (imageFrame == null) {
                                imageFrame = new JFrame();
                                imageFrame.setUndecorated(true);
                                imageFrame.add(new JLabel(icon));
                                imageFrame.pack();

                                // Get mouse location and position frame near it
                                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                                imageFrame.setLocation(mouseLocation.x + 20, mouseLocation.y + 20);

                                imageFrame.setVisible(true);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.SEVERE, "Unexpected error during productmanagement in showProductImage", ex);
        }
    }

    private void reset() {

        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jComboBox1.setSelectedItem("Select");
        jComboBox2.setSelectedItem("Select");
        jComboBox3.setSelectedItem("Select");
        jComboBox4.setSelectedItem("Select");
        jTable1.clearSelection();
        jButton1.setEnabled(true);
        jTextField1.setEditable(true);
        loadProduct("product_id", "ASC", jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText(), jTextField3.getText());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

    private void refreshCategoryComboBox() {
        jComboBox1.removeAllItems();  // Clear existing items
        jComboBox1.addItem("Select");
        try {
            ResultSet rs = MySQL2.executeSearch("SELECT * FROM `category`");

            // Add each position to the JComboBox
            while (rs.next()) {
                jComboBox1.addItem(rs.getString("Category"));
            }
        } catch (Exception e) {
//        e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Product Management in refreshCategoryComboBox", e);
        }
    }

    private void refreshBrandComboBox() {
        jComboBox3.removeAllItems();  // Clear existing items
        jComboBox3.addItem("Select");
        try {
            ResultSet rs = MySQL2.executeSearch("SELECT * FROM `brand`");

            // Add each position to the JComboBox
            while (rs.next()) {
                jComboBox3.addItem(rs.getString("name"));
            }
        } catch (Exception e) {
//        e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Product Management in refreshBrandComboBox", e);
        }
    }

    private void refreshModelComboBox() {
        jComboBox2.removeAllItems();  // Clear existing items
        jComboBox2.addItem("Select");
        try {
            ResultSet rs = MySQL2.executeSearch("SELECT * FROM `model`");

            // Add each position to the JComboBox
            while (rs.next()) {
                jComboBox2.addItem(rs.getString("name"));
            }
        } catch (Exception e) {
//        e.printStackTrace();
            logger.log(Level.WARNING, "Exception In Product Management in refreshModelComboBox", e);
        }
    }

}
