-- 清理表，便于测试
drop table if exists B_L;
drop table if exists FOLLOW;
drop table if exists FRIENDS;
drop table if exists SUB;
drop table if exists THUMB;
drop table if exists TOPDAY;
drop table if exists MBLOG;
drop table if exists USER;
drop table if exists LABEL;



-- 建表
create table if not exists USER (
  UID int primary key,
  NAME varchar(20) unique not null,
  SEX char(1) not null,
  BYEAR int not null comment '出生年份',
  CITY text not null
) charset utf8;

create table if not exists LABEL  (
  LID int primary key,
  LNAME varchar(20) unique not null
) charset utf8;

create table if not exists MBLOG (
  BID int primary key,
  TITLE text not null,
  UID int,
  PYEAR int not null comment '博文年份',
  PMONTH int not null,
  PDAY int not null,
  CONT text not null comment '正文',
  foreign key (UID) references USER(UID)
  on update cascade on delete cascade
) charset utf8;

create table if not exists B_L (
  BID int,
  LID int,
  primary key (BID, LID),
  foreign key (LID) references LABEL(LID)
  on update cascade on delete cascade,
  foreign key (BID) references MBLOG(BID)
  on update cascade on delete cascade
) charset utf8;

create table if not exists FOLLOW (
  UID int,
  UIDFLED int comment '被关注用户id',
  primary key (UID, UIDFLED),
  foreign key (UID) references USER(UID)
  on update cascade on delete cascade,
  foreign key (UIDFLED) references USER(UID)
  on update cascade on delete cascade
) charset utf8;

create table if not exists FRIENDS (
  UID int,
  FUID int comment '好友uid',
  primary key (UID, FUID),
  foreign key (UID) references USER(UID)
  on update cascade on delete cascade,
  foreign key (FUID) references USER(UID)
  on update cascade on delete cascade
) charset utf8;

create table if not exists SUB (
  UID int,
  LID int,
  primary key (UID, LID),
  foreign key (UID) references USER(UID)
  on update cascade on delete cascade,
  foreign key (LID) references LABEL(LID)
  on update cascade on delete cascade
) charset utf8;

create table if not exists THUMB (
  UID int,
  BID int,
  primary key (UID, BID),
  foreign key (UID) references USER(UID)
  on update cascade on delete cascade,
  foreign key (BID) references MBLOG(BID)
  on update cascade on delete cascade
) charset utf8;

create table if not exists TOPDAY (
  BID int not null,
  TNO int comment '顺序号',
  TYEAR int not null,
  TMONTH int not null,
  TDAY int not null,
  primary key (TNO, TYEAR, TMONTH, TDAY),
  foreign key (BID) references MBLOG(BID)
  on update cascade on delete cascade
) charset utf8;

source insert.sql;

-- 博文表增删改操作
insert into MBLOG(BID, TITLE, UID, PYEAR, PMONTH, PDAY, CONT) values (100, '测试用例', 1, 2019, 4, 29, '测试是具有试验性质的测量，即测量和试验的综合。而测试手段就是仪器仪表。由于测试和测量密切相关，在实际使用中往往并不严格区分测试与测量。测试的基本任务就是获取有用的信息。');
select * from MBLOG where BID = 100;
update MBLOG set TITLE = '另一个测用例', PDAY=28 where BID = 100;
select * from MBLOG where BID = 100;
delete from MBLOG where BID = 100;
select * from MBLOG where BID = 100;

-- 将关注3号用户的用户信息插入到一个自定义的新表FANS_3中。
create table if not exists FANS_3 as (
  select u.* from USER u, FOLLOW f where f.UIDFLED = 3 and u.UID = f.UID
);

drop trigger if exists thumb_trigger;
drop trigger if exists thumb_trigger_ins;
delimiter $$

create trigger thumb_trigger
before update on THUMB for each row
begin
  if ((select UID from MBLOG where BID = new.BID) = new.UID) then
    signal sqlstate '45000'
    set MESSAGE_TEXT = 'thumb same person';
  end if;
end$$

create trigger thumb_trigger_ins
before insert on THUMB for each row
begin
  if ((select UID from MBLOG where BID = new.BID) = new.UID) then
    signal sqlstate '45000'
    set MESSAGE_TEXT = 'thumb same person';
  end if;
end$$

delimiter ;

source query.sql;