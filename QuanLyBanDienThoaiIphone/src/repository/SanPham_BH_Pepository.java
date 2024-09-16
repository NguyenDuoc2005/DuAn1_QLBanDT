package repository;

import config.DBConnect;
import entity.SanPham;
import form.SanPham_form;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import response.SanPham_BH_Response;

public class SanPham_BH_Pepository {

    public ArrayList<SanPham_BH_Response> getAll() {
        String sql = """
                 SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, 
                 	dbo.SanPham.So_Luong, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, dbo.MauSac.Mau_Sac, 
                     COUNT(SanPham.ID_San_Pham)as'SoLuongImei'
                 FROM         dbo.SanPham INNER JOIN
                                       dbo.MauSac ON dbo.SanPham.ID_Mau_Sac = dbo.MauSac.ID_Mau_Sac INNER JOIN
                                       dbo.Imei ON dbo.SanPham.ID_San_Pham = dbo.Imei.ID_San_Pham
                 					  where Imei.Trang_Thai = 1 and dbo.SanPham.Trang_Thai = 0
                 group by	 dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, 
                 dbo.SanPham.So_Luong, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, dbo.MauSac.Mau_Sac
                     """;
        ArrayList<SanPham_BH_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_BH_Response sp = SanPham_BH_Response.builder()
                        .idSanPham(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .tenSanPham(rs.getString(3))
                        .soLuong(rs.getInt(4))
                        .giaBan(rs.getLong(5))
                        .trangThai(rs.getInt(6))
                        .mauSac(rs.getString(7))
                        .tongImeiSP(rs.getInt(8))
                        .build();
                lists.add(sp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
    public ArrayList<SanPham_BH_Response> getAll_GiaTang() {
        String sql = """
                 SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, 
                 	dbo.SanPham.So_Luong, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, dbo.MauSac.Mau_Sac, 
                     COUNT(SanPham.ID_San_Pham)as'SoLuongImei'
                 FROM         dbo.SanPham INNER JOIN
                                       dbo.MauSac ON dbo.SanPham.ID_Mau_Sac = dbo.MauSac.ID_Mau_Sac INNER JOIN
                                       dbo.Imei ON dbo.SanPham.ID_San_Pham = dbo.Imei.ID_San_Pham
                 					  where Imei.Trang_Thai = 1 and dbo.SanPham.Trang_Thai = 0
                 group by	 dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, 
                 dbo.SanPham.So_Luong, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, dbo.MauSac.Mau_Sac
                     order by Gia_Ban asc
                     """;
        ArrayList<SanPham_BH_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_BH_Response sp = SanPham_BH_Response.builder()
                        .idSanPham(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .tenSanPham(rs.getString(3))
                        .soLuong(rs.getInt(4))
                        .giaBan(rs.getLong(5))
                        .trangThai(rs.getInt(6))
                        .mauSac(rs.getString(7))
                        .tongImeiSP(rs.getInt(8))
                        .build();
                lists.add(sp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
    // giảm giá 
    public ArrayList<SanPham_BH_Response> getAll_GiaGiam() {
        String sql = """
                 SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, 
                 	dbo.SanPham.So_Luong, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, dbo.MauSac.Mau_Sac, 
                     COUNT(SanPham.ID_San_Pham)as'SoLuongImei'
                 FROM         dbo.SanPham INNER JOIN
                                       dbo.MauSac ON dbo.SanPham.ID_Mau_Sac = dbo.MauSac.ID_Mau_Sac INNER JOIN
                                       dbo.Imei ON dbo.SanPham.ID_San_Pham = dbo.Imei.ID_San_Pham
                 					  where Imei.Trang_Thai = 1 and dbo.SanPham.Trang_Thai = 0
                 group by	 dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, 
                 dbo.SanPham.So_Luong, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, dbo.MauSac.Mau_Sac
                     order by Gia_Ban desc
                     """;
        ArrayList<SanPham_BH_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_BH_Response sp = SanPham_BH_Response.builder()
                        .idSanPham(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .tenSanPham(rs.getString(3))
                        .soLuong(rs.getInt(4))
                        .giaBan(rs.getLong(5))
                        .trangThai(rs.getInt(6))
                        .mauSac(rs.getString(7))
                        .tongImeiSP(rs.getInt(8))
                        .build();
                lists.add(sp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
    public ArrayList<SanPham_BH_Response> chonImei_TheoMaSP(String maSP) {
        String sql = """
                 SELECT    dbo.Imei.ID_Imei, dbo.Imei.Ma_Imei, 
                                      dbo.Imei.Trang_Thai, dbo.SanPham.Ma_San_Pham,
                                      dbo.SanPham.Ten_San_Pham, dbo.SanPham.ID_San_Pham,dbo.SanPham.Gia_Ban
                                  FROM         dbo.Imei INNER JOIN dbo.SanPham ON 
                                      dbo.Imei.ID_San_Pham = dbo.SanPham.ID_San_Pham
                                  where SanPham.Ma_San_Pham = ? AND Imei.Trang_Thai = 1
                     """;
        ArrayList<SanPham_BH_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSP);
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

    // lấy số lượng sản phẩm theo mã sản phẩm 
    public ArrayList<SanPham_BH_Response> laySLg_SP_TheoMaSP(String maSP) {
        String sql = """
                 select Ma_San_Pham , So_Luong from SanPham
                 where Ma_San_Pham = ?
                     """;
        ArrayList<SanPham_BH_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_BH_Response sp = SanPham_BH_Response.builder()
                        .maSanPham(rs.getString(1))
                        .soLuong(rs.getInt(2))
                        .build();
                lists.add(sp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    //  xóa imei đã bán theo mã sp và id hd 
    public ArrayList<SanPham_BH_Response> ChonImeiDB_TheoIDSP_IDHD(String maSP_HDCT, int idHoaDon_HDCT) {
        String sql = """
                        SELECT dbo.HoaDonChiTiet.ID_HDCT, dbo.ImeiDaBan.ID_Imei_Da_Ban, dbo.ImeiDaBan.Ma_Imei_Da_Ban,
                        dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham , HoaDonChiTiet.Gia,HoaDonChiTiet.ID_San_Pham
                        ,HoaDonChiTiet.ID_Hoa_Don
                        FROM         dbo.HoaDonChiTiet INNER JOIN
                                              dbo.ImeiDaBan ON dbo.HoaDonChiTiet.ID_HDCT = dbo.ImeiDaBan.ID_HDCT INNER JOIN
                                              dbo.SanPham ON dbo.HoaDonChiTiet.ID_San_Pham = dbo.SanPham.ID_San_Pham
                        where SanPham.Ma_San_Pham =? and HoaDonChiTiet.ID_Hoa_Don = ?
                     """;

        ArrayList<SanPham_BH_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSP_HDCT);
            ps.setInt(2, idHoaDon_HDCT);
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
    //  xóa imei đã bán theo mã SP
    public ArrayList<SanPham_BH_Response> Lay_Imei_DB_HuyHD_Theo_MaSP_MaHD(String maSP_HDCT, String maHD_HD) {
        String sql = """
                       SELECT    ImeiDaBan.Ma_Imei_Da_Ban , dbo.HoaDonChiTiet.ID_HDCT, dbo.ImeiDaBan.ID_Imei_Da_Ban
                        FROM         dbo.HoaDonChiTiet INNER JOIN
                                              dbo.HoaDon ON dbo.HoaDonChiTiet.ID_Hoa_Don = dbo.HoaDon.ID_Hoa_Don INNER JOIN
                                              dbo.SanPham ON dbo.HoaDonChiTiet.ID_San_Pham = dbo.SanPham.ID_San_Pham INNER JOIN
                                              dbo.ImeiDaBan ON dbo.HoaDonChiTiet.ID_HDCT = dbo.ImeiDaBan.ID_HDCT	
                        					  where SanPham.Ma_San_Pham =? and HoaDon.Ma_Hoa_Don = ?
                     """;

        ArrayList<SanPham_BH_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSP_HDCT);
            ps.setString(2, maHD_HD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_BH_Response sp = SanPham_BH_Response.builder()
                        .maImeiDaBan(rs.getString(1))
                        .idHoaDonChiTiet(rs.getInt(2))
                        .idImeiDaBan(rs.getInt(3))
                        .build();
                lists.add(sp);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
    
   
    // tìm kiếm sản phẩm 
    public ArrayList<SanPham_BH_Response> search_SanPham(String keyword) {
        String sql = """
SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, 
                                    	dbo.SanPham.So_Luong, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, dbo.MauSac.Mau_Sac,
                                        COUNT(SanPham.ID_San_Pham)as'SoLuongImei'
                                    FROM         dbo.SanPham INNER JOIN
                                                          dbo.MauSac ON dbo.SanPham.ID_Mau_Sac = dbo.MauSac.ID_Mau_Sac INNER JOIN
                                                          dbo.Imei ON dbo.SanPham.ID_San_Pham = dbo.Imei.ID_San_Pham   
                                    					  where Imei.Trang_Thai = 1 and dbo.SanPham.Trang_Thai = 0
                   									 
                     """;
        if (keyword.length() > 0) {
            sql += """
                 and
               (
                    SanPham.Ma_San_Pham like ?
                    or SanPham.Ten_San_Pham like ?
                    or MauSac.Mau_Sac like ?
                                   									
                 )
             group by	 dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, 
             dbo.SanPham.So_Luong, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, dbo.MauSac.Mau_Sac
                 """;
        }
        
        
        ArrayList<SanPham_BH_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            int index = 1; // vị trí của dấu hỏi chấm đầu tiên
            String value = "%" + keyword + "%";
            if (keyword.length() > 0) {
                // search 1 ô trong nhiều dòng 
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
               
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_BH_Response sp = SanPham_BH_Response.builder()
                        .idSanPham(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .tenSanPham(rs.getString(3))
                        .soLuong(rs.getInt(4))
                        .giaBan(rs.getLong(5))
                        .trangThai(rs.getInt(6))
                        .mauSac(rs.getString(7))
                        .tongImeiSP(rs.getInt(8))
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
