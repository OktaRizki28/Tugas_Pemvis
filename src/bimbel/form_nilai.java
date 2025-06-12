/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bimbel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

/**
 *
 * @author admin
 */
public class form_nilai extends javax.swing.JPanel {
    private Connection conn = new koneksi().connect();
     private DefaultTableModel tabmode;
    
    /**
     * Creates new form form_guru
     */
    public form_nilai() {
        initComponents();
        datatabel();
         b_hapus.setVisible(false);
         warna_header();
        tabel_guru.setShowVerticalLines(true);
        tabel_guru.setGridColor(java.awt.Color.GRAY);
        isiComboBox();
        NamaMapel();
        Tanggal();
        tanggal.setEnabled(false);
      
    }
    
        protected void warna_header(){
          // Ubah warna header 
        javax.swing.table.JTableHeader header = tabel_guru.getTableHeader();
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

    protected void hilang(){
        b_hapus.setVisible(false);
       
    }
      protected void aktif(){
        id_siswa.setEnabled(true);
        nama_siswa.setEnabled(true);
        tambah_data.setEnabled(true);
        nama_mapel.setEnabled(true);
        kelas.setEnabled(true);
        catatan.setEnabled(true);
        id_siswa.requestFocus();
    }
      protected void kosong(){
        id_siswa.setSelectedItem("");   
        nama_siswa.setText("");
        nama_mapel.setSelectedItem("");
        kelas.setText("");
        nilai_tes.setText("");
        catatan.setText("");
        
        
    }
        //Data Tabel
        protected void datatabel(){
             
        Object [] Baris = {"ID Siswa","Nama Siswa","Kelas","ID Mata Pelajaran","Nama Mata Pelajaran",
                            "Tanggal","Nilai Tes","Catatan"};
        tabmode = new DefaultTableModel(null,Baris);
        tabel_guru.setModel(tabmode); 
        String sql = "SELECT n.id_siswa, s.nama_siswa, s.kelas, j.id_mapel, j.mata_pelajaran,"
                    + "n.tanggal, n.nilai_tes, n.catatan " 
                    +"FROM tabel_nilai n " 
                    +"JOIN tabel_siswa s ON s.id_siswa = n.id_siswa " 
                    +"JOIN tabel_jadwal j ON j.id_mapel = n.id_mapel";

        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_siswa");
                String b = hasil.getString("nama_siswa");
                String c = hasil.getString("kelas");
                String d = hasil.getString("id_mapel");
                String e = hasil.getString("mata_pelajaran");
                String f = hasil.getString("tanggal");
                String g = hasil.getString("nilai_tes");
                String h = hasil.getString("catatan");
               
            
                String[] data={a,b,c,d,e,f,g,h};
                tabmode.addRow(data);
       
            } 
            
        }catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage()); 
           
        }
    }
        // Tambah Data
     protected void tambah(){
    String sql = "INSERT INTO tabel_nilai (id_siswa, id_mapel, tanggal, nilai_tes, catatan) VALUES (?, ?, ?, ?, ?)";
    try {
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, (String) id_siswa.getSelectedItem());
        stat.setString(2, id_mapel.getText());
        stat.setString(3, tanggal.getText());
      
        String nilai = nilai_tes.getText().replace(",", "."); // ubah koma jadi titik
        try {
            Double.parseDouble(nilai); // cek apakah input valid
            stat.setString(4, nilai);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Nilai Tes harus berupa angka!");
            return;
        }
        
        stat.setString(5, catatan.getText());

        stat.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        kosong();
        aktif();
        id_siswa.requestFocus();
        datatabel();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan: " + e.getMessage());
    }
}

        
        
    protected void edit (){
        try {
                 // Memeriksa apakah ID tidak kosong
                if (nilai_tes.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nilai Tes tidak boleh kosong.");
                    return;
                }

                // Corrected SQL Syntax: Removed comma before WHERE
                String sql = "UPDATE tabel_nilai SET id_mapel=?, tanggal=?, nilai_tes=?,  catatan=?, WHERE id_siswa=?";
                PreparedStatement stat = conn.prepareStatement(sql);

                // Mengatur parameter
                stat.setString(8, (String) id_siswa.getSelectedItem());
                stat.setString(1, nama_siswa.getText());
                stat.setString(2, kelas.getText());
                stat.setString(3, (String) nama_mapel.getSelectedItem());
                stat.setString(4,id_mapel.getText());
                stat.setString(5,tanggal.getText());
                stat.setString(6,nilai_tes.getText());
                stat.setString(7,catatan.getText());
                
                // Eksekusi update
                int rowsUpdated = stat.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Data Berhasil diubah");
                    kosong(); // Panggil metode untuk mengosongkan form
                    id_siswa.requestFocus(); // Fokus kembali ke field ID
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

        panel_guru = new javax.swing.JPanel();
        data_guru = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        b_tambah = new javax.swing.JButton();
        t_cari = new javax.swing.JTextField();
        b_cari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_guru = new javax.swing.JTable();
        b_hapus = new javax.swing.JButton();
        tambah_data = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        b_save = new javax.swing.JButton();
        b_cancel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        id_siswa = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        nama_siswa = new javax.swing.JTextField();
        nama_mapel = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        kelas = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tanggal = new javax.swing.JTextField();
        nilai_tes = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        catatan = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        id_mapel = new javax.swing.JTextField();

        setLayout(new java.awt.CardLayout());

        panel_guru.setLayout(new java.awt.CardLayout());

        data_guru.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel1.setText("DATA GURU");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        b_tambah.setBackground(new java.awt.Color(51, 255, 51));
        b_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/add.png"))); // NOI18N
        b_tambah.setText("TAMBAH");
        b_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_tambahActionPerformed(evt);
            }
        });

        t_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_cariActionPerformed(evt);
            }
        });

        b_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/cari.png"))); // NOI18N
        b_cari.setText("Cari");
        b_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cariActionPerformed(evt);
            }
        });

        tabel_guru.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tabel_guru.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_guru.setGridColor(new java.awt.Color(0, 0, 0));
        tabel_guru.setRowHeight(38);
        tabel_guru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_guruMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_guru);

        b_hapus.setBackground(new java.awt.Color(255, 51, 51));
        b_hapus.setText("HAPUS");
        b_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_hapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout data_guruLayout = new javax.swing.GroupLayout(data_guru);
        data_guru.setLayout(data_guruLayout);
        data_guruLayout.setHorizontalGroup(
            data_guruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(data_guruLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(b_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 401, Short.MAX_VALUE)
                .addComponent(t_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b_cari)
                .addGap(31, 31, 31))
            .addComponent(jScrollPane1)
        );
        data_guruLayout.setVerticalGroup(
            data_guruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_guruLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(data_guruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(data_guruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(data_guruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(t_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b_cari)))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE))
        );

        panel_guru.add(data_guru, "card2");

        tambah_data.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel2.setText("DATA GURU");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
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
        jLabel3.setText("ID Siswa");

        id_siswa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        id_siswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_siswaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Nama Siswa");

        nama_siswa.setBackground(new java.awt.Color(239, 239, 239));
        nama_siswa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        nama_mapel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        nama_mapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_mapelActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Kelas");

        kelas.setBackground(new java.awt.Color(239, 239, 239));
        kelas.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Nama Mapel");

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Tanggal");

        tanggal.setBackground(new java.awt.Color(239, 239, 239));
        tanggal.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        nilai_tes.setBackground(new java.awt.Color(239, 239, 239));
        nilai_tes.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        nilai_tes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nilai_tesActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Nilai Tes");

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Catatan");

        catatan.setColumns(20);
        catatan.setRows(5);
        catatan.setBorder(new javax.swing.border.MatteBorder(null));
        jScrollPane2.setViewportView(catatan);

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("ID Mapel");

        id_mapel.setBackground(new java.awt.Color(239, 239, 239));
        id_mapel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout tambah_dataLayout = new javax.swing.GroupLayout(tambah_data);
        tambah_data.setLayout(tambah_dataLayout);
        tambah_dataLayout.setHorizontalGroup(
            tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(tambah_dataLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kelas)
                    .addComponent(id_siswa, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nama_siswa)
                    .addComponent(nama_mapel, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nilai_tes)
                    .addComponent(tanggal)
                    .addComponent(id_mapel)
                    .addGroup(tambah_dataLayout.createSequentialGroup()
                        .addGroup(tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addGroup(tambah_dataLayout.createSequentialGroup()
                                .addComponent(b_save, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(b_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(0, 806, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tambah_dataLayout.setVerticalGroup(
            tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tambah_dataLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_save, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nama_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nama_mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id_mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nilai_tes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel_guru.add(tambah_data, "card2");

        add(panel_guru, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void t_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_cariActionPerformed

    private void b_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_tambahActionPerformed
        // TODO add your handling code here:
         if(b_tambah.getText().equals("TAMBAH")){
             panel_guru.removeAll();
             panel_guru.repaint();
             panel_guru.revalidate();
                
            panel_guru.add(tambah_data);
            b_save.setText("SAVE");
            kosong();
            aktif();
        } else if(b_tambah.getText().equals("EDIT")){
             panel_guru.removeAll();
             panel_guru.repaint();
             panel_guru.revalidate();
                
            panel_guru.add(tambah_data);
            b_save.setText("EDIT");
        }
        

    }//GEN-LAST:event_b_tambahActionPerformed

    private void b_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_hapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
            if (ok==0){
                String sql="delete from tabel_nilai where id_siswa='"+id_siswa.getSelectedItem()+"'";
                try {
                    PreparedStatement stat = conn.prepareStatement(sql);
                    stat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "data berhasil dihapus");
                    kosong();
                    id_siswa.requestFocus();
                    datatabel();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data gagal dihapus"+e);
            }
            }
    }//GEN-LAST:event_b_hapusActionPerformed

    private void tabel_guruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_guruMouseClicked
    if (b_tambah.getText().equals("TAMBAH")){
            b_tambah.setText("EDIT");
            b_hapus.setVisible(true);
            b_tambah.setBackground(Color.yellow);
         
            
            //SELECT DATA
            int bar = tabel_guru.getSelectedRow();
        if (bar != -1) {
           
            String a = tabmode.getValueAt(bar, 0).toString();
            String b = tabmode.getValueAt(bar, 1).toString();
            String c = tabmode.getValueAt(bar, 2).toString();
            String d = tabmode.getValueAt(bar, 3).toString();
            String e = tabmode.getValueAt(bar, 4).toString();
            String f = tabmode.getValueAt(bar, 5).toString();
            String g = tabmode.getValueAt(bar, 6).toString();
            String h = tabmode.getValueAt(bar, 7).toString();

            id_siswa.setSelectedItem(a);
            nama_siswa.setText(b);
            kelas.setText(c);
            nama_mapel.setSelectedItem(d);
            id_mapel.setText(e);
            tanggal.setText(f);
            nilai_tes.setText(g);
            catatan.setText(h);
             
           ;
        } else {
            System.out.println("No row selected.");
            JOptionPane.showMessageDialog(null, "Please select a row to edit.");
        }
    }else{
            b_tambah.setText("TAMBAH");
            b_hapus.setVisible(false);
            b_tambah.setBackground(Color.green);
    }
        
    }//GEN-LAST:event_tabel_guruMouseClicked

    private void b_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cariActionPerformed
   Object [] Baris = {"ID Siswa","Nama Siswa","Kelas","ID Mata Pelajaran","Nama Mata Pelajaran","Catatan"};
        tabmode = new DefaultTableModel(null,Baris);
        tabel_guru.setModel(tabmode);

    // Dapatkan istilah pencarian dari kolom teks
        String searchTerm = t_cari.getText();

    
   String sql = "SELECT * FROM tabel_nilai " +
             "JOIN tabel_siswa ON tabel_nilai.id_siswa = tabel_siswa.id_siswa " +
             "WHERE id_nilai LIKE ? OR nama_siswa LIKE ?";

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
                String a = hasil.getString("id");
                String b = hasil.getString("nama");
                String c = hasil.getString("telp");
                String d = hasil.getString("email_guru");
                String e = hasil.getString("jk");
                String f = hasil.getString("pendidikan");
                String g = hasil.getString("alamat");
            
                String[] data={a,b,c,d,e,f,g};
                tabmode.addRow(data);
        }

        if (!hasResults) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_b_cariActionPerformed

    private void nama_mapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_mapelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nama_mapelActionPerformed

    private void id_siswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_siswaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_siswaActionPerformed

    private void b_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelActionPerformed
        panel_guru.removeAll();
        panel_guru.repaint();
        panel_guru.revalidate();

        panel_guru.add(data_guru);
        b_tambah.setText("TAMBAH");
        b_tambah.setBackground(Color.green);
        hilang();
    }//GEN-LAST:event_b_cancelActionPerformed

    private void b_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_saveActionPerformed
        if(b_tambah.getText().equals("TAMBAH")){
            tambah();
        } else if(b_tambah.getText().equals("EDIT")){
            edit();
        }
    }//GEN-LAST:event_b_saveActionPerformed

    private void nilai_tesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nilai_tesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nilai_tesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_cancel;
    private javax.swing.JButton b_cari;
    private javax.swing.JButton b_hapus;
    private javax.swing.JButton b_save;
    private javax.swing.JButton b_tambah;
    private javax.swing.JTextArea catatan;
    private javax.swing.JPanel data_guru;
    private javax.swing.JTextField id_mapel;
    private javax.swing.JComboBox<String> id_siswa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField kelas;
    private javax.swing.JComboBox<String> nama_mapel;
    private javax.swing.JTextField nama_siswa;
    private javax.swing.JTextField nilai_tes;
    private javax.swing.JPanel panel_guru;
    private javax.swing.JTextField t_cari;
    private javax.swing.JTable tabel_guru;
    private javax.swing.JPanel tambah_data;
    private javax.swing.JTextField tanggal;
    // End of variables declaration//GEN-END:variables

    
       private void isiComboBox() {
    try {
        Connection conn = new koneksi().connect();
        String query = "SELECT id_siswa, nama_siswa FROM tabel_siswa"; 
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        
        id_siswa.removeAllItems(); 
        id_siswa.addItem(""); // item kosong sebagai default

        while (rs.next()) {
            id_siswa.addItem(rs.getString("id_siswa"));
        }

        // listener untuk combo box
        id_siswa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedId = (String) id_siswa.getSelectedItem();
                if (selectedId != null && !selectedId.isEmpty()) {
                    isiNamaSiswa(selectedId);
                } else {
                    nama_siswa.setText("--Pilih Nama--"); 
                }
            }
        });

        rs.close();
        pst.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal isi combo box: " + e.getMessage());
    }
}

// mengisi nama siswa berdasarkan ID
private void isiNamaSiswa(String id){
    try {
        Connection conn = new koneksi().connect();
        String query = "SELECT nama_siswa FROM tabel_siswa WHERE id_siswa = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            nama_siswa.setText(rs.getString("nama_siswa"));
        } else {
            nama_siswa.setText("");
        }

        rs.close();
        pst.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal mengambil Nama Siswa: " + e.getMessage());
    }
}

    //Combo Box Mata Pelajaran
    private void NamaMapel() {
    try {
        Connection conn = new koneksi().connect();
        String query = "SELECT id_mapel, mata_pelajaran FROM tabel_jadwal"; 
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        
        nama_mapel.removeAllItems(); 
        nama_mapel.addItem(""); // item kosong sebagai default

        while (rs.next()) {
            nama_mapel.addItem(rs.getString("mata_pelajaran"));
        }

        // listener untuk combo box
        nama_mapel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedId = (String) nama_mapel.getSelectedItem();
                if (selectedId != null && !selectedId.isEmpty()) {
                    IdMapel(selectedId);
                } else {
                    id_mapel.setText(""); 
                }
            }
        });

        rs.close();
        pst.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal isi combo box: " + e.getMessage());
    }
}

    // mengisi nama Mapel berdasarkan ID
    private void IdMapel(String id){
    try {
        Connection conn = new koneksi().connect();
        String query = "SELECT id_mapel FROM tabel_jadwal WHERE mata_pelajaran = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            id_mapel.setText(rs.getString("id_mapel"));
        } else {
            id_mapel.setText("");
        }

        rs.close();
        pst.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal mengambil Mata Pelajaran: " + e.getMessage());
    }
}
    protected void Tanggal(){
        java.util.Date now = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("dd-MM-yyyy");
        tanggal.setText(kal.format(now));
        tanggal.setEnabled(false);
    }
}
