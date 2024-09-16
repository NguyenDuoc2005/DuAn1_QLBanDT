package form;

import static form.BanHang_form.lblTongTienSau;
import java.util.ArrayList;

import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.RowFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import repository.HoaDonChiTiet_Repository;
import repository.HoaDon_Repository;
import repository.ImeiChiTiet_Repository;
import repository.KhachHang_Repository;
import repository.NhanVien_Repository;
import response.HoaDonChiTiet_Response;
import response.HoaDon_Response;
import response.ImeiChiTiet_Response;
import ultil.ChuyenDoi;
import ultil.MsgBox;

public class HoaDon_form extends javax.swing.JInternalFrame {

    private DefaultTableModel dtm;
    private DefaultTableModel hdct;
    private DefaultTableModel imei;
    private DefaultComboBoxModel cboTrangThai;
    private HoaDon_Repository HoaDon_Repository;
    private HoaDonChiTiet_Repository HoaDonChiTiet_Repository;
    private KhachHang_Repository KhachHang_Repository;
    private NhanVien_Repository NhanVien_Repository;
    private ImeiChiTiet_Repository imeiChiTiet_Repository = new ImeiChiTiet_Repository();
    ChuyenDoi cd = new ChuyenDoi();
    int index_MaHD = -1;
    int index_MaSP = -1;

    public HoaDon_form() {
        initComponents();
        cauHinh_form();
        HoaDon_Repository = new HoaDon_Repository();
        HoaDonChiTiet_Repository = new HoaDonChiTiet_Repository();
        KhachHang_Repository = new KhachHang_Repository();
        NhanVien_Repository = new NhanVien_Repository();
        dtm = (DefaultTableModel) tbl_hoadon.getModel();
        hdct = (DefaultTableModel) tbl_hoadonchitiet.getModel();
        imei = (DefaultTableModel) tbl_imei.getModel();
        cboTrangThai = (DefaultComboBoxModel) cbb_TrangThai.getModel();
        showCbbTrangThai();
        showDataTable(HoaDon_Repository.getAll());

        tbl_hoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _MouseClick_HD();
            }
        });
        cbb_TrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterDataByTrangThai();
            }
        });

    }

    private void showDataTable(ArrayList<HoaDon_Response> list) {
        AtomicInteger index = new AtomicInteger(1);

        dtm.setRowCount(0);
        list.forEach(s -> {
            String trangThai = null;
            if (s.getTrangThai() == 1) {
                trangThai = "Đã Thanh Toán";
            } else if (s.getTrangThai() == 0) {
                trangThai = "Chưa Thanh Toán";
            } else if (s.getTrangThai() == 2) {
                trangThai = "Đã Hủy";
            }
            String mucGiamGia = null;
            // = 1 là giảm giá theo tiền 
            if (s.getKieuGiamGia() == 1) {
                mucGiamGia = cd.ChuyenDoiTien(s.getMucGiamGia()) + "";
            } else if (s.getKieuGiamGia() == 0) {
                if ((s.getTongGia() * s.getMucGiamGia() / 100) >= s.getMucGiamGiaToiDa()) {
                    // tổng tiền sau giảm giá nếu phần trăm giảm lớn hơn mức giảm tối đa 
                    mucGiamGia = s.getMucGiamGia() + "%";
                } else if (((s.getTongGia() * s.getMucGiamGia()) / 100) < s.getMucGiamGiaToiDa()) {
                    mucGiamGia = s.getMucGiamGia() + "%";

                }
                  if (s.getMaGiamGia() == null) {
                mucGiamGia = "0 VNĐ";
            }
            }
            dtm.addRow(new Object[]{index.getAndIncrement(),
                s.getMaHoaDon(), s.getNgayTao(), s.getMaKhachHang(),
                s.getTenNguoiNhan(), s.getSDT(),
                s.getMaNhanVien(), s.getHoTen(), s.getMaGiamGia(), mucGiamGia,
                cd.ChuyenDoiTien(s.getTongGia()), trangThai,
                s.getNgayThanhToan(),});
        });
    }

    private void showDataTablee(ArrayList<HoaDonChiTiet_Response> listhdct) {
        AtomicInteger index = new AtomicInteger(1);
        hdct.setRowCount(0);
        listhdct.forEach(s -> hdct.addRow(new Object[]{index.getAndIncrement(),
            s.getMaSanPham(), s.getTenSanPham(),
            s.getSoLuong(), cd.ChuyenDoiTien(s.getGiaBan())

        }));
    }

    public void showDataTable(String keyword, String trangThai, Double giaTu, Double giaDen) {
        try {
            ArrayList<HoaDon_Response> data = HoaDon_Repository.getAll();
            data.removeIf(hd -> {
                boolean matchKeyword = (keyword == null || keyword.trim().isEmpty()
                        || (hd.getMaHoaDon() != null && hd.getMaHoaDon().toLowerCase().contains(keyword))
                        || (hd.getHoTen() != null && hd.getHoTen().toLowerCase().contains(keyword))
                        || (hd.getSDT() != null && hd.getSDT().toLowerCase().contains(keyword))
                        || (hd.getMaGiamGia() != null && hd.getMaGiamGia().toLowerCase().contains(keyword))
                        || (hd.getTenNguoiNhan() != null && hd.getTenNguoiNhan().toLowerCase().contains(keyword)));

                boolean matchTrangThai = trangThai.equals("All")
                        || (trangThai.equals("Đã thanh toán") && hd.getTrangThai() == 1)
                        || (trangThai.equals("Chưa thanh toán") && hd.getTrangThai() == 0)
                        || (trangThai.equals("Đã hủy") && hd.getTrangThai() == 2);
                boolean matchGia = (giaTu == null || hd.getTongGia() >= giaTu)
                        && (giaDen == null || hd.getTongGia() <= giaDen);
                return !(matchKeyword && matchTrangThai && matchGia);
            });
            AtomicInteger index = new AtomicInteger(1);
            dtm.setRowCount(0);
            for (HoaDon_Response hd : data) {
                dtm.addRow(new Object[]{
                    index.getAndIncrement(),
                    hd.getMaHoaDon(),
                    hd.getNgayTao(),
                    hd.getMaKhachHang(),
                    hd.getTenNguoiNhan(),
                    hd.getSDT(),
                    hd.getMaNhanVien(),
                    hd.getHoTen(),
                    hd.getMaGiamGia(),
                    hd.getMucGiamGia(),
                    hd.getTongGia(),
                    hd.getTrangThai() == 1 ? "Đã thanh toán" : "Chưa thanh toán",
                    hd.getNgayThanhToan(),});
            }
            TableRowSorter<TableModel> sorter1 = new TableRowSorter<>(dtm);
            tbl_hoadon.setRowSorter(sorter1);
            sorter1.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void showCbbTrangThai() {
        cbb_TrangThai.removeAllItems();
        cboTrangThai.addElement("All");
        cboTrangThai.addElement("Chưa thanh toán");
        cboTrangThai.addElement("Đã thanh toán");
        cboTrangThai.addElement("Đã hủy");

    }

    private void filterDataByTrangThai() {
        String selectedTrangThai = (String) cbb_TrangThai.getSelectedItem();
        ArrayList<HoaDon_Response> filteredList = new ArrayList<>();

        if (selectedTrangThai.equals("All")) {
            filteredList = HoaDon_Repository.getAll();
        } else {
            for (HoaDon_Response hd : HoaDon_Repository.getAll()) {
                if ((selectedTrangThai.equals("Đã thanh toán") && hd.getTrangThai() == 1)
                        || (selectedTrangThai.equals("Chưa thanh toán") && hd.getTrangThai() == 0 || (selectedTrangThai.equals("Đã hủy") && hd.getTrangThai() == 2))) {
                    filteredList.add(hd);
                }
            }
        }
        showDataTable(filteredList);
    }

    private void _MouseClick_HD() {
        index_MaHD = tbl_hoadon.getSelectedRow();
        if (index_MaHD != -1) {
            String maHD = tbl_hoadon.getValueAt(index_MaHD, 1).toString();
            ArrayList<HoaDonChiTiet_Response> chiTietList = HoaDonChiTiet_Repository.getChiTietByMaHD(maHD);
            showDataTablee(chiTietList);
        }
    }

    private void showDataImei(ArrayList<ImeiChiTiet_Response> listImei) {
        imei.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        listImei.forEach(s -> imei.addRow(new Object[]{
            index.getAndIncrement(),
            s.getMaImeiDaBan()
        }));
    }

    private void _MouseClick_Imei() {
        int index_MaHD = tbl_hoadon.getSelectedRow();
        int index_MaSP = tbl_hoadonchitiet.getSelectedRow();
        String maHD = tbl_hoadon.getValueAt(index_MaHD, 1).toString();
        String maSP = tbl_hoadonchitiet.getValueAt(index_MaSP, 1).toString();

        showDataImei(imeiChiTiet_Repository.getImeiByMaSP(maSP, maHD));

    }

    private String layMaHD() {
        String maHD = tbl_hoadon.getValueAt(index_MaHD, 1).toString();
        return maHD;
    }

    private String layMaSP() {
        index_MaSP = tbl_hoadonchitiet.getSelectedRow();

        String maSP = tbl_hoadonchitiet.getValueAt(index_MaSP, 1).toString();
        return maSP;
    }

    public void cauHinh_form() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_hoadonchitiet = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_hoadon = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbb_TrangThai = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txt_giatruoc = new javax.swing.JTextField();
        txt_giasau = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_imei = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 255));
        jLabel1.setText("Quản Lý Hóa Đơn");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(466, 6, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 153, 255));
        jLabel2.setText("Chi Tiết Imei :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 460, 120, -1));

        tbl_hoadonchitiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP ", "Tên SP", "Số Lượng", "Giá Bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_hoadonchitiet.setRowHeight(30);
        tbl_hoadonchitiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoadonchitietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_hoadonchitiet);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 730, 190));

        tbl_hoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Ngày Tạo", "Mã Khách Hàng", "Tên Khách Hàng", "SĐT KH", "Mã Nhân Viên", "Tên Nhân Viên", "Mã Giảm Giá", "Mức GIảm Giá", "Tổng Giá", "Trạng Thái", "Ngày Thanh Toán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_hoadon.setRowHeight(30);
        tbl_hoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoadonMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_hoadon);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 231, 1120, 200));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setText("Tìm kiếm hóa đơn :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 140, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel4.setText("Trạng thái hóa đơn :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setText("Lọc theo khoản Giá :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        cbb_TrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_TrangThaiActionPerformed(evt);
            }
        });
        jPanel1.add(cbb_TrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 142, 31));

        jLabel6.setText("Đến");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, -1, -1));
        jPanel1.add(txt_giatruoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 140, 30));

        txt_giasau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_giasauActionPerformed(evt);
            }
        });
        jPanel1.add(txt_giasau, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 130, 30));

        btn_search.setBackground(new java.awt.Color(153, 204, 255));
        btn_search.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Zoom.png"))); // NOI18N
        btn_search.setText("Search");
        btn_search.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });
        jPanel1.add(btn_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 100, 30));

        btn_clear.setBackground(new java.awt.Color(153, 204, 255));
        btn_clear.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btn_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Bubble.png"))); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        jPanel1.add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 100, 30));

        tbl_imei.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Imei "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_imei.setRowHeight(30);
        jScrollPane1.setViewportView(tbl_imei);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 500, 300, 190));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 153, 255));
        jLabel7.setText("Danh sách chi tiết hóa đơn :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 462, -1, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_hoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoadonMouseClicked
        _MouseClick_HD();
    }//GEN-LAST:event_tbl_hoadonMouseClicked

    private void cbb_TrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_TrangThaiActionPerformed
        filterDataByTrangThai();
    }//GEN-LAST:event_cbb_TrangThaiActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        String searchText = txtSearch.getText().toLowerCase();
        String trangThai = (String) cbb_TrangThai.getSelectedItem();
        boolean check = true; 
        Double giaTu = null;
        Double giaDen = null;
        try {
            if (!txt_giatruoc.getText().trim().isEmpty()) {
                giaTu = Double.parseDouble(txt_giatruoc.getText().trim());
            }
            if (!txt_giasau.getText().trim().isEmpty()) {
                giaDen = Double.parseDouble(txt_giasau.getText().trim());
            }
            
        } catch (NumberFormatException e) {
            check = false; 
            MsgBox.showMessage(this,"Giá Phải là số nguyên");
        }
        if(check){
        showDataTable(searchText, trangThai, giaTu, giaDen);
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void txt_giasauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_giasauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_giasauActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed

        txtSearch.setText("");
        cbb_TrangThai.setSelectedIndex(0);
        txt_giatruoc.setText("");
        txt_giasau.setText("");

        // Hiển thị tất cả các hóa đơn
        showDataTable("", "All", null, null);
        hdct.setRowCount(0);
        imei.setRowCount(0);

    }//GEN-LAST:event_btn_clearActionPerformed

    private void tbl_hoadonchitietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoadonchitietMouseClicked
        _MouseClick_Imei();

    }//GEN-LAST:event_tbl_hoadonchitietMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_search;
    private javax.swing.JComboBox<String> cbb_TrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tbl_hoadon;
    private javax.swing.JTable tbl_hoadonchitiet;
    private javax.swing.JTable tbl_imei;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txt_giasau;
    private javax.swing.JTextField txt_giatruoc;
    // End of variables declaration//GEN-END:variables

}
