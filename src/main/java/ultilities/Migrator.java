                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultilities;

import java.sql.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Phong
 */
public class Migrator {

//  //Tạo DB trong SQL SERVER = SOFT2041_PTPM
//  //Sau đó tiến hành chạy đển zen bảng
//    public static void main(String[] args) {
//        // Tạo đối tượng ServiceRegistry từ hibernate.cfg.xml
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                .configure("META-INF/hibernate.cfg.xml")
//                .applySetting("hibernate.hbm2ddl.auto", "create")
//                .build();
//
//        // Tạo nguồn siêu dữ liệu (metadata) từ ServiceRegistry
//        // tạo nguồn siêu dữ liệu
//        Metadata metadata = new MetadataSources(serviceRegistry)
//                .getMetadataBuilder().build();
//
//        // tạo lớp giao tiếp với jdbc
//        SessionFactory factory = metadata.getSessionFactoryBuilder().build();
//        Session session = factory.openSession();
//        // tạo giao dịch tương ứng 
//        Transaction trans = session.beginTransaction();
//        
//        MauSac mauSac1 = new MauSac();
//        mauSac1.setMa("MS01");
//        mauSac1.setTen("Red");
//        mauSac1.setId(mauSac1.getId());
//        session.save(mauSac1);
//
//        MauSac mauSac2 = new MauSac();
//        mauSac2.setMa("MS02");
//        mauSac2.setTen("Yellow");
//        mauSac2.setId(mauSac2.getId());
//        session.save(mauSac2);
//
//        MauSac mauSac3 = new MauSac();
//        mauSac3.setMa("MS03");
//        mauSac3.setTen("Blue");
//        mauSac3.setId(mauSac3.getId());
//        session.save(mauSac3);
//
//        MauSac mauSac4 = new MauSac();
//        mauSac4.setMa("MS04");
//        mauSac4.setTen("Black");
//        mauSac4.setId(mauSac4.getId());
//        session.save(mauSac4);
//
//        MauSac mauSac5 = new MauSac();
//        mauSac5.setMa("MS05");
//        mauSac5.setTen("White");
//        mauSac5.setId(mauSac5.getId());
//        session.save(mauSac5);
//
//        DongSP dongSP = new DongSP();
//        dongSP.setTen("Iphone");
//        dongSP.setMa("D01");
//        dongSP.setId(dongSP.getId());
//        session.save(dongSP);
//
//        DongSP dongSP1 = new DongSP();
//        dongSP1.setTen("Sam Sung");
//        dongSP1.setMa("D02");
//        dongSP1.setId(dongSP1.getId());
//        session.save(dongSP1);
//
//        DongSP dongSP2 = new DongSP();
//        dongSP2.setTen("Oppo");
//        dongSP2.setMa("D03");
//        dongSP2.setId(dongSP2.getId());
//        session.save(dongSP2);
//
//        SanPham sanPham = new SanPham();
//        sanPham.setTen("San pham 1");
//        sanPham.setMa("SP01");
//        sanPham.setIdSanPham(sanPham.getIdSanPham());
//        session.save(sanPham);
//
//        SanPham sanPham1 = new SanPham();
//        sanPham1.setTen("San pham 2");
//        sanPham1.setMa("SP02");
//        sanPham1.setIdSanPham(sanPham1.getIdSanPham());
//        session.save(sanPham1);
//
//        SanPham sanPham2 = new SanPham();
//        sanPham2.setTen("San pham 3");
//        sanPham2.setMa("SP03");
//        sanPham2.setIdSanPham(sanPham2.getIdSanPham());
//        session.save(sanPham2);
//
//        NSX nsx = new NSX();
//        nsx.setMa("NSX01");
//        nsx.setTen("Hà Nội");
//        nsx.setId(nsx.getId());
//        session.save(nsx);
//
//        NSX nsx1 = new NSX();
//        nsx1.setMa("NSX02");
//        nsx1.setTen("HCM");
//        nsx1.setId(nsx1.getId());
//        session.save(nsx1);
//
//        NSX nsx2 = new NSX();
//        nsx2.setMa("NSX03");
//        nsx2.setTen("Đà Nẵng");
//        nsx2.setId(nsx2.getId());
//        session.save(nsx2);
//        
//        NSX nsx3 = new NSX();
//        nsx3.setMa("NSX04");
//        nsx3.setTen("Cần Thơ");
//        nsx3.setId(nsx3.getId());
//        session.save(nsx3);
//
//        CuaHang cuaHang = new CuaHang();
//        cuaHang.setMa("CH01");
//        cuaHang.setTen("Cửa Hàng 01");
//        cuaHang.setDiaChi("Hà Nội");
//        cuaHang.setThanhPho("HN");
//        cuaHang.setQuocGia("Việt Nam");
//        cuaHang.setId(cuaHang.getId());
//        session.save(cuaHang);
//
//        CuaHang cuaHang1 = new CuaHang();
//        cuaHang1.setMa("CH02");
//        cuaHang1.setTen("Cửa Hàng 02");
//        cuaHang1.setDiaChi("HCM");
//        cuaHang1.setThanhPho("HCM");
//        cuaHang1.setQuocGia("Việt Nam");
//        cuaHang1.setId(cuaHang1.getId());
//        session.save(cuaHang1);
//
//        CuaHang cuaHang2 = new CuaHang();
//        cuaHang2.setMa("CH03");
//        cuaHang2.setTen("Cửa Hàng 03");
//        cuaHang2.setDiaChi("Da Nang");
//        cuaHang2.setThanhPho("Da Nang");
//        cuaHang2.setQuocGia("Việt Nam");
//        cuaHang2.setId(cuaHang2.getId());
//        session.save(cuaHang2);
//
//        ChucVu chucVu = new ChucVu();
//        chucVu.setMa("CV01");
//        chucVu.setTen("Nhan Vien");
//        chucVu.setId(chucVu.getId());
//        session.save(chucVu);
//
//        ChucVu chucVu1 = new ChucVu();
//        chucVu1.setMa("CV02");
//        chucVu1.setTen("Truong Phong");
//        chucVu1.setId(chucVu1.getId());
//        session.save(chucVu1);
//
//        NhanVien nhanVien = new NhanVien();
//        nhanVien.setHo("Nguyen");
//        nhanVien.setTenDem("Van");
//        nhanVien.setTen("A");
//        nhanVien.setMa("NV01");
//        nhanVien.setSdt("0123456789");
//        nhanVien.setTrangThai(0);
//        nhanVien.setMatKhau("1");
//        nhanVien.setIdCH(cuaHang);
//        nhanVien.setIdCV(chucVu1);
//        nhanVien.setIdGuiBC(nhanVien);
//        nhanVien.setNgaySinh(Date.valueOf("2003-01-21"));
//        nhanVien.setGioiTinh("Nam");
//        nhanVien.setDiaChi("Hà Nội");
//        nhanVien.setId(nhanVien.getId());
//        session.save(nhanVien);
//
//        NhanVien nhanVien1 = new NhanVien();
//        nhanVien1.setHo("Nguyen");
//        nhanVien1.setTenDem("Van");
//        nhanVien1.setTen("B");
//        nhanVien1.setMa("NV02");
//        nhanVien1.setSdt("0123456799");
//        nhanVien1.setTrangThai(0);
//        nhanVien1.setMatKhau("1");
//        nhanVien1.setIdCH(cuaHang1);
//        nhanVien1.setIdCV(chucVu);
//        nhanVien1.setIdGuiBC(nhanVien1);
//        nhanVien1.setNgaySinh(Date.valueOf("2003-01-21"));
//        nhanVien1.setGioiTinh("Nữ");
//        nhanVien1.setDiaChi("Hà Nội");
//        nhanVien1.setId(nhanVien1.getId());
//        session.save(nhanVien1);
//
//        NhanVien nhanVien2 = new NhanVien();
//        nhanVien2.setHo("Nguyen");
//        nhanVien2.setTenDem("Van");
//        nhanVien2.setTen("B");
//        nhanVien2.setMa("NV02");
//        nhanVien2.setSdt("0123456799");
//        nhanVien2.setTrangThai(0);
//        nhanVien2.setMatKhau("1");
//        nhanVien2.setIdCH(cuaHang1);
//        nhanVien2.setIdCV(chucVu);
//        nhanVien2.setIdGuiBC(nhanVien);
//        nhanVien2.setNgaySinh(Date.valueOf("2003-01-21"));
//        nhanVien2.setGioiTinh("Nữ");
//        nhanVien2.setDiaChi("Hà Nội");
//        nhanVien2.setId(nhanVien2.getId());
//        session.save(nhanVien2);
//
//        ChiTietSP chiTietSP = new ChiTietSP();
//        chiTietSP.setIdChiTietSanPham(chiTietSP.getIdChiTietSanPham());
//        chiTietSP.setIdSP(sanPham);
//        chiTietSP.setIdNsx(nsx);
//        chiTietSP.setIdMauSac(mauSac5);
//        chiTietSP.setIdDongSP(dongSP);
//        chiTietSP.setNamBH(2022);
//        chiTietSP.setMoTa("Tốt");
//        chiTietSP.setSoLuongTon(0);
//        chiTietSP.setGiaNhap(new BigDecimal(10000.0));
//        chiTietSP.setGiaBan(new BigDecimal(200000.0));
//        session.save(chiTietSP);
//
//        ChiTietSP chiTietSP1 = new ChiTietSP();
//        chiTietSP1.setIdChiTietSanPham(chiTietSP1.getIdChiTietSanPham());
//        chiTietSP1.setIdSP(sanPham1);
//        chiTietSP1.setIdNsx(nsx1);
//        chiTietSP1.setIdMauSac(mauSac5);
//        chiTietSP1.setIdDongSP(dongSP2);
//        chiTietSP1.setNamBH(2022);
//        chiTietSP1.setMoTa("Tốt");
//        chiTietSP1.setSoLuongTon(0);
//        chiTietSP1.setGiaNhap(new BigDecimal(10000.0));
//        chiTietSP1.setGiaBan(new BigDecimal(200000.0));
//        session.save(chiTietSP1);
//
//        ChiTietSP chiTietSP2 = new ChiTietSP();
//        chiTietSP2.setIdChiTietSanPham(chiTietSP2.getIdChiTietSanPham());
//        chiTietSP2.setIdSP(sanPham);
//        chiTietSP2.setIdNsx(nsx);
//        chiTietSP2.setIdMauSac(mauSac5);
//        chiTietSP2.setIdDongSP(dongSP);
//        chiTietSP2.setNamBH(2022);
//        chiTietSP2.setMoTa("Tốt");
//        chiTietSP2.setSoLuongTon(0);
//        chiTietSP2.setGiaNhap(new BigDecimal(10000.0));
//        chiTietSP2.setGiaBan(new BigDecimal(200000.0));
//        session.save(chiTietSP2);
//        ChiTietSP chiTietSP3 = new ChiTietSP();
//        chiTietSP3.setIdChiTietSanPham(chiTietSP3.getIdChiTietSanPham());
//        chiTietSP3.setIdSP(sanPham);
//        chiTietSP3.setIdNsx(nsx);
//        chiTietSP3.setIdMauSac(mauSac5);
//        chiTietSP3.setIdDongSP(dongSP);
//        chiTietSP3.setNamBH(2022);
//        chiTietSP3.setMoTa("Tốt");
//        chiTietSP3.setSoLuongTon(0);
//        chiTietSP3.setGiaNhap(new BigDecimal(10000.0));
//        chiTietSP3.setGiaBan(new BigDecimal(200000.0));
//        session.save(chiTietSP3);
//
//        ChiTietSP chiTietSP4 = new ChiTietSP();
//        chiTietSP4.setIdChiTietSanPham(chiTietSP4.getIdChiTietSanPham());
//        chiTietSP4.setIdSP(sanPham);
//        chiTietSP4.setIdNsx(nsx);
//        chiTietSP4.setIdMauSac(mauSac5);
//        chiTietSP4.setIdDongSP(dongSP);
//        chiTietSP4.setNamBH(2022);
//        chiTietSP4.setMoTa("Tốt");
//        chiTietSP4.setSoLuongTon(0);
//        chiTietSP4.setGiaNhap(new BigDecimal(10000.0));
//        chiTietSP4.setGiaBan(new BigDecimal(200000.0));
//        session.save(chiTietSP4);
//
//        ChiTietSP chiTietSP5 = new ChiTietSP();
//        chiTietSP5.setIdChiTietSanPham(chiTietSP5.getIdChiTietSanPham());
//        chiTietSP5.setIdSP(sanPham);
//        chiTietSP5.setIdNsx(nsx);
//        chiTietSP5.setIdMauSac(mauSac5);
//        chiTietSP5.setIdDongSP(dongSP);
//        chiTietSP5.setNamBH(2022);
//        chiTietSP5.setMoTa("Tốt");
//        chiTietSP5.setSoLuongTon(0);
//        chiTietSP5.setGiaNhap(new BigDecimal(10000.0));
//        chiTietSP5.setGiaBan(new BigDecimal(200000.0));
//        session.save(chiTietSP5);
//
//        ChiTietSP chiTietSP8 = new ChiTietSP();
//        chiTietSP8.setIdChiTietSanPham(chiTietSP.getIdChiTietSanPham());
//        chiTietSP8.setIdSP(sanPham2);
//        chiTietSP8.setIdNsx(nsx);
//        chiTietSP8.setIdMauSac(mauSac5);
//        chiTietSP8.setIdDongSP(dongSP);
//        chiTietSP8.setNamBH(2022);
//        chiTietSP8.setMoTa("Tốt");
//        chiTietSP8.setSoLuongTon(0);
//        chiTietSP8.setGiaNhap(new BigDecimal(10000.0));
//        chiTietSP8.setGiaBan(new BigDecimal(200000.0));
//        session.save(chiTietSP8);
//
//        KhachHang khachHang = new KhachHang();
//        khachHang.setIdKhachHang(khachHang.getIdKhachHang());
//        khachHang.setMa("KH01");
//        khachHang.setTen("Huy");
//        khachHang.setTenDem("Quoc");
//        khachHang.setHo("Nguyen");
//        khachHang.setNgaySinh(Date.valueOf("2003-04-22"));
//        khachHang.setSdt("0123456789");
//        khachHang.setDiaChi("Ba Vi");
//        khachHang.setThanhPho("Hà Nội");
//        khachHang.setQuocGia("Viet Nam");
//        khachHang.setMatKhau("1");
//        session.save(khachHang);
//
//        KhachHang khachHang1 = new KhachHang();
//        khachHang1.setIdKhachHang(khachHang1.getIdKhachHang());
//        khachHang1.setMa("KH02");
//        khachHang1.setTen("Huy");
//        khachHang1.setTenDem("Quoc");
//        khachHang1.setHo("Nguyen");
//        khachHang1.setNgaySinh(Date.valueOf("2003-04-22"));
//        khachHang1.setSdt("0123456789");
//        khachHang1.setDiaChi("Ba Vi");
//        khachHang1.setThanhPho("Hà Nội");
//        khachHang1.setQuocGia("Viet Nam");
//        khachHang1.setMatKhau("1");
//        session.save(khachHang1);
//
//        KhachHang khachHang2 = new KhachHang();
//        khachHang2.setIdKhachHang(khachHang2.getIdKhachHang());
//        khachHang2.setMa("KH03");
//        khachHang2.setTen("Huy");
//        khachHang2.setTenDem("Quoc");
//        khachHang2.setHo("Nguyen");
//        khachHang2.setNgaySinh(Date.valueOf("2003-04-22"));
//        khachHang2.setSdt("0123456789");
//        khachHang2.setDiaChi("Ba Vi");
//        khachHang2.setThanhPho("Hà Nội");
//        khachHang2.setQuocGia("Viet Nam");
//        khachHang2.setMatKhau("1");
//        session.save(khachHang2);
//
//        GioHang gioHang = new GioHang();
//        gioHang.setIdGioHang(gioHang.getIdGioHang());
//        gioHang.setIdKH(khachHang);
//        gioHang.setIdNV(nhanVien);
//        gioHang.setMa("GH01");
//        gioHang.setNgayTao(Date.valueOf("2022-05-22"));
//        gioHang.setNgayThanhToan(Date.valueOf("2022-06-30"));
//        gioHang.setTenNguoiNhan("Nguyễn Quốc Huy");
//        gioHang.setDiaChhi("Hà Nội");
//        gioHang.setSdt("0123456789");
//        gioHang.setTinhTrang(0);
//        session.save(gioHang);
//
//        GioHang gioHang1 = new GioHang();
//        gioHang1.setIdGioHang(gioHang1.getIdGioHang());
//        gioHang1.setIdKH(khachHang);
//        gioHang1.setIdNV(nhanVien);
//        gioHang1.setMa("GH02");
//        gioHang1.setNgayTao(Date.valueOf("2022-05-22"));
//        gioHang1.setNgayThanhToan(Date.valueOf("2022-06-30"));
//        gioHang1.setTenNguoiNhan("Nguyễn Quốc Huy");
//        gioHang1.setDiaChhi("Hà Nội");
//        gioHang1.setSdt("0123456789");
//        gioHang1.setTinhTrang(1);
//        session.save(gioHang1);
//
//        GioHang gioHang2 = new GioHang();
//        gioHang2.setIdGioHang(gioHang2.getIdGioHang());
//        gioHang2.setIdKH(khachHang1);
//        gioHang2.setIdNV(nhanVien);
//        gioHang2.setMa("GH03");
//        gioHang2.setNgayTao(Date.valueOf("2022-05-22"));
//        gioHang2.setNgayThanhToan(Date.valueOf("2022-06-30"));
//        gioHang2.setTenNguoiNhan("Nguyễn Quốc Huy");
//        gioHang2.setDiaChhi("Hà Nội");
//        gioHang2.setSdt("0123456789");
//        gioHang2.setTinhTrang(0);
//        session.save(gioHang2);
//
//        GioHang gioHang3 = new GioHang();
//        gioHang3.setIdGioHang(gioHang3.getIdGioHang());
//        gioHang3.setIdKH(khachHang2);
//        gioHang3.setIdNV(nhanVien);
//        gioHang3.setMa("GH04");
//        gioHang3.setNgayTao(Date.valueOf("2022-05-22"));
//        gioHang3.setNgayThanhToan(Date.valueOf("2022-06-30"));
//        gioHang3.setTenNguoiNhan("Nguyễn Quốc Huy");
//        gioHang3.setDiaChhi("Hà Nội");
//        gioHang3.setSdt("0123456789");
//        gioHang3.setTinhTrang(0);
//        session.save(gioHang3);
//
//        GioHang gioHang4 = new GioHang();
//        gioHang4.setIdGioHang(gioHang4.getIdGioHang());
//        gioHang4.setIdKH(khachHang2);
//        gioHang4.setIdNV(nhanVien);
//        gioHang4.setMa("GH05");
//        gioHang4.setNgayTao(Date.valueOf("2022-05-22"));
//        gioHang4.setNgayThanhToan(Date.valueOf("2022-06-30"));
//        gioHang4.setTenNguoiNhan("Nguyễn Quốc Huy");
//        gioHang4.setDiaChhi("Hà Nội");
//        gioHang4.setSdt("0123456789");
//        gioHang4.setTinhTrang(0);
//        session.save(gioHang4);
//
//        GioHang gioHang5 = new GioHang();
//        gioHang5.setIdGioHang(gioHang5.getIdGioHang());
//        gioHang5.setIdKH(khachHang);
//        gioHang5.setIdNV(nhanVien);
//        gioHang5.setMa("GH06");
//        gioHang5.setNgayTao(Date.valueOf("2022-05-22"));
//        gioHang5.setNgayThanhToan(Date.valueOf("2022-06-30"));
//        gioHang5.setTenNguoiNhan("Nguyễn Quốc Huy");
//        gioHang5.setDiaChhi("Hà Nội");
//        gioHang5.setSdt("0123456789");
//        gioHang5.setTinhTrang(0);
//        session.save(gioHang5);
//
//        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
//        gioHangChiTiet.setIdGioHang(gioHang);
//        gioHangChiTiet.setIdChiTietSP(chiTietSP);
//        gioHangChiTiet.setSoLuong(2);
//        gioHangChiTiet.setDonGia(new BigDecimal(10000.0));
//        gioHangChiTiet.setDonGiaKhiGiam(new BigDecimal(9000.1));
//        session.save(gioHangChiTiet);
//
//        GioHangChiTiet gioHangChiTiet1 = new GioHangChiTiet();
//        gioHangChiTiet1.setIdGioHang(gioHang1);
//        gioHangChiTiet1.setIdChiTietSP(chiTietSP);
//        gioHangChiTiet1.setSoLuong(2);
//        gioHangChiTiet1.setDonGia(new BigDecimal(10000.0));
//        gioHangChiTiet1.setDonGiaKhiGiam(new BigDecimal(9000.1));
//        session.save(gioHangChiTiet1);
//
//        GioHangChiTiet gioHangChiTiet2 = new GioHangChiTiet();
//        gioHangChiTiet2.setIdGioHang(gioHang);
//        gioHangChiTiet2.setIdChiTietSP(chiTietSP1);
//        gioHangChiTiet2.setSoLuong(2);
//        gioHangChiTiet2.setDonGia(new BigDecimal(10000.0));
//        gioHangChiTiet2.setDonGiaKhiGiam(new BigDecimal(9000.1));
//        session.save(gioHangChiTiet2);
//
//        GioHangChiTiet gioHangChiTiet3 = new GioHangChiTiet();
//        gioHangChiTiet3.setIdGioHang(gioHang1);
//        gioHangChiTiet3.setIdChiTietSP(chiTietSP1);
//        gioHangChiTiet3.setSoLuong(2);
//        gioHangChiTiet3.setDonGia(new BigDecimal(10000.0));
//        gioHangChiTiet3.setDonGiaKhiGiam(new BigDecimal(9000.1));
//        session.save(gioHangChiTiet3);
//
//        HoaDon hd = new HoaDon();
//        hd.setId(hd.getId());
//        hd.setIdKH(khachHang1);
//        hd.setIdNhanVien(nhanVien);
//        hd.setMa("HD01");
//        hd.setNgayTao(Date.valueOf("2022-08-22"));
//        hd.setNgayThanhToan(Date.valueOf("2022-08-22"));
//        hd.setNgayShip(Date.valueOf("2022-08-22"));
//        hd.setNgayNhan(Date.valueOf("2022-08-22"));
//        hd.setTinhTrang(0);
//        hd.setTenNguoiNhan("Huy");
//        hd.setDiaChhi("Hà Nội");
//        hd.setSdt("0123456789");
//        session.save(hd);
//
//        HoaDon hd1 = new HoaDon();
//        hd1.setId(hd.getId());
//        hd1.setIdKH(khachHang);
//        hd1.setIdNhanVien(nhanVien1);
//        hd1.setMa("HD02");
//        hd1.setNgayTao(Date.valueOf("2022-08-22"));
//        hd1.setNgayThanhToan(Date.valueOf("2022-08-22"));
//        hd1.setNgayShip(Date.valueOf("2022-08-22"));
//        hd1.setNgayNhan(Date.valueOf("2022-08-22"));
//        hd1.setTinhTrang(0);
//        hd1.setTenNguoiNhan("Huy");
//        hd1.setDiaChhi("Hà Nội");
//        hd1.setSdt("0123456789");
//        session.save(hd1);
//
//        HoaDon hd2 = new HoaDon();
//        hd2.setId(hd.getId());
//        hd2.setIdKH(khachHang2);
//        hd2.setIdNhanVien(nhanVien1);
//        hd2.setMa("HD03");
//        hd2.setNgayTao(Date.valueOf("2022-08-22"));
//        hd2.setNgayThanhToan(Date.valueOf("2022-08-22"));
//        hd2.setNgayShip(Date.valueOf("2022-08-22"));
//        hd2.setNgayNhan(Date.valueOf("2022-08-22"));
//        hd2.setTinhTrang(0);
//        hd2.setTenNguoiNhan("Huy");
//        hd2.setDiaChhi("Hà Nội");
//        hd2.setSdt("0123456789");
//        session.save(hd2);
//
//        HoaDonChiTiet hdct = new HoaDonChiTiet();
//        hdct.setIdHoaDon(hd);
//        hdct.setIdChiTietSp(chiTietSP);
//        hdct.setSoLuong(2);
//        hdct.setDonGia(new BigDecimal(10000.0));
//        session.save(hdct);
//
//        HoaDonChiTiet hdct1 = new HoaDonChiTiet();
//        hdct1.setIdHoaDon(hd1);
//        hdct1.setIdChiTietSp(chiTietSP);
//        hdct1.setSoLuong(2);
//        hdct1.setDonGia(new BigDecimal(10000.0));
//        session.save(hdct1);
//
//        HoaDonChiTiet hdct2 = new HoaDonChiTiet();
//        hdct2.setIdHoaDon(hd);
//        hdct2.setIdChiTietSp(chiTietSP1);
//        hdct2.setSoLuong(2);
//        hdct2.setDonGia(new BigDecimal(10000.0));
//        session.save(hdct2);
//        
//        // db generator : gen bảng tự động
//       
//        
//        trans.commit();
//        session.close();
//    }
}
