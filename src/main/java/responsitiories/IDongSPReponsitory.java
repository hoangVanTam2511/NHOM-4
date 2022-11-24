/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package responsitiories;

import domainmodels.DongSp;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public interface IDongSPReponsitory {
       List<DongSp>findAll();

    DongSp findOneByMa(String ma);

    DongSp findOneByID(UUID id);

    boolean save(DongSp t);

    boolean delete(DongSp t);

    boolean update(DongSp t);
}
