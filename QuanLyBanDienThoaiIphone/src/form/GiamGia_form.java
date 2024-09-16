package form;

import entity.GiamGia;
import entity.ToanCuc_NV;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import repository.GiamGia_Repostiory;
import ultil.ChuyenDoi;
import ultil.MsgBox;

public class GiamGia_form extends javax.swing.JInternalFrame {
     private boolean isSearching = false;
    ChuyenDoi cd = new ChuyenDoi();
    int index = -1;
    private DefaultTableModel model_TatCa = new DefaultTableModel();
    private DefaultTableModel model_DangDienRa = new DefaultTableModel();
    private DefaultTableModel model_SapDienRa = new DefaultTableModel();
    private DefaultTableModel model_DaKetThuc = new DefaultTableModel();
    private DefaultTableModel model_DaXoa = new DefaultTableModel();

    GiamGia_Repostiory repo_gg = new GiamGia_Repostiory();

    public GiamGia_form() {
        initComponents();
        cauHinh_form();
        goiHam_Fill();
    }

    public void cauHinh_form() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }

    private void goiHam_Fill() {
        model_TatCa = (DefaultTableModel) tblTatca.getModel();
        model_DangDienRa = (DefaultTableModel) tblDangDienRa.getModel();
        model_SapDienRa = (DefaultTableModel) tblSapDienRa.getModel();
        model_DaKetThuc = (DefaultTableModel) tblDaKetThuc.getModel();
        model_DaXoa = (DefaultTableModel) tblDaXoa.getModel();

        fillToTable_TatCa(repo_gg.getAll());
        fillToTable_DangDienRa(repo_gg.getAll_DangDienRa());
        fillToTable_SapDienRa(repo_gg.getAll_SapDienRa());
        fillToTable_DaKetThuc(repo_gg.getAll_DaKetThuc());
        fillToTable_DaXoa(repo_gg.getAll_DaXoa());
    }

    private void fillToTable_TatCa(List<GiamGia> data) {
        DefaultTableModel model = (DefaultTableModel) tblTatca.getModel();
        model.setRowCount(0);
        for (GiamGia gg : data) {
            String thoiGianDienRa = gg.getNgay_Bat_Dau() + " đến " + gg.getNgay_Ket_Thuc();
            String trangThai = determineStatus(gg);
            String mucGiam = gg.getMuc_Giam_Gia() + " " + (gg.getKieu_Giam() == 0 ? "%" : "đ");
            if (gg.getKieu_Giam() == 0) {
                mucGiam += " Tối đa: " + gg.getMuc_Giam_Gia_Toi_Da() + "đ";
            }
            model.addRow(new Object[]{
                gg.getTen_Chuong_Trinh(),
                gg.getMa_Giam_Gia(),
                mucGiam,
                trangThai,
                cd.ChuyenDoiTien(gg.getGia_tri_DH_Toi_Thieu()),
                thoiGianDienRa
            });
        }
        tblTatca.revalidate();  // Cập nhật giao diện sau khi thay đổi dữ liệu
        tblTatca.repaint();     // Vẽ lại bảng để phản ánh thay đổi
    }

    private void fillToTable_DangDienRa(List<GiamGia> data) {
        DefaultTableModel model = (DefaultTableModel) tblDangDienRa.getModel();
        model.setRowCount(0);
        for (GiamGia gg : data) {
            String thoiGianDienRa = gg.getNgay_Bat_Dau() + " đến " + gg.getNgay_Ket_Thuc();
            String trangThai = determineStatus(gg);
            String mucGiam = gg.getMuc_Giam_Gia() + " " + (gg.getKieu_Giam() == 0 ? "%" : "đ");
            if (gg.getKieu_Giam() == 0) {
                mucGiam += " Tối đa: " + gg.getMuc_Giam_Gia_Toi_Da() + "đ";
            }
            model.addRow(new Object[]{
                gg.getTen_Chuong_Trinh(),
                gg.getMa_Giam_Gia(),
                mucGiam,
                trangThai,
                cd.ChuyenDoiTien(gg.getGia_tri_DH_Toi_Thieu()),
                thoiGianDienRa
            });
        }
        tblDangDienRa.revalidate();  // Cập nhật giao diện sau khi thay đổi dữ liệu
        tblDangDienRa.repaint();     // Vẽ lại bảng để phản ánh thay đổi
    }

    private void fillToTable_SapDienRa(List<GiamGia> data) {
        DefaultTableModel model = (DefaultTableModel) tblSapDienRa.getModel();
        model.setRowCount(0);
        for (GiamGia gg : data) {
            String thoiGianDienRa = gg.getNgay_Bat_Dau() + " đến " + gg.getNgay_Ket_Thuc();
            String trangThai = determineStatus(gg);
            String mucGiam = gg.getMuc_Giam_Gia() + " " + (gg.getKieu_Giam() == 0 ? "%" : "đ");
            if (gg.getKieu_Giam() == 0) {
                mucGiam += " Tối đa: " + gg.getMuc_Giam_Gia_Toi_Da() + "đ";
            }
            model.addRow(new Object[]{
                gg.getTen_Chuong_Trinh(),
                gg.getMa_Giam_Gia(),
                mucGiam,
                trangThai,
                cd.ChuyenDoiTien(gg.getGia_tri_DH_Toi_Thieu()),
                thoiGianDienRa
            });
        }
        tblSapDienRa.revalidate();  // Cập nhật giao diện sau khi thay đổi dữ liệu
        tblSapDienRa.repaint();     // Vẽ lại bảng để phản ánh thay đổi
    }

    private void fillToTable_DaKetThuc(List<GiamGia> data) {
        DefaultTableModel model = (DefaultTableModel) tblDaKetThuc.getModel();
        model.setRowCount(0);
        for (GiamGia gg : data) {
            String thoiGianDienRa = gg.getNgay_Bat_Dau() + " đến " + gg.getNgay_Ket_Thuc();
            String trangThai = determineStatus(gg);
            String mucGiam = gg.getMuc_Giam_Gia() + " " + (gg.getKieu_Giam() == 0 ? "%" : "đ");
            if (gg.getKieu_Giam() == 0) {
                mucGiam += " Tối đa: " + gg.getMuc_Giam_Gia_Toi_Da() + "đ";
            }
            model.addRow(new Object[]{
                gg.getTen_Chuong_Trinh(),
                gg.getMa_Giam_Gia(),
                mucGiam,
                trangThai,
                cd.ChuyenDoiTien(gg.getGia_tri_DH_Toi_Thieu()),
                thoiGianDienRa
            });
        }
        tblDaKetThuc.revalidate();  // Cập nhật giao diện sau khi thay đổi dữ liệu
        tblDaKetThuc.repaint();     // Vẽ lại bảng để phản ánh thay đổi
    }

    private void fillToTable_DaXoa(List<GiamGia> data) {
        DefaultTableModel model = (DefaultTableModel) tblDaXoa.getModel();
        model.setRowCount(0);
        for (GiamGia gg : data) {
            String thoiGianDienRa = gg.getNgay_Bat_Dau() + " đến " + gg.getNgay_Ket_Thuc();
            String trangThai = determineStatus(gg);
            String mucGiam = gg.getMuc_Giam_Gia() + " " + (gg.getKieu_Giam() == 0 ? "%" : "đ");
            if (gg.getKieu_Giam() == 0) {
                mucGiam += " Tối đa: " + gg.getMuc_Giam_Gia_Toi_Da() + "đ";
            }
            model.addRow(new Object[]{
                gg.getTen_Chuong_Trinh(),
                gg.getMa_Giam_Gia(),
                mucGiam,
                trangThai,
                cd.ChuyenDoiTien(gg.getGia_tri_DH_Toi_Thieu()),
                thoiGianDienRa
            });
        }
        tblDaXoa.revalidate();  // Cập nhật giao diện sau khi thay đổi dữ liệu
        tblDaXoa.repaint();     // Vẽ lại bảng để phản ánh thay đổi
    }

    private void display_TatCa(int index, ArrayList<GiamGia> list) {
        GiamGia gg = list.get(index);
        txtTenChuongTrinh.setText(gg.getTen_Chuong_Trinh());
        txtMaGiamGia.setText(gg.getMa_Giam_Gia());
        cboKieuGiamGia.setSelectedIndex(gg.getKieu_Giam());
        int kieuGiam = cboKieuGiamGia.getSelectedIndex();
        if (kieuGiam == 0) {
            txtGiamToiDa.setText(String.valueOf(gg.getMuc_Giam_Gia_Toi_Da()));
        } else {
            txtGiamToiDa.setText("");
        }
        txtMucGiamGia.setText(String.valueOf(gg.getMuc_Giam_Gia()));
        txtGiaTriToiThieu.setText((int) gg.getGia_tri_DH_Toi_Thieu() + "");
        JDateNgayBatDau.setDate(gg.getNgay_Bat_Dau());
        JDateNgayKetThuc.setDate(gg.getNgay_Ket_Thuc());
    }

    private void display_DangDienRa(int index, ArrayList<GiamGia> list) {
        GiamGia gg = list.get(index);
        txtTenChuongTrinh.setText(gg.getTen_Chuong_Trinh());
        txtMaGiamGia.setText(gg.getMa_Giam_Gia());
        cboKieuGiamGia.setSelectedIndex(gg.getKieu_Giam());
        int kieuGiam = cboKieuGiamGia.getSelectedIndex();
        if (kieuGiam == 0) {
            txtGiamToiDa.setText(String.valueOf(gg.getMuc_Giam_Gia_Toi_Da()));
        } else {
            txtGiamToiDa.setText("");
        }
        txtMucGiamGia.setText(String.valueOf(gg.getMuc_Giam_Gia()));
        txtGiaTriToiThieu.setText((int) gg.getGia_tri_DH_Toi_Thieu() + "");
        JDateNgayBatDau.setDate(gg.getNgay_Bat_Dau());
        JDateNgayKetThuc.setDate(gg.getNgay_Ket_Thuc());
    }

    private void display_SapDienRa(int index, ArrayList<GiamGia> list) {
        GiamGia gg = list.get(index);
        txtTenChuongTrinh.setText(gg.getTen_Chuong_Trinh());
        txtMaGiamGia.setText(gg.getMa_Giam_Gia());
        cboKieuGiamGia.setSelectedIndex(gg.getKieu_Giam());
        int kieuGiam = cboKieuGiamGia.getSelectedIndex();
        if (kieuGiam == 0) {
            txtGiamToiDa.setText(String.valueOf(gg.getMuc_Giam_Gia_Toi_Da()));
        } else {
            txtGiamToiDa.setText("");
        }
        txtMucGiamGia.setText(String.valueOf(gg.getMuc_Giam_Gia()));
        txtGiaTriToiThieu.setText((int) gg.getGia_tri_DH_Toi_Thieu() + "");
        JDateNgayBatDau.setDate(gg.getNgay_Bat_Dau());
        JDateNgayKetThuc.setDate(gg.getNgay_Ket_Thuc());
    }

    private void display_DaKetThuc(int index, ArrayList<GiamGia> list) {
        GiamGia gg = list.get(index);
        txtTenChuongTrinh.setText(gg.getTen_Chuong_Trinh());
        txtMaGiamGia.setText(gg.getMa_Giam_Gia());
        cboKieuGiamGia.setSelectedIndex(gg.getKieu_Giam());
        int kieuGiam = cboKieuGiamGia.getSelectedIndex();
        if (kieuGiam == 0) {
            txtGiamToiDa.setText(String.valueOf(gg.getMuc_Giam_Gia_Toi_Da()));
        } else {
            txtGiamToiDa.setText("");
        }
        txtMucGiamGia.setText(String.valueOf(gg.getMuc_Giam_Gia()));
        txtGiaTriToiThieu.setText((int) gg.getGia_tri_DH_Toi_Thieu() + "");
        JDateNgayBatDau.setDate(gg.getNgay_Bat_Dau());
        JDateNgayKetThuc.setDate(gg.getNgay_Ket_Thuc());
    }

    private void display_DaXoa(int index, ArrayList<GiamGia> list) {
        GiamGia gg = list.get(index);
        txtTenChuongTrinh.setText(gg.getTen_Chuong_Trinh());
        txtMaGiamGia.setText(gg.getMa_Giam_Gia());
        cboKieuGiamGia.setSelectedIndex(gg.getKieu_Giam());
        int kieuGiam = cboKieuGiamGia.getSelectedIndex();
        if (kieuGiam == 0) {
            txtGiamToiDa.setText(String.valueOf(gg.getMuc_Giam_Gia_Toi_Da()));
        } else {
            txtGiamToiDa.setText("");
        }
        txtMucGiamGia.setText(String.valueOf(gg.getMuc_Giam_Gia()));
        txtGiaTriToiThieu.setText((int) gg.getGia_tri_DH_Toi_Thieu() + "");
        JDateNgayBatDau.setDate(gg.getNgay_Bat_Dau());
        JDateNgayKetThuc.setDate(gg.getNgay_Ket_Thuc());
    }

    private String determineStatus(GiamGia gg) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date now = cal.getTime();

        Date start = gg.getNgay_Bat_Dau();
        Date end = gg.getNgay_Ket_Thuc();

        if ((start.before(now) || start.equals(now)) && (end.equals(now) || end.after(now))) {
            return "Đang diễn ra";
        } else if (start.after(now)) {
            return "Sắp diễn ra";
        } else if (end.before(now)) {
            return "Đã kết thúc";
        } else {
            return "";
        }
    }

    private boolean Validate_form_NV() {

        if (txtTenChuongTrinh.getText().trim().isEmpty()) {
            MsgBox.showMessage(this, "Vui lòng nhập Tên chương trình");
            return false;
        }
        if (txtMaGiamGia.getText().trim().isEmpty()) {
            MsgBox.showMessage(this, "Vui lòng nhập Mã giảm giá");
            return false;
        }
        int kieuGiam = cboKieuGiamGia.getSelectedIndex();
        if (kieuGiam < 0) {
            MsgBox.showMessage(this, "Vui lòng chọn Kiểu giảm giá");
            return false;
        }
        String mucGiamGiaStr = txtMucGiamGia.getText().trim();
        if (mucGiamGiaStr.isEmpty()) {
            MsgBox.showMessage(this, "Vui lòng nhập Mức giảm giá");
            return false;
        }
        if (kieuGiam == 0) {
            try {
                double mucGiamGia = Double.parseDouble(mucGiamGiaStr);
                if (mucGiamGia <= 0 || mucGiamGia > 100) {
                    MsgBox.showMessage(this, "Mức giảm giá phải lớn hơn 0 và bé hơn hoặc bằng 100 khi kiểu giảm giá là phần trăm");
                    return false;
                }
            } catch (NumberFormatException e) {
                MsgBox.showMessage(this, "Mức giảm giá phải là số");
                return false;
            }
        }
        if (kieuGiam == 0) {

            String mucGiamToiDaStr = txtGiamToiDa.getText().trim();
            if (mucGiamToiDaStr.isEmpty()) {
                MsgBox.showMessage(this, "Vui lòng nhập mức giảm giá tối đa khi kiểu giảm giá là phần trăm");
                return false;
            }
            if (!mucGiamToiDaStr.isEmpty()) {

                try {
                    double mucGiamToiDa = Double.parseDouble(mucGiamToiDaStr);
                    if (mucGiamToiDa <= 0) {
                        MsgBox.showMessage(this, "Mức giảm tối đa phải lớn hơn 0");
                        return false;
                    }
                } catch (NumberFormatException e) {
                    MsgBox.showMessage(this, "Mức giảm tối đa phải là số");
                    return false;
                }
            }
        } else if (kieuGiam == 1) {
            try {
                double mucGiamGia = Double.parseDouble(mucGiamGiaStr);
                if (mucGiamGia <= 0) {
                    MsgBox.showMessage(this, "Mức giảm giá phải lớn hơn 0 khi kiểu giảm giá là số tiền");
                    return false;
                }
            } catch (NumberFormatException e) {
                MsgBox.showMessage(this, "Mức giảm giá phải là số");
                return false;
            }
            if (!txtGiamToiDa.getText().trim().isEmpty()) {
                MsgBox.showMessage(this, "Không được nhập Mức giảm tối đa khi kiểu giảm là tiền");
                return false;
            }
        }
        String giaTriToiThieuStr = txtGiaTriToiThieu.getText().trim();
        if (giaTriToiThieuStr.isEmpty()) {
            MsgBox.showMessage(this, "Vui lòng nhập Giá trị tối thiểu");
            return false;
        }
        try {
            double giaTriToiThieu = Double.parseDouble(giaTriToiThieuStr);
            if (giaTriToiThieu <= 0) {
                MsgBox.showMessage(this, "Giá trị tối thiểu phải lớn hơn 0");
                return false;
            }
        } catch (NumberFormatException e) {
            MsgBox.showMessage(this, "Giá trị tối thiểu phải là số");
            return false;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date now = cal.getTime();
        Date ngayBatDau = JDateNgayBatDau.getDate();
        Date ngayKetThuc = JDateNgayKetThuc.getDate();
        if (ngayBatDau == null) {
            MsgBox.showMessage(this, "Vui lòng chọn Ngày bắt đầu");
            return false;
        }
        if (ngayBatDau.before(now)) {
            MsgBox.showMessage(this, "Ngày bắt đầu không được nhỏ hơn ngày hiện tại");
            return false;
        }
        if (ngayKetThuc == null) {
            MsgBox.showMessage(this, "Vui lòng chọn Ngày kết thúc");
            return false;
        }

        if (ngayKetThuc.before(now)) {
            MsgBox.showMessage(this, "Ngày kết thúc không được nhỏ hơn ngày hiện tại");
            return false;
        }
        if (ngayBatDau.after(ngayKetThuc)) {
            MsgBox.showMessage(this, "Ngày kết thúc phải lớn hơn hoặc bằng Ngày bắt đầu");
            return false;
        }

        return true;
    }

    public void clear() {
        txtTenChuongTrinh.setText("");
        txtMaGiamGia.setText("");
        cboKieuGiamGia.setSelectedIndex(-1);
        txtMucGiamGia.setText("");
        txtGiamToiDa.setText("");
        txtGiaTriToiThieu.setText("");
        JDateNgayBatDau.setDate(null);
        JDateNgayKetThuc.setDate(null);
        btnThem.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTatca = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDangDienRa = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSapDienRa = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDaKetThuc = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDaXoa = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JDateNgayBatDau = new com.toedter.calendar.JDateChooser();
        JDateNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTenChuongTrinh = new javax.swing.JTextField();
        txtMaGiamGia = new javax.swing.JTextField();
        txtMucGiamGia = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        txtGiaTriToiThieu = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboKieuGiamGia = new javax.swing.JComboBox<>();
        btnLamMoi = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtGiamToiDa = new javax.swing.JTextField();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        tblTatca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên chương trình", "Ma giảm giá", "Mức giảm giá", "Trạng thái", "Giá trị tối thiểu", "thời gian diễn ra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTatca.setRowHeight(30);
        tblTatca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTatcaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblTatcaMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblTatca);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Tất Cả", jPanel3);

        tblDangDienRa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên chương trình", "Ma giảm giá", "Mức giảm giá", "Trạng thái", "Giá trị tối thiểu", "thời gian diễn ra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDangDienRa.setRowHeight(30);
        tblDangDienRa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDangDienRaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDangDienRa);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Đang diễn ra", jPanel4);

        tblSapDienRa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên chương trình", "Ma giảm giá", "Mức giảm giá", "Trạng thái", "Giá trị tối thiểu", "thời gian diễn ra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSapDienRa.setRowHeight(30);
        tblSapDienRa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSapDienRaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSapDienRa);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Sắp diễn ra", jPanel5);

        tblDaKetThuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên chương trình", "Ma giảm giá", "Mức giảm giá", "Trạng thái", "Giá trị tối thiểu", "thời gian diễn ra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDaKetThuc.setRowHeight(30);
        tblDaKetThuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDaKetThucMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblDaKetThuc);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Đã kết thúc", jPanel7);

        tblDaXoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên chương trình", "Ma giảm giá", "Mức giảm giá", "Trạng thái", "Giá trị tối thiểu", "thời gian diễn ra"
            }
        ));
        tblDaXoa.setRowHeight(30);
        tblDaXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDaXoaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblDaXoa);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Đã xóa", jPanel8);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 153, 255));
        jLabel5.setText("Danh sách giảm giá");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setText("Tên chương trình giảm giá");

        jLabel3.setText("Ma giảm giá");

        jLabel4.setText("Kiểu giảm giá");

        JDateNgayBatDau.setDateFormatString("dd-MM-yyyy");

        JDateNgayKetThuc.setDateFormatString("dd-MM-yyyy");

        jLabel7.setText("Ngày bắt đầu");

        jLabel8.setText("Ngày kết thúc");

        jLabel9.setText("Giá trị tối thiểu");

        jLabel10.setText("jLabel10");

        btnThem.setBackground(new java.awt.Color(153, 204, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(153, 204, 255));
        btnSua.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(153, 204, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel6.setText("Mức giảm giá");

        cboKieuGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giảm gia theo phần trăm", "Giảm giá theo số tiền" }));

        btnLamMoi.setBackground(new java.awt.Color(153, 204, 255));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Bubble.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel11.setText("Giảm tối đa");

        btnTimKiem.setBackground(new java.awt.Color(153, 204, 255));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Zoom.png"))); // NOI18N
        btnTimKiem.setText("Tìn kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 255));
        jLabel1.setText("Quản Lý Giảm Giá");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLamMoi)
                                .addContainerGap())))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(btnTimKiem))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JDateNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JDateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiamToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cboKieuGiamGia, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMaGiamGia, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenChuongTrinh, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMucGiamGia, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addComponent(txtGiaTriToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboKieuGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtGiamToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaTriToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JDateNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JDateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnLamMoi))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblTatcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTatcaMouseClicked
        // TODO add your handling code here:
           if (tblTatca.getSelectedRow() < 0) {
            return;
        }
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
        btnSua.setEnabled(true);
        btnLamMoi.setEnabled(true);
        if (isSearching) {
            // Hiển thị dữ liệu tìm kiếm nếu đã tìm kiếm
            display_TatCa(tblTatca.getSelectedRow(), repo_gg.search(txtTimKiem.getText()));
            isSearching = false; // Reset trạng thái tìm kiếm sau khi sử dụng
        } else {
            // Hiển thị dữ liệu đầy đủ nếu chưa tìm kiếm
            display_TatCa(tblTatca.getSelectedRow(), repo_gg.getAll());
        }
    
    }//GEN-LAST:event_tblTatcaMouseClicked

    private void tblDangDienRaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDangDienRaMouseClicked
        // TODO add your handling code here:
        if (tblDangDienRa.getSelectedRow() < 0) {
            return;
        }
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
        btnSua.setEnabled(true);
        btnLamMoi.setEnabled(true);
        display_TatCa(tblDangDienRa.getSelectedRow(), repo_gg.getAll_DangDienRa());
    }//GEN-LAST:event_tblDangDienRaMouseClicked

    private void tblSapDienRaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSapDienRaMouseClicked
        // TODO add your handling code here:
        if (tblSapDienRa.getSelectedRow() < 0) {
            return;
        }
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
        btnSua.setEnabled(true);
        btnLamMoi.setEnabled(true);
        display_TatCa(tblSapDienRa.getSelectedRow(), repo_gg.getAll_SapDienRa());
    }//GEN-LAST:event_tblSapDienRaMouseClicked

    private void tblDaKetThucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDaKetThucMouseClicked
        // TODO add your handling code here:
        if (tblDaKetThuc.getSelectedRow() < 0) {
            return;
        }
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
        btnSua.setEnabled(true);
        btnLamMoi.setEnabled(true);
        display_TatCa(tblDaKetThuc.getSelectedRow(), repo_gg.getAll_DaKetThuc());
    }//GEN-LAST:event_tblDaKetThucMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        boolean kt = true;
        for (GiamGia gg : repo_gg.getAll()) {
            if (gg.getMa_Giam_Gia().equalsIgnoreCase(txtMaGiamGia.getText())) {
                MsgBox.showMessage(this, "Mã Giảm giá đã tồn tại");
                kt = false;
                break;
            }
        }
        for (GiamGia gg : repo_gg.getAll_DangDienRa()) {
            if (gg.getMa_Giam_Gia().equalsIgnoreCase(txtMaGiamGia.getText())) {
                MsgBox.showMessage(this, "Mã Giảm giá đã tồn tại");
                kt = false;
                break;
            }
        }
        for (GiamGia gg : repo_gg.getAll_SapDienRa()) {
            if (gg.getMa_Giam_Gia().equalsIgnoreCase(txtMaGiamGia.getText())) {
                MsgBox.showMessage(this, "Mã Giảm giá đã tồn tại");
                kt = false;
                break;
            }
        }
        for (GiamGia gg : repo_gg.getAll_DaKetThuc()) {
            if (gg.getMa_Giam_Gia().equalsIgnoreCase(txtMaGiamGia.getText())) {
                MsgBox.showMessage(this, "Mã Giảm giá đã tồn tại");
                kt = false;
                break;
            }
        }
        for (GiamGia gg : repo_gg.getAll_DaXoa()) {
            if (gg.getMa_Giam_Gia().equalsIgnoreCase(txtMaGiamGia.getText())) {
                MsgBox.showMessage(this, "Mã Giảm giá đã tồn tại");
                kt = false;
                break;
            }
        }
        if (kt) {
            if (Validate_form_NV()) {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm giảm giá này không?", "Xác nhận thêm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    GiamGia gg = new GiamGia();
                    gg.setTen_Chuong_Trinh(txtTenChuongTrinh.getText());
                    gg.setMa_Giam_Gia(txtMaGiamGia.getText());
                    gg.setKieu_Giam(cboKieuGiamGia.getSelectedIndex());
                    gg.setMuc_Giam_Gia(Integer.parseInt(txtMucGiamGia.getText()));
                    if (gg.getKieu_Giam() == 0) {
                        gg.setMuc_Giam_Gia_Toi_Da(Integer.parseInt(txtGiamToiDa.getText()));
                    } else {
                        gg.setMuc_Giam_Gia_Toi_Da(null);
                    }
                    gg.setGia_tri_DH_Toi_Thieu(Float.parseFloat(txtGiaTriToiThieu.getText()));
                    gg.setNgay_Bat_Dau(new java.sql.Date(JDateNgayBatDau.getDate().getTime()));
                    gg.setNgay_Ket_Thuc(new java.sql.Date(JDateNgayKetThuc.getDate().getTime()));
                    int idNhanVien = ToanCuc_NV.getId_NhanVien();
                    if (repo_gg.add(gg, idNhanVien)) {
                        JOptionPane.showMessageDialog(this, "Thêm giảm giá thành công");
                        fillToTable_TatCa(repo_gg.getAll());
                        fillToTable_DangDienRa(repo_gg.getAll_DangDienRa());
                        fillToTable_SapDienRa(repo_gg.getAll_SapDienRa());
                        fillToTable_DaKetThuc(repo_gg.getAll_DaKetThuc());
                        fillToTable_DaXoa(repo_gg.getAll_DaXoa());
                        clear();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm giảm giá thất bại");
                    }
                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn làm mới mã giảm giá này không?", "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            clear();
            btnThem.setEnabled(true);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
        }
    }//GEN-LAST:event_btnLamMoiActionPerformed


    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:

        String maGG = null;

        if ((index = tblTatca.getSelectedRow()) != -1) {
            maGG = tblTatca.getValueAt(index, 1).toString();
        } else if ((index = tblDangDienRa.getSelectedRow()) != -1) {
            maGG = tblDangDienRa.getValueAt(index, 1).toString();
        } else if ((index = tblSapDienRa.getSelectedRow()) != -1) {
            maGG = tblSapDienRa.getValueAt(index, 1).toString();
        } else if ((index = tblDaKetThuc.getSelectedRow()) != -1) {
            maGG = tblDaKetThuc.getValueAt(index, 1).toString();
        } else if ((index = tblDaXoa.getSelectedRow()) != -1) {
            maGG = tblDaXoa.getValueAt(index, 1).toString();
        }

        if (maGG == null) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn nội dung trước khi sửa");
            return;
        }

        boolean kt = true;
        for (GiamGia g : repo_gg.getAll()) {
            if (g.getMa_Giam_Gia().equalsIgnoreCase(txtMaGiamGia.getText())
                    && !maGG.equals(txtMaGiamGia.getText())) {
                MsgBox.showMessage(this, "Mã giảm giá đã tồn tại");
                kt = false;
                break;
            }
        }
        for (GiamGia g : repo_gg.getAll_DangDienRa()) {
            if (g.getMa_Giam_Gia().equalsIgnoreCase(txtMaGiamGia.getText())
                    && !maGG.equals(txtMaGiamGia.getText())) {
                MsgBox.showMessage(this, "Mã giảm giá đã tồn tại");
                kt = false;
                break;
            }
        }
        for (GiamGia g : repo_gg.getAll_SapDienRa()) {
            if (g.getMa_Giam_Gia().equalsIgnoreCase(txtMaGiamGia.getText())
                    && !maGG.equals(txtMaGiamGia.getText())) {
                MsgBox.showMessage(this, "Mã giảm giá đã tồn tại");
                kt = false;
                break;
            }
        }
        for (GiamGia g : repo_gg.getAll_DaKetThuc()) {
            if (g.getMa_Giam_Gia().equalsIgnoreCase(txtMaGiamGia.getText())
                    && !maGG.equals(txtMaGiamGia.getText())) {
                MsgBox.showMessage(this, "Mã giảm giá đã tồn tại");
                kt = false;
                break;
            }
        }
        for (GiamGia g : repo_gg.getAll_DaXoa()) {
            if (g.getMa_Giam_Gia().equalsIgnoreCase(txtMaGiamGia.getText())
                    && !maGG.equals(txtMaGiamGia.getText())) {
                MsgBox.showMessage(this, "Mã giảm giá đã tồn tại");
                kt = false;
                break;
            }
        }

        if (kt) {
            if (Validate_form_NV()) {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa giảm giá này không?", "Xác nhận sửa", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    GiamGia gg = new GiamGia();
                    gg.setTen_Chuong_Trinh(txtTenChuongTrinh.getText());
                    gg.setMa_Giam_Gia(txtMaGiamGia.getText());
                    gg.setKieu_Giam(cboKieuGiamGia.getSelectedIndex());
                    gg.setMuc_Giam_Gia(Integer.parseInt(txtMucGiamGia.getText()));
                    gg.setGia_tri_DH_Toi_Thieu(Float.parseFloat(txtGiaTriToiThieu.getText()));
                    gg.setNgay_Bat_Dau(new java.sql.Date(JDateNgayBatDau.getDate().getTime()));
                    gg.setNgay_Ket_Thuc(new java.sql.Date(JDateNgayKetThuc.getDate().getTime()));
                    if (gg.getKieu_Giam() == 0) {
                        gg.setMuc_Giam_Gia_Toi_Da(Integer.parseInt(txtGiamToiDa.getText()));
                    } else {
                        gg.setMuc_Giam_Gia_Toi_Da(null);
                    }
                    int idNhanVien = ToanCuc_NV.getId_NhanVien();
                    if (repo_gg.update(gg, maGG, idNhanVien)) {
                        JOptionPane.showMessageDialog(this, "Cập nhật giảm giá thành công");
                        fillToTable_TatCa(repo_gg.getAll());
                        fillToTable_DangDienRa(repo_gg.getAll_DangDienRa());
                        fillToTable_SapDienRa(repo_gg.getAll_SapDienRa());
                        fillToTable_DaKetThuc(repo_gg.getAll_DaKetThuc());
                        fillToTable_DaXoa(repo_gg.getAll_DaXoa());
                        clear();
                    } else {
                        JOptionPane.showMessageDialog(this, "Cập nhật giảm giá thất bại");
                    }
                }
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        String maGiamGia = txtMaGiamGia.getText().trim();
        if (!maGiamGia.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa giảm giá này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                GiamGia gg = new GiamGia();
                gg.setMa_Giam_Gia(maGiamGia);
                int idNhanVien = ToanCuc_NV.getId_NhanVien();
                if (repo_gg.Update_TrangThaiGG(gg, 1)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    fillToTable_TatCa(repo_gg.getAll());
                    fillToTable_DangDienRa(repo_gg.getAll_DangDienRa());
                    fillToTable_SapDienRa(repo_gg.getAll_SapDienRa());
                    fillToTable_DaKetThuc(repo_gg.getAll_DaKetThuc());
                    fillToTable_DaXoa(repo_gg.getAll_DaXoa());
                    clear();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã giảm giá!");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblDaXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDaXoaMouseClicked
        // TODO add your handling code here:
        if (tblDaXoa.getSelectedRow() < 0) {
            return;
        }
        btnXoa.setEnabled(false);
        btnThem.setEnabled(false);
        btnSua.setEnabled(true);
        btnLamMoi.setEnabled(true);
        display_DaXoa(tblDaXoa.getSelectedRow(), repo_gg.getAll_DaXoa());
    }//GEN-LAST:event_tblDaXoaMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String timKiem = txtTimKiem.getText().toLowerCase();
        fillToTable_TatCa(repo_gg.search(timKiem));
        isSearching = true;
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblTatcaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTatcaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTatcaMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JDateNgayBatDau;
    private com.toedter.calendar.JDateChooser JDateNgayKetThuc;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboKieuGiamGia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tblDaKetThuc;
    private javax.swing.JTable tblDaXoa;
    private javax.swing.JTable tblDangDienRa;
    private javax.swing.JTable tblSapDienRa;
    private javax.swing.JTable tblTatca;
    private javax.swing.JTextField txtGiaTriToiThieu;
    private javax.swing.JTextField txtGiamToiDa;
    private javax.swing.JTextField txtMaGiamGia;
    private javax.swing.JTextField txtMucGiamGia;
    private javax.swing.JTextField txtTenChuongTrinh;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
