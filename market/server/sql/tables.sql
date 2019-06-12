drop table if exists orders;
drop table if exists gl;
drop table if exists users;
drop table if exists goods;
drop table if exists labels;
drop table if exists remittence;
drop table if exists order_detail;

create table if not exists users (
  uid varchar(160) primary key, 
  name varchar(100) not null unique,
  sex tinyint default 1 comment '0:女, 1: 男',
  phone varchar(100) not null unique,
  password varchar(80) not null,
  description text,
  type tinyint default 0 not null comment '0: 普通用户, 1: 店家'
) charset utf8mb4;

create table if not exists goods (
  gid int primary key auto_increment,
  uid varchar(160) comment '谁发布的商品',
  name varchar(100) not null,
  description text not null,
  img_url text not null,
  pay DECIMAL(8,2) not null,
  discount float(2, 2) default 0
) charset utf8mb4;

create table if not exists labels (
  lid int primary key auto_increment,
  lname varchar(100) not null unique
) charset utf8mb4;

create table if not exists gl (
  gid int not null,
  lid int not null,
  primary key (gid, lid),
  foreign key (gid) references goods(gid)
  on update cascade on delete cascade,
  foreign key (lid) references labels(lid)
  on update cascade on delete cascade
) charset utf8mb4;

create table if not exists orders (
  oid int primary key auto_increment,
  uid varchar(160) not null,
  mid varchar(160) not null comment '卖家id',
  buyTime timestamp not null,
  state int default 0 comment '0: 未付款, 1: 已付款, 2: 运输中, 3: 已收货, 4: 已完成, 5: 退款中, 6: 已退款, 7: 已取消',
  foreign key (uid) references users(uid)
  on update cascade on delete cascade,
  foreign key (mid) references users(uid)
  on update cascade on delete cascade
) charset utf8mb4;

create table if not exists order_detail (
  oid int not null,
  gid int not null,
  gname varchar(100) not null,
  discount float(2, 2) default 0,
  goods_num int not null,
  pay DECIMAL(8,2) not null,
  img_url text not null,
  primary key (oid, gid)
) charset utf8mb4;

create table if not exists remittence (
  rid int primary key auto_increment,
  oid int not null,
  payid text not null comment '支付凭证',
  paymoney DECIMAL(8,2) not null,
  paytime timestamp
) charset utf8mb4;