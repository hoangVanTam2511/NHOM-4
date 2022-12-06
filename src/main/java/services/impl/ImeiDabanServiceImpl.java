/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.ImeiDaBan;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import reponces.QlImeiDaBan;
import responsitiories.impl.ImeiDaBanReponsitoryImpl;
import services.IService;

/**
 *
 * @author Admin
 */
public class ImeiDabanServiceImpl implements IService<QlImeiDaBan> {

    private ImeiDaBanReponsitoryImpl imeiDaBanReponsitoryImpl;

    public ImeiDabanServiceImpl() {
        this.imeiDaBanReponsitoryImpl = new ImeiDaBanReponsitoryImpl();
    }

    @Override
    public List<QlImeiDaBan> findAll() {
        List<QlImeiDaBan> listQlRam = new ArrayList<>();
        this.imeiDaBanReponsitoryImpl.findAll().forEach(ram -> {
            listQlRam.add(FormUtil.covertQlImeiDaBanToImeiDaBan(ram));
        });
        return listQlRam;
    }

    @Override
    public QlImeiDaBan findOne(String ma) {
       ImeiDaBan imeiDaBan = this.imeiDaBanReponsitoryImpl.findOneByImei(ma);
       return ;
    }

    @Override
    public String save(QlImeiDaBan t) {
        ImeiDaBan khuyenMai = FormUtil.covertImeiDabanToQlImeiDaBan(t);
        return this.imeiDaBanReponsitoryImpl.save(khuyenMai) == true ? "Thêm thành công" : "Thêm thất bại";
    }

    @Override
    public String delete(QlImeiDaBan t) {
        ImeiDaBan khuyenMai = FormUtil.covertImeiDabanToQlImeiDaBan(t);
        return this.imeiDaBanReponsitoryImpl.update(khuyenMai) == true ? "Thêm thành công" : "Thêm thất bại";
    }

    @Override
    public String update(QlImeiDaBan t) {
         ImeiDaBan khuyenMai = FormUtil.covertImeiDabanToQlImeiDaBan(t);
        return this.imeiDaBanReponsitoryImpl.update(khuyenMai) == true ? "Thêm thành công" : "Thêm thất bại";
    }

}
