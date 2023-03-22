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

use quanlykho
insert into mat_hang
values

use test;
select* from sinhvien

use test;
insert into lop
values('1','1',0,50),('2','1',0,35),('3','2',0,45),('4','3',0,60)