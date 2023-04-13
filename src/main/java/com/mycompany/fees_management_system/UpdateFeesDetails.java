/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fees_management_system;

import java.awt.Color;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Aman Kahar
 */
public class UpdateFeesDetails extends javax.swing.JFrame {

    /**
     * Creates new form AddFees
     */
    public UpdateFeesDetails() {
        initComponents();
        fillComboBankName();
        fillComboCourseBox();
        setRecord();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }

    public boolean validation() {

        if (dateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please Enter Date");
            return false;
        }

        if (combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("Cheque")) {
            if (txt_ChequeNo.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Cheque No.");
                return false;
            } else if (txt_ChequeNo.getText().matches("[0-9]+") == false) {
                JOptionPane.showMessageDialog(this, "Please Enter a Valid Cheque No");
                return false;
            }

        }

        if (combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("DD")) {
            if (txt_DDNo.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter DD No.");
                return false;
            } else if (txt_DDNo.getText().matches("[0-9]+") == false) {
                JOptionPane.showMessageDialog(this, "Please Enter a Valid DD No");
                return false;
            }

        }

        if (combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("Online")) {
            if (txt_TransactionId.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Transaction Id");
                return false;
            } else if (txt_TransactionId.getText().matches("[0-9]+") == false) {
                JOptionPane.showMessageDialog(this, "Please Enter a Valid Transaction Id");
                return false;
            }
        }

        if (txt_StudentName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Student Name");
            return false;
        }

        if (txt_Year1.getText().equals("") || txt_Year2.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter Year");
            return false;
        } else if ((txt_Year1.getText().matches("[0-9]+") == false) || (txt_Year2.getText().matches("[0-9]+") == false)) {
            JOptionPane.showMessageDialog(this, "Please Enter a Valid Year Format");
            return false;
        }

        if (txt_StudentRollNo.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Student Roll No");
            return false;
        }

        if (txt_Semester.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Semester");
            return false;
        }

        if (txt_Amount.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Amount");
            return false;
        } else if (txt_Amount.getText().matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(this, "Please Enter a Valid Amount (in number format)");
            return false;
        } else if (txt_TotalInWords.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Press Enter After Enterinig Amount");
            return false;
        }

        return true;
    }

    public void fillComboBankName() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management", "root", "root");
            String sql = "select bname from bankname";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                combo_BankName.addItem(rs.getString("bname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillComboCourseBox() {

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management", "root", "root");
            String sql = "select cname from course";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                combo_Course.addItem(rs.getString("cname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String updateData() {

        String status = "";

        int recieptNo = Integer.parseInt(txt_RecieptNo.getText());
        String studentName = txt_StudentName.getText();
        String rollNo = txt_StudentRollNo.getText();
        String paymentMode = combo_PaymentMode.getSelectedItem().toString();
        String chequeNo = txt_ChequeNo.getText();
        String bankName = combo_BankName.getSelectedItem().toString();
        String ddNo = txt_DDNo.getText();
        String transactionId = txt_TransactionId.getText();

        String courseName = combo_Course.getSelectedItem().toString();
        String gstin = txt_GSTINNo.getText();
        float totalAmount = Float.parseFloat(txt_Total.getText());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(dateChooser.getDate());
        float initialAmount = Float.parseFloat(txt_Amount.getText());
        float cgst = Float.parseFloat(txt_CGST.getText());
        float sgst = Float.parseFloat(txt_SGST.getText());
        String totalInWords = txt_TotalInWords.getText();
        String remark = txt_Remark.getText();
        int year1 = Integer.parseInt(txt_Year1.getText());
        int year2 = Integer.parseInt(txt_Year2.getText());
        int semester = Integer.parseInt(txt_Semester.getText());

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management", "root", "root");
            String sql = "update fees_details set student_name = ?, roll_no = ?, payment_mode = ?, cheque_no = ?, bank_name = ?, dd_no = ?, transaction_id = ?,"
                    + "course_name = ?, gstin = ?, total_amount = ?, t_date = ?, amount = ?, cgst = ?, sgst = ?, total_in_words = ?, remark = ?, year1 = ?, year2 = ?, semester = ? where reciept_no = ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, studentName);
            pst.setString(2, rollNo);
            pst.setString(3, paymentMode);
            pst.setString(4, chequeNo);
            pst.setString(5, bankName);
            pst.setString(6, ddNo);
            pst.setString(7, transactionId);
            pst.setString(8, courseName);
            pst.setString(9, gstin);
            pst.setFloat(10, totalAmount);
            pst.setString(11, date);
            pst.setFloat(12, initialAmount);
            pst.setFloat(13, cgst);
            pst.setFloat(14, sgst);
            pst.setString(15, totalInWords);
            pst.setString(16, remark);
            pst.setInt(17, year1);
            pst.setInt(18, year2);
            pst.setInt(19, semester);
            pst.setInt(20, recieptNo);

            int count = pst.executeUpdate();
            if (count == 1) {
                status = "Success";
            } else {
                status = "Failed";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public void setRecord() {

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management", "root", "root");
            String sql = "select * from fees_details order by reciept_no desc fetch first 1 rows only";
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            rs.next();

            txt_RecieptNo.setText(rs.getString("reciept_no"));
            txt_StudentName.setText(rs.getString("student_name"));
            txt_StudentRollNo.setText(rs.getString("roll_no"));

            combo_PaymentMode.setSelectedItem(rs.getString("payment_mode"));
            txt_ChequeNo.setText(rs.getString("cheque_no"));
            combo_BankName.setSelectedItem(rs.getString("bank_name"));
            txt_DDNo.setText(rs.getString("dd_no"));
            txt_TransactionId.setText(rs.getString("transaction_id"));

            combo_Course.setSelectedItem(rs.getString("course_name"));
            txt_CourseName.setText(rs.getString("course_name"));
            txt_GSTINNo.setText(rs.getString("gstin"));
            txt_Total.setText(rs.getString("total_amount"));
            String strDate = rs.getString("t_date");
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(strDate);
            dateChooser.setDate(date);

            String strAmount = rs.getString("amount");
            txt_Amount.setText(strAmount.substring(0, strAmount.lastIndexOf(".")));

            txt_CGST.setText(rs.getString("cgst"));
            txt_SGST.setText(rs.getString("sgst"));
            txt_TotalInWords.setText(rs.getString("total_in_words"));
            txt_Remark.setText(rs.getString("remark"));
            txt_Year1.setText(rs.getString("year1"));
            txt_Year2.setText(rs.getString("year2"));
            txt_Semester.setText(rs.getString("semester"));

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
        panelParent = new javax.swing.JPanel();
        lbl_RecepitNo = new javax.swing.JLabel();
        lbl_PaymentMode = new javax.swing.JLabel();
        lbl_ChequeNo = new javax.swing.JLabel();
        lbl_DDNo = new javax.swing.JLabel();
        lbl_DateChooser = new javax.swing.JLabel();
        lbl_GSTINNo = new javax.swing.JLabel();
        lbl_TransactionId = new javax.swing.JLabel();
        txt_RecieptNo = new javax.swing.JTextField();
        txt_ChequeNo = new javax.swing.JTextField();
        txt_DDNo = new javax.swing.JTextField();
        combo_PaymentMode = new javax.swing.JComboBox<>();
        dateChooser = new com.toedter.calendar.JDateChooser();
        panelChild = new javax.swing.JPanel();
        lbl_StudentName = new javax.swing.JLabel();
        lbl_Year = new javax.swing.JLabel();
        lbl_Remark = new javax.swing.JLabel();
        txt_Year2 = new javax.swing.JTextField();
        txt_Semester = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        combo_Course = new javax.swing.JComboBox<>();
        lbl_Semester = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_TotalInWords = new javax.swing.JTextField();
        txt_Total = new javax.swing.JTextField();
        txt_Amount = new javax.swing.JTextField();
        txt_CGST = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txt_SGST = new javax.swing.JTextField();
        lbl_SGST = new javax.swing.JLabel();
        txt_CourseName = new javax.swing.JTextField();
        lbl_TotalInWords = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Remark = new javax.swing.JTextArea();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        btn_Print = new javax.swing.JButton();
        lbl_Course = new javax.swing.JLabel();
        lbl_CGST = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_StudentName = new javax.swing.JTextField();
        lbl_StudentRollNo = new javax.swing.JLabel();
        txt_StudentRollNo = new javax.swing.JTextField();
        txt_Year1 = new javax.swing.JTextField();
        lbl_BankName1 = new javax.swing.JLabel();
        txt_TransactionId = new javax.swing.JTextField();
        combo_BankName = new javax.swing.JComboBox<>();
        txt_Reciept_No_Prefix = new javax.swing.JTextField();
        txt_GSTINNo = new javax.swing.JTextField();

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewAllRecordMouseClicked(evt);
            }
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
                .addComponent(btnViewAllRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\my icons\\update_fees_details.png")); // NOI18N
        jLabel1.setText(" Update Fees Details ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        panelParent.setBackground(new java.awt.Color(235, 235, 235));
        panelParent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 160, 200), 12));
        panelParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_RecepitNo.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_RecepitNo.setForeground(new java.awt.Color(0, 102, 153));
        lbl_RecepitNo.setText("Reciept No. : ");
        panelParent.add(lbl_RecepitNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 27, -1, 30));

        lbl_PaymentMode.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_PaymentMode.setForeground(new java.awt.Color(0, 102, 153));
        lbl_PaymentMode.setText("Mode of Payment : ");
        panelParent.add(lbl_PaymentMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 80, -1, 30));

        lbl_ChequeNo.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_ChequeNo.setForeground(new java.awt.Color(0, 102, 153));
        lbl_ChequeNo.setText("Cheque No : ");
        panelParent.add(lbl_ChequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 120, 30));

        lbl_DDNo.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_DDNo.setForeground(new java.awt.Color(0, 102, 153));
        lbl_DDNo.setText("DD No. :");
        panelParent.add(lbl_DDNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 130, -1, 30));

        lbl_DateChooser.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_DateChooser.setForeground(new java.awt.Color(0, 102, 153));
        lbl_DateChooser.setText("Date : ");
        panelParent.add(lbl_DateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, -1, 30));

        lbl_GSTINNo.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_GSTINNo.setForeground(new java.awt.Color(0, 102, 153));
        lbl_GSTINNo.setText("GSTIN : ");
        panelParent.add(lbl_GSTINNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, -1, 30));

        lbl_TransactionId.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_TransactionId.setForeground(new java.awt.Color(0, 102, 153));
        lbl_TransactionId.setText("Transaction Id : ");
        panelParent.add(lbl_TransactionId, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 130, 160, 30));

        txt_RecieptNo.setEditable(false);
        txt_RecieptNo.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_RecieptNo.setEnabled(false);
        txt_RecieptNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_RecieptNoActionPerformed(evt);
            }
        });
        panelParent.add(txt_RecieptNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 170, 36));

        txt_ChequeNo.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        panelParent.add(txt_ChequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 225, 36));

        txt_DDNo.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        panelParent.add(txt_DDNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 225, 36));

        combo_PaymentMode.setFont(new java.awt.Font("Copperplate Gothic Light", 3, 15)); // NOI18N
        combo_PaymentMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Online", "DD", "Cheque", "Cash" }));
        combo_PaymentMode.setSelectedIndex(3);
        combo_PaymentMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_PaymentModeActionPerformed(evt);
            }
        });
        panelParent.add(combo_PaymentMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 79, 225, 36));

        dateChooser.setDateFormatString("dd-MM-yyyy");
        dateChooser.setFont(new java.awt.Font("Comic Sans MS", 3, 13)); // NOI18N
        dateChooser.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dateChooserAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        panelParent.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, 150, 36));

        panelChild.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_StudentName.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_StudentName.setForeground(new java.awt.Color(0, 102, 153));
        lbl_StudentName.setText("Student Name :");
        panelChild.add(lbl_StudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, 30));

        lbl_Year.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_Year.setForeground(new java.awt.Color(0, 102, 153));
        lbl_Year.setText("Year : ");
        panelChild.add(lbl_Year, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, -1, 30));

        lbl_Remark.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_Remark.setForeground(new java.awt.Color(0, 102, 153));
        lbl_Remark.setText("Remark : ");
        panelChild.add(lbl_Remark, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, -1, 30));

        txt_Year2.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_Year2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Year2ActionPerformed(evt);
            }
        });
        panelChild.add(txt_Year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 94, 36));

        txt_Semester.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        panelChild.add(txt_Semester, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 70, 94, 36));

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 153));
        jLabel13.setText("-");
        panelChild.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, -1, 30));

        combo_Course.setFont(new java.awt.Font("Copperplate Gothic Light", 3, 14)); // NOI18N
        combo_Course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_CourseActionPerformed(evt);
            }
        });
        panelChild.add(combo_Course, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 360, 36));

        lbl_Semester.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_Semester.setForeground(new java.awt.Color(0, 102, 153));
        lbl_Semester.setText("Semester : ");
        panelChild.add(lbl_Semester, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, -1, 30));

        jSeparator1.setForeground(new java.awt.Color(0, 102, 153));
        panelChild.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 176, 943, 10));
        panelChild.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 490, 250, 10));

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 153));
        jLabel16.setText("Head");
        panelChild.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 50, 30));

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 153));
        jLabel17.setText("Amount (in Rupee)");
        panelChild.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, -1, 30));

        txt_TotalInWords.setEditable(false);
        txt_TotalInWords.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_TotalInWords.setEnabled(false);
        panelChild.add(txt_TotalInWords, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 440, 36));

        txt_Total.setEditable(false);
        txt_Total.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_Total.setEnabled(false);
        panelChild.add(txt_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, 193, 36));

        txt_Amount.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_Amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_AmountActionPerformed(evt);
            }
        });
        panelChild.add(txt_Amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 240, 193, 36));

        txt_CGST.setEditable(false);
        txt_CGST.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_CGST.setEnabled(false);
        panelChild.add(txt_CGST, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 290, 193, 36));

        jSeparator3.setForeground(new java.awt.Color(0, 102, 153));
        panelChild.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 224, 943, 10));

        txt_SGST.setEditable(false);
        txt_SGST.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_SGST.setEnabled(false);
        panelChild.add(txt_SGST, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 340, 193, 36));

        lbl_SGST.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_SGST.setForeground(new java.awt.Color(0, 102, 153));
        lbl_SGST.setText("SGST [9%] -");
        panelChild.add(lbl_SGST, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, -1, 30));

        txt_CourseName.setEditable(false);
        txt_CourseName.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_CourseName.setEnabled(false);
        txt_CourseName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_CourseNameActionPerformed(evt);
            }
        });
        panelChild.add(txt_CourseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 241, 360, 36));

        lbl_TotalInWords.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_TotalInWords.setForeground(new java.awt.Color(0, 102, 153));
        lbl_TotalInWords.setText("Total in Words : ");
        panelChild.add(lbl_TotalInWords, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, 30));

        txt_Remark.setColumns(20);
        txt_Remark.setFont(new java.awt.Font("Comic Sans MS", 3, 13)); // NOI18N
        txt_Remark.setRows(5);
        txt_Remark.setText("No Remarks");
        jScrollPane1.setViewportView(txt_Remark);

        panelChild.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 440, 110));
        panelChild.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 380, 250, 10));

        jLabel20.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        jLabel20.setText("Receiver Signature");
        panelChild.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 490, -1, -1));

        btn_Print.setBackground(new java.awt.Color(3, 53, 106));
        btn_Print.setFont(new java.awt.Font("Copperplate Gothic Bold", 3, 14)); // NOI18N
        btn_Print.setForeground(new java.awt.Color(255, 255, 255));
        btn_Print.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\my icons\\printer-.png")); // NOI18N
        btn_Print.setText("PRINT");
        btn_Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PrintActionPerformed(evt);
            }
        });
        panelChild.add(btn_Print, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 520, -1, 70));

        lbl_Course.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_Course.setForeground(new java.awt.Color(0, 102, 153));
        lbl_Course.setText("Course (Branch) : ");
        panelChild.add(lbl_Course, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 30));

        lbl_CGST.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_CGST.setForeground(new java.awt.Color(0, 102, 153));
        lbl_CGST.setText(" CGST [9%] - ");
        panelChild.add(lbl_CGST, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, -1, 30));

        jLabel18.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 153));
        jLabel18.setText("Sr No.");
        panelChild.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 187, -1, 30));

        txt_StudentName.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        panelChild.add(txt_StudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 290, 36));

        lbl_StudentRollNo.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_StudentRollNo.setForeground(new java.awt.Color(0, 102, 153));
        lbl_StudentRollNo.setText("Enrollment No. :");
        panelChild.add(lbl_StudentRollNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, 30));

        txt_StudentRollNo.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        panelChild.add(txt_StudentRollNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 290, 36));

        txt_Year1.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        panelChild.add(txt_Year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 94, 36));

        panelParent.add(panelChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, 592));

        lbl_BankName1.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        lbl_BankName1.setForeground(new java.awt.Color(0, 102, 153));
        lbl_BankName1.setText("Bank Name : ");
        panelParent.add(lbl_BankName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 183, -1, 30));

        txt_TransactionId.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        panelParent.add(txt_TransactionId, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 225, 36));

        combo_BankName.setFont(new java.awt.Font("Copperplate Gothic Light", 3, 14)); // NOI18N
        panelParent.add(combo_BankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 300, 40));

        txt_Reciept_No_Prefix.setEditable(false);
        txt_Reciept_No_Prefix.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_Reciept_No_Prefix.setText("SRIT- ");
        txt_Reciept_No_Prefix.setEnabled(false);
        panelParent.add(txt_Reciept_No_Prefix, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 220, 36));

        txt_GSTINNo.setEditable(false);
        txt_GSTINNo.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_GSTINNo.setText("23AAAFM4249F1ZN");
        txt_GSTINNo.setEnabled(false);
        panelParent.add(txt_GSTINNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, 160, 36));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelsideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelParent, javax.swing.GroupLayout.PREFERRED_SIZE, 1009, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelsideBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelParent, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered
        Color clr = new Color(0, 145, 145);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited
        Color clr = new Color(0, 102, 102);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_btnHomeMouseExited

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

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeMouseClicked

    private void txt_RecieptNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_RecieptNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_RecieptNoActionPerformed

    private void btn_PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PrintActionPerformed
        if (validation() == true) {
            String status = updateData();

            if (status.equals("Success")) {
                JOptionPane.showMessageDialog(this, "Fees Record Updated");

                PrintReciept p = new PrintReciept();
                p.show();
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Something Went's Wrong.\nInsert Data Again");
            }
        }
    }//GEN-LAST:event_btn_PrintActionPerformed

    private void combo_PaymentModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_PaymentModeActionPerformed
        if (combo_PaymentMode.getSelectedIndex() == 0) {
            lbl_TransactionId.setVisible(true);
            txt_TransactionId.setVisible(true);

            lbl_DDNo.setVisible(false);
            txt_DDNo.setVisible(false);

            lbl_ChequeNo.setVisible(false);
            txt_ChequeNo.setVisible(false);

            lbl_BankName1.setVisible(false);
            combo_BankName.setVisible(false);
        }

        if (combo_PaymentMode.getSelectedIndex() == 1) {
            lbl_TransactionId.setVisible(false);
            txt_TransactionId.setVisible(false);

            lbl_DDNo.setVisible(true);
            txt_DDNo.setVisible(true);

            lbl_ChequeNo.setVisible(false);
            txt_ChequeNo.setVisible(false);

            lbl_BankName1.setVisible(true);
            combo_BankName.setVisible(true);
        }

        if (combo_PaymentMode.getSelectedIndex() == 2) {
            lbl_TransactionId.setVisible(false);
            txt_TransactionId.setVisible(false);

            lbl_DDNo.setVisible(false);
            txt_DDNo.setVisible(false);

            lbl_ChequeNo.setVisible(true);
            txt_ChequeNo.setVisible(true);

            lbl_BankName1.setVisible(true);
            combo_BankName.setVisible(true);
        }

        if (combo_PaymentMode.getSelectedIndex() == 3) {
            lbl_TransactionId.setVisible(false);
            txt_TransactionId.setVisible(false);

            lbl_DDNo.setVisible(false);
            txt_DDNo.setVisible(false);

            lbl_ChequeNo.setVisible(false);
            txt_ChequeNo.setVisible(false);

            lbl_BankName1.setVisible(false);
            combo_BankName.setVisible(false);
        }
    }//GEN-LAST:event_combo_PaymentModeActionPerformed

    private void txt_AmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_AmountActionPerformed
        Float amnt = Float.parseFloat(txt_Amount.getText());

        Float cgst = (float) (amnt * 0.09);
        Float sgst = (float) (amnt * 0.09);

        txt_CGST.setText(cgst.toString());
        txt_SGST.setText(sgst.toString());

        float total = amnt + cgst + sgst;

        txt_Total.setText(Float.toString(total));

        txt_TotalInWords.setText(NumberToWordsConverter.convert((int) total) + " Only");
    }//GEN-LAST:event_txt_AmountActionPerformed

    private void txt_CourseNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_CourseNameActionPerformed

    }//GEN-LAST:event_txt_CourseNameActionPerformed

    private void combo_CourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_CourseActionPerformed
        txt_CourseName.setText(combo_Course.getSelectedItem().toString());
    }//GEN-LAST:event_combo_CourseActionPerformed

    private void dateChooserAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dateChooserAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_dateChooserAncestorAdded

    private void txt_Year2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Year2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Year2ActionPerformed

    private void btnSearchRecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchRecordMouseClicked
        SearchRecord search = new SearchRecord();
        search.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSearchRecordMouseClicked

    private void btnEditCourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCourseMouseClicked
        EditCourse edit = new EditCourse();
        edit.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditCourseMouseClicked

    private void btnViewAllRecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewAllRecordMouseClicked
        ViewAllRecords view = new ViewAllRecords();
        view.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnViewAllRecordMouseClicked

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
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateFeesDetails().setVisible(true);
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
    private javax.swing.JButton btn_Print;
    private javax.swing.JComboBox<String> combo_BankName;
    private javax.swing.JComboBox<String> combo_Course;
    private javax.swing.JComboBox<String> combo_PaymentMode;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbl_BankName1;
    private javax.swing.JLabel lbl_CGST;
    private javax.swing.JLabel lbl_ChequeNo;
    private javax.swing.JLabel lbl_Course;
    private javax.swing.JLabel lbl_DDNo;
    private javax.swing.JLabel lbl_DateChooser;
    private javax.swing.JLabel lbl_GSTINNo;
    private javax.swing.JLabel lbl_PaymentMode;
    private javax.swing.JLabel lbl_RecepitNo;
    private javax.swing.JLabel lbl_Remark;
    private javax.swing.JLabel lbl_SGST;
    private javax.swing.JLabel lbl_Semester;
    private javax.swing.JLabel lbl_StudentName;
    private javax.swing.JLabel lbl_StudentRollNo;
    private javax.swing.JLabel lbl_TotalInWords;
    private javax.swing.JLabel lbl_TransactionId;
    private javax.swing.JLabel lbl_Year;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelChild;
    private javax.swing.JPanel panelCourseList;
    private javax.swing.JPanel panelEditCourse;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelParent;
    private javax.swing.JPanel panelSearchRecord;
    private javax.swing.JPanel panelViewAllRecord;
    private javax.swing.JPanel panelsideBar;
    private javax.swing.JTextField txt_Amount;
    private javax.swing.JTextField txt_CGST;
    private javax.swing.JTextField txt_ChequeNo;
    private javax.swing.JTextField txt_CourseName;
    private javax.swing.JTextField txt_DDNo;
    private javax.swing.JTextField txt_GSTINNo;
    private javax.swing.JTextField txt_RecieptNo;
    private javax.swing.JTextField txt_Reciept_No_Prefix;
    private javax.swing.JTextArea txt_Remark;
    private javax.swing.JTextField txt_SGST;
    private javax.swing.JTextField txt_Semester;
    private javax.swing.JTextField txt_StudentName;
    private javax.swing.JTextField txt_StudentRollNo;
    private javax.swing.JTextField txt_Total;
    private javax.swing.JTextField txt_TotalInWords;
    private javax.swing.JTextField txt_TransactionId;
    private javax.swing.JTextField txt_Year1;
    private javax.swing.JTextField txt_Year2;
    // End of variables declaration//GEN-END:variables
}
