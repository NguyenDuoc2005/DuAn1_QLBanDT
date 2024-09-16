package ultil;
import form.KhachHang_form;
import java.awt.Component;
import javax.swing.JOptionPane;

public class MsgBox {
    // hien thi thong bao cho nguoi dung 
    // parent là cửa sổ chứa thông báo 
    // message là thông báo
    public static void showMessage(Component parent , String message ){
        JOptionPane.showMessageDialog(parent,message,"Quản Lý Bán Điện Thoại Iphone",
                JOptionPane.INFORMATION_MESSAGE);
    }
    // hien thi thong bao va yeu cau nguoi dung xac nhan
    // parent là cửa sổ chứa thông báo 
    // massage là câu trả lời yes / no 
    // return là kết quả nhận được tru  false 
    public static boolean showConfirm(Component parent , String message){
        int result = JOptionPane.showConfirmDialog(parent, message,
                "Quản Lý Bán Điện Thoại Iphone",JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return  result == JOptionPane.YES_OPTION;  
    }
    // hien thi thong bao yeu cau nhap du lieu 
    //parent là cửa sổ chứa thông báo 
    //massage là thông báo nhắc nhở nhập 
    // return là kết quả nhập được từ người sử dụng nhập vào 
    public static String showInput(Component parent , String message){
        return JOptionPane.showInputDialog(parent,message,
                "Quản Lý Bán Điện Thoại Iphone",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void alert(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void alert(KhachHang_form aThis, String vui_lòng_chọn_khách_hàng_để_sửa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
