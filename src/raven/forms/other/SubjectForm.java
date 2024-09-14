package raven.forms.other;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.application.LoginUser;
import raven.components.SimpleForm;
import raven.requiredFunctions.DatabaseConnection;
import static raven.requiredFunctions.Functions.exportToExcel;
import static raven.requiredFunctions.Functions.table_load;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class SubjectForm extends SimpleForm {

    public SubjectForm(String text) {

        initComponents();
        init();
    }

    @Override
    public void formRefresh() {
        System.err.println("Fresh Button CLick");
    }

    @Override
    public void formInitAndOpen() {
        System.out.println("init and open");
    }

    @Override
    public void formOpen() {
        System.out.println("Open");
    }

    private String branch;

    private void init() {
        subjectTeacherTF.setText(LoginUser.getUsername());
        if (LoginUser.getRole().equals("ADMIN")) {
            addSubjectBT.setEnabled(false);
        } else {
            branch = LoginUser.getBranch();
            branchLB.setText(branch);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        batchCB = new javax.swing.JComboBox<>();
        semCB = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        subjectNameTF = new javax.swing.JTextField();
        isTheoryCHB = new javax.swing.JCheckBox();
        isPraticalCHB = new javax.swing.JCheckBox();
        subjectTeacherTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        subjectCodeTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        addSubjectBT = new javax.swing.JButton();
        addSubjectBT1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        subjectTable = new javax.swing.JTable();
        showSubjectsBT = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        allSubjectTable = new javax.swing.JTable();
        showSubjectsBT1 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        branchLB = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ADD SUBJECT");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Batch");

        batchCB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        batchCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024", "2025", "2026", "2027" }));

        semCB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        semCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("SEM");

        subjectNameTF.setFont(new java.awt.Font("FiraCode Nerd Font Mono SemBd", 1, 14)); // NOI18N
        subjectNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectNameTFActionPerformed(evt);
            }
        });

        isTheoryCHB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        isTheoryCHB.setText("Theory ");
        isTheoryCHB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isTheoryCHBActionPerformed(evt);
            }
        });

        isPraticalCHB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        isPraticalCHB.setText("Pratical ");
        isPraticalCHB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isPraticalCHBActionPerformed(evt);
            }
        });

        subjectTeacherTF.setEditable(false);
        subjectTeacherTF.setFont(new java.awt.Font("FiraCode Nerd Font Mono SemBd", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Subject Code");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Subject Name");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Assigned Teacher");

        subjectCodeTF.setFont(new java.awt.Font("FiraCode Nerd Font Mono SemBd", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Subject Have");

        addSubjectBT.setBackground(new java.awt.Color(153, 255, 153));
        addSubjectBT.setFont(new java.awt.Font("Unitea Sans SemBd Ita", 0, 13)); // NOI18N
        addSubjectBT.setText("Add Subject");
        addSubjectBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSubjectBTActionPerformed(evt);
            }
        });

        addSubjectBT1.setBackground(new java.awt.Color(51, 153, 255));
        addSubjectBT1.setFont(new java.awt.Font("Unitea Sans SemBd Ita", 0, 13)); // NOI18N
        addSubjectBT1.setText("Update");
        addSubjectBT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSubjectBT1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(isTheoryCHB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(addSubjectBT, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addSubjectBT1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(isPraticalCHB, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(69, 69, 69)
                        .addComponent(subjectCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(subjectNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(subjectTeacherTF)))
                        .addGap(66, 66, 66))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(isTheoryCHB)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(isPraticalCHB)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(subjectNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subjectCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(subjectTeacherTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(addSubjectBT, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addComponent(addSubjectBT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1))
                .addGap(415, 415, 415))
        );

        jTabbedPane1.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 14)); // NOI18N

        subjectTable.setAutoCreateRowSorter(true);
        subjectTable.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 14)); // NOI18N
        subjectTable.setModel(new javax.swing.table.DefaultTableModel(
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
        subjectTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subjectTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(subjectTable);

        showSubjectsBT.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        showSubjectsBT.setText("Show Subjects");
        showSubjectsBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSubjectsBTActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("0xProto Nerd Font", 1, 13)); // NOI18N
        jButton12.setText("Export To Excel");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(showSubjectsBT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton12)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showSubjectsBT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Selected Semster Subjects", jPanel5);

        allSubjectTable.setFont(new java.awt.Font("0xProto Nerd Font Mono", 0, 13)); // NOI18N
        allSubjectTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(allSubjectTable);

        showSubjectsBT1.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        showSubjectsBT1.setText("Show All Subjects");
        showSubjectsBT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSubjectsBT1ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("0xProto Nerd Font", 1, 13)); // NOI18N
        jButton13.setText("Export To Excel");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(showSubjectsBT1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton13)
                        .addGap(0, 1113, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1398, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showSubjectsBT1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 329, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Selected Batch  All Subject", jPanel4);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        branchLB.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        branchLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        branchLB.setText("Branch");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(branchLB, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(batchCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(semCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(565, 565, 565))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(225, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batchCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(semCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(branchLB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1428, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void isPraticalCHBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isPraticalCHBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isPraticalCHBActionPerformed

    private void isTheoryCHBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isTheoryCHBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isTheoryCHBActionPerformed

    private void addSubjectBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSubjectBTActionPerformed
        // TODO add your handling code here:
        String subject_code = subjectCodeTF.getText(),
                batch = batchCB.getSelectedItem().toString(),
                subject_branch = branch,
                semster = semCB.getSelectedItem().toString(),
                subject_name = subjectNameTF.getText(),
                subject_teacher = LoginUser.getId();

        if (subject_code.isEmpty() || subject_code.length() != 3) {
            JOptionPane.showMessageDialog(this, "Please Enter Valid Subject Code");
        } else if (subject_name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Valid Subject Name");

        } else {
            int response
                    = JOptionPane.showConfirmDialog(
                            this,
                            "Do You To Submit Details ? ",
                            "Confirm",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) //                      System.out.println("Confirmed");
            {

                String myQuery
                        //                        1              2           3
                        = "insert into subjects (subject_code, batch, subject_branch, "
                        //                                              4            5          6
                        + "semster, subject_name, subject_teacher, "
                        //                                              7           8
                        + "ispratical, istheory) values "
                        + "(?,?,?,?,?,?,?,?);";
//                      1 2 3 4 5 6 7,8

                Boolean ispratical = isPraticalCHB.isSelected(), istheory = isTheoryCHB.isSelected();
                PreparedStatement query;
//                  String url = "jdbc:mysql://localhost:3306/students";
                try {
                    Connection sqlcon = DatabaseConnection.getInstance().getConnection();
                    query = sqlcon.prepareStatement(myQuery);
                    //                  query.setString(1, stdFnameTF.getText());
                    query.setString(1, subject_code);
                    //                  query.setString(2, stdLnametextField.getText());
                    query.setString(2, batch);
                    //                  query.setString(3, stdFathertextField.getText());
                    query.setString(3, subject_branch);
                    //                  query.setString(4, emailTextField.getText());
                    query.setString(4, semster);
                    query.setString(5, subject_name);
                    //                  query.setInt(5, Integer.parseInt(Student_Mobile_Number));
                    query.setString(6, subject_teacher);
                    query.setBoolean(7, ispratical);
                    query.setBoolean(8, istheory);

                    System.out.print(query.getResultSet());
                    System.out.print(query);
                    int q = query.executeUpdate();
                    System.out.print(query.getMetaData());
                    if (q > 0) {
                        JOptionPane.showMessageDialog(this, subject_name + " " + subject_code + " Added Successfully");
                    } else {
                        JOptionPane.showConfirmDialog(this, "Failed To Marks of " + subject_name + " " + subject_code);

                    }
                    query.close();
//                table_load(subjectTable, prepquery);
                    subjectCodeTF.setText("");
                    subjectNameTF.setText("");
                } catch (Exception e) {
                    JOptionPane.showConfirmDialog(this, e.getMessage());

                }
            } else {
            }
        }
        try {
            String myQuery = "select subject_code, batch, semster, subject_name, t.firstname || ' ' || t.lastname as \"Subject Teacher\", ispratical, istheory from subjects left join public.teacherform t on t.teacher_id = subjects.subject_teacher where Subject_Branch = ? and batch = ? and semster = ?;";
            PreparedStatement prepquery = DatabaseConnection.getInstance().getConnection().prepareStatement(myQuery);
            prepquery.setString(1, branch);
            prepquery.setString(2, batchCB.getSelectedItem().toString());
            prepquery.setString(3, semCB.getSelectedItem().toString());

            table_load(subjectTable, prepquery);
        } catch (SQLException ex) {
            Logger.getLogger(SubjectForm.class.getName()).log(Level.SEVERE, null, ex);
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, ex.getMessage());
        }
    }//GEN-LAST:event_addSubjectBTActionPerformed

    private void subjectNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subjectNameTFActionPerformed

    private void addSubjectBT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSubjectBT1ActionPerformed
        // TODO add your handling code here:
        String subject_code = subjectCodeTF.getText(),
                batch = batchCB.getSelectedItem().toString(),
                subject_branch = branch,
                semster = semCB.getSelectedItem().toString(),
                subject_name = subjectNameTF.getText(),
                subject_teacher = LoginUser.getId();
        if (subject_code.isEmpty() || subject_code.length() != 3) {
            JOptionPane.showMessageDialog(this, "Please Enter Valid Subject Code");
        } else if (subject_name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Valid Subject Name");

        } else {

            Boolean ispratical = isPraticalCHB.isSelected(), istheory = isTheoryCHB.isSelected();
            String updateQuery = "update subjects\n"
                    //         1             2            3             4             5                 6               7               8
                    + "set subject_code=?, batch=?, subject_branch=?, semster=?, subject_name=?, subject_teacher=?, ispratical=?, istheory=? "
                    //9                     10          11              12
                    + "where subject_code = ? and batch=? and subject_branch=? and semster=?;";
            try {
//            String myQuery = "SELECT * FROM subjects WHERE subject_branch like ?;";
                PreparedStatement prepquery = DatabaseConnection.getInstance().getConnection().prepareStatement(updateQuery);
                prepquery.setString(1, subject_code);
                prepquery.setString(2, batch);
                prepquery.setString(3, subject_branch);
                prepquery.setString(4, semster);
                prepquery.setString(5, subject_name);
                prepquery.setString(6, subject_teacher);
                prepquery.setBoolean(7, ispratical);
                prepquery.setBoolean(8, istheory);
                prepquery.setString(9, sub_Code);
                prepquery.setString(10, batch);
                prepquery.setString(11, branch);
                prepquery.setString(12, semster);

                System.out.print(prepquery);
                int q = prepquery.executeUpdate();
                System.out.print(prepquery.getMetaData());
                if (q > 0) {
                    JOptionPane.showMessageDialog(this, subject_name + " Added Successfully");
                } else {
                    JOptionPane.showConfirmDialog(this, "Failed To ADD Subject " + subject_name);

                }
                prepquery.close();
//            table_load(subjectTable, prepquery);
            } catch (SQLException ex) {
                Logger.getLogger(SubjectForm.class.getName()).log(Level.SEVERE, null, ex);
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, ex.getMessage());
                JOptionPane.showMessageDialog(this, "Can't Update Other Teacher Subjects :-(");
            }
        }
        try {
            String myQuery = "select subject_code, batch, semster, subject_name, t.firstname || ' ' || t.lastname as \"Subject Teacher\", ispratical, istheory from subjects left join public.teacherform t on t.teacher_id = subjects.subject_teacher where Subject_Branch = ? and batch = ? and semster = ?;";
            PreparedStatement prepquery = DatabaseConnection.getInstance().getConnection().prepareStatement(myQuery);
            prepquery.setString(1, branch);
            prepquery.setString(2, batchCB.getSelectedItem().toString());
            prepquery.setString(3, semCB.getSelectedItem().toString());

            table_load(subjectTable, prepquery);
        } catch (SQLException ex) {
            Logger.getLogger(SubjectForm.class.getName()).log(Level.SEVERE, null, ex);
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, ex.getMessage());
        }
    }//GEN-LAST:event_addSubjectBT1ActionPerformed

    private void showSubjectsBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showSubjectsBTActionPerformed
        // TODO add your handling code here:
        try {
            String myQuery = "select subject_code, batch, semster, subject_name, t.firstname || ' ' || t.lastname as \"Subject Teacher\", ispratical, istheory from subjects left join public.teacherform t on t.teacher_id = subjects.subject_teacher where Subject_Branch = ? and batch = ? and semster = ?;";
            PreparedStatement prepquery = DatabaseConnection.getInstance().getConnection().prepareStatement(myQuery);
            prepquery.setString(1, branch);
            prepquery.setString(2, batchCB.getSelectedItem().toString());
            prepquery.setString(3, semCB.getSelectedItem().toString());

            table_load(subjectTable, prepquery);
        } catch (SQLException ex) {
            Logger.getLogger(SubjectForm.class.getName()).log(Level.SEVERE, null, ex);
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, ex.getMessage());
        }
    }//GEN-LAST:event_showSubjectsBTActionPerformed
    public String sub_Code;
    private void subjectTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subjectTableMouseClicked
        // TODO add your handling code here:

        DefaultTableModel tableModel = (DefaultTableModel) subjectTable.getModel();
        int selectedRow = subjectTable.getSelectedRow();

//        String Name = tableModel.getValueAt(selectedRow, 0).toString() + " " + tableModel.getValueAt(selectedRow, 1).toString();
        String subjectCodeT = tableModel.getValueAt(selectedRow, 0).toString();
        String batchT = tableModel.getValueAt(selectedRow, 1).toString();
//        String sub_branch = tableModel.getValueAt(selectedRow, 2).toString();
        String semsterT = tableModel.getValueAt(selectedRow, 2).toString();
        String sub_name = tableModel.getValueAt(selectedRow, 3).toString();
        String sub_teacher = tableModel.getValueAt(selectedRow, 4).toString();
        String sub_pratical = tableModel.getValueAt(selectedRow, 5).toString();
        String sub_theory = tableModel.getValueAt(selectedRow, 6).toString();
        sub_Code = subjectCodeT;
        subjectNameTF.setText(sub_name);
        subjectTeacherTF.setText(sub_teacher);
        subjectCodeTF.setText(subjectCodeT);
        Object object = semsterT;
        Object objectb = batchT;
//        batchCB.setSelectedItem(objectb);
//        semCB.setSelectedItem(object);

        isTheoryCHB.setSelected(Boolean.parseBoolean(sub_theory));
        isPraticalCHB.setSelected(Boolean.parseBoolean(sub_pratical));
        //        subjectCodeTF.setText(sub_branch);
//        subjectCodeTF.

    }//GEN-LAST:event_subjectTableMouseClicked

    private void showSubjectsBT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showSubjectsBT1ActionPerformed
        // TODO add your handling code here:
        try {
            String myQuery = "select subject_code, batch, subject_branch, semster, subject_name, t.firstname || ' ' || t.lastname as \"Subject Teacher\", ispratical, istheory from subjects left join public.teacherform t on t.teacher_id = subjects.subject_teacher  where Subject_Branch = ? and batch = ? ;";
            PreparedStatement prepquery = DatabaseConnection.getInstance().getConnection().prepareStatement(myQuery);
            prepquery.setString(1, branch);
            prepquery.setString(2, batchCB.getSelectedItem().toString());
//            prepquery.setString(3, semCB.getSelectedItem().toString());

            table_load(allSubjectTable, prepquery);
        } catch (SQLException ex) {
            Logger.getLogger(SubjectForm.class.getName()).log(Level.SEVERE, null, ex);
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, ex.getMessage());
        }
    }//GEN-LAST:event_showSubjectsBT1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        subjectCodeTF.setText("");
        subjectNameTF.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        exportToExcel(allSubjectTable);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSubjectBT;
    private javax.swing.JButton addSubjectBT1;
    private javax.swing.JTable allSubjectTable;
    private javax.swing.JComboBox<String> batchCB;
    private javax.swing.JLabel branchLB;
    private javax.swing.JCheckBox isPraticalCHB;
    private javax.swing.JCheckBox isTheoryCHB;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> semCB;
    private javax.swing.JButton showSubjectsBT;
    private javax.swing.JButton showSubjectsBT1;
    private javax.swing.JTextField subjectCodeTF;
    private javax.swing.JTextField subjectNameTF;
    private javax.swing.JTable subjectTable;
    private javax.swing.JTextField subjectTeacherTF;
    // End of variables declaration//GEN-END:variables
}
