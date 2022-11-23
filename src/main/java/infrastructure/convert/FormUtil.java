/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infrastructure.convert;

import domainmodels.CauHinh;
import domainmodels.ChiTietSanPham;
import domainmodels.ChucVu;
import domainmodels.DongSp;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.KhachHang;
import domainmodels.ManHinh;
import domainmodels.MauSac;
import domainmodels.NSX;
import domainmodels.Ram;
import domainmodels.Rom;
import domainmodels.User;
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
import reponces.QlUser;
import reponces.QlSanPham;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;
import reponces.QLRam;
import reponces.QlCauHinh;
import reponces.QlManHinh;
import reponces.QlRom;

/**
 *
 * @author Admin
 * @param <K>
 * @param <V>
 */
public class FormUtil {

    public static QlUser convertFromNhanVienToQlNhanVien(User nhanVien) {
        ModelMapper mapper = new ModelMapper();
        QlUser qlNhanVien = mapper.map(nhanVien, QlUser.class);
        return qlNhanVien;
    }

    public static User convertFromQlNhanVienToNhanVien(QlUser qlNhanVien) {
        ModelMapper mapper = new ModelMapper();
        User nv = mapper.map(qlNhanVien, User.class);
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

    public static Date convertDateToString(String ngay) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(ngay);
        } catch (ParseException ex) {
            Logger.getLogger(FormUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }

    public static HoaDonChiTiet convertQlHoaDonChiTietToHoaDonChiTiet(QlHoaDonChiTiet qlHoaDonChiTiet) {
        ModelMapper mapper = new ModelMapper();
        HoaDonChiTiet hoaDonChiTiet = mapper.map(qlHoaDonChiTiet, HoaDonChiTiet.class);
        return hoaDonChiTiet;
    }

    public static QlHoaDonChiTiet convertHoaDonChiTietToQLHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        ModelMapper mapper = new ModelMapper();
        QlHoaDonChiTiet qlHoaDonChiTiet = mapper.map(hoaDonChiTiet, QlHoaDonChiTiet.class);
        return qlHoaDonChiTiet;
    }

    public static QLRam convertRamToQLRam(Ram ram) {
        ModelMapper mapper = new ModelMapper();
        QLRam qlRam = mapper.map(ram, QLRam.class);
        return qlRam;
    }

    public static Ram convertQLRamToRam(QLRam qlRam) {
        ModelMapper mapper = new ModelMapper();
        Ram ram = mapper.map(qlRam, Ram.class);
        return ram;
    }

    public static QlRom convertQlRomToRom(Rom rom) {
        ModelMapper mapper = new ModelMapper();
        QlRom qlRom = mapper.map(rom, QlRom.class);
        return qlRom;
    }

    public static Rom convertRomToQLRom(QlRom qlRom) {
        ModelMapper mapper = new ModelMapper();
        Rom rom = mapper.map(qlRom, Rom.class);
        return rom;
    }

    public static QlManHinh convertQlManHinhToManHinh(ManHinh manHinh) {
        ModelMapper mapper = new ModelMapper();
        QlManHinh qlManHinh = mapper.map(manHinh, QlManHinh.class);
        return qlManHinh;
    }

    public static ManHinh convertManHinhToQLManHinh(QlManHinh qlManHinh) {
        ModelMapper mapper = new ModelMapper();
        ManHinh manHinh = mapper.map(qlManHinh, ManHinh.class);
        return manHinh;
    }

    public static QlCauHinh convertQlCauHinhToCauHinh(CauHinh cauHinh) {
        ModelMapper mapper = new ModelMapper();
        QlCauHinh qlCauHinh = mapper.map(cauHinh, QlCauHinh.class);
        return qlCauHinh;
    }

    public static CauHinh convertCauHinhToQLCauHinh(CauHinh qlCauHinh) {
        ModelMapper mapper = new ModelMapper();
        CauHinh cauHinh = mapper.map(qlCauHinh, CauHinh.class);
        return cauHinh;
    }

    public static QlMauSac convertQlMauSacToCauHinh(MauSac mauSac) {
        ModelMapper mapper = new ModelMapper();
        QlMauSac qlMauSac = mapper.map(mauSac, QlMauSac.class);
        return qlMauSac;
    }

    public static MauSac convertCauHinhToQLCauHinh(QlMauSac qlMauSac) {
        ModelMapper mapper = new ModelMapper();
        MauSac mauSac = mapper.map(qlMauSac, MauSac.class);
        return mauSac;
    }

   
}
