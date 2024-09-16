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
public class SanPham_Response {
    private int idSanPham; 
    private String maSanPham; 
    private String tenSanPham;
    private String moTa; 
    private Date ngayTao; 
    private int soLuong; // trong bảng sp 
    private float giaNhap;
    private float giaBan; 
    private String hinhAnh; 
    private int trangThai;
    // thuộc tính sản phẩm
    private int idRom;
    private String rom;
    private int idMauSac; 
    private String mauSac; 
    private int idRam; 
    private String ram;
    private int idKichThuoc; 
    private String kichThuoc; 
    private int idPhanLoai; 
    private String phanLoai;
    private int idDungLuongPin;
    private String dungLuongPin;
    private int idXuatXu; 
    private String xuatXu; 
    private int idCPU; 
    private String CPU;
    
    // tong Imei 
    private int tongImei; // count 
    private String imei; 
    private int idImei ;
    private int trangThaiImei;
}
