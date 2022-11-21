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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "anh")
public class Anh extends PrimaryEntity{
    
    @Column(name = "ten")
    private String ten;
    
    @Column(name = "duongDan")
    private String duongDan;
    
    @Column(name = "trangThai")
    private int trangThai;
}
