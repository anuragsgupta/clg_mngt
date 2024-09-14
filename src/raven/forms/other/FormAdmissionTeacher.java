package raven.forms.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.raven.datechooser.DateChooser;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import raven.JasperReport.JasperReportLocation;
import raven.JasperReport.ReportManger;
import raven.components.SimpleForm;
import raven.requiredFunctions.DatabaseConnection;
import static raven.requiredFunctions.Functions.*;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class FormAdmissionTeacher extends SimpleForm {

    protected static final String PRESET_PIN = "1234";

    public Color getorg() {
        return dobTF.getForeground();
    }

    public void clearForm() {
        firstNameTF.setText("");               // 1
        lastNameTF.setText("");           // 2
        fatherNameTF.setText("");  //3
        mobileTF.setText("");           //4
        emailTF.setText("");          //5
        aadharTF.setText("");     //6
        addressTF.setText("");                 //7
        genderComboBox.getSelectedItem(); //8
        casteComboBox.getSelectedItem(); //9
//        dobTF.setText("01/01/2000"); //10
        panTF.setText("");        //11
        accountTF.setText("");        //12
    }

    ;
    protected void submitForm() {
//         personal details Total 10
        String name = firstNameTF.getText();               // 1
        String lastName = lastNameTF.getText();            // 2
        String fatherName = fatherNameTF.getText();   //3
        String mobile = mobileTF.getText();             //4
        String emailId = emailTF.getText();             //5
        String aadhar = aadharTF.getText();         //6
        String address = addressTF.getText();                //7
        String gender = (String) genderComboBox.getSelectedItem(); //8
        String caste = (String) casteComboBox.getSelectedItem(); //9
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dob = dobTF.getText(); //10
        String typeCb = (String) DesignationCB.getSelectedItem();
        String yearOfJoining = (String) yearOfJoiningCB.getSelectedItem();
        String branch = (String) departmentCB.getSelectedItem();
        // pancardTF details
        String pancardTF = panTF.getText();
        String accountNo = accountTF.getText();
        String highestQualification = highTF.getSelectedItem().toString();
        int batch = Integer.parseInt((String) yearOfJoiningCB.getSelectedItem());
        String teacherId = yearOfJoining.substring(2) + branch.substring(0, 1) + "126" + typeCb.substring(0, 2) + aadhar.substring(8);
        fatherNameTF.setText(teacherId);

        String printdetails = String.format("\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s", name, lastName, mobile, emailId,
                aadhar, address, gender, caste, dob, typeCb);
        System.err.println(printdetails);
        if (isValidString(name)
                && isValidString(lastName)
                && isValidAadharNumber(aadhar)
                && isValidEmail(emailId)
                && isValidMobileNumber(mobile)
                //                && isValidDateFormat(dob)
                && isValidPanCardNo(pancardTF)
                && isValidAadharNumber(accountNo)) {

            String MyQuery = "INSERT INTO teacherform\n"
                    + "(teacher_id,\n"
                    + //1
                    "firstName,\n"
                    + //2
                    "lastName,\n"
                    + //3
                    "email_ID,\n"
                    + //4
                    "mobileNumber,\n"
                    + //5
                    "category,\n"
                    + //6
                    "dob,\n"
                    + //7
                    "gender,\n"
                    + //8
                    "address,\n"
                    + //9
                    "aadhar,\n"
                    + //10
                    "account_No,\n"
                    + //11
                    "pan_Card,\n"
                    + //12
                    "type,\n"
                    +//13
                    "department,\n"
                    +//14
                    "year_of_joining,"
                    + "highest_qualification)\n"
                    +//15 16
                    "VALUES\n"
                    +//16
                    "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            System.err.println(MyQuery);

            int response
                    = JOptionPane.showConfirmDialog(
                            this,
                            "Do You To Submit Details ? ",
                            "Confirm",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) //                      System.out.println("Confirmed");
            {

                try {
                    PreparedStatement query;
//                  String url = "jdbc:mysql://localhost:3306/students";
                    try {
                        Connection sqlcon = DatabaseConnection.getInstance().getConnection();
                        System.err.println(sqlcon);
                        query = sqlcon.prepareStatement(MyQuery);
                        System.err.println(query);

                        //                  query.setString(1, stdFnameTF.getText());
                        query.setString(1, fatherName);
                        query.setString(2, name);
                        query.setString(3, lastName);
//                        query.setString(3, fatherName);
                        query.setString(4, emailId);
                        query.setString(5, mobile);
                        query.setString(6, caste);
                        java.sql.Date sqlDate = java.sql.Date.valueOf(dob);
                        query.setDate(7, sqlDate);

                        query.setString(8, gender);
                        query.setString(9, address);
                        query.setString(10, aadhar);
                        query.setString(11, accountNo);
                        query.setString(12, pancardTF);
                        query.setString(13, typeCb);
                        query.setString(14, branch);
                        query.setString(15, yearOfJoining);
                        query.setString(16, highestQualification);
//
                        System.out.print(query.getResultSet());
                        System.out.print(query);
                        System.out.print(query.getMetaData());
                        Boolean dfv = query.execute();
                        if (dfv) {
                            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.BOTTOM_RIGHT, "New Admission Data Submitted Successfully 😊😊");

                        }

                        query.close();

                        int responsePrint
                                = JOptionPane.showConfirmDialog(
                                        this,
                                        "Do you want to print this as Form",
                                        "Confirm",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE);

                        if (responsePrint == JOptionPane.YES_OPTION) //                      System.out.println("Confirmed");
                        {

                            HashMap<String, Object> ReportParmeterHashMap = new HashMap<>();

                            ReportParmeterHashMap.put("Name", name + " " + lastName);
                            ReportParmeterHashMap.put("Rollno", teacherId);
                            ReportParmeterHashMap.put("emailId", emailId);
                            ReportParmeterHashMap.put("FatherName", "father name");
                            ReportParmeterHashMap.put("mobileNo", mobile);
                            ReportParmeterHashMap.put("marksTenth", accountNo);
                            ReportParmeterHashMap.put("aadhar", aadhar);
                            ReportParmeterHashMap.put("enrollment", pancardTF);
                            ReportParmeterHashMap.put("branch", branch);
                            ReportParmeterHashMap.put("Dob", dob);
                            ReportParmeterHashMap.put("batch", batch);
                            ReportParmeterHashMap.put("caste", caste);
                            ReportParmeterHashMap.put("Gender", gender);
                            ReportParmeterHashMap.put("address", address);
                            String details = "{'Full Name' : " + name + " " + lastName + ", 'Father Name' : " + fatherName + ", 'Branch' : " + branch + ", 'Batch' : " + batch + ", 'Aadhar' : " + aadhar + ", 'Gender' : " + gender + ", 'Mobile No.' :" + mobile + "}";
                            ReportParmeterHashMap.put("Parameter7", generateQrcode(details));

                            ReportManger.getInstance().ReportMangerX(JasperReportLocation.getInstance().getStudentSlipReport(), ReportParmeterHashMap);

                            String fullname = name + " " + lastName;
                            String btch = String.valueOf(batch);
                            Date now = new Date();
//
                            // Format the date and time as a string
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            String formattedDateTime = dateFormat.format(now);

                            // Update the label with the formatted date and time
//                showMs
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                        System.out.println(ex.getMessage());
                    }
                } catch (Exception a) {
                    JOptionPane.showMessageDialog(this, a.getMessage());

                }

            } else {
                JOptionPane.showMessageDialog(
                        submitButton, "Wrong Input, Please ReCheck All Details");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Unable to sumbit data");
        }
    }
    DateChooser dateChooser1 = new DateChooser();

    public FormAdmissionTeacher() {
        initComponents();
        dateChooser1.setTextField(dobTF);
//        dateChooser1.setTextRefernce(dobTF);
//        dateChooser.setTextField(dateBWBT);
        dateChooser1.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        dateChooser1.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
//        dateChooser1.setTextRefernce(dobTF);
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        addressTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lastNameTF = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();
        aadharTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        casteComboBox = new javax.swing.JComboBox<>();
        mobileTF = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        genderComboBox = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        emailTF = new javax.swing.JTextField();
        firstNameTF = new javax.swing.JTextField();
        departmentCB = new javax.swing.JComboBox<>();
        panTF = new javax.swing.JTextField();
        fatherNameTF = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        accountTF = new javax.swing.JTextField();
        DesignationCB = new javax.swing.JComboBox<>();
        yearOfJoingTF = new javax.swing.JLabel();
        yearOfJoiningCB = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        dobTF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        yearOfJoingTF1 = new javax.swing.JLabel();
        highTF = new javax.swing.JComboBox<>();

        lb.setFont(new java.awt.Font("FiraCode Nerd Font Mono SemBd", 0, 12)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("NEW TEACHER ADMISSION");
        lb.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.white));

        jPanel1.setLayout(new java.awt.BorderLayout(150, 0));

        addressTF.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Lato Semibold", 0, 15)); // NOI18N
        jLabel8.setText("Aadhar No. *");

        lastNameTF.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N
        lastNameTF.setText("Last Name");

        submitButton.setText("Save");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        aadharTF.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N
        aadharTF.setText("12 digit aadhar Number");
        aadharTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                aadharTFFocusLost(evt);
            }
        });
        aadharTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                aadharTFKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Lato Semibold", 0, 15)); // NOI18N
        jLabel12.setText("Addres");

        casteComboBox.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N
        casteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UR", "OBC", "SC", "ST" }));
        casteComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                casteComboBoxActionPerformed(evt);
            }
        });

        mobileTF.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        mobileTF.setText("Whatsapp No. Recommended");
        mobileTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                mobileTFFocusLost(evt);
            }
        });
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

        jLabel17.setFont(new java.awt.Font("Lato Semibold", 0, 15)); // NOI18N
        jLabel17.setText("Department");

        jLabel9.setFont(new java.awt.Font("Lato Semibold", 0, 15)); // NOI18N
        jLabel9.setText("DOB");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel3.setText("PERSONAL DETAILS");

        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Lato Semibold", 0, 15)); // NOI18N
        jLabel4.setText("Gender *");

        jLabel1.setFont(new java.awt.Font("Lato Semibold", 0, 15)); // NOI18N
        jLabel1.setText("Catergory *");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel10.setText("ENROLLMENT DETAILS");

        genderComboBox.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N
        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        jLabel11.setFont(new java.awt.Font("Lato Semibold", 0, 15)); // NOI18N
        jLabel11.setText("PAN Card");

        jLabel2.setFont(new java.awt.Font("Lato Semibold", 0, 15)); // NOI18N
        jLabel2.setText("Full Name *");

        jLabel13.setFont(new java.awt.Font("Lato Semibold", 0, 15)); // NOI18N
        jLabel13.setText("Account No.");

        jLabel7.setFont(new java.awt.Font("Lato Semibold", 0, 15)); // NOI18N
        jLabel7.setText("Mobile No. *");

        emailTF.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N
        emailTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailTFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailTFFocusLost(evt);
            }
        });

        firstNameTF.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N
        firstNameTF.setText("First Name");
        firstNameTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                firstNameTFFocusLost(evt);
            }
        });
        firstNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameTFActionPerformed(evt);
            }
        });

        departmentCB.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N
        departmentCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CSE", "CIVIL", "ME", "ECE" }));

        panTF.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N
        panTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                panTFFocusLost(evt);
            }
        });
        panTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                panTFKeyReleased(evt);
            }
        });

        fatherNameTF.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N
        fatherNameTF.setText("First name only");
        fatherNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fatherNameTFActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Lato Semibold", 0, 15)); // NOI18N
        jLabel15.setText("Designation");

        jButton1.setText("Load Sample Data");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Lato Semibold", 0, 15)); // NOI18N
        jLabel6.setText("Email ID");

        accountTF.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N
        accountTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                accountTFKeyReleased(evt);
            }
        });

        DesignationCB.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N
        DesignationCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GUEST", "PERMANENT" }));
        DesignationCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesignationCBActionPerformed(evt);
            }
        });

        yearOfJoingTF.setFont(new java.awt.Font("Lato Semibold", 0, 15)); // NOI18N
        yearOfJoingTF.setText("Year Of Joining");

        yearOfJoiningCB.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N
        yearOfJoiningCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024" }));

        jButton3.setText("Generate ID");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        dobTF.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N
        dobTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dobTFFocusLost(evt);
            }
        });
        dobTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dobTFKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Lato Semibold", 0, 15)); // NOI18N
        jLabel14.setText("Teacher ID");

        yearOfJoingTF1.setFont(new java.awt.Font("Lato Semibold", 0, 15)); // NOI18N
        yearOfJoingTF1.setText("Highest Qualification");

        highTF.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N
        highTF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BE", "BTECH", "Master's", "PHD", " " }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(709, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(298, 298, 298))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addGap(75, 75, 75))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(yearOfJoingTF1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(46, 46, 46)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(71, 71, 71)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(panTF)
                                    .addComponent(aadharTF, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(accountTF, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(highTF, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(158, 158, 158))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(firstNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lastNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGap(78, 78, 78)))
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(addressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(mobileTF, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(yearOfJoingTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(83, 83, 83)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DesignationCB, 0, 170, Short.MAX_VALUE)
                                    .addComponent(departmentCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(yearOfJoiningCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(42, 42, 42))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(65, 65, 65)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fatherNameTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(genderComboBox, 0, 187, Short.MAX_VALUE)
                                    .addComponent(casteComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dobTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(12, 12, 12))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(genderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(casteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobileTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(dobTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fatherNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(4, 4, 4)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aadharTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(accountTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(yearOfJoingTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(highTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DesignationCB, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(departmentCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(yearOfJoingTF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearOfJoiningCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(182, 182, 182))))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mobileTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobileTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobileTFActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // TODO add your handling code here:

        String inputPin = JOptionPane.showInputDialog(null, "Enter PIN:");

        if (inputPin != null && inputPin.equals(PRESET_PIN)) {
            JOptionPane.showMessageDialog(null, "PIN Matched! Access granted.");
            submitForm();
        } else {
            JOptionPane.showMessageDialog(null, "PIN does not match. Access denied.");
        }

    }//GEN-LAST:event_submitButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clearForm();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void firstNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameTFActionPerformed

    private void emailTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTFFocusLost
        // TODO add your handling code here:
        if (!isValidEmail(emailTF.getText())) {
            emailTF.setForeground(Color.RED);

        } else {

            emailTF.setForeground(getorg());

        }
    }//GEN-LAST:event_emailTFFocusLost

    private void emailTFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTFFocusGained
        // TODO add your handling code here:
        if (!isValidEmail(emailTF.getText())) {
            emailTF.setForeground(Color.RED);

        } else {

            emailTF.setForeground(getorg());

        }
    }//GEN-LAST:event_emailTFFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        firstNameTF.setText(generateRandomFirstName());
        lastNameTF.setText(generateRandomLastName());
        fatherNameTF.setText(generateRandomFirstName() + " " + generateRandomLastName());
        departmentCB.setSelectedIndex(generateRandomDigit(4));
        genderComboBox.setSelectedIndex(generateRandomDigit(2));
        DesignationCB.setSelectedIndex(generateRandomDigit(2));
        casteComboBox.setSelectedIndex(generateRandomDigit(4));
        emailTF.setText(firstNameTF.getText() + lastNameTF.getText() + generateRandomString(4) + "@gmail.com");
        mobileTF.setText(generateRandomString(10));
        accountTF.setText(generateRandomString(12));
        aadharTF.setText(generateRandomString(12));
//        panTF.setText(generateRandomString(12));
//            showMsm.setText("Loaded Sample data 👍👍");
//        dobTF.setText("2004-" + generateRandomString(1) + "-" + generateRandomString(1));
        addressTF.setText("royal city");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void firstNameTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameTFFocusLost
//        Color org = firstNameTF.getForeground();
        if (!isValidString(firstNameTF.getText())) {
            firstNameTF.setForeground(Color.RED);

        } else {

            firstNameTF.setForeground(getorg());

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameTFFocusLost

    private void mobileTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mobileTFFocusLost

        if (!isValidMobileNumber(mobileTF.getText())) {
            mobileTF.setForeground(Color.RED);
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Not Valid Mobile Number ");

        } else {
            mobileTF.setForeground(getorg());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_mobileTFFocusLost

    private void aadharTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_aadharTFFocusLost
        // TODO add your handling code here:

        if (!isValidAadharNumber(aadharTF.getText())) {
            aadharTF.setForeground(Color.RED);
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Not Valid Aadhar Number ");

        } else {

            aadharTF.setForeground(getorg());

        }

    }//GEN-LAST:event_aadharTFFocusLost

    private void panTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panTFFocusLost
        // TODO add your handling code here:
        if (!isValidEnrollmentNumber(panTF.getText())) {
            panTF.setForeground(Color.RED);
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Not Valid Enrollment Number ");

        } else {

            panTF.setForeground(getorg());

        }
    }//GEN-LAST:event_panTFFocusLost

    private void mobileTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mobileTFKeyReleased
        // TODO add your handling code here:
        if (!isValidMobileNumber(mobileTF.getText())) {
            mobileTF.setForeground(Color.RED);
        } else {
            mobileTF.setForeground(getorg());
        }
    }//GEN-LAST:event_mobileTFKeyReleased

    private void panTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panTFKeyReleased

        if (!isValidPanCardNo(panTF.getText())) {
            panTF.setForeground(Color.RED);

        } else {

            panTF.setForeground(getorg());

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_panTFKeyReleased

    private void aadharTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aadharTFKeyReleased

        if (!isValidAadharNumber(aadharTF.getText())) {
            aadharTF.setForeground(Color.RED);

        } else {

            aadharTF.setForeground(getorg());

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_aadharTFKeyReleased

    private void casteComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_casteComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_casteComboBoxActionPerformed

    private void fatherNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fatherNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fatherNameTFActionPerformed

    private void DesignationCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesignationCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DesignationCBActionPerformed

    private void accountTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accountTFKeyReleased
        // TODO add your handling code here:

        if (!isValidAadharNumber(accountTF.getText())) {
            accountTF.setForeground(Color.RED);

        } else {

            accountTF.setForeground(getorg());

        }
    }//GEN-LAST:event_accountTFKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String name = firstNameTF.getText();               // 1
        String lastName = lastNameTF.getText();            // 2
        String fatherName = fatherNameTF.getText();   //3
        String mobile = mobileTF.getText();             //4
        String emailId = emailTF.getText();             //5
        String aadhar = aadharTF.getText();         //6
        String address = addressTF.getText();                //7
        String gender = (String) genderComboBox.getSelectedItem(); //8
        String caste = (String) casteComboBox.getSelectedItem(); //9
        String dob = dobTF.getText(); //10
        String typeCb = (String) DesignationCB.getSelectedItem();
        String yearOfJoining = (String) yearOfJoiningCB.getSelectedItem();
        String branch = (String) departmentCB.getSelectedItem();
        // pancardTF details
        String pancardTF = panTF.getText();
        String accountNo = accountTF.getText();
        int batch = Integer.parseInt((String) yearOfJoiningCB.getSelectedItem());
        String teacherId = yearOfJoining.substring(2) + typeCb.substring(0, 1) + "126" + branch.substring(0, 2) + aadhar.substring(8);
        fatherNameTF.setText(teacherId);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void dobTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dobTFFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_dobTFFocusLost

    private void dobTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dobTFKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_dobTFKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> DesignationCB;
    private javax.swing.JTextField aadharTF;
    private javax.swing.JTextField accountTF;
    private javax.swing.JTextField addressTF;
    private javax.swing.JComboBox<String> casteComboBox;
    private javax.swing.JComboBox<String> departmentCB;
    private javax.swing.JTextField dobTF;
    private javax.swing.JTextField emailTF;
    private javax.swing.JTextField fatherNameTF;
    private javax.swing.JTextField firstNameTF;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JComboBox<String> highTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField lastNameTF;
    private javax.swing.JLabel lb;
    private javax.swing.JTextField mobileTF;
    private javax.swing.JTextField panTF;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel yearOfJoingTF;
    private javax.swing.JLabel yearOfJoingTF1;
    private javax.swing.JComboBox<String> yearOfJoiningCB;
    // End of variables declaration//GEN-END:variables
}
