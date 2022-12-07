/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.KhuyenMai;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.Date;
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

    public String genMaTuDong() {
        List<String> listMa = this.khuyenMaiRepositoryImpl.getMaKhuyenMai();
        if (listMa.size() == 0) {
            return "KM00";
        }
        String maHoaDonHienTai = listMa.get(listMa.size() - 1);
        int index = Integer.parseInt(maHoaDonHienTai.substring(2));
        index++;
        String ma = "KM";
        if (index > 1 && index < 10) {
            ma += "0" + index;
        } else {
            ma += index;
        }
        return ma;
    }

    public String validate(QlKhuyenMai qlKhuyenMai) {
        Date date = new Date();
        if (qlKhuyenMai.getMaKhuyenMai().isBlank() || qlKhuyenMai.getTen().isBlank()) {
            return "Bạn phải nhập đủ số trường dữ liệu ";
        } else if (qlKhuyenMai.getMucGiamGiaPhanTram() > 100 || qlKhuyenMai.getMucGiamGiaPhanTram() < 0) {
            return "Mức giảm giá phải từ 0 -> 100";
        } else if (qlKhuyenMai.getMucGiamGiaTienMat() < 0) {
            return "Mức giá phải là số dương";
        } else if (qlKhuyenMai.getNgayBatDau().after(qlKhuyenMai.getNgayKetThuc()) == true) {
            return "Ngày bắt đầu phải nhỏ hơn ngày kết thúc";
        }if(!qlKhuyenMai.getNgayKetThuc().after(date)){
            return "Chương trình chưa chạy đã kết thúc.Vui lòng tạo lại";
        }
        
        return "";
    }
    public static void main(String[] args) {
        System.out.println(new KhuyenMaiServiceImpl().genMaTuDong());
    }
    

}
