insert into MBLOG(BID, TITLE, UID, PYEAR, PMONTH, PDAY, CONT) values (100, '测试用例', 1, 2019, 4, 29, '测试是具有试验性质的测量，即测量和试验的综合。而测试手段就是仪器仪表。由于测试和测量密切相关，在实际使用中往往并不严格区分测试与测量。测试的基本任务就是获取有用的信息。');
select * from MBLOG where BID = 100;
update MBLOG set TITLE = '另一个测用例', PDAY=28 where BID = 100;
select * from MBLOG where BID = 100;
delete from MBLOG where BID = 100;
select * from MBLOG where BID = 100;

-- 将关注3号用户的用户信息插入到一个自定义的新表FANS_3中。
select u.* into FANS_3 from USERS u, FOLLOW f where f.UIDFLED = 3 and u.UID = f.UID;

select * from FANS_3;

drop table FANS_3;