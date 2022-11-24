/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package responsitiories;

import domainmodels.NSX;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public interface INSXReponsitory {

    List<NSX> findAll();

    NSX findOneByMa(String ma);

    NSX findOneByID(UUID id);

    boolean save(NSX t);

    boolean delete(NSX t);

    boolean update(NSX t);
}
