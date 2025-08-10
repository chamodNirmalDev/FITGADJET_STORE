/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import static gui.SplashWindow.logger;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import model.MySQL2;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import raven.toast.Notifications;

/**
 *
 * @author Chamod
 */
public class NewJPanel1 extends javax.swing.JPanel {

    public NewJPanel1() {
        initComponents();
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 400));
        loadAndDisplayChart();
    }

    private void loadAndDisplayChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Query to retrieve the data
        String query = "SELECT CONCAT(MONTHNAME(i.date), ' ', YEAR(i.date)) AS month_year, "
                + "p.name AS product_name, "
                + "SUM(ii.qty * s.sell_price) AS total_sales "
                + "FROM invoice i "
                + "JOIN invoice_item ii ON i.id = ii.invoice_id "
                + "JOIN stock s ON ii.stock_stock_id = s.stock_id "
                + "JOIN product p ON s.product_product_id = p.product_id "
                + "WHERE i.date >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH) "
                + "GROUP BY YEAR(i.date), MONTH(i.date), p.name "
                + "ORDER BY YEAR(i.date), MONTH(i.date), p.name";

        try (Connection connection = MySQL2.getConnection()) {
            // Disable ONLY_FULL_GROUP_BY mode temporarily
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("SET SESSION sql_mode = (SELECT REPLACE(@@sql_mode, 'ONLY_FULL_GROUP_BY', ''));");
            }

            // Execute the main query
            try (PreparedStatement pstmt = connection.prepareStatement(query); ResultSet resultSet = pstmt.executeQuery()) {

                while (resultSet.next()) {
                    String productName = resultSet.getString("product_name");
                    String monthYear = resultSet.getString("month_year");
                    double totalSales = resultSet.getDouble("total_sales");

                    // Add data to the dataset
                    dataset.addValue(totalSales, productName, monthYear);
                }

                // Create the bar chart
                JFreeChart barChart = ChartFactory.createBarChart(
                        "Last 12 Months Sales by Product",
                        "Month and Year",
                        "Total Sales",
                        dataset,
                        PlotOrientation.VERTICAL,
                        true,
                        true,
                        false
                );

                // Customize the chart
                CategoryPlot plot = barChart.getCategoryPlot();
                plot.setRangeGridlinePaint(Color.BLACK);

                // Wrap the chart in a ChartPanel
                ChartPanel chartPanel = new ChartPanel(barChart);
                chartPanel.setPreferredSize(new Dimension(jPanel2.getWidth(), jPanel2.getHeight()));

                // Add the chart to jPanel2
                jPanel2.removeAll();
                jPanel2.setLayout(new BorderLayout());
                jPanel2.add(chartPanel, BorderLayout.CENTER);
                jPanel2.revalidate();
                jPanel2.repaint();

            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "NewJpanel1 loadAndDisplayChart Exception", ex);
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.log(Level.WARNING, "NewJpanel1 loadAndDisplayChart Exception", ex);
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setMaximumSize(new java.awt.Dimension(1150, 580));
        setMinimumSize(new java.awt.Dimension(1150, 580));
        setPreferredSize(new java.awt.Dimension(1150, 580));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 400));

        jLabel2.setText("Monthly ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(481, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(362, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 480));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
