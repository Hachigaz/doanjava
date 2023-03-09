package Nhan_vien;

public class DanhSachNhanVien {
    NhanVien[] danhSachNV;
    private int count;
    private int arrSize;
    public DanhSachNhanVien(){
        danhSachNV = null;
        count = 0;
        arrSize = 0;
    }
    
    public int laySoLuong(){
        return this.count;
    }
    
    public void themNhanVien(NhanVien nv){
        if(count==arrSize){
            arrSize++;
            NhanVien newDS[] = new NhanVien[arrSize];
            for(int i=0;i<this.count;i++)
            {
                newDS[i] = this.danhSachNV[i];
            }
            this.danhSachNV = newDS;
        }
        this.danhSachNV[count] = nv;
        count++;
    }
    
    public void suaNhanVien(int index, NhanVien nv){/*Sua nhan vien thu i*/
        if(index >= this.count){
            return;
        }else{
            this.danhSachNV[index] = nv;
        }
    }
    
    public void xoaNhanVien(int index){
        if(index >= this.count){
            return;
        }else{
            this.count--;
        }
        for(int i=index;i<this.count;i++){
            if(i<this.count){
                this.danhSachNV[i] = this.danhSachNV[i+1];
            }
        }
    }
    
    
}
