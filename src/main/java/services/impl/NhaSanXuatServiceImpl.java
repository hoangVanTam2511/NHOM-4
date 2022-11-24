/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.NSX;
import responsitiories.impl.NSXReponsitoryImpl;
import services.IService;
import reponces.QlNhaSanXuat;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class NhaSanXuatServiceImpl implements IService<QlNhaSanXuat> {

    private NSXReponsitoryImpl nhaSanXuatReponsitoryImpl;
    private List<QlNhaSanXuat> listNhaSanXuat;

    public NhaSanXuatServiceImpl() {
        this.nhaSanXuatReponsitoryImpl = new NSXReponsitoryImpl();
    }

    @Override
    public List<QlNhaSanXuat> findAll() {
        this.listNhaSanXuat = new ArrayList<>();
        this.nhaSanXuatReponsitoryImpl.findAll().forEach(nsx -> {
            listNhaSanXuat.add(new QlNhaSanXuat(nsx.getId(), nsx.getMa(), nsx.getTen()));
        });
        return listNhaSanXuat;
    }

    @Override
    public QlNhaSanXuat findOne(String ma) {
        NSX nhaSanXuat = this.nhaSanXuatReponsitoryImpl.findOneByMa(ma);
        return FormUtil.convertFromNhaSanXuatToQlNhaSanXuat(nhaSanXuat);
    }

    @Override
    public String save(QlNhaSanXuat qlNhaSanXuat) {
        NSX nhaNsx = FormUtil.convertFromQlNhaSanXuatToNSX(qlNhaSanXuat);
        return this.nhaSanXuatReponsitoryImpl.save(nhaNsx)==true?"Thêm thành công":"Thêm thất bại";
    }

    @Override
    public String delete(QlNhaSanXuat qlNhaSanXuat) {
        NSX nhaNsx = FormUtil.convertFromQlNhaSanXuatToNSX(qlNhaSanXuat);
        return this.nhaSanXuatReponsitoryImpl.delete(nhaNsx)==true?"Xóa thành công":"Xóa thất bại";
    }

    @Override
    public String update(QlNhaSanXuat qlNhaSanXuat) {
        NSX nhaSanXuat = FormUtil.convertFromQlNhaSanXuatToNSX(qlNhaSanXuat);
        return nhaSanXuatReponsitoryImpl.update( nhaSanXuat)==true?"Sửa thành công":"sửa thất bại";
    }
    
}
