package model;


import static gui.SplashWindow.logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import model.MySQL2;

/**
 * Class to handle automatic OUT TIME updates
 * This class can be called from main application's initialization or shutdown
 */
public class AutoOutTimeManager {
    
    /**
     * Sets default OUT TIME (5:00 PM) for all employees who haven't marked out today
     * Can be called at application shutdown or from a scheduled task
     */
    public static void updateMissingOutTimes() {
        LocalDate today = LocalDate.now();
        Time defaultOutTime = Time.valueOf(LocalTime.of(17, 0)); // 5:00 PM
        
        String query = "UPDATE `employee_attandance` " +
                       "SET `OutTime` = ? " +
                       "WHERE `Date` = ? AND `OutTime` IS NULL";
        
        try (Connection conn = MySQL2.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setTime(1, defaultOutTime);
            ps.setDate(2, java.sql.Date.valueOf(today));
            
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                logger.log(Level.INFO, "Set default OUT TIME (5:00 PM) for {0} employees", rowsAffected);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error setting default OUT TIME", e);
        }
    }
    
    /**
     * Start a background thread that periodically checks and sets default OUT TIME
     * Call this method when your application starts
     */
    public static void startAutoOutTimeMonitor() {
        Thread monitorThread = new Thread(() -> {
            while (true) {
                try {
                    // Get current time
                    LocalTime now = LocalTime.now();
                    
                    // If it's after work hours (e.g., after 5:15 PM), set default OUT TIME
                    if (now.isAfter(LocalTime.of(17, 15))) {
                        updateMissingOutTimes();
                        
                        // Wait until next day to check again
                        Thread.sleep(calculateTimeUntilNextWorkday());
                    } else {
                        // Check again in 15 minutes
                        Thread.sleep(15 * 60 * 1000);
                    }
                } catch (InterruptedException e) {
                    // Thread interrupted, exit loop
                    break;
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error in auto out time monitor", e);
                    try {
                        // Wait a while before trying again
                        Thread.sleep(30 * 60 * 1000);
                    } catch (InterruptedException ie) {
                        break;
                    }
                }
            }
        });
        
        monitorThread.setDaemon(true); // Make this a daemon thread so it won't prevent app from exiting
        monitorThread.start();
    }
    
    /**
     * Add this method to application shutdown hook/listeners
     * For example, call this when the main window is closing
     */
    public static void shutdownHook() {
        // Update all missing OUT TIMEs when application shuts down
        updateMissingOutTimes();
    }
    
    /**
     * Calculate time in milliseconds until the next workday
     * @return time in milliseconds
     */
    private static long calculateTimeUntilNextWorkday() {
        LocalTime now = LocalTime.now();
        long millisUntilMidnight = LocalTime.of(23, 59, 59).toNanoOfDay() / 1000000 - now.toNanoOfDay() / 1000000;
        
        // Add 8 hours (8AM next day) to give some buffer before next workday
        return millisUntilMidnight + (8 * 60 * 60 * 1000);
    }
}