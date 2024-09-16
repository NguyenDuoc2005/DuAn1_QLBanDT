package repository;

import config.DBConnect;
import entity.KichThuoc;
import entity.Ram;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class KichThuocRepository {
    public ArrayList<KichThuoc> getAll(){        
        ArrayList<KichThuoc> list = new ArrayList<>(); 
        list.clear();
        String query = """
                  SELECT [ID_Kich_Thuoc]
                             ,[Kich_Thuoc]
                         FROM [dbo].[KichThuoc]
                       where Trang_Thai = 0
                         order by ID_Kich_Thuoc desc
                        
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();            
            while (rs.next()) {
                KichThuoc kt = KichThuoc.builder()
                    .id_KichThuoc(rs.getInt(1))
                    .kichThuoc(rs.getString(2))
                    .build(); 
               list.add(kt);
            }            
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    
     public boolean Them(KichThuoc r){
         int check =0;
        ArrayList<KichThuoc> list = new ArrayList<>(); 
        String query = """
                   INSERT INTO [dbo].[KichThuoc]
                                           ([Kich_Thuoc])
                                     VALUES
                                           (?)
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      ps.setString(1,r.getKichThuoc());                        
          check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public boolean Sua(KichThuoc r){
         int check =0;
        ArrayList<KichThuoc> list = new ArrayList<>(); 
        String query = """
                     UPDATE [dbo].[KichThuoc]
                          SET [Kich_Thuoc] = ?
                        WHERE ID_Kich_Thuoc = ?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      ps.setString(1,r.getKichThuoc()); 
                      ps.setInt(2,r.getId_KichThuoc());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public boolean Xoa(KichThuoc r){
         int check =0;
        ArrayList<KichThuoc> list = new ArrayList<>(); 
        String query = """
                     UPDATE [dbo].[KichThuoc]
                          SET Trang_Thai =1
                        WHERE ID_Kich_Thuoc = ?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                       
                      ps.setInt(1,r.getId_KichThuoc());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public boolean khoiPhuc(KichThuoc r){
         int check =0;
        ArrayList<KichThuoc> list = new ArrayList<>(); 
        String query = """
                     UPDATE [dbo].[KichThuoc]
                          SET Trang_Thai =0
                        WHERE ID_Kich_Thuoc = ?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                       
                      ps.setInt(1,r.getId_KichThuoc());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public ArrayList<KichThuoc> getAllCu(){        
        ArrayList<KichThuoc> list = new ArrayList<>(); 
        list.clear();
        String query = """
                  SELECT [ID_Kich_Thuoc]
                             ,[Kich_Thuoc]
                         FROM [dbo].[KichThuoc]
                       where Trang_Thai = 1
                         order by ID_Kich_Thuoc desc
                        
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();            
            while (rs.next()) {
                KichThuoc kt = KichThuoc.builder()
                    .id_KichThuoc(rs.getInt(1))
                    .kichThuoc(rs.getString(2))
                    .build(); 
               list.add(kt);
            }            
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
}
