/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.ThongTinSanPham;

import entity.KichThuoc;
import form.SanPham_form;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import repository.KichThuocRepository;
import ultil.MsgBox;

public class KichThuoc_View extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelCu = new DefaultTableModel();
    KichThuocRepository repo_KichThuoc = new KichThuocRepository();
    int index = -1;
    SanPham_form spForm;

    public KichThuoc_View() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý Kích Thước");
        tatFormSua_Xoa();
        model = (DefaultTableModel) tblKichThuoc.getModel();
        modelCu = (DefaultTableModel) tblKichThuocCu.getModel();
        fillToTable(repo_KichThuoc.getAll());
        fillToTableCu(repo_KichThuoc.getAllCu());
    }

    public KichThuoc_View(SanPham_form SanPham_form) {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý Kích Thước");
        tatFormSua_Xoa();
        model = (DefaultTableModel) tblKichThuoc.getModel();
        modelCu = (DefaultTableModel) tblKichThuocCu.getModel();
        fillToTable(repo_KichThuoc.getAll());
        fillToTableCu(repo_KichThuoc.getAllCu());
        spForm = SanPham_form;
    }

    private void tatFormSua_Xoa() {
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);

    }

    private void fillToTable(ArrayList<KichThuoc> list) {
        model.setRowCount(0);
        for (KichThuoc kt : list) {
            model.addRow(new Object[]{
                kt.getKichThuoc()
            });
        }
    }

    private void fillToTableCu(ArrayList<KichThuoc> list) {
        modelCu.setRowCount(0);
        for (KichThuoc kt : list) {
            modelCu.addRow(new Object[]{
                kt.getKichThuoc()
            });
        }
    }

    private void clear() {
        tatFormSua_Xoa();
        btnThem.setEnabled(true);
        txtKichThuoc.setText("");

    }

    private KichThuoc them() {
        KichThuoc kt = KichThuoc.builder()
                .kichThuoc(txtKichThuoc.getText())
                .build();
        return kt;
    }

    private KichThuoc sua() {
        index = tblKichThuoc.getSelectedRow();
        KichThuoc r = repo_KichThuoc.getAll().get(index);
        KichThuoc kt = KichThuoc.builder()
                .id_KichThuoc(r.getId_KichThuoc())
                .kichThuoc(txtKichThuoc.getText())
                .build();
        return kt;
    }

    private KichThuoc xoa() {
        index = tblKichThuoc.getSelectedRow();
        KichThuoc kt = repo_KichThuoc.getAll().get(index);
        return kt;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnClear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtKichThuoc = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKichThuoc = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKichThuocCu = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnClear.setBackground(new java.awt.Color(153, 204, 255));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Text.png"))); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Kích Thước");

        tblKichThuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Kích Thước"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKichThuoc.setRowHeight(30);
        tblKichThuoc.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKichThuocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKichThuoc);

        btnThem.setBackground(new java.awt.Color(153, 204, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sử Dụng", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblKichThuocCu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kích Thước"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKichThuocCu.setRowHeight(30);
        tblKichThuocCu.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblKichThuocCu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKichThuocCuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKichThuocCuMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblKichThuocCu);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(217, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Đã Bán", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tblKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKichThuocMouseClicked
        // TODO add your handling code here:
        index = tblKichThuoc.getSelectedRow();
        txtKichThuoc.setText(tblKichThuoc.getValueAt(index, 0).toString());
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
    }//GEN-LAST:event_tblKichThuocMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (txtKichThuoc.getText().isEmpty()) {
            MsgBox.showMessage(this, "Chưa Nhập Kích Thước");
            return;
        }
        boolean kt = true;
        for (KichThuoc r : repo_KichThuoc.getAll()) {
            if (txtKichThuoc.getText().equalsIgnoreCase(r.getKichThuoc())) {
                kt = false;
                MsgBox.showMessage(this, "Kích Thước Đã Có");
                return;
            }
        }
        if (kt) {
            repo_KichThuoc.Them(them());
            fillToTable(repo_KichThuoc.getAll());
            MsgBox.showMessage(this, "thêm thành công");
            spForm.fillTo_CBO_KichThuocMan(repo_KichThuoc.getAll());
            txtKichThuoc.setText("");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (txtKichThuoc.getText().isEmpty()) {
            MsgBox.showMessage(this, "Chưa Nhập Kích Thước");

            return;
        }
        boolean kt = true;
        for (KichThuoc r : repo_KichThuoc.getAll()) {
            if (txtKichThuoc.getText().equalsIgnoreCase(r.getKichThuoc())) {
                kt = false;
                MsgBox.showMessage(this, "Kích Thước Đã Có");
                return;
            }
        }
        if (kt) {
            repo_KichThuoc.Sua(sua());
            fillToTable(repo_KichThuoc.getAll());
            MsgBox.showMessage(this, "Sửa Thành Công");
            spForm.fillTo_CBO_KichThuocMan(repo_KichThuoc.getAll());
            clear();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        repo_KichThuoc.Xoa(xoa());
        fillToTable(repo_KichThuoc.getAll());
        fillToTableCu(repo_KichThuoc.getAllCu());
        MsgBox.showMessage(this, "Xóa Thành Công");
        spForm.fillTo_CBO_KichThuocMan(repo_KichThuoc.getAll());
        clear();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblKichThuocCuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKichThuocCuMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblKichThuocCuMouseClicked
    private KichThuoc khoiPhuc() {
        index = tblKichThuocCu.getSelectedRow();
        KichThuoc kt = repo_KichThuoc.getAllCu().get(index);
        return kt;
    }
    private void tblKichThuocCuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKichThuocCuMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            boolean check = MsgBox.showConfirm(this, "Bạn muốn Khôi Phục K");
            if (check) {
                repo_KichThuoc.khoiPhuc(khoiPhuc());
                fillToTable(repo_KichThuoc.getAll());
                fillToTableCu(repo_KichThuoc.getAllCu());
                MsgBox.showMessage(this, "Khôi Phục Thành Công");
                spForm.fillTo_CBO_KichThuocMan(repo_KichThuoc.getAll());
            }
        }
    }//GEN-LAST:event_tblKichThuocCuMousePressed

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
            java.util.logging.Logger.getLogger(KichThuoc_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KichThuoc_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KichThuoc_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KichThuoc_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KichThuoc_View().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblKichThuoc;
    private javax.swing.JTable tblKichThuocCu;
    private javax.swing.JTextField txtKichThuoc;
    // End of variables declaration//GEN-END:variables
}
