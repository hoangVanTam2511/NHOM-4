/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

/**
 *
 * @author FPT Shop
 */
import domainmodels.KhachHang;
import responsitiories.impl.KhachHangRepositoryImpl;
import services.IService;
import reponces.QlKhachHang;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import responsitiories.impl.KhachHangRepositoryImpl;

/**
 *
 * @author Admin
 */
public class KhachHangServiceImpl implements IService<QlKhachHang> {

    private KhachHangRepositoryImpl khachHangReponsitoryImpl;
    private List<QlKhachHang> listKhachHang;

    public KhachHangServiceImpl() {
        this.khachHangReponsitoryImpl = new KhachHangRepositoryImpl();
        this.listKhachHang = new ArrayList<QlKhachHang>();
    }

    @Override
    public List<QlKhachHang> findAll() {
        listKhachHang = new ArrayList<>();
        this.khachHangReponsitoryImpl.findAll().forEach(khachHang -> {
            listKhachHang.add(FormUtil.convertFromKhachHangToQlKhachHang(khachHang));
        });
        return listKhachHang;
    }

    @Override
    public QlKhachHang findOne(String ma) {
        KhachHang khachHang = this.khachHangReponsitoryImpl.findOneByMa(ma);
        return FormUtil.convertFromKhachHangToQlKhachHang(khachHang);
    }

    @Override
    public String save(QlKhachHang t) {
        KhachHang khachHang = FormUtil.convertFromQlKhachHangToKhachHang(t);
        return this.khachHangReponsitoryImpl.save(khachHang) == true ? "Thêm thành công" : "Thêm thất bại";
    }

    @Override
    public String delete(QlKhachHang t) {

        KhachHang khachHang = FormUtil.convertFromQlKhachHangToKhachHang(t);
        return this.khachHangReponsitoryImpl.delete(khachHang) == true ? "Xóa thành công" : "Xóa thất bại";
    }

    @Override
    public String update(QlKhachHang t) {
        KhachHang khachHang = FormUtil.convertFromQlKhachHangToKhachHang(t);
        return this.khachHangReponsitoryImpl.update(khachHang)
                == true ? "Sửa thành công" : "Sửa thất bại";

    }

    public String validate(String ten, String diaChi, String soDienThoai) {
        if (ten.isBlank() || diaChi.isBlank() || soDienThoai.isBlank()) {
            return "Bạn phải nhập đủ các ô có trên bảng";
        } else if (ten.matches("\\d+")) {
            return "Bạn phải nhập tên là chữ ";
        } else if (!soDienThoai.matches("0+\\d{9}")) {
            return "Bạn phải nhập đúng định dạng số điện thoại 10 số";
        } else if (diaChi.matches("\\d+")) {
            return "Bạn phải nhập địa chỉ là chữ ";
        } else {
            return null;
        }
    }

    public String genMaTuDong() {
        List<String> mas = khachHangReponsitoryImpl.getMa();
        if(mas.size() != 0){
        String maCuoiCung = mas.get(mas.size() - 1);
        Integer soMa = Integer.parseInt(maCuoiCung.substring(2));
        return "KH" + (soMa + 1);
        }else{
            return "KH1";
        }
    }
}
