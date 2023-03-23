use quanlykho;
insert into kho(MaKho,TenKho,DiaChi)
VALUES('K01','Kho ADV','273 An Dương Vương'),
('K02','Kho THD','348 Trần Hưng Đạo')

use quanlykho;
insert into khuvuc
values('K01','KV_D001','14000.0')
,('K01','KV_B002','8000.0')

use quanlykho;
insert into loai_hang
values('LC02','Thực phẩm đóng hộp')
,('LA01','Bút/mực viết')
,('LA02','Tập/sách/giấy')
,('LC01','Thực phẩm đóng gói')
,('LB01','Thiết bị điện tử')


use quanlykho;
insert into loai_hang
values('LC06','Thực phẩm đông lạnh')

use quanlykho;
select * from loai_hang

use quanlykho;
insert into nhanvien (MaNV, HoTen, MaChucVu, GioiTinh, NgaySinh, DiaChi, MaKho) values ('NV1001', 'Aguie De Clairmont', 'CV01', 'Nam', '21/05/1982', '34 Ramsey Junction', 'K01');
insert into nhanvien (MaNV, HoTen, MaChucVu, GioiTinh, NgaySinh, DiaChi, MaKho) values ('NV2001', 'Jolyn Scarlett', 'CV01', 'Nữ', '21/11/1993', '93306 Manley Parkway', 'K02');
insert into nhanvien (MaNV, HoTen, MaChucVu, GioiTinh, NgaySinh, DiaChi, MaKho) values ('NV1002', 'Melvyn Castellucci', 'CV02', 'Nam', '08/08/1985', '55 Nelson Lane', 'K01');
insert into nhanvien (MaNV, HoTen, MaChucVu, GioiTinh, NgaySinh, DiaChi, MaKho) values ('NV1003', 'Barbette Stefanovic', 'CV02', 'Nữ', '07/07/1998', '0 Homewood Point', 'K01');
insert into nhanvien (MaNV, HoTen, MaChucVu, GioiTinh, NgaySinh, DiaChi, MaKho) values ('NV1004', 'Cal Frissell', 'CV02', 'Nam', '21/12/1979', '63 Springs Circle', 'K01');
insert into nhanvien (MaNV, HoTen, MaChucVu, GioiTinh, NgaySinh, DiaChi, MaKho) values ('NV1005', 'Milissent Mabbett', 'CV02', 'Nữ', '27/01/1990', '7847 Moland Drive', 'K01');
insert into nhanvien (MaNV, HoTen, MaChucVu, GioiTinh, NgaySinh, DiaChi, MaKho) values ('NV2002', 'Lois Dobrowolny', 'CV02', 'Nam', '29/03/1989', '19 Division Crossing', 'K02');
insert into nhanvien (MaNV, HoTen, MaChucVu, GioiTinh, NgaySinh, DiaChi, MaKho) values ('NV2003', 'Garrick Alliston', 'CV01', 'Nam', '09/04/1975', '483 Pawling Lane', 'K02');
insert into nhanvien (MaNV, HoTen, MaChucVu, GioiTinh, NgaySinh, DiaChi, MaKho) values ('NV1006', 'Grace Sinnat', 'CV01', 'Nữ', '20/06/1987', '1 Bashford Drive', 'K01');
insert into nhanvien (MaNV, HoTen, MaChucVu, GioiTinh, NgaySinh, DiaChi, MaKho) values ('NV2004', 'Joellyn Saltmarsh', 'CV02', 'Nam', '02/05/1999', '80368 Warbler Avenue', 'K02');

use quanlykho;
insert into chucvu
values('CV01','Quản lý kho'),('CV02','Nhân viên kho')

use quanlykho;
SELECT * FROM KHUVUC

use quanlykho;
insert into khuvuc_loai
values('K01','KV_A001','LC01'),('K01','KV_A001','LC02')
,('K01','KV_A002','LD01'),('K01','KV_A002','LD02')
,('K01','KV_B001','LC04'),('K01','KV_B001','LD03'),('K01','KV_B001','LD02')
,('K01','KV_D001','LC03')
,('K01','KV_C001','LA02'),('K01','KV_C001','LA01')
,('K01','KV_B002','LA01')

use quanlykho;
insert into mat_hang
values('MH_CC007','CocaCola chai Diet','CTY_CoCa','LC03')
,('MH_CC008','CocaCola lon Diet','CTY_CoCa','LC04')
,('MH_BHX001','Nước xả Downy xanh','CTY_BHX','LD02')
,('MH_BHX002','Nước xả Downy đỏ','CTY_BHX','LD02')
,('MH_BHX003','Nước xả Downy tím','CTY_BHX','LD02')
,('MH_BHX004','Nước xả Downy vàng','CTY_BHX','LD02')
,('MH_BHX005','Mì gói Gấu đỏ tôm chua cay','CTY_BHX','LC01')
,('MH_BHX006','Mì gói Gấu đỏ hành gà','CTY_BHX','LC01')
,('MH_BHX007','Mì gói Gấu đỏ sườn tỏi','CTY_BHX','LC01')
,('MH_BHX008','Mì gói Hảo Hảo tôm chua cay','CTY_BHX','LC01')
,('MH_BHX009','Mì gói Hảo Hảo hành gà tím','CTY_BHX','LC01')
,('MH_BHX010','Mì gói Omachi Spagetti','CTY_BHX','LC01')

,('MH_BHX011','Bột giặt OMO','CTY_BHX','LD03')
,('MH_BHX012','Bột giặt Tide','CTY_BHX','LD03')

,('MH_BHX013','Nước rửa chén Sunlight xanh','CTY_BHX','LD04')
,('MH_BHX014','Nước rửa chén Sunlight đỏ','CTY_BHX','LD04')
,('MH_BHX015','Nước rửa chén Sunlight vàng','CTY_BHX','LD04')

,('MH_KFC001','Gà đóng hộp nhập khẩu từ trung quốc','CTY_KFC','LC02')
,('MH_KFC002','Gà không rõ nguồn gốc','CTY_KFC','LC02')
,('MH_KFC001','Gà viên đông lạnh','CTY_KFC','LC02')


use quanlykho
insert into mat_hang
values

use test;
select* from sinhvien

use test;
insert into lop
values('1','1',0,50),('2','1',0,35),('3','2',0,45),('4','3',0,60)

use quanlykho;
select kho.TenKho,kho.DiaChi,khuvuc.MaKV,khuvuc.SucChua,loai_hang.TenLoai from kho,khuvuc,khuvuc_loai,loai_hang
where kho.MaKho = khuvuc.MaKho and khuvuc.MaKV = khuvuc_loai.MaKV
    and khuvuc_loai.MaLoai = loai_hang.MaLoai