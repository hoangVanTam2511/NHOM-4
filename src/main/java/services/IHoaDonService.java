/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import reponces.QlHoaDon;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public interface IHoaDonService {
    
    List<QlHoaDon> findAll();

    List<QlHoaDon> findAll(int status,String maNhanVien);

    QlHoaDon findOne(String ma);

    String save(QlHoaDon t);

    String update(QlHoaDon t);

    String delete(QlHoaDon t);

}
