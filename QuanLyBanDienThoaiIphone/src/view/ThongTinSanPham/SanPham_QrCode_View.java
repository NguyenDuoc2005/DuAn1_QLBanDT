package view.ThongTinSanPham;

import entity.Rom;
import entity.ToanCuc_MaImei_QrCode;
import entity.ToanCuc_MaSP_QrCode;
import entity.ToanCuc_NV;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import repository.SanPham_Repository;
import response.SanPham_Response;
import ultil.ChuyenDoi;

public class SanPham_QrCode_View extends javax.swing.JFrame {

    ArrayList<SanPham_Response> listSP = new ArrayList<>();
    SanPham_Repository repo_sanPham = new SanPham_Repository();
    ChuyenDoi cd = new ChuyenDoi();
    private DefaultComboBoxModel dcbm_Imei;
    public SanPham_QrCode_View() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dcbm_Imei = new DefaultComboBoxModel(); 
        dcbm_Imei = (DefaultComboBoxModel) cboImei.getModel(); 
        ToanCuc_MaImei_QrCode tc = new ToanCuc_MaImei_QrCode();
        ToanCuc_MaSP_QrCode tc_SP= new ToanCuc_MaSP_QrCode();
        listSP = repo_sanPham.getAll_SP_TheoMaSP(tc_SP.getMaSP());
        txtTenSP.setText(listSP.get(0).getTenSanPham());
        txtMaSP.setText(listSP.get(0).getMaSanPham());
        txtXuatXu.setText(listSP.get(0).getXuatXu());
        txtRom.setText(listSP.get(0).getRom());
        txtRam.setText(listSP.get(0).getRam());
        txtKichThuoc.setText(listSP.get(0).getKichThuoc());
        txtMauSac.setText(listSP.get(0).getMauSac());
        txtCPU.setText(listSP.get(0).getCPU());
        txtDungLuongPin.setText(listSP.get(0).getDungLuongPin());
        txtGiaNhap.setText(cd.ChuyenDoiTien(listSP.get(0).getGiaNhap())+"");
        txtGiaBan.setText(cd.ChuyenDoiTien(listSP.get(0).getGiaBan())+"");
        txtPhanLoai.setText(listSP.get(0).getPhanLoai());
        try {
            fillToCBO_Imei(repo_sanPham.getAll_ImeiChiTiet(tc_SP.getMaSP()));
        } catch (Exception e) {
        }
        txtSoLuong.setText(repo_sanPham.getAll_ImeiChiTiet(tc_SP.getMaSP()).size()+"");
        try {
            ImageIcon Icon = new ImageIcon(listSP.get(0).getHinhAnh());
            lblHinhAnh.setIcon(Icon);
            lblHinhAnh.setText("");
        } catch (Exception e) {
            lblHinhAnh.setIcon(null);
            lblHinhAnh.setText("Hình ảnh.png");
        }
    }
    
    private void fillToCBO_Imei(ArrayList<SanPham_Response> list){
        dcbm_Imei.removeAllElements();
        list.forEach(imei -> dcbm_Imei.addElement(imei.getImei()));
        return ;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        txtKichThuoc = new javax.swing.JTextField();
        txtPhanLoai = new javax.swing.JTextField();
        txtCPU = new javax.swing.JTextField();
        txtDungLuongPin = new javax.swing.JTextField();
        txtXuatXu = new javax.swing.JTextField();
        txtRom = new javax.swing.JTextField();
        txtRam = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        txtMauSac = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cboImei = new javax.swing.JComboBox<>();
        txtMaSP = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        lblHinhAnh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Thông Tin Sản Phẩm");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 260, 48));
        jPanel1.add(txtTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 160, 30));
        jPanel1.add(txtKichThuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 150, 30));
        jPanel1.add(txtPhanLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 400, 150, 30));
        jPanel1.add(txtCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, 160, 30));
        jPanel1.add(txtDungLuongPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 150, 30));
        jPanel1.add(txtXuatXu, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 320, 150, 30));
        jPanel1.add(txtRom, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 160, 30));
        jPanel1.add(txtRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 160, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel2.setText("Ram");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 50, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel3.setText("Dung Lượng Pin");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 110, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel4.setText("Xuất Xứ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, 110, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel5.setText("Tồn Kho");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 60, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel6.setText("Rom");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 50, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel7.setText("Kích Thước Màn");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 110, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel8.setText("CPU");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 110, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel9.setText("Phân Loại");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 380, 110, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel10.setText("Mã SP");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 110, -1));
        jPanel1.add(txtGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 160, 30));
        jPanel1.add(txtGiaNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 150, 30));
        jPanel1.add(txtMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 150, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel11.setText("Giá Bán");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 450, 110, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel12.setText("Giá Nhập");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 110, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel13.setText("Màu Sắc");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 110, -1));

        jPanel1.add(cboImei, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 150, 30));
        jPanel1.add(txtMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 150, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel14.setText("Tên Sản Phẩm");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 110, -1));

        txtSoLuong.setEditable(false);
        txtSoLuong.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 160, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel15.setText("Imei Sản Phẩm");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 110, -1));

        lblHinhAnh.setText("hinhanh.png");
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblHinhAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 170, 190));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SanPham_QrCode_View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboImei;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JTextField txtCPU;
    private javax.swing.JTextField txtDungLuongPin;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtKichThuoc;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMauSac;
    private javax.swing.JTextField txtPhanLoai;
    private javax.swing.JTextField txtRam;
    private javax.swing.JTextField txtRom;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtXuatXu;
    // End of variables declaration//GEN-END:variables
}
