create table studInfo
(roll int primary key,
sname varchar(100) NOT NULL,
sclass varchar(100) NOT NULL, 
addr varchar(100) NOT NULL,
contact long NULL,
email varchar(100) NULL,
DOB date NULL,
gender varchar(50) NOT NULL,
uniSeat long NULL,
academicYr varchar(50)
);

insert into studInfo values(64001,'Rupesh Jagtap','MCS','Sagamaner',9011989737,'rp123@live.in','1990-12-31','Male',13095,'2015-2017');
insert into studInfo values(64002,'Uday Bhapkar','MCS','Pune',9011989738,'UB36@live.in','1990-12-31','Male',13096,'2015-2017');
insert into studInfo values(64101,'Pratik Bali','MCA','BRMT',9011989739,'cn@live.in','1990-12-31','Male',13097,'2015-2017');


select * from studInfo;

create table masterCreditEntry
(
subCode varchar(100) primary key,
subName  varchar(100),
courseName varchar(100),
semester int,
creditActivity int
);

insert into masterCreditEntry values('cs-101','dip','mcs',2,5);

create table creditAllocation
(subCode varchar(100),
creditNo int,
allocatedMarks int,
activityName varchar(100)
);

insert into creditAllocation values('cs-101',1,10,'ass');
