package view.ThongTinSanPham;

import entity.CPU;
import form.SanPham_form;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lombok.experimental.Delegate;
import repository.CPU_Repository;
import ultil.MsgBox;
import view.Menu_View;

public class CPU_View extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelXoa = new DefaultTableModel();
    CPU_Repository repo_CPU = new CPU_Repository();
    int index = -1;
    SanPham_form spForm;

    public CPU_View() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý CPU");
        tatFormSua_Xoa();
        model = (DefaultTableModel) tblCPU.getModel();
        modelXoa = (DefaultTableModel) tblCPU_Cu.getModel();
        fillToTable(repo_CPU.getAll());
        fillToTableDaXoa(repo_CPU.getAllXoa());
    }

    public CPU_View(SanPham_form SanPham_form) {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý CPU");
        tatFormSua_Xoa();
        model = (DefaultTableModel) tblCPU.getModel();
        modelXoa = (DefaultTableModel) tblCPU_Cu.getModel();
        fillToTable(repo_CPU.getAll());
        fillToTableDaXoa(repo_CPU.getAllXoa());
        spForm = SanPham_form;
    }

    private void tatFormSua_Xoa() {
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);

    }

    public void fillToTable(ArrayList<CPU> list) {
        model.setRowCount(0);
        for (CPU c : list) {
            model.addRow(new Object[]{
                c.getCPU()
            });
        }
    }

    private void fillToTableDaXoa(ArrayList<CPU> list) {
        modelXoa.setRowCount(0);
        for (CPU c : list) {
            modelXoa.addRow(new Object[]{
                c.getCPU()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnSua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCPU = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCPU = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCPU_Cu = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setInheritsPopupMenu(true);
        jTabbedPane1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnSua.setBackground(new java.awt.Color(153, 204, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        tblCPU.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPU"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCPU.setRowHeight(30);
        tblCPU.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblCPU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCPUMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCPU);

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
        jLabel1.setText("CPU");

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
                    .addComponent(txtCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 44, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Sử Dụng", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblCPU_Cu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPU"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCPU_Cu.setRowHeight(30);
        tblCPU_Cu.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblCPU_Cu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCPU_CuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblCPU_CuMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblCPU_Cu);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addContainerGap())
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
    private void clear() {
        tatFormSua_Xoa();
        btnThem.setEnabled(true);
        txtCPU.setText("");

    }

    private CPU them() {
        CPU cpu = CPU.builder()
                .CPU(txtCPU.getText())
                .build();
        return cpu;
    }

    private CPU sua() {
        index = tblCPU.getSelectedRow();
        CPU r = repo_CPU.getAll().get(index);
        CPU cpu = CPU.builder()
                .id_CPU(r.getId_CPU())
                .CPU(txtCPU.getText())
                .build();
        return cpu;
    }

    private CPU xoa() {
        index = tblCPU.getSelectedRow();
        CPU cpu = repo_CPU.getAll().get(index);
        return cpu;
    }
    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (txtCPU.getText().isEmpty()) {
            MsgBox.showMessage(this, "Chưa Nhập Ram");

            return;
        }
        boolean kt = true;
        for (CPU c : repo_CPU.getAll()) {
            if (txtCPU.getText().equalsIgnoreCase(c.getCPU())) {
                kt = false;
                MsgBox.showMessage(this, "CPU Đã Có");
                return;
            }
        }
        if (kt) {
            repo_CPU.Sua(sua());
            fillToTable(repo_CPU.getAll());
            MsgBox.showMessage(this, "Sửa Thành Công");
            spForm.fillTo_CBO_CPU(repo_CPU.getAll());
            clear();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblCPUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCPUMouseClicked
        index = tblCPU.getSelectedRow();
        txtCPU.setText(tblCPU.getValueAt(index, 0).toString());
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnThem.setEnabled(false);
    }//GEN-LAST:event_tblCPUMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        repo_CPU.Xoa(xoa());
        fillToTable(repo_CPU.getAll());
        fillToTableDaXoa(repo_CPU.getAllXoa());
        MsgBox.showMessage(this, "Xóa Thành Công");
        spForm.fillTo_CBO_CPU(repo_CPU.getAll());
        clear();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (txtCPU.getText().isEmpty()) {
            MsgBox.showMessage(this, "Chưa Nhập CPU");
            return;
        }
        boolean kt = true;
        for (CPU c : repo_CPU.getAll()) {
            if (txtCPU.getText().equalsIgnoreCase(c.getCPU())) {
                kt = false;
                MsgBox.showMessage(this, "CPU Đã Có");
                return;
            }
        }
        if (kt) {
            repo_CPU.Them(them());
            fillToTable(repo_CPU.getAll());
            MsgBox.showMessage(this, "thêm thành công");
            spForm.fillTo_CBO_CPU(repo_CPU.getAll());
            txtCPU.setText("");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblCPU_CuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCPU_CuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCPU_CuMouseClicked

    private void tblCPU_CuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCPU_CuMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            boolean check = MsgBox.showConfirm(this, "Bạn muốn Khôi Phục K");
            if (check) {
                repo_CPU.khoiPhuc(khoiPhuc());
                fillToTable(repo_CPU.getAll());
                fillToTableDaXoa(repo_CPU.getAllXoa());
                MsgBox.showMessage(this, "Khôi Phục Thành Công");
                spForm.fillTo_CBO_CPU(repo_CPU.getAll());
            }
        }
    }//GEN-LAST:event_tblCPU_CuMousePressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:

    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:

    }//GEN-LAST:event_formWindowClosed

    private CPU khoiPhuc() {
        index = tblCPU_Cu.getSelectedRow();
        CPU cpu = repo_CPU.getAllXoa().get(index);
        return cpu;
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CPU_View().setVisible(true);
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
    private javax.swing.JTable tblCPU;
    private javax.swing.JTable tblCPU_Cu;
    private javax.swing.JTextField txtCPU;
    // End of variables declaration//GEN-END:variables
}
