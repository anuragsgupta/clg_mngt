/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.forms.other;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 *
 * @author anurag
 */
public class test {

    public static String formatIndianNumber(int number) {
        // Define the pattern for Indian number format
        String pattern = "##,##,###";
        // Create a DecimalFormatSymbols object to use a comma as the grouping separator
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');

        // Create a DecimalFormat object with the pattern and symbols
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);

        // Format the input number using the DecimalFormat object
        return decimalFormat.format(number);
    }
  public static String convertDate(String dateString) {
        // Split the input date string into components using "-"
        String[] dateComponents = dateString.split("-");
        
        // Extract the year and day from the components
        String year = dateComponents[0];
        String month = dateComponents[1];
        
        // Concatenate the day and the last two characters of the year
        String formattedDate =  year.substring(2) + month;
        
        // Return the formatted date string
        return formattedDate;
    }
    public static void main(String[] args) {
        HashMap<String, String> ui = new HashMap<>();

        LocalDate currentDate = LocalDate.now();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMM");
        String formattedDate = convertDate("2025-04-09");

        // Display the formatted date
        System.out.println("Formatted date: " + formattedDate);
        
        String q = "select Teacher_id,FirstName,LastName from `college_management`.`teacherform`;";
        String defaul = "123456789112";
        System.err.println(defaul.substring(8));
        
//    
//Connection sqlcon = DatabaseConnection.getInstance().getConnection();
//        try (PreparedStatement query = sqlcon.prepareStatement(q)) {
//            ResultSet rs = query.executeQuery();
//
//            while (rs.next()) {
////                        label.setText(rs.getString(1));
//                String teacherIds = rs.getString("Teacher_id");
//                String teacherNames = rs.getString("FirstName") + " " + rs.getString("LastName");
//
//                ui.put(teacherIds, teacherNames);
////                      
//                System.out.println("ID " + teacherIds + " Fname: "  + teacherNames);
//
//            }
//                  for (HashMap.Entry<String,String> entry : ui.entrySet()) { 
//                            System.out.println("Key = " + entry.getKey() + 
//                             ", Value = " + entry.getValue()); 
//    } 
//
//            query.close();
//            sqlcon.close();
//
//        } catch (SQLException aw) {
//            System.out.println(aw.getMessage());
//        }
//        

    }

    /**
     * Converts a given number into an abbreviated form (e.g., 60k, 8L).
     *
     * @param number the input number to convert
     * @return the abbreviated form of the number
     */
}
