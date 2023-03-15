package Kho;

public class KhuVuc {
    private String maKho;
    private String maKhuVuc;
    private float sucChua;

    public KhuVuc() {
        maKho = "";
        maKhuVuc = "";
        sucChua = 0;
    }

    public KhuVuc(String maKho, String maKhuVuc, float sucChua) {
        this.maKho = maKho;
        this.maKhuVuc = maKhuVuc;
        this.sucChua = sucChua;
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

    public float getSucChua() {
        return sucChua;
    }

    public void setSucChua(float sucChua) {
        this.sucChua = sucChua;
    }
}
