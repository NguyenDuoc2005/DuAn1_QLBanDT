package view;

import entity.ToanCuc_NV;
import form.BanHang_form;
import form.GiamGia_form;
import form.HoaDon_form;
import form.KhachHang_form;
import form.NhanVien_form;
import form.SanPham_form;
import form.ThongKe_form;
import form.home_form;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import ultil.MsgBox;
import view.ThongTinSanPham.Rom_View;

public class Menu_View extends javax.swing.JFrame {

    BanHang_form banhang_form = new BanHang_form();
    HoaDon_form hoaDon_form = new HoaDon_form();
    KhachHang_form khachHang_form = new KhachHang_form();
    NhanVien_form nhanVien_form = new NhanVien_form();
    ThongKe_form thongKe_form = new ThongKe_form();
    SanPham_form sanPham_form = new SanPham_form();
    private home_form home_form ;
    GiamGia_form giamGia_form = new GiamGia_form();
    Color ClickedColor = new Color(96, 123, 139);
    Color DefaultColor = new Color(153, 204, 255);
    Color ClickColor_lbl = new Color(255,102,102);
    Color DefaultColor_lbl = new Color(255,255,255);
    public Menu_View() {
        initComponents();
        home_form = new home_form();
        hienThi_form();
        
    }
    
    public void hienThi_form() {
          
        home_form.setVisible(true);
        main.add(home_form);
        home_menu.setBackground(ClickedColor);
        lblHome.setForeground(ClickColor_lbl);
    }
    private void lblColor_Default(){
        lblHome.setForeground(DefaultColor_lbl);
        lblbanHang.setForeground(DefaultColor_lbl);
        lblSanPham.setForeground(DefaultColor_lbl);
        lblHoaDon.setForeground(DefaultColor_lbl);
        lblNhanVien.setForeground(DefaultColor_lbl);
        lblKhachHang.setForeground(DefaultColor_lbl);
        lblGiamGia.setForeground(DefaultColor_lbl);
        lblThongKe.setForeground(DefaultColor_lbl);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        home_menu = new javax.swing.JPanel();
        lblHome = new javax.swing.JLabel();
        banhang_menu = new javax.swing.JPanel();
        lblbanHang = new javax.swing.JLabel();
        sanpham_menu = new javax.swing.JPanel();
        lblSanPham = new javax.swing.JLabel();
        hoadon_menu = new javax.swing.JPanel();
        lblHoaDon = new javax.swing.JLabel();
        nhanvien_menu = new javax.swing.JPanel();
        lblNhanVien = new javax.swing.JLabel();
        khachhang_menu = new javax.swing.JPanel();
        lblKhachHang = new javax.swing.JLabel();
        giamgia_menu = new javax.swing.JPanel();
        lblGiamGia = new javax.swing.JLabel();
        thongke_menu = new javax.swing.JPanel();
        lblThongKe = new javax.swing.JLabel();
        dangxuat_form = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        main = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1400, 40));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(102, 102, 102));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setText("Bán Điện Thoại Iphone");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 280, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 10, 37, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Unknown person.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 760));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(200, 50));

        home_menu.setBackground(new java.awt.Color(153, 204, 255));
        home_menu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        home_menu.setPreferredSize(new java.awt.Dimension(200, 50));
        home_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Home_MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                home_menuMousePressed(evt);
            }
        });

        lblHome.setBackground(new java.awt.Color(255, 255, 255));
        lblHome.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lblHome.setForeground(new java.awt.Color(255, 255, 255));
        lblHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Home.png"))); // NOI18N
        lblHome.setText("    Home");

        javax.swing.GroupLayout home_menuLayout = new javax.swing.GroupLayout(home_menu);
        home_menu.setLayout(home_menuLayout);
        home_menuLayout.setHorizontalGroup(
            home_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHome, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        home_menuLayout.setVerticalGroup(
            home_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_menuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblHome)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(home_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(home_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 200, 60));

        banhang_menu.setBackground(new java.awt.Color(153, 204, 255));
        banhang_menu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        banhang_menu.setPreferredSize(new java.awt.Dimension(200, 50));
        banhang_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                banhang_menuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                banhang_menuMousePressed(evt);
            }
        });

        lblbanHang.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lblbanHang.setForeground(new java.awt.Color(255, 255, 255));
        lblbanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Basket.png"))); // NOI18N
        lblbanHang.setText("    Bán Hàng");

        javax.swing.GroupLayout banhang_menuLayout = new javax.swing.GroupLayout(banhang_menu);
        banhang_menu.setLayout(banhang_menuLayout);
        banhang_menuLayout.setHorizontalGroup(
            banhang_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(banhang_menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblbanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        banhang_menuLayout.setVerticalGroup(
            banhang_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(banhang_menuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblbanHang)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.add(banhang_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, -1, 60));

        sanpham_menu.setBackground(new java.awt.Color(153, 204, 255));
        sanpham_menu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sanpham_menu.setPreferredSize(new java.awt.Dimension(200, 50));
        sanpham_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sanpham_menuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sanpham_menuMousePressed(evt);
            }
        });

        lblSanPham.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Mobile phone.png"))); // NOI18N
        lblSanPham.setText("    Sản Phẩm");

        javax.swing.GroupLayout sanpham_menuLayout = new javax.swing.GroupLayout(sanpham_menu);
        sanpham_menu.setLayout(sanpham_menuLayout);
        sanpham_menuLayout.setHorizontalGroup(
            sanpham_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanpham_menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        sanpham_menuLayout.setVerticalGroup(
            sanpham_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanpham_menuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblSanPham)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.add(sanpham_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, -1, 60));

        hoadon_menu.setBackground(new java.awt.Color(153, 204, 255));
        hoadon_menu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        hoadon_menu.setPreferredSize(new java.awt.Dimension(200, 50));
        hoadon_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hoadon_menuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hoadon_menuMousePressed(evt);
            }
        });

        lblHoaDon.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lblHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Text.png"))); // NOI18N
        lblHoaDon.setText("    Hóa Đơn");

        javax.swing.GroupLayout hoadon_menuLayout = new javax.swing.GroupLayout(hoadon_menu);
        hoadon_menu.setLayout(hoadon_menuLayout);
        hoadon_menuLayout.setHorizontalGroup(
            hoadon_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoadon_menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        hoadon_menuLayout.setVerticalGroup(
            hoadon_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoadon_menuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblHoaDon)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.add(hoadon_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, -1, 60));

        nhanvien_menu.setBackground(new java.awt.Color(153, 204, 255));
        nhanvien_menu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        nhanvien_menu.setPreferredSize(new java.awt.Dimension(200, 50));
        nhanvien_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nhanvien_menuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nhanvien_menuMousePressed(evt);
            }
        });

        lblNhanVien.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User group.png"))); // NOI18N
        lblNhanVien.setText("    Nhân viên");

        javax.swing.GroupLayout nhanvien_menuLayout = new javax.swing.GroupLayout(nhanvien_menu);
        nhanvien_menu.setLayout(nhanvien_menuLayout);
        nhanvien_menuLayout.setHorizontalGroup(
            nhanvien_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanvien_menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        nhanvien_menuLayout.setVerticalGroup(
            nhanvien_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nhanvien_menuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblNhanVien)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.add(nhanvien_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, -1, 60));

        khachhang_menu.setBackground(new java.awt.Color(153, 204, 255));
        khachhang_menu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        khachhang_menu.setPreferredSize(new java.awt.Dimension(200, 50));
        khachhang_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                khachhang_menuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                khachhang_menuMousePressed(evt);
            }
        });

        lblKhachHang.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lblKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lblKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User.png"))); // NOI18N
        lblKhachHang.setText("    Khách Hàng");

        javax.swing.GroupLayout khachhang_menuLayout = new javax.swing.GroupLayout(khachhang_menu);
        khachhang_menu.setLayout(khachhang_menuLayout);
        khachhang_menuLayout.setHorizontalGroup(
            khachhang_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, khachhang_menuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblKhachHang)
                .addGap(14, 14, 14))
        );
        khachhang_menuLayout.setVerticalGroup(
            khachhang_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khachhang_menuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblKhachHang)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.add(khachhang_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, -1, 60));

        giamgia_menu.setBackground(new java.awt.Color(153, 204, 255));
        giamgia_menu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        giamgia_menu.setPreferredSize(new java.awt.Dimension(200, 50));
        giamgia_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                giamgia_menuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                giamgia_menuMousePressed(evt);
            }
        });

        lblGiamGia.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lblGiamGia.setForeground(new java.awt.Color(255, 255, 255));
        lblGiamGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Coins.png"))); // NOI18N
        lblGiamGia.setText("    Giảm Giá");

        javax.swing.GroupLayout giamgia_menuLayout = new javax.swing.GroupLayout(giamgia_menu);
        giamgia_menu.setLayout(giamgia_menuLayout);
        giamgia_menuLayout.setHorizontalGroup(
            giamgia_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(giamgia_menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        giamgia_menuLayout.setVerticalGroup(
            giamgia_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(giamgia_menuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblGiamGia)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.add(giamgia_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, -1, 60));

        thongke_menu.setBackground(new java.awt.Color(153, 204, 255));
        thongke_menu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        thongke_menu.setPreferredSize(new java.awt.Dimension(200, 50));
        thongke_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thongke_menuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                thongke_menuMousePressed(evt);
            }
        });

        lblThongKe.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lblThongKe.setForeground(new java.awt.Color(255, 255, 255));
        lblThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Zoom.png"))); // NOI18N
        lblThongKe.setText("    Thống Kê");

        javax.swing.GroupLayout thongke_menuLayout = new javax.swing.GroupLayout(thongke_menu);
        thongke_menu.setLayout(thongke_menuLayout);
        thongke_menuLayout.setHorizontalGroup(
            thongke_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongke_menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        thongke_menuLayout.setVerticalGroup(
            thongke_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongke_menuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblThongKe)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.add(thongke_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, -1, 60));

        dangxuat_form.setBackground(new java.awt.Color(153, 204, 255));
        dangxuat_form.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dangxuat_form.setPreferredSize(new java.awt.Dimension(200, 50));
        dangxuat_form.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dangxuat_formMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Unlock.png"))); // NOI18N
        jLabel11.setText("    Đăng xuất");

        javax.swing.GroupLayout dangxuat_formLayout = new javax.swing.GroupLayout(dangxuat_form);
        dangxuat_form.setLayout(dangxuat_formLayout);
        dangxuat_formLayout.setHorizontalGroup(
            dangxuat_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dangxuat_formLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        dangxuat_formLayout.setVerticalGroup(
            dangxuat_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dangxuat_formLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(dangxuat_form, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, -1, 60));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel12.setText("N3 SHOP");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 110, 20));

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_START);

        main.setBackground(new java.awt.Color(255, 255, 255));
        main.setLayout(new java.awt.CardLayout());
        getContentPane().add(main, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void Home_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home_MouseClicked
        // TODO add your handling code here:
        
        main.removeAll();
        main.add(home_form).setVisible(true);
    }//GEN-LAST:event_Home_MouseClicked

    private void banhang_menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banhang_menuMouseClicked
        // TODO add your handling code here:
        banhang_form = new BanHang_form(); 
        main.removeAll();
        main.add(banhang_form).setVisible(true);
        
    }//GEN-LAST:event_banhang_menuMouseClicked

    private void sanpham_menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanpham_menuMouseClicked
        // TODO add your handling code here:
        sanPham_form = new SanPham_form(); 
        main.removeAll();
        main.add(sanPham_form).setVisible(true);
        
    }//GEN-LAST:event_sanpham_menuMouseClicked

    private void hoadon_menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hoadon_menuMouseClicked
        // TODO add your handling code here:
        hoaDon_form = new HoaDon_form();
        main.removeAll();
        main.add(hoaDon_form).setVisible(true);
        
    }//GEN-LAST:event_hoadon_menuMouseClicked


    private void nhanvien_menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhanvien_menuMouseClicked
        nhanVien_form = new NhanVien_form();
        // phân quyền 
        ToanCuc_NV tc = new ToanCuc_NV();
        if(tc.getTenChucVu()== null){
            main.removeAll();
        main.add(nhanVien_form).setVisible(true);
        return;
        }
        if (tc.getTenChucVu().equals("Nhân Viên")) {
            nhanvien_menu.setBackground(DefaultColor);
            MsgBox.showMessage(this, "Chỉ Trưởng Phòng Mới Được Quản Lý Nhân Viên!");
            return;
        }
        main.removeAll();
        main.add(nhanVien_form).setVisible(true);
        return;

    }//GEN-LAST:event_nhanvien_menuMouseClicked

    private void khachhang_menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khachhang_menuMouseClicked
        // TODO add your handling code here:
        khachHang_form = new KhachHang_form(); 
        main.removeAll();
        main.add(khachHang_form).setVisible(true);
    }//GEN-LAST:event_khachhang_menuMouseClicked

    private void giamgia_menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giamgia_menuMouseClicked
        // TODO add your handling code here:
        giamGia_form = new GiamGia_form();
        ToanCuc_NV tc = new ToanCuc_NV();
        if(tc.getTenChucVu()== null){
            main.removeAll();
        main.add(giamGia_form).setVisible(true);
        return;
        }
        if (tc.getTenChucVu().equals("Nhân Viên")) {
            giamgia_menu.setBackground(DefaultColor);
            MsgBox.showMessage(this, "Chỉ Trưởng Phòng Mới Được Quản Lý Giảm Giá!");
            return;
        }
        main.removeAll();
        main.add(giamGia_form).setVisible(true);
    }//GEN-LAST:event_giamgia_menuMouseClicked

    private void thongke_menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongke_menuMouseClicked
        // TODO add your handling code here:
        thongKe_form = new ThongKe_form(); 
        main.removeAll();
        main.add(thongKe_form).setVisible(true);
    }//GEN-LAST:event_thongke_menuMouseClicked

    private void dangxuat_formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dangxuat_formMouseClicked
        // TODO add your handling code here:
        DangNhap_View dn = new DangNhap_View();
        dn.setVisible(true);
        dispose();
    }//GEN-LAST:event_dangxuat_formMouseClicked

    private void home_menuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_menuMousePressed
        // TODO add your handling code here:
        mauDefault();
        lblColor_Default();
        home_menu.setBackground(ClickedColor);
        lblHome.setForeground(ClickColor_lbl);
    }//GEN-LAST:event_home_menuMousePressed

    private void banhang_menuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banhang_menuMousePressed
        // TODO add your handling code here:
        lblColor_Default();
        mauDefault();
        banhang_menu.setBackground(ClickedColor);
        lblbanHang.setForeground(ClickColor_lbl);
    }//GEN-LAST:event_banhang_menuMousePressed

    private void sanpham_menuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanpham_menuMousePressed
        // TODO add your handling code here:
        lblColor_Default();
        mauDefault();
        sanpham_menu.setBackground(ClickedColor);
        lblSanPham.setForeground(ClickColor_lbl);
    }//GEN-LAST:event_sanpham_menuMousePressed

    private void hoadon_menuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hoadon_menuMousePressed
        // TODO add your handling code here:
        lblColor_Default();
        mauDefault();
        hoadon_menu.setBackground(ClickedColor);
        lblHoaDon.setForeground(ClickColor_lbl);
    }//GEN-LAST:event_hoadon_menuMousePressed

    private void nhanvien_menuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhanvien_menuMousePressed
        // TODO add your handling code here:
        lblColor_Default();
        mauDefault();
        nhanvien_menu.setBackground(ClickedColor);
        lblNhanVien.setForeground(ClickColor_lbl);
    }//GEN-LAST:event_nhanvien_menuMousePressed

    private void khachhang_menuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khachhang_menuMousePressed
        // TODO add your handling code here:
        lblColor_Default();
        mauDefault();
        khachhang_menu.setBackground(ClickedColor);
        lblKhachHang.setForeground(ClickColor_lbl);
    }//GEN-LAST:event_khachhang_menuMousePressed

    private void giamgia_menuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giamgia_menuMousePressed
        // TODO add your handling code here:
        // phân quyền 
        lblColor_Default();
        lblGiamGia.setForeground(ClickColor_lbl);
        mauDefault();
        giamgia_menu.setBackground(ClickedColor);
    }//GEN-LAST:event_giamgia_menuMousePressed

    private void thongke_menuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongke_menuMousePressed
        // TODO add your handling code here:
        lblColor_Default();
        mauDefault();
        thongke_menu.setBackground(ClickedColor);
        lblThongKe.setForeground(ClickColor_lbl);
    }//GEN-LAST:event_thongke_menuMousePressed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        ThongTin_NhanVien_View tt_nv = new ThongTin_NhanVien_View();
        tt_nv.setVisible(true);
        
    }//GEN-LAST:event_jLabel13MouseClicked
    private void mauDefault() {
        home_menu.setBackground(DefaultColor);
        banhang_menu.setBackground(DefaultColor);
        sanpham_menu.setBackground(DefaultColor);
        hoadon_menu.setBackground(DefaultColor);
        nhanvien_menu.setBackground(DefaultColor);
        khachhang_menu.setBackground(DefaultColor);
        thongke_menu.setBackground(DefaultColor);
        giamgia_menu.setBackground(DefaultColor);
    }
    
    public void openSanPham(){
        main.removeAll();
        sanPham_form = new SanPham_form();
        main.add(sanPham_form).setVisible(true);
    }
    
    public static void main(String args[]) {
        

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel banhang_menu;
    private javax.swing.JPanel dangxuat_form;
    private javax.swing.JPanel giamgia_menu;
    private javax.swing.JPanel hoadon_menu;
    private javax.swing.JPanel home_menu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel khachhang_menu;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JLabel lblbanHang;
    public static javax.swing.JDesktopPane main;
    private javax.swing.JPanel nhanvien_menu;
    public javax.swing.JPanel sanpham_menu;
    private javax.swing.JPanel thongke_menu;
    // End of variables declaration//GEN-END:variables
}
