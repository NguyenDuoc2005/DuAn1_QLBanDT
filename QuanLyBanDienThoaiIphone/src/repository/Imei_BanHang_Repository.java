package repository;

import config.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import response.SanPham_BH_Response;

public class Imei_BanHang_Repository {
    public ArrayList<SanPham_BH_Response> Tim_TheoImei_BH(String maSP,String maImei) {
        String sql = """
                 SELECT    dbo.Imei.ID_Imei, dbo.Imei.Ma_Imei, 
                                      dbo.Imei.Trang_Thai, dbo.SanPham.Ma_San_Pham,
                                      dbo.SanPham.Ten_San_Pham, dbo.SanPham.ID_San_Pham,dbo.SanPham.Gia_Ban
                                  FROM         dbo.Imei INNER JOIN dbo.SanPham ON 
                                      dbo.Imei.ID_San_Pham = dbo.SanPham.ID_San_Pham
                                  where SanPham.Ma_San_Pham = ? AND Imei.Trang_Thai = 1
                                  and Imei.Ma_Imei = ?
                     """;

        ArrayList<SanPham_BH_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSP);
            ps.setString(2, maImei);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_BH_Response sp = SanPham_BH_Response.builder()
                        .idImei(rs.getInt(1))
                        .MaImei(rs.getString(2))
                        .trangThai(rs.getInt(3))
                        .maSanPham(rs.getString(4))
                        .tenSanPham(rs.getString(5))
                        .idSanPham(rs.getInt(6))
                        .giaBan(rs.getLong(7))
                        .build();
                lists.add(sp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
    
    // tìm theo imei đã bán 
    public ArrayList<SanPham_BH_Response> ChonImeiDB_TheoIDSP_IDHD(String maSP_HDCT, int idHoaDon_HDCT,String maImei) {
        String sql = """
                        SELECT    dbo.HoaDonChiTiet.ID_HDCT, dbo.ImeiDaBan.ID_Imei_Da_Ban, dbo.ImeiDaBan.Ma_Imei_Da_Ban,
                        dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham , HoaDonChiTiet.Gia,HoaDonChiTiet.ID_San_Pham
                        ,HoaDonChiTiet.ID_Hoa_Don
                        FROM         dbo.HoaDonChiTiet INNER JOIN
                                              dbo.ImeiDaBan ON dbo.HoaDonChiTiet.ID_HDCT = dbo.ImeiDaBan.ID_HDCT INNER JOIN
                                              dbo.SanPham ON dbo.HoaDonChiTiet.ID_San_Pham = dbo.SanPham.ID_San_Pham
                        where SanPham.Ma_San_Pham =? and HoaDonChiTiet.ID_Hoa_Don = ? and ImeiDaBan.Ma_Imei_Da_Ban = ?
                     """;

        ArrayList<SanPham_BH_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSP_HDCT);
            ps.setInt(2, idHoaDon_HDCT);
            ps.setString(3, maImei);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_BH_Response sp = SanPham_BH_Response.builder()
                        .idHoaDonChiTiet(rs.getInt(1))
                        .idImeiDaBan(rs.getInt(2))
                        .maImeiDaBan(rs.getString(3))
                        .maSanPham(rs.getString(4))
                        .tenSanPham(rs.getString(5))
                        .giaBan(rs.getLong(6))
                        .idSanPham(rs.getInt(7))
                        .idHoaDon(rs.getInt(8))
                        .build();
                lists.add(sp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
}
