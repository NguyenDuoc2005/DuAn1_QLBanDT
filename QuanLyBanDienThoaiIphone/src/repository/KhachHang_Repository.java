package repository;

import config.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import entity.KhachHang;
import java.sql.SQLException;
import response.GiaoDich_Response;
import response.KhachHang_Response;

public class KhachHang_Repository {

    // Phương thức này lấy tất cả khách hàng từ bảng KhachHang
    public ArrayList<KhachHang_Response> getAll_Kh() {
    ArrayList<KhachHang_Response> listKH = new ArrayList<>();
    String sql = "SELECT ID_Khach_Hang, Ma_Khach_Hang, Ten, SĐT, Email, Gioi_Tinh, Dia_Chi FROM KhachHang";
    try (Connection con = DBConnect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            KhachHang_Response kh = KhachHang_Response.builder()
                    .idKH(rs.getInt(1))
                    .maKh(rs.getString(2))
                    .ten(rs.getString(3))
                    .sdt(rs.getString(4))
                    .email(rs.getString(5))
                    .sex(rs.getBoolean(6))
                    .diaChi(rs.getString(7))
                    .build();
            listKH.add(kh);
        }
    } catch (Exception e) {
        e.printStackTrace(System.out);
    }
    return listKH;
}
        public ArrayList<KhachHang_Response> getAll_KhachHang() {
    ArrayList<KhachHang_Response> listKH = new ArrayList<>();
    String sql = "SELECT ID_Khach_Hang, Ma_Khach_Hang, Ten, SĐT, Email, Gioi_Tinh, Dia_Chi FROM KhachHang";
    try (Connection con = DBConnect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            KhachHang_Response kh = KhachHang_Response.builder()
                    .idKH(rs.getInt(1))
                    .maKh(rs.getString(2))
                    .ten(rs.getString(3))
                    .sdt(rs.getString(4))
                    .email(rs.getString(5))
                    .sex(rs.getBoolean(6))
                    .diaChi(rs.getString(7))
                    .build();
            listKH.add(kh);
        }
        return listKH;
    } catch (Exception e) {
        return null;
    }
    
}

    // Phương thức này tìm kiếm khách hàng dựa trên từ khóa
    public ArrayList<KhachHang_Response> search(String keyword) {
        String sql = "SELECT ID_Khach_Hang, Ma_Khach_Hang, Ten, SĐT, Email, Gioi_Tinh, Dia_Chi FROM KhachHang";
        if (keyword != null && !keyword.isEmpty()) {
            sql += " WHERE Ma_Khach_Hang LIKE ? OR Ten LIKE ? OR SĐT LIKE ? OR Email LIKE ? OR Dia_Chi LIKE ?";
        }
        ArrayList<KhachHang_Response> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            if (keyword != null && !keyword.isEmpty()) {
                String value = "%" + keyword + "%";
                ps.setString(1, value);
                ps.setString(2, value);
                ps.setString(3, value);
                ps.setString(4, value);
                ps.setString(5, value);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang_Response kh = KhachHang_Response.builder()
                        .idKH(rs.getInt("ID_Khach_Hang"))
                        .maKh(rs.getString("Ma_Khach_Hang"))
                        .ten(rs.getString("Ten"))
                        .sdt(rs.getString("SĐT"))
                        .email(rs.getString("Email"))
                        .sex(rs.getBoolean("Gioi_Tinh"))
                        .diaChi(rs.getString("Dia_Chi"))
                        .build();
                lists.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    // Phương thức này thêm thông tin khách hàng vào bảng KhachHang
    public boolean Add_ThongTin_KH(KhachHang kh) {
        int check = 0;
        String sql = "INSERT INTO KhachHang (Ma_Khach_Hang, Ten, SĐT, Email, Gioi_Tinh, Dia_Chi) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kh.getMaKh());
            ps.setString(2, kh.getTen());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getEmail());
            ps.setBoolean(5, kh.isSex());
            ps.setString(6, kh.getDiaChi());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    // Phương thức này cập nhật thông tin khách hàng trong bảng KhachHang
    public void Update_ThongTin_KH(KhachHang kh) {
    String sql = "UPDATE KhachHang SET Ten = ?, SĐT = ?, Dia_Chi = ?, Email = ?, Gioi_Tinh = ? WHERE Ma_Khach_Hang = ?";
    
    try (Connection conn = DBConnect.getConnection(); // Đảm bảo phương thức Database.getConnection() trả về kết nối hợp lệ
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        // Thiết lập các tham số cho câu lệnh SQL
        pstmt.setString(1, kh.getTen());
        pstmt.setString(2, kh.getSdt());
        pstmt.setString(3, kh.getDiaChi());
        pstmt.setString(4, kh.getEmail());
        pstmt.setBoolean(5, kh.isSex()); // Lưu ý rằng kh.isSex() trả về boolean
        pstmt.setString(6, kh.getMaKh()); // Sử dụng MaKh để xác định bản ghi cần cập nhật
        
        // Thực thi câu lệnh cập nhật
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        // Xử lý lỗi nếu có
        throw new RuntimeException("Lỗi khi cập nhật khách hàng: " + e.getMessage());
    }
}

    public ArrayList<GiaoDich_Response> getTransactionsByMaKh(String maKh) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}