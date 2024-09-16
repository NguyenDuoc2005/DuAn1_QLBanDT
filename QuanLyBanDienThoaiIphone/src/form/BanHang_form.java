package form;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.HoaDon_;
import entity.Imei;
import entity.ToanCuc_MaSP_QrCode;
import entity.ToanCuc_NV;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import repository.GiamGia_BH_Repository;
import repository.HoaDonChiTiet_BH_Repository;
import repository.HoaDon_BH_Repository;
import repository.SanPham_BH_Pepository;
import response.GiamGia_BH_Response;
import response.HoaDonChiTiet_BH_Response;
import response.HoaDon_BH_Response;
import response.SanPham_BH_Response;
import ultil.ChuyenDoi;
import ultil.MsgBox;
import view.ChonImei_view;
import view.ChonImeiDaBan_View;
import view.Chon_KH_View;
import view.Menu_View;
import view.QrCode_View_BanHang;
import view.QrCode_View_SanPham;

public class BanHang_form extends javax.swing.JInternalFrame {

    ChuyenDoi cd = new ChuyenDoi();
    private GiamGia_BH_Repository repo_GiamGia = new GiamGia_BH_Repository();
    private SanPham_BH_Pepository repo_SanPham;
    public HoaDon_BH_Repository repo_HoaDon;
    private HoaDonChiTiet_BH_Repository repo_HoaDonChiTiet;
    private DefaultTableModel model_SanPham;
    private DefaultTableModel model_HoaDon;
    private DefaultTableModel model_HoaDonChiTiet;
    //
    private DefaultComboBoxModel dcbm_GiamGia;
    public Integer indexSanPhamSelected = -1;
    public Integer indexHoaDonSelected;
    public Integer indexGioHangSelected = -1;
    private Integer Selected_HTTT = 0;
    public long TongTienSauKhiGG = 0;
    private ArrayList<String> list_MaSP_Huy_HD = new ArrayList<>();
    private ArrayList<String> list_MaImei_Huy_HD = new ArrayList<>();

    public BanHang_form() {
        initComponents();
        cauHinh_form();
        repo_SanPham = new SanPham_BH_Pepository();
        repo_HoaDon = new HoaDon_BH_Repository();
        repo_HoaDonChiTiet = new HoaDonChiTiet_BH_Repository();
        model_SanPham = (DefaultTableModel) tblSanPham.getModel();

        model_HoaDon = (DefaultTableModel) tblHoaDon.getModel();

        model_HoaDonChiTiet = (DefaultTableModel) tblGioHang.getModel();
        //
        dcbm_GiamGia = (DefaultComboBoxModel) cboGiamGia.getModel();

        fillTable_SanPham(repo_SanPham.getAll());
        fillTable_HoaDon(repo_HoaDon.getAllByStatus());

        indexHoaDonSelected = tblHoaDon.getSelectedRow();

        //
        txtTienKhachCK.setEnabled(false);
    }

    public void fillTable_SanPham(ArrayList<SanPham_BH_Response> lists) {
        model_SanPham.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> model_SanPham.addRow(new Object[]{
            index.getAndIncrement(), s.getMaSanPham(), s.getTenSanPham(),
            s.getMauSac(), s.getSoLuong(), cd.ChuyenDoiTien((long) s.getGiaBan())
        }));
    }

    public void fillTable_HoaDon(ArrayList<HoaDon_BH_Response> lists) {
        model_HoaDon.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> model_HoaDon.addRow(new Object[]{
            index.getAndIncrement(), s.getMaHoaDon(), s.getMaNhanVien(),
            s.getNgayTao(), s.getTrangThai() == 0 ? "Chưa Thanh Toán" : "Đã Hủy"
        }));
    }

    public void fillTable_HoaDonCT(ArrayList<HoaDonChiTiet_BH_Response> lists) {
        model_HoaDonChiTiet = (DefaultTableModel) tblGioHang.getModel();
        model_HoaDonChiTiet.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> model_HoaDonChiTiet.addRow(new Object[]{
            index.getAndIncrement(), s.getMaSanPham(), s.getTenSanPham(),
            s.getSoLuong(), cd.ChuyenDoiTienHDCT((long) s.getDonGia()),
            cd.ChuyenDoiTienHDCT((long) s.getThanhTien())
        }));
    }

    public void fill_CBO_GiamGia(ArrayList<GiamGia_BH_Response> list) {
        dcbm_GiamGia.removeAllElements();
        list.forEach(gg -> dcbm_GiamGia.addElement(gg.getMaGiamGia()));
        return;
    }

    private void fillTo_CBO_HTThanhToan() {
        cboHTThanhToan.removeAllItems();
        cboHTThanhToan.addItem("Tiền Mặt");
        cboHTThanhToan.addItem("Chuyển Khoản");
        cboHTThanhToan.addItem("Kết Hợp");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dBConnect1 = new config.DBConnect();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnTaoHD = new javax.swing.JButton();
        txtTimKiemHoaDon = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblMaKH = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        lblTenKH = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        lblNgayTao = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();
        lbltenNV = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        cboGiamGia = new javax.swing.JComboBox<>();
        cboHTThanhToan = new javax.swing.JComboBox<>();
        txtTienThua = new javax.swing.JTextField();
        lblTongTienSau = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        txtTienKhachCK = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtMucGiamGia = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoaSP_GioHang = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtTimSanPham = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1212, 760));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP), "Hóa Đơn", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14), new java.awt.Color(102, 153, 255))); // NOI18N
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Mã Nhân Viên", "Ngày Tạo", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setToolTipText("");
        tblHoaDon.setGridColor(new java.awt.Color(204, 204, 204));
        tblHoaDon.setRowHeight(30);
        tblHoaDon.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblHoaDon.setShowGrid(true);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 840, 150));

        btnTaoHD.setBackground(new java.awt.Color(153, 204, 255));
        btnTaoHD.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnTaoHD.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Create.png"))); // NOI18N
        btnTaoHD.setText("Tạo HD");
        btnTaoHD.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDActionPerformed(evt);
            }
        });
        jPanel2.add(btnTaoHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 105, 36));

        txtTimKiemHoaDon.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtTimKiemHoaDon.setBorder(null);
        txtTimKiemHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemHoaDonKeyReleased(evt);
            }
        });
        jPanel2.add(txtTimKiemHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 250, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Zoom.png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        jButton1.setBackground(new java.awt.Color(153, 204, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        jButton1.setText("Hủy HD");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 95, 36));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 13, -1, 60));

        jSeparator14.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator14.setForeground(new java.awt.Color(102, 153, 255));
        jPanel2.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 280, 10));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 0, 840, 230));

        jPanel3.setBackground(new java.awt.Color(177, 225, 249));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Khách Hàng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14), new java.awt.Color(255, 102, 102))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setText("Tên Khách Hàng :");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setText("Mã Khách Hàng :");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 120, -1));

        lblMaKH.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jPanel6.add(lblMaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 130, -1));

        jButton3.setBackground(new java.awt.Color(153, 204, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User group.png"))); // NOI18N
        jButton3.setText("Chọn ");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 70, 32));

        lblTenKH.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jPanel6.add(lblTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 146, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setText("SĐT :");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        lblSDT.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jPanel6.add(lblSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 146, -1));

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 140, 10));

        jSeparator12.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 140, 10));

        jSeparator13.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 140, 10));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 320, 156));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Hóa Đơn", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14), new java.awt.Color(255, 102, 102))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNgayTao.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jPanel7.add(lblNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 126, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel11.setText("Tên NV :");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 79, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel12.setText("Tổng Tiền :");
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 79, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel13.setText("Mức Giảm Giá:");
        jPanel7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 100, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel14.setText("Ngày Tạo :");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 79, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel15.setText("HT Thanh Toán :");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel16.setText("Tiền Thừa :");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel17.setText("Tiền Khách Đưa :");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel18.setText("Tiền Khách CK :");
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 0, 51));
        jLabel19.setText("Tổng Tiền :");
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, -1, -1));

        btnThanhToan.setBackground(new java.awt.Color(153, 204, 255));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/payment.png"))); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        jPanel7.add(btnThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 130, 34));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel22.setText("Mã HD :");
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 79, -1));

        lblMaHoaDon.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jPanel7.add(lblMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 126, -1));

        lbltenNV.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jPanel7.add(lbltenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 164, -1));

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblTongTien.setText("0");
        jPanel7.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 140, 20));

        cboGiamGia.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        cboGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGiamGiaActionPerformed(evt);
            }
        });
        jPanel7.add(cboGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 202, 150, 30));

        cboHTThanhToan.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        cboHTThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền Mặt", "Chuyển Khoản", "Kết Hợp" }));
        cboHTThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHTThanhToanActionPerformed(evt);
            }
        });
        jPanel7.add(cboHTThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 150, 30));

        txtTienThua.setEditable(false);
        txtTienThua.setBackground(new java.awt.Color(255, 255, 255));
        txtTienThua.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtTienThua.setBorder(null);
        jPanel7.add(txtTienThua, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 141, 30));

        lblTongTienSau.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblTongTienSau.setForeground(new java.awt.Color(255, 0, 51));
        lblTongTienSau.setText("0");
        jPanel7.add(lblTongTienSau, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, 169, -1));

        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtTienKhachDua.setText("0");
        txtTienKhachDua.setBorder(null);
        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });
        jPanel7.add(txtTienKhachDua, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 141, 28));

        txtTienKhachCK.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtTienKhachCK.setText("0");
        txtTienKhachCK.setBorder(null);
        txtTienKhachCK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTienKhachCKMouseClicked(evt);
            }
        });
        txtTienKhachCK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachCKKeyReleased(evt);
            }
        });
        jPanel7.add(txtTienKhachCK, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 141, 28));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel23.setText("Mã Giảm Giá :");
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        txtMucGiamGia.setEditable(false);
        txtMucGiamGia.setBackground(new java.awt.Color(255, 255, 255));
        txtMucGiamGia.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtMucGiamGia.setText("0 VNĐ");
        txtMucGiamGia.setBorder(null);
        txtMucGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMucGiamGiaActionPerformed(evt);
            }
        });
        txtMucGiamGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMucGiamGiaKeyReleased(evt);
            }
        });
        jPanel7.add(txtMucGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 141, 28));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, 150, 10));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, 150, 10));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 150, 10));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 150, 10));

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 150, 10));

        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 150, 10));

        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 150, 10));

        jSeparator15.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 150, 10));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 168, 322, 577));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 340, 750));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Giỏ Hàng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14), new java.awt.Color(102, 153, 255))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblGioHang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.setGridColor(new java.awt.Color(204, 204, 204));
        tblGioHang.setRowHeight(30);
        tblGioHang.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblGioHang.setShowGrid(true);
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblGioHang);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 840, 150));

        btnXoaSP_GioHang.setBackground(new java.awt.Color(153, 204, 255));
        btnXoaSP_GioHang.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnXoaSP_GioHang.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaSP_GioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Trash.png"))); // NOI18N
        btnXoaSP_GioHang.setText("Xóa");
        btnXoaSP_GioHang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnXoaSP_GioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSP_GioHangActionPerformed(evt);
            }
        });
        jPanel4.add(btnXoaSP_GioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 80, 33));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 244, 840, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14), new java.awt.Color(102, 153, 255))); // NOI18N
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.setToolTipText("");
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Màu Sắc", "Số Lượng", "Giá Bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setGridColor(new java.awt.Color(204, 204, 204));
        tblSanPham.setRowHeight(30);
        tblSanPham.setRowSelectionAllowed(false);
        tblSanPham.setShowGrid(true);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 820, 180));

        txtTimSanPham.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        txtTimSanPham.setBorder(null);
        txtTimSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimSanPhamKeyReleased(evt);
            }
        });
        jPanel5.add(txtTimSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 250, 30));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Zoom.png"))); // NOI18N
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        jLabel24.setBackground(new java.awt.Color(239, 245, 245));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/up-arrow.png"))); // NOI18N
        jLabel24.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, -1, 50));

        jLabel25.setBackground(new java.awt.Color(239, 245, 245));
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/down-arrow.png"))); // NOI18N
        jLabel25.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, -1, 50));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(102, 153, 255));
        jPanel5.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 280, 10));

        jButton5.setBackground(new java.awt.Color(239, 245, 245));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/qr-code-scan.png"))); // NOI18N
        jButton5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QR Scan", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 12), new java.awt.Color(102, 153, 255))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 70, 70));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 840, 273));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDActionPerformed
        if (tblHoaDon.getRowCount() >= 10) {
            MsgBox.showMessage(this, "Chỉ Được Tạo Tối Đa 10 Hóa Đơn");
            return;
        }
        ToanCuc_NV tc = new ToanCuc_NV();
        HoaDon_ hd = HoaDon_.builder()
                .idNhanVien(tc.getId_NhanVien())
                .build();
        repo_HoaDon.taoHoaDon(hd);
        fillTable_HoaDon(repo_HoaDon.getAllByStatus());
        // lựa chọn dòng bản ghi đầu tiên
        tblHoaDon.setRowSelectionInterval(0, 0);

        // Gọi sự kiện click chuột giả lập
        MouseEvent me = new MouseEvent(tblHoaDon, MouseEvent.MOUSE_CLICKED,
                System.currentTimeMillis(), 0, 0, 0, 1, false);
        for (java.awt.event.MouseListener listener : tblHoaDon.getMouseListeners()) {
            listener.mouseClicked(me);
        }
        System.out.println(tblHoaDon.getSelectedRow());
        MsgBox.showMessage(this, "Tạo Hóa Đơn Thành Công");
    }//GEN-LAST:event_btnTaoHDActionPerformed
    public void clickTable_HoaDon() {
        // lấy vị trí index của tblHoaDon
        indexHoaDonSelected = tblHoaDon.getSelectedRow();
        // Lấy MaHD tại vị trí index của tbl hóa đơn
        String maHD = tblHoaDon.getValueAt(indexHoaDonSelected, 1).toString();
        HoaDon_BH_Response hd = repo_HoaDon.getHD_Click_TheoMa(maHD).get(0);

        if (hd.getTenKhachHang() == null) {
            lblTenKH.setText("KhachLe");
            lblMaKH.setText("MacDinh");
            lblSDT.setText("");
        } else {
            lblTenKH.setText(hd.getTenKhachHang());
            lblMaKH.setText(hd.getMaKhachHang());
            lblSDT.setText(hd.getSdtKhachHang());
        }

        lblNgayTao.setText(hd.getNgayTao() + "");
        lblMaHoaDon.setText(hd.getMaHoaDon());
        lbltenNV.setText(hd.getTenNhanVien());
        lblTongTien.setText(cd.ChuyenDoiTien((long) hd.getTongGia()));
        if (cboGiamGia.getSelectedItem() == null) {
            TongTienSauKhiGG = hd.getTongGia();
        }
        fill_CBO_GiamGia(repo_GiamGia.get_GiamGia_HomNay(hd.getTongGia()));
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
                lblTongTienSau.setText(cd.ChuyenDoiTien((long) hd.getTongGia() - gg.getMucGiamGiaToiDa()));
            } else if (((hd.getTongGia() * gg.getMucGiamGia()) / 100) < gg.getMucGiamGiaToiDa()) {
                lblTongTienSau.setText(cd.ChuyenDoiTien((long) hd.getTongGia() - (hd.getTongGia() * gg.getMucGiamGia() / 100)));
            }

        } // lấy tổng tiền sau giảm giá tính theo số tiền 
        else if (gg.getKieuGiam() == 1) {
            lblTongTienSau.setText(cd.ChuyenDoiTien((long) hd.getTongGia() - gg.getMucGiamGia()));
        }

        // hiển thị lên bảng hóa đơn chi tiết 
        fillTable_HoaDonCT(repo_HoaDonChiTiet.getHDCT_TheoMaHD(maHD));
    }

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked

        clickTable_HoaDon();

    }//GEN-LAST:event_tblHoaDonMouseClicked
    public String layMaHD_Selec_theoMa() {
        String maHD_Click = tblHoaDon.getValueAt(indexHoaDonSelected, 1).toString();
        return maHD_Click;
    }

    public Integer layID_HD_CLick() {
        // Lấy MaHD tại vị trí index của tbl hóa đơn
        String maHD = tblHoaDon.getValueAt(indexHoaDonSelected, 1).toString();
        HoaDon_BH_Response hd = repo_HoaDon.getHD_Click_TheoMa(maHD).get(0);
        return hd.getIdHoaDon();

    }

    public Integer layID_SP_Click() {
        // Lấy MaHD tại vị trí index của tbl hóa đơn
        String maSP = tblSanPham.getValueAt(indexSanPhamSelected, 1).toString();
        SanPham_BH_Response sp = repo_SanPham.chonImei_TheoMaSP(maSP).get(0);
        return sp.getIdSanPham();
    }

    public String layMa_SP_Click() {
        // Lấy MaSP tại vị trí index của tbl sp
        String maSP = tblSanPham.getValueAt(indexSanPhamSelected, 1).toString();

        return maSP;
    }

    public long layGiaBan_SP_Click() {
        // Lấy MaSP tại vị trí index của tbl SP
        String maSP = tblSanPham.getValueAt(indexSanPhamSelected, 1).toString();
        SanPham_BH_Response sp = repo_SanPham.chonImei_TheoMaSP(maSP).get(0);
        return sp.getGiaBan();
    }

    public int lay_SoLuong_SP_Click() {
        // Lấy MaHD tại vị trí index của tbl hóa đơn
        String maSP = tblSanPham.getValueAt(indexSanPhamSelected, 1).toString();
        SanPham_BH_Response sp = repo_SanPham.laySLg_SP_TheoMaSP(maSP).get(0);
        return sp.getSoLuong();
    }

    public String layTenSP() {
        String tenSP = tblSanPham.getValueAt(indexSanPhamSelected, 2).toString();
        return tenSP;
    }
    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

        // kiểm tra háo đơn đã được chọn hay chưa 
        if (indexHoaDonSelected < 0) {
            MsgBox.showMessage(this, "Vui Lòng Chọn Hoặc Tạo Hóa Đơn");
            return;
        }

        // Lấy Ra chỉ số sp select  
        indexSanPhamSelected = tblSanPham.getSelectedRow();
        // Lấy ra mã sản phẩm để hiển thị ra Bảng imei 
        String maSP = tblSanPham.getValueAt(indexSanPhamSelected, 1).toString();
        // Hiển thị Bảng Chọn Imei
        ChonImei_view imei = new ChonImei_view(this);
        imei.setVisible(true);
        imei.fillToTable(repo_SanPham.chonImei_TheoMaSP(maSP));// hiển thị lên table imei theo masp click
        // 
        HoaDon_BH_Response hd = new HoaDon_BH_Response();
        try {
            hd = repo_HoaDon.getHD_Click_TheoMa(layMaHD_Selec_theoMa()).get(0);
        } catch (Exception e) {
        }


    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void cboHTThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHTThanhToanActionPerformed
        // TODO add your handling code here:
        Selected_HTTT = (Integer) cboHTThanhToan.getSelectedIndex();
        if (Selected_HTTT == 0) {
            txtTienKhachDua.setEnabled(true);
            txtTienKhachCK.setEnabled(false);
            txtTienKhachCK.setText("0");
            txtTienThua.setText("");
        } else if (Selected_HTTT == 1) {

            txtTienKhachDua.setEnabled(false);
            txtTienKhachCK.setEnabled(true);
            txtTienKhachDua.setText("0");
            txtTienThua.setText("");
        } else if (Selected_HTTT == 2) {

            txtTienKhachDua.setEnabled(true);
            txtTienKhachCK.setEnabled(false);
            txtTienKhachCK.setText("0");
            txtTienThua.setText("");
            txtTienKhachDua.setText("0");
        }

    }//GEN-LAST:event_cboHTThanhToanActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (indexHoaDonSelected < 0) {
            MsgBox.showMessage(this, "Vui Lòng Chọn Hoặc tạo Hóa Đơn");
            return;
        }
        Chon_KH_View chonKH = new Chon_KH_View(this);
        chonKH.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed
    public String layMaSP_CLick_HDCT() {
        String maSP = tblGioHang.getValueAt(indexGioHangSelected, 1).toString();
        return maSP;
    }

    public int laySLg_SP_GioHang_Click() {
        return repo_HoaDonChiTiet.getHDCT_Click_TheoMaHD_MaSP(
                layMaHD_Selec_theoMa(), layMaSP_CLick_HDCT()).get(0).getSoLuong();
    }

    public int laySLg_SP_HDCT_Click_KhiXoa() {
        return repo_SanPham.laySLg_SP_TheoMaSP(layMaSP_CLick_HDCT()).get(0).getSoLuong();
    }

    public int lay_ID_HDCT_Click() {
        return repo_HoaDonChiTiet.getHDCT_Click_TheoMaHD_MaSP(
                layMaHD_Selec_theoMa(), layMaSP_CLick_HDCT()).get(0).getIdHoaDonChiTiet();

    }

    public String layMaSP_CLick_GioHang() {
        indexGioHangSelected = tblGioHang.getSelectedRow();
        String maSP_Click_GioHang = tblGioHang.getValueAt(indexGioHangSelected, 1).toString();
        return maSP_Click_GioHang;
    }
    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:
        indexGioHangSelected = tblGioHang.getSelectedRow();
        String maSP_Click_GioHang = tblGioHang.getValueAt(indexGioHangSelected, 1).toString();
        ChonImeiDaBan_View imeiDB = new ChonImeiDaBan_View(this);
        imeiDB.setVisible(true);
        imeiDB.fillToTable(repo_SanPham.ChonImeiDB_TheoIDSP_IDHD(maSP_Click_GioHang, layID_HD_CLick()));
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        // TODO add your handling code here:
        // lấy vị trí index của tblHoaDon
        if (indexHoaDonSelected < 0) {
            MsgBox.showMessage(this, "Chọn Hóa Đơn Cần Thanh Toán");
            return;
        }

        indexHoaDonSelected = tblHoaDon.getSelectedRow();
        // Lấy MaHD tại vị trí index của tbl hóa đơn
        String maHD = tblHoaDon.getValueAt(indexHoaDonSelected, 1).toString();
        // lấy hóa đơn được chọn 
        HoaDon_BH_Response hd = repo_HoaDon.getHD_Click_TheoMa(maHD).get(0);
        if (validate_Tien()) {
            long tienKhachDua = 0;
            long tienKhachCK = 0;

            try {
                tienKhachDua = Long.parseLong(txtTienKhachDua.getText().trim());

                tienKhachCK = Long.parseLong(txtTienKhachCK.getText().trim());
            } catch (Exception e) {
            }

            // kiểm tra hình thức thanh toán 
            if (Selected_HTTT == 0) {
                // hình thức thanh toán là tiền mặt 
                txtTienThua.setText(tienKhachDua - (TongTienSauKhiGG) + "");
                txtTienKhachCK.setText("0");
            } else if (Selected_HTTT == 2) {
                // hình thức thanh toán là cả hai tiền thừa mặc định là 0
                if (tienKhachDua > TongTienSauKhiGG) {
                    txtTienThua.setText(tienKhachDua - TongTienSauKhiGG + "");
                    txtTienKhachCK.setText("0");
                    return;
                }

                txtTienKhachCK.setText(TongTienSauKhiGG - tienKhachDua + "");
                txtTienThua.setText("0");

            }
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased
    private Integer layID_GiamGia() {
        if (cboGiamGia.getItemCount() == 0) {

            return null;
        }
        return repo_GiamGia.get_GiamGia_TheoMaGG(cboGiamGia.getSelectedItem() + "")
                .get(0).getIdGiamGia();
    }

    private HoaDon_BH_Response layTT_HoaDon_ThanhToan() {

        HoaDon_BH_Response hd_Response = HoaDon_BH_Response
                .builder()
                .ngayThanhToan(new Date())
                .tongGia(TongTienSauKhiGG)
                .hinhThucTT(cboHTThanhToan.getSelectedItem() + "")
                .tenNguoiNhan(lblTenKH.getText())
                .SDT(lblSDT.getText())
                .diaChi(repo_HoaDon.getHD_Click_TheoMa(layMaHD_Selec_theoMa()).get(0).getDiaChi())
                .idGiamGia(layID_GiamGia())
                .build();

        return hd_Response;
    }
    private static final String PATH = "C:\\Users\\duocn\\OneDrive\\Âm nhạc\\Máy tính\\Dự Án 1_SD19304_Nhóm3\\QuanLyBanDienThoaiIphone\\InHoaDon\\in";
    private static final String PDF_NAME = "";

    public void inHoaDon() throws DocumentException {
        Document document = new Document();
        try {
            //
            PdfWriter pdfWrite = PdfWriter.getInstance(document, new FileOutputStream(new File(PATH + PDF_NAME + tblHoaDon.getValueAt(indexHoaDonSelected, 1))));
            // opend 
            document.open();
            //3 create a chunk
            //
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 14);

            // add text 
            //paragraph
            String multiLineText = "N3 SHOP\nBiên Lai Thanh Toán\nSDT:0366994505\n\n\n";
            // create paragraph object 
            Paragraph paragraph = new Paragraph(multiLineText, font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            String m = "Mã HD: " + tblHoaDon.getValueAt(indexHoaDonSelected, 1).toString() + "\n";
            // create paragraph object 
            Paragraph p = new Paragraph(m);
            document.add(p);

            String m1 = "HVT Khach Hang: " + lblTenKH.getText() + "\n";
            // create paragraph object 
            Paragraph p1 = new Paragraph(m1);
            document.add(p1);

            String m2 = "SDT : " + lblSDT.getText() + "\n";
            // create paragraph object 
            Paragraph p2 = new Paragraph(m2);
            document.add(p2);

            String m3 = "Ngay Lap : " + lblNgayTao.getText() + "\n\n\n";
            // create paragraph object 
            Paragraph p3 = new Paragraph(m3);
            document.add(p3);

            //
            //add atribuies 
            document.addAuthor("Okey java");
            document.addCreationDate();
            document.addProducer();
            document.addCreator("oke");
            document.addTitle("Hóa Đơn");
            document.setPageSize(PageSize.LETTER);
            document.addSubject("okeyjava - pdf ");
            document.addKeywords("okeyjava , java , itext pdf ");

            float[] columns = {50F, 100F, 150F, 100F, 170F, 170F};
            PdfPTable table = new PdfPTable(columns);
            // create a cell 
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new Phrase("STT"));
            table.addCell(cell);

            PdfPCell cell1 = new PdfPCell();
            cell1.setPhrase(new Phrase("Mã SP"));
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell();
            cell2.setPhrase(new Phrase("Tên SP"));
            table.addCell(cell2);

            PdfPCell cell3 = new PdfPCell();
            cell3.setPhrase(new Phrase("So luong"));
            table.addCell(cell3);

            PdfPCell cell4 = new PdfPCell();
            cell4.setPhrase(new Phrase("Don Gia"));
            table.addCell(cell4);

            PdfPCell cell5 = new PdfPCell();
            cell5.setPhrase(new Phrase("Thanh Tien"));
            table.addCell(cell5);

            for (int i = 0; i < tblGioHang.getRowCount(); i++) {

                PdfPCell in = new PdfPCell();
                in.setPhrase(new Phrase(tblGioHang.getValueAt(i, 0).toString()));
                table.addCell(in);

                PdfPCell cel1 = new PdfPCell();
                cell1.setPhrase(new Phrase(tblGioHang.getValueAt(i, 1).toString()));
                table.addCell(cell1);

                PdfPCell cel2 = new PdfPCell();
                cell2.setPhrase(new Phrase(tblGioHang.getValueAt(i, 2).toString()));
                table.addCell(cell2);

                PdfPCell cel3 = new PdfPCell();
                cell3.setPhrase(new Phrase(tblGioHang.getValueAt(i, 3).toString()));
                table.addCell(cell3);

                PdfPCell cel4 = new PdfPCell();
                cell4.setPhrase(new Phrase(tblGioHang.getValueAt(i, 4).toString()));
                table.addCell(cell4);

                PdfPCell cel5 = new PdfPCell();
                cell5.setPhrase(new Phrase(tblGioHang.getValueAt(i, 5).toString()));
                table.addCell(cell5);
            }
            document.add(table);

            String m5 = "\n\n\nMuc Giam Gia : " + txtMucGiamGia.getText() + "\n";
            // create paragraph object 
            Paragraph p5 = new Paragraph(m5);
            document.add(p5);

            String m4 = "Tong Tien Thanh Toan: " + lblTongTienSau.getText() + "\n";
            // create paragraph object 
            Paragraph p4 = new Paragraph(m4);
            document.add(p4);

            String m6 = "Tien Tra Lai : " + txtTienThua.getText() + "\n";
            // create paragraph object 
            Paragraph p6 = new Paragraph(m6);
            document.add(p6);
            //close the document 
            document.close();
            //
            System.out.println("pdf created.");
        } catch (FileNotFoundException ex) {
        }
    }

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        if (indexHoaDonSelected < 0) {
            MsgBox.showMessage(this, "Chọn Hóa Đơn Cần Thanh Toán");
            return;
        }
        if (TongTienSauKhiGG <= 0) {
            MsgBox.showMessage(this, "Chọn Sản Phẩm Trước Khi Thanh Toán");
            return;
        }
        int tienThua = -1;
        try {
            tienThua = Integer.parseInt(txtTienThua.getText());
        } catch (Exception e) {
        }
        if (tienThua < 0) {
            MsgBox.showMessage(this, "Tiền Thanh Toán Chưa Đủ");
            return;
        }
        // cập nhật thanh toán hóa đơn 
        boolean check = MsgBox.showConfirm(this, "Xác Nhận Thanh Toán");
        if (check) {
            // thanh toán hóa đơn --> cập nhật trạng thái 
            boolean in = MsgBox.showConfirm(this, "Bạn Có Muốn In Hóa Đơn Ko");
            if (in) {
                try {
                    inHoaDon();
                } catch (DocumentException ex) {
                    Logger.getLogger(BanHang_form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            repo_HoaDonChiTiet.update_ThanhToan_HD(layTT_HoaDon_ThanhToan(), layMaHD_Selec_theoMa());
            MsgBox.showMessage(this, "Thanh Toán Thành Công");
            // 

            // cập nhật lại số lượng giảm giá nếu có 
            // chưa làm xong 
            // update thông tin hóa đơn 
            lblTenKH.setText("");
            lblMaKH.setText("");
            lblSDT.setText("");
            lblNgayTao.setText("");
            lblMaHoaDon.setText("");
            lbltenNV.setText("");
            lblTongTien.setText("0 VNĐ");
            lblTongTienSau.setText("");
            cboGiamGia.removeAllItems();
            txtMucGiamGia.setText("0 VNĐ");

            txtTienKhachDua.setText("0");
            txtTienKhachCK.setText("0");
            txtTienThua.setText("");
            lblTongTienSau.setText("0 VNĐ");
            // hiển thị lại table hóa đơn 
            fillTable_HoaDon(repo_HoaDon.getAllByStatus());
            // xóa table háo đơn chi tiết 
            model_HoaDonChiTiet.setRowCount(0);
            // cập nhật lại sự kiện click 
            indexSanPhamSelected = -1;
            indexHoaDonSelected = -1;
            indexGioHangSelected = -1;
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTienKhachCKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienKhachCKMouseClicked

    }//GEN-LAST:event_txtTienKhachCKMouseClicked
    private boolean validate_Tien() {
        long tienKhachDua = 0;
        long tienKhachCK = 0;
        try {
            tienKhachDua = Long.parseLong(txtTienKhachDua.getText().trim());
        } catch (Exception e) {
            if (txtTienKhachDua.getText().trim().length() == 0) {

            } else {
                MsgBox.showMessage(this, "Tiền Phải Là Số Nguyên Dương");
                return false;
            }

        }

        try {
            tienKhachCK = Long.parseLong(txtTienKhachCK.getText().trim());
        } catch (Exception e) {
            if (txtTienKhachCK.getText().trim().length() == 0) {
            } else {
                MsgBox.showMessage(this, "Tiền Phải Là Số Nguyên Dương");
                return false;
            }
        }

        return true;
    }
    private void txtTienKhachCKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachCKKeyReleased
        if (indexHoaDonSelected < 0) {
            MsgBox.showMessage(this, "Chọn Hóa Đơn Cần Thanh Toán");
            return;
        }
        // lấy vị trí index của tblHoaDon
        indexHoaDonSelected = tblHoaDon.getSelectedRow();
        // Lấy MaHD tại vị trí index của tbl hóa đơn
        String maHD = tblHoaDon.getValueAt(indexHoaDonSelected, 1).toString();
        // lấy hóa đơn được chọn 
        HoaDon_BH_Response hd = repo_HoaDon.getHD_Click_TheoMa(maHD).get(0);
        if (validate_Tien()) {
            long tienKhachDua = 0;
            long tienKhachCK = 0;
            try {
                tienKhachDua = Long.parseLong(txtTienKhachDua.getText().trim());
                tienKhachCK = Long.parseLong(txtTienKhachCK.getText().trim());
            } catch (Exception e) {
            }

            // kiểm tra hình thức thanh toán 
            if (Selected_HTTT == 1) {
                // hình thức thanh toán là tiền mặt 
                txtTienThua.setText(tienKhachCK - TongTienSauKhiGG + "");
                txtTienKhachDua.setText("0");
            } else if (Selected_HTTT == 2) {
                // hình thức thanh toán là cả hai  

            }
        }
    }//GEN-LAST:event_txtTienKhachCKKeyReleased

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void cboGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGiamGiaActionPerformed
        // TODO add your handling code here:
        String MaGG_Select = (String) cboGiamGia.getSelectedItem();
        HoaDon_BH_Response hd = new HoaDon_BH_Response();
        try {
            hd = repo_HoaDon.getHD_Click_TheoMa(layMaHD_Selec_theoMa()).get(0);
        } catch (Exception e) {
        }

        if (cboGiamGia.getSelectedItem() == null) {
            TongTienSauKhiGG = hd.getTongGia();
        }

        GiamGia_BH_Response gg = new GiamGia_BH_Response();
        try {
            gg = repo_GiamGia.get_GiamGia_TheoMaGG(MaGG_Select).get(0);
        } catch (Exception e) {
        }

//        // kt xem có tồn tại giảm giá hay ko 
//        
//        
//        // lấy tổng tiền sau giảm giá tính theo phần trăm 
        // giảm theo phần trăm 
        if (gg.getKieuGiam() == 0) {// giảm theo phần trăm 
            if ((hd.getTongGia() * gg.getMucGiamGia() / 100) >= gg.getMucGiamGiaToiDa()) {
                // tổng tiền sau giảm giá nếu phần trăm giảm lớn hơn mức giảm tối đa 
                lblTongTienSau.setText(cd.ChuyenDoiTien((long) hd.getTongGia() - gg.getMucGiamGiaToiDa()));
                TongTienSauKhiGG = (long) hd.getTongGia() - gg.getMucGiamGiaToiDa();
                txtMucGiamGia.setText(cd.ChuyenDoiTien((long) gg.getMucGiamGiaToiDa()) + "");
                txtTienKhachDua.setText("0");
                txtTienKhachCK.setText("0");
                txtTienThua.setText("");
            } else if (((hd.getTongGia() * gg.getMucGiamGia()) / 100) < gg.getMucGiamGiaToiDa()) {
                lblTongTienSau.setText(cd.ChuyenDoiTien((long) hd.getTongGia() - (hd.getTongGia() * gg.getMucGiamGia() / 100)));
                TongTienSauKhiGG = (long) hd.getTongGia() - (hd.getTongGia() * gg.getMucGiamGia() / 100);
                txtMucGiamGia.setText(cd.ChuyenDoiTien((long) (hd.getTongGia() * gg.getMucGiamGia() / 100)) + "");
                txtTienKhachDua.setText("0");
                txtTienKhachCK.setText("0");
                txtTienThua.setText("");
            }

        } // lấy tổng tiền sau giảm giá tính theo số tiền 
        else if (gg.getKieuGiam() == 1) {
            lblTongTienSau.setText(cd.ChuyenDoiTien((long) hd.getTongGia() - gg.getMucGiamGia()));
            TongTienSauKhiGG = (long) hd.getTongGia() - gg.getMucGiamGia();
            txtMucGiamGia.setText(cd.ChuyenDoiTien((long) gg.getMucGiamGia()) + "");
            txtTienKhachDua.setText("0");
            txtTienKhachCK.setText("0");
            txtTienThua.setText("");
        }
    }//GEN-LAST:event_cboGiamGiaActionPerformed

    private void txtMucGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMucGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMucGiamGiaActionPerformed

    private void txtMucGiamGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMucGiamGiaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMucGiamGiaKeyReleased

    private void txtTimKiemHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemHoaDonKeyReleased
        // TODO add your handling code here:
        if (txtTimKiemHoaDon.getText().isEmpty()) {
            fillTable_HoaDon(repo_HoaDon.getAllByStatus());
        } else {
            // hóa đơn
            fillTable_HoaDon(repo_HoaDon.search_HoaDon(txtTimKiemHoaDon.getText()));
        }
    }//GEN-LAST:event_txtTimKiemHoaDonKeyReleased

    private void txtTimSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimSanPhamKeyReleased
        // TODO add your handling code here:
        if (txtTimSanPham.getText().isEmpty()) {
            fillTable_SanPham(repo_SanPham.getAll());
        } else {
            fillTable_SanPham(repo_SanPham.search_SanPham(txtTimSanPham.getText()));
        }
    }//GEN-LAST:event_txtTimSanPhamKeyReleased
    private void _add_MaSP_HuyHD_Click_List_() {
        list_MaSP_Huy_HD.clear();
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            list_MaSP_Huy_HD.add(tblGioHang.getValueAt(i, 1).toString());
        }
    }

    private void _add_Imei_HuyHD_Click_List_() {
        _add_MaSP_HuyHD_Click_List_();
        list_MaImei_Huy_HD.clear();
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            // duyệt qua từng phần tử của hdct trong giỏ hàng để lấy imei của hdct
            int soImeiDaBanHuyHD = repo_SanPham.Lay_Imei_DB_HuyHD_Theo_MaSP_MaHD(list_MaSP_Huy_HD.get(i), layMaHD_Selec_theoMa()).size();
            for (int i1 = 0; i1 < soImeiDaBanHuyHD; i1++) {
//                System.out.println(repo_SanPham.Lay_Imei_DB_HuyHD_Theo_MaSP_MaHD(
//                        list_MaSP_Huy_HD.get(i), layMaHD_Selec_theoMa()).size());
                list_MaImei_Huy_HD.add(repo_SanPham.Lay_Imei_DB_HuyHD_Theo_MaSP_MaHD(
                        list_MaSP_Huy_HD.get(i), layMaHD_Selec_theoMa()).get(i1).getMaImeiDaBan());

            }
        }
    }
    
    private void Xoa_All_SP_GioHang_BH() {
        // b1 lấy ra imei của hóa đơn định hủy vào list imei 
        _add_Imei_HuyHD_Click_List_();
        // b2 update lại trạng thái của list imei hủy chở lại thành imei chưa bán của bảng imei 
        for (int i = 0; i < list_MaImei_Huy_HD.size(); i++) {
            repo_HoaDonChiTiet.updateTrang_Thai_Imei_Huy_HD(list_MaImei_Huy_HD.get(i));
        }
        // b3.-1 lấy ra tổng số lượng sản phẩm(imei) theo mã sản phẩm  ()

        // b3.2 cập nhật lại số lượng của sản phẩm vào bảng sản phẩm (+ lại lên) theo mã sp 
        for (int i = 0; i < list_MaSP_Huy_HD.size(); i++) {
            repo_HoaDonChiTiet.updateSoLuong_SP_KhiXoa(
                    repo_HoaDonChiTiet.getTong_Imei_TheoMaSP_HuyHD(list_MaSP_Huy_HD.get(i)).get(0).getSoLuongImei(),
                    list_MaSP_Huy_HD.get(i));
        }
        // b4 xóa imei theo list imei ở bảng imei đã bán(xóa thẳng cẳng)
        for (int i = 0; i < list_MaImei_Huy_HD.size(); i++) {
            repo_HoaDonChiTiet.Xoa_Imei_DaBan(list_MaImei_Huy_HD.get(i));
        }
        // b5: cập nhật lại số lượng ở bảng hdct (Update về hết 0)
        repo_HoaDonChiTiet.updateSoLuong_HDCT_HuyHD(layID_HD_CLick());
        // cập nhật lại tổng tiền của hóa đơn về 0
        repo_HoaDonChiTiet.updateTongTien(0, layMaHD_Selec_theoMa());
        // xóa hóa đơn chi tiết khỏi giỏ hàng khi xóa all 
        // xóa thẳng hdct khi xóa 
        for (int i = 0; i < list_MaSP_Huy_HD.size(); i++) {
            repo_HoaDonChiTiet.Xoa_All_HDCT_GioHang(repo_HoaDonChiTiet.getHDCT_Click_TheoMaHD_MaSP(layMaHD_Selec_theoMa(), 
                    list_MaSP_Huy_HD.get(i)).get(0).getIdHoaDonChiTiet());
        }

        // cập nhật lại thông tin bên phải 
        lblTongTienSau.setText("0 VNĐ");
        lblTongTien.setText("0 VNĐ");
        cboGiamGia.removeAllItems();
        txtMucGiamGia.setText("0 VNĐ");
        return;
    }

//    private void Huy_HoaDon_BH() {
//        // b1 lấy ra imei của hóa đơn định hủy vào list imei 
//        _add_Imei_HuyHD_Click_List_();
//        // b2 update lại trạng thái của list imei hủy chở lại thành imei chưa bán của bảng imei 
//        for (int i = 0; i < list_MaImei_Huy_HD.size(); i++) {
//            repo_HoaDonChiTiet.updateTrang_Thai_Imei_Huy_HD(list_MaImei_Huy_HD.get(i));
//        }
//        // b3.-1 lấy ra tổng số lượng sản phẩm(imei) theo mã sản phẩm  ()
//
//        // b3.2 cập nhật lại số lượng của sản phẩm vào bảng sản phẩm (+ lại lên) theo mã sp 
//        for (int i = 0; i < list_MaSP_Huy_HD.size(); i++) {
//            repo_HoaDonChiTiet.updateSoLuong_SP_KhiXoa(
//                    repo_HoaDonChiTiet.getTong_Imei_TheoMaSP_HuyHD(list_MaSP_Huy_HD.get(i)).get(0).getSoLuongImei(),
//                    list_MaSP_Huy_HD.get(i));
//        }
//        // b4 xóa imei theo list imei ở bảng imei đã bán(xóa thẳng cẳng)
//        for (int i = 0; i < list_MaImei_Huy_HD.size(); i++) {
//            repo_HoaDonChiTiet.Xoa_Imei_DaBan(list_MaImei_Huy_HD.get(i));
//        }
//        // b5: cập nhật lại số lượng ở bảng hdct (Update về hết 0)
//        repo_HoaDonChiTiet.updateSoLuong_HDCT_HuyHD(layID_HD_CLick());
//        // cập nhật lại tổng tiền của hóa đơn về 0
//        repo_HoaDonChiTiet.updateTongTien(0, layMaHD_Selec_theoMa());
//        // cập nhật lại thông tin bên phải 
//        lblTongTienSau.setText("0 VNĐ");
//        lblTongTien.setText("0 VNĐ");
//        cboGiamGia.removeAllItems();
//        txtMucGiamGia.setText("0 VNĐ");
//        return;
//    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (indexHoaDonSelected < 0) {
            MsgBox.showMessage(this, "Chọn Hóa Đơn Muốn Hủy");
            return;
        }
        boolean check = MsgBox.showConfirm(this, "Xác Nhận Hủy : " + layMaHD_Selec_theoMa());
        if (check) {
            Xoa_All_SP_GioHang_BH();
            repo_HoaDon.huy_HD(layMaHD_Selec_theoMa());
            fillTable_HoaDon(repo_HoaDon.getAllByStatus());
            model_HoaDonChiTiet.setRowCount(0);
            fillTable_SanPham(repo_SanPham.getAll());
            // set index 
            indexGioHangSelected = -1;
            indexHoaDonSelected = -1;
            indexSanPhamSelected = -1;
            MsgBox.showMessage(this, "Hủy HD Thành Công");

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        // TODO add your handling code here:
        fillTable_SanPham(repo_SanPham.getAll_GiaTang());
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        // TODO add your handling code here:
        fillTable_SanPham(repo_SanPham.getAll_GiaGiam());
    }//GEN-LAST:event_jLabel25MouseClicked
    
    private void btnXoaSP_GioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSP_GioHangActionPerformed
        // TODO add your handling code here:
        _add_MaSP_HuyHD_Click_List_();
        if (indexHoaDonSelected < 0) {
            MsgBox.showMessage(this, "Chưa Chọn Hóa Đơn");
            return;
        }
        if (list_MaSP_Huy_HD.size() <= 0) {
            MsgBox.showMessage(this, "Giỏ Hàng Hiện Tại K Có SP");
            return;
        }
        boolean check = MsgBox.showConfirm(this, "Xác Nhận Xóa Tất Cả SP Của :" + layMaHD_Selec_theoMa());
        if (check) {
            Xoa_All_SP_GioHang_BH();
            MsgBox.showMessage(this, "Xóa Sản Phẩm Thành Công");
            model_HoaDonChiTiet.setRowCount(0);
            fillTable_SanPham(repo_SanPham.getAll());
        }


    }//GEN-LAST:event_btnXoaSP_GioHangActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (indexHoaDonSelected < 0) {
            MsgBox.showMessage(this, "Vui Lòng Chọn Hoặc Tạo Hóa Đơn");
            return;
        }
        boolean check = MsgBox.showConfirm(this, "Xác Nhận Quét QrSP");
        if (check) {
            QrCode_View_BanHang qr_BH = new QrCode_View_BanHang(this);
            qr_BH.setVisible(true);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    public int lay_indexSP_QrCode() {
        indexSanPhamSelected = -1;
        ToanCuc_MaSP_QrCode spQr = new ToanCuc_MaSP_QrCode();
        String maSP_list;
        for (int i = 0; i < tblSanPham.getRowCount(); i++) {
            maSP_list = tblSanPham.getValueAt(i, 1).toString();
            if (spQr.getMaSP().equals(maSP_list)) {
                indexSanPhamSelected = i;
                return indexSanPhamSelected;
            }
        }
        return indexSanPhamSelected;
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BanHang_form bh = new BanHang_form();
                new Menu_View().setVisible(true);
            }
        });
    }

    public void cauHinh_form() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTaoHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaSP_GioHang;
    public static javax.swing.JComboBox<String> cboGiamGia;
    private javax.swing.JComboBox<String> cboHTThanhToan;
    private config.DBConnect dBConnect1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    public static javax.swing.JLabel lblTongTien;
    public static javax.swing.JLabel lblTongTienSau;
    private javax.swing.JLabel lbltenNV;
    public static javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtMucGiamGia;
    private javax.swing.JTextField txtTienKhachCK;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTimKiemHoaDon;
    private javax.swing.JTextField txtTimSanPham;
    // End of variables declaration//GEN-END:variables
}
