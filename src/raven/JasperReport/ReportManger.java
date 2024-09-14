/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.JasperReport;
import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author anurag
 */
public class ReportManger {
    private static ReportManger instance;
    public static ReportManger getInstance() {
        if (instance == null) {
            instance = new ReportManger();
        }
        return instance;
    }
    private JasperReport Report;
    private JasperPrint print;

   
//    private JasperReport reportInvoices;

    private ReportManger() {
    }
    
    public void ReportMangerX(String ReportPathString,HashMap<String,Object> ReportParmeterHashMap) {
      
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(ReportPathString);
            Report = JasperCompileManager.compileReport(jasperDesign);
            print = JasperFillManager.fillReport(Report, ReportParmeterHashMap);
            viewReport();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
//            Logger.getLogger(ReportManger.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void viewReport() {
        JasperViewer.viewReport(print,false);
    }
    public byte[] exportReport(String format) {
        switch (format) {
            case "pdf":
                return exportToPdf(print);
            case "xml":
                return exportToXml(print);
            default:
                throw new IllegalArgumentException("Unknown report format: " + format);
        }
    }

    private byte[] exportToPdf(JasperPrint print) {
        try {
            return JasperExportManager.exportReportToPdf(print);
        } catch (JRException e) {
            throw new RuntimeException("Error exporting report to PDF", e);
        }
    }

    
    public void savePdfToFile(String filePath) {
        try {
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(pdfBytes);
            } catch (IOException e) {
                throw new RuntimeException("Error saving PDF to file", e);
            }
        } catch (JRException ex) {
            Logger.getLogger(ReportManger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    private byte[] exportToXml(JasperPrint print) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToXmlStream(print, outputStream);
            return outputStream.toByteArray();
        } catch (JRException e) {
            throw new RuntimeException("Error exporting report to XML", e);
        }
    }
}