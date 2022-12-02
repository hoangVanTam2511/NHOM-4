/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import Support.SubMessTypeOpt;
import domainmodels.ChiTietSanPham;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IChiTietSPService {
      public List<ChiTietSanPham> getAllData();

    public SubMessTypeOpt.TypeStatus addChiTietSP(ChiTietSanPham item);

    public SubMessTypeOpt.TypeStatus remakeChiTietSP(ChiTietSanPham item);

    public SubMessTypeOpt.TypeStatus deleteChiTietSP(String code);
}
