package repository;

import config.DBConnect;
import entity.DungLuongPin;
import entity.Ram;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DungLuongPin_Repository {

    public ArrayList<DungLuongPin> getAll() {
        ArrayList<DungLuongPin> list = new ArrayList<>();
        list.clear();
        String query = """
                   SELECT [ID_Dung_Luong_Pin]
                    ,[Dung_Luong_Pin]
                    FROM [dbo].[DungLuongPin]
                   where Trang_Thai = 0
                    order by ID_Dung_Luong_Pin desc
                        
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DungLuongPin pin = DungLuongPin
                        .builder()
                        .id_DungLuongPin(rs.getInt(1))
                        .DungLuongPin(rs.getString(2))
                        .build();
                list.add(pin);
            }
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public ArrayList<DungLuongPin> getAllXoa() {
        ArrayList<DungLuongPin> list = new ArrayList<>();
        list.clear();
        String query = """
                   SELECT [ID_Dung_Luong_Pin]
                    ,[Dung_Luong_Pin]
                    FROM [dbo].[DungLuongPin]
                   where Trang_Thai = 1
                    order by ID_Dung_Luong_Pin desc
                        
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DungLuongPin pin = DungLuongPin
                        .builder()
                        .id_DungLuongPin(rs.getInt(1))
                        .DungLuongPin(rs.getString(2))
                        .build();
                list.add(pin);
            }
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public boolean Them(DungLuongPin p) {
        int check = 0;
        ArrayList<Ram> list = new ArrayList<>();
        String query = """
                     INSERT INTO [dbo].[DungLuongPin]
                                ([Dung_Luong_Pin])
                          VALUES
                                (?)
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, p.getDungLuongPin());
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean Sua(DungLuongPin p) {
        int check = 0;
        ArrayList<DungLuongPin> list = new ArrayList<>();
        String query = """
                      UPDATE [dbo].[DungLuongPin]
                         SET [Dung_Luong_Pin] = ?
                       WHERE ID_Dung_Luong_Pin = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, p.getDungLuongPin());
            ps.setInt(2, p.getId_DungLuongPin());
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean Xoa(DungLuongPin p ) {
        int check = 0;
        ArrayList<DungLuongPin> list = new ArrayList<>();
        String query = """
                      UPDATE [dbo].[DungLuongPin]
                         SET Trang_Thai = 1
                       WHERE ID_Dung_Luong_Pin = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, p.getId_DungLuongPin());
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public boolean khoiPhuc(DungLuongPin p ) {
        int check = 0;
        ArrayList<DungLuongPin> list = new ArrayList<>();
        String query = """
                      UPDATE [dbo].[DungLuongPin]
                         SET Trang_Thai = 0
                       WHERE ID_Dung_Luong_Pin = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, p.getId_DungLuongPin());
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
}
