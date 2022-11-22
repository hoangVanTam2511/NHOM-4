/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.SanPham;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ISanPhamService {

    List<SanPham> findAll();

    List<SanPham> findAll(int status);

    SanPham findOne(String ma);

    String save(SanPham t);

    String update(SanPham t);

    String delete(SanPham t);
}
