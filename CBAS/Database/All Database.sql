
CREATE database CBAS;

use CBAS;

//--------------------------------------------------------------------------------------------

CREATE TABLE subjectInfo
(
 sub_code varchar(50) primary key,
 sub_name varchar(100) not null,
 no_of_sem int not null,
 dept_name varchar(50)not null,
 dept_course varchar(50)not null
);

INSERT INTO subjectInfo VALUES('cs-101','PPL',1,'Computer Science','MCS');
INSERT INTO subjectInfo VALUES('cs-102','DAA',1,'Computer Science','MCS');
INSERT INTO subjectInfo VALUES('cs-103','DDC',1,'Computer Science','MCS');
INSERT INTO subjectInfo VALUES('cs-104','AN',1,'Computer Science','MCS');
INSERT INTO subjectInfo VALUES('cs-105','NP',1,'Computer Science','MCS');

INSERT INTO subjectInfo VALUES('cs-201','AOS',2,'Computer Science','MCS');
INSERT INTO subjectInfo VALUES('cs-202','DIP',2,'Computer Science','MCS');
INSERT INTO subjectInfo VALUES('cs-203','.NET',2,'Computer Science','MCS');
INSERT INTO subjectInfo VALUES('cs-204','AI',2,'Computer Science','MCS');
INSERT INTO subjectInfo VALUES('cs-205','Project',2,'Computer Science','MCS');

  SELECT * FROM subjectInfo;


//--------------------------------------------------------------------------------------------
  
  CREATE TABLE teacherInfo
  (
   teach_id varchar(50)primary key,
   teach_name varchar(100)not null,
   dept_name varchar(50)not null,
   contact_no long null,
   email varchar(100)null  
  );
  
  INSERT INTO teacherInfo VALUES('T-101','Mrs.Jigisha Pawar','Computer Science',8722334455,'jigisha7875@yahoo.com');
  INSERT INTO teacherInfo VALUES('T-102','Mr.Rahul Shaha','Computer Science',8746633354,'rahul4545@gmail.com');
  INSERT INTO teacherInfo VALUES('T-103','Mr.Abhijeet Mankar','Computer Science',9822334455,'abhi872@gmail.com');
  INSERT INTO teacherInfo VALUES('T-104','Mrs.Prajkta Kulkarni','Electronics',5020334455,'praju4545@yahoo.com');
  INSERT INTO teacherInfo VALUES('T-105','Mrs.Maya Pawar','Electronics',9960334455,'maya4689@gmail.com');
  INSERT INTO teacherInfo VALUES('T-106','Mr.Ajay Thakur','Mathematics',9552334455,'ajay56565@hotmail.com');
  
  
  SELECT * FROM teacherInfo;
  
  constraint foreign key(dept_id) references deptInfo(dept_id)

//--------------------------------------------------------------------------------------------
  
  CREATE TABLE deptInfo
  (
    dept_id varchar(50)primary key,
	dept_name varchar(100)not null,
	contact_no long null,
	HOD_name varchar(100)not null
  );
  
  INSERT INTO deptInfo VALUES('D1','Electronics',9852454550,'Mr.Ram Joshi');
  INSERT INTO deptInfo VALUES('D2','Computer Science',8888884550,'Mr.Avinash Godase');
  INSERT INTO deptInfo VALUES('D3','Mathematics',9696969696,'Mr.Uday BHapkar');
  INSERT INTO deptInfo VALUES('D4','Mathematics','','Mr.Uday BHapkar');
  
  SELECT * FROM deptInfo;

//--------------------------------------------------------------------------------------------
  
  CREATE TABLE courseInfo
  (
	dept_id varchar(50)not null,
	dept_name varchar(100)not null,
	course_name varchar(100)not null,
	no_of_sem int not null,
	constraint foreign key(dept_id) references deptInfo(dept_id)
  );
  
   INSERT INTO courseInfo VALUES ('D1','Electronics','Analog','4');
   INSERT INTO courseInfo VALUES ('D1','Electronics','Digital','4');
   INSERT INTO courseInfo VALUES ('D2','Computer Science','MCS','4');
   INSERT INTO courseInfo VALUES ('D2','Computer Science','MCA','6');

   SELECT * FROM courseInfo;
   
//--------------------------------------------------------------------------------------------   

CREATE TABLE InternalMarks
(
 course_name varchar(100) not null,
 sem_no int not null,
 sub_code varchar(50)not null,
 sub_name varchar(100) not null,
 credit_no int not null,
 
 roll_no int not null,
 sname varchar(100) not null,
 marks int,
 constraint foreign key(roll_no) references studInfo(roll)
);