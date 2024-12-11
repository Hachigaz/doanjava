package DTO.Custom;

import DTO.Model;

public class DSChiTietKhuVucLoaiMD extends Model{

    public static final String selectStatement = "khuvuc.MaKho,khuvuc.MaKV,khuvuc.TenKV, khuvuc_loaihang.MaLoai,loai_hang.TenLoai,case when SLH.SoLuong is null then 0 else SLH.SoLuong end as SoLuong";
    public static final String fromStatement = "kho join khuvuc on kho.MaKho = khuvuc.MaKho join khuvuc_loaihang on khuvuc.MaKV = khuvuc_loaihang.MaKV join loai_hang on loai_hang.MaLoai = khuvuc_loaihang.MaLoai left outer join (select chitiet_donnhap.MaKV,mat_hang.MaLoai,sum(chitiet_donnhap.SLConLai) SoLuong from chitiet_donnhap join mat_hang on chitiet_donnhap.MaMH = mat_hang.MaMH group by chitiet_donnhap.MaKV,mat_hang.MaLoai) as SLH on khuvuc_loaihang.MaLoai = SLH.MaLoai and khuvuc_loaihang.MaKV = SLH.MaKV";
    public static final String groupByStatement = "";
    
    private String TenKho;
    private String MaKV;
    private String TenKhuVuc;
    private String MaLoai;
    private String TenLoai;
    private Float SoLuongChua;

    public DSChiTietKhuVucLoaiMD(String tenKho, String maKV,String tenKhuVuc,String maLoai, String tenLoai, Float soLuongChua) {
        TenKho = tenKho;
        MaKV=maKV;
        TenKhuVuc = tenKhuVuc;
        MaLoai = maLoai;
        TenLoai = tenLoai;
        SoLuongChua = soLuongChua;
    }
    public DSChiTietKhuVucLoaiMD(String tenKho,String maKV, String tenKhuVuc,String maLoai, String tenLoai, String soLuongChua) {
        TenKho = tenKho;
        MaKV=maKV;
        TenKhuVuc = tenKhuVuc;
        MaLoai = maLoai;
        TenLoai = tenLoai;
        SoLuongChua = Float.parseFloat(soLuongChua);
    }

    public String getTenKho() {
        return TenKho;
    }
    public String getMaKV() {
        return MaKV;
    }
    public String getTenKhuVuc() {
        return TenKhuVuc;
    }
    public String getMaLoai() {
        return MaLoai;
    }
    public String getTenLoai() {
        return TenLoai;
    }
    public Float getSoLuongChua() {
        return SoLuongChua;
    }

    public void setTenKho(String tenKho) {
        TenKho = tenKho;
    }
    public void setMaKV(String maKV) {
        MaKV = maKV;
    }
    public void setTenKhuVuc(String tenKhuVuc) {
        TenKhuVuc = tenKhuVuc;
    }
    public void setMaLoai(String maLoai) {
        MaLoai = maLoai;
    }
    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }
    public void setSoLuongChua(Float soLuongChua) {
        SoLuongChua = soLuongChua;
    }

    @Override
    public String getSelectStatement() {
        return DSChiTietKhuVucLoaiMD.selectStatement;
    }
    @Override
    public String getFromStatement() {
        return DSChiTietKhuVucLoaiMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "";
    }

    
}
