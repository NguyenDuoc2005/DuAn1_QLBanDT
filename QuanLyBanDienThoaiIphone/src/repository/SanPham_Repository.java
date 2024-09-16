package repository;

import config.DBConnect;
import entity.CPU;
import entity.Ram;
import entity.SanPham;
import form.SanPham_form;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import response.NhanVien_Response;
import response.SanPham_Response;

public class SanPham_Repository {
// bảng sp

    public ArrayList<SanPham_Response> getAll_SP_Moi() {
        ArrayList<SanPham_Response> listSP = new ArrayList<>();
        listSP.clear();
        String sql = """
                    SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.SanPham.Mo_Ta, CONVERT(date,dbo.SanPham.Ngay_Tao) as'NgayTao', dbo.SanPham.So_Luong, dbo.SanPham.Gia_Nhap, dbo.SanPham.Gia_Ban, 
                                          dbo.SanPham.Hinh_Anh, dbo.SanPham.Trang_Thai, dbo.Rom.ID_Rom, dbo.Rom.Rom, dbo.MauSac.ID_Mau_Sac, dbo.MauSac.Mau_Sac, dbo.Ram.ID_Ram, dbo.Ram.Ram, dbo.KichThuoc.ID_Kich_Thuoc, 
                                          dbo.KichThuoc.Kich_Thuoc, dbo.PhanLoai.ID_Phan_Loai, dbo.PhanLoai.Phan_Loai, dbo.DungLuongPin.ID_Dung_Luong_Pin, dbo.DungLuongPin.Dung_Luong_Pin, dbo.XuatXu.ID_Xuat_Xu, dbo.XuatXu.Xuat_Xu, 
                                          dbo.CPU.ID_CPU, dbo.CPU.CPU
                    FROM         dbo.SanPham INNER JOIN
                                          dbo.Rom ON dbo.SanPham.ID_Rom = dbo.Rom.ID_Rom INNER JOIN
                                          dbo.Ram ON dbo.SanPham.ID_Ram = dbo.Ram.ID_Ram INNER JOIN
                                          dbo.PhanLoai ON dbo.SanPham.ID_Phan_Loai = dbo.PhanLoai.ID_Phan_Loai INNER JOIN
                                          dbo.XuatXu ON dbo.SanPham.ID_Xuat_Xu = dbo.XuatXu.ID_Xuat_Xu INNER JOIN
                                          dbo.MauSac ON dbo.SanPham.ID_Mau_Sac = dbo.MauSac.ID_Mau_Sac INNER JOIN
                                          dbo.KichThuoc ON dbo.SanPham.ID_Kich_Thuoc = dbo.KichThuoc.ID_Kich_Thuoc INNER JOIN
                                          dbo.DungLuongPin ON dbo.SanPham.ID_Dung_Luong_Pin = dbo.DungLuongPin.ID_Dung_Luong_Pin INNER JOIN
                                          dbo.CPU ON dbo.SanPham.ID_CPU = dbo.CPU.ID_CPU
                    WHERE SanPham.Trang_Thai = 0 order by SanPham.Ma_San_Pham desc
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_Response sp = SanPham_Response.builder()
                        .idSanPham(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .tenSanPham(rs.getString(3))
                        .moTa(rs.getString(4))
                        .ngayTao(rs.getDate(5))
                        .soLuong(rs.getInt(6))
                        .giaNhap(rs.getFloat(7))
                        .giaBan(rs.getFloat(8))
                        .hinhAnh(rs.getString(9))
                        .trangThai(rs.getInt(10))
                        .idRom(rs.getInt(11))
                        .rom(rs.getString(12))
                        .idMauSac(rs.getInt(13))
                        .mauSac(rs.getString(14))
                        .idRam(rs.getInt(15))
                        .ram(rs.getString(16))
                        .idKichThuoc(rs.getInt(17))
                        .kichThuoc(rs.getString(18))
                        .idPhanLoai(rs.getInt(19))
                        .phanLoai(rs.getString(20))
                        .idDungLuongPin(rs.getInt(21))
                        .dungLuongPin(rs.getString(22))
                        .idXuatXu(rs.getInt(23))
                        .xuatXu(rs.getString(24))
                        .idCPU(rs.getInt(25))
                        .CPU(rs.getString(26))
                        .build();
                listSP.add(sp);
            }
            return listSP;
        } catch (Exception e) {
            return null;
        }

    }

    //get all sản phẩm bang imei --  (chưa dùng đến phương thức này)
    public ArrayList<SanPham_Response> getAll_Tong_SP_Imei() {
        ArrayList<SanPham_Response> listSP = new ArrayList<>();
        listSP.clear();
        String sql = """
                    SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.SanPham.So_Luong, dbo.SanPham.Gia_Nhap, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, 
                                          dbo.PhanLoai.ID_Phan_Loai, dbo.PhanLoai.Phan_Loai
                    					  , COUNT(SanPham.ID_San_Pham) as'SoLuongTon'
                    FROM         dbo.SanPham INNER JOIN
                                          dbo.PhanLoai ON dbo.SanPham.ID_Phan_Loai = dbo.PhanLoai.ID_Phan_Loai INNER JOIN
                                          dbo.Imei ON dbo.SanPham.ID_San_Pham = dbo.Imei.ID_San_Pham
                    					  where SanPham.Trang_Thai = 0 and Imei.Trang_Thai = 1
                    GROUP BY   dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.SanPham.So_Luong, dbo.SanPham.Gia_Nhap, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, 
                                          dbo.PhanLoai.ID_Phan_Loai, dbo.PhanLoai.Phan_Loai 
                    ORDER BY dbo.SanPham.Ma_San_Pham desc
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_Response sp = SanPham_Response.builder()
                        .idSanPham(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .tenSanPham(rs.getString(3))
                        .soLuong(rs.getInt(4))
                        .giaNhap(rs.getFloat(5))
                        .giaBan(rs.getFloat(6))
                        .trangThai(rs.getInt(7))
                        .idPhanLoai(rs.getInt(8))
                        .phanLoai(rs.getString(9))
                        .tongImei(rs.getInt(10))
                        .build();
                listSP.add(sp);
            }
            return listSP;
        } catch (Exception e) {
            return null;
        }
    }

    //get all chi tiết imei bảng imei 
    public ArrayList<SanPham_Response> getAll_ImeiChiTiet(String maSP) {
        ArrayList<SanPham_Response> listSP = new ArrayList<>();
        listSP.clear();
        String sql = """
                   SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, 
                     dbo.SanPham.Ten_San_Pham, dbo.Imei.ID_Imei, dbo.Imei.Ma_Imei, dbo.Imei.Trang_Thai
                    FROM         dbo.SanPham INNER JOIN
                                          dbo.Imei ON dbo.SanPham.ID_San_Pham = dbo.Imei.ID_San_Pham
                    where  Imei.Trang_Thai = 1 and SanPham.Ma_San_Pham = ?
                    order by Imei.ID_Imei desc
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_Response sp = SanPham_Response.builder()
                        .idSanPham(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .tenSanPham(rs.getString(3))
                        .idImei(rs.getInt(4))
                        .imei(rs.getString(5))
                        .trangThaiImei(rs.getInt(6))
                        .build();
                listSP.add(sp);
            }
            return listSP;
        } catch (Exception e) {
            return null;
        }
    }

    // them san pham bang san pham 
    public boolean ThemSanPham(SanPham_Response sp) {
        int check = 0;
        ArrayList<SanPham_Response> list = new ArrayList<>();
        String query = """
                     INSERT INTO [dbo].[SanPham]
                                ([Ten_San_Pham],[Mo_Ta],[So_Luong]
                                ,[Gia_Nhap],[Gia_Ban],[Hinh_Anh],[ID_Rom]
                                ,[ID_Mau_Sac],[ID_Ram],[ID_Kich_Thuoc],[ID_Dung_Luong_Pin]
                                ,[ID_CPU],[ID_Xuat_Xu],[ID_Phan_Loai],[Trang_Thai])
                          VALUES
                                (?,?,?,?,?,?,?,?,?,?,?,?,?,?,0)
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, sp.getTenSanPham());
            ps.setString(2, sp.getMoTa());
            ps.setInt(3, 0);
            ps.setFloat(4, sp.getGiaNhap());
            ps.setFloat(5, sp.getGiaBan());
            ps.setString(6, sp.getHinhAnh());
            ps.setInt(7, sp.getIdRom());
            ps.setInt(8, sp.getIdMauSac());
            ps.setInt(9, sp.getIdRam());
            ps.setInt(10, sp.getIdKichThuoc());
            ps.setInt(11, sp.getIdDungLuongPin());
            ps.setInt(12, sp.getIdCPU());
            ps.setInt(13, sp.getIdXuatXu());
            ps.setInt(14, sp.getIdPhanLoai());

            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    // them imei san phẩm bảng imei 
    public boolean ThemImei_SP(SanPham_Response sp) {
        int check = 0;
        ArrayList<SanPham_Response> list = new ArrayList<>();
        String query = """
                       INSERT INTO [dbo].[Imei]
                                  ([Ma_Imei]
                                  ,[Trang_Thai]
                                  ,[ID_San_Pham])
                            VALUES
                                  (?,1,?)
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, sp.getImei());
            ps.setInt(2, sp.getIdSanPham());

            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    // them imei san phẩm bảng imei  excel 
    public boolean ThemImei_SP_Excel(String Ma_Imei, Integer idSP) {
        int check = 0;
        ArrayList<SanPham_Response> list = new ArrayList<>();
        String query = """
                       INSERT INTO [dbo].[Imei]
                                  ([Ma_Imei]
                                  ,[Trang_Thai]
                                  ,[ID_San_Pham])
                            VALUES
                                  (?,1,?)
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1,Ma_Imei);
            ps.setInt(2, idSP);

            check = ps.executeUpdate();
        } catch (Exception e) {
           
        }
        return check > 0;
    }
    // cập nhật lại số lượng sp vào bảng sản phẩm khi thêm imei sản phẩm 
    public boolean update_TonKho(SanPham_Response sp) {
        int check = 0;
        ArrayList<SanPham_Response> list = new ArrayList<>();
        String query = """
                       UPDATE [dbo].[SanPham]
                          SET 
                             [So_Luong] = ?
                        WHERE Ma_San_Pham = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, sp.getSoLuong());
            ps.setString(2, sp.getMaSanPham());
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    // cập nhật lại số lượng sp vào bảng sản phẩm khi thêm imei sản phẩm 
    public boolean update_TonKho_Excel(int soluong ,int idSP) {
        int check = 0;
        ArrayList<SanPham_Response> list = new ArrayList<>();
        String query = """
                       UPDATE [dbo].[SanPham]
                       SET 
                       [So_Luong] = ?
                       WHERE ID_San_Pham = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, soluong);
            ps.setInt(2, idSP);
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    // lấy số lượng sản phẩm khi group by theo mã sản phẩm 
    public ArrayList<SanPham_Response> getTonKho_TheoMaSP(String maSP) {
        ArrayList<SanPham_Response> listSP = new ArrayList<>();
        listSP.clear();
        String sql = """
                    SELECT SanPham.Ma_San_Pham , count(SanPham.ID_San_Pham) as 'TonKho'from 
                    dbo.SanPham INNER JOIN
                    dbo.Imei ON dbo.SanPham.ID_San_Pham = dbo.Imei.ID_San_Pham
                    where SanPham.Ma_San_Pham = ? and Imei.Trang_Thai = 1
                    group by SanPham.ID_San_Pham,SanPham.Ma_San_Pham 
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_Response sp = SanPham_Response.builder()
                        .maSanPham(rs.getString(1))
                        .tongImei(rs.getInt(2))
                        .build();
                listSP.add(sp);
            }
            return listSP;
        } catch (Exception e) {
            return null;
        }

    }
    // lấy số lượng sản phẩm khi group by theo idsp
    public ArrayList<SanPham_Response> getTonKho_TheoIDSP_Excel(int idSP) {
        ArrayList<SanPham_Response> listSP = new ArrayList<>();
        listSP.clear();
        String sql = """
                    SELECT SanPham.Ma_San_Pham , count(SanPham.ID_San_Pham) as 'TonKho'from 
                    dbo.SanPham INNER JOIN
                    dbo.Imei ON dbo.SanPham.ID_San_Pham = dbo.Imei.ID_San_Pham
                    where SanPham.ID_San_Pham = ? and Imei.Trang_Thai = 1
                    group by SanPham.ID_San_Pham,SanPham.Ma_San_Pham 
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_Response sp = SanPham_Response.builder()
                        .maSanPham(rs.getString(1))
                        .tongImei(rs.getInt(2))
                        .build();
                listSP.add(sp);
            }
            return listSP;
        } catch (Exception e) {
            return null;
        }

    }
    // xóa imei của sản phẩm theo mã IMEI 
    public boolean xoa_ImeiSP(String maImei) {
        int check = 0;
        ArrayList<SanPham_Response> list = new ArrayList<>();
        String query = """
                       DELETE FROM [dbo].[Imei]
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

    // lấy ra thông tin và thuộc tính của sản phẩm theo mã sản phẩm 
    public ArrayList<SanPham_Response> getAll_SP_TheoMaSP(String maSP) {
        ArrayList<SanPham_Response> listSP = new ArrayList<>();
        listSP.clear();
        String sql = """
                    SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.SanPham.Mo_Ta, CONVERT(date,dbo.SanPham.Ngay_Tao) as'NgayTao', dbo.SanPham.So_Luong, dbo.SanPham.Gia_Nhap, dbo.SanPham.Gia_Ban, 
                    dbo.SanPham.Hinh_Anh, dbo.SanPham.Trang_Thai, dbo.Rom.ID_Rom, dbo.Rom.Rom, dbo.MauSac.ID_Mau_Sac, dbo.MauSac.Mau_Sac, dbo.Ram.ID_Ram, dbo.Ram.Ram, dbo.KichThuoc.ID_Kich_Thuoc, 
                    dbo.KichThuoc.Kich_Thuoc, dbo.PhanLoai.ID_Phan_Loai, dbo.PhanLoai.Phan_Loai, dbo.DungLuongPin.ID_Dung_Luong_Pin, dbo.DungLuongPin.Dung_Luong_Pin, dbo.XuatXu.ID_Xuat_Xu, dbo.XuatXu.Xuat_Xu, 
                    dbo.CPU.ID_CPU, dbo.CPU.CPU
                    FROM  
                    dbo.SanPham INNER JOIN
                    dbo.Rom ON dbo.SanPham.ID_Rom = dbo.Rom.ID_Rom INNER JOIN
                    dbo.Ram ON dbo.SanPham.ID_Ram = dbo.Ram.ID_Ram INNER JOIN
                    dbo.PhanLoai ON dbo.SanPham.ID_Phan_Loai = dbo.PhanLoai.ID_Phan_Loai INNER JOIN
                    dbo.XuatXu ON dbo.SanPham.ID_Xuat_Xu = dbo.XuatXu.ID_Xuat_Xu INNER JOIN
                    dbo.MauSac ON dbo.SanPham.ID_Mau_Sac = dbo.MauSac.ID_Mau_Sac INNER JOIN
                    dbo.KichThuoc ON dbo.SanPham.ID_Kich_Thuoc = dbo.KichThuoc.ID_Kich_Thuoc INNER JOIN
                    dbo.DungLuongPin ON dbo.SanPham.ID_Dung_Luong_Pin = dbo.DungLuongPin.ID_Dung_Luong_Pin INNER JOIN
                    dbo.CPU ON dbo.SanPham.ID_CPU = dbo.CPU.ID_CPU
                    WHERE SanPham.Trang_Thai = 0 and SanPham.Ma_San_Pham = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_Response sp = SanPham_Response.builder()
                        .idSanPham(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .tenSanPham(rs.getString(3))
                        .moTa(rs.getString(4))
                        .ngayTao(rs.getDate(5))
                        .soLuong(rs.getInt(6))
                        .giaNhap(rs.getFloat(7))
                        .giaBan(rs.getFloat(8))
                        .hinhAnh(rs.getString(9))
                        .trangThai(rs.getInt(10))
                        .idRom(rs.getInt(11))
                        .rom(rs.getString(12))
                        .idMauSac(rs.getInt(13))
                        .mauSac(rs.getString(14))
                        .idRam(rs.getInt(15))
                        .ram(rs.getString(16))
                        .idKichThuoc(rs.getInt(17))
                        .kichThuoc(rs.getString(18))
                        .idPhanLoai(rs.getInt(19))
                        .phanLoai(rs.getString(20))
                        .idDungLuongPin(rs.getInt(21))
                        .dungLuongPin(rs.getString(22))
                        .idXuatXu(rs.getInt(23))
                        .xuatXu(rs.getString(24))
                        .idCPU(rs.getInt(25))
                        .CPU(rs.getString(26))
                        .build();
                listSP.add(sp);
            }
            return listSP;
        } catch (Exception e) {
            return null;
        }
    }

    // xóa sản phẩm ở bảng sản phẩm theo mã sp 
    public boolean xoaSanPham(String maSP) {
        int check = 0;
        ArrayList<SanPham_Response> list = new ArrayList<>();
        String query = """
                       UPDATE [dbo].[SanPham]
                          SET [Trang_Thai] = 1
                        WHERE Ma_San_Pham = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maSP);
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    // hiển thị tất cả các sản phẩm đã xóa 
    public ArrayList<SanPham_Response> getAll_SP_DaXoa() {
        ArrayList<SanPham_Response> listSP = new ArrayList<>();
        listSP.clear();
        String sql = """
                    SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.SanPham.Mo_Ta, CONVERT(date,dbo.SanPham.Ngay_Tao) as'NgayTao', dbo.SanPham.So_Luong, dbo.SanPham.Gia_Nhap, dbo.SanPham.Gia_Ban, 
                                                              dbo.SanPham.Hinh_Anh, dbo.SanPham.Trang_Thai, dbo.Rom.ID_Rom, dbo.Rom.Rom, dbo.MauSac.ID_Mau_Sac, dbo.MauSac.Mau_Sac, dbo.Ram.ID_Ram, dbo.Ram.Ram, dbo.KichThuoc.ID_Kich_Thuoc, 
                                                              dbo.KichThuoc.Kich_Thuoc, dbo.PhanLoai.ID_Phan_Loai, dbo.PhanLoai.Phan_Loai, dbo.DungLuongPin.ID_Dung_Luong_Pin, dbo.DungLuongPin.Dung_Luong_Pin, dbo.XuatXu.ID_Xuat_Xu, dbo.XuatXu.Xuat_Xu, 
                                                              dbo.CPU.ID_CPU, dbo.CPU.CPU
                                        FROM         dbo.SanPham INNER JOIN
                                                              dbo.Rom ON dbo.SanPham.ID_Rom = dbo.Rom.ID_Rom INNER JOIN
                                                              dbo.Ram ON dbo.SanPham.ID_Ram = dbo.Ram.ID_Ram INNER JOIN
                                                              dbo.PhanLoai ON dbo.SanPham.ID_Phan_Loai = dbo.PhanLoai.ID_Phan_Loai INNER JOIN
                                                              dbo.XuatXu ON dbo.SanPham.ID_Xuat_Xu = dbo.XuatXu.ID_Xuat_Xu INNER JOIN
                                                              dbo.MauSac ON dbo.SanPham.ID_Mau_Sac = dbo.MauSac.ID_Mau_Sac INNER JOIN
                                                              dbo.KichThuoc ON dbo.SanPham.ID_Kich_Thuoc = dbo.KichThuoc.ID_Kich_Thuoc INNER JOIN
                                                              dbo.DungLuongPin ON dbo.SanPham.ID_Dung_Luong_Pin = dbo.DungLuongPin.ID_Dung_Luong_Pin INNER JOIN
                                                              dbo.CPU ON dbo.SanPham.ID_CPU = dbo.CPU.ID_CPU
                                        WHERE SanPham.Trang_Thai = 1 order by SanPham.Ma_San_Pham desc
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_Response sp = SanPham_Response.builder()
                        .idSanPham(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .tenSanPham(rs.getString(3))
                        .moTa(rs.getString(4))
                        .ngayTao(rs.getDate(5))
                        .soLuong(rs.getInt(6))
                        .giaNhap(rs.getFloat(7))
                        .giaBan(rs.getFloat(8))
                        .hinhAnh(rs.getString(9))
                        .trangThai(rs.getInt(10))
                        .idRom(rs.getInt(11))
                        .rom(rs.getString(12))
                        .idMauSac(rs.getInt(13))
                        .mauSac(rs.getString(14))
                        .idRam(rs.getInt(15))
                        .ram(rs.getString(16))
                        .idKichThuoc(rs.getInt(17))
                        .kichThuoc(rs.getString(18))
                        .idPhanLoai(rs.getInt(19))
                        .phanLoai(rs.getString(20))
                        .idDungLuongPin(rs.getInt(21))
                        .dungLuongPin(rs.getString(22))
                        .idXuatXu(rs.getInt(23))
                        .xuatXu(rs.getString(24))
                        .idCPU(rs.getInt(25))
                        .CPU(rs.getString(26))
                        .build();
                listSP.add(sp);
            }
            return listSP;
        } catch (Exception e) {
            return null;
        }
    }

    // Khôi Phục sản phẩm cũ ở bảng sản phẩm theo mã sp 
    public boolean khoiPhuc_SP(String maSP) {
        int check = 0;
        ArrayList<SanPham_Response> list = new ArrayList<>();
        String query = """
                       UPDATE [dbo].[SanPham]
                          SET [Trang_Thai] = 0
                        WHERE Ma_San_Pham = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maSP);
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    // Tìm kiếm sản phẩm theo mã sản phẩm -------- chưa làm xong 
    public ArrayList<SanPham_Response> timKiem_SPTheoImei(String maImei) {

        String sql = """
                   SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.SanPham.Mo_Ta, 
                   CONVERT(date,dbo.SanPham.Ngay_Tao) as'NgayTao', dbo.SanPham.So_Luong, dbo.SanPham.Gia_Nhap, dbo.SanPham.Gia_Ban, 
                                                             dbo.SanPham.Hinh_Anh, dbo.SanPham.Trang_Thai, dbo.Rom.ID_Rom, 
                   										  dbo.Rom.Rom, dbo.MauSac.ID_Mau_Sac, dbo.MauSac.Mau_Sac, dbo.Ram.ID_Ram,
                   										  dbo.Ram.Ram, dbo.KichThuoc.ID_Kich_Thuoc, 
                                                             dbo.KichThuoc.Kich_Thuoc, dbo.PhanLoai.ID_Phan_Loai, dbo.PhanLoai.Phan_Loai, 
                   										  dbo.DungLuongPin.ID_Dung_Luong_Pin, dbo.DungLuongPin.Dung_Luong_Pin,
                   										  dbo.XuatXu.ID_Xuat_Xu, dbo.XuatXu.Xuat_Xu, 
                                                             dbo.CPU.ID_CPU, dbo.CPU.CPU , dbo.Imei.Ma_Imei
                                       FROM         dbo.SanPham INNER JOIN
                                                             dbo.Rom ON dbo.SanPham.ID_Rom = dbo.Rom.ID_Rom INNER JOIN
                                                             dbo.Ram ON dbo.SanPham.ID_Ram = dbo.Ram.ID_Ram INNER JOIN
                                                             dbo.PhanLoai ON dbo.SanPham.ID_Phan_Loai = dbo.PhanLoai.ID_Phan_Loai INNER JOIN
                                                             dbo.XuatXu ON dbo.SanPham.ID_Xuat_Xu = dbo.XuatXu.ID_Xuat_Xu INNER JOIN
                                                             dbo.MauSac ON dbo.SanPham.ID_Mau_Sac = dbo.MauSac.ID_Mau_Sac INNER JOIN
                                                             dbo.KichThuoc ON dbo.SanPham.ID_Kich_Thuoc = dbo.KichThuoc.ID_Kich_Thuoc INNER JOIN
                                                             dbo.DungLuongPin ON dbo.SanPham.ID_Dung_Luong_Pin = dbo.DungLuongPin.ID_Dung_Luong_Pin INNER JOIN
                                                             dbo.CPU ON dbo.SanPham.ID_CPU = dbo.CPU.ID_CPU INNER JOIN 
                   										  Imei ON dbo.Imei.ID_San_Pham = dbo.SanPham.ID_San_Pham
                                       WHERE SanPham.Trang_Thai = 0 and dbo.Imei.Ma_Imei = ?
                   					
                   					order by SanPham.Ma_San_Pham desc
                   		
                     """;

        // khai báo list 
        ArrayList<SanPham_Response> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maImei);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_Response sp = SanPham_Response.builder()
                        .idSanPham(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .tenSanPham(rs.getString(3))
                        .moTa(rs.getString(4))
                        .ngayTao(rs.getDate(5))
                        .soLuong(rs.getInt(6))
                        .giaNhap(rs.getFloat(7))
                        .giaBan(rs.getFloat(8))
                        .hinhAnh(rs.getString(9))
                        .trangThai(rs.getInt(10))
                        .idRom(rs.getInt(11))
                        .rom(rs.getString(12))
                        .idMauSac(rs.getInt(13))
                        .mauSac(rs.getString(14))
                        .idRam(rs.getInt(15))
                        .ram(rs.getString(16))
                        .idKichThuoc(rs.getInt(17))
                        .kichThuoc(rs.getString(18))
                        .idPhanLoai(rs.getInt(19))
                        .phanLoai(rs.getString(20))
                        .idDungLuongPin(rs.getInt(21))
                        .dungLuongPin(rs.getString(22))
                        .idXuatXu(rs.getInt(23))
                        .xuatXu(rs.getString(24))
                        .idCPU(rs.getInt(25))
                        .CPU(rs.getString(26))
                        .imei(rs.getString(27))
                        .build();
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return null;
    }

    // Khôi Phục sản phẩm cũ ở bảng sản phẩm theo mã sp 
    public boolean Update_ThuocTinhSP(SanPham_Response sp, String maSP) {
        int check = 0;
        ArrayList<SanPham_Response> list = new ArrayList<>();
        String query = """
                       UPDATE [dbo].[SanPham]
                          SET [Ten_San_Pham] = ?
                             ,[Mo_Ta] =  ?,[Gia_Nhap] =  ?
                             ,[Gia_Ban] = ?,[Hinh_Anh] = ?,[ID_Rom] = ?
                             ,[ID_Mau_Sac] = ?,[ID_Ram] = ?,[ID_Kich_Thuoc] = ? 
                             ,[ID_Dung_Luong_Pin] =  ? ,[ID_CPU] = ?,[ID_Xuat_Xu] = ? 
                             ,[ID_Phan_Loai] = ? 
                        WHERE Ma_San_Pham = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, sp.getTenSanPham());
            ps.setString(2, sp.getMoTa());
            ps.setFloat(3, sp.getGiaNhap());
            ps.setFloat(4, sp.getGiaBan());
            ps.setString(5, sp.getHinhAnh());
            ps.setInt(6, sp.getIdRom());
            ps.setInt(7, sp.getIdMauSac());
            ps.setInt(8, sp.getIdRam());
            ps.setInt(9, sp.getIdKichThuoc());
            ps.setInt(10, sp.getIdDungLuongPin());
            ps.setInt(11, sp.getIdCPU());
            ps.setInt(12, sp.getIdXuatXu());
            ps.setInt(13, sp.getIdPhanLoai());
            // 
            ps.setString(14, maSP);
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    // Tìm kiếm sản phẩm theo mã sản phẩm -------- 
    public ArrayList<SanPham_Response> timKiem_SP(String keyword, Integer trangThai) {

        String sql = """
                     
                     """;
        if (keyword.length() > 0) {
            // các trường cần tìm kiếm
            sql += """
                SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.SanPham.Mo_Ta, 
                CONVERT(date,dbo.SanPham.Ngay_Tao) as'NgayTao', dbo.SanPham.So_Luong, dbo.SanPham.Gia_Nhap, dbo.SanPham.Gia_Ban, 
                                                          dbo.SanPham.Hinh_Anh, dbo.SanPham.Trang_Thai, dbo.Rom.ID_Rom, 
                										  dbo.Rom.Rom, dbo.MauSac.ID_Mau_Sac, dbo.MauSac.Mau_Sac, dbo.Ram.ID_Ram,
                										  dbo.Ram.Ram, dbo.KichThuoc.ID_Kich_Thuoc, 
                                                          dbo.KichThuoc.Kich_Thuoc, dbo.PhanLoai.ID_Phan_Loai, dbo.PhanLoai.Phan_Loai, 
                										  dbo.DungLuongPin.ID_Dung_Luong_Pin, dbo.DungLuongPin.Dung_Luong_Pin,
                										  dbo.XuatXu.ID_Xuat_Xu, dbo.XuatXu.Xuat_Xu, 
                                                          dbo.CPU.ID_CPU, dbo.CPU.CPU 
                                    FROM         dbo.SanPham INNER JOIN
                                                          dbo.Rom ON dbo.SanPham.ID_Rom = dbo.Rom.ID_Rom INNER JOIN
                                                          dbo.Ram ON dbo.SanPham.ID_Ram = dbo.Ram.ID_Ram INNER JOIN
                                                          dbo.PhanLoai ON dbo.SanPham.ID_Phan_Loai = dbo.PhanLoai.ID_Phan_Loai INNER JOIN
                                                          dbo.XuatXu ON dbo.SanPham.ID_Xuat_Xu = dbo.XuatXu.ID_Xuat_Xu INNER JOIN
                                                          dbo.MauSac ON dbo.SanPham.ID_Mau_Sac = dbo.MauSac.ID_Mau_Sac INNER JOIN
                                                          dbo.KichThuoc ON dbo.SanPham.ID_Kich_Thuoc = dbo.KichThuoc.ID_Kich_Thuoc INNER JOIN
                                                          dbo.DungLuongPin ON dbo.SanPham.ID_Dung_Luong_Pin = dbo.DungLuongPin.ID_Dung_Luong_Pin INNER JOIN
                                                          dbo.CPU ON dbo.SanPham.ID_CPU = dbo.CPU.ID_CPU 
                										
                                    WHERE SanPham.Trang_Thai = ?
                 """;
        }
        if (keyword.length() > 0) {
            sql += """
                 AND(
              dbo.SanPham.Ten_San_Pham like ?
              or dbo.SanPham.Ma_San_Pham like ?
              or rom.Rom like ?
              or MauSac.Mau_Sac like ?
              or Ram.Ram like ?
              or KichThuoc.Kich_Thuoc like ?
              or PhanLoai.Phan_Loai like ?
              or DungLuongPin.Dung_Luong_Pin like ?
              or XuatXu.Xuat_Xu like ?
              or CPU.CPU like ?
                                 								
                                 					)
                                 					
                                 					order by SanPham.Ma_San_Pham desc
                 """;
        }
        // khai báo list 
        ArrayList<SanPham_Response> listSP = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            int index = 1; // vị trí của dấu hỏi chấm đầu tiên
            ps.setObject(index++, trangThai);
            String value = "%" + keyword + "%";
            if (keyword.length() > 0) {
                // search 1 ô trong nhiều dòng 
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);

            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_Response sp = SanPham_Response.builder()
                        .idSanPham(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .tenSanPham(rs.getString(3))
                        .moTa(rs.getString(4))
                        .ngayTao(rs.getDate(5))
                        .soLuong(rs.getInt(6))
                        .giaNhap(rs.getFloat(7))
                        .giaBan(rs.getFloat(8))
                        .hinhAnh(rs.getString(9))
                        .trangThai(rs.getInt(10))
                        .idRom(rs.getInt(11))
                        .rom(rs.getString(12))
                        .idMauSac(rs.getInt(13))
                        .mauSac(rs.getString(14))
                        .idRam(rs.getInt(15))
                        .ram(rs.getString(16))
                        .idKichThuoc(rs.getInt(17))
                        .kichThuoc(rs.getString(18))
                        .idPhanLoai(rs.getInt(19))
                        .phanLoai(rs.getString(20))
                        .idDungLuongPin(rs.getInt(21))
                        .dungLuongPin(rs.getString(22))
                        .idXuatXu(rs.getInt(23))
                        .xuatXu(rs.getString(24))
                        .idCPU(rs.getInt(25))
                        .CPU(rs.getString(26))
                        .build();
                listSP.add(sp);
            }
            return listSP;
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return null;
    }

    // Tìm kiếm sản phẩm theo Khoảng Giá
    public ArrayList<SanPham_Response> timKiem_SP_TheoGia(String min,String max, Integer trangThai) {

        String sql = """
                   
                   		
                     """;

        // các trường cần tìm kiếm
        sql += """
                SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.SanPham.Mo_Ta, 
                                CONVERT(date,dbo.SanPham.Ngay_Tao) as'NgayTao', dbo.SanPham.So_Luong, dbo.SanPham.Gia_Nhap, dbo.SanPham.Gia_Ban, 
                                                                          dbo.SanPham.Hinh_Anh, dbo.SanPham.Trang_Thai, dbo.Rom.ID_Rom, 
                                										  dbo.Rom.Rom, dbo.MauSac.ID_Mau_Sac, dbo.MauSac.Mau_Sac, dbo.Ram.ID_Ram,
                                										  dbo.Ram.Ram, dbo.KichThuoc.ID_Kich_Thuoc, 
                                                                          dbo.KichThuoc.Kich_Thuoc, dbo.PhanLoai.ID_Phan_Loai, dbo.PhanLoai.Phan_Loai, 
                                										  dbo.DungLuongPin.ID_Dung_Luong_Pin, dbo.DungLuongPin.Dung_Luong_Pin,
                                										  dbo.XuatXu.ID_Xuat_Xu, dbo.XuatXu.Xuat_Xu, 
                                                                          dbo.CPU.ID_CPU, dbo.CPU.CPU 
                                                    FROM         dbo.SanPham INNER JOIN
                                                                          dbo.Rom ON dbo.SanPham.ID_Rom = dbo.Rom.ID_Rom INNER JOIN
                                                                          dbo.Ram ON dbo.SanPham.ID_Ram = dbo.Ram.ID_Ram INNER JOIN
                                                                          dbo.PhanLoai ON dbo.SanPham.ID_Phan_Loai = dbo.PhanLoai.ID_Phan_Loai INNER JOIN
                                                                          dbo.XuatXu ON dbo.SanPham.ID_Xuat_Xu = dbo.XuatXu.ID_Xuat_Xu INNER JOIN
                                                                          dbo.MauSac ON dbo.SanPham.ID_Mau_Sac = dbo.MauSac.ID_Mau_Sac INNER JOIN
                                                                          dbo.KichThuoc ON dbo.SanPham.ID_Kich_Thuoc = dbo.KichThuoc.ID_Kich_Thuoc INNER JOIN
                                                                          dbo.DungLuongPin ON dbo.SanPham.ID_Dung_Luong_Pin = dbo.DungLuongPin.ID_Dung_Luong_Pin INNER JOIN
                                                                          dbo.CPU ON dbo.SanPham.ID_CPU = dbo.CPU.ID_CPU 
                                										
                                                                            WHERE SanPham.Trang_Thai = ?								
                 """;

        sql += """
                 and 
                    (
                     SanPham.Gia_Nhap	between ? and ?
                  or SanPham.Gia_Ban	between ? and ?
                     )
                 """;

        // khai báo list 
        ArrayList<SanPham_Response> listSP = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, trangThai);
            ps.setObject(2,min);
            ps.setObject(3,max);
            ps.setObject(4,min);
            ps.setObject(5,max);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_Response sp = SanPham_Response.builder()
                        .idSanPham(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .tenSanPham(rs.getString(3))
                        .moTa(rs.getString(4))
                        .ngayTao(rs.getDate(5))
                        .soLuong(rs.getInt(6))
                        .giaNhap(rs.getFloat(7))
                        .giaBan(rs.getFloat(8))
                        .hinhAnh(rs.getString(9))
                        .trangThai(rs.getInt(10))
                        .idRom(rs.getInt(11))
                        .rom(rs.getString(12))
                        .idMauSac(rs.getInt(13))
                        .mauSac(rs.getString(14))
                        .idRam(rs.getInt(15))
                        .ram(rs.getString(16))
                        .idKichThuoc(rs.getInt(17))
                        .kichThuoc(rs.getString(18))
                        .idPhanLoai(rs.getInt(19))
                        .phanLoai(rs.getString(20))
                        .idDungLuongPin(rs.getInt(21))
                        .dungLuongPin(rs.getString(22))
                        .idXuatXu(rs.getInt(23))
                        .xuatXu(rs.getString(24))
                        .idCPU(rs.getInt(25))
                        .CPU(rs.getString(26))
                        .build();
                listSP.add(sp);
            }
            return listSP;
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return null;
    }

    // xuất excel 
    
    public ArrayList<SanPham_Response> getXuatExcel() {
        ArrayList<SanPham_Response> listSP = new ArrayList<>();
        listSP.clear();
        String sql = """
                    SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.SanPham.Mo_Ta, CONVERT(date,dbo.SanPham.Ngay_Tao) as'NgayTao', dbo.SanPham.So_Luong, dbo.SanPham.Gia_Nhap, dbo.SanPham.Gia_Ban, 
                                                              dbo.SanPham.Hinh_Anh, dbo.SanPham.Trang_Thai, dbo.Rom.ID_Rom, dbo.Rom.Rom, dbo.MauSac.ID_Mau_Sac, dbo.MauSac.Mau_Sac, dbo.Ram.ID_Ram, dbo.Ram.Ram, dbo.KichThuoc.ID_Kich_Thuoc, 
                                                              dbo.KichThuoc.Kich_Thuoc, dbo.PhanLoai.ID_Phan_Loai, dbo.PhanLoai.Phan_Loai, dbo.DungLuongPin.ID_Dung_Luong_Pin, dbo.DungLuongPin.Dung_Luong_Pin, dbo.XuatXu.ID_Xuat_Xu, dbo.XuatXu.Xuat_Xu, 
                                                              dbo.CPU.ID_CPU, dbo.CPU.CPU
                                        FROM         dbo.SanPham INNER JOIN
                                                              dbo.Rom ON dbo.SanPham.ID_Rom = dbo.Rom.ID_Rom INNER JOIN
                                                              dbo.Ram ON dbo.SanPham.ID_Ram = dbo.Ram.ID_Ram INNER JOIN
                                                              dbo.PhanLoai ON dbo.SanPham.ID_Phan_Loai = dbo.PhanLoai.ID_Phan_Loai INNER JOIN
                                                              dbo.XuatXu ON dbo.SanPham.ID_Xuat_Xu = dbo.XuatXu.ID_Xuat_Xu INNER JOIN
                                                              dbo.MauSac ON dbo.SanPham.ID_Mau_Sac = dbo.MauSac.ID_Mau_Sac INNER JOIN
                                                              dbo.KichThuoc ON dbo.SanPham.ID_Kich_Thuoc = dbo.KichThuoc.ID_Kich_Thuoc INNER JOIN
                                                              dbo.DungLuongPin ON dbo.SanPham.ID_Dung_Luong_Pin = dbo.DungLuongPin.ID_Dung_Luong_Pin INNER JOIN
                                                              dbo.CPU ON dbo.SanPham.ID_CPU = dbo.CPU.ID_CPU
                                        WHERE SanPham.Trang_Thai = 0 and SanPham.So_Luong >0
                    					order by SanPham.Ma_San_Pham desc
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_Response sp = SanPham_Response.builder()
                        .idSanPham(rs.getInt(1))
                        .maSanPham(rs.getString(2))
                        .tenSanPham(rs.getString(3))
                        .moTa(rs.getString(4))
                        .ngayTao(rs.getDate(5))
                        .soLuong(rs.getInt(6))
                        .giaNhap(rs.getFloat(7))
                        .giaBan(rs.getFloat(8))
                        .hinhAnh(rs.getString(9))
                        .trangThai(rs.getInt(10))
                        .idRom(rs.getInt(11))
                        .rom(rs.getString(12))
                        .idMauSac(rs.getInt(13))
                        .mauSac(rs.getString(14))
                        .idRam(rs.getInt(15))
                        .ram(rs.getString(16))
                        .idKichThuoc(rs.getInt(17))
                        .kichThuoc(rs.getString(18))
                        .idPhanLoai(rs.getInt(19))
                        .phanLoai(rs.getString(20))
                        .idDungLuongPin(rs.getInt(21))
                        .dungLuongPin(rs.getString(22))
                        .idXuatXu(rs.getInt(23))
                        .xuatXu(rs.getString(24))
                        .idCPU(rs.getInt(25))
                        .CPU(rs.getString(26))
                        .build();
                listSP.add(sp);
            }
            return listSP;
        } catch (Exception e) {
            return null;
        }

    }
    
    // lấy số lượng sản phẩm khi group by theo idsp
    public ArrayList<SanPham_Response> getID_SP_CBO_MaSP(String MaSP) {
        ArrayList<SanPham_Response> listSP = new ArrayList<>();
        listSP.clear();
        String sql = """
                    select ID_San_Pham from SanPham 
                    where Ma_San_Pham = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, MaSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_Response sp = SanPham_Response.builder()
                        .idSanPham(rs.getInt(1))
                        .build();
                listSP.add(sp);
            }
            return listSP;
        } catch (Exception e) {
            return null;
        }

    }
    
    
}
