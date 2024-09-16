package repository;

import config.DBConnect;
import entity.CPU;
import entity.Ram;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CPU_Repository {
    
    public ArrayList<CPU> getAll(){        
        ArrayList<CPU> list = new ArrayList<>(); 
        list.clear();
        String query = """
                    SELECT [ID_CPU]
                    ,[CPU]
                    ,[Trang_Thai]
                     FROM [dbo].[CPU]
                     where Trang_Thai=0
                     order by ID_CPU desc
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();            
            while (rs.next()) {
                CPU cpu = CPU.builder()
                    .id_CPU(rs.getInt(1))
                    .CPU(rs.getString(2))
                    .trangThai(rs.getInt(3))
                    .build(); 
               list.add(cpu);
            }            
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    
     public boolean Them(CPU c){
         int check =0;
        ArrayList<CPU> list = new ArrayList<>(); 
        String query = """
                     INSERT INTO [dbo].[CPU]
                                ([CPU])
                          VALUES
                                (?)
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      ps.setString(1,c.getCPU());                        
          check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public boolean Sua(CPU c){
         int check =0;
        ArrayList<CPU> list = new ArrayList<>(); 
        String query = """
                     UPDATE [dbo].[CPU]
                         SET [CPU] = ?
                       WHERE ID_CPU = ?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                      ps.setString(1,c.getCPU()); 
                      ps.setInt(2,c.getId_CPU());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public boolean Xoa(CPU c){
         int check =0;
        ArrayList<CPU> list = new ArrayList<>(); 
        String query = """
                    update CPU
                    set Trang_Thai =1
                    where ID_CPU  = ?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {   
                      ps.setInt(1,c.getId_CPU());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
     
     public ArrayList<CPU> getAllXoa(){        
        ArrayList<CPU> list = new ArrayList<>(); 
        list.clear();
        String query = """
                        SELECT [ID_CPU]
                        ,[CPU]
                        ,[Trang_Thai]
                         FROM [dbo].[CPU]
                         where Trang_Thai=1
                         order by ID_CPU desc 
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();            
            while (rs.next()) {
                CPU cpu = CPU.builder()
                    .id_CPU(rs.getInt(1))
                    .CPU(rs.getString(2))
                    .trangThai(rs.getInt(3))
                    .build(); 
               list.add(cpu);
            }            
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
     
     public boolean khoiPhuc(CPU c){
         int check =0;
        ArrayList<CPU> list = new ArrayList<>(); 
        String query = """
                    update CPU
                    set Trang_Thai =0
                    where ID_CPU  = ?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {   
                      ps.setInt(1,c.getId_CPU());
                    check =  ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check >0;
    }
}
     

