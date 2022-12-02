/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Support;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class SubMessTypeOpt {
      public enum TypeStatus {
        THEMTHANHCONG, MATONTAI, THATBAI, KHONGTONTAI, XOATHANHCONG, UPDATETHANHCONG, USERNOTFOUND, LOGINOK, THANHTOANTHANHCONG, THANHTOANTHATBAI, SOLUONGKHONGPHUHOP
    }

    public static void SubTypeOptReturn(TypeStatus type, JFrame frame) {
        switch (type) {
            case MATONTAI:
                JOptionPane.showMessageDialog(frame, "Mã tồn tại.");
                break;
            case THEMTHANHCONG:
                JOptionPane.showMessageDialog(frame, "Thêm thành công.");
                break;
            case THATBAI:
                JOptionPane.showMessageDialog(frame, "Faild.");
                break;
            case KHONGTONTAI:
                JOptionPane.showMessageDialog(frame, "Không tồn tại.");
                break;
            case XOATHANHCONG:
                JOptionPane.showMessageDialog(frame, "Xóa thành công.");
                break;
            case UPDATETHANHCONG:
                JOptionPane.showMessageDialog(frame, "Cập nhật thành công.");
                break;
            case LOGINOK:
                JOptionPane.showMessageDialog(frame, "Đăng nhập thành công.");
                break;
            case USERNOTFOUND:
                JOptionPane.showMessageDialog(frame, "Tài khoản không tồn tại.");
                break;
            case THANHTOANTHANHCONG:
                JOptionPane.showMessageDialog(frame, "Thanh toán thành công.");
                break;
            case THANHTOANTHATBAI:
                JOptionPane.showMessageDialog(frame, "Thanh toán không thành công.");
                break;
            case SOLUONGKHONGPHUHOP:
                JOptionPane.showMessageDialog(frame, "Kho không đủ số lượng.");
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Unknow Option!");
        }
    }
}
