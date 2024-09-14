package raven.forms.other;

//import static raven.forms.other.JdbcUtil;
import com.formdev.flatlaf.FlatClientProperties;
import com.raven.datechooser.DateBetween;
import com.raven.datechooser.DateChooser;
import com.raven.datechooser.listener.DateChooserAction;
import com.raven.datechooser.listener.DateChooserAdapter;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import raven.JasperReport.ReportManger;
import raven.application.LoginUser;
import raven.components.SimpleForm;
import raven.menu.FormManager;
import raven.requiredFunctions.DatabaseConnection;
import raven.requiredFunctions.Functions;
import static raven.requiredFunctions.Functions.createTableModel;
import static raven.requiredFunctions.Functions.searchbar;
import static raven.requiredFunctions.Functions.tableDataToExcel;
import static raven.requiredFunctions.Functions.table_load;

/**
 *
 * @author Raven
 */
public class FormSalary extends SimpleForm {

    String branhi = "%";
    String role;
    DefaultTableModel model = createTableModel();
    private DateChooser dateChooser = new DateChooser();
    private DateChooser dateChooser1 = new DateChooser();

// Handle any exceptions here
//        folder.Functions
    public FormSalary(String banche, String role) {
        initComponents();

        dateChooser.setTextField(dateBWBT);
        dateChooser.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);

        dateChooser1.setTextField(draftdateTF);
        dateChooser1.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
//        dateChooser.setLabelCurrentDayVisible(false);
        dateChooser1.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        this.role = LoginUser.getRole();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        branhi = "ADMIN".equals(LoginUser.getBranch()) ? "%" : LoginUser.getBranch();

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
        dateChooser.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                String batchFrom, batchTo;
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                batchFrom = df.format(date.getFromDate());
                batchTo = df.format(date.getToDate());
                System.err.println(batchFrom + " " + batchTo);
                try {
                    String Batch = (String) batchCombo.getSelectedItem();
                    String BatchFrom = (String) batchfrom.getSelectedItem();
                    String branch = (String) branchCombo.getSelectedItem();
//                    String Caste = (String) catergoryCombo.getSelectedItem();
                    String gender = (String) GenderCombox.getSelectedItem();

                    if ("All Batch".equals(Batch)) {
                        Batch = "2000";
                        System.out.println("Show all Batch Student");
                    }
                    if ("All Batch".equals(BatchFrom)) {
                        BatchFrom = "2090";
                        System.out.println("Show all Batch Student");
                    }

                    if ("All Branch".equals(branch)) {
                        branch = "%";
                        System.out.println("Show all Branch Student");
                    }

                    if ("All Gender".equals(gender)) {
                        gender = "%";
                        System.out.println("Show all gender Student");
                    }

//            String Query = " Batch like " + Batch + " and branch like " + branch + " and Category like " + Caste ;
//            String Query = " Batch like " + Batch + " and Category like " + Caste ;
//            fillTableModelFilter(JTable readTable, DatabaseConnection.getInstance().getConnection(), branch, Batch, Caste, gender);
                    Functions.fillTableModelFilter(readTable, branch, batchFrom, batchTo, "%", gender);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());

                    Logger.getLogger(FormSalary.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }
//    folder.Functions.table_load (readTable);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchBar = new javax.swing.JTextField();
        batchCombo = new javax.swing.JComboBox<>();
        refreshButton = new javax.swing.JButton();
        exportTable = new javax.swing.JButton();
        branchCombo = new javax.swing.JComboBox<>();
        showAllDetails = new javax.swing.JButton();
        GenderCombox = new javax.swing.JComboBox<>();
        jtableCellButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        batchfrom = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        dateBWBT = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        readTable = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        readTable2 = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        updateDraftBT = new javax.swing.JButton();
        draftdateTF = new javax.swing.JTextField();
        yearCB = new javax.swing.JComboBox<>();
        monthCB = new javax.swing.JComboBox<>();
        dayCB = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Teacher Salary's Records");

        jLabel1.setText("Search");

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

        batchCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Batch", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028" }));
        batchCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchComboActionPerformed(evt);
            }
        });

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        exportTable.setText("Export");
        exportTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportTableActionPerformed(evt);
            }
        });

        branchCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Branch", "CSE", "CIVIL", "ME", "ECE" }));
        branchCombo.setSelectedItem("All");
        branchCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                branchComboItemStateChanged(evt);
            }
        });
        branchCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                branchComboActionPerformed(evt);
            }
        });

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

        jtableCellButton.setText("Print");
        jtableCellButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtableCellButton.setEnabled(false);
        jtableCellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtableCellButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Show Teachers");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        batchfrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Batch", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028" }));
        batchfrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchfromActionPerformed(evt);
            }
        });

        jLabel3.setText("To");

        dateBWBT.setText("jTextField1");
        dateBWBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateBWBTActionPerformed(evt);
            }
        });

        //try {
            //            fillTableModel(model);
            //        } catch (SQLException e) {
            //            e.printStackTrace();
            //            // Handle any exceptions here
            //        }
        readTable.setAutoCreateRowSorter(true);
        readTable.setModel(model);
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
        jScrollPane2.setViewportView(readTable);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(512, 512, 512))
        );

        jTabbedPane1.addTab("All Salary ", jPanel2);

        //try {
            //            fillTableModel(model);
            //        } catch (SQLException e) {
            //            e.printStackTrace();
            //            // Handle any exceptions here
            //        }
        readTable2.setAutoCreateRowSorter(true);
        readTable2.setModel(model);
        readTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        readTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        readTable2.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        readTable2.setShowGrid(true);
        readTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                readTable2MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                readTable2MouseExited(evt);
            }
        });
        jScrollPane3.setViewportView(readTable2);

        jButton3.setText("show all Draft Salary");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        updateDraftBT.setText("Update Status To Paid");
        updateDraftBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDraftBTActionPerformed(evt);
            }
        });

        draftdateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                draftdateTFActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addGap(81, 81, 81)
                .addComponent(draftdateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateDraftBT, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yearCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(monthCB, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dayCB, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(yearCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(monthCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dayCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(draftdateTF)
                        .addComponent(updateDraftBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1246, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Draft Salary", jPanel9);

        jButton2.setText("Pay Salary");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchBar)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(showAllDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(batchCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(batchfrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(branchCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(exportTable, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(GenderCombox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateBWBT, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jtableCellButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(77, 77, 77))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showAllDetails)
                    .addComponent(refreshButton)
                    .addComponent(exportTable)
                    .addComponent(jtableCellButton)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batchCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(branchCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GenderCombox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batchfrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(dateBWBT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBarActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        jtableCellButton.setEnabled(true);

        try {
            String myQuery = "select  salary_id ,t.teacher_id,t.FirstName || ' ' || t.LastName as \"Full Name\",t.pan_card,t.department,\n"
                    + "       TO_CHAR(salary.Date, 'YYYY-Mon') as \"Month\",base_pay as \"Basic Pay\", dearness_allowance as \"Dearness Allowance\", hra as HRA,professional_allowance as \"Professional Allowance\",\n"
                    + "       nps_emp_contribution as \"NPS Employee Contribution\", tds as \"TDS\", nps_empr_contribution as \"NPS Employee Contribution\", insurance, net_pay as \"Net Pay\", total_pay as \"Total Pay\", da_at as \"DA %\", nps_at as \"NPS %\",\n"
                    + "       tnps_at as \"NPS Employeer Contribution\", status\n"
                    + "from salary left join teacherform t on t.teacher_id = salary.teacher_id;";
//            String myQuery = "select concat( t.FirstName,\" \",t.LastName) as `Full Name`, DATE_FORMAT(salary.Date, '%Y-%b') as Month, `Base Pay`,`Dearness Allowance`,`TDS`,(`NPS_Emp_Contribution`) as \"NPS Employee Contribution\",(`NPS_Empr_Contribution`) as \"NPS Employer Contribution\",`Professional Allowance`,`Insurance`,`Net Pay`,`DA_at`, `NPS_at`,`TNPS_at`, salary.Status\n" +
//"from salary left join college_management.teacherform t on t.Teacher_id = salary.teacher_id";
            Connection sqlcon = DatabaseConnection.getInstance().getConnection();
            PreparedStatement query = sqlcon.prepareStatement(myQuery);
//            query.setString(1, branhi);
            table_load(readTable, DatabaseConnection.getInstance().getConnection(), query);

        } catch (SQLException ex) {
            Logger.getLogger(FormSalary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void searchBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBarKeyReleased
        // TODO add your handling code here:
        searchbar(readTable, searchBar.getText());
    }//GEN-LAST:event_searchBarKeyReleased

    private void showAllDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showAllDetailsActionPerformed
//        String myQuery = "select * from maindetails WHERE branch = ?;";
        jtableCellButton.setEnabled(false);
//        jtableCellButton.setEnabled(Boolean.FALSE);

        try {
            String Batch = (String) batchCombo.getSelectedItem();
            String BatchFrom = (String) batchfrom.getSelectedItem();
            String branch = (String) branchCombo.getSelectedItem();
//            String Caste = (String) catergoryCombo.getSelectedItem();
            String gender = (String) GenderCombox.getSelectedItem();
            if ("All Batch".equals(Batch)) {
                Batch = "2000";
                System.out.println("Show all Batch Student");
            }
            if ("All Batch".equals(BatchFrom)) {
                BatchFrom = "2090";
                System.out.println("Show all Batch Student");
            }

            if ("All Branch".equals(branch)) {
                branch = "%";
                System.out.println("Show all Branch Student");
            }
//            if ("All Catergory".equals(Caste)) {
//                Caste = "%";
//                System.out.println("Show all caste Student");
//            }
            if ("All Gender".equals(gender)) {
                gender = "%";
                System.out.println("Show all gender Student");
            }
            Connection sqlcon = DatabaseConnection.getInstance().getConnection();
            String myQuery = "select t.teacher_id,t.FirstName || ' ' || t.LastName as \"Full Name\",t.pan_card,t.department,t.year_of_joining, \n"
                    + "       TO_CHAR(salary.Date, 'YYYY-Mon') as \"Month\",base_pay as \"Basic Pay\", dearness_allowance as \"DA\",hra as HRA, professional_allowance as \"PA\",\n"
                    + "       nps_emp_contribution as \"NPS Employee\", tds as \"TDS\", nps_empr_contribution as \"NPS Employer\", insurance, net_pay as \"Net Pay\", total_pay as \"Total Pay\", da_at as \"DA %\""
                    + "      , status\n,paid_date as \"Paid On\""
                    + "from salary left join teacherform t on t.teacher_id = salary.teacher_id where t.Department like '" + branch + "' and salary.Date between '" + Batch + "-03-31" + "' and '" + BatchFrom + "-04-01'  and t.Gender like '" + gender + "' ;";
//            +"from salary left join teacherform t on t.teacher_id = salary.teacher_id ;"; // Replace with your SQL query
//            Connection sqlcon = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = sqlcon.prepareStatement(myQuery);
//            preparedStatement.setString(1, branch);
////            preparedStatement.setString(2, batch);
////            preparedStatement.setString(2, Caste);
//            preparedStatement.setString(2, gender);

            ResultSet resultSet = preparedStatement.executeQuery();
            readTable.setModel(DbUtils.resultSetToTableModel(resultSet));
            preparedStatement.close();
//                while (resultSet.next()) {
            resultSet.close();
            System.err.println("Table Update");
        } catch (SQLException ex) {
            Logger.getLogger(FormSalary.class.getName()).log(Level.SEVERE, null, ex);
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
//        dateChooser.addActionDateChooserListener(new DateChooserAdapter() {
//            @Override
//            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
//                String batchFrom, batchTo;
//                SimpleDateFormat df = new SimpleDateFormat();
//                batchFrom = df.format(date.getFromDate());
//                batchTo = df.format(date.getToDate());
//            }
//        });

//        String dateFrom =
        try {
            String Batch = (String) batchCombo.getSelectedItem();
            String BatchFrom = (String) batchfrom.getSelectedItem();
            String branch = (String) branchCombo.getSelectedItem();
//            String Caste = (String) catergoryCombo.getSelectedItem();
            String gender = (String) GenderCombox.getSelectedItem();
            if ("All Batch".equals(Batch)) {
                Batch = "2000";
                System.out.println("Show all Batch Student");
            }
            if ("All Batch".equals(BatchFrom)) {
                BatchFrom = "2090";
                System.out.println("Show all Batch Student");
            }

            if ("All Branch".equals(branch)) {
                branch = "%";
                System.out.println("Show all Branch Student");
            }
//            if ("All Catergory".equals(Caste)) {
//                Caste = "%";
//                System.out.println("Show all caste Student");
//            }
            if ("All Gender".equals(gender)) {
                gender = "%";
                System.out.println("Show all gender Student");
            }

//            String Query = " Batch like " + Batch + " and branch like " + branch + " and Category like " + Caste ;
//            String Query = " Batch like " + Batch + " and Category like " + Caste ;
//            fillTableModelFilter(JTable readTable, DatabaseConnection.getInstance().getConnection(), branch, Batch, Caste, gender);
            Functions.fillTableModelFilter(readTable, branch, BatchFrom, Batch, "%", gender);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this, ex.getMessage());

            Logger.getLogger(FormSalary.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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

    private void branchComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_branchComboActionPerformed
        // TODO add your handling code here:
//        UpdateFilterTable();
//                 try {
//            Connection sqlcon = DatabaseConnection.getInstance().getConnection();
//            String myQuery = "select concat( t.FirstName,\" \",t.LastName) as `Full Name`,DATE_FORMAT(Date, '%Y-%b') as `Date`, `Base Pay`,`Dearness Allowance`,`NPS`,`Total NPS`,`Net Pay`,salary.Status\n" +
//"from salary left join college_management.teacherform t on t.Teacher_id = salary.teacher_id;";
//            PreparedStatement query = sqlcon.prepareStatement(myQuery);
//            query.setString(1, branchCombo.getSelectedItem().toString());
//            table_load(readTable, DatabaseConnection.getInstance().getConnection(), query);
//
//        } catch (SQLException ex) {
//            Logger.getLogger(FormSalary.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_branchComboActionPerformed

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

        String salaryId = tableModel.getValueAt(selectedRow, 0).toString(); // salary Id
        String TeacherId = tableModel.getValueAt(selectedRow, 1).toString(); // salary Id
        String FullName = tableModel.getValueAt(selectedRow, 2).toString();
        String pancard = tableModel.getValueAt(selectedRow, 3).toString();
        String Department = tableModel.getValueAt(selectedRow, 4).toString();
        String month = tableModel.getValueAt(selectedRow, 5).toString();
        String basicPay = tableModel.getValueAt(selectedRow, 6).toString();
        String DA = tableModel.getValueAt(selectedRow, 7).toString();
        String hra = tableModel.getValueAt(selectedRow, 8).toString();
        String PA = tableModel.getValueAt(selectedRow, 9).toString();
        String NPS = tableModel.getValueAt(selectedRow, 10).toString();
        String TDS = tableModel.getValueAt(selectedRow, 11).toString();
        String TNPS = tableModel.getValueAt(selectedRow, 12).toString();
        String insurance = tableModel.getValueAt(selectedRow, 13).toString();
        String netPay = tableModel.getValueAt(selectedRow, 14).toString();
        String totalPay = tableModel.getValueAt(selectedRow, 15).toString();
        String DaPercent = tableModel.getValueAt(selectedRow, 16).toString();
        String npsPercent = tableModel.getValueAt(selectedRow, 17).toString();
        String tnpsPercent = tableModel.getValueAt(selectedRow, 18).toString();
        String status = tableModel.getValueAt(selectedRow, 19).toString();

        HashMap<String, Object> ReportParmeterHashMap = new HashMap<String, Object>();
        ReportParmeterHashMap.put("DA", DA);
        ReportParmeterHashMap.put("TDS", TDS);
        ReportParmeterHashMap.put("Basic", basicPay);
        ReportParmeterHashMap.put("HRA", hra);
        ReportParmeterHashMap.put("Insurance", insurance);
        ReportParmeterHashMap.put("TNPS", TNPS);
        ReportParmeterHashMap.put("NPS", NPS);
        ReportParmeterHashMap.put("GradePay", totalPay);
        ReportParmeterHashMap.put("TotalPay", totalPay);
        ReportParmeterHashMap.put("PT", PA);
        ReportParmeterHashMap.put("Name", FullName);
        ReportParmeterHashMap.put("SlipOfMonth", month);
        ReportParmeterHashMap.put("Designation", "PAREMENT");
        ReportParmeterHashMap.put("SlipNo", salaryId);
        ReportParmeterHashMap.put("daPercent", DaPercent);
        ReportParmeterHashMap.put("npsPercent", npsPercent);
        ReportParmeterHashMap.put("tnpsPercent", tnpsPercent);
        ReportParmeterHashMap.put("panCard", pancard);
        ReportParmeterHashMap.put("status", status);
//
        ReportParmeterHashMap.put("NetPay", netPay);
        ReportManger.getInstance().ReportMangerX("C:\\Users\\anurag\\JaspersoftWorkspace\\MyReports\\teacherPaySlip.jrxml", ReportParmeterHashMap);
        //        } else {
//                      Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Please Refresh Table To Print Form");
//
////        JOptionPane.showMessageDialog(this, "Please Refresh Table To Print Form");
//        }
    }//GEN-LAST:event_jtableCellButtonActionPerformed
    ArrayList<String> salaryIdArrayList = new ArrayList<>();

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            Connection sqlcon = DatabaseConnection.getInstance().getConnection();
            String myQuery = "SELECT * FROM teacherform;";
            PreparedStatement query = sqlcon.prepareStatement(myQuery);
//            query.setString(1, branhi);
            table_load(readTable, DatabaseConnection.getInstance().getConnection(), query);

        } catch (SQLException ex) {
            Logger.getLogger(FormSalary.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void readTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_readTableMouseClicked
        // TODO add your handling code here:
        if ("GUEST".equals(role) | showAllDetails.isDefaultButton()) {
            jtableCellButton.setEnabled(false);
        } else {
            //            System.out.println("Cell Clicked!!");
            jtableCellButton.setEnabled(true);

        }
        //        refreshButton.setEnabled(true);
    }//GEN-LAST:event_readTableMouseClicked

    private void readTableMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_readTableMouseExited
        // TODO add your handling code here:

        if (role == "GUEST" | showAllDetails.isDefaultButton()) {
            jtableCellButton.setEnabled(false);
        }
    }//GEN-LAST:event_readTableMouseExited

    private void branchComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_branchComboItemStateChanged
        // TODO add your handling code here:
        try {
            Connection sqlcon = DatabaseConnection.getInstance().getConnection();
            String myQuery = "select concat( FirstName,\" \",LastName) as `Full Name`, `Base Pay`,`Dearness Allowance`,`NPS`,`Total NPS`,`Total NPS`,`Net Pay`,salary.Status\n"
                    + "from salary left join college_management.teacherform t on t.Teacher_id = salary.teacher_id where t.Department = ?;";
            PreparedStatement query = sqlcon.prepareStatement(myQuery);
            query.setString(1, branhi);
            table_load(readTable, DatabaseConnection.getInstance().getConnection(), query);

        } catch (SQLException ex) {
            Logger.getLogger(FormSalary.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_branchComboItemStateChanged

    private void batchfromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchfromActionPerformed
        // TODO add your handling code here:
//        UpdateFilterTable();
    }//GEN-LAST:event_batchfromActionPerformed

    private void updateDraftBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDraftBTActionPerformed
        String date = yearCB.getSelectedItem().toString() + "-" + monthCB.getSelectedItem().toString() + "-" + dayCB.getSelectedItem().toString(); //14

        int[] indices = readTable2.getSelectedRows();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        String date = df.format(draftdateTF.getText());
        Object[] row = new Object[4];
        DefaultTableModel model = (DefaultTableModel) readTable2.getModel();

        for (int index : indices) {
            row[0] = model.getValueAt(index, 0);
            salaryIdArrayList.add((String) row[0]);
            Logger.getLogger(FormSalary.class.getName()).info("Selected Salary ID: " + row[0]);
        }

        // Initialize transaction flag
        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement prepQuery = null;

        try {
            connection.setAutoCommit(false);  // Start transaction

            String queryString = "UPDATE salary SET status = 'PAID', paid_date=? WHERE salary_id = ? ; ";
            prepQuery = connection.prepareStatement(queryString);

            for (String teacherID : salaryIdArrayList) {
                Logger.getLogger(FormSalary.class.getName()).info("Updating Teacher Salary ID: " + teacherID);
                prepQuery.setDate(1, java.sql.Date.valueOf(date));
                prepQuery.setString(2, teacherID);
                prepQuery.addBatch();
            }

            int[] updateCounts = prepQuery.executeBatch();  // Execute batch update

            // Check for any errors during batch execution
            for (int count : updateCounts) {
                if (count == PreparedStatement.EXECUTE_FAILED) {
//                    throw new SQLException("Batch update failed.");
                    System.err.println("Batch update failed");
                }
            }

            connection.commit();  // Commit transaction

        } catch (SQLException ex) {
            // Log the exception and roll back transaction
            Logger.getLogger(FormSalary.class.getName()).log(Level.SEVERE, "Database update error", ex);
            try {
                connection.rollback();  // Rollback transaction on error
            } catch (SQLException rollbackEx) {
                Logger.getLogger(FormSalary.class.getName()).log(Level.SEVERE, "Failed to roll back transaction", rollbackEx);
            }

        } finally {
            // Always close the statement and reset auto-commit
            if (prepQuery != null) {
                try {
                    prepQuery.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FormSalary.class.getName()).log(Level.SEVERE, "Failed to close statement", ex);
                }
            }

            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(FormSalary.class.getName()).log(Level.SEVERE, "Failed to reset auto-commit", ex);
            }

            // Clear salaryIdArrayList for future use
            salaryIdArrayList.clear();
        }
        try {
            String myQuery = "select salary_id ,t.teacher_id,t.FirstName || ' ' || t.LastName as \"Full Name\",t.pan_card,t.department,\n"
                    + "       TO_CHAR(salary.Date, 'YYYY-Mon') as \"Month\",base_pay as \"Basic Pay\", dearness_allowance as \"Dearness Allowance\", hra as HRA,professional_allowance as \"Professional Allowance\",\n"
                    + "       nps_emp_contribution as \"NPS Employee Contribution\", tds as \"TDS\", nps_empr_contribution as \"NPS Employee Contribution\", insurance, net_pay as \"Net Pay\", total_pay as \"Total Pay\", da_at as \"DA %\", nps_at as \"NPS %\",\n"
                    + "       tnps_at as \"NPS Employeer Contribution\", status\n"
                    + "from salary left join teacherform t on t.teacher_id = salary.teacher_id where status = 'DRAFT';";
//            String myQuery = "select concat( t.FirstName,\" \",t.LastName) as `Full Name`, DATE_FORMAT(salary.Date, '%Y-%b') as Month, `Base Pay`,`Dearness Allowance`,`TDS`,(`NPS_Emp_Contribution`) as \"NPS Employee Contribution\",(`NPS_Empr_Contribution`) as \"NPS Employer Contribution\",`Professional Allowance`,`Insurance`,`Net Pay`,`DA_at`, `NPS_at`,`TNPS_at`, salary.Status\n" +
//"from salary left join college_management.teacherform t on t.Teacher_id = salary.teacher_id";
            Connection sqlcon = DatabaseConnection.getInstance().getConnection();
            PreparedStatement query = sqlcon.prepareStatement(myQuery);
//            query.setString(1, branhi);
            table_load(readTable2, DatabaseConnection.getInstance().getConnection(), query);

        } catch (SQLException ex) {
            Logger.getLogger(FormSalary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateDraftBTActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jtableCellButton.setEnabled(true);

        try {
            String myQuery = "select salary_id ,t.teacher_id,t.FirstName || ' ' || t.LastName as \"Full Name\",t.pan_card,t.department,\n"
                    + "       TO_CHAR(salary.Date, 'YYYY-Mon') as \"Month\",base_pay as \"Basic Pay\", dearness_allowance as \"Dearness Allowance\", professional_allowance as \"Professional Allowance\",\n"
                    + "       nps_emp_contribution as \"NPS Employee Contribution\", tds as \"TDS\", nps_empr_contribution as \"NPS Employee Contribution\", insurance, net_pay as \"Net Pay\", total_pay as \"Total Pay\", da_at as \"DA %\", nps_at as \"NPS %\",\n"
                    + "       tnps_at as \"NPS Employeer Contribution\", status\n"
                    + "from salary left join teacherform t on t.teacher_id = salary.teacher_id where status = 'DRAFT';";
//            String myQuery = "select concat( t.FirstName,\" \",t.LastName) as `Full Name`, DATE_FORMAT(salary.Date, '%Y-%b') as Month, `Base Pay`,`Dearness Allowance`,`TDS`,(`NPS_Emp_Contribution`) as \"NPS Employee Contribution\",(`NPS_Empr_Contribution`) as \"NPS Employer Contribution\",`Professional Allowance`,`Insurance`,`Net Pay`,`DA_at`, `NPS_at`,`TNPS_at`, salary.Status\n" +
//"from salary left join college_management.teacherform t on t.Teacher_id = salary.teacher_id";
            Connection sqlcon = DatabaseConnection.getInstance().getConnection();
            PreparedStatement query = sqlcon.prepareStatement(myQuery);
//            query.setString(1, branhi);
            table_load(readTable2, DatabaseConnection.getInstance().getConnection(), query);

        } catch (SQLException ex) {
            Logger.getLogger(FormSalary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void dateBWBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateBWBTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateBWBTActionPerformed

    private void readTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_readTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_readTable2MouseClicked

    private void readTable2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_readTable2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_readTable2MouseExited

    private void draftdateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_draftdateTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_draftdateTFActionPerformed

    private void monthCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthCBActionPerformed

    private void dayCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayCBActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        FormManager.showForm(new FormTeacherSalary("Teacher Salary"));        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        UpdateFilterTable();

    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> GenderCombox;
    private javax.swing.JComboBox<String> batchCombo;
    private javax.swing.JComboBox<String> batchfrom;
    private javax.swing.JComboBox<String> branchCombo;
    private javax.swing.JTextField dateBWBT;
    private javax.swing.JComboBox<String> dayCB;
    private javax.swing.JTextField draftdateTF;
    private javax.swing.JButton exportTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jtableCellButton;
    private javax.swing.JLabel lb;
    private javax.swing.JComboBox<String> monthCB;
    private javax.swing.JTable readTable;
    private javax.swing.JTable readTable2;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField searchBar;
    private javax.swing.JButton showAllDetails;
    private javax.swing.JButton updateDraftBT;
    private javax.swing.JComboBox<String> yearCB;
    // End of variables declaration//GEN-END:variables
}
