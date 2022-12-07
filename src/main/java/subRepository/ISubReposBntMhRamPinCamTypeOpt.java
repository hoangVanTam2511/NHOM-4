/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package subRepository;

import domainmodels.Camera;
import domainmodels.ManHinh;
import domainmodels.Pin;
import domainmodels.Ram;
import domainmodels.Rom;
import java.util.List;
import support.SubMessTypeOpt;

/**
 *
 * @author Admin
 */
public interface ISubReposBntMhRamPinCamTypeOpt {
    public List<Rom> getAllDataRom();

    public List<ManHinh> getAllDataMH();

    public List<Ram> getAllDataRam();

    public List<Pin> getAllDataPin();

    public List<Camera> getAllDataCamera();

    // chip
    public SubMessTypeOpt.TypeStatus addRom(Rom ch);

    public SubMessTypeOpt.TypeStatus remakeRom(Rom ch);

    public SubMessTypeOpt.TypeStatus removeRom(String code);

    //HDH
    public SubMessTypeOpt.TypeStatus addMH(ManHinh MH);

    public SubMessTypeOpt.TypeStatus remakeMH(ManHinh MH);

    public SubMessTypeOpt.TypeStatus removeMH(String code);

    // NSX
    public SubMessTypeOpt.TypeStatus addRam(Ram ram);

    public SubMessTypeOpt.TypeStatus remakeRam(Ram ram);

    public SubMessTypeOpt.TypeStatus removeRam(String code);
    // DongSP

    public SubMessTypeOpt.TypeStatus addPin(Pin dongSP);

    public SubMessTypeOpt.TypeStatus remakePin(Pin dongSP);

    public SubMessTypeOpt.TypeStatus removePin(String code);
    // MauSac

    public SubMessTypeOpt.TypeStatus addCamera(Camera cam);

    public SubMessTypeOpt.TypeStatus remakeCamera(Camera cam);

    public SubMessTypeOpt.TypeStatus removeCamera(String code);
}
