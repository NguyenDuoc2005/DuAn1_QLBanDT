package repository;

import config.DBConnect;
import entity.PhanLoai;
import entity.Ram;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PhanLoai_Repository {

    public ArrayList<PhanLoai> getAll() {
        ArrayList<PhanLoai> list = new ArrayList<>();
        list.clear();
        String query = """
                    SELECT [ID_Phan_Loai]
                          ,[Phan_Loai]
                      FROM [dbo].[PhanLoai]
                       where Trang_Thai = 0
                      order by ID_Phan_Loai desc                   
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhanLoai pl = PhanLoai.builder()
                        .id_PhanLoai(rs.getInt(1))
                        .phanLoai(rs.getString(2))
                        .build();
                list.add(pl);
            }
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    public ArrayList<PhanLoai> getAllCu() {
        ArrayList<PhanLoai> list = new ArrayList<>();
        list.clear();
        String query = """
                    SELECT [ID_Phan_Loai]
                          ,[Phan_Loai]
                      FROM [dbo].[PhanLoai]
                       where Trang_Thai = 1
                      order by ID_Phan_Loai desc                   
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhanLoai pl = PhanLoai.builder()
                        .id_PhanLoai(rs.getInt(1))
                        .phanLoai(rs.getString(2))
                        .build();
                list.add(pl);
            }
            return list;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean Them(PhanLoai r) {
        int check = 0;
        ArrayList<PhanLoai> list = new ArrayList<>();
        String query = """
                     INSERT INTO [dbo].[PhanLoai]
                                ([Phan_Loai])
                          VALUES
                                (?)
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, r.getPhanLoai());
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean Sua(PhanLoai r) {
        int check = 0;
        ArrayList<PhanLoai> list = new ArrayList<>();
        String query = """
                      UPDATE [dbo].[PhanLoai]
                         SET [Phan_Loai] = ?
                       WHERE ID_Phan_Loai = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, r.getPhanLoai());
            ps.setInt(2, r.getId_PhanLoai());
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean Xoa(PhanLoai r) {
        int check = 0;
        ArrayList<PhanLoai> list = new ArrayList<>();
        String query = """
                      UPDATE [dbo].[PhanLoai]
                         SET Trang_Thai = 1 
                       WHERE ID_Phan_Loai = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, r.getId_PhanLoai());
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean khoiPhuc(PhanLoai r) {
        int check = 0;
        ArrayList<PhanLoai> list = new ArrayList<>();
        String query = """
                      UPDATE [dbo].[PhanLoai]
                         SET Trang_Thai = 0
                       WHERE ID_Phan_Loai = ?
                      """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, r.getId_PhanLoai());
            check = ps.executeUpdate();
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
}
