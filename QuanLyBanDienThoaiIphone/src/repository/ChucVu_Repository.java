package repository;

import config.DBConnect;
import entity.ChucVu;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChucVu_Repository {
   public ArrayList<ChucVu> getAll_CV() {
        ArrayList<ChucVu> listCV = new ArrayList<>();
        String sql = """
                     SELECT [ID_Chuc_Vu]
                           ,[Chuc_Vu]
                           ,[Trang_Thai]
                       FROM [dbo].[ChucVu]
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu cv = ChucVu.builder()
                        .id_ChucVu(rs.getInt(1))
                        .chucVu(rs.getString(2))
                        .trangThai(rs.getInt(3))
                        .build();
            listCV.add(cv);
        }
        }catch(Exception e) {
            
        }
        return listCV;
    }
   
   public ChucVu getChucVuByMa(boolean ma1){
        String query = """
                      SELECT [ID_Chuc_Vu]
                              ,[Chuc_Vu]
                              ,[Trang_Thai]
                              FROM [dbo].[ChucVu]
                       where Chuc_Vu = ?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            // Set gia tri cho dau hoi cham 
            ps.setObject(1, ma1);
            ResultSet rs = ps.executeQuery(); // Lay ket qua

            while (rs.next()) {
                ChucVu cv = ChucVu.builder()
                        .id_ChucVu(rs.getInt(1))
                        .chucVu(rs.getString(2))
                        .trangThai(rs.getInt(3))
                        .build();
                return cv;
            }
            
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    
}
