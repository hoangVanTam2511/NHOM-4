/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "hoa_don_chi_tiet")
public class ChiTietKhuyenMai implements Serializable {

    @EmbeddedId
    private ChiTietKhuyenMaiId chiTietKhuyenMaiId;
    
    @Column(name  = "don_gia")
    private Double donGia;
    
    @Column(name = "so_tien_con_lai")
    private Double soTienConLai;

}
