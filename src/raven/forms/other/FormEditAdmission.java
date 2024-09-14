package raven.forms.other;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import raven.JasperReport.JasperReportLocation;
import raven.JasperReport.ReportManger;
import raven.components.SimpleForm;
import raven.requiredFunctions.DatabaseConnection;
import static raven.requiredFunctions.Functions.isValidAadharNumber;
import static raven.requiredFunctions.Functions.isValidDateFormat;
import static raven.requiredFunctions.Functions.isValidEmail;
import static raven.requiredFunctions.Functions.isValidEnrollmentNumber;
import static raven.requiredFunctions.Functions.isValidMobileNumber;
import static raven.requiredFunctions.Functions.isValidString;
import static raven.requiredFunctions.Functions.isvalid10marks;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class FormEditAdmission extends SimpleForm {

    public Color getorg() {
        return dobTF.getForeground();
    }

    public FormEditAdmission() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
    }

    public FormEditAdmission(String aadhar) {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        findByComboBox.setSelectedIndex(3);
        findInputTF.setText(aadhar);

    }

    public void updateForm() {
        // Personal details
        String name = firstNameTF.getText(); // 1
        String lastName = lastNameTF.getText(); // 2
        String fatherName = fatherNameTF.getText(); // 3
        String mobile = mobileTF.getText(); // 4
        String emailId = emailTF.getText(); // 5
        String aadhar = aadharTF.getText(); // 6
        String address = addressTF.getText(); // 7
        String gender = (String) genderComboBox.getSelectedItem(); // 8
        String caste = (String) casteComboBox.getSelectedItem(); // 9
        String dob = dobTF.getText(); // 10
        String enrollment = enrollTF.getText(); // 11
        String marks10 = mark10TF.getText(); // 12
        String batch = batchComboBox.getSelectedItem().toString(); // 13
        String branch = (String) branchComboBox.getSelectedItem(); // 14
        String scholarship = (String) availscholarTFComboFD.getSelectedItem(); // 15

        if (isValidString(name)
                && isValidString(lastName)
                && isValidString(fatherName)
                && isValidAadharNumber(aadhar)
                && isValidEmail(emailId)
                && isValidMobileNumber(mobile)
                && isValidDateFormat(dob)
                && isValidEnrollmentNumber(enrollment)
                && isvalid10marks(marks10)) {

            String colValue = findInputTF.getText();
            String where = null;
            String findItem = (String) findByComboBox.getSelectedItem();
            if (findItem != null) {
                switch (findItem) {
                    case "Enrollment" ->
                        where = "enrollment";
                    case "Mobile Number" ->
                        where = "mobileNumber";
                    case "Aadhar" ->
                        where = "aadhar";
                    case "Rollno" ->
                        where = "rollno";
                }
            }

            if (where == null || colValue == null) {
                JOptionPane.showMessageDialog(this, "Invalid search criteria.");
                return;
            }

            String myQuery = "UPDATE studentform SET "
                    + "firstName = ?, " // 1
                    + "lastName = ?, " // 2
                    + "fatherName = ?, " // 3
                    + "Email_ID = ?, " // 4
                    + "mobileNumber = ?, " // 5
                    + "marks10th = ?, " // 6
                    + "aadhar = ?, " // 7
                    + "enrollment = ?, " // 8
                    + "Dob = ?, " // 9
                    + "branch = ?, " // 10
                    + "Batch = ?, " // 11
                    + "category = ?, " // 12
                    + "Gender = ?, " // 13
                    + "address = ?, " // 14
                    + "scholarship = ? " // 15
                    + "WHERE " + where + " = ?;"; // 16

            int response = JOptionPane.showConfirmDialog(
                    updateButton,
                    "Are you Sure",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                try {
                    Connection sqlcon = DatabaseConnection.getInstance().getConnection();
                    PreparedStatement query = sqlcon.prepareStatement(myQuery);

                    query.setString(1, name);
                    query.setString(2, lastName);
                    query.setString(3, fatherName);
                    query.setString(4, emailId);
                    query.setString(5, mobile);
                    query.setString(6, marks10);
                    query.setString(7, aadhar);
                    query.setString(8, enrollment);
                    java.sql.Date sqlDate = java.sql.Date.valueOf(dob);
                    query.setDate(9, sqlDate);
                    query.setString(10, branch);
                    query.setString(11, batch);
                    query.setString(12, caste);
                    query.setString(13, gender);
                    query.setString(14, address);
                    query.setString(15, scholarship);
                    query.setString(16, colValue);

                    int result = query.executeUpdate();
                    if (result > 0) {
                        JOptionPane.showMessageDialog(this, "Updated Successfully");
                    } else {
                        JOptionPane.showMessageDialog(this, "No records updated.");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                    e.printStackTrace();
                    Logger.getLogger(SubjectForm.class.getName()).log(Level.SEVERE, null, e);
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid input data.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        findByComboBox = new javax.swing.JComboBox<>();
        findInputTF = new javax.swing.JTextField();
        findButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        firstNameTF = new javax.swing.JTextField();
        lastNameTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fatherNameTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        casteComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        emailTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        mobileTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        aadharTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        dobTF = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        enrollTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        addressTF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        mark10TF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        availscholarTFComboFD = new javax.swing.JComboBox<>();
        batchComboBox = new javax.swing.JComboBox<>();
        updateButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        branchComboBox = new javax.swing.JComboBox<>();
        genderComboBox = new javax.swing.JComboBox<>();
        PrintButton = new javax.swing.JButton();

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Edit Admission Details");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Find By");

        findByComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enrollment", "Rollno", "Mobile Number", "Aadhar" }));

        findInputTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findInputTFActionPerformed(evt);
            }
        });

        findButton.setText("Find ");
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(findByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(findInputTF, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(findButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(findByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findInputTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 529, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel3.setText("Personal Details");

        jLabel2.setText("Full Name *");

        firstNameTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                firstNameTFKeyReleased(evt);
            }
        });

        lastNameTF.setText("Last Name");
        lastNameTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lastNameTFKeyReleased(evt);
            }
        });

        jLabel4.setText("Gender *");

        jLabel5.setText("Father Name *");

        fatherNameTF.setText("First name only");

        jLabel1.setText("Catergory *");

        casteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UR", "OBC", "SC", "ST" }));
        casteComboBox.setEnabled(false);

        jLabel6.setText("Email ID");

        emailTF.setText("@gmail.com");

        jLabel7.setText("Mobile No. *");

        mobileTF.setText("Whatsapp no. Recommendent");
        mobileTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobileTFActionPerformed(evt);
            }
        });
        mobileTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mobileTFKeyReleased(evt);
            }
        });

        jLabel8.setText("Aadhar No. *");

        aadharTF.setEditable(false);
        aadharTF.setText("12 digit aadhar Number");
        aadharTF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel9.setText("DOB");

        dobTF.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel10.setText("Enrollment Details");

        jLabel11.setText("        Enrollment No *");

        enrollTF.setEditable(false);

        jLabel12.setText("Addres");

        jLabel13.setText("10 Marks ( 00.0 )");

        jLabel14.setText("%");

        jLabel15.setText("        Batch");

        jLabel16.setText("Avail Scholarship");

        availscholarTFComboFD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));

        batchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021","2022", "2023", "2024", "2025", "2026", "2027" }));
        batchComboBox.setEnabled(false);

        updateButton.setBackground(new java.awt.Color(78, 189, 34));
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel17.setText("Branch");

        branchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CSE","CIVIL", "ECE", "ME" }));

        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        genderComboBox.setEnabled(false);

        PrintButton.setBackground(new java.awt.Color(78, 189, 34));
        PrintButton.setText("Print Form");
        PrintButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(90, 90, 90)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(fatherNameTF)
                                            .addComponent(emailTF)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(firstNameTF)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lastNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(aadharTF)
                                            .addComponent(addressTF)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                                        .addGap(33, 33, 33)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(enrollTF, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(batchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(72, 72, 72)
                                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(branchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(88, 88, 88)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(PrintButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mobileTF, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                            .addComponent(dobTF)
                            .addComponent(casteComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(availscholarTFComboFD, javax.swing.GroupLayout.Alignment.LEADING, 0, 100, Short.MAX_VALUE)
                                    .addComponent(mark10TF, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14))
                            .addComponent(genderComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(genderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fatherNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(casteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobileTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aadharTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(dobTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enrollTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mark10TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(availscholarTFComboFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(batchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(branchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PrintButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(83, 83, 83)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(302, 302, 302)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mobileTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobileTFActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_mobileTFActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed

        // TODO add your handling code here:
        updateForm();

        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Updated Data Submitted Successfully 😊😊");

    }//GEN-LAST:event_updateButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void findInputTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findInputTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_findInputTFActionPerformed

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
        // TODO add your handling code here:
        String colVaue = findInputTF.getText();
        String where = getSelectedString();

        System.out.println(colVaue);
        System.out.println(where);

        Connection sqlcon = null;

        try {
//            sqlcon = DatabaseConnection.getInstance().getConnection().prepareStatement(q);
            String q = "select * from studentform where " + where + " = ?;";
            PreparedStatement query = DatabaseConnection.getInstance().getConnection().prepareStatement(q);
            //              query.setString(1, where);
            query.setString(1, colVaue);
            query.execute();
            System.out.println(query.executeQuery());

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
//                visableFd(true);
                firstNameTF.setText(rs.getString(1));
                lastNameTF.setText(rs.getString(2));
                fatherNameTF.setText(rs.getString(3));
                emailTF.setText(rs.getString(4));
                mobileTF.setText(rs.getString(5));
                mark10TF.setText(rs.getString(6));
                aadharTF.setText(rs.getString(7));
                enrollTF.setText(rs.getString(8));

//                FOR ROLLNO THIS PLACE IS EMPTY
                dobTF.setText(rs.getString(10));
                branchComboBox.setSelectedItem(rs.getString(11));
                batchComboBox.setSelectedItem(rs.getString(12));

                casteComboBox.setSelectedItem(rs.getString(13));
                genderComboBox.setSelectedItem(rs.getString(14));
                addressTF.setText(rs.getString(15));
                availscholarTFComboFD.setSelectedItem(rs.getString(16));
            }
//              updatebtnFD.setVisible(true);
//              printButton.setVisible(true);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(findButton, "Error " + ex.getMessage());
        } catch (NullPointerException a) {
            JOptionPane.showMessageDialog(this, a.getMessage());
        }

    }//GEN-LAST:event_findButtonActionPerformed

    private String getSelectedString() {
        String findItem = (String) findByComboBox.getSelectedItem();
        String where = null;

        if (Objects.equals(findItem, "Enrollment")) {
            where = "enrollment";
        } else if (Objects.equals(findItem, "Mobile Number")) {
            where = "mobileNumber";
        } else if (Objects.equals(findItem, "Aadhar")) {
            where = "aadhar";
        } else if (Objects.equals(findItem, "Rollno")) {
            where = "rollno";
        }
        return where;
    }

    private void PrintButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintButtonActionPerformed
        int responsePrint
                = JOptionPane.showConfirmDialog(
                        this,
                        "Do you want to print this as Form",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

        if (responsePrint == JOptionPane.YES_OPTION) //                      System.out.println("Confirmed");
        {
            System.out.println("Printing Form Begin");
            String name = firstNameTF.getText();               // 1
            String lastName = getLastNameTFText();            // 2
            String fatherName = fatherNameTF.getText();   //3
            String mobile = mobileTF.getText();             //4
            String emailId = emailTF.getText();             //5
            String aadhar = aadharTF.getText();         //6
            String address = addressTF.getText();                //7
            String gender = (String) genderComboBox.getSelectedItem(); //8
            String caste = (String) casteComboBox.getSelectedItem(); //9
            String dob = dobTF.getText(); //10
            String enrollment = enrollTF.getText(); //10
            String marks10 = mark10TF.getText(); //11
            int batch = Integer.parseInt((String) batchComboBox.getSelectedItem()); //12
            String branch = (String) branchComboBox.getSelectedItem();                //13
            String scholarship = (String) availscholarTFComboFD.getSelectedItem();  //14
            String fullname = name + " " + lastName;
            String btch = String.valueOf(batch);
            Date now = new Date();
//          String
            String Name = name + " " + lastName;

            HashMap<String, Object> ReportParmeterHashMap = new HashMap<>();
            ReportParmeterHashMap.put("Name", Name);
//      ReportParmeterHashMap.put("Rollno",Rollno);
            ReportParmeterHashMap.put("emailId", emailId);
            ReportParmeterHashMap.put("FatherName", fatherName);
            ReportParmeterHashMap.put("mobileNo", mobile);
            ReportParmeterHashMap.put("marksTenth", marks10);
            ReportParmeterHashMap.put("aadhar", aadhar);
            ReportParmeterHashMap.put("enrollment", enrollment);
            ReportParmeterHashMap.put("branch", branch);
            ReportParmeterHashMap.put("Dob", dob);
            ReportParmeterHashMap.put("batch", batch);
            ReportParmeterHashMap.put("caste", caste);
            ReportParmeterHashMap.put("Gender", gender);
            ReportParmeterHashMap.put("address", address);
            ReportManger.getInstance().ReportMangerX(JasperReportLocation.getInstance().getStudentSlipReport(), ReportParmeterHashMap);

            // Format the date and time as a string
            System.out.println("Printing completed");

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_PrintButtonActionPerformed

    private String getLastNameTFText() {
        return lastNameTF.getText();
    }

    private void mobileTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mobileTFKeyReleased
        if (!isValidMobileNumber(mobileTF.getText())) {
            mobileTF.setForeground(Color.RED);
        } else {
            mobileTF.setForeground(getorg());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_mobileTFKeyReleased

    private void firstNameTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_firstNameTFKeyReleased

        if (!isValidString(firstNameTF.getText())) {
            firstNameTF.setForeground(Color.RED);

        } else {

            firstNameTF.setForeground(getorg());

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameTFKeyReleased

    private void lastNameTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastNameTFKeyReleased
        if (!isValidString(lastNameTF.getText())) {
            lastNameTF.setForeground(Color.RED);

        } else {

            lastNameTF.setForeground(getorg());

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameTFKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PrintButton;
    private javax.swing.JTextField aadharTF;
    private javax.swing.JTextField addressTF;
    private javax.swing.JComboBox<String> availscholarTFComboFD;
    private javax.swing.JComboBox<String> batchComboBox;
    private javax.swing.JComboBox<String> branchComboBox;
    private javax.swing.JComboBox<String> casteComboBox;
    private javax.swing.JTextField dobTF;
    private javax.swing.JTextField emailTF;
    private javax.swing.JTextField enrollTF;
    private javax.swing.JTextField fatherNameTF;
    private javax.swing.JButton findButton;
    private javax.swing.JComboBox<String> findByComboBox;
    private javax.swing.JTextField findInputTF;
    private javax.swing.JTextField firstNameTF;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JTextField lastNameTF;
    private javax.swing.JLabel lb;
    private javax.swing.JTextField mark10TF;
    private javax.swing.JTextField mobileTF;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
