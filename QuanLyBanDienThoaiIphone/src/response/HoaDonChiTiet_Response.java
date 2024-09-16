/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class HoaDonChiTiet_Response {
    private String maKhachHang;
    private int id_HDCT;
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private float giaBan;
    private String mauSac;
    private String xuatXu;
    private String Imei;
    private int id_HoaDon;
    private String maImeiDaBan;
    private String maHoaDon;
    private int trangThai;
    
    @Override
    public String toString() {
        return "HoaDonResponse{" + " maSP=" + maSanPham + ", tenSP=" + tenSanPham + ", soLuong=" +soLuong + ", giaBan=" + giaBan + ", mauSac=" + mauSac + ",trangThai="+trangThai +", xuatXu=" + xuatXu + ", maImeiDaBan=" + maImeiDaBan +  '}';
    }
}
