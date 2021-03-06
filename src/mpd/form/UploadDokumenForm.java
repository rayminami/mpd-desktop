package mpd.form;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication2.DBConnection;
import javaapplication2.NewJFrame;
import javaapplication2.UploadWindow;
import javaapplication2.ViewUploadDokumen;
import mpd.lib.UlploadDoc;
import mpd.model.Item;
import virtualkeyboard.gui.DialogVirtualKeyboardReal;

/**
 *
 * @author Admin
 */
public class UploadDokumenForm extends javax.swing.JDialog {
    private final NewJFrame frame;
    private File docFile;
    public DBConnection dbConn;
    private final int t_customer_order_id;
    public String file_location;
    private ViewUploadDokumen vdoc;
    private Integer t_cust_order_legal_doc_id;
    public File getDocFile() {
        return docFile;
    }

    public void setDocFile(File docFile) {
        this.docFile = docFile;
    }
    /**
     * Creates new form UploadDokumenForm
     */
    public UploadDokumenForm(NewJFrame parent,ViewUploadDokumen vdoc, boolean modal) {
        super(parent, modal);
        this.frame = parent;
        this.vdoc = vdoc;
        this.t_customer_order_id = vdoc.t_customer_order_id;
        initComponents();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth() ) / 2 ,(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
        initCmbDocType();
    }
    public void setFileName(String file_name){
        this.file_name_field.setText(file_name);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        file_name_field = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        legal_doc_desc = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        p_legal_doc_type_id = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        error_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Upload File");

        jLabel2.setText("Tambah Dokumen Pendukung");

        file_name_field.setEditable(false);
        file_name_field.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        file_name_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                file_name_fieldActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Deskripsi");

        legal_doc_desc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        legal_doc_desc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                legal_doc_descMouseClicked(evt);
            }
        });
        legal_doc_desc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legal_doc_descActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        p_legal_doc_type_id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        p_legal_doc_type_id.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        p_legal_doc_type_id.setName("p_legal_doc_type_id"); // NOI18N
        p_legal_doc_type_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_legal_doc_type_idActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Jenis Dokumen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(error_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(p_legal_doc_type_id, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(file_name_field, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(legal_doc_desc))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addComponent(error_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(file_name_field, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p_legal_doc_type_id, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(legal_doc_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        error_label.getAccessibleContext().setAccessibleName("error_label");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        UploadWindow upwin;
        upwin = new UploadWindow(this, true);
        upwin.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(this.file_name_field == null){
            error_label.setText("File Belum dipilih");
            return;
        }
        if(this.docFile == null && this.t_cust_order_legal_doc_id != 0){
            try {
                Item item = (Item) p_legal_doc_type_id.getSelectedItem();
                Connection connection;
                connection = DBConnection.openConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE t_cust_order_legal_doc\n" +
                                                                            "SET p_legal_doc_type_id = ?,\n" +
                                                                            "description = ?\n" +
                                                                            "WHERE\n" +
                                                                            "t_cust_order_legal_doc_id = ?");
                statement.setInt(1, item.getId());
                statement.setString(2, legal_doc_desc.getText());
                statement.setInt(3, this.t_cust_order_legal_doc_id);
                statement.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(UploadDokumenForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            vdoc.setDataTable();
            this.setVisible(false);
            return;
        }
        System.out.println(this.frame.user_name);
        UlploadDoc main = new UlploadDoc(this.frame.user_name);
        Item item = (Item) p_legal_doc_type_id.getSelectedItem();
        main.httpConn(this.t_customer_order_id, item.getId(), legal_doc_desc.getText(),this.file_name_field.getText(),this.file_location,this.t_cust_order_legal_doc_id);
        this.setVisible(false);
        vdoc.setDataTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void p_legal_doc_type_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_legal_doc_type_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_legal_doc_type_idActionPerformed

    private void file_name_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_file_name_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_file_name_fieldActionPerformed

    private void legal_doc_descActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legal_doc_descActionPerformed
        // TODO add your handling code here:
        DialogVirtualKeyboardReal dlg;
        dlg = new DialogVirtualKeyboardReal(this.frame, true, legal_doc_desc);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);
    }//GEN-LAST:event_legal_doc_descActionPerformed

    private void legal_doc_descMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_legal_doc_descMouseClicked
        // TODO add your handling code here:
        DialogVirtualKeyboardReal dlg;
        dlg = new DialogVirtualKeyboardReal(this.frame, true, legal_doc_desc);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);
    }//GEN-LAST:event_legal_doc_descMouseClicked

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel error_label;
    private javax.swing.JTextField file_name_field;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField legal_doc_desc;
    private javax.swing.JComboBox p_legal_doc_type_id;
    // End of variables declaration//GEN-END:variables

    private void initCmbDocType() {
        try {
            p_legal_doc_type_id.removeAllItems();
            Connection con = dbConn.openConnection();
            Statement st = con.createStatement();

            String query = "select * from p_legal_doc_type ORDER BY p_legal_doc_type_id";

            ResultSet rs = st.executeQuery(query);
            p_legal_doc_type_id.addItem(new Item(null,""));
            while(rs.next()) {
                Item docTypeItem = new Item(rs.getInt("p_legal_doc_type_id"),rs.getString("code"));
                p_legal_doc_type_id.addItem(docTypeItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UploadDokumenForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setValues(Integer id){
        try {
            Connection con = dbConn.openConnection();
            PreparedStatement st = con.prepareStatement("select t_cust_order_legal_doc.*,code from t_cust_order_legal_doc \n" +
                                                        "left join p_legal_doc_type on p_legal_doc_type.p_legal_doc_type_id = t_cust_order_legal_doc.p_legal_doc_type_id\n" +
                                                        "where t_cust_order_legal_doc_id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            int legal_doc_type_id = rs.getInt("p_legal_doc_type_id");
            this.t_cust_order_legal_doc_id = id;
            p_legal_doc_type_id.setSelectedItem(new Item(rs.getInt("p_legal_doc_type_id"),rs.getString("code")));
            file_name_field.setText(rs.getString("origin_file_name"));
            legal_doc_desc.setText(rs.getString("description"));
        } catch (SQLException ex) {
            Logger.getLogger(UploadDokumenForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
