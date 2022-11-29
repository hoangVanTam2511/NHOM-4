/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */

@Entity
@Data
@Table(name = "chi_tiet_phieu_bao_hanh")
public class ChiTietBaoHanh implements Serializable{
    
    @EmbeddedId
    private ChiTietPhieuBaoHanhId chiTietPhieuBaoHanhId;
    
    @Column(name = "ngay_bat_dau")
    private Date ngayBatDau;
    
    @Column(name = "ngay_ket_thuc")
    private Date ngayKetThuc;
    
}
