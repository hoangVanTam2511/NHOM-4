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

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dong_sp")
public class DongSp extends PrimaryEntity implements IsIdentified,Serializable{ 
    
    @Column(name = "ma",length = EntityProperties.LENGTH_MA,nullable = false)
    private String ma;
    
    @Column(name = "ten",length = EntityProperties.LENGT_SHORT_NAME,nullable = false)
    private String ten;

   
    
}
