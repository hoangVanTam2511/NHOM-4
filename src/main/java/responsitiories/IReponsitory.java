/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitiories;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 * @param <T>
 */
public interface IReponsitory<T> {

    List<T> findAll();

    T findOneByMa(String ma);

    T findOneByID(UUID id);

    boolean save(T t);

    boolean update(T t);

    boolean delete(T t);
}
