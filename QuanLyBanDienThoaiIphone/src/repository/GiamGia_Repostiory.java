package repository;

import config.DBConnect;
import entity.GiamGia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GiamGia_Repostiory {

    private static Connection conn;

    public GiamGia_Repostiory() {
        conn = DBConnect.getConnection();
    }

    public ArrayList<GiamGia> getAll() {
        ArrayList<GiamGia> listGG = new ArrayList<>();
        listGG.clear();

        String query = """
                   SELECT ID_Giam_Gia, Ma_Giam_Gia, Ten_Chuong_Trinh, Mo_Ta, Ngay_Tao, Ngay_Bat_Dau, Ngay_Ket_Thuc, 
                       So_luong, Kieu_Giam, Gia_tri_DH_Toi_Thieu, Muc_Giam_Gia, Muc_Giam_Gia_Toi_Da, Trang_Thai, ID_Nhan_Vien 
                       FROM GiamGia where Trang_Thai = 0""";
        try (
                PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                GiamGia gg = new GiamGia();
                gg.setID_Giam_Gia(rst.getInt(1));
                gg.setMa_Giam_Gia(rst.getString(2));
                gg.setTen_Chuong_Trinh(rst.getString(3));
                gg.setMo_Ta(rst.getString(4));
                gg.setNgay_Tao(rst.getDate(5));
                gg.setNgay_Bat_Dau(rst.getDate(6));
                gg.setNgay_Ket_Thuc(rst.getDate(7));
                gg.setSo_luong(rst.getInt(8));
                gg.setKieu_Giam(rst.getInt(9));
                gg.setGia_tri_DH_Toi_Thieu(rst.getFloat(10));
                gg.setMuc_Giam_Gia(rst.getInt(11));
                gg.setMuc_Giam_Gia_Toi_Da(rst.getInt(12));
                gg.setTrang_Thai(rst.getInt(13));
                gg.setID_Nhan_Vien(rst.getInt(14));
                listGG.add(gg);
            }
            return listGG;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGG;
    }

    public ArrayList<GiamGia> getAll_DaXoa() {
        ArrayList<GiamGia> listGG = new ArrayList<>();
        listGG.clear();

        String query = """
                   SELECT ID_Giam_Gia, Ma_Giam_Gia, Ten_Chuong_Trinh, Mo_Ta, Ngay_Tao, Ngay_Bat_Dau, Ngay_Ket_Thuc, 
                       So_luong, Kieu_Giam, Gia_tri_DH_Toi_Thieu, Muc_Giam_Gia, Muc_Giam_Gia_Toi_Da, Trang_Thai, ID_Nhan_Vien 
                       FROM GiamGia where Trang_Thai = 1""";
        try (
                PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                GiamGia gg = new GiamGia();
                gg.setID_Giam_Gia(rst.getInt(1));
                gg.setMa_Giam_Gia(rst.getString(2));
                gg.setTen_Chuong_Trinh(rst.getString(3));
                gg.setMo_Ta(rst.getString(4));
                gg.setNgay_Tao(rst.getDate(5));
                gg.setNgay_Bat_Dau(rst.getDate(6));
                gg.setNgay_Ket_Thuc(rst.getDate(7));
                gg.setSo_luong(rst.getInt(8));
                gg.setKieu_Giam(rst.getInt(9));
                gg.setGia_tri_DH_Toi_Thieu(rst.getFloat(10));
                gg.setMuc_Giam_Gia(rst.getInt(11));
                gg.setMuc_Giam_Gia_Toi_Da(rst.getInt(12));
                gg.setTrang_Thai(rst.getInt(13));
                gg.setID_Nhan_Vien(rst.getInt(14));
                listGG.add(gg);
            }
            return listGG;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGG;
    }

    public ArrayList<GiamGia> search(String keyword) {
        ArrayList<GiamGia> listGG = new ArrayList<>();
        listGG.clear();

        String query = """
                   SELECT ID_Giam_Gia, Ma_Giam_Gia, Ten_Chuong_Trinh, Mo_Ta, Ngay_Tao, Ngay_Bat_Dau, Ngay_Ket_Thuc, 
                       So_luong, Kieu_Giam, Gia_tri_DH_Toi_Thieu, Muc_Giam_Gia, Muc_Giam_Gia_Toi_Da, Trang_Thai, ID_Nhan_Vien 
                       FROM GiamGia where Trang_Thai = 0""";
        if (keyword.length() > 0) {
            query += """
                 AND (Ma_Giam_Gia like ?
                    OR Ten_Chuong_Trinh LIKE ?
                     )
                 """;
        }

        try (
                PreparedStatement ps = conn.prepareStatement(query)) {
            if (keyword != null && !keyword.isEmpty()) {
                String value = "%" + keyword + "%";
                ps.setString(1, value);
                ps.setString(2, value);
            }
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                GiamGia gg = new GiamGia();
                gg.setID_Giam_Gia(rst.getInt(1));
                gg.setMa_Giam_Gia(rst.getString(2));
                gg.setTen_Chuong_Trinh(rst.getString(3));
                gg.setMo_Ta(rst.getString(4));
                gg.setNgay_Tao(rst.getDate(5));
                gg.setNgay_Bat_Dau(rst.getDate(6));
                gg.setNgay_Ket_Thuc(rst.getDate(7));
                gg.setSo_luong(rst.getInt(8));
                gg.setKieu_Giam(rst.getInt(9));
                gg.setGia_tri_DH_Toi_Thieu(rst.getFloat(10));
                gg.setMuc_Giam_Gia(rst.getInt(11));
                gg.setMuc_Giam_Gia_Toi_Da(rst.getInt(12));
                gg.setTrang_Thai(rst.getInt(13));
                gg.setID_Nhan_Vien(rst.getInt(14));
                listGG.add(gg);
            }
            return listGG;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGG;
    }

    public ArrayList<GiamGia> getAll_DangDienRa() {
        ArrayList<GiamGia> listGG = new ArrayList<>();
        String sql = """
                SELECT ID_Giam_Gia, Ma_Giam_Gia, Ten_Chuong_Trinh, Mo_Ta, Ngay_Tao, Ngay_Bat_Dau, Ngay_Ket_Thuc, 
                    So_luong, Kieu_Giam, Gia_tri_DH_Toi_Thieu, Muc_Giam_Gia, Muc_Giam_Gia_Toi_Da, Trang_Thai, ID_Nhan_Vien 
                FROM GiamGia
                WHERE Trang_Thai = 0 AND CAST(GETDATE() AS DATE) >= Ngay_Bat_Dau AND CAST(GETDATE() AS DATE) <= Ngay_Ket_Thuc
                 """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiamGia gg = new GiamGia();
                gg.setID_Giam_Gia(rs.getInt("ID_Giam_Gia"));
                gg.setMa_Giam_Gia(rs.getString("Ma_Giam_Gia"));
                gg.setTen_Chuong_Trinh(rs.getString("Ten_Chuong_Trinh"));
                gg.setMo_Ta(rs.getString("Mo_Ta"));
                gg.setNgay_Tao(rs.getDate("Ngay_Tao"));
                gg.setNgay_Bat_Dau(rs.getDate("Ngay_Bat_Dau"));
                gg.setNgay_Ket_Thuc(rs.getDate("Ngay_Ket_Thuc"));
                gg.setSo_luong(rs.getInt("So_luong"));
                gg.setKieu_Giam(rs.getInt("Kieu_Giam"));
                gg.setGia_tri_DH_Toi_Thieu(rs.getFloat("Gia_tri_DH_Toi_Thieu"));
                gg.setMuc_Giam_Gia(rs.getInt("Muc_Giam_Gia"));
                gg.setMuc_Giam_Gia_Toi_Da(rs.getInt("Muc_Giam_Gia_Toi_Da"));
                gg.setTrang_Thai(rs.getInt("Trang_Thai"));
                gg.setID_Nhan_Vien(rs.getInt("ID_Nhan_Vien"));
                listGG.add(gg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGG;
    }

    public ArrayList<GiamGia> getAll_SapDienRa() {
        ArrayList<GiamGia> listGG = new ArrayList<>();
        String sql = """
                SELECT ID_Giam_Gia, Ma_Giam_Gia, Ten_Chuong_Trinh, Mo_Ta, Ngay_Tao, Ngay_Bat_Dau, Ngay_Ket_Thuc, 
                    So_luong, Kieu_Giam, Gia_tri_DH_Toi_Thieu, Muc_Giam_Gia, Muc_Giam_Gia_Toi_Da, Trang_Thai, ID_Nhan_Vien 
                FROM GiamGia
                WHERE Trang_Thai = 0 AND CAST(GETDATE() AS DATE) < Ngay_Bat_Dau
                 """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiamGia gg = new GiamGia();
                gg.setID_Giam_Gia(rs.getInt("ID_Giam_Gia"));
                gg.setMa_Giam_Gia(rs.getString("Ma_Giam_Gia"));
                gg.setTen_Chuong_Trinh(rs.getString("Ten_Chuong_Trinh"));
                gg.setMo_Ta(rs.getString("Mo_Ta"));
                gg.setNgay_Tao(rs.getDate("Ngay_Tao"));
                gg.setNgay_Bat_Dau(rs.getDate("Ngay_Bat_Dau"));
                gg.setNgay_Ket_Thuc(rs.getDate("Ngay_Ket_Thuc"));
                gg.setSo_luong(rs.getInt("So_luong"));
                gg.setKieu_Giam(rs.getInt("Kieu_Giam"));
                gg.setGia_tri_DH_Toi_Thieu(rs.getFloat("Gia_tri_DH_Toi_Thieu"));
                gg.setMuc_Giam_Gia(rs.getInt("Muc_Giam_Gia"));
                gg.setMuc_Giam_Gia_Toi_Da(rs.getInt("Muc_Giam_Gia_Toi_Da"));
                gg.setTrang_Thai(rs.getInt("Trang_Thai"));
                gg.setID_Nhan_Vien(rs.getInt("ID_Nhan_Vien"));
                listGG.add(gg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGG;
    }

    public ArrayList<GiamGia> getAll_DaKetThuc() {
        ArrayList<GiamGia> listGG = new ArrayList<>();
        String sql = """
                SELECT ID_Giam_Gia, Ma_Giam_Gia, Ten_Chuong_Trinh, Mo_Ta, Ngay_Tao, Ngay_Bat_Dau, Ngay_Ket_Thuc, 
                    So_luong, Kieu_Giam, Gia_tri_DH_Toi_Thieu, Muc_Giam_Gia, Muc_Giam_Gia_Toi_Da, Trang_Thai, ID_Nhan_Vien 
                FROM GiamGia
                WHERE Trang_Thai = 0 AND CAST(GETDATE() AS DATE) > Ngay_Ket_Thuc
                 """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiamGia gg = new GiamGia();
                gg.setID_Giam_Gia(rs.getInt("ID_Giam_Gia"));
                gg.setMa_Giam_Gia(rs.getString("Ma_Giam_Gia"));
                gg.setTen_Chuong_Trinh(rs.getString("Ten_Chuong_Trinh"));
                gg.setMo_Ta(rs.getString("Mo_Ta"));
                gg.setNgay_Tao(rs.getDate("Ngay_Tao"));
                gg.setNgay_Bat_Dau(rs.getDate("Ngay_Bat_Dau"));
                gg.setNgay_Ket_Thuc(rs.getDate("Ngay_Ket_Thuc"));
                gg.setSo_luong(rs.getInt("So_luong"));
                gg.setKieu_Giam(rs.getInt("Kieu_Giam"));
                gg.setGia_tri_DH_Toi_Thieu(rs.getFloat("Gia_tri_DH_Toi_Thieu"));
                gg.setMuc_Giam_Gia(rs.getInt("Muc_Giam_Gia"));
                gg.setMuc_Giam_Gia_Toi_Da(rs.getInt("Muc_Giam_Gia_Toi_Da"));
                gg.setTrang_Thai(rs.getInt("Trang_Thai"));
                gg.setID_Nhan_Vien(rs.getInt("ID_Nhan_Vien"));
                listGG.add(gg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGG;
    }

    public boolean add(GiamGia gg, int idNhanVien) {
        int check = 0;

        String queryInsert = """
    INSERT INTO GiamGia (Ten_Chuong_Trinh, Ma_Giam_Gia, Kieu_Giam, Muc_Giam_Gia, Muc_Giam_Gia_Toi_Da, Gia_Tri_DH_Toi_Thieu, Ngay_Bat_Dau, Ngay_Ket_Thuc, ID_Nhan_Vien) 
    VALUES (?,?,?,?,?,?,?,?,?)
    """;

        try (PreparedStatement ps = conn.prepareStatement(queryInsert)) {
            ps.setString(1, gg.getTen_Chuong_Trinh());
            ps.setString(2, gg.getMa_Giam_Gia());
            ps.setInt(3, gg.getKieu_Giam());
            ps.setInt(4, gg.getMuc_Giam_Gia());
            if (gg.getKieu_Giam() == 0) {
                ps.setInt(5, gg.getMuc_Giam_Gia_Toi_Da());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            ps.setFloat(6, gg.getGia_tri_DH_Toi_Thieu());
            ps.setDate(7, new java.sql.Date(gg.getNgay_Bat_Dau().getTime()));
            ps.setDate(8, new java.sql.Date(gg.getNgay_Ket_Thuc().getTime()));
            ps.setInt(9, idNhanVien);

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean update(GiamGia gg, String ma_GG, int idNhanVien) {
        int check = 0;

        String queryUpadate = """
        UPDATE GiamGia 
        SET Ten_Chuong_Trinh = ?, Ma_Giam_Gia = ?, Kieu_Giam = ?, Muc_Giam_Gia = ?, Muc_Giam_Gia_Toi_Da = ?, Gia_Tri_DH_Toi_Thieu = ?, Ngay_Bat_Dau = ?, Ngay_Ket_Thuc = ?, ID_Nhan_Vien = ?
        WHERE Ma_Giam_Gia = ?
    """;

        try (PreparedStatement ps = conn.prepareStatement(queryUpadate)) {
            ps.setString(1, gg.getTen_Chuong_Trinh());
            ps.setString(2, gg.getMa_Giam_Gia());
            ps.setInt(3, gg.getKieu_Giam());
            ps.setInt(4, gg.getMuc_Giam_Gia());
            if (gg.getKieu_Giam() == 0) {
                ps.setInt(5, gg.getMuc_Giam_Gia_Toi_Da());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            ps.setFloat(6, gg.getGia_tri_DH_Toi_Thieu());
            ps.setDate(7, new java.sql.Date(gg.getNgay_Bat_Dau().getTime()));
            ps.setDate(8, new java.sql.Date(gg.getNgay_Ket_Thuc().getTime()));
            ps.setInt(9, idNhanVien);
            ps.setString(10, ma_GG);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return check > 0;
    }

    public boolean Update_TrangThaiGG(GiamGia nv, int trangThai) {
        int check = 0;
        ArrayList<GiamGia> listGG = new ArrayList<>();
        String sql = """
                      update GiamGia set Trang_Thai = ? where Ma_Giam_Gia = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, trangThai);
            ps.setString(2, nv.getMa_Giam_Gia());
            check = ps.executeUpdate();

        } catch (Exception e) {

        }
        return check > 0;
    }

}
