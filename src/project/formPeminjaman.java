/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author farul
 */
public class formPeminjaman extends javax.swing.JFrame {
    Connection koneksi;
    PreparedStatement pst, pst2;
    ResultSet rst;
     int kstok, istok, istok2, tstok, ijumlah;
     String  dbuku,buku,kd;

    /**
     * Creates new form formPeminjaman
     */
    public formPeminjaman() {
        initComponents();
        koneksi=Database.DataBase();
        txtnis.setDocument(new limitField(5));
        txtadmin.setDocument(new limitField(5));
        showTable();
        detail();
        autonumber();
    }
    
    public void showTable() {
         try {
        String sql="select * from buku";
        pst=koneksi.prepareStatement(sql);
        rst=pst.executeQuery();
        tblbuku.setModel(DbUtils.resultSetToTableModel(rst));
       } catch (Exception e){ JOptionPane.showMessageDialog(null, e);}  
    }
    
    public void clear(){
        txtstok.setText("");
    }
    public void clear2(){
        txtnis.setText("");
        txtadmin.setText("");
        datepinjam.setCalendar(null);
        datekembali.setCalendar(null);
    }
    public void kurangStok(){
        int qty;
        qty=Integer.parseInt(txtstok.getText());
        kstok=istok-qty;
    }
     
    public void tambahStok(){
    tstok=ijumlah+istok2;
        try {
        String update="update buku set stok='"+tstok+"' where id_buku='"+buku+"'";
        pst2=koneksi.prepareStatement(update);
        pst2.execute();
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    
    public void ambilStok(){
    try {
        String sql="select * from buku where id_buku='"+buku+"'";
        pst=koneksi.prepareStatement(sql);
        rst=pst.executeQuery();
        if (rst.next()) {    
        String stok=rst.getString(("stok"));
        istok2= Integer.parseInt(stok);
        }
    }catch (Exception e) {JOptionPane.showMessageDialog(null, e);}
    }

   
    public void autonumber(){
    try{
        String sql = "SELECT MAX(RIGHT(id_peminjaman,3)) AS NO FROM peminjaman";
        pst=koneksi.prepareStatement(sql);
        rst=pst.executeQuery();
        while (rst.next()) {
                if (rst.first() == false) {
                    txtid.setText("PJ001");
                } else {
                    rst.last();
                    int auto_id = rst.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int idtransaksi = no.length();
                    for (int j = 0; j < 3 - idtransaksi; j++) {
                        no = "0" + no;
                    }
                    txtid.setText("PJ" + no);
                    txtid.setEditable(false);
                }
            }
        rst.close();
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    
    public void detail(){
    try {
        String kodeDetail=txtid.getText();
        String kd="D"+kodeDetail;
        String sql="select * from detail_peminjaman where id_detail='"+kd+"'";
        pst=koneksi.prepareStatement(sql);
        rst=pst.executeQuery();
        tbldetail.setModel(DbUtils.resultSetToTableModel(rst));
       } catch (Exception e){ JOptionPane.showMessageDialog(null, e);} 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbuku = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldetail = new javax.swing.JTable();
        btnkembali = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        btntambah = new javax.swing.JButton();
        txtstok = new javax.swing.JTextField();
        btnkurang = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtnis = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btncek = new javax.swing.JButton();
        datepinjam = new com.toedter.calendar.JDateChooser();
        datekembali = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtadmin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnpinjam = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 180, 219));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel7.setText("PEMINJAMAN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(378, 378, 378))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        tblbuku.setModel(new javax.swing.table.DefaultTableModel(
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
        tblbuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblbuku);

        tbldetail.setModel(new javax.swing.table.DefaultTableModel(
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
        tbldetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldetailMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbldetail);

        btnkembali.setBackground(new java.awt.Color(41, 128, 185));
        btnkembali.setText("Kembali");
        btnkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkembaliActionPerformed(evt);
            }
        });

        txtsearch.setText("Search");
        txtsearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtsearchMouseClicked(evt);
            }
        });
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });

        btntambah.setBackground(new java.awt.Color(41, 128, 185));
        btntambah.setText("+");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        btnkurang.setBackground(new java.awt.Color(41, 128, 185));
        btnkurang.setText("-");
        btnkurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkurangActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(238, 242, 243));

        jLabel2.setText("NIS");

        btncek.setBackground(new java.awt.Color(41, 128, 185));
        btncek.setText("Cek");
        btncek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncekActionPerformed(evt);
            }
        });

        datepinjam.setDateFormatString("yyyy-MM-dd");
        datepinjam.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datepinjamPropertyChange(evt);
            }
        });

        datekembali.setDateFormatString("yyyy-MM-dd");

        jLabel3.setText("Pinjam");

        jLabel4.setText("Kembali");

        jLabel5.setText("Admin");

        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        jLabel1.setText("Kode ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtnis, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncek, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(datekembali, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addComponent(datepinjam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnis, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncek, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(datepinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(datekembali, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        jLabel6.setBackground(new java.awt.Color(238, 242, 243));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("                                                                   Keranjang");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        btnpinjam.setBackground(new java.awt.Color(41, 128, 185));
        btnpinjam.setText("Pinjam");
        btnpinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpinjamActionPerformed(evt);
            }
        });

        btnclear.setBackground(new java.awt.Color(41, 128, 185));
        btnclear.setText("Clear");
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnkurang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnkembali)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtstok, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btntambah, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnpinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtstok, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btntambah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnkurang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnpinjam)
                            .addComponent(btnclear))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnkembali)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtsearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsearchMouseClicked
        // TODO add your handling code here:
        txtsearch.setText(null);
    }//GEN-LAST:event_txtsearchMouseClicked

    private void tblbukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbukuMouseClicked
        // TODO add your handling code here:
         try {
            int row=tblbuku.getSelectedRow();
            String tabelclick=(tblbuku.getModel().getValueAt(row, 0).toString());
            String sql="select * from buku where id_buku='"+tabelclick+"'";
            pst=koneksi.prepareStatement(sql);
            rst=pst.executeQuery();
        if (rst.next()) {
            buku=rst.getString(("id_buku"));    
            String stok=rst.getString(("Stok"));
            istok=Integer.parseInt(stok);
            }
        }catch (Exception e) {JOptionPane.showMessageDialog(null, e);}
    }//GEN-LAST:event_tblbukuMouseClicked

    private void tbldetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldetailMouseClicked
        // TODO add your handling code here:
        try {
            int row=tbldetail.getSelectedRow();
            dbuku=(tbldetail.getModel().getValueAt(row, 1).toString());
            String sql="select * from detail_peminjaman where id_buku='"+dbuku+"'";
            pst=koneksi.prepareStatement(sql);
            rst=pst.executeQuery();
        if (rst.next()) {   
            String jumlah=rst.getString(("jumlah"));
            ijumlah=Integer.parseInt(jumlah);
            }
        }catch (Exception e) {JOptionPane.showMessageDialog(null, e);}
            ambilStok();
    }//GEN-LAST:event_tbldetailMouseClicked

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        // TODO add your handling code here:    
        kurangStok();
        try {
            String kodeDetail=txtid.getText();
            kd="D"+kodeDetail;
            String sql="insert into detail_peminjaman (id_detail,id_buku,jumlah) value (?,?,?)";
            String update="update buku set stok='"+kstok+"' where id_buku='"+buku+"'";
            pst=koneksi.prepareStatement(sql);
            pst2=koneksi.prepareStatement(update);
            pst.setString(1, kd);
            pst.setString(2, buku);  
            pst.setString(3, txtstok.getText());     
            pst.execute();
            pst2.execute();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            }
            clear();
            detail();   
            showTable();
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnkurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkurangActionPerformed
        // TODO add your handling code here:
          try {
            String sql="delete from detail_peminjaman where id_buku=?";
            pst=koneksi.prepareStatement(sql);
            pst.setString(1, dbuku);
            pst.execute();
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
        detail();
        tambahStok();
        showTable();
    }//GEN-LAST:event_btnkurangActionPerformed

    private void btnkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkembaliActionPerformed
        // TODO add your handling code here:
         new formMenu().setVisible(true);
         dispose();
    }//GEN-LAST:event_btnkembaliActionPerformed

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        // TODO add your handling code here:
            try {
            String sql="select * from buku where judul_buku LIKE '%"+txtsearch.getText()+"%'";
            pst=koneksi.prepareStatement(sql);
            rst=pst.executeQuery();
            tblbuku.setModel(DbUtils.resultSetToTableModel(rst));
        } catch (Exception e){ JOptionPane.showMessageDialog(null, e);} 
    }//GEN-LAST:event_txtsearchKeyReleased

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtidActionPerformed

    private void btncekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncekActionPerformed
        // TODO add your handling code here:
          try {
        String sql="Select * from siswa where id_siswa='"+txtnis.getText()+"'";
        pst=koneksi.prepareStatement(sql);
        rst=pst.executeQuery();
        if (rst.next()) {
            if(txtnis.getText().equals(rst.getString("id_siswa")))
            {  
                JOptionPane.showMessageDialog(null, "NIS Terdaftar!");
            }
                } else {JOptionPane.showMessageDialog(null, "NIS Tidak Terdaftar!");
                }
        }catch (Exception e) {JOptionPane.showMessageDialog(null, e);}
    }//GEN-LAST:event_btncekActionPerformed

    private void btnpinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpinjamActionPerformed
        // TODO add your handling code here:
        String tampilan ="yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggalPinjam = String.valueOf(fm.format(datepinjam.getDate()));
        String tanggalKembali = String.valueOf(fm.format(datekembali.getDate()));
        //------------------------------------------------""-------------------------------------------
        String nis = txtnis.getText();
        String admin = txtadmin.getText();
        String pinjam = tanggalPinjam;
        String kembali = tanggalKembali;
        if(nis.equals("")||admin.equals("")||pinjam.equals("")||kembali.equals("")){
            JOptionPane.showMessageDialog(null,"Isi data dengan lengkap!");
        }else {
        try {
            String sql="insert into peminjaman (id_peminjaman,id_detail,tanggal_pinjam,tanggal_kembali, status, id_siswa,id_petugas) value (?,?,?,?,?,?,?)";
            pst=koneksi.prepareStatement(sql);
            pst.setString(1, txtid.getText());
            pst.setString(2, kd);
            pst.setString(3, tanggalPinjam);
            pst.setString(4, tanggalKembali);
            pst.setString(5, "Sedang Dipinjam");
            pst.setString(6, txtnis.getText());
            pst.setString(7, txtadmin.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            }
        autonumber();
        clear2();
        detail();
        }
    }//GEN-LAST:event_btnpinjamActionPerformed

    private void datepinjamPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datepinjamPropertyChange
        // TODO add your handling code here:
  
    }//GEN-LAST:event_datepinjamPropertyChange

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
        clear2();
        clear();
    }//GEN-LAST:event_btnclearActionPerformed

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
            java.util.logging.Logger.getLogger(formPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formPeminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncek;
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnkembali;
    private javax.swing.JButton btnkurang;
    private javax.swing.JButton btnpinjam;
    private javax.swing.JButton btntambah;
    private com.toedter.calendar.JDateChooser datekembali;
    private com.toedter.calendar.JDateChooser datepinjam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable tblbuku;
    private javax.swing.JTable tbldetail;
    private javax.swing.JTextField txtadmin;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnis;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtstok;
    // End of variables declaration//GEN-END:variables
}
