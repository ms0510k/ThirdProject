
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
	comcontent varchar2(20),
	comresult varchar2(20),
	comhit number,
	comdate date,
	PRIMARY KEY (comnum)
);


CREATE TABLE exchange
(
	exnum number NOT NULL,
	memnum number NOT NULL,
	excoin varchar2(15),
	exmoney number,
	PRIMARY KEY (exnum)
);

CREATE TABLE fees
(
	feenum number NOT NULL,
	exnum number NOT NULL,
	connum number NOT NULL,
	feemoney number,
	PRIMARY KEY (feenum)
);


CREATE TABLE fnq
(
	fnqnum number NOT NULL,
	fnqtitle varchar2(15),
	fnqcontent varchar2(15),
	fnqresult varchar2(20),
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
	account number NOT NULL,
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
	notcontent varchar2(20) NOT NULL,
	nothit number,
	notdate date,
	PRIMARY KEY (notnum)
);


CREATE TABLE thistory
(
	tdate date,
	coin varchar2(10),
	coinamount number(7,0),
	tradetype varchar2(10),
	tprice  number(10,0),
	memnum number NOT NULL
);



/* Create Foreign Keys */

ALTER TABLE fees
	ADD FOREIGN KEY (exnum)
	REFERENCES exchange (exnum) on delete cascade
;


ALTER TABLE money
	ADD FOREIGN KEY (exnum)
	REFERENCES exchange (exnum) on delete cascade
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


CREATE FUNCTION ex_seq RETURN NUMBER IS
BEGIN
  RETURN exchange_seq.nextval;
END;
/

