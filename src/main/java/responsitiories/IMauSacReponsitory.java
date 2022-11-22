/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package responsitiories;

import domainmodels.MauSac;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public interface IMauSacReponsitory {

    List<MauSac> findAll();

    MauSac findOneByMa(String ma);

    MauSac findOneByID(UUID id);

    boolean save(MauSac t);

    boolean delete(MauSac t);

    boolean update(MauSac t);
}
