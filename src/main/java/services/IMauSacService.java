/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.MauSac;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IMauSacService {

    List<MauSac> findAll();

    List<MauSac> findAll(int status);

    MauSac findOne(String ma);

    String save(MauSac t);

    String update(MauSac t);

    String delete(MauSac t);
}
