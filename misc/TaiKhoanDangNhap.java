package misc;

public class TaiKhoanDangNhap {
    private String tenTK;
    private String maNV;
    private String maNhomQuyen;

    public TaiKhoanDangNhap(String tenTK, String maNV, String maNhomQuyen){
        this.tenTK = tenTK;
        this.maNV = maNV;
        this.maNhomQuyen = maNhomQuyen;
    }

    public String getTenTK() {
        return tenTK;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getMaNhomQuyen() {
        return maNhomQuyen;
    }
    
    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setMaNhomQuyen(String maNhomQuyen) {
        this.maNhomQuyen = maNhomQuyen;
    }
}
