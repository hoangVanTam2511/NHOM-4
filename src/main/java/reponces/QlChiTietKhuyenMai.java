/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reponces;

import domainmodels.ChiTietSanPham;
import domainmodels.KhuyenMai;
import infrastructure.convert.FormUtil;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class QlChiTietKhuyenMai {

    private ChiTietSanPham idChiTietSanPham;

    private KhuyenMai idKhuyenMai;

    private Double donGia;

    private Double soTienConLai;

    public String hinhThucKhuyenMai() {
        if (this.idKhuyenMai.getMucGiamGiaPhanTram() == 0) {
            return "Giảm theo tiền mặt";
        } else {
            return "Giảm theo %";
        }
    }

    public String giamGia() {
        if (this.idKhuyenMai.getMucGiamGiaPhanTram() == 0) {
            return FormUtil.convertNumber(this.idKhuyenMai.getMucGiamGiaTienMat()) + " VND";
        } else {
            return this.idKhuyenMai.getMucGiamGiaPhanTram() + " %";
        }
    }

    public String getTrangThai() {
        Date date = new Date();
        if (this.idKhuyenMai.getNgayKetThuc().after(date) == false) {
            return "Hết hạn";
        } else {
            return "Đang áp dụng";
        }
    }

    public Object getData() {
        return new Object[]{
            this.idKhuyenMai.getMaKhuyenMai(), this.idKhuyenMai.getTen(), hinhThucKhuyenMai(), giamGia(), this.idChiTietSanPham.getIdSanPham().getTen(), this.idKhuyenMai.getNgayBatDau(), this.idKhuyenMai.getNgayKetThuc(), getTrangThai(), this.idKhuyenMai.getMoTa()
        };
    }
}
