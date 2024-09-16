package repository;

import config.DBConnect;
import entity.Ram;
import entity.XuatXu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class XuatXu_Repository {
     public ArrayList<XuatXu> getAll(){        
        ArrayList<XuatXu> list = new ArrayList<>(); 
        list.clear();
        String query = """
                    SELECT [ID_Xuat_Xu]
                          ,[Xuat_Xu]
                      FROM [dbo].[XuatXu]
                       where Trang_Thai = 0
                      order by ID_Xuat_Xu desc
                        
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();            
            while (rs.next()) {
               XuatXu x = XuatXu.builder()
                    .id_XuatXu(rs.getInt(1))
                    .xuatXu(rs.getString(2))
                    .build(); 
               list.add(x);
            }            
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
     public ArrayList<XuatXu> getAllCu(){        
        ArrayList<XuatXu> list = new ArrayList<>(); 
        list.clear();
        String query = """
                    SELECT [ID_Xuat_Xu]
                          ,[Xuat_Xu]
                      FROM [dbo].[XuatXu]
                       where Trang_Thai = 1
                      order by ID_Xuat_Xu desc
                        
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();            
            while (rs.next()) {
               XuatXu x = XuatXu.builder()
                    .id_XuatXu(rs.getInt(1))
                    .xuatXu(rs.getString(2))
                    .build(); 
               list.add(x);
            }            
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    
     public boolean Them(XuatXu r){
         int check =0;
        ArrayList<XuatXu> list = new ArrayList<>(); 
        String query = """
                     INSERT INTO [dbo].[XuatXu]
                                ([Xuat_Xu])
                          VALUES
                                (?)
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      ps.setString(1,r.getXuatXu());                        
          check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public boolean Sua(XuatXu r){
         int check =0;
        ArrayList<XuatXu> list = new ArrayList<>(); 
        String query = """
                      UPDATE [dbo].[XuatXu]
                         SET [Xuat_Xu] = ?
                       WHERE ID_Xuat_Xu =?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      ps.setString(1,r.getXuatXu()); 
                      ps.setInt(2,r.getId_XuatXu());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public boolean Xoa(XuatXu r){
         int check =0;
        ArrayList<XuatXu> list = new ArrayList<>(); 
        String query = """
                      UPDATE [dbo].[XuatXu]
                         SET Trang_Thai = 1
                       WHERE ID_Xuat_Xu =?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      
                      ps.setInt(1,r.getId_XuatXu());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     public boolean khoiPhuc(XuatXu r){
         int check =0;
        ArrayList<XuatXu> list = new ArrayList<>(); 
        String query = """
                      UPDATE [dbo].[XuatXu]
                         SET Trang_Thai = 0
                       WHERE ID_Xuat_Xu =?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      
                      ps.setInt(1,r.getId_XuatXu());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
}
