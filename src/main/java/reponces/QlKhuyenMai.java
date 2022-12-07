/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author FPT Shop
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QlKhuyenMai {
    private UUID id;
    private int delected;
    private double mucGiamGiaPhanTram;
    private double mucGiamGiaTienMat;
    private String maKhuyenMai;
    private String ten;
    private String moTa;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private boolean  tinhTrang;

    public QlKhuyenMai(int delected, double mucGiamGiaPhanTram, double mucGiamGiaTienMat,String maKhuyenMai, String ten, String moTa, Date ngayBatDau, Date ngayKetThuc) {
        this.delected = delected;
        this.mucGiamGiaPhanTram = mucGiamGiaPhanTram;
        this.mucGiamGiaTienMat = mucGiamGiaTienMat;
        this.maKhuyenMai = maKhuyenMai;
        this.ten = ten;
        this.moTa = moTa;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }
    
}
