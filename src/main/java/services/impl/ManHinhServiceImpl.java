/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.ManHinh;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import reponces.QlManHinh;
import reponces.QlMauSac;
import responsitiories.impl.ManHinhRepository;
import services.IService;

/**
 *
 * @author Admin
 */
public class ManHinhServiceImpl implements IService<QlManHinh>{
    private ManHinhRepository manHinhReponsitoryImpl;
    private List<QlManHinh> qlManHinhs;

    public ManHinhServiceImpl() {
        this.manHinhReponsitoryImpl = new ManHinhRepository();
    }

    @Override
    public List<QlManHinh> findAll() {
        qlManHinhs = new ArrayList<>();
        this.manHinhReponsitoryImpl.findAll().forEach(manHinh -> {
            qlManHinhs.add(FormUtil.convertQlManHinhToManHinh(manHinh));
        });
        return qlManHinhs;
    }

    @Override
    public QlManHinh findOne(String ma) {
        ManHinh manHinh = this.manHinhReponsitoryImpl.findOneByMa(ma);
        return FormUtil.convertQlManHinhToManHinh(manHinh);
    }

    @Override
    public String save(QlManHinh qlManHinh) {
        ManHinh manHinh = FormUtil.convertManHinhToQLManHinh(qlManHinh);
        return this.manHinhReponsitoryImpl.save(manHinh)==true?"Thêm thành công":"Thêm thất bại";
    }

    @Override
    public String delete(QlManHinh qlManHinh) {
        ManHinh manHinh = FormUtil.convertManHinhToQLManHinh(qlManHinh);
        boolean result = this.manHinhReponsitoryImpl.delete(manHinh);
        return  result==true?"Xóa thành công":"Xóa thất bại ";
    }

    @Override
    public String update( QlManHinh qlManHinh) {
        ManHinh manHinh  = FormUtil.convertManHinhToQLManHinh(qlManHinh);
        return this.manHinhReponsitoryImpl.update(manHinh)==true?"update thành công":"update thất bại ";
    }

}
