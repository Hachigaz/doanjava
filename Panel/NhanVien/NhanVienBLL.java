package Panel.NhanVien;

import java.sql.SQLData;
import java.text.DecimalFormat;
import java.util.ArrayList;

import DAL.DataAccessLayer;
import DTO.ChucvuMD;
import DTO.KhoMD;
import DTO.KhuvucMD;
import DTO.NhanvienMD;
import DTO.Taikhoan_nhanvienMD;
import DTO.Custom.DSNhanVienMD;
import SQL.SQLUser;
import Panel.UI;

public class NhanVienBLL {
    private DataAccessLayer<DSNhanVienMD> DSNhanVienDAL;
    private DataAccessLayer<NhanvienMD> NhanVienDAL;
    private DataAccessLayer<KhoMD> KhoDAL;
    private DataAccessLayer<Taikhoan_nhanvienMD> TaiKhoanDAL;
    private DataAccessLayer<ChucvuMD> ChucVuDAL;
    public NhanVienBLL(){
        SQLUser master = UI.master;
        DSNhanVienDAL = new DataAccessLayer<>(master,DSNhanVienMD.class);
        NhanVienDAL = new DataAccessLayer<>(master, NhanvienMD.class);
        KhoDAL = new DataAccessLayer<>(master, KhoMD.class);
        TaiKhoanDAL = new DataAccessLayer<>(master, Taikhoan_nhanvienMD.class);
        ChucVuDAL = new DataAccessLayer<>(master, ChucvuMD.class);
    }
    public ArrayList<DSNhanVienMD> getDanhSachNhanVien(String... statements){
        return DSNhanVienDAL.getTable(statements);
    }
    public void themTKmoi(Taikhoan_nhanvienMD taikhoan){
        TaiKhoanDAL.addOne(taikhoan);
    }
    public void xoaTK(String... keys){
        TaiKhoanDAL.remove(keys);
    }
    public void suaTK(String key,String... statements){
        TaiKhoanDAL.update(key, statements);
    }
    public void themNVmoi(NhanvienMD nhanvienMoi){
        NhanVienDAL.addOne(nhanvienMoi);
    }
    public void xoaNV(String... keys){
        NhanVienDAL.remove(keys);
    }
    public void suaNV(String key,String... statements){
        NhanVienDAL.update(key, statements);
    }
    public ArrayList<NhanvienMD> getDSNhanVien(String... statements){
        return NhanVienDAL.getTable(statements);
    }
    public ArrayList<DSNhanVienMD> getDsNhanVienMD(String... statements){
        return DSNhanVienDAL.getTable(statements);
    }
    public String[] layTenChucVu(){
        String[] dsTenChucVu = new String[ChucVuDAL.getTable().size()];
        for(int i=0;i<ChucVuDAL.getTable().size();i++){
            ChucvuMD dschucvu = ChucVuDAL.getTable().get(i);
            dsTenChucVu[i] = dschucvu.getTenCV();
        }
        return dsTenChucVu;
    }
    public int layMa(){
        int count = 0;
        for(int i=1;i<NhanVienDAL.getTable().size();i++){
            NhanvienMD NVtemp = NhanVienDAL.getTable().get(i);
            String maStr = NVtemp.getMaNV().substring(NVtemp.getMaNV().length()-3);
            int maNum = Integer.parseInt(maStr);
            if(maNum > count){
                count = maNum;
            }
        }
        return count+1;
    }
    public String[] layTenKho(){
        String[] DsTenKho = new String[KhoDAL.getTable().size()];
        for(int i=0;i<KhoDAL.getTable().size();i++){
            KhoMD DsKho = KhoDAL.getTable().get(i);
            String tenKho = DsKho.getTenKho();
            DsTenKho[i] = tenKho;
        }
        return DsTenKho;
    }
    public String[] layTaiKhoan(String manvien){
        String[] taikhoan = new String[4];
        for(int i=0;i<TaiKhoanDAL.getTable().size();i++){
            Taikhoan_nhanvienMD dsTK = TaiKhoanDAL.getTable().get(i);
            String maNV = dsTK.getMaNV();
            if(maNV.equals(manvien)){
                taikhoan[0] = maNV;
                taikhoan[1] = dsTK.getTenTaiKhoan();
                taikhoan[2] = dsTK.getMatKhau();
                taikhoan[3] = dsTK.getMatKhau();
            }
        }
        return taikhoan;
    }
    public String layMaNV(String tenNV){
        String maNV = "";
        for(int i=0;i<TaiKhoanDAL.getTable().size();i++){
            NhanvienMD dsNV = NhanVienDAL.getTable().get(i);
            if(dsNV.getTenNV().equals(tenNV)){
                maNV = dsNV.getMaNV();
            }
        }
        return maNV;
    }
    public int[] layPhanTramChucVu(){
        String[] dsMaCV = new String[3];
        ChucvuMD dsChucvu;
        int[] dsTyle = new int[3];
        int quantri = 0;
        int quanlykho = 0;
        int nhanvienkho = 0;
        for(int i=0;i<ChucVuDAL.getTable().size();i++){
            dsChucvu = ChucVuDAL.getTable().get(i);
            dsMaCV[i] = dsChucvu.getMaCV();
        }
        for(int i=0;i<NhanVienDAL.getTable().size();i++){
            NhanvienMD dsNV = NhanVienDAL.getTable().get(i);
            if(dsNV.getMaCV().equals(dsMaCV[0])){
                quantri++;
            }else if(dsNV.getMaCV().equals(dsMaCV[1])){
                quanlykho++;
            }else if(dsNV.getMaCV().equals(dsMaCV[2])){
                nhanvienkho++;
            }
        }
        dsTyle[0] = quantri;
        dsTyle[1] = quanlykho;
        dsTyle[2] = nhanvienkho;
        return dsTyle;
    }
    public String[] layMaKho(){
        String[] DsMaKho = new String[KhoDAL.getTable().size()];
        for(int i=0;i<KhoDAL.getTable().size();i++){
            KhoMD dsKho = KhoDAL.getTable().get(i);
            String maKho = dsKho.getMaKho();
            DsMaKho[i] = maKho;
        }
        return DsMaKho;
    }
    public int laySoLuongKho(){
        return KhoDAL.getTable().size();
    }
    public int laySoLuongNhanVien(){
        return NhanVienDAL.getTable().size();
    }
}
