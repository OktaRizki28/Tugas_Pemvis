/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bimbel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

/**
 *
 * @author admin
 */
public class pembayaran_siswa extends javax.swing.JPanel {
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    

    /**
     * Creates new form form_pembayaran
     */
    public pembayaran_siswa() {
        initComponents();
        otomatis();
        datatabel();
        tabelSementara();
        detailtabel();
         b_hapus.setVisible(false);
         cancel.setVisible(false);
         pn_detail.setVisible(false);
         warna_header();
         warna_header1();
         warna_header2();
//         pn_detail.setVisible(false);
         
         
       
    }
    protected void mati(){
        tagihan.setEnabled(false);
        tunggakan.setEnabled(false);
        status.setEnabled(false);
        nobayar.setEnabled(false);
        t_tanggal.setEnabled(false);
        nama_siswa.setEnabled(false);
    }
    protected void Kosong(){
        lama.setText("");
        nama_siswa.setText("");
        tagihan.setText("");
        nominal.setText("");
        tunggakan.setText("");
        status.setText("");
        
    }
    //Tabel
    protected void datatabel(){
             
        Object [] Baris = {"No Transaksi","ID Siswa", "Nama Siswa","Jenis Kelas","Tanggal",
            "Tagihan","Jumlah Bayar","Tunggakan","Status"};
        tabmode = new DefaultTableModel(null,Baris);
        tabelbayar.setModel(tabmode);
//        tabel_sementara.setModel(tabmode);
        
         String sql = "SELECT p.no_pembayaran, p.id_siswa, s.nama_siswa, p.Jenis_kelas, "
                    + "p.tgl, p.Tagihan, p.nominal_bayar, p.tunggakan, p.status_pembayaran "
                    + "FROM tabel_pembayaran p "
                    + "JOIN tabel_siswa s ON p.id_siswa = s.id_siswa "
                    +"ORDER BY no_pembayaran ASC";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("no_pembayaran");
                String b = hasil.getString("id_siswa");
                String c = hasil.getString("nama_siswa"); //dari tabel siswa
                String d = hasil.getString("Jenis_kelas");
//                String e = hasil.getString("lama_belajar");
                String e = hasil.getString("tgl");
                String f = hasil.getString("Tagihan");
                String g = hasil.getString("nominal_bayar");
                String h = hasil.getString("tunggakan");
                String i = hasil.getString("status_pembayaran");
            
                String[] data={a,b,c,d,e,f,g,h,i,};
                tabmode.addRow(data);
       
            } 
            
        }catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage()); 
           
        }
    }
    
      protected void tabelSementara(){
             
        Object [] Baris = {"No ","No Pembayaran","ID Siswa","Jenis Kelas","Lama Belajar","Tagihan"};
        tabmode = new DefaultTableModel(null,Baris);
        tabel_bayar.setModel(tabmode);
        
         String sql = "SELECT * FROM pembayaran_sementara ORDER BY id_pem ASC";

        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_pem");
                String b = hasil.getString("no_pembayaran");
                String c = hasil.getString("id_siswa"); 
                String d = hasil.getString("Jenis_kelas");
                String e = hasil.getString("lama_belajar");
                String f = hasil.getString("tagihan");
                
            
                String[] data={a,b,c,d,e,f};
                tabmode.addRow(data);
       
            } 
            
        }catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage()); 
           
        }
    }
    protected void detailtabel(){
             
        Object [] Baris = {"No Pembayaran","Jenis Kelas","Lama Belajar","Tagihan"};
        tabmode = new DefaultTableModel(null,Baris);
        detail_pembayaran.setModel(tabmode);
        
         String sql = "SELECT * FROM detail_pembayaran ORDER BY id_detail ASC";

        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
               
                String a = hasil.getString("no_pembayaran"); 
                String b = hasil.getString("Jenis_kelas");
                String c = hasil.getString("lama_belajar");
                String d = hasil.getString("tagihan");
                
            
                String[] data={a,b,c,d};
                tabmode.addRow(data);
       
            } 
            
        }catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage()); 
           
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

        Pembayaran = new javax.swing.JPanel();
        Data_Pembayaran = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        b_cari = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        b_tambah = new javax.swing.JButton();
        b_hapus = new javax.swing.JButton();
        tcari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelbayar = new javax.swing.JTable();
        pn_detail = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        detail_pembayaran = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Pembayaran_siswa = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cancel2 = new javax.swing.JButton();
        b_tambah2 = new javax.swing.JButton();
        tglTransaksi = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        nobayar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        t_tanggal = new javax.swing.JTextField();
        t_waktu = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        nama_siswa = new javax.swing.JTextField();
        id_siswa = new javax.swing.JComboBox<>();
        lama = new javax.swing.JTextField();
        tagihan = new javax.swing.JTextField();
        mapel = new javax.swing.JComboBox<>();
        Tambah = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_bayar = new javax.swing.JTable();
        b_hapus1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        nominal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tunggakan = new javax.swing.JTextField();
        status = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        totalTagihan = new javax.swing.JLabel();
        Hitung1 = new javax.swing.JButton();
        Total = new javax.swing.JButton();

        setLayout(new java.awt.CardLayout());

        Pembayaran.setLayout(new java.awt.CardLayout());

        Data_Pembayaran.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel2.setText("DATA PEMBAYARAN SISWA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        b_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/cari.png"))); // NOI18N
        b_cari.setText("Cari");
        b_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cariActionPerformed(evt);
            }
        });

        cancel.setBackground(new java.awt.Color(255, 255, 153));
        cancel.setText("BATAL");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

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

        tabelbayar.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelbayar.setFocusable(false);
        tabelbayar.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tabelbayar.setRowHeight(38);
        tabelbayar.getTableHeader().setReorderingAllowed(false);
        tabelbayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelbayarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelbayar);

        pn_detail.setBackground(new java.awt.Color(255, 255, 255));
        pn_detail.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        detail_pembayaran.setModel(new javax.swing.table.DefaultTableModel(
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
        detail_pembayaran.setFocusable(false);
        detail_pembayaran.setIntercellSpacing(new java.awt.Dimension(0, 0));
        detail_pembayaran.setRowHeight(38);
        detail_pembayaran.getTableHeader().setReorderingAllowed(false);
        detail_pembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                detail_pembayaranMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(detail_pembayaran);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/close_window_96px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel3.setText("DETAIL PEMBAYARAN");

        javax.swing.GroupLayout pn_detailLayout = new javax.swing.GroupLayout(pn_detail);
        pn_detail.setLayout(pn_detailLayout);
        pn_detailLayout.setHorizontalGroup(
            pn_detailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
            .addGroup(pn_detailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );
        pn_detailLayout.setVerticalGroup(
            pn_detailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_detailLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(pn_detailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout Data_PembayaranLayout = new javax.swing.GroupLayout(Data_Pembayaran);
        Data_Pembayaran.setLayout(Data_PembayaranLayout);
        Data_PembayaranLayout.setHorizontalGroup(
            Data_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
            .addGroup(Data_PembayaranLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_cari)
                .addContainerGap())
            .addComponent(pn_detail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Data_PembayaranLayout.setVerticalGroup(
            Data_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Data_PembayaranLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(Data_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Data_PembayaranLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Data_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Data_PembayaranLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(Data_PembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_cari))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(pn_detail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Pembayaran.add(Data_Pembayaran, "card2");

        Pembayaran_siswa.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel6.setText("DATA PEMBAYARAN SISWA");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(685, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cancel2.setBackground(new java.awt.Color(255, 255, 153));
        cancel2.setText("BATAL");
        cancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel2ActionPerformed(evt);
            }
        });

        b_tambah2.setBackground(new java.awt.Color(51, 255, 51));
        b_tambah2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/add.png"))); // NOI18N
        b_tambah2.setText("TAMBAH");
        b_tambah2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_tambah2ActionPerformed(evt);
            }
        });

        tglTransaksi.setBackground(new java.awt.Color(0, 153, 0));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tanggal Bayar");

        nobayar.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("No Transaksi");

        t_tanggal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout tglTransaksiLayout = new javax.swing.GroupLayout(tglTransaksi);
        tglTransaksi.setLayout(tglTransaksiLayout);
        tglTransaksiLayout.setHorizontalGroup(
            tglTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tglTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tglTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(nobayar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tglTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tglTransaksiLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tglTransaksiLayout.createSequentialGroup()
                        .addComponent(t_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        tglTransaksiLayout.setVerticalGroup(
            tglTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tglTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tglTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tglTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nobayar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        t_waktu.setBackground(new java.awt.Color(0, 153, 0));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nama Siswa");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ID Siswa");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Lama Belajar");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Jenis Bimbel");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tagihan");

        id_siswa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        mapel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Tambah.setBackground(new java.awt.Color(255, 255, 255));
        Tambah.setText("TAMBAH");
        Tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout t_waktuLayout = new javax.swing.GroupLayout(t_waktu);
        t_waktu.setLayout(t_waktuLayout);
        t_waktuLayout.setHorizontalGroup(
            t_waktuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(t_waktuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(t_waktuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(t_waktuLayout.createSequentialGroup()
                        .addGroup(t_waktuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(id_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(t_waktuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(t_waktuLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(60, 60, 60))
                            .addGroup(t_waktuLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(nama_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 18, Short.MAX_VALUE))))
                    .addComponent(lama)
                    .addComponent(tagihan)
                    .addComponent(mapel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(t_waktuLayout.createSequentialGroup()
                        .addGroup(t_waktuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(0, 278, Short.MAX_VALUE)))
                .addContainerGap())
        );
        t_waktuLayout.setVerticalGroup(
            t_waktuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(t_waktuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(t_waktuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(t_waktuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nama_siswa, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(id_siswa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(6, 6, 6)
                .addComponent(lama, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabel_bayar.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_bayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_bayarMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_bayar);

        b_hapus1.setBackground(new java.awt.Color(255, 51, 51));
        b_hapus1.setText("HAPUS");
        b_hapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_hapus1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 0));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Total Bayar");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tunggakan");

        tunggakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tunggakanActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Status Bayar");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Nominal Bayar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(totalTagihan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(totalTagihan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Hitung1.setBackground(new java.awt.Color(255, 255, 255));
        Hitung1.setText("HITUNG ");
        Hitung1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hitung1ActionPerformed(evt);
            }
        });

        Total.setText("TOTAL");
        Total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)
                            .addComponent(tunggakan, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                            .addComponent(status))
                        .addGap(162, 162, 162))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Hitung1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nominal, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Total)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Total, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(nominal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tunggakan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(1, 1, 1)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(Hitung1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout Pembayaran_siswaLayout = new javax.swing.GroupLayout(Pembayaran_siswa);
        Pembayaran_siswa.setLayout(Pembayaran_siswaLayout);
        Pembayaran_siswaLayout.setHorizontalGroup(
            Pembayaran_siswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Pembayaran_siswaLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(b_tambah2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_hapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Pembayaran_siswaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Pembayaran_siswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 417, Short.MAX_VALUE)
                    .addComponent(t_waktu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tglTransaksi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2))
        );
        Pembayaran_siswaLayout.setVerticalGroup(
            Pembayaran_siswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pembayaran_siswaLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Pembayaran_siswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_tambah2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_hapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Pembayaran_siswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pembayaran_siswaLayout.createSequentialGroup()
                        .addComponent(tglTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(t_waktu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Pembayaran.add(Pembayaran_siswa, "card2");

        add(Pembayaran, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void b_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cariActionPerformed
        Object [] Baris = {"No Transaksi","ID Siswa", "Nama Siswa","Jenis Kelas","Lama Belajar","Tanggal Pembayaran",
            "Tagihan","Jumlah Bayar","Tunggakan","Status Pembayaran"};
        tabmode = new DefaultTableModel(null,Baris);
        tabelbayar.setModel(tabmode);
//        TablePembayaran.setModel(tabmode);
        
            // Dapatkan istilah pencarian dari kolom teks
                String searchTerm = tcari.getText();
        
        
           String sql = "SELECT p.no_pembayaran, p.id_siswa, s.nama_siswa, p.Jenis_kelas, p.tgl, "
                        + "p.Tagihan, p.nominal_bayar, p.tunggakan, p.status_pembayaran "
                        + "FROM tabel_pembayaran p "
                        + "JOIN tabel_siswa s ON p.id_siswa = s.id_siswa "
                        + "WHERE s.id_siswa LIKE ? OR s.nama_siswa LIKE ?";
        
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
                       while(hasil.next()){
                           hasResults = true;
                            String a = hasil.getString("no_pembayaran");
                            String b = hasil.getString("id_siswa");
                            String c = hasil.getString("nama_siswa");
                            String d = hasil.getString("Jenis_kelas");
                            String e = hasil.getString("tgl");
                            String f = hasil.getString("Tagihan");
                            String g = hasil.getString("nominal_bayar");
                            String h = hasil.getString("tunggakan");
                            String i = hasil.getString("status_pembayaran");
            
                        String[] data={a,b,c,d,e,f,g,h,i};
                        tabmode.addRow(data);
                
                        }
            
                    if (!hasResults) {
                            JOptionPane.showMessageDialog(null, "Data tidak ditemukan.");
                        }
            
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
    }//GEN-LAST:event_b_cariActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        b_tambah.setEnabled(true);
        b_hapus.setVisible(false);
        cancel.setVisible(false);
        b_tambah.setBackground(Color.green);
//       kosong();
//        aktif();
        otomatis();
    }//GEN-LAST:event_cancelActionPerformed

    private void b_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_tambahActionPerformed
        // TODO add your handling code here
            Pembayaran.removeAll();
            Pembayaran.repaint();
            Pembayaran.revalidate();

            Pembayaran.add(Pembayaran_siswa);
            mati();
            Tanggal();
            isiComboBox();
            mapel();
            Kosong();
    }//GEN-LAST:event_b_tambahActionPerformed

    private void tabelbayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelbayarMouseClicked
        tabelPembayaranClicked();
        pn_detail.setVisible(true);
//        b_hapus.setVisible(true);
//        cancel.setVisible(true);
    }//GEN-LAST:event_tabelbayarMouseClicked

    private void cancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel2ActionPerformed
        // TODO add your handling code here:
         Pembayaran.removeAll();
         Pembayaran.repaint();
        Pembayaran.revalidate();
                
        Pembayaran.add(Data_Pembayaran);
        Kosong();
        otomatis();
        datatabel();
        detailtabel();
    }//GEN-LAST:event_cancel2ActionPerformed

    private void b_tambah2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_tambah2ActionPerformed
    if(totalTagihan.getText().equals("") || nominal.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "ZAF JUNIOR", JOptionPane.INFORMATION_MESSAGE);
    } else {
        try {
            Statement s = conn.createStatement();

            // Simpan ke tabel_pembayaran sekali saja
            String sqlPembayaran = "INSERT INTO tabel_pembayaran "
                    + "(no_pembayaran, id_siswa, Jenis_kelas,  tgl, Tagihan, nominal_bayar, tunggakan, status_pembayaran) "
                    + "VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement stat = conn.prepareStatement(sqlPembayaran);
            stat.setString(1, nobayar.getText());
            stat.setString(2, id_siswa.getSelectedItem().toString());
            stat.setString(3, mapel.getSelectedItem().toString());
//            stat.setInt(4, Integer.parseInt(lama.getText()));
            stat.setString(4, t_tanggal.getText());
            stat.setDouble(5, Double.parseDouble(totalTagihan.getText()));
            stat.setDouble(6, Double.parseDouble(nominal.getText()));
            stat.setDouble(7, Double.parseDouble(tunggakan.getText()));
            stat.setString(8, status.getText());
            stat.executeUpdate();
            
            datatabel();

           
            String sql = "SELECT * FROM pembayaran_sementara";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                String sqlDetail = "INSERT INTO detail_pembayaran (no_pembayaran, jenis_kelas, lama_belajar, tagihan) VALUES (?,?,?,?)";
                PreparedStatement stat2 = conn.prepareStatement(sqlDetail);
                stat2.setString(1, r.getString("no_pembayaran"));
                stat2.setString(2, r.getString("jenis_kelas"));
                stat2.setInt(3, r.getInt("lama_belajar"));
                stat2.setDouble(4, r.getDouble("tagihan"));
                stat2.executeUpdate();
                detailtabel();
            }
            r.close();
            s.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");

        } catch (SQLException e) {
            e.printStackTrace();  // Gunakan ini untuk debug error sebenarnya
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage());
        } finally {
            try {
                // Hapus isi tabel pembayaran_sementara
                String sqlTruncate = "TRUNCATE pembayaran_sementara";
                PreparedStatement pst = conn.prepareStatement(sqlTruncate);
                pst.execute();

                JOptionPane.showMessageDialog(null, "TRANSAKSI SELESAI", "ZAF JUNIOR", JOptionPane.INFORMATION_MESSAGE);

                // Reset form
                nobayar.setText("");
                id_siswa.setSelectedItem("");
                lama.setText("");
                otomatis();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Gagal menghapus sementara: " + e.getMessage());
            }
        }
}

    }//GEN-LAST:event_b_tambah2ActionPerformed

    private void TambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TambahActionPerformed
        // TODO add your handling code here:
        if(nobayar.getText().equals("") ||t_tanggal.getText().equals("") || id_siswa.getSelectedItem().equals("")|| nama_siswa.getText().equals("")|| lama.getText().equals("")|| mapel.getSelectedItem().equals("")|| tagihan.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", " Zaf Junior", JOptionPane.INFORMATION_MESSAGE);

        }else{
             String sql = "INSERT INTO pembayaran_sementara VALUES (?,?, ?, ?, ?, ?)";
        try {
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1,null);
        stat.setString(2,nobayar.getText());
        stat.setString(3, (String) id_siswa.getSelectedItem());
        stat.setString(4, (String) mapel.getSelectedItem());
        stat.setString(5,lama.getText());
        stat.setString(6,tagihan.getText());
        
        stat.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
//        kosong();
//        aktif();
        nobayar.requestFocus();
        tabelSementara();
                        
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "Data Gagal Disimpan: " + e.getMessage());
        }
        }
  
    }//GEN-LAST:event_TambahActionPerformed

    private void tunggakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tunggakanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tunggakanActionPerformed

    private void tabel_bayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_bayarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_bayarMouseClicked

    private void b_hapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_hapus1ActionPerformed
        // TODO add your handling code here:
         int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            String sql="delete from tabel_pembayaran where no_pembayaran='"+nobayar.getText()+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "data berhasil dihapus");
//                kosong();
                nobayar.requestFocus();
                datatabel();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data gagal dihapus"+e);
            }
        }
    }//GEN-LAST:event_b_hapus1ActionPerformed

    private void b_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_hapusActionPerformed
       int ok = JOptionPane.showConfirmDialog(null, "Hapus data ini?", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            try {
                
                
                // Hapus dari detail_pembayaran dulu
                String sqlDetail = "DELETE FROM detail_pembayaran WHERE no_pembayaran = ?";
                PreparedStatement statDetail = conn.prepareStatement(sqlDetail);
                statDetail.setString(1, nobayar.getText());
                statDetail.executeUpdate();

                // Lalu hapus dari tabel_pembayaran
                String sqlPembayaran = "DELETE FROM tabel_pembayaran WHERE no_pembayaran = ?";
                PreparedStatement statPembayaran = conn.prepareStatement(sqlPembayaran);
                statPembayaran.setString(1, nobayar.getText());
                statPembayaran.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                nobayar.requestFocus();
                datatabel(); 
                detailtabel(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal dihapus: " + e.getMessage());
            }
        }

    }//GEN-LAST:event_b_hapusActionPerformed

    private void detail_pembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detail_pembayaranMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_detail_pembayaranMouseClicked

    private void Hitung1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hitung1ActionPerformed
        // TODO add your handling code here:
        
        double Nominal = Double.parseDouble(nominal.getText());
        double Tagihan = Double.parseDouble(totalTagihan.getText());
        double Tunggakan = Nominal - Tagihan; 
        tunggakan.setText(String.valueOf(Tunggakan));
        
        if (Tunggakan == 0){
            status.setText("Lunas"); 
        } else if (Tunggakan <= -1) {
            status.setText("Bulum Lunas");   
        } else {
            status.setText(" Kembalian, Rp." +Tunggakan);
        }
    }//GEN-LAST:event_Hitung1ActionPerformed

    private void TotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalActionPerformed
        // TODO add your handling code here:
         try {
            
            //Connection c = koneksi.getKoneksi();
            Statement s = conn.createStatement();

            String sql = "SELECT SUM(`tagihan`) AS total FROM pembayaran_sementara";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                totalTagihan.setText(r.getString(""+"total"));

            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }//GEN-LAST:event_TotalActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
                 pn_detail.setVisible(false);
//                 pn_detail.repaint();
//                 pn_detail.revalidate();
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Data_Pembayaran;
    private javax.swing.JButton Hitung1;
    private javax.swing.JPanel Pembayaran;
    private javax.swing.JPanel Pembayaran_siswa;
    private javax.swing.JButton Tambah;
    private javax.swing.JButton Total;
    private javax.swing.JButton b_cari;
    private javax.swing.JButton b_hapus;
    private javax.swing.JButton b_hapus1;
    private javax.swing.JButton b_tambah;
    private javax.swing.JButton b_tambah2;
    private javax.swing.JButton cancel;
    private javax.swing.JButton cancel2;
    private javax.swing.JTable detail_pembayaran;
    private javax.swing.JComboBox<String> id_siswa;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField lama;
    private javax.swing.JComboBox<String> mapel;
    private javax.swing.JTextField nama_siswa;
    private javax.swing.JTextField nobayar;
    private javax.swing.JTextField nominal;
    private javax.swing.JPanel pn_detail;
    private javax.swing.JTextField status;
    private javax.swing.JTextField t_tanggal;
    private javax.swing.JPanel t_waktu;
    private javax.swing.JTable tabel_bayar;
    private javax.swing.JTable tabelbayar;
    private javax.swing.JTextField tagihan;
    private javax.swing.JTextField tcari;
    private javax.swing.JPanel tglTransaksi;
    private javax.swing.JLabel totalTagihan;
    private javax.swing.JTextField tunggakan;
    // End of variables declaration//GEN-END:variables
public void otomatis() {
    try {
        Connection conn = new koneksi().connect();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX(no_pembayaran) FROM tabel_pembayaran");

        if (rs.next()) {
            String lastId = rs.getString(1); // Ambil ID terakhir

            if (lastId == null || lastId.equals("")) {
                nobayar.setText("BYR001");
            } else {
                // Misal ID seperti "BYR005"
                String angkaStr = lastId.replaceAll("[^0-9]", ""); // Ambil hanya angka
                int angka = Integer.parseInt(angkaStr) + 1;
                String newId = String.format("BYR%03d", angka); // Hasil: BYR006, dst.
                nobayar.setText(newId);
            }
        } else {
            nobayar.setText("BYR001");
        }
        
        nobayar.setEnabled(false);
        rs.close();
        st.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: >> Ada di" + e.getMessage());
    }
}
//inputan tanggal
    protected void Tanggal(){
        java.util.Date now = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("dd-MM-yyyy");
        t_tanggal.setText(kal.format(now));
        t_tanggal.setEnabled(false);
    }
    //combo box untuk siswa
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
                    nama_siswa.setText(""); 
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
private void isiNamaSiswa(String id) {
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
        JOptionPane.showMessageDialog(null, "Gagal mengambil nama siswa: " + e.getMessage());
    }
}
//combo box mapel
private void mapel() {
    try {
        Connection conn = new koneksi().connect();
        String query = "SELECT mata_pelajaran FROM tabel_jadwal";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        
        mapel.removeAllItems(); 
        mapel.addItem("");

        while (rs.next()) {
            mapel.addItem(rs.getString("mata_pelajaran"));
        }
        
        // Tambahkan ActionListener untuk menghitung tagihan
        mapel.addActionListener(e ->Tagihan());
        
        rs.close();
        pst.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal mengambil mata pelajaran: " + e.getMessage());
    }
}

private void Tagihan() {
    try {
        String selectedMapel = (String) mapel.getSelectedItem();
        
        // Jika tidak ada mapel yang dipilih atau kosong, set tagihan ke 0
        if (selectedMapel == null || selectedMapel.isEmpty()) {
            tagihan.setText("0");
            return;
        }
        
        // Periksa apakah lama_belajar kosong
        if (lama.getText().trim().isEmpty()) {
            tagihan.setText("0");
            return;
        }
        
        
        double durasi = Double.parseDouble(lama.getText());
        double hargaPerBulan = 0;

        // Tentukan harga berdasarkan mata pelajaran
        switch(selectedMapel) {
            case "Calistung":
                hargaPerBulan = 400000;
                break;
            case "Bahasa Inggris":
                hargaPerBulan = 500000;
                break;
            case "Matematika":
                hargaPerBulan = 200000;
                break;
            default:
                hargaPerBulan = 0;
        }

        // Hitung total tagihan
        double totalTagihan = hargaPerBulan * durasi;
        
        // Tampilkan hasil (format bisa disesuaikan)
        tagihan.setText(String.valueOf(totalTagihan));
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Masukkan durasi belajar yang valid!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: data belum ada " + e.getMessage());
    }
}
//Warna Untuk Header
 protected void warna_header(){
             tabelbayar.setShowVerticalLines(true);
             tabelbayar.setGridColor(java.awt.Color.GRAY);
          // Ubah warna header 
        javax.swing.table.JTableHeader header = tabelbayar.getTableHeader();
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
 
 // warna header
 protected void warna_header1(){
             tabel_bayar.setShowVerticalLines(true);
             tabel_bayar.setGridColor(java.awt.Color.GRAY);
          // Ubah warna header 
        javax.swing.table.JTableHeader header = tabel_bayar.getTableHeader();
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
        protected void warna_header2(){
             detail_pembayaran.setShowVerticalLines(true);
              detail_pembayaran.setGridColor(java.awt.Color.GRAY);
          // Ubah warna header 
        javax.swing.table.JTableHeader header =  detail_pembayaran.getTableHeader();
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

    private void tabelPembayaranClicked() {
    int selectedRow = tabelbayar.getSelectedRow();
    if (selectedRow >= 0) {
        String noPembayaran = tabelbayar.getValueAt(selectedRow, 0).toString(); // Asumsikan kolom 0 adalah no_pembayaran
        tampilDetailBerdasarkanNoPembayaran(noPembayaran);
    }
}
    
    //method tampil detail
    private void tampilDetailBerdasarkanNoPembayaran(String noPembayaran) {
    Object[] Kolom = { "No Pembayaran", "Jenis Kelas", "Lama Belajar", "Tagihan"};
    DefaultTableModel model = new DefaultTableModel(null, Kolom);
    detail_pembayaran.setModel(model);

    try {
        String sql = "SELECT * FROM detail_pembayaran WHERE no_pembayaran = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, noPembayaran);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
//            String id = rs.getString("id_detail");
            String no = rs.getString("no_pembayaran");
            String jenis = rs.getString("jenis_kelas");
            String lama = rs.getString("lama_belajar");
            String tagihan = rs.getString("tagihan");

            String[] data = { no, jenis, lama, tagihan};
            model.addRow(data);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal menampilkan detail: " + e.getMessage());
    }
}

 
  
}
   

