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
create trigger trig_DonXuat before insert on chitiet_donxuat
update chitiet_donnhap
set SLConLai = SLConLai - n

