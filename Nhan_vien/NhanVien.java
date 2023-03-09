package Nhan_vien;

public class NhanVien {
    protected String maNV;
    protected String hoTen;
    protected String chucVu;
    protected String gioiTinh;
    protected String ngaySinh;
    protected String diaChi;
    protected String soDienThoai;
    protected double luong;

    public NhanVien(){
        maNV = "";
        hoTen = "";
        chucVu = "";
        gioiTinh = "";
        ngaySinh = "";
        diaChi = "";
        soDienThoai = "";
        luong = 0;
    }
    
    public NhanVien(String maNV, String hoTen, String chucVu, String gioiTinh, String ngaySinh, String soDienThoai, double luong) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.chucVu = chucVu;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.luong = luong;
    }
    
    public NhanVien(NhanVien a){
        this.maNV = a.maNV;
        this.hoTen = a.hoTen;
        this.chucVu = a.chucVu;
        this.gioiTinh = a.gioiTinh;
        this.ngaySinh = a.ngaySinh;
        this.diaChi = a.diaChi;
        this.soDienThoai = a.soDienThoai;
        this.luong = a.luong;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }
}
