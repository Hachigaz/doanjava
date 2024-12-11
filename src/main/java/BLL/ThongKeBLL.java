package BLL;

import java.util.ArrayList;
import java.util.HashSet;


import DAL.DataAccessLayer;
import DTO.ChitietdonnhapMD;
import DTO.ChucvuMD;
import DTO.DonNhapMD;
import DTO.DonXuatMD;
import DTO.KhoMD;
import DTO.KhuvucMD;
import DTO.Khuvuc_loaihangMD;
import DTO.Loai_hangMD;
import DTO.Mat_hangMD;
import DTO.NhanvienMD;
import DTO.Taikhoan_nhanvienMD;
import DTO.Custom.DSNhanVienMD;
import Panel.UI;
import SQL.SQLUser;

public class ThongKeBLL {
    private DataAccessLayer<KhoMD> KhoDAL;
    private DataAccessLayer<KhuvucMD> KhuVucDAL;
    private DataAccessLayer<ChitietdonnhapMD> ctdnDAL;
    private DataAccessLayer<Mat_hangMD> MatHangDAL;
    private DataAccessLayer<Loai_hangMD> LoaiHangDAL;
    private DataAccessLayer<Khuvuc_loaihangMD> kvlDAL;
    private DataAccessLayer<DonNhapMD> DonNhapDAL;
    private DataAccessLayer<DonXuatMD> DonXuatDAL;
    public ThongKeBLL(){
        SQLUser master = UI.master;
        KhoDAL = new DataAccessLayer<>(master, KhoMD.class);
        KhuVucDAL = new DataAccessLayer<>(master, KhuvucMD.class);
        ctdnDAL = new DataAccessLayer<>(master, ChitietdonnhapMD.class);
        MatHangDAL = new DataAccessLayer<>(master, Mat_hangMD.class);
        LoaiHangDAL = new DataAccessLayer<>(master, Loai_hangMD.class);
        kvlDAL = new DataAccessLayer<>(master, Khuvuc_loaihangMD.class);
        DonNhapDAL = new DataAccessLayer<>(master, DonNhapMD.class);
        DonXuatDAL = new DataAccessLayer<>(master, DonXuatMD.class);
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
    public Object[][] dsMHTrongKho(String maKho){
        ArrayList<KhuvucMD> dsKV = KhuVucDAL.getTable("MaKho ="+maKho);
        if(dsKV==null){
            return null;
        }
        Object[][] thongKeReturn = new Object[2][dsKV.size()];
        for(int i = 0;  i < dsKV.size();i++){
            thongKeReturn[0][i] = dsKV.get(i).getTenKV();
        }
        Float[] slThungMoiKho = new Float[dsKV.size()];
        for(int i = 0 ; i < slThungMoiKho.length;i++){
            slThungMoiKho[i] = 0.0f;
        }
        ArrayList<ChitietdonnhapMD> dsCTDN = ctdnDAL.getTable();
        if(dsCTDN==null){
            return null;
        }
        for(ChitietdonnhapMD ctdn : dsCTDN){
            for(int i = 0; i < dsKV.size();i++){
                if(dsKV.get(i).getMaKV().equals(ctdn.getMaKV())){
                    slThungMoiKho[i]+=(float)Math.ceil(ctdn.getSLConLai()/(float)MatHangDAL.getFirst("MaMH ="+ctdn.getMaMH()).getSoLuongMoiThung());
                }
            }
        }
        for(int i =0 ;i< slThungMoiKho.length;i++){
            thongKeReturn[1][i]=slThungMoiKho[i];
        }
        return thongKeReturn;
    }
    public ArrayList<DonNhapMD> getDanhSachDonNhap(String... statements){
        return DonNhapDAL.getTable(statements);
    }
    public ArrayList<DonXuatMD> getDanhSachDonXuat(String... statements){
        return DonXuatDAL.getTable(statements);
    }
    public ArrayList<Object[]> getDanhSachMH_KV(String maKho){
        ArrayList<Object[]> dsReturn = new ArrayList<Object[]>();

        ArrayList<KhuvucMD> dsKV = KhuVucDAL.getTable("MaKho ="+maKho);
        if(dsKV==null){
            return null;
        }       
        ArrayList<ChitietdonnhapMD> dsCTDN = ctdnDAL.getTable();
        if(dsCTDN==null){
            return null;
        }        
        ArrayList<Mat_hangMD> dsMH = MatHangDAL.getTable();
        if(dsMH==null){
            return null;
        }
        ArrayList<Loai_hangMD> dsLoai = LoaiHangDAL.getTable();
        if(dsLoai==null){
            return null;
        }
        ArrayList<Khuvuc_loaihangMD> dsKVL = kvlDAL.getTable();
        if(dsKVL==null){
            return null;
        }
        for(int i = 0; i < dsKV.size();i++){
            for(Khuvuc_loaihangMD kvl : dsKVL){
                if(kvl.getMaKV().equals(dsKV.get(i).getMaKV())){
                    float soLuongThung = 0.0f;
                    for(ChitietdonnhapMD ctdn : dsCTDN){
                        if(ctdn.getMaKV().equals(dsKV.get(i).getMaKV())){
                            for(Mat_hangMD mh : dsMH){
                                if(mh.getMaMH().equals(ctdn.getMaMH())&&mh.getMaLoai().equals(kvl.getMaLoai())){
                                    soLuongThung+=Math.ceil(ctdn.getSLConLai()/(float)mh.getSoLuongMoiThung());
                                }
                            }
                        }
                    }
                    for(Loai_hangMD loai : dsLoai){
                        if(loai.getMaLoai().equals(kvl.getMaLoai())&&soLuongThung!=0.0f){
                            dsReturn.add(new Object[]{dsKV.get(i).getTenKV(),loai.getTenloai(),soLuongThung});
                        }
                    }
                }
            }
        }
        return dsReturn;
    }

    public String[] layCacNamNhap(){
        String[] arrNgayThangNam = new String[DonNhapDAL.getTable().size()];

        HashSet<String> arrNamHash = new HashSet<>();

        for(int i=0;i<DonNhapDAL.getTable().size();i++){
            DonNhapMD dsdn = DonNhapDAL.getTable().get(i);
            arrNgayThangNam[i] = dsdn.getNgayNhap();
        }

        for(String str : arrNgayThangNam){
            arrNamHash.add(str.substring(0, 4));
        }
        String[] arrNam = new String[arrNamHash.size()];
        int index = 0;
        for(String s:arrNamHash){
            arrNam[index] = s;
            index++;
        }
        return arrNam;
    }
    public int[] arrCacThang(String nam){
        int[] soDonCacThang = new int[12];
        for(int i=0;i<12;i++){
            soDonCacThang[i] = 0;
        }
        int count = 0;
        for(int i=0;i<DonNhapDAL.getTable().size();i++){
            DonNhapMD dsdn = DonNhapDAL.getTable().get(i);
            if(nam.equals(dsdn.getNgayNhap().substring(0, 4))){
                count++;
            }
        }
        String[] arrCacThangDaChon = new String[count];
        int index=0;
        for(int i=0;i<DonNhapDAL.getTable().size();i++){
            DonNhapMD dsdn = DonNhapDAL.getTable().get(i);
            if(nam.equals(dsdn.getNgayNhap().substring(0, 4))){
                arrCacThangDaChon[index] = dsdn.getNgayNhap();
                index++;
            }
        }
        
        for(int i=0;i<arrCacThangDaChon.length;i++){
            int num = Integer.parseInt(arrCacThangDaChon[i].substring(6, 7));
            soDonCacThang[num-1]++;
        }
        return soDonCacThang;
    }
    public String[] layCacNamXuat(){
        String[] arrNgayThangNam = new String[DonXuatDAL.getTable().size()];

        HashSet<String> arrNamHash = new HashSet<>();

        for(int i=0;i<DonXuatDAL.getTable().size();i++){
            DonXuatMD dsdx = DonXuatDAL.getTable().get(i);
            arrNgayThangNam[i] = dsdx.getNgayXuat();
        }

        for(String str : arrNgayThangNam){
            arrNamHash.add(str.substring(0, 4));
        }
        String[] arrNam = new String[arrNamHash.size()];
        int index = 0;
        for(String s:arrNamHash){
            arrNam[index] = s;
            index++;
        }
        return arrNam;
    }
    public int[] arrCacThangXuat(String nam){
        int[] soDonCacThang = new int[12];
        for(int i=0;i<12;i++){
            soDonCacThang[i] = 0;
        }
        int count = 0;
        for(int i=0;i<DonXuatDAL.getTable().size();i++){
            DonXuatMD dsdx = DonXuatDAL.getTable().get(i);
            if(nam.equals(dsdx.getNgayXuat().substring(0, 4))){
                count++;
            }
        }
        String[] arrCacThangDaChon = new String[count];
        int index=0;
        for(int i=0;i<DonXuatDAL.getTable().size();i++){
            DonXuatMD dsdx = DonXuatDAL.getTable().get(i);
            if(nam.equals(dsdx.getNgayXuat().substring(0, 4))){
                arrCacThangDaChon[index] = dsdx.getNgayXuat();
                index++;
            }
        }
        for(int i=0;i<arrCacThangDaChon.length;i++){
            int num = Integer.parseInt(arrCacThangDaChon[i].substring(6, 7));
            soDonCacThang[num-1]++;
        }
        return soDonCacThang;
    }
}