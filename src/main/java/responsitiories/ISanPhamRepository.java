/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package responsitiories;

import domainmodels.Imei;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public interface ISanPhamRepository {
      List<Imei> findAll();
    
    Imei findOneByLmei(String Lmei);
    
    Imei findOneByID(UUID id);
    
    boolean save(Imei t);
    
    boolean delete(Imei t);
    
    boolean update(Imei t);
}
