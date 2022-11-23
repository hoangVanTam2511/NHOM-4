/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.KhuyenMai;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import reponces.QlKhuyenMai;
import responsitiories.impl.KhuyenMaiRepositoryImpl;
import services.IService;
import ultilities.HibernateUtil;

/**
 *
 * @author FPT Shop
 */
public class KhuyenMaiServiceImpl implements IService<QlKhuyenMai> {

    private KhuyenMaiRepositoryImpl khuyenMaiRepositoryImpl;
    private List<QlKhuyenMai> listKhuyenMai;

    public KhuyenMaiServiceImpl() {
        this.khuyenMaiRepositoryImpl = new KhuyenMaiRepositoryImpl();
        this.listKhuyenMai = new ArrayList<QlKhuyenMai>();
    }

    @Override
    public List<QlKhuyenMai> findAll() {
        listKhuyenMai = new ArrayList<>();
        this.khuyenMaiRepositoryImpl.findAll().forEach(khuyenMai -> {
            listKhuyenMai.add(FormUtil.convertQlKhuyenMaiToKhuyenMai(khuyenMai));
        });
        return listKhuyenMai;
    }

    @Override
    public QlKhuyenMai findOne(String ma) {
        KhuyenMai khuyenMai = this.khuyenMaiRepositoryImpl.findOneByMa(ma);
        return FormUtil.convertQlKhuyenMaiToKhuyenMai(khuyenMai);
    }

    @Override
    public String save(QlKhuyenMai t) {
        KhuyenMai khuyenMai = FormUtil.convertKhuyenMaiToQlKhuyenMai(t);
        return this.khuyenMaiRepositoryImpl.save(khuyenMai) == true ? "Thêm thành công" : "Thêm thất bại";
    }

    @Override
    public String delete(QlKhuyenMai t) {
        KhuyenMai khuyenMai = FormUtil.convertKhuyenMaiToQlKhuyenMai(t);
        return this.khuyenMaiRepositoryImpl.delete(khuyenMai) == true ? "Xóa thành công" : "Xóa thất bại";
    }

    @Override
    public String update(QlKhuyenMai t) {
        KhuyenMai khuyenMai = FormUtil.convertKhuyenMaiToQlKhuyenMai(t);
        return this.khuyenMaiRepositoryImpl.update(khuyenMai) == true ? "Sửa thành công" : "Sửa thất bại";
    }
    

}
