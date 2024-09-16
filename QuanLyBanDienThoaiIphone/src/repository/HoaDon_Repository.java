/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import response.HoaDonChiTiet_Response;
import response.HoaDon_Response;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class HoaDon_Repository {

    public ArrayList<HoaDon_Response> getAll() {
        ArrayList<HoaDon_Response> listHD = new ArrayList<>();
        String sql = """
                    SELECT    dbo.HoaDon.ID_Hoa_Don, dbo.HoaDon.Ma_Hoa_Don, dbo.HoaDon.Ngay_Tao, dbo.HoaDon.Ngay_Thanh_Toan, dbo.HoaDon.Tong_Gia, dbo.HoaDon.Trang_Thai, 
                                      dbo.KhachHang.Ma_Khach_Hang, dbo.NhanVien.Ma_NV, dbo.NhanVien.Ho_Ten, dbo.GiamGia.Ma_Giam_Gia, dbo.GiamGia.Muc_Giam_Gia, dbo.HoaDon.SĐT, dbo.HoaDon.Ten_Nguoi_Nhan
                                                                   , GiamGia.Kieu_Giam, GiamGia.Muc_Giam_Gia_Toi_Da
                                      FROM         dbo.HoaDon left JOIN
                                                            dbo.GiamGia ON dbo.HoaDon.ID_Giam_Gia = dbo.GiamGia.ID_Giam_Gia left JOIN
                                                            dbo.KhachHang ON dbo.HoaDon.ID_Khach_Hang = dbo.KhachHang.ID_Khach_Hang left JOIN
                                                            dbo.NhanVien ON dbo.HoaDon.ID_Nhan_Vien = dbo.NhanVien.ID_Nhan_Vien
                                      					  order by dbo.HoaDon.Ngay_Thanh_Toan desc
                """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon_Response hd = HoaDon_Response.builder()
                        .maHoaDon(rs.getString("Ma_Hoa_Don"))
                        .ngayTao(rs.getDate("Ngay_Tao"))
                        .ngayThanhToan(rs.getDate("Ngay_Thanh_Toan"))
                        .tongGia(rs.getLong("Tong_Gia"))
                        .trangThai(rs.getInt("Trang_Thai"))
                        .maKhachHang(rs.getString("Ma_Khach_Hang"))
                        .maNhanVien(rs.getString("Ma_NV"))
                        .hoTen(rs.getString("Ho_Ten"))
                        .maGiamGia(rs.getString("Ma_Giam_Gia"))
                        .mucGiamGia(rs.getInt("Muc_Giam_Gia"))
                        .tenNguoiNhan(rs.getString("Ten_Nguoi_Nhan"))
                        .SDT(rs.getString("SĐT"))
                        .kieuGiamGia(rs.getInt("Kieu_Giam"))
                        .mucGiamGiaToiDa(rs.getInt("Muc_Giam_Gia_Toi_Da"))
                        .build();
                listHD.add(hd);
            }
            return listHD;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HoaDonChiTiet_Response> getAllChiTiet() {
        ArrayList<HoaDonChiTiet_Response> listHDCT = new ArrayList<>();
        String sql = """
               SELECT dbo.HoaDon.ID_Hoa_Don, dbo.HoaDon.Ma_Hoa_Don, dbo.HoaDonChiTiet.So_Luong,
               		 dbo.HoaDonChiTiet.Gia, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham
               FROM  dbo.HoaDon INNER JOIN
                        dbo.HoaDonChiTiet ON dbo.HoaDon.ID_Hoa_Don = dbo.HoaDonChiTiet.ID_Hoa_Don INNER JOIN
                        dbo.SanPham ON dbo.HoaDonChiTiet.ID_San_Pham = dbo.SanPham.ID_San_Pham
               		 where HoaDon.Ma_Hoa_Don= 'HD0001'
               group by dbo.HoaDon.ID_Hoa_Don, dbo.HoaDon.Ma_Hoa_Don, dbo.HoaDonChiTiet.So_Luong, 
               dbo.HoaDonChiTiet.Gia, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham
               		
                """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet_Response hdct = HoaDonChiTiet_Response.builder()
                        .maHoaDon(rs.getString("Ma_Hoa_Don"))
                        .soLuong(rs.getInt("So_Luong"))
                        .giaBan(rs.getFloat("Gia"))
                        .maSanPham(rs.getString("Ma_San_Pham"))
                        .tenSanPham(rs.getString("Ten_San_Pham"))
                        .build();
                listHDCT.add(hdct);
            }
            return listHDCT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HoaDon_Response> search(String keyword) throws SQLException {
        String sql = "SELECT dbo.HoaDon.ID_Hoa_Don, dbo.HoaDon.Ma_Hoa_Don, dbo.HoaDon.Ngay_Tao, dbo.HoaDon.Tong_Gia, dbo.HoaDon.Trang_Thai, dbo.KhachHang.Ma_Khach_Hang, dbo.NhanVien.Ma_NV, dbo.NhanVien.Ho_Ten, dbo.GiamGia.Ma_Giam_Gia, dbo.GiamGia.Muc_Giam_Gia, dbo.HoaDon.SĐT, dbo.HoaDon.Ten_Nguoi_Nhan\n"
                + "FROM  dbo.HoaDon LEFT JOIN\n"
                + "         dbo.KhachHang ON dbo.HoaDon.ID_Khach_Hang = dbo.KhachHang.ID_Khach_Hang LEFT JOIN\n"
                + "         dbo.NhanVien ON dbo.HoaDon.ID_Nhan_Vien = dbo.NhanVien.ID_Nhan_Vien LEFT JOIN\n"
                + "         dbo.GiamGia ON dbo.HoaDon.ID_Giam_Gia = dbo.GiamGia.ID_Giam_Gia AND dbo.NhanVien.ID_Nhan_Vien = dbo.GiamGia.ID_Nhan_Vien ";
        if (keyword != null && keyword.isEmpty()) {
            sql += """
                    WHERE hd.Ma_Hoa_Don LIKE ? OR kh.Ma_Khach_Hang LIKE ? OR nv.Ma_NV LIKE ? OR gg.Ma_Giam_Gia LIKE ? ? 
                      """;

        }

        ArrayList<HoaDon_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            if (keyword != null && !keyword.isEmpty()) {
                String value = "%" + keyword + "%";
                ps.setString(1, value);
                ps.setString(2, value);
                ps.setString(3, value);
                ps.setString(4, value);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon_Response kh = HoaDon_Response.builder()
                        .maHoaDon(rs.getString("Ma_Hoa_Don"))
                        .ngayTao(rs.getDate("Ngay_Tao"))
                        .ngayThanhToan(rs.getDate("Ngay_Thanh_Toan"))
                        .tongGia(rs.getLong("Tong_Gia"))
                        .trangThai(rs.getInt("Trang_Thai"))
                        .maKhachHang(rs.getString("Ma_Khach_Hang"))
                        .maNhanVien(rs.getString("Ma_NV"))
                        .hoTen(rs.getString("Ho_Ten"))
                        .maGiamGia(rs.getString("Ma_Giam_Gia"))
                        .mucGiamGia(rs.getInt("Muc_Giam_Gia"))
                        .tenNguoiNhan(rs.getString("Ten"))
                        .SDT(rs.getString("SĐT"))
                        .build();
                lists.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;

    }
    
}
