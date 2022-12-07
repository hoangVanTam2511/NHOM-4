/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support;

/**
 *
 * @author Admin
 */
public class SubUIBntMhRamPinCamTypeOpt {
     public enum TypeSub {
        BNT, MH, RAM, PIN, CAM
    }

    public static String code(TypeSub type) {
        switch (type) {
            case BNT:
                return "Mã BNT: ";
            case MH:
                return "Mã MH: ";
            case RAM:
                return "Mã RAM: ";
            case PIN:
                return "Mã PIN: ";
            case CAM:
                return "Mã CAM: ";
            default:
                return "Unknow:";
        }
    }

    public static String name(TypeSub type) {
           switch (type) {
            case BNT:
                return "Dung Lượng BNT: ";
            case MH:
                return "Tên MH: ";
            case RAM:
                return "Dung Lượng RAM: ";
            case PIN:
                return "Dung Lượng PIN: ";
            case CAM:
                return "Khẩu Độ CAM: ";
            default:
                return "Unknow:";
        }
    }
       public static String KichThuoc(TypeSub type) {
           switch (type) {
            case BNT:
                return "Kích Thước BNT: ";
            case MH:
                return "Kích Thước MH: ";
            case RAM:
                return "Kích Thước RAM: ";
            case PIN:
                return "Kích Thước PIN: ";
            case CAM:
                return "Kích Thước CAM: ";
            default:
                return "Unknow:";
        }
    }
    public static String title(TypeSub type) {
       switch (type) {
            case BNT:
                return "Rom";
            case MH:
                return "Màn Hình: ";
            case RAM:
                return "Ram";
            case PIN:
                return "Pin";
            case CAM:
                return "Camera";
            default:
                return "Unknow:";
        }
    }
}
