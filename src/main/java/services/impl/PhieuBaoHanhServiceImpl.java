/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.PhieuBaoHanh;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import reponces.QlPhieuBaoHanh;
import responsitiories.impl.PhieuBaoHanhRepositoryImpl;
import services.IService;

/**
 *
 * @author FPT Shop
 */
public class PhieuBaoHanhServiceImpl implements IService<QlPhieuBaoHanh> {

    private PhieuBaoHanhRepositoryImpl phieuBaoHanhRepositoryImpl;
    private List<QlPhieuBaoHanh> qlPhieuBaoHanhs;

    public PhieuBaoHanhServiceImpl() {
        this.phieuBaoHanhRepositoryImpl = new PhieuBaoHanhRepositoryImpl();
        this.qlPhieuBaoHanhs = new ArrayList<QlPhieuBaoHanh>();
    }

    @Override
    public List<QlPhieuBaoHanh> findAll() {
        qlPhieuBaoHanhs = new ArrayList<>();
        this.phieuBaoHanhRepositoryImpl.findAll().forEach(phieuBaoHanh -> {
            qlPhieuBaoHanhs.add(FormUtil.convertQlPhieuBaoHanhToBaoHanh(phieuBaoHanh));
        });
        return qlPhieuBaoHanhs;
    }

    @Override
    public QlPhieuBaoHanh findOne(String ma) {
        PhieuBaoHanh phieuBaoHanh = this.phieuBaoHanhRepositoryImpl.findOneByMa(ma);
        return FormUtil.convertQlPhieuBaoHanhToBaoHanh(phieuBaoHanh);
    }

    @Override
    public String save(QlPhieuBaoHanh t) {
        PhieuBaoHanh phieuBaoHanh = FormUtil.convertPhieuBaoHanhToQlPhieuBaoHanh(t);
        return this.phieuBaoHanhRepositoryImpl.save(phieuBaoHanh) == true ? "Thêm thành công" : "Thêm thất bại";
    }

    @Override
    public String delete(QlPhieuBaoHanh t) {
        PhieuBaoHanh phieuBaoHanh = FormUtil.convertPhieuBaoHanhToQlPhieuBaoHanh(t);
        return this.phieuBaoHanhRepositoryImpl.delete(phieuBaoHanh) == true ? "Xóa thành công" : "Xóa thất bại";
    }

    @Override
    public String update(QlPhieuBaoHanh t) {
        PhieuBaoHanh phieuBaoHanh = FormUtil.convertPhieuBaoHanhToQlPhieuBaoHanh(t);
        return this.phieuBaoHanhRepositoryImpl.update(phieuBaoHanh) == true ? "Sửa thành công" : "Sửa thất bại";
    }
    
    public static void main(String[] args) {
        System.out.println(new PhieuBaoHanhServiceImpl().findAll());
    }

}
