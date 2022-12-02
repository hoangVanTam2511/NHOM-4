/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainmodels.PhieuBaoHanh;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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

    public ArrayList<String> getListTenGoiBaoHanh() {
        return this.phieuBaoHanhRepositoryImpl.getListTenAnh();
    }

    public static void main(String[] args) {
        System.out.println(new PhieuBaoHanhServiceImpl().findAll());
    }

     public String genMaTuDong() {
        List<String> listMa  = this.phieuBaoHanhRepositoryImpl.getListmaPBH();
        if (listMa.size() == 0) {
            return "BH00";
        }
        String maHoaDonHienTai = listMa.get(listMa.size() - 1);
        int index = Integer.parseInt(maHoaDonHienTai.substring(3));
        index++;
        String ma = "BH";
        if (index > 1 && index < 10) {
            ma += "0" + index;
        } else {
            ma += index;
        }
        return ma;
    }
     
    public String validate(QlPhieuBaoHanh qlPhieuBaoHanh) {
        if (qlPhieuBaoHanh.getTenPhieuBaoHanh().isBlank() || qlPhieuBaoHanh.getMaPhieuBaoHanh().isBlank() || qlPhieuBaoHanh.getMoTa().isBlank()) {
            return "Bạn phải nhâp đủ tất cả các ô trên bảng";
        }  else if (!this.phieuBaoHanhRepositoryImpl.getMaPhieuBaoHanh(qlPhieuBaoHanh.getMaPhieuBaoHanh()).equalsIgnoreCase("")) {
            return "Mã gói bảo hành đã tồn tại";
        }
        return "";
    }
}
