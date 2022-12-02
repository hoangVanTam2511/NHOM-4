/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Support;

/**
 *
 * @author Admin
 */
public class SubUITypeOpt {

    public enum TypeSub {
        SANPHAM, NSX, DONGSP, MAUSAC, ANH
    }

    public static String code(TypeSub type) {
        switch (type) {
            case SANPHAM:
                return "Mã SP: ";
            case NSX:
                return "Mã NSX: ";
            case DONGSP:
                return "Mã DongSP: ";
            case MAUSAC:
                return "Mã Màu Sắc: ";
            case ANH:
                return "Mã ảnh: ";
            default:
                return "Unknow:";
        }
    }

    public static String name(TypeSub type) {
        switch (type) {
            case SANPHAM:
                return "Tên SP: ";
            case NSX:
                return "Tên NSX: ";
            case DONGSP:
                return "Tên DongSP: ";
            case MAUSAC:
                return "Tên Màu Sắc: ";
            case ANH:
                return "Tên ảnh: ";
            default:
                return "Unknow:";
        }
    }

    public static String title(TypeSub type) {
        switch (type) {
            case SANPHAM:
                return "Sản Phẩm";
            case NSX:
                return "Nhà Sản Xuất";
            case DONGSP:
                return "Dòng Sản Phẩm";
            case MAUSAC:
                return "Màu Sắc";
            case ANH:
                return "Ảnh";
            default:
                return "Unknow:";
        }
    }
}
