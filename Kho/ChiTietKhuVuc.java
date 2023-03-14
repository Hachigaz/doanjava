package Kho;

public class ChiTietKhuVuc {
    private String maKho;
    private String maKhuVuc;
    private String maMH;
    private int soLuong;
    private float tongTheTich;
    
    public ChiTietKhuVuc(){
        maKho = "";
        maKhuVuc = "";
        maMH = "";
        soLuong = 0;
        tongTheTich = 0;
    }

    public ChiTietKhuVuc(String maKho, String maKhuVuc, String maMH, int soLuong, float tongTheTich) {
        this.maKho = maKho;
        this.maKhuVuc = maKhuVuc;
        this.maMH = maMH;
        this.soLuong = soLuong;
        this.tongTheTich = tongTheTich;
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

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getTongTheTich() {
        return tongTheTich;
    }

    public void setTongTheTich(float tongTheTich) {
        this.tongTheTich = tongTheTich;
    }
}
