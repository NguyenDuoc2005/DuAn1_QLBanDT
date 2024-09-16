package repository;

import config.DBConnect;
import entity.MauSac;
import entity.Ram;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MauSac_Repository {
    public ArrayList<MauSac> getAll(){        
        ArrayList<MauSac> list = new ArrayList<>(); 
        list.clear();
        String query = """
                    SELECT [ID_Mau_Sac]
                     ,[Mau_Sac]
                    FROM [dbo].[MauSac]                    
                     where Trang_Thai = 0 
                     order by ID_Mau_Sac desc                      
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();            
            while (rs.next()) {
               MauSac ms = MauSac.builder()
                    .id_MauSac(rs.getInt(1))
                    .mauSac(rs.getString(2))
                    .build(); 
               list.add(ms);
            }            
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    public ArrayList<MauSac> getAllCu(){        
        ArrayList<MauSac> list = new ArrayList<>(); 
        list.clear();
        String query = """
                    SELECT [ID_Mau_Sac]
                     ,[Mau_Sac]
                    FROM [dbo].[MauSac]                    
                     where Trang_Thai = 1
                     order by ID_Mau_Sac desc                      
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();            
            while (rs.next()) {
               MauSac ms = MauSac.builder()
                    .id_MauSac(rs.getInt(1))
                    .mauSac(rs.getString(2))
                    .build(); 
               list.add(ms);
            }            
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
     public boolean Them(MauSac r){
         int check =0;
        ArrayList<MauSac> list = new ArrayList<>(); 
        String query = """
                     INSERT INTO [dbo].[MauSac]
                                ([Mau_Sac])
                          VALUES
                                (?)
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      ps.setString(1,r.getMauSac());                        
          check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public boolean Sua(MauSac r){
         int check =0;
        ArrayList<MauSac> list = new ArrayList<>(); 
        String query = """
                      UPDATE [dbo].[MauSac]
                         SET [Mau_Sac] = ?
                       WHERE ID_Mau_Sac = ?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      ps.setString(1,r.getMauSac()); 
                      ps.setInt(2,r.getId_MauSac());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public boolean Xoa(MauSac r){
         int check =0;
        ArrayList<MauSac> list = new ArrayList<>(); 
        String query = """
                      UPDATE [dbo].[MauSac]
                         SET Trang_Thai = 1
                       WHERE ID_Mau_Sac = ?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      
                      ps.setInt(1,r.getId_MauSac());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public boolean khoiPhuc(MauSac r){
         int check =0;
        ArrayList<MauSac> list = new ArrayList<>(); 
        String query = """
                      UPDATE [dbo].[MauSac]
                         SET Trang_Thai = 0
                       WHERE ID_Mau_Sac = ?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      
                      ps.setInt(1,r.getId_MauSac());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
}
