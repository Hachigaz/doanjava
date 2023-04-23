use quanlykho;
insert into kho(MaKho,TenKho,DiaChi)
VALUES('K01','Kho ADV','273 An Dương Vương'),
      ('K02','Kho THD','348 Trần Hưng Đạo')




use quanlykho;
INSERT INTO `khuvuc` (`MaKho`, `MaKV`,`TenKV`, `SucChua`) VALUES 
('K01', 'K01_001','Khu vực 001', '6000'), 
('K01', 'K01_002','Khu vực 002', '9500'), 
('K01', 'K01_003','Khu vực 003', '12000'), 
('K01', 'K01_004','Khu vực 004', '8000'),
('K01', 'K01_005','Khu vực 005', '7800'), 
('K01', 'K01_006','Khu vực 006', '14000')
use quanlykho;
INSERT INTO `khuvuc_loaihang` (`MaKV`, `MaLoai`) VALUES 
('K01_001', 'LC01'), 
('K01_001', 'LC02'), 
('K01_002', 'LD01'), 
('K01_002', 'LD02'), 
('K01_003', 'LC04'), 
('K01_003', 'LC05'), 
('K01_003', 'LB01'), 
('K01_003', 'LB02'), 
('K01_004', 'LA01'), 
('K01_004', 'LA03'), 
('K01_005', 'LA01'), 
('K01_005', 'LA02'), 
('K01_006', 'LC03')
use quanlykho;
insert into loai_hang
values
('LC01','Thực phẩm đóng gói'),
('LC02','Thực phẩm đóng hộp'),
('LC03','Thực phẩm đóng chai'),
('LC04','Nước giải khát đóng chai'),
('LC05','Nước giải khát đóng lon'),
('LD01','Thực phẩm đông lạnh'),
('LD02','Thực phẩm tươi'),
('LA01','Bút/mực viết'),
('LA02','Tập/sách/giấy'),
('LA03','Dụng cụ văn phòng phẩm'),
('LB01','Thiết bị điện tử'),
('LB02','Dụng cụ gia dụng')

use quanlykho;
select * from congty

use quanlykho;
insert into congty
values('CTY_BHX','Bách hoá xanh','')


use quanlykho;
insert into nhanvien (MaNV, TenNV, MaCV, GioiTinh, NgaySinh, DiaChi, Kho_lam_viec) values ('ADMIN', 'Admin', 'CV00', '', '', '', 'K01');

insert into nhanvien (MaNV, TenNV, MaCV, GioiTinh, NgaySinh, DiaChi, Kho_lam_viec) values ('NV1001', 'Aguie De Clairmont', 'CV01', 'Nam', '21/05/1982', '34 Ramsey Junction', 'K01');
insert into nhanvien (MaNV, TenNV, MaCV, GioiTinh, NgaySinh, DiaChi, Kho_lam_viec) values ('NV2001', 'Jolyn Scarlett', 'CV01', 'Nữ', '21/11/1993', '93306 Manley Parkway', 'K02');
insert into nhanvien (MaNV, TenNV, MaCV, GioiTinh, NgaySinh, DiaChi, Kho_lam_viec) values ('NV1002', 'Melvyn Castellucci', 'CV02', 'Nam', '08/08/1985', '55 Nelson Lane', 'K01');
insert into nhanvien (MaNV, TenNV, MaCV, GioiTinh, NgaySinh, DiaChi, Kho_lam_viec) values ('NV1003', 'Barbette Stefanovic', 'CV02', 'Nữ', '07/07/1998', '0 Homewood Point', 'K01');
insert into nhanvien (MaNV, TenNV, MaCV, GioiTinh, NgaySinh, DiaChi, Kho_lam_viec) values ('NV1004', 'Cal Frissell', 'CV02', 'Nam', '21/12/1979', '63 Springs Circle', 'K01');
insert into nhanvien (MaNV, TenNV, MaCV, GioiTinh, NgaySinh, DiaChi, Kho_lam_viec) values ('NV1005', 'Milissent Mabbett', 'CV02', 'Nữ', '27/01/1990', '7847 Moland Drive', 'K01');
insert into nhanvien (MaNV, TenNV, MaCV, GioiTinh, NgaySinh, DiaChi, Kho_lam_viec) values ('NV2002', 'Lois Dobrowolny', 'CV02', 'Nam', '29/03/1989', '19 Division Crossing', 'K02');
insert into nhanvien (MaNV, TenNV, MaCV, GioiTinh, NgaySinh, DiaChi, Kho_lam_viec) values ('NV2003', 'Garrick Alliston', 'CV01', 'Nam', '09/04/1975', '483 Pawling Lane', 'K02');
insert into nhanvien (MaNV, TenNV, MaCV, GioiTinh, NgaySinh, DiaChi, Kho_lam_viec) values ('NV1006', 'Grace Sinnat', 'CV01', 'Nữ', '20/06/1987', '1 Bashford Drive', 'K01');
insert into nhanvien (MaNV, TenNV, MaCV, GioiTinh, NgaySinh, DiaChi, Kho_lam_viec) values ('NV2004', 'Joellyn Saltmarsh', 'CV02', 'Nam', '02/05/1999', '80368 Warbler Avenue', 'K02');

use quanlykho;
INSERT INTO cong_ty VALUES
('Cty_ABC','Công ty TNHH Thương Mại và Dịch Vụ ABC', '123 Nguyễn Văn Linh, Quận 7', '0901234567'),
('Cty_XYZ','Công ty Cổ phần Đầu tư Xây dựng XYZ', '456 Nguyễn Thị Minh Khai, Quận 1', '0902345678'),
('Cty_KLM','Công ty TNHH Sản xuất và Thương mại KLM', '789 Lê Văn Lương, Quận 9', '0903456789'),
('Cty_MNO','Công ty TNHH Công nghệ MNO', '101 Lý Tự Trọng, Quận 1', '0904567890'),
('Cty_PQR','Công ty Cổ phần Tài chính PQR', '246 Nguyễn Hữu Cảnh, Quận Bình Thạnh', '0905678901'),
('Cty_STU','Công ty TNHH Dịch vụ Logistics STU', '888 Nguyễn Thái Sơn, Quận Gò Vấp', '0906789012'),
('Cty_VMX','Công ty Cổ phần Thương mại và Dịch vụ VWX', '369 Hoàng Văn Thụ, Quận Tân Bình', '0907890123'),
('Cty_YZ','Công ty TNHH Thương mại và Dịch vụ YZ', '555 Lê Văn Việt, Quận 9', '0908901234'),
('Cty_HIJ','Công ty TNHH Sản xuất và Kinh doanh HIJ', '777 Xa lộ Hà Nội, Quận 2', '0909012345'),
('Cty_KNO','Công ty Cổ phần Công nghệ và Giải trí KNO', '999 Điện Biên Phủ, Quận 10', '0900123456');

use quanlykho;
insert into taikhoan_nhanvien
value
('ADMIN','Admin','123','NQ_ADMIN'),
('NV1001','NVtest1','123test','NQ_QLK'),
('NV1002','NVtest2','123test','NQ_NV'),
('NV1003','NVtest3','123test','NQ_NV'),
('NV1004','NVtest4','123test','NQ_NV'),
('NV1005','NVtest5','123test','NQ_NV'),
('NV1006','NVtest6','123test','NQ_QLK'),
('NV2001','NVtest7','123test','NQ_QLK'),
('NV2002','NVtest8','123test','NQ_NV'),
('NV2003','NVtest9','123test','NQ_QLK'),
('NV2004','NVtest10','123test','NQ_NV')

use quanlykho;
insert into nhomquyen
value
('NQ_ADMIN','Quyền quản trị'),
('NQ_QLK','Quyền quản lý kho'),
('NQ_NV','Quyền nhân viên')

use quanlykho;
insert into chucvu

values('CV01','Quản lý kho'),('CV02','Nhân viên kho'),('CV00','Quản trị')

use quanlykho
insert into mat_hang
values

use test;
select* from sinhvien

use test;
insert into lop
values('1','1',0,50),('2','1',0,35),('3','2',0,45),('4','3',0,60)


use quanlykho;
insert into quyentaikhoan
values('Q1','Xem thêm xoá sửa danh sách tài khoản nhân viên')
,('Q2', 'Xem thêm xoá sửa các thông tin mặt hàng')
,('Q3','Sửa thông tin khu vực trong kho')
,('Q4','Sửa thông tin kho')
,('Q5','Xem thêm xoá sửa các đơn nhập')
,('Q6','Xem thêm xoá sửa các đơn xuất')
,('Q7','Xem thêm xoá sửa các đơn kiểm')

use quanlykho;
insert into chitiet_nhomquyen
values('NQ_ADMIN','Q1'),
('NQ_ADMIN','Q2'),
('NQ_ADMIN','Q3'),
('NQ_ADMIN','Q4'),
('NQ_ADMIN','Q5'),
('NQ_ADMIN','Q6'),
('NQ_ADMIN','Q7')

use quanlykho;
insert into donnhap
values
('DN0003','K02','Cty_ABC','NV2001',2021/12/1),
('DN0004','K02','Cty_HIJ','NV2003',2021/2/4)

use quanlykho;
insert into khuvuc
values
('K02','K02_001','Khu vực 001',7500),
('K02','K02_002','Khu vực 002',9000)

use quanlykho;
INSERT INTO `khuvuc_loaihang` (`MaKV`, `MaLoai`) VALUES 
('K02_001', 'LA01'), 
('K02_001', 'LA02'),
('K02_002', 'LC01'), 
('K02_002', 'LC02'),
('K02_002', 'LC03')

use quanlykho;
insert into chitiet_donnhap
values
('DN0003','MH_ABC_012','K02_001',250,230),
('DN0003','MH_ABC_013','K02_001',300,280),
('DN0003','MH_ABC_016','K02_001',240,240),
('DN0003','MH_ABC_008','K02_001',240,240)


use quanlykho;
insert into chitiet_donnhap
values
('DN0004','MH_HIJ_007','K02_002',500,450),
('DN0004','MH_HIJ_008','K02_002',750,640),
('DN0004','MH_HIJ_009','K02_002',300,240)


use quanlykho;
insert into chitiet_donnhap
values
('DN0001','MH_ABC_001','K01_005',2500,2350),
('DN0001','MH_ABC_002','K01_005',3000,2800),
('DN0001','MH_ABC_003','K01_004',2400,2400),
('DN0001','MH_ABC_004','K01_004',2400,2400),
('DN0001','MH_ABC_005','K01_004',5800,5700)


use quanlykho;
insert into chitiet_donnhap
values
('DN0002','MH_HIJ_001','K01_001',1200,950),
('DN0002','MH_HIJ_003','K01_001',1500,1640),
('DN0002','MH_HIJ_005','K01_001',1750,1440)

use quanlykho;
select donnhap.MaKho,khuvuc.TenKV,sum(chitiet_donnhap.SLConLai) 
from khuvuc join khuvuc_loaihang on khuvuc.MaKV = khuvuc_loaihang.MaKV join loai_hang on khuvuc_loaihang.MaLoai = loai_hang.MaLoai join chitiet_donnhap on chitiet_donnhap.MaKV = khuvuc_loaihang.MaKV join donnhap on donnhap.MaDonNhap = chitiet_donnhap.MaDonNhap
group by khuvuc.MaKV

use quanlykho;
select kho.MaKho,khuvuc.TenKV,loai_hang.TenLoai, case when SUM(chitiet_donnhap.SLConLai) is null then 0 else ceil(SUM(chitiet_donnhap.SLConLai/mat_hang.SoLuongMoiThung)) end
from khuvuc join kho on khuvuc.MaKho = kho.MaKho join khuvuc_loaihang on khuvuc.MaKV = khuvuc_loaihang.MaKV left outer join chitiet_donnhap on chitiet_donnhap.MaKV = khuvuc_loaihang.MaKV left outer join donnhap on donnhap.MaDonNhap = chitiet_donnhap.MaDonNhap left outer join mat_hang on chitiet_donnhap.MaMh = mat_hang.MaMH left outer join loai_hang on khuvuc_loaihang.MaLoai = loai_hang.MaLoai and mat_hang.MaLoai = loai_hang.MaLoai
group by khuvuc.MaKV,loai_hang.MaLoai

use quanlykho;
select *
from khuvuc join kho on khuvuc.MaKho = kho.MaKho join khuvuc_loaihang on khuvuc.MaKV = khuvuc_loaihang.MaKV left outer join chitiet_donnhap on chitiet_donnhap.MaKV = khuvuc_loaihang.MaKV left outer join donnhap on donnhap.MaDonNhap = chitiet_donnhap.MaDonNhap left outer join mat_hang on chitiet_donnhap.MaMh = mat_hang.MaMH 
where khuvuc.MaKV = 'K01_KV_A001'
group by khuvuc.MaKV,loai_hang.MaLoai

use quanlykho;
select kho.TenKho,khuvuc.MaKV,khuvuc.TenKV,loai_hang.MaLoai,loai_hang.TenLoai, case when SUM(chitiet_donnhap.SLConLai) is null then 0 else ceil(SUM(chitiet_donnhap.SLConLai/mat_hang.SoLuongMoiThung)) end
from kho join khuvuc on kho.MaKho=khuvuc.MaKho

use quanlykho;
select kho.MaKho,khuvuc.MaKV,khuvuc.TenKV,khuvuc_loaihang.MaLoai, case when SUM(SLH.SoLuong) is null then 0 else SUM(SLH.SoLuong) end
from kho join khuvuc on kho.MaKho=khuvuc.MaKho join khuvuc_loaihang on khuvuc.MaKV = khuvuc_loaihang.MaKV
left outer join
(select donnhap.MaDonNhap,chitiet_donnhap.MaKV,mat_hang.MaMH,loai_hang.MaLoai, CEIL(chitiet_donnhap.SLConLai/mat_hang.SoLuongMoiThung) SoLuong
from donnhap join chitiet_donnhap on donnhap.MaDonNhap = chitiet_donnhap.MaDonNhap  join mat_hang on chitiet_donnhap.MaMH = mat_hang.MaMh join loai_hang on mat_hang.MaLoai = loai_hang.MaLoai
) as SLH on SLH.MaLoai = khuvuc_loaihang.MaLoai and SLH.MaKV=khuvuc.MaKV
group by khuvuc.MaKV,khuvuc_loaihang.MaLoai