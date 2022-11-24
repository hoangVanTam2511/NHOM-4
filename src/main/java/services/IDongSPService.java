/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.DongSp;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IDongSPService {

    List<DongSp> findAll();

    List<DongSp> findAll(int status);

    DongSp findOne(String ma);

    String save(DongSp t);

    String update(DongSp t);

    String delete(DongSp t);
}
