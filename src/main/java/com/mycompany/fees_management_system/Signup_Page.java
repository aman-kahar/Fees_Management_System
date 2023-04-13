/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.fees_management_system;

import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JFrame;

/**
 *
 * @author Aman Kahar
 */
public class Signup_Page extends javax.swing.JFrame {

    /**
     * Creates new form Student Sign Up Page
     */
    String fname, lname, enrollmentNo, course, courseFees, email, contact_no, courseYear, createPassword, conformPassword;
    Date dob;
    int id = 0;

    public Signup_Page() {
        initComponents();
        fillComboCourseBox();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public int getId() {

        ResultSet rs = null;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management", "root", "root");
            String sql = "select max(id) from student_signup";
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt(1);
                id++;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return id;
    }

    boolean validation() {

        fname = txt_firstName.getText();
        lname = txt_lastName.getText();
        enrollmentNo = txt_enrollmentNo.getText();
        course = combo_course.getSelectedItem().toString();
        courseFees = txt_courseFees.getText();
        email = txt_emailId.getText();
        contact_no = txt_contactno.getText();
        dob = txt_dob.getDate();
        courseYear = txt_courseYear.getText();
        createPassword = txt_createPassword.getText();
        conformPassword = txt_conformPassword.getText();

        if (fname.equals("")) {
            JOptionPane.showMessageDialog(this, "please enter firstname");
            return false;
        }

        if (lname.equals("")) {
            JOptionPane.showMessageDialog(this, "please enter lastname");
            return false;
        }

        if (enrollmentNo.equals("")) {
            JOptionPane.showMessageDialog(this, "please enter enrollmentNo");
            return false;
        }

        if (course.equals("")) {
            JOptionPane.showMessageDialog(this, "please select course");
            return false;
        }

        if (courseFees.equals("")) {
            JOptionPane.showMessageDialog(this, "please enter course fees");
            return false;
        }
        if (email.equals("")) {
            JOptionPane.showMessageDialog(this, "please enter email Id");
            return false;
        }
        if (contact_no.equals("")) {
            JOptionPane.showMessageDialog(this, "please enter contact no");
            return false;
        }

        if (dob == null) {
            JOptionPane.showMessageDialog(this, "please enter date of birth");
            return false;
        }
        if (courseYear.equals("")) {
            JOptionPane.showMessageDialog(this, "please enter course year");
            return false;
        }

        if (createPassword.equals("")) {
            JOptionPane.showMessageDialog(this, "please enter password");
            return false;
        }

        if (conformPassword.equals("")) {
            JOptionPane.showMessageDialog(this, "please conform password");
            return false;
        }

        if (!createPassword.equals(conformPassword)) {
            JOptionPane.showMessageDialog(this, "password not matched");
            return false;
        }

        return true;
    }

    public void fillComboCourseBox() {

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management", "root", "root");
            String sql = "select cname from course";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                combo_course.addItem(rs.getString("cname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkPassword() {
        createPassword = txt_createPassword.getText();

        if (createPassword.length() < 8) {
            lbl_password_error.setText("password should be of 8 digit");
        } else {
            lbl_password_error.setText("");
        }
    }

    public void checkContactNo() {
        contact_no = txt_contactno.getText();

        if (contact_no.length() == 10) {
            lbl_contact_error.setText("");
        } else {
            lbl_contact_error.setText("contact no should be of 10 digit");
        }

    }

    void insertDetails() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String myDob = format.format(dob);
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management", "root", "root");

            PreparedStatement stmt = con.prepareStatement("insert into student_signup values(?,?,?,?,?,?,?,?,?,?,?)");

            stmt.setInt(1, getId());

            stmt.setString(2, fname);
            stmt.setString(3, lname);
            stmt.setString(4, enrollmentNo);
            stmt.setString(5, course);
            stmt.setString(6, courseFees);
            stmt.setString(7, email);
            stmt.setString(8, contact_no);
            stmt.setString(9, myDob);
            stmt.setString(10, courseYear);
            stmt.setString(11, createPassword);

            int i = stmt.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(this, "record inserted");
            } else {
                JOptionPane.showMessageDialog(this, "record not inserted");
            }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbl_firstName = new javax.swing.JLabel();
        txt_firstName = new javax.swing.JTextField();
        lbl_lastName = new javax.swing.JLabel();
        txt_lastName = new javax.swing.JTextField();
        lbl_enrollmentNO = new javax.swing.JLabel();
        txt_enrollmentNo = new javax.swing.JTextField();
        lbl_course = new javax.swing.JLabel();
        combo_course = new javax.swing.JComboBox<>();
        lbl_courseFees = new javax.swing.JLabel();
        txt_courseFees = new javax.swing.JTextField();
        lbl_emailId = new javax.swing.JLabel();
        txt_emailId = new javax.swing.JTextField();
        lbl_contactNo = new javax.swing.JLabel();
        txt_contactno = new javax.swing.JTextField();
        lbl_contact_error = new javax.swing.JLabel();
        lbl_dob = new javax.swing.JLabel();
        txt_dob = new com.toedter.calendar.JDateChooser();
        lbl_courseYear = new javax.swing.JLabel();
        txt_courseYear = new javax.swing.JTextField();
        lbl_createPassword = new javax.swing.JLabel();
        txt_createPassword = new javax.swing.JPasswordField();
        lbl_password_error = new javax.swing.JLabel();
        btn_signup = new javax.swing.JButton();
        lbl_conformPassword = new javax.swing.JLabel();
        txt_conformPassword = new javax.swing.JPasswordField();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 10));

        jLabel1.setBackground(new java.awt.Color(0, 102, 153));
        jLabel1.setFont(new java.awt.Font("Calisto MT", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Student SignUp");

        jLabel9.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\my icons\\signup_clr.png")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel9)
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, -1));

        jPanel2.setBackground(new java.awt.Color(235, 235, 235));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 160, 200), 12));

        lbl_firstName.setBackground(new java.awt.Color(255, 255, 255));
        lbl_firstName.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        lbl_firstName.setForeground(new java.awt.Color(0, 102, 153));
        lbl_firstName.setText("First Name :");

        txt_firstName.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N

        lbl_lastName.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        lbl_lastName.setForeground(new java.awt.Color(0, 102, 153));
        lbl_lastName.setText("Last Name :");

        txt_lastName.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N

        lbl_enrollmentNO.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        lbl_enrollmentNO.setForeground(new java.awt.Color(0, 102, 153));
        lbl_enrollmentNO.setText("Enrollment No : ");

        txt_enrollmentNo.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N

        lbl_course.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        lbl_course.setForeground(new java.awt.Color(0, 102, 153));
        lbl_course.setText("Course :");

        combo_course.setFont(new java.awt.Font("Copperplate Gothic Light", 3, 14)); // NOI18N
        combo_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_courseActionPerformed(evt);
            }
        });

        lbl_courseFees.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        lbl_courseFees.setForeground(new java.awt.Color(0, 102, 153));
        lbl_courseFees.setText("Course Fees :");

        txt_courseFees.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_courseFees.setEnabled(false);

        lbl_emailId.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        lbl_emailId.setForeground(new java.awt.Color(0, 102, 153));
        lbl_emailId.setText("Email Id :");

        txt_emailId.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_emailId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailIdActionPerformed(evt);
            }
        });
        txt_emailId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_emailIdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_emailIdKeyReleased(evt);
            }
        });

        lbl_contactNo.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        lbl_contactNo.setForeground(new java.awt.Color(0, 102, 153));
        lbl_contactNo.setText("Contact No. :");

        txt_contactno.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_contactno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_contactnoActionPerformed(evt);
            }
        });
        txt_contactno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_contactnoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_contactnoKeyReleased(evt);
            }
        });

        lbl_contact_error.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        lbl_contact_error.setForeground(new java.awt.Color(255, 0, 0));

        lbl_dob.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        lbl_dob.setForeground(new java.awt.Color(0, 102, 153));
        lbl_dob.setText("D.O.B :");

        txt_dob.setDateFormatString("yyyy-MM-dd");

        lbl_courseYear.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        lbl_courseYear.setForeground(new java.awt.Color(0, 102, 153));
        lbl_courseYear.setText("Course Year :");

        txt_courseYear.setFont(new java.awt.Font("Comic Sans MS", 0, 15)); // NOI18N
        txt_courseYear.setText("2019-2023");
        txt_courseYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_courseYearActionPerformed(evt);
            }
        });

        lbl_createPassword.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        lbl_createPassword.setForeground(new java.awt.Color(0, 102, 153));
        lbl_createPassword.setText("Create Passward :");

        txt_createPassword.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_createPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_createPasswordKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_createPasswordKeyReleased(evt);
            }
        });

        lbl_password_error.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        lbl_password_error.setForeground(new java.awt.Color(255, 0, 0));

        btn_signup.setBackground(new java.awt.Color(3, 53, 106));
        btn_signup.setFont(new java.awt.Font("Copperplate Gothic Bold", 3, 14)); // NOI18N
        btn_signup.setForeground(new java.awt.Color(255, 255, 255));
        btn_signup.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\my icons\\signup.png")); // NOI18N
        btn_signup.setText("Create Account");
        btn_signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_signupActionPerformed(evt);
            }
        });

        lbl_conformPassword.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        lbl_conformPassword.setForeground(new java.awt.Color(0, 102, 153));
        lbl_conformPassword.setText("Comform Passward :");

        txt_conformPassword.setFont(new java.awt.Font("Comic Sans MS", 3, 15)); // NOI18N
        txt_conformPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_conformPasswordKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_conformPasswordKeyReleased(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(0, 0, 0));
        btnExit.setFont(new java.awt.Font("Copperplate Gothic Bold", 3, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\my icons\\back1.png")); // NOI18N
        btnExit.setText("Back");
        btnExit.setActionCommand("Back");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_enrollmentNO, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_courseYear)
                            .addComponent(lbl_course)
                            .addComponent(lbl_courseFees)
                            .addComponent(lbl_emailId, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_contactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_dob, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_createPassword)
                            .addComponent(lbl_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_conformPassword)
                            .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(combo_course, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_enrollmentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_courseFees, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_emailId, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_courseYear, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_createPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(lbl_contact_error, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txt_dob, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                                                .addComponent(txt_contactno, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(lbl_password_error, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txt_conformPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(101, 101, 101)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 65, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_signup)
                                .addGap(103, 103, 103))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_enrollmentNO, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_enrollmentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_course, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_course, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_courseFees, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_courseFees, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_emailId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_emailId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_contactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_contactno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_contact_error, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_dob, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_dob, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_courseYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_courseYear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_createPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_createPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(lbl_password_error, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_conformPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_conformPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_signup, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 700, 810));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_signupActionPerformed
        if (validation()) {
            insertDetails();
        }
    }//GEN-LAST:event_btn_signupActionPerformed

    private void txt_createPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_createPasswordKeyPressed
        checkPassword();
    }//GEN-LAST:event_txt_createPasswordKeyPressed

    private void txt_createPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_createPasswordKeyReleased
        checkPassword();
    }//GEN-LAST:event_txt_createPasswordKeyReleased

    private void txt_contactnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_contactnoKeyReleased
        checkContactNo();
    }//GEN-LAST:event_txt_contactnoKeyReleased

    private void txt_contactnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_contactnoKeyPressed
        checkContactNo();
    }//GEN-LAST:event_txt_contactnoKeyPressed

    private void combo_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_courseActionPerformed
        String con_course = combo_course.getSelectedItem().toString();
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management", "root", "root");
            String sql = "select cost from course where cname = '" + con_course + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                txt_courseFees.setText(rs.getString("cost"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_combo_courseActionPerformed

    private void txt_contactnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_contactnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contactnoActionPerformed

    private void txt_courseYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_courseYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_courseYearActionPerformed

    private void txt_emailIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailIdActionPerformed

    private void txt_emailIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_emailIdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailIdKeyPressed

    private void txt_emailIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_emailIdKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailIdKeyReleased

    private void txt_conformPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_conformPasswordKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_conformPasswordKeyPressed

    private void txt_conformPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_conformPasswordKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_conformPasswordKeyReleased

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

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
            java.util.logging.Logger.getLogger(Signup_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Signup_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Signup_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Signup_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Signup_Page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btn_signup;
    private javax.swing.JComboBox<String> combo_course;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_conformPassword;
    private javax.swing.JLabel lbl_contactNo;
    private javax.swing.JLabel lbl_contact_error;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_courseFees;
    private javax.swing.JLabel lbl_courseYear;
    private javax.swing.JLabel lbl_createPassword;
    private javax.swing.JLabel lbl_dob;
    private javax.swing.JLabel lbl_emailId;
    private javax.swing.JLabel lbl_enrollmentNO;
    private javax.swing.JLabel lbl_firstName;
    private javax.swing.JLabel lbl_lastName;
    private javax.swing.JLabel lbl_password_error;
    private javax.swing.JPasswordField txt_conformPassword;
    private javax.swing.JTextField txt_contactno;
    private javax.swing.JTextField txt_courseFees;
    private javax.swing.JTextField txt_courseYear;
    private javax.swing.JPasswordField txt_createPassword;
    private com.toedter.calendar.JDateChooser txt_dob;
    private javax.swing.JTextField txt_emailId;
    private javax.swing.JTextField txt_enrollmentNo;
    private javax.swing.JTextField txt_firstName;
    private javax.swing.JTextField txt_lastName;
    // End of variables declaration//GEN-END:variables
}
