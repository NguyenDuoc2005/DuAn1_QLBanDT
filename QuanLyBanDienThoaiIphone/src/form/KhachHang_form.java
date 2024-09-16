package form;

import entity.KhachHang;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import repository.GiaoDich_Repository;
import repository.KhachHang_Repository;
import response.GiaoDich_Response;
import response.KhachHang_Response;
import ultil.ChuyenDoi;
import ultil.MsgBox;


public final class KhachHang_form extends javax.swing.JInternalFrame {
    ChuyenDoi cd = new ChuyenDoi();
    int index = -1;
    private DefaultTableModel dtm1 = new DefaultTableModel();
    private DefaultTableModel dtm2 = new DefaultTableModel();

    KhachHang_Repository repo_kh = new KhachHang_Repository();
    GiaoDich_Repository repo_gd = new GiaoDich_Repository();
   public KhachHang_form() {
        initComponents();
        cauHinh_form();
        goiHam_Fill();
    }
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
   private void goiHam_Fill(){
       dtm1 = (DefaultTableModel) tbl1.getModel();
       dtm2 = (DefaultTableModel) tbl2.getModel();
       fillToTable_TonTai(repo_kh.getAll_Kh());
       
   }
   private void fillToTable_GD(ArrayList<GiaoDich_Response> listGD){
       AtomicInteger index = new AtomicInteger(1);
       dtm2.setRowCount(0);
       listGD.forEach(s ->{
           String trangThai = null; 
          if(s.getTrangThai() ==1){
              trangThai ="Đã Thanh Toán";
          }else if(s.getTrangThai()==0){
              trangThai ="Chưa Thanh Toán";
          }else if(s.getTrangThai()==2){
              trangThai = "Đã Hủy";
          }
           dtm2.addRow(new Object[]{index.getAndIncrement(),
           s.getMaKh(),s.getTen(),s.getSdt(),s.getNgayTao(),s.getNgayTT(),s.getMaHD(),cd.ChuyenDoiTien(s.getTong()),
           trangThai
            });
       });
   }
   private void fillToTable_TonTai(ArrayList<KhachHang_Response> listkh){
       
       dtm1.setRowCount(0);
       listkh.forEach(s -> dtm1.addRow(new Object[]{
           s.getIdKH(), s.getMaKh(),s.getTen(),s.getSdt(),s.isSex() == false? "Nam" : "Nữ", s.getEmail(),s.getDiaChi()
       }));
   }
    public void cauHinh_form(){
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoGirl = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        btnSearch = new java.awt.Button();
        txtSearch = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        btnThem = new java.awt.Button();
        btnSua = new java.awt.Button();
        button3 = new java.awt.Button();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Mã Khách Hàng");

        jLabel3.setText("Tên Khách Hàng");

        jLabel4.setText("Số điện thoại");

        jLabel5.setText("Giới tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoGirl);
        rdoGirl.setText("Nữ");

        jLabel6.setText("Email");

        jLabel7.setText("Địa chỉ");

        btnSearch.setBackground(new java.awt.Color(153, 204, 255));
        btnSearch.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        btnSearch.setLabel("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addGap(33, 33, 33)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)
                        .addGap(31, 31, 31)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel4)
                        .addGap(50, 50, 50)
                        .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel5)
                        .addGap(75, 75, 75)
                        .addComponent(rdoNam)
                        .addGap(11, 11, 11)
                        .addComponent(rdoGirl))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel6)
                        .addGap(88, 88, 88)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addGap(80, 80, 80)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel2))
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel3))
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4))
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5))
                    .addComponent(rdoNam)
                    .addComponent(rdoGirl))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel6))
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel7))
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(817, 12, 370, 410));

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Khách hàng", "Mã Khách hàng", "Tên khách hàng", "Số điện thoại", "Giới tính", "Email", "Địa chỉ"
            }
        ));
        tbl1.setRowHeight(30);
        tbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl1);

        jTabbedPane1.addTab("Danh sách khách hàng", jScrollPane1);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 60, 799, 359));

        jLabel8.setBackground(new java.awt.Color(255, 153, 153));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 102, 102));
        jLabel8.setText("Thông Tin Khách Hàng");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 6, -1, -1));

        tbl2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã khách hàng", "Tên", "SĐT", "Ngày tạo", "Ngày thanh toán", "Mã hoá đơn", "Tổng", "Trạng thái"
            }
        ));
        tbl2.setToolTipText("");
        tbl2.setRowHeight(30);
        jScrollPane3.setViewportView(tbl2);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 479, 776, 221));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 102, 102));
        jLabel9.setText("Lịch Sử Giao Dịch");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 425, -1, -1));

        btnThem.setBackground(new java.awt.Color(153, 204, 255));
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnThem.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        btnThem.setLabel("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 480, 87, 40));

        btnSua.setBackground(new java.awt.Color(153, 204, 255));
        btnSua.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        btnSua.setLabel("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 480, 87, 37));

        button3.setBackground(new java.awt.Color(153, 204, 255));
        button3.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        button3.setLabel("Clear");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        jPanel1.add(button3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 480, 86, 38));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void clear(){
        txtEmail.setText("");
        buttonGroup1.clearSelection();
        txtDiaChi.setText("");
     
        txtMa.setText("");
        txtTen.setText("");
        txtSdt.setText("");
        btnThem.setEnabled(true);
        btnSua.setEnabled(true);
    }
    private boolean Validate_form_KH(){
        String email_Pattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    Pattern pattern = Pattern.compile(email_Pattern);
    Matcher matcher = pattern.matcher(txtEmail.getText());

    if (txtMa.getText().trim().isEmpty()) {
        MsgBox.showMessage(this, "Mã khách hàng không được trống");
        txtMa.requestFocus();
        return false;
    } else if (txtTen.getText().trim().isEmpty()) {
        MsgBox.showMessage(this, "Tên khách hàng không được trống");
        txtTen.requestFocus();
        return false;
    } else if (txtSdt.getText().trim().isEmpty()) {
        MsgBox.showMessage(this, "Số điện thoại không được trống");
        
        txtSdt.requestFocus();
        return false;
    } 
    if(!isValidPhoneNumber(txtSdt.getText())){
        JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu bằng 0 và có 10 hoặc 11 số");
        return false; 
    }
    if (!txtSdt.getText().matches("0[0-9]{9}")) {
        MsgBox.showMessage(this, "Số điện thoại không đúng định dạng");
        txtSdt.requestFocus();
        return false;
    }else if(!rdoGirl.isSelected() && !rdoNam.isSelected()){
        MsgBox.showMessage(this, "Vui lòng chọn giới tính");
        return false;
    }
    else if (txtEmail.getText().trim().isEmpty()) {
        MsgBox.showMessage(this, "Email không được trống");
        txtEmail.requestFocus();
        return false;
    } else if (!matcher.matches()) {
        MsgBox.showMessage(this, "Email không hợp lệ. Vui lòng nhập đúng định dạng email (ví dụ: example@domain.com).");
        txtEmail.requestFocus();
        return false;
    } else if (txtDiaChi.getText().trim().isEmpty()) {
        MsgBox.showMessage(this, "Địa chỉ không được trống");
        txtDiaChi.requestFocus();
        return false;
    }else {
        return true; // Trả về true nếu tất cả các kiểm tra đều đạt
    }
    }
    private KhachHang _add_ThongTin_KH(){
        KhachHang kh = KhachHang.builder()
                
                .maKh(txtMa.getText())
                .ten(txtTen.getText())
                .sdt(txtSdt.getText())
                .diaChi(txtDiaChi.getText())
                .email(txtEmail.getText())
                .sex(rdoNam.isSelected() == false ? true : false)
                
                
                .build();
        return kh;
    }
    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed

     clear();
    }//GEN-LAST:event_button3ActionPerformed

    private void filterTable(String searchText) {
    DefaultTableModel model1 = (DefaultTableModel) tbl1.getModel();
    DefaultTableModel model2 = (DefaultTableModel) tbl2.getModel();

    TableRowSorter<DefaultTableModel> sorter1 = new TableRowSorter<>(model1);
    TableRowSorter<DefaultTableModel> sorter2 = new TableRowSorter<>(model2);

    tbl1.setRowSorter(sorter1);
    tbl2.setRowSorter(sorter2);

    if (searchText.trim().length() == 0) {
        sorter1.setRowFilter(null);
//        sorter2.setRowFilter(null);
    } else {
        sorter1.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
//        sorter2.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
    }
//    txtMa.setEditable();
   
}
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        boolean kt = true;
        for(KhachHang_Response kh : repo_kh.getAll_Kh()){
            if(kh.getMaKh().equalsIgnoreCase(txtMa.getText())){
                 MsgBox.showMessage(this, "Mã KH đã tồn tại");
                kt = false;
                break;
            }
        }
        if(kt){
            if(Validate_form_KH()){
                repo_kh.Add_ThongTin_KH(_add_ThongTin_KH());
                fillToTable_TonTai(repo_kh.getAll_KhachHang());
//                fillToTable_Xoa(repo_kh.getAll_Kh_Daxoa());
                MsgBox.showMessage(this, "Thêm Thành Công");
                clear();
              
            }
        }
        
    }//GEN-LAST:event_btnThemActionPerformed

    private void tbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseClicked
        int selectedRow = tbl1.getSelectedRow();
    if (selectedRow != -1) {
       
        txtMa.setText(tbl1.getValueAt(selectedRow, 1).toString());
        txtTen.setText(tbl1.getValueAt(selectedRow, 2).toString());
        txtSdt.setText(tbl1.getValueAt(selectedRow, 3).toString());
        String gender = tbl1.getValueAt(selectedRow, 4).toString();
        if (gender.equals("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoGirl.setSelected(true);
        }
        txtEmail.setText(tbl1.getValueAt(selectedRow, 5).toString());
        txtDiaChi.setText(tbl1.getValueAt(selectedRow, 6).toString());
        
    }
  
    
    // end 
    index = tbl1.getSelectedRow();
    String ma = tbl1.getValueAt(index,1).toString();
        fillToTable_GD(repo_gd.getAll_Gd(ma));
    
    
    
    }//GEN-LAST:event_tbl1MouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchText = txtSearch.getText().toLowerCase();
    filterTable(searchText);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (txtMa.getText().trim().isEmpty()) {
        MsgBox.showMessage(this, "Vui lòng chọn khách hàng để sửa");
        return;
    }
    
    // Khóa trường mã khách hàng để không thể chỉnh sửa
    txtMa.setEditable(false);
    
    
    // Đặt nút "Sửa" thành không hoạt động, và kích hoạt nút "Cập nhật" nếu cần

    if (Validate_form_KH()) {
        KhachHang updatedKh = KhachHang.builder()
             // Giả sử có ID khách hàng để cập nhật
            .maKh(txtMa.getText())
            .ten(txtTen.getText())
            .sdt(txtSdt.getText())
            .diaChi(txtDiaChi.getText())
            .email(txtEmail.getText())
            .sex(rdoNam.isSelected() == false ? true : false)
            .build();
       

        // Cập nhật dữ liệu vào cơ sở dữ liệu
         repo_kh.Update_ThongTin_KH(updatedKh);

        // Cập nhật bảng dữ liệu
        fillToTable_TonTai(repo_kh.getAll_KhachHang());
        
        MsgBox.showMessage(this, "Cập nhật thành công");
        clear(); // Xóa các trường dữ liệu nếu cần
        
        // Khôi phục khả năng chỉnh sửa
        txtMa.setEditable(true);
 
    }
    }//GEN-LAST:event_btnSuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnSearch;
    private java.awt.Button btnSua;
    private java.awt.Button btnThem;
    private java.awt.Button button3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoGirl;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JTable tbl1;
    private javax.swing.JTable tbl2;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
