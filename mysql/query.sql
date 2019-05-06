-- q1:查询“张三”用户关注的所有用户的ID号、姓名、性别、出生年份，所在城市，
-- 并且按照出生年份的降序排列，同一个年份的则按照用户ID号升序排列。
select u.* from USER u, FOLLOW f
  where f.UID = u.UID 
  and f.UIDFLED = (select UID from USER u2 where u2.NAME = '张三')
  order by BYEAR DESC, UID ASC;

-- q2:查找没有被任何人点赞的博文ID、标题以及发表者姓名，
-- 并将结果按照标题字符顺序排列
select u.NAME, b.BID, b.TITLE from USER u, MBLOG b
  where b.UID = u.UID 
  and not exists (select * from THUMB t where t.BID = b.BID)
  order by b.TITLE;

-- q3:查找2000年以后出生的武汉市用户发表的进入过头条的博文ID
select b.BID from MBLOG b, TOPDAY t
  where b.UID in (select UID from USER where BYEAR >= 2000 and CITY = '武汉')
  and t.BID = b.BID;

-- q4:查找订阅了所有分类的用户ID
select UID from (
  select distinct UID, count(UID) as NUM from SUB group by UID
) as SUB_NUM
where SUB_NUM.NUM = (
  select count(LID) from LABEL
);

-- q5:查找出生年份小于1970年或者大于2010年的用户ID、出生年份、所在城市，
-- 要求where子句中只能有一个条件表达式
select UID, BYEAR, CITY from USER where BYEAR > 2010
union
select UID, BYEAR, CITY from USER where BYEAR < 1970;

-- q6:统计每个城市的用户数
select CITY, count(UID) as USER_NUM from USER group by CITY;

-- q7:统计每个城市的每个出生年份的用户数，并将结果按照城市的升序排列，
-- 同一个城市按照出生用户数的降序排列其相应的年份
select CITY, BYEAR, count(UID) as NUM from USER 
  group by CITY, BYEAR
  order by CITY ASC, NUM DESC;

-- q8:查找被点赞数超过10的博文ID号
select BID from THUMB
  group by BID
  having count(BID) > 10;

-- q9:查找被2000年后出生的用户点赞数超过10的博文ID号
select BID from THUMB t, USER u
  where u.UID = t.UID and u.BYEAR >= 2000
  group by BID
  having count(BID) > 10;

-- q10:查找被2000年后出生的用户点赞数超过10的每篇博文的进入头条的次数
select tb.BID, count(tb.BID) as count from (
  select BID from THUMB t, USER u
  where u.UID = t.UID and u.BYEAR >= 2000
  group by BID
  having count(BID) > 1
) as tb, TOPDAY t
  where tb.BID = t.BID
  group by tb.BID;

-- q11:查找订阅了文学、艺术、哲学、音乐中至少一种分类的用户ID，
-- 要求不能使用嵌套查询，且where子句中最多只能包含两个条件
select distinct UID from SUB s, LABEL l
  where s.LID = l.LID
  and l.LNAME in ('文学', '艺术', '哲学', '音乐');

-- q12:查找标题中包含了“最多地铁站”和“_华中科技大学”两个词的博文基本信息
select * from MBLOG where TITLE like '%最多地铁站%' and TITLE like '%_华中科技大学%';

-- q13: 查找所有相互关注的用户对的两个ID号，要求不能使用嵌套查询
select f1.UID as u1, f1.UIDFLED as u2 from FOLLOW f1 
  inner join FOLLOW f2
  on f1.UID = f2.UIDFLED and f1.UIDFLED = f2.UID 
  and f1.UID < f1.UIDFLED;

-- q14: 查找好友圈包含了5号用户好友圈的用户ID
select * from (
  select f1.UID as UID from FRIENDS f1
    inner join FRIENDS f2
    on f2.FUID = f1.FUID
    and f2.UID = 5
    and f1.UID <> 5
) as f5 group by f5.UID
  having count(f5.UID) >= (
    select count(UID) from FRIENDS where UID = 5 group by UID
  );

-- q15: 查找2019年4月20日每一篇头条博文的ID号、标题以及该博文的每一个分类ID，
-- 要求即使该博文没有任何分类ID也要输出其ID号、标题
select b.BID, b.TITLE, B_L.LID from MBLOG b
  inner join TOPDAY t 
  on b.BID = t.BID
  and t.TYEAR = 2019
  and t.TMONTH = 4
  and t.TDAY = 20
  left join B_L
  on B_L.BID = b.BID;

-- q16: 查找至少有3名共同好友的所有用户对的两个ID号
select f1.UID as u1, f2.UID as u2 from FRIENDS f1, FRIENDS f2
  where f1.FUID = f2.FUID
  and f1.UID < f2.UID
  group by f1.UID, f2.UID
  having count(f1.UID) >= 3;

drop view if exists TOP_10;

-- q17
create view TOP_10 as
  select distinct b.BID, b.TITLE, b.UID, b.NAME,
  case when tb.NUM is NULL then 0 else tb.NUM end THUMB_NUM
  from (
    select MBLOG.BID, TITLE, USER.UID, NAME from MBLOG
    inner join TOPDAY t
    on t.BID = MBLOG.BID
    and t.TYEAR = extract(year from now())
    and t.TMONTH = extract(month from now())
    and t.TDAY = extract(day from now())
    and t.TNO <= 10
    inner join USER
    on USER.UID = MBLOG.UID
    ) as b
    left join (
    select BID, count(BID) as NUM from THUMB group by BID
    ) tb on tb.BID = b.BID;

-- test for TOP_10
select * from TOP_10;