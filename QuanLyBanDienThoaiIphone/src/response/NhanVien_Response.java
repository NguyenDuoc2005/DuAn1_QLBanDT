package response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor   // contructor full tham so 
@NoArgsConstructor // contructor k tham so 
@Getter 
@Setter 
@ToString
@Builder // contructor tùy chọn tham số
public class NhanVien_Response {
    // nhân viên 
    private int id_NhanVien; 
    private String maNhanVien; 
    private String taiKhoan; 
    private String matKhau; 
    private String hoTen; 
    private boolean gioiTinh; 
    private Date ngaySinh; 
    private String diaChi; 
    private String SDT; 
    private String hinhAnh; 
    private String email ; 
    private int trangThai; 
    private int id_ChucVu_NV;
    // chuc vu 
    private int id_ChucVu;
    private String chucVu; 
    // khach hang va hoa don 
    private String tenKhachHang; 
    private String sdtKhachHang; 
    private String maHoaDon; 
    private long tongGia; 
    private Date ngayTao; 
    private Date ngayThanhToan; 
}
