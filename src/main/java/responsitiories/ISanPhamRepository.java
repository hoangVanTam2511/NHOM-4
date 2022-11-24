/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package responsitiories;

import domainmodels.SanPham;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public interface ISanPhamRepository {
      List<SanPham> findAll();
    
    SanPham findOneByLmei(String Lmei);
    
    SanPham findOneByID(UUID id);
    
    boolean save(SanPham t);
    
    boolean delete(SanPham t);
    
    boolean update(SanPham t);
}
