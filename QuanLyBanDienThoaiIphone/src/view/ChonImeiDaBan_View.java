/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import form.BanHang_form;
import static form.BanHang_form.lblTongTienSau;
import form.SanPham_form;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import repository.GiamGia_BH_Repository;
import repository.HoaDonChiTiet_BH_Repository;
import repository.HoaDon_BH_Repository;
import repository.Imei_BanHang_Repository;
import repository.SanPham_BH_Pepository;
import response.GiamGia_BH_Response;
import response.HoaDonChiTiet_BH_Response;
import response.HoaDon_BH_Response;
import response.SanPham_BH_Response;
import ultil.ChuyenDoi;
import ultil.MsgBox;
import static view.ChonImei_view.tblImei_SP;

/**
 *
 * @author Nguyen duoc
 */
public class ChonImeiDaBan_View extends javax.swing.JFrame {

    public ArrayList<String> listMaImeiBaBan_Selected = new ArrayList<>();
    private Integer soLuongImeiDaBan_Chon = 0;
    DefaultTableModel model_ImeiDaBan;
    // 
    private HoaDonChiTiet_BH_Repository repo_HDCT_BH = new HoaDonChiTiet_BH_Repository();
    private SanPham_BH_Pepository repo_SP_BH = new SanPham_BH_Pepository();
    public HoaDon_BH_Repository repo_HD_BH = new HoaDon_BH_Repository();
    private Imei_BanHang_Repository repo_ImeiBH = new Imei_BanHang_Repository(); 
    // 
    private GiamGia_BH_Repository repo_GiamGia = new GiamGia_BH_Repository();
    ChuyenDoi cd = new ChuyenDoi();
    BanHang_form form_BH;
    public ChonImeiDaBan_View() {
        initComponents();
        setTitle("Xóa Imei Sản Phẩm");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        model_ImeiDaBan = new DefaultTableModel();
        model_ImeiDaBan = (DefaultTableModel) tblImeiDaBan.getModel();

    }

    public ChonImeiDaBan_View(BanHang_form banHang_form) {
        initComponents();
        setTitle("Xóa Imei Sản Phẩm");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        model_ImeiDaBan = new DefaultTableModel();
        model_ImeiDaBan = (DefaultTableModel) tblImeiDaBan.getModel();
        
        form_BH = banHang_form;

    }

    public void fillToTable(ArrayList<SanPham_BH_Response> list) {
        model_ImeiDaBan.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        for (SanPham_BH_Response imei : list) {
            model_ImeiDaBan.addRow(new Object[]{index.getAndIncrement(),
                imei.getMaSanPham(), imei.getMaImeiDaBan()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTimImeiDB = new javax.swing.JTextField();
        ckALL = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblImeiDaBan = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Xóa Sản Phẩm");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 204, 255));
        jLabel3.setText("Tìm Kiếm :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 70, -1));

        txtTimImeiDB.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtTimImeiDB.setBorder(null);
        txtTimImeiDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimImeiDBActionPerformed(evt);
            }
        });
        txtTimImeiDB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimImeiDBKeyReleased(evt);
            }
        });
        jPanel1.add(txtTimImeiDB, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 200, 30));

        ckALL.setBackground(new java.awt.Color(255, 255, 255));
        ckALL.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        ckALL.setForeground(new java.awt.Color(153, 204, 255));
        ckALL.setText("ALL");
        ckALL.setBorder(null);
        ckALL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ckALLMouseClicked(evt);
            }
        });
        jPanel1.add(ckALL, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, -1, -1));

        tblImeiDaBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Imei", "Lựa Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblImeiDaBan.setRowHeight(30);
        tblImeiDaBan.setRowSelectionAllowed(false);
        tblImeiDaBan.setSelectionBackground(new java.awt.Color(198, 212, 221));
        jScrollPane1.setViewportView(tblImeiDaBan);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 420, 170));

        btnXoa.setBackground(new java.awt.Color(153, 204, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 420, 50));

        jLabel2.setText("____________________________________________");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 220, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ckALLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckALLMouseClicked
        if (ckALL.isSelected()) {
            for (int i = 0; i < tblImeiDaBan.getRowCount(); i++) {
                tblImeiDaBan.setValueAt(true, i, 3);
            }
        } else {
            for (int i = 0; i < tblImeiDaBan.getRowCount(); i++) {
                tblImeiDaBan.setValueAt(false, i, 3);
            }
        }

    }//GEN-LAST:event_ckALLMouseClicked

    private void txtTimImeiDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimImeiDBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimImeiDBActionPerformed
    private void _add_MaImeiDaBan_Click_List() {
        listMaImeiBaBan_Selected.clear();
        for (int i = 0; i < tblImeiDaBan.getRowCount(); i++) {
            try {
                Boolean isSelected = (Boolean) tblImeiDaBan.getValueAt(i, 3); // Cột thứ 2 (chỉ số là 1)
                if (isSelected) {
                    listMaImeiBaBan_Selected.add(tblImeiDaBan.getValueAt(i, 2).toString());

                }
            } catch (Exception e) {
            }
        }
        soLuongImeiDaBan_Chon = listMaImeiBaBan_Selected.size();
    }

    public long layTongTien(ArrayList<HoaDonChiTiet_BH_Response> list) {
        long tongTien = 0;
        for (HoaDonChiTiet_BH_Response a : list) {
            tongTien += a.getThanhTien();
        }
        return tongTien;
    }
    
    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:    
        // add imei đã bán click vào list và lấy ra số lượng imei đã bán đã chọn 
        _add_MaImeiDaBan_Click_List();
        // kiểm tra số lượng đã chọn gì chưa 
        if (soLuongImeiDaBan_Chon <= 0) {
            MsgBox.showMessage(this, "Chọn Imei Muốn Xóa");
            return;
        } 
        // b1 cập nhật lại số lượng ở bảng hóa đơn chi tiết(- đi) khi xóa sp 
        repo_HDCT_BH.updateSoLuong_HDCT_KhiXoa(
                form_BH.laySLg_SP_GioHang_Click() - soLuongImeiDaBan_Chon,
                form_BH.lay_ID_HDCT_Click());
        // b2 : cập nhật lại số lượng(+thêm) ở bảng sản phẩm khi xóa sản phẩm 
        repo_HDCT_BH.updateSoLuong_SP_KhiXoa(form_BH.laySLg_SP_HDCT_Click_KhiXoa() + soLuongImeiDaBan_Chon, form_BH.layMaSP_CLick_HDCT());
        // b3 update lại trạng thái của bảng imei từ đã bán --> chưa bán (2-->1)ở bảng imei 
        for (int y = 0; y < listMaImeiBaBan_Selected.size(); y++) {
            repo_HDCT_BH.capNhat_TrangThaiImei_ThanhChuaban(listMaImeiBaBan_Selected.get(y));
        }
        // b4 xóa imei khỏi bảng imei đã bán 
        for (int y = 0; y < listMaImeiBaBan_Selected.size(); y++) {
            repo_HDCT_BH.Xoa_Imei_DaBan(listMaImeiBaBan_Selected.get(y));
        }
        // b6 fill lại toàn bộ thứ cần thiết 
        // hiển thị lại lên bảng hóa đơn chi tiết (Giỏ Hàng)
        form_BH.fillTable_HoaDonCT(repo_HDCT_BH.getHDCT_TheoMaHD(form_BH.layMaHD_Selec_theoMa()));
        // hiển thị lại bảng sản phẩm 
        form_BH.fillTable_SanPham(repo_SP_BH.getAll());
        // thêm tổng tiền vào hóa đơn
        repo_HDCT_BH.updateTongTien(layTongTien(repo_HDCT_BH.getHDCT_TheoMaHD(form_BH.layMaHD_Selec_theoMa())), form_BH.layMaHD_Selec_theoMa());
        // set lại trường tổng tiền lên label
        // lấy ra hóa đơn được chọn 
        HoaDon_BH_Response hd = repo_HD_BH.getHD_Click_TheoMa(form_BH.layMaHD_Selec_theoMa()).get(0);
        form_BH.lblTongTien.setText(cd.ChuyenDoiTien((long) hd.getTongGia()));

        // kiểm tra giảm giá 
        // 
        
        // gán tổng tiền khi không có giảm giá 
        if(form_BH.cboGiamGia.getSelectedItem()== null){
            form_BH.TongTienSauKhiGG = hd.getTongGia();
        }
        form_BH.fill_CBO_GiamGia(repo_GiamGia.get_GiamGia_HomNay(hd.getTongGia()));
        GiamGia_BH_Response gg = new GiamGia_BH_Response();
        try {
            gg = repo_GiamGia.get_GiamGia_HomNay(hd.getTongGia()).get(0);
        } catch (Exception e) {
        }
//        // kt xem có tồn tại giảm giá hay ko 
//        
//        
//        // lấy tổng tiền sau giảm giá tính theo phần trăm 
        if (gg.getKieuGiam() == 0) {// giảm theo phần trăm 
            if (gg.getKieuGiam() == 0) {// giảm theo phần trăm 
                if ((hd.getTongGia() * gg.getMucGiamGia() / 100) >= gg.getMucGiamGiaToiDa()) {
                    // tổng tiền sau giảm giá nếu phần trăm giảm lớn hơn mức giảm tối đa 
                    lblTongTienSau.setText(cd.ChuyenDoiTien((long) hd.getTongGia() - gg.getMucGiamGiaToiDa()));
                } else if (((hd.getTongGia() * gg.getMucGiamGia()) / 100) < gg.getMucGiamGiaToiDa()) {
                    lblTongTienSau.setText(cd.ChuyenDoiTien((long) hd.getTongGia() - (hd.getTongGia() * gg.getMucGiamGia() / 100)));
                }

            }

        } // lấy tổng tiền sau giảm giá tính theo số tiền 
        else if (gg.getKieuGiam() == 1) {
            form_BH.lblTongTienSau.setText(cd.ChuyenDoiTien((long) hd.getTongGia() - gg.getMucGiamGia()));
        }

        dispose();
        MsgBox.showMessage(this, "Xóa Thành Công");

    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTimImeiDBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimImeiDBKeyReleased
        // TODO add your handling code here:
        if(txtTimImeiDB.getText().isEmpty()){
            fillToTable(repo_SP_BH.ChonImeiDB_TheoIDSP_IDHD(form_BH.layMaSP_CLick_GioHang(),form_BH.layID_HD_CLick()));
        }else{
            fillToTable(repo_ImeiBH.ChonImeiDB_TheoIDSP_IDHD(form_BH.layMaSP_CLick_GioHang(),form_BH.layID_HD_CLick(),txtTimImeiDB.getText()));
        }
    }//GEN-LAST:event_txtTimImeiDBKeyReleased

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
            java.util.logging.Logger.getLogger(ChonImeiDaBan_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChonImeiDaBan_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChonImeiDaBan_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChonImeiDaBan_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChonImeiDaBan_View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXoa;
    private javax.swing.JCheckBox ckALL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblImeiDaBan;
    private javax.swing.JTextField txtTimImeiDB;
    // End of variables declaration//GEN-END:variables
}
