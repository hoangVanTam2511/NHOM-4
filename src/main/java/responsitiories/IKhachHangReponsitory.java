/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package responsitiories;

import domainmodels.KhachHang;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public interface IKhachHangReponsitory {
    
    List<KhachHang> findAll(String diaChi);
    
    KhachHang findOneByMa(String ma);
    
    KhachHang findOneByID(UUID id);
    
    boolean save(KhachHang t);
    
    boolean delete(KhachHang t);
    
    boolean update(KhachHang t);
    
}
