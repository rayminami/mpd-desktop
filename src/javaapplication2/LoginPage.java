/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import virtualkeyboard.gui.DialogVirtualKeyboardReal;

/**
 *
 * @author Admin
 */
public class LoginPage extends javax.swing.JPanel {
    private final NewJFrame frame;
    public DBConnection dbConn;
    Image bgimage = null;
    /**
     * Creates new form LoginPage
     */
    public LoginPage(NewJFrame frame) {
        super();
        this.frame = frame;
        initComponents();
        jPanel1.setOpaque(false);
        MediaTracker mt = new MediaTracker(this);
        bgimage = Toolkit.getDefaultToolkit().getImage("bg-yanjak.jpg");
        mt.addImage(bgimage, 0);
        try {
            mt.waitForAll();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        dbConn = new DBConnection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUname = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 108, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel1.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(97, 106, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        txtUname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUnameFocusGained(evt);
            }
        });
        txtUname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUnameMouseClicked(evt);
            }
        });
        txtUname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnameActionPerformed(evt);
            }
        });
        txtUname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnameKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 151;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(94, 10, 0, 0);
        jPanel1.add(txtUname, gridBagConstraints);

        txtPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPasswordMouseClicked(evt);
            }
        });
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 153;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 10, 0, 111);
        jPanel1.add(txtPassword, gridBagConstraints);

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 76, 0);
        jPanel1.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 106;
        gridBagConstraints.ipady = 63;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(43, 10, 48, 10);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        // TODO add your handling code here:
        
        boolean usernameValid = false;
        String username = txtUname.getText().trim();
        String password = txtPassword.getText().trim();
        
        //pengecekkan query login
        if( username.isEmpty() || password.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Username dan Password tidak boleh kosong");
            return;
        }
        
        
        String user_name = "";
        String is_employee = "";
        String user_status = "";
        String user_password = "";
        try {
            Connection con = dbConn.openConnection();
            Statement st = con.createStatement();
            String query = "SELECT p_app_user_id as user_id,app_user_name as user_name,user_pwd as user_password,email_address as user_email,full_name as user_realname,p_user_status_id as user_status, is_employee FROM p_app_user WHERE app_user_name = '" + username + "'";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                user_name = rs.getString("user_name");
                is_employee = rs.getString("is_employee");
                user_password = rs.getString("user_password");
                user_status = rs.getString("user_status");
            }
            
            if(user_name.equals("")) { //username tidak terdaftar
                JOptionPane.showMessageDialog(null, "Username tidak terdaftar");
                return;
            }
            
            if(is_employee.equals("Y")) { //pegawai
                JOptionPane.showMessageDialog(null, "Username tidak terdaftar");
                return;
            }
            
            if(!user_status.equals("1")) { //tidak aktif
                JOptionPane.showMessageDialog(null, "Username tidak Aktif");
                return;
            }
            
            if(!this.md5Java(password.trim()).trim().equals(user_password.trim())) {
                JOptionPane.showMessageDialog(null, "Password tidak sama");
                return;
            }
            
            con.close();
            st.close();
            usernameValid = true;
            
            
        } catch (SQLException | NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(usernameValid) { //jika valid
            frame.callEventTest(this);
            //this.setVisible(false);
            InputDataPembayaran formInputDataPembayaran = new InputDataPembayaran(frame, user_name);
            formInputDataPembayaran.pack();
            
            frame.getContentPane().add(formInputDataPembayaran);
            frame.setContentPane(formInputDataPembayaran);
            formInputDataPembayaran.setVisible(true);
            
        }else { //jika tidak tampilkan pesan
            
        }
        
         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtUnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnameActionPerformed
        DialogVirtualKeyboardReal dlg;
        dlg = new DialogVirtualKeyboardReal(frame, false, txtUname);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnameActionPerformed

    private void txtUnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnameFocusGained

    }//GEN-LAST:event_txtUnameFocusGained

    private void txtUnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUnameMouseClicked
        DialogVirtualKeyboardReal dlg;
        dlg = new DialogVirtualKeyboardReal(frame, false, txtUname);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);
    }//GEN-LAST:event_txtUnameMouseClicked

    private void txtUnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnameKeyTyped

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        DialogVirtualKeyboardReal dlg;
        dlg = new DialogVirtualKeyboardReal(frame, false, txtPassword);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPasswordMouseClicked
        DialogVirtualKeyboardReal dlg;
        dlg = new DialogVirtualKeyboardReal(frame, false, txtPassword);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);
    }//GEN-LAST:event_txtPasswordMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUname;
    // End of variables declaration//GEN-END:variables
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int imwidth = bgimage.getWidth(null);
        int imheight = bgimage.getHeight(null);
        g.drawImage(bgimage, 1, 1, null);
    }
    
    public String md5Java(String message) throws NoSuchAlgorithmException{
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
           
            //converting byte array to Hexadecimal String
           StringBuilder sb = new StringBuilder(2*hash.length);
           for(byte b : hash){
               sb.append(String.format("%02x", b&0xff));
           }
          
           digest = sb.toString();
          
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return digest;
    }

}
