/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infrastructure.convert;

import domainmodels.ChiTietSanPham;
import domainmodels.ChucVu;
import domainmodels.CuaHang;
import domainmodels.DongSp;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.KhachHang;
import domainmodels.MauSac;
import domainmodels.NSX;
import domainmodels.NhanVien;
import domainmodels.SanPham;
import reponces.QlChiTietSanPham;
import reponces.QlChucVu;
import reponces.QlCuaHang;
import reponces.QlDongSp;
import reponces.QlGioHang;
import reponces.QlHoaDon;
import reponces.QlHoaDonChiTiet;
import reponces.QlKhachHang;
import reponces.QlMauSac;
import reponces.QlNhaSanXuat;
import reponces.QlNhanVien;
import reponces.QlSanPham;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Admin
 * @param <K>
 * @param <V>
 */
public class FormUtil {

    public static QlNhanVien convertFromNhanVienToQlNhanVien(NhanVien nhanVien) {
        ModelMapper mapper = new ModelMapper();
        QlNhanVien qlNhanVien = mapper.map(nhanVien, QlNhanVien.class);
        return qlNhanVien;
    }

    public static NhanVien convertFromQlNhanVienToNhanVien(QlNhanVien qlNhanVien) {
        ModelMapper mapper = new ModelMapper();
        NhanVien nv = mapper.map(qlNhanVien, NhanVien.class);
        nv.setId(qlNhanVien.getId());
        return nv;
    }

    public static QlDongSp convertFromDongSpToQlDongSp(DongSp dongSp) {
        ModelMapper mapper = new ModelMapper();
        QlDongSp qlDongSp = mapper.map(dongSp, QlDongSp.class);
        return qlDongSp;
    }

    public static DongSp convertFromQlDongSpToDongSp(QlDongSp qlDongSp) {
        ModelMapper mapper = new ModelMapper();
        DongSp dongSp = mapper.map(qlDongSp, DongSp.class);
        dongSp.setId(qlDongSp.getId());
        return dongSp;
    }

    public static QlMauSac convertFromQlMauSacToMauSac(MauSac mauSac) {
        ModelMapper mapper = new ModelMapper();
        QlMauSac qlMauSac = mapper.map(mauSac, QlMauSac.class);
        return qlMauSac;
    }

    public static MauSac convertFromQlMauSacToMauSac(QlMauSac qlMauSac) {
        ModelMapper mapper = new ModelMapper();
        MauSac mauSac = mapper.map(qlMauSac, MauSac.class);
        mauSac.setId(qlMauSac.getId());
        return mauSac;
    }

    public static QlNhaSanXuat convertFromNhaSanXuatToQlNhaSanXuat(NSX nhaSanXuat) {
        ModelMapper mapper = new ModelMapper();
        QlNhaSanXuat qlNhaSanXuat = mapper.map(nhaSanXuat, QlNhaSanXuat.class);
        return qlNhaSanXuat;
    }

    public static NSX convertFromQlNhaSanXuatToNSX(QlNhaSanXuat qlNhaSanXuat) {
        ModelMapper mapper = new ModelMapper();
        NSX nsx = mapper.map(qlNhaSanXuat, NSX.class);
        nsx.setId(qlNhaSanXuat.getId());
        return nsx;
    }

    public static QlCuaHang convertFromCuaHangToQlCuaHang(CuaHang cuaHang) {
        ModelMapper mapper = new ModelMapper();
        QlCuaHang qlCuaHang = mapper.map(cuaHang, QlCuaHang.class);
        return qlCuaHang;
    }

    public static CuaHang convertFromQlCuaHangToCuaHang(QlCuaHang qlCuaHang) {
        ModelMapper mapper = new ModelMapper();
        CuaHang cuaHang = mapper.map(qlCuaHang, CuaHang.class);
        cuaHang.setId(qlCuaHang.getId());
        return cuaHang;
    }

    public static QlHoaDon convertFromHoaDonToQlHoaDon(HoaDon hoaDon) {
        ModelMapper mapper = new ModelMapper();
        QlHoaDon qlHoaDon = mapper.map(hoaDon, QlHoaDon.class);
        return qlHoaDon;
    }

    public static HoaDon convertFromQlHoaDonToHoaDon(QlHoaDon qlHoaDon) {
        ModelMapper mapper = new ModelMapper();
        HoaDon hoaDon = mapper.map(qlHoaDon, HoaDon.class);
        hoaDon.setId(qlHoaDon.getId());
        return hoaDon;
    }

    public static QlKhachHang convertFromKhachHangToQlKhachHang(KhachHang khachHang) {
        ModelMapper mapper = new ModelMapper();
        QlKhachHang qkh = mapper.map(khachHang, QlKhachHang.class);
        return qkh;
    }

    public static KhachHang convertFromQlKhachHangToKhachHang(QlKhachHang qlKhachHang) {
        ModelMapper mapper = new ModelMapper();
        KhachHang khachHang = mapper.map(qlKhachHang, KhachHang.class);
        khachHang.setId(qlKhachHang.getId());
        return khachHang;
    }

    public static QlSanPham convertFromSanPhamToQlSanPham(SanPham sanPham) {
        ModelMapper mapper = new ModelMapper();
        QlSanPham qlSanPham = mapper.map(sanPham, QlSanPham.class);
        return qlSanPham;
    }

    public static SanPham convertFromQlSanPhamToSanPham(QlSanPham qlSanPham) {
        ModelMapper mapper = new ModelMapper();
        SanPham sanPham = mapper.map(qlSanPham, SanPham.class);
        sanPham.setId(qlSanPham.getId());
        return sanPham;
    }

    public static QlChucVu convertFromChucVuToQlChucVu(ChucVu chucVu) {
        ModelMapper mapper = new ModelMapper();
        QlChucVu qlChucVu = mapper.map(chucVu, QlChucVu.class);
        return qlChucVu;
    }

    public static ChucVu convertFromQlChucVuToChucVu(QlChucVu qlChucVu) {
        ModelMapper mapper = new ModelMapper();
        ChucVu chucVu = mapper.map(qlChucVu, ChucVu.class);
        chucVu.setId(qlChucVu.getId());
        return chucVu;
    }

     public static QlChiTietSanPham convertFromChiTietSanPhamToQlChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        ModelMapper mapper = new ModelMapper();
        QlChiTietSanPham qlChiTietSanPham = mapper.map(chiTietSanPham, QlChiTietSanPham.class);
        return qlChiTietSanPham;
    }

    public static ChiTietSanPham convertFromQlChiTietSanPhamToChiTietSanPham(QlChiTietSanPham qlChiTietSanPham) {
        ModelMapper mapper = new ModelMapper();
        ChiTietSanPham chiTietSanPham = mapper.map(qlChiTietSanPham, ChiTietSanPham.class);
        chiTietSanPham.setId(qlChiTietSanPham.getId());
        return chiTietSanPham;
    }
    
    
    public static Date convertDateToString(String ngay){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(ngay);
        } catch (ParseException ex) {
            Logger.getLogger(FormUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static String convertDateToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }
    
    public static HoaDonChiTiet convertQlHoaDonChiTietToHoaDonChiTiet(QlHoaDonChiTiet qlHoaDonChiTiet){
        ModelMapper mapper = new ModelMapper();
        HoaDonChiTiet hoaDonChiTiet = mapper.map(qlHoaDonChiTiet, HoaDonChiTiet.class);
        return hoaDonChiTiet;
    }
    
      public static QlHoaDonChiTiet convertHoaDonChiTietToQLHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet){
        ModelMapper mapper = new ModelMapper();
        QlHoaDonChiTiet qlHoaDonChiTiet = mapper.map(hoaDonChiTiet, QlHoaDonChiTiet.class);
        return qlHoaDonChiTiet;
    }
}
