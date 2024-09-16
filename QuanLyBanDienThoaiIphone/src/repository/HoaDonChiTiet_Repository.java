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

/**
 *
 * @author ASUS
 */
public class HoaDonChiTiet_Repository {
    
    public ArrayList<HoaDonChiTiet_Response> getChiTietByMaHD(String maHD) {
        ArrayList<HoaDonChiTiet_Response> listHDCT = new ArrayList<>();
        String sql = """
               		 SELECT dbo.HoaDon.ID_Hoa_Don, dbo.HoaDon.Ma_Hoa_Don, dbo.HoaDonChiTiet.So_Luong,
               		 dbo.HoaDonChiTiet.Gia, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham
               FROM  dbo.HoaDon INNER JOIN
                        dbo.HoaDonChiTiet ON dbo.HoaDon.ID_Hoa_Don = dbo.HoaDonChiTiet.ID_Hoa_Don INNER JOIN
                        dbo.SanPham ON dbo.HoaDonChiTiet.ID_San_Pham = dbo.SanPham.ID_San_Pham
               		 where HoaDon.Ma_Hoa_Don=? and HoaDonChiTiet.So_Luong>0
               group by dbo.HoaDon.ID_Hoa_Don, dbo.HoaDon.Ma_Hoa_Don, dbo.HoaDonChiTiet.So_Luong, 
               dbo.HoaDonChiTiet.Gia, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham
               """;
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet_Response hdct = HoaDonChiTiet_Response.builder()
                        .maHoaDon(maHD)
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
 
 
}

