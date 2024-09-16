package repository;

import config.DBConnect;
import entity.Imei;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import response.SanPham_Response;

public class Imei_Repository {
    public ArrayList<Imei> getAll_Imei() {
        ArrayList<Imei> listImei = new ArrayList<>();
        listImei.clear();
        String sql = """
                    SELECT [ID_Imei]
                          ,[Ma_Imei]
                          ,[Trang_Thai]
                          ,[ID_San_Pham]
                      FROM [dbo].[Imei]
                     """;
        try (Connection con = DBConnect.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Imei imei = Imei.builder()
                        .idImei(rs.getInt(1))
                        .maImei(rs.getString(2))
                        .trangThai(rs.getInt(3))
                        .idSanPham(rs.getInt(4))
                        .build();
                listImei.add(imei);
            }
            return listImei;
        } catch (Exception e) {
            return null;
        }

    }
    
    
}
