/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.ChucVu;
import responsitiories.impl.ChucVuReponsitoryImpl;
import services.IService;
import reponces.QlChucVu;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChucVuServiceImpl implements IService<QlChucVu>{

    private ChucVuReponsitoryImpl chucVuReponsitoryImpl;
    
    public ChucVuServiceImpl(){
        this.chucVuReponsitoryImpl = new ChucVuReponsitoryImpl();
    }
    
    @Override
    public List<QlChucVu> findAll() {
        List<QlChucVu> listChucVu = new ArrayList<>();
        this.chucVuReponsitoryImpl.findAll().forEach(chucVu->{
            listChucVu.add(FormUtil.convertFromChucVuToQlChucVu(chucVu));
        });
        return listChucVu;
    }

    @Override
    public QlChucVu findOne(String ma) {
        ChucVu chucVu = this.chucVuReponsitoryImpl.findOneByMa(ma);
        return FormUtil.convertFromChucVuToQlChucVu(chucVu);
    }

    @Override
    public String save(QlChucVu t) {
       ChucVu chucVu = FormUtil.convertFromQlChucVuToChucVu(t);
       return this.chucVuReponsitoryImpl.save(chucVu)==true?"Thêm thành công":"Thêm thất bại";
    }

    @Override
    public String delete(QlChucVu t) {
       ChucVu chucVu = FormUtil.convertFromQlChucVuToChucVu(t);
       return this.chucVuReponsitoryImpl.delete(chucVu)==true?"Xóa thành công":"Xóa thất bại";
    }

    @Override
    public String update(QlChucVu t) {
       ChucVu chucVu = FormUtil.convertFromQlChucVuToChucVu(t);
       return this.chucVuReponsitoryImpl.update(chucVu)==true?"Sửa thành công":"Sửa thất bại";
    }
    
}
