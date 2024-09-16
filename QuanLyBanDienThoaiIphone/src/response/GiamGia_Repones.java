
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

public class GiamGia_Repones {
    
    private int ID_Giam_Gia;
    private String Ma_Giam_Gia;
    private String Ten_Chuong_Trinh;
    private String Mo_Ta;
    private Date Ngay_Tao;
    private Date Ngay_Bat_Dau;
    private Date Ngay_Ket_Thuc;
    private int So_luong;
    private int Kieu_Giam;
    private int Muc_Giam_Gia;
    private float Gia_tri_DH_Toi_Thieu;
    private int Trang_Thai;
    private int ID_Nhan_Vien_GG;
    
    private int ID_Nhan_Vien;
}
