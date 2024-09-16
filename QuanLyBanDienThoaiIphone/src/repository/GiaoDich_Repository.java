/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import response.GiaoDich_Response;

/**
 *
 * @author FPT
 */
public class GiaoDich_Repository {
    
    public ArrayList<GiaoDich_Response> getAll_Gd(String maKh){
        ArrayList<GiaoDich_Response> listGD = new ArrayList<>();
        String sql = """
                     SELECT     dbo.KhachHang.Ma_Khach_Hang, dbo.KhachHang.Ten, dbo.KhachHang.SĐT, dbo.HoaDon.Ngay_Tao, dbo.HoaDon.Ngay_Thanh_Toan, dbo.HoaDon.Ma_Hoa_Don, 
                                           dbo.HoaDon.Tong_Gia,dbo.HoaDon.Trang_Thai
                     FROM         dbo.KhachHang INNER JOIN
                                           dbo.HoaDon ON dbo.KhachHang.ID_Khach_Hang = dbo.HoaDon.ID_Khach_Hang WHERE  dbo.KhachHang.Ma_Khach_Hang = ?
                     """;
        try(Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
                ){
            ps.setString(1, maKh );
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                GiaoDich_Response gd = GiaoDich_Response.builder()
                        
                        .maKh(rs.getString(1))
                        .ten(rs.getString(2))
                        .sdt(rs.getString(3))
                        .ngayTao(rs.getDate(4))
                        .ngayTT(rs.getDate(5))
                        .maHD(rs.getString(6))
                        .tong(rs.getLong(7))
                        .trangThai(rs.getInt(8))
                        .build();
                listGD.add(gd);
        }
            
        }catch(Exception e){
             e.printStackTrace(System.out);
    }
        return listGD;
}
    
    
}
