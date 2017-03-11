create table test1 (
	code decimal(10),
	name varchar(10),
	date1 date,
	date2 timestamp,
	time1 time
)

insert into test1 values(1, 'AAA', '2017-04-04', now(), '10:10:10');
insert into test1 values(4, 'AAA', '2017-04-14', now(), '10:10:13');
insert into test1 values(5, 'AAA', '2017-04-03', now(), '10:13:10');
insert into test1 values(2, 'AAA', '2017-04-11', now(), '13:10:10');
insert into test1 values(3, 'AAA', '2017-03-04', now(), '10:13:13');
