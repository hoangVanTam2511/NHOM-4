  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import domainmodels.base.IsIdentified;
import domainmodels.base.PrimaryEntity;
import infrastructure.constant.EntityProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "CuaHang")
public class CuaHang extends PrimaryEntity implements IsIdentified, Serializable {

    @Column(name = "Ma", length = EntityProperties.LENGTH_MA)
    private String ma;

    @Column(name = "DiaChi", length = EntityProperties.LENGT_ADDRESS)
    private String diaChi;

    @Column(name = "Ten", length = EntityProperties.LENGT_FULL_NAME)
    private String ten;

    @Column(name = "QuocGia", length = EntityProperties.LENGT_CITY)
    private String quocGia;

    @Column(name = "ThanhPho", length = EntityProperties.LENGT_CITY)
    private String thanhPho;

}
