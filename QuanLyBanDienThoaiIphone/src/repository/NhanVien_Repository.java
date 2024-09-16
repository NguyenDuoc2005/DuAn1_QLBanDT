package repository;

import config.DBConnect;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import response.NhanVien_Response;

public class NhanVien_Repository {

    public ArrayList<NhanVien_Response> getAll_NV_Moi() {
        ArrayList<NhanVien_Response> listNV = new ArrayList<>();
        listNV.clear();
        String sql = """
                    SELECT    dbo.NhanVien.ID_Nhan_Vien, dbo.NhanVien.Ma_NV, dbo.NhanVien.Tai_Khoan, dbo.NhanVien.Mat_Khau, dbo.NhanVien.Ho_Ten, dbo.NhanVien.Gioi_Tinh, dbo.NhanVien.Ngay_Sinh, dbo.NhanVien.Dia_Chi, 
                                                                 dbo.NhanVien.SDT, dbo.NhanVien.Hinh_Anh, dbo.NhanVien.Email, dbo.NhanVien.Trang_Thai, dbo.NhanVien.ID_Chuc_Vu, dbo.ChucVu.ID_Chuc_Vu AS Expr1, dbo.ChucVu.Chuc_Vu
                                           FROM         dbo.NhanVien INNER JOIN
                                                                 dbo.ChucVu ON dbo.NhanVien.ID_Chuc_Vu = dbo.ChucVu.ID_Chuc_Vu
                     where NhanVien.Trang_Thai = 0  order by ID_Nhan_Vien desc
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien_Response nv = NhanVien_Response.builder()
                        .id_NhanVien(rs.getInt(1))
                        .maNhanVien(rs.getString(2))
                        .taiKhoan(rs.getString(3))
                        .matKhau(rs.getString(4))
                        .hoTen(rs.getString(5))
                        .gioiTinh(rs.getBoolean(6))
                        .ngaySinh(rs.getDate(7))
                        .diaChi(rs.getString(8))
                        .SDT(rs.getString(9))
                        .hinhAnh(rs.getString(10))
                        .email(rs.getString(11))
                        .trangThai(rs.getInt(12))
                        .id_ChucVu_NV(rs.getInt(13))
                        .id_ChucVu(rs.getInt(14))
                        .chucVu(rs.getString(15))
                        .build();
                listNV.add(nv);
            }
            return listNV;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<NhanVien_Response> getAll_NV_Cu() {
        ArrayList<NhanVien_Response> listNV = new ArrayList<>();
        listNV.clear();
        String sql = """
                    SELECT    dbo.NhanVien.ID_Nhan_Vien, dbo.NhanVien.Ma_NV, dbo.NhanVien.Tai_Khoan, dbo.NhanVien.Mat_Khau, dbo.NhanVien.Ho_Ten, dbo.NhanVien.Gioi_Tinh, dbo.NhanVien.Ngay_Sinh, dbo.NhanVien.Dia_Chi, 
                                                                 dbo.NhanVien.SDT, dbo.NhanVien.Hinh_Anh, dbo.NhanVien.Email, dbo.NhanVien.Trang_Thai, dbo.NhanVien.ID_Chuc_Vu, dbo.ChucVu.ID_Chuc_Vu AS Expr1, dbo.ChucVu.Chuc_Vu
                                           FROM         dbo.NhanVien INNER JOIN
                                                                 dbo.ChucVu ON dbo.NhanVien.ID_Chuc_Vu = dbo.ChucVu.ID_Chuc_Vu
                     where NhanVien.Trang_Thai = 1
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien_Response nv = NhanVien_Response.builder()
                        .id_NhanVien(rs.getInt(1))
                        .maNhanVien(rs.getString(2))
                        .taiKhoan(rs.getString(3))
                        .matKhau(rs.getString(4))
                        .hoTen(rs.getString(5))
                        .gioiTinh(rs.getBoolean(6))
                        .ngaySinh(rs.getDate(7))
                        .diaChi(rs.getString(8))
                        .SDT(rs.getString(9))
                        .hinhAnh(rs.getString(10))
                        .email(rs.getString(11))
                        .trangThai(rs.getInt(12))
                        .id_ChucVu_NV(rs.getInt(13))
                        .id_ChucVu(rs.getInt(14))
                        .chucVu(rs.getString(15))
                        .build();
                listNV.add(nv);
            }
            return listNV;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<NhanVien_Response> search(String keyword, Integer trangThai) {
        String sql = """
                   SELECT    dbo.NhanVien.ID_Nhan_Vien, dbo.NhanVien.Ma_NV, dbo.NhanVien.Tai_Khoan, dbo.NhanVien.Mat_Khau, dbo.NhanVien.Ho_Ten, dbo.NhanVien.Gioi_Tinh, dbo.NhanVien.Ngay_Sinh, dbo.NhanVien.Dia_Chi, 
                                                                                    dbo.NhanVien.SDT, dbo.NhanVien.Hinh_Anh, dbo.NhanVien.Email, dbo.NhanVien.Trang_Thai, dbo.NhanVien.ID_Chuc_Vu, dbo.ChucVu.ID_Chuc_Vu AS Expr1, dbo.ChucVu.Chuc_Vu
                                                              FROM         dbo.NhanVien INNER JOIN
                                                                                    dbo.ChucVu ON dbo.NhanVien.ID_Chuc_Vu = dbo.ChucVu.ID_Chuc_Vu
                                        where NhanVien.Trang_Thai = ?
                   		
                     """;
        if (keyword.length() > 0) {
            sql += """
                 AND (NhanVien.Ma_NV like ?
                 		OR NhanVien.Ho_Ten LIKE ?
                                   OR NhanVien.Ngay_Sinh LIKE ?
                                   OR NhanVien.Dia_Chi LIKE ?
                                   OR NhanVien.SDT LIKE ?
                                   OR NhanVien.Email LIKE ?
                 	                		)
                   order by ID_Nhan_Vien desc
                 """;
        }
        ArrayList<NhanVien_Response> lists = new ArrayList<>();
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

            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien_Response nv = NhanVien_Response.builder()
                        .id_NhanVien(rs.getInt(1))
                        .maNhanVien(rs.getString(2))
                        .taiKhoan(rs.getString(3))
                        .matKhau(rs.getString(4))
                        .hoTen(rs.getString(5))
                        .gioiTinh(rs.getBoolean(6))
                        .ngaySinh(rs.getDate(7))
                        .diaChi(rs.getString(8))
                        .SDT(rs.getString(9))
                        .hinhAnh(rs.getString(10))
                        .email(rs.getString(11))
                        .trangThai(rs.getInt(12))
                        .id_ChucVu_NV(rs.getInt(13))
                        .id_ChucVu(rs.getInt(14))
                        .chucVu(rs.getString(15))
                        .build();
                lists.add(nv);
            }

        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public ArrayList<NhanVien_Response> getAll_NV_Moi_Cu() {
        ArrayList<NhanVien_Response> listNV = new ArrayList<>();
        listNV.clear();
        String sql = """
                    SELECT    dbo.NhanVien.ID_Nhan_Vien, dbo.NhanVien.Ma_NV, 
                     dbo.NhanVien.Tai_Khoan, dbo.NhanVien.Mat_Khau, dbo.NhanVien.Ho_Ten,
                     dbo.NhanVien.Gioi_Tinh, dbo.NhanVien.Ngay_Sinh, dbo.NhanVien.Dia_Chi, 
                                                                 dbo.NhanVien.SDT, dbo.NhanVien.Hinh_Anh,
                     dbo.NhanVien.Email, dbo.NhanVien.Trang_Thai, dbo.NhanVien.ID_Chuc_Vu, 
                     dbo.ChucVu.ID_Chuc_Vu AS Expr1, dbo.ChucVu.Chuc_Vu
                     FROM         dbo.NhanVien INNER JOIN
                      dbo.ChucVu ON dbo.NhanVien.ID_Chuc_Vu = dbo.ChucVu.ID_Chuc_Vu
                     
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien_Response nv = NhanVien_Response.builder()
                        .id_NhanVien(rs.getInt(1))
                        .maNhanVien(rs.getString(2))
                        .taiKhoan(rs.getString(3))
                        .matKhau(rs.getString(4))
                        .hoTen(rs.getString(5))
                        .gioiTinh(rs.getBoolean(6))
                        .ngaySinh(rs.getDate(7))
                        .diaChi(rs.getString(8))
                        .SDT(rs.getString(9))
                        .hinhAnh(rs.getString(10))
                        .email(rs.getString(11))
                        .trangThai(rs.getInt(12))
                        .id_ChucVu_NV(rs.getInt(13))
                        .id_ChucVu(rs.getInt(14))
                        .chucVu(rs.getString(15))
                        .build();
                listNV.add(nv);
            }
            return listNV;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean Add_ThongTin_NV(NhanVien nv) {
        int check = 0;
        String sql = """
                        INSERT INTO [dbo].[NhanVien]
                                   ([Ma_NV]
                                   ,[Tai_Khoan]
                                   ,[Mat_Khau]
                                   ,[Ho_Ten]
                                   ,[Gioi_Tinh]
                                   ,[Ngay_Sinh]
                                   ,[Dia_Chi]
                                   ,[SDT]
                                   ,[Hinh_Anh]
                                   ,[Email]
                                   ,[Trang_Thai]
                                   ,[ID_Chuc_Vu])
                             VALUES
                                   (?,?,?,?,?,?,?,?,?,?,0,?)
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nv.getMaNhanVien());
            ps.setString(2, nv.getTaiKhoan());
            ps.setString(3, nv.getMatKhau());
            ps.setString(4, nv.getHoTen());
            ps.setBoolean(5, nv.isGioiTinh());
            ps.setDate(6, new java.sql.Date(nv.getNgaySinh().getTime()));
            ps.setString(7, nv.getDiaChi());
            ps.setString(8, nv.getSDT());
            ps.setString(9, nv.getHinhAnh());
            ps.setString(10, nv.getEmail());
            ps.setInt(11, nv.getId_ChucVu());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean Update_ThongTin_NV(NhanVien nv, int id_NV) {
        int check = 0;
        String sql = """
                       UPDATE [dbo].[NhanVien]
                          SET [Ma_NV] = ?,[Tai_Khoan] = ? ,[Mat_Khau] =  ?
                             ,[Ho_Ten] =  ?,[Gioi_Tinh] = ? ,[Ngay_Sinh] =  ?,[Dia_Chi] =  ?
                             ,[SDT] = ?,[Hinh_Anh] = ? ,[Email] = ?,[Trang_Thai] = 0,[ID_Chuc_Vu] = ? 
                        WHERE  ID_Nhan_Vien = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nv.getMaNhanVien());
            ps.setString(2, nv.getTaiKhoan());
            ps.setString(3, nv.getMatKhau());
            ps.setString(4, nv.getHoTen());
            ps.setBoolean(5, nv.isGioiTinh());
            ps.setDate(6, new java.sql.Date(nv.getNgaySinh().getTime()));
            ps.setString(7, nv.getDiaChi());
            ps.setString(8, nv.getSDT());
            ps.setString(9, nv.getHinhAnh());
            ps.setString(10, nv.getEmail());
            ps.setInt(11, nv.getId_ChucVu());
            // dk 
            ps.setInt(12, id_NV);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean Update_TrangThaiNVCu_Moi(NhanVien nv, int trangThai) {
        int check = 0;
        ArrayList<NhanVien> listNV = new ArrayList<>();
        String sql = """
                      UPDATE [dbo].[NhanVien]
                        SET [Trang_Thai] = ?
                      WHERE  Ma_NV = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, trangThai);
            ps.setString(2, nv.getMaNhanVien());
            check = ps.executeUpdate();

        } catch (Exception e) {

        }
        return check > 0;
    }

    public ArrayList<NhanVien_Response> getALL_ChucVu(int idChucVu) {
        ArrayList<NhanVien_Response> listNV = new ArrayList<>();
        listNV.clear();
        String sql = """
                    SELECT    dbo.NhanVien.ID_Nhan_Vien, dbo.NhanVien.Ma_NV, dbo.NhanVien.Tai_Khoan, dbo.NhanVien.Mat_Khau, dbo.NhanVien.Ho_Ten, dbo.NhanVien.Gioi_Tinh, dbo.NhanVien.Ngay_Sinh, dbo.NhanVien.Dia_Chi, 
                                                                                     dbo.NhanVien.SDT, dbo.NhanVien.Hinh_Anh, dbo.NhanVien.Email, dbo.NhanVien.Trang_Thai, dbo.NhanVien.ID_Chuc_Vu, dbo.ChucVu.ID_Chuc_Vu AS Expr1, dbo.ChucVu.Chuc_Vu
                                                               FROM         dbo.NhanVien INNER JOIN
                                                                                     dbo.ChucVu ON dbo.NhanVien.ID_Chuc_Vu = dbo.ChucVu.ID_Chuc_Vu
                                         where NhanVien.Trang_Thai = 0  and dbo.ChucVu.ID_Chuc_Vu = ?
                    					 
                    					 order by ID_Nhan_Vien desc
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idChucVu);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien_Response nv = NhanVien_Response.builder()
                        .id_NhanVien(rs.getInt(1))
                        .maNhanVien(rs.getString(2))
                        .taiKhoan(rs.getString(3))
                        .matKhau(rs.getString(4))
                        .hoTen(rs.getString(5))
                        .gioiTinh(rs.getBoolean(6))
                        .ngaySinh(rs.getDate(7))
                        .diaChi(rs.getString(8))
                        .SDT(rs.getString(9))
                        .hinhAnh(rs.getString(10))
                        .email(rs.getString(11))
                        .trangThai(rs.getInt(12))
                        .id_ChucVu_NV(rs.getInt(13))
                        .id_ChucVu(rs.getInt(14))
                        .chucVu(rs.getString(15))
                        .build();
                listNV.add(nv);
            }
            return listNV;
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        NhanVien_Repository nv = new NhanVien_Repository();
        System.out.println(nv.getALL_lichSuBanHang("NV001"));
    }

    // hiển thị lịch sử bán hàng của nhân viên
    public ArrayList<NhanVien_Response> getALL_lichSuBanHang(String maNV) {
        ArrayList<NhanVien_Response> listNV = new ArrayList<>();
        listNV.clear();
        String sql = """
                  SELECT    dbo.NhanVien.Ma_NV, dbo.HoaDon.Ma_Hoa_Don, dbo.HoaDon.Ten_Nguoi_Nhan, dbo.HoaDon.SĐT,
                  		CONVERT(date, dbo.HoaDon.Ngay_Tao) as'NgayTao',
                                CONVERT(date, dbo.HoaDon.Ngay_Thanh_Toan) as'NgayThanhToan', dbo.HoaDon.Tong_Gia
                  FROM         dbo.NhanVien INNER JOIN
                               dbo.HoaDon ON dbo.NhanVien.ID_Nhan_Vien = dbo.HoaDon.ID_Nhan_Vien
                  		where HoaDon.Trang_Thai=1 and NhanVien.Ma_NV =?
                       order by HoaDon.Ngay_Thanh_Toan desc
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maNV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien_Response nv = NhanVien_Response.builder()
                        .maNhanVien(rs.getString(1))
                        .maHoaDon(rs.getString(2))
                        .tenKhachHang(rs.getString(3))
                        .sdtKhachHang(rs.getString(4))
                        .ngayTao(rs.getDate(5))
                        .ngayThanhToan(rs.getDate(6))
                        .tongGia(rs.getLong(7))
                        .build();
                listNV.add(nv);
            }
            return listNV;
        } catch (Exception e) {
            return null;
        }
    }
}
