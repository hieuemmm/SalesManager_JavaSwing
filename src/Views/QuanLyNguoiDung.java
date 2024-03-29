/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.TaiKhoanController;
import Core.MyObjectListCellRenderer;
import Core.NhomNguoiDung;
import Core.TaiKhoan;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class QuanLyNguoiDung extends javax.swing.JPanel {

    private static TaiKhoanController TKController;
    private final DefaultTableModel defaultTableModel;
    private List<NhomNguoiDung> NhomNguoiDungs;

    /**
     * Creates new form QuanLyNhomNguoiDung
     *
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public QuanLyNguoiDung() throws ClassNotFoundException, SQLException {
        initComponents();
        TKController = new TaiKhoanController();
        NhomNguoiDungs = new ArrayList<NhomNguoiDung>();
        jTextFieldTaiKhoan.setEditable(false);
        jLabelTrangThaiChucNang.setVisible(false);
        jLabelTrangThaiChucNang.setText("New");
        LoadComboboxNhomNguoiDung();
        LoadComboboxLocNhomNguoiDung();
        defaultTableModel = new DefaultTableModel() {
            //không cho phép sửa dữ liệu trên table
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //thông qua dữ liệu trong defaultTableModel để set data cho table
        jTablenguoiDung.setModel(defaultTableModel);
        //set giá trị cột
        defaultTableModel.addColumn("Tài Khoản");
        defaultTableModel.addColumn("Tên");
        defaultTableModel.addColumn("Phân Quyền");
        jTablenguoiDung.setRowHeight(30);
        setTableData(TKController.getAllTaiKhoan(ReadComboxLocNhomNguoiDung()));

        jTablenguoiDung.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    try {
                        TaiKhoan TaiKhoanSelect = LayDuLieuChon();
                        jTextFieldTaiKhoan.setText(TaiKhoanSelect.getTaiKhoan());
                        jTextFieldTMatKhau.setText(TaiKhoanSelect.getMatKhau());
                        jTextFieldHoten.setText(TaiKhoanSelect.getTen());
                        jComboBoxNhomNguoiDung.getModel().setSelectedItem(TKController.getNhomNguoiDungByTaiKhoan(TaiKhoanSelect));
                        jLabelTrangThaiChucNang.setText("Edit");
                        jTextFieldTaiKhoan.setEditable(false);
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(QuanLyNguoiDung.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public static TaiKhoan LayDuLieuChon() throws ClassNotFoundException, SQLException {
        int row = jTablenguoiDung.getSelectedRow();
        String taikhoan = String.valueOf(jTablenguoiDung.getValueAt(row, 0));
        return TKController.LayThongTinDangNhap(new TaiKhoan(taikhoan));
    }

    private void setTableData(List<TaiKhoan> taikhoans) throws ClassNotFoundException, SQLException {
        defaultTableModel.setRowCount(0);
        taikhoans.forEach(x -> {
            //set giá trị hàng
            defaultTableModel.addRow(new Object[]{
                x.getTaiKhoan(),
                x.getTen(),
                x.getTenNhomNguoiDung()
            });
        });
    }

    private void LoadComboboxNhomNguoiDung() throws ClassNotFoundException, SQLException {
        NhomNguoiDungs = TKController.getAllNhomNguoiDung();
        jComboBoxNhomNguoiDung.removeAllItems();
        NhomNguoiDungs.forEach(NND -> {
            jComboBoxNhomNguoiDung.addItem(new NhomNguoiDung(NND));
        });
        jComboBoxNhomNguoiDung.setRenderer(new MyObjectListCellRenderer());
    }

    private void LoadComboboxLocNhomNguoiDung() throws ClassNotFoundException, SQLException {
        NhomNguoiDungs = TKController.getAllNhomNguoiDung();
        jComboBoxLocNhomNguoiDung.removeAllItems();
        jComboBoxLocNhomNguoiDung.addItem(new NhomNguoiDung(0, "Tất cả"));
        NhomNguoiDungs.forEach(NND -> {
            jComboBoxLocNhomNguoiDung.addItem(new NhomNguoiDung(NND));
        });
        jComboBoxLocNhomNguoiDung.setRenderer(new MyObjectListCellRenderer());
    }

    private int ReadComboxNhomNguoiDung() {
        NhomNguoiDung NhomND = (NhomNguoiDung) (jComboBoxNhomNguoiDung.getSelectedItem());
        return NhomND.getMaNhomNguoiDung();
    }

    private int ReadComboxLocNhomNguoiDung() {
        NhomNguoiDung NhomND = (NhomNguoiDung) (jComboBoxLocNhomNguoiDung.getSelectedItem());
        return NhomND.getMaNhomNguoiDung();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldTaiKhoan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldHoten = new javax.swing.JTextField();
        jButtonThemMoi = new javax.swing.JButton();
        jButtonLuu = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxNhomNguoiDung = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldTMatKhau = new javax.swing.JTextField();
        jLabelTrangThaiChucNang = new javax.swing.JLabel();
        jButtonLoc = new javax.swing.JButton();
        jComboBoxLocNhomNguoiDung = new javax.swing.JComboBox<>();
        jTextFieldTimKiem = new javax.swing.JTextField();
        jButtonTimKiem = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablenguoiDung = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(988, 531));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("QUẢN LÝ NGƯỜI DÙNG");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tài Khoản:");

        jTextFieldTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Họ Tên :");

        jTextFieldHoten.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButtonThemMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonThemMoi.setText("Thêm Mới");
        jButtonThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemMoiActionPerformed(evt);
            }
        });

        jButtonLuu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonLuu.setText("Lưu");
        jButtonLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLuuActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Nhóm Người Dùng :");

        jComboBoxNhomNguoiDung.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Mật Khẩu:");

        jTextFieldTMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButtonLoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonLoc.setText("Lọc");
        jButtonLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLocActionPerformed(evt);
            }
        });

        jComboBoxLocNhomNguoiDung.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTextFieldTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButtonTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonTimKiem.setText("Tìm Kiếm");
        jButtonTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabelTrangThaiChucNang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxNhomNguoiDung, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldTaiKhoan)
                    .addComponent(jTextFieldHoten)
                    .addComponent(jTextFieldTMatKhau)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBoxLocNhomNguoiDung, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldTimKiem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxLocNhomNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxNhomNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addComponent(jLabelTrangThaiChucNang)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        jButtonThemMoi.getAccessibleContext().setAccessibleDescription(""); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTablenguoiDung.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTablenguoiDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Admin", "1", null},
                {"Nhân Viên", "2", null},
                {"User", null, null}
            },
            new String [] {
                "Tài Khoản", "Tên", "Nhóm người dùng"
            }
        ));
        jScrollPane1.setViewportView(jTablenguoiDung);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLuuActionPerformed
        switch (jLabelTrangThaiChucNang.getText()) {
            case "Edit":
            try {
                if (!jTextFieldTMatKhau.getText().equals("")) {
                    TKController.updateTaiKhoan(new TaiKhoan(jTextFieldTaiKhoan.getText(), jTextFieldTMatKhau.getText(), jTextFieldHoten.getText(), ReadComboxNhomNguoiDung()));
                    JOptionPane.showMessageDialog(this, "Sửa thành công!", "Sửa tài khoản", JOptionPane.DEFAULT_OPTION);
                    setTableData(TKController.getAllTaiKhoan(ReadComboxLocNhomNguoiDung()));
                } else {
                    JOptionPane.showMessageDialog(this, "Mật khẩu khỗng được rỗng", "Sửa tài khoản", JOptionPane.WARNING_MESSAGE);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(QuanLyNguoiDung.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Edit");
            break;

            case "New":
                if (!jTextFieldTMatKhau.getText().equals("") && !jTextFieldTaiKhoan.getText().equals("")) {
                    try {
                        if (TKController.addTaiKhoan(new TaiKhoan(jTextFieldTaiKhoan.getText(), jTextFieldTMatKhau.getText(), jTextFieldHoten.getText(), ReadComboxNhomNguoiDung()))) {
                            JOptionPane.showMessageDialog(this, "Thêm Thành công!", "Thêm mới tài khoản", JOptionPane.DEFAULT_OPTION);
                            setTableData(TKController.getAllTaiKhoan(ReadComboxLocNhomNguoiDung()));
                            jTextFieldTaiKhoan.setText("");
                            jTextFieldHoten.setText("");
                            jTextFieldTMatKhau.setText("");
                            jLabelTrangThaiChucNang.setText("New");
                            jTextFieldTaiKhoan.setEditable(true);
                            jTextFieldTaiKhoan.requestFocus();
                        } else {
                            JOptionPane.showMessageDialog(this, "tài khoản đã tồn tại!", "Thêm mới tài khoản", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(QuanLyNguoiDung.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Tài khoản và Mật khẩu khỗng được rỗng", "Thêm mới tài khoản", JOptionPane.WARNING_MESSAGE);
                }
                System.out.println("New");
                break;
        }
    }//GEN-LAST:event_jButtonLuuActionPerformed

    private void jButtonThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemMoiActionPerformed
        jTextFieldTaiKhoan.setText("");
        jTextFieldHoten.setText("");
        jTextFieldTMatKhau.setText("");
        jLabelTrangThaiChucNang.setText("New");
        jTextFieldTaiKhoan.setEditable(true);
        jTextFieldTaiKhoan.requestFocus();
    }//GEN-LAST:event_jButtonThemMoiActionPerformed

    private void jButtonLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLocActionPerformed
        try {
            setTableData(TKController.getAllTaiKhoan(ReadComboxLocNhomNguoiDung()));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuanLyDonHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonLocActionPerformed

    private void jButtonTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimKiemActionPerformed
        try {
            List<TaiKhoan> TKFind;
            TKFind = new ArrayList<>(TKController.TimKiemTaiKhoan(jTextFieldTimKiem.getText()));
            if (TKFind != null) {
                setTableData(TKFind);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản nào", "Tìm kiếm người dùng", JOptionPane.DEFAULT_OPTION);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuanLyDonHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLoc;
    private javax.swing.JButton jButtonLuu;
    private javax.swing.JButton jButtonThemMoi;
    private javax.swing.JButton jButtonTimKiem;
    private javax.swing.JComboBox<NhomNguoiDung> jComboBoxLocNhomNguoiDung;
    private javax.swing.JComboBox<NhomNguoiDung> jComboBoxNhomNguoiDung;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelTrangThaiChucNang;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jTablenguoiDung;
    private javax.swing.JTextField jTextFieldHoten;
    private javax.swing.JTextField jTextFieldTMatKhau;
    private javax.swing.JTextField jTextFieldTaiKhoan;
    private javax.swing.JTextField jTextFieldTimKiem;
    // End of variables declaration//GEN-END:variables

}
