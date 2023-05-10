package DTO.Custom;

import DTO.*;

public class DSDonXuatMD extends Model{
    
    public static final String selectStatement = "donxuat.MaDonXuat as 'Mã Đơn Xuat', donxuat.MaKho as 'Mã kho', donxuat.MaCty as 'Mã công ty', cong_ty.TenCty as 'Tên công ty',donxuat.MaNV as 'Mã Nhân viên', DATE(donxuat.NgayXuat) as 'Ngày Xuat'";
    public static final String fromStatement = "donxuat join cong_ty on donxuat.MaCty = cong_ty.MaCty ";
    public static final String groupByStatement = "";

    public DSDonXuatMD(String Madx, String Makho, String maCty, String tenCty, String Manv, String ngayXuat) {
        MaDX = Madx;
        MaKho = Makho;
        MaCty = maCty;
        TenCty = tenCty;
        MaNV = Manv;
        NgayXuat = ngayXuat;
    }

    private String MaDX;
    public String getMaDX() {
        return MaDX;
    }

    public void setMaDN(String maDX) {
        MaDX = maDX;
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

    private String NgayXuat;

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

    public String getNgayXuat() {
        return NgayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        NgayXuat = ngayXuat;
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
        String returnString = "('"+MaDX+"','"+MaKho+"',"+MaCty+",'"+TenCty+"','"+MaNV+"','"+NgayXuat+"')";
        return returnString;
    }
}
