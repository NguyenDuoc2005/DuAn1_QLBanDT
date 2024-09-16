/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
/**
 *
 * @author acer
 */
public class ThongKe {
    private int soHoaDon;
        private int tongSoKhachHang;
        private int tongDoanhThu;
        private String tongSanPham; 
}
