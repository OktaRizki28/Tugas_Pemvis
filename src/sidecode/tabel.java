///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package sidecode;
//
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Font;
//import java.awt.Graphics;
//import javax.swing.JLabel;
//import javax.swing.JTable;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//
///**
// *
// * @author admin
// */
//public class tabel extends JTable {
//    
//    public tabel() {
//        setShowHorizontalLines(true);
//        setGridColor(new Color(230,230,230));
//        setRowHeight(40);
//        getTableHeader().setReorderingAllowed(false);
//        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
//        @Override
//        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
//          TableHeader header = new TableHeader(o+"");
//          if (i1 == 4){
//             header.setHorizontalAlignment(JLabel.CENTER); 
//          }
//           return header;
//        }
//    });
//        
//        setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
//          @Override
//          public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1){
//              if ( i1 == 4){
//                  Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
//                  com.setBackground(Color.white);
//                  setBorder(noFocusBorder);
//                  if(selected ){
//                      com.setForeground(new Color(15,89,140));
//                  } else {
//                      setForeground(new Color(102,102,102));
//                  }
//                  return com;
//          }  else {
//                  StatusType type = (StatusType) o;
//                  CellStatus cell = new cellStatus(type);
//                  return cell;
//              }
//        }
//   });
//}
//    public void addRow(Object[] row){
//       DefaultTableModel model =  (DefaultTableModel)getModel();
//       model.addRow(row);
//    }
//    private class TableHeader extends JLabel {
//        public TableHeader (String text){
//            super(text);
//            setOpaque(true);
//            setBackground(Color.WHITE);
//                setFont(new Font("sansserif", 1, 12));
//        setForeground(new Color(102,102,102));
//        setBorder(new EmptyBorder(10,5,10,5));
//    }
//    
//    @Override
//    protected void paintComponent(Graphics g){
//        super.paintComponent(g);
//        g.setColor(new Color(230,230,230));
//        g.drawLine(0, getHeight() -1, getWidth(), getHeight() -1);
//    
//        }
//    }
//}
//
