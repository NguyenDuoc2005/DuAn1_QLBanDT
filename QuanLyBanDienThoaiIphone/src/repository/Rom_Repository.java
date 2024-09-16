package repository;

import config.DBConnect;
import entity.Ram;
import entity.Rom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Rom_Repository {
    public ArrayList<Rom> getAll(){        
        ArrayList<Rom> list = new ArrayList<>(); 
        list.clear();
        String query = """
                    SELECT [ID_Rom]
                          ,[Rom]
                      FROM [dbo].[Rom]
                       where Trang_Thai = 0
                      order by ID_Rom desc                        
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();            
            while (rs.next()) {
               Rom rom = Rom.builder()
                    .id_Rom(rs.getInt(1))
                    .Rom(rs.getString(2))
                    .build(); 
               list.add(rom);
            }            
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    public ArrayList<Rom> getAllCu(){        
        ArrayList<Rom> list = new ArrayList<>(); 
        list.clear();
        String query = """
                    SELECT [ID_Rom]
                          ,[Rom]
                      FROM [dbo].[Rom]
                       where Trang_Thai = 1
                      order by ID_Rom desc                        
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();            
            while (rs.next()) {
               Rom rom = Rom.builder()
                    .id_Rom(rs.getInt(1))
                    .Rom(rs.getString(2))
                    .build(); 
               list.add(rom);
            }            
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    
     public boolean Them(Rom r){
         int check =0;
        ArrayList<Rom> list = new ArrayList<>(); 
        String query = """
                     INSERT INTO [dbo].[Rom]
                                ([Rom])
                          VALUES
                                (?)
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      ps.setString(1,r.getRom());                        
          check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public boolean Sua(Rom r){
         int check =0;
        ArrayList<Rom> list = new ArrayList<>(); 
        String query = """
                      UPDATE [dbo].[Rom]
                         SET [Rom] = ?
                       WHERE ID_Rom = ?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      ps.setString(1,r.getRom()); 
                      ps.setInt(2,r.getId_Rom());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public boolean Xoa(Rom r){
         int check =0;
        ArrayList<Rom> list = new ArrayList<>(); 
        String query = """
                      UPDATE [dbo].[Rom]
                         SET Trang_Thai = 1
                       WHERE ID_Rom = ?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
             
                      ps.setInt(1,r.getId_Rom());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     public boolean khoiPhuc(Rom r){
         int check =0;
        ArrayList<Rom> list = new ArrayList<>(); 
        String query = """
                      UPDATE [dbo].[Rom]
                         SET Trang_Thai = 0
                       WHERE ID_Rom = ?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
             
                      ps.setInt(1,r.getId_Rom());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
}
