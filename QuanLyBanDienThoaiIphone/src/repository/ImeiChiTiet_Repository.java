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

import response.ImeiChiTiet_Response;

/**
 *
 * @author ASUS
 */

   public class ImeiChiTiet_Repository {
    public  ArrayList<ImeiChiTiet_Response>getImeiByMaSP(String maSP,String maHD) {
        ArrayList<ImeiChiTiet_Response> imeiList = new ArrayList<>();
        String sql = """
                SELECT dbo.ImeiDaBan.Ma_Imei_Da_Ban
                FROM  dbo.HoaDon INNER JOIN
                         dbo.HoaDonChiTiet ON dbo.HoaDon.ID_Hoa_Don = dbo.HoaDonChiTiet.ID_Hoa_Don INNER JOIN
                         dbo.ImeiDaBan ON dbo.HoaDonChiTiet.ID_HDCT = dbo.ImeiDaBan.ID_HDCT INNER JOIN
                         dbo.SanPham ON dbo.HoaDonChiTiet.ID_San_Pham = dbo.SanPham.ID_San_Pham where SanPham.Ma_San_Pham=? AND HoaDon.Ma_Hoa_Don=?
                """;

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSP);
            ps.setString(2,maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ImeiChiTiet_Response imei = ImeiChiTiet_Response.builder()
                    .maImeiDaBan(rs.getString("Ma_Imei_Da_Ban"))
                    .build();
                imeiList.add(imei);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imeiList; 
    }
    
       public static void main(String[] args) {
           ImeiChiTiet_Repository imei = new ImeiChiTiet_Repository(); 
           System.out.println(imei.getImeiByMaSP("SP0001","HD0001"));
       }
}



