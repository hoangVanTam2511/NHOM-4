/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.NSX;
import java.util.List;


/**
 *
 * @author Admin
 */
public interface INSXService {

    List<NSX> findAll();

    List<NSX> findAll(int status);

    NSX findOne(String ma);

    String save(NSX t);

    String update(NSX t);

    String delete(NSX t);
}
