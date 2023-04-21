package Model.Custom;

import Model.Model;

public class DSChiTietKhuVucLoaiMD extends Model{

    public static final String selectStatement = "kho.TenKho,khuvuc.TenKV,loai_hang.TenLoai, case when SUM(chitiet_donnhap.SLConLai) is null then 0 else ceil(SUM(chitiet_donnhap.SLConLai/mat_hang.SoLuongMoiThung)) end";
    public static final String fromStatement = "khuvuc join kho on khuvuc.MaKho = kho.MaKho join khuvuc_loaihang on khuvuc.MaKV = khuvuc_loaihang.MaKV join loai_hang on khuvuc_loaihang.MaLoai = loai_hang.MaLoai left outer join chitiet_donnhap on chitiet_donnhap.MaKV = khuvuc_loaihang.MaKV left outer join donnhap on donnhap.MaDonNhap = chitiet_donnhap.MaDonNhap left outer join mat_hang on chitiet_donnhap.MaMh = mat_hang.MaMH";
    public static final String groupByStatement = "khuvuc.MaKV,loai_hang.MaLoai";
    private String TenKho;
    private String TenKhuVuc;
    private String TenLoai;
    private Float SoLuongChua;

    public DSChiTietKhuVucLoaiMD(String tenKho, String tenKhuVuc, String tenLoai, Float soLuongChua) {
        TenKho = tenKho;
        TenKhuVuc = tenKhuVuc;
        TenLoai = tenLoai;
        SoLuongChua = soLuongChua;
    }
    public DSChiTietKhuVucLoaiMD(String tenKho, String tenKhuVuc, String tenLoai, String soLuongChua) {
        TenKho = tenKho;
        TenKhuVuc = tenKhuVuc;
        TenLoai = tenLoai;
        SoLuongChua = Float.parseFloat(soLuongChua);
    }

    public String getTenKho() {
        return TenKho;
    }
    public String getTenKhuVuc() {
        return TenKhuVuc;
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
    public void setTenKhuVuc(String tenKhuVuc) {
        TenKhuVuc = tenKhuVuc;
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
