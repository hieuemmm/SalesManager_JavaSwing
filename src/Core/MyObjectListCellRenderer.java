/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Administrator
 */
public class MyObjectListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        if (value instanceof NhomNguoiDung) {
            value = ((NhomNguoiDung) value).getTenNhomNguoiDung();
        }
        if (value instanceof DanhMucSanPham) {
            value = ((DanhMucSanPham) value).getTenDanhMuc();
        }
        if (value instanceof TrangThaiDonHang) {
            value = ((TrangThaiDonHang) value).getTenTrangThai();
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        return this;
    }
}
