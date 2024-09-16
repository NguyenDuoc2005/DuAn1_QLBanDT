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
public class SanPham {
    private int idSanPham;
    private String maSanPham;
    private String moTa; 
    private String tenSanPham;
    private Date ngayTao; 
    private int soLuong;
    private float giaNhap;
    private float giaBan;
    private String hinhAnh;
    private int idRom;
    private int idMauSac; 
    private int idRam;
    private int idkichThuoc; 
    private int idDungLuongPin;
    private int id_CPU; 
    private int id_XuatXu;
    private int id_PhanLoai;
    private int trangThai;
}
