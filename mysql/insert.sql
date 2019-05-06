INSERT INTO 
	USER
VALUES 
	(000001,'张三','男',1989,'青岛'),
	(000002,'章子怡','女',1979,'北京'),
	(000003,'李冰冰','女',1973,'哈尔滨'),
	(000004,'迪丽热巴','女',1992,'乌鲁木齐'),
	(000005,'周杰伦','男',1979,'新北'),
	(000006,'刘诗诗','女',1987,'北京'),
	(000007,'毛不易','男',1994,'齐齐哈尔'),
	(000008,'杨幂','女',1987,'北京'),
	(000009,'易烊千玺','男',2000,'怀化'),
	(000010,'王俊凯','男',2012,'武汉'),
	(000011,'成龙','男',1954,'香港'),
	(000012,'许晴','女',1969,'北京');

INSERT INTO 
	LABEL 
VALUES 
	(001,'文学'),
	(002,'艺术'),
	(003,'军事'),
	(004,'体育'),
	(005,'游戏'),
	(006,'八卦'),
	(007,'政治'),
	(008,'搞笑'),
	(009,'财经'),
	(010,'科技');

INSERT INTO 
	MBLOG 
VALUES 
	(000001,'A12、麒麟980、',000008,2019,05,04,'手机的角色从仅能拨打电话'), 
	(000002,'5G时代到来了',000010,2019,02,28,'4G改变生活，5G改变时代'),
	(000003,'你遇到过哪些演员',000008,2019,03,10,'天使宝贝'),
	(000004,'最好_华中科技大学 最多地铁站学校',000007,2019,02,01,'备受关注的英雄联盟IG冠军皮肤昨日正式上架销售'),
	(000005,'刘诗雯回应丁宁',000006,2019,01,31,'刘诗雯在赛后采访中表示'),
	(000006,'超百亿首只科创板',000011,2019,01,11,'备受市场关注的首只科创板基金卖出超过100亿'),
	(000007,'现在最暴利的行业',000001,2019,03,11,'你猜'),
	(000008,'嘻嘻嘻嘻嘻',000010,2019,04,20,'哈哈哈哈哈哈');

INSERT INTO 
	B_L 
VALUES
	(000001,010),
	(000002,010),
	(000003,006),
	(000004,005),
	(000005,004),
	(000006,009),
	(000007,009),
	(000008,008);

INSERT INTO 
	FOLLOW 
VALUES
	(000001,000002),
	(000001,000004),
	(000001,000006),
	(000001,000008),
	(000001,000010),
	(000001,000012),
	
	(000002,000003),
	(000002,000006),
	(000002,000009),
	(000002,000012),
	
	(000003,000004),
	(000003,000008),
	(000003,000012),
	(000003,000001),
	(000003,000002),
	
	(000004,000005),
	(000004,000010),
	(000004,000008),
	(000004,000007),
	
	(000005,000006),
	(000005,000012),
	
	(000006,000007),
	(000006,000010),
	(000006,000008),
	
	(000007,000008),
	(000007,000010),
	(000007,000006),
	(000007,000005),
	
	(000008,000009),
	(000008,000010),
	(000008,000011),
	
	(000009,000010),
	(000009,000007),
	
	(000010,000011),
	
	(000011,000012),
	(000011,000010),
	
	(000012,000001),
	(000012,000002),
	(000012,000008),
	(000012,000004);

INSERT INTO 
	FRIENDS 
VALUES
	(000001,000001),
	(000001,000003),
	(000001,000005),
	(000001,000007),
	
	
	(000002,000001),
	(000002,000005),
	
	(000003,000004),
	(000003,000003),
	(000003,000007),
	(000003,000001),
	(000003,000002),
	
	(000004,000005),
	(000004,000002),
	(000004,000006),
	
	(000005,000004),
	(000005,000002),
	
	(000006,000002),
	(000006,000003),
	(000006,000005),
	
	(000007,000007),
	(000007,000006),
	(000007,000001),
	(000007,000002),
	
	(000008,000005),
	(000008,000003),
	(000008,000001),
	
	(000009,000001),
	(000009,000002),
	
	(000010,000001),
	
	(000011,000002),
	(000011,000001),
	
	(000012,000001),
	(000012,000002),
	(000012,000010),
	(000012,000011);

INSERT INTO 
	THUMB 
VALUES
	(000001,000001),
	(000001,000003),
	(000001,000005),
	(000001,000007),
	(000001,000002),
	
	(000002,000001),
	(000002,000005),
	(000002,000002),
	
	(000003,000004),
	(000003,000007),
	(000003,000001),
	(000003,000002),
	
	(000004,000005),
	(000004,000004),
	(000004,000002),
	(000004,000006),
	
	(000005,000004),
	(000005,000002),
	
	(000006,000002),
	(000006,000001),
	(000006,000005),

	(000007,000002),
	(000007,000001),
	(000007,000004),
	
	(000008,000003),
	(000008,000004),
	(000008,000001),
	
	(000009,000001),
	(000009,000002),
	
	(000010,000001),
	
	(000011,000002),
	(000011,000001),
	
	(000012,000001),
	(000012,000002),
	(000012,000005),
	(000012,000006);
INSERT INTO 
	SUB 
VALUES
	(000001,004),
	(000001,006),
	(000001,007),
	(000001,009),
	(000001,001),
	(000001,003),
	(000001,005),
	(000001,008),
	(000001,010),
	(000001,002),
	

	
	(000002,001),
	(000002,005),
	(000002,009),
	(000002,002),
	
	(000003,004),
	(000003,008),
	(000003,007),
	(000003,001),
	(000003,002),
	
	(000004,005),
	(000004,010),
	(000004,002),
	(000004,006),
	
	(000005,004),
	(000005,002),
	
	(000006,002),
	(000006,010),
	(000006,005),
	
	(000007,008),
	(000007,010),
	(000007,001),
	(000007,002),
	
	(000008,009),
	(000008,003),
	(000008,010),
	
	(000009,010),
	(000009,002),
	
	(000010,001),
	
	(000011,002),
	(000011,001),
	
	(000012,001),
	(000012,002),
	(000012,010);

INSERT INTO
	TOPDAY
VALUES
	(8, 1, 2019, 04, 20),
	(1, 2, 2019, 05, 04);