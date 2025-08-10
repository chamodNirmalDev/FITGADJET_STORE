package gui;

import static gui.SplashWindow.logger;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import model.MySQL2;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.logging.Level;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class AttendanceChart extends javax.swing.JPanel {

    private Timer refreshTimer;

    public AttendanceChart() {
        initComponents();

        addPieChart();
        addLineChart();

        setupRefreshTimer();
        refreshCharts();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );

        jPanel3.setPreferredSize(new java.awt.Dimension(524, 483));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void addPieChart() {
        Date today = new Date(System.currentTimeMillis());

        int totalEmployees = 0;
        int presentEmployees = 0;

        String totalEmployeeQuery = "SELECT COUNT(*) AS total FROM employee";

        String presentEmployeeQuery = "SELECT COUNT(DISTINCT employee_employee_id) AS total "
                + "FROM employee_attandance "
                + "WHERE Date = ? AND mark = 1";

        try (Connection conn = MySQL2.getConnection()) {
            try (PreparedStatement totalStmt = conn.prepareStatement(totalEmployeeQuery); 
                    ResultSet totalRs = totalStmt.executeQuery()) {
                if (totalRs.next()) {
                    totalEmployees = totalRs.getInt("total");
                }
            } catch (Exception e) {
                logger.log(Level.WARNING, "AttendanceChart in addPieChart method in totalEmployees", e);
            }

            try (PreparedStatement presentStmt = conn.prepareStatement(presentEmployeeQuery)) {
                presentStmt.setDate(1, today);
                try (ResultSet presentRs = presentStmt.executeQuery()) {
                    if (presentRs.next()) {
                        presentEmployees = presentRs.getInt("total");
                    }
                }
            } catch (Exception e) {
                logger.log(Level.WARNING, "AttendanceChart in addPieChart method in presentEmployees", e);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "AttendanceChart in addPieChart method", e);
        }

        int presentPercentage = (totalEmployees > 0) ? (presentEmployees * 100) / totalEmployees : 0;
        int absentPercentage = 100 - presentPercentage;

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Present (" + presentPercentage + "%)", presentPercentage);
        dataset.setValue("Absent (" + absentPercentage + "%)", absentPercentage);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Today's Attendance",
                dataset,
                true,
                true,
                false
        );

        PiePlot plot = (PiePlot) pieChart.getPlot();

        plot.setSectionPaint(0, new java.awt.Color(0, 150, 0));
        plot.setSectionPaint(1, new java.awt.Color(255, 0, 0));

        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(524, 483));

        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.add(chartPanel, java.awt.BorderLayout.CENTER);
        jPanel2.revalidate();
    }

    public void addLineChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String attendanceQuery = "SELECT COUNT(DISTINCT employee_employee_id) as attendance_count, "
                + "DATE_FORMAT(Date, '%Y-%m') as month_year "
                + "FROM employee_attandance "
                + "WHERE Date >= '2024-11-01' "
                + "AND mark = 1 "
                + "GROUP BY DATE_FORMAT(Date, '%Y-%m') "
                + "ORDER BY month_year";

        try (Connection conn = MySQL2.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(attendanceQuery); 
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String monthYear = rs.getString("month_year");
                double attendanceCount = rs.getDouble("attendance_count");

                String[] parts = monthYear.split("-");
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, Integer.parseInt(parts[0]));
                cal.set(Calendar.MONTH, Integer.parseInt(parts[1]) - 1);

                SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
                String formattedMonthYear = sdf.format(cal.getTime());

                dataset.addValue(attendanceCount, "Attendance", formattedMonthYear);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "AttendanceChart in addLineChart", e);
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Year Summary",
                "Month",
                "Attendance",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.WHITE);

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(173, 216, 230));
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0, 10);

        ChartPanel lineChartPanel = new ChartPanel(lineChart);
        lineChartPanel.setPreferredSize(new java.awt.Dimension(524, 483));

        jPanel3.setLayout(new BorderLayout());
        jPanel3.add(lineChartPanel, BorderLayout.CENTER);
        jPanel3.revalidate();
    }

    private void setupRefreshTimer() {
        refreshTimer = new Timer(30000, (ActionEvent e) -> {
            refreshCharts();
        });
        refreshTimer.start();
    }

    public void triggerRefresh() {
        refreshCharts();
    }

    public void cleanup() {
        if (refreshTimer != null) {
            refreshTimer.stop();
        }
    }

    public void refreshCharts() {
        jPanel2.removeAll();
        jPanel3.removeAll();

        addPieChart();
        addLineChart();

        jPanel2.revalidate();
        jPanel2.repaint();
        jPanel3.revalidate();
        jPanel3.repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
