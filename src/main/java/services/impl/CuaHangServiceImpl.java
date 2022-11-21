/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.CuaHang;
import responsitiories.impl.CuaHangReponsitoryImpl;
import services.IService;
import reponces.QlCuaHang;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Admin
 */
public class CuaHangServiceImpl implements IService<QlCuaHang> {

    private CuaHangReponsitoryImpl cuaHangReponsitoryImpl;

    public CuaHangServiceImpl() {
        this.cuaHangReponsitoryImpl = new CuaHangReponsitoryImpl();
    }

    @Override
    public List<QlCuaHang> findAll() {
        List<QlCuaHang> listCuaHang = new ArrayList<>();
        this.cuaHangReponsitoryImpl.findAll().forEach(cuaHang -> {
            listCuaHang.add(FormUtil.convertFromCuaHangToQlCuaHang(cuaHang));
        });
        return listCuaHang;
    }

    @Override
    public QlCuaHang findOne(String ma) {
        CuaHang cuaHang = this.cuaHangReponsitoryImpl.findOneByMa(ma);
        return FormUtil.convertFromCuaHangToQlCuaHang(cuaHang);
    }

    @Override
    public String save(QlCuaHang qlCuaHang) {
        CuaHang cuaHang = FormUtil.convertFromQlCuaHangToCuaHang(qlCuaHang);
        return this.cuaHangReponsitoryImpl.save(cuaHang) == true ? "thêm thành công" : "thêm thất bại";
    }

    @Override
    public String delete(QlCuaHang qlCuaHang) {
        return this.cuaHangReponsitoryImpl.delete(FormUtil.convertFromQlCuaHangToCuaHang(qlCuaHang))== true ? "Xóa thành công" : "Xóa thất bại";
    }

    @Override
    public String update(QlCuaHang qlCuaHang) {
        CuaHang cuaHang = FormUtil.convertFromQlCuaHangToCuaHang(qlCuaHang);
        return this.cuaHangReponsitoryImpl.update(cuaHang) == true ? "sửa thành công" : "sửa thất bại";
    }
    public static void main(String[] args) {
        System.out.println("hello");
    }
}
