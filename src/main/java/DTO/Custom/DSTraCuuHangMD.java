package DTO.Custom;

import DTO.*;

public class DSTraCuuHangMD extends Model{
    
    public static final String selectStatement = "khuvuc.TenKV as 'Khu vực', mat_hang.TenMH as 'Tên mặt hàng', chitiet_donnhap.SLConLai as 'Số lượng', loai_hang.TenLoai as 'Loại sản phẩm', cong_ty.TenCty as 'Tên công ty', DATE_FORMAT(DATE(donnhap.NgayNhap), '%d/%m/%Y') as 'Ngày nhập'";
    public static final String fromStatement = "chitiet_donnhap join mat_hang on chitiet_donnhap.MaMH = mat_hang.MaMH join khuvuc on chitiet_donnhap.MaKV = khuvuc.MaKV join donnhap on chitiet_donnhap.MaDonNhap = donnhap.MaDonNhap join loai_hang on mat_hang.MaLoai = loai_hang.MaLoai join cong_ty on mat_hang.MaCty = cong_ty.MaCty";
    public static final String groupByStatement = "";

    public DSTraCuuHangMD(String tenKV, String tenMH, Float sLConLai, String tenLoai, String tenCty, String ngayNhap) {
        TenKV = tenKV;
        TenMH = tenMH;
        SLConLai = sLConLai;
        TenLoai = tenLoai;
        TenCty = tenCty;
        NgayNhap = ngayNhap;
    }

    private String TenKV;
    private String TenMH;
    private Float SLConLai;
    private String TenLoai;
    private String TenCty;
    private String NgayNhap;

    public static String getSelectstatement() {
        return selectStatement;
    }

    public static String getFromstatement() {
        return fromStatement;
    }

    public String getTenKV() {
        return TenKV;
    }

    public void setTenKV(String tenKV) {
        TenKV = tenKV;
    }

    public String getTenMH() {
        return TenMH;
    }

    public void setTenMH(String tenMH) {
        TenMH = tenMH;
    }

    public Float getSLConLai() {
        return SLConLai;
    }

    public void setSLConLai(Float sLConLai) {
        SLConLai = sLConLai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
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
        String returnString = "('"+TenKV+"','"+TenMH+"',"+SLConLai+",'"+TenLoai+"','"+TenCty+"','"+NgayNhap+"')";
        return returnString;
    }
}
