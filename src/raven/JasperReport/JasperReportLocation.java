package raven.JasperReport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JasperReportLocation {

    // Singleton instance
    private static JasperReportLocation instance;

    // Properties
    private String teacherSlipReport;
    private String studentSlipReport;
    private String paySlipReport;

    // Private constructor to prevent instantiation
    private JasperReportLocation() {
//        FileReader reader;
        Properties p = new Properties();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            // Adjust the relative path according to your directory structure
            File file = new File(classLoader.getResource("raven/JasperReport/config.properties").toURI());

            try (FileReader reader = new FileReader(file)) {
                p.load(reader);

                // Initialize properties
                teacherSlipReport = p.getProperty("teacherSlipReport");
                studentSlipReport = p.getProperty("studentSlipReport");
                paySlipReport = p.getProperty("paySlipReport");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JasperReportLocation.class.getName()).log(Level.SEVERE, "Properties file not found", ex);
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(JasperReportLocation.class.getName()).log(Level.SEVERE, "Error loading properties", ex);
        }
    }

    // Public method to provide access to the instance
    public static JasperReportLocation getInstance() {
        if (instance == null) {
            synchronized (JasperReportLocation.class) {
                if (instance == null) { // Double-checked locking
                    instance = new JasperReportLocation();
                }
            }
        }
        return instance;
    }

    // Getter methods
    public String getTeacherSlipReport() {
        return teacherSlipReport;
    }

    public String getStudentSlipReport() {
        return studentSlipReport;
    }

    public String getPaySlipReport() {
        return paySlipReport;
    }
}
