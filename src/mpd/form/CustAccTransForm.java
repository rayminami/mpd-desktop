package mpd.form;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication2.DBConnection;
import javaapplication2.NewJFrame;
import javaapplication2.ViewCustAccTrans;
import mpd.dao.daoCustAccTrans;
import mpd.model.CustAccTrans;
import virtualkeyboard.gui.DialogVirtualKeyboardCalc;
import virtualkeyboard.gui.DialogVirtualKeyboardNumber;
import virtualkeyboard.gui.DialogVirtualKeyboardReal;

/**
 *
 * @author Admin
 */
public class CustAccTransForm extends javax.swing.JDialog {
    private final NewJFrame frame;
    public DBConnection dbConn;
    private final int t_customer_order_id;
    private ViewCustAccTrans vdoc;
    private String formAction;
    private Integer t_cust_acc_dtl_trans_id;
    /**
     * Creates new form UploadDokumenForm
     */
    public CustAccTransForm(NewJFrame parent, ViewCustAccTrans vdoc, boolean modal,String action) {
        super(parent, modal);
        this.frame = parent;
        this.vdoc = vdoc;
        this.t_customer_order_id = vdoc.t_customer_order_id;
        initComponents();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth() ) / 2 ,(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
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
        jLabel3 = new javax.swing.JLabel();
        service_charge_input = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        error_label = new javax.swing.JLabel();
        bill_no_input = new javax.swing.JTextField();
        trans_date_input = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Tanggal Transaksi");

        jLabel2.setText("Tambah Dokumen Pendukung");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nilai Transaksi");

        service_charge_input.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        service_charge_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                service_charge_inputMouseClicked(evt);
            }
        });
        service_charge_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                service_charge_inputActionPerformed(evt);
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

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("No Faktur");

        bill_no_input.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bill_no_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bill_no_inputMouseClicked(evt);
            }
        });
        bill_no_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bill_no_inputActionPerformed(evt);
            }
        });

        trans_date_input.setDateFormatString("dd-MM-yyyy");
        trans_date_input.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 206, Short.MAX_VALUE)
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
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(trans_date_input, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(error_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(service_charge_input)
                            .addComponent(bill_no_input))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(error_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(trans_date_input, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bill_no_input, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(service_charge_input, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel1)))
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        error_label.getAccessibleContext().setAccessibleName("error_label");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(this.t_cust_acc_dtl_trans_id == null){
            createCustAccTrans();
        }else{
            updateCustAccTrans();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void bill_no_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bill_no_inputActionPerformed
        // TODO add your handling code here:
        DialogVirtualKeyboardReal dlg;
        dlg = new DialogVirtualKeyboardReal(this.frame, true, bill_no_input);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);
    }//GEN-LAST:event_bill_no_inputActionPerformed

    private void bill_no_inputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bill_no_inputMouseClicked
        // TODO add your handling code here:
        DialogVirtualKeyboardReal dlg;
        dlg = new DialogVirtualKeyboardReal(this.frame, true, bill_no_input);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);
    }//GEN-LAST:event_bill_no_inputMouseClicked

    private void service_charge_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_service_charge_inputActionPerformed
        // TODO add your handling code here:
        DialogVirtualKeyboardCalc dlg;
        dlg = new DialogVirtualKeyboardCalc(this.frame, true, service_charge_input);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);
    }//GEN-LAST:event_service_charge_inputActionPerformed

    private void service_charge_inputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_service_charge_inputMouseClicked
        // TODO add your handling code here:
        DialogVirtualKeyboardCalc dlg;
        dlg = new DialogVirtualKeyboardCalc(this.frame, true, service_charge_input);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);
    }//GEN-LAST:event_service_charge_inputMouseClicked

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bill_no_input;
    private javax.swing.JLabel error_label;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField service_charge_input;
    private com.toedter.calendar.JDateChooser trans_date_input;
    // End of variables declaration//GEN-END:variables

    public void setValues(Integer id){
        try {
            Connection con = dbConn.openConnection();
            PreparedStatement st = con.prepareStatement("select * from t_cust_acc_dtl_trans \n" +
                                                        "where t_cust_acc_dtl_trans_id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            this.t_cust_acc_dtl_trans_id = id;
            java.util.Date transdate = rs.getTimestamp("trans_date");
            trans_date_input.setDate(transdate);
            bill_no_input.setText(rs.getString("bill_no"));
            service_charge_input.setText(String.format(Locale.ENGLISH,"%.2f", rs.getDouble("service_charge")));
        } catch (SQLException ex) {
            Logger.getLogger(CustAccTransForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private boolean createCustAccTrans(){
        if(true){
            CustAccTrans acc_trans = new CustAccTrans();
            acc_trans.setService_desc("");
            acc_trans.setTrans_date(this.trans_date_input.getDate());
            acc_trans.setDescription("");
            acc_trans.setBill_no(this.bill_no_input.getText());
            Double service_charge = Double.valueOf(this.service_charge_input.getText());
            acc_trans.setService_charge(service_charge);
            acc_trans.setVat_charge(0);
            acc_trans.setP_vat_type_dtl_id(this.frame.p_vat_type_dtl_id);
            daoCustAccTrans thisdao = this.vdoc.getDao();
            thisdao.insert(acc_trans);
            vdoc.setDataTable();
            this.setVisible(false);
        }
        return true;
    }
    private boolean updateCustAccTrans(){
        if(true){
            CustAccTrans acc_trans = new CustAccTrans();
            acc_trans.setT_cust_acc_dtl_trans_id(this.t_cust_acc_dtl_trans_id);
            acc_trans.setService_desc("");
            acc_trans.setTrans_date(this.trans_date_input.getDate());
            acc_trans.setDescription("");
            acc_trans.setBill_no(this.bill_no_input.getText());
            Double service_charge = Double.valueOf(this.service_charge_input.getText());
            acc_trans.setService_charge(service_charge);
            acc_trans.setVat_charge(0);
            acc_trans.setP_vat_type_dtl_id(this.frame.p_vat_type_dtl_id);
            daoCustAccTrans thisdao = this.vdoc.getDao();
            thisdao.insert(acc_trans);
            vdoc.setDataTable();
            this.setVisible(false);
        }
        return true;
    }
}
