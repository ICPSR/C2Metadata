INSERT INTO OAUTH_CALLBACKS VALUES(2, 'http://localhost:8080/c2metadata/oauth/callback',sysdate);
INSERT INTO OAUTH_CALLBACKS VALUES(2, 'https://c2metadata.dev.icpsr.umich.edu/c2metadata/oauth/callback',sysdate);
INSERT INTO OAUTH_CALLBACKS VALUES(2, 'https://c2metadata.test.icpsr.umich.edu/c2metadata/oauth/callback',sysdate);
INSERT INTO OAUTH_CALLBACKS VALUES(2, 'https://c2metadata.icpsr.umich.edu/c2metadata/oauth/callback',sysdate);

INSERT INTO SYS_AUTH_HOSTS_T VALUES(SYS_AUTH_HOSTS_SEQ.nextval,'c2metadata.dev.icpsr.umich.edu');
INSERT INTO SYS_AUTH_HOSTS_T VALUES(SYS_AUTH_HOSTS_SEQ.nextval,'c2metadata.test.icpsr.umich.edu');
INSERT INTO SYS_AUTH_HOSTS_T VALUES(SYS_AUTH_HOSTS_SEQ.nextval,'c2metadata.icpsr.umich.edu');

create sequence C2M_JOBS_SEQ
	INCREMENT BY 1
	START WITH 1
	NOMAXVALUE
	NOMINVALUE
	NOCYCLE
	NOORDER;
 
create table C2M_JOBS_T (
    ID NUMBER NOT NULL,
    EMAIL VARCHAR2(256), 
 	START_DATE TIMESTAMP, 
	END_DATE TIMESTAMP, 
    ERROR VARCHAR2(1024),
	constraint C2M_JOBS_PK primary key (ID) 
);

create sequence C2M_TASKS_SEQ
	INCREMENT BY 1
	START WITH 1
	NOMAXVALUE
	NOMINVALUE
	NOCYCLE
	NOORDER;

create table C2M_TASKS_T (
    ID NUMBER NOT NULL,
    JOB_ID NUMBER,
 	START_DATE TIMESTAMP, 
	END_DATE TIMESTAMP, 
    ERROR VARCHAR2(1024),
	constraint C2M_TASKS_PK primary key (ID) 
);



