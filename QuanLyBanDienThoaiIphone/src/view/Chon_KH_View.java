package view;

import entity.KhachHang;
import form.BanHang_form;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import repository.ChonKhachHang_Repository;
import ultil.MsgBox;

public class Chon_KH_View extends javax.swing.JFrame {
    
    ChonKhachHang_Repository repo_ChonKH = new ChonKhachHang_Repository();    
    DefaultTableModel modelKH = new DefaultTableModel();
    private int clickTableKhachHang = -1;
    BanHang_form form_BH; 
    public Chon_KH_View() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý Khách Hàng");
        btnChon.setEnabled(false);
        modelKH = (DefaultTableModel) tblKhachHang.getModel();        
        fillTable_KhachHang(repo_ChonKH.getAll_KhachHang());
    }
    public Chon_KH_View(BanHang_form banHang_form) {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Khách Hàng");
        btnChon.setEnabled(false);
        modelKH = (DefaultTableModel) tblKhachHang.getModel();        
        fillTable_KhachHang(repo_ChonKH.getAll_KhachHang());
        form_BH = banHang_form; 
    }

    public void fillTable_KhachHang(ArrayList<KhachHang> list) {
        modelKH.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        for (KhachHang kh : list) {
            modelKH.addRow(new Object[]{index.getAndIncrement(),
                kh.getMaKh(), kh.getTen(), kh.getSdt(), kh.isSex() == false ? "Nam" : "Nữ", kh.getDiaChi()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panel_Tong = new javax.swing.JTabbedPane();
        panel_DS_KhachHang = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        btnChon = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panel_CapNhat_KhachHang = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        panel_Tong.setBackground(new java.awt.Color(255, 255, 255));

        panel_DS_KhachHang.setBackground(new java.awt.Color(255, 255, 255));
        panel_DS_KhachHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã KH", "Tên KH", "SĐT", "Giới Tính", "Địa Chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setGridColor(new java.awt.Color(255, 255, 255));
        tblKhachHang.setRowHeight(30);
        tblKhachHang.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        panel_DS_KhachHang.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 400, 210));

        btnChon.setBackground(new java.awt.Color(153, 204, 255));
        btnChon.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnChon.setText("Chọn");
        btnChon.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });
        panel_DS_KhachHang.add(btnChon, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 80, -1));

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
        panel_DS_KhachHang.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 260, 20));

        jLabel1.setText("_____________________________________________________");
        panel_DS_KhachHang.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 260, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setText("Tìm Kiếm :");
        panel_DS_KhachHang.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, -1));

        panel_Tong.addTab("Danh Sách Khách Hàng", panel_DS_KhachHang);

        panel_CapNhat_KhachHang.setBackground(new java.awt.Color(255, 255, 255));
        panel_CapNhat_KhachHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel3.setText("Mã Khách Hàng :");
        panel_CapNhat_KhachHang.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 110, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel4.setText("Giới Tính :");
        panel_CapNhat_KhachHang.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 70, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel5.setText("Email :");
        panel_CapNhat_KhachHang.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 70, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel6.setText("Số Điện Thoại :");
        panel_CapNhat_KhachHang.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 100, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 102, 102));
        jLabel7.setText("Thêm Nhanh Khách Hàng");
        panel_CapNhat_KhachHang.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 180, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel8.setText("Địa Chỉ :");
        panel_CapNhat_KhachHang.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 70, -1));

        jLabel9.setText("_____________________________");
        panel_CapNhat_KhachHang.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 150, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel10.setText("Tên Khách Hàng :");
        panel_CapNhat_KhachHang.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 110, -1));

        jLabel11.setText("_____________________________");
        panel_CapNhat_KhachHang.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 150, -1));

        jLabel12.setText("_____________________________");
        panel_CapNhat_KhachHang.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 150, -1));

        jLabel13.setText("_____________________________");
        panel_CapNhat_KhachHang.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 150, -1));

        jLabel14.setText("_____________________________");
        panel_CapNhat_KhachHang.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 150, -1));

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");
        panel_CapNhat_KhachHang.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, -1, -1));

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        panel_CapNhat_KhachHang.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, -1, -1));

        txtSDT.setBorder(null);
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });
        panel_CapNhat_KhachHang.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 140, 20));

        txtEmail.setBorder(null);
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });
        panel_CapNhat_KhachHang.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 140, 20));

        txtMaKH.setBorder(null);
        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });
        txtMaKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaKHKeyReleased(evt);
            }
        });
        panel_CapNhat_KhachHang.add(txtMaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 140, 20));

        txtDiaChi.setBorder(null);
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });
        txtDiaChi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiaChiKeyReleased(evt);
            }
        });
        panel_CapNhat_KhachHang.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 140, 20));

        txtTenKH.setBorder(null);
        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });
        txtTenKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenKHKeyReleased(evt);
            }
        });
        panel_CapNhat_KhachHang.add(txtTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 140, 20));

        jButton1.setBackground(new java.awt.Color(153, 204, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jButton1.setText("Làm Mới");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panel_CapNhat_KhachHang.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 70, 30));

        jButton2.setBackground(new java.awt.Color(153, 204, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jButton2.setText("Thêm");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panel_CapNhat_KhachHang.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 70, 30));

        panel_Tong.addTab("Cập Nhật Khách Hàng", panel_CapNhat_KhachHang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_Tong, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_Tong)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        btnChon.setEnabled(true);
        clickTableKhachHang = tblKhachHang.getSelectedRow();
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // 
        
        if (txtTimKiem.getText().isEmpty()) {
            fillTable_KhachHang(repo_ChonKH.getAll_KhachHang());
            btnChon.setEnabled(false);
        } else {
            fillTable_KhachHang(repo_ChonKH.searchKH(txtTimKiem.getText()));
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased
    private Integer layId_KH_Click(){
        // lấy ra mã kh clcik ở tbl 
        String maKH_Click= tblKhachHang.getValueAt(clickTableKhachHang,1).toString();
        // so sánh để lấy ra id của kh đó
        for(KhachHang kh : repo_ChonKH.getAll_KhachHang()){
            if(kh.getMaKh().equals(maKH_Click)){
                return kh.getIdKH() ; 
            }
        }
        return null; 
    }
    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        // TODO add your handling code here:
        repo_ChonKH.themID_KH_Vao_HD(layId_KH_Click(),form_BH.layID_HD_CLick());
        dispose();
        MsgBox.showMessage(this,"Thêm Thành Công");
        form_BH.clickTable_HoaDon();
        
    }//GEN-LAST:event_btnChonActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTKeyReleased

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void txtMaKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaKHKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHKeyReleased

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtDiaChiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiaChiKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiKeyReleased

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void txtTenKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKHKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHKeyReleased
    private void Clear(){
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        buttonGroup1.clearSelection();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Clear();
    }//GEN-LAST:event_jButton1ActionPerformed
    public boolean isValidPhoneNumber(String phoneNumber) {
        // Biểu thức chính quy để kiểm tra số điện thoại
        String regex = "^0\\d{9,10}$";

        // Tạo Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Tạo Matcher object
        Matcher matcher = pattern.matcher(phoneNumber);

        // Kiểm tra xem số điện thoại có khớp với biểu thức chính quy không
        return matcher.matches();
    }
    
    private KhachHang _themKH(){
        KhachHang kh = KhachHang.builder()
                .maKh(txtMaKH.getText())
                .ten(txtTenKH.getText())
                .email(txtEmail.getText())
                .sdt(txtSDT.getText())
                .diaChi(txtDiaChi.getText())
                .sex(!rdoNam.isSelected())
                .build();
        return kh; 
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        boolean kt = true; 
        // check mã 
        for(KhachHang kh : repo_ChonKH.getAll_KhachHang()){
            if(kh.getMaKh().equals(txtMaKH.getText())){
                kt = false; 
                MsgBox.showMessage(this,"Mã Khách Hàng Đã Tồn Tại");
                break; 
            }
        }
        
        if(kt){
        if(validate_KH()){
            repo_ChonKH.Add_ThongTin_KH(_themKH());
            MsgBox.showMessage(this,"Thêm thành công");
            Clear();
            fillTable_KhachHang(repo_ChonKH.getAll_KhachHang());
        }
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    public boolean isValid_ChiTietEmail() {
        int atCount = txtEmail.getText().length() - txtEmail.getText().replace("@", "").length();
        if (atCount != 1) {
            MsgBox.showMessage(this, "Email phải và chỉ chứa một ký tự '@'.");
            return false;
        }

        // Tách phần tên người dùng và tên miền
        String[] parts = txtEmail.getText().split("@");
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
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean validate_KH(){
        if(txtMaKH.getText().isEmpty()){
            MsgBox.showMessage(this,"Vui Lòng Nhập Mã");
            return false; 
        }
        else if(txtTenKH.getText().isEmpty()){
            MsgBox.showMessage(this,"Vui Lòng Nhập Tên");
            return false; 
        }
         if(txtEmail.getText().isEmpty()){
            MsgBox.showMessage(this,"Vui Lòng Nhập Email");
            return false; 
        }
        if (!isValid_ChiTietEmail()) {
            return false;
        } 
        if(isValidEmail(txtEmail.getText())){
            
        }else {
            MsgBox.showMessage(this, "Không đúng định dạng Email");
            return false;
        }
         if(txtSDT.getText().isEmpty()){
            MsgBox.showMessage(this,"Vui Lòng Nhập SĐT");
            return false; 
        }
         try {
            Integer.parseInt(txtSDT.getText().trim());
        } catch (Exception e) {
            MsgBox.showMessage(this, "SĐT phải là dãy số");
            return false;
        }
         if (!isValidPhoneNumber(txtSDT.getText().trim())) {
            MsgBox.showMessage(this, "SĐT phải bắt  đầu là 0 và phải đủ 10 hoặc 11 số");
            return false;
        }
        if(txtDiaChi.getText().isEmpty()){
            MsgBox.showMessage(this,"Vui Lòng Nhập Địa Chỉ");
            return false; 
        }
        else if(rdoNam.isSelected() ==false && rdoNu.isSelected()== false){
            MsgBox.showMessage(this,"Vui Lòng Chọn Giới Tính");
            return false;
        }
        
        
        
        
        
        return true; 
    }
    
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
            java.util.logging.Logger.getLogger(Chon_KH_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chon_KH_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chon_KH_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chon_KH_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chon_KH_View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChon;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel_CapNhat_KhachHang;
    private javax.swing.JPanel panel_DS_KhachHang;
    private javax.swing.JTabbedPane panel_Tong;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
