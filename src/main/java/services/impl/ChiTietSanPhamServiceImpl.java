/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import Support.SubMessTypeOpt;
import domainmodels.ChiTietSanPham;
import domainmodels.DongSp;
import domainmodels.MauSac;
import domainmodels.NSX;
import domainmodels.SanPham;
import responsitiories.impl.ChiTietSanPhamReponsitoryImpl;
import services.IService;
import reponces.QlChiTietSanPham;
import reponces.QlHoaDonChiTiet;
import infrastructure.convert.FormUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import services.IChiTietSPService;
import ultilities.HibernateUtil;

/**
 *
 * @author Admin
 */
public class ChiTietSanPhamServiceImpl implements IChiTietSPService {

    @Override
    public List<ChiTietSanPham> getAllData() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From ChiTietSP";
            Query<ChiTietSanPham> query = session.createQuery(queryCheck);
            return query.getResultList();
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus addChiTietSP(ChiTietSanPham item) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryCheck = "From ChiTietSanPham item where item.ma = :opt";
            Query<ChiTietSanPham> query = session.createQuery(queryCheck);
            query.setParameter("opt", item.getMa());
            if (query.getResultList().size() > 0) {
                return SubMessTypeOpt.TypeStatus.MATONTAI;
            }
            String strMS = "From MauSac ms where ms.id = :opt";
            Query<MauSac> queryMS = session.createQuery(strMS);
            queryMS.setParameter("opt", item.getIdMauSac());
            MauSac mauSac = queryMS.getSingleResult();
            String strSanPham = "From SanPham ms where ms.id = :opt";
            Query<SanPham> querySP = session.createQuery(strSanPham);
            querySP.setParameter("opt", item.getIdSanPham());
            SanPham sanPham = querySP.getSingleResult();
            String strNSX = "From NSX ms where ms.id = :opt";
            Query<NSX> queryNSX = session.createQuery(strNSX);
            queryNSX.setParameter("opt", item.getIdNsx());
            NSX nsx = queryNSX.getSingleResult();
            String strDongSP = "From DongSp ms where ms.id = :opt";
            Query<DongSp> queryDongSP = session.createQuery(strDongSP);
            queryDongSP.setParameter("opt", item.getIdDongSp());
            DongSp dongSP = queryDongSP.getSingleResult();
            item.setIdDongSp(dongSP);
            item.setIdMauSac(mauSac);
            item.setIdNsx(nsx);
            item.setIdSanPham(sanPham);
            session.getTransaction().begin();
            session.save(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.THEMTHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus remakeChiTietSP(ChiTietSanPham item) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From ChiTietSanPham item where item.ma = :opt";
            Query<ChiTietSanPham> query = session.createQuery(queryStr);
            query.setParameter("opt", item.getMa());
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            ChiTietSanPham itemDB = query.getSingleResult();
            String strMS = "From MauSac ms where ms.id = :opt";
            Query<MauSac> queryMS = session.createQuery(strMS);
            queryMS.setParameter("opt", item.getIdMauSac());
            MauSac mauSac = queryMS.getSingleResult();
            String strSanPham = "From SanPham ms where ms.id = :opt";
            Query<SanPham> querySP = session.createQuery(strSanPham);
            querySP.setParameter("opt", item.getIdSanPham());
            SanPham sanPham = querySP.getSingleResult();
            String strNSX = "From NSX ms where ms.id = :opt";
            Query<NSX> queryNSX = session.createQuery(strNSX);
            queryNSX.setParameter("opt", item.getIdNsx());
            NSX nsx = queryNSX.getSingleResult();
            String strDongSP = "From DongSP ms where ms.id = :opt";
            Query<DongSp> queryDongSP = session.createQuery(strDongSP);
            queryDongSP.setParameter("opt", item.getIdDongSp());
            DongSp dongSP = queryDongSP.getSingleResult();
            itemDB.setIdDongSp(dongSP);
            itemDB.setIdMauSac(mauSac);
            itemDB.setIdNsx(nsx);
            itemDB.setIdSanPham(sanPham);
            itemDB.setMoTa(item.getMoTa());
            itemDB.setSoLuongTon(item.getSoLuongTon());
            itemDB.setDonGia(item.getDonGia());

            session.beginTransaction();
            session.update(itemDB);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.UPDATETHANHCONG;
        }
    }

    @Override
    public SubMessTypeOpt.TypeStatus deleteChiTietSP(String code) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryStr = "From ChiTietSP item where item.ma = :opt";
            Query<ChiTietSanPham> query = session.createQuery(queryStr);
            query.setParameter("opt", code);
            if (query.getResultList().size() == 0) {
                return SubMessTypeOpt.TypeStatus.KHONGTONTAI;
            }
            ChiTietSanPham item = query.getSingleResult();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            return SubMessTypeOpt.TypeStatus.XOATHANHCONG;
        }
    }

}
