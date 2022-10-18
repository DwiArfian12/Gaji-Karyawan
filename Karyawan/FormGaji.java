/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Karyawan;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Koneksi.DB_Koneksi;
import java.sql.Statement;


/**
 *
 * @author ACER
 */
public class FormGaji extends javax.swing.JFrame {

    private final DefaultTableModel model;
    String nip, nama, jabatan;
    int gajipokok, transport, gajibersih;
    
    /**
     * Creates new form FormGaji
     */
    public FormGaji() {
        initComponents();
        model = new DefaultTableModel();
        tbGaji.setModel(model);
        model.addColumn("NIP");
        model.addColumn("Nama");
        model.addColumn("Jabatan");
        model.addColumn("Gaji Pokok");
        model.addColumn("Transport");
        model.addColumn("Gaji Bersih");
        
        getData();
    }
    
    public final void getData(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try {
            Statement stat  = (Statement) DB_Koneksi.getKoneksi().createStatement();
            String sql      = "SELECT * FROM t_gaji";
            ResultSet rs    = stat.executeQuery(sql);
            
            while(rs.next()){
                Object[] obj = new Object[6];
                obj [0] = rs.getString("nip");
                obj [1] = rs.getString("nama");
                obj [2] = rs.getString("jabatan");
                obj [3] = rs.getString("gajipokok");
                obj [4] = rs.getString("transport");
                obj [5] = rs.getString("gajibersih");
                
                model.addRow(obj);
            }
        } catch (SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void loadData(){
        nip = txtNip.getText();
        nama = txtNama.getText();
        jabatan = (String)cbJabatan.getSelectedItem();
        gajipokok = Integer.parseInt(txtGapok.getText());
        transport = Integer.parseInt(txtTransport.getText());
        gajibersih = Integer.parseInt(txtGaber.getText());
    }
    
    public void loadGaji(){
        jabatan = "" + cbJabatan.getSelectedItem();
        switch (jabatan){
            case "Direktur Utama" -> gajipokok = 8000000;
            case "Manager" -> gajipokok = 6500000;
            case "Asistant Manager" -> gajipokok = 5000000;
            case "HRD" -> gajipokok = 4000000;
            case "Karyawan" -> gajipokok = 2500000;
            case "Office Boy" -> gajipokok = 1750000;
        }
        
        transport = (int)(gajipokok * 0.1);
        gajibersih = gajipokok+transport;
        txtGapok.setText(""+gajipokok);
        txtTransport.setText(""+transport);
        txtGaber.setText(""+gajibersih);
        
    }
    
    public void saveData(){
        loadData();
        try {
            Statement stat  = (Statement) DB_Koneksi.getKoneksi().createStatement();
            String sql = "INSERT INTO t_gaji (nip, nama, jabatan, gajipokok, transport, gajibersih)"
                    + "VALUES ('"+nip+"','"+nama+"','"+jabatan+"','"+gajipokok+"','"+transport+"','"+gajibersih+"')";
            PreparedStatement p = (PreparedStatement)DB_Koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            getData();
            
        } catch (SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void Reset(){
        nip = "";
        nama = "";
        jabatan = "";
        gajipokok = 0;
        transport = 0;
        gajibersih = 0;
        txtNip.setText(nip);
        txtNama.setText(nama);
        txtGapok.setText("");
        txtTransport.setText("");
        txtGaber.setText(""); 
    }
    
    public void dataSelect(){
        int i = tbGaji.getSelectedRow();
        if (i == -1) {
            return;
        }
        txtNip.setText(""+model.getValueAt(i, 0));
        txtNama.setText(""+model.getValueAt(i, 1));
        cbJabatan.setSelectedItem(""+model.getValueAt(i, 2));
        txtGapok.setText(""+model.getValueAt(i, 3));
        txtTransport.setText(""+model.getValueAt(i, 4));
        txtGaber.setText(""+model.getValueAt(i, 5));
    }
    
    public void updateData(){
        loadData();
        try {
            Statement stat  = (Statement) DB_Koneksi.getKoneksi().createStatement();
            String sql = "UPDATE t_gaji SET nama = '"+nama+"',"+"jabatan = '"+jabatan+"',"+"gajipokok = "+gajipokok+","+"transport = "+transport+","+"gajibersih = "+gajibersih+" WHERE nip="+nip;
            PreparedStatement p = (PreparedStatement)DB_Koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            getData();
            Reset();
            JOptionPane.showMessageDialog(null, "UPDATE BERHASIL");
        } catch (SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void deleteData(){
        loadData();
        int pesan = JOptionPane.showConfirmDialog(null, "Yakin mau menghapus data nip: "+nip+"?","Konfirmasi",
        JOptionPane.OK_CANCEL_OPTION);
        if(pesan==JOptionPane.OK_OPTION){
            try {
                Statement stat  = (Statement) DB_Koneksi.getKoneksi().createStatement();
                String sql = "DELETE FROM t_gaji WHERE nip="+nip;
                PreparedStatement p = (PreparedStatement)DB_Koneksi.getKoneksi().prepareStatement(sql);
                p.executeUpdate();
                getData();
                Reset();
                JOptionPane.showMessageDialog(null, "DELETE BERHASIL");
            } catch (SQLException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
            }   
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
        jLabel2 = new javax.swing.JLabel();
        txtNip = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGaber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtGapok = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTransport = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbGaji = new javax.swing.JTable();
        cbJabatan = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DAFTAR GAJI KARYAWAN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(223, 223, 223))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("NIP");

        txtNip.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNip.setName("txttNip"); // NOI18N
        txtNip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNipActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Nama Lengkap");

        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNama.setName("txtNama"); // NOI18N
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Jabatan");

        txtGaber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGaber.setName("txtGaber"); // NOI18N
        txtGaber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGaberActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Gaji Bersih");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Gaji Pokok");

        txtGapok.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGapok.setName("txtGapok"); // NOI18N
        txtGapok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGapokActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Transport");

        txtTransport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTransport.setName("txtTransport"); // NOI18N
        txtTransport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTransportActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setText("Save");
        btnSave.setName("btnSave"); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.setName("btnReset"); // NOI18N
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setName("btnDelete"); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setName("btnUpdate"); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExit.setText("Exit");
        btnExit.setName("btnExit"); // NOI18N
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        tbGaji.setModel(new javax.swing.table.DefaultTableModel(
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
        tbGaji.setName("tbGaji"); // NOI18N
        tbGaji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGajiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbGaji);

        cbJabatan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbJabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Direktur Utama", "Manager", "Asistant Manager", "HRD", "Karyawan", "Office Boy" }));
        cbJabatan.setName("cbJabatan"); // NOI18N
        cbJabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbJabatanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(txtNip)
                            .addComponent(txtNama)
                            .addComponent(cbJabatan, 0, 287, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGaber)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(txtGapok, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addComponent(txtTransport))))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGapok, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTransport, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNip, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGaber, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(cbJabatan))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNipActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void txtGaberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGaberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGaberActionPerformed

    private void txtGapokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGapokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGapokActionPerformed

    private void txtTransportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTransportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTransportActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        saveData();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        Reset();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteData();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateData();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitActionPerformed

    private void cbJabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbJabatanActionPerformed
        // TODO add your handling code here:
        loadGaji();
    }//GEN-LAST:event_cbJabatanActionPerformed

    private void tbGajiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGajiMouseClicked
        dataSelect();
        // TODO add your handling code here:
    }//GEN-LAST:event_tbGajiMouseClicked

    
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
            java.util.logging.Logger.getLogger(FormGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormGaji().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbJabatan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbGaji;
    private javax.swing.JTextField txtGaber;
    private javax.swing.JTextField txtGapok;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNip;
    private javax.swing.JTextField txtTransport;
    // End of variables declaration//GEN-END:variables
}
