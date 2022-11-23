/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import domainmodels.MauSac;
import responsitiories.impl.MauSacReponsitoryImpl;
import services.IService;
import reponces.QlMauSac;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class MauSacServiceImpl implements IService<QlMauSac> {

    private MauSacReponsitoryImpl mauSacReponsitoryImpl;
    private List<QlMauSac> qlMauSacs;

    public MauSacServiceImpl() {
        this.mauSacReponsitoryImpl = new MauSacReponsitoryImpl();
    }

    @Override
    public List<QlMauSac> findAll() {
        qlMauSacs = new ArrayList<>();
        this.mauSacReponsitoryImpl.findAll().forEach(mauSac -> {
            qlMauSacs.add(FormUtil.convertFromQlMauSacToMauSac(mauSac));
        });
        return qlMauSacs;
    }

    @Override
    public QlMauSac findOne(String ma) {
        MauSac mauSac = this.mauSacReponsitoryImpl.findOneByMa(ma);
        return FormUtil.convertFromQlMauSacToMauSac(mauSac);
    }

    @Override
    public String save(QlMauSac qlMauSac) {
        return this.mauSacReponsitoryImpl.save(FormUtil.convertFromQlMauSacToMauSac(qlMauSac))==true?"Thêm thành công":"Thêm thất bại";
    }

    @Override
    public String delete(QlMauSac qlMauSac) {
        MauSac mauSac = FormUtil.convertFromQlMauSacToMauSac(qlMauSac);
        boolean result = this.mauSacReponsitoryImpl.delete(mauSac);
        return  result==true?"Xóa thành công":"Xóa thất bại ";
    }

    @Override
    public String update( QlMauSac qlMauSac) {
        MauSac mauSac  = FormUtil.convertFromQlMauSacToMauSac(qlMauSac);
        return this.mauSacReponsitoryImpl.update(mauSac)==true?"update thành công":"update thất bại ";
    }
}
