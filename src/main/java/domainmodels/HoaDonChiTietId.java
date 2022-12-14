/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Getter
@Setter
@Embeddable
public class HoaDonChiTietId implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hoa_don", nullable = false)
    private HoaDon idHoaDon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_chi_tietsp", nullable = false)
    private ChiTietSanPham idChiTietSanPham;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idHoaDon);
        hash = 37 * hash + Objects.hashCode(this.idChiTietSanPham);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HoaDonChiTietId other = (HoaDonChiTietId) obj;
        if (!Objects.equals(this.idHoaDon, other.idHoaDon)) {
            return false;
        }
        return Objects.equals(this.idChiTietSanPham, other.idChiTietSanPham);
    }

}
