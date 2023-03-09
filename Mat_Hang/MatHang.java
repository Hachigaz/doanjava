package Mat_Hang;

public class MatHang {
    protected String maMatHang;
    protected String tenMatHang;
    protected String maLoaiHang;
    protected String maHangSX;
    protected String tenCongTy;

    public MatHang() {
        maMatHang = "";
        tenMatHang = "";
        maLoaiHang = "";
        maHangSX = "";
        tenCongTy = "";
    }
    
    public MatHang(String maMatHang, String tenMatHang, String maLoaiHang, String maHangSX, String tenCongTy) {
        this.maMatHang = maMatHang;
        this.tenMatHang = tenMatHang;
        this.maLoaiHang = maLoaiHang;
        this.maHangSX = maHangSX;
        this.tenCongTy = tenCongTy;
    }

    public MatHang(MatHang a){
        this.maHangSX = a.maHangSX;
        this.tenMatHang = a.tenMatHang;
        this.maLoaiHang = a.maLoaiHang;
        this.maHangSX = a.maHangSX;
        this.tenCongTy = a.tenCongTy;
    }
    public String getMaMatHang() {
        return maMatHang;
    }

    public void setMaMatHang(String maMatHang) {
        this.maMatHang = maMatHang;
    }

    public String getTenMatHang() {
        return tenMatHang;
    }

    public void setTenMatHang(String tenMatHang) {
        this.tenMatHang = tenMatHang;
    }

    public String getMaLoaiHang() {
        return maLoaiHang;
    }

    public void setMaLoaiHang(String maLoaiHang) {
        this.maLoaiHang = maLoaiHang;
    }

    public String getMaHangSX() {
        return maHangSX;
    }

    public void setMaHangSX(String maHangSX) {
        this.maHangSX = maHangSX;
    }

    public String getTenCongTy() {
        return tenCongTy;
    }

    public void setTenCongTy(String tenCongTy) {
        this.tenCongTy = tenCongTy;
    }
}
