/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.Ram;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.bytebuddy.asm.Advice;
import reponces.QLRam;
import responsitiories.impl.RamReponsitory;
import services.IService;

/**
 *
 * @author Admin
 */
public class RamServiceImpl implements IService<QLRam> {

    private RamReponsitory ramReponsitory;

    public RamServiceImpl() {
        this.ramReponsitory = new RamReponsitory();
    }

    @Override
    public List<QLRam> findAll() {
        List<QLRam> listQlRam = new ArrayList<>();
        this.ramReponsitory.findAll().forEach(ram -> {
            listQlRam.add(FormUtil.convertRamToQLRam(ram));
        });
        return listQlRam;
    }

    @Override
    public QLRam findOne(String ma) {
        Ram ram = this.ramReponsitory.findOneByMa(ma);
        return FormUtil.convertRamToQLRam(ram);
    }

    public QLRam findOne(UUID ma) {
        Ram ram = this.ramReponsitory.findOneByID(ma);
        return FormUtil.convertRamToQLRam(ram);
    }

    public QLRam findOneByKichThuoc(int kichThuoc) {
        Ram ram = this.ramReponsitory.findOneByKichCo(kichThuoc);
        return FormUtil.convertRamToQLRam(ram);
    }

    @Override
    public String save(QLRam t) {
        return this.ramReponsitory.save(FormUtil.convertQLRamToRam(t)) == true ? "Thêm thành công" : "Thêm thất bại";
    }

    @Override
    public String delete(QLRam t) {
        Ram ram = FormUtil.convertQLRamToRam(t);
        boolean result = this.ramReponsitory.delete(ram);
        return result == true ? "Xóa thành công" : "Xóa thất bại ";

    }

    @Override
    public String update(QLRam t) {
        Ram ram = FormUtil.convertQLRamToRam(t);
        return this.ramReponsitory.update(ram) == true ? "update thành công" : "update thất bại ";
    }
}
