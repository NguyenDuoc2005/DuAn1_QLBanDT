/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

import entity.*;
import response.*;
import java.util.Date;
import java.util.Vector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ASUS
 */

@AllArgsConstructor   // contructor full tham so 
@NoArgsConstructor // contructor k tham so 
@Getter 
@Setter 
@ToString
@Builder // contructor tùy chọn tham số
public class HoaDon_Response {
    private int id_HoaDon;
    private String maHoaDon;
    private Date ngayTao;
    private Date ngayThanhToan;
    private long tongGia;
    private String hinhThucThanhToan;
    private String tenNguoiNhan;
    private String SDT;
    private String diaChi;
    private int trangThai;
    private int id_KhachHang;
    private int id_GiamGia;
    private int id_NhanVien;
    private String maNhanVien;
    private String ten;
    private String maKhachHang;
    private int id_HDCT;
    private String hoTen;
    private String maGiamGia;
    private int mucGiamGia;
    private int soLuong;
    private long giaBan;
    private String maSanPham;
    private String tenSanPham; 
    private int kieuGiamGia; 
    private int mucGiamGiaToiDa; 

    @Override
    public String toString() {
        return "HoaDonResponse{" + "id=" + id_HoaDon + ", maHD=" + maHoaDon + ", ngaytao=" + ngayTao + ", ngaythanhtoan=" + ngayThanhToan + ", tonggia=" + tongGia + ", hinhthuc=" + hinhThucThanhToan + ", ten=" + tenNguoiNhan + ", sdt=" + SDT + ", trangthai=" + trangThai + ", makhachhang=" +maKhachHang +", manhanvien=" + maNhanVien +",tennhanvien="+ten+ '}';
    }

   
}
