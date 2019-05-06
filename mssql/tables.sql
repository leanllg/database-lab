drop table if exists B_L;
drop table if exists FOLLOW;
drop table if exists FRIENDS;
drop table if exists SUB;
drop table if exists THUMB;
drop table if exists TOPDAY;
drop table if exists MBLOG;
drop table if exists USERS;
drop table if exists LABEL;

create table USERS (
  UID int primary key,
  NAME varchar(20) unique not null,
  SEX char(4) not null,
  BYEAR int not null,
  CITY varchar(30) not null
);

create table LABEL  (
  LID int primary key,
  LNAME varchar(20) unique not null
);

create table MBLOG (
  BID int primary key,
  TITLE varchar(200) not null,
  UID int,
  PYEAR int not null,
  PMONTH int not null,
  PDAY int not null,
  CONT varchar(max) not null,
  foreign key (UID) references USERS(UID)
  on update cascade on delete cascade
);

create table B_L (
  BID int,
  LID int,
  primary key (BID, LID),
  foreign key (LID) references LABEL(LID)
  on update cascade on delete cascade,
  foreign key (BID) references MBLOG(BID)
  on update cascade on delete cascade
);

create table FOLLOW (
  UID int,
  UIDFLED int,
  primary key (UID, UIDFLED),
  foreign key (UID) references USERS(UID)
  on update cascade on delete cascade,
  foreign key (UIDFLED) references USERS(UID)
);

create table FRIENDS (
  UID int,
  FUID int,
  primary key (UID, FUID),
  foreign key (UID) references USERS(UID)
  on update cascade on delete cascade,
  foreign key (FUID) references USERS(UID)
);

create table SUB (
  UID int,
  LID int,
  primary key (UID, LID),
  foreign key (UID) references USERS(UID)
  on update cascade on delete cascade,
  foreign key (LID) references LABEL(LID)
  on update cascade on delete cascade
);

create table THUMB (
  UID int,
  BID int,
  primary key (UID, BID),
  foreign key (UID) references USERS(UID)
  on update cascade on delete cascade,
  foreign key (BID) references MBLOG(BID)
);

create table TOPDAY (
  id int primary key IDENTITY,
  BID int not null,
  TNO int,
  TYEAR int not null,
  TMONTH int not null,
  TDAY int not null,
  foreign key (BID) references MBLOG(BID)
  on update cascade on delete cascade
);

