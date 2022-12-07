/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import domainmodels.base.PrimaryEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Entity
@Table(name = "rom")
@AllArgsConstructor
@NoArgsConstructor
public class Rom extends PrimaryEntity{
    
    @Column(name = "kich_thuoc")
    private int kichThuoc;
    @Column(name = "ma_rom")
    private String ma;
    @Column(name = "bo_nho")
    private String boNho;
}
