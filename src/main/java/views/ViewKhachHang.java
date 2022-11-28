/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reponces.QlKhachHang;
import services.impl.UserServiceImpl;
import reponces.QlUser;
import services.impl.KhachHangServiceImpl;

/**
 *
 * @author Admin
 */
public class ViewKhachHang extends javax.swing.JDialog {

    private KhachHangServiceImpl khachHangServiceImpl;
    private DefaultTableModel tableModel;
    private QlUser qlUser = null;

    /**
     * Creates new form ViewNhanVien
     */
    public ViewKhachHang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.khachHangServiceImpl = new KhachHangServiceImpl();
        this.tableModel = (DefaultTableModel) tableKhachHang.getModel();
        String title[] = {"Stt", "Mã", "Tên", "Địa chỉ"};
        tableModel.setColumnIdentifiers(title);
        showDataOnTable();
        this.setLocationRelativeTo(null);
    }
     public ViewKhachHang(java.awt.Frame parent, boolean modal,QlUser qlUser) {
        super(parent, modal);
        initComponents();
        this.qlUser = qlUser;
        this.khachHangServiceImpl = new KhachHangServiceImpl();
        this.tableModel = (DefaultTableModel) tableKhachHang.getModel();
        String title[] = {"Stt", "Mã", "Tên", "Địa chỉ"};
        tableModel.setColumnIdentifiers(title);
        showDataOnTable();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiemKhachHang = new javax.swing.JTextField();
        btnChon = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKhachHang = new javax.swing.JTable();
        btnThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Danh sách khách hàng");

        jLabel2.setText("Tìm theo tên:");

        btnChon.setText("Chọn");
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        tableKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableKhachHang);

        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(txtTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTimKiemKhachHang, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        // TODO add your handling code here:
        int row = tableKhachHang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn phải chọn 1 dòng tương ứng");
            return;
        }
        String ma = (String) tableModel.getValueAt(row, 1);
        QlKhachHang qlKhachHang = this.khachHangServiceImpl.findOne(ma);
        this.dispose();
        new ViewCuaHangBanDienThoai(this.qlUser,qlKhachHang).setVisible(true);
    }//GEN-LAST:event_btnChonActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void tableKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKhachHangMouseClicked

    }//GEN-LAST:event_tableKhachHangMouseClicked

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
            java.util.logging.Logger.getLogger(ViewKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewKhachHang dialog = new ViewKhachHang(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableKhachHang;
    private javax.swing.JTextField txtTimKiemKhachHang;
    // End of variables declaration//GEN-END:variables

    private void showDataOnTable() {
        tableModel.setRowCount(0);
        int i = 1;
        for (QlKhachHang user : this.khachHangServiceImpl.findAll()) {
            tableModel.addRow((Object[]) user.getData(i));
            i++;
        }
    }
}