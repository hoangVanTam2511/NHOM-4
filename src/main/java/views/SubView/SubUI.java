/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.SubView;

import Support.InputSupport;
import Support.SubMessTypeOpt;
import Support.SubUITypeOpt;
import domainmodels.Anh;
import domainmodels.DongSp;
import domainmodels.MauSac;
import domainmodels.NSX;
import domainmodels.SanPham;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import responsitiories.impl.SubControllerImp;
import views.ViewSanPham;

/**
 *
 * @author Admin
 */
public class SubUI extends javax.swing.JFrame {

    /**
     * Creates new form SubUI
     */
    private SubControllerImp controller = new SubControllerImp();
    private SubUITypeOpt.TypeSub type;

    public SubUI(SubUITypeOpt.TypeSub type) {
        initComponents();
        this.type = type;
        settingTypeUI(type);
        fillData(type);
        setLocationRelativeTo(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jlabelCode = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        jlabelName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMain = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jlabelTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Quay Lại");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jlabelCode.setText("Mã SP:");

        jlabelName.setText("Tên SP:");

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        tblMain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMainMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMain);

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jlabelTitle.setText("SP");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(221, 221, 221)
                            .addComponent(jlabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(31, 31, 31)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton1))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jlabelName, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                        .addComponent(jlabelCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jlabelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlabelCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCode))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabelName, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new ViewSanPham().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private SubUI() {
    }

    /*
    * Setting UI này sẽ dành cho bảng nào trong 4 bảng Sản phẩm, NSX,
     */
    private void settingTypeUI(SubUITypeOpt.TypeSub type) {
        jlabelCode.setText(SubUITypeOpt.code(type));
        jlabelName.setText(SubUITypeOpt.name(type));
        jlabelTitle.setText(SubUITypeOpt.title(type));
    }

    private boolean checkInputAddAndUpdate() {
        String code = txtCode.getText();
        String name = txtName.getText();
        if (!InputSupport.checkNullLength(code)) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã.");
            return false;
        }
        if (!InputSupport.checkNullLength(name)) {
            JOptionPane.showMessageDialog(this, "Không được để trống tên.");
            return false;
        }
        return true;
    }

    private boolean checkInputDelete() {
        String code = txtCode.getText();
        if (!InputSupport.checkNullLength(code)) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã.");
            return false;
        }
        return true;
    }

    /*
    * Get data Sản phẩm
     */
    private SanPham getDataSanPham() {
        SanPham item = new SanPham();
        item.setSoImei(txtCode.getText().trim());
        item.setTen(txtName.getText().trim());
        return item;
    }

    /*
    * Get data DongSP
     */
    private DongSp getDataDongSP() {
        DongSp item = new DongSp();
        item.setMa(txtCode.getText().trim());
        item.setTen(txtName.getText().trim());
        return item;
    }

    private Anh getDataAnh() {
        Anh item = new Anh();
        item.setDuongDan(txtCode.getText().trim());
        item.setTen(txtName.getText().trim());
        return item;
    }

    /*
    * Get data NSX
     */
    private NSX getDataNSX() {
        NSX item = new NSX();
        item.setMa(txtCode.getText().trim());
        item.setTen(txtName.getText().trim());
        return item;
    }/*
    * Get data MauSac
     */
    private MauSac getDataMauSac() {
        MauSac item = new MauSac();
        item.setMa(txtCode.getText().trim());
        item.setTen(txtName.getText().trim());
        return item;
    }

    private void fillData(SubUITypeOpt.TypeSub type) {
        DefaultTableModel dtm = (DefaultTableModel) tblMain.getModel();
        dtm.setRowCount(0);
        switch (type) {
            case SANPHAM:
                List<SanPham> dataSanPham = controller.getAllDataSanPham();
                if (dataSanPham == null || dataSanPham.size() == 0) {
                    return;
                }
                for (SanPham sp : dataSanPham) {
                    dtm.addRow(new Object[]{
                        sp.getSoImei(), sp.getTen()
                    });
                }
                tblMain.setModel(dtm);
                break;
            case DONGSP:
                List<DongSp> dataDongSp = controller.getAllDataDongSP();
                if (dataDongSp == null || dataDongSp.size() == 0) {
                    return;
                }
                for (DongSp item : dataDongSp) {
                    dtm.addRow(new Object[]{
                        item.getMa(), item.getTen()
                    });
                }
                tblMain.setModel(dtm);
                break;
            case MAUSAC:
                List<MauSac> dataMauSac = controller.getAllDataMauSac();
                if (dataMauSac == null || dataMauSac.size() == 0) {
                    return;
                }
                for (MauSac item : dataMauSac) {
                    dtm.addRow(new Object[]{
                        item.getMa(), item.getTen()
                    });
                }
                tblMain.setModel(dtm);
                break;
            case NSX:
                List<NSX> dataNSX = controller.getAllDataNSX();
                if (dataNSX == null || dataNSX.size() == 0) {
                    return;
                }
                for (NSX item : dataNSX) {
                    dtm.addRow(new Object[]{
                        item.getMa(), item.getTen()
                    });
                }
                tblMain.setModel(dtm);
                break;
            case ANH:
                List<Anh> dataAnh = controller.getAllDataAnh();
                if (dataAnh == null || dataAnh.size() == 0) {
                    return;
                }
                for (Anh item : dataAnh) {
                    dtm.addRow(new Object[]{
                        item.getDuongDan(), item.getTen()
                    });
                }
                tblMain.setModel(dtm);
                break;
            default:
                return;
        }
    }

    private void clear() {
        txtCode.setText("");
        txtName.setText("");
    }
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!checkInputAddAndUpdate()) {
            return;
        }
        switch (type) {
            case SANPHAM:
                SubMessTypeOpt.SubTypeOptReturn(controller.addSanPham(getDataSanPham()), this);
                clear();
                break;
            case DONGSP:
                SubMessTypeOpt.SubTypeOptReturn(controller.addDongSP(getDataDongSP()), this);
                clear();
                break;
            case MAUSAC:
                SubMessTypeOpt.SubTypeOptReturn(controller.addMauSac(getDataMauSac()), this);
                clear();
                break;
            case NSX:
                SubMessTypeOpt.SubTypeOptReturn(controller.addNSX(getDataNSX()), this);
                clear();
                break;
            case ANH:
                SubMessTypeOpt.SubTypeOptReturn(controller.addAnh(getDataAnh()), this);
                clear();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Error!");
        }
        fillData(type);
    }//GEN-LAST:event_btnAddActionPerformed
    private void fillDataControll(String code, String name) {
        txtCode.setText(code);
        txtName.setText(name);
    }
    private void tblMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMainMouseClicked
        int row = tblMain.getSelectedRow();
        switch (type) {
            case SANPHAM:
                SanPham item1 = controller.getAllDataSanPham().get(row);
                fillDataControll(item1.getSoImei(), item1.getTen());
                break;
            case MAUSAC:
                MauSac item2 = controller.getAllDataMauSac().get(row);
                fillDataControll(item2.getMa(), item2.getTen());
                break;
            case DONGSP:
                DongSp item3 = controller.getAllDataDongSP().get(row);
                fillDataControll(item3.getMa(), item3.getTen());
                break;
            case NSX:
                NSX item4 = controller.getAllDataNSX().get(row);
                fillDataControll(item4.getMa(), item4.getTen());
                break;
            case ANH:
                Anh item5 = controller.getAllDataAnh().get(row);
                fillDataControll(item5.getDuongDan(), item5.getTen());
                break;
        }
    }//GEN-LAST:event_tblMainMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (!checkInputAddAndUpdate()) {
            return;
        }
        switch (type) {
            case SANPHAM:
                SubMessTypeOpt.SubTypeOptReturn(controller.remakeSanPham(getDataSanPham()), this);
                clear();
                break;
            case DONGSP:
                SubMessTypeOpt.SubTypeOptReturn(controller.remakeDongSP(getDataDongSP()), this);
                clear();
                break;
            case MAUSAC:
                SubMessTypeOpt.SubTypeOptReturn(controller.remakeMauSac(getDataMauSac()), this);
                clear();
                break;
            case NSX:
                SubMessTypeOpt.SubTypeOptReturn(controller.remakeNSX(getDataNSX()), this);
                clear();
                break;
            case ANH:
                SubMessTypeOpt.SubTypeOptReturn(controller.remakeAnh(getDataAnh()), this);
                clear();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Error!");
        }
        fillData(type);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (!checkInputDelete()) {
            return;
        }
        switch (type) {
            case SANPHAM:
                SubMessTypeOpt.SubTypeOptReturn(controller.removeSanPham(txtCode.getText().trim()), this);
                clear();
                break;
            case DONGSP:
                SubMessTypeOpt.SubTypeOptReturn(controller.removeDongSP(txtCode.getText().trim()), this);
                clear();
                break;
            case MAUSAC:
                SubMessTypeOpt.SubTypeOptReturn(controller.removeMauSac(txtCode.getText().trim()), this);
                clear();
                break;
            case NSX:
                SubMessTypeOpt.SubTypeOptReturn(controller.removeNSX(txtCode.getText().trim()), this);
                clear();
                break;
            case ANH:
                SubMessTypeOpt.SubTypeOptReturn(controller.removeAnh(txtCode.getText().trim()), this);
                clear();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Error!");
        }
        fillData(type);
    }//GEN-LAST:event_btnDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(SubUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SubUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SubUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SubUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SubUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabelCode;
    private javax.swing.JLabel jlabelName;
    private javax.swing.JLabel jlabelTitle;
    private javax.swing.JTable tblMain;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
