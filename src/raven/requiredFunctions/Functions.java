package raven.requiredFunctions;

//package std_mngt;
//import com.google.zxing.BarcodeFormat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.proteanit.sql.DbUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import raven.toast.Notifications;

public class Functions {

    private static final String[] maleNames = {"Aarav", "Aryan", "Vijay", "Rahul", "Raj", "Kumar", "Amit", "Sunil", "Ramesh", "Sanjay"};
    private static final String[] femaleNames = {"Sita", "Priya", "Neeta", "Anita", "Pooja", "Rani", "Neha", "Sneha", "Kavita", "Pallavi"};
    private static final String[] surnames = {"Sharma", "Verma", "Gupta", "Singh", "Yadav", "Patel", "Joshi", "Malik", "Kumar", "Chauhan"};

    public static boolean isAbove15YearsOld(String dobString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob;
        System.out.println(sdf.getCalendar());
        try {
            dob = sdf.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
            return false; // Invalid date format
        }

        Calendar dobCalendar = Calendar.getInstance();
        dobCalendar.setTime(dob);

        Calendar fifteenYearsAgo = Calendar.getInstance();
        fifteenYearsAgo.add(Calendar.YEAR, -15);
        System.out.println("is valid date in fuction " + dobCalendar.before(fifteenYearsAgo));

        return dobCalendar.before(fifteenYearsAgo);
    }

    public static String abbreviateNumber(int number) {
        // Define the thresholds for each abbreviation
        int thousand = 1_000;
        int million = 1_000_000;

        if (number >= million) {
            // Million range
            return String.valueOf(number / million);
        } else if (number >= thousand) {
            // Thousand range
            return String.valueOf(number / thousand);
        } else {
            // Number is less than 1,000, return the number as is
            return String.valueOf(number);
        }
    }

    public static boolean isValidPanCardNo(String panCardNo) {

        // Regex to check valid PAN Card number.
        String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the PAN Card number
        // is empty return false
        if (panCardNo == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given
        // PAN Card number using regular expression.
        Matcher m = p.matcher(panCardNo);

        // Return if the PAN Card number
        // matched the ReGex
        return m.matches();
    }

    public static String[] getYearOptions() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String[] yearOptions = {"2021",
            String.valueOf(currentYear - 1), // Previous year
            String.valueOf(currentYear), // Current year
            String.valueOf(currentYear + 1) // Next year
    };
        return yearOptions;
    }

//    public staic
    public static void tableDataToExcel(JTable table, File file) {
        // this export function really works
        try {
            WritableWorkbook workbook1 = jxl.Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0);
            TableModel model = table.getModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                String columnName = model.getColumnName(i);
                jxl.write.Label column = new jxl.write.Label(i, 0, columnName != null ? columnName : "");
                sheet1.addCell(column);
            }

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Object cellValue = model.getValueAt(i, j);
                    String cellValueAsString = (cellValue != null) ? cellValue.toString() : "";
                    jxl.write.Label row = new jxl.write.Label(j, i + 1, cellValueAsString);
                    sheet1.addCell(row);
                }
            }
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Export Data To Successfully ");

            workbook1.write();
            workbook1.close();
        } catch (IOException | WriteException ex) {
            ex.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Export Data To Failed ");
            System.out.println(ex.getMessage());
        }
    }

    public static void fillTableModelFilter(JTable table, Connection sqlcon, String branch, String batch, String Caste, String gender) throws SQLException {
//        model.setRowCount(0); // refresh the table

        try {
//            String query = "SELECT * FROM admissionform where "+ Query + " ;"; // Replace with your SQL query
            String query = "SELECT *\n"
                    + "FROM studentform\n"
                    + "WHERE  cast(batch as varchar) like ? and Category LIKE ? and branch like ? and gender like ?;"; // Replace with your SQL query
            PreparedStatement preparedStatement = sqlcon.prepareStatement(query);
            preparedStatement.setString(1, batch);
            preparedStatement.setString(2, Caste);
            preparedStatement.setString(3, branch);
            preparedStatement.setString(4, gender);

            ResultSet resultSet = preparedStatement.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            preparedStatement.close();
            resultSet.close();
            System.err.println("Table Update");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void fillTableModelFilter(JTable table, String branch, String batchFrom, String batchTo, String Caste, String gender) throws SQLException {
//        model.setRowCount(0); // refresh the table

        try {

//            String query = "SELECT * FROM admissionform where "+ Query + " ;"; // Replace with your SQL query
            String query = "select t.teacher_id,t.FirstName || ' ' || t.LastName as \"Full Name\",t.pan_card,t.department,\n"
                    + "       TO_CHAR(salary.Date, 'YYYY-Mon') as \"Month\",base_pay as \"Basic Pay\", dearness_allowance as \"Dearness Allowance\", professional_allowance as \"Professional Allowance\",\n"
                    + "       nps_emp_contribution as \"NPS Employee Contribution\", tds as \"TDS\", nps_empr_contribution as \"NPS Employee Contribution\", insurance, net_pay as \"Net Pay\", total_pay as \"Total Pay\", da_at as \"DA %\", nps_at as \"NPS %\",\n"
                    + "       tnps_at as \"NPS Employeer Contribution\", status\n"
                    + "from salary left join teacherform t on t.teacher_id = salary.teacher_id where t.Department like ? and salary.Date between '" + batchFrom + "' and '" + batchTo + "' and t.Category like ? and t.Gender like ? ;"; // Replace with your SQL query
            Connection sqlcon = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = sqlcon.prepareStatement(query);
            preparedStatement.setString(1, branch);
//            preparedStatement.setString(2, batch);
            preparedStatement.setString(2, Caste);
            preparedStatement.setString(3, gender);

            ResultSet resultSet = preparedStatement.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            preparedStatement.close();
//                while (resultSet.next()) {
            resultSet.close();
            System.err.println("Table Update");
//
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void searchbar(JTable table, String str) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        table.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter("(?i)" + str));
    }

    public static void exportToExcel(JTable readTable) {
        JFileChooser fileChooser = new JFileChooser();

        // Set a file filter to allow only Excel files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls");
        fileChooser.setFileFilter(filter);

        // Show the save dialog
        int returnVal = fileChooser.showSaveDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Ensure the file ends with .xls extension
            if (!file.getAbsolutePath().endsWith(".xls")) {
                file = new File(file.getAbsolutePath() + ".xls");
            }

            // Call the method to export the table data
            tableDataToExcel(readTable, file);

            JOptionPane.showMessageDialog(null, "Data saved at '" + file.getAbsolutePath() + "' successfully",
                    "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static boolean isValidString(String input) {
        String camecase = toCamelCase(input);
        // Check if the input string contains symbols or numbers
        if (!camecase.matches("^[a-zA-Z]+$")) {
            return false;
        }
        // Check if the input string has a length less than 3
        // If all checks pass, return true

        return input.length() >= 2;
    }

    public static boolean isValidMobileNumber(String phoneNumber) {
        // Define a regular expression pattern for a 10-digit mobile number
        String mobileNumberRegex = "^[0-9]{10}$";

        // Check if the input phone number matches the pattern
        return phoneNumber.matches(mobileNumberRegex);
    }

    public static boolean isValidAadharNumber(String aadharNumber) {
        // Define a regular expression pattern for a 10-digit mobile number
        String aadharNumberRegex = "^[0-9]{12}$";

        // Check if the input phone number matches the pattern
        return aadharNumber.matches(aadharNumberRegex);
    }

    public static boolean isValidEnrollmentNumber(String enrollmentNumber) {
        // Define a regular expression pattern for a 10-digit mobile number
        String enrollmentNumberRegex = "^[0-9]{12}$";

        // Check if the input phone number matches the pattern
        return enrollmentNumber.matches(enrollmentNumberRegex);
    }

    public static boolean isvalid10marks(String marks) {
        // Define a regular expression pattern for a valid format (two digits, a decimal point, and one digit)
        String marksRegex = "^[0-9]{2}\\.[0-9]$";

        // Check if the input marks match the valid format pattern
        boolean isValidFormat = marks.matches(marksRegex);

        if (!isValidFormat) {
            return false; // Marks do not match the valid format
        }

        // Parse the marks as a double
        double marksValue = Double.parseDouble(marks);
        // Check if the marks are less than 40 or greater than 99.9
        // Marks are in a valid format and within the desired range

        return !(marksValue < 33.0 || marksValue > 99.9);
    }

    public static boolean isValidEmail(String email) {
        // Define a regular expression pattern for a basic email validation
        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";

        // Compile the pattern
        Pattern pattern = Pattern.compile(emailRegex);

        // Match the input email against the pattern
        Matcher matcher = pattern.matcher(email);

        // Return true if it's a valid email, false otherwise
        return matcher.matches();
    }

    public static String toCamelCase(String input) {
        StringBuilder camelCase = new StringBuilder();
        boolean capitalizeNext = false;

        for (char c : input.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                camelCase.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                camelCase.append(Character.toLowerCase(c));
            }
        }

        return camelCase.toString();
    }

    public static void showDateTime(JLabel showMsm) {
        Timer timer
                = new Timer(
                        1000, (ActionEvent e) -> {
                            // Get the current date and time
                            Date now = new Date();

                            // Format the date and time as a string
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String formattedDateTime = dateFormat.format(now);

                            // Update the label with the formatted date and time
                            showMsm.setText("Current Date and Time: " + formattedDateTime);
                        });
        timer.start();
    }

    public static void checkNameFormatter(JTextField TF) {
        TF.addFocusListener(
                new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                String currentText = TF.getText();
                // Capitalize the text and remove whitespace
                String cleanedText = currentText.toLowerCase().replaceAll("\\s+", "");
                if (isValidString(cleanedText)) {
                    TF.setText(cleanedText);
                    TF.setBackground(Color.WHITE);
                } else {
                    TF.setBackground(Color.RED);
                }
            }
        });
    }

    public static void printTable(JTable table) {

        try {
            table.print(JTable.PrintMode.NORMAL);
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static String totalCountStudents(
            String count, String whereEqualTo, int batch) {
        //                 totalCountQueryStudents(TotalMaleStudentsCount,"Gender","male",selected);
        String url = "jdbc:mysql://localhost:3306/students";

        try {
            Connection sqlcon = DriverManager.getConnection(url, "root", "1379");
            System.out.println("        Connected  SuccessFully 😊😊");

            String q
                    = "select COUNT(" + count + ") from admissionform where " + count + " = ? && batch = ?;";
            PreparedStatement query = sqlcon.prepareStatement(q);
            query.setString(1, whereEqualTo);
            query.setInt(2, batch);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
//        label.setText(rs.getString(1));
                System.out.println("Total " + count + " Enrolled Students : " + rs.getString(1));
                query.close();
                sqlcon.close();
                return rs.getString(1);
            }

        } catch (SQLException aw) {
            System.err.println(aw.getMessage());
        }
        return null;
    }

    public static void totalCountQueryStudents(
            JLabel label, String count, String whereEqualTo, int batch) {
        //                 totalCountQueryStudents(TotalMaleStudentsCount,"Gender","male",selected);
        String url = "jdbc:mysql://localhost:3306/students";

        try {
            try (Connection sqlcon = DriverManager.getConnection(url, "root", "1379")) {
                System.out.println("        Connected  SuccessFully 😊😊");

                String q
                        = "select COUNT(" + count + ") from admissionform where " + count + " = ? && batch = ?;";
                try (PreparedStatement query = sqlcon.prepareStatement(q)) {
                    query.setString(1, whereEqualTo);
                    query.setInt(2, batch);

                    ResultSet rs = query.executeQuery();

                    while (rs.next()) {
                        label.setText(rs.getString(1));
                        System.out.println("Total " + count + " Enrolled Students : " + rs.getString(1));
                    }
                }
            }
        } catch (SQLException aw) {
            System.out.println(aw.getMessage());
        }
    }

    public static HashMap<String, Double> totalCategoryStudents(int batch) {

        HashMap<String, Double> map = new HashMap<>();

        try (Connection sqlcon = DatabaseConnection.getInstance().getConnection()) {

            System.out.println("        Connected  SuccessFully 😊😊");

            String q = "SELECT category,COUNT(*) AS count FROM students.admissionform where batch = ? GROUP BY \n"
                    + " category;";
            try (PreparedStatement query = sqlcon.prepareStatement(q)) {
                query.setInt(1, batch);

                ResultSet rs = query.executeQuery();

                // Adding elements to the Map
                // using standard put() method
//                System.err.println(rs.next());
                while (rs.next()) {
                    String category = rs.getString("category");
                    Double count = Double.valueOf(String.valueOf(rs.getInt("count")));
                    System.err.println(category + " " + count);
                    map.put(category, count);
                }
            }
            sqlcon.close();

        } catch (SQLException aw) {
            System.out.println(aw.getMessage());
        }
        return map;

    }

    public static HashMap<String, Integer> totalYearlyStudents() {
        String url = "jdbc:mysql://localhost:3306/students";
        HashMap<String, Integer> map = new HashMap<>();

        try (Connection sqlcon = DriverManager.getConnection(url, "root", "1379")) {

            System.out.println("        Connected  SuccessFully 😊😊");

            String q = "SELECT \n"
                    + "    batch,\n"
                    + "    branch,\n"
                    + "    COUNT(*) AS total_students\n"
                    + "FROM \n"
                    + "    students.admissionform\n"
                    + "GROUP BY \n"
                    + "    batch, branch;";
            try (PreparedStatement query = sqlcon.prepareStatement(q)) {

                ResultSet rs = query.executeQuery();

                // Adding elements to the Map
                // using standard put() method
//                System.err.println(rs.next());
                while (rs.next()) {

                    System.err.println(rs.getString("batch") + " " + rs.getString("branch") + " " + rs.getInt("total_students"));
//                   map.put(rs.getString("batch"), {})
                }
            }
            sqlcon.close();

        } catch (SQLException aw) {
            System.out.println(aw.getMessage());
        }
        return map;

    }

    public static void main(String[] args) {
//        try (Connection slqConnection = DatabaseConnection.getInstance().getConnection()) {
//            System.err.println(slqConnection.getMetaData());
//            totalCategoryStudents(2021);
//        } catch (SQLException ex) {
//            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
//
//}
        HashMap<String, Double> map = totalCategoryStudents(2021);

    }

    public static InputStream generateQrcode(String qrString) {

        String invoice = qrString;
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix;
        ByteArrayOutputStream outputStream = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(invoice, BarcodeFormat.QR_CODE, 100, 100, hints);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);

        } catch (WriterException | IOException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    public static void totalStudents(JLabel label, int batch) {
        String url = "jdbc:mysql://localhost:3306/students";

        try {
            Connection sqlcon = DriverManager.getConnection(url, "root", "1379");
            System.out.println("        Connected  SuccessFully 😊😊");

            String q = "select COUNT(aadhar) from admissionform where batch = ?;";
            PreparedStatement query = sqlcon.prepareStatement(q);
            query.setInt(1, batch);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                label.setText(rs.getString(1));
                System.out.println("Total  Enrolled Students : " + rs.getString(1));
            }
            query.close();
            sqlcon.close();

        } catch (SQLException aw) {
            System.out.println(aw.getMessage());
        }
    }

    public static int totalStudents(String branch, int batch) {
        String url = "jdbc:mysql://localhost:3306/students";
        int totalStudent = 0;
        try {
            Connection sqlcon = DriverManager.getConnection(url, "root", "1379");
            System.out.println("        Connected  SuccessFully 😊😊");

            String q = "select COUNT(aadhar) from admissionform where branch = ? and batch = ?;";
            PreparedStatement query = sqlcon.prepareStatement(q);
            query.setString(1, branch);
            query.setInt(2, batch);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                System.out.println("Total  Enrolled Students : " + rs.getString(1));
                totalStudent = Integer.parseInt(rs.getString(1));
            }
            query.close();
            sqlcon.close();

        } catch (SQLException aw) {
            System.out.println(aw.getMessage());
        }
        return totalStudent;
    }

    public static HashMap<String, Integer> totalStudentsYearly(int batch) {
        String url = "jdbc:mysql://localhost:3306/students";
        HashMap<String, Integer> map = new HashMap<>();

        try {
            try (Connection sqlcon = DatabaseConnection.getInstance().getConnection()) {
                System.out.println("        Connected  SuccessFully 😊😊");

                String q = "SELECT   branch, COUNT(*) AS count FROM students.admissionform where batch = ? \n"
                        + " GROUP BY  batch, branch ";
                PreparedStatement query = sqlcon.prepareStatement(q);

                query.setInt(1, batch);

                ResultSet rs = query.executeQuery();

                while (rs.next()) {
                    String branch = rs.getString("branch");
                    int count = rs.getInt("count");
                    System.err.println(branch + " " + count);
                    map.put(branch, count);
                }
                query.close();
            }

        } catch (SQLException aw) {
            System.out.println(aw.getMessage());

        }
        return map;
    }

    public static HashMap<String, Integer> totalGenderStudentsYearly(int batch) {
        String url = "jdbc:mysql://localhost:3306/students";
        HashMap<String, Integer> map = new HashMap<>();

        try {
            Connection sqlcon = DatabaseConnection.getInstance().getConnection();
            System.out.println("        Connected  SuccessFully 😊😊");

            String q = "SELECT gender, COUNT(*) AS count FROM students.admissionform where batch = ? GROUP BY gender ;";
            PreparedStatement query = sqlcon.prepareStatement(q);

            query.setInt(1, batch);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                String gender = rs.getString("gender");
                int count = rs.getInt("count");
                System.err.println(gender + " " + count);
                map.put(gender, count);
            }
            query.close();
            sqlcon.close();

        } catch (SQLException aw) {
            System.out.println(aw.getMessage());

        }
        return map;
    }

    public static void exportTable(JTable table, JLabel showMsm) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet ws = wb.createSheet();
        TreeMap<String, Object[]> map = new TreeMap<>();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        map.put(
                "0",
                new Object[]{
                    model.getColumnName(0),
                    model.getColumnName(1),
                    model.getColumnName(2),
                    model.getColumnName(3),
                    model.getColumnName(4),
                    model.getColumnName(5),
                    model.getColumnName(6),
                    model.getColumnName(7),
                    model.getColumnName(8),
                    model.getColumnName(9),
                    model.getColumnName(10),
                    model.getColumnName(11),
                    model.getColumnName(12),
                    model.getColumnName(13),
                    model.getColumnName(14),
                    model.getColumnName(15),
                    model.getColumnName(16)
                });

        for (Map.Entry<String, Object[]> entry : map.entrySet()) {
            String key = entry.getKey();
            Object[] Value = entry.getValue();
            System.out.println(Arrays.toString(Value));
        }

        for (int i = 1; i < model.getRowCount(); i++) {
            map.put(
                    Integer.toString(i),
                    new Object[]{
                        model.getValueAt(i, 0),
                        model.getValueAt(i, 1),
                        model.getValueAt(i, 2),
                        model.getValueAt(i, 3),
                        model.getValueAt(i, 4),
                        model.getValueAt(i, 5),
                        model.getValueAt(i, 6),
                        model.getValueAt(i, 7),
                        model.getValueAt(i, 8),
                        model.getValueAt(i, 9),
                        model.getValueAt(i, 10),
                        model.getValueAt(i, 11),
                        model.getValueAt(i, 12),
                        model.getValueAt(i, 13),
                        model.getValueAt(i, 14),
                        model.getValueAt(i, 15),
                        model.getValueAt(i, 16),});
        }
        Set<String> id = map.keySet();
        XSSFRow fRow;
        int rowId = 0;

        for (String key : id) {
            fRow = ws.createRow(rowId++);
            Object[] value = map.get(key);
            int cellId = 0;
            for (Object object : value) {
                XSSFCell cell = fRow.createCell(cellId++);
                cell.setCellValue(object.toString());
            }
            try {

                JFileChooser sd = new JFileChooser();
                sd.showOpenDialog(null);
                File f = sd.getSelectedFile();
                String path = f.getAbsolutePath();
                path = path + ".xlsx";

                FileOutputStream fos = new FileOutputStream(new File(path));
                System.out.println(sd);
                wb.write(fos);
                JOptionPane.showMessageDialog(null, "Export Successfully");
//        fos.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
    }

    public static DefaultTableModel createTableModel() {
        DefaultTableModel model = new DefaultTableModel();

        // Add columns to the model based on the column names
        model.addColumn("First Name");             // 1
        model.addColumn("Last Name");              // 2
        model.addColumn("Father Name");            // 3
        model.addColumn("Email ID");               // 4
        model.addColumn("Mobile Number");          // 5
        model.addColumn("Marks 10th");             // 6
        model.addColumn("Aadhar");                 // 7
        model.addColumn("Enrollment");             // 8
        model.addColumn("Rollno");                 // 9
        model.addColumn("DOB");                    // 10
        model.addColumn("Branch");                 // 11
        model.addColumn("Batch");                  // 12
        model.addColumn("Category");               // 13
        model.addColumn("Gender");                 // 14
        model.addColumn("Address");                // 15
        model.addColumn("Scholarship");            // 16

        return model;
    }

    public static void fillTableModel(DefaultTableModel model) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/students";
        Connection sqlcon = null;

        sqlcon = DriverManager.getConnection(url, "root", "1379");

        String query = "SELECT * FROM admissionform"; // Replace with your SQL query
        PreparedStatement preparedStatement = sqlcon.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            // Retrieve data from the ResultSet and add it to the model
            String[] rowData = {
                resultSet.getString(2), // 1 corresponds to the  column (firstName)
                resultSet.getString(3), // 2 corresponds to the column (Student_Last_Name)
                resultSet.getString(4), // 3 corresponds to the column (Student_Father_Name)
                resultSet.getString(5), // 4 corresponds to the column (Email_ID)
                resultSet.getString(6), // 5 corresponds to the column (Student_Mobile_Number)
                resultSet.getString(7), // 6 corresponds to the column (marks10th)
                resultSet.getString(8), // 7 corresponds to the column (aadhar)
                resultSet.getString(9), // 8 corresponds to the column (enrollment)
                //
                resultSet.getString(10), // 10 corresponds to the ninth column (rollno)
                resultSet.getString(11), // 10 corresponds to the ninth column (Dob)
                resultSet.getString(12), // 10 corresponds to the tenth column (branch)
                resultSet.getString(13), // 11 corresponds to the eleventh column (Batch)
                resultSet.getString(14), // 12 corresponds to the twelfth column (category)
                resultSet.getString(15), // 13 corresponds to the thirteenth column (Gender)
                resultSet.getString(16), // 14 corresponds to the fourteenth column (address)
                resultSet.getString(17), // 15 corresponds to the fifteenth column (scholarship)
            //                resultSet.getString(17)  // 15 corresponds to the fifteenth column (scholarship)
            };

            model.addRow(rowData);
        }

        resultSet.close();
        preparedStatement.close();
    }

    public static void viewTable_load(JTable table) {
        String url = "jdbc:mysql://localhost:3306/students";
        Connection sqlcon = null;

        try {
            sqlcon = DriverManager.getConnection(url, "root", "1379");
            String myQuery = "select * from maindetails;";
            PreparedStatement query = sqlcon.prepareStatement(myQuery);
            ResultSet rs = query.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(rs));
//     query.close();
            sqlcon.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void table_load(JTable table, Connection sqlcon, PreparedStatement query) {

        try {
            ResultSet rs = query.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            query.close();
//            sqlcon.close();

//            String myQuery = "SELECT * FROM students.maindetails WHERE BRANCH like ?;";
//            String myQuery = "SELECT * FROM students.admissionform WHERE BRANCH like ?;";
//            PreparedStatement query = sqlcon.prepareStatement(myQuery);
//            query.setString(1, batch);
            //      String[] columnNames = {"Column1", "Column3", "Column5", "Column7", "Column9"}; //
            // Replace with actual column names
            //      DefaultTableModel tableModel = new DefaultTableModel(columnNames, 5);
            //
            //      // Populate the table model with data from the ResultSet
            //      while (rs.next()) {
            //        Object[] rowData = {
            //                rs.getObject(1),  // Column 1
            //                rs.getObject(3),  // Column 3
            //                rs.getObject(5),  // Column 5
            //                rs.getObject(7),  // Column 7
            //                rs.getObject(9)   // Column 9
            //        };
            //        tableModel.addRow(rowData);
            //      }
            // Set the custom table model for your JTable
            //      table.setModel(tableModel);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void table_load(JTable table, PreparedStatement query) {

        try {
//            PreparedStatement prepquery = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet rs = query.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            query.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String generateRandomString(int length) {
        // Define the length of the desired string

        // Define characters that can be used in the random string
        String characters = "0123456789";

        // Create a StringBuilder to store the random string
        StringBuilder randomString = new StringBuilder();

        // Initialize a random number generator
        Random random = new Random();

        // Generate the random string
        for (int i = 0; i < length; i++) {
            // Get a random index from the characters string
            int randomIndex = random.nextInt(characters.length());

            // Append the character at the random index to the StringBuilder
            randomString.append(characters.charAt(randomIndex));
        }

        // Convert the StringBuilder to a String and return it
        return randomString.toString();
    }

    public static String generateRandomFirstName() {
        // Create a Random object
        Random random = new Random();

        // Generate a random index for either male or female name
        int nameTypeIndex = random.nextInt(2);

        // Get a random name based on the selected type
        String randomFirstName = (nameTypeIndex == 0) ? maleNames[random.nextInt(maleNames.length)] : femaleNames[random.nextInt(femaleNames.length)];

        return randomFirstName;
    }

    public static String generateRandomLastName() {
        // Create a Random object
        Random random = new Random();

        // Generate a random index for the surname
        int surnameIndex = random.nextInt(surnames.length);

        // Get a random last name
        String randomLastName = surnames[surnameIndex];

        return randomLastName;
    }

    public static int generateRandomDigit(int maxDigit) {
        // Create a Random object
        Random random = new Random();

        // Generate a random digit between 0 (inclusive) and maxDigit (exclusive)
        int randomDigit = random.nextInt(maxDigit);

        return randomDigit;
    }

    public static boolean isValidDateFormat(String date) {
        // Define a regular expression pattern for the "yyyy-MM-dd" format
        String dateFormatPattern = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";

        // Use Pattern.matches() to check if the input matches the pattern
        return Pattern.matches(dateFormatPattern, date);
    }

    public void printPanel(JPanel panel) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Data");

        job.setPrintable(
                new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                pf.setOrientation(PageFormat.PORTRAIT);
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX() * 2, pf.getImageableY() * 2);
                g2.scale(0.5, 0.5);

                panel.print(g2);

                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if (ok) {
            try {

                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void dateTimeDisplay(JLabel dateTimeLabel) {
        Timer timer
                = new Timer(
                        1000,
                        new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Get the current date and time
                        Date now = new Date();

                        // Format the date and time as a string
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String formattedDateTime = dateFormat.format(now);

                        // Update the label with the formatted date and time
                        dateTimeLabel.setText("Current Date and Time: " + formattedDateTime);
                    }
                });
        timer.start();
    }
}
