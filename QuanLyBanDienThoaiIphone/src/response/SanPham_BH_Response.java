package response;
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
public class SanPham_BH_Response {
    private int idSanPham; 
    private String maSanPham; 
    private String tenSanPham; 
    private int soLuong;
    private long giaBan; 
    private int idMauSac; 
    private String mauSac; 
    private int trangThai;  
    private int tongImeiSP;
    // bảng imei 
    private int idImei;
    private String MaImei;
    private int trangThaiImei;
    // imei đã bán và hdct 
    private int idHoaDonChiTiet; 
    private int idImeiDaBan; 
    private String maImeiDaBan;
    // hóa đơn 
    private int idHoaDon ; 
    
    
}
