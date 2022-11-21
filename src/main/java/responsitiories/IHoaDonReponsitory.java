/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package responsitiories;

import domainmodels.HoaDon;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public interface IHoaDonReponsitory {

    List<HoaDon> findAll();

    List<HoaDon> findAll(int status);

    HoaDon findOneByMa(String ma);

    HoaDon findOneByID(UUID id);

    boolean save(HoaDon t);

    boolean update(HoaDon t);

    boolean delete(HoaDon t);

}
