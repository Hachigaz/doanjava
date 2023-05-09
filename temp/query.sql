use test;
create trigger themsvvaolop before insert on chitietlop
for each row
begin
set @soluong = 0
select count(MaSV) = @soluong from chitietlop 
                where chitietlop.MaLop = new.MaLop
set @soluongmax = select TongSoLuong from lop where lop.MaLop = new.MaLop
if(@soluongmax = @soluong)
begin
    select 'da du' AS '** DEBUG:';
end
else
begin
    select 'chua du' AS '** DEBUG:';
end
end

use test;
DROP TRIGGER IF EXISTS kiemtrathaydoisoluong;
create trigger kiemtrathaydoisoluong before insert on chitietlop
for each row
begin
    declare a int(10);
    if 3<4 then set a=3;
    endif;
end;

(select TongSoLuong from lop where lop.MaLop = new.MaLop)
<(select count(MaSV) from chitietlop where chitietlop.MaLop = new.MaLop)

use test;
insert into chitietlop
value(1,2),(2,1),(3,1),(1,3),(2,5)


use quanlykho;
select * from mat_hang;
select * from nhanvien


use quanlykho;
create trigger trig_DonXuat before insert on chitiet_donxuat
for each row
begin
    if(select ctdn.SLConLai from chitiet_donnhap ctdn 
    where ctdn.MaMH = new.MaMH and new.MaDonNhap = ctdn.MaDonNhap) >= new.SoLuong
    then 
        update chitiet_donnhap
        set SLConLai = SLConLai - NEW.SoLuong;
    end if;
end;

use quanlykho;
insert chitiet_donnhap
value('DN_BHX_0001','K01_KV_A001','MH_BHX005',120,120),
('DN_BHX_0001','K01_KV_A001','MH_BHX006',120,120);

use quanlykho;
insert donnhap
value('DN_BHX_0001','K01','CTY_BHX','NV1001','2021-9-14');

use quanlykho;
insert donxuat
value('DX_BHX_0001','K01','CTY_BHX','NV1001','2021-12-8');

use quanlykho;
insert chitiet_donxuat
value('DX_BHX_0001','DN_BHX_0001','K01_KV_A001','MH_BHX005',35);

use quanlykho;
insert into mat_hang
values
 ('MH_ABC_001','Cty_ABC','LA01','Bút chì bản mỏng ABC',100)
,('MH_ABC_002','Cty_ABC','LA01','Bút chì bản đậm ABC',100)
,('MH_ABC_003','Cty_ABC','LA01','Bút bi màu xanh ABC',100)
,('MH_ABC_004','Cty_ABC','LA01','Bút bi màu đen ABC',100)
,('MH_ABC_005','Cty_ABC','LA01','Bút mực đen ABC',100)
,('MH_ABC_006','Cty_ABC','LA03','Cục tẩy bút chì ABC',80)
,('MH_ABC_007','Cty_ABC','LA03','Cục tẩy bút mực ABC',80)
,('MH_ABC_008','Cty_ABC','LA01','Cặp bút chì và bút mực ABC',70)
,('MH_ABC_009','Cty_ABC','LA01','Cặp bút bi và bút mực ABC',70)
,('MH_ABC_010','Cty_ABC','LA03','Tẩy bảng khô ABC',75)
,('MH_ABC_011','Cty_ABC','LA03','Tẩy bảng ướt ABC',75)
,('MH_ABC_012','Cty_ABC','LA01','Mực in đen ABC',40)
,('MH_ABC_013','Cty_ABC','LA01','Mực in màu ABC',40)
,('MH_ABC_014','Cty_ABC','LA03','Bìa hồ sơ màu xanh ABC',125)
,('MH_ABC_015','Cty_ABC','LA03','Bìa hồ sơ màu đỏ ABC',125)
,('MH_ABC_016','Cty_ABC','LA01','Bút mực xanh ABC',115)
,('MH_STU_001','Cty_STU','LA02','Giấy A4 dày 80gsm STU',120)
,('MH_STU_002','Cty_STU','LA02','Giấy A4 dày 70gsm STU',120)
,('MH_STU_003','Cty_STU','LA02','Sổ tay bìa da STU',80)
,('MH_STU_004','Cty_STU','LA02','Sổ tay bìa cứng STU',80)
,('MH_STU_005','Cty_STU','LA03','Kẹp giấy màu xanh STU',200)
,('MH_STU_006','Cty_STU','LA03','Kẹp giấy màu đen STU',200)
,('MH_STU_007','Cty_STU','LA03','Băng keo trong suốt STU',60)
,('MH_STU_008','Cty_STU','LA03','Băng keo màu STU',60)
,('MH_STU_009','Cty_STU','LA03','Thước kẻ kim loại STU',125)
,('MH_STU_010','Cty_STU','LA03','Thước kẻ nhựa STU',125)
,('MH_STU_011','Cty_STU','LA02','File hồ sơ A4 STU',100)
,('MH_STU_012','Cty_STU','LA02','File hồ sơ A3 STU',100)


use quanlykho;
insert into mat_hang
values
('MH_HIJ_001','Cty_HIJ','LC01','Sữa bột HIJ',24),
('MH_HIJ_002','Cty_HIJ','LC02','Bánh quy HIJ',30),
('MH_HIJ_003','Cty_HIJ','LC01','Hạt điều rang muối HIJ',24),
('MH_HIJ_004','Cty_HIJ','LC02','Hủ tiếu khô HIJ',24),
('MH_HIJ_005','Cty_HIJ','LC01','Bánh tráng trộn đồ HIJ',24),
('MH_HIJ_006','Cty_HIJ','LC03','Nước mắm tôm HIJ',30),
('MH_HIJ_007','Cty_HIJ','LC01','Bánh mì sandwich HIJ',24),
('MH_HIJ_008','Cty_HIJ','LC01','Thịt bò khô HIJ',50),
('MH_HIJ_009','Cty_HIJ','LC01','Kẹo cao su HIJ',44),
('MH_HIJ_010','Cty_HIJ','LC02','Bánh quy bơ HIJ',30),
('MH_HIJ_011','Cty_HIJ','LC01','Bánh tráng cuộn HIJ',50),
('MH_HIJ_012','Cty_HIJ','LC01','Gạo nếp hương HIJ',24),
('MH_HIJ_013','Cty_HIJ','LC04','Nước chanh HIJ',44),
('MH_HIJ_014','Cty_HIJ','LC01','Bánh phồng tôm HIJ',30),
('MH_HIJ_015','Cty_HIJ','LC01','Bún bò Huế đóng gói HIJ',50),
('MH_HIJ_016','Cty_HIJ','LC01','Mì ăn liền HIJ',24),
('MH_HIJ_017','Cty_HIJ','LC01','Cà phê rang xay HIJ',44),
('MH_HIJ_018','Cty_HIJ','LC04','Nước ép trái cây HIJ',30),
('MH_HIJ_019','Cty_HIJ','LC01','Bánh pía HIJ',50),
('MH_HIJ_020','Cty_HIJ','LC01','Hạt điều bọc mè HIJ',24),
('MH_VMX_001','Cty_VMX','LC01','Hạt sen rang muối VMX',44),
('MH_VMX_002','Cty_VMX','LC01','Hủ tiếu Nam Vang đóng gói VMX',30),
('MH_VMX_003','Cty_VMX','LC01','Bún riêu đóng gói VMX',50),
('MH_VMX_004','Cty_VMX','LC01','Nước sốt cà chua đóng gói VMX',24),
('MH_VMX_005','Cty_VMX','LC01','Cá viên chiên đóng gói VMX',30),
('MH_VMX_006','Cty_VMX','LC02','Sữa đặc VMX',44),
('MH_VMX_007','Cty_VMX','LC05','Nước ngọt VMX',50),
('MH_VMX_008','Cty_VMX','LC01','Kẹo dẻo VMX',24),
('MH_VMX_009','Cty_VMX','LC01','Khoai tây chiên đóng gói VMX',44),
('MH_VMX_010','Cty_VMX','LC01','Phở bò đóng gói VMX',30),
('MH_VMX_011','Cty_VMX','LC01','Mì tôm đóng gói VMX',50),
('MH_VMX_012','Cty_VMX','LC01','Bánh đa lợn đóng gói VMX',20)
,('MH_VMX_013','Cty_VMX','LC01','Trà đen đóng gói VMX',35)
,('MH_VMX_014','Cty_VMX','LC02','Bánh nướng socola VMX',20)

use quanlykho;
select TenNV as 'Tên nhân viên',DiaChi as 'Chức vụ' from nhanvien 

use quanlykho;SELECT MaKV,TenKV FROM khuvuc WHERE khuvuc.MaKV = 'K01'

mat_hang mh, khuvuc kv,chitiet_donnhap ctdn, donnhap dn, loai_hang loai,cty

use quanlykho;
insert into chitiet_nhomquyen
values('NQ_NV','Q5'),('NQ_NV','Q6'),('NQ_NV','Q3')

use quanlykho;
update chitiet_nhomquyen
set MaNhomQuyen='NQ_NV',MaQuyen='Q7'
where MaNhomQuyen='NQ_NV' and MaQuyen='Q3';

use quanlykho;
update kho
set TenKho = 'Kho ADV',DiaChi = '273 An Dương Vương'
where MaKho = 'K01'

use quanlykho;
insert into kho
values('K03','Kho TBT','784 Trần Bình Trọng')

use quanlykho;
delete from kho where MaKho = 'K03'