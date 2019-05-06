create procedure thumbNum()
as
begin
  declare @date = getdate()
  declare @year = datename(year, @date)
  declare @month = datename(month, @date)
  declare @day = datename(day, @date)
  declare @inserted = select 1 from 
end