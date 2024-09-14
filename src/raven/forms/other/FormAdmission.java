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
public class FormAdmission extends SimpleForm {

    protected static final String PRESET_PIN = "1234";
    private DateChooser dateChooser1 = new DateChooser();

    public Color getorg() {
//        dateChooser1.setSelectedDate();
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
        dobTF.setText("01/01/2000"); //10
        enrollTF.setText("");        //11
        mark10TF.setText("");        //12
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
        String branch = (String) branchComboBox.getSelectedItem();
        String address = addressTF.getText();                //7
        String gender = (String) genderComboBox.getSelectedItem(); //8
        String caste = (String) casteComboBox.getSelectedItem(); //9
        String dob = dobTF.getText(); //10

        // enrollment details
        String enrollment = enrollTF.getText();
        String marks10 = mark10TF.getText();
        int batch = Integer.parseInt((String) batchComboBox.getSelectedItem());
        String scholarship = (String) scholarshipCombBoxTF.getSelectedItem();

        if (isValidString(name)
                && isValidString(lastName)
                && isValidString(fatherName)
                && isValidAadharNumber(aadhar)
                && isValidEmail(emailId)
                && isValidMobileNumber(mobile)
                //                && isValidDateFormat(dob)
                && isValidEnrollmentNumber(enrollment) //                && isvalid10marks(marks10)
                ) {
            String rollno = batchComboBox.getSelectedItem().toString().substring(2) + "126" + branch.substring(0, 1) + "01" + aadhar.substring(8);
            rollnoTF.setText(rollno);

            String myQuery
                    //                              1       2           3           4
                    = "insert into studentform(firstName, lastName, fatherName, Email_ID,"
                    //      5           6           7         8       9      10     11      12      13       14         15       16
                    + " mobileNumber, marks10th, aadhar, enrollment, Dob, branch, Batch, category, Gender, address, scholarship,rollno)"
                    //                             1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            System.out.println(myQuery);

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
                        query = sqlcon.prepareStatement(myQuery);

                        query.setString(1, name);
                        //                  query.setString(2, stdLnametextField.getText());
                        query.setString(2, lastName);
                        //                  query.setString(3, stdFathertextField.getText());
                        query.setString(3, fatherName);
                        //                  query.setString(4, emailTextField.getText());
                        query.setString(4, emailId);
                        query.setString(5, mobile);
                        //                  query.setInt(5, Integer.parseInt(Student_Mobile_Number));
                        query.setString(6, marks10);
                        query.setString(7, aadhar);
                        query.setString(8, enrollment);
                        // Assuming dobTextField contains a valid date string in "yyyy-MM-dd" format
//                  String dobString = dobTextField.getText();
                        java.sql.Date sqlDate = java.sql.Date.valueOf(dob);
                        query.setDate(9, sqlDate);
//                    query.setDate(9,
// java.sql.Date.valueOf(dobTextField.getText()));
                        query.setString(10, branch);
                        query.setInt(11, batch);
                        query.setString(12, caste);
                        query.setString(13, gender);
                        query.setString(14, address);
                        query.setString(15, scholarship);
                        query.setString(16, rollno);
                        System.out.print(query.getResultSet());
                        System.out.print(query);
//                        System.out.print(p);
                        System.out.print(query.getMetaData());

                        Boolean t = query.execute();

                        if (t) {
                            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "New Admission Data Submitted Successfully 😊😊");
                            JOptionPane.showConfirmDialog(this, "Admission Completed Successfully");
                        }

                        query.close();

                        int responsePrint
                                = JOptionPane.showConfirmDialog(
                                        this,
                                        "Do you want to print this as Form",
                                        "Confirm",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE);

                        if (responsePrint == JOptionPane.YES_OPTION) {

                            HashMap<String, Object> ReportParmeterHashMap = new HashMap<>();

                            ReportParmeterHashMap.put("Name", name + " " + lastName);
                            System.err.println(rollno);
                            ReportParmeterHashMap.put("rolno", rollno);
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
                            String details = "{'Full Name' : " + name + " " + lastName + ", 'Father Name' : " + fatherName + ", 'Branch' : " + branch + ", 'Batch' : " + batch + ", 'Aadhar' : " + aadhar + ", 'Gender' : " + gender + ", 'Mobile No.' :" + mobile + "}";
                            ReportParmeterHashMap.put("Parameter7", generateQrcode(details));

                            String fullname = name + " " + lastName;
                            String btch = String.valueOf(batch);
                            Date now = new Date();
//
                            // Format the date and time as a string
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            String formattedDateTime = dateFormat.format(now);

                            ReportManger.getInstance().ReportMangerX(JasperReportLocation.getInstance().getStudentSlipReport(), ReportParmeterHashMap);

                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                        System.out.println(ex.getMessage());
                    }
                } catch (NullPointerException a) {
                    JOptionPane.showMessageDialog(this, a.getMessage());

                }
                //                 else {
                //                  JOptionPane.showMessageDialog(submitButton, "Please Check All
                // Detail Are Entered Correctly or not");
                //                }
            } else {
                JOptionPane.showMessageDialog(
                        submitButton, "Wrong Input, Please ReCheck All Details");
            }
        }
    }

    public FormAdmission() {
        initComponents();
        dateChooser1.setTextField(dobTF);
//        dateChooser1.setTextRefernce(dobTF);
//        dateChooser.setTextField(dateBWBT);
        dateChooser1.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        dateChooser1.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        lb = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        addressTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lastNameTF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        aadharTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        casteComboBox = new javax.swing.JComboBox<>();
        mobileTF = new javax.swing.JTextField();
        scholarshipCombBoxTF = new javax.swing.JComboBox<>();
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
        dobTF = new javax.swing.JTextField();
        branchComboBox = new javax.swing.JComboBox<>();
        enrollTF = new javax.swing.JTextField();
        fatherNameTF = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        batchComboBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        mark10TF = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        rollnoTF = new javax.swing.JTextField();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        lb.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("NEW ADMISSION");
        lb.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.white));

        jPanel1.setLayout(new java.awt.BorderLayout(150, 0));

        addressTF.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 15)); // NOI18N

        jLabel8.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 15)); // NOI18N
        jLabel8.setText("Aadhar No. *");

        lastNameTF.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 15)); // NOI18N
        lastNameTF.setText("Last Name");

        jLabel14.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        jLabel14.setText("%");

        submitButton.setBackground(new java.awt.Color(153, 153, 255));
        submitButton.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        submitButton.setText("Save");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 15)); // NOI18N
        jLabel16.setText("Avail SCholarship");

        aadharTF.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 15)); // NOI18N
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

        jLabel5.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 15)); // NOI18N
        jLabel5.setText("Father Name *");

        jLabel12.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 15)); // NOI18N
        jLabel12.setText("Address");

        casteComboBox.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 15)); // NOI18N
        casteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UR", "OBC", "SC", "ST" }));
        casteComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                casteComboBoxActionPerformed(evt);
            }
        });

        mobileTF.setFont(new java.awt.Font("0xProto Nerd Font Propo", 1, 15)); // NOI18N
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

        scholarshipCombBoxTF.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 15)); // NOI18N
        scholarshipCombBoxTF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        scholarshipCombBoxTF.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                scholarshipCombBoxTFItemStateChanged(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 15)); // NOI18N
        jLabel17.setText("Branch");

        jLabel9.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 15)); // NOI18N
        jLabel9.setText("DOB");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel3.setText("PERSONAL DETAILS");

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 15)); // NOI18N
        jLabel4.setText("Gender *");

        jLabel1.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 15)); // NOI18N
        jLabel1.setText("Catergory *");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel10.setText("ENROLLMENT DETAILS");

        genderComboBox.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 15)); // NOI18N
        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        jLabel11.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 15)); // NOI18N
        jLabel11.setText("Enrollment No *");

        jLabel2.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 15)); // NOI18N
        jLabel2.setText("Full Name *");

        jLabel13.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 15)); // NOI18N
        jLabel13.setText("10 Marks ( 00.0 )");

        jLabel7.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 15)); // NOI18N
        jLabel7.setText("Mobile No. *");

        emailTF.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 15)); // NOI18N
        emailTF.setText("@gmail.com");
        emailTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailTFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailTFFocusLost(evt);
            }
        });

        firstNameTF.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 15)); // NOI18N
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

        dobTF.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 15)); // NOI18N
        dobTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dobTFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                dobTFFocusLost(evt);
            }
        });
        dobTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dobTFActionPerformed(evt);
            }
        });

        branchComboBox.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 15)); // NOI18N
        branchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CSE","CIVIL", "ECE", "ME" }));

        enrollTF.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 15)); // NOI18N
        enrollTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                enrollTFFocusLost(evt);
            }
        });
        enrollTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                enrollTFKeyReleased(evt);
            }
        });

        fatherNameTF.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 15)); // NOI18N
        fatherNameTF.setText("First name only");
        fatherNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fatherNameTFActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 15)); // NOI18N
        jLabel15.setText("Batch");

        batchComboBox.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 15)); // NOI18N
        batchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>( raven.requiredFunctions.Functions.getYearOptions()));
        batchComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchComboBoxActionPerformed(evt);
            }
        });

        jButton1.setText("Load Sample Data");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 15)); // NOI18N
        jLabel6.setText("Email ID");

        mark10TF.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 15)); // NOI18N
        mark10TF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mark10TFKeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 15)); // NOI18N
        jLabel18.setText("RollNo");

        rollnoTF.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(269, 269, 269))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11))
                                .addGap(62, 62, 62)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(batchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(branchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(enrollTF)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fatherNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(firstNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(lastNameTF))
                                        .addComponent(aadharTF)
                                        .addComponent(emailTF)
                                        .addComponent(addressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(97, 97, 97)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(196, 196, 196))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(180, 180, 180))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(genderComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(casteComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mobileTF, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(dobTF)
                            .addComponent(scholarshipCombBoxTF, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rollnoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(mark10TF, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)))
                        .addContainerGap())))
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
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fatherNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(casteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobileTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aadharTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(dobTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rollnoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(enrollTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(batchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(branchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mark10TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(scholarshipCombBoxTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
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
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
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
            JOptionPane.showMessageDialog(this, "PIN Matched! Access granted.");
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
        branchComboBox.setSelectedIndex(generateRandomDigit(4));
        genderComboBox.setSelectedIndex(generateRandomDigit(2));
        batchComboBox.setSelectedIndex(generateRandomDigit(3));
        casteComboBox.setSelectedIndex(generateRandomDigit(4));
        emailTF.setText(generateRandomFirstName() + generateRandomLastName() + generateRandomString(4) + "@gmail.com");
        mobileTF.setText(generateRandomString(10));
        mark10TF.setText(generateRandomString(2) + "." + generateRandomString(1));
        aadharTF.setText(generateRandomString(12));
        enrollTF.setText(generateRandomString(12));
//            showMsm.setText("Loaded Sample data 👍👍");
        dobTF.setText("2004-0" + generateRandomString(1) + "-0" + generateRandomString(1));
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

    private void enrollTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_enrollTFFocusLost
        // TODO add your handling code here:
        if (!isValidEnrollmentNumber(enrollTF.getText())) {
            enrollTF.setForeground(Color.RED);
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Not Valid Enrollment Number ");

        } else {

            enrollTF.setForeground(getorg());

        }
    }//GEN-LAST:event_enrollTFFocusLost

    private void mobileTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mobileTFKeyReleased
        // TODO add your handling code here:
        if (!isValidMobileNumber(mobileTF.getText())) {
            mobileTF.setForeground(Color.RED);
        } else {
            mobileTF.setForeground(getorg());
        }
    }//GEN-LAST:event_mobileTFKeyReleased

    private void enrollTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enrollTFKeyReleased

        if (!isValidEnrollmentNumber(enrollTF.getText())) {
            enrollTF.setForeground(Color.RED);

        } else {

            enrollTF.setForeground(getorg());

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_enrollTFKeyReleased

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

    private void batchComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_batchComboBoxActionPerformed

    private void dobTFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dobTFFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_dobTFFocusGained

    private void dobTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dobTFFocusLost

        // TODO add your handling code here:
//                if (isAbove15YearsOld(dobTF.getText())) {
//                dobTF.setForeground(getorg());
//
//                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Below 15 Age Not Allowed");
//                    System.out.println(" valid date " +dobTF.getText());
//
//           System.out.println(dobTF.getText());
//        } else {
//                            dobTF.setForeground(Color.RED);
//
//                    System.out.println(dobTF.getText());
//                    System.out.println("not valid date " +dobTF.getText());
//
//        }
    }//GEN-LAST:event_dobTFFocusLost

    private void scholarshipCombBoxTFItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_scholarshipCombBoxTFItemStateChanged
        // TODO add your handling code here:
        String aadhar = aadharTF.getText();         //6
        String branch = (String) branchComboBox.getSelectedItem();

        String rollno = batchComboBox.getSelectedItem().toString().substring(2) + "126" + branch.substring(0, 2) + aadhar.substring(8);
        rollnoTF.setText(rollno);
    }//GEN-LAST:event_scholarshipCombBoxTFItemStateChanged

    private void mark10TFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mark10TFKeyReleased
        // TODO add your handling code here:

        if (!isvalid10marks(mark10TF.getText())) {
            mark10TF.setForeground(Color.RED);

        } else {

            mark10TF.setForeground(getorg());

        }
    }//GEN-LAST:event_mark10TFKeyReleased

    private void dobTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dobTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dobTFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField aadharTF;
    private javax.swing.JTextField addressTF;
    private javax.swing.JComboBox<String> batchComboBox;
    private javax.swing.JComboBox<String> branchComboBox;
    private javax.swing.JComboBox<String> casteComboBox;
    private javax.swing.JTextField dobTF;
    private javax.swing.JTextField emailTF;
    private javax.swing.JTextField enrollTF;
    private javax.swing.JTextField fatherNameTF;
    private javax.swing.JTextField firstNameTF;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFrame jFrame1;
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
    private javax.swing.JTextField rollnoTF;
    private javax.swing.JComboBox<String> scholarshipCombBoxTF;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}
