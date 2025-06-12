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
public class form_guru extends javax.swing.JPanel {
    private Connection conn = new koneksi().connect();
     private DefaultTableModel tabmode;
    
    /**
     * Creates new form form_guru
     */
    public form_guru() {
        initComponents();
        datatabel();
         b_hapus.setVisible(false);
         warna_header();
        tabel_guru.setShowVerticalLines(true);
        tabel_guru.setGridColor(java.awt.Color.GRAY);
      
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
        tid.setEnabled(true);
        tnama.setEnabled(true);
        tambah_data.setEnabled(true);
        tid.requestFocus();
    }
      protected void kosong(){
        tid.setText("");   
        tnama.setText("");
        ttlp.setText("");
        rjk1.setSelected(false);
        rjk2.setSelected(false);
        talm.setText("");
        email_guru.setText("");
        pendidikan.setText("");
        
    }
        protected void datatabel(){
             
        Object [] Baris = {"ID Guru","Nama Guru", "No. Telepon","Email","Jenis Klamin",
                           "Pendidikan Terakhir","Alamat"};
        tabmode = new DefaultTableModel(null,Baris);
        tabel_guru.setModel(tabmode);
        String sql = " SELECT * FROM tabel_guru";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
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
            
        }catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage()); 
           
        }
    }
        protected void tambah(){
             String sql = "INSERT INTO tabel_Guru  VALUES (?,?, ?, ?, ?,?,?)";
        try {
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1,tid.getText());
        stat.setString(2,tnama.getText());
        stat.setString(3,ttlp.getText());
        stat.setString(4,email_guru.getText());

        
        String Jkel=" ";
        if(rjk1.isSelected()) Jkel="laki-laki";
        else Jkel="Perempuan";
        stat.setString(5,Jkel);
        
        stat.setString(6,pendidikan.getText());
        stat.setString(7,talm.getText());
        
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
                String sql = "UPDATE tabel_guru SET nama=?, telp=?, email_guru=?,  jk=?,"
                        + "pendidikan=?, alamat=? WHERE id=?";
                PreparedStatement stat = conn.prepareStatement(sql);

                // Mengatur parameter
                stat.setString(7, tid.getText());
                stat.setString(1, tnama.getText());
                stat.setString(2,ttlp.getText());
                stat.setString(3,email_guru.getText());

                String jkel = rjk1.isSelected() ? "Laki-Laki" : "Perempuan";
                stat.setString(4, jkel);
                
                stat.setString(5,pendidikan.getText());
                stat.setString(6, talm.getText());


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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tnama = new javax.swing.JTextField();
        tid = new javax.swing.JTextField();
        ttlp = new javax.swing.JTextField();
        rjk1 = new javax.swing.JRadioButton();
        rjk2 = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        talm = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        email_guru = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        pendidikan = new javax.swing.JTextField();

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
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
        jLabel3.setText("ID Guru");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Nama Guru");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("No. Telpon");

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Jenis Klamin");

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Alamat");

        tnama.setBackground(new java.awt.Color(239, 239, 239));
        tnama.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        tnama.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        tnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnamaActionPerformed(evt);
            }
        });

        tid.setBackground(new java.awt.Color(240, 238, 238));
        tid.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        tid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        tid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidActionPerformed(evt);
            }
        });

        ttlp.setBackground(new java.awt.Color(239, 239, 239));
        ttlp.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        ttlp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        ttlp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttlpActionPerformed(evt);
            }
        });

        rjk1.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        rjk1.setText("Laki-laki");
        rjk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rjk1ActionPerformed(evt);
            }
        });

        rjk2.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        rjk2.setText("Perempuan");
        rjk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rjk2ActionPerformed(evt);
            }
        });

        talm.setBackground(new java.awt.Color(239, 239, 239));
        talm.setColumns(20);
        talm.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        talm.setRows(5);
        talm.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        jScrollPane2.setViewportView(talm);

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Email");

        email_guru.setBackground(new java.awt.Color(239, 239, 239));
        email_guru.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        email_guru.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        email_guru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                email_guruActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Pendidikan Terakhir");

        pendidikan.setBackground(new java.awt.Color(239, 239, 239));
        pendidikan.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        pendidikan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        pendidikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendidikanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tambah_dataLayout = new javax.swing.GroupLayout(tambah_data);
        tambah_data.setLayout(tambah_dataLayout);
        tambah_dataLayout.setHorizontalGroup(
            tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(tambah_dataLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tambah_dataLayout.createSequentialGroup()
                        .addGroup(tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addGroup(tambah_dataLayout.createSequentialGroup()
                                .addComponent(rjk1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(rjk2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambah_dataLayout.createSequentialGroup()
                        .addGroup(tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1004, Short.MAX_VALUE)
                            .addComponent(pendidikan, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tid, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tnama, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ttlp, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(email_guru, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tambah_dataLayout.createSequentialGroup()
                                .addGroup(tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addGroup(tambah_dataLayout.createSequentialGroup()
                                        .addComponent(b_save, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(b_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(48, 48, 48))))
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
                .addComponent(tid, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ttlp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email_guru, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tambah_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rjk1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rjk2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pendidikan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
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

    private void b_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_saveActionPerformed
     if(b_tambah.getText().equals("TAMBAH")){
         tambah();
     } else if(b_tambah.getText().equals("EDIT")){
         edit();
     }
    }//GEN-LAST:event_b_saveActionPerformed

    private void tnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnamaActionPerformed

    private void ttlpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttlpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttlpActionPerformed

    private void tidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tidActionPerformed

    private void b_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_hapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
            if (ok==0){
                String sql="delete from tabel_guru where id='"+tid.getText()+"'";
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

            tid.setText(a);
            tnama.setText(b);
            ttlp.setText(c);
            email_guru.setText(d);
            // Set the date in the date picker
           
            rjk1.setSelected(true);

            if (e.equals("laki-laki")) {
                rjk1.setSelected(true);
                rjk2.setSelected(false);
            } else {
                rjk1.setSelected(false);
                rjk2.setSelected(true);
            }
            
            pendidikan.setText(c);
            talm.setText(g);
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

    private void b_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelActionPerformed
                    panel_guru.removeAll();
                    panel_guru.repaint();
                    panel_guru.revalidate();
                
                    panel_guru.add(data_guru);
                    b_tambah.setText("TAMBAH");
                    b_tambah.setBackground(Color.green);
                    hilang();
    }//GEN-LAST:event_b_cancelActionPerformed

    private void b_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cariActionPerformed
    Object [] Baris = {"ID Guru","Nama Guru", "No. Telepon","Email","Jenis Klamin",
                        "Pendidikan Terakhir","Alamat"};
    tabmode = new DefaultTableModel(null, Baris);
    tabel_guru.setModel(tabmode);

    // Dapatkan istilah pencarian dari kolom teks
        String searchTerm = t_cari.getText();

    
    String sql = "SELECT * FROM tabel_guru WHERE id LIKE ? OR nama LIKE ?";

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

    private void email_guruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_email_guruActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_email_guruActionPerformed

    private void pendidikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendidikanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pendidikanActionPerformed

    private void rjk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rjk2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rjk2ActionPerformed

    private void rjk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rjk1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rjk1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_cancel;
    private javax.swing.JButton b_cari;
    private javax.swing.JButton b_hapus;
    private javax.swing.JButton b_save;
    private javax.swing.JButton b_tambah;
    private javax.swing.JPanel data_guru;
    private javax.swing.JTextField email_guru;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panel_guru;
    private javax.swing.JTextField pendidikan;
    private javax.swing.JRadioButton rjk1;
    private javax.swing.JRadioButton rjk2;
    private javax.swing.JTextField t_cari;
    private javax.swing.JTable tabel_guru;
    private javax.swing.JTextArea talm;
    private javax.swing.JPanel tambah_data;
    private javax.swing.JTextField tid;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField ttlp;
    // End of variables declaration//GEN-END:variables
}
