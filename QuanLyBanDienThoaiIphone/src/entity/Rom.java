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
@Builder 
public class Rom {
    private int id_Rom; 
    private String Rom; 
    private int trangThai; 
}
