package entity;

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
public class HoaDon_ {
    private int idHoaDon; 
    private String maHoaDon; 
    private Date ngayTao; 
    private Date ngayThanhToan;
    private float tongGia; 
    private String hinhThucTT; 
    private String tenNguoiNhan; 
    private String SDT; 
    private String diaChi; 
    private int trangThai;
    private int idKhachHang; 
    private int idGiamGia; 
    private int idNhanVien; 
}
