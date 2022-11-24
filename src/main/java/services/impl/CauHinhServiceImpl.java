/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.CauHinh;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import reponces.QlCauHinh;
import responsitiories.impl.CauHinhReponsitoryImpl;
import services.IService;

/**
 *
 * @author Admin
 */
public class CauHinhServiceImpl implements IService<QlCauHinh>{

    private CauHinhReponsitoryImpl cauHinhReponsitoryImpl;
    private List<QlCauHinh> qlCauHinhs;
    
    public CauHinhServiceImpl(){
        this.cauHinhReponsitoryImpl = new CauHinhReponsitoryImpl();
    }
    @Override
    public List<QlCauHinh> findAll() {
        this.qlCauHinhs = new ArrayList<>();
        this.cauHinhReponsitoryImpl.findAll().forEach(cauHinh -> {
            qlCauHinhs.add(new QlCauHinh(cauHinh.getId(), cauHinh.getIdManHinh(), cauHinh.getIdRam(), cauHinh.getIdRom()));
        });
        return qlCauHinhs;
    }

    @Override
    public QlCauHinh findOne(String ma) {
        CauHinh cauHinh = this.cauHinhReponsitoryImpl.findOneByMa(ma);
        return FormUtil.convertQlCauHinhToCauHinh(cauHinh);
    }

    @Override
    public String save(QlCauHinh qlCauHinh) {
        CauHinh cauHinh = FormUtil.convertCauHinhToQLCauHinh(qlCauHinh);
       return this.cauHinhReponsitoryImpl.save(cauHinh)== true? "Thêm thành công":"Thêm thất bại";
    }

    @Override
    public String delete(QlCauHinh t) {
       CauHinh cauHinh = FormUtil.convertCauHinhToQLCauHinh(t);
        boolean result = this.cauHinhReponsitoryImpl.delete(cauHinh);
        return  result==true?"Xóa thành công":"Xóa thất bại ";
    }

    @Override
    public String update(QlCauHinh t) {
       CauHinh cauHinh = FormUtil.convertCauHinhToQLCauHinh(qlCauHinh));
       return this.cauHinhReponsitoryImpl.update(cauHinh) == true?"update thành công":"update thất bại ";
    }
    
}
