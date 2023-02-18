DROP TABLE toy_board CASCADE CONSTRAINTS;CREATE TABLE toy_board (	ID	number		NOT NULL,	title	varchar2(500)		NOT NULL,	content	varchar2(4000)		NOT NULL,	user_id	varchar2(50)		NOT NULL,	user_pw	varchar2(200)		NOT NULL,	create_date	timestamp		NOT NULL,	modi_date	timestamp		,	del_flag	varchar2(5)		NOT NULL,	category	number	        NOT	NULL,    view_cnt    number  DEFAULT 0    );ALTER TABLE toy_board ADD CONSTRAINT PK_toy_BOARD PRIMARY KEY (ID);--카테고리 일반글 10 질문글 20 공지글 50 전체공지 60 CREATE TABLE CATEGORY (	CATEGORY_ID	NUMBER		NOT NULL,	CATEGORY_NAME	VARCHAR2(200)		NULL);ALTER TABLE CATEGORY ADD CONSTRAINT PK_CATEGORY PRIMARY KEY (CATEGORY_ID);--카테고리 테이블 내용      INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME) VALUES (10, '일반');INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME) VALUES (50, '공지');INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME) VALUES (60, '전체공지');INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME) VALUES (20, '질문');CREATE SEQUENCE BOARD_SEQ       INCREMENT BY 1       START WITH 1       MINVALUE 1       NOCYCLE       NOCACHE       NOORDER;