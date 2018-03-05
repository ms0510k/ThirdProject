
/* Drop Tables */

DROP TABLE complaine CASCADE CONSTRAINTS;
DROP TABLE fees CASCADE CONSTRAINTS;
DROP TABLE money CASCADE CONSTRAINTS;
DROP TABLE exchange CASCADE CONSTRAINTS;
DROP TABLE fnq CASCADE CONSTRAINTS;
DROP TABLE thistory CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE notice CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE complaine
(
	comnum number NOT NULL,
	memnum number NOT NULL,
	comtitle varchar2(15) NOT NULL,
	email varchar2(15) NOT NULL,
	comcontent varchar2(50),
	comresult varchar2(50),
	comhit number,
	comdate date,
	PRIMARY KEY (comnum)
);


CREATE TABLE exchange
(	
	memnum number NOT NULL,
	excoin varchar2(15),
	exmoney number(15,5),
	examount number(15,5)
);


CREATE TABLE fees
(
	feenum number NOT NULL,
	connum number,
	feemoney number,
	memnum number,
	feedate date,
	PRIMARY KEY (feenum)
);


CREATE TABLE fnq
(
	fnqnum number NOT NULL,
	fnqtitle varchar2(15),
	fnqcontent varchar2(50),
	fnqresult varchar2(50),
	PRIMARY KEY (fnqnum)
);


CREATE TABLE member
(
	memnum number NOT NULL,
	name varchar2(15) NOT NULL,
	email varchar2(15) NOT NULL,
	pwd varchar2(15) NOT NULL,
	phone varchar2(15) NOT NULL,
	bank varchar2(15) NOT NULL,
	account number(15) NOT NULL,
	memdate date,
	PRIMARY KEY (memnum)
);


CREATE TABLE money
(
	connum number NOT NULL,
	exnum number NOT NULL,
	memnum number NOT NULL,
	outmoney number,
	confirm varchar2(10),
	condate date,
	PRIMARY KEY (connum)
);


CREATE TABLE notice
(
	notnum number NOT NULL,
	nottitle varchar2(15) NOT NULL,
	notcontent varchar2(50) NOT NULL,
	nothit number,
	notdate date,
	PRIMARY KEY (notnum)
);


CREATE TABLE thistory
(
	tnum number primary key,
	tdate date,
	coin varchar2(10),
	coinamount number(15,5),
	tradetype varchar2(20),
	tprice  number(15,5),
	memnum number NOT NULL,
	fee number
);



/* Create Foreign Keys */


ALTER TABLE fees
	ADD FOREIGN KEY (memnum)
	REFERENCES exchange (memnum) on delete cascade
;


ALTER TABLE money
	ADD FOREIGN KEY (memnum)
	REFERENCES exchange (memnum) on delete cascade
;


ALTER TABLE complaine
	ADD FOREIGN KEY (memnum)
	REFERENCES member (memnum) on delete cascade
;


ALTER TABLE exchange
	ADD FOREIGN KEY (memnum)
	REFERENCES member (memnum) on delete cascade
;


ALTER TABLE money
	ADD FOREIGN KEY (memnum)
	REFERENCES member (memnum) on delete cascade
;


ALTER TABLE thistory
	ADD FOREIGN KEY (memnum)
	REFERENCES member (memnum) on delete cascade
;


ALTER TABLE fees
	ADD FOREIGN KEY (connum)
	REFERENCES money (connum) on delete cascade
;


drop sequence memnum_seq;
drop sequence comnum_seq;
drop sequence connum_seq;
drop sequence fnqnum_seq;
drop sequence notnum_seq;
drop sequence feenum_seq;
drop sequence tnum_seq;

create sequence memnum_seq;
create sequence comnum_seq;
create sequence connum_seq;
create sequence fnqnum_seq;
create sequence notnum_seq;
create sequence feenum_seq;
create sequence tnum_seq;



