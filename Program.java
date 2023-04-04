import DangNhap.DangNhap;
import SQL.SQLUser;

public class Program {
    private final String url = "jdbc:mysql://localhost:3306/QuanLyKho";
    private final String url2 = "jdbc:mysql://26.236.133.174:3306/QuanLyKho";

    private final String username = "master";
    private final String password = "123";


    //còn cái này chắc là model (kiểu như nó dùng để gọi đến cơ sở dữ liệu)
    private SQLUser master;

    public Program(){
        master = new SQLUser(url, username, password);
        this.dangNhap();
    }

    
    public void dangNhap(){
        DangNhap dn = new DangNhap(master);
        // DangNhapUI dangnhapui = new DangNhapUI();
        // uiHienTai = dangnhapui;
        // ActionListener submitDanhMucSP = new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         if(!testgiaodien){
        //             String tentk = dangnhapui.getUsernameInput();
        //             String mk = dangnhapui.getPasswordInput();
                    
        //             String sql = "select * from taikhoan_nhanvien tknv where tknv.TenTaiKhoan = '"+tentk+"' and '"+mk+"'=tknv.MatKhau";
    
        //             DataSet ds = master.getDataQuery(sql);
        //             if(ds!=null){//là tìm thấy tài khoản trong csdl
        //                 Test test = new Test(master);
        //             }
        //             else{//là không tìm thấy tài khoản trong csdl
        //                 JDialog dialog = new JDialog(uiHienTai,Dialog.ModalityType.DOCUMENT_MODAL);
        //                 dialog.setVisible(true);
        //             }
        //         }
        //         else{
        //             Test test = new Test(master);
        //         }
        //     }
        // };
        // dangnhapui.setSubmitAction(submitDanhMucSP);
    }
}
