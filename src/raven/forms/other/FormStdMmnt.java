package raven.forms.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.raven.datechooser.DateChooser;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import raven.application.LoginUser;
import raven.chart.data.pie.DefaultPieDataset;
import raven.chart.pie.PieChart;
import raven.components.SimpleForm;
import raven.requiredFunctions.DatabaseConnection;
import static raven.requiredFunctions.Functions.exportToExcel;

/**
 *
 * @author Raven
 */
public class FormStdMmnt extends SimpleForm {

    DateChooser dateChooser1 = new DateChooser();

    public FormStdMmnt(String text) {
// jComboBox1 = new javax.swing.JComboBox<>();
//jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024" }));
//        jComboBox1.addItemListener(this::jComboBox1ItemStateChanged);
//        add(jComboBox1);
//        init();
//        dateChooser1.setTextRefernce(dobTF);

        initComponents();
        init();
//        dateChooser.setTextField(dateBWBT);
        dateChooser1.setTextField(feeDateTF);
        dateChooser1.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        dateChooser1.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
//        showPie(pieChart4);
        int s = yearFeeCB.getSelectedIndex();

        if (s < 1) {

            updateBT.setEnabled(false);
            saveBT.setEnabled(true);
        } else {
            saveBT.setEnabled(false);
            updateBT.setEnabled(true);
        }

    }

    @Override
    public void formRefresh() {
//        lineChart.startAnimation();
//        pieCasteChart.startAnimation();
//        pieChart3.startAnimation();
//        pieChart2.startAnimation();

//        barChart1.startAnimation();
//        barChart2.startAnimation();
    }

    @Override
    public void formInitAndOpen() {
        System.out.println("init and open");
    }

    @Override
    public void formOpen() {
        System.out.println("Open");
    }
// private PieChart pieChart1;
    private PieChart pieChart2;
    private PieChart pieChart3;
    private PieChart pieCasteChart;

    private void init() {
//       JPanel jPanel2 = new JPanel();
//        jPanel2.setLayout(new MigLayout("wrap,fill,gap 10", "fill"));
//        jPanel2.add(pieChart2);
//        jPanel2.add(pieChart3);
//        jPanel2.add(pieCasteChart);

//  batch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024" }));
// Code adding the component to the parent container - not shown here
//
//  jPanel2.setLayout(new MigLayout("wrap,fill,gap 10", "fill"));
//    int Batch = Integer.parseInt( jComboBox1.getSelectedItem().toString()) ;
//
//    createPieChart();
//        showGenderPie();
//        showCastePie();
//        updateDataset(Batch);
//                createPieChart();
    }

    private void createPieChart() {
//        pieChart2.repaint();

        pieChart2 = new PieChart();
        JLabel header2 = new JLabel("Branch ");
        header2.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1");
        pieChart2.setHeader(header2);
        pieChart2.getChartColor().addColor(Color.decode("#f87171"), Color.decode("#fb923c"), Color.decode("#fbbf24"), Color.decode("#a3e635"), Color.decode("#34d399"), Color.decode("#22d3ee"), Color.decode("#818cf8"), Color.decode("#c084fc"));
        pieChart2.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");

//      DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
//      HashMap<String,Integer> map = raven.requiredFunctions.Functions.totalStudentsYearly(Batch );
//      int CSE=0,ME=0,CIVIL=0,ECE=0;
//        try {
//      CSE = map.get("CSE");
//      ME = map.get("ME");
//      ECE = map.get("ECE");
//      CIVIL = map.get("CIVIL");
//
//        } catch (Exception e) {
//        }
//      dataset.addValue("CSE" , CSE);
//      dataset.addValue("ME" , ME);
//      dataset.addValue("Civil" , CIVIL);
//      dataset.addValue("ECE" ,ECE);
//
//        pieChart2.setDataset(dataset);
        add(pieChart2, "height 290");
//                pieChart2.removeAll();
    }

    public void showPie() {
//        pieChart1.clearData();
//       JLabel header1 = new JLabel("Product Income");
//        header1.putClientProperty(FlatClientProperties.STYLE, ""
//                + "font:+1");
//
//          int batch = Integer.parseInt( jComboBox1.getSelectedItem().toString()) ;
////    int batch = Integer.parseInt((String) buttonDB.getSelectedItem()) ;
//    HashMap<String,Integer> map = raven.requiredFunctions.Functions.totalStudentsYearly(batch);
//
//    pieChart1.setForeground(new Color(233, 162, 59));
//    pieChart1.addData(new ModelPieChart("CSE" , map.get("CSE"),new Color(245, 189, 135)));
//    pieChart1.addData(new ModelPieChart("ME" , map.get("ME"),new Color(102,255,102)));
//    pieChart1.addData(new ModelPieChart("CIVIL" , map.get("CIVIL"),new Color(255,102,153)));
//    pieChart1.addData(new ModelPieChart("ECE" , map.get("ECE"),new Color(0,204,204)));
////        pieChart1.addData(new ModelPieChart("Civil", 60, new Color(001,03,05)));
//
    }

    private void updateDataset(int Batch) {

        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        HashMap<String, Integer> map = raven.requiredFunctions.Functions.totalStudentsYearly(Batch);
        int CSE = 0, ME = 0, CIVIL = 0, ECE = 0;
        try {
            CSE = map.get("CSE");
            ME = map.get("ME");
            ECE = map.get("ECE");
            CIVIL = map.get("CIVIL");

        } catch (Exception e) {
        }
        dataset.addValue("CSE", CSE);
        dataset.addValue("ME", ME);
        dataset.addValue("Civil", CIVIL);
        dataset.addValue("ECE", ECE);

        pieChart2.remove(this);
        pieChart2 = new PieChart();
        JLabel header2 = new JLabel("Branch ");
        header2.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1");
        pieChart2.setHeader(header2);
        pieChart2.getChartColor().addColor(Color.decode("#f87171"), Color.decode("#fb923c"), Color.decode("#fbbf24"), Color.decode("#a3e635"), Color.decode("#34d399"), Color.decode("#22d3ee"), Color.decode("#818cf8"), Color.decode("#c084fc"));
        pieChart2.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");
        pieChart2.setDataset(dataset);

        pieChart2.startAnimation();

        DefaultPieDataset<String> dataset2 = new DefaultPieDataset<>();
        HashMap<String, Integer> map2 = raven.requiredFunctions.Functions.totalGenderStudentsYearly(Batch);
        int Male = 0, Female = 0;
        try {
            Male = map2.get("Male");
            Female = map2.get("Female");

        } catch (Exception e) {
            System.err.println(e);
        }
        dataset2.addValue("Male", Male);
        dataset2.addValue("Female", Female);

        pieChart3.setDataset(dataset2);

        DefaultPieDataset<String> dataset3 = new DefaultPieDataset<>();
        HashMap<String, Double> map3 = raven.requiredFunctions.Functions.totalCategoryStudents(Batch);
        double UR = 0, OBC = 0, SC = 0, ST = 0;
        try {
            UR = map3.get("UR");
            OBC = map3.get("OBC");
            SC = map3.get("SC");
            ST = map3.get("ST");
        } catch (Exception e) {
            System.err.println(e);
        }

        dataset3.addValue("UR", UR);
        dataset3.addValue("OBC", OBC);
        dataset3.addValue("SC", SC);
        dataset3.addValue("ST", ST);

        pieCasteChart.setDataset(dataset3);

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        stdNameTF = new javax.swing.JTextField();
        firstYearTF = new javax.swing.JTextField();
        updateBT = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        stdRollnoTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        saveBT = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        yearFeeCB = new javax.swing.JComboBox<>();
        feeDateTF = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        stdTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        branchLB = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        batchCB = new javax.swing.JComboBox<>();
        loadBT = new javax.swing.JButton();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        allStudentMarksTable = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Student Fee Management");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Student Rollno");

        stdNameTF.setEditable(false);
        stdNameTF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        stdNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stdNameTFActionPerformed(evt);
            }
        });

        firstYearTF.setFont(new java.awt.Font("Liberation Mono", 1, 13)); // NOI18N
        firstYearTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstYearTFActionPerformed(evt);
            }
        });

        updateBT.setBackground(new java.awt.Color(153, 153, 255));
        updateBT.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        updateBT.setText("Update");
        updateBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBTActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Student Name");

        stdRollnoTF.setEditable(false);
        stdRollnoTF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Noto Serif CJK JP", 1, 13)); // NOI18N
        jLabel11.setText("DATE (yyyy-MM-dd)");

        saveBT.setBackground(new java.awt.Color(153, 255, 153));
        saveBT.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        saveBT.setText("Save");
        saveBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBTActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Student Fees");

        jLabel10.setFont(new java.awt.Font("Noto Serif CJK JP", 1, 13)); // NOI18N
        jLabel10.setText("AMOUNT");

        yearFeeCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "first_year_fee", "second_year_fee", "third_year_fee" }));
        yearFeeCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                yearFeeCBItemStateChanged(evt);
            }
        });

        feeDateTF.setFont(new java.awt.Font("Liberation Mono", 1, 13)); // NOI18N
        feeDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feeDateTFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(stdRollnoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(stdNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(73, 73, 73))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yearFeeCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(firstYearTF, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(feeDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(332, 332, 332)
                        .addComponent(saveBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(updateBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stdNameTF)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stdRollnoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(8, 8, 8)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearFeeCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstYearTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(feeDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        branchLB.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        branchLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        branchLB.setText("Branch");
        jPanel2.add(branchLB);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Batch");
        jPanel2.add(jLabel2);

        batchCB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        batchCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024", "2025", "2026", "2027" }));
        batchCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchCBActionPerformed(evt);
            }
        });
        jPanel2.add(batchCB);

        loadBT.setBackground(new java.awt.Color(153, 153, 255));
        loadBT.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        loadBT.setText("Load  Students");
        loadBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBTActionPerformed(evt);
            }
        });
        jPanel2.add(loadBT);

        jTabbedPane7.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N

        allStudentMarksTable.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
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
        jScrollPane9.setViewportView(allStudentMarksTable);

        jButton10.setFont(new java.awt.Font("0xProto Nerd Font", 1, 13)); // NOI18N
        jButton10.setText("Show All");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("0xProto Nerd Font", 1, 13)); // NOI18N
        jButton11.setText("show Batch wise");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("0xProto Nerd Font", 1, 13)); // NOI18N
        jButton12.setText("Export To Excel");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1419, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11)
                .addGap(18, 18, 18)
                .addComponent(jButton12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11)
                    .addComponent(jButton12))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
        );

        jTabbedPane7.addTab("Students Fee", jPanel10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1419, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void batchCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_batchCBActionPerformed

    private void stdNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stdNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stdNameTFActionPerformed

    private void firstYearTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstYearTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstYearTFActionPerformed

    private void loadBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBTActionPerformed
        // TODO add your handling code here:

//        String loadSubNameCodeQuery = "select subject_name,subject_code from subjects where subject_teacher = ?  and batch = ? and semster = ?;";
        String loadStudentQuery = "select firstname || ' ' || lastname as FullName,rollno from studentform where batch = ? and branch = ?;";
        //        Connection sqlcon = ;
        String batch = batchCB.getSelectedItem().toString();
        try {
//             rs = null, loadStudentResultSet = null;
            ResultSet loadStudentResultSet = null;
            PreparedStatement loadStudentquery = DatabaseConnection.getInstance().getConnection().prepareStatement(loadStudentQuery);
            //            query.setString(1, LoginUser.getId());
            loadStudentquery.setString(1, batch);
            loadStudentquery.setString(2, LoginUser.getBranch());
            loadStudentResultSet = loadStudentquery.executeQuery();
            stdTable.setModel(DbUtils.resultSetToTableModel(loadStudentResultSet));
            loadStudentquery.close();
            loadStudentResultSet.close();

        } catch (SQLException aw) {
            System.out.println(aw.getMessage());
        }
        //        yearCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{currentYear}));
    }//GEN-LAST:event_loadBTActionPerformed

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
    private void saveBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBTActionPerformed
        // TODO add your handling code here:
        String stdRollno = stdRollnoTF.getText();
        String stdName = stdNameTF.getText();
        String year1 = firstYearTF.getText();
//        String year2 = secondYearTF.getText();
//        String year3 = thirdYearTF.getText();
        String year1Date = feeDateTF.getText();
//        String year2Date = secondYearDate.getText();
//        String year3Date = thirdYearDate.getText();
        if (valiadValuesTF(firstYearTF, "Fee Value", 5) //                && valiadValuesTF(secondYearTF, "second Year fee", 5)
                //                && valiadValuesTF(thirdYearTF, "third Year fee", 5)
                //
                ) {

//                                                       1         2                  3                  4                 5
            String myQueryBak = "insert into student_fee ( rollno, first_year_fee, first_year_fee_date, second_year_fee, second_year_fee_date,\n"
                    //                                                6                 7
                    + "                         third_year_fee, third_year_fee_date)\n"
                    //                             1 2 3 4 5 6 7
                    + "values (?,?,?,?,?,?,?);";
            String myQuery = "insert into student_fee ( rollno, " + yearFeeCB.getSelectedItem().toString() + ", first_year_fee_date)\n"
                    //                             1 2 3 4 5 6 7
                    + "values (?,?,?);";
            try {
                PreparedStatement prepquery = DatabaseConnection.getInstance().getConnection().prepareStatement(myQuery);
                prepquery.setString(1, stdRollno);
                prepquery.setBigDecimal(2, new java.math.BigDecimal(year1));
                prepquery.setDate(3, java.sql.Date.valueOf(year1Date));
//                prepquery.setBigDecimal(4, new java.math.BigDecimal(year2));
//                prepquery.setDate(5, java.sql.Date.valueOf(year2Date));
//                prepquery.setBigDecimal(6, new java.math.BigDecimal(year3));
//                prepquery.setDate(7, java.sql.Date.valueOf(year3Date));
                Boolean q = prepquery.execute();
                prepquery.close();
                if (q) {
                    JOptionPane.showMessageDialog(this, stdName + " Fee Updated Successfully ");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }

    }//GEN-LAST:event_saveBTActionPerformed

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

    private void yearFeeCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_yearFeeCBItemStateChanged
        // TODO add your handling code here:
        int s = yearFeeCB.getSelectedIndex();

        if (s < 1) {

            updateBT.setEnabled(false);
            saveBT.setEnabled(true);
        } else {
            saveBT.setEnabled(false);
            updateBT.setEnabled(true);
        }
    }//GEN-LAST:event_yearFeeCBItemStateChanged

    private void updateBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBTActionPerformed
        // TODO add your handling code here:
//        "update student_fee set second_year_fee = ? where rollno = ?";
        String stdRollno = stdRollnoTF.getText();
        String stdName = stdNameTF.getText();
        String year1 = firstYearTF.getText();
//        String year2 = secondYearTF.getText();
//        String year3 = thirdYearTF.getText();
        String year1Date = feeDateTF.getText();
        String myQuery = "update student_fee set " + yearFeeCB.getSelectedItem().toString() + " = ? , " + yearFeeCB.getSelectedItem().toString() + "_date = ? where rollno = ?";
        try {
            PreparedStatement prepquery = DatabaseConnection.getInstance().getConnection().prepareStatement(myQuery);
            prepquery.setBigDecimal(1, new java.math.BigDecimal(year1));
            prepquery.setDate(2, java.sql.Date.valueOf(year1Date));
            prepquery.setString(3, stdRollno);
//
            int q = prepquery.executeUpdate();
            prepquery.close();
            if (q < 0) {
                JOptionPane.showMessageDialog(this, stdName + " Fee Updated Successfully ");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_updateBTActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try {
            //            String query = "SELECT * FROM admissionform where "+ Query + " ;"; // Replace with your SQL query
            String query = "select * from student_fee where ;"; // Replace with your SQL query
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            //                preparedStatement.setString(1, LoginUser.getBranch());
            //                preparedStatement.setString(2, batchCB.getSelectedItem().toString());
            //                preparedStatement.setString(3, semCB.getSelectedItem().toString());
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
        // TODO add your handling code here:
        // TODO add your handling code here:
        //        if (subCodeTF.getText().equals("")) {
        //            JOptionPane.showMessageDialog(this, "Please Subject Name To Show Students");
        //
        //        } else {
        //            try {
        //                //            String query = "SELECT * FROM admissionform where "+ Query + " ;"; // Replace with your SQL query
        //                String query = "SELECT s.FirstName || ' ' || s.LastName AS \"FullName\",\n"
        //                        + "       t.FirstName || ' ' || t.LastName AS \"Teacher\",\n"
        //                        + "       s2.Subject_Name, marks.batch,marks.semster,sub_code,\n"
        //                        + "       marks.rollno, mid_sem_1, mid_sem_2, mid_sem_avg, pratical_marks, endsemgrade\n"
        //                        + "FROM marks\n"
        //                        + "         LEFT JOIN studentform AS s ON s.Rollno = marks.rollno\n"
        //                        + "         LEFT JOIN subjects AS s2 ON s2.Subject_Code = marks.Sub_code\n"
        //                        + "         LEFT JOIN public.teacherform t on s2.subject_teacher = t.teacher_id\n"
        //                        + "WHERE s2.subject_branch = ? and s2.batch = ? and s2.semster = ?;"; // Replace with your SQL query
        //                PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        //                preparedStatement.setString(1, LoginUser.getBranch());
        //                preparedStatement.setString(2, batchCB.getSelectedItem().toString());
        //                preparedStatement.setString(3, semCB.getSelectedItem().toString());
        //                preparedStatement.setString(4, semCB.getSelectedItem().toString());
        //
        //                ResultSet resultSet = preparedStatement.executeQuery();
        //                allStudentMarksTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        //                preparedStatement.close();
        //                resultSet.close();
        //                System.err.println("Table Update");
        //            } catch (SQLException ex) {
        //                System.err.println(ex.getMessage());
        //                JOptionPane.showMessageDialog(this, ex.getMessage());
        //            }
        //        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:

        try {
            //            String query = "SELECT * FROM admissionform where "+ Query + " ;"; // Replace with your SQL query
            String query = "select * from student_fee;"; // Replace with your SQL query
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            //                preparedStatement.setString(1, LoginUser.getBranch());
            //                preparedStatement.setString(2, batchCB.getSelectedItem().toString());
            //                preparedStatement.setString(3, semCB.getSelectedItem().toString());
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
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        exportToExcel(allStudentMarksTable);

    }//GEN-LAST:event_jButton12ActionPerformed

    private void feeDateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feeDateTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_feeDateTFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable allStudentMarksTable;
    private javax.swing.JComboBox<String> batchCB;
    private javax.swing.JLabel branchLB;
    private javax.swing.JTextField feeDateTF;
    private javax.swing.JTextField firstYearTF;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JButton loadBT;
    private javax.swing.JButton saveBT;
    private javax.swing.JTextField stdNameTF;
    private javax.swing.JTextField stdRollnoTF;
    private javax.swing.JTable stdTable;
    private javax.swing.JButton updateBT;
    private javax.swing.JComboBox<String> yearFeeCB;
    // End of variables declaration//GEN-END:variables
}
