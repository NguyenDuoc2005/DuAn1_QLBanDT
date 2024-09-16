package ultil;

import java.text.DecimalFormat;


public class ChuyenDoi {
    // chuyển đổi tiền 
    public String ChuyenDoiTien(float tien){
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedMoney = formatter.format(tien);
        return formattedMoney +" VNĐ";
    }
    public String ChuyenDoiTienHDCT(float tien){
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedMoney = formatter.format(tien);
        return formattedMoney +" VND";
    }
}
