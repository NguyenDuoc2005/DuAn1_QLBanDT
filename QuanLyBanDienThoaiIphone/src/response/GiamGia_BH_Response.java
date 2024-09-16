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
public class GiamGia_BH_Response {
    private int idGiamGia; 
    private String maGiamGia; 
    private String tenChuongTrinh;
    private String moTa; 
    private Date ngayTao; 
    private Date ngayBatDau; 
    private Date ngayKetThuc; 
    private int soLuong; 
    private int kieuGiam;
    private long giaTriDonHangToiThieu; 
    private long mucGiamGia; 
    private long mucGiamGiaToiDa;
    private int trangThai;
    private int idNhanVien;
}
