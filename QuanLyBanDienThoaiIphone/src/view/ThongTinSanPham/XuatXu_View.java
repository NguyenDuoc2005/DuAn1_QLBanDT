package view.ThongTinSanPham;

import entity.XuatXu;
import form.SanPham_form;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import repository.XuatXu_Repository;
import ultil.MsgBox;

public class XuatXu_View extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelCu = new DefaultTableModel();
    XuatXu_Repository repo_XuatXu = new XuatXu_Repository();
    int index = -1;
    SanPham_form spForm; 
    public XuatXu_View() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý Xuất Xứ");
        tatFormSua_Xoa();
        model = (DefaultTableModel) tblXuatXu.getModel();
        modelCu = (DefaultTableModel) tblXuatXuCu.getModel();
        fillToTable(repo_XuatXu.getAll());
        fillToTableCu(repo_XuatXu.getAllCu());
    }
    public XuatXu_View(SanPham_form  SanPham_form) {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý Xuất Xứ");
        tatFormSua_Xoa();
        model = (DefaultTableModel) tblXuatXu.getModel();
        modelCu = (DefaultTableModel) tblXuatXuCu.getModel();
        fillToTable(repo_XuatXu.getAll());
        fillToTableCu(repo_XuatXu.getAllCu());
        spForm = SanPham_form;
    }
    private void tatFormSua_Xoa() {
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);

    }

    private XuatXu them() {
        XuatXu x = XuatXu.builder()
                .xuatXu(txtXuatXu.getText())
                .build();
        return x;
    }

    private XuatXu sua() {
        index = tblXuatXu.getSelectedRow();
        XuatXu r = repo_XuatXu.getAll().get(index);
        XuatXu x = XuatXu.builder()
                .id_XuatXu(r.getId_XuatXu())
                .xuatXu(txtXuatXu.getText())
                .build();
        return x;
    }

    private void clear() {
        tatFormSua_Xoa();
        btnThem.setEnabled(true);
        txtXuatXu.setText("");

    }

    private XuatXu xoa() {
        index = tblXuatXu.getSelectedRow();
        XuatXu x = repo_XuatXu.getAll().get(index);
        return x;
    }

    private XuatXu khoiPhuc() {
        index = tblXuatXuCu.getSelectedRow();
        XuatXu x = repo_XuatXu.getAllCu().get(index);
        return x;
    }

    private void fillToTable(ArrayList<XuatXu> list) {
        model.setRowCount(0);
        for (XuatXu r : list) {
            model.addRow(new Object[]{
                r.getXuatXu()
            });
        }
    }

    private void fillToTableCu(ArrayList<XuatXu> list) {
        modelCu.setRowCount(0);
        for (XuatXu r : list) {
            modelCu.addRow(new Object[]{
                r.getXuatXu()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblXuatXu = new javax.swing.JTable();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtXuatXu = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblXuatXuCu = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblXuatXu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Xuất Xứ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblXuatXu.setRowHeight(30);
        tblXuatXu.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblXuatXu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblXuatXuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblXuatXu);

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
        jLabel1.setText("Xuất Xứ");

        btnThem.setBackground(new java.awt.Color(153, 204, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

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
                    .addComponent(txtXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 38, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sử Dụng", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblXuatXuCu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Xuất Xứ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblXuatXuCu.setRowHeight(30);
        tblXuatXuCu.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblXuatXuCu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblXuatXuCuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblXuatXuCuMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblXuatXuCu);

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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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

    private void tblXuatXuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblXuatXuMouseClicked
        // TODO add your handling code here:
        index = tblXuatXu.getSelectedRow();
        txtXuatXu.setText(tblXuatXu.getValueAt(index, 0).toString());
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
    }//GEN-LAST:event_tblXuatXuMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (txtXuatXu.getText().isEmpty()) {
            MsgBox.showMessage(this, "Chưa Nhập Xuất Xứ");

            return;
        }
        boolean kt = true;
        for (XuatXu r : repo_XuatXu.getAll()) {
            if (txtXuatXu.getText().equalsIgnoreCase(r.getXuatXu())) {
                kt = false;
                MsgBox.showMessage(this, "Xuất Xứ Đã Có");
                return;
            }
        }
        if (kt) {
            repo_XuatXu.Sua(sua());
            fillToTable(repo_XuatXu.getAll());
            MsgBox.showMessage(this, "Sửa Thành Công");
            spForm.fillTo_CBO_XuatXu(repo_XuatXu.getAll());
            clear();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        repo_XuatXu.Xoa(xoa());
        fillToTable(repo_XuatXu.getAll());
        fillToTableCu(repo_XuatXu.getAllCu());
        MsgBox.showMessage(this, "Xóa Thành Công");
        spForm.fillTo_CBO_XuatXu(repo_XuatXu.getAll());
        clear();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (txtXuatXu.getText().isEmpty()) {
            MsgBox.showMessage(this, "Chưa Nhập Xuất Xứ");
            return;
        }
        boolean kt = true;
        for (XuatXu r : repo_XuatXu.getAll()) {
            if (txtXuatXu.getText().equalsIgnoreCase(r.getXuatXu())) {
                kt = false;
                MsgBox.showMessage(this, "Xuất Xứ Đã Có");
                return;
            }
        }
        if (kt) {
            repo_XuatXu.Them(them());
            fillToTable(repo_XuatXu.getAll());
            MsgBox.showMessage(this, "thêm thành công");
            spForm.fillTo_CBO_XuatXu(repo_XuatXu.getAll());
            txtXuatXu.setText("");
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void tblXuatXuCuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblXuatXuCuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblXuatXuCuMouseClicked

    private void tblXuatXuCuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblXuatXuCuMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            boolean check = MsgBox.showConfirm(this, "Bạn muốn Khôi Phục K");
            if (check) {
                repo_XuatXu.khoiPhuc(khoiPhuc());
                fillToTable(repo_XuatXu.getAll());
                fillToTableCu(repo_XuatXu.getAllCu());
                MsgBox.showMessage(this, "Khôi Phục Thành Công");
                spForm.fillTo_CBO_XuatXu(repo_XuatXu.getAll());
            }
        }
    }//GEN-LAST:event_tblXuatXuCuMousePressed

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
            java.util.logging.Logger.getLogger(XuatXu_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XuatXu_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XuatXu_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XuatXu_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XuatXu_View().setVisible(true);
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
    private javax.swing.JTable tblXuatXu;
    private javax.swing.JTable tblXuatXuCu;
    private javax.swing.JTextField txtXuatXu;
    // End of variables declaration//GEN-END:variables
}
