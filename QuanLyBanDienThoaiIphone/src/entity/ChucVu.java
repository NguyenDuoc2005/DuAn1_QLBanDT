package entity;

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
public class ChucVu {
    private int id_ChucVu; 
    private String chucVu; 
    private int trangThai; 
}
