/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.ThongTinSanPham;

import entity.DungLuongPin;
import form.SanPham_form;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import repository.DungLuongPin_Repository;
import ultil.MsgBox;

/**
 *
 * @author Nguyen duoc
 */
public class DungLuongPin_View extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelCu = new DefaultTableModel();
    DungLuongPin_Repository repo_pin = new DungLuongPin_Repository();
    int index = -1;
    SanPham_form spForm;

    public DungLuongPin_View() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý Dung Lượng Pin");
        tatFormSua_Xoa();
        model = (DefaultTableModel) tblPin.getModel();
        modelCu = (DefaultTableModel) tblPinCu.getModel();
        fillToTable(repo_pin.getAll());
        fillToTableCu(repo_pin.getAllXoa());
    }

    public DungLuongPin_View(SanPham_form SanPham_form) {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý Dung Lượng Pin");
        tatFormSua_Xoa();
        model = (DefaultTableModel) tblPin.getModel();
        modelCu = (DefaultTableModel) tblPinCu.getModel();
        fillToTable(repo_pin.getAll());
        fillToTableCu(repo_pin.getAllXoa());
        spForm = SanPham_form;
    }

    private void tatFormSua_Xoa() {
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);

    }

    private void fillToTable(ArrayList<DungLuongPin> list) {
        model.setRowCount(0);
        for (DungLuongPin p : list) {
            model.addRow(new Object[]{
                p.getDungLuongPin()
            });
        }
    }

    private void fillToTableCu(ArrayList<DungLuongPin> list) {
        modelCu.setRowCount(0);
        for (DungLuongPin p : list) {
            modelCu.addRow(new Object[]{
                p.getDungLuongPin()
            });
        }
    }

    private void clear() {
        tatFormSua_Xoa();
        btnThem.setEnabled(true);
        txtPin.setText("");

    }

    private DungLuongPin them() {
        DungLuongPin pin = DungLuongPin.builder()
                .DungLuongPin(txtPin.getText())
                .build();
        return pin;
    }

    private DungLuongPin sua() {
        index = tblPin.getSelectedRow();
        DungLuongPin r = repo_pin.getAll().get(index);
        DungLuongPin pin = DungLuongPin.builder()
                .id_DungLuongPin(r.getId_DungLuongPin())
                .DungLuongPin(txtPin.getText())
                .build();
        return pin;
    }

    private DungLuongPin xoa() {
        index = tblPin.getSelectedRow();
        DungLuongPin pin = repo_pin.getAll().get(index);
        return pin;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnSua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPin = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtPin = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPinCu = new javax.swing.JTable();

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

        tblPin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Dung Lượng Pin"
            }
        ));
        tblPin.setRowHeight(30);
        tblPin.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblPin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPinMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPin);

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
        jLabel1.setText("Dung Lượng Pin");

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
                    .addComponent(txtPin, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Sử Dụng", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblPinCu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dung Lượng Pin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPinCu.setRowHeight(30);
        tblPinCu.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblPinCu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPinCuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPinCuMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblPinCu);

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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
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
        if (txtPin.getText().isEmpty()) {
            MsgBox.showMessage(this, "Chưa Nhập Pin");

            return;
        }
        boolean kt = true;
        for (DungLuongPin r : repo_pin.getAll()) {
            if (txtPin.getText().equalsIgnoreCase(r.getDungLuongPin())) {
                kt = false;
                MsgBox.showMessage(this, "Pin Đã Có");
                return;
            }
        }
        if (kt) {
            repo_pin.Sua(sua());
            fillToTable(repo_pin.getAll());
            MsgBox.showMessage(this, "Sửa Thành Công");
            spForm.fillTo_CBO_Pin(repo_pin.getAll());
            clear();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblPinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPinMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        index = tblPin.getSelectedRow();
        txtPin.setText(tblPin.getValueAt(index, 0).toString());
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
    }//GEN-LAST:event_tblPinMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        repo_pin.Xoa(xoa());
        fillToTable(repo_pin.getAll());
        fillToTableCu(repo_pin.getAllXoa());
        MsgBox.showMessage(this, "Xóa Thành Công");
        spForm.fillTo_CBO_Pin(repo_pin.getAll());
        clear();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (txtPin.getText().isEmpty()) {
            MsgBox.showMessage(this, "Chưa Nhập DL Pin");
            return;
        }

        boolean kt = true;
        for (DungLuongPin r : repo_pin.getAll()) {
            if (txtPin.getText().equalsIgnoreCase(r.getDungLuongPin())) {
                kt = false;
                MsgBox.showMessage(this, "Pin Đã Có");
                return;
            }
        }

        if (kt) {
            repo_pin.Them(them());
            fillToTable(repo_pin.getAll());
            MsgBox.showMessage(this, "thêm thành công");
            spForm.fillTo_CBO_Pin(repo_pin.getAll());
            txtPin.setText("");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblPinCuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPinCuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPinCuMouseClicked
    private DungLuongPin khoiPhuc() {
        index = tblPinCu.getSelectedRow();
        DungLuongPin pin = repo_pin.getAllXoa().get(index);
        return pin;
    }
    private void tblPinCuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPinCuMousePressed
        if (evt.getClickCount() == 2) {
            boolean check = MsgBox.showConfirm(this, "Bạn muốn Khôi Phục K");
            if (check) {
                repo_pin.khoiPhuc(khoiPhuc());
                fillToTable(repo_pin.getAll());
                fillToTableCu(repo_pin.getAllXoa());
                MsgBox.showMessage(this, "Khôi Phục Thành Công");
                spForm.fillTo_CBO_Pin(repo_pin.getAll());
                clear();

            }
        }


    }//GEN-LAST:event_tblPinCuMousePressed

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
            java.util.logging.Logger.getLogger(DungLuongPin_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DungLuongPin_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DungLuongPin_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DungLuongPin_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DungLuongPin_View().setVisible(true);
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
    private javax.swing.JTable tblPin;
    private javax.swing.JTable tblPinCu;
    private javax.swing.JTextField txtPin;
    // End of variables declaration//GEN-END:variables
}
