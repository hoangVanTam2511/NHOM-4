/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import domainmodels.DongSp;
import domainmodels.MauSac;
import responsitiories.impl.DongSPReponsitoryImpl;
import services.IService;
import ultilities.HibernateUtil;
import reponces.QlDongSp;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class DongSpServiceImpl implements IService<QlDongSp> {

    private List<QlDongSp> listDongSp;
    private DongSPReponsitoryImpl dongSpReponsitory;

    public DongSpServiceImpl() {
        this.dongSpReponsitory = new DongSPReponsitoryImpl();
    }

    @Override
    public List<QlDongSp> findAll() {
        this.listDongSp = new ArrayList<>();
        this.dongSpReponsitory.findAll().forEach(dongSp -> {
            listDongSp.add(FormUtil.convertFromDongSpToQlDongSp(dongSp));
        });
        return listDongSp;
    }

    @Override
    public QlDongSp findOne(String ma) {
        DongSp dongSp = this.dongSpReponsitory.findOneByMa(ma);
        return FormUtil.convertFromDongSpToQlDongSp(dongSp);
    }

    @Override
    public String save(QlDongSp qlDongSp) {
        DongSp dongSp = FormUtil.convertFromQlDongSpToDongSp(qlDongSp);
        return this.dongSpReponsitory.save(dongSp) == true ? "thêm thành công" : "thêm thất bại";
    }

    @Override
    public String delete(QlDongSp t) {
       DongSp dongSp = FormUtil.convertFromQlDongSpToDongSp(t);
       return this.dongSpReponsitory.delete(dongSp)==true?"Xóa thành công":"Xóa thất bại";
    }

    @Override
    public String update(QlDongSp t) {
       DongSp dongSp = FormUtil.convertFromQlDongSpToDongSp(t);
       return this.dongSpReponsitory.update(dongSp)==true?"Sửa thành công":"Sửa thất bại";
    }
//
//    public QlDongSp findOne(String ma, String ten) {
//         DongSp dongSp = this.dongSpReponsitory.findOneByMaAndTen(ma,ten);
//        return FormUtil.convertFromDongSpToQlDongSp(dongSp);
//    }
}
