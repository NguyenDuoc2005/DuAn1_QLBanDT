/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package form;

import entity.ThongKe;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import repository.ThongKe_Repository;
import response.ThongKe_Response;
import ultil.ChuyenDoi;
import ultil.MsgBox;

/**
 *
 * @author Nguyen duoc
 */
public class ThongKe_form extends javax.swing.JInternalFrame {

    ArrayList<ThongKe_Response> List = new ArrayList<>();
    ThongKe_Repository repo_ThongKe = new ThongKe_Repository();
    DefaultTableModel model_TKSanPham = new DefaultTableModel();
    DefaultTableModel model_TKDoanhThu = new DefaultTableModel();
    ChuyenDoi cd = new ChuyenDoi();
    String nhapEmail = null;

    public ThongKe_form() {
        initComponents();
        cauHinh_form();
        fillTongDoanhThu(repo_ThongKe.getAll_ThongKe());
        List = repo_ThongKe.getAll_ChiTietSanPham();
        model_TKSanPham = (DefaultTableModel) jTable1.getModel();

        fillChiTietSanPham(repo_ThongKe.getAll_ChiTietSanPham());
        fillDoanhThuHomNay(repo_ThongKe.getALL_TongDoanhThuHomNay());
        fillSoHoaDon(repo_ThongKe.getThongKe_HoaDon());
        lblSoHDHuy.setText(repo_ThongKe.getSoHoaDonHuyAll().get(0).getTongHoaDonHuy() + "");
        // truyền vào tháng 
        for (int i = 0; i < 13; i++) {
            if (i == 0) {
                cbThang.addItem("");
            } else {
                cbThang.addItem(i + "");
            }
        }
        Date dateCBO = new Date();
        String strDateBD = "";
        // Định dạng ngày thành chuỗi
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        strDateBD = formatter.format(dateCBO);
        // truyền vào năm 
        for (int y = Integer.parseInt(strDateBD); y > 2004; y--) {
            if (y == Integer.parseInt(strDateBD)) {
                cbNam.addItem("");
            }
            cbNam.addItem(y + "");
        }
    }

    public void fillTongDoanhThu(ArrayList<ThongKe_Response> list) {

        ThongKe_Response t = list.get(0);
        lbTongDoanhThu.setText(cd.ChuyenDoiTien(t.getTongDoanhThu()) + "");
    }

    public void fillDoanhThuHomNay(ArrayList<ThongKe_Response> list) {
        ThongKe_Response tkhn = list.get(0);
    }

    public void fillSoHoaDon(ArrayList<ThongKe_Response> list) {
        ThongKe_Response tkhd = list.get(0);
        lbSoHoaDon.setText(tkhd.getSoHoaDonDaDuocThanhToan() + "");
    }

    public void fillSoHoaDonHomNay(ArrayList<ThongKe_Response> list) {
        ThongKe_Response tkhdn = list.get(0);
        lbSoHoaDon.setText(tkhdn.getSoHoaDonDaDuocThanhToanHomNay() + "");
    }

    public void fillChiTietSanPham(ArrayList<ThongKe_Response> list) {
        model_TKSanPham.setRowCount(0);
        for (ThongKe_Response CTSP : list) {
            model_TKSanPham.addRow(new Object[]{
                CTSP.getMaSanPham(), CTSP.getTenSanPham(), CTSP.getSoLuongSanPham(), CTSP.getDoanhthusanpham()
            });
        }
    }

    public void fillThongKeTableHomNay(ArrayList<ThongKe_Response> list) {

        for (ThongKe_Response tk : list) {
            Object[] row = new Object[]{
                tk.getSanPhamBan(),
                tk.getNgayThanhToan(),
                tk.getSoLuong(),
                tk.getTongGiaBan(),
                tk.getTongDoanhThu2()
            };

        }
    }

    public void fillThongKeTableDate(ArrayList<ThongKe_Response> list) {
        model_TKDoanhThu.setRowCount(0);
        for (ThongKe_Response tk : list) {
            model_TKDoanhThu.addRow(new Object[]{
                tk.getSanPhamBan(),
                tk.getNgayThanhToan(),
                tk.getSoLuong(),
                tk.getTongGiaBan(),
                tk.getTongGiaGiam(),
                tk.getTongDoanhThu2(),});
        }
    }

    public void fillThongKeTheoNgay(ArrayList<ThongKe_Response> list) {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbTONGDOANHTHU = new javax.swing.JLabel();
        lbTongDoanhThu = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lbSOHOADON = new javax.swing.JLabel();
        lbSoHoaDon = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnGuiEmail = new javax.swing.JButton();
        DoanhThuTab = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cbNam = new javax.swing.JComboBox<>();
        cbThang = new javax.swing.JComboBox<>();
        btLocSP = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbLoaiThoiGian = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbDateNBD = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        cbNgayKT = new com.toedter.calendar.JDateChooser();
        btLoc = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblSoHoaDonHuy = new javax.swing.JLabel();
        lblSoHDHuy = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 0)));
        jPanel2.setPreferredSize(new java.awt.Dimension(273, 107));

        lbTONGDOANHTHU.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lbTONGDOANHTHU.setForeground(new java.awt.Color(255, 51, 51));
        lbTONGDOANHTHU.setText("Tổng Số Doanh Thu Cửa Hàng");

        lbTongDoanhThu.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lbTongDoanhThu.setText("$$$");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Coins.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(lbTongDoanhThu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(62, 62, 62))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(lbTONGDOANHTHU)
                .addGap(51, 51, 51))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTONGDOANHTHU)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lbTongDoanhThu)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 21, 300, -1));

        jPanel5.setBackground(new java.awt.Color(153, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 102, 102)));
        jPanel5.setPreferredSize(new java.awt.Dimension(273, 107));

        lbSOHOADON.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lbSOHOADON.setForeground(new java.awt.Color(255, 51, 51));
        lbSOHOADON.setText("Tổng Số Hóa Đơn Đã Được Thanh Toán");

        lbSoHoaDon.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lbSoHoaDon.setText("$$$");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel13.setText("Hóa Đơn");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Unordered list.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbSOHOADON))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(lbSoHoaDon)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(jLabel19)))
                .addGap(23, 23, 23))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lbSOHOADON)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(lbSoHoaDon)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 310, 110));

        btnGuiEmail.setBackground(new java.awt.Color(153, 204, 255));
        btnGuiEmail.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnGuiEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Bubble.png"))); // NOI18N
        btnGuiEmail.setText("Gửi Báo Cáo");
        btnGuiEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiEmailActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuiEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 350, 150, 30));

        DoanhThuTab.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(153, 204, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 102, 102));
        jLabel11.setText("Top 5 Sản Phẩm Bán Chạy");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Số Lượng", "Doanh Thu"
            }
        ));
        jTable1.setRowHeight(30);
        jTable1.setShowGrid(true);
        jScrollPane3.setViewportView(jTable1);

        cbNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNamActionPerformed(evt);
            }
        });

        btLocSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btLocSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Zoom.png"))); // NOI18N
        btLocSP.setText("Lọc");
        btLocSP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btLocSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLocSPActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Năm");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Tháng");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(cbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(cbThang, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(btLocSP, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(97, 97, 97)
                        .addComponent(jLabel11)
                        .addGap(405, 405, 405))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btLocSP, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );

        DoanhThuTab.addTab("Sản Phẩm Bán Chạy", jPanel6);

        jPanel1.add(DoanhThuTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 1170, 410));
        DoanhThuTab.getAccessibleContext().setAccessibleDescription("");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Loại Thời Gian");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 184, -1, -1));

        cbLoaiThoiGian.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        cbLoaiThoiGian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tổng Tất Cả", "Hôm Nay", "Theo Ngày" }));
        cbLoaiThoiGian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLoaiThoiGianActionPerformed(evt);
            }
        });
        jPanel1.add(cbLoaiThoiGian, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 177, 179, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Ngày Bắt Đầu");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, -1, 30));

        cbDateNBD.setDateFormatString("dd-MM-yyyy");
        jPanel1.add(cbDateNBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, 175, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Ngày Kết Thúc");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, -1, 30));

        cbNgayKT.setDateFormatString("dd-MM-yyyy");
        jPanel1.add(cbNgayKT, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 180, 177, 30));

        btLoc.setBackground(new java.awt.Color(153, 204, 255));
        btLoc.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btLoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Zoom.png"))); // NOI18N
        btLoc.setText("Lọc");
        btLoc.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLocActionPerformed(evt);
            }
        });
        jPanel1.add(btLoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 180, 90, 30));

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 204, 51)));

        lblSoHoaDonHuy.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblSoHoaDonHuy.setForeground(new java.awt.Color(255, 51, 51));
        lblSoHoaDonHuy.setText("Tổng Số Hóa Đơn Hủy");

        lblSoHDHuy.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblSoHDHuy.setText("$$$");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setText("Hóa Đơn");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Unordered list.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblSoHDHuy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(39, 39, 39)
                .addComponent(jLabel20)
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(lblSoHoaDonHuy)
                .addGap(55, 55, 55))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSoHoaDonHuy)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSoHDHuy)
                        .addComponent(jLabel3))
                    .addComponent(jLabel20))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(844, 21, 280, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLocActionPerformed
        // TODO add your handling code here:
        String selectedTimePeriod = cbLoaiThoiGian.getSelectedItem().toString();

        ArrayList<ThongKe_Response> filteredList = new ArrayList<>();

        if ("Hôm Nay".equals(selectedTimePeriod)) {

            lbSOHOADON.setText("Số Hóa Đơn Ngày Hôm Nay");
            lbSoHoaDon.setText(repo_ThongKe.getALL_ThongKeHoaDonNow().get(0).getSoHoaDonDaDuocThanhToanHomNay() + "");
            lbTONGDOANHTHU.setText("Doanh Thu Ngày Hôm Nay");
            lbTongDoanhThu.setText(cd.ChuyenDoiTien(repo_ThongKe.getALL_TongDoanhThuHomNay().get(0).getTongDoanhThuHomNay()) + "");
            lblSoHoaDonHuy.setText("Số Hóa Đơn Hủy Ngày Hôm Nay");
            try {
                lblSoHDHuy.setText(repo_ThongKe.gethomnay_huyHD().get(0).getTongHoaDonHuy() + "");
            } catch (Exception e) {
            }
            // Check if the list is empty and show a message if it is
            fillThongKeTableHomNay(repo_ThongKe.getALL_ThongKeTableUpdateNow());
        } else if ("Tổng Tất Cả".equals(selectedTimePeriod)) {
            lbSOHOADON.setText("Tổng Số Hóa Đơn Đã Được Thanh Toán");
            lbSoHoaDon.setText(repo_ThongKe.getThongKe_HoaDon().get(0).getSoHoaDonDaDuocThanhToan() + "");
            lbTONGDOANHTHU.setText("Tổng Số Doanh Thu Cửa Hàng");
            lbTongDoanhThu.setText(cd.ChuyenDoiTien(repo_ThongKe.getAll_ThongKe().get(0).getTongDoanhThu()) + "");
            lblSoHoaDonHuy.setText("Tổng Số Hóa Đơn Hủy");
            // tổng tất cả số hóa đơn hủy 
            try {
                lblSoHDHuy.setText(repo_ThongKe.getSoHoaDonHuyAll().get(0).getTongHoaDonHuy() + "");
            } catch (Exception e) {
            }

            // Fill the table with the filtered data            
        } else if ("Theo Ngày".equals(selectedTimePeriod)) {
            if (cbDateNBD.getDate() == null || cbNgayKT.getDate() == null) {
                MsgBox.showMessage(this, "Chọn Khoảng Ngày");
                return;
            }
            if(cbDateNBD.getDate().after(cbNgayKT.getDate())){
                MsgBox.showMessage(this,"Ngày Bắt Đầu Phải Nhỏ Hơn Ngày Kết Thúc");
                return ; 
            }
            lbTONGDOANHTHU.setText("Doanh Thu Theo Khoảng Ngày");
            lbTongDoanhThu.setText(cd.ChuyenDoiTien(repo_ThongKe.getALL_ThongKeTongDoanhThuDate(cbDateNBD.getDate(), cbNgayKT.getDate()).get(0).getTongDoanhThuDate()) + "");
            lbSOHOADON.setText("Số Hóa Đơn Theo Khoảng Ngày");
            lbSoHoaDon.setText(repo_ThongKe.getALL_ThongKeHoaDonDate(cbDateNBD.getDate(), cbNgayKT.getDate()).get(0).getTongHoaDonDate() + "");
            lblSoHoaDonHuy.setText("Số Hóa Đơn Hủy Theo Ngày");
            lblSoHDHuy.setText(repo_ThongKe.get_HDHuy_TheoKhoangNgay(cbDateNBD.getDate(), cbNgayKT.getDate()).get(0).getTongHoaDonHuy() + "");
        }
    }//GEN-LAST:event_btLocActionPerformed

    private void cbLoaiThoiGianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLoaiThoiGianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbLoaiThoiGianActionPerformed
    private void GuiEmail_BaoCao(String email_) {
        final String username = "duocn940@gmail.com";
        final String password = "lkxd kpfu umbk sjiv";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        // dăng nhap gmail
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Date dateBD = cbDateNBD.getDate();
        Date dateKT = cbNgayKT.getDate();
        String strDateBD = "";
        String strDateKT = "";
        if (dateBD != null && dateKT != null) {
            // Định dạng ngày thành chuỗi
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            strDateBD = formatter.format(dateBD);
            strDateKT = formatter.format(dateKT);
        }
        String thongTin = null;
        if (cbLoaiThoiGian.getSelectedItem().equals("Tổng Tất Cả")) {
            thongTin = "Thông tin tất cả tại cửa hàng.";
        } else if (cbLoaiThoiGian.getSelectedItem().equals("Hôm Nay")) {
            thongTin = "Thông tin cửa hàng hôm nay.";
        } else if (cbLoaiThoiGian.getSelectedItem().equals("Theo Ngày")) {
            thongTin = "Thông tin cửa hàng theo khoảng ngày : "
                    + strDateBD + " đến " + strDateKT;
        }
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("duocn940@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email_)
            );
            message.setSubject("Thông Báo Doanh Số Của Cửa Hàng N3 Shop");
            message.setText(
                    "----Báo Cáo Doanh Số Của Cửa Hàng N3 Shop---- \n\n"
                    + thongTin + "\n"
                    + lbTONGDOANHTHU.getText() + " : " + lbTongDoanhThu.getText() + "\n"
                    + lbSOHOADON.getText() + " : " + lbSoHoaDon.getText() + "\n"
                    + lblSoHoaDonHuy.getText() + " : " + lblSoHDHuy.getText()
            );
            Transport.send(message);
            JOptionPane.showMessageDialog(this, "Gửi Mail Thành Công");
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValid_ChiTietEmail(String nhapemail) {
        int atCount = nhapemail.length() - nhapemail.replace("@", "").length();
        if (atCount != 1) {
            MsgBox.showMessage(this, "Email phải và chỉ chứa một ký tự '@'.");
            return false;
        }

        // Tách phần tên người dùng và tên miền
        String[] parts = nhapemail.split("@");
        if (parts.length < 2) {
            MsgBox.showMessage(this, "Email phải chứa phần tên miền sau ký tự '@'.");
            return false;
        }

        // Kiểm tra phần tên người dùng
        if (parts[0].isEmpty()) {
            MsgBox.showMessage(this, "Email phải chứa phần tên người dùng trước ký tự '@'.");
            return false;
        }

        // Kiểm tra phần tên miền
        String domainPart = parts[1];
        if (domainPart.isEmpty()) {
            MsgBox.showMessage(this, "Email phải chứa phần tên miền sau ký tự '@'.");
            return false;
        }

        // Kiểm tra tên miền chứa dấu chấm
        if (!domainPart.contains(".")) {
            MsgBox.showMessage(this, "Email phải chứa dấu chấm (.) trong phần tên miền.");
            return false;
        }

        // Kiểm tra độ dài phần tên miền
        String[] domainParts = domainPart.split("\\.");
        if (domainParts[domainParts.length - 1].length() < 2) {
            MsgBox.showMessage(this, "Phần tên miền của email phải có ít nhất hai ký tự.");
            return false;
        }
        return true;
    }

    public boolean validate_guiEmail(String nhapEmail) {
        if (nhapEmail.equals("")) {
            MsgBox.showMessage(this, "Nhập Email cần gửi");
            return false;
        }
        if (isValidEmail(nhapEmail)) {
            
        } else {
            MsgBox.showMessage(this, "Email không hợp lệ. Vui lòng nhập đúng định dạng email (ví dụ: example@domain.com).");
            return false;
        }
        return true;
    }
    private void btnGuiEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiEmailActionPerformed
        // 
        boolean check = MsgBox.showConfirm(this, "Xác Nhận Gửi mail Báo Cáo Doanh Số");
        if (check == true) {
                nhapEmail = JOptionPane.showInputDialog("Nhập Email muốn gửi");
                if (nhapEmail != null) {
                  
                    if (validate_guiEmail(nhapEmail)) {
                        MsgBox.showMessage(this, "Vui Lòng đợi");
                        GuiEmail_BaoCao(nhapEmail);
                    }else{
                        
                    }
                  
                }   
         

        }


    }//GEN-LAST:event_btnGuiEmailActionPerformed

    private void cbNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNamActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbNamActionPerformed

    private void btLocSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLocSPActionPerformed
        // TODO add your handling code here:
        if (cbNam.getSelectedItem() != "" && cbThang.getSelectedItem() != "") {
            fillChiTietSanPham(repo_ThongKe.get_SPTheoThangNam(Integer.parseInt(cbNam.getSelectedItem() + ""), Integer.parseInt(cbThang.getSelectedItem() + "")));
        } else if (cbNam.getSelectedItem() != "" && cbThang.getSelectedItem() == "") {
            fillChiTietSanPham(repo_ThongKe.get_SPTheo_Nam(Integer.parseInt(cbNam.getSelectedItem() + "")));
        } else if (cbNam.getSelectedItem() == "" && cbThang.getSelectedItem() != "") {
            MsgBox.showMessage(this, "Chọn Năm cần lọc");
        } else {
            fillChiTietSanPham(repo_ThongKe.getAll_ChiTietSanPham());
        }
    }//GEN-LAST:event_btLocSPActionPerformed

    public void cauHinh_form() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane DoanhThuTab;
    private javax.swing.JButton btLoc;
    private javax.swing.JButton btLocSP;
    private javax.swing.JButton btnGuiEmail;
    private com.toedter.calendar.JDateChooser cbDateNBD;
    private javax.swing.JComboBox<String> cbLoaiThoiGian;
    private javax.swing.JComboBox<String> cbNam;
    private com.toedter.calendar.JDateChooser cbNgayKT;
    private javax.swing.JComboBox<String> cbThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbSOHOADON;
    private javax.swing.JLabel lbSoHoaDon;
    private javax.swing.JLabel lbTONGDOANHTHU;
    private javax.swing.JLabel lbTongDoanhThu;
    private javax.swing.JLabel lblSoHDHuy;
    private javax.swing.JLabel lblSoHoaDonHuy;
    // End of variables declaration//GEN-END:variables
}
