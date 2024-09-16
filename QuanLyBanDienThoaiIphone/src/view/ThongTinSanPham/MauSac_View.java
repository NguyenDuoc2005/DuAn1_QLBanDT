package view.ThongTinSanPham;

import entity.MauSac;
import form.SanPham_form;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import repository.MauSac_Repository;
import ultil.MsgBox;

public class MauSac_View extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelCu = new DefaultTableModel();
    MauSac_Repository repo_MauSac = new MauSac_Repository();
    int index = -1;
    SanPham_form spForm; 
    public MauSac_View() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý Màu Sắc");
        tatFormSua_Xoa();
        model = (DefaultTableModel) tblMauSac.getModel();
        modelCu = (DefaultTableModel) tblMauSacCu.getModel();
        fillToTable(repo_MauSac.getAll());
        fillToTableCu(repo_MauSac.getAllCu());
    }
    public MauSac_View(SanPham_form SanPham_form) {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý Màu Sắc");
        tatFormSua_Xoa();
        model = (DefaultTableModel) tblMauSac.getModel();
        modelCu = (DefaultTableModel) tblMauSacCu.getModel();
        fillToTable(repo_MauSac.getAll());
        fillToTableCu(repo_MauSac.getAllCu());
        spForm = SanPham_form; 
    }
    private void tatFormSua_Xoa() {
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);

    }

    private void fillToTable(ArrayList<MauSac> list) {
        model.setRowCount(0);
        for (MauSac r : list) {
            model.addRow(new Object[]{
                r.getMauSac()
            });
        }
    }

    private void fillToTableCu(ArrayList<MauSac> list) {
        modelCu.setRowCount(0);
        for (MauSac r : list) {
            modelCu.addRow(new Object[]{
                r.getMauSac()
            });
        }
    }

    private MauSac them() {
        MauSac ms = MauSac.builder()
                .mauSac(txtMauSac.getText())
                .build();
        return ms;
    }

    private void clear() {
        tatFormSua_Xoa();
        btnThem.setEnabled(true);
        txtMauSac.setText("");

    }

    private MauSac sua() {
        index = tblMauSac.getSelectedRow();
        MauSac r = repo_MauSac.getAll().get(index);
        MauSac ms = MauSac.builder()
                .id_MauSac(r.getId_MauSac())
                .mauSac(txtMauSac.getText())
                .build();
        return ms;
    }

    private MauSac xoa() {
        index = tblMauSac.getSelectedRow();
        MauSac ms = repo_MauSac.getAll().get(index);
        return ms;
    }

    private MauSac khoiPhuc() {
        index = tblMauSacCu.getSelectedRow();
        MauSac ms = repo_MauSac.getAllCu().get(index);
        return ms;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtMauSac = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMauSacCu = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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
        jLabel1.setText("Màu Sắc");

        btnThem.setBackground(new java.awt.Color(153, 204, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Màu Sắc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMauSac.setRowHeight(30);
        tblMauSac.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMauSac);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
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
                    .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sử Dụng", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblMauSacCu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Màu Sắc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMauSacCu.setRowHeight(30);
        tblMauSacCu.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblMauSacCu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacCuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMauSacCuMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblMauSacCu);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(239, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Đã Xóa", jPanel2);

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

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (txtMauSac.getText().isEmpty()) {
            MsgBox.showMessage(this, "Chưa Nhập Màu Sắc");

            return;
        }
        boolean kt = true;
        for (MauSac r : repo_MauSac.getAll()) {
            if (txtMauSac.getText().equalsIgnoreCase(r.getMauSac())) {
                kt = false;
                MsgBox.showMessage(this, "Màu Sắc Đã Có");
                return;
            }
        }
        if (kt) {
            repo_MauSac.Sua(sua());
            fillToTable(repo_MauSac.getAll());
            MsgBox.showMessage(this, "Sửa Thành Công");
            spForm.fillTo_CBO_MauSac(repo_MauSac.getAll());
            clear();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        repo_MauSac.Xoa(xoa());
        fillToTable(repo_MauSac.getAll());
        fillToTableCu(repo_MauSac.getAllCu());
        MsgBox.showMessage(this, "Xóa Thành Công");
       spForm.fillTo_CBO_MauSac(repo_MauSac.getAll());
        clear();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (txtMauSac.getText().isEmpty()) {
            MsgBox.showMessage(this, "Chưa Nhập Màu ");
            return;
        }
        boolean kt = true;
        for (MauSac r : repo_MauSac.getAll()) {
            if (txtMauSac.getText().equalsIgnoreCase(r.getMauSac())) {
                kt = false;
                MsgBox.showMessage(this, "Màu Sắc Đã Có");
                return;
            }
        }
        if (kt) {
            repo_MauSac.Them(them());
            fillToTable(repo_MauSac.getAll());
            MsgBox.showMessage(this, "thêm thành công");
            spForm.fillTo_CBO_MauSac(repo_MauSac.getAll());
            txtMauSac.setText("");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        // TODO add your handling code here:
        index = tblMauSac.getSelectedRow();
        txtMauSac.setText(tblMauSac.getValueAt(index, 0).toString());
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void tblMauSacCuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacCuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblMauSacCuMouseClicked

    private void tblMauSacCuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacCuMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            boolean check = MsgBox.showConfirm(this, "Bạn muốn Khôi Phục K");
            if (check) {
                repo_MauSac.khoiPhuc(khoiPhuc());
                fillToTable(repo_MauSac.getAll());
                fillToTableCu(repo_MauSac.getAllCu());
                MsgBox.showMessage(this, "Khôi Phục Thành Công");
                spForm.fillTo_CBO_MauSac(repo_MauSac.getAll());
                clear();
            }
        }
    }//GEN-LAST:event_tblMauSacCuMousePressed

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
            java.util.logging.Logger.getLogger(MauSac_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MauSac_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MauSac_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MauSac_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MauSac_View().setVisible(true);
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
    private javax.swing.JTable tblMauSac;
    private javax.swing.JTable tblMauSacCu;
    private javax.swing.JTextField txtMauSac;
    // End of variables declaration//GEN-END:variables
}
