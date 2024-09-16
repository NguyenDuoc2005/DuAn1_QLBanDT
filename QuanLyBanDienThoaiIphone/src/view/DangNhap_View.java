package view;


import entity.NhanVien;
import entity.ToanCuc_NV;
import form.NhanVien_form;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import repository.NhanVien_Repository;
import response.NhanVien_Response;

import ultil.MsgBox;

public class DangNhap_View extends javax.swing.JFrame {

    public DangNhap_View() {
        initComponents();
        setTitle("Quản Lý Bán Điện Thoại Iphone");// đặt tên cho tiêu đề 
    }

    private boolean validate_form_DangNhap() {
        if (txtUserName.getText().isEmpty()) {
            MsgBox.showMessage(this, "Chưa nhập UserName");
            return false;
        } else if (txtPassWord.getText().isEmpty()) {
            MsgBox.showMessage(this, "Chưa nhập PassWord");
            return false;
        }

        return true;
    }
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtPassWord = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        ckHienPass = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 330));
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(250, 320));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iphone-14-pro-max.jpg"))); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        getContentPane().add(jPanel3, java.awt.BorderLayout.LINE_START);

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Login");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 76, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("UserName");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("PassWord");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("______________________________________________");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 230, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Key.png"))); // NOI18N
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User.png"))); // NOI18N
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, -1, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("______________________________________________");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 230, 20));

        txtUserName.setBackground(new java.awt.Color(153, 204, 255));
        txtUserName.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtUserName.setText("taikhoan2");
        txtUserName.setBorder(null);
        txtUserName.setCaretColor(new java.awt.Color(255, 255, 255));
        txtUserName.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel4.add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 220, -1));

        txtPassWord.setBackground(new java.awt.Color(153, 204, 255));
        txtPassWord.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtPassWord.setText("Matkhau2@");
        txtPassWord.setBorder(null);
        txtPassWord.setCaretColor(new java.awt.Color(255, 255, 255));
        txtPassWord.setPreferredSize(new java.awt.Dimension(65, 20));
        txtPassWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassWordActionPerformed(evt);
            }
        });
        jPanel4.add(txtPassWord, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 200, 20));

        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(102, 204, 255));
        btnLogin.setText("LOGIN");
        btnLogin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel4.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 150, 40));

        jLabel9.setFont(new java.awt.Font("Roboto Slab Light", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Hello! Let's get started");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));

        ckHienPass.setBackground(new java.awt.Color(153, 204, 255));
        ckHienPass.setForeground(new java.awt.Color(255, 255, 255));
        ckHienPass.setText("Hiện Mật Khẩu");
        ckHienPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ckHienPassMouseClicked(evt);
            }
        });
        ckHienPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckHienPassActionPerformed(evt);
            }
        });
        jPanel4.add(ckHienPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
  
    public void Login() {
   
        NhanVien_Repository repo_NV = new NhanVien_Repository();
        ArrayList<NhanVien_Response> list_NV_Cu = repo_NV.getAll_NV_Cu();
        ArrayList<NhanVien_Response> list_NV_Moi = repo_NV.getAll_NV_Moi();
        ArrayList<NhanVien_Response> list_NV_Moi_Cu = repo_NV.getAll_NV_Moi_Cu();
        // Kiểm Tra tài khoản nv cũ 
        for(NhanVien_Response nv_: list_NV_Cu){
             if (txtUserName.getText().equals(nv_.getTaiKhoan())
                    && txtPassWord.getText().equals(nv_.getMatKhau())) {
                 MsgBox.showMessage(this,"Tài Khoản Đã Ngừng Hoạt Động!");
                 return;     
             }
        }
         
        
        // kt tk nhân viên mới 
        for (NhanVien_Response nv : list_NV_Moi) {
            if (txtUserName.getText().equals(nv.getTaiKhoan())
  
                    && txtPassWord.getText().equals(nv.getMatKhau())) {
                // đúng tài khoản  thêm thông tin nv vào biến toàn cục          
                // 
                
                ToanCuc_NV tc = new ToanCuc_NV();
                tc.setId_NhanVien(nv.getId_NhanVien());
                tc.setMaNhanVien(nv.getMaNhanVien());
                tc.setHoTen(nv.getHoTen());
                tc.setTaiKhoan(nv.getTaiKhoan());
                tc.setMatKhau(nv.getMatKhau());
                tc.setId_ChucVu(nv.getId_ChucVu());
                tc.setTenChucVu(nv.getChucVu());
                tc.setHinhAnh(nv.getHinhAnh());
                    new Menu_View().setVisible(true);
                    dispose();
                    return; 
            }
        }
        JOptionPane.showMessageDialog(this, "Sai UserName hoặc PassWord!");
        return ;
    }
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // check         
       if (validate_form_DangNhap()) {
            Login();
        }

    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtPassWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassWordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassWordActionPerformed

    private void ckHienPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckHienPassMouseClicked
        // TODO add your handling code here:
        if (ckHienPass.isSelected()) {
         txtPassWord.setEchoChar((char) 0);
        } else {
           txtPassWord.setEchoChar('\u2022');  
        }
    }//GEN-LAST:event_ckHienPassMouseClicked

    private void ckHienPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckHienPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckHienPassActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhap_View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox ckHienPass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField txtPassWord;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
