drop function if exists getTopdays
go
create function getTopdays(@UID int)
returns int
as
begin
  declare @days int
  set @days = (select count(*) from (select distinct TYEAR, TMONTH, TDAY from TOPDAY t, MBLOG b
    where t.BID = b.BID
    and b.UID = @UID
  ) as tdate)
  return @days
end
go
select dbo.getTopdays(1) as days
go
select UID from USERS where BYEAR >= 2000 and dbo.getTopdays(UID) >= 100;
