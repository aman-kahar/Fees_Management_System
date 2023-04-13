/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fees_management_system;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Aman Kahar
 */
public class StartAnimation extends javax.swing.JFrame {

    /**
     * Creates new form StartAnimation
     */
    String text1 = "FEES MANGAEMENT SYSTEM ";
    String text2 = "SHRI RAM INSTITUTE OF TECHNOLOGY JABALPUR ";
    Thread t;

    public StartAnimation() {
        initComponents();
        callme();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public void callme() {
        this.animation1();
        this.animation2();
        this.loginMe();
    }

    public void animation2() {
        t = new Thread() {
            @Override
            public void run() {
                int len = text1.length();
                String s = "";
                int time = 80;

                for (int i = 0; i < len; i++) {

                    lbl_main.setText(s + "A");
                    try {
                        t.sleep(time);
                    } catch (InterruptedException ex) {
                    }

                    lbl_main.setText(s + "E");
                    try {
                        t.sleep(time);
                    } catch (InterruptedException ex) {
                    }

                    lbl_main.setText(s + "H");
                    try {
                        t.sleep(time);
                    } catch (InterruptedException ex) {
                    }

                    lbl_main.setText(s + text1.charAt(i));
                    s = s + text1.charAt(i);
                }
            }
        };

        t.start();

    }

    public void animation1() {
        t = new Thread() {
            @Override
            public void run() {
                int len = text2.length();
                String s = "";
                int time = 35;

                for (int i = 0; i < len; i++) {

                    lbl_main2.setText(s + "A");
                    try {
                        t.sleep(time);
                    } catch (InterruptedException ex) {
                    }

                    lbl_main2.setText(s + "E");
                    try {
                        t.sleep(time);
                    } catch (InterruptedException ex) {
                    }

                    lbl_main2.setText(s + "H");
                    try {
                        t.sleep(time);
                    } catch (InterruptedException ex) {
                    }

                    lbl_main2.setText(s + text2.charAt(i));
                    s = s + text2.charAt(i);
                }
            }
        };

        t.start();

    }

    public void loginMe() {
        t = new Thread() {
            public void run() {
                try {
                    t.sleep(7000);
                    log();
                } catch (InterruptedException ex) {
                }
            }
        };

        t.start();
    }

    public void log() {
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
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
        lbl_main2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbl_main = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(244, 245, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 120, 153), 15));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_main2.setFont(new java.awt.Font("Algerian", 3, 48)); // NOI18N
        lbl_main2.setForeground(new java.awt.Color(0, 120, 153));
        lbl_main2.setText("SHRI RAM INSTITUTE OF TECHNBOLOGY JABALPUR ");
        jPanel1.add(lbl_main2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, 107));

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\icon2.png")); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1015, 395, 271, 260));

        lbl_main.setFont(new java.awt.Font("Algerian", 3, 44)); // NOI18N
        lbl_main.setForeground(new java.awt.Color(0, 160, 200));
        lbl_main.setText("FEES MANAGEMENT SYSTEM ");
        jPanel1.add(lbl_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, -1, 107));

        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\icon3.png")); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 395, -1, 260));

        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\MAJOR PROJECT DATABASE\\Icon_Images\\Shri_Ram_Group_Logo.jpeg")); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(557, 28, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 740));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(StartAnimation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartAnimation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartAnimation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartAnimation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartAnimation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_main;
    private javax.swing.JLabel lbl_main2;
    // End of variables declaration//GEN-END:variables
}