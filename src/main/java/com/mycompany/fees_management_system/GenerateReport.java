/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fees_management_system;

import java.awt.Color;
import java.io.*;
import java.sql.*;
import java.text.MessageFormat;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;

/**
 *
 * @author DELL
 */
public class GenerateReport extends javax.swing.JFrame {

    /**
     * Creates new form GenerateReport
     */
    DefaultTableModel model = new DefaultTableModel();

    public GenerateReport() {
        initComponents();
        fillComboCourseBox();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public void fillComboCourseBox() {

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management", "root", "root");
            String sql = "select cname from course";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                combo_SelectCourse.addItem(rs.getString("cname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearTableRecords() {
        DefaultTableModel model = (DefaultTableModel) tbl_GenerateRecords.getModel();
        model.setRowCount(1);
    }

    public void setRecordsToTable() {

        Float totalFinalAmount = 0.0f;

        String cname = combo_SelectCourse.getSelectedItem().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fromDate = dateFormat.format(dateChooserFrom.getDate());
        String toDate = dateFormat.format(dateChooserTo.getDate());

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management", "root", "root");
            String sql = "select * from fees_details where t_date between ? and ? and course_name = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, fromDate);
            pst.setString(2, toDate);
            pst.setString(3, cname);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String recieptNo = rs.getString("reciept_no");
                String studentEnrollmentNo = rs.getString("roll_no");
                String studentName = rs.getString("student_name");
                String course = rs.getString("course_name");
                String paymentMode = rs.getString("payment_mode");
                Float totalAmount = rs.getFloat("total_amount");

                totalFinalAmount = totalFinalAmount + totalAmount;

                Object obj[] = {recieptNo, studentEnrollmentNo, studentName, course, paymentMode, totalAmount};
                model = (DefaultTableModel) tbl_GenerateRecords.getModel();
                model.addRow(obj);
            };

            txt_CourseSelected.setText(cname);
            txt_TotalAmountCollected.setText(totalFinalAmount.toString());
            txt_TotalAmountInWords.setText(NumberToWordsConverter.convert(totalFinalAmount.intValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportToExcel() {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet ws = wb.createSheet();
        DefaultTableModel model = (DefaultTableModel) tbl_GenerateRecords.getModel();

        TreeMap<String, Object[]> map = new TreeMap<>();

        map.put("0", new Object[]{model.getColumnName(0), model.getColumnName(1), model.getColumnName(2), model.getColumnName(3), model.getColumnName(4), model.getColumnName(5)}); //Anonomous Array Object created.

        for (int i = 1; i < model.getRowCount(); i++) {
            map.put(Integer.toString(i), new Object[]{model.getValueAt(i, 0), model.getValueAt(i, 1), model.getValueAt(i, 2), model.getValueAt(i, 3), model.getValueAt(i, 4), model.getValueAt(i, 5)});
        }

        Set<String> id = map.keySet(); // to get map keys

        XSSFRow fRow;
        int rowId = 0;

        for (String key : id) {
            fRow = ws.createRow(rowId++);
            Object[] value = map.get(key);

            int cellId = 0;

            for (Object object : value) {
                XSSFCell cell = fRow.createCell(cellId++);
                cell.setCellValue(object.toString());
            }

        }

        try {
            FileOutputStream fos = new FileOutputStream(new File(txt_ExportToExcel.getText()));
            wb.write(fos);
            fos.close();

            JOptionPane.showMessageDialog(this, "Excel File Exported Successfully.\nAt given path '" + txt_ExportToExcel.getText() + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelsideBar = new javax.swing.JPanel();
        panelHome = new javax.swing.JPanel();
        btnHome = new javax.swing.JLabel();
        panelEditCourse = new javax.swing.JPanel();
        btnEditCourse = new javax.swing.JLabel();
        panelCourseList = new javax.swing.JPanel();
        btnCourseList = new javax.swing.JLabel();
        panelViewAllRecord = new javax.swing.JPanel();
        btnViewAllRecord = new javax.swing.JLabel();
        panelBack = new javax.swing.JPanel();
        btnBack = new javax.swing.JLabel();
        panelSearchRecord = new javax.swing.JPanel();
        btnSearchRecord = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbl_SelectCourse = new javax.swing.JLabel();
        combo_SelectCourse = new javax.swing.JComboBox<>();
        lbl_SelectDate = new javax.swing.JLabel();
        lbl_SelectDateFrom = new javax.swing.JLabel();
        lbl_SelectDateTo = new javax.swing.JLabel();
        btn_Submit = new javax.swing.JButton();
        btn_Print = new javax.swing.JButton();
        txt_ExportToExcel = new javax.swing.JTextField();
        btn_Browse = new javax.swing.JButton();
        btn_ExportToExcel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_GenerateRecords = new javax.swing.JTable();
        dateChooserTo = new com.toedter.calendar.JDateChooser();
        dateChooserFrom = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        lbl_CourseSelected = new javax.swing.JLabel();
        lbl_TotalAmountCollected = new javax.swing.JLabel();
        lbl_TotalAmountInWords = new javax.swing.JLabel();
        txt_CourseSelected = new javax.swing.JLabel();
        txt_TotalAmountCollected = new javax.swing.JLabel();
        txt_TotalAmountInWords = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelsideBar.setBackground(new java.awt.Color(245, 245, 245));
        panelsideBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 10));

        panelHome.setBackground(new java.awt.Color(0, 102, 102));
        panelHome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));

        btnHome.setBackground(new java.awt.Color(0, 102, 153));
        btnHome.setFont(new java.awt.Font("Comic Sans MS", 3, 28)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\my icons\\home.png")); // NOI18N
        btnHome.setText(" HOME");
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelHomeLayout = new javax.swing.GroupLayout(panelHome);
        panelHome.setLayout(panelHomeLayout);
        panelHomeLayout.setHorizontalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
        );
        panelHomeLayout.setVerticalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );

        panelEditCourse.setBackground(new java.awt.Color(0, 160, 200));
        panelEditCourse.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));

        btnEditCourse.setFont(new java.awt.Font("Comic Sans MS", 3, 28)); // NOI18N
        btnEditCourse.setForeground(new java.awt.Color(255, 255, 255));
        btnEditCourse.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\my icons\\edit2.png")); // NOI18N
        btnEditCourse.setText("Edit Course");
        btnEditCourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditCourseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditCourseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditCourseMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelEditCourseLayout = new javax.swing.GroupLayout(panelEditCourse);
        panelEditCourse.setLayout(panelEditCourseLayout);
        panelEditCourseLayout.setHorizontalGroup(
            panelEditCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditCourseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditCourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEditCourseLayout.setVerticalGroup(
            panelEditCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEditCourse, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );

        panelCourseList.setBackground(new java.awt.Color(0, 160, 200));
        panelCourseList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));

        btnCourseList.setFont(new java.awt.Font("Comic Sans MS", 3, 28)); // NOI18N
        btnCourseList.setForeground(new java.awt.Color(255, 255, 255));
        btnCourseList.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\my icons\\list_1.png")); // NOI18N
        btnCourseList.setText("Course List");
        btnCourseList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCourseListMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCourseListMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelCourseListLayout = new javax.swing.GroupLayout(panelCourseList);
        panelCourseList.setLayout(panelCourseListLayout);
        panelCourseListLayout.setHorizontalGroup(
            panelCourseListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCourseListLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCourseList, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelCourseListLayout.setVerticalGroup(
            panelCourseListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCourseList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );

        panelViewAllRecord.setBackground(new java.awt.Color(0, 160, 200));
        panelViewAllRecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));

        btnViewAllRecord.setFont(new java.awt.Font("Comic Sans MS", 3, 28)); // NOI18N
        btnViewAllRecord.setForeground(new java.awt.Color(255, 255, 255));
        btnViewAllRecord.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\my icons\\view all record.png")); // NOI18N
        btnViewAllRecord.setText("View All Record");
        btnViewAllRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnViewAllRecordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnViewAllRecordMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelViewAllRecordLayout = new javax.swing.GroupLayout(panelViewAllRecord);
        panelViewAllRecord.setLayout(panelViewAllRecordLayout);
        panelViewAllRecordLayout.setHorizontalGroup(
            panelViewAllRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewAllRecordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnViewAllRecord, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
        );
        panelViewAllRecordLayout.setVerticalGroup(
            panelViewAllRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnViewAllRecord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );

        panelBack.setBackground(new java.awt.Color(51, 51, 51));
        panelBack.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));

        btnBack.setFont(new java.awt.Font("Comic Sans MS", 3, 28)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\my icons\\left-arrow.png")); // NOI18N
        btnBack.setText("Back");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelBackLayout = new javax.swing.GroupLayout(panelBack);
        panelBack.setLayout(panelBackLayout);
        panelBackLayout.setHorizontalGroup(
            panelBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        panelBackLayout.setVerticalGroup(
            panelBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );

        panelSearchRecord.setBackground(new java.awt.Color(0, 160, 200));
        panelSearchRecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));

        btnSearchRecord.setFont(new java.awt.Font("Comic Sans MS", 3, 28)); // NOI18N
        btnSearchRecord.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchRecord.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\my icons\\search2.png")); // NOI18N
        btnSearchRecord.setText("Search Record");
        btnSearchRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchRecordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSearchRecordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSearchRecordMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelSearchRecordLayout = new javax.swing.GroupLayout(panelSearchRecord);
        panelSearchRecord.setLayout(panelSearchRecordLayout);
        panelSearchRecordLayout.setHorizontalGroup(
            panelSearchRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchRecordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSearchRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSearchRecordLayout.setVerticalGroup(
            panelSearchRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSearchRecord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(204, 0, 51));
        jPanel11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Copperplate Gothic Bold", 3, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\my icons\\logout.png")); // NOI18N
        jLabel9.setText("Logout");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Bold", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\my icons\\view_reports.png")); // NOI18N
        jLabel1.setText("   Reports");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelsideBarLayout = new javax.swing.GroupLayout(panelsideBar);
        panelsideBar.setLayout(panelsideBarLayout);
        panelsideBarLayout.setHorizontalGroup(
            panelsideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsideBarLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelsideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelViewAllRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelEditCourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCourseList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSearchRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelsideBarLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(panelHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelsideBarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelsideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86))
        );
        panelsideBarLayout.setVerticalGroup(
            panelsideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsideBarLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(panelHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelSearchRecord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelEditCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCourseList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelViewAllRecord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 160, 200), 12));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_SelectCourse.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_SelectCourse.setForeground(new java.awt.Color(0, 102, 153));
        lbl_SelectCourse.setText("Select Course : ");
        jPanel2.add(lbl_SelectCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 26, -1, 30));

        combo_SelectCourse.setFont(new java.awt.Font("Copperplate Gothic Light", 3, 14)); // NOI18N
        combo_SelectCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_SelectCourseActionPerformed(evt);
            }
        });
        jPanel2.add(combo_SelectCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 25, 360, 36));

        lbl_SelectDate.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_SelectDate.setForeground(new java.awt.Color(0, 102, 153));
        lbl_SelectDate.setText("Select Date : ");
        jPanel2.add(lbl_SelectDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 74, -1, 30));

        lbl_SelectDateFrom.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_SelectDateFrom.setForeground(new java.awt.Color(51, 51, 51));
        lbl_SelectDateFrom.setText("From ");
        jPanel2.add(lbl_SelectDateFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 74, -1, 30));

        lbl_SelectDateTo.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_SelectDateTo.setText("To");
        jPanel2.add(lbl_SelectDateTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 74, 29, 30));

        btn_Submit.setBackground(new java.awt.Color(0, 0, 51));
        btn_Submit.setFont(new java.awt.Font("Copperplate Gothic Bold", 3, 14)); // NOI18N
        btn_Submit.setForeground(new java.awt.Color(255, 255, 255));
        btn_Submit.setText("Submit");
        btn_Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SubmitActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 132, 95, 41));

        btn_Print.setBackground(new java.awt.Color(0, 0, 51));
        btn_Print.setFont(new java.awt.Font("Copperplate Gothic Bold", 3, 14)); // NOI18N
        btn_Print.setForeground(new java.awt.Color(255, 255, 255));
        btn_Print.setText("Print");
        btn_Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PrintActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Print, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 132, 95, 41));

        txt_ExportToExcel.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_ExportToExcel.setForeground(new java.awt.Color(255, 255, 255));
        txt_ExportToExcel.setToolTipText("");
        txt_ExportToExcel.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_ExportToExcel.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_ExportToExcel.setEnabled(false);
        txt_ExportToExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ExportToExcelActionPerformed(evt);
            }
        });
        jPanel2.add(txt_ExportToExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 193, 290, 36));

        btn_Browse.setBackground(new java.awt.Color(0, 0, 51));
        btn_Browse.setFont(new java.awt.Font("Copperplate Gothic Bold", 3, 14)); // NOI18N
        btn_Browse.setForeground(new java.awt.Color(255, 255, 255));
        btn_Browse.setText("Browse");
        btn_Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BrowseActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Browse, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 192, 110, 41));

        btn_ExportToExcel.setBackground(new java.awt.Color(0, 0, 51));
        btn_ExportToExcel.setFont(new java.awt.Font("Copperplate Gothic Bold", 3, 14)); // NOI18N
        btn_ExportToExcel.setForeground(new java.awt.Color(255, 255, 255));
        btn_ExportToExcel.setText("Export To Excel");
        btn_ExportToExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExportToExcelActionPerformed(evt);
            }
        });
        jPanel2.add(btn_ExportToExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 190, 41));

        tbl_GenerateRecords.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tbl_GenerateRecords.setForeground(new java.awt.Color(51, 51, 51));
        tbl_GenerateRecords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Reciept No.", "Enrollment No.", "Student Name", "Course Name", "Payment Mode", "Total Amount"
            }
        ));
        tbl_GenerateRecords.setEnabled(false);
        tbl_GenerateRecords.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tbl_GenerateRecords);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 251, 943, 525));

        dateChooserTo.setDateFormatString("dd-MM-yyyy");
        dateChooserTo.setFont(new java.awt.Font("Comic Sans MS", 3, 13)); // NOI18N
        dateChooserTo.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dateChooserToAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel2.add(dateChooserTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(389, 74, 136, 36));

        dateChooserFrom.setDateFormatString("dd-MM-yyyy");
        dateChooserFrom.setFont(new java.awt.Font("Comic Sans MS", 3, 13)); // NOI18N
        dateChooserFrom.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dateChooserFromAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel2.add(dateChooserFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 74, 136, 36));

        jPanel3.setBackground(new java.awt.Color(245, 245, 245));

        lbl_CourseSelected.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        lbl_CourseSelected.setForeground(new java.awt.Color(0, 102, 153));
        lbl_CourseSelected.setText("Course Selected :");

        lbl_TotalAmountCollected.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        lbl_TotalAmountCollected.setForeground(new java.awt.Color(0, 102, 153));
        lbl_TotalAmountCollected.setText("Total Amount Collected :");

        lbl_TotalAmountInWords.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        lbl_TotalAmountInWords.setForeground(new java.awt.Color(0, 102, 153));
        lbl_TotalAmountInWords.setText("Total Amount In Words :");

        txt_CourseSelected.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        txt_CourseSelected.setForeground(new java.awt.Color(51, 51, 51));

        txt_TotalAmountCollected.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        txt_TotalAmountCollected.setText(" ");

        txt_TotalAmountInWords.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbl_TotalAmountInWords)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbl_CourseSelected)
                        .addGap(6, 6, 6)
                        .addComponent(txt_CourseSelected, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lbl_TotalAmountCollected)
                        .addGap(6, 6, 6)
                        .addComponent(txt_TotalAmountCollected, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txt_TotalAmountInWords, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_CourseSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_CourseSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_TotalAmountCollected, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TotalAmountCollected, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_TotalAmountInWords, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_TotalAmountInWords, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 25, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelsideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelsideBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered
        Color clr = new Color(0, 145, 145);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited
        Color clr = new Color(0, 102, 102);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_btnHomeMouseExited

    private void btnEditCourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCourseMouseClicked
        EditCourse edit = new EditCourse();
        edit.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditCourseMouseClicked

    private void btnEditCourseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCourseMouseEntered
        Color clr = new Color(0, 102, 153);
        panelEditCourse.setBackground(clr);
    }//GEN-LAST:event_btnEditCourseMouseEntered

    private void btnEditCourseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCourseMouseExited
        Color clr = new Color(0, 160, 200);
        panelEditCourse.setBackground(clr);
    }//GEN-LAST:event_btnEditCourseMouseExited

    private void btnCourseListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseListMouseEntered
        Color clr = new Color(0, 102, 153);
        panelCourseList.setBackground(clr);
    }//GEN-LAST:event_btnCourseListMouseEntered

    private void btnCourseListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCourseListMouseExited
        Color clr = new Color(0, 160, 200);
        panelCourseList.setBackground(clr);
    }//GEN-LAST:event_btnCourseListMouseExited

    private void btnViewAllRecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordMouseEntered
        Color clr = new Color(0, 102, 153);
        panelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_btnViewAllRecordMouseEntered

    private void btnViewAllRecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordMouseExited
        Color clr = new Color(0, 160, 200);
        panelViewAllRecord.setBackground(clr);
    }//GEN-LAST:event_btnViewAllRecordMouseExited

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        Color clr = new Color(61, 85, 100);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_btnBackMouseEntered

    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        Color clr = new Color(51, 51, 51);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_btnBackMouseExited

    private void btnSearchRecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecordMouseClicked
        SearchRecord search = new SearchRecord();
        search.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSearchRecordMouseClicked

    private void btnSearchRecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecordMouseEntered
        Color clr = new Color(0, 102, 153);
        panelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_btnSearchRecordMouseEntered

    private void btnSearchRecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecordMouseExited
        Color clr = new Color(0, 160, 200);
        panelSearchRecord.setBackground(clr);
    }//GEN-LAST:event_btnSearchRecordMouseExited

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
        Color clr = new Color(255, 51, 51);
        jPanel11.setBackground(clr);
    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
        Color clr = new Color(204, 0, 51);
        jPanel11.setBackground(clr);
    }//GEN-LAST:event_jLabel9MouseExited

    private void combo_SelectCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_SelectCourseActionPerformed

    }//GEN-LAST:event_combo_SelectCourseActionPerformed

    private void btn_SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SubmitActionPerformed
        clearTableRecords();
        setRecordsToTable();
    }//GEN-LAST:event_btn_SubmitActionPerformed

    private void btn_PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PrintActionPerformed
        SimpleDateFormat Date_Format = new SimpleDateFormat("dd-MM-yyyy");
        String datefrom = Date_Format.format(dateChooserFrom.getDate());
        String dateto = Date_Format.format(dateChooserTo.getDate());

        MessageFormat header = new MessageFormat("Report From " + datefrom + " To " + dateto);
        MessageFormat footer = new MessageFormat("page{0,number,integer}");
        try {
            tbl_GenerateRecords.print(JTable.PrintMode.FIT_WIDTH, header, footer);

        } catch (Exception e) {
            e.getMessage();
        }
    }//GEN-LAST:event_btn_PrintActionPerformed

    private void btn_BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BrowseActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(new Date());

        try {
            File f = fileChooser.getSelectedFile();
            String path = f.getAbsolutePath();
            path = path + "_" + date + ".xlsx";
            txt_ExportToExcel.setText(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_BrowseActionPerformed

    private void btn_ExportToExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExportToExcelActionPerformed
        exportToExcel();
    }//GEN-LAST:event_btn_ExportToExcelActionPerformed

    private void dateChooserToAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dateChooserToAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_dateChooserToAncestorAdded

    private void dateChooserFromAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dateChooserFromAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_dateChooserFromAncestorAdded

    private void txt_ExportToExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ExportToExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ExportToExcelActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerateReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnCourseList;
    private javax.swing.JLabel btnEditCourse;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnSearchRecord;
    private javax.swing.JLabel btnViewAllRecord;
    private javax.swing.JButton btn_Browse;
    private javax.swing.JButton btn_ExportToExcel;
    private javax.swing.JButton btn_Print;
    private javax.swing.JButton btn_Submit;
    private javax.swing.JComboBox<String> combo_SelectCourse;
    private com.toedter.calendar.JDateChooser dateChooserFrom;
    private com.toedter.calendar.JDateChooser dateChooserTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_CourseSelected;
    private javax.swing.JLabel lbl_SelectCourse;
    private javax.swing.JLabel lbl_SelectDate;
    private javax.swing.JLabel lbl_SelectDateFrom;
    private javax.swing.JLabel lbl_SelectDateTo;
    private javax.swing.JLabel lbl_TotalAmountCollected;
    private javax.swing.JLabel lbl_TotalAmountInWords;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelCourseList;
    private javax.swing.JPanel panelEditCourse;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelSearchRecord;
    private javax.swing.JPanel panelViewAllRecord;
    private javax.swing.JPanel panelsideBar;
    private javax.swing.JTable tbl_GenerateRecords;
    private javax.swing.JLabel txt_CourseSelected;
    private javax.swing.JTextField txt_ExportToExcel;
    private javax.swing.JLabel txt_TotalAmountCollected;
    private javax.swing.JLabel txt_TotalAmountInWords;
    // End of variables declaration//GEN-END:variables
}
