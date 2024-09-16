package repository;

import config.DBConnect;
import entity.ImeiDaBan;
import entity.MauSac;
import entity.Ram;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import response.HoaDonChiTiet_BH_Response;
import response.HoaDon_BH_Response;

public class HoaDonChiTiet_BH_Repository {

    // lấy hóa đơn chi tiết theo mã háo đơn khi click 
    public ArrayList<HoaDonChiTiet_BH_Response> getHDCT_TheoMaHD(String maHD) {
        String sql = """
                     SELECT    dbo.HoaDon.Ma_Hoa_Don, dbo.HoaDonChiTiet.ID_HDCT, dbo.SanPham.ID_San_Pham, 
                     dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.HoaDonChiTiet.So_Luong, 
                     dbo.HoaDonChiTiet.Gia ,(HoaDonChiTiet.So_Luong*HoaDonChiTiet.Gia) as 'TongTien', 
                     count(dbo.SanPham.ID_San_Pham) as 'Số lượng Imei'
                     FROM         dbo.HoaDon INNER JOIN
                                           dbo.HoaDonChiTiet ON dbo.HoaDon.ID_Hoa_Don = dbo.HoaDonChiTiet.ID_Hoa_Don INNER JOIN
                                           dbo.ImeiDaBan ON dbo.HoaDonChiTiet.ID_HDCT = dbo.ImeiDaBan.ID_HDCT INNER JOIN
                                           dbo.SanPham ON dbo.HoaDonChiTiet.ID_San_Pham = dbo.SanPham.ID_San_Pham
                     where HoaDon.Trang_Thai = 0 and HoaDon.Ma_Hoa_Don = ?
                     GROUP BY dbo.HoaDon.Ma_Hoa_Don, dbo.HoaDonChiTiet.ID_HDCT, dbo.SanPham.ID_San_Pham, 
                     dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.HoaDonChiTiet.So_Luong, 
                     dbo.HoaDonChiTiet.Gia ,(HoaDonChiTiet.So_Luong*HoaDonChiTiet.Gia) 
                     order by dbo.HoaDonChiTiet.ID_HDCT desc
                     """;
        ArrayList<HoaDonChiTiet_BH_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet_BH_Response response = HoaDonChiTiet_BH_Response.builder()
                        .maHD(rs.getString(1))
                        .idHoaDonChiTiet(rs.getInt(2))
                        .idSanPham(rs.getInt(3))
                        .maSanPham(rs.getString(4))
                        .tenSanPham(rs.getString(5))
                        .soLuong(rs.getInt(6))
                        .donGia(rs.getLong(7))
                        .thanhTien(rs.getLong(8))
                        .soLuongImei(rs.getInt(9))
                        .build();
                lists.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    // lấy ra hóa đơm chi tiết theo mã hóa đơn nhưng k group với imei 
    public ArrayList<HoaDonChiTiet_BH_Response> getID_HDCT_TheoMaHD(String maHD) {
        String sql = """
                  SELECT    dbo.HoaDonChiTiet.ID_HDCT,  dbo.HoaDonChiTiet.ID_San_Pham,HoaDonChiTiet.So_Luong,HoaDonChiTiet.Gia
                   FROM         dbo.HoaDon INNER JOIN
                                         dbo.HoaDonChiTiet ON dbo.HoaDon.ID_Hoa_Don = dbo.HoaDonChiTiet.ID_Hoa_Don
                   where HoaDon.Ma_Hoa_Don =?
                     """;
        ArrayList<HoaDonChiTiet_BH_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet_BH_Response response = HoaDonChiTiet_BH_Response.builder()
                        .idHoaDonChiTiet(rs.getInt(1))
                        .idSanPham(rs.getInt(2))
                        .soLuong(rs.getInt(3))
                        .donGia(rs.getLong(4))
                        .build();
                lists.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    // thêm vào hdct 
    public boolean Them_Vao_HDCT(HoaDonChiTiet_BH_Response hdct) {
        int check = 0;
        ArrayList<HoaDonChiTiet_BH_Response> list = new ArrayList<>();
        String query = """
                       INSERT INTO [dbo].[HoaDonChiTiet]
                       ([So_Luong],[Gia],[Trang_Thai]
                       ,[ID_Hoa_Don],[ID_San_Pham])
                            VALUES
                                  (?,?,0,?,?)
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, hdct.getSoLuong());
            ps.setLong(2, hdct.getDonGia());
            ps.setInt(3, hdct.getIdHoaDon());
            ps.setInt(4, hdct.getIdSanPham());
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    // Lấy ra id hdct vừa tạo 
    public ArrayList<HoaDonChiTiet_BH_Response> lay_HDCT_VuaTao_() {
        ArrayList<HoaDonChiTiet_BH_Response> list = new ArrayList<>();
        list.clear();
        String query = """
                           select top 1 ID_HDCT, So_Luong,Gia,Trang_Thai,ID_Hoa_Don,ID_San_Pham from HoaDonChiTiet 
                                      order by ID_HDCT desc           
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet_BH_Response hdct = HoaDonChiTiet_BH_Response.builder()
                        .idHoaDonChiTiet(rs.getInt(1))
                        .soLuong(rs.getInt(2))
                        .donGia(rs.getLong(3))
                        .trangThaiHD(rs.getInt(4))
                        .idHoaDon(rs.getInt(5))
                        .idSanPham(rs.getInt(6))
                        .build();
                list.add(hdct);
            }
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    // thêm vào bảng imei đã bán
    public boolean Them_Vao_Imei_DaBan(String maImeiDB, int id_HDCT) {
        int check = 0;
        String query = """
                     INSERT INTO [dbo].[ImeiDaBan]
                                ([Ma_Imei_Da_Ban]
                                ,[Trang_Thai]
                                ,[ID_HDCT])
                          VALUES
                                (?,0,?)
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maImeiDB);
            ps.setInt(2, id_HDCT);
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    // cập nhật lại số lượng sp 
    public boolean capNhat_SLg_SP_KhiThem_TheoMaSP(int Soluong, String maSP) {
        int check = 0;
        String query = """
                     UPDATE [dbo].[SanPham]
                        SET [So_Luong] = ?
                      WHERE Ma_San_Pham = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, Soluong);
            ps.setString(2, maSP);
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    // cập nhật lại trạng thái của bảng imei thành trạng thái đã bán 
    public boolean capNhat_TrangThaiImei_ThanhDaBan(String maImei) {
        int check = 0;
        String query = """
                        UPDATE [dbo].[Imei]
                            SET [Trang_Thai] = 2

                          WHERE Ma_Imei = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maImei);
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    // cập nhật lại trạng thái của bảng imei thành trạng thái chưa bán

    public boolean capNhat_TrangThaiImei_ThanhChuaban(String maImei) {
        int check = 0;
        String query = """
                        UPDATE [dbo].[Imei]
                            SET [Trang_Thai] = 1

                          WHERE Ma_Imei = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maImei);
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    // Xóa Imei Ở Bảng imei đã bán 
    // và cả dùng cho b4 của huy hóa đơn 
    public boolean Xoa_Imei_DaBan(String maImei) {
        int check = 0;
        String query = """
                        DELETE FROM [dbo].[ImeiDaBan]
                        WHERE Ma_Imei_Da_Ban = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maImei);
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    // cập nhật lại tổng tiền ở hóa đơn khi thêm sản phẩm 

    public boolean updateTongTien(long tongtien, String maHD) {
        int check = 0;
        String sql = """
                    update HoaDon
                    set Tong_Gia = ?
                    where Ma_Hoa_Don = ?
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Object la cha cua cac loai kieu du lieu 
            ps.setObject(1, tongtien);
            ps.setObject(2, maHD);

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }

    // cập nhật lại số lượng vào bảng hóa đơn chi tiết khi có sản phẩm đó ở bảng hdct r check trùng tăng số lượng lên
    public boolean updateSoLuong_HDCT_CheckTrung(int soluong, int idHDCT) {
        int check = 0;
        String sql = """
                    update HoaDonChiTiet
                    set So_Luong = ?
                    where ID_HDCT = ?
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Object la cha cua cac loai kieu du lieu 
            ps.setObject(1, soluong);
            ps.setObject(2, idHDCT);

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }

    // lấy hóa đơn chi tiết theo IDHD 
    public ArrayList<HoaDonChiTiet_BH_Response> getHDCT_TheoIDHD(int idHD) {
        String sql = """
                     select* from HoaDonChiTiet where ID_Hoa_Don = ?
                     """;
        ArrayList<HoaDonChiTiet_BH_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet_BH_Response response = HoaDonChiTiet_BH_Response.builder()
                        .idHoaDonChiTiet(rs.getInt(1))
                        .soLuong(rs.getInt(2))
                        .donGia(rs.getLong(3))
                        .trangThaiHD(rs.getInt(4))
                        .idHoaDon(rs.getInt(5))
                        .idSanPham(rs.getInt(6))
                        .build();
                lists.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    // lấy hóa đơn chi tiết được click theo mã hóa đơn và mã sản phẩm 
    public ArrayList<HoaDonChiTiet_BH_Response> getHDCT_Click_TheoMaHD_MaSP(String maHD, String maSP) {
        String sql = """
                     SELECT  HoaDonChiTiet.id_HoaDon , HoaDonChiTiet.id_SanPham,SanPham.TenSanPham, HoaDonChiTiet.SoLuong
                     , HoaDonChiTiet.id_HDCT
                     FROM  dbo.HoaDon INNER JOIN
                                           dbo.HoaDonChiTiet ON dbo.HoaDon.id_HoaDon = dbo.HoaDonChiTiet.id_HoaDon INNER JOIN
                                           dbo.SanPham ON dbo.HoaDonChiTiet.id_SanPham = dbo.SanPham.id_SanPham
                     where SanPham.MaSanPham = ? and HoaDon.MaHoaDon = ?
                     """;
        ArrayList<HoaDonChiTiet_BH_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maSP);
            ps.setObject(2, maHD);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet_BH_Response response = HoaDonChiTiet_BH_Response.builder()
                        .idHoaDon(rs.getInt(1))
                        .idSanPham(rs.getInt(2))
                        .tenSanPham(rs.getString(3))
                        .soLuong(rs.getInt(4))
                        .idHoaDonChiTiet(rs.getInt(5))
                        .build();
                lists.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    // b1 cập nhật lại số lượng ở bảng hóa đơn chi tiết(- đi) khi xóa sp 
    public boolean updateSoLuong_HDCT_KhiXoa(int soluong, int idHDCT) {
        int check = 0;
        String sql = """
                    update HoaDonChiTiet
                    set So_Luong = ?
                    where ID_HDCT = ?
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, soluong);
            ps.setObject(2, idHDCT);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    // b2 : cập nhật lại số lượng(+thêm) ở bảng sản phẩm khi xóa sản phẩm 
    //và cả b3 của hủy hóa đơn 
    public boolean updateSoLuong_SP_KhiXoa(int soluong, String maSP) {
        int check = 0;
        String sql = """
                    UPDATE [dbo].[SanPham]
                       SET [So_Luong] = ?
                     WHERE Ma_San_Pham = ?
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, soluong);
            ps.setObject(2, maSP);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    // thanh toán hóa đơn 
    public boolean update_ThanhToan_HD(HoaDon_BH_Response hd, String maHD) {
        int check = 0;
        String sql = """
                    update HoaDon
                    set Ngay_Thanh_Toan =? ,
                    Tong_Gia = ?,
                    Hinh_Thuc_Thanh_Toan= ?,
                    Ten_Nguoi_Nhan=?,
                    SĐT=?,
                    Dia_Chi=?,
                    Trang_Thai=1	,
                    ID_Giam_Gia=?
                    where Ma_Hoa_Don=?
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hd.getNgayThanhToan());
            ps.setObject(2, hd.getTongGia());
            ps.setObject(3, hd.getHinhThucTT());
            ps.setObject(4, hd.getTenNguoiNhan());
            ps.setObject(5, hd.getSDT());
            ps.setObject(6, hd.getDiaChi());
            ps.setObject(7, hd.getIdGiamGia());
            ps.setObject(8, maHD);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    // b1 hủy hóa đơn 
    public boolean updateTrang_Thai_Imei_Huy_HD(String maImei) {
        int check = 0;
        String sql = """
                    update Imei 
                    set Trang_Thai = 1
                    where Ma_Imei = ?
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maImei);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    // lấy ra hóa đơm chi tiết theo mã hóa đơn nhưng k group với imei 
    public ArrayList<HoaDonChiTiet_BH_Response> getTong_Imei_TheoMaSP_HuyHD(String maSP) {
        String sql = """
                 SELECT    count(SanPham.ID_San_Pham) as 'Tong Imei Theo Ma SP'
                 FROM         dbo.SanPham INNER JOIN
                                       dbo.Imei ON dbo.SanPham.ID_San_Pham = dbo.Imei.ID_San_Pham
                 					  where Imei.Trang_Thai =1 and SanPham.Ma_San_Pham = ?
                     """;
        ArrayList<HoaDonChiTiet_BH_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet_BH_Response response = HoaDonChiTiet_BH_Response.builder()
                        .soLuongImei(rs.getInt(1))
                        .build();
                lists.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    // cập nhật số lượng của hóa đơn chi tiết khi hủy hóa đơn 
    public boolean updateSoLuong_HDCT_HuyHD(int idHD) {
        int check = 0;
        String sql = """
                   update HoaDonChiTiet 
                   set So_Luong = 0
                   where ID_Hoa_Don = ?
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Object la cha cua cac loai kieu du lieu 
            ps.setObject(1, idHD);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }

    // Xóa thẳng hóa đơn chi tiết theo id hdct 
    public boolean Xoa_All_HDCT_GioHang(int idHDCT) {
        int check = 0;
        String sql = """
                 delete HoaDonChiTiet 
                  where ID_HDCT = ?
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Object la cha cua cac loai kieu du lieu 
            ps.setObject(1, idHDCT);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }

}
