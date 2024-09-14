package raven.forms.other;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.util.HashMap;
import javax.swing.JLabel;
import raven.chart.data.pie.DefaultPieDataset;
import raven.chart.pie.PieChart;
import raven.components.SimpleForm;
import raven.menu.FormManager;

/**
 *
 * @author Raven
 */
public class DemoForm extends SimpleForm {

    public DemoForm(String text) {
// jComboBox1 = new javax.swing.JComboBox<>();
//jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024" }));
//        jComboBox1.addItemListener(this::jComboBox1ItemStateChanged);
//        add(jComboBox1);
//        init();
        initComponents();
        init();
//        showPie(pieChart4);

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

    private void showGenderPie() {
        ///////////////////////////////////////////////////////////
//        int Batch = Integer.parseInt(jComboBox1.getSelectedItem().toString());
//        pieChart2.repaint();

        pieChart3 = new PieChart();
        JLabel header3 = new JLabel("Gender");
        header3.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1");
        pieChart3.setHeader(header3);
        pieChart3.getChartColor().addColor(Color.decode("#f87171"), Color.decode("#fb923c"), Color.decode("#fbbf24"), Color.decode("#a3e635"), Color.decode("#34d399"), Color.decode("#22d3ee"), Color.decode("#818cf8"), Color.decode("#c084fc"));
        pieChart3.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");

        add(pieChart3, "height 290");
    }

    @SuppressWarnings("unchecked")
    private void showCastePie() {
        ///////////////////////////////////////////////////////////
//        pieChart2.repaint();

        pieCasteChart = new PieChart();
        JLabel header2 = new JLabel("Product Cost");
        header2.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1");
        pieCasteChart.setHeader(header2);
        pieCasteChart.getChartColor().addColor(Color.decode("#f87171"), Color.decode("#fb923c"), Color.decode("#fbbf24"), Color.decode("#a3e635"), Color.decode("#34d399"), Color.decode("#22d3ee"), Color.decode("#818cf8"), Color.decode("#c084fc"));
        pieCasteChart.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");

//      DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        add(pieCasteChart, "height 290");

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jLabel1.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("WELCOME TO GPC MANAGEMENT");

        jButton3.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jButton3.setText("Student Admission");
        jButton3.setPreferredSize(new java.awt.Dimension(250, 70));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jButton2.setText("New Teacher Admission");
        jButton2.setPreferredSize(new java.awt.Dimension(250, 70));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jButton1.setText("List Student ");
        jButton1.setPreferredSize(new java.awt.Dimension(250, 70));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jButton4.setText("My Salary");
        jButton4.setPreferredSize(new java.awt.Dimension(250, 70));
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
                .addGap(134, 134, 134)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Quick Start");

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(246, 246, 246));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Georgia", 0, 22)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(88, 86, 214));
        jTextArea1.setRows(5);
        jTextArea1.setText("\t\tWe're delighted to have you here! This software is designed to help you efficiently manage\n\n~ Here's what you can do:\n\n-> Manage Departmental Subjects and Students: Access and organize subjects, student information, \n\t     and all grades (including end, mid-semester, and practical marks) from a central location.\n\n-> Add New Members: Easily add new students and teachers to your department.\n\n-> Handle Teacher Salaries: Pay and manage teacher salaries with ease.\n\n~ New Feature Are Coming Soon....\n\t\nMade With Love For beloved GPC Teachers\n\nWe would like to express our sincere gratitude to Shailja Nema Ma'am and Nitesh Sir for \n    \ttheir Support in the software development process.\n");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setCaretColor(new java.awt.Color(51, 51, 0));
        jTextArea1.setDisabledTextColor(new java.awt.Color(246, 246, 246));
        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1428, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        FormManager.showForm(new FormAdmission());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        FormManager.showForm(new FormAdmissionTeacher());

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        FormManager.showForm(new FormRead());

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        FormManager.showForm(new FormMySalary(""));

    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
