/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author FPT
 */
@AllArgsConstructor   // contructor full tham so 
@NoArgsConstructor // contructor k tham so 
@Getter
@Setter
@ToString
@Builder // contructor tùy chọn tham số
public class KhachHang {
    private int idKH;
    private String maKh;
    private String ten;
    private String sdt;
   private String diaChi;
   private String email;
   private int trangThai;
   private boolean sex;
   
}
