/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package responsitiories;

import Support.SubMessTypeOpt;
import domainmodels.Anh;
import domainmodels.DongSp;
import domainmodels.MauSac;
import domainmodels.NSX;
import domainmodels.SanPham;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ISubController {

    public List<SanPham> getAllDataSanPham();

    public List<Anh> getAllDataAnh();

    public List<DongSp> getAllDataDongSP();

    public List<MauSac> getAllDataMauSac();

    public List<NSX> getAllDataNSX();

    // SanPham
    public SubMessTypeOpt.TypeStatus addSanPham(SanPham sp);

    public SubMessTypeOpt.TypeStatus addAnh(Anh anh);

    public SubMessTypeOpt.TypeStatus remakeSanPham(SanPham sp);

    public SubMessTypeOpt.TypeStatus remakeAnh(Anh anh);

    public SubMessTypeOpt.TypeStatus removeSanPham(String code);

    public SubMessTypeOpt.TypeStatus removeAnh(String code);

    // NSX
    public SubMessTypeOpt.TypeStatus addNSX(NSX nsx);

    public SubMessTypeOpt.TypeStatus remakeNSX(NSX nsx);

    public SubMessTypeOpt.TypeStatus removeNSX(String code);
    // DongSP

    public SubMessTypeOpt.TypeStatus addDongSP(DongSp dongSP);

    public SubMessTypeOpt.TypeStatus remakeDongSP(DongSp dongSP);

    public SubMessTypeOpt.TypeStatus removeDongSP(String code);
    // MauSac

    public SubMessTypeOpt.TypeStatus addMauSac(MauSac mauSac);

    public SubMessTypeOpt.TypeStatus remakeMauSac(MauSac mauSac);

    public SubMessTypeOpt.TypeStatus removeMauSac(String code);
}
