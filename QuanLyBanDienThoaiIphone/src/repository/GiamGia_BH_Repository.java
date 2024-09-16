package repository;

import config.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import response.GiamGia_BH_Response;

public class GiamGia_BH_Repository {
    // lấy ra các mã giảm  giá phù hợp với hóa đơn 
    public ArrayList<GiamGia_BH_Response> get_GiamGia_HomNay(long giaTriDH_ToiThieu) {
        ArrayList<GiamGia_BH_Response> list = new ArrayList<>();
        list.clear();
        String query = """
                         SELECT [ID_Giam_Gia]
                                                   ,[Ma_Giam_Gia],[Ten_Chuong_Trinh],[Mo_Ta]
                                                   ,[Ngay_Tao],[Ngay_Bat_Dau],[Ngay_Ket_Thuc]
                                                   ,[So_luong],[Kieu_Giam],[Gia_tri_DH_Toi_Thieu]
                                                   ,[Muc_Giam_Gia],[Muc_Giam_Gia_Toi_Da],[Trang_Thai],
                                                    [ID_Nhan_Vien]
                                                    FROM [dbo].[GiamGia]    
                                               where Ngay_Bat_Dau <= CAST(GETDATE() AS DATE) and Ngay_Ket_Thuc >=CAST(GETDATE() AS DATE)
                                                and Gia_tri_DH_Toi_Thieu <= ? and Trang_Thai = 0
                                                order by  Muc_Giam_Gia_Toi_Da desc ,Muc_Giam_Gia desc 
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1,giaTriDH_ToiThieu);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiamGia_BH_Response giamGia = GiamGia_BH_Response.builder()
                        .idGiamGia(rs.getInt(1))
                        .maGiamGia(rs.getString(2))
                        .tenChuongTrinh(rs.getString(3))
                        .moTa(rs.getString(4))
                        .ngayTao(rs.getDate(5))
                        .ngayBatDau(rs.getDate(6))
                        .ngayKetThuc(rs.getDate(7))
                        .soLuong(rs.getInt(8))
                        .kieuGiam(rs.getInt(9))
                        .giaTriDonHangToiThieu(rs.getInt(10))
                        .mucGiamGia(rs.getInt(11))
                        .mucGiamGiaToiDa(rs.getInt(12))
                        .trangThai(rs.getInt(13))
                        .idNhanVien(rs.getInt(14))
                        .build();
                list.add(giamGia);
            }
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    // lấy ra các mã giảm  giá theo mã giảm giá ở cbo lựa chọn 
    public ArrayList<GiamGia_BH_Response> get_GiamGia_TheoMaGG(String maGG) {
        ArrayList<GiamGia_BH_Response> list = new ArrayList<>();
        list.clear();
        String query = """
                         SELECT [ID_Giam_Gia]
                         ,[Ma_Giam_Gia],[Ten_Chuong_Trinh],[Mo_Ta]
                         ,[Ngay_Tao],[Ngay_Bat_Dau],[Ngay_Ket_Thuc]
                         ,[So_luong],[Kieu_Giam],[Gia_tri_DH_Toi_Thieu]
                         ,[Muc_Giam_Gia],[Muc_Giam_Gia_Toi_Da],[Trang_Thai],
                         [ID_Nhan_Vien]
                          FROM [dbo].[GiamGia]    
                          where Ma_Giam_Gia = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1,maGG);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiamGia_BH_Response giamGia = GiamGia_BH_Response.builder()
                        .idGiamGia(rs.getInt(1))
                        .maGiamGia(rs.getString(2))
                        .tenChuongTrinh(rs.getString(3))
                        .moTa(rs.getString(4))
                        .ngayTao(rs.getDate(5))
                        .ngayBatDau(rs.getDate(6))
                        .ngayKetThuc(rs.getDate(7))
                        .soLuong(rs.getInt(8))
                        .kieuGiam(rs.getInt(9))
                        .giaTriDonHangToiThieu(rs.getInt(10))
                        .mucGiamGia(rs.getInt(11))
                        .mucGiamGiaToiDa(rs.getInt(12))
                        .trangThai(rs.getInt(13))
                        .idNhanVien(rs.getInt(14))
                        .build();
                list.add(giamGia);
            }
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    
}
