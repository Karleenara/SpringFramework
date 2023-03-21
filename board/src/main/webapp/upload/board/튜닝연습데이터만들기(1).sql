create table myemp(empno number, ename varchar2(20), 
   job varchar2(20), hiredate date, sal number, comm number, deptno number,
	 empno_var varchar2(20), gender char(1));

create or replace procedure insert_emp
is
	i number;
	gender char(1);
	cnt number;
	comm number;
	hiredate date;
	job varchar2(30);

  
begin
  delete from myemp;
	for i in 1..1000000 loop
	  	if mod(i, 2) = 0 then 
			gender :='M';
		else
			gender :='F';
		end if;

		if mod(i, 4) = 0 then 
			comm:=null;
		elsif mod(i, 4) = 1 then
			comm:=100;
		elsif mod(i, 4) = 2 then
			comm:=200;
    else
			comm:=300;
		end if;

		if mod(i, 7) = 0 then 
			job:=null;
		elsif mod(i, 7) = 1 then
			job:='CLERK';
		elsif mod(i, 7) = 2 then
			job:='ACCOUNTING';
		elsif mod(i, 7) = 3 then
			job:='MANAGER';
		elsif mod(i, 7) = 4 then
			job:='SALESMAN';
		elsif mod(i, 7) = 5 then
			job:='PRESIDENT';
		elsif mod(i, 7) = 6 then
			job:='ANAYLST';
    else
			job:='INTERN';
		end if;


	  if mod(i, 100) = 0 then 
			hiredate:='2011-01-01';
		elsif mod(i, 100) = 1 then
			hiredate:='2012-01-01';
		elsif mod(i, 100) = 2 then
			hiredate:='2013-01-01';
		elsif mod(i, 100) = 3 then
			hiredate:='2014-01-01';
		elsif mod(i, 100) = 4 then
			hiredate:='2015-01-01';
		elsif mod(i, 100) = 5 then
			hiredate:='2016-01-01';
		elsif mod(i, 100) = 6 then
			hiredate:='2010-01-01';
		elsif mod(i, 100) = 7 then
			hiredate:='2006-01-01';
		elsif mod(i, 100) = 8 then
			hiredate:='2007-01-01';
		elsif mod(i, 100) = 9 then
			hiredate:='2008-01-01';
			hiredate:='2011-01-01';
		elsif mod(i, 100) = 10 then
			hiredate:='2012-02-01';
		elsif mod(i, 100) = 11 then
			hiredate:='2012-02-01';
		elsif mod(i, 100) = 12 then
			hiredate:='2013-02-01';
		elsif mod(i, 100) = 13 then
			hiredate:='2014-02-01';
		elsif mod(i, 100) = 14 then
			hiredate:='2015-02-01';
		elsif mod(i, 100) = 15 then
			hiredate:='2016-02-01';
		elsif mod(i, 100) = 16 then
			hiredate:='2010-02-01';
		elsif mod(i, 100) = 17 then
			hiredate:='2006-02-01';
		elsif mod(i, 100) = 18 then
			hiredate:='2007-02-01';
		elsif mod(i, 100) = 19 then
			hiredate:='2008-02-01';
		elsif mod(i, 100) = 20 then
			hiredate:='2012-03-01';
		elsif mod(i, 100) = 21 then
			hiredate:='2012-03-01';
		elsif mod(i, 100) = 22 then
			hiredate:='2013-03-01';
		elsif mod(i, 100) = 23 then
			hiredate:='2014-03-01';
		elsif mod(i, 100) = 24 then
			hiredate:='2015-03-01';
		elsif mod(i, 100) = 25 then
			hiredate:='2016-03-01';
		elsif mod(i, 100) = 26 then
			hiredate:='2010-03-01';
		elsif mod(i, 100) = 27 then
			hiredate:='2006-04-01';
		elsif mod(i, 100) = 28 then
			hiredate:='2007-04-01';
		elsif mod(i, 100) = 29 then
			hiredate:='2008-04-01';
			elsif mod(i, 100) = 30 then
			hiredate:='2012-04-01';
		elsif mod(i, 100) = 31 then
			hiredate:='2012-04-01';
		elsif mod(i, 100) = 32 then
			hiredate:='2013-04-01';
		elsif mod(i, 100) = 33 then
			hiredate:='2014-04-01';
		elsif mod(i, 100) = 34 then
			hiredate:='2015-04-01';
		elsif mod(i, 100) = 35 then
			hiredate:='2016-04-01';
		elsif mod(i, 100) = 36 then
			hiredate:='2010-04-01';
		elsif mod(i, 100) = 37 then
			hiredate:='2006-04-01';
		elsif mod(i, 100) = 38 then
			hiredate:='2007-04-01';
		elsif mod(i, 100) = 39 then
			hiredate:='2008-04-01';
		elsif mod(i, 100) = 40 then
			hiredate:='2009-04-01';
		elsif mod(i, 100) = 41 then
			hiredate:='2010-04-01';
		elsif mod(i, 100) = 42 then
			hiredate:='2010-07-01';
		elsif mod(i, 100) = 43 then
			hiredate:='2011-06-01';
		elsif mod(i, 100) = 44 then
			hiredate:='2012-07-01';
		elsif mod(i, 100) = 45 then
			hiredate:='2011-04-05';
		elsif mod(i, 100) = 46 then
			hiredate:='2012-09-01';
		elsif mod(i, 100) = 47 then
			hiredate:='2013-11-01';
		elsif mod(i, 100) = 48 then
			hiredate:='2014-12-01';
		elsif mod(i, 100) = 49 then
			hiredate:='2015-02-01';
		elsif mod(i, 100) = 50 then
			hiredate:='2016-03-01';
    else
			hiredate:='2017-11-01';
		end if;

		insert into myemp(empno,empno_var,  ename, deptno, sal, hiredate, comm, gender, job) 
		values(i, to_char(i), 'test'||i , mod(i, 100)*10+10, 3000+i,  hiredate, comm, gender, job); 
  end loop;
	commit;
end;
/

exec insert_emp;
