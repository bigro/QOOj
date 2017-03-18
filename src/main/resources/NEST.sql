create table nest1 (
	code decimal(10),
	nameId int,
	address varchar(40)
);

create table name (
	id int,
	name varchar(10)
);

insert into nest1 values(1, 101, '大阪');
insert into nest1 values(2, 102, '北海道');
insert into nest1 values(3, 103, '兵庫');
insert into nest1 values(4, 104, '鹿児島');
insert into nest1 values(5, 105, '秋田');

insert into name values(101, 'ロサンゼルス');
insert into name values(102, 'ロンドン');
insert into name values(103, 'モナコ');
insert into name values(104, 'ドバイ');
insert into name values(105, 'ジャカルタ');
