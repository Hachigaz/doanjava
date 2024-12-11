package DTO.Custom;

import DTO.*;

public class DSDonNhapMD extends Model{
    
    public static final String selectStatement = "donnhap.MaDonNhap as 'Mã Đơn Nhập', donnhap.MaKho as 'Mã kho', donnhap.MaCty as 'Mã công ty', cong_ty.TenCty as 'Tên công ty',donnhap.MaNV as 'Mã Nhân viên', DATE_FORMAT(DATE(donnhap.NgayNhap), '%d/%m/%Y') as 'Ngày nhập'";
    public static final String fromStatement = "donnhap join cong_ty on donnhap.MaCty = cong_ty.MaCty ";
    public static final String groupByStatement = "";

    public DSDonNhapMD(String Madn, String Makho, String maCty, String tenCty, String Manv, String ngayNhap) {
        MaDN = Madn;
        MaKho = Makho;
        MaCty = maCty;
        TenCty = tenCty;
        MaNV = Manv;
        NgayNhap = ngayNhap;
    }

    private String MaDN;
    public String getMaDN() {
        return MaDN;
    }

    public void setMaDN(String maDN) {
        MaDN = maDN;
    }

    private String MaKho;
    public String getMaKho() {
        return MaKho;
    }

    public void setMaKho(String maKho) {
        MaKho = maKho;
    }

    private String MaCty;
    public String getMaCty() {
        return MaCty;
    }

    public void setMaCty(String maCty) {
        MaCty = maCty;
    }

    private String TenCty;
    private String MaNV;
    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    private String NgayNhap;

    public static String getSelectstatement() {
        return selectStatement;
    }

    public static String getFromstatement() {
        return fromStatement;
    }

   

    public String getTenCty() {
        return TenCty;
    }

    public void setTenCty(String tenCty) {
        TenCty = tenCty;
    }

    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        NgayNhap = ngayNhap;
    }

    @Override
    public String getSelectStatement() {
        return DSTraCuuHangMD.selectStatement;
    }
    
    @Override
    public String getFromStatement() {
        return DSTraCuuHangMD.fromStatement;
    }

    @Override
    public String toSQLString() {
        String returnString = "('"+MaDN+"','"+MaKho+"',"+MaCty+",'"+TenCty+"','"+MaNV+"','"+NgayNhap+"')";
        return returnString;
    }
}
