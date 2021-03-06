/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import mpd.model.Item;
import mpd.model.Pembayaran;
import virtualkeyboard.gui.DialogVirtualKeyboardNumber;


/**
 *
 * @author toshiba
 */
public class FormInputDataPembayaran extends javax.swing.JDialog {
    private final NewJFrame frame;
    public DBConnection dbConn;
    public Pembayaran pembayaran;
    private double percentage;
    private double pengali_denda;
    private double totalVatAmount;
    private double totalDenda;
    /**
     * Creates new form FormInputDataPembayaran
     */
    public FormInputDataPembayaran(NewJFrame parent, boolean modal, Pembayaran pembayaran) {
        
        super(parent, modal);
        this.frame = parent;
        initComponents();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth() ) / 2 ,(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
        this.pembayaran = pembayaran;
        initFormFields();
        txtNilaiOmset.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hitungPajak();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                hitungPajak();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                 hitungPajak();
            }
        });
    }
    
    private void initFormFields() {
        
        Connection con = dbConn.openConnection();
        Statement st;
        try {
            st = con.createStatement();
            
            /*add cmbNPWD*/
            cmbNPWPD.addItem(new Item(pembayaran.getT_cust_account_id(), pembayaran.getNpwd()));
            
            /*add cmbPeriodePajak (mengambil 3 tahun kebelakang)*/
            String query = "select *, to_char(start_date,'dd-mm-yyyy') as start_period, to_char(end_date,'dd-mm-yyyy') as end_period from view_finance_period_bayar finance\n" +
                            "where substring(code from char_length(code) - 3)::Integer >= extract(year from sysdate) - 3\n" +
                            "order by start_date desc";
            
            ResultSet rs = st.executeQuery(query);
            Item initPeriod = new Item(null,"");
            initPeriod.setAdditionalVal("pengali_denda", "0");
            cmbPeriodePajak.addItem(initPeriod);
            while(rs.next()) {
                Item periodItem = new Item(rs.getInt("p_finance_period_id"),rs.getString("code"));
                periodItem.setAdditionalVal("start_period",rs.getString("start_period"));
                periodItem.setAdditionalVal("end_period",rs.getString("end_period"));
                periodItem.setAdditionalVal("pengali_denda",rs.getString("pengali_denda"));
                cmbPeriodePajak.addItem(periodItem);
            }
            
            cmbPeriodePajak.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent arg0) {
                    
                    Item item = (Item)cmbPeriodePajak.getSelectedItem();
                    dateMasaPajakFrom.setText( item.getAdditionalVal("start_period"));
                    dateMasaPajakUntil.setText( item.getAdditionalVal("end_period"));
                    
                    txtNilaiOmset.setText("0");
                    txtNilaiHarusDibayar.setText("0");
                    txtNilaiDenda.setText("0");
                    txtTotalHarusBayar.setText("0");
                }
            });
            
            /* cmbAyatPajak */
            query = "select p_vat_type_dtl_id, vat_code,vat_pct from p_vat_type_dtl\n" +
                    "where p_vat_type_dtl_id = " + pembayaran.getP_vat_type_dtl_id();
            
            rs = st.executeQuery(query);
            while(rs.next()) {
                Item vat_dtl_item = new Item(rs.getInt("p_vat_type_dtl_id"),rs.getString("vat_code"));
                vat_dtl_item.setAdditionalVal("vat_pct", rs.getString("vat_pct"));
                cmbAyatPajak.addItem(vat_dtl_item);
            }
            
            
            /* cmbKelasPajak */
            query = "select p_vat_type_dtl_cls_id, vat_code,vat_pct from p_vat_type_dtl_cls\n" +
                    "where p_vat_type_dtl_id = " + pembayaran.getP_vat_type_dtl_id();
            
            rs = st.executeQuery(query);
            cmbKelasPajak.addItem(new Item(null,""));
            int totalKelasPajak =0; 
            while(rs.next()) {
                Item vat_dtl_cls_item = new Item(rs.getInt("p_vat_type_dtl_cls_id"),rs.getString("vat_code"));
                vat_dtl_cls_item.setAdditionalVal("vat_pct", rs.getString("vat_pct"));
                cmbKelasPajak.addItem(vat_dtl_cls_item);
                totalKelasPajak++;
            }   
            if(totalKelasPajak == 0){
                cmbKelasPajak.setVisible(false);
                jLabel6.setVisible(false);
            }
            this.percentage = (float) pembayaran.getVat_pct();
        } catch (SQLException ex) {
            Logger.getLogger(FormInputDataPembayaran.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        cmbNPWPD = new javax.swing.JComboBox();
        cmbPeriodePajak = new javax.swing.JComboBox();
        cmbAyatPajak = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbKelasPajak = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dateMasaPajakFrom = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        dateMasaPajakUntil = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNilaiOmset = new javax.swing.JTextField();
        txtNilaiHarusDibayar = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtNilaiDenda = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTotalHarusBayar = new javax.swing.JTextField();
        txtNomorPembayaran = new javax.swing.JTextField();
        btnPrintNoPembayaran = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnTutup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Tambah Data Pelaporan");

        cmbNPWPD.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        cmbPeriodePajak.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cmbPeriodePajak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPeriodePajakActionPerformed(evt);
            }
        });

        cmbAyatPajak.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("NPWPD");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Periode");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Ayat Pajak");

        cmbKelasPajak.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Kelas Pajak");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Masa Pajak");

        dateMasaPajakFrom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("s.d");

        dateMasaPajakUntil.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Nilai Omset");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Pajak yang harus dibayar");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Denda");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Total yang harus dibayar");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Nomor Pembayaran");

        txtNilaiOmset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNilaiOmset.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNilaiOmset.setText("0");
        txtNilaiOmset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNilaiOmsetMouseClicked(evt);
            }
        });
        txtNilaiOmset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNilaiOmsetActionPerformed(evt);
            }
        });
        txtNilaiOmset.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNilaiOmsetKeyReleased(evt);
            }
        });

        txtNilaiHarusDibayar.setBackground(new java.awt.Color(153, 255, 153));
        txtNilaiHarusDibayar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNilaiHarusDibayar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNilaiHarusDibayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNilaiHarusDibayarMouseClicked(evt);
            }
        });
        txtNilaiHarusDibayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNilaiHarusDibayarActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Rp.");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Rp.");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Rp.");

        txtNilaiDenda.setBackground(new java.awt.Color(153, 255, 153));
        txtNilaiDenda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNilaiDenda.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Rp.");

        txtTotalHarusBayar.setBackground(new java.awt.Color(153, 255, 153));
        txtTotalHarusBayar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTotalHarusBayar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalHarusBayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTotalHarusBayarMouseClicked(evt);
            }
        });
        txtTotalHarusBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalHarusBayarActionPerformed(evt);
            }
        });

        txtNomorPembayaran.setEditable(false);
        txtNomorPembayaran.setBackground(new java.awt.Color(153, 255, 153));
        txtNomorPembayaran.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnPrintNoPembayaran.setText("Print No Pembayaran");
        btnPrintNoPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintNoPembayaranActionPerformed(evt);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnTutup.setText("Tutup");
        btnTutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTutupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrintNoPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnTutup, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4)
                                .addComponent(jLabel2)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(dateMasaPajakFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel8)
                                    .addGap(18, 18, 18)
                                    .addComponent(dateMasaPajakUntil, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cmbNPWPD, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbKelasPajak, javax.swing.GroupLayout.Alignment.LEADING, 0, 277, Short.MAX_VALUE)
                                    .addComponent(cmbAyatPajak, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbPeriodePajak, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTotalHarusBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel15))
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtNilaiHarusDibayar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                            .addComponent(txtNilaiDenda, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNilaiOmset))))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17)
                                .addComponent(txtNomorPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(155, 155, 155))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbNPWPD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPeriodePajak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAyatPajak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbKelasPajak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(dateMasaPajakFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(dateMasaPajakUntil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNilaiOmset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNilaiHarusDibayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel16)
                    .addComponent(txtNilaiDenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel17)
                    .addComponent(txtTotalHarusBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtNomorPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnPrintNoPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTutup, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTutupActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnTutupActionPerformed

    private void txtNilaiOmsetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNilaiOmsetActionPerformed
        // TODO add your handling code here:
        DialogVirtualKeyboardNumber dlg;  
        dlg = new DialogVirtualKeyboardNumber(this.frame, true, txtNilaiOmset);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);
        
    }//GEN-LAST:event_txtNilaiOmsetActionPerformed

    private void txtNilaiOmsetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNilaiOmsetMouseClicked
        // TODO add your handling code here:
        this.txtNilaiOmset.setText("");
        DialogVirtualKeyboardNumber dlg;
        dlg = new DialogVirtualKeyboardNumber(this.frame, true, txtNilaiOmset);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);
        
    }//GEN-LAST:event_txtNilaiOmsetMouseClicked

    private void btnPrintNoPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintNoPembayaranActionPerformed
        // TODO add your handling code here:
        URL website;
        ReadableByteChannel rbc;
        FileOutputStream fos;
       
        String noBayar = txtNomorPembayaran.getText();
        if (noBayar.equals("")) {
            JOptionPane.showMessageDialog(this.frame, "Nomor Pembayaran Belum Ada. Simpan Data Terlebih Dahulu");
        } else {

            try {
                String url = "http://202.154.24.3:81/mpd/report/cetak_no_bayar_anjungan.php?no_bayar=" + noBayar;
                website = new URL(url);
                rbc = Channels.newChannel(website.openStream());
                String fileLocation = "pdf_pembayaran\\" + noBayar + ".pdf";

                fos = new FileOutputStream(fileLocation);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

                rbc.close();
                fos.close();

                Runtime rt = Runtime.getRuntime();
                //Process pr = rt.exec("java -jar pdfbox-app-1.8.8.jar PDFReader "+fileLocation);
                Process pr = rt.exec("sumatrapdf.exe -reuse-instance -view \"single view\" " + fileLocation);

            } catch (IOException ex) {
                Logger.getLogger(FormInputDataPembayaran.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnPrintNoPembayaranActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        
        if(formIsValid()) {
            //t_cust_account_id
            Item item = (Item) cmbNPWPD.getSelectedItem();
            pembayaran.setT_cust_account_id(item.getId());
            
            //p_finance_period_id
            item = (Item) cmbPeriodePajak.getSelectedItem();
            pembayaran.setP_finance_period_id(item.getId());
            
            //start_period
            pembayaran.setStart_period( dateMasaPajakFrom.getText() );
            
            //end period
            pembayaran.setEnd_period( dateMasaPajakUntil.getText() );
            
            //total_trans_amount
            double total_trans_amount = Double.valueOf(txtNilaiOmset.getText());
            pembayaran.setTotal_trans_amount(total_trans_amount);
            
            //total_vat_amount
            double total_vat_amount = this.totalDenda+this.totalVatAmount;
            pembayaran.setTotal_vat_amount(total_vat_amount);
            
            //p_vat_type_dtl_id
            item = (Item) cmbAyatPajak.getSelectedItem();
            pembayaran.setP_vat_type_dtl_id(item.getId());
            
            //p_vat_type_dtl_cls_id
            item = (Item) cmbKelasPajak.getSelectedItem();
            pembayaran.setP_vat_type_dtl_cls_id(item.getId());
            
            //user_name {sudah ada di objek pembayaran}
            
            try {
                pembayaran.doInsert();
                
                if(pembayaran.getNoPembayaran().equals("")) {
                    JOptionPane.showMessageDialog(this.frame, "Data gagal tersimpan");
                }else {
                    txtNomorPembayaran.setText(pembayaran.getNoPembayaran());
                    txtNilaiDenda.setText(String.valueOf(pembayaran.getNilaiPenalty()));
                    JOptionPane.showMessageDialog(this.frame, "Data telah tersimpan. Nomor Pembayaran : "+ pembayaran.getNoPembayaran() +". Silahkan Cetak Nomor Pembayaran");
                    
                }
                                
            } catch (SQLException ex) {
                Logger.getLogger(FormInputDataPembayaran.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtNilaiHarusDibayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNilaiHarusDibayarActionPerformed
        // TODO add your handling code here:
        DialogVirtualKeyboardNumber dlg;
        dlg = new DialogVirtualKeyboardNumber(this.frame, true, txtNilaiHarusDibayar);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);
    }//GEN-LAST:event_txtNilaiHarusDibayarActionPerformed

    private void txtNilaiHarusDibayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNilaiHarusDibayarMouseClicked
        // TODO add your handling code here:
        DialogVirtualKeyboardNumber dlg;
        dlg = new DialogVirtualKeyboardNumber(this.frame, true, txtNilaiHarusDibayar);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);
    }//GEN-LAST:event_txtNilaiHarusDibayarMouseClicked

    private void txtTotalHarusBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalHarusBayarActionPerformed
        // TODO add your handling code here:
        DialogVirtualKeyboardNumber dlg;
        dlg = new DialogVirtualKeyboardNumber(this.frame, true, txtTotalHarusBayar);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);
    }//GEN-LAST:event_txtTotalHarusBayarActionPerformed

    private void txtTotalHarusBayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTotalHarusBayarMouseClicked
        // TODO add your handling code here:
        DialogVirtualKeyboardNumber dlg;
        dlg = new DialogVirtualKeyboardNumber(this.frame, true, txtTotalHarusBayar);
        dlg.setPoitToUp(false);
        dlg.setShiftBs(false);
        dlg.setLocaleL(Locale.ENGLISH);
    }//GEN-LAST:event_txtTotalHarusBayarMouseClicked

    private void cmbPeriodePajakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPeriodePajakActionPerformed
        // TODO add your handling code here:
        Item item = (Item) cmbPeriodePajak.getSelectedItem();
        this.pengali_denda = Double.valueOf(item.getAdditionalVal("pengali_denda"));
        if(this.pengali_denda < 0){
            this.pengali_denda = 0;
        }
    }//GEN-LAST:event_cmbPeriodePajakActionPerformed
    
    private void txtNilaiOmsetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNilaiOmsetKeyReleased
        // TODO add your handling code here:
        this.hitungPajak();
    }//GEN-LAST:event_txtNilaiOmsetKeyReleased
    
    private boolean formIsValid() {
        
        String message = "";
        
        String nomor_pembayaran = txtNomorPembayaran.getText();
        if(!nomor_pembayaran.equals("")) {
            message += "Data sudah tersimpan. Silahkan Print Nomor Pembayaran";
            JOptionPane.showMessageDialog(this.frame, message);
            return false;
        }
        
        Item item = (Item) cmbPeriodePajak.getSelectedItem();
        if(item.getId() == null ) {
            message += "- Periode Pajak Belum Diisi \n";
        }
        
        String start_period = dateMasaPajakFrom.getText();
        String end_period = dateMasaPajakUntil.getText();
        
        if(start_period.equals("") || end_period.equals("")) {
            message += "- Masa Pajak Tidak Boleh Kosong \n";
            JOptionPane.showMessageDialog(this.frame, message);
            return false;
        }
        
        
        double total_trans_amount = this.totalVatAmount + this.totalDenda;
        if(total_trans_amount == 0) {
            message += "- Nilai Omset Belum Diisi \n";
        }
        
        double total_vat_amount = this.totalVatAmount;
        if(total_vat_amount == 0 ) {
            message += "- Nilai Pajak Yang Harus Dibayar Belum Diisi\n";
        }
        
        if(!message.equals("")) {
            JOptionPane.showMessageDialog(this.frame, message);
            return false;
        }
        
        return true;
    }
    
    
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrintNoPembayaran;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTutup;
    private javax.swing.JComboBox cmbAyatPajak;
    private javax.swing.JComboBox cmbKelasPajak;
    private javax.swing.JComboBox cmbNPWPD;
    private javax.swing.JComboBox cmbPeriodePajak;
    private javax.swing.JFormattedTextField dateMasaPajakFrom;
    private javax.swing.JFormattedTextField dateMasaPajakUntil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtNilaiDenda;
    private javax.swing.JTextField txtNilaiHarusDibayar;
    private javax.swing.JTextField txtNilaiOmset;
    private javax.swing.JTextField txtNomorPembayaran;
    private javax.swing.JTextField txtTotalHarusBayar;
    // End of variables declaration//GEN-END:variables
    private void hitungPajak(){
        double persen;
        double nilai;
        double denda;
        if(this.txtNilaiOmset.getText()=="" || this.txtNilaiOmset.getText().isEmpty()){
            nilai=0;
        }else{
            nilai=Double.valueOf(this.txtNilaiOmset.getText());
        }
        persen = this.percentage;
        denda = this.pengali_denda;
        this.totalVatAmount = (double)nilai*((double)persen/100);
        this.totalDenda = (double)denda * (double)totalVatAmount;
        this.txtNilaiDenda.setText(String.format("%1$,.2f", totalDenda));
        this.txtNilaiHarusDibayar.setText(String.format("%1$,.2f", totalVatAmount));
        this.txtTotalHarusBayar.setText(String.format("%1$,.2f", totalVatAmount+totalDenda));
    }
}
