package form;

import entity.ChucVu;
import entity.NhanVien;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import repository.ChucVu_Repository;
import repository.NhanVien_Repository;
import response.NhanVien_Response;
import ultil.ChuyenDoi;
import ultil.MsgBox;
import view.Menu_View;

public class NhanVien_form extends javax.swing.JInternalFrame {

    String strHinhAnh = null;
    String pathFile = null;
    private int index = -1;
    private DefaultTableModel model_lichSuBanHang = new DefaultTableModel(); 
    private DefaultTableModel model_NVMoi = new DefaultTableModel();
    private DefaultTableModel model_NVCu = new DefaultTableModel();
    private DefaultComboBoxModel dcbm_CV = new DefaultComboBoxModel();
    NhanVien_Repository repo_nv = new NhanVien_Repository();
    ChucVu_Repository repo_cv = new ChucVu_Repository();
    private Integer Selected_TKCV = 0;
    ChuyenDoi cd = new ChuyenDoi(); 
    public NhanVien_form() {
        initComponents();
        cauHinh_form();
        goiHam_Fill();
        updateStatus();

    }

    private void goiHam_Fill() {
        model_NVMoi = (DefaultTableModel) tblNhanVienMoi.getModel();
        model_NVCu = (DefaultTableModel) tblNhanVienCu.getModel();
        dcbm_CV = (DefaultComboBoxModel) cboChucVu.getModel();
        model_lichSuBanHang = (DefaultTableModel) tblLichSuBanHang.getModel(); 
        
        fillToTable_NVMoi(repo_nv.getAll_NV_Moi());
        fillToTable_NVCu(repo_nv.getAll_NV_Cu());
        fillCBO_CV_Model();
    }

    private void fillToTable_NVMoi(ArrayList<NhanVien_Response> listnv) {
        model_NVMoi.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        listnv.forEach(s -> model_NVMoi.addRow(new Object[]{index.getAndIncrement(),
            s.getMaNhanVien(), s.getHoTen(), s.isGioiTinh() == false ? "Nam" : "Nữ",
            s.getNgaySinh(), s.getDiaChi(),
            s.getEmail(), s.getSDT(), s.getChucVu()
        }));
    }
    private void fillToTable_lichSuBanHang(ArrayList<NhanVien_Response> listnv) {
        model_lichSuBanHang.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        listnv.forEach(s -> model_lichSuBanHang.addRow(new Object[]{index.getAndIncrement(),
            s.getMaNhanVien(),s.getMaHoaDon(),s.getTenKhachHang()
                ,s.getSdtKhachHang(),s.getNgayTao(),s.getNgayThanhToan(),
                cd.ChuyenDoiTien(s.getTongGia())
        }));
    }

    private void fillCBO_CV_Model() {
        dcbm_CV.removeAllElements();
        repo_cv.getAll_CV().forEach(cv -> dcbm_CV.addElement(cv.getChucVu()));
    }

    private void fillToTable_NVCu(ArrayList<NhanVien_Response> listnv) {
        AtomicInteger index = new AtomicInteger(1);
        model_NVCu.setRowCount(0);
        listnv.forEach(s -> model_NVCu.addRow(new Object[]{index.getAndIncrement(),
            s.getMaNhanVien(), s.getHoTen(), s.isGioiTinh() == false ? "Nam" : "Nữ",
            s.getNgaySinh(), s.getDiaChi(),
            s.getEmail(), s.getSDT(), s.getChucVu()
        }));
    }

    private void updateStatus() {
        btnXoaNV.setEnabled(false);
        btnKhoiPhucNVCu.setEnabled(false);
        btnSuaNV.setEnabled(false);
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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

    public boolean isValid_matKhau() {
        if (txtMatKhau.getText().length() < 8) {
            MsgBox.showMessage(this, "Mật khẩu phải có ít nhất 8 ký tự.");
            return false;
        }

        // Kiểm tra chữ hoa
        if (!txtMatKhau.getText().matches(".*[A-Z].*")) {
            MsgBox.showMessage(this, "Mật khẩu phải chứa ít nhất một chữ hoa.");
            return false;
        }

        // Kiểm tra chữ thường
        if (!txtMatKhau.getText().matches(".*[a-z].*")) {
            MsgBox.showMessage(this, "Mật khẩu phải chứa ít nhất một chữ thường.");
            return false;
        }

        // Kiểm tra số
        if (!txtMatKhau.getText().matches(".*\\d.*")) {
            MsgBox.showMessage(this, "Mật khẩu phải chứa ít nhất một số.");
            return false;
        }

        // Kiểm tra ký tự đặc biệt
        if (!txtMatKhau.getText().matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            MsgBox.showMessage(this, "Mật khẩu phải chứa ít nhất một ký tự đặc biệt.");
            return false;
        }
        return true;
    }

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

    private boolean Validate_form_NV() {

        if (txtMaNV.getText().isEmpty()) {
            MsgBox.showMessage(this, "Vui lòng nhập mã NV");
            return false;
        } else if (txtHoTen.getText().isEmpty()) {
            MsgBox.showMessage(this, "Vui lòng nhập Tên NV");
            return false;
        }else if (txtTaiKhoan.getText().isEmpty()) {
            MsgBox.showMessage(this, "Vui lòng nhập nhập Tài Khoản");
            return false;
        } else if (txtMatKhau.getText().isEmpty()) {
            MsgBox.showMessage(this, "Vui lòng nhập MK");
            return false;
        } else if (!isValid_matKhau()) {
            return false;
        } else if (txtXacNhanMatKhau.getText().isEmpty()) {
            MsgBox.showMessage(this, "Vui lòng Xác NhậnMK");
            return false;
        } else if (!(txtMatKhau.getText().equals(txtXacNhanMatKhau.getText()))) {
            MsgBox.showMessage(this, "Xác Nhận Mật khẩu K Chính Xác");
            return false;
        }  else if (txtEmail.getText().isEmpty()) {
            MsgBox.showMessage(this, "Vui lòng nhập Email");
            return false;
        } if (!isValid_ChiTietEmail()) {
            return false;
        }if (isValidEmail(txtEmail.getText())) {
            
        } else {
            MsgBox.showMessage(this, "Không đúng định dạng Email");
            return false;
        } if (txtDiaChi.getText().isEmpty()) {
            MsgBox.showMessage(this, "Vui lòng nhập Địa chỉ");
            return false;
        }  else if (txtSoDienThoai.getText().isEmpty()) {
            MsgBox.showMessage(this, "Vui lòng nhập SĐT");
            return false;
        } 
        try {
            Integer.parseInt(txtSoDienThoai.getText());
        } catch (Exception e) {
            MsgBox.showMessage(this, "SĐT phải là số");
            return false;
        } if (!isValidPhoneNumber(txtSoDienThoai.getText())) {
            MsgBox.showMessage(this, "SĐT phải bắt  đầu là 0 và phải đủ 10 hoặc 11 số");
            return false;
        }

        if (JDateNgaySinh.getDate() == null) {
            MsgBox.showMessage(this, "Vui lòng chọn Ngày Sinh");
            return false;
        }
        if (JDateNgaySinh.getDate().after(new Date())) {
            MsgBox.showMessage(this, "Ngày Sinh Không Được lớn hơn ngày hiện tại");
            return false;
        } 
        if (rdoNam.isSelected() == false && rdonu.isSelected() == false) {
            MsgBox.showMessage(this, "Chọn giới tính");
            return false;
        }       
        if (cboChucVu.getSelectedItem() == null) {
            MsgBox.showMessage(this, "Chọn Chức Vụ");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        JPanel_NhanVien = new javax.swing.JPanel();
        jTabbedNhanVien = new javax.swing.JTabbedPane();
        JPanel_NhanVienDangLam = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNhanVienMoi = new javax.swing.JTable();
        lblvien = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtTimKiemNVMoi = new javax.swing.JTextField();
        btnXoaNV = new javax.swing.JButton();
        btntraitrai = new javax.swing.JButton();
        btnphai_phai = new javax.swing.JButton();
        btnTrai = new javax.swing.JButton();
        btnPhai = new javax.swing.JButton();
        cboChucVuTimKiem = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLichSuBanHang = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        JPanel_NhanVienNghiViec = new javax.swing.JPanel();
        tableNVCu = new javax.swing.JScrollPane();
        tblNhanVienCu = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        txtTimKiemNVCu = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        btnKhoiPhucNVCu = new javax.swing.JButton();
        JPanelQLNV = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        rdonu = new javax.swing.JRadioButton();
        rdoNam = new javax.swing.JRadioButton();
        txtMatKhau = new javax.swing.JPasswordField();
        txtXacNhanMatKhau = new javax.swing.JPasswordField();
        btnSuaNV = new javax.swing.JButton();
        btnClearNV = new javax.swing.JButton();
        btnThemNV = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        JDateNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        cboChucVu = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Create.png"))); // NOI18N

        jLabel24.setText("jLabel24");

        setPreferredSize(new java.awt.Dimension(1200, 760));

        JPanel_NhanVien.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedNhanVien.setBackground(new java.awt.Color(255, 255, 255));

        JPanel_NhanVienDangLam.setBackground(new java.awt.Color(255, 255, 255));
        JPanel_NhanVienDangLam.setForeground(new java.awt.Color(255, 255, 255));

        tblNhanVienMoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã NV", "Họ Tên", "Giới Tính", "Ngày Sinh", "Địa Chỉ", "Email", "SĐT", "Chức Vụ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVienMoi.setRowHeight(30);
        tblNhanVienMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMoiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblNhanVienMoi);

        lblvien.setBackground(new java.awt.Color(153, 204, 255));
        lblvien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblvien.setText("____________________________________________________________________________");

        jLabel22.setBackground(new java.awt.Color(153, 204, 255));
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Lọc Chức Vụ :");

        txtTimKiemNVMoi.setFont(txtTimKiemNVMoi.getFont());
        txtTimKiemNVMoi.setBorder(null);
        txtTimKiemNVMoi.setCaretColor(new java.awt.Color(153, 204, 255));
        txtTimKiemNVMoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemNVMoiKeyReleased(evt);
            }
        });

        btnXoaNV.setBackground(new java.awt.Color(153, 204, 255));
        btnXoaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Trash.png"))); // NOI18N
        btnXoaNV.setText("Xóa");
        btnXoaNV.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNVActionPerformed(evt);
            }
        });

        btntraitrai.setBackground(new java.awt.Color(153, 204, 255));
        btntraitrai.setFont(new java.awt.Font("Source Sans Pro Black", 0, 12)); // NOI18N
        btntraitrai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/rewind.png"))); // NOI18N
        btntraitrai.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btntraitrai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntraitraiActionPerformed(evt);
            }
        });

        btnphai_phai.setBackground(new java.awt.Color(153, 204, 255));
        btnphai_phai.setFont(new java.awt.Font("Source Sans Pro Black", 0, 12)); // NOI18N
        btnphai_phai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/fast_forward.png"))); // NOI18N
        btnphai_phai.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnphai_phai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnphai_phaiActionPerformed(evt);
            }
        });

        btnTrai.setBackground(new java.awt.Color(153, 204, 255));
        btnTrai.setFont(new java.awt.Font("Source Sans Pro Black", 0, 12)); // NOI18N
        btnTrai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/skip_backward.png"))); // NOI18N
        btnTrai.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnTrai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraiActionPerformed(evt);
            }
        });

        btnPhai.setBackground(new java.awt.Color(153, 204, 255));
        btnPhai.setFont(new java.awt.Font("Source Sans Pro Black", 0, 12)); // NOI18N
        btnPhai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/skip_forward.png"))); // NOI18N
        btnPhai.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnPhai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhaiActionPerformed(evt);
            }
        });

        cboChucVuTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL\t", "Nhân Viên", "Trưởng Phòng" }));
        cboChucVuTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChucVuTimKiemActionPerformed(evt);
            }
        });

        jLabel26.setBackground(new java.awt.Color(153, 204, 255));
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Tìm Kiếm :");

        tblLichSuBanHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã NV", "Mã Hóa Đơn", "Tên Khách Hàng", "SĐT Khách Hàng", "Ngày Tạo", "Ngày Thanh Toán", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLichSuBanHang.setRowHeight(30);
        tblLichSuBanHang.setShowGrid(true);
        jScrollPane1.setViewportView(tblLichSuBanHang);

        jLabel21.setBackground(new java.awt.Color(153, 204, 255));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(119, 181, 243));
        jLabel21.setText("Lịch Sử Bán Hàng");

        javax.swing.GroupLayout JPanel_NhanVienDangLamLayout = new javax.swing.GroupLayout(JPanel_NhanVienDangLam);
        JPanel_NhanVienDangLam.setLayout(JPanel_NhanVienDangLamLayout);
        JPanel_NhanVienDangLamLayout.setHorizontalGroup(
            JPanel_NhanVienDangLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_NhanVienDangLamLayout.createSequentialGroup()
                .addGroup(JPanel_NhanVienDangLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanel_NhanVienDangLamLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(JPanel_NhanVienDangLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiemNVMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblvien, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(JPanel_NhanVienDangLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboChucVuTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(120, 120, 120)
                        .addComponent(btnXoaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPanel_NhanVienDangLamLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPanel_NhanVienDangLamLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(JPanel_NhanVienDangLamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btntraitrai, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTrai, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPhai, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnphai_phai, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        JPanel_NhanVienDangLamLayout.setVerticalGroup(
            JPanel_NhanVienDangLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_NhanVienDangLamLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(JPanel_NhanVienDangLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanel_NhanVienDangLamLayout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(JPanel_NhanVienDangLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTimKiemNVMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JPanel_NhanVienDangLamLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblvien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(JPanel_NhanVienDangLamLayout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cboChucVuTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPanel_NhanVienDangLamLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnXoaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(JPanel_NhanVienDangLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanel_NhanVienDangLamLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(JPanel_NhanVienDangLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btntraitrai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTrai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPhai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnphai_phai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanel_NhanVienDangLamLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jTabbedNhanVien.addTab("Nhân Viên Đang Làm Việc", JPanel_NhanVienDangLam);

        JPanel_NhanVienNghiViec.setBackground(new java.awt.Color(255, 255, 255));
        JPanel_NhanVienNghiViec.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblNhanVienCu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã NV", "Họ Tên", "Giới Tính", "Ngày Sinh", "Địa Chỉ", "Email", "SĐT", "Chức Vụ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVienCu.setRowHeight(30);
        tblNhanVienCu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienCuMouseClicked(evt);
            }
        });
        tableNVCu.setViewportView(tblNhanVienCu);

        JPanel_NhanVienNghiViec.add(tableNVCu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 670, 286));

        jLabel19.setBackground(new java.awt.Color(153, 204, 255));
        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("____________________________________________________________________________");
        JPanel_NhanVienNghiViec.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 280, 20));

        txtTimKiemNVCu.setFont(txtTimKiemNVCu.getFont());
        txtTimKiemNVCu.setBorder(null);
        txtTimKiemNVCu.setCaretColor(new java.awt.Color(153, 204, 255));
        txtTimKiemNVCu.setPreferredSize(new java.awt.Dimension(65, 20));
        txtTimKiemNVCu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemNVCuActionPerformed(evt);
            }
        });
        txtTimKiemNVCu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemNVCuKeyReleased(evt);
            }
        });
        JPanel_NhanVienNghiViec.add(txtTimKiemNVCu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 280, 20));

        jLabel20.setBackground(new java.awt.Color(153, 204, 255));
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Tìm Kiếm :");
        JPanel_NhanVienNghiViec.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 69, 20));

        btnKhoiPhucNVCu.setBackground(new java.awt.Color(153, 204, 255));
        btnKhoiPhucNVCu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        btnKhoiPhucNVCu.setText("Khôi Phục");
        btnKhoiPhucNVCu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnKhoiPhucNVCu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucNVCuActionPerformed(evt);
            }
        });
        JPanel_NhanVienNghiViec.add(btnKhoiPhucNVCu, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 100, 30));

        jTabbedNhanVien.addTab("Nhân Viên Đã Nghỉ Việc", JPanel_NhanVienNghiViec);

        JPanelQLNV.setBackground(new java.awt.Color(255, 255, 255));
        JPanelQLNV.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHinhAnh.setBackground(new java.awt.Color(255, 255, 255));
        lblHinhAnh.setForeground(new java.awt.Color(0, 51, 51));
        lblHinhAnh.setText("HinhAnh.png");
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(51, 51, 51), new java.awt.Color(204, 204, 204)));
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });
        JPanelQLNV.add(lblHinhAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 180, 190));

        jLabel2.setBackground(new java.awt.Color(153, 204, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Mã Nhân Viên");
        JPanelQLNV.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));

        jLabel3.setBackground(new java.awt.Color(153, 204, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Mật Khẩu");
        JPanelQLNV.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, -1, -1));

        jLabel4.setBackground(new java.awt.Color(153, 204, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Xác Nhận Mật Khẩu");
        JPanelQLNV.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, -1, -1));

        jLabel5.setBackground(new java.awt.Color(153, 204, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Giới Tính");
        JPanelQLNV.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 529, -1, -1));

        jLabel6.setBackground(new java.awt.Color(153, 204, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Email");
        JPanelQLNV.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 61, -1));

        jLabel7.setBackground(new java.awt.Color(153, 204, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Tài Khoản");
        JPanelQLNV.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 69, -1));

        jLabel8.setBackground(new java.awt.Color(153, 204, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Số Điện Thoại");
        JPanelQLNV.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 465, 80, -1));

        jLabel9.setBackground(new java.awt.Color(153, 204, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Chức Vụ");
        JPanelQLNV.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 530, -1, -1));

        jLabel10.setBackground(new java.awt.Color(153, 204, 255));
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Địa Chỉ");
        JPanelQLNV.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 390, 47, -1));

        jLabel12.setBackground(new java.awt.Color(153, 204, 255));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("_______________________");
        JPanelQLNV.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 367, 210, 20));

        jLabel13.setBackground(new java.awt.Color(153, 204, 255));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("_____________________________");
        JPanelQLNV.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 432, 194, -1));

        jLabel14.setBackground(new java.awt.Color(153, 204, 255));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("_____________________________");
        JPanelQLNV.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 194, -1));

        jLabel15.setBackground(new java.awt.Color(153, 204, 255));
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("_____________________________");
        JPanelQLNV.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, -1, -1));

        jLabel16.setBackground(new java.awt.Color(153, 204, 255));
        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("_____________________________");
        JPanelQLNV.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 194, -1));

        jLabel17.setBackground(new java.awt.Color(153, 204, 255));
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("_____________________________");
        JPanelQLNV.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 194, -1));

        jLabel18.setBackground(new java.awt.Color(153, 204, 255));
        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("_____________________________");
        JPanelQLNV.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 499, 194, -1));

        txtMaNV.setBorder(null);
        JPanelQLNV.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 180, 20));

        txtDiaChi.setBorder(null);
        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });
        JPanelQLNV.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 190, 20));

        txtHoTen.setBorder(null);
        JPanelQLNV.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 190, 20));

        txtEmail.setBorder(null);
        JPanelQLNV.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 190, 20));

        txtSoDienThoai.setBorder(null);
        txtSoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDienThoaiActionPerformed(evt);
            }
        });
        JPanelQLNV.add(txtSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 190, 20));

        buttonGroup2.add(rdonu);
        rdonu.setText("Nữ");
        JPanelQLNV.add(rdonu, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 550, 60, -1));

        buttonGroup2.add(rdoNam);
        rdoNam.setText("Nam");
        JPanelQLNV.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 60, -1));

        txtMatKhau.setBorder(null);
        JPanelQLNV.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 190, 20));

        txtXacNhanMatKhau.setBorder(null);
        JPanelQLNV.add(txtXacNhanMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 190, 20));

        btnSuaNV.setBackground(new java.awt.Color(153, 204, 255));
        btnSuaNV.setFont(new java.awt.Font("Source Sans Pro Black", 0, 12)); // NOI18N
        btnSuaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        btnSuaNV.setText("Sửa");
        btnSuaNV.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVActionPerformed(evt);
            }
        });
        JPanelQLNV.add(btnSuaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 620, 90, 40));

        btnClearNV.setBackground(new java.awt.Color(153, 204, 255));
        btnClearNV.setFont(new java.awt.Font("Source Sans Pro Black", 0, 12)); // NOI18N
        btnClearNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Bubble.png"))); // NOI18N
        btnClearNV.setText("Làm Mới");
        btnClearNV.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnClearNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearNVActionPerformed(evt);
            }
        });
        JPanelQLNV.add(btnClearNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 620, 110, 40));

        btnThemNV.setBackground(new java.awt.Color(153, 204, 255));
        btnThemNV.setFont(new java.awt.Font("Source Sans Pro Black", 0, 12)); // NOI18N
        btnThemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Create.png"))); // NOI18N
        btnThemNV.setText("Thêm");
        btnThemNV.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });
        JPanelQLNV.add(btnThemNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 620, 90, 40));

        jLabel23.setBackground(new java.awt.Color(153, 204, 255));
        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("_____________________________");
        JPanelQLNV.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 430, 194, 20));

        txtTaiKhoan.setBorder(null);
        txtTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaiKhoanActionPerformed(evt);
            }
        });
        JPanelQLNV.add(txtTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 190, 30));

        jLabel25.setBackground(new java.awt.Color(153, 204, 255));
        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Họ Và Tên");
        JPanelQLNV.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 334, 69, -1));

        JDateNgaySinh.setDateFormatString("dd-MM-yyyy");
        JPanelQLNV.add(JDateNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 480, 199, 30));

        jLabel11.setBackground(new java.awt.Color(153, 204, 255));
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Ngày Sinh");
        JPanelQLNV.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 460, -1, -1));

        JPanelQLNV.add(cboChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 550, 200, 30));

        jLabel27.setBackground(new java.awt.Color(153, 204, 255));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(119, 181, 243));
        jLabel27.setText("Thông Tin Nhân Viên");
        JPanelQLNV.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        javax.swing.GroupLayout JPanel_NhanVienLayout = new javax.swing.GroupLayout(JPanel_NhanVien);
        JPanel_NhanVien.setLayout(JPanel_NhanVienLayout);
        JPanel_NhanVienLayout.setHorizontalGroup(
            JPanel_NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_NhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPanelQLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JPanel_NhanVienLayout.setVerticalGroup(
            JPanel_NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedNhanVien)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanel_NhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPanelQLNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(JPanel_NhanVien, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemNVCuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemNVCuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemNVCuActionPerformed

    private void txtSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private NhanVien Xoa_NVMoi_Cu_TimKiem(int index1, ArrayList<NhanVien_Response> list) {
        index = -1;
        index = index1;
        if (index == -1) {
            return null;
        }
        NhanVien_Response nv = list.get(index);
        NhanVien nv_ = NhanVien.builder().maNhanVien(nv.getMaNhanVien()).build();
        btnXoaNV.setEnabled(false);
        btnKhoiPhucNVCu.setEnabled(false);
        this.index = index1;
        return nv_;
    }
    private void btnKhoiPhucNVCuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucNVCuActionPerformed
        //

        if (!txtTimKiemNVCu.getText().isEmpty()) {

            Click_NV_Moi_Cu(tblNhanVienCu.getSelectedRow(), repo_nv.search(txtTimKiemNVCu.getText(), 1));
            repo_nv.Update_TrangThaiNVCu_Moi(Xoa_NVMoi_Cu_TimKiem(
                    tblNhanVienCu.getSelectedRow(), repo_nv.search(txtTimKiemNVCu.getText(), 1)), 0);
            fillToTable_NVCu(repo_nv.getAll_NV_Cu());
            fillToTable_NVMoi(repo_nv.getAll_NV_Moi());
            txtTimKiemNVCu.setText("");
            ClearNV();
            return;
        }

        repo_nv.Update_TrangThaiNVCu_Moi(Xoa_NVMoi_Cu_TimKiem(tblNhanVienCu.getSelectedRow(), repo_nv.getAll_NV_Cu()), 0);
        fillToTable_NVCu(repo_nv.getAll_NV_Cu());
        fillToTable_NVMoi(repo_nv.getAll_NV_Moi());
        ClearNV();

    }//GEN-LAST:event_btnKhoiPhucNVCuActionPerformed


    private void btnXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNVActionPerformed

        if (!txtTimKiemNVMoi.getText().isEmpty()) {
            boolean check = MsgBox.showConfirm(this, "Xác Nhận Xóa Nhân Viên");
            if (check) {
                Click_NV_Moi_Cu(tblNhanVienMoi.getSelectedRow(), repo_nv.getAll_NV_Moi());
                repo_nv.Update_TrangThaiNVCu_Moi(Xoa_NVMoi_Cu_TimKiem(
                        tblNhanVienMoi.getSelectedRow(), repo_nv.search(txtTimKiemNVMoi.getText(), 0)), 1);
                fillToTable_NVCu(repo_nv.getAll_NV_Cu());
                fillToTable_NVMoi(repo_nv.getAll_NV_Moi());
                txtTimKiemNVMoi.setText("");
                ClearNV();
                MsgBox.showMessage(this, "Xóa Thành Công");
                return;
            }
        } else {
            if (Selected_TKCV == 0) {
                boolean check = MsgBox.showConfirm(this, "Xác Nhận Xóa Nhân Viên");
                if (check) {
                    repo_nv.Update_TrangThaiNVCu_Moi(Xoa_NVMoi_Cu_TimKiem(tblNhanVienMoi.getSelectedRow(), repo_nv.getAll_NV_Moi()), 1);
                    fillToTable_NVCu(repo_nv.getAll_NV_Cu());
                    fillToTable_NVMoi(repo_nv.getAll_NV_Moi());
                    ClearNV();
                    MsgBox.showMessage(this, "Xóa Thành Công");
                    return;
                }
            } else if (Selected_TKCV == 1) {
                boolean check = MsgBox.showConfirm(this, "Xác Nhận Xóa Nhân Viên");
                if (check) {
                    Click_NV_Moi_Cu(tblNhanVienMoi.getSelectedRow(), repo_nv.getALL_ChucVu(1));
                    repo_nv.Update_TrangThaiNVCu_Moi(Xoa_NVMoi_Cu_TimKiem(
                            tblNhanVienMoi.getSelectedRow(),
                            repo_nv.getALL_ChucVu(1)), 1);

                    fillToTable_NVCu(repo_nv.getAll_NV_Cu());
                    fillToTable_NVMoi(repo_nv.getALL_ChucVu(1));
                    MsgBox.showMessage(this, "Xóa Thành Công");
                    return;
                }
            } else if (Selected_TKCV == 2) {
                boolean check = MsgBox.showConfirm(this, "Xác Nhận Xóa Nhân Viên");
                if (check) {
                    Click_NV_Moi_Cu(tblNhanVienMoi.getSelectedRow(), repo_nv.getALL_ChucVu(2));

                    repo_nv.Update_TrangThaiNVCu_Moi(Xoa_NVMoi_Cu_TimKiem(
                            tblNhanVienMoi.getSelectedRow(),
                            repo_nv.getALL_ChucVu(2)), 1);
                    fillToTable_NVCu(repo_nv.getAll_NV_Cu());

                    fillToTable_NVMoi(repo_nv.getALL_ChucVu(2));
                    MsgBox.showMessage(this, "Xóa Thành Công");
                    return;
                }
            }
        }


    }//GEN-LAST:event_btnXoaNVActionPerformed

    private Integer lay_ChucVu(ArrayList<ChucVu> list) {
        for (ChucVu cv : list) {
            if (cboChucVu.getSelectedItem().equals(cv.getChucVu())) {
                return cv.getId_ChucVu();
            }
        }
        return null;
    }

    private NhanVien _add_ThongTin_NV() {
        NhanVien nv = NhanVien.builder()
                .maNhanVien(txtMaNV.getText())
                .taiKhoan(txtTaiKhoan.getText())
                .matKhau(txtMatKhau.getText())
                .hoTen(txtHoTen.getText())
                .gioiTinh(rdoNam.isSelected() == true ? false : true)
                .ngaySinh(JDateNgaySinh.getDate())
                .diaChi(txtDiaChi.getText())
                .SDT(txtSoDienThoai.getText())
                .hinhAnh(pathFile)
                .email(txtEmail.getText())
                .id_ChucVu(lay_ChucVu(repo_cv.getAll_CV()))
                .build();
        return nv;
    }

    private void GuiEmail_NhanVien() {
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

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("duocn940@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(txtEmail.getText())
            );
            message.setSubject("Thông Báo Cấp Tài Khoản Nhân viên ");
            message.setText(
                    "----Thông Tin Nhân Viên---- \n\n"
                    + "   Mã Nhân Viên:  " + txtMaNV.getText() + "\n"
                    + "   Tài Khoản :  " + txtTaiKhoan.getText() + "\n"
                    + "   Mật Khẩu :  " + txtMatKhau.getText() + "\n"
                    + "   Chức Vụ :  " + cboChucVu.getSelectedItem()
            );
            Transport.send(message);
            JOptionPane.showMessageDialog(this, "Gửi Mail Thành Công");
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        boolean kt = true;
        for (NhanVien_Response nv : repo_nv.getAll_NV_Moi_Cu()) {
            if (nv.getMaNhanVien().equalsIgnoreCase(txtMaNV.getText())) {
                MsgBox.showMessage(this, "Mã NV đã tồn tại");
                kt = false;
                break;
            }
            if (txtTaiKhoan.getText().equals(nv.getTaiKhoan())
                    && txtMatKhau.getText().equals(nv.getMatKhau())) {
                MsgBox.showMessage(this, "Tài Khoản Đã Tồn Tại");
                kt = false;
                break;
            }
        }

        // thực hiện thêm 
        if (kt) {
            if (Validate_form_NV()) {
                repo_nv.Add_ThongTin_NV(_add_ThongTin_NV());
                fillToTable_NVMoi(repo_nv.getAll_NV_Moi());
                boolean check = MsgBox.showConfirm(this, "    Thêm Thành Công\n"
                        + "Gửi Thông Báo Cho NV!");
                if (check) {
                    MsgBox.showMessage(this, "Vui Lòng Đợi Trong Vài Giây");
                    GuiEmail_NhanVien();
                }
                ClearNV();
            }
        }

    }//GEN-LAST:event_btnThemNVActionPerformed
    private int _sua_Theo_ID_NV(int index, ArrayList<NhanVien_Response> list) {
        NhanVien_Response nv = list.get(index);
        int ID_NV = nv.getId_NhanVien();
        this.index = index;
        return ID_NV;

    }
    private void btnSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVActionPerformed
        boolean kt = true, kt_TimKiem = true, kt_loc1 = true, kt_loc2 = true;
        NhanVien_Response nv_TimKiem = repo_nv.search(txtTimKiemNVMoi.getText(), 0).get(index);
        NhanVien_Response nv_ = repo_nv.getAll_NV_Moi().get(index);

        // kt nhân viênlọc
        if (txtTimKiemNVMoi.getText().isEmpty()) {
            if (Selected_TKCV == 0) {
                //  kiểm tra nv 
                for (NhanVien_Response nv : repo_nv.getAll_NV_Moi_Cu()) {
                    // kt mã
                    if (nv.getMaNhanVien().equalsIgnoreCase(txtMaNV.getText()) && !nv_.getMaNhanVien().equals(txtMaNV.getText())) {
                        MsgBox.showMessage(this, "Mã NV đã tồn tại");
                        kt = false;
                        break;
                    }
                    // kt tk mk
                    if ((txtTaiKhoan.getText().equals(nv.getTaiKhoan())
                            && txtMatKhau.getText().equals(nv.getMatKhau()))
                            && !(txtTaiKhoan.getText().equals(nv_.getTaiKhoan())
                            && txtMatKhau.getText().equals(nv_.getMatKhau()))) {
                        MsgBox.showMessage(this, "Tài Khoản Đã Tồn Tại");
                        kt = false;
                        break;
                    }
                }
                if (kt) {
                    // nhân viên thường 
                    if (Validate_form_NV()) {
                        boolean check = MsgBox.showConfirm(this, "Xác Nhận thay đổi thông tin Nhân Viên");
                        if (check) {
                            repo_nv.Update_ThongTin_NV(_add_ThongTin_NV(),
                                    _sua_Theo_ID_NV(tblNhanVienMoi.getSelectedRow(), repo_nv.getAll_NV_Moi()));

                            fillToTable_NVMoi(repo_nv.getAll_NV_Moi());

                            pathFile = null;
                            btnSuaNV.setEnabled(false);
                            btnXoaNV.setEnabled(false);

                            MsgBox.showMessage(this, "Update Thành Công");
                            return;
                        }
                    }
                }

            }// het chức vụ all
            else if (Selected_TKCV == 1) {
                NhanVien_Response nv_Loc1;
                try {
                    nv_Loc1 = repo_nv.getALL_ChucVu(1).get(index);
                } catch (Exception e) {
                    return;
                }
                for (NhanVien_Response nv : repo_nv.getAll_NV_Moi_Cu()) {

                    if (nv.getMaNhanVien().equalsIgnoreCase(txtMaNV.getText())
                            && !nv_Loc1.getMaNhanVien().equals(txtMaNV.getText())) {
                        MsgBox.showMessage(this, "Mã NV đã tồn tại");
                        kt_loc1 = false;
                        return;
                    }

                    if ((txtTaiKhoan.getText().equals(nv.getTaiKhoan())
                            && txtMatKhau.getText().equals(nv.getMatKhau()))
                            && !(txtTaiKhoan.getText().equals(nv_Loc1.getTaiKhoan())
                            && txtMatKhau.getText().equals(nv_Loc1.getMatKhau()))) {
                        MsgBox.showMessage(this, "Tài Khoản Đã Tồn Tại");
                        kt_loc1 = false;
                        return;
                    }

                }
                // update kt loc nv
                if (kt_loc1) {
                    if (Validate_form_NV()) {
                        boolean check = MsgBox.showConfirm(this, "Xác Nhận thay đổi thông tin Nhân Viên");
                        if (check) {
                            repo_nv.Update_ThongTin_NV(_add_ThongTin_NV(),
                                    _sua_Theo_ID_NV(tblNhanVienMoi.getSelectedRow(), repo_nv.getALL_ChucVu(1)));
                            fillToTable_NVMoi(repo_nv.getALL_ChucVu(1));
                            pathFile = null;
                            btnSuaNV.setEnabled(false);
                            btnXoaNV.setEnabled(false);
                            MsgBox.showMessage(this, "Update Thành Công");
                            return;
                        }
                    }
                }// hết lọc nv 1

            } //
            else if (Selected_TKCV == 2) {
                NhanVien_Response nv_Loc2;
                try {
                    nv_Loc2 = repo_nv.getALL_ChucVu(2).get(index);
                } catch (Exception e) {
                    return;
                }
                for (NhanVien_Response nv : repo_nv.getAll_NV_Moi_Cu()) {

                    if (nv.getMaNhanVien().equalsIgnoreCase(txtMaNV.getText())
                            && !nv_Loc2.getMaNhanVien().equals(txtMaNV.getText())) {
                        MsgBox.showMessage(this, "Mã NV đã tồn tại");

                        kt_loc2 = false;
                        return;

                    }

                    if ((txtTaiKhoan.getText().equals(nv.getTaiKhoan())
                            && txtMatKhau.getText().equals(nv.getMatKhau()))
                            && !(txtTaiKhoan.getText().equals(nv_Loc2.getTaiKhoan())
                            && txtMatKhau.getText().equals(nv_Loc2.getMatKhau()))) {
                        MsgBox.showMessage(this, "Tài Khoản Đã Tồn Tại");
                        kt_loc2 = false;

                        return;
                    }

                }

                // update kt loc nv2
                if (kt_loc2) {
                    if (Validate_form_NV()) {
                        boolean check = MsgBox.showConfirm(this, "Xác Nhận thay đổi thông tin Nhân Viên");
                        if (check) {
                            repo_nv.Update_ThongTin_NV(_add_ThongTin_NV(),
                                    _sua_Theo_ID_NV(tblNhanVienMoi.getSelectedRow(), repo_nv.getALL_ChucVu(2)));

                            fillToTable_NVMoi(repo_nv.getALL_ChucVu(2));
                            pathFile = null;
                            btnSuaNV.setEnabled(false);
                            btnXoaNV.setEnabled(false);
                            MsgBox.showMessage(this, "Update Thành Công");
                            return;
                        }
                    }
                }// hết lọc nv 2

            }// hết lọc nv 2
//        
//        
//       
//        }

        }// end nv ko tìm kiếm  
        // kt nhân viên tìm kiếm
        else if (!txtTimKiemNVMoi.getText().isEmpty()) {

            for (NhanVien_Response nv : repo_nv.getAll_NV_Moi_Cu()) {
                if (nv.getMaNhanVien().equalsIgnoreCase(txtMaNV.getText())
                        && !nv_TimKiem.getMaNhanVien().equals(txtMaNV.getText())) {
                    MsgBox.showMessage(this, "Mã NV đã tồn tại");
                    kt_TimKiem = false;
                    break;
                }

                if ((txtTaiKhoan.getText().equals(nv.getTaiKhoan())
                        && txtMatKhau.getText().equals(nv.getMatKhau()))
                        && !(txtTaiKhoan.getText().equals(nv_TimKiem.getTaiKhoan())
                        && txtMatKhau.getText().equals(nv_TimKiem.getMatKhau()))) {
                    MsgBox.showMessage(this, "Tài Khoản Đã Tồn Tại");
                    kt_TimKiem = false;
                    break;
                }

            }

            // tìm kiếm nv
            if (kt_TimKiem) {
                if (Validate_form_NV()) {
                    boolean check = MsgBox.showConfirm(this, "Xác Nhận thay đổi thông tin Nhân Viên");
                    if (check) {
                        repo_nv.Update_ThongTin_NV(_add_ThongTin_NV(),
                                _sua_Theo_ID_NV(tblNhanVienMoi.getSelectedRow(), repo_nv.search(txtTimKiemNVMoi.getText(), 0)));
                        fillToTable_NVMoi(repo_nv.search(txtTimKiemNVMoi.getText(), 0));
                        pathFile = null;
                        btnSuaNV.setEnabled(false);
                        btnXoaNV.setEnabled(false);
                        MsgBox.showMessage(this, "Update Thành Công");
                        return;
                    }
                }
            }
        }


    }//GEN-LAST:event_btnSuaNVActionPerformed
    private void ClearNV() {
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtHoTen.setText("");
        txtTaiKhoan.setText("");
        txtMaNV.setText("");
        txtMatKhau.setText("");
        txtXacNhanMatKhau.setText("");
        txtTimKiemNVMoi.setText("");
        txtTimKiemNVCu.setText("");
        txtSoDienThoai.setText("");
        cboChucVu.setSelectedItem(null);
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        JDateNgaySinh.setDate(null);
        lblHinhAnh.setText("HinhAnh.png");
        pathFile = null;
        lblHinhAnh.setIcon(null);
        btnThemNV.setEnabled(true);
        btnSuaNV.setEnabled(false);
    }
    
    private void btnClearNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearNVActionPerformed
        cboChucVuTimKiem.setSelectedIndex(0);
        ClearNV();
        btnXoaNV.setEnabled(false);
        btnKhoiPhucNVCu.setEnabled(false);
        model_lichSuBanHang.setRowCount(0);
    }//GEN-LAST:event_btnClearNVActionPerformed

    private void Click_NV_Moi_Cu(int index, ArrayList<NhanVien_Response> list) {

        NhanVien_Response nv = list.get(index);
        txtMaNV.setText(nv.getMaNhanVien());
        txtTaiKhoan.setText(nv.getTaiKhoan());
        txtDiaChi.setText(nv.getDiaChi());
        txtEmail.setText(nv.getEmail());
        txtHoTen.setText(nv.getHoTen());
        txtMatKhau.setText(nv.getMatKhau());
        txtXacNhanMatKhau.setText(nv.getMatKhau());
        txtSoDienThoai.setText(nv.getSDT());
        rdoNam.setSelected(!nv.isGioiTinh());
        rdonu.setSelected(nv.isGioiTinh());

        if (nv.getChucVu().equals("Nhân Viên")) {
            cboChucVu.setSelectedItem(nv.getChucVu());
        } else {
            cboChucVu.setSelectedItem(nv.getChucVu());
        }
        JDateNgaySinh.setDate(nv.getNgaySinh());
        try {
            ImageIcon Icon = new ImageIcon(nv.getHinhAnh());
            lblHinhAnh.setIcon(Icon);
            lblHinhAnh.setText("");
        } catch (Exception e) {
            lblHinhAnh.setIcon(null);
            lblHinhAnh.setText("Hình ảnh.png");
        }
        pathFile = nv.getHinhAnh();
        this.index = index;
    }
    private void tblNhanVienMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMoiMouseClicked
        btnXoaNV.setEnabled(true);
        btnThemNV.setEnabled(false);
        btnSuaNV.setEnabled(true);
        index = tblNhanVienMoi.getSelectedRow(); 
        String maNV = tblNhanVienMoi.getValueAt(index,1).toString();
       
        // hiển thị lên table lịch sử bán hàng 
        fillToTable_lichSuBanHang( repo_nv.getALL_lichSuBanHang(maNV));
        if (!txtTimKiemNVMoi.getText().isEmpty()) {

            Click_NV_Moi_Cu(tblNhanVienMoi.getSelectedRow(), repo_nv.search(txtTimKiemNVMoi.getText(), 0));
            return;
        } else {
            if (Selected_TKCV == 0) {
                Click_NV_Moi_Cu(tblNhanVienMoi.getSelectedRow(), repo_nv.getAll_NV_Moi());
                return;
            } else if (Selected_TKCV == 1) {
                Click_NV_Moi_Cu(tblNhanVienMoi.getSelectedRow(), repo_nv.getALL_ChucVu(1));
                return;
            } else if (Selected_TKCV == 2) {
                Click_NV_Moi_Cu(tblNhanVienMoi.getSelectedRow(), repo_nv.getALL_ChucVu(2));
                return;
            }
        }


    }//GEN-LAST:event_tblNhanVienMoiMouseClicked

    private void tblNhanVienCuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienCuMouseClicked
        btnKhoiPhucNVCu.setEnabled(true);
        btnThemNV.setEnabled(false);
        btnSuaNV.setEnabled(false);
        if (!txtTimKiemNVCu.getText().isEmpty()) {
            cboChucVuTimKiem.setSelectedIndex(0);
            Click_NV_Moi_Cu(tblNhanVienCu.getSelectedRow(), repo_nv.search(txtTimKiemNVCu.getText(), 1));
            return;
        }
        cboChucVuTimKiem.setSelectedIndex(0);
        Click_NV_Moi_Cu(tblNhanVienCu.getSelectedRow(), repo_nv.getAll_NV_Cu());
    }//GEN-LAST:event_tblNhanVienCuMouseClicked

    private String docHinhAnh_NV() {

        JFileChooser fileChooser = new JFileChooser("C:\\Users\\duocn\\OneDrive\\Âm nhạc\\Máy tính\\Dự Án 1_SD19304_Nhóm3\\QuanLyBanDienThoaiIphone\\src\\img_NhanVien");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // lấy đường dẫn file để lưu vào 1 trường trong csdl 
            pathFile = file.getAbsolutePath();
//            String pathFile1 = file.getAbsolutePath().replace("//","--");
            BufferedImage b;
            try {
                b = ImageIO.read(file);
                lblHinhAnh.setIcon(new ImageIcon(b));
                lblHinhAnh.setText("");
            } catch (Exception e) {
            }
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            lblHinhAnh.setText("HinhAnh.png");
            lblHinhAnh.setIcon(null);
            pathFile = null;
        }
        return pathFile;// file lưu vào csdl 

    }
    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked
        docHinhAnh_NV();
    }//GEN-LAST:event_lblHinhAnhMouseClicked

    private void btnPhaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhaiActionPerformed
        if (tblNhanVienMoi.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không có nhân viên");
            return;
        }
        if (index == tblNhanVienMoi.getRowCount() - 1) {
            index = -1;
        }

        Click_NV_Moi_Cu(++index, repo_nv.getAll_NV_Moi());
        btnThemNV.setEnabled(false);
        btnSuaNV.setEnabled(false);
    }//GEN-LAST:event_btnPhaiActionPerformed

    private void btntraitraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntraitraiActionPerformed
        if (tblNhanVienMoi.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không có nhân viên");
            return;
        }

        // nv thường 
        index = 0;
        Click_NV_Moi_Cu(index, repo_nv.getAll_NV_Moi());
        btnThemNV.setEnabled(false);
        btnSuaNV.setEnabled(false);
    }//GEN-LAST:event_btntraitraiActionPerformed

    private void btnphai_phaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnphai_phaiActionPerformed
        if (tblNhanVienMoi.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không có nhân viên");
            return;
        }

        // nhân viên thường
        index = tblNhanVienMoi.getRowCount() - 1;

        try {
            Click_NV_Moi_Cu(index, repo_nv.getAll_NV_Moi());
        } catch (Exception e) {
        }
        btnThemNV.setEnabled(false);
        btnSuaNV.setEnabled(false);

    }//GEN-LAST:event_btnphai_phaiActionPerformed

    private void btnTraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraiActionPerformed
        if (tblNhanVienMoi.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không có nhân viên");
            return;
        }
        if (index == -1) {
            index = tblNhanVienMoi.getRowCount() - 1;
        }
        try {
            Click_NV_Moi_Cu(--index, repo_nv.getAll_NV_Moi());
        } catch (Exception e) {

        }
        btnThemNV.setEnabled(false);
        btnSuaNV.setEnabled(false);

    }//GEN-LAST:event_btnTraiActionPerformed

    private void txtTimKiemNVMoiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemNVMoiKeyReleased
        if (!txtTimKiemNVMoi.getText().isEmpty()) {
            btnXoaNV.setEnabled(false);
            if (Selected_TKCV != 0) {
                Selected_TKCV = 0;
                cboChucVuTimKiem.setSelectedIndex(0);
                
            }
        }

        fillToTable_NVMoi(repo_nv.search(txtTimKiemNVMoi.getText(), 0));
        int kt = 0;
        if (kt == tblNhanVienMoi.getRowCount()) {
            btnXoaNV.setEnabled(false);
        }
        model_lichSuBanHang.setRowCount(0);
    }//GEN-LAST:event_txtTimKiemNVMoiKeyReleased

    private void txtTimKiemNVCuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemNVCuKeyReleased
        if (!txtTimKiemNVCu.getText().isEmpty()) {
            btnKhoiPhucNVCu.setEnabled(false);
        }

        fillToTable_NVCu(repo_nv.search(txtTimKiemNVCu.getText(), 1));
        int kt = 0;
        if (kt == tblNhanVienCu.getRowCount()) {
            btnXoaNV.setEnabled(false);
        }

    }//GEN-LAST:event_txtTimKiemNVCuKeyReleased

    private void txtTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaiKhoanActionPerformed

    private void cboChucVuTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChucVuTimKiemActionPerformed
        // TODO add your handling code here:
        txtTimKiemNVMoi.setText("");
        Selected_TKCV = (Integer) cboChucVuTimKiem.getSelectedIndex();

        if (Selected_TKCV == 0) {

            fillToTable_NVMoi(repo_nv.getAll_NV_Moi());

            btntraitrai.setEnabled(true);
            btnphai_phai.setEnabled(true);
            btnPhai.setEnabled(true);
            btnTrai.setEnabled(true);
            return;
        } else if (Selected_TKCV == 1) {
            // nhân viên 

            fillToTable_NVMoi(repo_nv.getALL_ChucVu(1));
            btntraitrai.setEnabled(false);
            btnphai_phai.setEnabled(false);
            btnPhai.setEnabled(false);
            btnTrai.setEnabled(false);
        } else if (Selected_TKCV == 2) {
            // trưởng phòng

            fillToTable_NVMoi(repo_nv.getALL_ChucVu(2));
            btntraitrai.setEnabled(false);
            btnphai_phai.setEnabled(false);
            btnPhai.setEnabled(false);
            btnTrai.setEnabled(false);
        }
    }//GEN-LAST:event_cboChucVuTimKiemActionPerformed
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
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
    private com.toedter.calendar.JDateChooser JDateNgaySinh;
    private javax.swing.JPanel JPanelQLNV;
    private javax.swing.JPanel JPanel_NhanVien;
    private javax.swing.JPanel JPanel_NhanVienDangLam;
    private javax.swing.JPanel JPanel_NhanVienNghiViec;
    private javax.swing.JButton btnClearNV;
    private javax.swing.JButton btnKhoiPhucNVCu;
    private javax.swing.JButton btnPhai;
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnTrai;
    private javax.swing.JButton btnXoaNV;
    private javax.swing.JButton btnphai_phai;
    private javax.swing.JButton btntraitrai;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JComboBox<String> cboChucVuTimKiem;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedNhanVien;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblvien;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdonu;
    private javax.swing.JScrollPane tableNVCu;
    private javax.swing.JTable tblLichSuBanHang;
    private javax.swing.JTable tblNhanVienCu;
    private javax.swing.JTable tblNhanVienMoi;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTimKiemNVCu;
    private javax.swing.JTextField txtTimKiemNVMoi;
    private javax.swing.JPasswordField txtXacNhanMatKhau;
    // End of variables declaration//GEN-END:variables
}
