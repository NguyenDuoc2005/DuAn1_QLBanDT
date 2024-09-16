package response;

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
public class HoaDonChiTiet_BH_Response {
    private int idHoaDonChiTiet; 
    private int soLuong ; 
    private long donGia;
    private int trangThaiHDCT; 
    private long thanhTien; 
    // lấy tổng số lượng imei theo Count()
    private int soLuongImei; 
    // sản phẩm 
    private int idSanPham; 
    private String maSanPham;
    private String tenSanPham;
    // hóa đơn
    private int idHoaDon;
    private String maHD;
    private int trangThaiHD; 
    // imei đã bán 
    private int idImeiDaBan; 
    private String maImeiDaBan; 
    
    
}
