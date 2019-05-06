create table if not exists users (
  uid text primary key default uuid(), 
  name text not null,
  sex int default 1 comment '0:女, 1: 男',
  phone text not null unique,
  description text default '这个人很懒，什么也没有留下',
  type int default 0 not null comment '0: 普通用户, 1: 店家'
) charset utf8;

create table if not exists goods (
  gid int primary key auto_increment,
  uid text comment '谁发布的商品',
  name text not null,
  description text not null,
  img text not null,
  pay DECIMAL(8,2) not null,
  discount float(0, 2),
  foreign key uid references users(lid)
  on update cascade on delete cascade
) charset utf8;

create table if not exists label (
  lid int primary key auto_increment,
  name text not null unique,
) charset utf8;

create table if not exists gl (
  gid int not null,
  lid int not null,
  primary key (gid, lid),
  foreign key gid references goods(gid)
  on update cascade on delete cascade,
  foreign key lid references label(lid)
  on update cascade on delete cascade
) charset utf8;

create table if not exists order (
  oid int primary key auto_increment,
  uid text not null,
  gid int not null,
  buyTime timestamp default unix_timestamp(now()),
  state int default 0 comment '0: 未付款, 1: 已付款, 2: 运输中, 3: 已收货, 4: 已完成, 5: 退款中, 6: 已退款',
  foreign key gid references goods(gid)
  on update cascade on delete cascade,
  foreign key uid references users(uid)
  on update cascade on delete cascade
) charset utf8;

create table if not exists remittence (
  rid int primary key auto_increment,
  oid int not null,
  payid text not null comment '支付凭证',
  paymoney DECIMAL(8,2) not null,
  paytime timestamp default unix_timestamp(now())
) charset utf8;