package raven.forms.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.raven.datechooser.DateChooser;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import raven.JasperReport.ReportManger;
import raven.components.SimpleForm;
import raven.requiredFunctions.DatabaseConnection;
import raven.toast.Notifications;

public class FormTeacherSalary extends SimpleForm {

    public Color getorg() {
//        dateChooser1.setSelectedDate();
        return npsEmpTF.getForeground();

    }
    /**
     * LocalDate currentDate = LocalDate.now();
     *
     * // Get the current year int currentYear = currentDate.getYear();
     *
     * @return the hraTF
     */
    HashMap<String, String> teachersList = new HashMap<>();
    String[] teacherLS;
    private DateChooser dateChooser = new DateChooser();

    private boolean validateTF(JTextField TF, String nameTF) {
        Boolean validBasicPay = false;
        try {
            int basicPay = Integer.parseInt(TF.getText());
            validBasicPay = true;
            TF.setForeground(Color.BLACK);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invaild Numeric Value\nPlease Enter Only Numeric Value In " + nameTF + ";)", "VALUE ERROR", JOptionPane.ERROR_MESSAGE);
            TF.setText("0");
            TF.setForeground(Color.RED);
//            PtTF.setFocusable(true);
        }
        return validBasicPay;
    }

    private void calculateSalary() {

        if (validAllPercent() && checkallTF()) {

            daTF.setText(String.valueOf(getDA_pay()));

            int totalNPSpercent = Integer.parseInt(totalNPSpercentTF.getText());
            long totalNPS_result = Math.round((totalNPSpercent / 100.0) * (getDA_pay() + getDA_pay()));
            npsEmpTF.setText(String.valueOf(getTotalNpsEmp_Pay()));

            // calculating  NPS Employe =  (  NPS percent / 100 ) * (Basic Pay + DA )
            int NpsEmpPercent = Integer.parseInt(NpsEmpPercentTF.getText());
            long NpsEmp_result = Math.round((NpsEmpPercent / 100.0) * (getbasic_pay() + getDA_pay()));

            totalNPSempTF.setText(String.valueOf(getNpsEmp_Pay()));

            //    (basic Pay   +  DA pay     + HRA)       - PT -     TDS -  NPS - insurance
            String netPay = String.valueOf(Integer.parseInt(BasicPayTF.getText()) + Integer.parseInt(hraTF.getText()) + Integer.parseInt(daTF.getText())
                    - Integer.parseInt(PtTF.getText()) - Integer.parseInt(tdsTF.getText()) - Integer.parseInt(totalNPSempTF.getText()) - Integer.parseInt(insuranceTF.getText()));
//            NetPayTF.setText(String.valueOf(getNet_pay()));

            String totalPay = String.valueOf(Integer.parseInt(BasicPayTF.getText()) + Integer.parseInt(hraTF.getText()) + Integer.parseInt(daTF.getText()));
            NetPayTF.setText(netPay);
//            totalPayTF.setText(String.valueOf(geTotal_pay()) );
            // total pay =  (basic Pay   +  DA pay     + HRA)
            totalPayTF.setText(totalPay);
        }

    }

    private boolean valiadValuesTF(JTextField TF, String nameTF, int leng) {
        Boolean validBasicPay = false;
        String input = TF.getText();
        boolean isNumeric = input.matches("\\d+");

        // Check the length of the string
        boolean isLengthValid = input.length() >= 0 && input.length() <= leng;

        // Check if the string is negative (contains a negative sign)
        boolean isNotNegative = !input.startsWith("-");

        if (!isNotNegative) {
            JOptionPane.showMessageDialog(this, "\nPlease Enter Postivite Numeric Value In " + nameTF + ";)", "Negative VALUE ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (!isNumeric) {
            JOptionPane.showMessageDialog(this, "\nPlease Enter Only Numeric Value In " + nameTF + ";)", "isNumeric VALUE ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (!isLengthValid) {
            JOptionPane.showMessageDialog(this, "\nPlease Enter Only Numeric Value In Range of 0 - " + leng + "in" + nameTF + ";)", "isNumeric VALUE ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            validBasicPay = true;
        }

        return validBasicPay;
    }

    private boolean validAllPercent() {
        Boolean da = false, nps = false, tnps = false;
        if (valiadValuesTF(daPercentTF, "DA Percent", 2)) {
            da = true;
        } else {
            daTF.setText("10");
        }
        if (valiadValuesTF(totalNPSpercentTF, "Total NPS Percent", 2)) {
            tnps = true;
        } else {
            totalNPSpercentTF.setText("10");
        }
        if (valiadValuesTF(NpsEmpPercentTF, "Total NPS Percent", 2)) {
            nps = true;
        } else {
            NpsEmpPercentTF.setText("10");
        }
        return da && nps && tnps;
    }

    public static boolean validateValue(String input, int leng) {
        // Check if the string contains only numeric characters
        boolean isNumeric = input.matches("\\d+");

        // Check the length of the string
        boolean isLengthValid = input.length() >= leng;

        // Check if the string is negative (contains a negative sign)
        boolean isNotNegative = !input.startsWith("-");

        // Return true if the string meets all criteria
        return isNumeric && isLengthValid && isNotNegative;
    }

    public FormTeacherSalary(String text) {
        initComponents();
        LocalDate currentDate = LocalDate.now();
//        DateChooser.setText();
        dateChooser.setTextField(jTextField1);
        dateChooser.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
//        dateChooser.setLabelCurrentDayVisible(false);
        dateChooser.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        // Get the current year
        String currentYear = Integer.toString(currentDate.getYear());
        String q = "select Teacher_id,FirstName,LastName from teacherform where type = 'PERMANENT';";
        Connection sqlcon = DatabaseConnection.getInstance().getConnection();

//        String[] teacherLS = new String[];
        ArrayList<String> teacherArray = new ArrayList<>();

        try (PreparedStatement query = sqlcon.prepareStatement(q)) {
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
//                        label.setText(rs.getString(1));
                String teacherIds = rs.getString("Teacher_id");
                String teacherNames = rs.getString("FirstName") + " " + rs.getString("LastName");
                teacherArray.add(teacherNames);
                teachersList.put(teacherNames, teacherIds);
//
                System.out.println("ID " + teacherIds + " Fname: " + teacherNames);

            }
            for (HashMap.Entry<String, String> entry : teachersList.entrySet()) {
                System.out.println("Key = " + entry.getKey()
                        + ", Value = " + entry.getValue());
            }
            teacherLS = teacherArray.toArray(new String[0]);

            query.close();
//            sqlcon.close();

        } catch (SQLException aw) {
            System.out.println(aw.getMessage());
        }
        yearCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{currentYear}));
        teachersListCB.setModel(new javax.swing.DefaultComboBoxModel<>(teacherLS));

        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        lb.setText(text);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        NpsEmpPercentTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        totalNPSpercentTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        daPercentTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        teachersListCB = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jTextField1 = new javax.swing.JTextField();
        lb = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        BasicPayTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        daTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        hraTF = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        PtTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        npsEmpTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tdsTF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        totalNPSempTF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        insuranceTF = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        NetPayTF = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        teacherIdTF = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        salaryStatusCB = new javax.swing.JComboBox<>();
        yearCB = new javax.swing.JComboBox<>();
        monthCB = new javax.swing.JComboBox<>();
        dayCB = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        totalPayTF = new javax.swing.JTextField();

        NpsEmpPercentTF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NpsEmpPercentTF.setText("10");
        NpsEmpPercentTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                NpsEmpPercentTFFocusLost(evt);
            }
        });
        NpsEmpPercentTF.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                NpsEmpPercentTFInputMethodTextChanged(evt);
            }
        });
        NpsEmpPercentTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NpsEmpPercentTFActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("NPS Employer Contribution  %");

        totalNPSpercentTF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        totalNPSpercentTF.setForeground(new java.awt.Color(102, 0, 204));
        totalNPSpercentTF.setText("14");
        totalNPSpercentTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                totalNPSpercentTFFocusLost(evt);
            }
        });
        totalNPSpercentTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalNPSpercentTFActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Month");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("DA %");

        daPercentTF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        daPercentTF.setForeground(new java.awt.Color(0, 0, 0));
        daPercentTF.setText("12");
        daPercentTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                daPercentTFFocusLost(evt);
            }
        });
        daPercentTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daPercentTFActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("NPS Employe  Contribution%");

        teachersListCB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        teachersListCB.setForeground(new java.awt.Color(51, 51, 255));
        teachersListCB.setMaximumRowCount(40);
        teachersListCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        teachersListCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                teachersListCBItemStateChanged(evt);
            }
        });
        teachersListCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teachersListCBActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Teacher Name");

        jTextField1.setText("jTextField1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(teachersListCB, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(daPercentTF, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel4)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(NpsEmpPercentTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(12, 12, 12)
                        .addComponent(totalNPSpercentTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teachersListCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(daPercentTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NpsEmpPercentTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalNPSpercentTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        lb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("TEACHER SALARY");

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Basic Pay");

        BasicPayTF.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        BasicPayTF.setText("60000");
        BasicPayTF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        BasicPayTF.setDisabledTextColor(new java.awt.Color(153, 153, 255));
        BasicPayTF.setMargin(new java.awt.Insets(0, 5, 0, 0));
        BasicPayTF.setMinimumSize(new java.awt.Dimension(200, 35));
        BasicPayTF.setName(""); // NOI18N
        BasicPayTF.setPreferredSize(new java.awt.Dimension(250, 25));
        BasicPayTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                BasicPayTFFocusLost(evt);
            }
        });
        BasicPayTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BasicPayTFActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Dearness Allowance");

        daTF.setEditable(false);
        daTF.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        daTF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        daTF.setMargin(new java.awt.Insets(0, 5, 0, 0));
        daTF.setMinimumSize(new java.awt.Dimension(200, 35));
        daTF.setName(""); // NOI18N
        daTF.setPreferredSize(new java.awt.Dimension(150, 25));
        daTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daTFActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("House Rental Allowance");

        hraTF.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        hraTF.setText("700");
        hraTF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        hraTF.setMargin(new java.awt.Insets(0, 5, 0, 0));
        hraTF.setMinimumSize(new java.awt.Dimension(200, 35));
        hraTF.setName(""); // NOI18N
        hraTF.setPreferredSize(new java.awt.Dimension(250, 25));
        hraTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                hraTFFocusLost(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Professional Allowance");

        PtTF.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        PtTF.setText("0");
        PtTF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        PtTF.setMargin(new java.awt.Insets(0, 5, 0, 0));
        PtTF.setMinimumSize(new java.awt.Dimension(200, 35));
        PtTF.setName(""); // NOI18N
        PtTF.setPreferredSize(new java.awt.Dimension(150, 25));
        PtTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PtTFFocusLost(evt);
            }
        });
        PtTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PtTFActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("NPS Employe Contribution");

        npsEmpTF.setEditable(false);
        npsEmpTF.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        npsEmpTF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        npsEmpTF.setMargin(new java.awt.Insets(0, 5, 0, 0));
        npsEmpTF.setMinimumSize(new java.awt.Dimension(200, 35));
        npsEmpTF.setName(""); // NOI18N
        npsEmpTF.setPreferredSize(new java.awt.Dimension(250, 25));

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Tax Deduction at Source");

        tdsTF.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        tdsTF.setText("0000");
        tdsTF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tdsTF.setMargin(new java.awt.Insets(0, 5, 0, 0));
        tdsTF.setMinimumSize(new java.awt.Dimension(200, 35));
        tdsTF.setName(""); // NOI18N
        tdsTF.setPreferredSize(new java.awt.Dimension(150, 25));
        tdsTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tdsTFFocusLost(evt);
            }
        });
        tdsTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tdsTFActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("NPS Employer Contribution");

        totalNPSempTF.setEditable(false);
        totalNPSempTF.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        totalNPSempTF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        totalNPSempTF.setMargin(new java.awt.Insets(0, 5, 0, 0));
        totalNPSempTF.setMinimumSize(new java.awt.Dimension(200, 35));
        totalNPSempTF.setName(""); // NOI18N
        totalNPSempTF.setPreferredSize(new java.awt.Dimension(250, 25));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Insurance");

        insuranceTF.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        insuranceTF.setText("00");
        insuranceTF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        insuranceTF.setMargin(new java.awt.Insets(0, 5, 0, 0));
        insuranceTF.setMinimumSize(new java.awt.Dimension(200, 35));
        insuranceTF.setName(""); // NOI18N
        insuranceTF.setPreferredSize(new java.awt.Dimension(150, 25));
        insuranceTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                insuranceTFFocusLost(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Net Pay");

        NetPayTF.setEditable(false);
        NetPayTF.setFont(new java.awt.Font("FiraCode Nerd Font Mono", 1, 16)); // NOI18N
        NetPayTF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        NetPayTF.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        NetPayTF.setMargin(new java.awt.Insets(0, 5, 0, 0));
        NetPayTF.setMinimumSize(new java.awt.Dimension(200, 35));
        NetPayTF.setName(""); // NOI18N
        NetPayTF.setPreferredSize(new java.awt.Dimension(250, 25));

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Add Pay Check");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setMultiClickThreshhold(0);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Date (yyyy-MM-dd)");

        jButton3.setBackground(new java.awt.Color(153, 153, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Calculate");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setMultiClickThreshhold(0);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Teacher ID");

        teacherIdTF.setFont(new java.awt.Font("FiraCode Nerd Font Med", 1, 16)); // NOI18N
        teacherIdTF.setForeground(new java.awt.Color(102, 102, 255));
        teacherIdTF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        teacherIdTF.setDisabledTextColor(new java.awt.Color(51, 51, 255));
        teacherIdTF.setEnabled(false);
        teacherIdTF.setMargin(new java.awt.Insets(0, 5, 0, 0));
        teacherIdTF.setMinimumSize(new java.awt.Dimension(200, 35));
        teacherIdTF.setName(""); // NOI18N
        teacherIdTF.setPreferredSize(new java.awt.Dimension(150, 25));
        teacherIdTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherIdTFActionPerformed(evt);
            }
        });

        jButton4.setText("Load Saved Config");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Salary Status");

        salaryStatusCB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        salaryStatusCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PAID", "DRAFT" }));

        yearCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2023" }));

        monthCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "12" }));
        monthCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthCBActionPerformed(evt);
            }
        });

        dayCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        dayCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayCBActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Total Pay");

        totalPayTF.setEditable(false);
        totalPayTF.setFont(new java.awt.Font("FiraCode Nerd Font Mono", 1, 16)); // NOI18N
        totalPayTF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        totalPayTF.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        totalPayTF.setMargin(new java.awt.Insets(0, 5, 0, 0));
        totalPayTF.setMinimumSize(new java.awt.Dimension(200, 35));
        totalPayTF.setName(""); // NOI18N
        totalPayTF.setPreferredSize(new java.awt.Dimension(250, 25));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(57, 57, 57)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BasicPayTF, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(teacherIdTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tdsTF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(insuranceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(npsEmpTF, javax.swing.GroupLayout.PREFERRED_SIZE, 199, Short.MAX_VALUE)
                                    .addComponent(totalNPSempTF, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(53, 53, 53)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(PtTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(13, 13, 13))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(daTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(NetPayTF, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(yearCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(monthCB, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dayCB, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(totalPayTF, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(63, 63, 63))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(salaryStatusCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(hraTF, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(13, 13, 13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teacherIdTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(yearCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BasicPayTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tdsTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel10))
                            .addComponent(PtTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(hraTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(insuranceTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(npsEmpTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(daTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(totalNPSempTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NetPayTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalPayTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(salaryStatusCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void monthCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthCBActionPerformed

    private void daPercentTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daPercentTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_daPercentTFActionPerformed

    private void NpsEmpPercentTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NpsEmpPercentTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NpsEmpPercentTFActionPerformed

    private void totalNPSpercentTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalNPSpercentTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalNPSpercentTFActionPerformed

    private void BasicPayTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BasicPayTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BasicPayTFActionPerformed

    private void BasicPayTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BasicPayTFFocusLost
        // TODO add your handling code here:
//        validAllPercent();

        if (valiadValuesTF(BasicPayTF, "Basic Pay", 6)) {

            daTF.setText(String.valueOf(getDA_pay()));

            int totalNPSpercent = Integer.parseInt(totalNPSpercentTF.getText());
            long totalNPS_result = Math.round((totalNPSpercent / 100.0) * (getDA_pay() + getDA_pay()));
            npsEmpTF.setText(String.valueOf(getTotalNpsEmp_Pay()));

            // calculating  NPS Employe =  (  NPS percent / 100 ) * (Basic Pay + DA )
            int NpsEmpPercent = Integer.parseInt(NpsEmpPercentTF.getText());
            long NpsEmp_result = Math.round((NpsEmpPercent / 100.0) * (getbasic_pay() + getDA_pay()));
            totalNPSempTF.setText(String.valueOf(getNpsEmp_Pay()));
        } else {
            BasicPayTF.setText("0");
//            JOptionPane.showMessageDialog(this, "Invaild Value In Basic Pay", "OOPS ERROR", JOptionPane.ERROR_MESSAGE);
        }

//        basicK.setText(String.valueOf(abbreviateNumber(Integer.parseInt(BasicPayTF.getText()))) + "K Rs");
    }//GEN-LAST:event_BasicPayTFFocusLost

    private long getNet_pay() {
        //    c    (basic Pay   +  DA pay     + HRA)       - PT -     TDS -  NPS - insurance
        return (getbasic_pay() + getDA_pay() + getHRA() - getPT() - getTDS() - getNpsEmp_Pay() - getInsurance());

    }

    private long geTotal_pay() {
        //        (basic Pay   +  DA pay     + HRA +  NPS )
        return (getbasic_pay() + getDA_pay() + getHRA() + getNpsEmp_Pay());

    }

    private long getbasic_pay() {
        return Long.parseLong(BasicPayTF.getText());
    }

    private long getTotalNpsEmp_Pay() {
        int totalNPSpercent = Integer.parseInt(totalNPSpercentTF.getText());
        return Math.round((totalNPSpercent / 100.0) * (getbasic_pay() + getDA_pay()));

    }

    private long getNpsEmp_Pay() {

        int NpsEmpPercent = Integer.parseInt(NpsEmpPercentTF.getText());
        return Math.round((NpsEmpPercent / 100.0) * (getbasic_pay() + getDA_pay()));

    }

    private long getHRA() {
        return Integer.parseInt(hraTF.getText());
    }

    private long getTDS() {
        return Integer.parseInt(tdsTF.getText());
    }

    private long getPT() {
        return Integer.parseInt(PtTF.getText());
    }

    /**
     * @return the insuranceTF
     */
    private long getInsurance() {
        return Integer.parseInt(insuranceTF.getText());
    }

    private long getDA_pay() {

//         float basicPay = Integer.parseInt(BasicPayTF.getText());
        float daPercent = Integer.parseInt(daPercentTF.getText());
        // calculating Dearness allowance = (DA /100)  * Basic Pay
        return Math.round((daPercent / 100.0) * getbasic_pay()); // Note the use of 100.0 to ensure floating-point division

    }

    private void insuranceTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_insuranceTFFocusLost
        // TODO add your handling code here:
        Boolean pass = false;
        if (validAllPercent()) {
            System.err.println("Pass");
            pass = true;
        }
        if (valiadValuesTF(PtTF, "Professional Allowance", 6) && pass) {
//            NetPayTF.setText(String.valueOf(getNet_pay()));
//            System.err.println("pass");
            calculateSalary();
        }

    }//GEN-LAST:event_insuranceTFFocusLost

    public static String convertDate(String dateString) {
        // Split the input date string into components using "-"
        String[] dateComponents = dateString.split("-");

        // Extract the year and day from the components
        String year = dateComponents[0];
        String month = dateComponents[1];

        // Concatenate the day and the last two characters of the year
        String formattedDate = year.substring(2) + month;

        // Return the formatted date string
        return formattedDate;
    }
    private void PtTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PtTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PtTFActionPerformed

    private void tdsTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tdsTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tdsTFActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Boolean pass = false;

        if (validAllPercent()) {
            System.err.println("Pass");
            pass = true;
        }
        String status = (String) salaryStatusCB.getSelectedItem();

        if (teacherIdTF.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Select Teacher To Proceed");
        } else if (NetPayTF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill All Required Field To Proceed");
        } else {
            calculateSalary();
//            String date = jTextField1.getText(); //14
            String date = yearCB.getSelectedItem().toString() + "-" + monthCB.getSelectedItem().toString() + "-" + dayCB.getSelectedItem().toString(); //14
            String teacherId = teacherIdTF.getText();
            String salaryID = teacherId.substring(8) + convertDate(date);
            String da = daTF.getText(); //                                                                                                                                                                                                                                                                                                                                                                                                                  ,                                                       1
            String tds = tdsTF.getText(); //2
            String basic = BasicPayTF.getText(); //3
            String hra = hraTF.getText(); //4
            String insurance = insuranceTF.getText(); //5
            String tnps = totalNPSempTF.getText(); //6
            String nps = npsEmpTF.getText(); //7
            String gradePay = daTF.getText(); //8
            String totalPay = totalPayTF.getText(); //9
            String pt = PtTF.getText(); //10
            String NetPay = NetPayTF.getText(); //11
            String daPercent = daPercentTF.getText(); //12
            String npsEmpPercent = NpsEmpPercentTF.getText(); //13
            String totalNpsEmpPercent = totalNPSpercentTF.getText(); //14
//            String totalNpsEmpPercent = totalPayTF.getText(); //14
            String myquery = "INSERT INTO salary\n"
                    //   1             2           3       4                   5         6                          7             8
                    + "(salary_id, teacher_id, Date, base_Pay, Dearness_Allowance, Professional_Allowance, NPS_Emp_Contribution, TDS,"
                    + //                        9           10          11          12        13   14        15        16   17
                    "               NPS_Empr_Contribution, Insurance, Net_Pay, Total_Pay, DA_at,  NPS_at, TNPS_at,  Status ,hra)\n"
                    + "VALUES\n"
                    //  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15  16 17
                    + "(?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?  ,?,?);";

            String fd = da + " " + tds + " " + basic + " " + hra + " " + insurance + " " + tnps + " " + nps + " " + gradePay + " " + totalPay + " " + pt + " " + NetPay;
            System.err.println(fd);
            PreparedStatement query;
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
                    Connection sqlcon = DatabaseConnection.getInstance().getConnection();
                    query = sqlcon.prepareStatement(myquery);
                    query.setString(1, salaryID);
                    query.setString(2, teacherId);
                    query.setDate(3, java.sql.Date.valueOf(date));
                    query.setBigDecimal(4, new java.math.BigDecimal(basic));
                    query.setBigDecimal(5, new java.math.BigDecimal(da));
                    query.setBigDecimal(6, new java.math.BigDecimal(pt));
                    query.setBigDecimal(7, new java.math.BigDecimal(nps));
                    query.setBigDecimal(8, new java.math.BigDecimal(tds));
                    query.setBigDecimal(9, new java.math.BigDecimal(tnps));
                    query.setBigDecimal(10, new java.math.BigDecimal(insurance));
                    query.setBigDecimal(11, new java.math.BigDecimal(NetPay));
                    query.setBigDecimal(12, new java.math.BigDecimal(totalPay));
                    query.setBigDecimal(13, new java.math.BigDecimal(daPercent));
                    query.setBigDecimal(14, new java.math.BigDecimal(npsEmpPercent));
                    query.setBigDecimal(15, new java.math.BigDecimal(totalNpsEmpPercent));
                    query.setBigDecimal(17, new java.math.BigDecimal(hra));
                    query.setString(16, status);
                    System.out.print(query.getResultSet());
                    System.out.print(query);
                    System.out.print(query.getMetaData());
                    query.execute();

                    query.close();
//                    sqlcon.close();
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "New Admission Data Submitted Successfully 😊😊");

                } catch (SQLException e) {
                    System.err.println(e);
                    JOptionPane.showMessageDialog(this, e, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            HashMap<String, Object> ReportParmeterHashMap = new HashMap<String, Object>();
            ReportParmeterHashMap.put("DA", daTF.getText());
            ReportParmeterHashMap.put("TDS", tdsTF.getText());
            ReportParmeterHashMap.put("Basic", BasicPayTF.getText());
            ReportParmeterHashMap.put("HRA", hraTF.getText());
            ReportParmeterHashMap.put("Insurance", insuranceTF.getText());
            ReportParmeterHashMap.put("TNPS", totalNPSempTF.getText());
            ReportParmeterHashMap.put("NPS", npsEmpTF.getText());
            ReportParmeterHashMap.put("GradePay", "NUll");
            ReportParmeterHashMap.put("TotalPay", npsEmpTF.getText());
            ReportParmeterHashMap.put("PT", npsEmpTF.getText());
            ReportParmeterHashMap.put("Name", teachersListCB.getSelectedItem().toString());
            ReportParmeterHashMap.put("SlipOfMonth", "er");
            ReportParmeterHashMap.put("Designation", "tte");
            ReportParmeterHashMap.put("SlipNo", "erg");

            ReportParmeterHashMap.put("NetPay", NetPayTF.getText());
            ReportManger.getInstance().ReportMangerX("D:\\Serious Project\\College-Management-Software\\src\\JasperReport\\teacherPaySlip.jrxml", ReportParmeterHashMap);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void daTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_daTFActionPerformed

    private void teacherIdTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherIdTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherIdTFActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        calculateSalary();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void teachersListCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_teachersListCBItemStateChanged
        // TODO add your handling code here:
        String selecteditem = (String) teachersListCB.getSelectedItem();
        if (teachersList.containsKey(selecteditem)) {

//            fullNameTF.setText(selecteditem);
            String a = teachersList.get(selecteditem);
            teacherIdTF.setText(a);
            // Printing value for the corresponding key

        }
    }//GEN-LAST:event_teachersListCBItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        BasicPayTF.setText("63100");
        PtTF.setText("0");
        insuranceTF.setText("000");
        tdsTF.setText("10000");
        hraTF.setText("709");

    }//GEN-LAST:event_jButton4ActionPerformed

    private Boolean checkallTF() {
        Boolean pt = false, hra = false, tds = false, basic = false;

        if (valiadValuesTF(BasicPayTF, "Basic Pay", 7)) {
            basic = true;
        }

        if (valiadValuesTF(PtTF, "Professional Allowance", 7)) {
            pt = true;
        }
        if (valiadValuesTF(hraTF, "House Rental Allowance", 5)) {
            hra = true;
        }
        if (valiadValuesTF(tdsTF, "TDS", 6)) {
            tds = true;
        }
        return pt && hra && tds && basic;
    }
    private void PtTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PtTFFocusLost
        // TODO add your handling code here:

        if (validateValue(BasicPayTF.getText(), 6) && validateTF(PtTF, "Professional Allowance")) {
            validateTF(PtTF, "Professional Allowance");
        }

    }//GEN-LAST:event_PtTFFocusLost

    private void hraTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hraTFFocusLost
        // TODO add your handling code here:
        if (validateTF(hraTF, "House Rental Allowance")) {
            calculateSalary();

        }

    }//GEN-LAST:event_hraTFFocusLost

    private void tdsTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tdsTFFocusLost
        // TODO add your handling code here:
        validateTF(tdsTF, "TDS");

    }//GEN-LAST:event_tdsTFFocusLost

    private void dayCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayCBActionPerformed

    private void daPercentTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_daPercentTFFocusLost
        // TODO add your handling code here:
        validAllPercent();

    }//GEN-LAST:event_daPercentTFFocusLost

    private void NpsEmpPercentTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NpsEmpPercentTFFocusLost
        // TODO add your handling code here:
        validAllPercent();

    }//GEN-LAST:event_NpsEmpPercentTFFocusLost

    private void NpsEmpPercentTFInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_NpsEmpPercentTFInputMethodTextChanged
        // TODO add your handling code here:
        validAllPercent();

    }//GEN-LAST:event_NpsEmpPercentTFInputMethodTextChanged

    private void totalNPSpercentTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_totalNPSpercentTFFocusLost
        // TODO add your handling code here:
        validAllPercent();

    }//GEN-LAST:event_totalNPSpercentTFFocusLost

    private void teachersListCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teachersListCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teachersListCBActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BasicPayTF;
    private javax.swing.JTextField NetPayTF;
    private javax.swing.JTextField NpsEmpPercentTF;
    private javax.swing.JTextField PtTF;
    private javax.swing.JTextField daPercentTF;
    private javax.swing.JTextField daTF;
    private javax.swing.JComboBox<String> dayCB;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JTextField hraTF;
    private javax.swing.JTextField insuranceTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lb;
    private javax.swing.JComboBox<String> monthCB;
    private javax.swing.JTextField npsEmpTF;
    private javax.swing.JComboBox<String> salaryStatusCB;
    private javax.swing.JTextField tdsTF;
    private javax.swing.JTextField teacherIdTF;
    private javax.swing.JComboBox<String> teachersListCB;
    private javax.swing.JTextField totalNPSempTF;
    private javax.swing.JTextField totalNPSpercentTF;
    private javax.swing.JTextField totalPayTF;
    private javax.swing.JComboBox<String> yearCB;
    // End of variables declaration//GEN-END:variables
}
