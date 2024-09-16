package view;

import config.DBConnect;
import entity.NhanVien;
import form.BanHang_form;
import static form.BanHang_form.lblTongTienSau;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import repository.GiamGia_BH_Repository;
import repository.GiamGia_Repostiory;
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

public class ChonImei_view extends javax.swing.JFrame {
    
    public ArrayList<String> listMaImei_Selected = new ArrayList<>();
    private Integer soLuongImei_Chon = 0;
    DefaultTableModel model_Imei;
    private GiamGia_BH_Repository repo_GiamGia = new GiamGia_BH_Repository();
    private HoaDonChiTiet_BH_Repository repo_HDCT_BH = new HoaDonChiTiet_BH_Repository();
    private SanPham_BH_Pepository repo_SP_BH = new SanPham_BH_Pepository();
    public HoaDon_BH_Repository repo_HD_BH = new HoaDon_BH_Repository();
    private Imei_BanHang_Repository repo_ImeiBH = new Imei_BanHang_Repository();
    // lấy view bán hàng 
    BanHang_form form_BH;
    ChuyenDoi cd = new ChuyenDoi();

    public ChonImei_view() {
        initComponents();
        setTitle("Chọn Imei Sản Phẩm");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        model_Imei = new DefaultTableModel();
        model_Imei = (DefaultTableModel) tblImei_SP.getModel();
    }

    public ChonImei_view(BanHang_form banHang_form) {
        initComponents();
        form_BH = banHang_form;
      
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        model_Imei = new DefaultTableModel();
        model_Imei = (DefaultTableModel) tblImei_SP.getModel();

    }

    public void fillToTable(ArrayList<SanPham_BH_Response> list) {
        model_Imei.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        for (SanPham_BH_Response imei : list) {
            model_Imei.addRow(new Object[]{index.getAndIncrement(),
                imei.getMaSanPham(), imei.getMaImei()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnXacNhan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblImei_SP = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ckALL = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnXacNhan.setBackground(new java.awt.Color(153, 204, 255));
        btnXacNhan.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnXacNhan.setText("Xác Nhận");
        btnXacNhan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });
        jPanel1.add(btnXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 450, 40));

        tblImei_SP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Imei Sản Phẩm", "Lựa Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblImei_SP.setRowHeight(30);
        tblImei_SP.setSelectionBackground(new java.awt.Color(198, 212, 221));
        jScrollPane1.setViewportView(tblImei_SP);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 450, 270));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Chọn Sản Phẩm");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        jLabel2.setText("____________________________________________");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 220, 20));

        txtTimKiem.setBorder(null);
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel1.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 210, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 204, 255));
        jLabel3.setText("Tìm Kiếm :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 70, -1));

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
        jPanel1.add(ckALL, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // tìm theo mã imei 
        if(txtTimKiem.getText().isEmpty()){
            fillToTable(repo_SP_BH.chonImei_TheoMaSP(form_BH.layMa_SP_Click()));
        }else{
            fillToTable(repo_ImeiBH.Tim_TheoImei_BH(form_BH.layMa_SP_Click(),txtTimKiem.getText()));
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void add_MaImei_Click_List() {
        listMaImei_Selected.clear();
        for (int i = 0; i < tblImei_SP.getRowCount(); i++) {
            try {
                Boolean isSelected = (Boolean) tblImei_SP.getValueAt(i, 3); // Cột thứ 2 (chỉ số là 1)
                if (isSelected) {
                    listMaImei_Selected.add(tblImei_SP.getValueAt(i, 2).toString());

                }
            } catch (Exception e) {
            }
        }
        soLuongImei_Chon = listMaImei_Selected.size();
    }

    private HoaDonChiTiet_BH_Response lay_DL_ThemVaoHDCT() {
        HoaDonChiTiet_BH_Response hdct = HoaDonChiTiet_BH_Response
                .builder()
                .soLuong(soLuongImei_Chon)
                .donGia(form_BH.layGiaBan_SP_Click())
                .idHoaDon(form_BH.layID_HD_CLick())
                .idSanPham(form_BH.layID_SP_Click())
                .build();
        return hdct;
    }

    public int lay_ID_HDCT_VuaThemVao() {
        HoaDonChiTiet_BH_Response hd = repo_HDCT_BH.lay_HDCT_VuaTao_().get(0);
        return hd.getIdHoaDonChiTiet();
    }

    public long layTongTien(ArrayList<HoaDonChiTiet_BH_Response> list) {
        long tongTien = 0;
        for (HoaDonChiTiet_BH_Response a : list) {
            tongTien += a.getThanhTien();
        }
        return tongTien;
    }

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // add imei click vào list và lấy ra số lượng imei đã chọn 
        add_MaImei_Click_List();
        // kiểm tra số lượng đã chọn gì chưa 
        if (soLuongImei_Chon <= 0) {
            MsgBox.showMessage(this, "Chọn Imei Của SP");
            return;
        }

        // kiểm tra xem sp đó đã tồn tại hay chưa để cộng dồn --> chưa làm 
        // -- Phần trên chưa làm đến 
        boolean checkCongDon = false;

        // kiểm tra để cộng dồn sản phẩm 
        for (int i = 0; i < repo_HDCT_BH.getID_HDCT_TheoMaHD(form_BH.layMaHD_Selec_theoMa()).size(); i++) {
            if ( 
// lấy ra id sp của hóa đơn chi tiết có trước đó sau đó so sánh với id của sp click vào 
                    repo_HDCT_BH.getID_HDCT_TheoMaHD(form_BH.layMaHD_Selec_theoMa()).get(i).getIdSanPham() == form_BH.layID_SP_Click()) {
                checkCongDon = true;
                System.out.println("Cộng dồn sp vào hdct");
                // b1 cập nhật số lượng ở bảng hdct đã có 
                repo_HDCT_BH.updateSoLuong_HDCT_CheckTrung(
                        soLuongImei_Chon + repo_HDCT_BH.getID_HDCT_TheoMaHD(form_BH.layMaHD_Selec_theoMa()).get(i).getSoLuong(),
                        repo_HDCT_BH.getID_HDCT_TheoMaHD(form_BH.layMaHD_Selec_theoMa()).get(i).getIdHoaDonChiTiet()
                );
                // b2 thêm imei vào bảng imei đã bán và cả idspct từ bảng hdct đã có 
                // 

                for (int y = 0; y < listMaImei_Selected.size(); y++) {
                    repo_HDCT_BH.Them_Vao_Imei_DaBan(listMaImei_Selected.get(y), repo_HDCT_BH.getID_HDCT_TheoMaHD(form_BH.layMaHD_Selec_theoMa()).get(i).getIdHoaDonChiTiet());
                }

                // b3 cập nhật lại số lượng sản phẩm ở bảng sản phẩm
                repo_HDCT_BH.capNhat_SLg_SP_KhiThem_TheoMaSP(form_BH.lay_SoLuong_SP_Click() - soLuongImei_Chon, form_BH.layMa_SP_Click());
                // b4 cập nhật lại trạng thái của bảng imei thành imei đã bán 
                for (int y = 0; y < listMaImei_Selected.size(); y++) {
                    repo_HDCT_BH.capNhat_TrangThaiImei_ThanhDaBan(listMaImei_Selected.get(y));
                }
                break;
            }
        }
        // thêm mới sản phẩm vào hóa đơn chi tiết 
        if (!checkCongDon) {
            System.out.println("THêm Mới Sản phẩm vào HDCT");
            // sp Chưa tồn tại trong giỏ hàng 
            // b1 Thêm tất cả vào hdct 
            repo_HDCT_BH.Them_Vao_HDCT(lay_DL_ThemVaoHDCT());// đã được
            // b2 lấy ra id hdct vừa tạo // hàm riêng--xong 
            // b3 thêm list imei vào imei đã bán và cả idspct vừa tạo 
            // vòng for lấy ra tất cả mã imei đã chọn và thêm vào imei db 
            for (int i = 0; i < listMaImei_Selected.size(); i++) {
                repo_HDCT_BH.Them_Vao_Imei_DaBan(listMaImei_Selected.get(i), lay_ID_HDCT_VuaThemVao());
            }
            // lấy slg sp khi click sp 
            // b4 cập nhật lại số lượng ở bảng sp 
            repo_HDCT_BH.capNhat_SLg_SP_KhiThem_TheoMaSP(form_BH.lay_SoLuong_SP_Click() - soLuongImei_Chon, form_BH.layMa_SP_Click());
            // b5 cập nhật lại trạng thái của bảng imei thành imei đã bán 
            for (int i = 0; i < listMaImei_Selected.size(); i++) {
                repo_HDCT_BH.capNhat_TrangThaiImei_ThanhDaBan(listMaImei_Selected.get(i));
            }

        }

        // b6 fill lại toàn bộ thứ cần thiết 
        // hiển thị lại lên bảng hóa đơn chi tiết (Giỏ Hàng)
        form_BH.fillTable_HoaDonCT(repo_HDCT_BH.getHDCT_TheoMaHD(form_BH.layMaHD_Selec_theoMa()));
        // hiển thị lại bảng sản phẩm 
        form_BH.fillTable_SanPham(repo_SP_BH.getAll());
        // thêm tổng tiền vào hóa đơn
        repo_HDCT_BH.updateTongTien(layTongTien(repo_HDCT_BH.getHDCT_TheoMaHD(form_BH.layMaHD_Selec_theoMa())), form_BH.layMaHD_Selec_theoMa());
       
//         set lại trường tổng tiền lên label
//         lấy ra hóa đơn được chọn 
        HoaDon_BH_Response hd = repo_HD_BH.getHD_Click_TheoMa(form_BH.layMaHD_Selec_theoMa()).get(0);
        form_BH.lblTongTien.setText(cd.ChuyenDoiTien((long) hd.getTongGia()));
       // kiểm tra khi không có mã giảm giá 
        if(form_BH.cboGiamGia.getSelectedItem()== null){
            form_BH.TongTienSauKhiGG = hd.getTongGia();
        }
        
//        // hiển thị lên giảm giá theo đk là số tiền tối thiểu của hóa đơn 
        form_BH.fill_CBO_GiamGia(repo_GiamGia.get_GiamGia_HomNay(hd.getTongGia()));
            // 
        
         GiamGia_BH_Response gg = new GiamGia_BH_Response(); 
        try {
             gg = repo_GiamGia.get_GiamGia_HomNay(hd.getTongGia()).get(0);
        } catch (Exception e) {
        }
        // lấy tổng tiền sau giảm giá tính theo phần trăm 
   
            if (gg.getKieuGiam() == 0) {// giảm theo phần trăm 
            if ((hd.getTongGia() * gg.getMucGiamGia() / 100) >= gg.getMucGiamGiaToiDa()) {
                // tổng tiền sau giảm giá nếu phần trăm giảm lớn hơn mức giảm tối đa 
                lblTongTienSau.setText(cd.ChuyenDoiTien((long) hd.getTongGia()-gg.getMucGiamGiaToiDa()));
            }else if(((hd.getTongGia() * gg.getMucGiamGia()) / 100) < gg.getMucGiamGiaToiDa()){
                lblTongTienSau.setText(cd.ChuyenDoiTien((long) hd.getTongGia()-(hd.getTongGia() * gg.getMucGiamGia() / 100)));
            }

        }

        
        // lấy tổng tiền sau giảm giá tính theo số tiền 
        else if(gg.getKieuGiam() == 1){
        form_BH.lblTongTienSau.setText(cd.ChuyenDoiTien((long) hd.getTongGia()-gg.getMucGiamGia()));
        }else{
            lblTongTienSau.setText(hd.getTongGia()+"");
           
        }
        dispose();
        MsgBox.showMessage(this, "Thêm Thành Công");
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void ckALLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckALLMouseClicked
        // TODO add your handling code here:
        // lựa chọn tất cả 
        if (ckALL.isSelected()) {
            for (int i = 0; i < tblImei_SP.getRowCount(); i++) {
                tblImei_SP.setValueAt(true, i, 3);
            }
        } else {
            for (int i = 0; i < tblImei_SP.getRowCount(); i++) {
                tblImei_SP.setValueAt(false, i, 3);
            }
        }
    }//GEN-LAST:event_ckALLMouseClicked

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
            java.util.logging.Logger.getLogger(ChonImei_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChonImei_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChonImei_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChonImei_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChonImei_view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JCheckBox ckALL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tblImei_SP;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
