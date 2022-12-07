/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import domainmodels.ChiTietKhuyenMai;
import domainmodels.ChiTietSanPham;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.Imei;
import domainmodels.ImeiDaBan;
import infrastructure.convert.FormUtil;
import infrastructure.responce.QlHoaDonChiTietReponce;
import infrastructure.responce.QlThongKeResponce;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import reponces.QlChiTietKhuyenMai;
import reponces.QlChiTietSanPham;
import reponces.QlChucVu;
import reponces.QlHinhThucThanhToan;
import reponces.QlHoaDon;
import reponces.QlHoaDonChiTiet;
import reponces.QlKhachHang;
import reponces.QlKhuyenMai;
import reponces.QlPhieuBaoHanh;
import reponces.QlImei;
import reponces.QlImeiDaBan;
import reponces.QlUser;
import responsitiories.impl.HinhThucThanhToanReponsitoryImpl;
import services.impl.ChiTietKhuyenMaiServiceImpl;
import services.impl.ChiTietSanPhamServiceImpl;
import services.impl.ChucVuServiceImpl;
import services.impl.HinhThucThanhToanServiceImpl;
import services.impl.HoaDonChiTietServiceImpl;
import services.impl.HoaDonServiceImpl;
import services.impl.ImeiDabanServiceImpl;
import services.impl.KhachHangServiceImpl;
import services.impl.KhuyenMaiServiceImpl;
import services.impl.SanPhamServiceImpl;
import services.impl.UserServiceImpl;
import static views.test.getBillingandShippingCell;
import static views.test.getCell10fLeft;
import static views.test.getHeaderTextCell;
import static views.test.getHeaderTextCellValue;
import javax.swing.table.TableCellRenderer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class ViewCuaHangBanDienThoai extends javax.swing.JFrame {

    private HoaDonChiTietServiceImpl hoaDonChiTietServiceImpl;
    private KhuyenMaiServiceImpl khuyenMaiServiceImpl;
    private UserServiceImpl userServiceImpl;
    private List<QlKhuyenMai> qlKhuyenMais;
    private KhachHangServiceImpl khachHangServiceImpl;
    private ChiTietKhuyenMaiServiceImpl chiTietKhuyenMaiServiceImpl;
    private HinhThucThanhToanServiceImpl hinhThucThanhToanServiceImpl;
    private ChucVuServiceImpl chucVuServiceImpl;
    // default table model
    private DefaultTableModel tableModelKhuyenMaiSanPham;
    private DefaultTableModel tableModelHoaDonSanPham;
    private DefaultTableModel tableModelBanHangDonHangCho;
    private DefaultTableModel tableModelBanHangGioHang;
    private DefaultTableModel tableModelSanPhamSanPham;
    private DefaultTableModel tableModelHoaDonHoaDon;
    private DefaultTableModel tableModelHoaDonHoaDonChiTiet;
    private DefaultTableModel tableModelKhuyenMaiKhuyenMai;
    private DefaultTableModel tableModelThongKeThongKe;
    private DefaultTableModel tableModelKhachHangCaNhan;
    private DefaultTableModel tableModelKhachHangLichSuGiaoDich;
    private DefaultTableModel tableModelNhanVienNhanVien;

    private HoaDonServiceImpl hoaDonServiceImpl;
    private Double tongTienSauKhiGiam = 0.0;
    private ChiTietSanPhamServiceImpl chiTietSanPhamServiceImpl;
    // default combobox
    private DefaultComboBoxModel comboboxBanHangKhuyenMai;
    private DefaultComboBoxModel comBoMoxBanHangBaoHanh;
    private DefaultComboBoxModel comBoBoxBaoHanhGoiBaoHanh;
    private QlUser qlUser = new QlUser();
    private QlKhachHang qlKhachHang = new QlKhachHang();
    private int viTriSanPhamHienTai = 0;

    private List<QlChiTietSanPham> listChiTietSanPhams = new ArrayList<>();

    public ViewCuaHangBanDienThoai() {
        initComponents();
        setColorLabel();
        userServiceImpl = new UserServiceImpl();
        this.qlUser = userServiceImpl.findOne("NV1");
        this.labelBanHang.setBackground(Color.white);
        this.labelBanHang.setOpaque(true);
        init();
        // set khi là khách lẻ
        if (rbKhachLe.isSelected()) {
            // truờng hợp đây là khách lẻ thì mình sẽ xử lý khác 
            this.seperatorBanHangKhachHang.setVisible(false);
            setUIKhachLe();
        }

    }

    public ViewCuaHangBanDienThoai(QlUser qlUser, QlKhachHang qlKhachHang) {
        // trường hợp khách hàng chọn khách mua 
        initComponents();
        setColorLabel();
        // đăng nhập
        this.qlUser = qlUser;
        this.labelTenDangNhap.setText(this.qlUser.getTen() + "-" + qlUser.getIdChucVu().getTen());
        this.labelDonHangNhanVien.setText(this.qlUser.getTen());
        this.labelAnh.setIcon(new ImageIcon(qlUser.getAnh()));

        // khuyến mãi 
        this.qlKhuyenMais = new ArrayList<>();

        // set selected panel
        this.labelBanHang.setBackground(Color.white);
        this.labelBanHang.setOpaque(true);
        init();

        // set khi là khách lẻ
        if (rbKhachLe.isSelected()) {
            // truờng hợp đây là khách lẻ thì mình sẽ xử lý khác 
            this.seperatorBanHangKhachHang.setVisible(false);
            setUIKhachLe();
        }

        // set khách mua
        this.qlKhachHang = qlKhachHang;
        if (qlKhachHang != null) {
            this.rbKhachMua.setSelected(true);
            setUIKhachMua();// trường hợp đây là khách mua s
            this.txtBanHangMaKhachHang.setText(qlKhachHang.getMa() + "-" + qlKhachHang.getTen());
        }

        // set pannel nhân viên
        if (qlUser.getIdChucVu().getMa().equalsIgnoreCase("NV")) {
            labelNhanVien.setVisible(false);
            pannelNhanVien.setVisible(false);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelTenDangNhap = new javax.swing.JLabel();
        labelBanHang = new javax.swing.JLabel();
        labelSanPham = new javax.swing.JLabel();
        labelHoaDon = new javax.swing.JLabel();
        labelKhuyenMai = new javax.swing.JLabel();
        labelNhanvien2 = new javax.swing.JLabel();
        labelAnh = new javax.swing.JLabel();
        labelKhachHang = new javax.swing.JLabel();
        labelNhanVien = new javax.swing.JLabel();
        labelDangXuat = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        pannelSanPham = new javax.swing.JTabbedPane();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableSanPham = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jComboBox8 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox<>();
        jButton10 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jComboBox15 = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jComboBox16 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jComboBox17 = new javax.swing.JComboBox<>();
        jButton13 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox<>();
        jButton14 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        pannelbanHang = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHoaDonSanPham = new javax.swing.JTable();
        txtHoaDonTimKiem = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnThemVaoGioHang = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableBanHangDonHangCho = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableBanHangGioHang = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        btnBanHangXoaSanPham = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelDonHangNhanVien = new javax.swing.JLabel();
        rbKhachLe = new javax.swing.JRadioButton();
        rbKhachMua = new javax.swing.JRadioButton();
        labelBanHangMaKhachHang = new javax.swing.JLabel();
        btnBanHangThemKhachHang = new javax.swing.JButton();
        seperatorBanHangKhachHang = new javax.swing.JSeparator();
        txtBanHangMaKhachHang = new javax.swing.JLabel();
        seperatorBanHangKhachHang1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        labelMaHoaDon = new javax.swing.JLabel();
        btnTaoHoaDonBanHang = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        labelTongTien = new javax.swing.JLabel();
        txtBanHangThanhToan = new javax.swing.JLabel();
        txtBanHangKhuyenMai = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        labelTienThua = new javax.swing.JLabel();
        btnBanHangThanhToan = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        rbBanHangTienMat = new javax.swing.JRadioButton();
        rbBanHangChuyenKhoan = new javax.swing.JRadioButton();
        rbBanHangBitcoin = new javax.swing.JRadioButton();
        btnBanHangHuyHoaDon = new javax.swing.JButton();
        btnPhieuBaoHanh3 = new javax.swing.JButton();
        panelQr = new javax.swing.JPanel();
        panelHoaDon = new javax.swing.JTabbedPane();
        cbDongSanPham2 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        txtHoaDonTenKhachHang = new javax.swing.JTextField();
        labelAnhSanPham2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cbHoaDonTrangThaiThanhToan = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableHoaDonHoaDon = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableHoaDonHoaDonChiTiet = new javax.swing.JTable();
        pannelKhuyenMai = new javax.swing.JPanel();
        pannelKhuyenMaiChuongTrinhKhuyenMai = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtKhuyenMaiMaChuongTrinh = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtKhuyenMaiTenKhuyenMai = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        cbKhuyenMaiHinhThucGiamGia = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        txtKhuyenMaiGiamGia = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableKhuyenMaiSanPham = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtKhuyenMaiThoiGianBatDau = new com.toedter.calendar.JDateChooser();
        txtKhuyenMaiThoiGianKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel39 = new javax.swing.JLabel();
        btnKhuyenMaiLuu = new javax.swing.JButton();
        btnKhuyenmaiSua = new javax.swing.JButton();
        labelKhuyenMaiLamMoi = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtKhuyenMaiMoTa = new javax.swing.JTextArea();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tableKhuyenMaiKhuyenMai = new javax.swing.JTable();
        pannelKhachHang = new javax.swing.JPanel();
        pannelKhuyenMaiChuongTrinhKhuyenMai2 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtKhachHangTenKhachHang = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txtKhachHangSoDienThoai = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtKhachHangDiaChi = new javax.swing.JTextArea();
        btnKhachHangThem = new javax.swing.JButton();
        btnKhachHangSua = new javax.swing.JButton();
        btnKhachHangXoa = new javax.swing.JButton();
        txtKhachHangMa = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tableKhachHangLichSuGiaoDich = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tableKhachHangCaNhan = new javax.swing.JTable();
        jLabel53 = new javax.swing.JLabel();
        txtKhachHangTimKiem = new javax.swing.JTextField();
        btnExportExcel = new javax.swing.JButton();
        pannelThongKe = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        labelTongDoanhThu = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        labelSoHoaDon = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        labelSoHoaDonBiHuy = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        labelSoKhachHang = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelThongKeHangHoa = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tableThongKeThongKe = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel26 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        btnThongKeLoc = new javax.swing.JButton();
        txtThongKeNgayBatDau = new com.toedter.calendar.JDateChooser();
        txtThongKeNgayKetThuc = new com.toedter.calendar.JDateChooser();
        pannelNhanVien = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tableNhanVienNhanVien = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        txthoten = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        txtdiachi = new javax.swing.JTextField();
        txtmatkhau = new javax.swing.JTextField();
        rdnam = new javax.swing.JRadioButton();
        rdnu = new javax.swing.JRadioButton();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        txtcccd = new javax.swing.JTextField();
        rdhoatdong = new javax.swing.JRadioButton();
        rdkhonghoatdong = new javax.swing.JRadioButton();
        rdnhanvien = new javax.swing.JRadioButton();
        btnthem = new javax.swing.JButton();
        rdquanli = new javax.swing.JRadioButton();
        jLabel68 = new javax.swing.JLabel();
        btnsua = new javax.swing.JButton();
        jLabel69 = new javax.swing.JLabel();
        btnmoi = new javax.swing.JButton();
        txttimkiemten = new javax.swing.JTextField();
        btnxoa = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel24 = new javax.swing.JPanel();
        labelNhanVienAnh = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        labelNhanVienMaNhanVien = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cửa hàng bán điện thoại");

        jPanel1.setBackground(Color.PINK);

        jLabel1.setAlignmentX(0.5F);

        labelTenDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        labelTenDangNhap.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        labelTenDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTenDangNhap.setText("Hoàng Văn Tám");

        labelBanHang.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelBanHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBanHang.setText("Bán hàng");
        labelBanHang.setOpaque(true);
        labelBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelBanHangMousePressed(evt);
            }
        });

        labelSanPham.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSanPham.setText("Sản Phẩm");
        labelSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelSanPhamMousePressed(evt);
            }
        });

        labelHoaDon.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHoaDon.setText("HóaĐơn");
        labelHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelHoaDonMousePressed(evt);
            }
        });

        labelKhuyenMai.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelKhuyenMai.setText("Khuyến mại");
        labelKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelKhuyenMaiMousePressed(evt);
            }
        });

        labelNhanvien2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelNhanvien2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNhanvien2.setText("Thống kê");
        labelNhanvien2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelNhanvien2MousePressed(evt);
            }
        });

        labelKhachHang.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelKhachHang.setText("Khách Hàng");
        labelKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelKhachHangMousePressed(evt);
            }
        });

        labelNhanVien.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNhanVien.setText("Nhân viên");
        labelNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelNhanVienMousePressed(evt);
            }
        });

        labelDangXuat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelDangXuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDangXuat.setText("Đăng xuất");
        labelDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelDangXuatMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelNhanvien2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelBanHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(labelAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
            .addComponent(labelKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(labelAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNhanvien2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );

        jLayeredPane1.setLayout(new java.awt.CardLayout());

        tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tableSanPham);

        jLabel3.setText("Tìm kiếm:");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel15.setText("Bộ nhớ trong:");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("jButton2");

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton3.setText("jButton2");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel17.setText("Màn hình");

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton4.setText("jButton2");

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel20.setText("Ram");

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton5.setText("jButton2");

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel21.setText("TÊN SẢN PHẨM");

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton6.setText("jButton2");

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel22.setText("Hệ điều hành");

        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton8.setText("jButton2");

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel23.setText("CPU");

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton9.setText("jButton2");

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel24.setText("Camera");

        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton10.setText("jButton2");

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel25.setText("Nhà sản xuất");

        jButton11.setText("jButton2");

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel26.setText("Pin");

        jButton12.setText("jButton2");

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel27.setText("Dòng sản phẩm");

        jLabel41.setBackground(new java.awt.Color(153, 153, 255));
        jLabel41.setText("jLabel41");
        jLabel41.setOpaque(true);

        jLabel42.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel42.setText("Màu sắc");

        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton13.setText("jButton13");

        jLabel43.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel43.setText("GIÁ BÁN:");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel44.setText("DANH SÁCH IMEI");

        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton14.setText("jButton13");

        jLabel45.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel45.setText("TỒN KHO");

        jLabel48.setText("0");

        jLabel49.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("MÔ TẢ:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane10.setViewportView(jTextArea1);

        jButton15.setText("ADD");

        jButton16.setText("EDIT");

        jButton17.setText("DELETE");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox17, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel45))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton17)))))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton13))
                                .addGap(6, 6, 6)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jButton5))
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1)
                                    .addComponent(jButton4))
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1)
                                    .addComponent(jButton3))
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1)
                                    .addComponent(jButton2))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton14)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton15)
                            .addComponent(jButton16)
                            .addComponent(jButton17))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)
                                        .addComponent(jButton6))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)
                                        .addComponent(jButton8))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)
                                        .addComponent(jButton9)))
                                .addGap(7, 7, 7))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jButton10)))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jButton11))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jButton12)))
                        .addGap(49, 49, 49))))
        );

        pannelSanPham.addTab("Sản Phẩm", jPanel16);

        jLayeredPane1.add(pannelSanPham, "card3");

        pannelbanHang.setPreferredSize(new java.awt.Dimension(800, 1100));

        jLabel5.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Bán Hàng");
        jLabel5.setOpaque(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        tableHoaDonSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableHoaDonSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHoaDonSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHoaDonSanPham);

        txtHoaDonTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtHoaDonTimKiemCaretUpdate(evt);
            }
        });

        jLabel6.setText("Tìm kiếm tên sản phẩm");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));

        jLabel4.setText("Xếp theo nhà sản xuất");

        btnThemVaoGioHang.setBackground(new java.awt.Color(255, 204, 204));
        btnThemVaoGioHang.setText("ADD TO CART");
        btnThemVaoGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVaoGioHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtHoaDonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemVaoGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBox1)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtHoaDonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThemVaoGioHang)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn hàng chờ"));

        tableBanHangDonHangCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableBanHangDonHangCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBanHangDonHangChoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableBanHangDonHangCho);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tableBanHangGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableBanHangGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBanHangGioHangMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tableBanHangGioHang);

        jButton7.setBackground(new java.awt.Color(255, 204, 204));
        jButton7.setText("Xóa tất cả");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnBanHangXoaSanPham.setBackground(new java.awt.Color(255, 204, 204));
        btnBanHangXoaSanPham.setText("Xóa sản phẩm");
        btnBanHangXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangXoaSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(btnBanHangXoaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 25, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBanHangXoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N

        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel7.setText("Khách Hàng :");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel8.setText("Nhân viên:");

        labelDonHangNhanVien.setText("CHƯA CÓ AI");

        buttonGroup5.add(rbKhachLe);
        rbKhachLe.setSelected(true);
        rbKhachLe.setText("Khách lẻ");
        rbKhachLe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbKhachLeActionPerformed(evt);
            }
        });

        buttonGroup5.add(rbKhachMua);
        rbKhachMua.setText("Khách mua");
        rbKhachMua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbKhachMuaActionPerformed(evt);
            }
        });

        labelBanHangMaKhachHang.setText("Mã khách hàng:");

        btnBanHangThemKhachHang.setBackground(new java.awt.Color(255, 204, 204));
        btnBanHangThemKhachHang.setText("Thêm");
        btnBanHangThemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangThemKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelBanHangMaKhachHang)
                        .addGap(18, 18, 18)
                        .addComponent(txtBanHangMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnBanHangThemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbKhachLe)
                                .addGap(31, 31, 31)
                                .addComponent(rbKhachMua)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(0, 85, Short.MAX_VALUE)
                                .addComponent(seperatorBanHangKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelDonHangNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap(75, Short.MAX_VALUE)
                    .addComponent(seperatorBanHangKhachHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(22, 22, 22)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDonHangNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbKhachLe)
                        .addComponent(rbKhachMua))
                    .addComponent(seperatorBanHangKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBanHangThemKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelBanHangMaKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBanHangMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap(37, Short.MAX_VALUE)
                    .addComponent(seperatorBanHangKhachHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(58, 58, 58)))
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setText("Mã hóa đơn:");

        labelMaHoaDon.setText("19");

        btnTaoHoaDonBanHang.setBackground(new java.awt.Color(255, 204, 204));
        btnTaoHoaDonBanHang.setText("Tạo ");
        btnTaoHoaDonBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonBanHangActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel11.setText("Tổng tiền :");

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel31.setText("Giảm giá:");

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel32.setText("Tiền khách đưa:");

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel33.setText("Thanh toán:");

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel34.setText("Tiền thừa:");

        labelTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTongTien.setText("0 Đ");

        txtBanHangThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtBanHangThanhToan.setText("0 Đ");

        txtBanHangKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtBanHangKhuyenMai.setText("0 Đ");

        txtTienKhachDua.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaCaretUpdate(evt);
            }
        });

        labelTienThua.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTienThua.setText("0 Đ");

        btnBanHangThanhToan.setBackground(new java.awt.Color(255, 204, 204));
        btnBanHangThanhToan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBanHangThanhToan.setText("Thanh toán");
        btnBanHangThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangThanhToanActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel38.setText("Loại hình thanh toán:");

        buttonGroup1.add(rbBanHangTienMat);
        rbBanHangTienMat.setText("Tiền mặt");

        buttonGroup1.add(rbBanHangChuyenKhoan);
        rbBanHangChuyenKhoan.setText("Chuyển khoản");

        buttonGroup1.add(rbBanHangBitcoin);
        rbBanHangBitcoin.setText("Bitcoin,NFC");

        btnBanHangHuyHoaDon.setBackground(new java.awt.Color(255, 204, 204));
        btnBanHangHuyHoaDon.setText("Hủy hóa đơn");
        btnBanHangHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangHuyHoaDonActionPerformed(evt);
            }
        });

        btnPhieuBaoHanh3.setBackground(new java.awt.Color(255, 204, 204));
        btnPhieuBaoHanh3.setText("Làm mới hóa đơn");
        btnPhieuBaoHanh3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuBaoHanh3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnBanHangHuyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnPhieuBaoHanh3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                                    .addGap(23, 23, 23)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(labelTienThua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtBanHangThanhToan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(rbBanHangTienMat)
                                    .addGap(29, 29, 29)
                                    .addComponent(rbBanHangChuyenKhoan)
                                    .addGap(33, 33, 33)
                                    .addComponent(rbBanHangBitcoin))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                                        .addComponent(jLabel2))
                                    .addGap(23, 23, 23)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(labelTongTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(labelMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnTaoHoaDonBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(txtBanHangKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(btnBanHangThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTaoHoaDonBanHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMaHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(txtBanHangKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBanHangThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtTienKhachDua)
                        .addGap(61, 61, 61))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbBanHangTienMat)
                    .addComponent(rbBanHangChuyenKhoan)
                    .addComponent(rbBanHangBitcoin))
                .addGap(10, 10, 10)
                .addComponent(btnBanHangThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPhieuBaoHanh3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBanHangHuyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout panelQrLayout = new javax.swing.GroupLayout(panelQr);
        panelQr.setLayout(panelQrLayout);
        panelQrLayout.setHorizontalGroup(
            panelQrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        panelQrLayout.setVerticalGroup(
            panelQrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pannelbanHangLayout = new javax.swing.GroupLayout(pannelbanHang);
        pannelbanHang.setLayout(pannelbanHangLayout);
        pannelbanHangLayout.setHorizontalGroup(
            pannelbanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelbanHangLayout.createSequentialGroup()
                .addGroup(pannelbanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pannelbanHangLayout.createSequentialGroup()
                        .addGroup(pannelbanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelbanHangLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(pannelbanHangLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pannelbanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pannelbanHangLayout.createSequentialGroup()
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(panelQr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(12, 12, 12)))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pannelbanHangLayout.setVerticalGroup(
            pannelbanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelbanHangLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pannelbanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pannelbanHangLayout.createSequentialGroup()
                        .addGroup(pannelbanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelQr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );

        jLayeredPane1.add(pannelbanHang, "card2");

        cbDongSanPham2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel47.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel47.setText("Tìm kiếm theo tên KH:");

        txtHoaDonTenKhachHang.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtHoaDonTenKhachHangCaretUpdate(evt);
            }
        });
        txtHoaDonTenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoaDonTenKhachHangActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel9.setText("Trạng thái thanh toán");

        cbHoaDonTrangThaiThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang chờ", "Đã thanh toán", "Đã hủy" }));
        cbHoaDonTrangThaiThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHoaDonTrangThaiThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbHoaDonTrangThaiThanhToan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbHoaDonTrangThaiThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel13.setText("Hình thức thanh toán");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Tiền mặt", "Chuyển khoản", "Bitcoin,NFC" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel14.setText("Tháng ");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox4, 0, 129, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel16.setText("Năm");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox5, 0, 129, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        tableHoaDonHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableHoaDonHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHoaDonHoaDonMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tableHoaDonHoaDon);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn chi tiết"));

        tableHoaDonHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(tableHoaDonHoaDonChiTiet);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout cbDongSanPham2Layout = new javax.swing.GroupLayout(cbDongSanPham2);
        cbDongSanPham2.setLayout(cbDongSanPham2Layout);
        cbDongSanPham2Layout.setHorizontalGroup(
            cbDongSanPham2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cbDongSanPham2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(cbDongSanPham2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(cbDongSanPham2Layout.createSequentialGroup()
                        .addGroup(cbDongSanPham2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cbDongSanPham2Layout.createSequentialGroup()
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoaDonTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(cbDongSanPham2Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAnhSanPham2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
        );
        cbDongSanPham2Layout.setVerticalGroup(
            cbDongSanPham2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cbDongSanPham2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(cbDongSanPham2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoaDonTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cbDongSanPham2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cbDongSanPham2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(cbDongSanPham2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAnhSanPham2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(cbDongSanPham2Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        panelHoaDon.addTab("Hóa đơn", cbDongSanPham2);

        jLayeredPane1.add(panelHoaDon, "card3");

        pannelKhuyenMaiChuongTrinhKhuyenMai.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chương trình khuyến mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DialogInput", 1, 14))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel18.setText("Tên chương trình khuyến mại:");

        txtKhuyenMaiMaChuongTrinh.setEnabled(false);

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel19.setText("Mã chương trình khuyến mại:");

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel28.setText("Hình thức giảm giá:");

        cbKhuyenMaiHinhThucGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giảm giá theo phần trăm", "Giảm giá theo số tiền" }));

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel29.setText("Mức giảm giá:");

        tableKhuyenMaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Select", "Số Imei", "Tên sản phẩm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableKhuyenMaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKhuyenMaiSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableKhuyenMaiSanPham);

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel30.setText("Sản phẩm áp dụng chương trình:");

        javax.swing.GroupLayout pannelKhuyenMaiChuongTrinhKhuyenMaiLayout = new javax.swing.GroupLayout(pannelKhuyenMaiChuongTrinhKhuyenMai);
        pannelKhuyenMaiChuongTrinhKhuyenMai.setLayout(pannelKhuyenMaiChuongTrinhKhuyenMaiLayout);
        pannelKhuyenMaiChuongTrinhKhuyenMaiLayout.setHorizontalGroup(
            pannelKhuyenMaiChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMaiLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKhuyenMaiTenKhuyenMai)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMaiLayout.createSequentialGroup()
                        .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(cbKhuyenMaiHinhThucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKhuyenMaiGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtKhuyenMaiMaChuongTrinh))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        pannelKhuyenMaiChuongTrinhKhuyenMaiLayout.setVerticalGroup(
            pannelKhuyenMaiChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMaiLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtKhuyenMaiMaChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKhuyenMaiTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbKhuyenMaiHinhThucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKhuyenMaiGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thời gian chương trình", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DialogInput", 1, 14))); // NOI18N

        jLabel35.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel35.setText("Thời gian kết thúc chương trình:");

        jLabel36.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel36.setText("Thời gian bắt đầu giảm giá:");

        txtKhuyenMaiThoiGianBatDau.setDateFormatString("yyyy-MM-dd");

        txtKhuyenMaiThoiGianKetThuc.setDateFormatString("yyyy-MM-dd");

        jLabel39.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel39.setText("Mô tả:");

        btnKhuyenMaiLuu.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnKhuyenMaiLuu.setText("Lưu");
        btnKhuyenMaiLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhuyenMaiLuuActionPerformed(evt);
            }
        });

        btnKhuyenmaiSua.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnKhuyenmaiSua.setText("Sửa");

        labelKhuyenMaiLamMoi.setFont(new java.awt.Font("DialogInput", 2, 12)); // NOI18N
        labelKhuyenMaiLamMoi.setForeground(new java.awt.Color(255, 102, 102));
        labelKhuyenMaiLamMoi.setText("Làm mới ");
        labelKhuyenMaiLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelKhuyenMaiLamMoiMousePressed(evt);
            }
        });

        txtKhuyenMaiMoTa.setColumns(20);
        txtKhuyenMaiMoTa.setRows(5);
        jScrollPane9.setViewportView(txtKhuyenMaiMoTa);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(labelKhuyenMaiLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnKhuyenMaiLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                        .addComponent(btnKhuyenmaiSua, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKhuyenMaiThoiGianBatDau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                            .addComponent(txtKhuyenMaiThoiGianKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKhuyenMaiThoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKhuyenMaiThoiGianKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelKhuyenMaiLamMoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKhuyenMaiLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKhuyenmaiSua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách chương trình khuyến mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        tableKhuyenMaiKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(tableKhuyenMaiKhuyenMai);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pannelKhuyenMaiLayout = new javax.swing.GroupLayout(pannelKhuyenMai);
        pannelKhuyenMai.setLayout(pannelKhuyenMaiLayout);
        pannelKhuyenMaiLayout.setHorizontalGroup(
            pannelKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelKhuyenMaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pannelKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pannelKhuyenMaiLayout.createSequentialGroup()
                        .addComponent(pannelKhuyenMaiChuongTrinhKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pannelKhuyenMaiLayout.setVerticalGroup(
            pannelKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelKhuyenMaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pannelKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pannelKhuyenMaiChuongTrinhKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLayeredPane1.add(pannelKhuyenMai, "card5");

        pannelKhuyenMaiChuongTrinhKhuyenMai2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DialogInput", 1, 14))); // NOI18N

        jLabel46.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel46.setText("Tên khách hàng:");

        jLabel50.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel50.setText("Mã khách hàng:");

        jLabel51.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel51.setText("Số diện thoại");

        jLabel52.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel52.setText("Địa chỉ :");

        txtKhachHangDiaChi.setColumns(20);
        txtKhachHangDiaChi.setRows(5);
        jScrollPane12.setViewportView(txtKhachHangDiaChi);

        btnKhachHangThem.setText("Thêm");
        btnKhachHangThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangThemActionPerformed(evt);
            }
        });

        btnKhachHangSua.setText("Sửa");
        btnKhachHangSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangSuaActionPerformed(evt);
            }
        });

        btnKhachHangXoa.setText("Xóa");
        btnKhachHangXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pannelKhuyenMaiChuongTrinhKhuyenMai2Layout = new javax.swing.GroupLayout(pannelKhuyenMaiChuongTrinhKhuyenMai2);
        pannelKhuyenMaiChuongTrinhKhuyenMai2.setLayout(pannelKhuyenMaiChuongTrinhKhuyenMai2Layout);
        pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.setHorizontalGroup(
            pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                        .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnKhachHangThem, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtKhachHangTenKhachHang)
                    .addComponent(txtKhachHangSoDienThoai)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnKhachHangSua, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnKhachHangXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtKhachHangMa))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.setVerticalGroup(
            pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtKhachHangMa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKhachHangTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKhachHangSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pannelKhuyenMaiChuongTrinhKhuyenMai2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKhachHangThem)
                    .addComponent(btnKhachHangSua)
                    .addComponent(btnKhachHangXoa))
                .addContainerGap())
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lịnh sử giao dịch", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        tableKhachHangLichSuGiaoDich.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane14.setViewportView(tableKhachHangLichSuGiaoDich);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin cá nhân"));

        tableKhachHangCaNhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableKhachHangCaNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKhachHangCaNhanMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(tableKhachHangCaNhan);

        jLabel53.setText("Tìm kiếm theo tên:");

        txtKhachHangTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtKhachHangTimKiemCaretUpdate(evt);
            }
        });

        btnExportExcel.setText("Xuất file Excel");
        btnExportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(txtKhachHangTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btnExportExcel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKhachHangTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExportExcel)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout pannelKhachHangLayout = new javax.swing.GroupLayout(pannelKhachHang);
        pannelKhachHang.setLayout(pannelKhachHangLayout);
        pannelKhachHangLayout.setHorizontalGroup(
            pannelKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pannelKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pannelKhachHangLayout.createSequentialGroup()
                        .addComponent(pannelKhuyenMaiChuongTrinhKhuyenMai2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pannelKhachHangLayout.setVerticalGroup(
            pannelKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pannelKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pannelKhuyenMaiChuongTrinhKhuyenMai2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.add(pannelKhachHang, "card5");

        jPanel12.setBackground(new java.awt.Color(255, 153, 153));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Doanh Thu");

        labelTongDoanhThu.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelTongDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTongDoanhThu.setText("34574254");

        jLabel40.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel40.setText("VND");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 153, 153));

        jLabel56.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("Số hóa đơn");

        labelSoHoaDon.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelSoHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelSoHoaDon.setText("34574254");

        jLabel58.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel58.setText("hóa đơn");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel58)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(255, 153, 153));

        jLabel59.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("Số hóa đơn bị hủy");

        labelSoHoaDonBiHuy.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelSoHoaDonBiHuy.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelSoHoaDonBiHuy.setText("34574254");

        jLabel61.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel61.setText("hóa đơn");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSoHoaDonBiHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel61)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSoHoaDonBiHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(255, 153, 153));

        jLabel62.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("Tổng khách hàng");

        labelSoKhachHang.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelSoKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelSoKhachHang.setText("34574254");

        jLabel64.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel64.setText("khách hàng");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSoKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel64)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSoKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableThongKeThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane15.setViewportView(tableThongKeThongKe);

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder("Giá bán"));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Dưới 2 triệu", "Từ 2 - 10 triệu", "Trên 10 triệu" }));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2, 0, 196, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2)
                .addContainerGap())
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm theo tên"));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1)
                .addContainerGap())
        );

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhà sản xuất"));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Samsung", "IPhone" }));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox7, 0, 230, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox7)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelThongKeHangHoaLayout = new javax.swing.GroupLayout(panelThongKeHangHoa);
        panelThongKeHangHoa.setLayout(panelThongKeHangHoaLayout);
        panelThongKeHangHoaLayout.setHorizontalGroup(
            panelThongKeHangHoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongKeHangHoaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15)
                .addContainerGap())
            .addGroup(panelThongKeHangHoaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
        );
        panelThongKeHangHoaLayout.setVerticalGroup(
            panelThongKeHangHoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThongKeHangHoaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelThongKeHangHoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Hàng hóa", panelThongKeHangHoa);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setText("Thống kê theo :");

        jLabel57.setText("Ngày kết thúc:");

        jLabel60.setText("Ngày bắt đầu:");

        btnThongKeLoc.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnThongKeLoc.setText("Lọc");
        btnThongKeLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeLocActionPerformed(evt);
            }
        });

        txtThongKeNgayBatDau.setDateFormatString("yyyy-MM-dd");

        txtThongKeNgayKetThuc.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout pannelThongKeLayout = new javax.swing.GroupLayout(pannelThongKe);
        pannelThongKe.setLayout(pannelThongKeLayout);
        pannelThongKeLayout.setHorizontalGroup(
            pannelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelThongKeLayout.createSequentialGroup()
                .addGroup(pannelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pannelThongKeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pannelThongKeLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(pannelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pannelThongKeLayout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pannelThongKeLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(txtThongKeNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtThongKeNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btnThongKeLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)))))
                .addGap(27, 27, 27))
        );
        pannelThongKeLayout.setVerticalGroup(
            pannelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelThongKeLayout.createSequentialGroup()
                .addGroup(pannelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pannelThongKeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pannelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelThongKeLayout.createSequentialGroup()
                                .addComponent(btnThongKeLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addComponent(txtThongKeNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pannelThongKeLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(pannelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtThongKeNgayBatDau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pannelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(pannelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jLayeredPane1.add(pannelThongKe, "card5");

        tableNhanVienNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableNhanVienNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNhanVienNhanVienMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tableNhanVienNhanVien);

        jLabel54.setText("Địa chỉ");

        jLabel55.setText("Mật khẩu");

        jLabel63.setText("Giới tính");

        rdnam.setText("Nam");

        rdnu.setText("Nữ");

        jLabel65.setText("CCCD");

        jLabel66.setText("Trạng thái");

        jLabel67.setText("Chức vụ");

        buttonGroup3.add(rdhoatdong);
        rdhoatdong.setText("Hoạt động");

        buttonGroup3.add(rdkhonghoatdong);
        rdkhonghoatdong.setText("Không hoạt động");

        buttonGroup4.add(rdnhanvien);
        rdnhanvien.setText("Nhân viên");

        btnthem.setBackground(new java.awt.Color(255, 204, 204));
        btnthem.setText("Thêm");
        btnthem.setToolTipText("");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        buttonGroup4.add(rdquanli);
        rdquanli.setText("Quản lí");

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("QUẢN LÍ NHÂN VIÊN");

        btnsua.setBackground(new java.awt.Color(255, 204, 204));
        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel69.setText("Tìm theo tên:");

        btnmoi.setBackground(new java.awt.Color(255, 204, 204));
        btnmoi.setText("Mới");
        btnmoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmoiActionPerformed(evt);
            }
        });

        txttimkiemten.setActionCommand("<Not Set>");
        txttimkiemten.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txttimkiemtenCaretUpdate(evt);
            }
        });

        btnxoa.setBackground(new java.awt.Color(255, 204, 204));
        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        jLabel70.setText("Họ tên");

        jLabel71.setText("SDT");

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ảnh nhân viên", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNhanVienAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(labelNhanVienAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel72.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel72.setText("MÃ NV:");

        labelNhanVienMaNhanVien.setText("NV001");

        javax.swing.GroupLayout pannelNhanVienLayout = new javax.swing.GroupLayout(pannelNhanVien);
        pannelNhanVien.setLayout(pannelNhanVienLayout);
        pannelNhanVienLayout.setHorizontalGroup(
            pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(pannelNhanVienLayout.createSequentialGroup()
                .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pannelNhanVienLayout.createSequentialGroup()
                        .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pannelNhanVienLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pannelNhanVienLayout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(jLabel72)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelNhanVienMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38)
                        .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pannelNhanVienLayout.createSequentialGroup()
                                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttimkiemten, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pannelNhanVienLayout.createSequentialGroup()
                                .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(pannelNhanVienLayout.createSequentialGroup()
                                            .addGap(43, 43, 43)
                                            .addComponent(rdnam, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(rdnu, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(50, 50, 50))
                                        .addGroup(pannelNhanVienLayout.createSequentialGroup()
                                            .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(38, 38, 38)))
                                    .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtdiachi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                        .addComponent(txthoten, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtsdt, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(0, 0, 0)
                                .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pannelNhanVienLayout.createSequentialGroup()
                                        .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pannelNhanVienLayout.createSequentialGroup()
                                        .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pannelNhanVienLayout.createSequentialGroup()
                                        .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pannelNhanVienLayout.createSequentialGroup()
                                        .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnmoi, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pannelNhanVienLayout.createSequentialGroup()
                                        .addComponent(rdnhanvien)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdquanli))
                                    .addGroup(pannelNhanVienLayout.createSequentialGroup()
                                        .addComponent(rdhoatdong)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdkhonghoatdong))
                                    .addComponent(txtmatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcccd, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pannelNhanVienLayout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(pannelNhanVienLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pannelNhanVienLayout.setVerticalGroup(
            pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelNhanVienLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel68)
                .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pannelNhanVienLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65)
                            .addComponent(txtcccd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel66)
                                .addComponent(rdhoatdong)
                                .addComponent(rdkhonghoatdong)))
                        .addGap(18, 18, 18)
                        .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel67)
                                .addComponent(rdnhanvien)
                                .addComponent(rdquanli)
                                .addComponent(rdnam)
                                .addComponent(rdnu))))
                    .addGroup(pannelNhanVienLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pannelNhanVienLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnthem)
                            .addComponent(btnsua)
                            .addComponent(btnxoa)
                            .addComponent(btnmoi)))
                    .addGroup(pannelNhanVienLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNhanVienMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pannelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimkiemten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jLayeredPane1.add(pannelNhanVien, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 912, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelBanHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBanHangMousePressed
        setColorLabel();
        resetPannel();
        loadDataOnHoaDonSanPham();
        this.pannelbanHang.setVisible(true);
        this.labelBanHang.setBackground(Color.white);
        this.labelBanHang.setOpaque(true);
    }//GEN-LAST:event_labelBanHangMousePressed

    private void labelHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHoaDonMousePressed
        setColorLabel();
        resetPannel();
        this.labelHoaDon.setBackground(Color.white);
        this.labelHoaDon.setOpaque(true);
        this.panelHoaDon.setVisible(true);
        loadHoaDonHoaDon();
    }//GEN-LAST:event_labelHoaDonMousePressed

    private void labelKhuyenMaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelKhuyenMaiMousePressed
        setColorLabel();
        resetPannel();
        this.labelKhuyenMai.setBackground(Color.white);
        this.pannelKhuyenMai.setVisible(true);
        this.labelKhuyenMai.setOpaque(true);
        loadDataOnTableKhuyenMaiSanPham();
        loadDataOnTableKhuyenMaiKhuyenMai();
//        Date date = this.txtKhuyenMaiThoiGianBatDau.getDate();
//        JOptionPane.showMessageDialog(this, date);
    }//GEN-LAST:event_labelKhuyenMaiMousePressed

    private void labelSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSanPhamMousePressed
        setColorLabel();
        resetPannel();
        loadDataOnTableSanPham();
    }//GEN-LAST:event_labelSanPhamMousePressed

    private void labelNhanvien2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNhanvien2MousePressed
//     labelThong ke cố gắng lên nào 
        setColorLabel();
        resetPannel();
        this.labelNhanvien2.setBackground(Color.white);
        this.pannelThongKe.setVisible(true);
        this.labelNhanvien2.setOpaque(true);
//        int month = this.cbThongKeMonth.getMonth();
//        int year = this.cbThongKeYear.getYear();
//        loadThongKe(month, year);
        // mình sẽ lấy ngày đầu và ngày cuối của thạg đnag làm việc
        Date date = new Date();
        Date ngayBatDauDangLuaChon = FormUtil.convertDateToString("2021" + "-" + "1" + "-01");
        Date ngayKetThucDangLuaChon = FormUtil.convertDateToString("2025" + "-" + "12" + "-30");

        //default là mặc định cùa tháng đang làm việc 
        txtThongKeNgayBatDau.setDate(ngayBatDauDangLuaChon);
        txtThongKeNgayKetThuc.setDate(ngayKetThucDangLuaChon);
        loadThongKe(ngayBatDauDangLuaChon, ngayKetThucDangLuaChon);
        loadChartThongKe();
    }//GEN-LAST:event_labelNhanvien2MousePressed

    private void txtHoaDonTenKhachHangCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHoaDonTenKhachHangCaretUpdate
        // tìm kiếm theo tên hoặc theo id khách hàng
        String text = this.txtHoaDonTenKhachHang.getText().trim();
        List<QlHoaDon> listHoaDonKhachHang = this.hoaDonServiceImpl.findAllByNameOrMa(text);
        loadHoaDonHoaDon(listHoaDonKhachHang);
    }//GEN-LAST:event_txtHoaDonTenKhachHangCaretUpdate

    private void txtHoaDonTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoaDonTenKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoaDonTenKhachHangActionPerformed

    private void tableHoaDonHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHoaDonHoaDonMouseClicked
        int row = tableHoaDonHoaDon.getSelectedRow();
        String maHoaDon = (String) tableHoaDonHoaDon.getValueAt(row, 0);
        List<QlHoaDonChiTietReponce> listChiTietHoaDon = this.hoaDonChiTietServiceImpl.findGioHangByMaHoaDon(maHoaDon);
        loadHoaDonHoaDonChiTiet(listChiTietHoaDon);
    }//GEN-LAST:event_tableHoaDonHoaDonMouseClicked

    private void cbHoaDonTrangThaiThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHoaDonTrangThaiThanhToanActionPerformed
        String text = this.cbHoaDonTrangThaiThanhToan.getSelectedItem().toString();
        List<QlHoaDon> listQlHoaDons = new ArrayList<>();
        if (text.equalsIgnoreCase("Đang chờ")) {
            listQlHoaDons = this.hoaDonServiceImpl.findAll(1, qlUser.getMa());
        } else if (text.equalsIgnoreCase("Đã thanh toán")) {
            listQlHoaDons = this.hoaDonServiceImpl.findAll(2, qlUser.getMa());
        } else if (text.equalsIgnoreCase("Đã hủy")) {
            listQlHoaDons = this.hoaDonServiceImpl.findAll(3, qlUser.getMa());
        } else {
            listQlHoaDons = this.hoaDonServiceImpl.findAll();
        }
        loadHoaDonHoaDon(listQlHoaDons);
    }//GEN-LAST:event_cbHoaDonTrangThaiThanhToanActionPerformed

    private void labelKhuyenMaiLamMoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelKhuyenMaiLamMoiMousePressed
        this.txtKhuyenMaiMaChuongTrinh.setText("");
        this.txtKhuyenMaiTenKhuyenMai.setText("");
        this.txtKhuyenMaiGiamGia.setText("");
        this.txtKhuyenMaiMoTa.setText("");
        /// kk
    }//GEN-LAST:event_labelKhuyenMaiLamMoiMousePressed

    private void btnKhuyenMaiLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhuyenMaiLuuActionPerformed
        // màn hình khuyến mãi .... Thêm khi validate thành công
        QlKhuyenMai qlKhuyenMai = getDataQlKhuyenMaiFromForm();
        if (qlKhuyenMai == null) {
            return;
        }
        String text = this.khuyenMaiServiceImpl.save(qlKhuyenMai);
        List<QlChiTietSanPham> listChiTietSanPham = this.chiTietSanPhamServiceImpl.findAll();
        for (QlChiTietSanPham qlChiTietSanPham : listChiTietSanPham) {
            if (qlChiTietSanPham.getDelected() == 0) {
                createKhuyenMaiChiTiet(qlKhuyenMai.getMaKhuyenMai(), qlChiTietSanPham);
            }
        }
        loadDataOnTableKhuyenMaiKhuyenMai();
    }//GEN-LAST:event_btnKhuyenMaiLuuActionPerformed

    private void tableKhuyenMaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKhuyenMaiSanPhamMouseClicked
        // Nhấn hcuột vào , talbe sẽ tự động thay đổi và update lại trạng thái update lại delected
        int row = this.tableKhuyenMaiSanPham.getSelectedRow();// lấy vị trí hành hiện tại dang lựa chọn
        Boolean check = (Boolean) this.tableKhuyenMaiSanPham.getValueAt(row, 0);// vị trsi này sẽ trả về false or true
        // lấy sản phẩm lựa chọn và update lại delected
        String maSanPham = (String) this.tableKhuyenMaiSanPham.getValueAt(row, 1);
        QlChiTietSanPham qlChiTietSanPham = this.chiTietSanPhamServiceImpl.findOneByMa(maSanPham);
        if (check == true) {
            qlChiTietSanPham.setDelected(0);
        } else {
            qlChiTietSanPham.setDelected(1);
        }
        this.chiTietSanPhamServiceImpl.update(qlChiTietSanPham);
    }//GEN-LAST:event_tableKhuyenMaiSanPhamMouseClicked

    private void btnPhieuBaoHanh3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhieuBaoHanh3ActionPerformed
        String maHoaDon = this.labelMaHoaDon.getText().trim();
        List<QlHoaDonChiTiet> listQlHoaDonChiTiets = this.hoaDonChiTietServiceImpl.getAllByMaHoaDon(maHoaDon);
        loadChiTietHoaDonKhiHuy(listQlHoaDonChiTiets);
        loadDataOnHoaDonDangCho();
        loadDataOnHoaDonSanPham();
        loadDataOnGioHang();
    }//GEN-LAST:event_btnPhieuBaoHanh3ActionPerformed

    private void btnBanHangHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangHuyHoaDonActionPerformed
        // hủy hóa đơn
        String maHoaDon = this.labelMaHoaDon.getText().trim();
        QlHoaDon qlHoaDon = this.hoaDonServiceImpl.findOne(maHoaDon);
        qlHoaDon.setTinhTrang(3);
        this.hoaDonServiceImpl.update(qlHoaDon);
        List<QlHoaDonChiTiet> listQlHoaDonChiTiets = this.hoaDonChiTietServiceImpl.getAllByMaHoaDon(maHoaDon);
        loadChiTietHoaDonKhiHuy(listQlHoaDonChiTiets);
        loadDataOnHoaDonDangCho();
        loadDataOnHoaDonSanPham();
        loadDataOnGioHang();
    }//GEN-LAST:event_btnBanHangHuyHoaDonActionPerformed

    private void btnBanHangThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangThanhToanActionPerformed
        String ma = this.labelMaHoaDon.getText().trim();
        QlHoaDon qlHoaDon = this.hoaDonServiceImpl.findOne(ma);
        qlHoaDon.setTinhTrang(2);
        // thanh toan 
        List<QlHinhThucThanhToan> listHinhThucThanhToans = this.hinhThucThanhToanServiceImpl.findAll();
        if (rbBanHangBitcoin.isSelected()) {
            for (QlHinhThucThanhToan qlHinhThucThanhToan : listHinhThucThanhToans) {
                if (qlHinhThucThanhToan.getMaHinhThuc().equalsIgnoreCase("BITCOIN")) {
                    qlHoaDon.setIdHinhThucThanhToan(FormUtil.covertHinhThucThanhToanToQlHinhThucThanhToan(qlHinhThucThanhToan));
                }
            }
        } else if (rbBanHangChuyenKhoan.isSelected()) {
            for (QlHinhThucThanhToan qlHinhThucThanhToan : listHinhThucThanhToans) {
                if (qlHinhThucThanhToan.getMaHinhThuc().equalsIgnoreCase("CK")) {
                    qlHoaDon.setIdHinhThucThanhToan(FormUtil.covertHinhThucThanhToanToQlHinhThucThanhToan(qlHinhThucThanhToan));
                }
            }
        } else if (rbBanHangTienMat.isSelected()) {
            for (QlHinhThucThanhToan qlHinhThucThanhToan : listHinhThucThanhToans) {
                if (qlHinhThucThanhToan.getMaHinhThuc().equalsIgnoreCase("TT")) {
                    qlHoaDon.setIdHinhThucThanhToan(FormUtil.covertHinhThucThanhToanToQlHinhThucThanhToan(qlHinhThucThanhToan));
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn hình thức thanh toán"
            );
            return;
        }

        Double tongTien = this.hoaDonChiTietServiceImpl.tinhTongTienBanDau(ma);
        Double thanhToan = this.hoaDonChiTietServiceImpl.tinhTongTien(ma);
        Double tienKhachDua = Double.parseDouble(this.txtTienKhachDua.getText());
        Double tienThua = tienKhachDua - thanhToan;
        qlHoaDon.setTongTien(tongTien);
        qlHoaDon.setThanhToan(thanhToan);
        qlHoaDon.setTienThua(tienThua);
        Date date = new Date();
        qlHoaDon.setNgayThanhToan(date);
        qlHoaDon.setUpdated(date);
        String text = this.hoaDonServiceImpl.update(qlHoaDon);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không");
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                printPDF();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
        } else {
            JOptionPane.showMessageDialog(this, text);
        }
        loadDataOnHoaDonDangCho();
        loadDataOnGioHang();
        labelMaHoaDon.setText("Không có hóa đơn");
//        tableModelBanHangGioHang.setRowCount(0);

    }//GEN-LAST:event_btnBanHangThanhToanActionPerformed

    private void txtTienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCaretUpdate
        double tienKhachDua = Double.valueOf(this.txtTienKhachDua.getText());
        if (txtTienKhachDua.getText() == "" || tienKhachDua < 10) {
            tienKhachDua = 0.0;
        }
        Double tienConThua = tienKhachDua - tongTienSauKhiGiam;
        this.labelTienThua.setText(FormUtil.convertNumber(tienConThua) + " VND");
    }//GEN-LAST:event_txtTienKhachDuaCaretUpdate

    private void btnTaoHoaDonBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonBanHangActionPerformed
        // nút bán hàng
        createHoaDon();
        loadDataOnHoaDonDangCho();
    }//GEN-LAST:event_btnTaoHoaDonBanHangActionPerformed

    private void tableBanHangDonHangChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBanHangDonHangChoMouseClicked
        int row = tableBanHangDonHangCho.getSelectedRow();
        this.labelMaHoaDon.setText((String) tableBanHangDonHangCho.getValueAt(row, 1));
        qlUser = this.userServiceImpl.findOne((String) tableBanHangDonHangCho.getValueAt(row, 3));
        qlKhachHang = this.khachHangServiceImpl.findOne((String) tableBanHangDonHangCho.getValueAt(row, 4));
        this.txtBanHangMaKhachHang.setText(qlKhachHang.getMa() + "-" + qlKhachHang.getTen());
        this.labelDonHangNhanVien.setText(qlUser.getTen());
        loadDataOnGioHang();
        if (tableBanHangDonHangCho.getValueAt(row, 4).toString().equalsIgnoreCase("KH0")) {
            setUIKhachLe();
            rbKhachLe.setSelected(true);
        } else {
            setUIKhachMua();
            rbKhachMua.setSelected(true);
        }
    }//GEN-LAST:event_tableBanHangDonHangChoMouseClicked

    private void txtHoaDonTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHoaDonTimKiemCaretUpdate
        String txtTimKiem = txtHoaDonTimKiem.getText().trim();
        findDataOnHoaDonSanPham(txtTimKiem);
    }//GEN-LAST:event_txtHoaDonTimKiemCaretUpdate

    private void labelKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelKhachHangMousePressed
        // khách hàng
        setColorLabel();
        resetPannel();
        this.labelKhachHang.setBackground(Color.white);
        this.pannelKhachHang.setVisible(true);
        this.labelKhachHang.setOpaque(true);
        loadDataKhachHang();
        this.txtKhachHangMa.setText(this.khachHangServiceImpl.genMaTuDong());
        txtKhachHangMa.setVisible(true);
    }//GEN-LAST:event_labelKhachHangMousePressed

    private void labelNhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNhanVienMousePressed
        setColorLabel();
        resetPannel();
        this.labelNhanVien.setBackground(Color.white);
        this.pannelNhanVien.setVisible(true);
        this.labelNhanVien.setOpaque(true);
        loadDataOnNhanVienNhanVien();
        this.labelNhanVienMaNhanVien.setText(this.userServiceImpl.genMaTuDong());
    }//GEN-LAST:event_labelNhanVienMousePressed

    private void btnBanHangXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangXoaSanPhamActionPerformed
        int row = tableBanHangGioHang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn 1 sản phẩm trong bảng");
        }
        int soLuong = 0;
        try {
            soLuong = Integer.parseInt(JOptionPane.showInputDialog("Mời nhập số lượng tương ứng:"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String maHoaDon = (String) tableBanHangGioHang.getValueAt(row, 0);
        String maSanPham = (String) tableBanHangGioHang.getValueAt(row, 1);
        QlHoaDonChiTiet qlHoaDonChiTiet = hoaDonChiTietServiceImpl.findOne(maHoaDon, maSanPham);
        setSoLuong(soLuong, qlHoaDonChiTiet);
    }//GEN-LAST:event_btnBanHangXoaSanPhamActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tableBanHangGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBanHangGioHangMouseClicked

    }//GEN-LAST:event_tableBanHangGioHangMouseClicked

    private void btnThongKeLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeLocActionPerformed
        Date ngayBatDau = this.txtThongKeNgayBatDau.getDate();
        Date ngayKetThuc = this.txtThongKeNgayKetThuc.getDate();
        if (ngayBatDau.after(ngayKetThuc)) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn ngày bắt đầu nhỏ hơn ngày kết thúc");
            return;
        }
        loadThongKe(ngayBatDau, ngayKetThuc);
        loadChartThongKe();
    }//GEN-LAST:event_btnThongKeLocActionPerformed

    private void labelDangXuatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDangXuatMousePressed
        setColorLabel();
        resetPannel();
        loadDataOnHoaDonSanPham();
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_labelDangXuatMousePressed

    private void tableKhachHangCaNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKhachHangCaNhanMouseClicked
        int row = tableKhachHangCaNhan.getSelectedRow();
        String maKhachHang = (String) tableKhachHangCaNhan.getValueAt(row, 1);
        QlKhachHang qlKhachHang = this.khachHangServiceImpl.findOne(maKhachHang);
        loadFormKhachHang(qlKhachHang);
        loadDataKhachHangLichSuGiaoDich(maKhachHang);
    }//GEN-LAST:event_tableKhachHangCaNhanMouseClicked

    private void btnKhachHangThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangThemActionPerformed
        String tenKhachHang = this.txtKhachHangTenKhachHang.getText();
        String diaChi = this.txtKhachHangDiaChi.getText();
        String sdt = this.txtKhachHangSoDienThoai.getText();
        String maKhachHang = this.txtKhachHangMa.getText();
        QlKhachHang qlKhachHang = new QlKhachHang(1, maKhachHang, tenKhachHang, diaChi, sdt, sdt);
        String validate = this.khachHangServiceImpl.validate(tenKhachHang, diaChi, sdt);
        if (validate == null) {
            JOptionPane.showMessageDialog(this, validate);
        } else {
            String text = this.khachHangServiceImpl.save(qlKhachHang);
            JOptionPane.showMessageDialog(this, text);
            loadDataKhachHang();
            resetFormKhachHangKhachHang();
        }
    }//GEN-LAST:event_btnKhachHangThemActionPerformed

    private void btnKhachHangXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangXoaActionPerformed
        int row = this.tableKhachHangCaNhan.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 khách hàng");
            return;
        }
        String maKhachHang = (String) tableKhachHangCaNhan.getValueAt(row, 1);
        QlKhachHang qlKhachHang = this.khachHangServiceImpl.findOne(maKhachHang);
        String text = this.khachHangServiceImpl.delete(qlKhachHang);
        JOptionPane.showMessageDialog(this, text);
        loadDataKhachHang();
        resetFormKhachHangKhachHang();
    }//GEN-LAST:event_btnKhachHangXoaActionPerformed

    private void btnKhachHangSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangSuaActionPerformed
        int row = this.tableKhachHangCaNhan.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 khách hàng");
            return;
        }
        String maKhachHang = (String) tableKhachHangCaNhan.getValueAt(row, 1);
        String tenKhachHang = this.txtKhachHangTenKhachHang.getText();
        String diaChi = this.txtKhachHangDiaChi.getText();
        String sdt = this.txtKhachHangDiaChi.getText();
        String validate = this.khachHangServiceImpl.validate(tenKhachHang, diaChi, sdt);
        QlKhachHang qlKhachHang = this.khachHangServiceImpl.findOne(maKhachHang);
        qlKhachHang.setTen(tenKhachHang);
        qlKhachHang.setDiaChi(diaChi);
        qlKhachHang.setSdt(sdt);
        String update = this.khachHangServiceImpl.update(qlKhachHang);
        JOptionPane.showMessageDialog(this, update);
        loadDataKhachHang();
        resetFormKhachHangKhachHang();
    }//GEN-LAST:event_btnKhachHangSuaActionPerformed

    private void tableNhanVienNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNhanVienNhanVienMouseClicked
        // TODO add your handling code here:
        int row = tableNhanVienNhanVien.getSelectedRow();
        txthoten.setText(tableNhanVienNhanVien.getValueAt(row, 2).toString());
        txtdiachi.setText(tableNhanVienNhanVien.getValueAt(row, 3).toString());
        txtsdt.setText(tableNhanVienNhanVien.getValueAt(row, 4).toString());
        txtcccd.setText(tableNhanVienNhanVien.getValueAt(row, 6).toString());
        txtmatkhau.setText(tableNhanVienNhanVien.getValueAt(row, 8).toString());
        if (tableNhanVienNhanVien.getValueAt(row, 5).toString().equalsIgnoreCase("Nam")) {
            rdnam.setSelected(true);
        } else {
            rdnu.setSelected(true);

        }
        if (tableNhanVienNhanVien.getValueAt(row, 7).toString().equalsIgnoreCase("Hoạt động")) {
            rdhoatdong.setSelected(true);
        } else {
            rdkhonghoatdong.setSelected(true);
        }
        if (tableNhanVienNhanVien.getValueAt(row, 9).toString().equalsIgnoreCase("Nhân viên")) {
            rdnhanvien.setSelected(true);
        } else {
            rdquanli.setSelected(true);
        }
        QlUser qlUser = this.userServiceImpl.findOne((String) tableNhanVienNhanVien.getValueAt(row, 1));
        this.labelNhanVienMaNhanVien.setText(qlUser.getMa());
        this.labelNhanVienAnh.setIcon(new ImageIcon(qlUser.getAnh()));

    }//GEN-LAST:event_tableNhanVienNhanVienMouseClicked

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        if (Validateform()) {
            String ten = txthoten.getText().trim();
            String ma = userServiceImpl.genMaTuDong();
            String diaChi = txtdiachi.getText().trim();
            String sdt = txtsdt.getText().trim();
            String matkhau = txtmatkhau.getText().trim();
            String cccd = txtcccd.getText().trim();
            boolean gioitinh = false;
            if (rdnam.isSelected()) {
                gioitinh = true;
            }
            int trangthai = 0;
            if (rdhoatdong.isSelected()) {
                trangthai = 1;
            }

            QlChucVu qlchucvu1 = new QlChucVu();
            if (rdnhanvien.isSelected()) {
                qlchucvu1 = chucVuServiceImpl.findOne("NV");
            } else {
                qlchucvu1 = chucVuServiceImpl.findOne("TP");
            }

            QlUser qluser = new QlUser(ma, ten, diaChi, sdt, gioitinh, cccd, trangthai, "img", matkhau, FormUtil.convertFromQlChucVuToChucVu(qlchucvu1));
            String text = this.userServiceImpl.save(qluser);
            JOptionPane.showMessageDialog(rootPane, text);
            loadDataOnNhanVienNhanVien();
            resetNhanVienNhanVien();
        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // TODO add your handling code here:
        String ten = txthoten.getText().trim();

        String diaChi = txtdiachi.getText().trim();
        String sdt = txtsdt.getText().trim();
        String matkhau = txtmatkhau.getText().trim();
        String cccd = txtcccd.getText().trim();
        boolean gioitinh = true;
        if (rdnam.isSelected()) {
            gioitinh = false;
        }
        int trangthai = 1;
        if (rdhoatdong.isSelected()) {
            trangthai = 0;
        }

        QlChucVu qlchucvu1 = new QlChucVu();
        if (rdnhanvien.isSelected()) {
            qlchucvu1 = this.chucVuServiceImpl.findOne("NV");
        } else {
            qlchucvu1 = chucVuServiceImpl.findOne("QL");
        }
        int row = tableNhanVienNhanVien.getSelectedRow();
        if (row == - 1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn phải chọn 1 dòng tương ứg");
            return;
        }
//        if (Validateform()) {
//            String ma = tableNhanVien.getValueAt(row, 1).toString();
//            QlUser qluser = this.userServiceImpl.findOne(ma);
//
//            qluser.setTen(ten);
//
//            qluser.setDiaChi(diaChi);
//            qluser.setMatKhau(matkhau);
//            qluser.setCccd(cccd);
//            qluser.setSdt(sdt);
//            qluser.setGioiTinh(gioitinh);
//            qluser.setTrangThai(trangthai);
//            qluser.setIdChucVu(FormUtil.convertFromQlChucVuToChucVu(qlchucvu1));
//            String text = this.userServiceImpl.update(qluser);
//            JOptionPane.showMessageDialog(this,"Sửa thành công");
//            loadDataOnTable();
//        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnmoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmoiActionPerformed
        // TODO add your handling code here:
        resetNhanVienNhanVien();
    }//GEN-LAST:event_btnmoiActionPerformed

    private void resetNhanVienNhanVien() {
        txttimkiemten.setText(null);
        txthoten.setText(null);
        txtsdt.setText(null);
        txtdiachi.setText(null);
        txtmatkhau.setText(null);
        this.labelNhanVienMaNhanVien.setText(this.userServiceImpl.genMaTuDong());
        txtcccd.setText(null);
        buttonGroup1.clearSelection();
        buttonGroup3.clearSelection();
        buttonGroup4.clearSelection();
    }

    private void txttimkiemtenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txttimkiemtenCaretUpdate
        // TODO add your handling code here:
        String manv = txttimkiemten.getText().trim();
        findDataOnUser(manv);
    }//GEN-LAST:event_txttimkiemtenCaretUpdate

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        int row = tableNhanVienNhanVien.getSelectedRow();
        if (row == - 1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn phải chọn 1 dòng tương ứg");
            return;
        }
        String ma = tableNhanVienNhanVien.getValueAt(row, 1).toString();
        QlUser qluser = this.userServiceImpl.findOne(ma);
        String text = this.userServiceImpl.delete(qluser);
        JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
        loadDataOnNhanVienNhanVien();
    }//GEN-LAST:event_btnxoaActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void rbKhachLeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbKhachLeActionPerformed
        setUIKhachLe();
        this.qlKhachHang = khachHangServiceImpl.findOne("NV0");
        setKhachHang();
        loadDataOnHoaDonDangCho();
    }//GEN-LAST:event_rbKhachLeActionPerformed

    private void rbKhachMuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbKhachMuaActionPerformed
        setUIKhachMua();

    }//GEN-LAST:event_rbKhachMuaActionPerformed

    private void btnBanHangThemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangThemKhachHangActionPerformed
        this.dispose();
        new ViewKhachHang(this, rootPaneCheckingEnabled, qlUser).setVisible(true);
    }//GEN-LAST:event_btnBanHangThemKhachHangActionPerformed

    private void btnThemVaoGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVaoGioHangActionPerformed
        int row = tableHoaDonSanPham.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn  1 sản phẩm");
            return;
        }
        int soLuong = 0;
        // hàm lấy hóa đơn và lấy sản phẩm tương ứng nè 
        String maHoaDon = this.labelMaHoaDon.getText().trim();
        String maSp = (String) this.tableHoaDonSanPham.getValueAt(row, 1);
        try {
            soLuong = Integer.parseInt(JOptionPane.showInputDialog("Mời bạn nhập số lượng tương ứng"));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Bạn phải nhập số :))");
        }
        setSoLuong(soLuong, chiTietSanPhamServiceImpl.findOneByMa(maSp));
    }//GEN-LAST:event_btnThemVaoGioHangActionPerformed

    private void tableHoaDonSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHoaDonSanPhamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableHoaDonSanPhamMouseClicked

    private void txtKhachHangTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtKhachHangTimKiemCaretUpdate
        // TODO add your handling code here:
        String name = txtKhachHangTimKiem.getText();
        findDataOnKhachHang(name);
    }//GEN-LAST:event_txtKhachHangTimKiemCaretUpdate

    private void btnExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportExcelActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(this);
            File saveFile = fileChooser.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("Khach Hang");
                
                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tableKhachHangCaNhan.getColumnCount(); i++) {
                    org.apache.poi.ss.usermodel.Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tableKhachHangCaNhan.getColumnName(i));
                }
                for (int j = 0; j < tableKhachHangCaNhan.getRowCount(); j++) {
                    Row row = sheet.createRow(j);
                    for (int k = 0; k < tableKhachHangCaNhan.getColumnCount(); k++) {
                        org.apache.poi.ss.usermodel.Cell cell = row.createCell(k);
                        if (tableKhachHangCaNhan.getValueAt(j, k) != null) {
                            cell.setCellValue(tableKhachHangCaNhan.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                JOptionPane.showMessageDialog(this, "Xuat file thanh cong");
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);

        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_btnExportExcelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewCuaHangBanDienThoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCuaHangBanDienThoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCuaHangBanDienThoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCuaHangBanDienThoai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewCuaHangBanDienThoai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHangHuyHoaDon;
    private javax.swing.JButton btnBanHangThanhToan;
    private javax.swing.JButton btnBanHangThemKhachHang;
    private javax.swing.JButton btnBanHangXoaSanPham;
    private javax.swing.JButton btnExportExcel;
    private javax.swing.JButton btnKhachHangSua;
    private javax.swing.JButton btnKhachHangThem;
    private javax.swing.JButton btnKhachHangXoa;
    private javax.swing.JButton btnKhuyenMaiLuu;
    private javax.swing.JButton btnKhuyenmaiSua;
    private javax.swing.JButton btnPhieuBaoHanh3;
    private javax.swing.JButton btnTaoHoaDonBanHang;
    private javax.swing.JButton btnThemVaoGioHang;
    private javax.swing.JButton btnThongKeLoc;
    private javax.swing.JButton btnmoi;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JPanel cbDongSanPham2;
    private javax.swing.JComboBox<String> cbHoaDonTrangThaiThanhToan;
    private javax.swing.JComboBox<String> cbKhuyenMaiHinhThucGiamGia;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox15;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel labelAnh;
    private javax.swing.JLabel labelAnhSanPham2;
    private javax.swing.JLabel labelBanHang;
    private javax.swing.JLabel labelBanHangMaKhachHang;
    private javax.swing.JLabel labelDangXuat;
    private javax.swing.JLabel labelDonHangNhanVien;
    private javax.swing.JLabel labelHoaDon;
    private javax.swing.JLabel labelKhachHang;
    private javax.swing.JLabel labelKhuyenMai;
    private javax.swing.JLabel labelKhuyenMaiLamMoi;
    private javax.swing.JLabel labelMaHoaDon;
    private javax.swing.JLabel labelNhanVien;
    private javax.swing.JLabel labelNhanVienAnh;
    private javax.swing.JLabel labelNhanVienMaNhanVien;
    private javax.swing.JLabel labelNhanvien2;
    private javax.swing.JLabel labelSanPham;
    private javax.swing.JLabel labelSoHoaDon;
    private javax.swing.JLabel labelSoHoaDonBiHuy;
    private javax.swing.JLabel labelSoKhachHang;
    private javax.swing.JLabel labelTenDangNhap;
    private javax.swing.JLabel labelTienThua;
    private javax.swing.JLabel labelTongDoanhThu;
    private javax.swing.JLabel labelTongTien;
    private javax.swing.JTabbedPane panelHoaDon;
    private javax.swing.JPanel panelQr;
    private javax.swing.JPanel panelThongKeHangHoa;
    private javax.swing.JPanel pannelKhachHang;
    private javax.swing.JPanel pannelKhuyenMai;
    private javax.swing.JPanel pannelKhuyenMaiChuongTrinhKhuyenMai;
    private javax.swing.JPanel pannelKhuyenMaiChuongTrinhKhuyenMai2;
    private javax.swing.JPanel pannelNhanVien;
    private javax.swing.JTabbedPane pannelSanPham;
    private javax.swing.JPanel pannelThongKe;
    private javax.swing.JPanel pannelbanHang;
    private javax.swing.JRadioButton rbBanHangBitcoin;
    private javax.swing.JRadioButton rbBanHangChuyenKhoan;
    private javax.swing.JRadioButton rbBanHangTienMat;
    private javax.swing.JRadioButton rbKhachLe;
    private javax.swing.JRadioButton rbKhachMua;
    private javax.swing.JRadioButton rdhoatdong;
    private javax.swing.JRadioButton rdkhonghoatdong;
    private javax.swing.JRadioButton rdnam;
    private javax.swing.JRadioButton rdnhanvien;
    private javax.swing.JRadioButton rdnu;
    private javax.swing.JRadioButton rdquanli;
    private javax.swing.JSeparator seperatorBanHangKhachHang;
    private javax.swing.JSeparator seperatorBanHangKhachHang1;
    private javax.swing.JTable tableBanHangDonHangCho;
    private javax.swing.JTable tableBanHangGioHang;
    private javax.swing.JTable tableHoaDonHoaDon;
    private javax.swing.JTable tableHoaDonHoaDonChiTiet;
    private javax.swing.JTable tableHoaDonSanPham;
    private javax.swing.JTable tableKhachHangCaNhan;
    private javax.swing.JTable tableKhachHangLichSuGiaoDich;
    private javax.swing.JTable tableKhuyenMaiKhuyenMai;
    private javax.swing.JTable tableKhuyenMaiSanPham;
    private javax.swing.JTable tableNhanVienNhanVien;
    private javax.swing.JTable tableSanPham;
    private javax.swing.JTable tableThongKeThongKe;
    private javax.swing.JLabel txtBanHangKhuyenMai;
    private javax.swing.JLabel txtBanHangMaKhachHang;
    private javax.swing.JLabel txtBanHangThanhToan;
    private javax.swing.JTextField txtHoaDonTenKhachHang;
    private javax.swing.JTextField txtHoaDonTimKiem;
    private javax.swing.JTextArea txtKhachHangDiaChi;
    private javax.swing.JTextField txtKhachHangMa;
    private javax.swing.JTextField txtKhachHangSoDienThoai;
    private javax.swing.JTextField txtKhachHangTenKhachHang;
    private javax.swing.JTextField txtKhachHangTimKiem;
    private javax.swing.JTextField txtKhuyenMaiGiamGia;
    private javax.swing.JTextField txtKhuyenMaiMaChuongTrinh;
    private javax.swing.JTextArea txtKhuyenMaiMoTa;
    private javax.swing.JTextField txtKhuyenMaiTenKhuyenMai;
    private com.toedter.calendar.JDateChooser txtKhuyenMaiThoiGianBatDau;
    private com.toedter.calendar.JDateChooser txtKhuyenMaiThoiGianKetThuc;
    private com.toedter.calendar.JDateChooser txtThongKeNgayBatDau;
    private com.toedter.calendar.JDateChooser txtThongKeNgayKetThuc;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtcccd;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txthoten;
    private javax.swing.JTextField txtmatkhau;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txttimkiemten;
    // End of variables declaration//GEN-END:variables

    private void setColorLabel() {
        this.labelBanHang.setBackground(Color.PINK);
        this.labelHoaDon.setBackground(Color.PINK);
        this.labelKhuyenMai.setBackground(Color.PINK);
        this.labelSanPham.setBackground(Color.PINK);
        this.labelNhanVien.setBackground(Color.PINK);
//        this.labelBaoHanh.setBackground(Color.PINK);
        this.labelKhachHang.setBackground(Color.PINK);
        this.labelNhanvien2.setBackground(Color.PINK);
        this.labelDangXuat.setBackground(Color.PINK);
    }

    private void resetPannel() {
        this.pannelSanPham.setVisible(false);
        this.pannelbanHang.setVisible(false);
        this.panelHoaDon.setVisible(false);
        this.pannelKhuyenMai.setVisible(false);
        this.pannelKhachHang.setVisible(false);
//        this.pannelBaoHanh.setVisible(false);
        this.pannelThongKe.setVisible(false);
        this.pannelNhanVien.setVisible(false);

    }

    private void init() {
        this.pannelbanHang.setVisible(true);
        this.setLocationRelativeTo(null);
        //service
        this.hoaDonChiTietServiceImpl = new HoaDonChiTietServiceImpl();
        this.khuyenMaiServiceImpl = new KhuyenMaiServiceImpl();
        this.userServiceImpl = new UserServiceImpl();
        this.khachHangServiceImpl = new KhachHangServiceImpl();
        this.hoaDonServiceImpl = new HoaDonServiceImpl();
        this.chiTietSanPhamServiceImpl = new ChiTietSanPhamServiceImpl();
        this.chiTietKhuyenMaiServiceImpl = new ChiTietKhuyenMaiServiceImpl();
        this.hinhThucThanhToanServiceImpl = new HinhThucThanhToanServiceImpl();
        this.chucVuServiceImpl = new ChucVuServiceImpl();
        // tablemodel
        this.tableModelHoaDonHoaDon = (DefaultTableModel) this.tableHoaDonHoaDon.getModel();
        this.tableModelHoaDonHoaDonChiTiet = (DefaultTableModel) this.tableHoaDonHoaDonChiTiet.getModel();
        this.tableModelHoaDonSanPham = (DefaultTableModel) tableHoaDonSanPham.getModel();
        this.tableModelBanHangDonHangCho = (DefaultTableModel) tableBanHangDonHangCho.getModel();
        this.tableModelSanPhamSanPham = (DefaultTableModel) tableSanPham.getModel();
        this.tableModelBanHangGioHang = (DefaultTableModel) tableBanHangGioHang.getModel();
        this.tableModelKhuyenMaiKhuyenMai = (DefaultTableModel) tableKhuyenMaiKhuyenMai.getModel();
        this.tableModelKhuyenMaiSanPham = (DefaultTableModel) this.tableKhuyenMaiSanPham.getModel();
//        this.tableModelBaohanhBaoHanh = (DefaultTableModel) this.tableBaohanhBaoHanh.getModel();
        this.tableModelThongKeThongKe = (DefaultTableModel) this.tableThongKeThongKe.getModel();
        this.tableModelKhachHangCaNhan = (DefaultTableModel) this.tableKhachHangCaNhan.getModel();
        this.tableModelKhachHangLichSuGiaoDich = (DefaultTableModel) this.tableKhachHangLichSuGiaoDich.getModel();
        this.tableModelNhanVienNhanVien = (DefaultTableModel) tableNhanVienNhanVien.getModel();
        // combobox
        this.comboboxBanHangKhuyenMai = new DefaultComboBoxModel();
        this.comboboxBanHangKhuyenMai = new DefaultComboBoxModel();
        this.comBoBoxBaoHanhGoiBaoHanh = new DefaultComboBoxModel();
        // custom model
        String titleBanHangGioHang[] = {"Mã hóa đơn", "Mã sản phẩm", "Tên sản phẩm", "Số lượng ", "Đơn giá", "Thành tiền"};
        String titleDonHangHoaDon[] = {"STT", "Mã hóa đơn", "Ngày tạo hóa đơn", "Nhân viên tạo", "Khách hàng"};
        String titleSanPhamSanPham[] = {"STT", "Imei", "Tên", "Nhà sản xuất", "Dòng sản phẩm", "Màu sắc", "Độ phân giải", "Kích thước màn hình", "Ram", "Rom", "Số lượng tồn", "Đơn giá"};
        String titleHoaDonSanPham[] = {"STT", "Mã", "Tên", "Nhà sản xuất", "Dòng sản phẩm", "Màu sắc", "Số lượng tồn", "Đơn giá"};
        String titleHoaDonHoaDon[] = {"Mã hóa đơn", "Tổng tiền", "Thanh toán", "Tiền thừa", "Hình thức thanh toán", "Ngày lập hóa đơn", "Trạng thái", "Mã NV", "Tên NV", "Mã KH", "Tên KH"};
        String titleHoaDonHoaDonChiTiet[] = {"STT", "Số Imei", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"};
        String titleKhuyenMaiKhuyenMai[] = {"Mã chương trình", "Tên chương trình", "Hình thức giảm giá", "Giả m giá", "Sản phẩm", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái", "Mô tả"};
        String titleThongKeThongKe[] = {"STT", "Sản phẩm", "Số lượng đã bán", "Giá bán hiện tại", "Doanh thu"};
        String titleKhachHangCaNhan[] = {"Stt", "Mã", "Tên", "Địa chỉ", "Số điện thoại"};
        String titleNhanVienNhanVien[] = {"STT", "Mã", "Tên", "Địa chỉ", "Số điện thoại", "Giới tính", "cccd", "Trạng thái", "Mật khẩu", "Chức vụ"};
        // default table model
        // set column identifier111
        tableModelHoaDonHoaDon.setColumnIdentifiers(titleHoaDonHoaDon);
        tableModelHoaDonHoaDonChiTiet.setColumnIdentifiers(titleHoaDonHoaDonChiTiet);
        tableModelSanPhamSanPham.setColumnIdentifiers(titleSanPhamSanPham);
        tableModelBanHangGioHang.setColumnIdentifiers(titleBanHangGioHang);
        tableModelBanHangDonHangCho.setColumnIdentifiers(titleDonHangHoaDon);
        tableModelKhuyenMaiKhuyenMai.setColumnIdentifiers(titleKhuyenMaiKhuyenMai);
        tableModelHoaDonSanPham.setColumnIdentifiers(titleHoaDonSanPham);
        tableModelThongKeThongKe.setColumnIdentifiers(titleThongKeThongKe);
        tableModelKhachHangCaNhan.setColumnIdentifiers(titleKhachHangCaNhan);
        tableModelKhachHangLichSuGiaoDich.setColumnIdentifiers(titleHoaDonHoaDon);
        tableModelNhanVienNhanVien.setColumnIdentifiers(titleNhanVienNhanVien);
        // Phân quyền người dùng 

        resetPannel();
        loadDataOnHoaDonDangCho();
        this.pannelbanHang.setVisible(true);
        this.qlKhuyenMais = new ArrayList<>();
//        if (qlKhachHang == null) {
//            this.labelBanHangKhachHang.setText("Chưa có ai");
//        } else if (this.qlKhachHang.getSdt() != null) {
//            String maHoaDon = labelMaHoaDon.getText().trim();
//            if (!maHoaDon.equalsIgnoreCase("19")) {
//                QlHoaDon qlHoaDon = this.hoaDonServiceImpl.findOne(maHoaDon);
//                qlHoaDon.setIdKhachHang(FormUtil.convertFromQlKhachHangToKhachHang(qlKhachHang));
//                this.hoaDonServiceImpl.update(qlHoaDon);
//            } else {
//                this.labelBanHangKhachHang.setText(this.qlKhachHang.getMa() + "-" + this.qlKhachHang.getTen());
//            }
//        }
        loadDataOnHoaDonSanPham();
        loadDataOnHoaDonDangCho();
        loadDataOnGioHang();
        listChiTietSanPhams = this.chiTietSanPhamServiceImpl.findAll();
        String ma = this.labelMaHoaDon.getText().trim();
        this.labelTongTien.setText(FormUtil.convertNumber(this.hoaDonChiTietServiceImpl.tinhTongTienBanDau(ma)) + " VND");
        loadDataCombobox();
        loadDataKhuyenMai();
    }

    private void loadDataOnTableSanPham() {
        this.pannelSanPham.setVisible(true);
        this.labelSanPham.setBackground(Color.white);
        this.labelSanPham.setOpaque(true);
        tableModelSanPhamSanPham.setRowCount(0);
        int i = 0;
        for (QlChiTietSanPham qlChiTietSanPham : this.chiTietSanPhamServiceImpl.findAll()) {
            tableModelSanPhamSanPham.addRow((Object[]) qlChiTietSanPham.getData(i));
            i++;
        }
    }

    private void loadDataOnHoaDonSanPham() {
        tableModelHoaDonSanPham.setRowCount(0);
        int i = 0;
        for (QlChiTietSanPham qlChiTietSanPham : this.chiTietSanPhamServiceImpl.findAll()) {
            tableModelHoaDonSanPham.addRow((Object[]) qlChiTietSanPham.getData1(i));
            i++;
        }
    }

    private void findDataOnHoaDonSanPham(String ten) {
        tableModelHoaDonSanPham.setRowCount(0);
        int i = 0;
        for (QlChiTietSanPham qlChiTietSanPham : this.chiTietSanPhamServiceImpl.findAllByName(ten)) {
            tableModelHoaDonSanPham.addRow((Object[]) qlChiTietSanPham.getData1(i));
            i++;
        }
    }

    private void findDataOnSanPhamSanPham(String txtTimKiem) {
        tableModelSanPhamSanPham.setRowCount(0);
        int i = 0;
        for (QlChiTietSanPham qlChiTietSanPham : this.chiTietSanPhamServiceImpl.findAllByName(txtTimKiem)) {
            tableModelSanPhamSanPham.addRow((Object[]) qlChiTietSanPham.getData(i));
            i++;
        }
    }

    private void createHoaDon() {
        if (this.labelDonHangNhanVien.getText().equalsIgnoreCase("CHƯA CÓ AI") || this.txtKhachHangMa.getText().equalsIgnoreCase("CHƯA CÓ AI")) {
            JOptionPane.showMessageDialog(rootPane, "Bạn phải chọn nhân viên và khách hàng");
            return;
        } else {

            if (rbKhachLe.isSelected()) {
                QlUser qlKhachBanLe = userServiceImpl.findOne("KH0");
                QlHoaDon qlHoadon = this.hoaDonServiceImpl.createHoaDon(qlKhachBanLe, qlKhachHang);
                String text = this.hoaDonServiceImpl.save(qlHoadon);
                JOptionPane.showMessageDialog(rootPane, text);
            } else {
                QlHoaDon qlHoadon = this.hoaDonServiceImpl.createHoaDon(qlUser, qlKhachHang);
                String text = this.hoaDonServiceImpl.save(qlHoadon);
                JOptionPane.showMessageDialog(rootPane, text);
            }

        }
    }

    private void loadDataOnHoaDonDangCho() {

        tableModelBanHangDonHangCho.setRowCount(0);
        List<QlHoaDon> listQlHoaDons = this.hoaDonServiceImpl.findAll(1, qlUser.getMa());

        int i = 1;
        for (QlHoaDon qlHoaDon : listQlHoaDons) {
            tableModelBanHangDonHangCho.addRow((Object[]) qlHoaDon.getData(i));
            i++;
        }
        setKhachHang();
    }

    public void setKhachHang() {
        List<QlHoaDon> listQlHoaDons = this.hoaDonServiceImpl.findAll(1, qlUser.getMa());
        if (listQlHoaDons.size() != 0) {
            this.labelMaHoaDon.setText((String) tableBanHangDonHangCho.getValueAt(listQlHoaDons.size() - 1, 1));
            qlUser = this.userServiceImpl.findOne((String) tableBanHangDonHangCho.getValueAt(listQlHoaDons.size() - 1, 3));
            if (qlKhachHang == null) {
                this.txtBanHangMaKhachHang.setText("Chưa có khách hàng");
            } else if (this.qlKhachHang.getSdt() == null) {
                qlKhachHang = this.khachHangServiceImpl.findOne((String) tableBanHangDonHangCho.getValueAt(listQlHoaDons.size() - 1, 4));
                this.txtBanHangMaKhachHang.setText(qlKhachHang.getMa() + "-" + qlKhachHang.getTen());
            }
            if (this.qlKhachHang.getMa().equalsIgnoreCase("NVOO")) {
                setUIKhachLe();
            } else {
                setUIKhachMua();
                rbKhachMua.setSelected(true);
            }
            this.labelDonHangNhanVien.setText(qlUser.getTen());
        }
    }

    private void loadDataOnGioHang() {
        String maHoaDon = this.labelMaHoaDon.getText().trim();
        tableModelBanHangGioHang.setRowCount(0);

        for (QlHoaDonChiTiet qlHoaDonChiTiet : this.hoaDonChiTietServiceImpl.getAllByMaHoaDon(maHoaDon)) {
            tableModelBanHangGioHang.addRow((Object[]) qlHoaDonChiTiet.getData());

        }

        this.labelTongTien.setText(FormUtil.convertNumber(this.hoaDonChiTietServiceImpl.tinhTongTienBanDau(maHoaDon)) + "VND");
        loadDataKhuyenMai();
    }
    // set số lượng khi thêm sản phẩm vào giỏ hàng

    public void setSoLuong(int index, QlChiTietSanPham qlChiTietSanPham) {
        String maHoaDon = this.labelMaHoaDon.getText().trim();
        QlHoaDon qlHoaDon = this.hoaDonServiceImpl.findOne(maHoaDon);
        ChiTietSanPham chiTietSanPham = FormUtil.convertFromQlChiTietSanPhamToChiTietSanPham(qlChiTietSanPham);
        HoaDon hoaDon = FormUtil.convertFromQlHoaDonToHoaDon(qlHoaDon);
        // trường hợp làm theo hóa đơn nè
        QlHoaDonChiTiet qlHoaDonChiTiet = this.hoaDonChiTietServiceImpl.findOne(maHoaDon, qlChiTietSanPham.getMa());
        if (qlHoaDonChiTiet == null) {
            long soLuongTon = chiTietSanPhamServiceImpl.getSoLuongTonTheoTungMaSanPham(qlChiTietSanPham.getMa());
            if (soLuongTon < index) {
                JOptionPane.showMessageDialog(this, "Số lượng còn lại không đủ");
                return;
            } else if (soLuongTon > index) {
                double soTienSauKhuyenMai = this.chiTietKhuyenMaiServiceImpl.getSoTienSauKhuyenMai(qlChiTietSanPham.getMa());
                double donGiaMoi = chiTietSanPham.getDonGia() - soTienSauKhuyenMai;
                int soLuongTonSauKhiTru = (int) soLuongTon - index;
                qlChiTietSanPham.setSoLuongTon(soLuongTonSauKhiTru);
                // tính số tiền ssau khuyến mãi 
                // tính hóa đơn chi tiết 
                qlHoaDonChiTiet = new QlHoaDonChiTiet(hoaDon, chiTietSanPham, donGiaMoi, index);
                String text = this.hoaDonChiTietServiceImpl.save(qlHoaDonChiTiet);

                setSoLuongImeiTheoSoMayMua(index, qlChiTietSanPham.getMa(), hoaDonChiTietServiceImpl.findOne(maHoaDon, qlChiTietSanPham.getMa()));
                JOptionPane.showMessageDialog(rootPane, text);
                loadDataOnHoaDonSanPham();
                loadDataOnGioHang();
            } else if (soLuongTon == index) {
                // trường hợp trùng nhau nè
                double soTienSauKhuyenMai = this.chiTietKhuyenMaiServiceImpl.getSoTienSauKhuyenMai(qlChiTietSanPham.getMa());
                double donGiaMoi = chiTietSanPham.getDonGia() - soTienSauKhuyenMai;
                int soLuongTonSauKhiTru = (int) soLuongTon - index;
                qlChiTietSanPham.setSoLuongTon(soLuongTonSauKhiTru);

                qlHoaDonChiTiet = new QlHoaDonChiTiet(hoaDon, chiTietSanPham, donGiaMoi, index);
                String text = this.hoaDonChiTietServiceImpl.save(qlHoaDonChiTiet);
                JOptionPane.showMessageDialog(rootPane, text);

                setSoLuongImeiTheoSoMayMua(index, qlChiTietSanPham.getMa(), hoaDonChiTietServiceImpl.findOne(maHoaDon, qlChiTietSanPham.getMa()));
                loadDataOnGioHang();
                loadDataOnHoaDonSanPham();
            }
        } else {
            long soLuongTon = chiTietSanPhamServiceImpl.getSoLuongTonTheoTungMaSanPham(qlChiTietSanPham.getMa());
            long soLuongMua = new ImeiDabanServiceImpl().getSoLuongDaBan(qlHoaDonChiTiet.getId());
            if (soLuongTon < index) {
                JOptionPane.showMessageDialog(this, "Số lượng còn lại không đủ");
                return;
            } else if (soLuongTon > index) {
                // trưuòng hợp tìm thấy
                setSoLuongImeiTheoSoMayMua(index, qlChiTietSanPham.getMa(), qlHoaDonChiTiet);
            } else if (soLuongTon == index) {
                setSoLuongImeiTheoSoMayMua(index, qlChiTietSanPham.getMa(), qlHoaDonChiTiet);
            }
            // update xong thì update lại cái này
            int soLuongMuaSauKhiTru = (int) soLuongMua + index;
            qlHoaDonChiTiet.setSoLuong(soLuongMuaSauKhiTru);
            String text = this.hoaDonChiTietServiceImpl.update(qlHoaDonChiTiet);
            loadDataOnGioHang();
            loadDataOnHoaDonSanPham();
        }
    }

    // set số lượng khi xóa sản phẩm ra khỏi giỏ hàng
    public void setSoLuong(int index, QlHoaDonChiTiet qlHoaDonChiTiet) {
        QlChiTietSanPham qlChiTietSanPham = this.chiTietSanPhamServiceImpl.findOne(qlHoaDonChiTiet.getIdChiTietSanPham().getMa());
        int soLuongDangChon = qlHoaDonChiTiet.getSoLuong();
        long soLuongTon = chiTietSanPhamServiceImpl.getSoLuongTonTheoTungMaSanPham(qlHoaDonChiTiet.getIdChiTietSanPham().getMa());
        // validate
        // trả hàng nè
        if (soLuongDangChon < index) {
            JOptionPane.showMessageDialog(this, "Số lượng đang chọn hiện không đủ");
            return;
        } else if (soLuongDangChon > index) {
            int soLuongDangChonSauKhiTru = soLuongDangChon - index;
            qlHoaDonChiTiet.setSoLuong(soLuongDangChonSauKhiTru);
            String text = this.hoaDonChiTietServiceImpl.update(qlHoaDonChiTiet);
            setSoLuongImeiTheoSoMayKhiTra(index, qlHoaDonChiTiet.getIdChiTietSanPham().getMa(), qlHoaDonChiTiet);
            JOptionPane.showMessageDialog(rootPane, text);

            loadDataOnGioHang();
            loadDataOnHoaDonSanPham();

        } else if (soLuongDangChon == index) {
            setSoLuongImeiTheoSoMayKhiTraHetHang(index, qlHoaDonChiTiet.getIdChiTietSanPham().getMa(), qlHoaDonChiTiet);
            this.hoaDonChiTietServiceImpl.delete(qlHoaDonChiTiet);
            JOptionPane.showMessageDialog(rootPane, "Bạn đã cập nhật thành công");

            loadDataOnGioHang();
            loadDataOnHoaDonSanPham();
        }
    }

    private void loadDataCombobox() {
//        for (QlKhuyenMai qlKhuyenMai : this.khuyenMaiServiceImpl.findAll()) {
//            qlKhuyenMais.add(qlKhuyenMai);
//            comboboxBanHangKhuyenMai.addElement(qlKhuyenMai.getTen());
//        }
//        for (String ten : this.phieuBaoHanhServiceImpl.getListTenGoiBaoHanh()) {
//            comBoBoxBaoHanhGoiBaoHanh.addElement(ten);
//        }
    }

    private void loadDataKhuyenMai() {
        String maHoaDon = this.labelMaHoaDon.getText().trim();
        double tongTienBanDau = this.hoaDonChiTietServiceImpl.tinhTongTienBanDau(maHoaDon);
        tongTienSauKhiGiam = this.hoaDonChiTietServiceImpl.tinhTongTien(maHoaDon);
        double giamGia = tongTienBanDau - tongTienSauKhiGiam;
        this.txtBanHangKhuyenMai.setText(FormUtil.convertNumber(giamGia) + " VND");
        this.txtBanHangThanhToan.setText(FormUtil.convertNumber(tongTienSauKhiGiam) + " VND");
        Double tienKhachHangDua = 0.0;
        Double tienThua = tongTienSauKhiGiam - tienKhachHangDua;
        this.labelTienThua.setText("0 VND");
    }

    private QlKhuyenMai findQlKhuyenMai(String txt) {
        for (QlKhuyenMai qlKhuyenMai : qlKhuyenMais) {
            if (qlKhuyenMai.getTen().equalsIgnoreCase(txt)) {
                return qlKhuyenMai;
            }
        }
        return null;
    }

    private void loadHoaDonHoaDon() {
        tableModelHoaDonHoaDon.setRowCount(0);
        for (QlHoaDon qlHoaDon : this.hoaDonServiceImpl.findAll()) {
            tableModelHoaDonHoaDon.addRow((Object[]) qlHoaDon.getData1());
        }
    }

    private void loadHoaDonHoaDon(List<QlHoaDon> listHoaDon) {
        tableModelHoaDonHoaDon.setRowCount(0);
        for (QlHoaDon qlHoaDon : listHoaDon) {
            tableModelHoaDonHoaDon.addRow((Object[]) qlHoaDon.getData1());
        }
    }

    private void loadHoaDonHoaDonChiTiet(List<QlHoaDonChiTietReponce> listChiTietHoaDon) {
        this.tableModelHoaDonHoaDonChiTiet.setRowCount(0);
        int i = 1;
        for (QlHoaDonChiTietReponce qlHoaDonChiTietReponce : listChiTietHoaDon) {
            tableModelHoaDonHoaDonChiTiet.addRow((Object[]) qlHoaDonChiTietReponce.getData(i));
            i++;
        }
    }

    private QlKhuyenMai getDataQlKhuyenMaiFromForm() {
        Double mucGiamGiaPhanTram = 0.0, mucGiamGiaTienMat = 0.0;
        String maKhuyenMai = this.txtKhuyenMaiMaChuongTrinh.getText().trim();
        String tenKhuyenMai = this.txtKhuyenMaiTenKhuyenMai.getText().trim();
        String hinhThucGiamGia = this.cbKhuyenMaiHinhThucGiamGia.getSelectedItem().toString().trim();
        // String mucGiamGiaPhanTram
        try {
            if (hinhThucGiamGia.equalsIgnoreCase("Giảm giá theo phần trăm")) {
                // khi giảm giá theo phần trăm thì muc giảm giá theo phần trăm được thay đổi 
                mucGiamGiaPhanTram = Double.parseDouble(this.txtKhuyenMaiGiamGia.getText().trim());
                mucGiamGiaTienMat = 0.0;
            } else {
                // khi giảm giá theo tiền mặt thì mức giảm giá theo tiền mặt được thay đôi 
                mucGiamGiaTienMat = Double.parseDouble(this.txtKhuyenMaiGiamGia.getText().trim());
                mucGiamGiaPhanTram = 0.0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Bạn phải nhập số lượng tương ứng");
        }
        // String mucGiamGiaTienMat
        String mucGiamGia = this.txtKhuyenMaiGiamGia.getText().trim();
        // lấy thời gian bắt đầu và thời gian kết thúc
        Date thoiGianBatDau = this.txtKhuyenMaiThoiGianBatDau.getDate();
        Date thoiGianKetThuc = this.txtKhuyenMaiThoiGianKetThuc.getDate();
        String moTa = this.txtKhuyenMaiMoTa.getText().trim(); // mô tả chương trình
        QlKhuyenMai qlKhuyenMai = new QlKhuyenMai(1, mucGiamGiaPhanTram, mucGiamGiaTienMat, maKhuyenMai, tenKhuyenMai, moTa, thoiGianBatDau, thoiGianKetThuc);
        String validate = this.khuyenMaiServiceImpl.validate(qlKhuyenMai);///validate lại khuyến nmãi 
        if (validate == "") {
            return qlKhuyenMai;
        } else {
            JOptionPane.showMessageDialog(this, validate);
        }
        return null;
    }

    private void loadDataOnTableKhuyenMaiSanPham() {

        tableModelKhuyenMaiSanPham.setRowCount(0);
        for (QlChiTietSanPham qlChiTietSanPham : this.chiTietSanPhamServiceImpl.findAll()) {
            tableModelKhuyenMaiSanPham.addRow((Object[]) qlChiTietSanPham.getDataKhuyenMaiSanPham());
        }
    }

    private void setComboBox() {
//        this.comBoBoxBaoHanhGoiBaoHanh = new DefaultComboBoxModel();
        // set combobox gói bảo hành 
//        for (String ten : this.phieuBaoHanhServiceImpl.getListTenGoiBaoHanh()) {
//            comBoBoxBaoHanhGoiBaoHanh.addElement(ten);
//        }
//        cbBaoHanhGoiBaoHanh.setModel(comBoBoxBaoHanhGoiBaoHanh);
    }

    private QlPhieuBaoHanh getDataFromPhieuBaoHanh() {
//        String maPhieuBaoHanh = this.txtBaoHanhMaGoiBaoHanh.getText().trim();
//        String tenPhieuBaoHanh = this.txtBaoHanhTenGoiBaoHanh.getText().trim();
//        Integer thoiGianBaoHanh = Integer.parseInt(this.txtBaoHanhThoiGianBaoHanh.getText().trim());
//        String moTa = this.txtBaoHanhMoTa.getText().trim();
//        QlPhieuBaoHanh qlPhieuBaoHanh = new QlPhieuBaoHanh(1, thoiGianBaoHanh, maPhieuBaoHanh, tenPhieuBaoHanh, moTa);
//        String validate = this.phieuBaoHanhServiceImpl.validate(qlPhieuBaoHanh);
//        if (validate == "") {
//            return qlPhieuBaoHanh;
//        } else {
//            JOptionPane.showMessageDialog(this, validate);
//        }
        return null;
    }

    private void createKhuyenMaiChiTiet(String maKhuyenMai, QlChiTietSanPham qlChiTietSanPham) {
        QlKhuyenMai qlKhuyenMai = this.khuyenMaiServiceImpl.findOne(maKhuyenMai);

        QlChiTietKhuyenMai qlChiTietKhuyenMai = this.chiTietKhuyenMaiServiceImpl.createChiTietKhuyenMai(qlKhuyenMai, qlChiTietSanPham);
        chiTietKhuyenMaiServiceImpl.save(qlChiTietKhuyenMai);
    }

    private void loadDataOnTableKhuyenMaiKhuyenMai() {
        String genMa = this.khuyenMaiServiceImpl.genMaTuDong();
        this.txtKhuyenMaiMaChuongTrinh.setText(genMa);
        tableModelKhuyenMaiKhuyenMai.setRowCount(0);
        for (QlChiTietKhuyenMai qlChiTietKhuyenmai : this.chiTietKhuyenMaiServiceImpl.findAll()) {
            tableModelKhuyenMaiKhuyenMai.addRow((Object[]) qlChiTietKhuyenmai.getData());
        }
    }

    private void loadChiTietHoaDonKhiHuy(List<QlHoaDonChiTiet> listQlHoaDonChiTiets) {
        for (QlHoaDonChiTiet qlHoaDonChiTiet : listQlHoaDonChiTiets) {
            setSoLuongChiTietSanPham(qlHoaDonChiTiet);
            this.hoaDonChiTietServiceImpl.delete(qlHoaDonChiTiet);
        }
    }

    private void setSoLuongChiTietSanPham(QlHoaDonChiTiet qlHoaDonChiTiet) {
        for (QlChiTietSanPham qlChiTietSanPham : this.listChiTietSanPhams) {
            if (qlHoaDonChiTiet.getIdChiTietSanPham().getId().equals(qlChiTietSanPham.getId())) {
                int soLuongMoi = qlChiTietSanPham.getSoLuongTon() + qlHoaDonChiTiet.getSoLuong();
                qlChiTietSanPham.setSoLuongTon(soLuongMoi);
                this.chiTietSanPhamServiceImpl.update(qlChiTietSanPham);
            }
        }
    }

    private void loadThongKe(Date ngayBatDau, Date ngayKetThuc) {
        Double tongDoanhThu = this.hoaDonServiceImpl.getTongDoanhThu(ngayBatDau, ngayKetThuc);
        Long soHoaDonDaTao = this.hoaDonServiceImpl.getSoHoaDonDaTao(ngayBatDau, ngayKetThuc);
        Long soKhachHang = this.hoaDonServiceImpl.getSoKhachHang();
        Long soHoaDonDaHuy = this.hoaDonServiceImpl.getSoHoaDonDaHuy(ngayBatDau, ngayKetThuc);
        if (tongDoanhThu == null) {
            this.labelTongDoanhThu.setText("0");
        } else {
            this.labelTongDoanhThu.setText(FormUtil.convertNumber(tongDoanhThu));
        }
        if (soHoaDonDaTao == null) {
            this.labelSoHoaDon.setText("0");
        } else {
            this.labelSoHoaDon.setText(FormUtil.convertNumber(soHoaDonDaTao));
        }
        if (soHoaDonDaHuy == null) {
            this.labelSoHoaDonBiHuy.setText("0");
        } else {
            this.labelSoHoaDonBiHuy.setText(FormUtil.convertNumber(soHoaDonDaHuy));
        }
        if (soKhachHang == null) {
            this.labelSoKhachHang.setText("0");
        } else {
            this.labelSoKhachHang.setText(FormUtil.convertNumber(soKhachHang));
        }

        //load data
        tableModelThongKeThongKe.setRowCount(0);
        List<QlThongKeResponce> listThongKes = this.hoaDonChiTietServiceImpl.getSanPhamThongKeTheoThang(ngayBatDau, ngayKetThuc);
        if (listThongKes.size() != 0) {
            int stt = 1;
            for (QlThongKeResponce qlThongKe : this.hoaDonChiTietServiceImpl.getSanPhamThongKeTheoThang(ngayBatDau, ngayKetThuc)) {
                tableModelThongKeThongKe.addRow((Object[]) qlThongKe.getData(stt));
                stt++;
            }
        }
    }

    private void loadDataKhachHang() {
        tableModelKhachHangCaNhan.setRowCount(0);
        int i = 1;
        for (QlKhachHang qlKhachHang : this.khachHangServiceImpl.findAll()) {
            tableModelKhachHangCaNhan.addRow((Object[]) qlKhachHang.getData(i));
            i++;
        }
    }

    private void loadDataKhachHangLichSuGiaoDich(String maKhachHang) {
        tableModelKhachHangLichSuGiaoDich.setRowCount(0);
        for (QlHoaDon qlHoaDon : this.hoaDonServiceImpl.findAllByNameOrMa(maKhachHang)) {
            tableModelKhachHangLichSuGiaoDich.addRow((Object[]) qlHoaDon.getData1());
        }
    }

    private void loadFormKhachHang(QlKhachHang qlKhachHang) {
        txtKhachHangDiaChi.setText(qlKhachHang.getDiaChi());
        txtKhachHangSoDienThoai.setText(qlKhachHang.getSdt());
        txtKhachHangTenKhachHang.setText(qlKhachHang.getTen());
        txtKhachHangMa.setText(qlKhachHang.getMa());
        txtKhachHangMa.setEnabled(false);
    }

    private void resetFormKhachHangKhachHang() {
        txtKhachHangDiaChi.setText("");
        txtKhachHangSoDienThoai.setText("");
        txtKhachHangTenKhachHang.setText("");
        txtKhachHangMa.setText(this.khachHangServiceImpl.genMaTuDong());
        txtKhachHangMa.setEnabled(false);
    }

    private void loadDataOnNhanVienNhanVien() {
        tableModelNhanVienNhanVien.setRowCount(0);
        int i = 0;
        for (QlUser qlUser : this.userServiceImpl.findAll()) {
            tableModelNhanVienNhanVien.addRow((Object[]) qlUser.getData(i));
            i++;
        }
    }

    private void findDataOnKhachHang(String name) {
        tableModelKhachHangCaNhan.setRowCount(0);
        int i = 1;
        for (QlKhachHang qlKhachHang : this.khachHangServiceImpl.findAllByName(name)) {
            tableModelKhachHangCaNhan.addRow((Object[]) qlKhachHang.getData(i));
            i++;
        }
    }

    public Boolean Validateform() {
        String ten = txthoten.getText().trim();

        String diaChi = txtdiachi.getText().trim();
        String sdt = txtsdt.getText().trim();
        String matkhau = txtmatkhau.getText().trim();
        String cccd = txtcccd.getText().trim();
        if (ten.isBlank() || diaChi.isBlank() || sdt.isBlank() || matkhau.isBlank() || cccd.isBlank()) {
            JOptionPane.showMessageDialog(this, "Mời bạn nhập đầy đủ dữ liệu");
            return false;
        }
        if (!sdt.matches("0+\\d{9}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải 10 số và bắt đầu từ 0");
            return false;
        }
        return true;
    }

    private void findDataOnUser(String name) {
        tableModelNhanVienNhanVien.setRowCount(0);
        int i = 0;
        for (QlUser qluser : this.userServiceImpl.findAllByName(name)) {
            tableModelNhanVienNhanVien.addRow((Object[]) qluser.getData(i));
            i++;
        }
    }

    public void printPDF() throws FileNotFoundException {
        String path = "nhom4.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);// tạo file pdf
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        Document document = new Document(pdfDocument);
        float threecol = 190f;
        float twoCol = 285f;
        float twoCol150 = twoCol + 150f;
        float twoColumnWidth[] = {twoCol150, twoCol};
        float threeColumnWidth[] = {threecol, threecol, threecol};
        float fullWidth[] = {threecol * 3};
        Paragraph onesp = new Paragraph("\n");

        Table table = new Table(twoColumnWidth);
        table.addCell(new Cell().add("NHÓM 4").setBorder(Border.NO_BORDER).setBold());
        Table nestedtabe = new Table(new float[]{twoCol / 2, twoCol / 2});
        nestedtabe.addCell(getHeaderTextCell("Code Bill: "));// set ma hoa don
        nestedtabe.addCell(getHeaderTextCellValue("HD01"));
        nestedtabe.addCell(getHeaderTextCell("Created: "));
        Date date = new Date();
        nestedtabe.addCell(getHeaderTextCellValue(date.toString()));

        table.addCell(new Cell().add(nestedtabe).setBorder(Border.NO_BORDER));

        Border border = new SolidBorder(com.itextpdf.kernel.color.Color.GRAY, 1f / 2f);
        Table divider = new Table(fullWidth);
        divider.setBorder(border);
        document.add(table);
        document.add(onesp);
        document.add(divider);
        document.add(onesp);

        Table twoColTable = new Table(twoColumnWidth);
        twoColTable.addCell(getBillingandShippingCell("Staff information"));
        twoColTable.addCell(getBillingandShippingCell("Buyer information"));
        document.add(twoColTable.setMarginBottom(12f));

        Table twoColTable2 = new Table(twoColumnWidth);
        twoColTable2.addCell(getCell10fLeft("Code Staff : ", true));
        twoColTable2.addCell(getCell10fLeft("Name buyer", true));
        twoColTable2.addCell(getCell10fLeft(qlUser.getMa(), false));// teen 
        twoColTable2.addCell(getCell10fLeft(qlKhachHang.getTen(), false));
        document.add(twoColTable2);

        Table twoColTable3 = new Table(twoColumnWidth);
        twoColTable3.addCell(getCell10fLeft("Name Staff", true));
        twoColTable3.addCell(getCell10fLeft("Address", true));
        twoColTable3.addCell(getCell10fLeft(qlUser.getTen(), false));
        twoColTable3.addCell(getCell10fLeft(qlKhachHang.getDiaChi(), false));
        document.add(twoColTable3);

        float oneColoumnWidth[] = {twoCol150};

        Table oneColTable1 = new Table(oneColoumnWidth);
        oneColTable1.addCell(getCell10fLeft("Phone number", true));
        oneColTable1.addCell(getCell10fLeft(qlUser.getSdt(), false));
        document.add(oneColTable1.setMarginBottom(10f));

        Table tableDivider2 = new Table(fullWidth);
        Border dgb = new DashedBorder(com.itextpdf.kernel.color.Color.GRAY, 0.5f);
        document.add(tableDivider2.setBorder(dgb));

        Paragraph productParam = new Paragraph("Products");

        document.add(productParam.setBold());
        Table threeColTable1 = new Table(threeColumnWidth);
        threeColTable1.setBackgroundColor(com.itextpdf.kernel.color.Color.BLACK, 0.7f);

        threeColTable1.addCell(new Cell().add("Product Name").setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE).setBorder(Border.NO_BORDER));
        threeColTable1.addCell(new Cell().add("Quantity").setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        threeColTable1.addCell(new Cell().add("Price").setBold().setFontColor(com.itextpdf.kernel.color.Color.WHITE).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
        document.add(threeColTable1);
        // cột header
        Table threTable2 = new Table(threeColumnWidth);
        String maHoaDon = this.labelMaHoaDon.getText().trim();
        for (QlHoaDonChiTiet qlHoaDonChiTiet : this.hoaDonChiTietServiceImpl.getAllByMaHoaDon(maHoaDon)) {

            threTable2.addCell(new Cell().add(qlHoaDonChiTiet.getIdChiTietSanPham().getTen()).setBorder(Border.NO_BORDER).setMarginLeft(10f));
            threTable2.addCell(new Cell().add(FormUtil.convertNumber(qlHoaDonChiTiet.getDonGia())).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));

            threTable2.addCell(new Cell().add(FormUtil.convertNumber(qlHoaDonChiTiet.getDonGia())).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
        }
        document.add(threTable2.setMarginBottom(20f));
        float oneTwo[] = {threecol + 125f, threecol * 2};
        Table threeColTable4 = new Table(oneTwo);
        threeColTable1.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        threeColTable4.addCell(tableDivider2).setBorder(Border.NO_BORDER);
        document.add(threeColTable4);

        Table threeColTable3 = new Table(threeColumnWidth);
        threeColTable3.addCell(new Cell().add("").setBorder(Border.NO_BORDER).setMarginLeft(10f));
        threeColTable3.addCell(new Cell().add("Total:").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER).setMarginLeft(10f));
        threeColTable3.addCell(new Cell().add(FormUtil.convertNumber(this.hoaDonChiTietServiceImpl.tinhTongTien(maHoaDon))).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));

        document.add(threeColTable3);
        document.add(tableDivider2);
        document.add(new Paragraph("\n"));
        document.add(divider.setBorder(new SolidBorder(com.itextpdf.kernel.color.Color.GRAY, 1)).setMarginBottom(15f));
        Table tb = new Table(fullWidth);
        tb.addCell(new Cell().add(" Passion is energy. Feel the power that comes from focusing on what excites you.").setBold().setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));

        document.add(tb);
        document.close();
    }

    static Cell getHeaderTextCell(String textValue) {
        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }

    static Cell getHeaderTextCellValue(String textValue) {
        return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getBillingandShippingCell(String textValue) {
        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getCell10fLeft(String textValue, Boolean isBold) {
        Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        return isBold ? myCell.setBold() : myCell;
    }

    private void loadChartThongKe() {
        // alàm chart tương ứng với sôs lu0wjng sả phẩm bán ra tỏng ngày hôm đó 
        JFreeChart barras = ChartFactory.createBarChart("Biểu đồ số lượng từng sản phẩm ", "Số lượng sản phẩm bán theo ngày", "tam", createDataset(), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel(barras);
        chartPanel.setPreferredSize(panelThongKeHangHoa.getPreferredSize());
        jTabbedPane1.addTab("Biểu đồ", chartPanel);
    }

    private CategoryDataset createDataset() {
        //tạo data giả
        Date ngayBatDau = this.txtThongKeNgayBatDau.getDate();
        Date ngayKetThuc = this.txtThongKeNgayKetThuc.getDate();// 
        DefaultCategoryDataset tableDefault = new DefaultCategoryDataset();
        // sẽ ở đây ///mình sẽ thống kê số lượng từng tháng luôn 

        List<QlThongKeResponce> listThongKes = this.hoaDonChiTietServiceImpl.getSanPhamThongKeTheoThang(ngayBatDau, ngayKetThuc);
        if (listThongKes.size() != 0) {
            for (QlThongKeResponce qlThongKe : listThongKes) {
                tableDefault.addValue(qlThongKe.getSoLuongTheoThang(), qlThongKe.getTen(), "Sản phẩm ");
            }
        }

        return tableDefault;
    }

    public void setUIKhachLe() {
        this.labelBanHangMaKhachHang.setVisible(false);
        //this.txtKhachHangMaKhachHang.setVisible(false);
        this.btnBanHangThemKhachHang.setVisible(false);
    }

    public void setUIKhachMua() {
        this.labelBanHangMaKhachHang.setVisible(true);
        this.txtBanHangMaKhachHang.setVisible(true);
        this.btnBanHangThemKhachHang.setVisible(true);
    }

    public void setLabekKhachHang(QlKhachHang qlKhachHang) {
        System.out.println(qlKhachHang.getMa());
        txtBanHangMaKhachHang.setText(qlKhachHang.getMa());
    }

    public void setSoLuongImeiTheoSoMayMua(int index, String maChiTietSp, QlHoaDonChiTiet qlHoaDonChiTiet) {
        // set cho imei về là 1 : trạng thái đã bán
        List<Imei> imeis = chiTietSanPhamServiceImpl.getDanhSachImeiTheoTungMaSanPham(maChiTietSp);
        // set tình trạng số imei
        for (int i = 0; i < index; i++) {
            QlImeiDaBan qlImeiDaBan1 = new ImeiDabanServiceImpl().findOne(imeis.get(i).getSoImei());
            qlImeiDaBan1.setIdHoaDonChiTiet(FormUtil.convertQlHoaDonChiTietToHoaDonChiTiet(qlHoaDonChiTiet));
            qlImeiDaBan1.setTrangThai(false);
            new ImeiDabanServiceImpl().update(qlImeiDaBan1);
            // xử lý thêm imei đã bán trước rồi mới xóa
            chiTietSanPhamServiceImpl.setTinhTrangImeiKhiMuaHang(imeis.get(i).getSoImei());
        }
        // set xong thì mình lựa chọn thông minh hơn 

    }

    public void setSoLuongImeiTheoSoMayKhiTra(int index, String maChiTietSp, QlHoaDonChiTiet qlHoaDonChiTiet) {
        // set cho imei về là 1 : trạng thái đã bán
        List<ImeiDaBan> imeis = new ImeiDabanServiceImpl().getDanhSachImeiTheoTungMaSanPham(qlHoaDonChiTiet.getId());
        // set tình trạng số imei
        for (int i = 0; i < index; i++) {
            //tắt trạng thái nè
            Imei imei = chiTietSanPhamServiceImpl.getOneImei(imeis.get(i).getSoImei());
            imei.setTrangThai(false);
            chiTietSanPhamServiceImpl.updateImei(imei);
            // xử lý thêm imei đã bán trước rồi mới xóa
            new ImeiDabanServiceImpl().setTinhTrangImeiDaBanKhiTraLai(imeis.get(i).getSoImei());
        }
        // set xong thì mình lựa chọn thông minh hơn 

    }

    public void setSoLuongImeiTheoSoMayKhiTraHetHang(int index, String maChiTietSp, QlHoaDonChiTiet qlHoaDonChiTiet) {
        // set cho imei về là 1 : trạng thái đã bán
        List<ImeiDaBan> imeis = new ImeiDabanServiceImpl().getDanhSachImeiTheoTungMaSanPham(qlHoaDonChiTiet.getId());
        // set tình trạng số imei
        for (int i = 0; i < index; i++) {
            //tắt trạng thái nè
            Imei imei = chiTietSanPhamServiceImpl.getOneImei(imeis.get(i).getSoImei());
            imei.setTrangThai(false);
            chiTietSanPhamServiceImpl.updateImei(imei);
            // xử lý thêm imei đã bán trước rồi mới xóa
            new ImeiDabanServiceImpl().setTinhTrangImeiDaBanKhiTraLaiHetHang(imeis.get(i).getSoImei());
        }
        // set xong thì mình lựa chọn thông minh hơn 

    }

}
