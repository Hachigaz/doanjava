package Panel.Account;

import DAL.DataAccessLayer;
import DTO.ChitietdonnhapMD;
import DTO.ChucvuMD;
import DTO.KhoMD;
import DTO.KhuvucMD;
import DTO.Mat_hangMD;
import DTO.NhanvienMD;
import DTO.Taikhoan_nhanvienMD;
import DTO.Custom.DSNhanVienMD;
import Panel.UI;
import SQL.SQLUser;

public class ThongKeBLL {
    private DataAccessLayer<KhoMD> KhoDAL;
    private DataAccessLayer<KhuvucMD> KhuVucDAL;
    private DataAccessLayer<ChitietdonnhapMD> DonNhapDAL;
    private DataAccessLayer<Mat_hangMD> MatHangDAL;
    public ThongKeBLL(){
        SQLUser master = UI.master;
        KhoDAL = new DataAccessLayer<>(master, KhoMD.class);
        KhuVucDAL = new DataAccessLayer<>(master, KhuvucMD.class);
        DonNhapDAL = new DataAccessLayer<>(master, ChitietdonnhapMD.class);
        MatHangDAL = new DataAccessLayer<>(master, Mat_hangMD.class);
    }
    public String[] layDSKV(String maKho) {
        int count = 0;
        
        // Đếm số lượng phần tử
        for (int i = 0; i < KhuVucDAL.getTable().size(); i++) {
            KhuvucMD dsKhuvucMD = KhuVucDAL.getTable().get(i);
            if (dsKhuvucMD.getMaKho().equals(maKho)) {
                count++;
            }
        }
        
        // Tạo mảng dsTenKV với kích thước count
        String[] dsTenKV = new String[count];
        
        // Gán giá trị vào mảng dsTenKV
        int index = 0;
        for (int i = 0; i < KhuVucDAL.getTable().size(); i++) {
            KhuvucMD dsKhuvucMD = KhuVucDAL.getTable().get(i);
            if (dsKhuvucMD.getMaKho().equals(maKho)) {
                dsTenKV[index] = dsKhuvucMD.getTenKV();
                index++;
            }
        }
        
        return dsTenKV;
    }
    public float[] laySucChua(String maKho){
        int count = 0;
        
        // Đếm số lượng phần tử
        for (int i = 0; i < KhuVucDAL.getTable().size(); i++) {
            KhuvucMD dsKhuvucMD = KhuVucDAL.getTable().get(i);
            if (dsKhuvucMD.getMaKho().equals(maKho)) {
                count++;
            }
        }
        
        // Tạo mảng dsTenKV với kích thước count
        float[] dsSucChuaKV = new float[count];
        
        // Gán giá trị vào mảng dsTenKV
        int index = 0;
        for (int i = 0; i < KhuVucDAL.getTable().size(); i++) {
            KhuvucMD dsKhuvucMD = KhuVucDAL.getTable().get(i);
            if (dsKhuvucMD.getMaKho().equals(maKho)) {
                dsSucChuaKV[index] = dsKhuvucMD.getSucChua();
                index++;
            }
        }
        
        return dsSucChuaKV;
    }
    // public int[] dsMHTrongKho(String maKho){
    //     KhuVucDAL.getTable("MaKho ="+maKho);



    //     int[] dsSPTrongKho = new int[count];

        
    // }
}