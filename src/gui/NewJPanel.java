/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import static gui.SplashWindow.logger;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.time.YearMonth;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MySQL2;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Chamod
 */
public class NewJPanel extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public NewJPanel() {
        initComponents();
        getEmployeeCount();
        getSupplierCount();
        getCustomerCount();
        getbrandCount();
        getCategoryCount();
        getProductCount();
        addBarChartToPanelMonth();
        addBarChartToPanelYear();
        jpanel1.setPreferredSize(new java.awt.Dimension(580, 258));
        jpanel2.setPreferredSize(new java.awt.Dimension(500, 258));

    }

    private void addBarChartToPanelMonth() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Map<String, Double>> monthlyIncomeData = getMonthlyIncome();

        // Populate the dataset with Revenue, COGS, and Net Income
        for (Map.Entry<String, Map<String, Double>> monthEntry : monthlyIncomeData.entrySet()) {
            String monthName = monthEntry.getKey(); // e.g., "2024 JANUARY"
            Map<String, Double> incomeDetails = monthEntry.getValue();

            // Add Revenue, COGS, and Net Income to the dataset
            dataset.setValue(incomeDetails.get("Revenue"), "Revenue", monthName);
            dataset.setValue(incomeDetails.get("COGS"), "COGS", monthName);
            dataset.setValue(incomeDetails.get("Net Income"), "Net Income", monthName);
        }

        // Create the bar chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Monthly Income Breakdown", // Chart title
                "Months", // X-axis label
                "Amount (LKR)", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Chart orientation
                true, // Include legend
                true, // Tooltips
                false // URLs
        );

        // Customize the chart's appearance
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK); // Set gridline color

        // Wrap the chart in a ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpanel1.getWidth(), jpanel1.getHeight()));

        // Add the chart to the JPanel
        jpanel1.removeAll(); // Clear previous content
        jpanel1.setLayout(new BorderLayout());
        jpanel1.add(chartPanel, BorderLayout.CENTER);
        jpanel1.revalidate();
        jpanel1.repaint();
    }

    private void addBarChartToPanelYear() {
        // Create a dataset for the bar chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<Integer, Map<String, Double>> yearlyIncomeData = getYearlyIncomeFromDatabase();

        // Populate the dataset with yearly data
        for (Map.Entry<Integer, Map<String, Double>> entry : yearlyIncomeData.entrySet()) {
            int year = entry.getKey();
            Map<String, Double> incomeDetails = entry.getValue();

            // Add revenue, COGS, and net income to the dataset
            dataset.addValue(incomeDetails.get("Revenue"), "Revenue", String.valueOf(year));
            dataset.addValue(incomeDetails.get("COGS"), "COGS", String.valueOf(year));
            dataset.addValue(incomeDetails.get("Net Income"), "Net Income", String.valueOf(year));
        }

        // Create the bar chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Yearly Income Breakdown", // Chart Title
                "Years", // X-Axis Label
                "Amount (LKR)", // Y-Axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Chart Orientation
                true, // Include legend
                true, // Enable tooltips
                false // URLs
        );

        // Customize the plot
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);

        // Add the chart to a ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpanel2.getWidth(), jpanel2.getHeight()));

        // Update the jpanel2 with the chart
        jpanel2.removeAll(); // Clear any existing content
        jpanel2.setLayout(new BorderLayout());
        jpanel2.add(chartPanel, BorderLayout.CENTER);
        jpanel2.revalidate(); // Revalidate the panel
        jpanel2.repaint(); // Repaint the panel
    }

    private Map<String, Map<String, Double>> getMonthlyIncome() {
        Map<String, Map<String, Double>> monthlyIncomeData = new HashMap<>();

        try {
            String query = "SELECT YEAR(invoice.date) AS year, MONTH(invoice.date) AS month, "
                    + "SUM(invoice.paid_amount - IFNULL(invoice.discount, 0)) AS total_revenue, "
                    + "SUM(invoice_item.qty * grn_item.buy_price) AS total_cogs, "
                    + "SUM(invoice.paid_amount - IFNULL(invoice.discount, 0) - (invoice_item.qty * grn_item.buy_price)) AS net_income "
                    + "FROM invoice "
                    + "INNER JOIN invoice_item ON invoice.id = invoice_item.invoice_id "
                    + "INNER JOIN stock ON invoice_item.stock_stock_id = stock.stock_id "
                    + "INNER JOIN grn_item ON stock.stock_id = grn_item.stock_stock_id "
                    + "GROUP BY YEAR(invoice.date), MONTH(invoice.date) "
                    + "ORDER BY YEAR(invoice.date) DESC, MONTH(invoice.date) DESC";

            PreparedStatement statement = MySQL2.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int year = resultSet.getInt("year");
                int month = resultSet.getInt("month");
                double totalRevenue = resultSet.getDouble("total_revenue");
                double totalCogs = resultSet.getDouble("total_cogs");
                double netIncome = resultSet.getDouble("net_income");

                String monthName = YearMonth.of(year, month).getMonth().toString();

                Map<String, Double> incomeDetails = new HashMap<>();
                incomeDetails.put("Revenue", totalRevenue);
                incomeDetails.put("COGS", totalCogs);
                incomeDetails.put("Net Income", netIncome);

                monthlyIncomeData.put(year + " " + monthName, incomeDetails);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "NewJpanel getMonthlyIncome Exception", e);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "NewJpanel getMonthlyIncome Exception", ex);
        }

        return monthlyIncomeData;
    }

    // Fetch Yearly Revenue, COGS, and Net Income from Database
    private Map<Integer, Map<String, Double>> getYearlyIncomeFromDatabase() {
        Map<Integer, Map<String, Double>> yearlyIncomeData = new HashMap<>();

        try {
            // SQL query to get yearly data (Revenue, COGS, and Net Income)
            String query = "SELECT YEAR(invoice.date) AS year, "
                    + "SUM(invoice.paid_amount - IFNULL(invoice.discount, 0)) AS total_revenue, "
                    + "SUM(invoice_item.qty * grn_item.buy_price) AS total_cogs, "
                    + "SUM(invoice.paid_amount - IFNULL(invoice.discount, 0) - (invoice_item.qty * grn_item.buy_price)) AS net_income "
                    + "FROM invoice "
                    + "INNER JOIN invoice_item ON invoice.id = invoice_item.invoice_id "
                    + "INNER JOIN stock ON invoice_item.stock_stock_id = stock.stock_id "
                    + "INNER JOIN grn_item ON stock.stock_id = grn_item.stock_stock_id "
                    + "GROUP BY YEAR(invoice.date) "
                    + "ORDER BY YEAR(invoice.date) DESC";

            PreparedStatement statement = MySQL2.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int year = resultSet.getInt("year");
                double totalRevenue = resultSet.getDouble("total_revenue");
                double totalCogs = resultSet.getDouble("total_cogs");
                double netIncome = resultSet.getDouble("net_income");

                Map<String, Double> incomeDetails = new HashMap<>();
                incomeDetails.put("Revenue", totalRevenue);
                incomeDetails.put("COGS", totalCogs);
                incomeDetails.put("Net Income", netIncome);

                yearlyIncomeData.put(year, incomeDetails);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "NewJpanel getYearlyIncomeFromDatabase Exception", e);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "NewJpanel getYearlyIncomeFromDatabase Exception", ex);
        }

        return yearlyIncomeData;
    }

    public void getSupplierCount() {
        try {
            String query = "SELECT COUNT(*) AS supplier_count FROM `supplier`";

            PreparedStatement stmt = MySQL2.getConnection().prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int supplierCount = resultSet.getInt("supplier_count");
                jLabel6.setText("" + supplierCount);
            }
            resultSet.close();
            stmt.close();

        } catch (Exception e) {
//            e.printStackTrace();
            logger.log(Level.WARNING, "NewJpanel getSupplierCount Exception", e);
        }
    }

    public void getbrandCount() {
        try {
            String query = "SELECT COUNT(*) AS brand_count FROM `brand`";

            PreparedStatement stmt = MySQL2.getConnection().prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int brand_count = resultSet.getInt("brand_count");
                jLabel18.setText("" + brand_count);
            }
            resultSet.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "NewJpanel getbrandCount Exception", e);
        }
    }

    public void getProductCount() {
        try {
            String query = "SELECT COUNT(*) AS Product_count FROM `product`";

            PreparedStatement stmt = MySQL2.getConnection().prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int Product_count = resultSet.getInt("Product_count");
                jLabel12.setText("" + Product_count);
            }
            resultSet.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "NewJpanel getProductCount Exception", e);
        }
    }

    public void getCategoryCount() {
        try {
            String query = "SELECT COUNT(*) AS category_count FROM `category`";

            PreparedStatement stmt = MySQL2.getConnection().prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int category_count = resultSet.getInt("category_count");
                jLabel15.setText("" + category_count);
            }
            resultSet.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "NewJpanel getCategoryCount Exception", e);
        }
    }
//

    public void getEmployeeCount() {
        try {
            String query = "SELECT COUNT(*) AS employee_count FROM `employee`";

            PreparedStatement stmt = MySQL2.getConnection().prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int employeeCount = resultSet.getInt("employee_count");
                jLabel3.setText("" + employeeCount);
            }
            resultSet.close();
            stmt.close();

        } catch (Exception e) {
//            e.printStackTrace();
            logger.log(Level.WARNING, "NewJpanel getEmployeeCount Exception", e);
        }
    }

    public void getCustomerCount() {
        try {
            String query = "SELECT COUNT(*) AS customer_count FROM `customer`";

            PreparedStatement stmt = MySQL2.getConnection().prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int customer_count = resultSet.getInt("customer_count");
                jLabel9.setText("" + customer_count);
            }
            resultSet.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "NewJpanel getCustomerCount Exception", e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanel1 = new javax.swing.JPanel();
        jpanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        setMaximumSize(new java.awt.Dimension(1150, 580));
        setMinimumSize(new java.awt.Dimension(1150, 580));
        setPreferredSize(new java.awt.Dimension(1150, 580));

        jpanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jpanel1.setMaximumSize(new java.awt.Dimension(515, 258));
        jpanel1.setMinimumSize(new java.awt.Dimension(515, 258));

        javax.swing.GroupLayout jpanel1Layout = new javax.swing.GroupLayout(jpanel1);
        jpanel1.setLayout(jpanel1Layout);
        jpanel1Layout.setHorizontalGroup(
            jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );
        jpanel1Layout.setVerticalGroup(
            jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );

        jpanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jpanel2.setMaximumSize(new java.awt.Dimension(515, 258));
        jpanel2.setMinimumSize(new java.awt.Dimension(515, 258));
        jpanel2.setPreferredSize(new java.awt.Dimension(515, 258));

        javax.swing.GroupLayout jpanel2Layout = new javax.swing.GroupLayout(jpanel2);
        jpanel2.setLayout(jpanel2Layout);
        jpanel2Layout.setHorizontalGroup(
            jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpanel2Layout.setVerticalGroup(
            jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jPanel1.setMaximumSize(new java.awt.Dimension(220, 250));
        jPanel1.setMinimumSize(new java.awt.Dimension(220, 250));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/worker.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Employee Count");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jPanel2.setMaximumSize(new java.awt.Dimension(220, 250));
        jPanel2.setMinimumSize(new java.awt.Dimension(220, 250));
        jPanel2.setPreferredSize(new java.awt.Dimension(220, 250));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/suppliercount.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Supplier Count");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("jLabel3");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jPanel3.setPreferredSize(new java.awt.Dimension(220, 250));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/suppliercount.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Customer Count");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("jLabel3");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/icons8-product-60.png"))); // NOI18N
        jLabel10.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setText("Product Count");
        jLabel11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setText("jLabel12");
        jLabel12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/category.png"))); // NOI18N
        jLabel13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setText("Category Count");
        jLabel14.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 0, 0));
        jLabel15.setText("jLabel12");
        jLabel15.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resosurcs/brand.png"))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setText("Brand Count");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 0, 0));
        jLabel18.setText("jLabel12");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jpanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jpanel1;
    private javax.swing.JPanel jpanel2;
    // End of variables declaration//GEN-END:variables
}
