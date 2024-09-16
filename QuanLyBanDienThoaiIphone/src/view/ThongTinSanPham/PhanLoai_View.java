/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.ThongTinSanPham;

import entity.PhanLoai;
import form.SanPham_form;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import repository.PhanLoai_Repository;
import ultil.MsgBox;

/**
 *
 * @author Nguyen duoc
 */
public class PhanLoai_View extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelCu = new DefaultTableModel();
    PhanLoai_Repository repo_PhanLoai = new PhanLoai_Repository();
    int index = -1;
    SanPham_form spForm; 
    public PhanLoai_View() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý Phân loại");
        tatFormSua_Xoa();
        model = (DefaultTableModel) tblPhanLoai.getModel();
        modelCu = (DefaultTableModel) tblPhanLoaiCu.getModel();
        fillToTable(repo_PhanLoai.getAll());
        fillToTableCu(repo_PhanLoai.getAllCu());
    }
    
    public PhanLoai_View(SanPham_form SanPham_form) {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý Phân loại");
        tatFormSua_Xoa();
        model = (DefaultTableModel) tblPhanLoai.getModel();
        modelCu = (DefaultTableModel) tblPhanLoaiCu.getModel();
        fillToTable(repo_PhanLoai.getAll());
        fillToTableCu(repo_PhanLoai.getAllCu());
        spForm = SanPham_form;
    }

    private void tatFormSua_Xoa() {
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);

    }

    private void fillToTable(ArrayList<PhanLoai> list) {
        model.setRowCount(0);
        for (PhanLoai r : list) {
            model.addRow(new Object[]{
                r.getPhanLoai()
            });
        }
    }

    private void fillToTableCu(ArrayList<PhanLoai> list) {
        modelCu.setRowCount(0);
        for (PhanLoai r : list) {
            modelCu.addRow(new Object[]{
                r.getPhanLoai()
            });
        }
    }

    private void clear() {
        tatFormSua_Xoa();
        btnThem.setEnabled(true);
        txtPhanLoai.setText("");

    }

    private PhanLoai them() {
        PhanLoai pl = PhanLoai.builder()
                .phanLoai(txtPhanLoai.getText())
                .build();
        return pl;
    }

    private PhanLoai sua() {
        index = tblPhanLoai.getSelectedRow();
        PhanLoai r = repo_PhanLoai.getAll().get(index);
        PhanLoai pl = PhanLoai.builder()
                .id_PhanLoai(r.getId_PhanLoai())
                .phanLoai(txtPhanLoai.getText())
                .build();
        return pl;
    }

    private PhanLoai xoa() {
        index = tblPhanLoai.getSelectedRow();
        PhanLoai pl = repo_PhanLoai.getAll().get(index);
        return pl;
    }

    private PhanLoai khoiPhuc() {
        index = tblPhanLoaiCu.getSelectedRow();
        PhanLoai pl = repo_PhanLoai.getAllCu().get(index);
        return pl;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPhanLoai = new javax.swing.JTable();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtPhanLoai = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPhanLoaiCu = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnThem.setBackground(new java.awt.Color(153, 204, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        tblPhanLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Phân Loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhanLoai.setRowHeight(30);
        tblPhanLoai.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblPhanLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhanLoaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPhanLoai);

        btnSua.setBackground(new java.awt.Color(153, 204, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(153, 204, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Trash.png"))); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(153, 204, 255));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Text.png"))); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Phân Loại");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sử Dụng", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblPhanLoaiCu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Phân Loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhanLoaiCu.setRowHeight(30);
        tblPhanLoaiCu.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblPhanLoaiCu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhanLoaiCuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPhanLoaiCuMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblPhanLoaiCuMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblPhanLoaiCu);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đã Xóa", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (txtPhanLoai.getText().isEmpty()) {
            MsgBox.showMessage(this, "Chưa Nhập Phân loại");
            return;
        }
        boolean kt = true;
        for (PhanLoai r : repo_PhanLoai.getAll()) {
            if (txtPhanLoai.getText().equalsIgnoreCase(r.getPhanLoai())) {
                kt = false;
                MsgBox.showMessage(this, "Phân loại Đã Có");
                return;
            }
        }
        if (kt) {
            repo_PhanLoai.Them(them());
            fillToTable(repo_PhanLoai.getAll());
            MsgBox.showMessage(this, "thêm thành công");
            spForm.fillTo_CBO_PhanLoai(repo_PhanLoai.getAll());
            txtPhanLoai.setText("");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblPhanLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhanLoaiMouseClicked
        // TODO add your handling code here:
        index = tblPhanLoai.getSelectedRow();
        txtPhanLoai.setText(tblPhanLoai.getValueAt(index, 0).toString());
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
    }//GEN-LAST:event_tblPhanLoaiMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (txtPhanLoai.getText().isEmpty()) {
            MsgBox.showMessage(this, "Chưa Nhập Phân loại");

            return;
        }
        boolean kt = true;
        for (PhanLoai r : repo_PhanLoai.getAll()) {
            if (txtPhanLoai.getText().equalsIgnoreCase(r.getPhanLoai())) {
                kt = false;
                MsgBox.showMessage(this, "Phân Loại Đã Có");
                return;
            }
        }
        if (kt) {
            repo_PhanLoai.Sua(sua());
            fillToTable(repo_PhanLoai.getAll());
            MsgBox.showMessage(this, "Sửa Thành Công");
            spForm.fillTo_CBO_PhanLoai(repo_PhanLoai.getAll());
            clear();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        repo_PhanLoai.Xoa(xoa());
        fillToTable(repo_PhanLoai.getAll());
        fillToTableCu(repo_PhanLoai.getAllCu());
        MsgBox.showMessage(this, "Xóa Thành Công");
        spForm.fillTo_CBO_PhanLoai(repo_PhanLoai.getAll());
        clear();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tblPhanLoaiCuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhanLoaiCuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPhanLoaiCuMouseClicked

    private void tblPhanLoaiCuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhanLoaiCuMousePressed
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) {
            boolean check = MsgBox.showConfirm(this, "Bạn muốn Khôi Phục K");
            if (check) {
                repo_PhanLoai.khoiPhuc(khoiPhuc());
                fillToTable(repo_PhanLoai.getAll());
                fillToTableCu(repo_PhanLoai.getAllCu());
                MsgBox.showMessage(this, "Khôi Phục Thành Công");
                spForm.fillTo_CBO_PhanLoai(repo_PhanLoai.getAll());
                clear();
            }
        }
    }//GEN-LAST:event_tblPhanLoaiCuMousePressed

    private void tblPhanLoaiCuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhanLoaiCuMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPhanLoaiCuMouseReleased

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
            java.util.logging.Logger.getLogger(PhanLoai_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PhanLoai_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PhanLoai_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PhanLoai_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PhanLoai_View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblPhanLoai;
    private javax.swing.JTable tblPhanLoaiCu;
    private javax.swing.JTextField txtPhanLoai;
    // End of variables declaration//GEN-END:variables
}
