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
