/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

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
public class HoaDon {
    private int id_HoaDon;
    private String maHoaDon;
    private Date ngayTao;
    private Date ngayThanhToan;
    private float tongGia;
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
    private String taiKhoan;
    private String matKhau;
    private String hoten;
    private String gioiTinh;
    private Date ngaySinh;
    private String hinhAnh;
    private String email;
    private int id_ChucVu;
    private String maKhachHang;
    private int id_HDCT;
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private float giaBan;
    private String mauSac;
    private String xuatXu;
    private String Imei;
    

    public Vector<?> toDataRow() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
