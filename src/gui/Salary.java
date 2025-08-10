/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import static gui.SplashWindow.logger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.MySQL2;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

public class Salary extends javax.swing.JPanel {

    private int daysPresent = 0;
    private int daysAbsent = 0;
    private double totalWorkHours = 0.0;
    private double overtimeHours = 0.0;
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    private static final double STANDARD_WORK_HOURS = 9.0;
    private static final double EPF_RATE = 0.08;
    private static final double ETF_RATE = 0.03;
    private JFrame tipFrame = null;

    public Salary() {
        initComponents();
        initializeFields();
        loadOTRate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField11 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jTextField12 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jLabel24 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/icons8-employee-32.png"))); // NOI18N
        jLabel14.setText("EMPLOYEE SALARY  ");

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
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("USER ID :");

        jTextField1.setBackground(new java.awt.Color(242, 242, 242));
        jTextField1.setToolTipText("ENTER COMPANY ID");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("NAME :");

        jTextField2.setBackground(new java.awt.Color(242, 242, 242));
        jTextField2.setToolTipText("ENTER COMPANY ID");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("POSITION :");

        jTextField3.setBackground(new java.awt.Color(242, 242, 242));
        jTextField3.setToolTipText("ENTER COMPANY ID");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("SALARY PERIOD :");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("TRANSPORT ALLOWANCE :");

        jTextField5.setBackground(new java.awt.Color(242, 242, 242));
        jTextField5.setToolTipText("ENTER COMPANY ID");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("BASIC INFORMATION");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9MouseExited(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("LEAVE COUNT  :");

        jTextField6.setBackground(new java.awt.Color(242, 242, 242));
        jTextField6.setToolTipText("ENTER COMPANY ID");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("BASIC SALARY  :");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("OVERTIME   :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("OT RATE  :");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("SALARY CALCULATION");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel15MouseExited(evt);
            }
        });

        jTextField7.setBackground(new java.awt.Color(242, 242, 242));
        jTextField7.setToolTipText("ENTER COMPANY ID");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("FOOD ALLOWANCE :");

        jTextField8.setBackground(new java.awt.Color(242, 242, 242));
        jTextField8.setToolTipText("ENTER COMPANY ID");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("OVERTIME AMOUNT :");

        jTextField9.setBackground(new java.awt.Color(242, 242, 242));
        jTextField9.setToolTipText("ENTER COMPANY ID");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("GROSS SALARY :");

        jTextField10.setBackground(new java.awt.Color(242, 242, 242));
        jTextField10.setToolTipText("ENTER COMPANY ID");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("ALLOWANCE ");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("DEDUCTIONS ");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("EPF AMOUNT :");

        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jTextField11.setBackground(new java.awt.Color(242, 242, 242));
        jTextField11.setToolTipText("ENTER COMPANY ID");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("ETF AMOUNT :");

        jCheckBox2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jTextField12.setBackground(new java.awt.Color(242, 242, 242));
        jTextField12.setToolTipText("ENTER COMPANY ID");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("NET SALARY :");

        jTextField14.setBackground(new java.awt.Color(242, 242, 242));
        jTextField14.setToolTipText("ENTER COMPANY ID");

        jButton1.setBackground(new java.awt.Color(0, 153, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("CALCULATE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("UPDATE ETC..");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jYearChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jYearChooser1PropertyChange(evt);
            }
        });

        jMonthChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jMonthChooser1PropertyChange(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("WORK DAYS  :");

        jTextField15.setBackground(new java.awt.Color(242, 242, 242));
        jTextField15.setToolTipText("ENTER COMPANY ID");

        jTextField4.setBackground(new java.awt.Color(242, 242, 242));
        jTextField4.setToolTipText("ENTER COMPANY ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jTextField5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jMonthChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel24)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField15))
                                .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)))
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(23, 23, 23)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                .addComponent(jTextField11)
                                .addComponent(jTextField10)
                                .addComponent(jTextField9)
                                .addComponent(jTextField8)
                                .addComponent(jTextField7)
                                .addComponent(jTextField14)))
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jMonthChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void initializeFields() {

        jTextField2.setEditable(false);
        jTextField3.setEditable(false);
        jTextField6.setEditable(false);
        jTextField15.setEditable(false);
        jTextField4.setEditable(false);
        jTextField5.setEditable(false);
        jTextField9.setEditable(false);
        jTextField10.setEditable(false);
        jTextField11.setEditable(false);
        jTextField12.setEditable(false);
        jTextField14.setEditable(false);
    }

    private void loadOTRate() {
        String query = "SELECT amount FROM overtime LIMIT 1";
        try (Connection con = MySQL2.getConnection(); PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String amount = rs.getString("amount");
                jLabel13.setText("OT RATE  : Rs. " + amount + "0");
            }
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception In Salary in loadOTRate", ex);
        }
    }

    private void loadBasicSalary() {
        String position = jTextField3.getText().trim();
        if (position.isEmpty()) {
            return;
        }

        String query = "SELECT bs.price FROM besic_salary bs "
                + "INNER JOIN employee_type et ON et.id = bs.employee_type_id "
                + "WHERE et.type = ?";

        try (Connection con = MySQL2.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, position);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    jTextField4.setText("Rs. " + rs.getString("price"));
                } else {
                    Notifications.getInstance().show(Notifications.Type.WARNING, "No salary found for this position!");
                }
            }
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception In Salary in loadBasicSalary", ex);
        }
    }

    private void loadEmployee() {
        String userid = jTextField1.getText().trim();
        if (userid.isEmpty()) {
            clearEmployeeFields();
            return;
        }

        String query = "SELECT e.f_name, e.l_name, et.type FROM employee e "
                + "INNER JOIN employee_type et ON et.id = e.employee_type_id "
                + "WHERE e.employee_id = ?";

        try (Connection conn = MySQL2.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, userid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    jTextField2.setText(rs.getString("f_name") + " " + rs.getString("l_name"));
                    jTextField3.setText(rs.getString("type"));
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Employee data loaded successfully!");
                } else {
                    clearEmployeeFields();
                    Notifications.getInstance().show(Notifications.Type.INFO, "Employee not found with ID: " + userid);
                }
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception in loadEmployee method", e);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Error retrieving employee data!");
        }
    }

    private void clearEmployeeFields() {
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField6.setText("");
        jTextField15.setText("");
    }

    private void calculateAttendanceData() {
        String userid = jTextField1.getText().trim();
        int year = jYearChooser1.getYear();
        int month = jMonthChooser1.getMonth() + 1;

        if (userid.isEmpty()) {
            return;
        }

        resetCalculationVariables();

        String selectedDate = String.format("%04d-%02d", year, month);
        int workingDaysInMonth = calculateWorkingDays(year, month);

        String query = "SELECT mark, InTime, OutTime FROM employee_attandance "
                + "WHERE employee_employee_id = ? AND Date LIKE ?";

        try (Connection conn = MySQL2.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, userid);
            ps.setString(2, selectedDate + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    processAttendanceRecord(rs);
                }

                calculateOvertimePayment();
                updateAttendanceDisplay(workingDaysInMonth);

                Notifications.getInstance().show(Notifications.Type.SUCCESS,
                        "Attendance data loaded for " + selectedDate);

            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "SQL Exception in calculateAttendanceData", e);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Database error: " + e.getMessage());
        }
    }

    private void resetCalculationVariables() {
        daysPresent = 0;
        daysAbsent = 0;
        totalWorkHours = 0.0;
        overtimeHours = 0.0;
    }

    private void processAttendanceRecord(ResultSet rs) throws SQLException {
        String mark = rs.getString("mark");
        String inTimeStr = rs.getString("InTime");
        String outTimeStr = rs.getString("OutTime");

        if ("1".equals(mark)) {
            daysPresent++;

            if (isValidTimeData(inTimeStr, outTimeStr)) {
                calculateWorkHours(inTimeStr, outTimeStr);
            }
        } else {
            daysAbsent++;
        }
    }

    private boolean isValidTimeData(String inTime, String outTime) {
        return inTime != null && !inTime.equals("(NULL)")
                && outTime != null && !outTime.equals("(NULL)");
    }

    private void calculateWorkHours(String inTimeStr, String outTimeStr) {
        try {
            LocalTime inTime = LocalTime.parse(inTimeStr);
            LocalTime outTime = LocalTime.parse(outTimeStr);

            Duration duration = Duration.between(inTime, outTime);
            double hoursWorked = duration.toHours() + (duration.toMinutesPart() / 60.0);

            totalWorkHours += hoursWorked;

            if (hoursWorked > STANDARD_WORK_HOURS) {
                overtimeHours += (hoursWorked - STANDARD_WORK_HOURS);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error parsing time data", e);
        }
    }

    private void calculateOvertimePayment() {
        String query = "SELECT amount FROM overtime LIMIT 1";
        try (Connection conn = MySQL2.getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                double amount = rs.getDouble("amount");
                double otPayment = overtimeHours * amount;
                jTextField9.setText("Rs. " + decimalFormat.format(otPayment));
            } else {
                jTextField9.setText("Rs. 0.00");
            }
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception in calculateOvertimePayment method", ex);
        }
    }

    private void updateAttendanceDisplay(int workingDaysInMonth) {
        int daysNotWorked = workingDaysInMonth - daysPresent;

        jTextField6.setText(daysNotWorked + " Days");
        jTextField15.setText(daysPresent + " Days");
        jTextField5.setText(convertToHourMinuteString(overtimeHours));
    }

    private void loadAllowances() {
        loadAllowanceByType("Transport", jTextField7);
        loadAllowanceByType("Food", jTextField8);
    }

    private void loadAllowanceByType(String allowanceType, JTextField textField) {
        String query = "SELECT amount FROM allowance WHERE allowance_type = ?";
        try (Connection conn = MySQL2.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, allowanceType);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    textField.setText("Rs. " + rs.getString("amount"));
                }
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception in loadAllowanceByType method", e);
        }
    }

    private void calculateGrossSalary() {
        double basicSalary = parseDoubleOrZero(jTextField4.getText());
        double otAmount = parseDoubleOrZero(jTextField9.getText());
        double transport = parseDoubleOrZero(jTextField7.getText());
        double food = parseDoubleOrZero(jTextField8.getText());

        int year = jYearChooser1.getYear();
        int month = jMonthChooser1.getMonth() + 1;
        int totalWorkingDays = calculateWorkingDays(year, month);

        if (totalWorkingDays == 0) {
            jTextField10.setText("Rs. 0.00");
            return;
        }

        double dailyBasicSalary = basicSalary / totalWorkingDays;
        double dailyTransport = transport / totalWorkingDays;
        double dailyFood = food / totalWorkingDays;

        double grossSalary = (dailyBasicSalary * daysPresent) + otAmount
                + (dailyTransport * daysPresent) + (dailyFood * daysPresent);

        jTextField10.setText("Rs. " + decimalFormat.format(grossSalary));
        calculateNetSalary();
    }

    private void calculateEPFandETF() {
        double basicSalary = parseDoubleOrZero(jTextField4.getText());

        int year = jYearChooser1.getYear();
        int month = jMonthChooser1.getMonth() + 1;
        int totalWorkingDays = calculateWorkingDays(year, month);

        if (totalWorkingDays == 0) {
            jTextField11.setText("");
            jTextField12.setText("");
            return;
        }

        double dailyBasicSalary = basicSalary / totalWorkingDays;
        double actualBasicSalaryForWorkedDays = dailyBasicSalary * daysPresent;

        double epf = actualBasicSalaryForWorkedDays * EPF_RATE;
        double etf = actualBasicSalaryForWorkedDays * ETF_RATE;

        if (jCheckBox1.isSelected()) {
            jTextField11.setText("Rs. " + decimalFormat.format(epf));
        } else {
            jTextField11.setText("");
        }

        if (jCheckBox2.isSelected()) {
            jTextField12.setText("Rs. " + decimalFormat.format(etf));
        } else {
            jTextField12.setText("");
        }

        calculateNetSalary();
    }

    private void calculateNetSalary() {
        double grossSalary = parseDoubleOrZero(jTextField10.getText());
        double epf = parseDoubleOrZero(jTextField11.getText());
        double etf = parseDoubleOrZero(jTextField12.getText());
        double netSalary = grossSalary - (epf + etf);

        jTextField14.setText("Rs. " + decimalFormat.format(netSalary));
    }

    private double parseDoubleOrZero(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0.0;
        }
        try {
            text = text.replace("Rs.", "").replace(",", "").trim();
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private String convertToHourMinuteString(double decimalHours) {
        int hours = (int) decimalHours;
        int minutes = (int) Math.round((decimalHours - hours) * 60);
        return hours + " Hours " + minutes + " Minutes";
    }

    private int calculateWorkingDays(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate date = LocalDate.of(year, month, 1);
        int workingDays = 0;

        for (int day = 1; day <= daysInMonth; day++) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SUNDAY) {
                workingDays++;
            }
            date = date.plusDays(1);
        }

        return workingDays;
    }

    private String getMonthName(int month) {
        String[] monthNames = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
        return monthNames[month - 1];
    }

    private boolean salaryExists(String employeeId, int year, int month) {
        String query = "SELECT COUNT(*) as count FROM salary_payment "
                + "WHERE employee_id = ? AND payment_period = ?";

        try (Connection conn = MySQL2.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            String paymentPeriod = getMonthName(month) + " " + year;
            ps.setString(1, employeeId);
            ps.setString(2, paymentPeriod);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count") > 0;
                }
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception in salaryExists method", e);
        }

        return false;
    }

    private boolean saveSalaryToDatabase(String userID, String name, String position,
            String workDays, String ot, String otAmount,
            String transport, String food, String grossSalary,
            String epfText, String etfText, String netSalary,
            int year, String monthName) {

        if (salaryExists(userID, year, jMonthChooser1.getMonth() + 1)) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    "Salary already exists for " + userID + " in " + monthName + " " + year);
            return false;
        }

        Connection conn = null;
        PreparedStatement psInsert = null;
        PreparedStatement psSelect = null;
        ResultSet rs = null;

        try {
            conn = MySQL2.getConnection();

            String selectQuery = "SELECT "
                    + "bs.id as basic_salary_id, "
                    + "et.id as employee_type_id, "
                    + "o.overtime_id, "
                    + "a.allowance_id "
                    + "FROM employee_type et "
                    + "LEFT JOIN besic_salary bs ON et.id = bs.employee_type_id "
                    + "CROSS JOIN overtime o "
                    + "CROSS JOIN allowance a "
                    + "WHERE et.type = ? "
                    + "LIMIT 1";

            psSelect = conn.prepareStatement(selectQuery);
            psSelect.setString(1, position);
            rs = psSelect.executeQuery();

            int basicSalaryId = 1, employeeTypeId = 1, overtimeId = 1, allowanceId = 1;

            if (rs.next()) {
                basicSalaryId = rs.getInt("basic_salary_id");
                employeeTypeId = rs.getInt("employee_type_id");
                overtimeId = rs.getInt("overtime_id");
                allowanceId = rs.getInt("allowance_id");
            }

            rs.close();
            psSelect.close();

            String insertSql = "INSERT INTO salary_payment ("
                    + "employee_id, payment_period, work_days, gross_salary, net_salary, "
                    + "besic_salary_id, employee_type_id, overtime, epf, etf, "
                    + "overtime_overtime_id, allowance_allowance_id"
                    + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            psInsert = conn.prepareStatement(insertSql);
            psInsert.setString(1, userID);
            psInsert.setString(2, monthName + " " + year);
            psInsert.setInt(3, Integer.parseInt(workDays.replace(" Days", "")));
            psInsert.setDouble(4, parseDoubleOrZero(grossSalary));
            psInsert.setDouble(5, parseDoubleOrZero(netSalary));
            psInsert.setInt(6, basicSalaryId);
            psInsert.setInt(7, employeeTypeId);
            psInsert.setString(8, ot);
            psInsert.setDouble(9, parseDoubleOrZero(epfText));
            psInsert.setDouble(10, parseDoubleOrZero(etfText));
            psInsert.setInt(11, overtimeId);
            psInsert.setInt(12, allowanceId);

            int rowsAffected = psInsert.executeUpdate();

            if (rowsAffected > 0) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, "Salary saved successfully!");
                return true;
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Failed to save salary data!");
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception in saveSalaryToDatabase", e);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Database SQL Error: " + e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Number format exception in saveSalaryToDatabase", e);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Invalid number format: " + e.getMessage());
            return false;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "General exception in saveSalaryToDatabase", e);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Unexpected error: " + e.getMessage());
            return false;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.log(Level.WARNING, "Error closing ResultSet", e);
                }
            }
            if (psSelect != null) {
                try {
                    psSelect.close();
                } catch (SQLException e) {
                    logger.log(Level.WARNING, "Error closing select PreparedStatement", e);
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    logger.log(Level.WARNING, "Error closing insert PreparedStatement", e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.log(Level.WARNING, "Error closing Connection", e);
                }
            }
        }
    }

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
            scrollPane.setPreferredSize(new Dimension(350, 600));

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
    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered

        String tooltipText = "• Enter the Employee ID in the User ID field to automatically load employee details.\n\n"
                + "• Select the desired Year and Month using the date choosers to calculate attendance for that period.\n\n"
                + "• The system will automatically calculate:\n"
                + "  - Working days and absent days based on attendance records\n"
                + "  - Overtime hours and payment based on logged hours\n"
                + "  - Basic salary, allowances, and deductions\n\n"
                + "• Check the EPF and ETF checkboxes if the employee is eligible for these deductions.\n\n"
                + "• Click the \"Calculate & Save\" button to:\n"
                + "  - Save salary data to the database\n"
                + "  - Display the salary record in the table\n"
                + "  - Generate and preview the salary report\n\n"
                + "• The table will show only the current employee's salary data.\n\n"
                + "• All calculated fields are read-only and automatically updated based on attendance data.\n\n"
                + "• Ensure employee attendance is properly recorded before calculating salary.\n\n"
                + "• The system prevents duplicate salary entries for the same employee and month.";

        showTooltip(tooltipText);


    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        hideTooltip();
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered

    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited

    }//GEN-LAST:event_jLabel9MouseExited

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered

    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited

    }//GEN-LAST:event_jLabel15MouseExited

    private void clearTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
    }

    private void addCurrentEmployeeToTable(String userID, String name, String position,
            String workDays, String ot, String otAmount,
            String transport, String food, String epfText,
            String etfText, String netSalary, int year, String monthName) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        Vector<String> row = new Vector<>();
        row.add(monthName + " " + year);
        row.add(userID);
        row.add(name);
        row.add(position);
        row.add(workDays);
        row.add(ot);
        row.add(otAmount);
        row.add(transport);
        row.add(food);
        row.add(epfText);
        row.add(etfText);
        row.add(netSalary);

        model.addRow(row);
    }

    private boolean validateRequiredFields() {
        String userID = jTextField1.getText().trim();
        if (userID.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Please enter User ID.");
            jTextField1.requestFocus();
            return false;
        }

        if (jTextField2.getText().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Employee data not loaded. Please check User ID.");
            jTextField1.requestFocus();
            return false;
        }

        if (daysPresent == 0) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "No attendance data found for selected period.");
            return false;
        }

        return true;
    }

    private void generateSalaryReport(String userID, String name, String position, String workDays,
            String ot, String otAmount, String transport, String food,
            String grossSalary, String epfText, String etfText,
            String netSalary, int year, String monthName) {
        try {
            InputStream reportStream = getClass().getClassLoader().getResourceAsStream("Report/Salary.jasper");
            if (reportStream == null) {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        "Report file 'Salary.jasper' not found in resources/Report/ folder.");
                return;
            }

            // Create parameters HashMap with safe values
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", year + " " + monthName);
            params.put("Parameter2", safeString(userID));
            params.put("Parameter3", safeString(name));
            params.put("Parameter4", safeString(position));
            params.put("Parameter5", safeString(jTextField6.getText()));
            params.put("Parameter6", safeString(workDays));
            params.put("Parameter7", safeString(ot));
            params.put("Parameter8", safeString(jLabel13.getText()));
            params.put("Parameter9", safeString(jTextField4.getText()));
            params.put("Parameter10", safeString(transport));
            params.put("Parameter11", safeString(food));
            params.put("Parameter12", safeString(otAmount));
            params.put("Parameter13", safeString(grossSalary));
            params.put("Parameter14", safeString(epfText));
            params.put("Parameter15", safeString(etfText));
            params.put("Parameter16", safeString(netSalary));

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params, new JREmptyDataSource());

            if (jasperPrint.getPages().isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING,
                        "Report generated but has no pages. Check report design and data.");
                return;
            }

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            logger.log(Level.SEVERE, "Jasper Report Error", e);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Report Error: " + e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "General Error in report generation", e);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Error generating report: " + e.getMessage());
        }
    }

    private String safeString(String value) {
        return value != null ? value : "";
    }

    private void clearAllFields() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField14.setText("");
        jTextField15.setText("");
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        clearTable();

        resetCalculationVariables();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (!validateRequiredFields()) {
            return;
        }

        String userID = jTextField1.getText().trim();
        String name = jTextField2.getText();
        String position = jTextField3.getText();
        String workDays = jTextField15.getText();
        String ot = jTextField5.getText();
        String otAmount = jTextField9.getText();
        String transport = jTextField7.getText();
        String food = jTextField8.getText();

        int year = jYearChooser1.getYear();
        int month = jMonthChooser1.getMonth() + 1;
        String monthName = getMonthName(month);

        calculateGrossSalary();
        calculateEPFandETF();

        String epfText = jTextField11.getText();
        String etfText = jTextField12.getText();
        String netSalary = jTextField14.getText();
        String grossSalary = jTextField10.getText();

        boolean saveSuccess = saveSalaryToDatabase(userID, name, position, workDays, ot,
                otAmount, transport, food, grossSalary,
                epfText, etfText, netSalary, year, monthName);

        if (saveSuccess) {

            clearTable();

            addCurrentEmployeeToTable(userID, name, position, workDays, ot, otAmount,
                    transport, food, epfText, etfText, netSalary, year, monthName);

            generateSalaryReport(userID, name, position, workDays, ot, otAmount, transport, food,
                    grossSalary, epfText, etfText, netSalary, year, monthName);

            clearAllFields();

        }

    }//GEN-LAST:event_jButton1ActionPerformed


    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String userId = jTextField1.getText().trim();
        if (!userId.isEmpty()) {
            loadEmployee();
            loadBasicSalary();
            loadAllowances();
            calculateAttendanceData();
            calculateGrossSalary();
            calculateEPFandETF();
        } else {
            clearEmployeeFields();
        }
    }//GEN-LAST:event_jTextField1KeyReleased


    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        String userId = jTextField1.getText().trim();
        if (!userId.isEmpty()) {
            loadEmployee();
            loadBasicSalary();
            loadAllowances();
            calculateAttendanceData();
            calculateGrossSalary();
            calculateEPFandETF();
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jYearChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jYearChooser1PropertyChange
        if ("year".equals(evt.getPropertyName()) && !jTextField1.getText().trim().isEmpty()) {
            try {
                resetCalculationVariables();
                calculateAttendanceData();
                calculateGrossSalary();
                calculateEPFandETF();
            } catch (Exception e) {
                logger.log(Level.WARNING, "Exception when updating attendance after year change", e);
            }
        }
    }//GEN-LAST:event_jYearChooser1PropertyChange

    private void jMonthChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jMonthChooser1PropertyChange
        if ("month".equals(evt.getPropertyName()) && !jTextField1.getText().trim().isEmpty()) {
            try {
                resetCalculationVariables();
                calculateAttendanceData();
                calculateGrossSalary();
                calculateEPFandETF();
            } catch (Exception e) {
                logger.log(Level.WARNING, "Exception when updating attendance after month change", e);
            }
        }
    }//GEN-LAST:event_jMonthChooser1PropertyChange

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        calculateEPFandETF();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        calculateEPFandETF();
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Update_SalaryData update_SalaryData = new Update_SalaryData();
        update_SalaryData.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables
}
