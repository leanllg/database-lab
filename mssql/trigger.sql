drop trigger if exists thumb_trigger
drop trigger if exists thumb_trigger_ins
go
create trigger thumb_trigger
on THUMB after update
as
begin
	declare @UID int
	select @UID=UID from inserted
	if (@UID =(SELECT MBLOG.UID FROM MBLOG ,inserted where inserted.BID = MBLOG.BID))
	begin
    print '不能点赞自己的博文'
    rollback tran
	end
end
go
create trigger thumb_trigger_ins
on THUMB after insert
as
begin
	declare @UID int
	select @UID=UID from inserted
	if (@UID =(SELECT MBLOG.UID FROM MBLOG ,inserted where inserted.BID = MBLOG.BID))
	begin
	  print '不能点赞自己的博文'
	  rollback tran
	end
end