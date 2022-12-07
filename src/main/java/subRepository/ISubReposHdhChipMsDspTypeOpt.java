/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package subRepository;

import domainmodels.Chip;
import domainmodels.DongSp;
import domainmodels.HeDieuHanh;
import domainmodels.MauSac;
import domainmodels.NSX;
import java.util.List;
import support.SubMessTypeOpt;

/**
 *
 * @author Admin
 */
public interface ISubReposHdhChipMsDspTypeOpt {

    public List<Chip> getAllDataChip();

    public List<HeDieuHanh> getAllDataHDH();

    public List<DongSp> getAllDataDongSP();

    public List<MauSac> getAllDataMauSac();

    public List<NSX> getAllDataNSX();

    // chip
    public SubMessTypeOpt.TypeStatus addChip(Chip ch);

    public SubMessTypeOpt.TypeStatus remakechip(Chip ch);

    public SubMessTypeOpt.TypeStatus removeChip(String code);

    //HDH
    public SubMessTypeOpt.TypeStatus addHDH(HeDieuHanh ch);

    public SubMessTypeOpt.TypeStatus remakeHDH(HeDieuHanh ch);

    public SubMessTypeOpt.TypeStatus removeHDH(String code);

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
