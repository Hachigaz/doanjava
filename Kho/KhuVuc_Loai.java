package Kho;

public class KhuVuc_Loai {
    private String maKho;
    private String maKhuVuc;
    private String maLoaiHang;
    
    public KhuVuc_Loai(){
        maKho = "";
        maKhuVuc = "";
        maLoaiHang = "";
    }

    public KhuVuc_Loai(String maKho, String maKhuVuc, String maLoaiHang) {
        this.maKho = maKho;
        this.maKhuVuc = maKhuVuc;
        this.maLoaiHang = maLoaiHang;
    }

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public String getMaKhuVuc() {
        return maKhuVuc;
    }

    public void setMaKhuVuc(String maKhuVuc) {
        this.maKhuVuc = maKhuVuc;
    }

    public String getMaLoaiHang() {
        return maLoaiHang;
    }

    public void setMaLoaiHang(String maLoaiHang) {
        this.maLoaiHang = maLoaiHang;
    }
    
}
