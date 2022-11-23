/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;


import java.util.List;
import reponces.QlCauHinh;

/**
 *
 * @author Home
 */
public interface ICauHinhService {
    List<QlCauHinh> findAll();
    
    List<QlCauHinh> findAll(int status);
    
    QlCauHinh findOne(String ma);
    
    String save(QlCauHinh t);
    
    String update(QlCauHinh t);
    
    String delete(QlCauHinh t);
    
}
