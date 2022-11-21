/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import reponces.QlCuaHang;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public interface IService<T> {

    List<T> findAll();

    T findOne(String ma);

    String save(T t);

    String delete(T t);

    String update(T t);

}
