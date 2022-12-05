/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support;

/**
 *
 * @author Admin
 */
public class SubUIHdhChipMsDspTypeOpt {

    public enum TypeSub {
        CHIP, HDH, NSX, DONGSP, MAUSAC
    }

    public static String code(TypeSub type) {
        switch (type) {
            case CHIP:
                return "Mã CHIP: ";
            case HDH:
                return "Mã HDH: ";
            case NSX:
                return "Mã NSX: ";
            case DONGSP:
                return "Mã DongSP: ";
            case MAUSAC:
                return "Mã Màu Sắc: ";
            default:
                return "Unknow:";
        }
    }

    public static String name(TypeSub type) {
        switch (type) {
            case CHIP:
                return "Tên CHIP: ";
            case HDH:
                return "Tên HDH: ";
            case NSX:
                return "Tên NSX: ";
            case DONGSP:
                return "Tên DongSP: ";
            case MAUSAC:
                return "Tên Màu Sắc: ";
            default:
                return "Unknow:";
        }
    }

    public static String title(TypeSub type) {
        switch (type) {
            case CHIP:
                return "Chíp";
            case HDH:
                return "Hệ điều hành";
            case NSX:
                return "Nhà Sản Xuất";
            case DONGSP:
                return "Dòng Sản Phẩm";
            case MAUSAC:
                return "Màu Sắc";
            default:
                return "Unknow:";
        }
    }
}
