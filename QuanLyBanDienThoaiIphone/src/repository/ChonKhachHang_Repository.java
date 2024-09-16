package repository;

import config.DBConnect;
import entity.ChucVu;
import entity.KhachHang;
import entity.PhanLoai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import response.NhanVien_Response;

public class ChonKhachHang_Repository {
    public ArrayList<KhachHang> getAll_KhachHang() {
        ArrayList<KhachHang> listKH = new ArrayList<>();
        String sql = """
                     SELECT [ID_Khach_Hang]
                           ,[Ma_Khach_Hang],[Ten],[SĐT]
                           ,[Email],[Gioi_Tinh],[Dia_Chi],[Trang_Thai]
                           FROM [dbo].[KhachHang]   order by ID_Khach_Hang desc
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = KhachHang.builder()
                        .idKH(rs.getInt(1))
                        .maKh(rs.getString(2))
                        .ten(rs.getString(3))
                        .sdt(rs.getString(4))
                        .email(rs.getString(5))
                        .sex(rs.getBoolean(6))
                        .diaChi(rs.getString(7))
                        .trangThai(rs.getInt(8))
                        .build();
            listKH.add(kh);
        }
        }catch(Exception e) {
            
        }
        return listKH;
    }
    
    public ArrayList<KhachHang> searchKH(String keyword) {
        String sql = """
                   SELECT [ID_Khach_Hang]
                         ,[Ma_Khach_Hang]
                         ,[Ten]
                         ,[SĐT]
                         ,[Email]
                         ,[Gioi_Tinh]
                         ,[Dia_Chi]
                         ,[Trang_Thai]
                     FROM [dbo].[KhachHang]
                   	
                   		
                     """;
        if (keyword.length() > 0) {
            sql += """
                 where (Ma_Khach_Hang like ?
                                    	or Ten like ?
                                    	or SĐT like ?
                                    	or Email like ? 
                                    	or Dia_Chi like  ? 
                                    	)
                 """;
        }
        ArrayList<KhachHang> listKH = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            int index = 1; // vị trí của dấu hỏi chấm đầu tiên
            String value = "%" + keyword + "%";
            if (keyword.length() > 0) {
                // search 1 ô trong nhiều dòng 
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);

            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = KhachHang.builder()
                        .idKH(rs.getInt(1))
                        .maKh(rs.getString(2))
                        .ten(rs.getString(3))
                        .sdt(rs.getString(4))
                        .email(rs.getString(5))
                        .sex(rs.getBoolean(6))
                        .diaChi(rs.getString(7))
                        .trangThai(rs.getInt(8))
                        .build();
            listKH.add(kh);
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return listKH;
    }
    
    
    // thêm khách hàng đã chọn vào hóa đơn đã click 
    public boolean themID_KH_Vao_HD(int id_KH , int id_HD) {
        int check = 0;
        String query = """
                      update HoaDon 
                      set ID_Khach_Hang = ?
                      where ID_Hoa_Don =?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1,id_KH);
            ps.setInt(2,id_HD);
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
     // Phương thức này thêm thông tin khách hàng vào bảng KhachHang
    public boolean Add_ThongTin_KH(KhachHang kh) {
        int check = 0;
        String sql = "INSERT INTO KhachHang (Ma_Khach_Hang, Ten, SĐT, Email, Gioi_Tinh, Dia_Chi) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kh.getMaKh());
            ps.setString(2, kh.getTen());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getEmail());
            ps.setBoolean(5, kh.isSex());
            ps.setString(6, kh.getDiaChi());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
}
