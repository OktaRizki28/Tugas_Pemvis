/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bimbel;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import koneksi.koneksi;

/**
 *
 * @author admin
 */
public class form_jadwal extends javax.swing.JPanel {
    private Connection conn = new koneksi().connect();
     private DefaultTableModel tabmode;

    /**
     * Creates new form form_jadwal
     */
    public form_jadwal() {
        initComponents();
         b_hapus.setVisible(false);
         datatabel();
         warna_header();
//         tabel_jadwal.setShowVerticalLines(true);
//         tabel_jadwal.setGridColor(java.awt.Color.GRAY);
          
    }
    
        protected void warna_header(){
             tabel_jadwal.setShowVerticalLines(true);
             tabel_jadwal.setGridColor(java.awt.Color.GRAY);
          // Ubah warna header 
        javax.swing.table.JTableHeader header = tabel_jadwal.getTableHeader();
        header.setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
          @Override
         public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
        java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
         c.setBackground(new java.awt.Color(0, 153, 0)); 
        c.setForeground(java.awt.Color.WHITE);           
        setHorizontalAlignment(javax.swing.SwingConstants.LEFT); 
        c.setFont(c.getFont().deriveFont(java.awt.Font.BOLD, 13f));
        return c;
         }
        });  
    }
      protected void datatabel(){
             
        Object [] Baris = {"ID Mata Pelajaran","Mata Pelajaran", "Hari","Jam Pelajaran","Pengajar","Tempat"};
        tabmode = new DefaultTableModel(null,Baris);
        tabel_jadwal.setModel(tabmode);
        String sql = " SELECT * FROM tabel_jadwal";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_mapel");
                String b = hasil.getString("mata_pelajaran");
                String c = hasil.getString("hari");
                String d = hasil.getString("jam");
                String e = hasil.getString("pengajar");
                String f = hasil.getString("tempat");
            
                String[] data={a,b,c,d,e,f};
                tabmode.addRow(data);
       
            } 
            
        }catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage()); 
           
        }
    }
      
    protected void hilang(){
        b_hapus.setVisible(false);
       
    }
     protected void aktif(){
        tid.setEnabled(true);
        mp.setEnabled(true);
        hari.setEnabled(true);
        jam.setEnabled(true);
        pengajar.setEnabled(true);
        tempat.setEditable(true);
        tambah_data.setEnabled(true);
        tid.requestFocus();
    }
       protected void kosong(){
        tid.setText("");
        mp.setSelectedItem("");
        hari.setSelectedItem("");
        jam.setText("");
        pengajar.setText("");
        tempat.setText("");
        
    }
        protected void tambah(){
             String sql = "INSERT INTO tabel_jadwal (id_mapel,mata_pelajaran,hari,jam,pengajar,tempat) VALUES (?,?, ?, ?, ?,?)";
        try {
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1,tid.getText());
        stat.setString(2, mp.getSelectedItem().toString());
        stat.setString(3, hari.getSelectedItem().toString());
        stat.setString(4,jam.getText());
        stat.setString(5,pengajar.getText());
        stat.setString(6,tempat.getText());
         

        stat.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        kosong();
        aktif();
        tid.requestFocus();
        datatabel();
                        
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "Data Gagal Disimpan: " + e.getMessage());
        }
    }
         protected void edit (){
        try {
                 // Memeriksa apakah ID tidak kosong
                if (tid.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ID Guru tidak boleh kosong.");
                    return;
                }

                // Corrected SQL Syntax: Removed comma before WHERE
                String sql = "UPDATE tabel_jadwal SET mata_pelajaran=?, hari=?, jam=?, pengajar=?, tempat=? WHERE id_mapel=?";
                PreparedStatement stat = conn.prepareStatement(sql);

                // Mengatur parameter
                stat.setString(6, tid.getText());
                stat.setString(1, (String) mp.getSelectedItem()); 
                stat.setString(2, (String) hari.getSelectedItem()); 
                stat.setString(3, jam.getText());
                stat.setString(4, pengajar.getText());
                stat.setString(5, tempat.getText());
              

                // Eksekusi update
                int rowsUpdated = stat.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Data Berhasil diubah");
                    kosong(); // Panggil metode untuk mengosongkan form
                    tid.requestFocus(); // Fokus kembali ke field ID
                    datatabel(); // Memperbarui tampilan tabel
                } else {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan untuk diubah.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah: " + e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
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

        panel_jadwal = new javax.swing.JPanel();
        data_jadwal = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        b_tambah = new javax.swing.JButton();
        b_hapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_jadwal = new javax.swing.JTable();
        tcari = new javax.swing.JTextField();
        b_cari = new javax.swing.JButton();
        tambah_data = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        b_save = new javax.swing.JButton();
        b_cancel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tid = new javax.swing.JTextField();
        jam = new javax.swing.JTextField();
        pengajar = new javax.swing.JTextField();
        tempat = new javax.swing.JTextField();
        mp = new javax.swing.JComboBox<>();
        hari = new javax.swing.JComboBox<>();

        setLayout(new java.awt.CardLayout());

        panel_jadwal.setLayout(new java.awt.CardLayout());

        data_jadwal.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel1.setText("JADWAL PELAJARAN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        b_tambah.setBackground(new java.awt.Color(51, 255, 51));
        b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/add.png"))); // NOI18N
        b_tambah.setText("TAMBAH");
        b_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_tambahActionPerformed(evt);
            }
        });

        b_hapus.setBackground(new java.awt.Color(255, 51, 51));
        b_hapus.setText("HAPUS");
        b_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_hapusActionPerformed(evt);
            }
        });

        tabel_jadwal.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_jadwal.setFocusable(false);
        tabel_jadwal.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tabel_jadwal.setRowHeight(38);
        tabel_jadwal.getTableHeader().setReorderingAllowed(false);
        tabel_jadwal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_jadwalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_jadwal);

        b_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/cari.png"))); // NOI18N
        b_cari.setText("Cari");
        b_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout data_jadwalLayout = new javax.swing.GroupLayout(data_jadwal);
        data_jadwal.setLayout(data_jadwalLayout);
        data_jadwalLayout.setHorizontalGroup(
            data_jadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(data_jadwalLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_cari)
                .addGap(40, 40, 40))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE)
        );
        data_jadwalLayout.setVerticalGroup(
            data_jadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_jadwalLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(data_jadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(data_jadwalLayout.createSequentialGroup()
                        .addGroup(data_jadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, data_jadwalLayout.createSequentialGroup()
                        .addGroup(data_jadwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_cari))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1)
                .addGap(6, 6, 6))
        );

        panel_jadwal.add(data_jadwal, "card2");

        tambah_data.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel2.setText("TAMBAH DATA PELAJARAN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(624, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        b_save.setBackground(new java.awt.Color(51, 255, 51));
        b_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/add.png"))); // NOI18N
        b_save.setText("SAVE");
        b_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_saveActionPerformed(evt);
            }
        });

        b_cancel.setBackground(new java.awt.Color(255, 51, 51));
        b_cancel.setText("CANCEL");
        b_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cancelActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("ID Mata Pelajaran");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Mata Pelajaran");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Hari ");

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Jam Pelajaran");

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Pengajar");

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Tempat");

        tid.setBackground(new java.awt.Color(239, 239, 239));
        tid.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        tid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jam.setBackground(new java.awt.Color(239, 239, 239));
        jam.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        pengajar.setBackground(new java.awt.Color(239, 239, 239));
        pengajar.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        pengajar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        tempat.setBackground(new java.awt.Color(239, 239, 239));
        tempat.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        tempat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        mp.setBackground(new java.awt.Color(153, 153, 153));
        mp.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        mp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bahasa Inggris", "Matematika", "Calistung" }));

        hari.setBackground(new java.awt.Color(153, 153, 153));
        hari.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        hari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu" }));

        javax.swing.GroupLayout tambah_dataLayout = new javax.swing.GroupLayout(tambah_data);
        tambah_data.setLayout(tambah_dataLayout);
        tambah_dataLayout.setHorizontalGroup(
            tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambah_dataLayout.createSequentialGroup()
                .addGroup(tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tambah_dataLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(b_save, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tambah_dataLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mp, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tid)
                            .addComponent(jam, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
                            .addComponent(pengajar, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
                            .addComponent(tempat, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
                            .addComponent(hari, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(tambah_dataLayout.createSequentialGroup()
                                .addGroup(tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(33, 33, 33))
        );
        tambah_dataLayout.setVerticalGroup(
            tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambah_dataLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_save, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tid, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mp, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hari, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jam, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pengajar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tempat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_jadwal.add(tambah_data, "card2");

        add(panel_jadwal, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void b_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_tambahActionPerformed
        // TODO add your handling code here:
         if(b_tambah.getText().equals("TAMBAH")){
             panel_jadwal.removeAll();
             panel_jadwal.repaint();
             panel_jadwal.revalidate();
                
            panel_jadwal.add(tambah_data);
            b_save.setText("SAVE");
//            kosong();
//            aktif();
        } else if(b_tambah.getText().equals("EDIT")){
             panel_jadwal.removeAll();
             panel_jadwal.repaint();
             panel_jadwal.revalidate();
                
            panel_jadwal.add(tambah_data);
            b_save.setText("EDIT");
        }
        
    }//GEN-LAST:event_b_tambahActionPerformed

    private void b_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_saveActionPerformed
        // TODO add your handling code here:
         if(b_tambah.getText().equals("TAMBAH")){
         tambah();
     } else if(b_tambah.getText().equals("EDIT")){
         edit();
     }
    }//GEN-LAST:event_b_saveActionPerformed

    private void b_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelActionPerformed
                    panel_jadwal.removeAll();
                    panel_jadwal.repaint();
                    panel_jadwal.revalidate();
                
                    panel_jadwal.add(data_jadwal);
                    b_tambah.setText("TAMBAH");
                    b_tambah.setBackground(Color.green);
                    hilang();
                    kosong();
                    aktif();
    }//GEN-LAST:event_b_cancelActionPerformed

    private void b_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_hapusActionPerformed
         int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
            if (ok==0){
                String sql="delete from tabel_jadwal where id_mapel='"+tid.getText()+"'";
                try {
                    PreparedStatement stat = conn.prepareStatement(sql);
                    stat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "data berhasil dihapus");
                    kosong();
                    tid.requestFocus();
                    datatabel();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data gagal dihapus"+e);
            }
            }
    }//GEN-LAST:event_b_hapusActionPerformed

    private void tabel_jadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_jadwalMouseClicked
        // TODO add your handling code here:
         if (b_tambah.getText().equals("TAMBAH")){
            b_tambah.setText("EDIT");
            b_hapus.setVisible(true);
            b_tambah.setBackground(Color.yellow);
            
            //SELECT DATA
              int bar = tabel_jadwal.getSelectedRow();
        if (bar != -1) {
            String a = tabmode.getValueAt(bar, 0).toString();
            String b = tabmode.getValueAt(bar, 1).toString();
            String c = tabmode.getValueAt(bar, 2).toString();
            String d = tabmode.getValueAt(bar, 3).toString();
            String e = tabmode.getValueAt(bar, 4).toString();
            String f = tabmode.getValueAt(bar, 5).toString();

            tid.setText(a);
            mp.setSelectedItem(b);
            hari.setSelectedItem(c);
            jam.setText(d);
            pengajar.setText(e);
            tempat.setText(f);
            
           
        } else {
            System.out.println("No row selected.");
            JOptionPane.showMessageDialog(null, "Please select a row to edit.");
        }
     }else {
            b_tambah.setText("TAMBAH");
            b_hapus.setVisible(false);
            b_tambah.setBackground(Color.green);
            kosong();
            aktif();
         }
    }//GEN-LAST:event_tabel_jadwalMouseClicked

    private void b_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cariActionPerformed
        // TODO add your handling code here:
        Object [] Baris = {"ID Mata Pelajaran","Mata Pelajaran", "Hari","Jam Pelajaran","Pengajar","Tempat"};
    tabmode = new DefaultTableModel(null, Baris);
    tabel_jadwal.setModel(tabmode);

    // Dapatkan istilah pencarian dari kolom teks
        String searchTerm = tcari.getText();

    
    String sql = "SELECT * FROM tabel_jadwal WHERE mata_pelajaran LIKE ? OR pengajar LIKE ?";

    try {
        PreparedStatement stat = conn.prepareStatement(sql);
        String wildcardTerm = "%" + searchTerm + "%";
        stat.setString(1, wildcardTerm); // For id
        stat.setString(2, wildcardTerm); // For nama

        ResultSet hasil = stat.executeQuery();

        // Hapus hasil sebelumnya
        tabmode.setRowCount(0);

        //Isi tabel dengan hasil
        boolean hasResults = false;
        while (hasil.next()) {
            hasResults = true;
                String a = hasil.getString("id_mapel");
                String b = hasil.getString("mata_pelajaran");
                String c = hasil.getString("hari");
                String d = hasil.getString("jam");
                String e = hasil.getString("pengajar");
                String f = hasil.getString("tempat");
            
                String[] data={a,b,c,d,e,f};
                tabmode.addRow(data);

        }

        if (!hasResults) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_b_cariActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_cancel;
    private javax.swing.JButton b_cari;
    private javax.swing.JButton b_hapus;
    private javax.swing.JButton b_save;
    private javax.swing.JButton b_tambah;
    private javax.swing.JPanel data_jadwal;
    private javax.swing.JComboBox<String> hari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jam;
    private javax.swing.JComboBox<String> mp;
    private javax.swing.JPanel panel_jadwal;
    private javax.swing.JTextField pengajar;
    private javax.swing.JTable tabel_jadwal;
    private javax.swing.JPanel tambah_data;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField tempat;
    private javax.swing.JTextField tid;
    // End of variables declaration//GEN-END:variables
}
