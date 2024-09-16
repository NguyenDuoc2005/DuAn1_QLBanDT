package response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class HoaDon_BH_Response {
    private int idHoaDon; 
    private int idKhachHang;
    private int idNhanVien; 
    private String maHoaDon; 
    private Date ngayTao; 
    private Date ngayThanhToan;
    private long tongGia; 
    private String hinhThucTT; 
    private String tenNguoiNhan; 
    private String SDT; 
    private String diaChi; 
    private int trangThai;
     
    private String tenKhachHang; 
    private String maKhachHang; 
    private String sdtKhachHang; // trong bảng khách hàng 
    private Integer idGiamGia; 
    
    private String tenNhanVien;
    private String maNhanVien;
}
