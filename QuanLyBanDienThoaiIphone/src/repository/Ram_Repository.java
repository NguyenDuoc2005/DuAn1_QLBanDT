
package repository;

import config.DBConnect;
import entity.Ram;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Ram_Repository {
    public ArrayList<Ram> getAll(){        
        ArrayList<Ram> list = new ArrayList<>(); 
        list.clear();
        String query = """
                    SELECT [ID_Ram]
                           ,[Ram]
                       FROM [dbo].[Ram]
                       where Trang_Thai =0 
                       order by ID_Ram desc
                        
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();            
            while (rs.next()) {
               Ram ram = Ram.builder()
                    .id_Ram(rs.getInt(1))
                    .Ram(rs.getString(2))
                    .build(); 
               list.add(ram);
            }            
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    public ArrayList<Ram> getAllCu(){        
        ArrayList<Ram> list = new ArrayList<>(); 
        list.clear();
        String query = """
                    SELECT [ID_Ram]
                           ,[Ram]
                       FROM [dbo].[Ram]
                       where Trang_Thai =1
                       order by ID_Ram desc
                        
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();            
            while (rs.next()) {
               Ram ram = Ram.builder()
                    .id_Ram(rs.getInt(1))
                    .Ram(rs.getString(2))
                    .build(); 
               list.add(ram);
            }            
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    
     public boolean Them(Ram r){
         int check =0;
        ArrayList<Ram> list = new ArrayList<>(); 
        String query = """
                     INSERT INTO [dbo].[Ram]
                                ([Ram])
                          VALUES
                                (?)
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      ps.setString(1,r.getRam());                        
          check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public boolean Sua(Ram r){
         int check =0;
        ArrayList<Ram> list = new ArrayList<>(); 
        String query = """
                      UPDATE [dbo].[Ram]
                         SET [Ram] = ?
                       WHERE ID_Ram =?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      ps.setString(1,r.getRam()); 
                      ps.setInt(2,r.getId_Ram());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public boolean Xoa(Ram r){
        int check =0;
        ArrayList<Ram> list = new ArrayList<>(); 
        String query = """
                      UPDATE [dbo].[Ram]
                         SET Trang_Thai = 1
                       WHERE ID_Ram =?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
   
                      ps.setInt(1,r.getId_Ram());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     public boolean khoiPhuc(Ram r){
        int check =0;
        ArrayList<Ram> list = new ArrayList<>(); 
        String query = """
                      UPDATE [dbo].[Ram]
                         SET Trang_Thai = 0
                       WHERE ID_Ram =?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                
                      ps.setInt(1,r.getId_Ram());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
}
