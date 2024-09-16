package view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import entity.ToanCuc_MaImei_QrCode;
import entity.ToanCuc_MaSP_QrCode;
import form.BanHang_form;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import repository.SanPham_BH_Pepository;
import repository.SanPham_Repository;
import view.ThongTinSanPham.SanPham_QrCode_View;

public class QrCode_View_SanPham extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private SanPham_BH_Pepository repo_SanPham = new SanPham_BH_Pepository();
    BanHang_form form_BH;

    public QrCode_View_SanPham() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initWebCam();
        setTitle("Quét QrCode Sản Phẩm");
        form_BH = new BanHang_form();
    }

    public QrCode_View_SanPham(BanHang_form banHang_form) {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initWebCam();
        setTitle("Quét QrCode Sản Phẩm");
        form_BH = banHang_form;
    }

    private void initWebCam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        jPanelCamera.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));
        executor.execute(this);
    }

    @Override
    public void run() {
        boolean check = true;
        do {

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
//                Logger.getLogger(ReadQRCode.class.getName()).log(Level.SEVERE, null, ex);
            }
            Result result = null;
            BufferedImage image = null;
            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }

            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException ex) {
//                Logger.getLogger(ReadQRCode.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (result != null) {
                // lấy dữ liệu từ result
                result_field.setText(result.getText());
                ToanCuc_MaSP_QrCode sp_TC = new ToanCuc_MaSP_QrCode();
                sp_TC.setMaSP(result.getText());
                try {

//                    ChonImei_view imei = new ChonImei_view(new BanHang_form());
//                    imei.setVisible(true);
//                    imei.fillToTable(repo_SanPham.chonImei_TheoMaSP(tc.getMaImei()));
                    SanPham_QrCode_View sp = new SanPham_QrCode_View();
                    sp.setVisible(true);
                    closeWebcam();
                    dispose();
                } catch (Exception e) {
                }

            }
        } while (check);

        return;

    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    public void closeWebcam() {
        if (webcam.isOpen()) {
            webcam.close();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelCamera = new javax.swing.JPanel();
        result_field = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelCamera.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelCamera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanelCamera, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));

        result_field.setBorder(null);
        jPanel1.add(result_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 470, 30));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 470, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        closeWebcam();
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QrCode_View_SanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QrCode_View_SanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QrCode_View_SanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QrCode_View_SanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QrCode_View_SanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCamera;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField result_field;
    // End of variables declaration//GEN-END:variables

    private static class ReadQRCode {

        public ReadQRCode() {
        }
    }
}
