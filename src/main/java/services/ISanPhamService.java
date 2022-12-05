/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.Imei;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ISanPhamService {

    List<Imei> findAll();

    List<Imei> findAll(int status);

    Imei findOne(String ma);

    String save(Imei t);

    String update(Imei t);

    String delete(Imei t);
}
