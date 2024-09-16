package form;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;
import com.sun.java.accessibility.util.AWTEventMonitor;
import entity.CPU;
import entity.DungLuongPin;
import entity.Imei;
import entity.KichThuoc;
import entity.MauSac;
import entity.PhanLoai;
import entity.Ram;
import entity.Rom;
import entity.XuatXu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import lombok.experimental.Delegate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import repository.CPU_Repository;
import repository.DungLuongPin_Repository;
import repository.Imei_Repository;
import repository.KichThuocRepository;
import repository.MauSac_Repository;
import repository.PhanLoai_Repository;
import repository.Ram_Repository;
import repository.Rom_Repository;
import repository.SanPham_Repository;
import repository.XuatXu_Repository;
import response.NhanVien_Response;
import response.SanPham_Response;
import ultil.ChuyenDoi;
import ultil.MsgBox;
import view.Menu_View;
import view.ThongTinSanPham.CPU_View;
import view.ThongTinSanPham.DungLuongPin_View;
import view.ThongTinSanPham.KichThuoc_View;
import view.ThongTinSanPham.MauSac_View;
import view.ThongTinSanPham.PhanLoai_View;
import view.ThongTinSanPham.Ram_View;
import view.ThongTinSanPham.Rom_View;
import view.ThongTinSanPham.XuatXu_View;
import static view.ChonImei_view.tblImei_SP;
import view.QrCode_View_SanPham;

public class SanPham_form extends javax.swing.JInternalFrame {

    ChuyenDoi cd = new ChuyenDoi(); // phương thức chuyển đổi tiền sang có chấm
    String strHinhAnh = null;
    String pathFile = null;
    private int click_San_Pham = -1; 
    private int click_Imei_SanPham = -1;
    private int click_Imei_ChiTiet = -1;
    private int click_SanPham_DaXoa = -1;
    // default_CBO_MODEL
    private DefaultComboBoxModel dcbm_CPU;
    private DefaultComboBoxModel dcbm_Ram;
    private DefaultComboBoxModel dcbm_DungLuongPin;
    private DefaultComboBoxModel dcbm_KichThuoc;
    private DefaultComboBoxModel dcbm_MauSac;
    private DefaultComboBoxModel dcbm_PhanLoai;
    private DefaultComboBoxModel dcbm_Rom;
    private DefaultComboBoxModel dcbm_XuatXu;
    private DefaultComboBoxModel dcbm_MaSanPham;
    // default table model 
    private DefaultTableModel model_SanPham;
    private DefaultTableModel model_SanPhamDaXoa;
    private DefaultTableModel model_SanPhamImei;
    private DefaultTableModel model_ImeiChiTiet;
    // repository 
    private CPU_Repository repo_CPU;
    private Ram_Repository repo_Ram;
    private DungLuongPin_Repository repo_Pin;
    private KichThuocRepository repo_KichThuoc;
    private MauSac_Repository repo_MauSac;
    private PhanLoai_Repository repo_PhanLoai;
    private Rom_Repository repo_Rom;
    private XuatXu_Repository repo_XuatXu;
    //
    private Imei_Repository repo_Imei;
    private SanPham_Repository repo_SanPham;

    public SanPham_form() {
        initComponents();
        cauHinh_form();

        DinhDang_Repository(); // repo
        DinhDang_CBO();        // cbo
        dinhDang_TB_Model();   // tabel model
        // fill cbo 
        loadCBO();
        fillTo_CBO_MaSanPham(repo_SanPham.getAll_SP_Moi());
        // fill table 
        fillTable_SP(repo_SanPham.getAll_SP_Moi());
        fillTable_SP_Imei(repo_SanPham.getAll_SP_Moi());
        fillTable_SP_DaXoa(repo_SanPham.getAll_SP_DaXoa());
        // 
        clearFormSP();
    }

    private void khoaForm() {
        btnSuaSP.setEnabled(false);
        btnXoaSP.setEnabled(false);
        btnKhoiPhucSP.setEnabled(false);
    }

    public void loadCBO() {
        fillTo_CBO_CPU(repo_CPU.getAll());
        fillTo_CBO_Ram(repo_Ram.getAll());
        fillTo_CBO_Rom(repo_Rom.getAll());
        fillTo_CBO_Pin(repo_Pin.getAll());
        fillTo_CBO_KichThuocMan(repo_KichThuoc.getAll());
        fillTo_CBO_XuatXu(repo_XuatXu.getAll());
        fillTo_CBO_MauSac(repo_MauSac.getAll());
        fillTo_CBO_PhanLoai(repo_PhanLoai.getAll());
    }

    public void DinhDang_Repository() {
        repo_CPU = new CPU_Repository();
        repo_KichThuoc = new KichThuocRepository();
        repo_MauSac = new MauSac_Repository();
        repo_PhanLoai = new PhanLoai_Repository();
        repo_Pin = new DungLuongPin_Repository();
        repo_Ram = new Ram_Repository();
        repo_Rom = new Rom_Repository();
        repo_XuatXu = new XuatXu_Repository();
        repo_SanPham = new SanPham_Repository();
        repo_Imei = new Imei_Repository();
    }

    public void dinhDang_TB_Model() {
        model_SanPham = (DefaultTableModel) tblSanPhamMoi.getModel();
        model_SanPhamDaXoa = (DefaultTableModel) tblSanPhamDaXoa.getModel();
        model_SanPhamImei = (DefaultTableModel) tblSanPhamImei.getModel();
        model_ImeiChiTiet = (DefaultTableModel) tblImeiChiTiet.getModel();
    }

    public void DinhDang_CBO() {
        dcbm_CPU = (DefaultComboBoxModel) cboCPU.getModel();
        dcbm_Ram = (DefaultComboBoxModel) cboRam.getModel();
        dcbm_DungLuongPin = (DefaultComboBoxModel) cboDungLuongPin.getModel();
        dcbm_KichThuoc = (DefaultComboBoxModel) cboKichThuocMan.getModel();
        dcbm_MauSac = (DefaultComboBoxModel) cboMauSac.getModel();
        dcbm_PhanLoai = (DefaultComboBoxModel) cboPhanLoai.getModel();
        dcbm_Rom = (DefaultComboBoxModel) cboRom.getModel();
        dcbm_XuatXu = (DefaultComboBoxModel) cboXuatXu.getModel();
        dcbm_MaSanPham = (DefaultComboBoxModel) cboMaSanPham.getModel();
    }

    // fill table model 
    // hiển thị bảng sản phẩm chữa xóa 
    public void fillTable_SP(ArrayList<SanPham_Response> lists) {
        model_SanPham.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        for (SanPham_Response sp : lists) {
            model_SanPham.addRow(new Object[]{index.getAndIncrement(),
                sp.getMaSanPham(), sp.getTenSanPham(), sp.getRam(), sp.getDungLuongPin(),
                sp.getRom(), sp.getCPU(), sp.getMauSac(), sp.getKichThuoc(),
                sp.getXuatXu(), cd.ChuyenDoiTien((int) sp.getGiaNhap()),
                cd.ChuyenDoiTien((int) sp.getGiaBan()), sp.getPhanLoai(), sp.getSoLuong()
            });
        }
    }

    // hiển thị bảng sản phẩm đã xóa 
    public void fillTable_SP_DaXoa(ArrayList<SanPham_Response> lists) {
        model_SanPhamDaXoa.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        for (SanPham_Response sp : lists) {
            model_SanPhamDaXoa.addRow(new Object[]{index.getAndIncrement(),
                sp.getMaSanPham(), sp.getTenSanPham(), sp.getRam(), sp.getDungLuongPin(),
                sp.getRom(), sp.getCPU(),
                cd.ChuyenDoiTien((int) sp.getGiaNhap()),
                cd.ChuyenDoiTien((int) sp.getGiaBan()), sp.getSoLuong(), sp.getPhanLoai()
            });
        }
    }

    // hiển thị table bảng sp_Imei 
    public void fillTable_SP_Imei(ArrayList<SanPham_Response> lists) {
        model_SanPhamImei.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        for (SanPham_Response sp : lists) {
            model_SanPhamImei.addRow(new Object[]{index.getAndIncrement(),
                sp.getMaSanPham(), sp.getTenSanPham(), cd.ChuyenDoiTien((int) sp.getGiaNhap()),
                cd.ChuyenDoiTien((int) sp.getGiaBan()), sp.getPhanLoai(), sp.getSoLuong()
            });
        }
    }

    // hiển thị table bảng Imei CHi tiết 
    public void fillTable_Imei_ChiTiet(ArrayList<SanPham_Response> lists) {
        model_ImeiChiTiet.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        for (SanPham_Response sp : lists) {
            model_ImeiChiTiet.addRow(new Object[]{index.getAndIncrement(),
                sp.getMaSanPham(), sp.getImei()
            });
        }
    }

    // fill to cbo 
    public void fillTo_CBO_Ram(ArrayList<Ram> list) {
        dcbm_Ram.removeAllElements();
        list.forEach(ram -> dcbm_Ram.addElement(ram.getRam()));
    }

    public void fillTo_CBO_CPU(ArrayList<CPU> list) {
        dcbm_CPU.removeAllElements();
        list.forEach(cpu -> dcbm_CPU.addElement(cpu.getCPU()));
    }

    public void fillTo_CBO_Rom(ArrayList<Rom> list) {
        dcbm_Rom.removeAllElements();
        list.forEach(rom -> dcbm_Rom.addElement(rom.getRom()));
    }

    public void fillTo_CBO_Pin(ArrayList<DungLuongPin> list) {
        dcbm_DungLuongPin.removeAllElements();
        list.forEach(pin -> dcbm_DungLuongPin.addElement(pin.getDungLuongPin()));
    }

    public void fillTo_CBO_KichThuocMan(ArrayList<KichThuoc> list) {
        dcbm_KichThuoc.removeAllElements();
        list.forEach(kt -> dcbm_KichThuoc.addElement(kt.getKichThuoc()));
    }

    public void fillTo_CBO_XuatXu(ArrayList<XuatXu> list) {
        dcbm_XuatXu.removeAllElements();
        list.forEach(x -> dcbm_XuatXu.addElement(x.getXuatXu()));
    }

    public void fillTo_CBO_MauSac(ArrayList<MauSac> list) {
        dcbm_MauSac.removeAllElements();
        list.forEach(mau -> dcbm_MauSac.addElement(mau.getMauSac()));
    }

    public void fillTo_CBO_PhanLoai(ArrayList<PhanLoai> list) {
        dcbm_PhanLoai.removeAllElements();
        list.forEach(pl -> dcbm_PhanLoai.addElement(pl.getPhanLoai()));
    }

    public void fillTo_CBO_MaSanPham(ArrayList<SanPham_Response> list) {
        dcbm_MaSanPham.removeAllElements();
        list.forEach(pl -> dcbm_MaSanPham.addElement(pl.getMaSanPham()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelSanPham = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhamMoi = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiemSanPhamMoi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cboRam = new javax.swing.JComboBox<>();
        cboXuatXu = new javax.swing.JComboBox<>();
        cboRom = new javax.swing.JComboBox<>();
        cboKichThuocMan = new javax.swing.JComboBox<>();
        cboCPU = new javax.swing.JComboBox<>();
        cboPhanLoai = new javax.swing.JComboBox<>();
        btnDungLuongPin = new javax.swing.JButton();
        btnXuatXu = new javax.swing.JButton();
        btnMauSac = new javax.swing.JButton();
        btnRom = new javax.swing.JButton();
        btnKichThuoc = new javax.swing.JButton();
        btnCPU = new javax.swing.JButton();
        btnPhanLoai = new javax.swing.JButton();
        btnRam = new javax.swing.JButton();
        cboDungLuongPin = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cboMauSac = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtGiaban = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnSuaSP = new javax.swing.JButton();
        btnClearSP = new javax.swing.JButton();
        btnThemSP = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        lblTonKho = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        txtTimTheoImei = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSanPhamImei = new javax.swing.JTable();
        panelThemImei = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtMaImei = new javax.swing.JTextField();
        cboMaSanPham = new javax.swing.JComboBox<>();
        btnClear1 = new javax.swing.JButton();
        btnThemImeiSP = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btnImportExcel = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        btnXoaImei = new javax.swing.JButton();
        txtImeiClick = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblImeiChiTiet = new javax.swing.JTable();
        txtMax = new javax.swing.JTextField();
        txtMin = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        btnTimTheoGia = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jPanelSanPhamDaXoa = new javax.swing.JPanel();
        txtTimKiemSPCu = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanPhamDaXoa = new javax.swing.JTable();
        btnKhoiPhucSP = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanelSanPham.setBackground(new java.awt.Color(255, 255, 255));

        tblSanPhamMoi.setAutoCreateRowSorter(true);
        tblSanPhamMoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Ram ", "Dung Lượng Pin", "Rom", "CPU", "Màu", "Kích Thước", "Xuất Xứ", "Giá Nhập", "Giá Bán", "Phân  Loại", "Tồn Kho"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamMoi.setGridColor(new java.awt.Color(204, 204, 204));
        tblSanPhamMoi.setRowHeight(30);
        tblSanPhamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMoiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPhamMoi);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtTimKiemSanPhamMoi.setBorder(null);
        txtTimKiemSanPhamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemSanPhamMoiActionPerformed(evt);
            }
        });
        txtTimKiemSanPhamMoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSanPhamMoiKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thuôc Tính Sản Phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 12), new java.awt.Color(255, 102, 102))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Ram");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 24, 35, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Phân Loại");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(639, 144, 60, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Xuất Xứ");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 24, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Rom");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 144, 80, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Dung Lượng Pin");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 24, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Kích Thước Màn ");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 144, 100, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("CPU");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 144, 35, -1));

        jPanel6.add(cboRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 44, 170, 30));

        jPanel6.add(cboXuatXu, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 44, 170, 30));

        jPanel6.add(cboRom, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 164, 170, 30));

        jPanel6.add(cboKichThuocMan, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 164, 170, 30));

        jPanel6.add(cboCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 164, 170, 30));

        jPanel6.add(cboPhanLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(639, 164, 170, 30));

        btnDungLuongPin.setBackground(new java.awt.Color(153, 204, 255));
        btnDungLuongPin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Create.png"))); // NOI18N
        btnDungLuongPin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDungLuongPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDungLuongPinActionPerformed(evt);
            }
        });
        jPanel6.add(btnDungLuongPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 74, 170, 40));

        btnXuatXu.setBackground(new java.awt.Color(153, 204, 255));
        btnXuatXu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Create.png"))); // NOI18N
        btnXuatXu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatXuActionPerformed(evt);
            }
        });
        jPanel6.add(btnXuatXu, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 74, 170, 40));

        btnMauSac.setBackground(new java.awt.Color(153, 204, 255));
        btnMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Create.png"))); // NOI18N
        btnMauSac.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacActionPerformed(evt);
            }
        });
        jPanel6.add(btnMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(639, 74, 170, 40));

        btnRom.setBackground(new java.awt.Color(153, 204, 255));
        btnRom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Create.png"))); // NOI18N
        btnRom.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRomActionPerformed(evt);
            }
        });
        jPanel6.add(btnRom, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 194, 170, 40));

        btnKichThuoc.setBackground(new java.awt.Color(153, 204, 255));
        btnKichThuoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Create.png"))); // NOI18N
        btnKichThuoc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKichThuocActionPerformed(evt);
            }
        });
        jPanel6.add(btnKichThuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 194, 170, 40));

        btnCPU.setBackground(new java.awt.Color(153, 204, 255));
        btnCPU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Create.png"))); // NOI18N
        btnCPU.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCPUActionPerformed(evt);
            }
        });
        btnCPU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnCPUKeyReleased(evt);
            }
        });
        jPanel6.add(btnCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 194, 170, 40));

        btnPhanLoai.setBackground(new java.awt.Color(153, 204, 255));
        btnPhanLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Create.png"))); // NOI18N
        btnPhanLoai.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPhanLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhanLoaiActionPerformed(evt);
            }
        });
        jPanel6.add(btnPhanLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(639, 194, 170, 40));

        btnRam.setBackground(new java.awt.Color(153, 204, 255));
        btnRam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Create.png"))); // NOI18N
        btnRam.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRamActionPerformed(evt);
            }
        });
        jPanel6.add(btnRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 74, 170, 40));

        jPanel6.add(cboDungLuongPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 44, 170, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Màu Sắc");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(639, 24, 56, -1));

        jPanel6.add(cboMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(639, 44, 170, 30));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHinhAnh.setBackground(new java.awt.Color(153, 204, 255));
        lblHinhAnh.setText("anh.png");
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblHinhAnh.setName(""); // NOI18N
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });
        jPanel7.add(lblHinhAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 14, 170, 190));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Giá Bán");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 56, -1));

        txtGiaban.setBorder(null);
        jPanel7.add(txtGiaban, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 220, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("VND");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 393, -1, -1));

        txtGiaNhap.setBorder(null);
        jPanel7.add(txtGiaNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 220, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Giá Nhập ");
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 56, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("VND");
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 323, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Tên Sản Phẩm");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 80, -1));

        txtTenSanPham.setBorder(null);
        jPanel7.add(txtTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 220, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Mô Tả");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 434, -1, -1));

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        jPanel7.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 456, 295, 111));

        btnSuaSP.setBackground(new java.awt.Color(153, 204, 255));
        btnSuaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        btnSuaSP.setText("Sửa");
        btnSuaSP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });
        jPanel7.add(btnSuaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 579, 95, 40));

        btnClearSP.setBackground(new java.awt.Color(153, 204, 255));
        btnClearSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Text.png"))); // NOI18N
        btnClearSP.setText("Làm Mới");
        btnClearSP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnClearSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSPActionPerformed(evt);
            }
        });
        jPanel7.add(btnClearSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 637, 95, 40));

        btnThemSP.setBackground(new java.awt.Color(153, 204, 255));
        btnThemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnThemSP.setText("Thêm");
        btnThemSP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });
        jPanel7.add(btnThemSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 579, 100, 40));

        btnXoaSP.setBackground(new java.awt.Color(153, 204, 255));
        btnXoaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Trash.png"))); // NOI18N
        btnXoaSP.setText("Xóa");
        btnXoaSP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });
        jPanel7.add(btnXoaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 637, 100, 40));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("Tồn Kho");
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 55, -1));

        lblTonKho.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTonKho.setForeground(new java.awt.Color(255, 51, 51));
        lblTonKho.setText("?");
        jPanel7.add(lblTonKho, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 246, -1, -1));

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 220, 10));

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 220, 10));

        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 220, 10));

        txtTimTheoImei.setBorder(null);
        txtTimTheoImei.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimTheoImeiKeyReleased(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 153, 255));
        jLabel26.setText("Tìm Kiếm :");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 153, 255));
        jLabel30.setText("Tìm Theo Imei :");

        jButton1.setBackground(new java.awt.Color(153, 204, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excel_1.png"))); // NOI18N
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/qr-code.png"))); // NOI18N
        jButton4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 204, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/qr-code-scan.png"))); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(102, 153, 255));

        jSeparator4.setForeground(new java.awt.Color(102, 153, 255));

        javax.swing.GroupLayout jPanelSanPhamLayout = new javax.swing.GroupLayout(jPanelSanPham);
        jPanelSanPham.setLayout(jPanelSanPhamLayout);
        jPanelSanPhamLayout.setHorizontalGroup(
            jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                        .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTimKiemSanPhamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(90, 90, 90)
                        .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(txtTimTheoImei, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel1))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(130, 130, 130)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton2)
                        .addGap(11, 11, 11)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                                .addGap(250, 250, 250)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(8, 8, 8)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelSanPhamLayout.setVerticalGroup(
            jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(4, 4, 4)
                                .addComponent(txtTimKiemSanPhamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel30)
                                .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTimTheoImei, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel1)))
                                .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jButton2))
                            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jLabel6))))
                    .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông Tin Sản Phẩm", jPanelSanPham);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblSanPhamImei.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Giá Nhập", "Giá Bán", "Phân  Loại", "Tồn Kho"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamImei.setGridColor(new java.awt.Color(204, 204, 204));
        tblSanPhamImei.setRowHeight(30);
        tblSanPhamImei.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamImeiMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblSanPhamImei);

        jPanel2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 845, 350));

        panelThemImei.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelThemImei.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setText("Mã SP");
        panelThemImei.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 65, 80, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setText("Mã Imei");
        panelThemImei.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 143, 80, 24));

        txtMaImei.setBackground(new java.awt.Color(242, 242, 242));
        txtMaImei.setBorder(null);
        panelThemImei.add(txtMaImei, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 181, 30));

        panelThemImei.add(cboMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 100, 123, 31));

        btnClear1.setBackground(new java.awt.Color(153, 204, 255));
        btnClear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Text.png"))); // NOI18N
        btnClear1.setText("Làm Mới");
        btnClear1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });
        panelThemImei.add(btnClear1, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 289, 181, 40));

        btnThemImeiSP.setBackground(new java.awt.Color(153, 204, 255));
        btnThemImeiSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnThemImeiSP.setText("Thêm Imei");
        btnThemImeiSP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnThemImeiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemImeiSPActionPerformed(evt);
            }
        });
        panelThemImei.add(btnThemImeiSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 231, 181, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 51, 51));
        jLabel13.setText("Thêm Imei Cho Sản Phẩm");
        panelThemImei.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 28, -1, -1));

        btnImportExcel.setBackground(new java.awt.Color(153, 204, 255));
        btnImportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excel.png"))); // NOI18N
        btnImportExcel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnImportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportExcelActionPerformed(evt);
            }
        });
        panelThemImei.add(btnImportExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 91, -1, 40));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        panelThemImei.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 180, 10));

        jPanel2.add(panelThemImei, new org.netbeans.lib.awtextra.AbsoluteConstraints(917, 74, -1, 360));

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnXoaImei.setBackground(new java.awt.Color(153, 204, 255));
        btnXoaImei.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Trash.png"))); // NOI18N
        btnXoaImei.setText("Xóa Imei");
        btnXoaImei.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXoaImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaImeiActionPerformed(evt);
            }
        });

        txtImeiClick.setEditable(false);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("Mã Imei");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 51, 51));
        jLabel17.setText("Imei Chi Tiết");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtImeiClick)
                    .addComponent(btnXoaImei, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(55, 55, 55)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(txtImeiClick, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaImei, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 480, 220, 220));

        tblImeiChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Imei"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblImeiChiTiet.setGridColor(new java.awt.Color(204, 204, 204));
        tblImeiChiTiet.setRowHeight(30);
        tblImeiChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblImeiChiTietMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblImeiChiTiet);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Imei Sản Phẩm", jPanel5);

        jPanel2.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 449, -1, -1));

        txtMax.setBorder(null);
        txtMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaxKeyReleased(evt);
            }
        });
        jPanel2.add(txtMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 120, 30));

        txtMin.setBorder(null);
        txtMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMinKeyReleased(evt);
            }
        });
        jPanel2.add(txtMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 116, 30));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Tìm Theo Giá :");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, -1));

        btnTimTheoGia.setBackground(new java.awt.Color(153, 204, 255));
        btnTimTheoGia.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnTimTheoGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Zoom.png"))); // NOI18N
        btnTimTheoGia.setText("Tìm Kiếm");
        btnTimTheoGia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTimTheoGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimTheoGiaActionPerformed(evt);
            }
        });
        jPanel2.add(btnTimTheoGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, 37));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel29.setText("-->");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, 30));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 120, 10));

        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 120, 10));

        jTabbedPane1.addTab("Imei Sản Phẩm", jPanel2);

        jPanelSanPhamDaXoa.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSanPhamDaXoa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTimKiemSPCu.setBorder(null);
        txtTimKiemSPCu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSPCuKeyReleased(evt);
            }
        });
        jPanelSanPhamDaXoa.add(txtTimKiemSPCu, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 40, 550, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tìm Kiếm");
        jPanelSanPhamDaXoa.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 8, -1, -1));

        tblSanPhamDaXoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Ram ", "Dung Lượng Pin", "Rom", "CPU", "Giá Bán", "Giá Nhập", "Số lượng", "Phân  Loại", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamDaXoa.setGridColor(new java.awt.Color(204, 204, 204));
        tblSanPhamDaXoa.setRowHeight(30);
        tblSanPhamDaXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamDaXoaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblSanPhamDaXoa);

        jPanelSanPhamDaXoa.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 98, 1169, 624));

        btnKhoiPhucSP.setBackground(new java.awt.Color(153, 204, 255));
        btnKhoiPhucSP.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnKhoiPhucSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        btnKhoiPhucSP.setText("Khôi Phục");
        btnKhoiPhucSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucSPActionPerformed(evt);
            }
        });
        jPanelSanPhamDaXoa.add(btnKhoiPhucSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(1023, 31, -1, 40));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jPanelSanPhamDaXoa.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 550, 20));

        jTabbedPane1.addTab("Sản Phẩm Đã Xóa", jPanelSanPhamDaXoa);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRamActionPerformed
        // TODO add your handling code here:
        Ram_View ram = new Ram_View(this);
        ram.setVisible(true);

    }//GEN-LAST:event_btnRamActionPerformed

    private void btnDungLuongPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDungLuongPinActionPerformed
        // TODO add your handling code here:
        DungLuongPin_View pin = new DungLuongPin_View(this);
        pin.setVisible(true);
    }//GEN-LAST:event_btnDungLuongPinActionPerformed

    private void btnXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatXuActionPerformed
        // TODO add your handling code here:
        XuatXu_View xuatxu = new XuatXu_View(this);
        xuatxu.setVisible(true);

    }//GEN-LAST:event_btnXuatXuActionPerformed

    private void btnMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacActionPerformed
        // TODO add your handling code here:
        MauSac_View mauSac = new MauSac_View(this);
        mauSac.setVisible(true);
    }//GEN-LAST:event_btnMauSacActionPerformed

    private void btnRomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRomActionPerformed
        // TODO add your handling code here:
        Rom_View rom = new Rom_View(this);
        rom.setVisible(true);

    }//GEN-LAST:event_btnRomActionPerformed

    private void btnKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKichThuocActionPerformed
        // TODO add your handling code here:
        KichThuoc_View kt = new KichThuoc_View(this);
        kt.setVisible(true);
    }//GEN-LAST:event_btnKichThuocActionPerformed


    private void btnCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCPUActionPerformed
        // TODO add your handling code here:
        CPU_View cpu = new CPU_View(this);
        cpu.setVisible(true);
    }//GEN-LAST:event_btnCPUActionPerformed

    private void btnPhanLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhanLoaiActionPerformed
        // TODO add your handling code here:
        PhanLoai_View pl = new PhanLoai_View(this);
        pl.setVisible(true);

    }//GEN-LAST:event_btnPhanLoaiActionPerformed

    private void btnCPUKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCPUKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_btnCPUKeyReleased
    private SanPham_Response _themImeiSP() {
        SanPham_Response sp = SanPham_Response.builder()
                .imei(txtMaImei.getText())
                .idSanPham(getID_MaSP_CBB())
                .build();
        return sp;
    }

    private SanPham_Response _update_SoLuongSP() {
        // lay ma tu cbomaSP
        String maSP = cboMaSanPham.getSelectedItem() + "";
        //lay ton kho theo ma sp
        SanPham_Response get_SP = repo_SanPham.getTonKho_TheoMaSP(maSP).get(0);
        // truyen vao sp 
        SanPham_Response sp = SanPham_Response.builder()
                .soLuong(get_SP.getTongImei())
                .maSanPham(cboMaSanPham.getSelectedItem() + "")
                .build();
        return sp;
    }
    private int layTongImei_theoID_SP(int idSP){
         
        //lay ton kho theo ma sp
        SanPham_Response get_SP = repo_SanPham.getTonKho_TheoIDSP_Excel(idSP).get(0);
        return get_SP.getTongImei(); 
    }
    private void btnThemImeiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemImeiSPActionPerformed
        boolean kt = true;
        if (validate_Form_ThemImei()) {
            for (Imei imei : repo_Imei.getAll_Imei()) {
                if (imei.getMaImei().equals(txtMaImei.getText())) {
                    MsgBox.showMessage(this, "Imei Đã Tồn tại");
                    kt = false;
                    break;
                }
            }
            // thực hiện kiểm tra và thêm
            if (kt) {

                repo_SanPham.ThemImei_SP(_themImeiSP()); // htmee sản phẩm
                // cập nhật lại vào số lượng sản phẩm trong db sản phẩm 
                repo_SanPham.update_TonKho(_update_SoLuongSP());
                // hiển thị lại table sản phẩm imei
                if (txtTimKiemSanPhamMoi.getText().isEmpty()) {
                    fillTable_SP_Imei(repo_SanPham.getAll_SP_Moi());
                } else {
                    fillTable_SP_Imei(repo_SanPham.timKiem_SP(txtTimKiemSanPhamMoi.getText(), 0));
                }
                //  hiển thị lại bảng imei chi tiết khi đã xóa 
                fillTable_Imei_ChiTiet(repo_SanPham.getAll_ImeiChiTiet(cboMaSanPham.getSelectedItem() + ""));
                // hiển thị lại bảng sản phẩm
                fillTable_SP(repo_SanPham.getAll_SP_Moi());

                MsgBox.showMessage(this, "Thêm Thành Công");
                lblTonKho.setText("?");
            }
        }

    }//GEN-LAST:event_btnThemImeiSPActionPerformed

    private void tblSanPhamImeiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamImeiMouseClicked
        // TODO add your handling code here:
        click_Imei_SanPham = tblSanPhamImei.getSelectedRow();
        if (click_Imei_SanPham < 0) {
            return;
        }

        String maSP = tblSanPhamImei.getValueAt(click_Imei_SanPham, 1).toString();
        fillTable_Imei_ChiTiet(repo_SanPham.getAll_ImeiChiTiet(maSP));
        // hiển thị lên cbo mã click xóa
        cboMaSanPham.setSelectedItem(maSP);
        // set clickImei thành trống 
        txtImeiClick.setText("");
        // đóng ô  xóa
        btnXoaImei.setEnabled(false);
    }//GEN-LAST:event_tblSanPhamImeiMouseClicked

    private void tblImeiChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblImeiChiTietMouseClicked
        // hiển thị btn sửa xóa
        btnXoaImei.setEnabled(true);
        click_Imei_ChiTiet = tblImeiChiTiet.getSelectedRow();
        if (click_Imei_ChiTiet < 0) {
            return;
        }

        // hiển thị lên txtCLickImei
        String ImeiClick = tblImeiChiTiet.getValueAt(click_Imei_ChiTiet, 2).toString();
        txtImeiClick.setText(ImeiClick);

    }//GEN-LAST:event_tblImeiChiTietMouseClicked

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        // TODO add your handling code here:
        txtMaImei.setText("");

    }//GEN-LAST:event_btnClear1ActionPerformed
    private String docHinhAnh_SP() {

        JFileChooser fileChooser = new JFileChooser("C:\\Users\\duocn\\OneDrive\\Âm nhạc\\Máy tính\\Dự Án 1_SD19304_Nhóm3\\QuanLyBanDienThoaiIphone\\src\\img_product");
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
        // TODO add your handling code here:
        docHinhAnh_SP();
    }//GEN-LAST:event_lblHinhAnhMouseClicked
    public void clearFormSP() {
        txtTenSanPham.setText("");
        txtGiaNhap.setText("");
        txtGiaban.setText("");
        txtMoTa.setText("");
        lblHinhAnh.setText("HinhAnh.png");
        pathFile = null;
        lblHinhAnh.setIcon(null);
        // xóa trắng cbo 
        cboRom.setSelectedItem(null);
        cboRam.setSelectedItem(null);
        cboDungLuongPin.setSelectedItem(null);
        cboKichThuocMan.setSelectedItem(null);
        cboXuatXu.setSelectedItem(null);
        cboCPU.setSelectedItem(null);
        cboMauSac.setSelectedItem(null);
        cboPhanLoai.setSelectedItem(null);
        lblTonKho.setText("?");
        btnThemSP.setEnabled(true);
        btnSuaSP.setEnabled(false);
        btnXoaSP.setEnabled(false);
        btnKhoiPhucSP.setEnabled(false);
    }

    private void updateStatus() {
        btnXoaSP.setEnabled(false);
        btnSuaSP.setEnabled(false);
    }
    private void btnClearSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSPActionPerformed
        // TODO add your handling code here:
        clearFormSP();
        fillTable_SP(repo_SanPham.getAll_SP_Moi());
    }//GEN-LAST:event_btnClearSPActionPerformed
    public SanPham_Response _themSP() {
        SanPham_Response sp = SanPham_Response.builder()
                .tenSanPham(txtTenSanPham.getText())
                .moTa(txtMoTa.getText())
                .giaNhap(Float.parseFloat(txtGiaNhap.getText()))
                .giaBan(Float.parseFloat(txtGiaban.getText()))
                .hinhAnh(pathFile)
                .idRom(getID_Rom_CBB())
                .idMauSac(getID_MauSac_CBB())
                .idRam(getID_Ram_CBB())
                .idKichThuoc(getID_KichThuoc_CBB())
                .idDungLuongPin(getID_Pin_CBB())
                .idCPU(getID_CPU_CBB())
                .idXuatXu(getID_XuatXu_CBB())
                .idPhanLoai(getID_PhanLoai_CBB())
                .build();
        return sp;
    }
    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        // TODO add your handling code here:
        if (validate_Form_SP()) {
            repo_SanPham.ThemSanPham(_themSP());
            // hiển thị lại bảng sản phẩm 
            fillTable_SP(repo_SanPham.getAll_SP_Moi());
            fillTo_CBO_MaSanPham(repo_SanPham.getAll_SP_Moi());// load lại cbo sản phẩm
            fillTable_SP_Imei(repo_SanPham.getAll_SP_Moi()); // load lại bảng sp imei
            // xóa bảng imei chi tiết 
            model_ImeiChiTiet.setRowCount(0);
            btnXoaSP.setEnabled(false);
            btnSuaSP.setEnabled(false);
            MsgBox.showMessage(this, "Thêm Thành Công");
            lblTonKho.setText("0");
        }
    }//GEN-LAST:event_btnThemSPActionPerformed
    private String layMaSP_Update() {
        click_Imei_SanPham = tblSanPhamMoi.getSelectedRow();
        String maSP = tblSanPhamMoi.getValueAt(click_Imei_SanPham, 1).toString();
        return maSP;
    }
    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed

        if (validate_Form_SP()) {
            boolean check = MsgBox.showConfirm(this, "Xác Nhận Update SP Có Mã : " + layMaSP_Update());
            if (check) {
                repo_SanPham.Update_ThuocTinhSP(_themSP(), layMaSP_Update());
                fillTable_SP(repo_SanPham.getAll_SP_Moi());
                fillTo_CBO_MaSanPham(repo_SanPham.getAll_SP_Moi());// load lại cbo sản phẩm
                fillTable_SP_Imei(repo_SanPham.getAll_SP_Moi()); // load lại bảng sp imei
                MsgBox.showMessage(this, "Update Thành Công");

            }
        }
    }//GEN-LAST:event_btnSuaSPActionPerformed
    private void _MouseClick_SP() {
        click_Imei_SanPham = tblSanPhamMoi.getSelectedRow();
        // lấy ra mã sp từ table 
        String maSP = tblSanPhamMoi.getValueAt(click_Imei_SanPham, 1).toString();
        //  lấy thông tin sp theo ma 
        SanPham_Response sp = repo_SanPham.getAll_SP_TheoMaSP(maSP).get(0);
        // gán vào ô 
        txtTenSanPham.setText(sp.getTenSanPham());
        txtGiaNhap.setText((int) sp.getGiaNhap() + "");
        txtGiaban.setText((int) sp.getGiaBan() + "");
        txtMoTa.setText(sp.getMoTa());
        // gán vào cbo
        cboRam.setSelectedItem(sp.getRam());
        cboRom.setSelectedItem(sp.getRom());
        cboDungLuongPin.setSelectedItem(sp.getDungLuongPin());
        cboKichThuocMan.setSelectedItem(sp.getKichThuoc());
        cboXuatXu.setSelectedItem(sp.getXuatXu());
        cboCPU.setSelectedItem(sp.getCPU());
        cboMauSac.setSelectedItem(sp.getMauSac());
        cboPhanLoai.setSelectedItem(sp.getPhanLoai());
        lblTonKho.setText(sp.getSoLuong() + "");
        try {
            ImageIcon Icon = new ImageIcon(sp.getHinhAnh());
            lblHinhAnh.setIcon(Icon);
            lblHinhAnh.setText("");
        } catch (Exception e) {
            lblHinhAnh.setIcon(null);
            lblHinhAnh.setText("Hình ảnh.png");
        }
        pathFile = sp.getHinhAnh();
    }
    private void tblSanPhamMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMoiMouseClicked
        _MouseClick_SP();
        btnSuaSP.setEnabled(true);
        btnXoaSP.setEnabled(true);
        btnThemSP.setEnabled(false);

    }//GEN-LAST:event_tblSanPhamMoiMouseClicked
    private String _xoaImei_SP() {
        click_Imei_ChiTiet = tblImeiChiTiet.getSelectedRow();
        String maImei = tblImeiChiTiet.getValueAt(click_Imei_ChiTiet, 2).toString();
        return maImei;
    }

    private SanPham_Response _update_SoLuongSP_KhiXoa() {
        // lay ma sp  tu table 
        click_Imei_ChiTiet = tblImeiChiTiet.getSelectedRow();
        String maSP = tblImeiChiTiet.getValueAt(click_Imei_ChiTiet, 1).toString();
        //lay ton kho theo ma sp
        SanPham_Response get_SP = repo_SanPham.getTonKho_TheoMaSP(maSP).get(0);
        // kiểm tra nếu sluong con 1 

        // truyen vao sp 
        SanPham_Response sp = SanPham_Response.builder()
                .soLuong(get_SP.getTongImei() - 1)
                .maSanPham(cboMaSanPham.getSelectedItem() + "")
                .build();
        return sp;
    }

    private void btnXoaImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaImeiActionPerformed
        // hiển thị imei khi click 
        String imeiCLick = tblImeiChiTiet.getValueAt(click_Imei_ChiTiet, 2).toString();
        boolean LuaChon = MsgBox.showConfirm(this, "Xác Nhận Xóa Imei : " + imeiCLick);
        if (LuaChon) {
            // cập nhật lại vào số lượng sản phẩm trong db sản phẩm
            repo_SanPham.update_TonKho(_update_SoLuongSP_KhiXoa());
            // xóa imei sp
            repo_SanPham.xoa_ImeiSP(_xoaImei_SP());
            // hiển thị lại table sản phẩm imei
            fillTable_SP_Imei(repo_SanPham.getAll_SP_Moi());
            //  hiển thị lại bảng imei chi tiết khi đã xóa 
            String maSP = tblSanPhamImei.getValueAt(click_Imei_SanPham, 1).toString();
            fillTable_Imei_ChiTiet(repo_SanPham.getAll_ImeiChiTiet(maSP));
            // hiển thị lại bảng sản phẩm
            fillTable_SP(repo_SanPham.getAll_SP_Moi());
            btnXoaImei.setEnabled(false);
            txtImeiClick.setText("");
            MsgBox.showMessage(this, "Xóa Thành Công");
            lblTonKho.setText("?");
        }
    }//GEN-LAST:event_btnXoaImeiActionPerformed
    private String _xoaSP_TheoMaSP() {
        click_Imei_SanPham = tblSanPhamMoi.getSelectedRow();
        String maSP = tblSanPhamMoi.getValueAt(click_Imei_SanPham, 1).toString();
        return maSP;
    }

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed

        boolean checkSP = MsgBox.showConfirm(this, "Xác Nhận Xóa SP Có Mã :" + _xoaSP_TheoMaSP());
        if (checkSP) {
            // xóa sản phẩm theo mã sản phẩm
            repo_SanPham.xoaSanPham(_xoaSP_TheoMaSP());
            // hiển thị bảng sp
            fillTable_SP(repo_SanPham.getAll_SP_Moi());
            // hiển thị lại bảng imei sản phẩm
            fillTable_SP_Imei(repo_SanPham.getAll_SP_Moi());
            // đóng bảng imei chi tiết 
            model_ImeiChiTiet.setRowCount(0);
            // hiển thị lại bảng sản phẩm đã xóa
            fillTable_SP_DaXoa(repo_SanPham.getAll_SP_DaXoa());
            // load lại cbo sản phẩm
            fillTo_CBO_MaSanPham(repo_SanPham.getAll_SP_Moi());
            MsgBox.showMessage(this, "Xóa Thành Công");
            btnKhoiPhucSP.setEnabled(false);
            clearFormSP();
        }
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void tblSanPhamDaXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamDaXoaMouseClicked
        // TODO add your handling code here:
        int check = 0;
        for (int i = 0; i < tblSanPhamDaXoa.getRowCount(); i++) {
            try {
                Boolean isSelected = (Boolean) tblSanPhamDaXoa.getValueAt(i, 11); // Cột thứ 2 (chỉ số là 1)
                if (isSelected) {
                    check = 1;
                } else {
                    check = 2;
                }
            } catch (Exception e) {
            }
        }
        if (check == 1) {
            btnKhoiPhucSP.setEnabled(true);
        } else if (check == 2) {
            btnKhoiPhucSP.setEnabled(false);
        }

    }//GEN-LAST:event_tblSanPhamDaXoaMouseClicked
    private ArrayList<String> listMaSP_Selected = new ArrayList<>();

    private String _khoiPhucSP_TheoMaSP() {
        click_SanPham_DaXoa = tblSanPhamDaXoa.getSelectedRow();

        String maSP = tblSanPhamDaXoa.getValueAt(click_SanPham_DaXoa, 1).toString();
        return maSP;
    }

    private void add_MaSP_List() {
        listMaSP_Selected.clear();
        for (int i = 0; i < tblSanPhamDaXoa.getRowCount(); i++) {
            try {
                Boolean isSelected = (Boolean) tblSanPhamDaXoa.getValueAt(i, 11); // Cột thứ 2 (chỉ số là 1)
                if (isSelected) {
                    listMaSP_Selected.add(tblSanPhamDaXoa.getValueAt(i, 1).toString());
                }
            } catch (Exception e) {
            }
        }
    }
    private void btnKhoiPhucSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucSPActionPerformed
        // xác nhận khôi phục sp ko
        boolean checkSP = MsgBox.showConfirm(this, "Xác Nhận Khôi Phục SP ");
        if (checkSP) {

            // Khôi Phục sản phẩm theo mã sản phẩm
            add_MaSP_List();
            for (int i = 0; i < listMaSP_Selected.size(); i++) {
                repo_SanPham.khoiPhuc_SP(listMaSP_Selected.get(i));
            }

            fillTable_SP(repo_SanPham.getAll_SP_Moi());
            // hiển thị lại bảng imei sản phẩm
            fillTable_SP_Imei(repo_SanPham.getAll_SP_Moi());
            // đóng bảng imei chi tiết 
            model_ImeiChiTiet.setRowCount(0);
            // hiển thị lại bảng sản phẩm đã xóa
            fillTable_SP_DaXoa(repo_SanPham.getAll_SP_DaXoa());
            // load lại cbo sản phẩm
            fillTo_CBO_MaSanPham(repo_SanPham.getAll_SP_Moi());
            MsgBox.showMessage(this, "Khôi Phục Thành Công");
            btnKhoiPhucSP.setEnabled(false);
            clearFormSP();
        }

    }//GEN-LAST:event_btnKhoiPhucSPActionPerformed

    private void txtTimKiemSanPhamMoiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamMoiKeyReleased
        // TODO add your handling code here:
        if (txtTimKiemSanPhamMoi.getText().isEmpty()) {
            fillTable_SP(repo_SanPham.getAll_SP_Moi());
            fillTable_SP_Imei(repo_SanPham.getAll_SP_Moi());
        } else {
            fillTable_SP(repo_SanPham.timKiem_SP(txtTimKiemSanPhamMoi.getText(), 0));
            fillTable_SP_Imei(repo_SanPham.timKiem_SP(txtTimKiemSanPhamMoi.getText(), 0));
            model_ImeiChiTiet.setRowCount(0);
        }
    }//GEN-LAST:event_txtTimKiemSanPhamMoiKeyReleased

    private void txtTimTheoImeiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimTheoImeiKeyReleased
        // TODO add your handling code here:
        if (txtTimTheoImei.getText().isEmpty()) {
            fillTable_SP(repo_SanPham.getAll_SP_Moi());
            fillTable_SP_Imei(repo_SanPham.getAll_SP_Moi());
        } else {
            fillTable_SP(repo_SanPham.timKiem_SPTheoImei(txtTimTheoImei.getText()));
            fillTable_SP_Imei(repo_SanPham.timKiem_SPTheoImei(txtTimTheoImei.getText()));
            model_ImeiChiTiet.setRowCount(0);
        }
    }//GEN-LAST:event_txtTimTheoImeiKeyReleased

    private void txtTimKiemSPCuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSPCuKeyReleased
        // TODO add your handling code here:
        if (txtTimKiemSPCu.getText().isEmpty()) {
            fillTable_SP_DaXoa(repo_SanPham.getAll_SP_DaXoa());

        } else {
            fillTable_SP_DaXoa(repo_SanPham.timKiem_SP(txtTimKiemSPCu.getText(), 1));

            model_ImeiChiTiet.setRowCount(0);
        }
    }//GEN-LAST:event_txtTimKiemSPCuKeyReleased
    private boolean validate_TimTheoGia() {
        if (txtMin.getText().isEmpty()) {
            MsgBox.showMessage(this, "Nhập Khoảng Min");
            return false;
        } else if (txtMax.getText().isEmpty()) {
            MsgBox.showMessage(this, "Nhập Khoảng Max");
            return false;
        }
        Integer min = null, max = null;
        try {
            min = Integer.parseInt(txtMin.getText());

        } catch (Exception e) {
            return false;
        }
        try {
            max = Integer.parseInt(txtMax.getText());

        } catch (Exception e) {
            return false;
        }
        if (min > max) {
            MsgBox.showMessage(this, "Min Phải < Max");
            return false;
        }

        return true;
    }
    private void btnTimTheoGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimTheoGiaActionPerformed
        if (validate_TimTheoGia()) {
            fillTable_SP(repo_SanPham.timKiem_SP_TheoGia(txtMin.getText(), txtMax.getText(), 0));
            fillTable_SP_Imei(repo_SanPham.timKiem_SP_TheoGia(txtMin.getText(), txtMax.getText(), 0));
            model_ImeiChiTiet.setRowCount(0);
        }
    }//GEN-LAST:event_btnTimTheoGiaActionPerformed

    private void txtMinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinKeyReleased
        // TODO add your handling code here:
        if (txtMin.getText().isEmpty()) {
            fillTable_SP(repo_SanPham.getAll_SP_Moi());
            fillTable_SP_Imei(repo_SanPham.getAll_SP_Moi());
            model_ImeiChiTiet.setRowCount(0);
        }
    }//GEN-LAST:event_txtMinKeyReleased

    private void txtMaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaxKeyReleased
        // TODO add your handling code here:
        if (txtMax.getText().isEmpty()) {
            fillTable_SP(repo_SanPham.getAll_SP_Moi());
            fillTable_SP_Imei(repo_SanPham.getAll_SP_Moi());
            model_ImeiChiTiet.setRowCount(0);
        }
    }//GEN-LAST:event_txtMaxKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        boolean luaChon = MsgBox.showConfirm(this, "Xuất Excel Sản Phẩm Tồn Kho");
        if (luaChon) {
            xuatExcel();
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void import_Imei_Excel() throws IOException {
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJtableImport = null;

        String defaultCurrentDirecotoryPath = "C:\\Users\\duocn\\OneDrive\\"
                + "Âm nhạc\\Máy tính\\Dự Án 1_SD19304_Nhóm3"
                + "\\QuanLyBanDienThoaiIphone\\File_Excel";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirecotoryPath);
        // filter only excel files 
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);

        //set dialog title 
        excelFileChooser.setDialogTitle("Select Excel File");
        int excelChooser = excelFileChooser.showOpenDialog(null);
        // if open button is clicked 
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelJtableImport = new XSSFWorkbook(excelBIS);

                XSSFSheet excelSheet = excelJtableImport.getSheetAt(0);

                for (int row = 0; row <= excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);

                    XSSFCell excelImei = excelRow.getCell(0);
                    
                    repo_SanPham.ThemImei_SP_Excel(excelImei.getStringCellValue(),
                            layIDSP_theo_CBO_MaSP());
                 
                    // cập nhật lại vào số lượng sản phẩm trong db sản phẩm 
                    try {
                          repo_SanPham.update_TonKho_Excel(
                     layTongImei_theoID_SP(layIDSP_theo_CBO_MaSP()),
                      layIDSP_theo_CBO_MaSP());
                    } catch (Exception e) {
                    }
                  
                    
                    
//                   for(int column = 0; column<excelRow.getLastCellNum(); column++){
//                       XSSFCell excelCell = excelRow.getCell(column);
//                       System.out.println(excelCell.getStringCellValue());
//                   }
                }

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } finally {
                try {
                    if (excelFIS != null) {
                        excelFIS.close();
                    }
                    if (excelBIS != null) {
                        excelBIS.close();
                    }
                    if (excelJtableImport != null) {
                        excelJtableImport.close();
                    }

                } catch (IOException iOException) {
                    JOptionPane.showMessageDialog(this, iOException.getMessage());
                }
            }
            MsgBox.showMessage(this, "Import Thành Công");
        }
        return;
    }
    private int layIDSP_theo_CBO_MaSP(){
                return repo_SanPham.getID_SP_CBO_MaSP(cboMaSanPham.getSelectedItem()+"").get(0).getIdSanPham();

    }
    private void btnImportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportExcelActionPerformed
        try {
            // TODO add your handling code here:
            boolean check = MsgBox.showConfirm(this, "Import Imei Sản Phẩm");
            if (check) {
                import_Imei_Excel();
                // hiển thị lại table
                fillTable_SP(repo_SanPham.getAll_SP_Moi());
                fillTable_SP_Imei(repo_SanPham.getAll_SP_Moi());
                model_ImeiChiTiet.setRowCount(0);
            }
        } catch (IOException ex) {
            Logger.getLogger(SanPham_form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImportExcelActionPerformed
    private static final String ouputQR = "C:\\Users\\duocn\\OneDrive\\Âm nhạc\\Máy tính\\Dự Án 1_SD19304_Nhóm3\\QuanLyBanDienThoaiIphone\\QrCode_\\QrCode_";
    private static void generateQRCode(String text, int width, int height ,String filePath)throws WriterException{
        QRCodeWriter qc = new QRCodeWriter(); 
        BitMatrix bm = qc.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path pobj = FileSystems.getDefault().getPath(filePath);
        try {
            MatrixToImageWriter.writeToPath(bm,"PNG", pobj);
        } catch (IOException ex) {
            Logger.getLogger(QRCode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private String layMaSP_Click(){
        click_San_Pham = tblSanPhamMoi.getSelectedRow(); 
        String maSP = tblSanPhamMoi.getValueAt(click_San_Pham,1).toString();
        return maSP; 
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
      click_San_Pham= tblSanPhamMoi.getSelectedRow();
      if(click_San_Pham<0){
          MsgBox.showMessage(this,"Chưa Chọn SP muốn tạo QR");
          return; 
      }
      boolean check =  MsgBox.showConfirm(this,"Xác Nhận Tạo QrCode Cho : "+layMaSP_Click());
      if(check ==true){
          try {
              generateQRCode(layMaSP_Click(),300,300, ouputQR+layMaSP_Click());
              MsgBox.showMessage(this,"Tạo Thành Công");
          } catch (WriterException ex) {
              Logger.getLogger(SanPham_form.class.getName()).log(Level.SEVERE, null, ex);
              
          }
      }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        boolean check = MsgBox.showConfirm(this,"Xác Nhận Tìm SP bằng Quét Qr_Code");
        if(check==true){
        QrCode_View_SanPham sp_QR = new QrCode_View_SanPham();
        sp_QR.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtTimKiemSanPhamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemSanPhamMoiActionPerformed

    private void xuatExcel() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Sản Phẩm Tồn Kho");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã SP");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên SP");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Ram");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Dung Lượng Pin");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Xuất Xứ");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Màu Sắc");
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Rom ");
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Kích Thước Màn");
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("CPU");
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("Giá Nhập");
            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue("Giá Bán");
            cell = row.createCell(12, CellType.STRING);
            cell.setCellValue("Phân Loại");
            cell = row.createCell(13, CellType.STRING);
            cell.setCellValue("Tồn Kho");
            // list sản phẩm chưa bán 
            ArrayList<SanPham_Response> listItem = repo_SanPham.getXuatExcel();
            if (listItem != null) {
                int s = listItem.size();
                for (int i = 0; i < s; i++) {
                    SanPham_Response sp = listItem.get(i);
                    row = sheet.createRow(4 + i);
                    cell = row.createCell(0, CellType.NUMERIC);
                    cell.setCellValue(i + 1);
                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(sp.getMaSanPham());
                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(sp.getTenSanPham());
                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(sp.getRam());
                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue(sp.getDungLuongPin());
                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue(sp.getXuatXu());
                    cell = row.createCell(6, CellType.STRING);
                    cell.setCellValue(sp.getMauSac());
                    cell = row.createCell(7, CellType.STRING);
                    cell.setCellValue(sp.getRom());
                    cell = row.createCell(8, CellType.STRING);
                    cell.setCellValue(sp.getKichThuoc());
                    cell = row.createCell(9, CellType.STRING);
                    cell.setCellValue(sp.getCPU());
                    cell = row.createCell(10, CellType.STRING);
                    cell.setCellValue(sp.getGiaNhap());
                    cell = row.createCell(11, CellType.STRING);
                    cell.setCellValue(sp.getGiaBan());
                    cell = row.createCell(12, CellType.NUMERIC);
                    cell.setCellValue(sp.getPhanLoai());
                    cell = row.createCell(13, CellType.NUMERIC);
                    cell.setCellValue(sp.getSoLuong());

                }
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = fileChooser.getSelectedFile();

                try {
                    FileOutputStream fis = new FileOutputStream(
                            selectedFolder.getAbsolutePath() + File.separator + "DanhSachSP.xlsx");
                    workbook.write(fis);
                    fis.close();
                    JOptionPane.showMessageDialog(this, "Xuất thành công");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {

        }
    }

    public Integer getID_Ram_CBB() {
        for (Ram r : repo_Ram.getAll()) {
            if (cboRam.getSelectedItem().equals(r.getRam())) {
                return r.getId_Ram();
            }
        }
        return null;
    }

    public Integer getID_Rom_CBB() {
        for (Rom r : repo_Rom.getAll()) {
            if (cboRom.getSelectedItem().equals(r.getRom())) {
                return r.getId_Rom();
            }
        }
        return null;
    }

    public Integer getID_Pin_CBB() {
        for (DungLuongPin r : repo_Pin.getAll()) {
            if (cboDungLuongPin.getSelectedItem().equals(r.getDungLuongPin())) {
                return r.getId_DungLuongPin();
            }
        }
        return null;
    }

    public Integer getID_KichThuoc_CBB() {
        for (KichThuoc r : repo_KichThuoc.getAll()) {
            if (cboKichThuocMan.getSelectedItem().equals(r.getKichThuoc())) {
                return r.getId_KichThuoc();
            }
        }
        return null;
    }

    public Integer getID_XuatXu_CBB() {
        for (XuatXu r : repo_XuatXu.getAll()) {
            if (cboXuatXu.getSelectedItem().equals(r.getXuatXu())) {
                return r.getId_XuatXu();
            }
        }
        return null;
    }

    public Integer getID_CPU_CBB() {
        for (CPU r : repo_CPU.getAll()) {
            if (cboCPU.getSelectedItem().equals(r.getCPU())) {
                return r.getId_CPU();
            }
        }
        return null;
    }

    public Integer getID_MauSac_CBB() {
        for (MauSac r : repo_MauSac.getAll()) {
            if (cboMauSac.getSelectedItem().equals(r.getMauSac())) {
                return r.getId_MauSac();
            }
        }
        return null;
    }

    public Integer getID_PhanLoai_CBB() {
        for (PhanLoai r : repo_PhanLoai.getAll()) {
            if (cboPhanLoai.getSelectedItem().equals(r.getPhanLoai())) {
                return r.getId_PhanLoai();
            }
        }
        return null;
    }

    public Integer getID_MaSP_CBB() {
        for (SanPham_Response sp : repo_SanPham.getAll_SP_Moi()) {
            if (cboMaSanPham.getSelectedItem().equals(sp.getMaSanPham())) {
                return sp.getIdSanPham();
            }
        }
        return null;
    }

    private boolean validate_ThuocTinh_SP() {
        if (cboRam.getSelectedItem() == null) {
            MsgBox.showMessage(this, "Vui Lòng Chọn Ram");
            return false;
        } else if (cboDungLuongPin.getSelectedItem() == null) {
            MsgBox.showMessage(this, "Vui Lòng Chọn Dung Lượng Pin");
            return false;
        } else if (cboXuatXu.getSelectedItem() == null) {
            MsgBox.showMessage(this, "Vui Lòng Chọn Xuất Xứ");
            return false;
        } else if (cboMauSac.getSelectedItem() == null) {
            MsgBox.showMessage(this, "Vui Lòng Chọn Màu Sắc");
            return false;
        } else if (cboRom.getSelectedItem() == null) {
            MsgBox.showMessage(this, "Vui Lòng Chọn Rom");
            return false;
        } else if (cboKichThuocMan.getSelectedItem() == null) {
            MsgBox.showMessage(this, "Vui Lòng Chọn Kích Thước Màn");
            return false;
        } else if (cboCPU.getSelectedItem() == null) {
            MsgBox.showMessage(this, "Vui Lòng Chọn CPU");
            return false;
        } else if (cboPhanLoai.getSelectedItem() == null) {
            MsgBox.showMessage(this, "Vui Lòng Chọn Phân loại");
            return false;
        }
        return true;
    }

    private boolean validate_Form_SP() {
        if (txtTenSanPham.getText().isEmpty()) {
            MsgBox.showMessage(this, "Vui lòng nhập Tên SP");
            return false;
        } else if (txtGiaNhap.getText().isEmpty()) {
            MsgBox.showMessage(this, "Vui lòng nhập Giá Nhập");
            return false;
        } else if (txtGiaban.getText().isEmpty()) {
            MsgBox.showMessage(this, "Vui lòng nhập Giá Bán");
            return false;
        }
        int giaNhap, giaBan;
        try {
            giaNhap = Integer.parseInt(txtGiaNhap.getText().trim());

        } catch (Exception e) {
            MsgBox.showMessage(this, "Giá Nhập Phải là số nguyên");
            return false;
        }
        if (giaNhap < 0) {
            MsgBox.showMessage(this, "Giá Nhập Không Được Nhỏ Hơn 0");
            return false;
        }
        try {
            giaBan = Integer.parseInt(txtGiaban.getText().trim());

        } catch (Exception e) {
            MsgBox.showMessage(this, "Giá Bán Phải là số nguyên");
            return false;
        }
        if (giaBan < 0) {
            MsgBox.showMessage(this, "Giá Bán Không Được Nhỏ Hơn 0 ");
            return false;
        }
        if (giaNhap > giaBan) {
            MsgBox.showMessage(this, "Giá Nhập Phải < Giá Bán");
            return false;
        }

        if (!validate_ThuocTinh_SP()) {
            return false;
        }

        return true;
    }

    private boolean validate_Form_ThemImei() {
        if (txtMaImei.getText().isEmpty()) {
            MsgBox.showMessage(this, "Vui Lòng Nhập Imei");
            return false;
        }
        // kiểm tra xem có phải là số thôi k
        if (!txtMaImei.getText().trim().matches("\\d+")) {
            MsgBox.showMessage(this, "Imei Phải Là 1 Dãy Số");
            return false;
        }
        if (txtMaImei.getText().trim().length() != 15) {
            MsgBox.showMessage(this, "Mã Imei Phải Đúng 15 Số");
            return false;
        }

        return true;
    }

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
    public javax.swing.JButton btnCPU;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnClearSP;
    private javax.swing.JButton btnDungLuongPin;
    private javax.swing.JButton btnImportExcel;
    private javax.swing.JButton btnKhoiPhucSP;
    private javax.swing.JButton btnKichThuoc;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnPhanLoai;
    private javax.swing.JButton btnRam;
    public static javax.swing.JButton btnRom;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnThemImeiSP;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnTimTheoGia;
    private javax.swing.JButton btnXoaImei;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton btnXuatXu;
    public static javax.swing.JComboBox<Object> cboCPU;
    private javax.swing.JComboBox<String> cboDungLuongPin;
    private javax.swing.JComboBox<String> cboKichThuocMan;
    private javax.swing.JComboBox<String> cboMaSanPham;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboPhanLoai;
    private javax.swing.JComboBox<Object> cboRam;
    private javax.swing.JComboBox<String> cboRom;
    private javax.swing.JComboBox<String> cboXuatXu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelSanPham;
    private javax.swing.JPanel jPanelSanPhamDaXoa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblTonKho;
    private javax.swing.JPanel panelThemImei;
    private javax.swing.JTable tblImeiChiTiet;
    private javax.swing.JTable tblSanPhamDaXoa;
    private javax.swing.JTable tblSanPhamImei;
    private javax.swing.JTable tblSanPhamMoi;
    private javax.swing.JTextField txtGiaNhap;
    public static javax.swing.JTextField txtGiaban;
    private javax.swing.JTextField txtImeiClick;
    private javax.swing.JTextField txtMaImei;
    private javax.swing.JTextField txtMax;
    private javax.swing.JTextField txtMin;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenSanPham;
    public javax.swing.JTextField txtTimKiemSPCu;
    public javax.swing.JTextField txtTimKiemSanPhamMoi;
    private javax.swing.JTextField txtTimTheoImei;
    // End of variables declaration//GEN-END:variables
}
