package DTO.Custom;

import DTO.Model;

public class ThongTinSPMD extends Model{
    public static final String selectStatement = "mat_hang.MaCty, mat_hang.MaMH, mat_hang.TenMH, loai_hang.MaLoai, loai_hang.TenLoai";
    public static final String fromStatement = "mat_hang join loai_hang on mat_hang.MaLoai = loai_hang.MaLoai";
    public static final String groupByStatement = "";
    private String MaCty;
    private String MaMH;
    private String TenMH;
    private String MaLoai;
    private String TenLoai;
    public ThongTinSPMD(String maCty, String maMH, String tenMH, String maLoai, String tenLoai) {
        MaCty = maCty;
        MaMH = maMH;
        TenMH = tenMH;
        MaLoai = maLoai;
        TenLoai = tenLoai;
    }
    @Override
    public String getSelectStatement() {
        return ThongTinSPMD.selectStatement;
    }
    @Override
    public String getFromStatement() {
        return ThongTinSPMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return null;
    }
    public static String getSelectstatement() {
        return selectStatement;
    }
    public static String getFromstatement() {
        return fromStatement;
    }
    public static String getGroupbystatement() {
        return groupByStatement;
    }
    public String getMaCty() {
        return MaCty;
    }
    public void setMaCty(String maCty) {
        MaCty = maCty;
    }
    public String getMaMH() {
        return MaMH;
    }
    public void setMaMH(String maMH) {
        MaMH = maMH;
    }
    public String getTenMH() {
        return TenMH;
    }
    public void setTenMH(String tenMH) {
        TenMH = tenMH;
    }
    public String getMaLoai() {
        return MaLoai;
    }
    public void setMaLoai(String maLoai) {
        MaLoai = maLoai;
    }
    public String getTenLoai() {
        return TenLoai;
    }
    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }
}
