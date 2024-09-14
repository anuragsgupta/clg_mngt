package raven.forms.other;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import raven.application.LoginUser;
import raven.components.SimpleForm;
import raven.requiredFunctions.DatabaseConnection;
import static raven.requiredFunctions.Functions.exportToExcel;

/**
 *
 * @author Raven
 */
public class FormMark extends SimpleForm {

    public FormMark() {
        initComponents();
        branchLB.setText(LoginUser.getBranch());
        subTeacherTF.setText(LoginUser.getUsername());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studMarksTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        allStudentMarksTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        branchLB = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        batchCB = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        semCB = new javax.swing.JComboBox<>();
        loadBT = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        midSem1TF = new javax.swing.JTextField();
        midSem2TF = new javax.swing.JTextField();
        stdRollnoTF = new javax.swing.JTextField();
        endSemCB = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        stdNameTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        praticalMarksTF = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        subTeacherTF = new javax.swing.JTextField();
        subCodeTF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        midAvgTF = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        subNameCB = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        stdTable = new javax.swing.JTable();

        jLabel1.setBackground(new java.awt.Color(204, 204, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Marks");

        jTabbedPane1.setFont(new java.awt.Font("Unitea Sans SemBd", 0, 13)); // NOI18N

        studMarksTable.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 13)); // NOI18N
        studMarksTable.setModel(new javax.swing.table.DefaultTableModel(
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
        studMarksTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studMarksTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(studMarksTable);

        jButton1.setText("Show Students");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton6.setText("Export To Excel");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1278, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Subject Marks", jPanel2);

        jButton4.setFont(new java.awt.Font("0xProto Nerd Font Propo", 1, 13)); // NOI18N
        jButton4.setText("show all");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("0xProto Nerd Font Propo", 1, 13)); // NOI18N
        jButton5.setText("show subject wise");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("0xProto Nerd Font Propo", 1, 13)); // NOI18N
        jButton7.setText("Export To Excel");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        allStudentMarksTable.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 13)); // NOI18N
        allStudentMarksTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(allStudentMarksTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1266, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("All Students Marks", jPanel3);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 10));

        branchLB.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        branchLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        branchLB.setText("Branch");
        jPanel4.add(branchLB);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Batch");
        jPanel4.add(jLabel8);

        batchCB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        batchCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024", "2025", "2026", "2027" }));
        jPanel4.add(batchCB);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("SEM");
        jPanel4.add(jLabel9);

        semCB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        semCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));
        jPanel4.add(semCB);

        loadBT.setBackground(new java.awt.Color(153, 153, 255));
        loadBT.setText("Load  Students");
        loadBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBTActionPerformed(evt);
            }
        });
        jPanel4.add(loadBT);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Students Rollno");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("First Mid Sem Marks");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Second Mid Sem Marks");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("End Sem Grade");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Subject Code");

        midSem1TF.setFont(new java.awt.Font("Liberation Mono", 1, 13)); // NOI18N

        midSem2TF.setFont(new java.awt.Font("Liberation Mono", 1, 13)); // NOI18N

        stdRollnoTF.setEditable(false);
        stdRollnoTF.setText("jTextField1");

        endSemCB.setFont(new java.awt.Font("Liberation Mono", 1, 13)); // NOI18N
        endSemCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A", "B+", "B", "C+", "C", "D", "D+", "Pass with grace", "F", " " }));
        endSemCB.setSelectedIndex(4);
        endSemCB.setToolTipText("");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Students Name");

        stdNameTF.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Pratical Marks");

        praticalMarksTF.setFont(new java.awt.Font("Liberation Mono", 1, 13)); // NOI18N

        jButton2.setBackground(new java.awt.Color(153, 255, 153));
        jButton2.setFont(new java.awt.Font("0xProto Nerd Font Propo", 1, 13)); // NOI18N
        jButton2.setText("Save ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Subject Teacher");

        subTeacherTF.setEditable(false);
        subTeacherTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        subCodeTF.setEditable(false);
        subCodeTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Avg/best Marks");

        midAvgTF.setFont(new java.awt.Font("Liberation Mono", 1, 13)); // NOI18N

        jButton3.setBackground(new java.awt.Color(51, 153, 255));
        jButton3.setFont(new java.awt.Font("0xProto Nerd Font Propo", 1, 13)); // NOI18N
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        subNameCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Subject" }));
        subNameCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                subNameCBItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Subject Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stdRollnoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subCodeTF))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stdNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subTeacherTF))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(subNameCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endSemCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(midSem2TF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(praticalMarksTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(midAvgTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(midSem1TF)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(46, 46, 46))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(midSem1TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(midSem2TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(midAvgTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(praticalMarksTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(endSemCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(stdNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(subNameCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(stdRollnoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(subCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(subTeacherTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel5.add(jPanel1);

        stdTable.setFont(new java.awt.Font("0xProto Nerd Font Propo", 0, 13)); // NOI18N
        stdTable.setModel(new javax.swing.table.DefaultTableModel(
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
        stdTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stdTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(stdTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.add(jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
  HashMap<String, String> subTeachersHashMap = new HashMap<>();
    String[] subNameToArray;

//        String[] subNameToArray = new String[];
    ArrayList<String> subNameArray = new ArrayList<>();
    private void loadBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBTActionPerformed
        // TODO add your handling code here:
        subNameCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select Subject"}));
        subTeachersHashMap.clear();
        subNameArray.clear();
        String loadSubNameCodeQuery = "select subject_name,subject_code from subjects where subject_teacher = ?  and batch = ? and semster = ?;";

        String loadStudentQuery = "select firstname || ' ' || lastname as FullName,rollno from studentform where batch = ? and branch = ?;";
        ResultSet rs = null, loadStudentResultSet = null;
//        Connection sqlcon = ;
        String batch = batchCB.getSelectedItem().toString(),
                semster = semCB.getSelectedItem().toString();
        try {
            PreparedStatement query = DatabaseConnection.getInstance().getConnection().prepareStatement(loadSubNameCodeQuery);
            query.setString(1, LoginUser.getId());
            query.setString(2, batch);
            query.setString(3, semster);

            rs = query.executeQuery();

            PreparedStatement loadStudentquery = DatabaseConnection.getInstance().getConnection().prepareStatement(loadStudentQuery);
//            query.setString(1, LoginUser.getId());
            loadStudentquery.setString(1, batch);
            loadStudentquery.setString(2, branchLB.getText());
            loadStudentResultSet = loadStudentquery.executeQuery();
            stdTable.setModel(DbUtils.resultSetToTableModel(loadStudentResultSet));
            loadStudentquery.close();
            loadStudentResultSet.close();

            while (rs.next()) {
//                        label.setText(rs.getString(1));
                String subjectNames = rs.getString("subject_name");
                String subjectcode = rs.getString("subject_code");
                subNameArray.add(subjectNames);
                subTeachersHashMap.put(subjectNames, subjectcode);
//
                System.out.println("ID " + subjectNames + " Fname: " + subjectcode);

            }
            for (HashMap.Entry<String, String> entry : subTeachersHashMap.entrySet()) {
                System.out.println("Key = " + entry.getKey()
                        + ", Value = " + entry.getValue());
            }
            subNameToArray = subNameArray.toArray(new String[0]);

            subNameCB.setModel(new javax.swing.DefaultComboBoxModel<>(subNameToArray));
            String selecteditem = (String) subNameCB.getSelectedItem();
            if (subTeachersHashMap.containsKey(selecteditem)) {
                String a = subTeachersHashMap.get(selecteditem);
                subCodeTF.setText(a);
                // Printing value for the corresponding key

            }
//            rs.close();
//            query.close();
//            sqlcon.close();
//            subNameCB.setFocusable(true);
        } catch (SQLException aw) {
            System.out.println(aw.getMessage());
        } finally {
            // Close resources
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Error closing ResultSet: " + e.getMessage());
                }
            } else if (loadStudentResultSet != null) {
                try {
                    loadStudentResultSet.close();
                } catch (SQLException e) {
                    System.err.println("Error closing ResultSet: " + e.getMessage());
                }
            }

        }
//        yearCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{currentYear}));

    }//GEN-LAST:event_loadBTActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (subCodeTF.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Subject Name To Show Students");

        } else {
            try {
//            String query = "SELECT * FROM admissionform where "+ Query + " ;"; // Replace with your SQL query
                String query = "SELECT s.FirstName || ' ' || s.LastName AS \"FullName\",\n"
                        + "       t.FirstName || ' ' || t.LastName AS \"Teacher\",\n"
                        + "       s2.Subject_Name, marks.batch,marks.semster,sub_code,\n"
                        + "       marks.rollno, mid_sem_1, mid_sem_2, mid_sem_avg, pratical_marks, endsemgrade\n"
                        + "FROM marks\n"
                        + "         LEFT JOIN studentform AS s ON s.Rollno = marks.rollno\n"
                        + "         LEFT JOIN subjects AS s2 ON s2.Subject_Code = marks.Sub_code\n"
                        + "         LEFT JOIN public.teacherform t on s2.subject_teacher = t.teacher_id\n"
                        + "WHERE s2.subject_branch = ? and s2.batch = ? and s2.semster = ? and s2.subject_code=?;"; // Replace with your SQL query
                PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
                preparedStatement.setString(1, LoginUser.getBranch());
                preparedStatement.setString(2, batchCB.getSelectedItem().toString());
                preparedStatement.setString(3, semCB.getSelectedItem().toString());
//            preparedStatement.setString(3, subCodeTF.getText());
                preparedStatement.setString(4, subCodeTF.getText());
//            preparedStatement.setString(2, semCB.getSelectedItem().toString());
//            preparedStatement.setString(2, Caste);
//            preparedStatement.setString(3, branch);
//            preparedStatement.setString(4, gender);

                ResultSet resultSet = preparedStatement.executeQuery();
                studMarksTable.setModel(DbUtils.resultSetToTableModel(resultSet));
                preparedStatement.close();
                resultSet.close();
                System.err.println("Table Update");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        int response
                = JOptionPane.showConfirmDialog(
                        this,
                        "Do You To Submit Details ? ",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) //                      System.out.println("Confirmed");
        {
//                                                  1           2           3           4        5          6               7       8    9
            String myQuery = "insert into marks (rollno, pratical_marks, sub_code, mid_sem_1, mid_sem_2, mid_sem_avg, endsemgrade,batch,semster)\n"
                    + "values (? ,? ,? ,? ,? ,? ,? ,? ,?);";
            //                 1  2  3  4  5  6  7  8  9praticalMarks
//
            String batch = batchCB.getSelectedItem().toString(),
                    mid1marks = midSem1TF.getText(),
                    mid2marks = midSem2TF.getText(),
                    midAvgmarks = midAvgTF.getText(),
                    subCode = subCodeTF.getText(),
                    rollno = stdRollnoTF.getText(),
                    praticalMarks = praticalMarksTF.getText(),
                    semster = semCB.getSelectedItem().toString(),
                    endSemMarks = endSemCB.getSelectedItem().toString();

//            Boolean ispratical = isPraticalCHB.isSelected(), istheory = isTheoryCHB.isSelected();
            PreparedStatement query;
//                  String url = "jdbc:mysql://localhost:3306/students";
            try {
                Connection sqlcon = DatabaseConnection.getInstance().getConnection();
                query = sqlcon.prepareStatement(myQuery);
                //                  query.setString(1, stdFnameTF.getText());
//                setBigDecimal(9, new java.math.BigDecimal(tnps));
                query.setString(1, rollno);
                //                  query.setString(2, stdLnametextField.getText());
                query.setBigDecimal(2, new java.math.BigDecimal(Integer.parseInt(praticalMarks)));
//                query.setString(2, praticalMarks);
                //                  query.setString(3, stdFathertextField.getText());
                query.setString(3, subCode);
                //                  query.setString(4, emailTextField.getText());
                query.setBigDecimal(4, new java.math.BigDecimal(Integer.parseInt(mid1marks)));
                query.setBigDecimal(5, new java.math.BigDecimal(Integer.parseInt(mid2marks)));
                query.setBigDecimal(6, new java.math.BigDecimal(Integer.parseInt(midAvgmarks)));
//                query.setString(5, mid2marks);
                //                  query.setInt(5, Integer.parseInt(Student_Mobile_Number));
//                query.setString(6, midAvgmarks);
                query.setString(7, endSemCB.getSelectedItem().toString());
                query.setString(8, batch);
                query.setString(9, semster);
//                query.setBoolean(8, istheory);

                System.out.print(query.getResultSet());
                System.out.print(query);
                int q = query.executeUpdate();
                System.out.print(query.getMetaData());
                if (q > 0) {
                    JOptionPane.showMessageDialog(this, stdNameTF.getText() + "Marks Added Successfully");
                } else {
                    JOptionPane.showConfirmDialog(this, "Failed To Marks of" + rollno);

                }

                query.close();
//                table_load(subjectTable, prepquery);

            } catch (Exception e) {
                JOptionPane.showConfirmDialog(this, e.getMessage());

            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void subNameCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_subNameCBItemStateChanged
        // TODO add your handling code here:
        String selecteditem = (String) subNameCB.getSelectedItem();
        if (subTeachersHashMap.containsKey(selecteditem)) {

//            fullNameTF.setText(selecteditem);
            String a = subTeachersHashMap.get(selecteditem);
            subCodeTF.setText(a);
            // Printing value for the corresponding key

        }
    }//GEN-LAST:event_subNameCBItemStateChanged

    private void stdTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stdTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel tableModel = (DefaultTableModel) stdTable.getModel();
        int selectedRow = stdTable.getSelectedRow();

//        String Name = tableModel.getValueAt(selectedRow, 0).toString() + " " + tableModel.getValueAt(selectedRow, 1).toString();
        String fullname = tableModel.getValueAt(selectedRow, 0).toString();
        String rollno = tableModel.getValueAt(selectedRow, 1).toString();

        stdNameTF.setText(fullname);
        stdRollnoTF.setText(rollno);
    }//GEN-LAST:event_stdTableMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
//        String update = "update marks set rollno=?, sub_code=?, mid_sem_1=?, mid_sem_2=?, mid_sem_avg=?, pratical_marks=?, endsemgrade=?, batch=?, semster=? where markid = ?;";
        // 1            2           3             4                  5
        String update = "update marks set mid_sem_1=?, mid_sem_2=?, mid_sem_avg=?, pratical_marks=?, endsemgrade=? "
                //                          6                7           8           9
                + "where  rollno=? and sub_code=? and batch=? and semster=?;";

        String batch = batchCB.getSelectedItem().toString(),
                mid1marks = midSem1TF.getText(),
                mid2marks = midSem2TF.getText(),
                midAvgmarks = midAvgTF.getText(),
                subCode = subCodeTF.getText(),
                rollno = stdRollnoTF.getText(),
                praticalMarks = praticalMarksTF.getText(),
                semster = semCB.getSelectedItem().toString(),
                endSemMarks = endSemCB.getSelectedItem().toString();

        PreparedStatement query;
        try {
            Connection sqlcon = DatabaseConnection.getInstance().getConnection();
            query = sqlcon.prepareStatement(update);
            query.setString(6, rollno);
            query.setBigDecimal(4, new java.math.BigDecimal(Integer.parseInt(praticalMarks)));
            query.setString(7, subCode);
            query.setBigDecimal(1, new java.math.BigDecimal(Integer.parseInt(mid1marks)));
            query.setBigDecimal(2, new java.math.BigDecimal(Integer.parseInt(mid2marks)));
            query.setBigDecimal(3, new java.math.BigDecimal(Integer.parseInt(midAvgmarks)));
            query.setString(5, endSemCB.getSelectedItem().toString());
            query.setString(8, batch);
            query.setString(9, semster);

            System.out.print(query.getResultSet());
            System.out.print(query);
            Boolean q = query.execute();
            System.out.print(query.getMetaData());
            if (q) {
                JOptionPane.showMessageDialog(this, stdNameTF.getText() + "Marks Added Successfully");
            } else {
//                    JOptionPane.showConfirmDialog(this, "Failed To Add" + subject_name);

            }

            query.close();
//                table_load(subjectTable, prepquery);

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(this, e.getMessage());

        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (subCodeTF.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Subject Name To Show Students");

        } else {
            try {
//            String query = "SELECT * FROM admissionform where "+ Query + " ;"; // Replace with your SQL query
                String query = "SELECT s.FirstName || ' ' || s.LastName AS \"FullName\",\n"
                        + "       t.FirstName || ' ' || t.LastName AS \"Teacher\",\n"
                        + "       s2.Subject_Name, marks.batch,marks.semster,sub_code,\n"
                        + "       marks.rollno, mid_sem_1, mid_sem_2, mid_sem_avg, pratical_marks, endsemgrade\n"
                        + "FROM marks\n"
                        + "         LEFT JOIN studentform AS s ON s.Rollno = marks.rollno\n"
                        + "         LEFT JOIN subjects AS s2 ON s2.Subject_Code = marks.Sub_code\n"
                        + "         LEFT JOIN public.teacherform t on s2.subject_teacher = t.teacher_id\n"
                        + "WHERE s2.subject_branch = ? and s2.batch = ? and s2.semster = ? ;"; // Replace with your SQL query
                PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
                preparedStatement.setString(1, LoginUser.getBranch());
                preparedStatement.setString(2, batchCB.getSelectedItem().toString());
                preparedStatement.setString(3, semCB.getSelectedItem().toString());
//            preparedStatement.setString(3, subCodeTF.getText());
//                preparedStatement.setString(4, subCodeTF.getText());
//            preparedStatement.setString(2, semCB.getSelectedItem().toString());
//            preparedStatement.setString(2, Caste);
//            preparedStatement.setString(3, branch);
//            preparedStatement.setString(4, gender);

                ResultSet resultSet = preparedStatement.executeQuery();
                allStudentMarksTable.setModel(DbUtils.resultSetToTableModel(resultSet));
                preparedStatement.close();
                resultSet.close();
                System.err.println("Table Update");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (subCodeTF.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Subject Name To Show Students");

        } else {
            try {
//            String query = "SELECT * FROM admissionform where "+ Query + " ;"; // Replace with your SQL query
                String query = "SELECT s.FirstName || ' ' || s.LastName AS \"FullName\",\n"
                        + "       t.FirstName || ' ' || t.LastName AS \"Teacher\",\n"
                        + "       s2.Subject_Name, marks.batch,marks.semster,sub_code,\n"
                        + "       marks.rollno, mid_sem_1, mid_sem_2, mid_sem_avg, pratical_marks, endsemgrade\n"
                        + "FROM marks\n"
                        + "         LEFT JOIN studentform AS s ON s.Rollno = marks.rollno\n"
                        + "         LEFT JOIN subjects AS s2 ON s2.Subject_Code = marks.Sub_code\n"
                        + "         LEFT JOIN public.teacherform t on s2.subject_teacher = t.teacher_id\n"
                        + "WHERE s2.subject_branch = ? and s2.batch = ? and s2.semster = ?;"; // Replace with your SQL query
                PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
                preparedStatement.setString(1, LoginUser.getBranch());
                preparedStatement.setString(2, batchCB.getSelectedItem().toString());
                preparedStatement.setString(3, semCB.getSelectedItem().toString());
//                preparedStatement.setString(4, semCB.getSelectedItem().toString());

                ResultSet resultSet = preparedStatement.executeQuery();
                allStudentMarksTable.setModel(DbUtils.resultSetToTableModel(resultSet));
                preparedStatement.close();
                resultSet.close();
                System.err.println("Table Update");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void studMarksTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studMarksTableMouseClicked
        // TODO add your handling code here:

        DefaultTableModel tableModel = (DefaultTableModel) studMarksTable.getModel();
        int selectedRow = studMarksTable.getSelectedRow();

//        String Name = tableModel.getValueAt(selectedRow, 0).toString() + " " + tableModel.getValueAt(selectedRow, 1).toString();
        String fullname = tableModel.getValueAt(selectedRow, 0).toString();
        String teacher = tableModel.getValueAt(selectedRow, 1).toString();
        String sub_name = tableModel.getValueAt(selectedRow, 2).toString();
        String batch = tableModel.getValueAt(selectedRow, 3).toString();
        String semster = tableModel.getValueAt(selectedRow, 4).toString();
        String sub_code = tableModel.getValueAt(selectedRow, 5).toString();
        String rollno = tableModel.getValueAt(selectedRow, 6).toString();
        String mid_1 = tableModel.getValueAt(selectedRow, 7).toString();
        String mid_2 = tableModel.getValueAt(selectedRow, 8).toString();
        String mid_avg = tableModel.getValueAt(selectedRow, 9).toString();
        String pratical = tableModel.getValueAt(selectedRow, 10).toString();
        String grade = tableModel.getValueAt(selectedRow, 11).toString();

        stdNameTF.setText(fullname);
        subNameCB.setSelectedItem(sub_name);
        stdRollnoTF.setText(rollno);
        subCodeTF.setText(sub_code);
        semCB.setSelectedItem(semster);
        batchCB.setSelectedItem(batch);
        midSem1TF.setText(mid_1);
        midSem2TF.setText(mid_2);
        midAvgTF.setText(mid_avg);
        praticalMarksTF.setText(pratical);
        endSemCB.setSelectedItem(grade);

    }//GEN-LAST:event_studMarksTableMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        exportToExcel(studMarksTable);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        exportToExcel(allStudentMarksTable);

    }//GEN-LAST:event_jButton7ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable allStudentMarksTable;
    private javax.swing.JComboBox<String> batchCB;
    private javax.swing.JLabel branchLB;
    private javax.swing.JComboBox<String> endSemCB;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton loadBT;
    private javax.swing.JTextField midAvgTF;
    private javax.swing.JTextField midSem1TF;
    private javax.swing.JTextField midSem2TF;
    private javax.swing.JTextField praticalMarksTF;
    private javax.swing.JComboBox<String> semCB;
    private javax.swing.JTextField stdNameTF;
    private javax.swing.JTextField stdRollnoTF;
    private javax.swing.JTable stdTable;
    private javax.swing.JTable studMarksTable;
    private javax.swing.JTextField subCodeTF;
    private javax.swing.JComboBox<String> subNameCB;
    private javax.swing.JTextField subTeacherTF;
    // End of variables declaration//GEN-END:variables
}
