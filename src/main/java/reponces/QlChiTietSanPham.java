/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.Anh;
import domainmodels.CauHinh;
import domainmodels.DongSp;
import domainmodels.MauSac;
import domainmodels.NSX;
import domainmodels.SanPham;
import infrastructure.convert.FormUtil;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Admin
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QlChiTietSanPham {

    private UUID id;
    private int delected;

    private double donGia;

    private String moTa;

    private int soLuongTon;

    private DongSp idDongSp;

    private MauSac idMauSac;

    private CauHinh idCauHinh;

    private NSX idNsx;

    private SanPham idSanPham;

    private Anh idAnh;

    public Object getData(int stt) {
        return new Object[]{
            stt, this.idSanPham.getSoImei(), this.idSanPham.getTen(), this.idNsx.getTen(), this.idDongSp.getTen(), this.idMauSac.getTen(), this.idCauHinh.getIdManHinh().getDoPhanGiai(), this.idCauHinh.getIdManHinh().getKichThuoc(), this.idCauHinh.getIdRam().getKichThuoc(), this.idCauHinh.getIdRom().getKichThuoc(), this.soLuongTon, FormUtil.convertNumber(donGia)
        };
    }

    public Object getData1(int stt) {
        return new Object[]{
            stt, this.idSanPham.getSoImei(), this.idSanPham.getTen(), this.idNsx.getTen(), this.idDongSp.getTen(), this.idMauSac.getTen(), this.soLuongTon, FormUtil.convertNumber(donGia)
        };
    }

    public Object getDataKhuyenMaiSanPham() {
        return new Object[]{
            (this.getDelected() == 1) ? false :true, this.idSanPham.getSoImei(), this.idSanPham.getTen()
        };
    }
}
