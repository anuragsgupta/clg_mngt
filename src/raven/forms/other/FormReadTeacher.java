package raven.forms.other;

//import static raven.forms.other.JdbcUtil;
import com.formdev.flatlaf.FlatClientProperties;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import raven.JasperReport.JasperReportLocation;
import raven.JasperReport.ReportManger;
import raven.application.LoginUser;
import raven.components.SimpleForm;
import raven.menu.FormManager;
import raven.requiredFunctions.DatabaseConnection;
import static raven.requiredFunctions.Functions.createTableModel;
import static raven.requiredFunctions.Functions.generateQrcode;
import static raven.requiredFunctions.Functions.searchbar;
import static raven.requiredFunctions.Functions.tableDataToExcel;
import static raven.requiredFunctions.Functions.table_load;

/**
 *
 * @author Raven
 */
public class FormReadTeacher extends SimpleForm {

    String branhi = "%";
    String role;
    DefaultTableModel model = createTableModel();

    public void fillTableModel(DefaultTableModel model) throws SQLException {
        model.setRowCount(0);

        Connection sqlcon = DatabaseConnection.getInstance().getConnection();

        String query = "SELECT * FROM admissionform where department like ? ;"; // Replace with your SQL query
        PreparedStatement preparedStatement = sqlcon.prepareStatement(query);
        preparedStatement.setString(1, branhi);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            // Retrieve data from the ResultSet and add it to the model
            String[] rowData = {
                resultSet.getString(1), // 1 corresponds to the  column (firstName)
                resultSet.getString(2), // 2 corresponds to the column (Student_Last_Name)
                resultSet.getString(3), // 3 corresponds to the column (Student_Father_Name)
                resultSet.getString(4), // 4 corresponds to the column (Email_ID)
                resultSet.getString(5), // 5 corresponds to the column (Student_Mobile_Number)
                resultSet.getString(6), // 6 corresponds to the column (marks10th)
                resultSet.getString(7), // 7 corresponds to the column (aadhar)
                resultSet.getString(8), // 8 corresponds to the column (enrollment)
                resultSet.getString(9), // 8 corresponds to the column (enrollment)
                //
                resultSet.getString(10), // 10 corresponds to the ninth column (rollno)
                resultSet.getString(11), // 10 corresponds to the ninth column (Dob)
                resultSet.getString(12), // 10 corresponds to the tenth column (department)
                resultSet.getString(13), // 11 corresponds to the eleventh column (Batch)
                resultSet.getString(14), // 12 corresponds to the twelfth column (category)
                resultSet.getString(15), // 13 corresponds to the thirteenth column (Gender)
                resultSet.getString(16), // 14 corresponds to the fourteenth column (address)
            //                resultSet.getString(17),  // 15 corresponds to the fifteenth column (scholarship)
            };

            model.addRow(rowData);
        }

        resultSet.close();
        preparedStatement.close();
    }

// Handle any exceptions here
//        folder.Functions
    public FormReadTeacher(String banche, String role) {
        initComponents();
//        talb();
        this.role = LoginUser.getRole();
//            this.branhi = branche;
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        branhi = "ADMIN".equals(LoginUser.getBranch()) ? "%" : LoginUser.getBranch();
//        System.err.println("Login Role "+LoginUser.getRole());

        if ("TEACHER".equals(role)) {
            branchCombo
                    .setSelectedItem(branhi);
            branchCombo.setEnabled(false);
        } else if ("GUEST".equals(role)) {
            branchCombo
                    .setSelectedItem(branhi);
            branchCombo.setEnabled(false);
            jtableCellButton.setEnabled(false);
        }

    }

    public FormReadTeacher() {
        initComponents();
//        talb();
        this.role = LoginUser.getRole();
//            this.branhi = branche;
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        branhi = "ADMIN".equals(LoginUser.getBranch()) ? "%" : LoginUser.getBranch();
//        System.err.println("Login Role "+LoginUser.getRole());

        if ("TEACHER".equals(role)) {
            branchCombo
                    .setSelectedItem(branhi);
            branchCombo.setEnabled(false);
        } else if ("GUEST".equals(role)) {
            branchCombo
                    .setSelectedItem(branhi);
            branchCombo.setEnabled(false);
            jtableCellButton.setEnabled(false);
        }

    }
//    folder.Functions.table_load (readTable);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        readTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        searchBar = new javax.swing.JTextField();
        batchCombo = new javax.swing.JComboBox<>();
        catergoryCombo = new javax.swing.JComboBox<>();
        refreshButton = new javax.swing.JButton();
        exportTable = new javax.swing.JButton();
        branchCombo = new javax.swing.JComboBox<>();
        showAllDetails = new javax.swing.JButton();
        GenderCombox = new javax.swing.JComboBox<>();
        jtableCellButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        editBT = new javax.swing.JButton();
        editBT1 = new javax.swing.JButton();

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("TEACHER RECORD");

        //try {
            //            fillTableModel(model);
            //        } catch (SQLException e) {
            //            e.printStackTrace();
            //            // Handle any exceptions here
            //        }
        readTable.setAutoCreateRowSorter(true);
        readTable.setFont(new java.awt.Font("Liberation Sans", 0, 15)); // NOI18N
        readTable.setModel(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        readTable.setDefaultRenderer(Object.class, centerRenderer);

        // Set preferred column widths (adjust these as needed)
        int[] columnWidths = {100, 150, 300, 250, 250, 100, 250, 250, 100, 100, 100, 100, 100, 200, 150};
        for (int i = 0; i < columnWidths.length; i++) {
            readTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }
        readTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        readTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        readTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        readTable.setShowGrid(true);
        readTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                readTableMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                readTableMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(readTable);
        readTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        searchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarActionPerformed(evt);
            }
        });
        searchBar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBarKeyReleased(evt);
            }
        });

        batchCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Batch", "2016", "2017", "2018","2019","2020","2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028" }));
        batchCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchComboActionPerformed(evt);
            }
        });

        catergoryCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Catergory", "UR", "OBC", "SC", "ST" }));
        catergoryCombo.setSelectedItem("All Catergory");
        catergoryCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catergoryComboActionPerformed(evt);
            }
        });

        refreshButton.setFont(new java.awt.Font("Liberation Mono", 1, 13)); // NOI18N
        refreshButton.setText("Department Teachers");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        exportTable.setFont(new java.awt.Font("Liberation Mono", 1, 13)); // NOI18N
        exportTable.setText("Export");
        exportTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportTableActionPerformed(evt);
            }
        });

        branchCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Department", "CSE", "CIVIL", "ME", "ECE" }));
        branchCombo.setSelectedItem("All Department");
        branchCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                branchComboActionPerformed(evt);
            }
        });

        showAllDetails.setFont(new java.awt.Font("Liberation Mono", 1, 13)); // NOI18N
        showAllDetails.setText("Show Main Details");
        showAllDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showAllDetailsActionPerformed(evt);
            }
        });

        GenderCombox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Gender", "Male", "Female" }));
        GenderCombox.setSelectedItem("All Gender");
        GenderCombox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenderComboxActionPerformed(evt);
            }
        });

        jtableCellButton.setFont(new java.awt.Font("Liberation Mono", 1, 13)); // NOI18N
        jtableCellButton.setText("Print");
        jtableCellButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtableCellButton.setEnabled(false);
        jtableCellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtableCellButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Search");

        editBT.setFont(new java.awt.Font("Liberation Mono", 1, 13)); // NOI18N
        editBT.setText("Edit Details");
        editBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBTActionPerformed(evt);
            }
        });

        editBT1.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        editBT1.setText("Filter Teachers ");
        editBT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBT1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(showAllDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(batchCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(catergoryCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(exportTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(branchCombo, 0, 95, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtableCellButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(GenderCombox, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editBT1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(showAllDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(exportTable, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(editBT, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jtableCellButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batchCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(catergoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(branchCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GenderCombox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(editBT1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBarActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        jtableCellButton.setEnabled(true);
        editBT.setEnabled(true);

        try {
            //              fillTableModel(model);
            Connection sqlcon = DatabaseConnection.getInstance().getConnection();
            String myQuery = "SELECT \n"
                    + "    firstname || ' ' || lastname AS fullName,\n"
                    + "    email_id,\n"
                    + "    mobilenumber,\n"
                    + "    gender,\n"
                    + "    department,\n"
                    + "    type,\n"
                    + "    year_of_joining,\n"
                    + "    highest_qualification,\n"
                    + "    EXTRACT(YEAR FROM CURRENT_DATE) - CAST(year_of_joining AS INTEGER) AS years_of_experience\n"
                    + "FROM teacherform WHERE department like ?;";
            PreparedStatement query = sqlcon.prepareStatement(myQuery);
            query.setString(1, branhi);

            table_load(readTable, DatabaseConnection.getInstance().getConnection(), query);
//            readTable.setModel(model);
            jtableCellButton.setEnabled(false);
            editBT.setEnabled(false);

        } catch (SQLException ex) {
            Logger.getLogger(FormReadTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void searchBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBarKeyReleased
        // TODO add your handling code here:
        searchbar(readTable, searchBar.getText());
    }//GEN-LAST:event_searchBarKeyReleased

    private void showAllDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showAllDetailsActionPerformed
//        String myQuery = "select * from maindetails WHERE department = ?;";
        jtableCellButton.setEnabled(false);
//        jtableCellButton.setEnabled(Boolean.FALSE);

        try {
            Connection sqlcon = DatabaseConnection.getInstance().getConnection();
            String myQuery = "SELECT * FROM teachermainDetails WHERE department like ?;";
            PreparedStatement query = sqlcon.prepareStatement(myQuery);
            query.setString(1, branhi);
            table_load(readTable, DatabaseConnection.getInstance().getConnection(), query);

        } catch (SQLException ex) {
            Logger.getLogger(FormReadTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_showAllDetailsActionPerformed

    private void exportTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportTableActionPerformed

//                    ExcelExporter exp = new ExcelExporter();
        tableDataToExcel(readTable, new File("D:\\result.xls"));
        JOptionPane.showMessageDialog(null, "Data saved at "
                + "'D: \\ result.xls' successfully", "Message",
                JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_exportTableActionPerformed

    public void UpdateFilterTable() {
        try {
            String batch = (String) batchCombo.getSelectedItem();
            String department = (String) branchCombo.getSelectedItem();
            String category = (String) catergoryCombo.getSelectedItem(); // corrected spelling
            String gender = (String) GenderCombox.getSelectedItem();

            if ("All Batch".equals(batch)) {
                batch = "20%";
                System.out.println("Show all Batch Student " + batch);
            }
            if ("All Department".equals(department)) {
                department = "%";
                System.out.println("Show all department Student " + department);
            }
            if ("All Catergory".equals(category)) { // corrected spelling
                category = "%";
                System.out.println("Show all category Student " + category);
            }
            if ("All Gender".equals(gender)) {
                gender = "%";
                System.out.println("Show all gender Student " + gender);
            }

            String query = "SELECT * FROM teacherform WHERE year_of_joining::VARCHAR LIKE ? AND category LIKE ? AND department LIKE ? AND gender LIKE ?;";

            try (PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
                preparedStatement.setString(1, batch);
                preparedStatement.setString(2, category);
                preparedStatement.setString(3, department);
                preparedStatement.setString(4, gender);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    readTable.setModel(DbUtils.resultSetToTableModel(resultSet));
                    System.err.println("Table Update");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.err.println("SQL Error: " + ex.getMessage());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            Logger.getLogger(FormReadTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void batchComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchComboActionPerformed
//        UpdateFilterTable();
    }//GEN-LAST:event_batchComboActionPerformed

    private void branchComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_branchComboActionPerformed
        // TODO add your handling code here:
//        UpdateFilterTable();
    }//GEN-LAST:event_branchComboActionPerformed

    private void catergoryComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catergoryComboActionPerformed
        // TODO add your handling code here:
//        UpdateFilterTable();
    }//GEN-LAST:event_catergoryComboActionPerformed

    private void GenderComboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenderComboxActionPerformed
//        UpdateFilterTable();
        // TODO add your handling code here:
    }//GEN-LAST:event_GenderComboxActionPerformed

    private void jtableCellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtableCellButtonActionPerformed

        // TODO add your handling code here:
//        if(refreshButton.isDefaultButton()){
        DefaultTableModel tableModel = (DefaultTableModel) readTable.getModel();
        int selectedRow = readTable.getSelectedRow();
        System.err.println("First name" + tableModel.getValueAt(selectedRow, 0).toString());
        System.err.println("Last name" + tableModel.getValueAt(selectedRow, 1).toString());
        System.err.println("Father name" + tableModel.getValueAt(selectedRow, 2).toString());
        String Name = tableModel.getValueAt(selectedRow, 0).toString() + " " + tableModel.getValueAt(selectedRow, 1).toString();
        String FatherName = tableModel.getValueAt(selectedRow, 2).toString();
        String EmailId = tableModel.getValueAt(selectedRow, 3).toString();
        String MobileNumber = tableModel.getValueAt(selectedRow, 4).toString();
        String mark10th = tableModel.getValueAt(selectedRow, 5).toString();
        String Dob = tableModel.getValueAt(selectedRow, 6).toString();
        String Enrollment = tableModel.getValueAt(selectedRow, 7).toString();
        String Rollno = tableModel.getValueAt(selectedRow, 8).toString();
        String aadhar = tableModel.getValueAt(selectedRow, 9).toString();
        String department = tableModel.getValueAt(selectedRow, 10).toString();
        String Batch = tableModel.getValueAt(selectedRow, 11).toString();
        String caste = tableModel.getValueAt(selectedRow, 12).toString();
        String Gender = tableModel.getValueAt(selectedRow, 13).toString();
//        String adress = tableModel.getValueAt(selectedRow, 15).toString();

        HashMap<String, Object> ReportParmeterHashMap = new HashMap<>();

        ReportParmeterHashMap.put("Name", Name);
        ReportParmeterHashMap.put("Rollno", Rollno);
        ReportParmeterHashMap.put("emailId", EmailId);
        ReportParmeterHashMap.put("FatherName", FatherName);
        ReportParmeterHashMap.put("mobileNo", MobileNumber);
        ReportParmeterHashMap.put("marksTenth", mark10th);
        ReportParmeterHashMap.put("aadhar", aadhar);
        ReportParmeterHashMap.put("enrollment", Enrollment);
        ReportParmeterHashMap.put("department", department);
        ReportParmeterHashMap.put("Dob", Dob);
        ReportParmeterHashMap.put("batch", Batch);
        ReportParmeterHashMap.put("caste", caste);
        ReportParmeterHashMap.put("Gender", Gender);
//        ReportParmeterHashMap.put("address", adress);
        String details = "{'Full Name' : " + Name + ", 'Father Name' : " + FatherName + ", 'department' : " + department + ", 'Batch' : " + Batch + ", 'Aadhar' : " + aadhar + ", 'Gender' : " + Gender + ", 'Mobile No.' :" + MobileNumber + "}";
        ReportParmeterHashMap.put("Parameter7", generateQrcode(details));

        ReportManger.getInstance().ReportMangerX(JasperReportLocation.getInstance().getTeacherSlipReport(), ReportParmeterHashMap);
//        } else {
//                      Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Refresh Table To Print Form");
//
////        JOptionPane.showMessageDialog(this, "Please Refresh Table To Print Form");
//        }
    }//GEN-LAST:event_jtableCellButtonActionPerformed

    private void readTableMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_readTableMouseExited
        // TODO add your handling code here:

        if (role == "GUEST" | showAllDetails.isDefaultButton()) {
            jtableCellButton.setEnabled(false);
            editBT.setEnabled(false);
        }
    }//GEN-LAST:event_readTableMouseExited

    private void readTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_readTableMouseClicked
        // TODO add your handling code here:
        if ("GUEST".equals(role) | showAllDetails.isDefaultButton()) {
            jtableCellButton.setEnabled(false);
            editBT.setEnabled(false);
        } else {
//            System.out.println("Cell Clicked!!");
            jtableCellButton.setEnabled(true);
            editBT.setEnabled(true);

        }
        //        refreshButton.setEnabled(true);

    }//GEN-LAST:event_readTableMouseClicked

    private void editBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBTActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tableModel = (DefaultTableModel) readTable.getModel();
        int selectedRow = readTable.getSelectedRow();
        String aadhar = tableModel.getValueAt(selectedRow, 6).toString();

        FormManager.showForm(new FormEditAdmission(aadhar));
    }//GEN-LAST:event_editBTActionPerformed

    private void editBT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBT1ActionPerformed
        // TODO add your handling code here:
        UpdateFilterTable();
    }//GEN-LAST:event_editBT1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> GenderCombox;
    private javax.swing.JComboBox<String> batchCombo;
    private javax.swing.JComboBox<String> branchCombo;
    private javax.swing.JComboBox<String> catergoryCombo;
    private javax.swing.JButton editBT;
    private javax.swing.JButton editBT1;
    private javax.swing.JButton exportTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jtableCellButton;
    private javax.swing.JLabel lb;
    private javax.swing.JTable readTable;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField searchBar;
    private javax.swing.JButton showAllDetails;
    // End of variables declaration//GEN-END:variables
}
