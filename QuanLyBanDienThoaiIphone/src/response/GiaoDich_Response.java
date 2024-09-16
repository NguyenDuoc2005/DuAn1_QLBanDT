/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor   // contructor full tham so 
@NoArgsConstructor // contructor k tham so 
@Getter
@Setter
@ToString
@Builder // contructor tùy chọn tham số
/**
 *
 * @author FPT
 */
public class GiaoDich_Response {
    private int idKH;
    private String maKh;
    private String ten;
    private String sdt;
    private Date ngayTao;
    private Date ngayTT;
    private String maHD;
    private long tong;
    private int trangThai;
}
