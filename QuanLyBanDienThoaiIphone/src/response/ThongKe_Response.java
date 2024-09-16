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
public class ThongKe_Response {
        private int soHoaDon;
        private int tongSoKhachHang;
        private int tongDoanhThu;
        private int tongSanPham;
        private String maSanPham;
        private String tenSanPham;
        private int soLuongSanPham;
        private float giaNhap;
        private float giaBan;
        private int doanhthusanpham;
        private String sanPhamBan;
        private int soLuong;
        private int tongGiaBan;
        private int tongGiaGiam;
        private int tongDoanhThu2;
        private Date ngayThanhToan;
        private Date startDate;
        private Date endDate;
        private int soHoaDonDaDuocThanhToan;
        private int soHoaDonDaDuocThanhToanHomNay;
        private int tongDoanhThuHomNay;
        private int tongDoanhThuDate;
        private int tongHoaDonDate;
        private int tongHoaDonHuy;
        private int sanPhamTheoThangNam;
}
