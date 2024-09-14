package raven.forms.other;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import raven.application.LoginUser;
import raven.components.SimpleForm;
import raven.requiredFunctions.DatabaseConnection;
import static raven.requiredFunctions.Functions.searchbar;
import static raven.requiredFunctions.Functions.table_load;

/**
 *
 * @author Raven
 */
public class FormMySalary extends SimpleForm {

    public FormMySalary(String text
    ) {

        initComponents();

    }

    @Override
    public void formRefresh() {

    }

    @Override
    public void formInitAndOpen() {
        System.out.println("init and open");
    }

    @Override
    public void formOpen() {
        System.out.println("Open");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        searchBar = new javax.swing.JTextField();
        batchCombo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        batchfrom = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mySalaryTable = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Serif", 1, 26)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("My Salary ");

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 5));

        searchBar.setPreferredSize(new java.awt.Dimension(120, 32));
        searchBar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBarKeyReleased(evt);
            }
        });
        jPanel2.add(searchBar);

        batchCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Batch", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028" }));
        batchCombo.setPreferredSize(new java.awt.Dimension(120, 32));
        batchCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchComboActionPerformed(evt);
            }
        });
        jPanel2.add(batchCombo);

        jLabel3.setText("To");
        jLabel3.setPreferredSize(new java.awt.Dimension(20, 32));
        jPanel2.add(jLabel3);

        batchfrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Batch", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028" }));
        batchfrom.setPreferredSize(new java.awt.Dimension(120, 32));
        batchfrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchfromActionPerformed(evt);
            }
        });
        jPanel2.add(batchfrom);

        jButton2.setText("Show Salary");
        jButton2.setPreferredSize(new java.awt.Dimension(120, 32));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton1.setText("Button");
        jButton1.setPreferredSize(new java.awt.Dimension(72, 35));
        jPanel2.add(jButton1);

        jPanel1.setLayout(new java.awt.GridLayout());

        mySalaryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        mySalaryTable.setCellSelectionEnabled(false);
        mySalaryTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mySalaryTable.setPreferredSize(new java.awt.Dimension(1320, 120));
        mySalaryTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(mySalaryTable);

        jPanel1.add(jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        String Batch = (String) batchCombo.getSelectedItem();
        String BatchFrom = (String) batchfrom.getSelectedItem();
        if ("All Batch".equals(Batch)) {
            Batch = "2000";
            System.out.println("Show all Batch Student");
        }
        if ("All Batch".equals(BatchFrom)) {
            BatchFrom = "2090";
            System.out.println("Show all Batch Student");
        }

        if (!LoginUser.getRole().equals("ADMIN")) {

            try {
                String myQuery = "select "
                        + "       TO_CHAR(salary.Date, 'YYYY-Mon') as \"Month\",base_pay as \"Basic Pay\", dearness_allowance as \"Dearness Allowance\", hra as HRA,professional_allowance as \"Professional Allowance\",\n"
                        + "       nps_emp_contribution as \"NPS Employee Contribution\", tds as \"TDS\", nps_empr_contribution as \"NPS Employee Contribution\", insurance, net_pay as \"Net Pay\", total_pay as \"Total Pay\", da_at as \"DA %\", nps_at as \"NPS %\",\n"
                        + "       tnps_at as \"NPS Employeer Contribution\", status\n"
                        + "from salary left join teacherform t on t.teacher_id = salary.teacher_id where t.teacher_id  like ? and salary.Date between '" + Batch + "-03-31" + "' and '" + BatchFrom + "-04-01'   ;";
//            String myQuery = "select concat( t.FirstName,\" \",t.LastName) as `Full Name`, DATE_FORMAT(salary.Date, '%Y-%b') as Month, `Base Pay`,`Dearness Allowance`,`TDS`,(`NPS_Emp_Contribution`) as \"NPS Employee Contribution\",(`NPS_Empr_Contribution`) as \"NPS Employer Contribution\",`Professional Allowance`,`Insurance`,`Net Pay`,`DA_at`, `NPS_at`,`TNPS_at`, salary.Status\n" +
//"from salary left join college_management.teacherform t on t.Teacher_id = salary.teacher_id";
                Connection sqlcon = DatabaseConnection.getInstance().getConnection();
                PreparedStatement query = sqlcon.prepareStatement(myQuery);
                query.setString(1, LoginUser.getId());
                table_load(mySalaryTable, DatabaseConnection.getInstance().getConnection(), query);

            } catch (SQLException ex) {
                Logger.getLogger(FormSalary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void searchBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBarKeyReleased
        // TODO add your handling code here:
        searchbar(mySalaryTable, searchBar.getText());
    }//GEN-LAST:event_searchBarKeyReleased

    private void batchComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchComboActionPerformed
        //                   try {
        //                       String yearfrom = batchCombo.getSelectedItem().toString();
        //                       String yearto =  String.valueOf(Integer.parseInt(yearfrom)-1);
        //            Connection sqlcon = DatabaseConnection.getInstance().getConnection();
        //            String myQuery = "select concat( t.FirstName,\" \",t.LastName) as `Full Name`, DATE_FORMAT(salary.Date, '%Y-%b'), `Base Pay`,`Dearness Allowance`,`TDS`,`NPS`,`Total NPS`,`Professional Allowance`,`Insurance`,`Net Pay`,`DA_at`, `NPS_at`,`TNPS_at`, salary.Status\n" +
        //"from salary left join college_management.teacherform t on t.Teacher_id = salary.teacher_id where salary.Date between '"+ yearto +"-03-31' and '"+yearfrom+"-04-01';";
        //            PreparedStatement query = sqlcon.prepareStatement(myQuery);
        ////            query.setString(1, branchCombo.getSelectedItem().toString());
        //            table_load(readTable, DatabaseConnection.getInstance().getConnection(), query);
        //
        //        } catch (SQLException ex) {
        //            Logger.getLogger(FormSalary.class.getName()).log(Level.SEVERE, null, ex);
        //        }
        //        UpdateFilterTable();
    }//GEN-LAST:event_batchComboActionPerformed

    private void batchfromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchfromActionPerformed
        // TODO add your handling code here:
        //        UpdateFilterTable();
    }//GEN-LAST:event_batchfromActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> batchCombo;
    private javax.swing.JComboBox<String> batchfrom;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable mySalaryTable;
    private javax.swing.JTextField searchBar;
    // End of variables declaration//GEN-END:variables
}
